package ru.dairy.pages;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.dairy.base.BaseView;

public class AcceptPage extends BaseView {

    @FindBy(name = "yes")
    private WebElement btnYes;

    public AcceptPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click Yes")
    public SuccessRemovePage clickYes() {
        btnYes.click();
        return new SuccessRemovePage(driver);
    }
}
