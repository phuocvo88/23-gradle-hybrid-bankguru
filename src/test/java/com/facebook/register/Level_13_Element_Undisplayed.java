package com.facebook.register;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.facebook.LoginPageObject;

import pageObjects.facebook.PageGeneratorManager;

public class Level_13_Element_Undisplayed extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;


	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();
		
		verifyTrue(loginPage.isEmailAddressTextboxDisplayed());
	}


	@Test
	public void TC_02_Verify_Element_Undisplayed_In_Dom() {
		//Nếu như mình mong đợi 1 hàm vừa verify displayed/ undispalyed thì không được kết hợp wait
		// waitForElementVisible và isElementDisplayed
		//Vì nó đã mong đợi nó k displayed mà còn wait nữa thì sẽ fail test case
		
		
		//Verify True - mong đợi Confirm Email displayed (true)
		loginPage.enterToEmailAddressTextbox("autoamtionfc@gmail.com");
		loginPage.sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed()); 
		
		
		//Verify False - mong đợi Confirm Email Undisplayed (false)
		loginPage.enterToEmailAddressTextbox("");
		loginPage.sleepInSecond(3);
		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		
	}
	
	@Test
	public void TC_03_Verify_Element_Undisplayed_Not_In_Dom() {
		loginPage.clickCloseIconAtRegisterForm();
		loginPage.sleepInSecond(3);
		
		//Khi close cái form Register đi thì Confirm Email không còn trong DOM nữa
		//Nên hàm findElement sẽ bị fail vì k tìm thây element
		//1- Nó sẽ chờ hết timeout của implicit: 30s
		//2- Nó sẽ đánh fial test case tại đúng step này luôn =>NoSuchElementException
		//3- Không chạy các step còn lại nữa
		
		//Verify False - mong đợi confirm email undisplayed(false)
		// Hàm isDisplayed bản chất không kiểm tra 1 element không có trong DOM được 
		// vì element không có trong DOM thì chưa pass được step findElement
		//verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());
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
