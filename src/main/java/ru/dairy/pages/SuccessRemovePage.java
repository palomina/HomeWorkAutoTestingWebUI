package ru.dairy.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.dairy.base.BaseView;

public class SuccessRemovePage extends BaseView {

    @FindBy(xpath = "//p[@class='h1']")
    private WebElement header;

    public SuccessRemovePage(WebDriver driver) {
        super(driver);
    }

    public void checkSuccess() {
        Assertions.assertEquals("Запись удалена", header.getText());
    }
}
