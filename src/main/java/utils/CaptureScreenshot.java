package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.baseSetup;


public class CaptureScreenshot extends baseSetup  {
	
	
	public static String capturescreen(String name)
	{
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File src = takescreenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File("Screenshot/"+name);
        try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}        
                     
        return destination.getAbsolutePath();
	
	}

}
