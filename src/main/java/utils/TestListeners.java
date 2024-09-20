package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;

import base.ExtentManager;

public class TestListeners implements ITestListener {

	public static Logger logger = LogManager.getLogger("uiLogger");
	public static String screenshot;
	String classname;
	
	@Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.info("Starting test method: {}", methodName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.info("Test method passed: {}", methodName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.error("Test method failed: {}", methodName);
        String screenshotPath = null;
        try {
            screenshotPath = CaptureScreenshot.capturescreen("Test_Failed_" + ExtentManager.timeStamp + ".png");
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("An Error occurred while taking screenshot", e);
        }

        if (screenshotPath != null) {
            ExtentManager.getExtentTest()
                .fail("Screenshot of the failure", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.warn("Test method skipped: {}", methodName);
        
        String screenshotPath = null;
        try {
            screenshotPath = CaptureScreenshot.capturescreen("Test_Failed_" + ExtentManager.timeStamp + ".png");
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("An Error occurred while taking screenshot", e);
        }

        if (screenshotPath != null) {
            ExtentManager.getExtentTest()
                .fail("Screenshot of the failure", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

	

}
