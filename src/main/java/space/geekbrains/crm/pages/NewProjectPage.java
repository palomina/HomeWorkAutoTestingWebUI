package space.geekbrains.crm.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import space.geekbrains.crm.base.BaseView;

import java.util.concurrent.TimeUnit;

public class NewProjectPage extends BaseView {

    @FindBy(name = "crm_project[name]")
    private WebElement inputName;

    @FindBy(xpath = "//label[contains(text(),'Наименование')]")
    private WebElement labelName;

    @FindBy(name = "crm_contact[company]")
    private WebElement inputCompany;

    @FindBy(name = "crm_project[businessUnit]")
    private WebElement selectBusinessUnit;

    @FindBy(xpath = "//*[@name='crm_project[businessUnit]']/option[. = 'Research & Development']")
    private WebElement optionBusiness;

    @FindBy(name = "crm_project[rp]")
    private WebElement selectRP;

    @FindBy(xpath = "//select[@name='crm_project[rp]']/option[contains(text(),'Student')]")
    private WebElement optionRP;

    @FindBy(name = "crm_project[curator]")
    private WebElement selectCurator;

    @FindBy(xpath = "//select[@name='crm_project[curator]']/option[contains(text(),'Student')]")
    private WebElement optionCurator;

    @FindBy(name = "crm_project[administrator]")
    private WebElement selectAdministrator;

    @FindBy(xpath = "//select[@name='crm_project[administrator]']/option[contains(text(),'Student')]")
    private WebElement optionAdministrator;

    @FindBy(name = "crm_project[manager]")
    private WebElement selectManager;

    @FindBy(xpath = "//select[@name='crm_project[manager]']/option[contains(text(),'Student')]")
    private WebElement optionManager;

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

    @FindBy(css = ".btn-group:nth-child(4) > .btn")
    private WebElement btnSave;

    @FindBy(xpath = "//div[contains(@id,'s2id_crm_project_contactMain')]//a")
    private WebElement contactContainer;

    @FindBy(xpath = "//div[contains(@id,'s2id_crm_project_contactMain')]//span[@class='select2-chosen']")
    private WebElement selectChosenContact;

    @FindBy(xpath = "//div[contains(@style, 'display: block')][contains(@class, 'select2-drop-active')]//ul/li[1]")
    private WebElement firstContact;

    @Step("Enter Name {name}")
    public NewProjectPage enterName(String name) {
        wait.until(ExpectedConditions.textToBePresentInElement(labelName, "Наименование"));
        inputName.sendKeys(name);
        return this;
    }

    @Step("Enter Business")
    public NewProjectPage enterBusiness() {
        selectBusinessUnit.click();
        optionBusiness.click();
        return this;
    }

    @Step("Enter RP")
    public NewProjectPage enterRP() {
        selectRP.click();
        optionRP.click();
        return this;
    }

    @Step("Enter Curator")
    public NewProjectPage enterCurator() {
        selectCurator.click();
        optionCurator.click();
        return this;
    }

    @Step("Enter Administrator")
    public NewProjectPage enterAdministrator() {
        selectAdministrator.click();
        optionAdministrator.click();
        return this;
    }

    @Step("Enter Manager")
    public NewProjectPage enterManager() {
        selectManager.click();
        optionManager.click();
        return this;
    }

    @Step("Enter Company {company}")
    public NewProjectPage enterCompany(String company) {
        wait.until(ExpectedConditions.visibilityOf(companyContainer));
        delay();
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

    @Step("Enter Contact {contact}")
    public NewProjectPage enterContact(String contact) {
        delay();
        contactContainer.click();
        {
            Actions builder = new Actions(driver);
            builder.moveToElement(selectChosenContact).perform();
        }
        {
            Actions builder = new Actions(driver);
            builder.moveToElement(selectChosenContact).clickAndHold().sendKeys(contact).build().perform();
        }
        wait.until(ExpectedConditions.visibilityOf(firstContact));
        delay();
        {
            Actions builder = new Actions(driver);
            builder.moveToElement(firstContact).click().build().perform();
        }
        return this;
    }

    public static void delay(){
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (Exception e) {}
    }

    @Step("Check Filling {name}")
    public NewProjectPage checkFilling(String name) {

        {
            String value = inputName.getAttribute("value");
            Assertions.assertEquals(name, value);
        }
        return this;
    }

    @Step("Click Save")
    public MyProjectsPage clickSave() {
        btnSave.click();
        return  new MyProjectsPage(driver);
    }

    public NewProjectPage(WebDriver driver) {
        super(driver);
    }
}
