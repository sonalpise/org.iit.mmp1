package org.iit.adminmodule.tests;
import java.util.HashMap;
import org.iit.adminmodule.page.MessageSendingDocPages;
import org.iit.mmp.helper.HelperClass;
import org.iit.util.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MessageSendingDocTests extends MessageSendingDocPages {

		/**
		 * Static declarations
		 **/
		private static String appURL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
		private static String adminURL = "http://96.84.175.78/MMP-Release2-Admin-Build.2.1.000/login.php";
		private static String username = "ria1";
		private static String password = "Ria12345";
		private static String adminUsername = "Thomas_444";
		private static String adminPassword = "Edison_444";

		private static String subject = "Hello Dr. Becky";
		private static String message = "Hello Doc, Please Refill my blood sugar prescriptions";

		String linkSendingMessage = "Messages";
		String linkEditProfile = "Profile";
		String linkLogout = "Logout";

		By linkSendingMessageAdminM = By.xpath("//a[contains(@href,'message')]");

		HelperClass  patientDriver;
		HelperClass adminDriver;
	
		HashMap<String, String> messageData = new HashMap<String, String>();
		
		@Test(priority = 1)
		public void sendMessageAsPatient() {
			initiateDriver();
			patientDriver = new HelperClass(driver, appURL);
			patientDriver.loginPatientPortal(username, password);
			Logger.log("I", "Logged in as Patient");
			// -- get patient profile name for later use
			patientDriver.moduleNavigation(linkEditProfile);
			String patientFirstName = driver.findElement(By.id("fname")).getAttribute("value");
			// -- send message
			patientDriver.moduleNavigation(linkSendingMessage);
			messageData = sendMessage(patientFirstName, subject.trim(), message.trim());
			Logger.log("I", "Done! Message sent as Patient");
		} // sendMessageCall
		
		@Test(priority = 2)
		public void logoutPatient() {
			patientDriver.moduleNavigation(linkLogout);
			Logger.log("I", "Logged out as Patient");
		} // logout

		
		@Test(priority = 3)
		public void validateMessageAsAdmin() {
			adminDriver = new HelperClass(driver, adminURL);
			adminDriver.loginAdminPortal(adminUsername, adminPassword);
			Logger.log("I", "Logged in as Admin");
			adminDriver.moduleNavigation(linkSendingMessage);
			// -- retrieve message and validate
			Assert.assertEquals(validateMessage(messageData), true);
		} // validateMessageAsAdmin

		@Test(priority = 4)
		public void closeURL() {
			adminDriver.moduleNavigation(linkLogout);
			adminDriver._closeAppURL();
			Logger.log("I", "Logged out as ADMIN");
		} // logout
}//MessageSendingDocTests
