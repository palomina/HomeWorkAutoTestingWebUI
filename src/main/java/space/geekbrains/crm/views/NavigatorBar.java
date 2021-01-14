package space.geekbrains.crm.views;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import space.geekbrains.crm.base.BaseView;
import space.geekbrains.crm.base.SubMenu;
import space.geekbrains.crm.enums.NavigatorBarTabs;

public class NavigatorBar extends BaseView {

    public NavigatorBar(WebDriver driver) {
        super(driver);
    }

    public SubMenu moveCursorToNavigationTab(NavigatorBarTabs tab) {
        Actions actions = new Actions(driver);
        actions
            .moveToElement(driver.findElement(tab.getBy()))
            .build()
            .perform();
        switch (tab) {
            case CONTRACTORS:
                return new SubMenu(driver);
            case PROJECTS:
                return new SubMenu(driver);
            default:
                throw new IllegalArgumentException("Another tab not implemented");
        }
    }

    public NavigatorBar checkTabVisibility(NavigatorBarTabs tab) {
        Assertions.assertTrue(driver.findElement(tab.getBy()).isDisplayed());
        return this;
    }
}
