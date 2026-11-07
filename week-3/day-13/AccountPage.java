package day13;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends BasePage
{
	
	
	// Constructor
		public AccountPage(WebDriver driver)
		{
			super(driver); 
        }
		
   //Locators
   @FindBy(xpath="//h1[normalize-space()='My Account']")
   WebElement confirmationText_MyAccount;
		
		//Thread.sleep(15000); //putting wait on the code for 15 secs
		boolean status = driver.findElement(By.xpath("//h1[normalize-space()='My Account']")).isDisplayed();
		
  //Action Methods
		public WebElement getMyAccountConfirmation()
		{
			return confirmationText_MyAccount;
		}
}