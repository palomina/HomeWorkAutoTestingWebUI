package space.geekbrains.crm.features.project;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import space.geekbrains.crm.base.BaseUITest;
import space.geekbrains.crm.common.Configuration;
import space.geekbrains.crm.enums.NavigatorBarTabs;
import space.geekbrains.crm.enums.SubMenu;
import space.geekbrains.crm.pages.*;

public class NewProjectTest extends BaseUITest {

    @Test
    @DisplayName("Проверка создания проекта")
    public void newContactTest() {
        MyProjectsPage myProjectsPage = (MyProjectsPage) new LoginPage(driver)
                .authoriseScenario(Configuration.LOGIN, Configuration.PASSWORD)
                .getNavigator()
                .moveCursorToNavigationTab(NavigatorBarTabs.PROJECTS)
                .clickSubMenuButton(SubMenu.MY_PROJECTS);

        Faker faker = new Faker();

        String name = faker.letterify("Project ???????");
        String company = "Test Organisation_1";
        String contact = "Тестовая Тестина";

        NewProjectPage newProjectPage = myProjectsPage
                .clickNewProject();

        newProjectPage
                .enterName(name)
                .enterCompany(company)
                .enterBusiness()
                .enterCurator()
                .enterManager()
                .enterAdministrator()
                .enterRP()
                .enterContact(contact)
                .checkFilling(name)
                .clickSave()
                .checkPage();
    }
}
