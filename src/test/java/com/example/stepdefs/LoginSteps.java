package com.example.stepdefs;

import com.example.config.ConfigManager;
import com.example.core.DriverManager;
import com.example.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.cucumber.java.Scenario;
import org.junit.Assert;

public class LoginSteps {
    private LoginPage loginPage;
    private Scenario scenario;

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        String baseUrl = ConfigManager.getProperty("base.url");
        DriverManager.getDriver().get(baseUrl);
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.waitForLoginPageToLoad();
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("I should see the homepage")
    public void i_should_see_the_homepage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Handle interruption silently
        }
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue("Expected URL to contain 'logged-in-successfully', but got: " + currentUrl,
                        currentUrl.contains("logged-in-successfully"));
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Handle interruption silently
        }
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue("Expected error message to contain 'Your username is invalid!', but got: " + errorMsg,
                errorMsg.contains("Your username is invalid!"));
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
} 