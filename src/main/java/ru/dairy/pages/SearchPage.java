package ru.dairy.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.dairy.base.BaseView;

public class SearchPage extends BaseView {

    @FindBy(xpath = "//h1")
    private WebElement header;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement inputSearch;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check Page with {searchText}")
    public void checkPage(String searchText) {
        Assertions.assertEquals("Поиск по дневникам", header.getText());
        Assertions.assertEquals(searchText, inputSearch.getAttribute("value"));
    }

}
