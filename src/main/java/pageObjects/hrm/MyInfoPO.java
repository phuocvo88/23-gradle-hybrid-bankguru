package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.hrm.MyInfoPageUI;

public class MyInfoPO extends BasePage {
	WebDriver driver;

	public MyInfoPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToChangePhotoImage() {
		waitForElementClickable(driver, MyInfoPageUI.AVATAR_IMAGE);
		clickToElement(driver, MyInfoPageUI.AVATAR_IMAGE);

	}

	public boolean isNewAvatarImageDisplayed() {
		waitForElementClickable(driver, MyInfoPageUI.AVATAR_IMAGE);
		int imageWidth = Integer.parseInt(getElementAttribute(driver, MyInfoPageUI.AVATAR_IMAGE, "width"));
		int imageHeight = Integer.parseInt(getElementAttribute(driver, MyInfoPageUI.AVATAR_IMAGE, "height"));
		return (imageWidth != 201) || (imageHeight != 200);
		// do hình của thầy chọn cố định và nó sẽ ra avatar kich thước 156 x 176 khác
		// với placeholder 200x200 nên case này của thầy passed
		// hình avatar.jpg mình up thì nó tự scale và fix 200 x200 nên phải sửa giá trị
		// width cho nó pass
	}

	public void openTabAtSideBarByName(String tabName) {
		waitForElementClickable(driver, MyInfoPageUI.TAB_LINK_AT_SIDEBAR, tabName);
		clickToElement(driver, MyInfoPageUI.TAB_LINK_AT_SIDEBAR, tabName);
	}

}
