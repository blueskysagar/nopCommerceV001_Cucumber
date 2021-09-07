Feature: Customers 


Background: Below are common steps for each and every scenario 
	Given User launches chrome browser 
	When User opens URL "https://admin-demo.nopcommerce.com/login" 
	And User enters Email as "admin@yourstore.com" and Password as "admin" 
	And Click on Login 
	Then User can view the dashboard 
	
	@sanity
Scenario:  Add new Customers

	When User click on customers Menu 
	And click on customers Menu Item 
	And click on Add new button 
	Then User can view Add new Customer Page 
	When User enter customer info 
	And click on Save button 
	Then User can view confirmation message "The new customer has been added Sucessfully" 
	And close the browser 

@regression
Scenario: Search Customer by EMailID 

	When User click on customers Menu 
	And click on customers Menu Item 
	And Enter customer EMail 
	When click on search button 
	Then User should found Email in the Search table 
	And close the browser 
       
@regression
 Scenario: Search Customer by Name 

	When User click on customers Menu 
	And click on customers Menu Item 
	And Enter customer FirstName 
	And Enter customer LastName 
	When Click on search button 
	Then User should found Name in the Search table 
	And close browser