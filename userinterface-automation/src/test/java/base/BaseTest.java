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

        ChromeOptions options = new ChromeOptions();

        // =========================
        // CI + LOCAL SAFE CONFIG
        // =========================
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        // =========================
        // CHROME BINARY HANDLING (CI SAFE)
        // =========================
        String chromeBinary = System.getenv("CHROME_BIN");

        if (chromeBinary != null && !chromeBinary.isEmpty()) {
            options.setBinary(chromeBinary);
            Allure.step("Using Chrome binary from CI: " + chromeBinary);
        } else {
            Allure.step("Using system default Chrome (local execution)");
        }

        // =========================
        // DRIVER SETUP (FIXED)
        // =========================
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);

        // =========================
        // TIMEOUTS
        // =========================
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.get(baseUrl);

        Allure.step("Browser opened and navigated to: " + baseUrl);
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

            Allure.step("Screenshot captured: " + screenshotName);

        } catch (Exception e) {
            Allure.step("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
