package space.geekbrains.crm.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import space.geekbrains.crm.pages.AllContactsPage;
import space.geekbrains.crm.pages.MyProjectsPage;

public class SubMenu extends BaseView {
    public SubMenu(WebDriver driver) {
        super(driver);
    }

    public BaseView clickSubMenuButton(space.geekbrains.crm.enums.SubMenu item) {
        Actions actions = new Actions(driver);
        actions
            .moveToElement(driver.findElement(item.getBy()))
            .click()
            .build()
            .perform();
        switch (item) {
            case CONTACTS:
                return new AllContactsPage(driver);
            case MY_PROJECTS:
                return new MyProjectsPage(driver);
            default:
                throw new IllegalArgumentException("Another tab not implemented");
        }
    }
}
