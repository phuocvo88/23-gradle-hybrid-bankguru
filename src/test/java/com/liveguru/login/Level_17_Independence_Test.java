package com.liveguru.login;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
//bài này thầy làm trang liveguru chậm, với lại phải define lại pageobject,pageUI,action mà ko thấy trên video, khó làm
public class Level_17_Independence_Test extends BaseTest {
	WebDriver driver;
	String emailAddress, password;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		emailAddress = getRandomEmail();
		password = "123456";

	}

	@BeforeMethod
	public void beforeMethod() {

	}

	@Test
	public void TC1() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getRandomEmail() {
		Random rand = new Random();
		return "testing" + rand.nextInt(9999) + "@live.com";

	}

//	HomePageObject homePage;
//	LoginPageObject loginPage;
//	MyDashboardPageObject myDashboardPage;

}
