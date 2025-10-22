package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ThirdCardPage extends Form {
    private final ILabel cardNumber = AqualityServices.getElementFactory().getLabel(By.xpath("//div[@class='page-indicator']"), "Card Number");

    public ThirdCardPage() {
        super(By.className("personal-details"), "Third Card Page");
    }

    public int getCardNumber() {
        return Integer.parseInt(cardNumber.getText().substring(0, 1));
    }
}
