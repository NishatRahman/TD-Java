package tests;

import constants.Constants;
import enums.PageNumber;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FirstCardPage;
import pages.HomePage;
import pages.SecondCardPage;
import pages.ThirdCardPage;

public class UserInterfaceTest extends BaseTest {
    @Test
    public void userInterfaceTest() {
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.state().waitForDisplayed(), "Home page is not opened");
        homePage.clickLink();
        FirstCardPage firstCardPage = new FirstCardPage();
        Assert.assertTrue(firstCardPage.state().waitForDisplayed(), "First card page is not opened");
        Assert.assertEquals(firstCardPage.getCardNumber(), PageNumber.FIRST_CARD.getNumber(), "Page indicator is not correct");
        firstCardPage.fillLoginForm();
        firstCardPage.clickNextButton();
        SecondCardPage secondCardPage = new SecondCardPage();
        Assert.assertTrue(secondCardPage.state().waitForDisplayed(), "Second card page is not opened");
        Assert.assertEquals(secondCardPage.getCardNumber(), PageNumber.SECOND_CARD.getNumber(), "Page indicator is not correct");
        secondCardPage.unselectAllInterests();
        secondCardPage.selectInterests();
        secondCardPage.uploadImage();
        secondCardPage.clickNextButton();
        ThirdCardPage thirdCardPage = new ThirdCardPage();
        Assert.assertTrue(thirdCardPage.state().waitForDisplayed(), "Third card page is not opened");
        Assert.assertEquals(thirdCardPage.getCardNumber(), PageNumber.THIRD_CARD.getNumber(), "Page indicator is not correct");
    }

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
