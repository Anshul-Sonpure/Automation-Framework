package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {

	public static Logger logger = LogManager.getLogger("uiLogger");
	
	
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
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.warn("Test method skipped: {}", methodName);
    }

	

}
