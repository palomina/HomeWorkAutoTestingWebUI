package space.geekbrains.crm.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import space.geekbrains.crm.base.BaseView;

import java.util.concurrent.TimeUnit;

public class NewContactPage extends BaseView {

    @FindBy(name = "crm_contact[lastName]")
    private WebElement inputLastName;

    @FindBy(name = "crm_contact[firstName]")
    private WebElement inputFirstName;

    @FindBy(name = "crm_contact[jobTitle]")
    private WebElement inputJobTitle;

    @FindBy(name = "crm_contact[company]")
    private WebElement inputCompany;

    @FindBy(xpath = "//div[@class='company-container']//a")
    private WebElement companyContainer;

    @FindBy(css = ".select2-default > .select2-chosen")
    private WebElement selectChosen;

    @FindBy(tagName = "body")
    private WebElement body;

    @FindBy(xpath = "//div[contains(@style, 'display: block')][contains(@class, 'select2-drop-active')]//ul/li[1]")
    private WebElement firstCompany;

    @FindBy(css = "div.company-container .select2-chosen")
    private WebElement companyChosen;

    @FindBy(xpath = "//button[contains(.,'Сохранить и закрыть')]")
    private WebElement btnSave;

    public NewContactPage enterLastName(String lastName) {
        inputLastName.sendKeys(lastName);
        return this;
    }

    public NewContactPage enterFirstName(String firstName) {
        inputFirstName.sendKeys(firstName);
        return this;
    }

    public NewContactPage enterJobTitle(String jobTitle) {
        inputJobTitle.sendKeys(jobTitle);
        return this;
    }

    public NewContactPage enterCompany(String company) {

        companyContainer.click();
        {
            Actions builder = new Actions(driver);
            builder.moveToElement(selectChosen).perform();
        }
        {
            Actions builder = new Actions(driver);
            builder.moveToElement(body, 0, 0).perform();
        }
        {
            Actions builder = new Actions(driver);
            builder.moveToElement(selectChosen).clickAndHold().sendKeys(company).build().perform();
        }
        wait.until(ExpectedConditions.visibilityOf(firstCompany));
        delay();
        {
            Actions builder = new Actions(driver);
            builder.moveToElement(firstCompany).click().build().perform();
        }
        return this;
    }

    public static void delay(){
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (Exception e) {}
    }

    public NewContactPage checkFilling(String lastName, String firstName, String jobTitle, String company) {
        Assertions.assertEquals(company, companyChosen.getText());

        {
            String value = inputLastName.getAttribute("value");
            Assertions.assertEquals(lastName, value);
        }
        {
            String value = inputFirstName.getAttribute("value");
            Assertions.assertEquals(firstName, value);
        }
        {
            String value = inputJobTitle.getAttribute("value");
            Assertions.assertEquals(jobTitle, value);
        }

        return this;
    }

    public AllContactsPage clickSave() {
        btnSave.click();
        return  new AllContactsPage(driver);
    }

    public NewContactPage(WebDriver driver) {
        super(driver);
    }
}
