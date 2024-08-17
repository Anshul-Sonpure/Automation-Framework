package base;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigManager;

public class baseSetup {
	
	
    static final String ZAP_API_KEY =ConfigManager.getConfigValue("ZapAPIKey");
    public static Logger logger = LogManager.getLogger("secTestlogger");
    public WebDriver driver;
    private ClientApi zapAPI;
    
    @BeforeMethod
    public void setup() throws InterruptedException, ClientApiException{
    	zapAPI = new ClientApi("localhost",8080, ZAP_API_KEY);
		

    	String sessionName = "Session_" + System.currentTimeMillis();
    	zapAPI.core.newSession(sessionName, "true");
    	
    	String proxyServerUrl = "localhost" + ":" + "8080";
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyServerUrl);
        proxy.setSslProxy(proxyServerUrl);

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setProxy(proxy);
		
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
       driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
       

    }
    
    @AfterMethod
    public void tearDown() throws Exception {
        if (zapAPI != null) {
        	
            String title = ConfigManager.getConfigValue("AppName")+" Security Report";
            String template = "traditional-html";
            String description = "Security Test Report based on the Top 10 Vulnerabilites listed by OWASP";
            String reportfilename = title+".html";
            String targetFolder = System.getProperty("user.dir");
            try {
                ApiResponse res = zapAPI.reports.generate(title, template, null, description, null, null, null,null, null, reportfilename,null, targetFolder,null);
                logger.info("Security Test Report generated for : {}",title);
            } catch (ClientApiException ex) {
                throw new Exception(ex);
            }
            driver.quit();

        }
    }

}
