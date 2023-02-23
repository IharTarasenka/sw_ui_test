package com.soft_swiss.selenium_task.page;

import com.soft_swiss.selenium_task.page.site_footer.Footer;
import com.soft_swiss.selenium_task.page.site_header.SiteHeaderWithBanner;
import lombok.Getter;
import org.openqa.selenium.WebElement;

import java.util.List;

@Getter
public class MainPage extends BasePage {

    private SiteHeaderWithBanner siteHeader = new SiteHeaderWithBanner();
    private Footer siteFooter = new Footer();

    public void goToMainPage(String url) {
        goToUrl(url);
    }

    public List<WebElement> getLinkElements() {
        return siteHeader.getLinkElements();
    }

    public void searchText(String searchTerm) {
        siteHeader.searchText(searchTerm);
    }
}
