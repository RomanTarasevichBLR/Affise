package ui.elements;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Element;
import aquality.selenium.elements.interfaces.*;
import org.openqa.selenium.By;
import ui.enums.ElementType;

public class ElementWrapper extends Element implements IElement {

    private final ElementType elementType;
    private final By[] optionLocator;
    private static final String EXCEPTION_MESSAGE = "Unexpected value: ";

    public ElementWrapper(By locator, String name, ElementState state, ElementType elementType, By... optionLocator) {
        super(locator, name, state);
        this.elementType = elementType;
        this.optionLocator = optionLocator;
    }

    protected String getElementType() {
        return this.getLocalizationManager().getLocalizedMessage("loc.ElementWrapper");
    }


    public String setValue(String value) {
        switch (elementType) {
            case TEXTBOX:
                getElementFactory().getTextBox(getLocator(), getName()).clearAndType(value);
                break;

            case COMBOBOX:
                IComboBox comboBox = getElementFactory().getComboBox(getLocator(), getName());
                comboBox.selectByText(value);
                break;

            case MULTI_SELECT:
                new MultiSelectField(getLocator(), getName(), ElementState.DISPLAYED).selectFirstItemByValue(value);
                break;

            case CHECKBOX:
                ICheckBox checkBox = getElementFactory().getCheckBox(getLocator(), getName());
                boolean shouldBeChecked = Boolean.parseBoolean(value);
                boolean isChecked = this.getElement().findElement(By.xpath("./input")).isSelected();

                if (shouldBeChecked != isChecked) {
                    checkBox.check();
                }
                break;

            case RADIO_OPTION:
                getElementFactory().getCustomElement(RadioOption.class, getLocator(), getName()).select();
                break;

            default:
                throw new IllegalStateException(EXCEPTION_MESSAGE + getName());
        }
        return value;
    }

    public String getValue() {
        switch (elementType) {
            case TEXTBOX:
            case TEXT_DROPDOWN:
                return getElementFactory().getTextBox(getLocator(), getName()).getValue();

            case COMBOBOX:
                return getElementFactory().getComboBox(getLocator(), getName()).getSelectedText();

            case LABEL:
                return getElementFactory().getLabel(getLocator(), getName()).getText();

            default:
                throw new IllegalStateException(EXCEPTION_MESSAGE + getName());
        }
    }

    public boolean isFieldEnabled() {
        boolean value;
        switch (elementType) {
            case TEXTBOX:
                ITextBox textBox = getElementFactory().getTextBox(getLocator(), getName());
                value = textBox.state().isEnabled();
                break;

            case COMBOBOX:
                IComboBox comboBox = getElementFactory().getComboBox(getLocator(), getName());
                value = comboBox.state().isEnabled();
                break;

            case RADIO_OPTION:
                IRadioButton radioButton = getElementFactory().getRadioButton(getLocator(), getName());
                value = radioButton.state().isEnabled();
                break;

            case CHECKBOX:
                ICheckBox checkBox = this.findChildElement(By.xpath("./input"), aquality.selenium.elements.ElementType.CHECKBOX);
                value = checkBox.state().isClickable();
                break;

            default:
                throw new IllegalStateException(EXCEPTION_MESSAGE + getName());
        }

        return value;
    }

}
