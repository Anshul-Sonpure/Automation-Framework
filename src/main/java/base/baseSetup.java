package base;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import utils.ConfigManager;

public class baseSetup extends BrowserManager {

	static final String ZAP_API_KEY = ConfigManager.getConfigValue("ZapAPIKey");
	public static Logger logger = LogManager.getLogger("secTestlogger");
	private static ClientApi zapAPI;
	protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

	@BeforeClass
	public static void setup() throws Exception {

		zapAPI = new ClientApi("localhost", 8080, ZAP_API_KEY);

		String sessionName = "Session_" + System.currentTimeMillis();
		zapAPI.core.newSession(sessionName, "true");
		WebDriver driver = BrowserManager.initDriver();
		threadLocalDriver.set(driver);

		driver = getDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}

	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}

	@AfterClass
	public void tearDown() throws Exception {
	    if (zapAPI != null) {
	        String title = ConfigManager.getConfigValue("AppName") + " Security Report";
	        String template = "traditional-html";
	        String description = "Security Test Report based on the Top 10 Vulnerabilities listed by OWASP";
	        String reportFilename = title + ".html";
	        String targetFolder = System.getProperty("user.dir");

	        try {
	            ApiResponse res = zapAPI.reports.generate(title, template, null, description, null, null, null, null, null, reportFilename, null, targetFolder, null);
	            logger.info("Security Test Report generated for: {}", title);
	        } catch (ClientApiException ex) {
	            throw new Exception(ex);
	        }
	    }
	    driver = getDriver();  
	    if (driver != null) {
	        driver.quit();  
	        threadLocalDriver.remove(); 
	    }
	    
	    ExtentManager.removeExtentTest();  
	}


}
