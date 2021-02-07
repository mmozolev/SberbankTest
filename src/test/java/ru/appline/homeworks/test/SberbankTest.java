package ru.appline.homeworks.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.appline.homeworks.testBase.TestBase;



public class SberbankTest extends TestBase {

    @Test
    void test() {

        //Проверяем заголовок
        Assertions.assertEquals("Частным клиентам — СберБанк", webDriver.getTitle());

        //Переходим -> Карты -> Дебетовые карты
        webDriver.findElement(By.xpath("//button[text()='Закрыть']")).click();
        webDriver.findElement(By.xpath("//a[@aria-label='Карты']")).click();
        webDriver.findElement(By.xpath("//a[text()='Дебетовые карты'][contains(@class, 'kitt-top')]")).click();

        Assertions.assertEquals("Дебетовые карты", webDriver.findElement(By.xpath("//h1")).getText());

        webDriver.findElement(By.xpath("//h2[text()='Молодёжная карта']/../..//span[text()='Заказать онлайн']")).click();

        //Проверяем заголовок
        Assertions.assertEquals("Молодёжная карта", webDriver.findElement(By.xpath("//h1")).getText());

        clickOnElement(webDriver.findElement(By.xpath("//a[contains(@data-test-id,'Page')]//span[text()='Оформить онлайн']")));

        //Ожидаем окончание скролинга
        try {
            Thread.sleep(2000);
        }catch (InterruptedException ignore){}

        String xPath = "//input[@data-name='%s']";

        //Фамилия
        fillInputField(webDriver.findElement(By.xpath(String.format(xPath, "lastName"))), "Фамилия");
        Assertions.assertEquals(webDriver.findElement(By.xpath(String.format(xPath, "lastName"))).getAttribute("value"),
                "Фамилия", "Ошибка заполнения поля Фамилия");
        //Имя
        fillInputField(webDriver.findElement(By.xpath(String.format(xPath, "firstName"))), "Имя");
        Assertions.assertEquals(webDriver.findElement(By.xpath(String.format(xPath, "firstName"))).getAttribute("value"),
                "Имя", "Ошибка заполнения поля Имя");
        //Отвечтво
        fillInputField(webDriver.findElement(By.xpath(String.format(xPath, "middleName"))), "Отчество");
        Assertions.assertEquals(webDriver.findElement(By.xpath(String.format(xPath, "middleName"))).getAttribute("value"),
                "Отчество", "Ошибка заполнения поля Отчество");
        //Имя на карте
        Assertions.assertEquals(webDriver.findElement(By.xpath(String.format(xPath, "cardName"))).getAttribute("value"),
                "IMIA FAMILIIA", "Ошибка заполнения поля Имя на карте");

        //Дата рождения
        fillInputField(webDriver.findElement(By.xpath(String.format(xPath, "birthDate"))), "12122000");
        Assertions.assertEquals(webDriver.findElement(By.xpath(String.format(xPath, "birthDate"))).getAttribute("value"),
                "12.12.2000", "Ошибка заполнения потя Дата рождения");

        //Электронная почта
        fillInputField(webDriver.findElement(By.xpath(String.format(xPath, "email"))), "email@mail.ru");
        Assertions.assertEquals(webDriver.findElement(By.xpath(String.format(xPath, "email"))).getAttribute("value"),
                "email@mail.ru", "Ошибка заполнения поля Электронная почта");

        //Телефон
        fillInputField(webDriver.findElement(By.xpath(String.format(xPath, "phone"))), "9999999999");
        Assertions.assertEquals(webDriver.findElement(By.xpath(String.format(xPath, "phone"))).getAttribute("value"),
                "+7 (999) 999-99-99", "Ошибка заполнения поля Телефон");

        clickOnElement(webDriver.findElement(By.xpath("//span[text()='Далее']")));

        //Проверка полей
        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.
                xpath("//input[@data-name='series']//following-sibling::node()")).getText(),
                "Проверка ошибки у поля не была пройдена");

        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.
                xpath("//input[@data-name='number']//following-sibling::node()")).getText(),
                "Проверка ошибки у поля не была пройдена");

        Assertions.assertEquals("Обязательное поле", webDriver.findElement(By.
                xpath("//input[@data-name='issueDate']/../../..//div[@class='odcui-error__text']")).getText(),
                "Проверка ошибки у поля не была пройдена");

    }
}
