import api.model.user.User;
import api.utility.DataGenerator;
import aquality.selenium.browser.AqualityServices;
import configuration.Config;
import org.testng.Assert;
import org.testng.annotations.*;
import ui.enums.MenuItems;
import ui.form.users.usermanagement.UserPermissionsForm;
import ui.form.users.usermanagement.UserProfileForm;
import ui.page.LoginPage;
import ui.page.users.usersmanagement.ProfilePage;
import ui.page.users.usersmanagement.UserManagementPage;

import java.util.Arrays;
import java.util.Map;

@Listeners(listener.TestListener.class)
public class UserTests extends BaseUsersTests {

    private User user;

    @BeforeClass
    public void openApp() {
        AqualityServices.getBrowser().goTo(Config.getUrl(Config.START_URL));
        loginPage = new LoginPage();
    }

    @BeforeTest
    public void createNewUserAndCheckThatHeIsCreatedOk() {
        user = DataGenerator.generateUserDataOnlyRequiredFieldsWithRoles(Arrays.asList("ROLE_ADMIN"));  //prepare random user data with ROLE_ADMIN role
        Map<String, Object> params = prepareParamsForNewUserWithRequired(user);   // prepare method params with data from previous step
        response = userRequest.createNewUser(params);    //create new user
        statusCodeIs(200); // check status code
        response = userRequest.returnListOfAllUsers(); // return list of users from 1st page
        statusCodeIs(200);
        Assert.assertTrue(getAllListUsers().stream()
                .anyMatch(userFromList -> userFromList.getEmail().equals(user.getEmail())),
                        String.format("User with email %s should be present in users list", user.getEmail()));
    }

    @Test
    public void createNewUserViaUI() {
        mainPage = loginPage.logInAs(user);
        mainPage.getNavigationMenuBar().openTab(MenuItems.USERS);
        userManagementPage = mainPage.getNavigationMenuBar().openMenu(MenuItems.USERS_MANAGEMENT, UserManagementPage.class);
        profilePage = userManagementPage.addUser();
        User createUserViaUI = profilePage.getUserProfileForm().createUserWithAvatar("lesley.jpg", UserProfileForm.Fields.GENERAL_MANAGER);
        profilePage.openForm(ProfilePage.UserTab.PERMISSIONS);
        profilePage.getUserPermissionsForm().selectPermissionGroup(UserPermissionsForm.PermissionGroup.GENERAL);
        profilePage.getUserPermissionsForm().selectItemFromGroup(UserPermissionsForm.PermissionsItems.MARKETPLACE);
        profilePage.getUserPermissionsForm().selectLevel(UserPermissionsForm.PermissionLevel.READ);
        profilePage.getUserPermissionsForm().selectPermissionGroup(UserPermissionsForm.PermissionGroup.USERS);
        profilePage.getUserPermissionsForm().selectItemFromGroup(UserPermissionsForm.PermissionsItems.AFFILIATES_EDITING);
        profilePage.getUserPermissionsForm().selectDeny("iktest");
        profilePage.save();
        Assert.assertTrue(profilePage.getUsersList().isUserItemDisplayed(createUserViaUI),
                String.format("User %s %s with email %s should be present in Users list",
                        createUserViaUI.getFirst_name(), createUserViaUI.getLast_name(), createUserViaUI.getEmail()));

        //check that user was created via API call
        response = userRequest.returnListOfAllUsers();
        statusCodeIs(200);
        Assert.assertTrue(getAllListUsers().stream()
                        .anyMatch(userFromList -> userFromList.getEmail().equals(createUserViaUI.getEmail())),
                String.format("User with email %s should be present in users list", createUserViaUI.getEmail()));
    }

    @AfterSuite
    public void closeBrowser() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}
