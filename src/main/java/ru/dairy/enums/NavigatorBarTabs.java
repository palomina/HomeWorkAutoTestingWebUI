package ru.dairy.enums;

import org.openqa.selenium.By;

public enum NavigatorBarTabs {
    NEW_RECORD("//a[contains(.,'Новая запись')]"),
    MY_DAIRY("//ul[contains(@class,'nav')]/li/a[contains(.,'Мой дневник')]"),
    EXIT("//a[contains(.,'Выход')]");

    private final By by;

    NavigatorBarTabs(String xpath) {
        this.by = By.xpath(xpath);
    }

    public By getBy() {
        return by;
    }
}
