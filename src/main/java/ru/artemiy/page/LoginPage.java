package ru.artemiy.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.artemiy.configuration.DriversConfiguration;

import java.time.Duration;

public class LoginPage extends Page{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password){
        DriversConfiguration.waitUntilPageLoads(driver, Duration.ofSeconds(10));
        WebElement loginWithEmailButton = DriversConfiguration.getElementBySelector(driver, By.xpath("//button[@class='dKGjO']"));
        loginWithEmailButton.click();
        loginWithEmail(email,password);
    }
    public void loginWithEmail(String email, String password) {
        DriversConfiguration.waitUntilPageLoads(driver, Duration.ofSeconds(10));
        WebElement emailInput = DriversConfiguration.getElementBySelector(driver, By.xpath("//input[@type='email']"));
        WebElement emailEnterButton = DriversConfiguration.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div/div[4]/div/div/div/div/div[2]/div/form/div[1]/div[1]/button/span"));
        emailInput.clear();
        emailInput.sendKeys(email);
        emailEnterButton.click();

        WebElement passwordInput = DriversConfiguration.getElementBySelector(driver, By.xpath("//input[@name='password']"));
        WebElement passwordEnterButton = DriversConfiguration.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div/div[4]/div/div/div/div/div[2]/div/form/div[1]/div[1]/div/button/span"));
        passwordInput.clear();
        passwordInput.sendKeys(password);
        passwordEnterButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
