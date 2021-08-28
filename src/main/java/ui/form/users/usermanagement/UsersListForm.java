package ui.form.users.usermanagement;

import api.model.user.User;
import aquality.selenium.elements.interfaces.ILabel;
import org.openqa.selenium.By;
import ui.form.BaseForm;

public class UsersListForm extends BaseForm {

    private static final String USER_ITEM_TEMPLATE = "//div[contains(@class,'UserItem')][.//span[contains(@class,'name')][text()='%s %s']]";

    public UsersListForm() {
        super(By.xpath("div[class='src-app-routes-UsersManagement-css__list']"), "Users list");
    }

    public boolean isUserItemDisplayed(User user) {
        ILabel userItem = getElementFactory().getLabel(By.xpath(String.format(USER_ITEM_TEMPLATE, user.getFirst_name(), user.getLast_name())), "User item");
        return userItem.state().waitForDisplayed();
    }
}
