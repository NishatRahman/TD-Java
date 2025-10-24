package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public abstract class BaseTest {
    @BeforeMethod
    public void setup() {
        getBrowser().maximize();
        getBrowser().goTo("https://userinyerface.com/");
    }

    @AfterMethod
    public void teardown() {
        if (AqualityServices.isBrowserStarted()) {
            getBrowser().quit();
        }
    }
}
