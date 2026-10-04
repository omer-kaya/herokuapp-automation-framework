package com.kaya.herokuapp.base;

import com.kaya.herokuapp.utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    private static final Logger logger = LogManager.getLogger(DriverManager.class);

    // ThreadLocal: paralel çalışmada her thread'in kendi bağımsız driver'ı olur
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        if (driver.get() == null) {

            // -Dheadless=true verilirse pencere açmadan çalışır (CI için şart). Verilmezse false (local'de pencere görünür)
            boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
            logger.info("Chrome driver başlatılıyor... (headless: {})", headless);

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }

            WebDriver chromeDriver = new ChromeDriver(options);

            boolean maximizeWindow = Boolean.parseBoolean(ConfigReader.getProperty("maximizeWindow"));
            if (!headless && maximizeWindow) {
                chromeDriver.manage().window().maximize();
            }

            driver.set(chromeDriver);
            logger.info("Chrome driver başarıyla başlatıldı.");
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            logger.info("Chrome driver kapatıldı.");
        }
    }
}