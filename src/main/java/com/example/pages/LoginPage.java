package com.example.pages;

import com.example.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    // Locators
    private static final By USERNAME_FIELD = By.id("username");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("submit");
    private static final By ERROR_MESSAGE = By.xpath("//div[@id='error']");

    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "submit")
    WebElement loginButton;

    @FindBy(xpath = "//div[@id='error']")
    WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        sendKeys(USERNAME_FIELD, username);
    }

    public void enterPassword(String password) {
        sendKeys(PASSWORD_FIELD, password);
    }

    public void clickLogin() {
        click(LOGIN_BUTTON);
    }

    public String getErrorMessage() {
        waitForElementToBeVisible(ERROR_MESSAGE);
        String errorText = getText(ERROR_MESSAGE);
        return errorText;
    }

    public boolean isErrorDisplayed() {
        return isElementDisplayed(ERROR_MESSAGE);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(USERNAME_FIELD) && isElementDisplayed(PASSWORD_FIELD);
    }

    public void waitForLoginPageToLoad() {
        waitForElementToBeVisible(USERNAME_FIELD);
        waitForElementToBeVisible(PASSWORD_FIELD);
    }
} 