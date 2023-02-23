package search;

import base.BaseTest;
import com.soft_swiss.selenium_task.page.DiscoverPackagePage;
import com.soft_swiss.selenium_task.page.MainPage;
import com.soft_swiss.selenium_task.page.SearchResultPage;
import com.soft_swiss.selenium_task.page.discover_package_parts.DetailsNavigation.NavigationItem;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.soft_swiss.selenium_task.util.WebElementUtil.isClickable;

public class SearchPackageTest extends BaseTest {

    private static final String MAIN_PAGE_URL = "https://go.dev/";
    private static final String DESIRED_PACKAGE = "github.com/mikekonan/protoc-gen-setter";
    private static final String DESIRED_PACKAGE_HREF = "https://pkg.go.dev/mod/github.com/mikekonan/protoc-gen-setter";
    private static final String VERSION = "v1.3.2";
    private static final String PUBLISHED_DATE = "Apr 13, 2020";
    private MainPage mainPage;
    private SearchResultPage searchPage;
    private DiscoverPackagePage discoverPackagePage;

    @BeforeMethod
    private void init() {
        mainPage = new MainPage();
        searchPage = new SearchResultPage();
        discoverPackagePage = new DiscoverPackagePage();
    }

    @Test
    private void consistencyTest() {
        mainPage.goToMainPage(MAIN_PAGE_URL);
        mainPage.searchText("setter");
        searchPage.clickDesiredPackage(DESIRED_PACKAGE, 5);
        checkHeaderParameters();
        checkNavigationAvailability();
    }

    private void checkHeaderParameters() {
        Assert.assertEquals(discoverPackagePage.getModule().getText(), DESIRED_PACKAGE, "Module link text is incorrect");
        Assert.assertEquals(discoverPackagePage.getModule().getAttribute("href"), DESIRED_PACKAGE_HREF, "Module link is incorrect");
        Assert.assertEquals(discoverPackagePage.getVersion().getText(), VERSION, "Version is incorrect");
        Assert.assertEquals(discoverPackagePage.getPublished().getText(), PUBLISHED_DATE, "Published date is incorrect");
    }

    private void checkNavigationAvailability() {
        Assert.assertTrue(isClickable(discoverPackagePage.getNavigationContainer()), "Navigation container is available");
        Assert.assertTrue(isClickable(discoverPackagePage.getNavigationPanel()), "Navigation panel is available");
        Assert.assertEquals(NavigationItem.values().length, discoverPackagePage.getTabList().size(), "Navigation panel contain not all available tabs");
        discoverPackagePage.getTabList().forEach(i -> Assert.assertTrue(isClickable(i), "Navigation panel element is available"));
        List<String> expected = Arrays.stream(NavigationItem.values()).map(NavigationItem::getText).sorted().collect(Collectors.toList());
        List<String> actual = discoverPackagePage.getTabLinkList().stream().map(WebElement::getText).sorted().collect(Collectors.toList());
        Assert.assertEquals(actual, expected, "Not all items displayed");
        discoverPackagePage.getTabLinkList().forEach(i -> checkHeaderElementProps(i, NavigationItem.findByText(i.getText())));
    }

    private static void checkHeaderElementProps(WebElement element, NavigationItem item) {
        String descForAvailableAssert = "Is not available element on UI %s";
        Assert.assertTrue(isClickable(element), String.format(descForAvailableAssert, item.name()));
    }
}
