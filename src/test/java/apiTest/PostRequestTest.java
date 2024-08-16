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

	File loginUser = new File("src/test/resources/loginUser.json");

	@Test
	public void LoginUserToken() {
		try {
				
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
	        logger.info("Running test method: {}", methodName);
			Response response = ApiClient.sendRequest("auth/login", "post", loginUser, null, null);
			String token = response.body().jsonPath().getString("token").toString();
			logger.info("Login User Token written in LoginUserToken.txt");
	        WriteFile.writeToFile("./src/test/resources/LoginUserToken.txt",token);
			logger.info("Running test method: {}", methodName);
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
