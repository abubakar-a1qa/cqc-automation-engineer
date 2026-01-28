package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WelcomePage;

@Feature("Timer Functionality")
public class TimerTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify timer starts at 00:00:00")
    public void testTimerStartsAtZero() {
        // Test Case 4 - Step 1: Navigate to home page and then game page
        WelcomePage welcomePage = new WelcomePage(driver);
        Assert.assertTrue(welcomePage.isWelcomePageDisplayed(), "Welcome page should be open");
        welcomePage.clickStart();

        // Test Case 4 - Step 2: Validate that timer starts from "00:00"
        String timerText = welcomePage.getTimerText();
        Assert.assertEquals(timerText.trim(), "00:00:00", "Timer should start at 00:00");
    }
}
