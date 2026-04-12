package swag.lab.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport.html");

            reporter.config().setReportName("Swag Labs Automation Report");
            reporter.config().setDocumentTitle("Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        return extent;
    }	
	
	
	
	
}
