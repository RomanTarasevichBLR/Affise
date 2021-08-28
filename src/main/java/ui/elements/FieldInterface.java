package ui.elements;

import org.openqa.selenium.By;
import ui.enums.ElementType;

public interface FieldInterface {
    By getFieldLocator();

    String getFieldName();

    ElementType getElementType();
}
