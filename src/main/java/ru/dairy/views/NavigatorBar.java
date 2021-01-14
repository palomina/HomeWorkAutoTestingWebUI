package ru.dairy.views;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.dairy.base.BaseView;
import ru.dairy.enums.NavigatorBarTabs;
import ru.dairy.pages.MyDairyPage;
import ru.dairy.pages.NewRecordPage;

public class NavigatorBar extends BaseView {

    public NavigatorBar(WebDriver driver) {
        super(driver);
    }

    public BaseView moveCursorToNavigationTab(NavigatorBarTabs tab) {
        Actions actions = new Actions(driver);
        actions
            .moveToElement(driver.findElement(tab.getBy()))
            .click()
            .build()
            .perform();
        switch (tab) {
            case NEW_RECORD:
                return new NewRecordPage(driver);
            case MY_DAIRY:
                return new MyDairyPage(driver);
            default:
                throw new IllegalArgumentException("Another tab not implemented");
        }
    }

    public NavigatorBar checkTabVisibility(NavigatorBarTabs tab) {
        Assertions.assertTrue(driver.findElement(tab.getBy()).isDisplayed());
        return this;
    }
}
