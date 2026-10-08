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
import java.util.List;

public class CheckboxesPage {

    private static final Logger logger = LogManager.getLogger(CheckboxesPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    // ==========================================
    // LOCATORS
    // ==========================================
    // Dikkat: tekil değil, ÇOĞUL bir locator - sayfadaki TÜM checkbox'ları bulur
    private By allCheckboxes = By.cssSelector("#checkboxes input[type='checkbox']");

    // ==========================================
    // CONSTRUCTOR
    // ==========================================
    public CheckboxesPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getIntProperty("explicitWait")));
    }

    // ==========================================
    // NAVIGATION
    // ==========================================
    public void goToCheckboxesPage() {
        driver.get(ConfigReader.getProperty("baseUrl") + "/checkboxes");
        wait.until(ExpectedConditions.visibilityOfElementLocated(allCheckboxes));
        logger.info("Checkboxes sayfasına gidildi.");
    }

    // ==========================================
    // ACTIONS
    // ==========================================

    // Sayfadaki TÜM checkbox'ları bir liste olarak döndürür
    private List<WebElement> getAllCheckboxes() {
        return driver.findElements(allCheckboxes);
    }

    // Index numarasına göre (0'dan başlayarak) belirli bir checkbox'ı işaretler
    public void checkCheckboxByIndex(int index) {
        List<WebElement> checkboxes = getAllCheckboxes();
        WebElement checkbox = checkboxes.get(index);

        if (!checkbox.isSelected()) {
            checkbox.click();
            logger.info("{}. index'teki checkbox işaretlendi.", index);
        } else {
            logger.info("{}. index'teki checkbox zaten işaretliydi, tıklanmadı.", index);
        }
    }

    // Index numarasına göre belirli bir checkbox'ın işaretini kaldırır
    public void uncheckCheckboxByIndex(int index) {
        List<WebElement> checkboxes = getAllCheckboxes();
        WebElement checkbox = checkboxes.get(index);

        if (checkbox.isSelected()) {
            checkbox.click();
            logger.info("{}. index'teki checkbox'ın işareti kaldırıldı.", index);
        } else {
            logger.info("{}. index'teki checkbox zaten işaretsizdi, tıklanmadı.", index);
        }
    }

    // ==========================================
    // VALIDATIONS
    // ==========================================

    // Belirli bir index'teki checkbox işaretli mi, değil mi?
    public boolean isCheckboxSelected(int index) {
        List<WebElement> checkboxes = getAllCheckboxes();
        boolean selected = checkboxes.get(index).isSelected();
        logger.info("{}. index'teki checkbox durumu: {}", index, selected ? "işaretli" : "işaretsiz");
        return selected;
    }

    // Sayfada toplam kaç checkbox olduğunu döndürür
    public int getCheckboxCount() {
        int count = getAllCheckboxes().size();
        logger.info("Sayfada toplam {} checkbox bulundu.", count);
        return count;
    }
}