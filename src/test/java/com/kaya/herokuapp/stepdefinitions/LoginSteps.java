package com.kaya.herokuapp.stepdefinitions;

import com.kaya.herokuapp.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("kullanıcı login sayfasındadır")
    public void kullaniciLoginSayfasindadir() {
        loginPage = new LoginPage();
        loginPage.goToLoginPage();
    }

    @When("kullanıcı {string} ve {string} ile giriş yapar")
    public void kullaniciIleGirisYapar(String username, String password) {
        loginPage.login(username, password);
    }

    @When("kullanıcı çıkış yapar")
    public void kullaniciCikisYapar() {
        loginPage.clickLogout();
    }

    @Then("{string} mesajı görüntülenir")
    public void mesajiGoruntulenir(String beklenenMesaj) {
        String gercekMesaj = loginPage.getFlashMessage();
        Assert.assertTrue(gercekMesaj.contains(beklenenMesaj),
                "Beklenen mesaj bulunamadı. Beklenen: '" + beklenenMesaj + "', Gerçek: '" + gercekMesaj + "'");
    }

    @And("secure area sayfası açılır")
    public void secureAreaSayfasiAcilir() {
        Assert.assertTrue(loginPage.isSecureAreaDisplayed(),
                "Secure Area başlığı görüntülenemedi.");
    }
}