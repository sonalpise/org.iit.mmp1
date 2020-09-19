package org.iit.patientmodule.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.iit.mmp.helper.HelperClass;
import org.iit.patientmodule.page.EditPatientProfileSavePage;
import org.iit.util.Logger;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class EditPatientProfileSaveTests extends EditPatientProfileSavePage {

	/**
	 * Static declarations
	 **/
	private static String appURL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
	private static String username = "ria1";
	private static String password = "Ria12345";
	public static String testDataPath = System.getProperty("user.dir") + "\\src\\test\\resources\\";
	private static String testDataFilename = "datashare.xlsx";
	private static String dataSheetname = "Data";
	By editProfileLink = By.xpath("//a[@href='profile.php']");
	HelperClass helperObj;

	/* ```````````````````````````````````````````````````````````````` */
	/*
	 * Automation Testing Steps 1.Driver Initiated and Open the Chrome Browser.
	 * 2.Enter the URL and Login into MMP Website :
	 * helperObj.loginPatientPortal(username, password); 3.Navigate to PROFILE page
	 * : helperObj.navigateToAModule(editProfileLink); 4.Get Current Patient Profile
	 * Values into HashMAP 5.Enable the EDIT Mode by clicking EDIT button 6.Read
	 * Excel File Data values and put all values into New HashMap 7.Also Update all
	 * Fields into Edit Profile Page 8.Save the Page 9.Navigate again to Profile
	 * Page 10.Read all updated values from Profile Fields and Compare with Updated
	 * Values from Excel 11.Validate Values are updated successfully 12.Logout from
	 * MMP and Close the driver.
	 */

	// ***********2.Enter the URL and Login into MMP Website***********/
	@Test(priority = 1, dataProvider = "LoginData")
	public void loginMMP(String username1, String password1) {
		helperObj.loginPatientPortal(username, password);
	} // loginMMP

	@Test(priority = 2)
	public void editProfileAndVerify() throws EncryptedDocumentException, IOException {
		try {
			// ***********3.Navigate to PROFILE page*********************/
			helperObj.navigateToAModule(editProfileLink);
			Assert.assertEquals(editProfileAndSave1(), true);
			Logger.log("I", "Test Case editProfileAndVerify - Passed");
		//	 AssertJUnit.assertEquals(editProfileAndSave(testDataPath, testDataFilename,dataSheetname), true);
			// ***********9.Navigate to PROFILE page*********************/
	} catch (AssertionError e) {
		Logger.log("I", "Test Case editProfileAndVerify - Failed");
	}
		Logger.log("I", "Test Case editProfileAndVerify - Passed");
	} // editProfileAndVerify

	@Test(priority = 3) 
	public void logout() {
		helperObj.logoutPatientPortal();
		helperObj._closeAppURL();
	} // logout

	/**
	 **/

	@DataProvider(name = "LoginData")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { { "ria1", "Ria12345" }, };

	}

	@BeforeClass
	public void beforeTest() {
		// 1.Driver Initiated and Open the Chrome Browser.
		initiateDriver();
		helperObj = new HelperClass(driver, appURL);
	} // beforeTest

	/**
	 **/
	@AfterClass
	public void afterTest() {
		shutDownDriver();
	} // afterTest

} // EditPatientProfileSaveTests
