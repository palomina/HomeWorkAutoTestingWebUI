package space.geekbrains.crm.enums;

import org.openqa.selenium.By;

public enum SubMenu {
    MY_PROJECTS("//*[@id='main-menu']//a[@href='/project/my']"),
    CONTACTS("//a/span[contains(.,'Контактные лица')]");

    private final By by;

    SubMenu(String xpath) {
        this.by = By.xpath(xpath);
    }

    public By getBy() {
        return by;
    }
}
