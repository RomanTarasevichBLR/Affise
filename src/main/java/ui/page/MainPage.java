package ui.page;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import org.openqa.selenium.By;
import ui.form.NavigationMenuBar;

import java.util.List;

public class MainPage extends BasePage {

    private final List<ILabel> loading = getElementFactory().findElements(By.cssSelector("div[class*='DotsLoading-stylus']"), ElementType.LABEL);
    public NavigationMenuBar navigationMenuBar;

    public MainPage() {
        super(By.cssSelector("div[class='src-app-routes-Dashboard-css__container']"), "Main page");
    }

    public NavigationMenuBar getNavigationMenuBar() {
        navigationMenuBar = navigationMenuBar == null ? new NavigationMenuBar() : navigationMenuBar;
        return navigationMenuBar;
    }

    private void waitForLoadingCompleted() {
        loading.forEach(dots -> dots.state().waitForNotDisplayed());
    }


}
