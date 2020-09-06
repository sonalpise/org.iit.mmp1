package org.iit.patientmodule.page;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.iit.mmp.helpers.TestDataManager;
import org.iit.util.BaseClass;
import org.iit.util.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

public class EditProfileSingleEntryPage  extends BaseClass {

/* By Variable Locators Declaration*/
	
	By loginMainLink = By.linkText("Login");
	By loginUsername = By.id("username");
	By loginPassword = By.id("password");
	By loginClick = By.xpath("//input[@name='submit']");
	By logoutLink = By.xpath("/html/body/div[1]/div[1]/div[1]/div/ul/li[9]/a/span");
	By editProfileLink = By.xpath("//a[@href='profile.php']");
	
	By saveButton = By.id("Sbtn");
	Fairy fairy = Fairy.create();
	Person person = fairy.person();
  
	
	Map<String, String> objMap = new HashMap<String, String>(); // Current data HaspMap
	Map<String, String> objMapnew = new HashMap<String, String>(); // New
															

	public void GetCurrentPatientData() throws Exception {
		
		driver.findElement(By.id("Ebtn")).click();
		// Get all Current Patient Profile Data
		objMap.put("Firstname", driver.findElement(By.id("fname")).getAttribute("value"));
		objMap.put("LastName", driver.findElement(By.id("lname")).getAttribute("value"));
		objMap.put("License", driver.findElement(By.id("licn")).getAttribute("value"));
		objMap.put("SSN", driver.findElement(By.id("ssn")).getAttribute("value"));
		objMap.put("Address", driver.findElement(By.id("addr")).getAttribute("value"));
		objMap.put("Age", driver.findElement(By.id("age")).getAttribute("value"));

		objMap.put("Weight", driver.findElement(By.id("weight")).getAttribute("value"));
		objMap.put("Height", driver.findElement(By.id("height")).getAttribute("value"));
		objMap.put("City", driver.findElement(By.id("city")).getAttribute("value"));
		objMap.put("State", driver.findElement(By.id("state")).getAttribute("value"));
		objMap.put("Zipcode", driver.findElement(By.id("zip")).getAttribute("value"));
		objMap.put("ProInfo", driver.findElement(By.id("proinfo")).getAttribute("value"));
		objMap.put("InsuranceInfo", driver.findElement(By.id("Insinfo")).getAttribute("value"));

		System.out.println("Elements of the OLD  Map:");
		System.out.println(objMap);
	} // Close GetCurrentPatientData
	
