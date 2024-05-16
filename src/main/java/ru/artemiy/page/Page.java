package ru.artemiy.page;

import org.openqa.selenium.WebDriver;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
public class Page {

    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

}

