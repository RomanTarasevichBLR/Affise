package ui.page.users.usersmanagement;

import aquality.selenium.elements.interfaces.IButton;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import ui.form.users.usermanagement.UserPermissionsForm;
import ui.form.users.usermanagement.UserProfileForm;
import ui.form.users.usermanagement.UsersListForm;
import ui.page.BasePage;

public class ProfilePage extends BasePage {

    public static final String USER_TAB_TEMPLATE = "//div[contains(@class,'UsersManagement-components-UserForm-TabLink')][text()='%s']";
    private final IButton saveButton = getElementFactory().getButton(By.xpath("//span[contains(@class,'Button-css__title')]"), "Save button");
    public UserPermissionsForm userPermissionsForm;
    public UserProfileForm userProfileForm;
    public UsersListForm usersList;

    public ProfilePage() {
        super(By.xpath("//div[contains(@class,'UsersManagement-components-UserForm-css__container')]"), "Profile page");
    }

    public UserProfileForm getUserProfileForm() {
        userProfileForm = userProfileForm == null ? new UserProfileForm() : userProfileForm;
        return userProfileForm;
    }

    public UserPermissionsForm getUserPermissionsForm() {
        userPermissionsForm = userPermissionsForm == null ? new UserPermissionsForm() : userPermissionsForm;
        return userPermissionsForm;
    }

    public UsersListForm getUsersList() {
        usersList = usersList == null ? new UsersListForm() : usersList;
        return usersList;
    }

    public void openForm(UserTab tab) {
        IButton tabToOpen = getElementFactory().getButton(By.xpath(String.format(USER_TAB_TEMPLATE, tab.getTab())), "Tab to open");
        tabToOpen.clickAndWait();
    }

    public void save() {
        saveButton.clickAndWait();
    }

    @Getter
    @AllArgsConstructor
    public enum UserTab {
        PROFILE("Profile"),
        PERMISSIONS("Permissions"),
        SECTIONS("Sections");

        private final String tab;

    }

}
