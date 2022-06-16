package com.nopcommerce.user;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;





public class Level_15_ExtentV5 extends BaseTest{
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
//		homePage = new HomePageObject(driver);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Automation";
		lastName = "FC";
		validPassword = "123456";
		emailAddress = "afc" + generateFakeNumber() + "@mail.vn"; // web mail server
	
	}

	@Test
	public void User_01_Register(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_01_Register");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Enter to Firstname textbox with value is '"+ firstName +"' ");
		registerPage.inputToFirstnameTextbox(firstName);
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to Lastname textbox with value is '"+ lastName +"' ");
		registerPage.inputToLastnameTextbox(lastName);
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to Email textbox with value is '"+ emailAddress +"' ");
		registerPage.inputToEmailTextbox(emailAddress);
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Enter to Password textbox with value is '"+ validPassword +"' ");
		registerPage.inputToPasswordTextbox(validPassword);
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06: Enter to Confirm  Passwordtextbox with value is '"+ validPassword +"' ");
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08:  Verify register success message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
			
	}

	@Test
	public void User_02_Login(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_02_Login");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01:  Navigate to Login page");
		homePage = registerPage.clickToLogoutLink();
	
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Enter to Email textbox with value is '"+ emailAddress +"' ");
		loginPage = homePage.openLoginPage();
	 
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Enter to Email textbox with value is '"+ validPassword +"' ");
		loginPage.inputToEmailTextbox(emailAddress);
		
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Click to Login button");
		loginPage.inputToPasswordTextbox(validPassword);
		
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05: Verify 'My Account' link is displayed");
		homePage = loginPage.clickToLoginButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 06: Navigate to 'My Account' page");
		Assert.assertFalse(homePage.isMyAccountLinkDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 07: Verify 'Customer Infor' page is displayed");
		customerInforPage = homePage.openMyAccountPage();
		
		
		Assert.assertFalse(customerInforPage.isCustomerInforPageDisplayed());
		
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
