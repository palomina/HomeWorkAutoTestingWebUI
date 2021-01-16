package space.geekbrains.crm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import space.geekbrains.crm.base.BaseView;


public class LoginPage extends BaseView {

    @FindBy(id = "prependedInput")
    private WebElement inputLogin;

    @FindBy(id = "prependedInput2")
    private WebElement inputPassword;

    @FindBy(id = "_submit")
    private WebElement btnLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter Login {login}")
    public LoginPage enterLogin(String login) {
        inputLogin.click();
        inputLogin.sendKeys(login);
        return this;
    }

    @Step("Enter Password {password}")
    public LoginPage enterPassword(String password) {
        inputPassword.click();
        inputPassword.sendKeys(password);
        return this;
    }

    @Step("Click Login")
    public DashboardPage clickLogin() {
        btnLogin.click();
        return new DashboardPage(driver);
    }

    @Step("AuthoriseScenario  with {login} and {password}")
    public DashboardPage authoriseScenario(String login, String password) {
        return this
                .enterLogin(login)
                .enterPassword(password)
                .clickLogin();
    }

}
