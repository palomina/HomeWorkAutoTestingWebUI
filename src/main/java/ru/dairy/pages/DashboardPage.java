package ru.dairy.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import ru.dairy.base.BaseView;
import ru.dairy.views.NavigatorBar;

public class DashboardPage extends BaseView {


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void checkUrl(String url) {
        Assertions.assertEquals(url, driver.getCurrentUrl());
    }

    public NavigatorBar getNavigator() {
        return new NavigatorBar(driver);
    }
}
