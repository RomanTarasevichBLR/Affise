package ui.page.users.usersmanagement;

import aquality.selenium.elements.interfaces.IButton;
import org.openqa.selenium.By;
import ui.page.BasePage;

public class UserManagementPage extends BasePage {

    private final IButton createUserBtn = getElementFactory().getButton(By.xpath("//div[contains(@class,'UsersManagement-components-Actions-css')]"),
            "Create user btn");

    public UserManagementPage() {
        super(By.cssSelector("div[class*='UsersManagement-css__container']"), "User management page");
    }

    public ProfilePage addUser() {
        createUserBtn.clickAndWait();
        return new ProfilePage();
    }
}
