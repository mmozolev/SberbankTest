package ru.appline.homeworks.testBase;

import org.junit.jupiter.api.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver webDriver;
    public static WebDriverWait wait;

    @BeforeAll
    static void before() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");

        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(webDriver, 15, 1000);

    }

    @BeforeEach
    void beforeEach() {
        webDriver.get("https://www.sberbank.ru/ru/person");
    }

    @AfterAll
    static void after() {
        webDriver.quit();
    }

    public static void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        javascriptExecutor.executeScript("window.scrollBy(0, -400)");
    }

    public static void clickOnElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        scrollToElementJs(element);
        executor.executeScript("arguments[0].click()", element);
    }

    public static void fillInputField(WebElement element, String value) {
        clearField(element);
        element.click();
        waitUtilElementToBeVisible(element);
        element.sendKeys(value);
    }

    public static void clearField(WebElement element) {
        waitUtilElementToBeVisible(element);
        waitUtilElementToBeClickable(element);
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    public static void waitUtilElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUtilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}


