package pages;

import base.BasePage;
import constants.LocatorConstants;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends BasePage {

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isWelcomePageDisplayed() {
        return isDisplayed(LocatorConstants.NAVIGATE_TO_NEXT_PAGE);
    }

    public void clickStart() {
        click(LocatorConstants.NAVIGATE_TO_NEXT_PAGE);
    }

    public void waitForHelpForm() {
        waitForVisibility(LocatorConstants.HELP_FORM);
    }

    public boolean isHelpFormHidden() {
        return isDisplayed(LocatorConstants.HIDDEN_HELP_FORM);
    }

    public void hideHelpForm() {
        click(LocatorConstants.HELP_FORM_HIDE_BUTTON);
    }

    public void waitForCookiesBanner() {
        waitForPresence(LocatorConstants.COOKIES_BANNER);
    }

    public boolean isCookiesBannerVisible() {
        return exists(LocatorConstants.COOKIES_BANNER) && isDisplayed(LocatorConstants.COOKIES_BANNER);
    }

    public void acceptCookies() {
        click(LocatorConstants.COOKIES_ACCEPT_BUTTON);
        waitForInvisibility(LocatorConstants.COOKIES_BANNER);
    }

    public void acceptCookiesIfPresent() {
        if (exists(LocatorConstants.COOKIES_BANNER)) {
            acceptCookies();
        }
    }

    public String getTimerText() {
        return getText(LocatorConstants.TIMER_ELEMENT);
    }
}
