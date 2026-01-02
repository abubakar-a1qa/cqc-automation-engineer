package pages;

import base.BasePage;
import constants.LocatorConstants;
import org.openqa.selenium.WebDriver;
import utils.RandomUtils;

public class CardOnePage extends BasePage {

    public CardOnePage(WebDriver driver) {
        super(driver);
    }

    public boolean isFirstCardDisplayed() {
        return isDisplayed(LocatorConstants.FIRST_CARD_INDICATOR);
    }

    public void fillCredentialsAndProceed() {
        String emailName = RandomUtils.randomEmailName();
        String domain = RandomUtils.randomDomain();
        String password = RandomUtils.strongPassword(emailName);

        type(LocatorConstants.PASSWORD_INPUT, password);
        type(LocatorConstants.EMAIL_INPUT, emailName);
        type(LocatorConstants.DOMAIN_INPUT, domain);

        click(LocatorConstants.DROPDOWN);
        click(LocatorConstants.DOT_COM_DOMAIN);

        click(LocatorConstants.TERMS_CHECKBOX);

        click(LocatorConstants.CARD_ONE_NEXT_BUTTON);
    }
}
