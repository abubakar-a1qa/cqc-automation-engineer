package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WelcomePage;

public class HelpFormTest extends BaseTest {

    @Test
    public void testHideHelpForm() {
        // Test Case 2 - Step 1: Navigate to home page and then game page
        WelcomePage welcomePage = new WelcomePage(driver);
        Assert.assertTrue(welcomePage.isWelcomePageDisplayed(), "Welcome page should be open");
        welcomePage.clickStart();

        // Test Case 2 - Step 2: Hide help form
        welcomePage.waitForHelpForm();
        welcomePage.hideHelpForm();
        Assert.assertTrue(welcomePage.isHelpFormHidden(), "Form content should be hidden");
    }
}
