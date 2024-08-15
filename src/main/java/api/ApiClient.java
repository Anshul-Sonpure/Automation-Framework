package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.IOException;

public class ApiClient {
	
	private String baseUrl;


	public ApiClient(String baseUrl) {
		this.baseUrl = baseUrl;
		RestAssured.baseURI = baseUrl;
	}

	public static Response sendRequest(String endpoint, String httpMethod, Object payload, String id)
			throws IOException {

		String token = null;
		Response response = null;
		switch (httpMethod.toLowerCase()) {
		case "get":
			response = RestAssured.given().when().get(endpoint).then().extract().response();
			break;
		case "post":
			response = RestAssured.given().header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).contentType(ContentType.JSON).accept(ContentType.JSON)
					.body(payload).when().post(endpoint).then().extract().response();
			break;
		case "patch":
			response = RestAssured.given().header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).pathParam("id", id).contentType(ContentType.JSON)
					.body(payload).when().patch(endpoint).then().extract().response();
			break;
		case "delete":
			response = RestAssured.given().header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).pathParam("id", id).when().delete(endpoint).then()
					.extract().response();
			break;
		default:
			throw new UnsupportedOperationException("HTTP method not supported.");
		}
		return response;
	}
}