package ru.dairy.features.removerecord;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dairy.base.BaseUITest;
import ru.dairy.common.Configuration;
import ru.dairy.enums.NavigatorBarTabs;
import ru.dairy.pages.HomePage;
import ru.dairy.pages.MyDairyPage;

@Feature("DairyDeleteRecord")
public class RemoveRecordPositiveTest extends BaseUITest {

    @Test
    @DisplayName("Проверка удаления записи")
    @Description("Проверка удаления записи")
    public void removeRecordTest() {
        MyDairyPage dairyPage = (MyDairyPage) new HomePage(driver)
                .authoriseScenario(Configuration.LOGIN, Configuration.PASSWORD)
                .getNavigator()
                .moveCursorToNavigationTab(NavigatorBarTabs.MY_DAIRY);

        dairyPage
                .checkExistsRecords()
                .clickRemove()
                .clickYes()
                .checkSuccess();
    }
}
