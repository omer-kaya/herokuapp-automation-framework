package com.kaya.herokuapp.stepdefinitions;

import com.kaya.herokuapp.pages.CheckboxesPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckboxesSteps {

    private CheckboxesPage checkboxesPage;

    @Given("kullanıcı checkboxes sayfasındadır")
    public void kullaniciCheckboxesSayfasindadir() {
        checkboxesPage = new CheckboxesPage();
        checkboxesPage.goToCheckboxesPage();
    }

    @Then("toplam checkbox sayısı {int} olmalıdır")
    public void toplamCheckboxSayisiOlmalidir(int beklenenSayi) {
        int gercekSayi = checkboxesPage.getCheckboxCount();
        Assert.assertEquals(gercekSayi, beklenenSayi,
                "Checkbox sayısı beklenenden farklı. Beklenen: " + beklenenSayi + ", Gerçek: " + gercekSayi);
    }

    @When("{int}. index'teki checkbox işaretlenir")
    public void indexTekiCheckboxIsaretlenir(int index) {
        checkboxesPage.checkCheckboxByIndex(index);
    }

    @When("{int}. index'teki checkbox işareti kaldırılır")
    public void indexTekiCheckboxIsaretiKaldirilir(int index) {
        checkboxesPage.uncheckCheckboxByIndex(index);
    }

    @Then("{int}. index'teki checkbox işaretlidir")
    public void indexTekiCheckboxIsaretlidir(int index) {
        Assert.assertTrue(checkboxesPage.isCheckboxSelected(index),
                index + ". index'teki checkbox işaretli olmalıydı ama değildi.");
    }

    @Then("{int}. index'teki checkbox işaretli değildir")
    public void indexTekiCheckboxIsaretliDegildir(int index) {
        Assert.assertFalse(checkboxesPage.isCheckboxSelected(index),
                index + ". index'teki checkbox işaretsiz olmalıydı ama işaretliydi.");
    }
}