package base;

import com.soft_swiss.selenium_task.driver.DriverManager;
import org.testng.annotations.AfterMethod;

public class BaseTest {

    @AfterMethod
    protected void quitDriver() {
        DriverManager.quitDriver();
    }
}
