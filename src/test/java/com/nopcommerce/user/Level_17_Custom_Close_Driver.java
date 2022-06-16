package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;



public class Level_17_Custom_Close_Driver extends BaseTest{
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String firstName, lastName, invalidEmail, notFoundEmail, emailAddress, validPassword;

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;



	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn"; 
		validPassword = "123456";
	
		log.info("Pre-Condition - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();
		
		log.info("Pre-Condition - Step 02: Enter to Firstname textbox with value is '"+ firstName +"' ");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Pre-Condition - Step 03: Enter to Lastname textbox with value is '"+ lastName +"' ");
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Pre-Condition - Step 04: Enter to Email textbox with value is '"+ emailAddress +"' ");
		registerPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-Condition - Step 05: Enter to Password textbox with value is '"+ validPassword +"' ");
		registerPage.inputToPasswordTextbox(validPassword);
		
		log.info("Pre-Condition - Step 06: Enter to Confirm  Passwordtextbox with value is '"+ validPassword +"' ");
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		
		log.info("Pre-Condition - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();
		
		driver = null;
		
		log.info("Pre-Condition - Step 08:  Verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed -test failed here");
			
		log.info("Pre-Condition - Step 09:  Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
		
		log.info("Pre-Condition - Step 10:  Navigate to Login page");
		loginPage = homePage.openLoginPage();
	 
		log.info("Pre-Condition - Step 11: Enter to Email textbox with value is '"+ emailAddress +"' ");
		loginPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-Condition - Step 12: Enter to Email textbox with value is '"+ validPassword +"' ");
		loginPage.inputToPasswordTextbox(validPassword);
		
		log.info("Pre-Condition - Step 13: Click to Login button");
		homePage = loginPage.clickToLoginButton();
	}

	@Test
	public void Search_01_Name() {
		
		
	}

	@Test
	public void Search_01_Address() {
		
	}
	
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	public int generateFakeNumber() {
		Random rand = new Random();

		return rand.nextInt(9999);
	}

}
