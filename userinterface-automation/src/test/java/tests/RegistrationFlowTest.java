package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CardOnePage;
import pages.CardThreePage;
import pages.CardTwoPage;
import pages.WelcomePage;

@Feature("Registration Flow")
public class RegistrationFlowTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify complete registration flow from welcome page through all cards")
    public void testCompleteRegistrationFlow() {
        // Test Case 1 - Step 1: Navigate to home page
        WelcomePage welcomePage = new WelcomePage(driver);
        Assert.assertTrue(welcomePage.isWelcomePageDisplayed(), "Welcome page should be open");

        // Test Case 1 - Step 2: Click the link to navigate to next page
        welcomePage.acceptCookiesIfPresent();
        welcomePage.clickStart();
        CardOnePage cardOnePage = new CardOnePage(driver);
        Assert.assertTrue(cardOnePage.isFirstCardDisplayed(), "Card 1 should be open after clicking next link");

        // Test Case 1 - Step 3: Input credentials, accept terms and click next
        cardOnePage.fillCredentialsAndProceed();
        CardTwoPage cardTwoPage = new CardTwoPage(driver);
        Assert.assertTrue(cardTwoPage.isSecondCardDisplayed(), "Card 2 should be open after submitting credentials");

        // Test Case 1 - Step 4: Choose interests, upload image and click next
        cardTwoPage.selectInterests();
        cardTwoPage.uploadImageUsingExplorer();
        cardTwoPage.clickNextOnPagCardTwoPage();

        CardThreePage cardThreePage = new CardThreePage(driver);
        Assert.assertTrue(cardThreePage.isDisplayed(), "Card 3 should be open after selecting interests and uploading image");
    }
}
