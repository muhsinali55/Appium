package appium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject() {

        String path = System.getProperty("user.dir") + "/reports/index.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Appium Automation Report");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        extent.setSystemInfo("Tester", "Muhsin");
        extent.setSystemInfo("Platform", "Android");
        extent.setSystemInfo("Framework", "TestNG + Appium");

        return extent;
    }
}
