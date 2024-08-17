package apiTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.ApiClient;
import io.restassured.response.Response;
import utils.ConfigManager;
import utils.WriteFile;

public class EndToEndTest {
	
	String apiUrl = ConfigManager.getConfigValue("api.base.url");
	ApiClient apiClient = new ApiClient(apiUrl);
	public static Logger logger = LogManager.getLogger("uiLogger");
	
	String RefreshToken =null;
	
	@Test(priority = 1)
	public void getLoginUserToken() {
		try {
				
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	        logger.info("Running test method: {}", methodName);
	        File loginUser = new File("src/test/resources/loginUser.json");
			Response response = ApiClient.sendRequest("/auth/login", "post", loginUser, null, null);
			String token = response.body().jsonPath().getString("token").toString();
			
			RefreshToken = response.body().jsonPath().getString("refreshToken").toString();
			logger.info("Login User Token written in LoginUserToken.txt");
	        WriteFile.writeToFile("./src/test/resources/LoginUserToken.txt",token);
			logger.info("*** Response Status Code ***: {}", response.getStatusCode());
			logger.info("*** Response Body ***: {}", response.getBody().prettyPrint());
			logger.info("*** Response Headers ***: {}", response.getHeaders());
			Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
			Assert.assertNotNull(response.getBody().asString(), "Response body is null");
			
		} catch (IOException e) {
			// Log the error
			logger.error("An unexpected error occurred: {}", e.getMessage(), e);
			// Fail the test for any other exceptions
			Assert.fail("Unexpected error occurred: " + e.getMessage());
		}

	}
	
	@Test(priority = 2)
	public void getAuthUser() {
	    try {
	    	
	    	Path path = Paths.get("./src/test/resources/LoginUserToken.txt");
			String token = Files.readString(path);
	        Response response = ApiClient.sendRequest("/user/me", "get", null, null,token);
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
	
	
	@Test(dependsOnMethods = {"getLoginUserToken"})
	public void getRefreshToken() {
	    try {
	    	
	    	JSONObject payload = new JSONObject();
	    	payload.put("refreshToken",RefreshToken );
	    	payload.put("expiresInMins",45);
	    	
	        Response response = ApiClient.sendRequest("/auth/refresh", "post", payload, null,null);
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
	
	@Test(priority = 3)
	public void addUser()
	{
		try {
			
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	        logger.info("Running test method: {}", methodName);
	        File addUser = new File("src/test/resources/addUser.json");
			Response response = ApiClient.sendRequest("/users/add", "post",addUser, null, null);
			logger.info("User Added");
			logger.info("*** Response Status Code ***: {}", response.getStatusCode());
			logger.info("*** Response Body ***: {}", response.getBody().prettyPrint());
			logger.info("*** Response Headers ***: {}", response.getHeaders());
			Assert.assertEquals(response.getStatusCode(), 201, "Status code is not 201");
			Assert.assertNotNull(response.getBody().asString(), "Response body is null");
			
		} catch (IOException e) {
			// Log the error
			logger.error("An unexpected error occurred: {}", e.getMessage(), e);
			// Fail the test for any other exceptions
			Assert.fail("Unexpected error occurred: " + e.getMessage());
		}
	}

	
	@Test(priority = 4)
	public void updateUserDetails()
	{
		try {
			
			JSONObject updateUser = new JSONObject();
			updateUser.put("firstName","Elliot");
			updateUser.put("lastName","Anderson");
			updateUser.put("age",28);
			
			
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	        logger.info("Running test method: {}", methodName);
	        
			Response response = ApiClient.sendRequest("/users/2", "put",updateUser, null, null);
			logger.info("User Details Updated");
			logger.info("*** Response Status Code ***: {}", response.getStatusCode());
			logger.info("*** Response Body ***: {}", response.getBody().prettyPrint());
			logger.info("*** Response Headers ***: {}", response.getHeaders());
			Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
			Assert.assertNotNull(response.getBody().asString(), "Response body is null");
			
		} catch (IOException e) {
			// Log the error
			logger.error("An unexpected error occurred: {}", e.getMessage(), e);
			// Fail the test for any other exceptions
			Assert.fail("Unexpected error occurred: " + e.getMessage());
		}
	}
	
	
	@Test(priority = 5)
	public void deleteUserDetails()
	{
		try {
			
			
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	        logger.info("Running test method: {}", methodName);
	        
			Response response = ApiClient.sendRequest("/users", "delete",null, "4", null);
			logger.info("User Deleted");
			logger.info("*** Response Status Code ***: {}", response.getStatusCode());
			logger.info("*** Response Body ***: {}", response.getBody().prettyPrint());
			logger.info("*** Response Headers ***: {}", response.getHeaders());
			Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
			Assert.assertNotNull(response.getBody().asString(), "Response body is null");
			
		} catch (IOException e) {
			// Log the error
			logger.error("An unexpected error occurred: {}", e.getMessage(), e);
			// Fail the test for any other exceptions
			Assert.fail("Unexpected error occurred: " + e.getMessage());
		}
	}
	
	
}
