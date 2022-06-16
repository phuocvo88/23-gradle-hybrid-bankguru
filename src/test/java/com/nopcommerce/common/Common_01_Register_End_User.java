package com.nopcommerce.common;

import java.util.Random;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;



public class Common_01_Register_End_User extends BaseTest{
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String firstName, lastName;
	public static String  emailAddress, validPassword;


	@Parameters("browser")
	@BeforeTest(description = "Create new common User for all Classes Test")
	public void Register(String browserName) {
		
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn"; // web mail server
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
		
		log.info("Pre-Condition - Step 08:  Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
			
		log.info("Pre-Condition - Step 09:  Click to Logout link");
		homePage = registerPage.clickToLogoutLink();

//		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
		driver.quit();
		
	}

	
	@AfterTest
	public void afterClass() {
	
	}

	public int generateFakeNumber() {
		Random rand = new Random();

		return rand.nextInt(9999);
	}

}
