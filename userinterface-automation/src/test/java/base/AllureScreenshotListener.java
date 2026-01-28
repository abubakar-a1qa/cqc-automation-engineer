package base;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class AllureScreenshotListener implements ITestListener {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void removeDriver() {
        driverThreadLocal.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = getDriver();
        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Test Failed Screenshot", "image/png", 
                    new ByteArrayInputStream(screenshot), "png");
                Allure.step("Screenshot captured on test failure: " + result.getName());
            } catch (Exception e) {
                Allure.step("Failed to capture screenshot on failure: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.step("Test skipped: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.step("Test passed: " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        Allure.step("Test started: " + result.getName());
    }
}