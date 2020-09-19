package org.iit.patientmodule.page;

import org.iit.util.BaseClass;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.EncryptedDocumentException;
import org.iit.util.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;


public class SearchSymptomPage extends BaseClass{

	public void _navigateSerachSymptoms(String symptomtype) {
		Logger.log("I", "Attempting to navigate to Search Symptoms");
		driver.findElement(By.xpath("//span[contains(text(),'Search Symptoms')]")).click();
		driver.findElement(By.xpath("//input[@id='search']")).clear();
		driver.findElement(By.xpath("//input[@id='search']")).sendKeys(symptomtype);
		driver.findElement(By.xpath(" //input[@name='submit']")).click();
	} // navigateSerachSymptoms

	public void searchSymptoms(String symptomtype) {
		_navigateSerachSymptoms(symptomtype);
		try {
			// -- let page refresh with results
			Thread.sleep(2000);
			Logger.log("I", "Validating search results from search");			
			WebElement mytable = driver.findElement(By.xpath(" //table[@class='table']"));
			List<WebElement> resultRows = mytable.findElements(By.xpath("//tbody//tr"));
			System.out.println("I have " + resultRows.size() + " record(s) for " + symptomtype);
		} catch (InterruptedException e) {
			System.out.println("Timer interrupted");
		}
	}// searchSymptoms
	
	
} // SearchSymptomPage
