package ru.artemiy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.openqa.selenium.By;
import ru.artemiy.configuration.DriversConfiguration;
import ru.artemiy.page.MainPage;

import java.util.stream.Stream;


public class LoginTest {

    @BeforeAll
    static void prepareDrivers() {
        DriversConfiguration.prepareDrivers();
    }

    // Быть аккуратнее, за очень частые логины банят :)
    @TestFactory
    public Stream<DynamicTest> testLoginWithMail() {
        return DriversConfiguration.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Успешный вход в аккаунт в браузере " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(DriversConfiguration.BASE_URL);
                        var loginPage = mainPage.goToLoginPage();
                        loginPage.login(DriversConfiguration.CORRECT_EMAIL, DriversConfiguration.CORRECT_PASSWORD);
                    } finally {
                        driver.quit();
                    }
                }));
    }

    @TestFactory
    public Stream<DynamicTest> testLoginWithWrongPassword() {
        return DriversConfiguration.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Не успешный вход в аккаунт (неверный пароль) в браузере " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(DriversConfiguration.BASE_URL);
                        var loginPage = mainPage.goToLoginPage();
                        loginPage.login(DriversConfiguration.CORRECT_EMAIL, DriversConfiguration.WRONG_PASSWORD);
                        Assertions.assertNotNull(DriversConfiguration.getElementBySelector(driver, By.xpath("//div[@class='a0A37 hAkP2']")));
                    } finally {
                        driver.quit();
                    }
                }));
    }

}
