package constants;

import org.openqa.selenium.By;

public final class LocatorConstants {

    private LocatorConstants() {
    }

    // Welcome / navigation
    public static final By NAVIGATE_TO_NEXT_PAGE = By.xpath("//*[contains(@class, 'start__link')]");

    // Page indicators
    public static final By FIRST_CARD_INDICATOR = By.xpath("//*[contains(@class, 'page-indicator') and contains(text(), '1 / 4')]");
    public static final By SECOND_CARD_INDICATOR = By.xpath("//*[contains(@class, 'page-indicator') and contains(text(), '2 / 4')]");
    public static final By THIRD_CARD_INDICATOR = By.xpath("//*[contains(@class, 'page-indicator') and contains(text(), '3 / 4')]");

    // Card One (credentials)
    public static final By PASSWORD_INPUT = By.xpath("//input[contains(@class, 'input') and contains(@placeholder, 'Choose Password') and contains(@type, 'text')]");
    public static final By EMAIL_INPUT = By.xpath("//input[contains(@class, 'input') and contains(@placeholder, 'Your email') and contains(@type, 'text')]");
    public static final By DOMAIN_INPUT = By.xpath("//input[contains(@class, 'input') and contains(@placeholder, 'Domain') and contains(@type, 'text')]");
    public static final By DROPDOWN = By.xpath("//div[@class='dropdown__field' and text()='other']");
    public static final By DOT_COM_DOMAIN = By.xpath("//*[text()='.com']");
    public static final By TERMS_CHECKBOX = By.xpath("//span[contains(@class, 'icon-check') and contains(@class, 'checkbox__check')]");
    public static final By CARD_ONE_NEXT_BUTTON = By.xpath("//*[@class='button--secondary' and text()='Next']");

    // Card Two (interests & upload)
    public static final By IMAGE_UPLOAD_BUTTON = By.xpath("//a[contains(@class,'avatar-and-interests__upload-button')]");
    public static final By UNSELECT_ALL = By.xpath("//label[@for='interest_unselectall']");
    public static final By INTEREST_PURPLE = By.xpath("//label[@for='interest_purple' and contains(@class, 'checkbox__label')]");
    public static final By INTEREST_WINDOWS = By.xpath("//label[@for='interest_windows' and contains(@class, 'checkbox__label')]");
    public static final By INTEREST_QUESTIONS = By.xpath("//label[@for='interest_questions' and contains(@class, 'checkbox__label')]");
    public static final By CARD_TWO_NEXT_BUTTON = By.xpath("//button[contains(@class, 'button--stroked') and contains(@class, 'button--fluid') and text()='Next']");

    // Help form and cookies (welcome page)
    public static final By HELP_FORM = By.xpath("//div[contains(@class,'help-form')]");
    public static final By HELP_FORM_HIDE_BUTTON = By.xpath("//button[contains(@class,'help-form__send-to-bottom-button')]");
    public static final By HIDDEN_HELP_FORM = By.xpath("//div[contains(@class,'help-form') and contains(@class,'is-hidden')]");
    public static final By COOKIES_BANNER = By.xpath("//div[contains(@class,'cookies')]");
    public static final By COOKIES_ACCEPT_BUTTON = By.xpath("//button[normalize-space()='Not really, no']");
    public static final By TIMER_ELEMENT = By.xpath("//div[contains(@class,'timer')]");
}
