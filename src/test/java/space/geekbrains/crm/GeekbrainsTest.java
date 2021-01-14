package space.geekbrains.crm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GeekbrainsTest {
    private static Logger logger;
    private static ChromeDriver driver;
    private static WebDriverWait wait;
    private static Map<String, Object> vars;
    private static JavascriptExecutor js;

    public final String URL = "https://crm.geekbrains.space";
    public final String LOGIN = "Applanatest";
    public final String PASSWORD = "Student2020!";


    public static String randomName() {
        Random r = new Random();
        return String.valueOf(r.nextInt(100000));
    }

    public static void delay(){
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (Exception e) {}
    }

    @BeforeAll
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 30);
        logger = Logger.getLogger(GeekbrainsTest.class);

        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();

    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка создания проекта")
    public void test1() {
        logger.info("Authorization on site (Авторизация на сайте)");
        driver.get("https://crm.geekbrains.space/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("Applanatest");
        driver.findElement(By.id("prependedInput2")).click();
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.id("_submit")).click();
        Assertions.assertEquals("Панель инструментов", driver.findElement(By.xpath("//*[@id='container']//h1")).getText());
        logger.info("Click on My projects (Клик на Мои проекты)");
        {
            WebElement element = driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[3]/a"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='main-menu']//a[@href='/project/my']"))));
        driver.findElement(By.xpath("//*[@id='main-menu']//a[@href='/project/my']")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//h1[contains(text(), 'Панель инструментов')]"))));

        logger.info("Click on Create project (Клик на Создать проект)");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(@title,'Создать проект')]"))));
        Assertions.assertEquals("Создать проект", driver.findElement(By.xpath("//a[contains(@title,'Создать проект')]")).getText());
        driver.findElement(By.linkText("Создать проект")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.linkText("Создать проект"))));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//label[contains(text(),'Наименование')]")), "Наименование"));
        Assertions.assertEquals("Создать проект", driver.findElement(By.xpath("//h1[@class='user-name'][contains(.,'Создать проект')]")).getText());
        delay();

        logger.info("Fill the name of the project (Заполнение наименования)");
        vars.put("myRandomNumber", js.executeScript("return Math.random()"));
        driver.findElement(By.name("crm_project[name]")).sendKeys("Test Project " + vars.get("myRandomNumber").toString());
        {
            String value = driver.findElement(By.name("crm_project[name]")).getAttribute("value");
            Assertions.assertEquals("Test Project " + vars.get("myRandomNumber").toString(), value);
        }

        logger.info("Fill the organization of the project (Заполнение Организация)");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body/div[@class='loader-mask']")));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='company-container']//a"))));
        driver.findElement(By.xpath("//div[@class='company-container']//a")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".select2-default > .select2-chosen"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".select2-default > .select2-chosen"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().sendKeys("Test Organisation_1").perform();
        }
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@style, 'display: block')][contains(@class, 'select2-drop-active')]//ul/li[1]"))));
        delay();

       {
            WebElement element = driver.findElement(By.xpath("//div[contains(@style, 'display: block')][contains(@class, 'select2-drop-active')]//ul/li[1]"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).click().perform();
        }
        Assertions.assertEquals("Test Organisation_1", driver.findElement(By.cssSelector("div.company-container .select2-chosen")).getText());

        logger.info("Fill the fields of the project (Заполнение Подразделение, Куратор проекта, Руководитель проекта, Администратор проекта, Менеджер)");
        {
            WebElement dropdown = driver.findElement(By.name("crm_project[businessUnit]"));
            dropdown.findElement(By.xpath("//option[. = 'Research & Development']")).click();
        }
        driver.findElement(By.name("crm_project[rp]")).click();
        {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//select[@name='crm_project[rp]']/option[contains(text(),'Student')]"))));
            driver.findElement(By.xpath("//select[@name='crm_project[rp]']/option[contains(text(),'Student')]")).click();
        }

        driver.findElement(By.name("crm_project[curator]")).click();
        {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//select[@name='crm_project[curator]']/option[contains(text(),'Student')]"))));
            driver.findElement(By.xpath("//select[@name='crm_project[curator]']/option[contains(text(),'Student')]")).click();
        }

        driver.findElement(By.name("crm_project[administrator]")).click();
        {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//select[@name='crm_project[administrator]']/option[contains(text(),'Student')]"))));
            driver.findElement(By.xpath("//select[@name='crm_project[administrator]']/option[contains(text(),'Student')]")).click();
        }

        driver.findElement(By.name("crm_project[manager]")).click();
        {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//select[@name='crm_project[manager]']/option[contains(text(),'Student')]"))));
            driver.findElement(By.xpath("//select[@name='crm_project[manager]']/option[contains(text(),'Student')]")).click();
        }

        {
            WebElement element = driver.findElement(By.name("crm_project[businessUnit]"));
            String value = element.getAttribute("value");
            String locator = String.format("option[@value='%s']", value);
            String selectedText = element.findElement(By.xpath(locator)).getText();
            Assertions.assertEquals("Research & Development", selectedText);
        }
        {
            WebElement element = driver.findElement(By.name("crm_project[manager]"));
            String value = element.getAttribute("value");
            String locator = String.format("option[@value='%s']", value);
            String selectedText = element.findElement(By.xpath(locator)).getText();
            Assertions.assertEquals("Student Student Stu", selectedText);
        }
        {
            WebElement element = driver.findElement(By.name("crm_project[administrator]"));
            String value = element.getAttribute("value");
            String locator = String.format("option[@value='%s']", value);
            String selectedText = element.findElement(By.xpath(locator)).getText();
            Assertions.assertEquals("Student Student Stu", selectedText);
        }
        {
            WebElement element = driver.findElement(By.name("crm_project[rp]"));
            String value = element.getAttribute("value");
            String locator = String.format("option[@value='%s']", value);
            String selectedText = element.findElement(By.xpath(locator)).getText();
            Assertions.assertEquals("Student Student Stu", selectedText);
        }
        {
            WebElement element = driver.findElement(By.name("crm_project[curator]"));
            String value = element.getAttribute("value");
            String locator = String.format("option[@value='%s']", value);
            String selectedText = element.findElement(By.xpath(locator)).getText();
            Assertions.assertEquals("Student Student Stu", selectedText);
        }


        logger.info("Fill the contact face of the project (Заполнение Основное контактное лицо)");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[contains(@id,'s2id_crm_project_contactMain')]//a"))));
        driver.findElement(By.xpath("//div[contains(@id,'s2id_crm_project_contactMain')]//a")).click();
        {
            WebElement element = driver.findElement(By.xpath("//div[contains(@id,'s2id_crm_project_contactMain')]//span[@class='select2-chosen']"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }

        {
            WebElement element = driver.findElement(By.xpath("//div[contains(@id,'s2id_crm_project_contactMain')]//span[@class='select2-chosen']"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().sendKeys("Тестовая Тестина").perform();

        }

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@style, 'display: block')][contains(@class, 'select2-drop-active')]//ul/li[1]"))));
        delay();
        {
            WebElement element = driver.findElement(By.xpath("//div[contains(@style, 'display: block')][contains(@class, 'select2-drop-active')]//ul/li[1]"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).click().perform();
        }

        Assertions.assertEquals("Тестовая Тестина", driver.findElement(By.xpath("//div[contains(@id,'s2id_crm_project_contactMain')]//span[@class='select2-chosen']")).getText());
        Assertions.assertEquals("Тестовая Тестина", driver.findElement(By.xpath("//div[2]/div/a/span")).getText());

        logger.info("Save the project (Сохранение проекта)");

        driver.findElement(By.cssSelector(".btn-group:nth-child(4) > .btn")).click();
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Проект сохранен')]")));
        }
        Assertions.assertEquals("Все Проекты", driver.findElement(By.xpath("//div[@id='container']//h1")).getText());
    }

    @Test
    @DisplayName("Проверка создания контактного лица")
    public void test2() {
        logger.info("Authorization on site (Авторизация на сайте)");
        driver.get("https://crm.geekbrains.space/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("Applanatest");
        driver.findElement(By.id("prependedInput2")).click();
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.id("_submit")).click();
        Assertions.assertEquals("Панель инструментов", driver.findElement(By.xpath("//*[@id='container']//h1")).getText());

        logger.info("Click on Contact faces (Клик на контактные лица)");
        {
            WebElement element = driver.findElement(By.xpath("//a/span[contains(.,'Контрагенты')]"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.xpath("//a/span[contains(.,'Контактные лица')]")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//h1[contains(text(), 'Панель инструментов')]"))));

        logger.info("Click on Create contact face (Клик на Создать контактное лицо)");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(@title,'Создать контактное лицо')]"))));
        Assertions.assertEquals("Создать контактное лицо", driver.findElement(By.linkText("Создать контактное лицо")).getText());
        {
            List<WebElement> elements = driver.findElements(By.xpath("//table//a[contains(.,'ФИО')]"));
            assert(elements.size() > 0);
        }

        driver.findElement(By.linkText("Создать контактное лицо")).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.linkText("Создать контактное лицо"))));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//label[contains(text(),'Фамилия')]")), "Фамилия"));
        Assertions.assertEquals("Создать контактное лицо", driver.findElement(By.xpath("//h1[@class='user-name'][contains(.,'Создать контактное лицо')]")).getText());

        logger.info("Fill the fields (Заполнение полей для создания контактного лица)");
        vars.put("myRandomNumber", js.executeScript("return Math.random()"));
        driver.findElement(By.name("crm_contact[lastName]")).sendKeys("Контактное лицо "+vars.get("myRandomNumber").toString());
        driver.findElement(By.name("crm_contact[firstName]")).sendKeys("Имя");
        driver.findElement(By.name("crm_contact[jobTitle]")).sendKeys("Должность");


        logger.info("Fill the organization of the project (Заполнение Организация)");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//body/div[@class='loader-mask']")));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='company-container']//a"))));
        driver.findElement(By.xpath("//div[@class='company-container']//a")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".select2-default > .select2-chosen"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        {
            WebElement element = driver.findElement(By.cssSelector(".select2-default > .select2-chosen"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).clickAndHold().sendKeys("Test Organisation_1").perform();
        }
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@style, 'display: block')][contains(@class, 'select2-drop-active')]//ul/li[1]"))));
        delay();
        {
            WebElement element = driver.findElement(By.xpath("//div[contains(@style, 'display: block')][contains(@class, 'select2-drop-active')]//ul/li[1]"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).click().perform();
        }
        Assertions.assertEquals("Test Organisation_1", driver.findElement(By.cssSelector("div.company-container .select2-chosen")).getText());

        {
            String value = driver.findElement(By.name("crm_contact[lastName]")).getAttribute("value");
            Assertions.assertEquals("Контактное лицо " +vars.get("myRandomNumber").toString(), value);
        }
        {
            String value = driver.findElement(By.name("crm_contact[firstName]")).getAttribute("value");
            Assertions.assertEquals("Имя", value);
        }
        {
            String value = driver.findElement(By.name("crm_contact[jobTitle]")).getAttribute("value");
            Assertions.assertEquals("Должность", value);
        }
        logger.info("Save the contact face (Сохранение контактного лица)");
        driver.findElement(By.xpath("//button[contains(.,'Сохранить и закрыть')]")).click();
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Контактное лицо сохранено')]")));
        }
        Assertions.assertEquals("Все Контактные лица", driver.findElement(By.xpath("//div[@id='container']//h1")).getText());
    }
}
