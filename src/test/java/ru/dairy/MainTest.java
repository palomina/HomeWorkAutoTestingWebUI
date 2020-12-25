package ru.dairy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;


public class MainTest {

    private static Logger logger;
    private static ChromeDriver driver;
    private static WebDriverWait wait;

    public final String URL = "https://www.diary.ru/";
    public final String LOGIN = "Oliandra";
    public final String PASSWORD = "fa8e7ffdc";


    public static String randomName() {
        Random r = new Random();
        return String.valueOf(r.nextInt(100000));
    }

    @BeforeAll
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 30);
        logger = Logger.getLogger(MainTest.class);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка авторизации")
    public void test1() {
        logger.info("Opening of the website dairy.ru (Открытие сайта dairy.ru)");
        driver.get(URL);

        logger.info("Entering a password and login (Ввод пароля и логина)");
        driver.findElement(By.id("drop-login")).click();
        driver.findElement(By.id("usrlog2")).click();
        driver.findElement(By.id("usrlog2")).sendKeys(LOGIN);
        driver.findElement(By.id("usrpass2")).click();
        driver.findElement(By.id("usrpass2")).sendKeys(PASSWORD);
        logger.info("Authorization on the site diary.ru (Авторизация на сайте diary.ru)");
        driver.findElement(By.cssSelector(".btn-plain:nth-child(7)")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(LOGIN))));
        logger.info("Authorization check (Проверка авторизации)");
        Assertions.assertEquals(LOGIN, driver.findElement(By.linkText(LOGIN)).getText());

        logger.info("Leaving the site diary.ru (Выход с сайта diary.ru)");
        driver.findElement(By.id("drop")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Выход"))));
        Assertions.assertEquals("Выход", driver.findElement(By.linkText("Выход")).getText());
        driver.findElement(By.linkText("Выход")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("drop-login"))));
        Assertions.assertEquals("Вход", driver.findElement(By.id("drop-login")).getText());
    }

    @Test
    @DisplayName("Проверка создания новой записи")
    public void test2() {
        String title = "НОВАЯ ЗАПИСЬ " + randomName();
        String message = "НОВАЯ ЗАПИСЬ qwe qwe qw eqw eqw eqw eqwe wqe qwe qw";

        logger.info("Opening of the website dairy.ru (Открытие сайта dairy.ru)");
        driver.get(URL);

        if (driver.findElement(By.id("drop-login")).getText().equals("Вход")) {
            logger.info("Entering a password and login (Ввод пароля и логина)");
            driver.findElement(By.id("drop-login")).click();
            driver.findElement(By.id("usrlog2")).click();
            driver.findElement(By.id("usrlog2")).sendKeys(LOGIN);
            driver.findElement(By.id("usrpass2")).click();
            driver.findElement(By.id("usrpass2")).sendKeys(PASSWORD);
            logger.info("Authorization on the site diary.ru (Авторизация на сайте diary.ru)");
            driver.findElement(By.cssSelector(".btn-plain:nth-child(7)")).click();
        }
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(LOGIN))));
        logger.info("Authorization check (Проверка авторизации)");
        Assertions.assertEquals(LOGIN, driver.findElement(By.linkText(LOGIN)).getText());

        logger.info("Click to create a new record (Клик на создание новой записи)");
        driver.findElement(By.cssSelector("a > .i-menu-newpost")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='new_post_title']/span[contains(text(),'Новая запись')]"))));
        Assertions.assertEquals("НОВАЯ ЗАПИСЬ", driver.findElement(By.xpath("//div[@id='new_post_title']/span[contains(text(),'Новая запись')]")).getText());

        logger.info("Filling out the form (Заполнение формы)");
        driver.findElement(By.id("postTitle")).click();
        driver.findElement(By.id("postTitle")).sendKeys(title);
        driver.findElement(By.id("message")).click();
        driver.findElement(By.id("message")).sendKeys(message);
        driver.findElement(By.id("closedPost")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("closeaccessmode7")))).click();
        Assertions.assertEquals(driver.findElement(By.id("postTitle")).getAttribute("value"), title);
        Assertions.assertEquals(driver.findElement(By.id("message")).getAttribute("value"), message);
        Assertions.assertTrue(driver.findElement(By.id("closedPost")).isSelected());
        Assertions.assertTrue(driver.findElement(By.id("closeaccessmode7")).isSelected());

        logger.info("Saving changes (Сохранение изменений)");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("rewrite")))).click();

        logger.info("Go to My Diary page (Переход на страницу Мой дневник)");
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.id("rewrite"))));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("p > a:nth-child(2)")), "Мой дневник"));
        Assertions.assertEquals("Мой дневник", driver.findElement(By.cssSelector("p > a:nth-child(2)")).getText());
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(LOGIN))));
        Assertions.assertEquals(LOGIN, driver.findElement(By.linkText(LOGIN)).getText());

        logger.info("Leaving the site diary.ru (Выход с сайта diary.ru)");
        driver.findElement(By.id("drop")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Выход"))));
        Assertions.assertEquals("Выход", driver.findElement(By.linkText("Выход")).getText());
        driver.findElement(By.linkText("Выход")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("drop-login"))));
        Assertions.assertEquals("Вход", driver.findElement(By.id("drop-login")).getText());
    }

}
