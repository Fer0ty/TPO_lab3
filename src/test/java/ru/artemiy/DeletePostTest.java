package ru.artemiy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import ru.artemiy.configuration.DriversConfiguration;
import ru.artemiy.page.LoginPage;
import ru.artemiy.page.MainPage;

import java.util.stream.Stream;

public class DeletePostTest {
    @BeforeAll
    static void prepareDrivers() {
        DriversConfiguration.prepareDrivers();
    }

    @TestFactory
    public Stream<DynamicTest> testDeletePost() {
        return DriversConfiguration.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Удалить пост" + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(DriversConfiguration.BASE_URL);
                        LoginPage loginPage = mainPage.goToLoginPage();
                        loginPage.login(DriversConfiguration.CORRECT_EMAIL, DriversConfiguration.CORRECT_PASSWORD);
                        mainPage.deletePost();
                        try {
                            Assertions.assertNull(DriversConfiguration.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div/div[2]/div/div[2]/div/div[1]/main/div[2]/div[2]/div[1]/div/div/div/article/div[1]/div/span/div/div/p")));
                        } catch (TimeoutException e){
                            Assertions.assertNotNull("Test proyden");
                        }
                    } finally {
                        driver.quit();
                    }
                }));
    }
}
