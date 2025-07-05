package com.example.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.io.IOException;
import java.nio.file.*;

public class ExtentReportHook {
    private static boolean screenshotsCleaned = false;
    
    @Before
    public void beforeScenario(Scenario scenario) {
        // Clean screenshots directory only once before the first scenario
        if (!screenshotsCleaned) {
            ScreenshotUtil.createScreenshotDirectory();
            try {
                Path screenshotDir = Paths.get(ScreenshotUtil.getScreenshotDirectory());
                if (Files.exists(screenshotDir)) {
                    Files.walk(screenshotDir)
                        .filter(Files::isRegularFile)
                        .forEach(path -> {
                            try { Files.delete(path); } catch (IOException ignored) {}
                        });
                }
            } catch (IOException e) {
                System.out.println("Failed to clean screenshot directory: " + e.getMessage());
            }
            screenshotsCleaned = true;
        }
    }
    
    @AfterStep
    public void afterStep(Scenario scenario) {
        // No @AfterStep screenshot logic here
    }
    
    @After
    public void afterScenario(Scenario scenario) {
        // Capture screenshot on test failure and attach to ExtentReports
        if (scenario.isFailed()) {
            try {
                // Capture screenshot
                String screenshotPath = ScreenshotUtil.captureScreenshot(
                    scenario.getName().replaceAll("[^a-zA-Z0-9]", "_"), 
                    "FAILED"
                );
                
                // Attach screenshot to Cucumber scenario
                byte[] screenshotBytes = ScreenshotUtil.captureScreenshotAsBytes();
                scenario.attach(screenshotBytes, "image/png", "Screenshot on Failure");
            } catch (IOException e) {
                System.out.println("Failed to capture screenshot on test failure: " + e.getMessage());
            }
        }
    }
} 