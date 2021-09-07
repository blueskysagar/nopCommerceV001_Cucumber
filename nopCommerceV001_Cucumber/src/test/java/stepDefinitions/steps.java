package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class steps extends BaseClass {
	@Before
	public void setup() throws IOException  {
		logger = Logger.getLogger("nopCommerceV001_Cucumber");// Added logger
		PropertyConfigurator.configure("log4j.properties");// Added logger
		
		//Reading Properties
		configProp = new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);
		
		
		
		String br = configProp.getProperty("browser");
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
		    driver = new ChromeDriver();
		}
		else
		{
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
		    driver = new ChromeDriver();
		}
	    
	    logger.info("*******Launching browser**********");
	}
	
	@Given("User launches chrome browser")
	public void user_launches_chrome_browser() {
		
	    lp = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
		logger.info("**********Opening URL*******");
		driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) {
		logger.info("**********Providing Login Details*******");
		lp.setUserName(email);
	    lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() throws InterruptedException {
		Thread.sleep(3000);
		logger.info("****Started Login*****");
		lp.clickLogin();
	}
	

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
		if(driver.getPageSource().contains("Login was unsuccessful.")) {
			   driver.close();
			   logger.info("**********Login Passed********");
			   Assert.assertTrue(false);
		   }else {
			   logger.info("*****Login Failed******");
			   Assert.assertEquals(title, driver.getTitle());
		   }   
		Thread.sleep(3000);
	}

	@When("User click on Logout Link")
	public void user_click_on_logout_link() throws InterruptedException {
		logger.info("*********Click on Logout Link*******");
		lp.clickLogout();
		   Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("*******closing the browser*******");
	    driver.close();
	}


//Customers Feature Step Definition

@Then("User can view the dashboard")
public void user_can_view_the_dashboard() {
    addCust = new AddcustomerPage(driver);
    Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
    
}

@When("User click on customers Menu")
public void user_click_on_customers_Menu() throws InterruptedException {
	Thread.sleep(3000);
	addCust.clickOnCustomersMenu();
}

@When("click on customers Menu Item")
public void click_on_customers_Menu_Item() throws InterruptedException {
	 Thread.sleep(2000); 
		addCust.clickOnCustomersMenuItem();
}


@When("click on Add new button")
public void click_on_add_new_button() throws InterruptedException {
    
	addCust.clickOnAddnew();
	Thread.sleep(2000);
}
@Then("User can view Add new Customer Page")
public void user_can_view_add_new_customer_page() 
{
  Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());  
}
@When("User enter customer info")
public void user_enter_customer_info() throws InterruptedException 
{
	logger.info("*******Adding new Customer*****");
	logger.info("********Providing Customer Details*******");
    String email = randomstring()+"@gmail.com";
    addCust.setEmail(email);
    addCust.setPassword("test123");
    addCust.setCustomerRoles("Guests");
    Thread.sleep(3000);
    addCust.setManagerOfVendor("Vendor 2");
    addCust.setGender("Male");
    addCust.setFirstName("Sagar");
    addCust.setLastName("Shrestha");
    addCust.setDob("03/10/1991");
    addCust.setCompanyName("SagarQA");
    addCust.setAdminContent("This is for testing............");    
}
@When("click on Save button")
public void click_on_save_button() throws InterruptedException 
{
	logger.info("***********Saving customer data*******");
   addCust.clickOnSave();
   Thread.sleep(2000);
}
@Then("User can view confirmation message {string}")
public void user_can_view_confirmation_message(String msg)
{
 Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));   
}


// steps for searching the customer using EmailID.....
@When("Enter customer EMail")
public void enter_customer_e_mail() {
	logger.info("******Searching customer by email id********");
    searchCust = new SearchCustomerPage(driver);
    searchCust.setEmail("victoria_victoria@nopCommerce.com");
}
@When("click on search button")
public void click_on_search_button() throws InterruptedException {
   searchCust.clickSearch();
   Thread.sleep(3000);
}
@Then("User should found Email in the Search table")
public void user_should_found_email_in_the_search_table() {
  boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
  Assert.assertEquals(true, status);
}
 @Then("close the browser")
public void close_the_browser()
 {
    driver.close();
}

 //Steps for searching customer using First Name and Last Name
 @When("Enter customer FirstName")
 public void enter_customer_first_name() {
	 logger.info("******Searching Customer by Name*******");
	 searchCust = new SearchCustomerPage(driver);
	 searchCust.setFirstName("Victoria");
 }
 @When("Enter customer LastName")
 public void enter_customer_last_name() {
     searchCust.setLastName("Terces");
 }
 
 @When("Click on search button")
 public void click_on_search_button1() throws InterruptedException {
     searchCust.clickSearch();
     Thread.sleep(2000);
 }

 
 @Then("User should found Name in the Search table")
 public void user_should_found_name_in_the_search_table() {
    boolean status = searchCust.searchCustomerByName("Victoria Terces");
    Assert.assertEquals(true,status );
 }





}