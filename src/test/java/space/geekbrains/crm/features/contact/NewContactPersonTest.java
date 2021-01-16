package space.geekbrains.crm.features.contact;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import space.geekbrains.crm.base.BaseUITest;
import space.geekbrains.crm.common.Configuration;
import space.geekbrains.crm.enums.NavigatorBarTabs;
import space.geekbrains.crm.enums.SubMenu;
import space.geekbrains.crm.pages.AllContactsPage;
import space.geekbrains.crm.pages.LoginPage;
import space.geekbrains.crm.pages.NewContactPage;

@Feature("GeekbrainsContactPerson")
public class NewContactPersonTest extends BaseUITest {

    @Test
    @DisplayName("Проверка создания контактного лица")
    @Description("Проверка создания контактного лица")
    public void newContactTest() {
        AllContactsPage allContactsPage = (AllContactsPage) new LoginPage(driver)
                .authoriseScenario(Configuration.LOGIN, Configuration.PASSWORD)
                .getNavigator()
                .moveCursorToNavigationTab(NavigatorBarTabs.CONTRACTORS)
                .clickSubMenuButton(SubMenu.CONTACTS);

        Faker faker = new Faker();

        String lastName = faker.letterify("Contact ???????");
        String firstName = faker.letterify("????");
        String jobTitle = faker.letterify("jobTitle????????");
        String company = "Test Organisation_1";

        NewContactPage newContactPage = allContactsPage
                .clickNewContact();

        newContactPage
                .enterLastName(lastName)
                .enterFirstName(firstName)
                .enterJobTitle(jobTitle)
                .enterCompany(company)
                .checkFilling(lastName, firstName, jobTitle, company)
                .clickSave()
                .checkPage();
    }
}
