package com.nopcommerce.user;

import java.lang.reflect.Method;
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





public class Level_15_ExtentV3_Screenshot extends BaseTest{
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
		registerPage = homePage.openRegisterPage();
		
		registerPage.inputToFirstnameTextbox(firstName);
		
		registerPage.inputToLastnameTextbox(lastName);
		
		registerPage.inputToEmailTextbox(emailAddress);
		
		registerPage.inputToPasswordTextbox(validPassword);
		
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		
		registerPage.clickToRegisterButton();
		
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
			
	}

	@Test
	public void User_02_Login(Method method) {
		homePage = registerPage.clickToLogoutLink();
	
		loginPage = homePage.openLoginPage();
	 
		loginPage.inputToEmailTextbox(emailAddress);
		
		loginPage.inputToPasswordTextbox(validPassword);
		
		homePage = loginPage.clickToLoginButton();
		
		Assert.assertFalse(homePage.isMyAccountLinkDisplayed());

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
