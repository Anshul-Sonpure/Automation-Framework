package securityTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.baseSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigManager;

public class AppSecurityTest extends baseSetup {
	
	@Test
	public void SecurityTest()
	{
		driver.get(ConfigManager.getConfigValue("security.base.url"));
        Assert.assertTrue(driver.getTitle().contains("User Management System"));
	}
	
	@Test
    public void testSqlInjection() {
        String maliciousInput = "' OR 1=1 --";
        
        // Send the request with malicious input
        Response response = RestAssured.given()
            .queryParam("username", maliciousInput)
            .post(ConfigManager.getConfigValue("security.base.url"));

        // Check the response for any signs of SQL injection vulnerabilities
        int responseCode = response.getStatusCode();
        String status = response.getStatusLine();
        
       //Assert for the Status
        Assert.assertEquals(status, "HTTP/1.1 403 Forbidden");
    }


}
