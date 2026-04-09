package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks extends BaseTest {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @Before
    public void setUp(Scenario scenario) {
        super.setUp();
        driverThreadLocal.set(driver);
        scenario.attach("Driver initialized for scenario: " + scenario.getName(), "text/plain", "setup");
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                try {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "screenshot_on_failure");
                } catch (Exception e) {
                    scenario.attach("Failed to take screenshot: " + e.getMessage(), "text/plain", "error");
                }
            }

            scenario.attach("Driver cleanup for scenario: " + scenario.getName(), "text/plain", "teardown");
        } finally {
            driverThreadLocal.remove();
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
