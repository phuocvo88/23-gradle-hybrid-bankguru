package com.hrm.employee;

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
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.PageGenerator;
import reportConfig.ExtentTestManager;
import pageObjects.hrm.MyInfoPO;

public class Level_16_Live_Coding extends BaseTest {
	String employeeID, statusValue;
	String adminUserName, adminPassword, empFirstName, empLastName, empFullName, empUserName, empPassword;
	String editEmpFirstName, editEmpLastName, editEmpGender, editEmpMaritalStatus, editEmpNationality;
	String avatarFilePath = GlobalConstants.UPLOAD_FILE_FOLDER + "Avatar.jpg";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "' ");
		driver = getBrowserDriver(browserName, appUrl);

		loginPage = PageGenerator.getLoginPage(driver);

		statusValue = "Enabled";
		adminUserName = "Admin";
		adminPassword = "admin123";
		empFirstName = "Automation";
		empLastName = "FC";
		empUserName = "afcvn";
		empPassword = "automation123";
		empFullName = empFirstName + " " + empLastName;

		editEmpFirstName = "John";
		editEmpLastName = "Wick";
		editEmpGender = "Male";
		editEmpMaritalStatus = "Single";
		editEmpNationality = "Vietnamese";

		log.info("Pre-condition - Step 02: Login with Admin Role");
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);
	}

	@Test
	public void Employee_01_Add_New_Employee(Method method) {
		//log.info("Add_New_01 - Step 01: Open 'Employee List' page");
		ExtentTestManager.startTest(method.getName(), "Employee_01_Add_New_Employee");
		ExtentTestManager.getTest().log(Status.INFO, " Step 01:");
		// A: Dashboard
		dashboardPage.openSubMenuPage_HRM(driver, "PIM", "Employee List");
		showBrowserConsoleLogs(driver);
		
		// B: EmployeeList
		ExtentTestManager.getTest().log(Status.INFO, " Step 01:");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);
		showBrowserConsoleLogs(driver);
		
		//log.info("Add_New_01 - Step 02: Click to 'Add' button ");	
		ExtentTestManager.getTest().log(Status.INFO, " Step 01:");
		employeeListPage.clickToButtonByID_HRM(driver, "btnAdd");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);

//		log.info("Add_New_01 - Step 03: Enter valid info to 'First Name' textbox");
		ExtentTestManager.getTest().log(Status.INFO, " Step 01:");
		addEmployeePage.enterToTextboxByID_HRM(driver, "firstName", empFirstName);

