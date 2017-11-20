package com.dxc.vpc.automation.login;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.dxc.vpc.automation.util.WebConnector;

import cucumber.api.java.en.*;

public class LoginNGPTest {
	
	WebConnector selenium = WebConnector.getInstance();
	Properties property = selenium.getLocalProperties();
	
	@Given("^I navigate to \"([^\"]*)\" on \"([^\"]*)\"$")
	public void navigateTo(String NGPURL,String Browser) {
		
		selenium.log("loggin in browser");
		String webBrowser =property.getProperty("webBrowser");
		String webUrl =property.getProperty("webUrl");
		selenium.openBrowser(webBrowser);
		selenium.navigateToURL(webUrl);
	    //selenium.isElementPresentCustom("xpath");
	}
	
	@And("^I enter \"([^\"]*)\" as \"([^\"]*)\"$")
	public void enter(String Object,String text) {
		System.out.println("Enter values in"+ Object + "--values---"+ text);
		String userName =property.getProperty("vpcUserName");
		String userNameId = "migration";
		selenium.click(userName, userNameId);
		
		
	}
	@And("^I click on \"([^\"]*)\"$")
	public void clickOn(String Object) {
		
	
		
	}
	
	@Then("^Login should be \"([^\"]*)\"$")
	public void login(String expectedResult) {
		
		
	}

}
