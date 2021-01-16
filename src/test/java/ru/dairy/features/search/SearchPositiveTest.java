package ru.dairy.features.search;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dairy.base.BaseUITest;
import ru.dairy.pages.HomePage;

@Feature("DairySearch")
public class SearchPositiveTest extends BaseUITest {

    @Test
    @DisplayName("Проверка поиска для неавторизованного пользователя")
    @Description("Проверка поиска для неавторизованного пользователя")
    public void loginTest() {
        String searchText = "qqq";

        new HomePage(driver)
                .openSearchMenu()
                .enterSearchQuery(searchText)
                .clickSearch()
                .checkPage(searchText);
    }
}
