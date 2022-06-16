package com.nopcommerce.common;

import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Cookie;
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



public class Common_01_Register_Cookie extends BaseTest{
	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private String firstName, lastName;
	public static String  emailAddress, validPassword;
	public static Set<Cookie> loggedCookies;
	


	@Parameters("browser")
	@BeforeTest(description = "Create new common User for all Classes Test")
	public void Register(String browserName) {
		
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn"; // web mail server
		validPassword = "123456";
		
		
		
		log.info("Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();
		
		log.info("Register - Step 02: Enter to Firstname textbox with value is '"+ firstName +"' ");
		registerPage.inputToFirstnameTextbox(firstName);
		
		log.info("Register - Step 03: Enter to Lastname textbox with value is '"+ lastName +"' ");
		registerPage.inputToLastnameTextbox(lastName);
		
		log.info("Register - Step 04: Enter to Email textbox with value is '"+ emailAddress +"' ");
		registerPage.inputToEmailTextbox(emailAddress);
		
		log.info("Register - Step 05: Enter to Password textbox with value is '"+ validPassword +"' ");
		registerPage.inputToPasswordTextbox(validPassword);
		
		log.info("Register - Step 06: Enter to Confirm  Passwordtextbox with value is '"+ validPassword +"' ");
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		
		log.info("Register - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();
		
		log.info("Register - Step 08:  Verify register success message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
			
		log.info("Register - Step 09:  Click to Logout link");
		homePage = registerPage.clickToLogoutLink();

		log.info("Pre-Condition - Step 10:  Navigate to Login page");
		loginPage = homePage.openLoginPage();
	 
		log.info("Pre-Condition - Step 11: Enter to Email textbox with value is '"+ emailAddress +"' ");
		loginPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-Condition - Step 12: Enter to Email textbox with value is '"+ validPassword +"' ");
		loginPage.inputToPasswordTextbox(validPassword);
		
		log.info("Pre-Condition - Step 13: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		
		loggedCookies = homePage.getAllCookies(driver);
		for (Cookie cookie : loggedCookies) {
			System.out.println("Cookie at Common Class: " + loggedCookies);
		}
		
	}

	
	@AfterTest
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();

		return rand.nextInt(9999);
	}

}
