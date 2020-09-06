package org.iit.mmp.helper;

import org.iit.util.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperClass {
WebDriver d;

By loginMainLink = By.linkText("Login");
By loginUsername = By.id("username");
By loginPassword = By.id("password");
By loginClick = By.xpath("//input[@name='submit']");
By logoutLink = By.xpath("/html/body/div[1]/div[1]/div[1]/div/ul/li[9]/a/span");
By editProfileLink = By.xpath("//a[@href='profile.php']");


	public HelperClass(WebDriver driver, String appURL){
		this.d = driver;
		driver.get(appURL);
	} // HelperClass
	
	public void navigateToAModule(By xpathBy)
	{ 
		Logger.log("I", "Attempting to Navigate");
		d.findElement(xpathBy).click();
	} // navigateToAModule

	public void _openAppURL(String appURL) {
		Logger.log("I", "Opening URL " + appURL);
		d.get(appURL);
	} // _openAppURL

	/**
	 **/
	public void _closeAppURL() {
		d.quit();
	} // _closeAppURL
	
	public void loginPatientPortal(String username, String password) {
		Logger.log("I", "Attempting to login to Patient Portal");
		d.findElement(loginMainLink).click();
		d.findElement(loginUsername).sendKeys(username);
		d.findElement(loginPassword).sendKeys(password);
		d.findElement(loginClick).click();
	} // loginPatientPortal

	/**
	 **/
	public void logoutPatientPortal() {
		Logger.log("I", "Attempting to logout from Patient Portal");
		d.findElement(logoutLink).click();
	} // logoutPatientPortal
	
} //HelperClass
