package day5;

import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class TC01_LaunchApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		Test case
		---------
		1) Launch Chrome browser
		2) Open URL: https://cloudberrystore.services/
		3) Validate page title = "Your store of fun"
		4) Close browser
		*/

		//1)) Launch Chrome Browser
		WebDriver driver = new ChromeDriver(); //Launch Browser
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		
		
		//2) Open URL: https://cloudberrystore.services/
		driver.get("https://cloudberrystore.services/");
		driver.manage().window().maximize();		
		
		//3) Validate page title = "Your store of fun"
		String title = driver.getTitle();
		System.out.println(title);
		
		if (title.equals("Your store of funnnnn")) 
		{
			System.out.println("Test Passed");
		}else{
			System.out.println("Test Failed");
		}
		
		//4) Close browser
		driver.quit();
	}

}
