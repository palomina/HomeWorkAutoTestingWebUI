package space.geekbrains.crm.features.login;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import space.geekbrains.crm.base.BaseUITest;
import space.geekbrains.crm.common.Configuration;
import space.geekbrains.crm.pages.LoginPage;

public class LoginPositiveTest extends BaseUITest {

    @Test
    @DisplayName("Проверка авторизации")
    public void loginTest() {
        new LoginPage(driver)
                .enterLogin(Configuration.LOGIN)
                .enterPassword(Configuration.PASSWORD)
                .clickLogin()
                .checkUrl(Configuration.BASE_URL);
    }
}
