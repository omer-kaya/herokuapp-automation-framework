package com.kaya.herokuapp.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
    @Given("kullanıcı login sayfasındadır")
    public void kullanıcıLoginSayfasındadır() {
    }

    @When("kullanıcı {string} ve {string} bilgilerle giriş yapar")
    public void kullanıcıVeBilgilerleGirişYapar(String arg0, String arg1) {
    }

    @Then("{string} mesajı görüntülenir")
    public void mesajıGörüntülenir(String arg0) {
    }

    @And("secure area sayfası açılır")
    public void secureAreaSayfasıAçılır() {
    }

    @When("kullanıcı {string} ve {string} ile giriş yapar")
    public void kullanıcıVeIleGirişYapar(String arg0, String arg1) {
    }

    @When("kullanıcı {string} ve {string} ile giirş yapar")
    public void kullanıcıVeIleGiirşYapar(String arg0, String arg1) {
    }

    @And("kullanıcı çıkış yapar")
    public void kullanıcıÇıkışYapar() {
    }

    @Then("{string} esajı görüntülenir")
    public void esajıGörüntülenir(String arg0) {
    }
}
