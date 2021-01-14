package ru.dairy.features.newrecord;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dairy.base.BaseUITest;
import ru.dairy.common.Configuration;
import ru.dairy.enums.NavigatorBarTabs;
import ru.dairy.pages.HomePage;
import ru.dairy.pages.NewRecordPage;

public class NewRecordPositiveTest extends BaseUITest {

    @Test
    @DisplayName("Проверка создания новой записи")
    public void newRecordTest() {
        NewRecordPage newRecord = (NewRecordPage) new HomePage(driver)
                .authoriseScenario(Configuration.LOGIN, Configuration.PASSWORD)
                .getNavigator()
                .moveCursorToNavigationTab(NavigatorBarTabs.NEW_RECORD);

        Faker faker = new Faker();
        String title = faker.letterify("New record ????????? ? ???? ???");
        String message = faker.letterify("???? ?????? ????????? ? ???? ???");

        newRecord
                .enterTitle(title)
                .enterMessage(message)
                .setClosePost()
                .setAccessMode()
                .checkFilling(title, message)
                .clickSave()
                .checkPage();
    }
}
