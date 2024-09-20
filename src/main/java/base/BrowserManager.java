package base;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigManager;


public class BrowserManager {
	public static  WebDriver driver = null;
	public static String browser;
	
	public static WebDriver initDriver() throws Exception {
	    browser = ConfigManager.getConfigValue("browser");
	    System.out.println("Browser initialized as :" + browser);

	    // Create proxy configuration
	    Proxy proxy = new Proxy();
	    proxy.setHttpProxy("localhost:8080");
	    proxy.setSslProxy("localhost:8080");
	    Object options = getBrowserOptions(browser, proxy);
	    WebDriver driver;
	    switch (browser.toLowerCase()) {
	        case "chrome":
	            WebDriverManager.chromedriver().clearDriverCache().setup();
	            System.setProperty("webdriver.chrome.silentOutput", "true");
	            driver = new ChromeDriver((ChromeOptions) options);
	            break;
	        case "firefox":
	            WebDriverManager.firefoxdriver().setup();
	            driver = new FirefoxDriver((FirefoxOptions) options);
	            break;
	        case "ie":
	            WebDriverManager.iedriver().setup();
	            driver = new InternetExplorerDriver((InternetExplorerOptions) options);
	            break;
	        default:
	            throw new IllegalStateException(browser + " is not a recognized browser.");
	    }
	    
	    return driver; 
	}

	public static Object getBrowserOptions(String browser, Proxy proxy) {
	    if (browser.equalsIgnoreCase("chrome")) {
	        ChromeOptions options = new ChromeOptions();
	        options.setProxy(proxy);
	        options.setAcceptInsecureCerts(true);
	        options.addArguments("--ignore-certificate-errors");
	        options.addArguments("--remote-allow-origins=*");
	        options.setAcceptInsecureCerts(true);
	        return options;
	    } else if (browser.equalsIgnoreCase("firefox")) {
	        FirefoxOptions options = new FirefoxOptions();
	        options.setProxy(proxy);
	        options.setAcceptInsecureCerts(true);
	        options.addArguments("--ignore-certificate-errors");
	        options.addArguments("--remote-allow-origins=*");
	        return options;
	    } else if (browser.equalsIgnoreCase("ie")) {
	        InternetExplorerOptions options = new InternetExplorerOptions();
	        options.setProxy(proxy);
	        options.setCapability("acceptInsecureCerts", true);
	        return options;
	    } else {
	        throw new IllegalArgumentException("Browser type not supported");
	    }
	}
	
}
