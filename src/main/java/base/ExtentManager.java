package base;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static String timeStamp = new SimpleDateFormat("dd-MM-yyyy-HH_mm_ss").format(new Date());
    
    // Initialize ExtentReports instance
    public static ExtentReports getExtentReports() {
        if (extentReports == null) {
            extentReports = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Reports/extentreport_" + timeStamp + ".html");
            extentReports.attachReporter(sparkReporter);
            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Test Report");
            extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
            extentReports.setSystemInfo("JVM", System.getProperty("java.runtime.version"));
            extentReports.setSystemInfo("User Directory", System.getProperty("user.dir"));
            extentReports.setSystemInfo("User", System.getProperty("user.name"));
            
        }
        return extentReports;
    }

    // Set ExtentTest instance for the current thread
    public static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    // Get ExtentTest instance for the current thread
    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    // Remove ExtentTest instance from the current thread
    public static void removeExtentTest() {
        extentTest.remove();
    }
}
