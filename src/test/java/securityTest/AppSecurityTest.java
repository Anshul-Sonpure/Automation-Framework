package securityTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.baseSetup;
import utils.ConfigManager;

public class AppSecurityTest extends baseSetup {
	
	@Test
	public void SecurityTest()
	{
		driver.get(ConfigManager.getConfigValue("security.base.url"));
        Assert.assertTrue(driver.getTitle().contains("User Management System"));
	}

}
