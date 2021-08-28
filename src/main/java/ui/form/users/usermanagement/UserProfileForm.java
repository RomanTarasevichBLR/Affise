package ui.form.users.usermanagement;

import api.model.user.User;
import api.utility.DataGenerator;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import ui.elements.FieldInterface;
import ui.enums.ElementType;
import ui.form.BaseForm;
import utility.DataReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class UserProfileForm extends BaseForm {

    private static final String ADD_FILE_LOCATOR = "//input[@type='file']";
    private final IButton applyBtn = getElementFactory().getButton(By.xpath("//button[contains(@class,'submit')]"), "Apply button");

    public UserProfileForm() {
        super(By.cssSelector("div[class*='UserForm-Profile-css__container']"), "User profile form");
    }

    public User createUserWithAvatar(String fileName, Fields userType) {
        User userData = DataGenerator.generateUserDataOnlyRequiredFields();
        setFieldValue(Fields.EMAIL, userData.getEmail());
        setFieldValue(Fields.PASSWORD,userData.getPassword());
        setFieldValue(Fields.NAME, userData.getFirst_name());
        setFieldValue(Fields.SURNAME, userData.getLast_name());
        userData.setAvatar(uploadAvatarAndAddToUser(fileName));
        apply();
        userData.setType(setFieldValue(userType, "true"));
        return userData;
    }

    private String encodedImageToString(String path) {
        String encodedFile = null;
        File file = new File(path);
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            encodedFile = new String(Base64.encodeBase64(bytes), StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return encodedFile;
    }

    public String uploadAvatarAndAddToUser(String fileName) {
        String path = DataReader.getPathForTestData(fileName);
        AqualityServices.getBrowser().getDriver().findElement(By.xpath(ADD_FILE_LOCATOR)).sendKeys(path);
        Actions actions = new Actions(AqualityServices.getBrowser().getDriver());
        actions.clickAndHold(AqualityServices.getBrowser().getDriver().findElement(By.cssSelector("span[data-action='br']"))); // right bottom dot click and hold
        actions.moveToElement(AqualityServices.getBrowser()
                .getDriver().findElement(By.xpath("//button[contains(@class,'submit')]")), 5, 5);  // mouse move to apply button
        actions.perform();
        actions.release(AqualityServices.getBrowser()
                .getDriver().findElement(By.xpath("//button[contains(@class,'submit')]")));
        actions.perform();
        return encodedImageToString(path);
    }

    public void apply() {
        applyBtn.clickAndWait();
    }

    @Getter
    @AllArgsConstructor
    public enum Fields implements FieldInterface {
        EMAIL("Email", By.cssSelector("input[placeholder='username@example.com']"), "email", ElementType.TEXTBOX),
        PASSWORD("Password", By.cssSelector("input[placeholder='6 and more symbols']"), "password", ElementType.TEXTBOX),
        NAME("Name", By.cssSelector("input[placeholder='Example']"), "first_name", ElementType.TEXTBOX),
        SURNAME("Surname", By.cssSelector("input[placeholder='User']"), "last_name", ElementType.TEXTBOX),
        GENERAL_MANAGER("General manager", By.xpath("//div[contains(@class,'Radio')][.//span[text()='General Manager']]"), "type", ElementType.RADIO_OPTION);

        private final String fieldName;
        private final By fieldLocator;
        private final String modelField;
        private final ElementType elementType;

        public static Fields getEnumValue(String fieldName) {
            return Arrays.stream(Fields.values())
                    .filter(v -> v.getFieldName().equals(fieldName))
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);
        }
    }
}
