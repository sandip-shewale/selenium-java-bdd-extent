package com.example.core;

import com.example.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class DriverManager {
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThread.get() == null) {
            initializeDriver();
        }
        return driverThread.get();
    }

    private static void initializeDriver() {
        String browser = ConfigManager.getProperty("browser", "chrome").toLowerCase();
        boolean headless = ConfigManager.getBooleanProperty("headless");

        WebDriver driver = null;
        switch (browser) {
            case "chrome":
                driver = createChromeDriver(headless);
                break;
            case "firefox":
                driver = createFirefoxDriver(headless);
                break;
            case "edge":
                driver = createEdgeDriver(headless);
                break;
            case "safari":
                driver = createSafariDriver();
                break;
            default:
                driver = createChromeDriver(headless);
        }

        setupDriver(driver);
        driverThread.set(driver);
    }

    private static WebDriver createChromeDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless");
        }
        return new EdgeDriver(options);
    }

    private static WebDriver createSafariDriver() {
        return new SafariDriver();
    }

    private static void setupDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(ConfigManager.getIntProperty("implicit.wait", 10)));
        driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(ConfigManager.getIntProperty("page.load.timeout", 30)));
    }

    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                // Handle driver quit failure silently
            }
            driverThread.remove();
        }
    }

    public static void closeDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            try {
                driver.close();
            } catch (Exception e) {
                // Handle driver close failure silently
            }
        }
    }

    public static boolean isDriverActive() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            try {
                // Try to get window handles to check if driver is still active
                driver.getWindowHandles();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
} 