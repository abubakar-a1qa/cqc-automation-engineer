package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigManager;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected String baseUrl;

    @BeforeMethod
    public void setUp() throws Exception {

        baseUrl = ConfigManager.getBaseUrl();

ChromeOptions options = new ChromeOptions();

        // For local development, remove headless mode to see browser
        // For CI/CD, keep headless mode
        String isHeadless = System.getenv("HEADLESS_MODE");
        if (isHeadless == null || isHeadless.equals("false")) {
            options.addArguments("--disable-gpu");
        } else {
            options.addArguments("--headless=new");
        }
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        String gridUrl = System.getenv("SELENIUM_GRID_URL");
        String isLocal = System.getenv("LOCAL_MODE");

        // Check if running locally or with Selenium Grid
        if (isLocal != null && isLocal.equals("true")) {
            // Local mode - use ChromeDriver directly
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            Allure.step("Browser started locally in visible mode");
        } else {
            // Grid mode - use RemoteWebDriver
            if (gridUrl == null || gridUrl.isEmpty()) {
                gridUrl = "http://selenium:4444/wd/hub";
            }
            driver = new RemoteWebDriver(new URL(gridUrl), options);
            Allure.step("Browser started via Selenium Grid: " + gridUrl);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (!result.isSuccess()) {
            captureScreenshot("Failure Screenshot");
        }

        if (driver != null) {
            driver.quit();
        }
    }

    protected void captureScreenshot(String screenshotName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    screenshotName,
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    "png"
            );

        } catch (Exception e) {
            System.out.println("Screenshot error: " + e.getMessage());
        }
    }
}
