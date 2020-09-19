package org.iit.patientmodule.page;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.util.MultidimensionalCounter.Iterator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.iit.mmp.helper.HelperClass;
import org.iit.mmp.helpers.TestDataManager;
import org.iit.util.BaseClass;
import org.iit.util.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

public class LoginAccountPage extends BaseClass {

	/**
	 * Static declarations
	 **/
	private static String appURL = "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
	private static String username = "ria1";
	private static String password = "Ria12345";
	public static String testDataPath = System.getProperty("user.dir") + "\\src\\test\\resources\\";
	private static String testDataFilename = "logindata.xlsx";
	private static String dataSheetname = "Data";
	By editProfileLink = By.xpath("//a[@href='profile.php']");
	HelperClass helperObj;

	/**
	 * @return
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 */
	@DataProvider(name = "authentication")
	public Object[][] getDataFromDataprovider() throws EncryptedDocumentException, IOException {
		Logger.log("I", "Attempting to Edit Patient profile and Save");
//		driver.findElement(editProfileLink).click();

		// -- read test data from file
		HashMap<String, List<String>> testDataFromFile = TestDataManager
				.readDataFromExcel(new File(testDataPath + testDataFilename), dataSheetname);
		Object[][] testData = new Object[testDataFromFile.size()][1];
//Set entries = testDataFromFile.entrySet();
//Iterator entriesIterator = entries.iterator();
int i=0;
//while(entriesIterator.hasNext()) {
//	
//}
		return testData;
	} // getDataFromDataprovider

}// LoginAccountPage
