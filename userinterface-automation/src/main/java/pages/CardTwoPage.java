package pages;

import base.BasePage;
import constants.LocatorConstants;
import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;
import utils.FileUploadUtils;

public class CardTwoPage extends BasePage {

    public CardTwoPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSecondCardDisplayed() {
        return isDisplayed(LocatorConstants.SECOND_CARD_INDICATOR);
    }

    public void selectInterests() {
        click(LocatorConstants.UNSELECT_ALL);
        click(LocatorConstants.INTEREST_PURPLE);
        click(LocatorConstants.INTEREST_WINDOWS);
        click(LocatorConstants.INTEREST_QUESTIONS);
    }

    public void clickNextOnPagCardTwoPage() {
        click(LocatorConstants.CARD_TWO_NEXT_BUTTON);
    }

    public void uploadImageUsingExplorer() {
        uploadImageUsingExplorer(Paths.get("src", "test", "resources", "test-pages", "avatar.png").toString());
    }
    public void uploadImageUsingExplorer(String relativePath) {
        click(LocatorConstants.IMAGE_UPLOAD_BUTTON);

        String imagePath = Paths.get(relativePath).toAbsolutePath().toString();
        FileUploadUtils.uploadFileViaExplorer(imagePath);
    }
}
