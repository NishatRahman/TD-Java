package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import constants.Constants;
import org.openqa.selenium.By;
import utils.RandomInputGenerator;

import java.util.Map;

public class FirstCardPage extends Form {
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ILabel cardNumber = elementFactory.getLabel(By.xpath("//div[@class='page-indicator']"), "Card Number");
    private final ITextBox passwordTxb = elementFactory.getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "Password");
    private final ITextBox emailTxb = elementFactory.getTextBox(By.xpath("//input[@placeholder='Your email']"), "Email");
    private final ITextBox domainTxb = elementFactory.getTextBox(By.xpath("//input[@placeholder='Domain']"), "Domain");
    private final IButton dropDownOpenerBtn = elementFactory.getButton(By.className("dropdown__field"), "Dropdown Opener");
    private final ILabel dotcomDropdownListItem = elementFactory.getLabel(By.xpath("//div[@class='dropdown__list-item' and contains(text(), 'com')]"), "Dropdown List");
    private final ICheckBox checkBox = elementFactory.getCheckBox(By.className("checkbox__box"), "Checkbox");
    private final IButton nextButton = elementFactory.getButton(By.className("button--secondary"), "Next Button");
    private final IButton helpButton = elementFactory.getButton(By.xpath("//span[contains(text(), 'Send')]"), "Help Button");
    private final IButton acceptCookiesBtn = elementFactory.getButton(By.xpath("//button[contains(text(), 'Not really')]"), "Accept Cookies Button");
    private final ILabel timer = elementFactory.getLabel(By.xpath("//div[@class='timer timer--white timer--center']"), "Timer");

    public FirstCardPage() {
        super(By.className("login-form"), "First Card Page");
    }

    public int getCardNumber() {
        return Integer.parseInt(cardNumber.getText().substring(0, 1));
    }

    public void inputRandomValidPassword(String randomPassword) {
        passwordTxb.clearAndType(randomPassword);
    }

    public void inputRandomValidEmail(String randomEmail) {
        emailTxb.clearAndType(randomEmail);
    }

    public void inputRandomDomainName(String domain) {
        domainTxb.clearAndType(domain);
    }

    public void chooseDomain() {
        dropDownOpenerBtn.click();
        dotcomDropdownListItem.click();
    }

    public void acceptTermsOfUse() {
        checkBox.check();
    }

    public void fillLoginForm() {
        Map<String, String> credentials = RandomInputGenerator.generateCredentials(Constants.MIN_PASSWORD_LENGTH);
        inputRandomValidPassword(credentials.get("Password"));
        inputRandomValidEmail(credentials.get("Email"));
        inputRandomDomainName(credentials.get("Domain"));
        chooseDomain();
        acceptTermsOfUse();
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void hideHelpForm() {
        helpButton.click();
    }

    public boolean isHelpFormHidden() {
        return helpButton.state().waitForNotDisplayed();
    }

    public void acceptCookies() {
        acceptCookiesBtn.click();
    }

    public boolean areCookiesAccepted() {
        return acceptCookiesBtn.state().waitForNotDisplayed();
    }

    public String getTimerValue() {
        timer.state().waitForDisplayed();
        return timer.getText();
    }
}
