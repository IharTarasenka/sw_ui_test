package com.soft_swiss.selenium_task.page.discover_package_parts;

import com.soft_swiss.selenium_task.page.BasePage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Stream;

@Getter
public class DetailsNavigation extends BasePage {

    @FindBy(xpath = "//nav[contains(@class,'DetailsNav')]")
    private WebElement navigationContainer;

    @FindBy(className = "DetailsNav-list")
    private WebElement navigationPanel;

    @FindBy(xpath = "//li[contains(@class,'DetailsNav-tab')]")
    private List<WebElement> tabList;

    @FindBy(xpath = "//a[contains(@class,'DetailsNav-link')]")
    private List<WebElement> tabLinkList;

    @Getter
    @AllArgsConstructor
    public enum NavigationItem {
        DOC("Doc"),
        OVERVIEW( "Overview"),
        SUBDIRECTORIES("Subdirectories"),
        VERSION("Versions"),
        IMPORTS("Imports"),
        IMPORTED_BY("Imported By"),
        LICENSES("Licenses");

        private final String text;

        public static NavigationItem findByText(String text) {
            return Stream.of(NavigationItem.values())
                    .filter(f -> f.getText().equals(text))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Unknown resource: " + text));
        }
    }
}
