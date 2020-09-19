package org.iit.patientmodule.tests;

import java.util.HashMap;

import org.iit.mmp.helper.HelperClass;
import org.iit.patientmodule.page.SendingMessageDocPage;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SendingMessageDocTests extends SendingMessageDocPage{
	/**
	 **/
	private static String appURL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
	private static String adminURL = "http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php";
	//http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php
	private static String username = "ria1";
	private static String password = "Ria12345";
	//private static String testDataPath = "C:\\Users\\pisef\\eclipse-workspace\\org.iit.mmp\\";
	private static String testDataPath = "C:\\Users\\pisef\\eclipse-workspace\\org.iit.mmp1\\";
	private static String testDataFilename = "datashare.xlsx";
	private static String dataSheetname = "Data";
	By linkSendingMessage  = By.xpath("//a[contains(@href,'sendmessage')]");
	By editProfileLink = By.xpath("//a[@href='profile.php']");
	By linkSendingMessageAdminM  = By.xpath("//a[contains(@href,'message')]");
	HelperClass helperObj;
	HashMap <String, String> hMap;
	private static String adminusername = "Thomas_444";
	private static String adminpassword = "Edison_444";

	
	@Test(priority = 1)
	public void loginMMP() {
		//***********2.Enter the URL and Login into MMP Website***********/
		helperObj.loginPatientPortal(username, password);
	} // loginMMP
 
 
 @Test(priority = 2)
	public void sendMessageCall() {
	 helperObj.navigateToAModule(editProfileLink);
	patientFirstName = driver.findElement(By.id("fname")).getAttribute("value");
	 helperObj.navigateToAModule(linkSendingMessage);
	 sendingMessage();
	 openDdminUrl();
	 logintoADMIN();
	 retrieveRecentMessageDetails();
	 Assert.assertEquals(validateMessageFromAdminModule(), true);
	 closeDdminUrl();
	 
	} // sendMessageCall
	public void logintoADMIN() {
		helperObj.loginAdminPortal(adminusername, adminpassword);
		helperObj.navigateToAModule(linkSendingMessageAdminM);
		retrieveRecentMessageDetails();
	} //logintoADMIN
	
	@Test(priority = 3)
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

	public void openDdminUrl() {
		// 1.Driver Initiated and Open the Chrome Browser.
		initiateDriver();
		 helperObj = new HelperClass(driver, adminURL);
	} // openDdminUrl
	/**
	 * 
	 * 
	 **/
	
	public void closeDdminUrl() {
		shutDownDriver();
	} // closeDdminUrl
	@AfterClass
	public void afterTest() {
		shutDownDriver();
	} // afterTest
	
	
	
	
} //SendingMessageDocTests
