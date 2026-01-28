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

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify user can accept cookies from the cookies banner")
    public void testAcceptCookies() {
        // Test Case 3 - Step 1: Navigate to home page and then game page
        WelcomePage welcomePage = new WelcomePage(driver);
        Assert.assertTrue(welcomePage.isWelcomePageDisplayed(), "Welcome page should be open");
        welcomePage.clickStart();

        // Test Case 3 - Step 2: Accept cookies
        welcomePage.waitForCookiesBanner();
        welcomePage.acceptCookies();
        Assert.assertFalse(welcomePage.isCookiesBannerVisible(), "Form should be closed after accepting cookies");

        Allure.addAttachment("Attachment name", "Attachment Content");
    }
}
