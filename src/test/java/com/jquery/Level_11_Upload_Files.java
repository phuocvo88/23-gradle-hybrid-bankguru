package com.jquery;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;

import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGeneratorManager;

public class Level_11_Upload_Files extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;

	String csharpFileName = "csharp.jpg";
	String javaFileName = "java.png";
	String pythonFileName = "python.png";
	String rubyFileName = "ruby.png";

	String[] multipleFileNames = { csharpFileName, javaFileName, pythonFileName, rubyFileName };

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Upload_01_One_File_Per_Time() {
		// Step 01 - Load single file
		homePage.uploadMultipleFiles(driver, csharpFileName);

		// Step 02 - Verify single file loaded success
		Assert.assertTrue(homePage.isFileLoadedByName(csharpFileName));

		// Step 03- Click to Start button
		homePage.clickToStartButton();

		// Step 04 - Verify single file link uploaded success
		Assert.assertTrue(homePage.isFileLinkUploadedByName(csharpFileName));

		// Step 05 - Verify single file image upload success
		Assert.assertTrue(homePage.isFileImageUploadedByName(csharpFileName));
	}

	@Test
	public void Upload_02_Multiple_File_Per_Time() {
		homePage.refreshCurrentPage(driver);
		// Step 01 - Load multiple file
		homePage.uploadMultipleFiles(driver, multipleFileNames);

		// Step 02 - Verify multiple file loaded success
		// csharpFileName, javaFileName, pythonFileName, rubyFileName
		Assert.assertTrue(homePage.isFileLoadedByName(csharpFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(javaFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(pythonFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(rubyFileName));

		// Step 03- Click to Start button
		homePage.clickToStartButton();

		// Step 04 - Verify single file link uploaded success
		Assert.assertTrue(homePage.isFileLinkUploadedByName(csharpFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(javaFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(pythonFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(rubyFileName));

		// Step 05 - Verify single file image upload success
		Assert.assertTrue(homePage.isFileImageUploadedByName(csharpFileName));
		Assert.assertTrue(homePage.isFileImageUploadedByName(javaFileName));
		Assert.assertTrue(homePage.isFileImageUploadedByName(pythonFileName));
		Assert.assertTrue(homePage.isFileImageUploadedByName(rubyFileName));

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
