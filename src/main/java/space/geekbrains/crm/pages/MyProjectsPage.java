package space.geekbrains.crm.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import space.geekbrains.crm.base.BaseView;

public class MyProjectsPage extends BaseView {

    @FindBy(linkText = "Создать проект")
    private WebElement btnNewProject;

    @FindBy(xpath = "//div[@id='container']//h1")
    private WebElement header;

    public NewProjectPage clickNewProject() {
        btnNewProject.click();
        return new NewProjectPage(driver);
    }

    public MyProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check Page")
    public MyProjectsPage checkPage() {
        wait.until(ExpectedConditions.visibilityOf(btnNewProject));
        Assertions.assertEquals("Все Проекты", header.getText());

        return this;
    }
}
