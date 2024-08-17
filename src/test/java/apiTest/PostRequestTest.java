package apiTest;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.ApiClient;
import io.restassured.response.Response;
import utils.ConfigManager;
import utils.WriteFile;

public class PostRequestTest {

	String apiUrl = ConfigManager.getConfigValue("api.base.url");
	ApiClient apiClient = new ApiClient(apiUrl);

	public static Logger logger = LogManager.getLogger("uiLogger");

	

	
	
}
