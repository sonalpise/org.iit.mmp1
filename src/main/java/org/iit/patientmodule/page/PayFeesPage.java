package org.iit.patientmodule.page;

import org.iit.util.BaseClass;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.iit.util.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class PayFeesPage  extends BaseClass{
	
	By fees=By.xpath("//p[contains(text(),'%%defaultItem%%')]");
	   WebElement populatedFees;
	   protected String displayedFees;
	   protected String defaultItem;
	   
	   
	public void payFees() {
		String selectedAmount;
		Logger.log("I", "Attempting to pay  Fees");
		
		driver.findElement(By.xpath("//button[contains(text(),'Pay Now')]")).click();
		
		int selection = 1;
		Select payamt = new Select(driver.findElement(By.id("amount")));
		payamt.selectByIndex(selection);
		
		WebElement option = payamt.getFirstSelectedOption();
		defaultItem = option.getText();
		System.out.println(defaultItem);
		
	    driver.findElement(By.xpath("//div[@class='panel-body nopadding']//form//p//input")).click();
	    //	String doctorXpath =  "//h4[contains(text(),'%%doctorName%%')]/ancestor::td/button[@id='opener']";
	    // driver.findElement(By.xpath(doctorXpath.replace("%%doctorName%%", doctorName))).click();
	    
	 
	    String xpatha = "(//p[contains(text(),'" +defaultItem + "' )])";
	    populatedFees = driver.findElement(By.xpath(xpatha));
	    displayedFees = (populatedFees.getText());
	  
	    driver.findElement(By.xpath(" //input[@id='name']")).sendKeys("First  Last");
	    
		Select card = new Select(driver.findElement(By.id("card_name")));
		card.selectByIndex(1);
		
		driver.findElement(By.xpath("//input[@id='cid']")).sendKeys("4222222222222");
		
		Select month = new Select(driver.findElement(By.xpath("//select[@id='cardMonth']")));
		month.selectByIndex(1);
		
		Select year = new Select(driver.findElement(By.xpath("//select[@id='cardYear']")));
		year.selectByIndex(3);
		
		driver.findElement(By.id("cvv")).sendKeys("233");
		
		driver.findElement(By.xpath("//form[@id='myform']//p//input")).click();
		
		System.out.println("Fees Paid Successfully");
		
	}// payFees
	
	public void verifyFeesAmout() {
		
		
	} // verifyFeesAmout

}
