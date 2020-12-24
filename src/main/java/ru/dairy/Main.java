package ru.dairy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


class Main {


    public static void main(String[] args) {
        WebDriver driver = getWebDriver();

        test1(driver);
        test2(driver);

        driver.close();
    }

    public static WebDriver getWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        WebDriverManager.chromedriver().setup();

        return new ChromeDriver(options);
    }

    public static void test1(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        driver.get("https://www.diary.ru/");
        try {
            driver.findElement(By.id("drop-login")).click();
            driver.findElement(By.id("usrlog2")).click();
            driver.findElement(By.id("usrlog2")).sendKeys("Oliandra");
            driver.findElement(By.id("usrpass2")).click();
            driver.findElement(By.id("usrpass2")).sendKeys("fa8e7ffdc");
            driver.findElement(By.cssSelector(".btn-plain:nth-child(7)")).click();

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Oliandra"))));

            driver.findElement(By.id("drop")).click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Выход"))));

            driver.findElement(By.linkText("Выход")).click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("drop-login"))));

            System.out.println("Завершение работы TC1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test2(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        JavascriptExecutor js;
        js = (JavascriptExecutor) driver;

        String title = "НОВАЯ ЗАПИСЬ " + randomName();
        String message = "НОВАЯ ЗАПИСЬ qwe qwe qw eqw eqw eqw eqwe wqe qwe qw";

        driver.get("https://www.diary.ru/");
        try {

            if ( driver.findElement(By.id("drop-login")).getText().equals("Вход")) {
                driver.findElement(By.id("drop-login")).click();
                driver.findElement(By.id("usrlog2")).click();
                driver.findElement(By.id("usrlog2")).sendKeys("Oliandra");
                driver.findElement(By.id("usrpass2")).click();
                driver.findElement(By.id("usrpass2")).sendKeys("fa8e7ffdc");
                driver.findElement(By.cssSelector(".btn-plain:nth-child(7)")).click();
            }
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Oliandra"))));

            driver.findElement(By.cssSelector("a > .i-menu-newpost")).click();
            driver.findElement(By.id("postTitle")).click();

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='new_post_title']/span[contains(text(),'Новая запись')]"))));

            driver.findElement(By.id("postTitle")).click();
            driver.findElement(By.id("postTitle")).sendKeys(title);
            driver.findElement(By.id("message")).click();
            driver.findElement(By.id("message")).sendKeys(message);
            driver.findElement(By.id("closedPost")).click();
   //         js.executeScript("scroll(0,  document.body.scrollHeight);");
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("closeaccessmode7"))));
            driver.findElement(By.id("closeaccessmode7")).click();


            wait.until(ExpectedConditions.textToBePresentInElementValue(driver.findElement(By.id("postTitle")), title));
            wait.until(ExpectedConditions.textToBePresentInElementValue(driver.findElement(By.id("message")), message));
            wait.until(ExpectedConditions.elementToBeSelected(By.id("closedPost")));
            wait.until(ExpectedConditions.elementToBeSelected(By.id("closeaccessmode7")));
  //          wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("closeaccessmode7")), "checked", ""));
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("rewrite"))));

            driver.findElement(By.id("rewrite")).click();
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.id("rewrite"))));
            wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("p > a:nth-child(2)")), "Мой дневник"));

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Oliandra"))));
            driver.findElement(By.id("drop")).click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Выход"))));

            driver.findElement(By.linkText("Выход")).click();
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("drop-login"))));

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Завершение работы TC2");
    }

    public static String randomName() {
        Random r = new Random();
        return String.valueOf(r.nextInt(100000));
    }
}
