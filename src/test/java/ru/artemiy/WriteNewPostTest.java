package ru.artemiy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.openqa.selenium.By;
import ru.artemiy.configuration.DriversConfiguration;
import ru.artemiy.page.LoginPage;
import ru.artemiy.page.MainPage;

import java.util.stream.Stream;

public class WriteNewPostTest {
    @BeforeAll
    static void prepareDrivers() {
        DriversConfiguration.prepareDrivers();
    }

    // warning: Not Working :(
    @TestFactory
    public Stream<DynamicTest> testWriteNewPost() {
        return DriversConfiguration.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Написать новый пост" + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(DriversConfiguration.BASE_URL);
                        LoginPage loginPage = mainPage.goToLoginPage();
                        loginPage.login(DriversConfiguration.CORRECT_EMAIL, DriversConfiguration.CORRECT_PASSWORD);
                        mainPage.writeNewPost();
                        Assertions.assertNotNull(DriversConfiguration.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div/div[2]/div/div[2]/div/div[1]/main/div[2]/div[2]/div[1]/div/div/div/article/div[1]/div/span/div/div/p")));
                    } finally {
                        driver.quit();
                    }
                }));
    }
}
