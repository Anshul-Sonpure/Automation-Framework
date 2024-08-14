package api;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class ApiClient {

	private String baseUrl;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.baseURI = baseUrl;
    }
    
    public ApiResponse sendRequest(ApiRequest request) {
        Response response = null;

        switch (request.getMethod()) {
            case "GET":
                response = RestAssured.given()
                        .headers(request.getHeaders())
                        .when()
                        .get(request.getEndpoint());
                break;

            case "POST":
                response = RestAssured.given()
                        .headers(request.getHeaders())
                        .body(request.getBody())
                        .when()
                        .post(request.getEndpoint());
                break;

            case "PUT":
                response = RestAssured.given()
                        .headers(request.getHeaders())
                        .body(request.getBody())
                        .when()
                        .put(request.getEndpoint());
                break;

            case "DELETE":
                response = RestAssured.given()
                        .headers(request.getHeaders())
                        .when()
                        
                        
                        
                        .delete(request.getEndpoint());
                break;

            default:
                throw new UnsupportedOperationException("HTTP method not supported.");
        }

        Headers headers = response.getHeaders();
        // Convert headers to a map (use a utility method)
        Map<String, String> headersMap = headers.asList().stream()
            .collect(Collectors.toMap(Header::getName, Header::getValue));
       
     // Format the response body as pretty-printed JSON
        String formattedBody = formatJson(response.getBody().asString());

        return new ApiResponse(response.getStatusCode(), formattedBody, headersMap);
        
    }
    private String formatJson(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            Object json = objectMapper.readValue(jsonString, Object.class);
            return objectMapper.writeValueAsString(json);
        } catch (Exception e) {
            return jsonString; // Return the original string if formatting fails
        }
    }
    
}
