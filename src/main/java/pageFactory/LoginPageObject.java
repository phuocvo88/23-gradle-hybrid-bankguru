package pageFactory;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageObject extends BasePageFactory{
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "Email")
	private WebElement emailTextbox;
	
	@FindBy(id = "Password")
	private WebElement passwordTextbox;
	
	@FindBy (xpath = "//button[contains(@class,'login-button')]")
	private WebElement loginButton;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement email_Error_Message;
	
	@FindBy(xpath = "//div[contains(@class,'validation-summary-errors')]")
	private WebElement unsuccessful_Error_Message;
	

	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, email_Error_Message);
		return getElementText(driver, email_Error_Message);	
	}

	public void inputToEmailTextbox(String invalidEmail) {
		waitForElementVisible(driver, emailTextbox);
		sendKeyToElement(driver, emailTextbox, invalidEmail);
	}

	public String getErrorMessageUnsuccessful() {
		waitForElementVisible(driver, unsuccessful_Error_Message);
		return getElementText(driver, unsuccessful_Error_Message);	
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendKeyToElement(driver, passwordTextbox, password);
		
	}
	
	
}
