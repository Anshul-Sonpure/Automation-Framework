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

	
	@Test
	public void postUserPost()
	{
		try {
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	        logger.info("Running test method: {}", methodName);
			File Posts = new File("src/test/resources/addPost.json");
			Response response = ApiClient.sendRequest("/posts/add","post",Posts,null,null);
			
			logger.info("*** Response Status Code ***: {}", response.getStatusCode());
	        logger.info("*** Response Body ***: {}", response.getBody().prettyPrint());
	        logger.info("*** Response Headers ***: {}", response.getHeaders());
	        Assert.assertEquals(response.getStatusCode(), 201, "Status code is not 201");
	        Assert.assertNotNull(response.getBody().asString(), "Response body is null");
		} catch (Exception e) {
	        // Log the error
	        logger.error("An unexpected error occurred: {}", e.getMessage(), e);
	        // Fail the test for any other exceptions
	        Assert.fail("Unexpected error occurred: " + e.getMessage());
		}
	}

	
	@Test
	public void postProducts()
	{
		try {
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	        logger.info("Running test method: {}", methodName);
			File Product = new File("src/test/resources/addProduct.json");
			Response response = ApiClient.sendRequest("/products/add","post",Product,null,null);
			
			logger.info("*** Response Status Code ***: {}", response.getStatusCode());
	        logger.info("*** Response Body ***: {}", response.getBody().prettyPrint());
	        logger.info("*** Response Headers ***: {}", response.getHeaders());
	        Assert.assertEquals(response.getStatusCode(), 201, "Status code is not 201");
	        Assert.assertNotNull(response.getBody().asString(), "Response body is null");
		} catch (Exception e) {
	        // Log the error
	        logger.error("An unexpected error occurred: {}", e.getMessage(), e);
	        // Fail the test for any other exceptions
	        Assert.fail("Unexpected error occurred: " + e.getMessage());
		}
	}
	
	
}
