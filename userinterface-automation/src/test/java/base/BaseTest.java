package base;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
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

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");

        String gridUrl = System.getenv("SELENIUM_GRID_URL");

        if (gridUrl == null || gridUrl.isEmpty()) {
            gridUrl = "http://selenium:4444/wd/hub";
        }

        driver = new RemoteWebDriver(new URL(gridUrl), options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.get(baseUrl);

        Allure.step("Browser started via Selenium Grid: " + gridUrl);
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
