package com.soft_swiss.selenium_task.page;

import com.soft_swiss.selenium_task.driver.DriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Getter
public class BasePage {

    protected WebDriver webDriver;

    public BasePage() {
        this.webDriver = DriverManager.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    protected void goToUrl(String url) {
        webDriver.get(url);
    }
}
