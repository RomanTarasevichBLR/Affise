package ui.page;

import api.model.user.User;
import aquality.selenium.elements.interfaces.IButton;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import ui.elements.FieldInterface;
import ui.enums.ElementType;

import java.util.Arrays;

public class LoginPage extends BasePage {

    private final IButton loginBtn = getElementFactory().getButton(By.cssSelector("button[class*='success']"), "Login button");

    public LoginPage() {
        super(By.id("loginForm"), "Login page");
    }

    public MainPage logInAs(User user) {
        setFieldValue(Fields.EMAIL, user.getEmail());
        setFieldValue(Fields.PASSWORD, user.getPassword());
        clickLoginButton();
        return new MainPage();
    }

    public void clickLoginButton() {
        loginBtn.clickAndWait();
    }

    @Getter
    @AllArgsConstructor
    public enum Fields implements FieldInterface {
        EMAIL("Email", By.id("email"), "email", ElementType.TEXTBOX),
        PASSWORD("Password", By.id("password"), "password", ElementType.TEXTBOX);

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
