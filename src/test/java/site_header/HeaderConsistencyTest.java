package site_header;

import base.BaseTest;
import com.soft_swiss.selenium_task.page.MainPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.soft_swiss.selenium_task.page.site_header.Header.ElementProps;
import static com.soft_swiss.selenium_task.util.WebElementUtil.isClickable;

public class HeaderConsistencyTest extends BaseTest {

    private static final String MAIN_PAGE_URL = "https://go.dev/";
    private MainPage mainPage;

    @BeforeMethod
    private void init() {
        mainPage = new MainPage();
    }

    @Test
    private void allItemIsPresentAndAvailable() {
        mainPage.goToMainPage(MAIN_PAGE_URL);
        List<String> expected = Arrays.stream(ElementProps.values()).map(ElementProps::getText).sorted().collect(Collectors.toList());
        List<String> actual = mainPage.getLinkElements().stream().map(WebElement::getText).sorted().collect(Collectors.toList());
        Assert.assertEquals(actual, expected, "Not all items displayed");
        mainPage.getLinkElements().forEach(i -> checkHeaderElementProps(i, ElementProps.findByText(i.getText())));
    }

    private static void checkHeaderElementProps(WebElement element, ElementProps props) {
        String descForParamAssert = "Incorrect parameters for element %s";
        String descForAvailableAssert = "Is not available element on UI %s";
        Assert.assertTrue(props.isCorrectElementParameters(element), String.format(descForParamAssert, props.name()));
        Assert.assertTrue(isClickable(element), String.format(descForAvailableAssert, props.name()));
    }
}
