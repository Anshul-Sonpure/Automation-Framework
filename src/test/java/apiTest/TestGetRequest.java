package apiTest;

import java.util.*;

import org.testng.annotations.*;

import api.ApiClient;
import api.ApiRequest;
import api.ApiResponse;
import utils.ConfigManager;

public class TestGetRequest {
	
	String apiUrl = ConfigManager.getConfigValue("api.base.url");
	ApiClient client = new ApiClient(apiUrl);
	Map<String, String> headers = new HashMap<>();
    
	
	
	@Test
	public void getSingleUser()
	{
		headers.put("Accept", "application/json");
        ApiRequest request = new ApiRequest("GET", "/users/1", null, headers);
        ApiResponse response = client.sendRequest(request);

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        System.out.println("Response Headers: " + response.getHeaders());
	}
	
	@Test
	public void getAllUsers()
	{
		headers.put("Accept", "application/json");
        ApiRequest request = new ApiRequest("GET", "/users", null, headers);
        ApiResponse response = client.sendRequest(request);

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        System.out.println("Response Headers: " + response.getHeaders());
	}

}
