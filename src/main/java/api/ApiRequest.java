package api;

import java.util.Map;

public class ApiRequest {

    private String method;
    private String endpoint;
    private String body;
    private Map<String, String> headers;

    public ApiRequest(String method, String endpoint, String body, Map<String, String> headers) {
        this.method = method;
        this.endpoint = endpoint;
        this.body = body;
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
