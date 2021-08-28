package ui.form.users.usermanagement;

import aquality.selenium.elements.interfaces.IButton;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import ui.elements.FieldInterface;
import ui.enums.ElementType;
import ui.form.BaseForm;

import java.util.Arrays;

public class UserPermissionsForm extends BaseForm {

    private static final String PERMISSION_GROUP_TEMPLATE = "//span[contains(@class,'PermissionsSelector-components-Scopes')]/span[text()='%s']";
    private static final String PERMISSION_ITEM_TEMPLATE = "//span[contains(@class,'Permissions-css__item')]//span[text()='%s']";

    public UserPermissionsForm() {
        super(By.cssSelector("div[class*='UserForm-Permissions-css__container']"), "User permissions form");
    }

    public void selectPermissionGroup(PermissionGroup group) {
        IButton groupToSelect = getElementFactory().getButton(By.xpath(String.format(PERMISSION_GROUP_TEMPLATE, group.getGroup())), "Permission group");
        groupToSelect.clickAndWait();
    }

    public void selectItemFromGroup(PermissionsItems item) {
        IButton itemToSelect = getElementFactory().getButton(By.xpath(String.format(PERMISSION_ITEM_TEMPLATE, item.getItem())), "Permission item");
        itemToSelect.clickAndWait();
    }

    public void selectLevel(PermissionLevel level) {
        setFieldValue(level, "true");
    }

    public void selectDeny(String text) {
        setFieldValue(PermissionLevel.DENY_MULTI_SELECT, text);
    }

    @Getter
    @AllArgsConstructor
    public enum PermissionGroup {
        AUTOMATION("Automation"),
        GENERAL("General"),
        NOTIFICATOR("Notificator"),
        STATISTICS("Statistics"),
        USERS("Users");

        private final String group;
    }

    @Getter
    @AllArgsConstructor
    public enum PermissionsItems {

        AFFISE_CHECKER("Affise checker"),

        MARKETPLACE("Marketplace"),
        SETTINGS("Settings"),

        AFFILIATES_EDITING("Affiliates editing"),
        USERS_LIST("Users list");

        private final String item;
    }

    @Getter
    @AllArgsConstructor
    public enum PermissionLevel implements FieldInterface {
        READ("Read", By.xpath("//div[contains(@class,'Radio')][.//span[text()='Read']]"), null, ElementType.RADIO_OPTION),
        WRITE("Write", By.xpath("//div[contains(@class,'Radio')][.//span[text()='Write']]"), null, ElementType.RADIO_OPTION),
        DENY("Deny", By.xpath("//div[contains(@class,'Radio')][.//span[text()='Deny']]"), null, ElementType.RADIO_OPTION),
        DENY_MULTI_SELECT("Deny multi select",
                By.xpath("//div[contains(@class,'AsyncMultiSelect')][.//span[text()='Deny']]//div[contains(@class,'results')]"),
                null, ElementType.MULTI_SELECT);


        private final String fieldName;
        private final By fieldLocator;
        private final String modelField;
        private final ElementType elementType;

        public static PermissionLevel getEnumValue(String fieldName) {
            return Arrays.stream(PermissionLevel.values())
                    .filter(v -> v.getFieldName().equals(fieldName))
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);
        }
    }



}
