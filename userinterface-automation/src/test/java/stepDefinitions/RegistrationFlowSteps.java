package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.CardOnePage;
import pages.CardThreePage;
import pages.CardTwoPage;
import pages.WelcomePage;

public class RegistrationFlowSteps {

    private WelcomePage welcomePage;
    private CardOnePage cardOnePage;
    private CardTwoPage cardTwoPage;

    @Given("the user is on the welcome page")
    public void theUserIsOnTheWelcomePage() {
        WebDriver driver = Hooks.getDriver();
        welcomePage = new WelcomePage(driver);
        Assert.assertTrue(welcomePage.isWelcomePageDisplayed(), "Welcome page should be open");
    }

    @When("the user accepts cookies if present")
    public void theUserAcceptsCookiesIfPresent() {
        welcomePage.acceptCookiesIfPresent();
    }

    @When("the user clicks the start button")
    public void theUserClicksTheStartButton() {
        welcomePage.clickStart();
    }

    @Then("the user should be on card one page")
    public void theUserShouldBeOnCardOnePage() {
        WebDriver driver = Hooks.getDriver();
        cardOnePage = new CardOnePage(driver);
        Assert.assertTrue(cardOnePage.isFirstCardDisplayed(), "Card 1 should be open after clicking next link");
    }

    @When("the user fills credentials and proceeds")
    public void theUserFillsCredentialsAndProceeds() {
        cardOnePage.fillCredentialsAndProceed();
    }

    @Then("the user should be on card two page")
    public void theUserShouldBeOnCardTwoPage() {
        WebDriver driver = Hooks.getDriver();
        cardTwoPage = new CardTwoPage(driver);
        Assert.assertTrue(cardTwoPage.isSecondCardDisplayed(), "Card 2 should be open after submitting credentials");
    }

    @When("the user selects interests")
    public void theUserSelectsInterests() {
        cardTwoPage.selectInterests();
    }

    @When("the user uploads an image using explorer")
    public void theUserUploadsAnImageUsingExplorer() {
        cardTwoPage.uploadImageUsingExplorer();
    }

    @When("the user clicks next on card two page")
    public void theUserClicksNextOnCardTwoPage() {
        cardTwoPage.clickNextOnPagCardTwoPage();
    }

    @Then("the user should be on card three page")
    public void theUserShouldBeOnCardThreePage() {
        WebDriver driver = Hooks.getDriver();
        CardThreePage cardThreePage = new CardThreePage(driver);
        Assert.assertTrue(cardThreePage.isDisplayed(), "Card 3 should be open after selecting interests and uploading image");
    }
}
