package com.soft_swiss.selenium_task.page;

import com.soft_swiss.selenium_task.page.discover_package_parts.DetailsNavigation;
import com.soft_swiss.selenium_task.page.site_footer.Footer;
import com.soft_swiss.selenium_task.page.site_header.Header;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DiscoverPackagePage extends BasePage {

    private Header siteHeader = new Header();
    private Footer siteFooter = new Footer();
    private com.soft_swiss.selenium_task.page.discover_package_parts.Header header = new com.soft_swiss.selenium_task.page.discover_package_parts.Header();
    private DetailsNavigation navigation = new DetailsNavigation();

    public DiscoverPackagePage() {
        super();
    }

    public WebElement getModule() {
        return header.getModule();
    }

    public WebElement getVersion() {
        return header.getVersion();
    }

    public WebElement getPublished() {
        return header.getPublished();
    }

    public WebElement getNavigationContainer() {
        return navigation.getNavigationContainer();
    }

    public WebElement getNavigationPanel() {
        return navigation.getNavigationPanel();
    }

    public List<WebElement> getTabList() {
        return navigation.getTabList();
    }

    public List<WebElement> getTabLinkList() {
        return navigation.getTabLinkList();
    }
}
