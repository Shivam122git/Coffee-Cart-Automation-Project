package com.coffeecart.listeners;

import com.coffeecart.managers.DriverManager;
import com.coffeecart.utils.ConfigReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        takeScreenshot(testName);
        logFailureDetails(result);
    }

    private void takeScreenshot(String testName) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String screenshotName = testName + "_" + timestamp + ".png";

            File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "output/screenshots/" + screenshotName;

            FileUtils.copyFile(screenshot, new File(screenshotPath));
            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logFailureDetails(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        System.out.println("Failure Reason: " + result.getThrowable().getMessage());
        System.out.println("Test Class: " + result.getTestClass().getName());
        System.out.println("Test Method: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        generateCustomReport(context);
    }

    private void generateCustomReport(ITestContext context) {
        System.out.println("=== Test Execution Summary ===");
        System.out.println("Total Tests: " + context.getAllTestMethods().length);
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
        System.out.println("==============================");
    }
}