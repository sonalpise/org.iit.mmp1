package org.iit.patientmodule.page;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.iit.mmp.helper.HelperClass;
import org.iit.util.BaseClass;
import org.iit.util.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
1. Verify Message Link visible and functional
2. Verify able to enter text in contact Reason
3. Verify enter Subject and Send

*/
public class SendingMessageDocPage  extends BaseClass
{
	By linkSendingMessagePatientM  = By.xpath("//a[contains(@href,'sendmessage')]");
	By linkSendingMessageAdminM  = By.xpath("//a[contains(@href,'message')]");
	By editProfileLink = By.xpath("//a[@href='profile.php']");
	By sendButton = By.xpath("//input[contains(@value,'Send')]");
	By subjectBox = By.id("subject");
	By messageBox = By.id("message");
	
	//ADMIN MODULE
	String name, subject, description;
	HashMap <String, String> hMap;
	By patientName = By.xpath("//table[@class='table']//tr[2]/td[1]");
	By subjectMessage = By.xpath("//table[@class='table']//tr[2]/td[2]");
	By descriptionMessage = By.xpath("//table[@class='table']//tr[3]/td[2]");

	public String patientFirstName;
	HashMap<String, String> messageData = new HashMap<String, String>();	
	
	public void sendingMessage() {
		driver.findElement(linkSendingMessagePatientM).click();
		// Verify Subject and Reason

		messageData.put("Subject", "Message for Dr. Becky");
		messageData.put("Message", "Hello Doc, Please Refill my blood sugar prescriptions ");
		messageData.put("Patientname", patientFirstName);
		driver.findElement(subjectBox).sendKeys(messageData.get("Subject"));
		driver.findElement(messageBox).sendKeys(messageData.get("Message"));
	
		Logger.log("I",messageData.keySet().toString());
		
		for (String key : messageData.keySet())
			System.out.println(key + ": " + messageData.get(key));
		//Verify Send is functional
	//	driver.findElement(sendButton).click();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement continueWE = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(sendButton)));
		continueWE.click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		
//		if (alert.getText().contains("Messages Successfully sent.")) {
//			Logger.log("I", "Sending Message is Successful"); 
//			
//		}
//			else {
//				Logger.log("I", "Message sending failed");
//			}
	} //sendingMessage
	
	public HashMap <String, String> retrieveRecentMessageDetails(){
		name = driver.findElement(patientName).getText();
		subject = driver.findElement(subjectMessage).getText();
		description = driver.findElement(descriptionMessage).getText();
		hMap.put("Name", name);
		hMap.put("Subject", subject);
		hMap.put("Description", description);
		
		for (String key : hMap.keySet())
			System.out.println(key + ": " + hMap.get(key));
		
		System.out.println(driver.findElement(By.xpath("//table[@class='table']//tr[2]/td[2]")).getText());
		System.out.println(driver.findElement(By.xpath("//table[@class='table']//tr[3]/td[2]")).getText());
		return hMap;
	}//retrieveRecentMessageDetails

	public boolean validateMessageFromAdminModule(){

		boolean result = false;
		if(hMap.get("Subject").equals(messageData.get("Subject")) && hMap.get("Description").equals(messageData.get("Message")) &&hMap.get("Name").equals(messageData.get("Patientname"))){
			System.out.println("Passed");
			result = true;
		}
		return result;
	} //validateMessageFromAdminModule
	
	
} //SendingMessageDocPage
