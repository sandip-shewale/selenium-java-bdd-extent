package com.example.core;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {
    
    private static final String SCREENSHOT_DIR = "target/extent-reports/screenshots/";
    
    /**
     * Captures a screenshot and saves it to the specified directory
     * @param scenarioName Name of the scenario for filename
     * @param suffix Suffix to add to filename (e.g., "step", "final")
     * @return Relative path to the screenshot for ExtentReports
     * @throws IOException if screenshot capture fails
     */
    public static String captureScreenshot(String scenarioName, String suffix) throws IOException {
        // Create timestamp for unique filename
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
        
        // Create filename
        String fileName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + suffix + "_" + timestamp + ".png";
        String filePath = SCREENSHOT_DIR + fileName;
        
        // Capture screenshot
        WebDriver driver = DriverManager.getDriver();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        // Copy to destination
        Path destination = Paths.get(filePath);
        Files.copy(screenshot.toPath(), destination);
        
        // Return relative path for ExtentReports - use the correct format
        return "screenshots/" + fileName;
    }
    
    /**
     * Captures a screenshot as bytes for Cucumber scenario attachment
     * @return Screenshot as byte array
     */
    public static byte[] captureScreenshotAsBytes() {
        WebDriver driver = DriverManager.getDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    
    /**
     * Creates the screenshot directory if it doesn't exist
     */
    public static void createScreenshotDirectory() {
        try {
            Path screenshotDir = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(screenshotDir)) {
                Files.createDirectories(screenshotDir);
            }
        } catch (IOException e) {
            System.out.println("Failed to create screenshot directory: " + e.getMessage());
        }
    }
    
    /**
     * Gets the screenshot directory path
     * @return Screenshot directory path
     */
    public static String getScreenshotDirectory() {
        return SCREENSHOT_DIR;
    }
} 