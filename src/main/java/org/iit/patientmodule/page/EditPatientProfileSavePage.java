package org.iit.patientmodule.page;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.iit.mmp.helpers.TestDataManager;
import org.iit.util.BaseClass;
import org.iit.util.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;

public class EditPatientProfileSavePage  extends BaseClass{

	/* By Variable Locators Declaration*/
	By loginMainLink = By.linkText("Login");
	By loginUsername = By.id("username");
	By loginPassword = By.id("password");
	By loginClick = By.xpath("//input[@name='submit']");
	By logoutLink = By.xpath("/html/body/div[1]/div[1]/div[1]/div/ul/li[9]/a/span");
	By editProfileLink = By.xpath("//a[@href='profile.php']");
	
	By fname = By.id("fname");
	By lname = By.id("lname");
	By licn = By.id("licn");
	By ssn = By.id("ssn");
	By addr = By.id("addr");
	By age = By.id("age");
	By weight = By.id("weight");
	By height = By.id("height");
	By city = By.id("city");
	By state = By.id("state");
	By zip = By.id("zip");
	By proinfo = By.id("proinfo");
	By Insinfo = By.id("Insinfo");
	
	By Ebtn = By.id("Ebtn");
	By Sbtn = By.id("Sbtn");
	

	/**
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 **/
	public boolean editProfileAndSave(String testDataPath, String testDataFilename, String dataSheetname)
			throws EncryptedDocumentException, IOException {
		 
	Logger.log("I", "Attempting to edit Patient profile and save");
//		driver.findElement(editProfileLink).click();

		// -- read test data from file
		HashMap<String, List<String>> testData = TestDataManager
				.readDataFromExcel(new File(testDataPath + testDataFilename), dataSheetname);

		// -- updating new values
		int numRecords = testData.get("SSN").size();
		Logger.log("I", "Dataset contain " + numRecords + " records");

		Boolean allRecordsUpdated = true;
		/********************* Start For Loop for Records from Excel File HASHMAP *********************************/
		for (int i = 0; i < numRecords; i++) {
			Logger.log("I", " ... working on record # " + i);
			/*************** * 4.Get Current Patient Profile Values into HashMAP***************/		
			// -- save current state of data values
			HashMap<String, String> currentData = new HashMap<String, String>();
			currentData.put("fname", driver.findElement(fname).getAttribute("value"));
			currentData.put("lname", driver.findElement(lname).getAttribute("value"));
			currentData.put("licn", driver.findElement(licn).getAttribute("value"));
			currentData.put("ssn", driver.findElement(ssn).getAttribute("value"));
			currentData.put("addr", driver.findElement(addr).getAttribute("value"));
			currentData.put("age", driver.findElement(age).getAttribute("value"));
			currentData.put("weight", driver.findElement(weight).getAttribute("value"));
			currentData.put("height", driver.findElement(height).getAttribute("value"));
			currentData.put("city", driver.findElement(city).getAttribute("value"));
			currentData.put("state", driver.findElement(state).getAttribute("value"));
			currentData.put("zip", driver.findElement(zip).getAttribute("value"));
			currentData.put("proinfo", driver.findElement(proinfo).getAttribute("value"));
			currentData.put("Insinfo", driver.findElement(Insinfo).getAttribute("value"));
			 /****** 5.Enable the EDIT Mode by clicking EDIT button*********/		 		
			// -- enable edit mode
			driver.findElement(Ebtn).click();

			/**********	 * 6.Read Excel File Data values and put all values into New HashMap******************/
			 /********* 7.Also Update all Fields into Edit Profile Page ****************/
		
			// -- change values from test data
			driver.findElement(fname).clear();
			driver.findElement(fname).sendKeys(testData.get("Firstname").toArray()[i].toString());
			driver.findElement(lname).clear();
			driver.findElement(lname).sendKeys(testData.get("Lastname").toArray()[i].toString());
			driver.findElement(licn).clear();
			driver.findElement(licn).sendKeys(testData.get("License").toArray()[i].toString());
			driver.findElement(ssn).clear();
			driver.findElement(ssn).sendKeys(testData.get("SSN").toArray()[i].toString());
			driver.findElement(addr).clear();
			driver.findElement(addr).sendKeys(testData.get("Address").toArray()[i].toString());
			driver.findElement(age).clear();
			driver.findElement(age).sendKeys(testData.get("Age").toArray()[i].toString());
			driver.findElement(weight).clear();
			driver.findElement(weight).sendKeys(testData.get("Weight").toArray()[i].toString());
			driver.findElement(height).clear();
			driver.findElement(height).sendKeys(testData.get("Height").toArray()[i].toString());
			driver.findElement(city).clear();
			driver.findElement(city).sendKeys(testData.get("City").toArray()[i].toString());
			driver.findElement(state).clear();
			driver.findElement(state).sendKeys(testData.get("State").toArray()[i].toString());
			driver.findElement(zip).clear();
			driver.findElement(zip).sendKeys(testData.get("Zipcode").toArray()[i].toString());
			driver.findElement(proinfo).clear();
			driver.findElement(proinfo).sendKeys(testData.get("Proinfo").toArray()[i].toString());
			driver.findElement(Insinfo).clear();
			driver.findElement(Insinfo).sendKeys(testData.get("Issuranceinfo").toArray()[i].toString());

			//7.Also Update all Fields into Edit Profile Page 		
			 //8.Save the Page 
			driver.findElement(Sbtn).click();	
			/*Moving towards Update Alert */
			try {
				// -- validate alert message
				Alert alert = driver.switchTo().alert();
				if (alert.getText().contains("has been updated")) {
					alert.accept();
					// -- validate data on form with previous values
					
					 //10.Read all updated values from Profile Fields and Compare with Updated Values from which updated from Data values
					 // 11.Validate Values are updated successfully				 
					 
					Boolean valuesUpdated = false;
					for (String key : currentData.keySet()) {
						if (!currentData.get(key).equals(driver.findElement(By.id(key)).getAttribute("value"))) {
							valuesUpdated = true;
						}
					} // loop over all current data elements
					if (!valuesUpdated) {
						allRecordsUpdated = false;
						Logger.log("W", "Record # " + i + " was not updated.");
					}
				} else {
					Logger.log("W", "Alert message box says: " + alert.getText());
					return false;
				}
			} catch (NoAlertPresentException e) {
				Logger.log("W", "Form has errors, cannot save");
				return false;
			}
		} // loop all records
		return allRecordsUpdated;
	} // editProfileAndSave

	
} //EditPatientProfileSavePage
