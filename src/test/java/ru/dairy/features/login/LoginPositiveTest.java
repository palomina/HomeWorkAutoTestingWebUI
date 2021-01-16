package ru.dairy.features.login;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dairy.base.BaseUITest;
import ru.dairy.common.Configuration;
import ru.dairy.pages.HomePage;

@Feature("DairyLogin")
@Severity(SeverityLevel.BLOCKER)
public class LoginPositiveTest extends BaseUITest {

    @Test
    @DisplayName("Проверка авторизации")
    @Description("Проверка авторизации")
    public void loginTest() {
        new HomePage(driver)
                .openLoginMenu()
                .enterLogin(Configuration.LOGIN)
                .enterPassword(Configuration.PASSWORD)
                .clickLogin()
                .checkUrl(Configuration.BASE_URL);
    }
}