//		log.info("Add_New_01 - Step 04:  Enter valid info to 'Last Name' textbox");
		ExtentTestManager.getTest().log(Status.INFO, " Step 01:");
		addEmployeePage.enterToTextboxByID_HRM(driver, "lastName", empLastName);

		log.info("Add_New_01 - Step 05: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboxValueByID_HRM(driver, "employeeId");

		log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel_HRM(driver, "Create Login Details");

		log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox");
		addEmployeePage.enterToTextboxByID_HRM(driver, "user_name", empUserName);

		log.info("Add_New_01 - Step 08: Enter valid info to 'Password' textbox");
		addEmployeePage.enterToTextboxByID_HRM(driver, "user_password", empPassword);

		log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Password' textbox");
		addEmployeePage.enterToTextboxByID_HRM(driver, "re_password", empPassword);

		log.info("Add_New_01 - Step 10: Select '" + statusValue + "'value in 'Status' dropdown");
		addEmployeePage.selectItemInDropdownByID_HRM(driver, "status", statusValue);

		log.info("Add_New_01 - Step 11: Click to 'Save' button");
		addEmployeePage.clickToButtonByID_HRM(driver, "btnSave");
		myInforPage = PageGenerator.getMyInfoPage(driver);

		log.info("Add_New_01 - Step 12: Open 'Employee List' page");
		myInforPage.openMenuPage_HRM(driver, "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.enterToTextboxByID_HRM(driver, "empsearch_employee_name_empName", empFullName);
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Add_New_01 - Step 14: Click to 'Search' button");
		employeeListPage.clickToButtonByID_HRM(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Add_New_01 - Step 15: Verify Employee Information displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"),
				employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable",
				"First (& Middle) Name", "1"), empFirstName);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"),
				empLastName);
	}

	@Test
	public void Employee_01_Upload_Avatar(Method method) {
		//log.info("Upload_Avatar_02 - Step 01: Login with Employee role");
		ExtentTestManager.startTest(method.getName(), "Employee_01_Add_New_Employee");
		ExtentTestManager.getTest().log(Status.INFO, " Step 01:");
		loginPage = employeeListPage.logOutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, empUserName, empPassword);

		log.info("Upload_Avatar_02 - Step 02: Open Personal detail page");
		dashboardPage.openMenuPage_HRM(driver, "My Info");
		myInforPage = PageGenerator.getMyInfoPage(driver);

		log.info("Upload_Avatar_02 - Step 03: Click to change photo image");
		myInforPage.clickToChangePhotoImage();

		log.info("Upload_Avatar_02 - Step 04: Upload new Avatar image");
		myInforPage.uploadImage_HRM(driver, avatarFilePath);

		log.info("Upload_Avatar_02 - Step 05: Click to Upload button");
		myInforPage.clickToButtonByID_HRM(driver, "btnSave");

		log.info("Upload_Avatar_02 - Step 06: Verify success message is displayed");
		verifyTrue(myInforPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));

		log.info("Upload_Avatar_02 - Step 07: Verify new Avatar image is displayed");
		verifyTrue(myInforPage.isNewAvatarImageDisplayed());
	}

	@Test
	public void Employee_03_Personal_Details() {
		log.info("Personal_Details_03 - Step 01: Open 'Personal Details' tab at Side bar");
		myInforPage.openTabAtSideBarByName("Personal Details");

		log.info("Personal_Details_03 - Step 02:  Verify all fields at 'Personal Details' form are disabled");
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_txtEmpFirstName"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_txtEmpLastName"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_txtEmployeeId"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_txtLicenNo"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_txtNICNo"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_txtSINNo"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_optGender_1"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_optGender_2"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_cmbMarital"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_cmbNation"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_DOB"));

		log.info("Personal_Details_03 - Step 03:  Click to 'Edit' button at'Personal Details' form");
		addEmployeePage.clickToButtonByID_HRM(driver, "btnSave");

		log.info("Personal_Details_03 - Step 04:  Verify 'Employee Id' textbox is disabled");
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_txtEmployeeId"));

		log.info("Personal_Details_03 - Step 05:  Verify 'Driver's License Number' textbox is disabled");
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_txtLicenNo"));

		log.info("Personal_Details_03 - Step 06:  Verify 'SSN Number' textbox is disabled");
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_txtNICNo"));

		log.info("Personal_Details_03 - Step 07:  Verify 'SIN Number' textbox is disabled");
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_txtSINNo"));

		log.info("Personal_Details_03 - Step 08:  Verify 'Date of Birth' textbox is disabled");
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "personal_DOB"));

		log.info("Personal_Details_03 - Step 09:  Enter new value to 'First Name' textbox");
		myInforPage.enterToTextboxByID_HRM(driver, "personal_txtEmpFirstName", editEmpFirstName);
		
		log.info("Personal_Details_03 - Step 10:  Enter new value to 'Last Name' textbox");
		myInforPage.enterToTextboxByID_HRM(driver, "personal_txtEmpLastName", editEmpLastName);
		
		log.info("Personal_Details_03 - Step 11:  Select new value to 'Gender' radio button");
		myInforPage.clickToRadioButtonByLabel(driver, editEmpGender);
		
		log.info("Personal_Details_03 - Step 12:  Select new value to 'Marital Status' dropdown");
		myInforPage.selectItemInDropdownByID_HRM(driver, "personal_cmbMarital", editEmpMaritalStatus);
		
		log.info("Personal_Details_03 - Step 13:  Select new value to 'Nationality' dropdown");
		myInforPage.selectItemInDropdownByID_HRM(driver, "personal_cmbNation", editEmpNationality);
		
		log.info("Personal_Details_03 - Step 14:  Click to 'Save' button at 'Personal Details' form");
		myInforPage.clickToButtonByID_HRM(driver, "btnSave");
		
		log.info("Personal_Details_03 - Step 15:  Verify Success message is displayed");
		verifyTrue(myInforPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Personal_Details_03 - Step 16:  Verify 'First Name' textbox is updated successfully");
		verifyEquals(myInforPage.getTextboxValueByID(driver, "personal_txtEmpFirstName"), editEmpFirstName);
		
		log.info("Personal_Details_03 - Step 17:  Verify 'Last Name' textbox is updated successfully");
		verifyEquals(myInforPage.getTextboxValueByID(driver, "personal_txtEmpLastName"), editEmpLastName);
		
		log.info("Personal_Details_03 - Step 18:  Verify 'Gender' radio button is updated successfully");
		verifyTrue(myInforPage.isRadioButtonSelectedByLabel(driver, editEmpGender));
		
		log.info("Personal_Details_03 - Step 19:  Verify 'Marital Status' dropdown is updated successfully");
		verifyEquals(myInforPage.getSelectedValueInDropdownByID_HRM(driver, "personal_cmbMarital"), editEmpMaritalStatus);
		
		log.info("Personal_Details_03 - Step 20:  Verify 'Nationality' dropdown is updated successfully");
		verifyEquals(myInforPage.getSelectedValueInDropdownByID_HRM(driver, "personal_cmbNation"), editEmpNationality);
		
		log.info("Personal_Details_03 - Step 21:  Verify 'Employee ID' textbox value is correct");
		verifyEquals(myInforPage.getTextboxValueByID(driver, "personal_txtEmployeeId"), employeeID);
		
	}

	@Test
	public void Employee_04_Contact_Details() {
		log.info("Contact_Details_04 - Step 01: Open 'Contact Details' tab at Side bar");
		myInforPage.openTabAtSideBarByName("Contact Details");

		log.info("Contact_Details_04  - Step 02:  Verify all fields at 'Contact Details' form are disabled");
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "contact_street1"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "contact_street2"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "contact_city"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "contact_province"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "contact_emp_zipcode"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "contact_country"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "contact_emp_hm_telephone"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "contact_emp_mobile"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "contact_emp_work_telephone"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "contact_emp_work_email"));
		verifyFalse(myInforPage.isFieldEnabledByName(driver, "contact_emp_oth_email"));

		log.info("Contact_Details_04 - Step 03:  Click to 'Edit' button Contact Details' form");
		addEmployeePage.clickToButtonByID_HRM(driver, "btnSave");
		
		log.info("Contact_Details_04 - Step 04: Enter new value to 'Address Street 1' textbox");
		
		
		//add rồi verify thông tin của Dependents
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Date of Birth", "1"), employeeID);
		
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "2"), employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "2"), employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Date of Birth", "2"), employeeID);
	}

	@Test
	public void Employee_05_Emergency_Details() {

	}

	@Test
	public void Employee_06_Assigned_Dependents() {

	}

	@Test
	public void Employee_07_Edit_View_Job() {

	}

	@Test
	public void Employee_08_Edit_View_Salary() {

	}

	@Test
	public void Employee_09_Edit_View_Tax() {

	}

	@Test
	public void Employee_10_Qualifications() {

	}

	@Test
	public void Employee_11_Search_Employee() {

	}

//	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	public int generateFakeNumber() {
		Random rand = new Random();

		return rand.nextInt(9999);
	}

	WebDriver driver;
	LoginPO loginPage;
	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage;
	EmployeeListPO employeeListPage;
	MyInfoPO myInforPage;
}
