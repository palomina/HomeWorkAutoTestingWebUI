package ru.dairy.pages;

import org.openqa.selenium.By;
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

    public HomePage openLoginMenu() {
        openLoginMenu.click();
        return this;
    }

    public HomePage enterLogin(String login) {
        inputLogin.click();
        inputLogin.sendKeys(login);
        return this;
    }

    public HomePage enterPassword(String password) {
        inputPassword.click();
        inputPassword.sendKeys(password);
        return this;
    }

    public DashboardPage clickLogin() {
        btnLogin.click();
        return new DashboardPage(driver);
    }


    public HomePage openSearchMenu() {
        openSearchMenu.click();
        return this;
    }

    public HomePage enterSearchQuery(String query) {
        inputSearch.click();
        inputSearch.sendKeys(query);
        return this;
    }

    public SearchPage clickSearch() {
        btnSearch.click();
        return new SearchPage(driver);
    }


    public DashboardPage authoriseScenario(String login, String password) {
        return this.openLoginMenu()
                .enterLogin(login)
                .enterPassword(password)
                .clickLogin();
    }

}
