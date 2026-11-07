package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseClass 
{
   
	public static WebDriver driver; //open the chrome
	public Properties p;
    
	@BeforeClass
    public void openApp() throws IOException
    {
	FileReader file= new FileReader(".//src//test//resources//config.properties");
	p = new Properties();
	p.load(file);
    driver = new ChromeDriver();
	//1) Launch Chrome browser
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	//2) Open URL: https://cloudberrystore.services/
	driver.get(p.getProperty("appURL"));
	driver.manage().window().maximize();
    }
    
    @AfterClass
	public void closeApp()
	{
	driver.quit();
	}
    
    public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
    
    
}