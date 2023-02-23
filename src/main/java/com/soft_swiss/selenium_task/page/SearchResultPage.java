package com.soft_swiss.selenium_task.page;

import com.soft_swiss.selenium_task.page.site_footer.Footer;
import com.soft_swiss.selenium_task.page.site_header.Header;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static com.soft_swiss.selenium_task.util.WebElementUtil.isClickable;
import static java.util.stream.Collectors.toList;

public class SearchResultPage extends BasePage {

    private Header siteHeader = new Header();
    private Footer siteFooter = new Footer();

    @FindBy(className = "SearchSnippet")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//div[@class='SearchSnippet']/h2[@class='SearchSnippet-header']/a")
    private List<WebElement> searchResultLinks;

    @FindBy(className = "Pagination-next")
    private WebElement nextButton;

    public void clickDesiredPackage(String desiredPackage, int countPageForSearch) {
        lookInSearchResultByLinkText(desiredPackage, countPageForSearch).click();
    }

    public WebElement lookInSearchResultByLinkText(String desiredPackage, int countPageForSearch) {
        Function<WebElement, List<WebElement>> function = (x) -> desiredPackage.equals(x.getText()) ? Collections.singletonList(x) : Collections.emptyList();
        List<WebElement> desiredPackages = lookInSearchResult(searchResultLinks, function, countPageForSearch, true);
        if (desiredPackages.size() > 1) {
            throw new RuntimeException("Several search results dublication found" + desiredPackages);
        }
        return desiredPackages.get(0);
    }

    public List<WebElement> lookInSearchResult(List<WebElement> source, Function<WebElement, List<WebElement>> function, int countPageForSearch, boolean stopWhenFoundFirst) {
        List<WebElement> results = new ArrayList<>();
        for (int i = 0; i < countPageForSearch; i++) {
            results.addAll(source.stream().map(function).flatMap(List::stream).collect(toList()));
            if (!results.isEmpty() && stopWhenFoundFirst) {
                return results;
            }
            clickNext();
        }
        if (results.isEmpty()) {
            throw new RuntimeException("No results on set pages");
        }
        return results;
    }

    public void clickNext() {
        if (isClickable(nextButton)) {
            nextButton.click();
        } else {
            throw new RuntimeException("Next button is not available, maybe not enough search results");
        }
    }
}
