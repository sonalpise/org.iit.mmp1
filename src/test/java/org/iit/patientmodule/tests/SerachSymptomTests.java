package org.iit.patientmodule.tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.iit.mmp.helper.HelperClass;
import org.iit.patientmodule.page.SearchSymptomPage;
import org.iit.util.Logger;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SerachSymptomTests  extends SearchSymptomPage{

	
	/**
	 **/
	private static String appURL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
	//http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php
	private static String username = "ria1";
	private static String password = "Ria12345";
	//private static String testDataPath = "C:\\Users\\pisef\\eclipse-workspace\\org.iit.mmp\\";
	private static String testDataPath = "C:\\Users\\pisef\\eclipse-workspace\\org.iit.mmp1\\";
	private static String testDataFilename = "datashare.xlsx";
	private static String dataSheetname = "Data";
	By linkSendingMessage  = By.xpath("//a[contains(@href,'sendmessage')]");
	HelperClass helperObj;
	
	
		@Test(priority = 1)
		public void loginMMP() {
			//***********2.Enter the URL and Login into MMP Website***********/
			helperObj.loginPatientPortal(username, password);
		} // loginMMP
	 
	 
	 @Test(priority = 2)
		public void searchSymptoms() {
			searchSymptoms("fever");
		} // searchSymptoms
		
	 @Test(priority = 3)
		public void searchSymptoms2() {
			searchSymptoms("cough");
		} // searchSymptoms
		

	 
		@Test(priority = 4)
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
		
		
		
} // SerachSymptomTests
