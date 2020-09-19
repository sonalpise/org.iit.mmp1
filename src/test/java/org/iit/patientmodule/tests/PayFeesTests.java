package org.iit.patientmodule.tests;
import org.testng.annotations.Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.iit.mmp.helper.HelperClass;
import org.iit.patientmodule.page.PayFeesPage;
import org.iit.util.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class PayFeesTests extends PayFeesPage{
	
	
	/**
	 * Static declarations
	 **/
	private static String appURL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
	//http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php
	private static String username = "ria1";
	private static String password = "Ria12345";
	By editProfileLink = By.xpath("//a[@href='profile.php']");
	By feesLink = By.xpath("//span[contains(text(),'Fees')]");
	HelperClass helperObj;
	
	@Test(priority = 1)
	public void loginMMP() {
		//***********2.Enter the URL and Login into MMP Website***********/
		helperObj.loginPatientPortal(username, password);
	} // loginMMP

	@Test(priority = 2)
	public void navigateToFeesModule() {
		Logger.log("I", "Attempting to navigate to Fees");
		helperObj.navigateToAModule(feesLink);
	}//navigateToFeesModule
	

	
	@Test(priority = 3)
	public void varifyFeesAmountPresent() {
		payFees();
	}//varifySymptomsPresent
	

	@Test(priority = 4)
	public void testAssertFunctions() {
		Assert.assertEquals(displayedFees,defaultItem);
	}//testAssertFunctions
	
	
	
	@Test(priority = 5)
	public void logout() {
		helperObj.logoutPatientPortal();
		helperObj._closeAppURL();
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
	@AfterClass
	public void afterTest() {
		shutDownDriver();
	} // afterTest
	
	
	
	
} //PayFeesTests
