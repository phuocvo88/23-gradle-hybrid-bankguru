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
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;


public class Level_10_DataTable_DataGrid extends BaseTest{
	private WebDriver driver;
	HomePageObject homePage;
	List<String> actualAllCountryValues;
	List<String> expectedAllCountryValues;
	

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {

		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	//@Test
	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("10"));
		
		homePage.openPagingByPageNumber("20");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("20"));
		
		homePage.openPagingByPageNumber("7");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("7"));
		
	}

	

	//@Test
	public void Table_02_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);
	
		homePage.enterToHeaderTextboxByLabel("Country", "Argentina");
		homePage.enterToHeaderTextboxByLabel("Females", "338282");
		homePage.enterToHeaderTextboxByLabel("Males", "349238");
		homePage.enterToHeaderTextboxByLabel("Total", "687522");
		homePage.sleepInSecond(3);
		
		homePage.enterToHeaderTextboxByLabel("Country", "Angola");
		homePage.enterToHeaderTextboxByLabel("Females", "276880");
		homePage.enterToHeaderTextboxByLabel("Males", "276472");
		homePage.enterToHeaderTextboxByLabel("Total", "553353");
		homePage.sleepInSecond(3);
	}

	//@Test
	public void Table_03_Enter_To_Header() {
		//đọc dữ liệu của file countery.txt ra
		//Lưu vào 1 List<String> = expected value = expectedAllCountryValues
		
		//Actual value
		actualAllCountryValues =  homePage.getValueEachRowAtAllPage();
		
		Assert.assertEquals(actualAllCountryValues, expectedAllCountryValues);
	}

	@Test
	public void Table_04_Enter_To_Textbox_At_Any_Row() {
		
		homePage.clickToLoadButton();
		homePage.sleepInSecond(2);
		//Value để nhập liệu - tham số 1
		//Row number tại row nào
		// Ex: nhập vào textbox tai dòng số 3/ 5/ 2
		// column name: Album/ Artist/ Year/ Price
		
//		homePage.enterToTextboxByColumnNameAtRowNumber("Album", "2", "Michael 97");
//		homePage.sleepInSecond(1);
//		
//		homePage.enterToTextboxByColumnNameAtRowNumber("Artist", "4", "Michael Jackson");
//		homePage.sleepInSecond(1);
//		
//		homePage.enterToTextboxByColumnNameAtRowNumber("Year", "3", "1997");
//		homePage.sleepInSecond(1);
//		
//		homePage.enterToTextboxByColumnNameAtRowNumber("Price", "1", "115");
//		homePage.sleepInSecond(1);
//		
//		homePage.selectDropdownByColumnNameAtRowNUmber("Origin", "1", "Japan");
//		homePage.sleepInSecond(1);
//		
//		homePage.selectDropdownByColumnNameAtRowNUmber("Origin", "1", "Hong Kong");
//		homePage.sleepInSecond(1);
//		
//		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "3");
//		homePage.checkToCheckboxByColumnNameAtRowNumber("With Poster?", "5");
//		
//		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "1");
//		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "2");
//		homePage.uncheckToCheckboxByColumnNameAtRowNumber("With Poster?", "4");
		
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("1", "Insert Row Above");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("3", "Move Up");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("5", "Remove Current Row");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("4", "Remove Current Row");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("3", "Remove Current Row");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("2", "Remove Current Row");
		homePage.sleepInSecond(1);
		
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.sleepInSecond(1);
		
	}
	
	
	
	
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	public int generateFakeNumber() {
		Random rand = new Random();

		return rand.nextInt(9999);
	}

}
