Feature: Login

@Sanity
Scenario: Successful login with valid credentials
Given User launches chrome browser
When User opens URL "https://admin-demo.nopcommerce.com/login"
And User enters Email as "admin@yourstore.com" and Password as "admin"
And Click on Login
Then Page Title should be "Dashboard / nopCommerce administration"
When User click on Logout Link
Then close browser



@regression
Scenario Outline: Login Data Driven
Given User launches chrome browser
When User opens URL "https://admin-demo.nopcommerce.com/login"
And User enters Email as "<email>" and Password as "<password>"
And Click on Login
Then Page Title should be "Dashboard / nopCommerce administration"
When User click on Logout Link
Then close browser


Examples:
| email | password |
| admin@yourstore.com  |  admin  |
| admin1@yourstore.com |  admin123  |
