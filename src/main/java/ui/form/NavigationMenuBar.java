package ui.form;

import aquality.selenium.elements.interfaces.IButton;
import org.openqa.selenium.By;
import ui.enums.MenuItems;
import ui.page.BasePage;

public class NavigationMenuBar extends BaseForm {

    private static final String FIRST_LEVEL_MENU_ITEM_TEMPLATE = "//div[@class='src-app-components-Navigation-css__container']//*[text()='%s']";
    private static final String SECOND_LEVEL_MENU_ITEM_TEMPLATE = "//div[contains(@class,'menu')]//a[text()='%s']";

    public NavigationMenuBar() {
        super(By.cssSelector("div[class='src-app-components-Navigation-css__container']"), "Navigation menu bar");
    }

    public void openTab(MenuItems firstLevelItem) {
        IButton tab = getElementFactory().getButton(By.xpath(String.format(FIRST_LEVEL_MENU_ITEM_TEMPLATE, firstLevelItem.getItem())),
                "First level tab");
        tab.clickAndWait();
    }

    public <T extends BasePage> T openMenu(MenuItems secondLevelItem, Class<T> instance) {
        T page = null;
        IButton menuItem = getElementFactory().getButton(By.xpath(String.format(SECOND_LEVEL_MENU_ITEM_TEMPLATE, secondLevelItem.getItem())),
                "Second level tab");
        menuItem.clickAndWait();
        try {
            page = instance.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return page;
    }
}
