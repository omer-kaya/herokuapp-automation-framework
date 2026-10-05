package com.kaya.herokuapp.pages;

import com.kaya.herokuapp.base.DriverManager;
import com.kaya.herokuapp.utilities.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    // ==========================================
    // LOCATORS
    // ==========================================
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By flashMessage = By.id("flash");
    private By secureAreaHeader = By.cssSelector("div.example h2");
    private By logoutButton = By.cssSelector("a.button.secondary.radius");

    // ==========================================
    // CONSTRUCTOR
    // ==========================================
    public LoginPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getIntProperty("explicitWait")));
    }

    // ==========================================
    // NAVIGATION
    // ==========================================
    public void goToLoginPage() {
        driver.get(ConfigReader.getProperty("baseUrl") + "/login");
        logger.info("Login sayfasına gidildi.");
    }

    // ==========================================
    // ACTIONS
    // ==========================================
    public void enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        element.clear();
        element.sendKeys(username);
        logger.debug("Kullanıcı adı girildi: {}", username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        logger.debug("Şifre girildi.");
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        logger.debug("Giriş butonuna tıklandı.");
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        logger.debug("Çıkış butonuna tıklandı.");
    }

    // ==========================================
    // VALIDATIONS (doğrulama için veri döndüren metotlar)
    // ==========================================
    public String getFlashMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage));
        String text = driver.findElement(flashMessage).getText();
        logger.info("Flash mesajı okundu: {}", text);
        return text;
    }

    public boolean isSecureAreaDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(secureAreaHeader));
            String headerText = driver.findElement(secureAreaHeader).getText();
            logger.info("Secure area başlığı: {}", headerText);
            return headerText.contains("Secure Area");
        } catch (Exception e) {
            logger.error("Secure area görüntülenemedi: {}", e.getMessage());
            return false;
        }
    }
}