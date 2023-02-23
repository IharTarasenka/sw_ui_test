package com.soft_swiss.selenium_task.page.site_header;

import com.soft_swiss.selenium_task.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;

import java.util.List;

@Getter
public class SiteHeaderWithBanner extends BasePage {

    private Banner banner = new Banner();
    private Header header = new Header();

    public void searchText(String searchTerm) {
        header.searchText(searchTerm);
    }

    public List<WebElement> getLinkElements() {
        return header.getLinkElements();
    }
}
