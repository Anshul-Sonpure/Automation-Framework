package apiTest;

import java.util.*;

import org.testng.annotations.*;

import api.ApiClient;
import api.ApiRequest;
import api.ApiResponse;

public class TestGetRequest {
	
	@Test
	public void getRequestTest()
	{
		ApiClient client = new ApiClient("https://dummyjson.com");

        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        ApiRequest request = new ApiRequest("GET", "/users/1", null, headers);
        ApiResponse response = client.sendRequest(request);

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        System.out.println("Response Headers: " + response.getHeaders());
	}

}
