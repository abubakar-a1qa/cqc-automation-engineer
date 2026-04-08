package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigManager;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl;

    @BeforeMethod
    public void setUp() {
        baseUrl = ConfigManager.getBaseUrl();
        
        // Setup Chrome driver with visible browser
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        driver.get(baseUrl);
        Allure.step("Browser opened and navigated to " + baseUrl);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            captureScreenshot("Failure Screenshot");
        }
        
        if (driver != null) {
            driver.quit();
            Allure.step("Browser closed");
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
