package ru.artemiy.page;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.artemiy.configuration.DriversConfiguration;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLoginPage() {
        WebElement loginButton = DriversConfiguration.getElementBySelector(driver, By.xpath("/html/body/div/div/div/div[2]/div/div[2]/div[1]/div/div[2]/button[2]/span"));
        loginButton.click();
        return new LoginPage(this.driver);
    }

    public void scrollToBottom() throws AWTException, InterruptedException {
        DriversConfiguration.waitUntilPageLoads(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get(DriversConfiguration.BASE_URL);
        driver.manage().window().maximize();
        for (int i =0; i < 10; i++){
            js.executeScript("window.scrollBy(0,2000)");
            sleep(1000);
        }
        sleep(5000);
    }


}
