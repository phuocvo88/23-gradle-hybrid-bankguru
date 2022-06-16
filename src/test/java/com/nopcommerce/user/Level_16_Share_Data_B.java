package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_End_User;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;



public class Level_16_Share_Data_B extends BaseTest{
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

		//Pre-condition
		firstName = "Automation";
		lastName = "FC";
		validPassword = "123456";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn"; // web mail server
		
		//Register Account
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
	public void Search_01_Empty_Data() {
		
		

	}
	
	@Test
	public void Search_02_Relative_Product_Name() {
		
		

	}
	
	@Test
	public void Search_01_Absolute_Product_Name() {
		
		

	}
	
	@Test
	public void Search_04_Parent_Category() {
		
		

	}
	
	@Test
	public void Search_05_Incorrect_Manufacturer() {
		
		

	}
	
	@Test
	public void Search_06_Correct_Manufacturer() {
		
		

	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();

		return rand.nextInt(9999);
	}

}
