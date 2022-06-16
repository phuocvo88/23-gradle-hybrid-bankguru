package com.saucelab.sort;

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
import pageObjects.sauceLab.InventoryPageObject;
import pageObjects.sauceLab.LoginPageObject;
import pageObjects.sauceLab.PageGenerator;

public class Level_18_Sort extends BaseTest {
	private WebDriver driver;
	LoginPageObject loginPage;
	InventoryPageObject inventoryPage;

	@Parameters({"browser" ,"url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName,appUrl);
		
		loginPage = PageGenerator.getLoginPage(driver);
		loginPage.enterToUsernameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		inventoryPage = loginPage.clickToLoginButton();
	}

	@Test
	public void Sort_01_Name() {
		inventoryPage.selectItemInSortDropdown("Name (A to Z)");
		verifyTrue(inventoryPage.isProductNameSortAscending());
		
		inventoryPage.selectItemInSortDropdown("Name (Z to A)");
		verifyTrue(inventoryPage.isProductNameSortDescending());
		
	}

	@Test
	public void Sort_02_Price() {
		inventoryPage.selectItemInSortDropdown("Price (low to high)");
		verifyTrue(inventoryPage.isProductPriceSortAscending());
		
		inventoryPage.selectItemInSortDropdown("Price (high to low)");
		verifyTrue(inventoryPage.isProductPriceSortDescending());
	}
	
	

	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void cleanBrowser(String browserName) {
		log.info("Post-Condition: Close browser'" + browserName + "'");
		closeBrowserAndDriver();
		
	}

	public int generateFakeNumber() {
		Random rand = new Random();

		return rand.nextInt(9999);
	}

}
