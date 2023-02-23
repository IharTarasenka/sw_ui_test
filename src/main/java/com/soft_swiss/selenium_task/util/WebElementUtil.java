package com.soft_swiss.selenium_task.util;

import org.openqa.selenium.WebElement;

public class WebElementUtil {

    public static boolean isClickable(WebElement element) {
        return element.isDisplayed() && element.isEnabled();
    }
}
