package ui.elements;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Element;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;
import org.openqa.selenium.By;

public class MultiSelectField extends Element implements IElement {

    private final ILabel loadingSpinner = getElementFactory()
            .getLabel(By.xpath("//span[contains(@class,'AsyncMultiSelect-css__loader')]/*"), "Loading");

    public MultiSelectField(By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    public void selectFirstItemByValue(String value) {
        this.clickAndWait();
        this.findChildElement(By.xpath("/input"), ElementType.TEXTBOX).sendKeys(value);
        loadingSpinner.state().waitForNotDisplayed();
        IButton item = getElementFactory()
                .getButton(By.xpath(String.format("//div[contains(@class,'AsyncMultiSelect-css__list-body')]//div[contains(text(),'%s')]", value)),
                "Item to select");
        item.clickAndWait();
    }


    protected String getElementType() {
        return this.getLocalizationManager().getLocalizedMessage("loc.multiSelectField");
    }
}
