package ui.page;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import ui.elements.ElementWrapper;
import ui.elements.FieldInterface;

public class BasePage extends Form {

    protected BasePage(By locator, String name) {
        super(locator, name);
        this.state().waitForDisplayed();
        AqualityServices.getBrowser().waitForPageToLoad();
    }

    public String setFieldValue(FieldInterface field, String value, By... optionLocator) {
        return new ElementWrapper(field.getFieldLocator(),
                field.getFieldName(), ElementState.DISPLAYED, field.getElementType(), optionLocator)
                .setValue(value);
    }

    public String getFieldValue(FieldInterface field, By... optionLocator) {
        String value = new ElementWrapper(field.getFieldLocator(),
                field.getFieldName(),
                ElementState.DISPLAYED,
                field.getElementType(),
                optionLocator).getValue();

        return value.isEmpty() ? null : value;
    }
}
