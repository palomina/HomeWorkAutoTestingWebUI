package ru.dairy.features.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dairy.base.BaseUITest;
import ru.dairy.common.Configuration;
import ru.dairy.pages.HomePage;

public class SearchPositiveTest extends BaseUITest {

    @Test
    @DisplayName("Проверка поиска для неавторизованного пользователя")
    public void loginTest() {
        String searchText = "qqq";

        new HomePage(driver)
                .openSearchMenu()
                .enterSearchQuery(searchText)
                .clickSearch()
                .checkPage(searchText);
    }
}
