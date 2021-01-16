package space.geekbrains.crm.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import space.geekbrains.crm.base.BaseView;

public class AllContactsPage extends BaseView {

    @FindBy(linkText = "Создать контактное лицо")
    private WebElement btnNewContact;

    @FindBy(xpath = "//div[@id='container']//h1")
    private WebElement header;

    public NewContactPage clickNewContact() {
        btnNewContact.click();
        return new NewContactPage(driver);
    }

    public AllContactsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check Page")
    public AllContactsPage checkPage() {
        wait.until(ExpectedConditions.visibilityOf(btnNewContact));
        Assertions.assertEquals("Все Контактные лица", header.getText());

        return this;
    }
}
