package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(screenshotName, "image/png", new ByteArrayInputStream(screenshot), "png");
            Allure.step("Screenshot captured: " + screenshotName);
        } catch (Exception e) {
            Allure.step("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
