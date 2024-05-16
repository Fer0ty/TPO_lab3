package ru.artemiy.page;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.artemiy.configuration.DriversConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLoginPage() {
        var loginButton = DriversConfiguration.getElementBySelector(driver, By.xpath("/html/body/div/div/div/div[2]/div/div[2]/div[1]/div/div[2]/button[2]/span"));
        loginButton.click();
        return new LoginPage(this.driver);
    }


}
