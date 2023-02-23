package com.soft_swiss.selenium_task.page.site_header;

import com.soft_swiss.selenium_task.page.BasePage;
import com.soft_swiss.selenium_task.page.search.SearchPackagePart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Getter
public class Header extends BasePage {

    private SearchPackagePart searchPackage = new SearchPackagePart();

    @FindBy(xpath = "//a[text()='Why Go']")
    private WebElement whyGoLink;

    @FindBy(xpath = "//a[text()='Getting Started']")
    private WebElement gettingStartedLink;

    @FindBy(xpath = "//a[text()='Discover Packages']")
    private WebElement discoverPackagesLink;

    @FindBy(xpath = "//a[text()='About']")
    private WebElement aboutLink;

    public List<WebElement> linkElements = Arrays.asList(whyGoLink, gettingStartedLink, discoverPackagesLink, aboutLink);

    public void searchText(String searchTerm) {
        searchPackage.searchText(searchTerm);
    }

    @Getter
    @AllArgsConstructor
    public enum ElementProps {
        WHY_GO_LINK("Solutions", "Why Go"),
        GETTING_STARTED_LINK("Learn", "Getting Started"),
        DISCOVER_PACKAGES_LINK("Explore", "Discover Packages"),
        ABOUT_LINK( "", "About");

        private final String title;
        private final String text;

        public boolean isCorrectElementParameters(WebElement webElement) {
            return (webElement != null && this.getTitle().equals(webElement.getAttribute("title")));
        }

        public static ElementProps findByText(String text) {
            return Stream.of(ElementProps.values())
                    .filter(f -> f.getText().equals(text))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Unknown resource: " + text));
        }
    }
}
