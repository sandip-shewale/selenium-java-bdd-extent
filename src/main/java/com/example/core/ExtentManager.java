package com.example.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.example.config.ConfigManager;

public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            String reportPath = ConfigManager.getProperty("extent.reports.path", "target/extent-reports/") + "ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            
            // Configure the reporter
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("BDD Selenium Test Report");
            sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
            sparkReporter.config().setCss(".badge { font-size: 100%; }");
            
            // Configure for better screenshot handling
            sparkReporter.config().setEncoding("utf-8");
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            
            // Set system info
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Selenium Version", "4.21.0");
            extent.setSystemInfo("Test Framework", "Cucumber + TestNG");
            
            // Set the working directory for screenshots
            extent.setSystemInfo("Working Directory", System.getProperty("user.dir"));
        }
        return extent;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
} 