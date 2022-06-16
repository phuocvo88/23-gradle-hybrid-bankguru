package pageObjects.sauceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sauceLab.InventoryPageUI;

public class InventoryPageObject extends BasePage {
	private WebDriver driver;

	public InventoryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInSortDropdown(String itemText) {
		waitForElementClickable(driver, InventoryPageUI.SORT_DROPDOWN);
		selectItemInDefaultDropdownByText(driver, InventoryPageUI.SORT_DROPDOWN, itemText);
	}

	public boolean isProductNameSortAscending() {
		List<WebElement> productNameElements = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_TEXT);

		List<String> productNameText = new ArrayList<String>();
		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}

		// Shallow copy, lấy toàn bộ dữ liệu của productNameText truyền vào list clone
		List<String> productNameTextClone = new ArrayList<String>(productNameText);
		Collections.sort(productNameTextClone);

		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductNameSortDescending() {
		List<WebElement> productNameElements = getListWebElement(driver, InventoryPageUI.PRODUCT_NAME_TEXT);

		List<String> productNameText = new ArrayList<String>();
		for (WebElement productName : productNameElements) {
			productNameText.add(productName.getText());
		}

		// Shallow copy, lấy toàn bộ dữ liệu của productNameText truyền vào list clone
		List<String> productNameTextClone = new ArrayList<String>(productNameText);
		Collections.sort(productNameTextClone);
		Collections.reverse(productNameTextClone);

		return productNameText.equals(productNameTextClone);
	}

	public boolean isProductPriceSortAscending() {
		List<WebElement> productPriceElements = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);

		List<Float> productPriceName = new ArrayList<Float>();
		for (WebElement productPrice : productPriceElements) {
			Float productPriceNumber = Float.parseFloat(productPrice.getText().replace("$", "")) ;
			productPriceName.add(productPriceNumber) ;
		}

		// Shallow copy, lấy toàn bộ dữ liệu của productNameText truyền vào list clone
		List<Float> productPriceTextClone = new ArrayList<Float>(productPriceName);
		Collections.sort(productPriceTextClone);

		return productPriceName.equals(productPriceTextClone);

	}

	public boolean isProductPriceSortDescending() {
		List<WebElement> productPriceElements = getListWebElement(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);

		List<Float> productPriceName = new ArrayList<Float>();
		for (WebElement productPrice : productPriceElements) {
			Float productPriceNumber = Float.parseFloat(productPrice.getText().replace("$", "")) ;
			productPriceName.add(productPriceNumber) ;
		}

		// Shallow copy, lấy toàn bộ dữ liệu của productNameText truyền vào list clone
		List<Float> productPriceTextClone = new ArrayList<Float>(productPriceName);
		Collections.sort(productPriceTextClone);
		Collections.reverse(productPriceTextClone);
		
		return productPriceName.equals(productPriceTextClone);
	}

}
