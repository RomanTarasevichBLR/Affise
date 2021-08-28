package ui.elements;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Element;
import aquality.selenium.elements.interfaces.IElement;
import org.openqa.selenium.By;

public class RadioOption extends Element implements IElement {

    public RadioOption(By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    protected String getElementType() {
        return this.getLocalizationManager().getLocalizedMessage("loc.RadioOption");
    }

    public void select() {
        this.clickAndWait();
    }
}
