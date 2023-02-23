package com.soft_swiss.selenium_task.page.discover_package_parts;

import com.soft_swiss.selenium_task.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class Header extends BasePage {

    @FindBy(className = "DetailsHeader-version")
    private WebElement version;

    @FindBy(xpath = "//a[@data-test-id='DetailsHeader-infoLabelModule']")
    private WebElement module;

    @FindBy(xpath = "//div[@class='DetailsHeader-infoLabel']/strong")
    private WebElement published;
}
