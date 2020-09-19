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
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class EditProfileProperyPage extends BaseClass {
	
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
	
	public void editProfileAndSave() {

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
			expHMAP.put("fname",currentHMAP.get("fname").concat("New"));
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
			expHMAP.put("Insinfo", currentHMAP.get("insinfo").concat("New"));
		
	

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
			driver.findElement(Insinfo).sendKeys(expHMAP.get("insinfo").toString());
			
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
						
						if(expHMAP.get("fname").equals(actHMAP.get("fname"))) {
							System.out.println("Firstname Matched");
						}
						else
						{
							System.out.println("Firstname MisMatched");
						}

	} // editProfileAndSave

	
	
}  //EditProfileProperyPage
