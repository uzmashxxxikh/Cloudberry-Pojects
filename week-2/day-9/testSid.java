package day9;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class testSid {

	// 1) Launch Chrome browser
	WebDriver driver = new ChromeDriver();

	@Test(priority = 1)
	void openApp() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// 2) Open URL: https://cloudberrystore.services/
		driver.get("https://cloudberrystore.services/");
		driver.manage().window().maximize();
	}

	@Test(priority = 2, dependsOnMethods = { "openApp" }, dataProvider = "DataP")
	void testLogin(String email, String pwd) {
		// 3) Click on 'My Account'
		// i[@class='fa-solid fa-user']

		driver.findElement(By.xpath("//i[@class='fa-solid fa-user']")).click();

		// 4) Click 'Login'
		driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();

		// 5) Enter valid Email and Password
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);

		// 6)
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

		// 7) Verify Successful login
		// Thread.sleep(5000);
		boolean status = driver.findElement(By.xpath("//h1[normalize-space()='My Account']")).isDisplayed();
		Assert.assertTrue(true);

		if (status == true)
		{

		//logout steps
		driver.findElement(By.xpath("//li[@class='list-inline-item']//i[@class='fa-solid fa-caret-down']")).click();
		driver.findElement(By.xpath("//a[@class='dropdown-item'][normalize-space()='Logout']")).click();
		Assert.assertTrue(true);

		}

	}

	@Test (priority =3)
	void closeApp()
	{
	driver.quit();
	}

	@DataProvider(name="DataP")
	Object[][] loginData()
	{
	Object data[][] = {
	    {"uzmashxikh@hotmail.com", "uzmauzma"},
	    {"nona@yahoo.com", "QWER!@#$"},
	    {"sid@cloudberry.services", "Test123"},
	    {"apushedwoman@gmail.com", "password123"},
	    {"hichamkhachiai@gmail.com", "hicham123"},
	};
	return data;
	}

}
