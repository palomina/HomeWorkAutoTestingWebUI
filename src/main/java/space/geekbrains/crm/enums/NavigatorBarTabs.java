package space.geekbrains.crm.enums;

import org.openqa.selenium.By;

public enum NavigatorBarTabs {
    CONTRACTORS("//a/span[contains(.,'Контрагенты')]"),
    PROJECTS("//*[@id='main-menu']/ul/li[3]/a");

    private final By by;

    NavigatorBarTabs(String xpath) {
        this.by = By.xpath(xpath);
    }

    public By getBy() {
        return by;
    }
}
