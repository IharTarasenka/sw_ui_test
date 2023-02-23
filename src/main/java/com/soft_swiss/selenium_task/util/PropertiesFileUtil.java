package com.soft_swiss.selenium_task.util;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtil {
    public static Properties getParametersForDriver() {
        String pathToFile = getFilePath();
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(pathToFile)) {
            prop.load(fis);
            if (!StringUtils.isEmpty(System.getProperty("driver_path"))) {
                prop.setProperty("driver_path", System.getProperty("driver_path"));
            }
        } catch (IOException e) {
            throw new RuntimeException("Something wait rong");
        }
        return prop;
    }

    private static String getFilePath() {
        String browser = System.getProperty("browser");
        return StringUtils.isEmpty(browser) ? "src\\main\\resources\\driver_settings\\chrome81.properties" :
                String.format("src\\main\\resources\\driver_settings\\%s.properties", browser.toLowerCase());
    }
}
