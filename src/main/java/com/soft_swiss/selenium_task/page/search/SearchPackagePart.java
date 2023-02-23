package com.soft_swiss.selenium_task.page.search;

import com.soft_swiss.selenium_task.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPackagePart extends BasePage {

    @FindBy(name = "q")
    private WebElement searchInput;

    @FindBy(className = "SearchForm-submit")
    private WebElement searchButton;

    public void searchText(String searchTerm) {
        searchInput.sendKeys(searchTerm);
        searchButton.click();
    }
}
