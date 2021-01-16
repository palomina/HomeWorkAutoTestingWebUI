package space.geekbrains.crm.features.login;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import space.geekbrains.crm.base.BaseUITest;
import space.geekbrains.crm.common.Configuration;
import space.geekbrains.crm.pages.LoginPage;

@Feature("GeekbrainsLogin")
@Severity(SeverityLevel.BLOCKER)
public class LoginPositiveTest extends BaseUITest {

    @Test
    @DisplayName("Проверка авторизации")
    @Description("Проверка авторизации")
    public void loginTest() {
        new LoginPage(driver)
                .enterLogin(Configuration.LOGIN)
                .enterPassword(Configuration.PASSWORD)
                .clickLogin()
                .checkUrl(Configuration.BASE_URL);
    }
}
