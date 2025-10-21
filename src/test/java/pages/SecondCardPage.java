package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import constants.Constants;
import constants.ResourceConstants;
import org.openqa.selenium.By;
import utils.AssetUploader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecondCardPage extends Form {
    private final ILabel cardNumber = AqualityServices.getElementFactory().getLabel(By.xpath("//div[@class='page-indicator']"), "Card Number");
    private final ITextBox uploadImage = AqualityServices.getElementFactory().getTextBox(By.className("avatar-and-interests__upload-button"), "Upload Image");
    private final ICheckBox unselectAll = AqualityServices.getElementFactory().getCheckBox(By.xpath("//label[@for='interest_unselectall']"), "Unselect All");
    private final List<ICheckBox> allCheckboxesList = AqualityServices.getElementFactory().findElements(By.xpath("//label[@class='checkbox__label']"), ElementType.CHECKBOX);
    private final IButton nextButton = AqualityServices.getElementFactory().getButton(By.xpath("//button[text()='Next']"), "Next Button");

    public SecondCardPage() {
        super(By.className("avatar-and-interests"), "Second Card Page");
    }

    public int getCardNumber() {
        return Integer.parseInt(cardNumber.getText().substring(0, 1));
    }

    public void uploadImage() {
        uploadImage.click();
        AssetUploader.uploadImage(ResourceConstants.ABSOLUTE_FILE_PATH_TO_AVATAR);
    }

    public void unselectAllInterests() {
        unselectAll.check();
    }

    public void selectInterests() {
        List<ICheckBox> allCheckboxes = new ArrayList<>(allCheckboxesList);
        List<ICheckBox> randomCheckboxes = new ArrayList<>();
        Random random = new Random();

        while (randomCheckboxes.size() < Constants.MIN_NUMBER_OF_INTERESTS && !allCheckboxes.isEmpty()) {
            int randomIndex = random.nextInt(allCheckboxes.size());
            ICheckBox checkbox = allCheckboxes.get(randomIndex);

            randomCheckboxes.add(checkbox);
            allCheckboxes.remove(randomIndex);
        }

        for (ICheckBox checkbox : randomCheckboxes) {
            checkbox.state().waitForClickable();
            checkbox.click();
        }
    }


    public void clickNextButton() {
        nextButton.click();
    }
}