	public void editPatientProfile() throws Exception {
		
	  	initiateDriver();
		driver.manage().window().maximize();
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/");

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		driver.findElement(By.xpath("//a[@href='profile.php']")).click(); // Click		
		Logger.log("I", "Attempting to edit Patient profile and save");
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement continueWE = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(editProfileLink)));
		driver.findElement(editProfileLink).click();
		
		GetCurrentPatientData();
		editRandomfirstname();
		editlastname();
		editLicense();
		editSSN();
		editAge();
		editAddress();
		editWeight();
		editHeight();
		editCity();
		editState();
		editZipcode();
		editProinfo();
		editInsurance();
		verifyAlerMessage();
		checkUpdatedValues();
		signout();
	} // editPatientProfile

	public void editRandomfirstname() {
		driver.findElement(By.id("fname")).clear();
		String fnamevalue = person.getFirstName();
		driver.findElement(By.id("fname")).sendKeys(fnamevalue);
		objMapnew.put("Firstname", fnamevalue);
	} //editRandomfirstname

	public void editlastname() {
		driver.findElement(By.id("lname")).clear();
		String lnamevalue = person.getLastName();
		driver.findElement(By.id("lname")).sendKeys(lnamevalue);
		objMapnew.put("Lastname", lnamevalue);
	}//editlastname

	public void editLicense() {
		driver.findElement(By.id("licn")).clear();
		String licence = "12345678";
		driver.findElement(By.id("licn")).sendKeys(licence);
		objMapnew.put("License", licence);
	}//editLicense

	public void editSSN() {
		int count = 9;
		driver.findElement(By.id("ssn")).clear();
		String SSN = RandomStringUtils.randomNumeric(count);
		driver.findElement(By.id("ssn")).sendKeys(SSN);
		objMapnew.put("SSN", SSN);
	}

	public void editAge() {
		driver.findElement(By.id("age")).clear();
		String Agee = Integer.toString(person.getAge());
		driver.findElement(By.id("age")).sendKeys(Agee);
		objMapnew.put("Age", Agee);
	}//editAge

	public void editAddress() {
		driver.findElement(By.id("addr")).clear();
		String Streetadd = person.getAddress().getApartmentNumber() + " " + person.getAddress().getStreet();
		driver.findElement(By.id("addr")).sendKeys(Streetadd);
		objMapnew.put("Address", Streetadd);
	}

	public void editWeight() {
		int count = 2;
		driver.findElement(By.id("weight")).clear();
		String Wt = String.valueOf(RandomStringUtils.randomNumeric(count));
		driver.findElement(By.id("weight")).sendKeys(Wt);
		objMapnew.put("Weight", Wt);
	}//editWeight

	public void editHeight() {
		int count = 2;
		driver.findElement(By.id("height")).clear();
		String Ht = String.valueOf(RandomStringUtils.randomNumeric(count));
		driver.findElement(By.id("height")).sendKeys(Ht);
		objMapnew.put("Height", Ht);
	}//editHeight

	public void editCity() {
		driver.findElement(By.id("city")).clear();
		String city = person.getAddress().getCity().trim();
		city = city.replaceAll("\\s", "").trim();
		driver.findElement(By.id("city")).sendKeys(city);
		objMapnew.put("City", city);
	}//editCity

	public void editState() {
		int count = 2;
		driver.findElement(By.id("state")).clear();
		String state = RandomStringUtils.randomAlphabetic(count).toUpperCase();
		driver.findElement(By.id("state")).sendKeys(state);
		objMapnew.put("State", state);
	}

	public void editZipcode() {
		int count = 5;
		driver.findElement(By.id("zip")).clear();
		String zip = RandomStringUtils.randomNumeric(count);
		driver.findElement(By.id("zip")).sendKeys(zip);
		objMapnew.put("Zipcode", zip);
	}

	public void editProinfo() {
		int count = 8;
		driver.findElement(By.id("proinfo")).clear();
		String provalue = String.valueOf(RandomStringUtils.randomAlphabetic(count).toLowerCase());
		driver.findElement(By.id("proinfo")).sendKeys(provalue);
		objMapnew.put("ProInfo", provalue);
	}//editProinfo

	public void editInsurance() {
		int count = 8;
		driver.findElement(By.id("Insinfo")).clear();
		String insuvalue = RandomStringUtils.randomAlphabetic(count).toLowerCase();
		driver.findElement(By.id("Insinfo")).sendKeys(insuvalue);
		objMapnew.put("InsuranceInfo", insuvalue);
		System.out.println(objMapnew);
	} //editInsurance

	public void verifyAlerMessage() {
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement continueWE = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(saveButton)));
		driver.findElement(saveButton).click();
		Boolean message = false;
		Alert alert = driver.switchTo().alert();
		if (alert.getText().contains("Your Profile has been updated.")) {
			message = true;
			System.out.println("Patient data saved and alert message verified.");
			// Accepting alert
			alert.accept();
		} else {
			message = false;
			System.out.println("Patient data not updated sucessfully");
		}//verifyAlerMessage

	}  //verifyAlerMessage

	public void checkUpdatedValues() {
		Set<String> keyList = objMap.keySet();
		for (String key : keyList) {
			System.out.println("Different Values found in field" + key);
			if (objMap.get(key).equals(objMapnew.get(key)))
				System.out.println("No Update for   " + objMap.get(key) + " same as  " + objMapnew.get(key));
			else
				System.out.println("Updated Values for  " + objMap.get(key) + " are " + objMapnew.get(key));
		}
		System.out.println("New Data Verification Completed");
	} //checkUpdatedValues
	
	public void signout() {
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div/ul/li[9]/a/span")).click();
		driver.close();
	}
	
}  //EditProfileSingleEntryPage
