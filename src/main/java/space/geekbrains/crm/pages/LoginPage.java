package space.geekbrains.crm.pages;

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

    public LoginPage enterLogin(String login) {
        inputLogin.click();
        inputLogin.sendKeys(login);
        return this;
    }

    public LoginPage enterPassword(String password) {
        inputPassword.click();
        inputPassword.sendKeys(password);
        return this;
    }

    public DashboardPage clickLogin() {
        btnLogin.click();
        return new DashboardPage(driver);
    }

    public DashboardPage authoriseScenario(String login, String password) {
        return this
                .enterLogin(login)
                .enterPassword(password)
                .clickLogin();
    }

}
