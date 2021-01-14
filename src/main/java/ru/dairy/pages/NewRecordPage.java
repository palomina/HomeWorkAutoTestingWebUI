package ru.dairy.pages;

import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ru.dairy.base.BaseView;

public class NewRecordPage extends BaseView {

    @FindBy(id = "postTitle")
    private WebElement inputPostTitle;

    @FindBy(id = "message")
    private WebElement inputMessage;

    @FindBy(id = "closedPost")
    private WebElement closedPost;

    @FindBy(id = "closeaccessmode7")
    private WebElement closeaccessmode7;

    @FindBy(id = "rewrite")
    private WebElement btnSave;

    public NewRecordPage enterTitle(String title) {
        inputPostTitle.sendKeys(title);
        return this;
    }

    public NewRecordPage enterMessage(String message) {
        inputMessage.sendKeys(message);
        return this;
    }

    public NewRecordPage setClosePost() {
        closedPost.click();
        return this;
    }

    public NewRecordPage setAccessMode() {
        closeaccessmode7.click();
        return this;
    }

    public MyDairyPage clickSave() {
        btnSave.click();
//        wait.until(ExpectedConditions.stalenessOf(btnSave));
        return new MyDairyPage(driver);
    }

    public NewRecordPage checkFilling(String title, String message) {
        Assertions.assertEquals(inputPostTitle.getAttribute("value"), title);
        Assertions.assertEquals(inputMessage.getAttribute("value"), message);
        Assertions.assertTrue(closedPost.isSelected());
        Assertions.assertTrue(closeaccessmode7.isSelected());
        return this;
    }

    public NewRecordPage(WebDriver driver) {
        super(driver);
    }
}
