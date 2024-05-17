package ru.artemiy.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.artemiy.configuration.DriversConfiguration;

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

    public void scrollToBottom() throws InterruptedException {
        DriversConfiguration.waitUntilPageLoads(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get(DriversConfiguration.BASE_URL);
        driver.manage().window().maximize();
        for (int i =0; i < 10; i++){
            js.executeScript("window.scrollBy(0,2000)");
            sleep(1000);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void goToMyProfile() {
        DriversConfiguration.waitUntilPageLoads(driver, Duration.ofSeconds(10));
        WebElement profileButton = DriversConfiguration.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div/div[2]/div/div[1]/div/div[1]/nav/ul/li[5]/button/span/div[2]"));
        profileButton.click();
        WebElement myProfileButton = DriversConfiguration.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div/div[2]/div/div[1]/div/div[1]/nav/ul/ul/li[4]/ul/li/div/div/div/a/div/div[2]/div[1]/span"));
        myProfileButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeNewPost() {
        final String POST_TEXT = "WOW! NEW POST! TPO KLASS";
        DriversConfiguration.waitUntilPageLoads(driver, Duration.ofSeconds(10));
        goToMyProfile();
        WebElement newTextPostButton = DriversConfiguration.getElementBySelector(driver, By.xpath("//button[@aria-label='Текст']"));
        newTextPostButton.click();
        WebElement TextArea = DriversConfiguration.getElementBySelector(driver, By.xpath("//*[@id='block-b796dffe-05a8-4b22-8185-37bc0f5bb482']"));
        TextArea.sendKeys(POST_TEXT);
        WebElement sendButton = DriversConfiguration.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div/div[4]/div/div/div/div/div/div/div[2]/div/div[3]/div/div/div/button/span"));
        sendButton.click();
    }

    public void deletePost(){
        DriversConfiguration.waitUntilPageLoads(driver, Duration.ofSeconds(10));
        goToMyProfile();
        WebElement openOptionButton = DriversConfiguration.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div/div[2]/div/div[2]/div/div[1]/main/div[2]/div[2]/div[1]/div/div/div/article/header/div[3]/span/span/span/button/span"));
        openOptionButton.click();
        WebElement deleteButton = DriversConfiguration.getElementBySelector(driver, By.xpath("//div[contains(@class, 'XLZRW')]"));
        deleteButton.click();
        WebElement submitButton = DriversConfiguration.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div/div[4]/div/div[2]/div/div[2]/button[2]/span"));
        submitButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendNewMessage(){
        String TEST_MESSAGE = "TEST MESSAGE. SORRY";
        DriversConfiguration.waitUntilPageLoads(driver, Duration.ofSeconds(10));
        WebElement messageMainButton = DriversConfiguration.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div/div[2]/div/div[1]/div/div[1]/nav/ul/span[2]/span/li/button/span/div[2]"));
        messageMainButton.click();
        WebElement messageToTest = DriversConfiguration.getElementBySelector(driver, By.xpath("//span[@class='EvhBA leMd5']"));
        messageToTest.click();
        WebElement messageArea = DriversConfiguration.getElementBySelector(driver, By.xpath("//textarea"));
        messageArea.click();
        messageArea.sendKeys(TEST_MESSAGE);
        WebElement sendButton = DriversConfiguration.getElementBySelector(driver, By.xpath("/html/body/div[1]/div/div/div[3]/div/div/div/div[2]/div/div[3]/div[2]/button[2]/span"));
        sendButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
