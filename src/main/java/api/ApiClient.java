package api;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class ApiClient {

	private String baseUrl;

	public ApiClient(String baseUrl) {
		this.baseUrl = baseUrl;
		RestAssured.baseURI = baseUrl;
	}

	public static Response sendRequest(String endpoint, String httpMethod, Object payload, String id, String token)
			throws IOException {
		Response response = null;
		
		// Convert payload to String if it's a file
        if (payload instanceof File) {
            payload = new String(Files.readAllBytes(((File) payload).toPath()));
        }

        // Construct the request based on HTTP method
        switch (httpMethod.toLowerCase()) {
            case "get":
                response = RestAssured.given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get(endpoint)
                        .then()
                        .extract()
                        .response();
                break;
            case "post":
                response = RestAssured.given()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + token)
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(payload)
                        .when()
                        .post(endpoint)
                        .then()
                        .extract()
                        .response();
                break;
            case "put":
                response = RestAssured.given()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + token)
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .when()
                        .put(endpoint)
                        .then()
                        .extract()
                        .response();
                break;
            case "patch":
                response = RestAssured.given()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + token)
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .pathParam("id", id)
                        .when()
                        .patch(endpoint)
                        .then()
                        .extract()
                        .response();
                break;
            case "delete":
                response = RestAssured.given()
                        .header("Authorization", "Bearer " + token)
                        .pathParam("id", id)
                        .when()
                        .delete(endpoint)
                        .then()
                        .extract()
                        .response();
                break;
            default:
                throw new UnsupportedOperationException("HTTP method not supported.");
        }
        return response;
    }

    
}