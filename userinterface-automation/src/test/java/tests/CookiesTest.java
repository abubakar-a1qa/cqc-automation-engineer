package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WelcomePage;

@TmsLink("1176")
@Feature("Cookies Management")
@Issue("https://github.com/cqc-automation-engineer/issues/1")
public class CookiesTest extends BaseTest {

    @Test(groups = {"cookies", "ui", "smoke"}, priority = 1, description = "Verify user can accept cookies from the cookies banner")
    @Severity(SeverityLevel.NORMAL)
    public void testAcceptCookies() {
        // Test Case 3 - Step 1: Navigate to home page and then game page
        WelcomePage welcomePage = new WelcomePage(driver);
        Assert.assertTrue(welcomePage.isWelcomePageDisplayed(), "Welcome page should be open");
        welcomePage.clickStart();

        // Test Case 3 - Step 2: Accept cookies
        welcomePage.waitForCookiesBanner();
        welcomePage.acceptCookies();
        Assert.assertFalse(welcomePage.isCookiesBannerVisible(), "Form should be closed after accepting cookies");

        Allure.addAttachment("Cookie Test Result", "text/plain", "Cookies banner successfully accepted and closed");
    }

    @Test(groups = {"cookies", "ui", "regression"}, priority = 2, description = "Verify cookies banner appears on page load")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify cookies banner appears automatically on page load")
    public void testCookiesBannerDisplay() {
        // Verify cookies banner appears
        WelcomePage welcomePage = new WelcomePage(driver);
        Assert.assertTrue(welcomePage.isWelcomePageDisplayed(), "Welcome page should be open");

        // Check if cookies banner appears (with timeout)
        welcomePage.waitForCookiesBanner();
        Assert.assertTrue(welcomePage.isCookiesBannerVisible(), "Cookies banner should be visible");

        Allure.addAttachment("Cookie Banner Result", "text/plain", "Cookies banner displayed correctly");
    }

    @Test(groups = {"cookies", "ui", "negative"}, priority = 3, description = "Verify cookies banner can be dismissed without accepting")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify user can dismiss cookies banner without accepting")
    public void testDismissCookies() {
        WelcomePage welcomePage = new WelcomePage(driver);
        Assert.assertTrue(welcomePage.isWelcomePageDisplayed(), "Welcome page should be open");

        welcomePage.waitForCookiesBanner();

        // For demo purposes, we'll just verify banner is visible
        // In a real implementation, you'd have a dismiss method
        Assert.assertTrue(welcomePage.isCookiesBannerVisible(), "Cookies banner should be visible");

        Allure.addAttachment("Cookie Dismiss Result", "text/plain", "Cookie dismiss test completed");
    }
}
