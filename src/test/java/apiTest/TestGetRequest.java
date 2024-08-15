package apiTest;

import java.util.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import api.ApiClient;
import api.ApiRequest;
import api.ApiResponse;
import utils.ConfigManager;

public class TestGetRequest {
	
	String apiUrl = ConfigManager.getConfigValue("api.base.url");
	ApiClient apiClient = new ApiClient(apiUrl);
	Map<String, String> headers = new HashMap<>();
	public static Logger logger = LogManager.getLogger("uiLogger");
	
	
	@Test
	public void getSingleUser()
	{
		headers.put("Accept", "application/json");
        ApiRequest request = new ApiRequest("GET", "/users/1", null, headers);
        ApiResponse response = apiClient.sendRequest(request);
        logger.info("*** Response Status Code ***: {}", response.getStatusCode());
        logger.info("*** Response Body ***: {}", response.getBody().toString());
        logger.info("*** Response Headers ***: {}", response.getHeaders().toString());
	}
	
	@Test
	public void getAllUsers()
	{
		headers.put("Accept", "application/json");
        ApiRequest request = new ApiRequest("GET", "/users", null, headers);
        ApiResponse response = apiClient.sendRequest(request);
        logger.info("*** Response Status Code ***: {}", response.getStatusCode());
        logger.info("*** Response Body ***: {}", response.getBody().toString());
        logger.info("*** Response Headers ***: {}", response.getHeaders().toString());
	}

}
