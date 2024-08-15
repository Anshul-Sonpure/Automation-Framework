package apiTest;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import api.ApiClient;
import utils.ConfigManager;

public class PostRequestTest {

	String apiUrl = ConfigManager.getConfigValue("api.base.url");
	ApiClient apiClient = new ApiClient(apiUrl);
	
	public static Logger logger = LogManager.getLogger("uiLogger");
	
	File loginUser = new File("src/test/resources/loginUser.json");
	
	@Test
	public void LoginUserToken()
	{
		
		
		
	}
}
