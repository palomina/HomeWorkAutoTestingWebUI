package space.geekbrains.crm.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import space.geekbrains.crm.base.BaseView;
import space.geekbrains.crm.views.NavigatorBar;


public class DashboardPage extends BaseView {


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check Url {url}")
    public void checkUrl(String url) {
        Assertions.assertEquals(url, driver.getCurrentUrl());
    }

    public NavigatorBar getNavigator() {
        return new NavigatorBar(driver);
    }
}
