package org.iit.patientmodule.tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.iit.mmp.helper.HelperClass;
import org.iit.patientmodule.page.LoginAccountPage;
import org.iit.util.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginAccountTests extends LoginAccountPage {
	/**
	 * Static declarations
	 **/
	private static String appURL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
	private static String username = "ria1";
	private static String password = "Ria12345";
	public static String testDataPath = System.getProperty("user.dir") + "\\src\\test\\resources\\";
	private static String testDataFilename = "logindata.xlsx";
	private static String dataSheetname = "Data";
	By editProfileLink = By.xpath("//a[@href='profile.php']");
	HelperClass helperObj;

	
	@Test(priority = 1, dataProvider = "authentication", dataProviderClass=LoginAccountPage.class)
	public void loginwithData() throws EncryptedDocumentException, IOException {
		try {
			// ***********3.Navigate to PROFILE page*********************/
		//	getDataFromDataprovider(testDataPath, testDataFilename, dataSheetname);
			helperObj.loginPatientPortal(username, password);
			// ***********9.Navigate to PROFILE page*********************/	
		} catch (AssertionError e) {
			Logger.log("I", "Test Case editProfileAndVerify - Failed");
		}
	} // loginwithData
		
	@Test(priority = 2)
	public void logout() {
		helperObj.logoutPatientPortal();
//		helperObj._closeAppURL();
	} // logout

	/**
	 **/

	@BeforeClass
	public void beforeTest() {
		// 1.Driver Initiated and Open the Chrome Browser.
		initiateDriver();
		helperObj = new HelperClass(driver, appURL);
	} // beforeTest

	/**
	 **/
//	@AfterClass
//	public void afterTest() {
//		shutDownDriver();
//	} // afterTest

} // LoginAccountTests
