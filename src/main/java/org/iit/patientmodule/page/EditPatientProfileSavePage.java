package org.iit.patientmodule.page;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.iit.mmp.helpers.TestDataManager;
import org.iit.util.BaseClass;
import org.iit.util.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class EditPatientProfileSavePage extends BaseClass {

	/* By Variable Locators Declaration */
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

		Logger.log("I", "Attempting to Edit Patient profile and Save");
//		driver.findElement(editProfileLink).click();

		// -- read test data from file
		HashMap<String, List<String>> testData = TestDataManager
				.readDataFromExcel(new File(testDataPath + testDataFilename), dataSheetname);

		// -- updating new values
		int numRecords = testData.get("SSN").size();
		Logger.log("I", "Dataset contain " + numRecords + " records");

		Boolean allRecordsUpdated = true;
		/*********************
		 * Start For Loop for Records from Excel File HASHMAP
		 *********************************/
		for (int i = 0; i < numRecords; i++) {
			Logger.log("I", " ... Updating  record # " + i);
			/***************
			 * * 4.Get Current Patient Profile Values into HashMAP
			 ***************/
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
			/****** 5.Enable the EDIT Mode by clicking EDIT button *********/
			// -- enable edit mode
			driver.findElement(Ebtn).click();

			/**********
			 * * 6.Read Excel File Data values and put all values into New HashMap
			 ******************/
			/********* 7.Also Update all Fields into Edit Profile Page ****************/

			// -- change values from test data
			WebElement fWname = driver.findElement(fname);
			fWname.clear();
			fWname.sendKeys(testData.get("Firstname").toArray()[i].toString());

			// driver.findElement(fname).clear();
			// driver.findElement(fname).sendKeys(testData.get("Firstname").toArray()[i].toString());
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

			// 7.Also Update all Fields into Edit Profile Page
			// 8.Save the Page
			driver.findElement(Sbtn).click();
			/* Moving towards Update Alert */
			try {
				// -- validate alert message
				Alert alert = driver.switchTo().alert();
				if (alert.getText().contains("has been updated")) {
					alert.accept();
					// 10.Read all updated values from Profile Fields and Compare with Updated
					// Values from which updated from Data values
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
		if (allRecordsUpdated)
			Logger.log("I", "All records were updated and verified.");
		return allRecordsUpdated;
	} // editProfileAndSave

	/*Login for Single Entry Update*/
	public boolean editProfileAndSave1() {

		Logger.log("I", "Attempting to Edit Patient profile and Save");
//		driver.findElement(editProfileLink).click();

		// -- save current state of data values

		HashMap<String, String> currentHMAP = new HashMap<String, String>();
		currentHMAP.put("fname", driver.findElement(fname).getAttribute("value"));
		currentHMAP.put("lname", driver.findElement(lname).getAttribute("value"));
		currentHMAP.put("licn", driver.findElement(licn).getAttribute("value"));
		currentHMAP.put("ssn", driver.findElement(ssn).getAttribute("value"));
		currentHMAP.put("addr", driver.findElement(addr).getAttribute("value"));
		currentHMAP.put("age", driver.findElement(age).getAttribute("value"));
		currentHMAP.put("weight", driver.findElement(weight).getAttribute("value"));
		currentHMAP.put("height", driver.findElement(height).getAttribute("value"));
		currentHMAP.put("city", driver.findElement(city).getAttribute("value"));
		currentHMAP.put("state", driver.findElement(state).getAttribute("value"));
		currentHMAP.put("zip", driver.findElement(zip).getAttribute("value"));
		currentHMAP.put("proinfo", driver.findElement(proinfo).getAttribute("value"));
		currentHMAP.put("Insinfo", driver.findElement(Insinfo).getAttribute("value"));
		/****** 5.Enable the EDIT Mode by clicking EDIT button *********/
		// -- enable edit mode
		driver.findElement(Ebtn).click();

		// Edit HaspMap values with Expected Values

		HashMap<String, String> expHMAP = new HashMap<String, String>();
		expHMAP.put("fname", currentHMAP.get("fname").concat("New"));
		expHMAP.put("lname", currentHMAP.get("lname").concat("New"));
		expHMAP.put("licn", currentHMAP.get("licn"));
		expHMAP.put("ssn", currentHMAP.get("ssn"));
		expHMAP.put("addr", currentHMAP.get("addr"));
		expHMAP.put("age", currentHMAP.get("age"));
		expHMAP.put("weight", currentHMAP.get("weight"));
		expHMAP.put("height", currentHMAP.get("height"));
		expHMAP.put("city", currentHMAP.get("city").concat("New"));
		expHMAP.put("state", currentHMAP.get("state"));
		expHMAP.put("zip", currentHMAP.get("zip"));
		expHMAP.put("proinfo", currentHMAP.get("proinfo").concat("New"));
		expHMAP.put("Insinfo", currentHMAP.get("Insinfo").concat("New"));

//  Put  Updated HaspMAP to EDIT FORM Fields

		driver.findElement(fname).clear();
		driver.findElement(fname).sendKeys(expHMAP.get("fname").toString());
		driver.findElement(lname).clear();
		driver.findElement(lname).sendKeys(expHMAP.get("lname").toString());
		driver.findElement(licn).clear();
		driver.findElement(licn).sendKeys(expHMAP.get("licn").toString());
		driver.findElement(ssn).clear();
		driver.findElement(ssn).sendKeys(expHMAP.get("ssn").toString());
		driver.findElement(addr).clear();
		driver.findElement(addr).sendKeys(expHMAP.get("addr").toString());
		driver.findElement(age).clear();
		driver.findElement(age).sendKeys(expHMAP.get("age").toString());
		driver.findElement(weight).clear();
		driver.findElement(weight).sendKeys(expHMAP.get("weight").toString());
		driver.findElement(height).clear();
		driver.findElement(height).sendKeys(expHMAP.get("height").toString());
		driver.findElement(city).clear();
		driver.findElement(city).sendKeys(expHMAP.get("city").toString());
		driver.findElement(state).clear();
		driver.findElement(state).sendKeys(expHMAP.get("state").toString());
		driver.findElement(zip).clear();
		driver.findElement(zip).sendKeys(expHMAP.get("zip").toString());
		driver.findElement(proinfo).clear();
		driver.findElement(proinfo).sendKeys(expHMAP.get("proinfo").toString());
		driver.findElement(Insinfo).clear();
		driver.findElement(Insinfo).sendKeys(expHMAP.get("Insinfo").toString());

		driver.findElement(Sbtn).click();

		Alert alert = driver.switchTo().alert();
		if (alert.getText().contains("has been updated"))
			alert.accept();

		// Go to Edit Profile Page and Compare 2 Hashmaps

		// -- save current state of data values
		HashMap<String, String> actHMAP = new HashMap<String, String>();
		actHMAP.put("fname", driver.findElement(fname).getAttribute("value"));
		actHMAP.put("lname", driver.findElement(lname).getAttribute("value"));
		actHMAP.put("licn", driver.findElement(licn).getAttribute("value"));
		actHMAP.put("ssn", driver.findElement(ssn).getAttribute("value"));
		actHMAP.put("addr", driver.findElement(addr).getAttribute("value"));
		actHMAP.put("age", driver.findElement(age).getAttribute("value"));
		actHMAP.put("weight", driver.findElement(weight).getAttribute("value"));
		actHMAP.put("height", driver.findElement(height).getAttribute("value"));
		actHMAP.put("city", driver.findElement(city).getAttribute("value"));
		actHMAP.put("state", driver.findElement(state).getAttribute("value"));
		actHMAP.put("zip", driver.findElement(zip).getAttribute("value"));
		actHMAP.put("proinfo", driver.findElement(proinfo).getAttribute("value"));
		actHMAP.put("Insinfo", driver.findElement(Insinfo).getAttribute("value"));

		// Compare Expected and Actual HashMAPS
		Boolean allCheck = true;
		try {
			Assert.assertEquals(expHMAP.get("fname"), actHMAP.get("fname"));
		} catch (AssertionError e) {
			Logger.log("I", "Firstname is not matching");
			allCheck = false;
		}
		try {
			Assert.assertEquals(expHMAP.get("lname"), actHMAP.get("lname"));
		} catch (AssertionError e) {
			Logger.log("I", "Lastname is not matching");
			allCheck = false;
		}
		try {
			Assert.assertEquals(expHMAP.get("licn"), actHMAP.get("licn"));
		} catch (AssertionError e) {
			Logger.log("I", "Licence is not matching");
			allCheck = false;
		}
		try {
			Assert.assertEquals(expHMAP.get("ssn"), actHMAP.get("ssn"));
		} catch (AssertionError e) {
			Logger.log("I", "SSN  is not matching");
			allCheck = false;
		}
		try {
			Assert.assertEquals(expHMAP.get("addr"), actHMAP.get("addr"));
		} catch (AssertionError e) {
			Logger.log("I", "Address is not matching");
			allCheck = false;
		}
		try {
			Assert.assertEquals(expHMAP.get("age"), actHMAP.get("age"));
		} catch (AssertionError e) {
			Logger.log("I", "Age is not matching");
			allCheck = false;
		}
		try {
			Assert.assertEquals(expHMAP.get("weight"), actHMAP.get("weight"));
		} catch (AssertionError e) {
			Logger.log("I", "Weight is not matching");
			allCheck = false;
		}
		try {
			Assert.assertEquals(expHMAP.get("height"), actHMAP.get("height"));
		} catch (AssertionError e) {
			Logger.log("I", "Height is not matching");
			allCheck = false;
		}
		try {
			Assert.assertEquals(expHMAP.get("city"), actHMAP.get("city"));
		} catch (AssertionError e) {
			Logger.log("I", "City is not matching");
			allCheck = false;
		}
		try {
			Assert.assertEquals(expHMAP.get("state"), actHMAP.get("state"));
		} catch (AssertionError e) {
			Logger.log("I", "State is not matching");
			allCheck = false;
		}
		return allCheck;
	} // editProfileAndSave1

} // EditPatientProfileSavePage
