package apiTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import api.ApiClient;
import io.restassured.response.Response;
import utils.ConfigManager;

public class GetRequestTest {
	
	String apiUrl = ConfigManager.getConfigValue("api.base.url");
	ApiClient apiClient = new ApiClient(apiUrl);
	public static Logger logger = LogManager.getLogger("uiLogger");
	
	
	@Test
	public void getSingleUser()
	{
		try {
			Response response = ApiClient.sendRequest("/users/1","get",null,null,null);
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	        logger.info("Running test method: {}", methodName);
			logger.info("*** Response Status Code ***: {}", response.getStatusCode());
	        logger.info("*** Response Body ***: {}", response.getBody().prettyPrint());
	        logger.info("*** Response Headers ***: {}", response.getHeaders());
	        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
	        Assert.assertNotNull(response.getBody().asString(), "Response body is null");
		} catch (Exception e) {
	        // Log the error
	        logger.error("An unexpected error occurred: {}", e.getMessage(), e);
	        // Fail the test for any other exceptions
	        Assert.fail("Unexpected error occurred: " + e.getMessage());
		}
	}
	
	@Test
	public void getAllUsers() {
	    try {
	        Response response = ApiClient.sendRequest("/users", "get", null, null,null);
	        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	        logger.info("Running test method: {}", methodName);
	        logger.info("*** Response Status Code ***: {}", response.getStatusCode());
	        logger.info("*** Response Body ***: {}", response.getBody().prettyPrint());
	        logger.info("*** Response Headers ***: {}", response.getHeaders());
	        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
	        Assert.assertNotNull(response.getBody().asString(), "Response body is null");
	     
	    } catch (Exception e) {
	        // Log the error
	        logger.error("An unexpected error occurred: {}", e.getMessage(), e);
	        // Fail the test for any other exceptions
	        Assert.fail("Unexpected error occurred: " + e.getMessage());
	    }
	}
	
	
	@Test
	public void getSearchUser() {
	    try {
	    	
	    	String user ="Michael";
	    	
	        Response response = ApiClient.sendRequest("/users/search?q="+user, "get", null, null,null);
	        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	        logger.info("Running test method: {}", methodName);
	        logger.info("*** Response Status Code ***: {}", response.getStatusCode());
	        logger.info("*** Response Body ***: {}", response.getBody().prettyPrint());
	        logger.info("*** Response Headers ***: {}", response.getHeaders());
	        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
	        Assert.assertNotNull(response.getBody().asString(), "Response body is null");
	     
	    } catch (Exception e) {
	        // Log the error
	        logger.error("An unexpected error occurred: {}", e.getMessage(), e);
	        // Fail the test for any other exceptions
	        Assert.fail("Unexpected error occurred: " + e.getMessage());
	    }
	}
	
}


