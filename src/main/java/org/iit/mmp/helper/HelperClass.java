package org.iit.mmp.helper;
import org.iit.util.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperClass {
WebDriver d;

By loginMainLink = By.linkText("Login");
By loginUsername = By.id("username");
By loginPassword = By.id("password");
By AdminloginUsername = By.xpath("//input[@id='username']");
By AdminloginPassword = By.xpath("//input[@id='password']");

By loginClick = By.xpath("//input[@name='submit']");
By AdminloginClick = By.xpath("///input[@name='admin']");
By logoutLink = By.xpath("/html/body/div[1]/div[1]/div[1]/div/ul/li[9]/a/span");
By editProfileLink = By.xpath("//a[@href='profile.php']");
By feesLink = By.xpath("//span[contains(text(),'Fees')]");

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
		Logger.log("I", "Closing URL");
		d.quit();
	} // _closeAppURL
	
	public void loginPatientPortal(String username, String password) {
		Logger.log("I", "Attempting to login to Patient Portal");
		d.findElement(loginMainLink).click();
		d.findElement(loginUsername).sendKeys(username);
		d.findElement(loginPassword).sendKeys(password);
		d.findElement(loginClick).click();
	} // loginPatientPortal

	
	public void loginAdminPortal(String username, String password) {
		Logger.log("I", "Attempting to login to Admin Portal");
		//d.findElement(loginMainLink).click();
		d.findElement(AdminloginUsername).sendKeys(username);
		d.findElement(AdminloginPassword).sendKeys(password);
		d.findElement(AdminloginClick).click();
	} // loginPatientPortal
	
	/**
	 **/
	public void logoutPatientPortal() {
		Logger.log("I", "Attempting to logout from Patient Portal");
		d.findElement(logoutLink).click();
	} // logoutPatientPortal
	
} //HelperClass
