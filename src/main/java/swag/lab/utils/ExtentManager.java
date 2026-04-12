package swag.lab.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            new File("reports").mkdirs();  // ⭐ important fix

            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport.html");

            reporter.config().setReportName("Swag Labs Automation Report");
            reporter.config().setDocumentTitle("Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        return extent;
    }
}