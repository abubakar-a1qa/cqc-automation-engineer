package pages;

import base.BasePage;
import constants.LocatorConstants;
import org.openqa.selenium.WebDriver;

public class CardThreePage extends BasePage {

    public CardThreePage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return isDisplayed(LocatorConstants.THIRD_CARD_INDICATOR);
    }
}
