package tests;

import constants.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FirstCardPage;
import pages.HomePage;
import pages.SecondCardPage;
import pages.ThirdCardPage;

public class UserInterfaceTest extends BaseTest {
    @Test
    public void hideHelpFormTest() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.state().waitForDisplayed(), "Home page is not opened");
        homePage.clickLink();
        FirstCardPage firstCardPage = new FirstCardPage();
        firstCardPage.hideHelpForm();
        Assert.assertTrue(firstCardPage.isHelpFormHidden(), "Help form is not hidden");
    }

    @Test
    public void acceptCookiesTest() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.state().waitForDisplayed(), "Home page is not opened");
        homePage.clickLink();
        FirstCardPage firstCardPage = new FirstCardPage();
        firstCardPage.acceptCookies();
        Assert.assertTrue(firstCardPage.areCookiesAccepted(), "Cookies are not accepted");
    }

    @Test
    public void timerTest() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.state().waitForDisplayed(), "Home page is not opened");
        homePage.clickLink();
        FirstCardPage firstCardPage = new FirstCardPage();
        String initialTime = firstCardPage.getTimerValue();
        Assert.assertTrue(initialTime.startsWith(Constants.EXPECTED_INITIAL_TIME), "Timer does not start from 00:00");
    }
}
