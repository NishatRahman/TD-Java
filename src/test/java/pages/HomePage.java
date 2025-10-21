package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HomePage extends Form {
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final ILink link = elementFactory.getLink(By.cssSelector(".start__link"), "Link");

    public HomePage() {
        super(By.cssSelector(".start__link"), "Home Page");
    }

    public void clickLink() {
        link.click();
    }
}
