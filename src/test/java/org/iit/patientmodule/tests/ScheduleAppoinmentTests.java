package org.iit.patientmodule.tests;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.HashMap;
import org.iit.mmp.helper.HelperClass;
import org.iit.patientmodule.page.ScheduleAppoinmentPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class ScheduleAppoinmentTests extends ScheduleAppoinmentPage {
	/**
	 * Static declarations
	 **/
	private static String appURL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
	//http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php
	private static String username = "ria1";
	private static String password = "Ria12345";
	private static String testDataPath = "C:\\Users\\pisef\\eclipse-workspace\\org.iit.mmp\\";
	private static String testDataFilename = "datashare.xlsx";
	private static String dataSheetname = "Data";
	HelperClass helperObj;

	/* ```````````````````````````````````````````````````````````````` */
	@Test(priority = 1)
	public void loginMMP() {
		helperObj.loginPatientPortal(username, password);
	} // loginMMP
	

	@Test(priority = 2)
	public void validateScheduleAppointment() throws InterruptedException
	{
		 helperObj.navigateToAModule(By.xpath("//span[contains(text(),'Schedule Appointment')]"));
		 HashMap<String,String> hMap = bookAppointment("Smith");
		 helperObj.navigateToAModule(By.xpath("//span[contains(text(),'HOME')]"));
		 boolean result=validateAppointmentDetailsinHomePage(hMap);
		 Assert.assertTrue(result);
		 helperObj.navigateToAModule(By.xpath("//span[contains(text(),'Schedule Appointment')]"));
		 boolean result1 = validateAppointmentDetailsinSPage(hMap);		
		 Assert.assertTrue(result1);
	} // validateScheduleAppointment

	@Test(priority = 3)
	public void logout() {
		helperObj.logoutPatientPortal();
		helperObj._closeAppURL();
	} // logout
	/**
	 **/
	
	@BeforeClass
	public void beforeTest() {
		initiateDriver();
		 helperObj = new HelperClass(driver, appURL);
	} // beforeTest

	/**
	 **/
	@AfterClass
	public void afterTest() {
		shutDownDriver();
	} // afterTest
	
}  //ScheduleAppoinmentTests
