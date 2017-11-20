Feature: Logging into NGP

In order to migrate VPC to NPG portel 
As a migration users
I want to login and verify the dashboard

@login
Scenario: Successful Login with Valid Credentials
Given I navigate to "NGPURL" on "Browser"
And I enter "loginusername" as "Username"
And I enter "loginpassword" as "Password"
And I click on "loginButton"
Then Login should be "Expected_Result"