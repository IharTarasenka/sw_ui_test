package com.soft_swiss.selenium_task.driver;

import com.soft_swiss.selenium_task.util.PropertiesFileUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Properties;

import static com.soft_swiss.selenium_task.util.ClassUtil.getClazz;
import static java.util.concurrent.TimeUnit.SECONDS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverManager {
    private static final ThreadLocal<WebDriver> webDriverContainer = new ThreadLocal<>();
    private static final String PROPERTY_NAME = "property_name";
    private static final String DRIVER_CLASS = "driver_class";
    private static final String DRIVER_PATH = "driver_path";


    public static WebDriver getDriver() {
        if (webDriverContainer.get() == null) {
            initDriver();
        }
        return webDriverContainer.get();
    }

    public static void quitDriver() {
        if (webDriverContainer.get() != null) {
            webDriverContainer.get().quit();
        }
    }

    private static void initDriver() {
        WebDriver webDriver = null;
        Properties prop = PropertiesFileUtil.getParametersForDriver();
        try {
            System.setProperty(prop.getProperty(PROPERTY_NAME), prop.getProperty(DRIVER_PATH));
            webDriver = (WebDriver) Objects.requireNonNull(getClazz(prop.getProperty(DRIVER_CLASS))).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(4, SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(3, SECONDS);
        webDriverContainer.set(webDriver);
    }
}
