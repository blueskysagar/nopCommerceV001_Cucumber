package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddcustomerPage {
	public WebDriver ldriver;
	public AddcustomerPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
		By lnkCustomers_menu = By.xpath("//p[normalize-space()='Customers']//i[contains(@class,'right fas fa-angle-left')]");
		By lnkCustomers_menuitem = By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
		By btnAddnew = By.xpath("//a[normalize-space()='Add new']");
		By txtEmail = By.xpath("//input[@id='Email']");
		By txtPassword = By.xpath("//input[@id='Password']");
		By txtFirstName = By.xpath("//input[@id='FirstName']");
		By txtLastName = By.xpath("//input[@id='LastName']");
		By rdMaleGender = By.xpath("//label[normalize-space()='Male']");
		By rdFemaleGender = By.xpath("//label[normalize-space()='Female']");
		By txtDob = By.xpath("//input[@id='DateOfBirth']");
	    By txtcompanyName = By.xpath("//input[@id='Company']");
	    By txtcustomerRoles = By.xpath("//div[@class = 'k-multiselect-wrap k-floatwrap' ]");
		By lstitemAdministrator = By.xpath("//li[contains(text(), 'Administrators')]");
		By lstitemForumModerators = By.xpath("//li[normalize-space()='Forum Moderators']");
		By lstitemGuests = By.xpath("//li[normalize-space()='Guests']");
		By lstitemRegistered = By.xpath("//span[normalize-space()='Registered']");
		By lstitemVendors = By.xpath("//li[contains(text(),'Vendors')]");
		By drpmgrOfVendor = By.xpath("//select[@id='VendorId']");
		By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");
		By btnSave = By.xpath("//button[@name='save']");
		
		
		//Actions Methods
		public String getPageTitle()
		{
			return ldriver.getTitle();
		}
		
		
		public void clickOnCustomersMenu() {
			ldriver.findElement(lnkCustomers_menu).click();
		}
		
		public void clickOnCustomersMenuItem() {
			ldriver.findElement(lnkCustomers_menuitem).click();
		}
		
		public void clickOnAddnew() {
			ldriver.findElement(btnAddnew).click();
		}
		
		public void setEmail(String email) {
			ldriver.findElement(txtEmail).sendKeys(email);
		}
		
		public void setPassword(String password) {
			ldriver.findElement(txtPassword).sendKeys(password);
		}
		
		public void setCustomerRoles(String role ) throws InterruptedException
		{
			if(!role.equals("Vendors"))
			{
				ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
			}
			ldriver.findElement(txtcustomerRoles).click();
			WebElement listitem;
			Thread.sleep(3000);
			
			if(role.equals("Administrators"))
			{
				listitem = ldriver.findElement(lstitemAdministrator);
			}
			else if(role.equals("Guests"))
			{
				listitem = ldriver.findElement(lstitemGuests);
			}
			else if(role.equals("Registered"))
			{
				listitem = ldriver.findElement(lstitemRegistered);
			}
			else if(role.equals("Vendors"))
			{
				listitem = ldriver.findElement(lstitemVendors);
			}
			else 
			{
				listitem = ldriver.findElement(lstitemForumModerators);
			}
			JavascriptExecutor js = (JavascriptExecutor)ldriver;
			js.executeScript("arguments[0].click();", listitem);
		}
		
	public void setManagerOfVendor(String value)
	{
		Select drp = new Select(ldriver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);
	}
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			ldriver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female"))
		{
			ldriver.findElement(rdFemaleGender).click();
		}
		else
		{
			ldriver.findElement(rdMaleGender).click();
		}
	}
	public void setFirstName(String fname)
	{
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	public void setLastName(String lname)
	{
		ldriver.findElement(txtLastName).sendKeys(lname);
	}
	public void setDob(String dob)
	{
		ldriver.findElement(txtDob).sendKeys(dob);
	}
	public void setCompanyName(String comname)
	{
		ldriver.findElement(txtcompanyName).sendKeys(comname);
	}
	public void setAdminContent(String content)
	{
		ldriver.findElement(txtAdminContent).sendKeys(content);
	}
	public void clickOnSave()
	{
		ldriver.findElement(btnSave).click();
	}
	
	
}
