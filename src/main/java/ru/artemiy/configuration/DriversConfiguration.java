package ru.artemiy.configuration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DriversConfiguration {
    private static final String PROPERTIES_PATH = "/Users/artemiy/Desktop/ITMO/3rd_Year/2nd_sem/TPO/lab3/src/main/java/ru/artemiy/configuration/auto-testing.properties";
    public static final String CHROME_SYSTEM_PROPERTY_NAME = "webdriver.chrome.driver";
    public static final String CHROME_SYSTEM_PROPERTY_PATH = "/Users/artemiy/Desktop/ITMO/3rd_Year/2nd_sem/TPO/lab3/src/main/java/ru/artemiy/configuration/chromedriver";
    public static final String BASE_URL = "https://www.tumblr.com";
    public static final String CORRECT_EMAIL = "***"; // забанили :(
    public static final String CORRECT_PASSWORD ="dyrpen-qaPhu2-gyvwes" ;
    public static final String WRONG_PASSWORD = "dyrpen-qaPhu2";


    public static void prepareDrivers() {
        System.setProperty(CHROME_SYSTEM_PROPERTY_NAME, CHROME_SYSTEM_PROPERTY_PATH);
    }

    public static WebElement getElementBySelector(WebDriver driver, By selector) {
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static void waitUntilPageLoads(WebDriver driver, Duration timeout) {
        WebDriverWait waitDriver = new WebDriverWait(driver, timeout);
        waitDriver.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    private static ChromeDriver getChromeDriver() {
        if (!System.getProperties().containsKey(CHROME_SYSTEM_PROPERTY_NAME)) {
            throw new RuntimeException("Chrome driver is not set properly");
        }
        return new ChromeDriver();
    }

    public static List<WebDriver> getDrivers() {
        List<WebDriver> drivers = new ArrayList<>();
        try {
            List<String> properties = Files.readAllLines(Paths.get(PROPERTIES_PATH));
            for (String property : properties) {
                if (property.startsWith("web-driver") && property.toLowerCase().split("=")[1].equals("chrome")) {
                    drivers.add(getChromeDriver());
                    return drivers;
                }
                throw new RuntimeException("Web driver is not specified");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return drivers;
    }

}
