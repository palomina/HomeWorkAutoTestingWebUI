package ru.dairy.pages;

import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.dairy.base.BaseView;

import java.util.List;

public class MyDairyPage extends BaseView {

    @FindBy(css = "p > a:nth-child(2)")
    private WebElement header;

    @FindBy(xpath = "//div[contains(@class, 'first')]//a[@class='delPostLink']")
    private WebElement btnFirstRecordRemove;

    @FindBy(xpath = "//div[contains(@class,'item')]")
    private List<WebElement> records;

    public MyDairyPage(WebDriver driver) {
        super(driver);
    }

    public void checkPage() {
        Assertions.assertEquals("Мой дневник", header.getText());
    }

    public MyDairyPage checkExistsRecords() {
        assert(records.size() > 0);
        return this;
    }

    public AcceptPage clickRemove() {
        btnFirstRecordRemove.click();
        return new AcceptPage(driver);
    }
}
