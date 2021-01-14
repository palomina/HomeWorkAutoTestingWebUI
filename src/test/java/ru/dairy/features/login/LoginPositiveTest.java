package ru.dairy.features.login;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dairy.base.BaseUITest;
import ru.dairy.common.Configuration;
import ru.dairy.pages.HomePage;

public class LoginPositiveTest extends BaseUITest {

    @Test
    @DisplayName("Проверка авторизации")
    public void loginTest() {
        new HomePage(driver)
                .openLoginMenu()
                .enterLogin(Configuration.LOGIN)
                .enterPassword(Configuration.PASSWORD)
                .clickLogin()
                .checkUrl(Configuration.BASE_URL);
    }
}
