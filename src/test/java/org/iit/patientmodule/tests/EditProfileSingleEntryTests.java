package org.iit.patientmodule.tests;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.iit.mmp.helper.HelperClass;
import org.iit.patientmodule.page.EditPatientProfileSavePage;
import org.iit.patientmodule.page.EditProfileSingleEntryPage;
import org.iit.util.Logger;
import org.junit.AfterClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EditProfileSingleEntryTests extends EditProfileSingleEntryPage {
	
	@Test
	public void  validateUpdateProfile() throws Exception{
		Logger.log("I", "Validate Edit & Update  Functionality Successfully");
		editPatientProfile();
	} // validateUpdateProfile
	
} //EditProfileSingleEntryTests

