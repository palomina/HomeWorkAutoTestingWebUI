package ru.dairy.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.dairy.base.BaseView;

public class HomePage extends BaseView {

    @FindBy(id = "drop-login")
    private WebElement openLoginMenu;

    @FindBy(id = "usrlog2")
    private WebElement inputLogin;

    @FindBy(id = "usrpass2")
    private WebElement inputPassword;

    @FindBy(css = ".btn-plain:nth-child(7)")
    private WebElement btnLogin;

    @FindBy(id = "drop-search")
    private WebElement openSearchMenu;

    @FindBy(xpath = "//a[@id='drop-search']//following-sibling::div//input[@id='sq']")
    private WebElement inputSearch;

    @FindBy(xpath = "//a[@id='drop-search']//following-sibling::div//button[.='Найти']")
    private WebElement btnSearch;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Login Menu")
    public HomePage openLoginMenu() {
        openLoginMenu.click();
        return this;
    }

    @Step("Enter Login {login}")
    public HomePage enterLogin(String login) {
        inputLogin.click();
        inputLogin.sendKeys(login);
        return this;
    }

    @Step("Enter Password {password}")
    public HomePage enterPassword(String password) {
        inputPassword.click();
        inputPassword.sendKeys(password);
        return this;
    }

    @Step("Click Login")
    public DashboardPage clickLogin() {
        btnLogin.click();
        return new DashboardPage(driver);
    }

    @Step("Open Search Menu")
    public HomePage openSearchMenu() {
        openSearchMenu.click();
        return this;
    }

    @Step("Enter Search Query")
    public HomePage enterSearchQuery(String query) {
        inputSearch.click();
        inputSearch.sendKeys(query);
        return this;
    }

    @Step("Click Search")
    public SearchPage clickSearch() {
        btnSearch.click();
        return new SearchPage(driver);
    }

    @Step("Authorise Scenario with {login} and {password}")
    public DashboardPage authoriseScenario(String login, String password) {
        return this.openLoginMenu()
                .enterLogin(login)
                .enterPassword(password)
                .clickLogin();
    }

}
