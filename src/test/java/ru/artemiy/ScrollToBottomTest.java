package ru.artemiy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.openqa.selenium.By;
import ru.artemiy.configuration.DriversConfiguration;
import ru.artemiy.page.MainPage;

import java.util.stream.Stream;

public class ScrollToBottomTest {
    @BeforeAll
    static void prepareDrivers() {
        DriversConfiguration.prepareDrivers();
    }

    @TestFactory
    public Stream<DynamicTest> TestAlert() {
        return DriversConfiguration.getDrivers().stream().map(driver -> DynamicTest.dynamicTest("Проскроллить весь интернет " + driver.getClass(),
                () -> {
                    try {
                        driver.manage().deleteAllCookies();
                        MainPage mainPage = new MainPage(driver);
                        driver.get(DriversConfiguration.BASE_URL);
                        mainPage.scrollToBottom();
                        Assertions.assertNotNull(DriversConfiguration.getElementBySelector(driver, By.xpath("//div[@class='vVe9A']")));
                    } finally {
                        driver.quit();
                    }
                }));
    }
}
