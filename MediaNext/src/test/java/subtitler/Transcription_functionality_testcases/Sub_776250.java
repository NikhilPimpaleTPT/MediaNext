package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.RobotExt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Verify;

public class Sub_776250 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_776250" + CommonElements.BROWSER + "Q4";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String path;
	
	
	

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_776250");
		dataSet.put("TL_test_case_description","SUB-776250:Import an incorrect SRT file : message is wrong");
		dataSet.put("TL_internal_testCase_ID", "776250");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
		try {
			// PM login
			General.action().login(CommonElements.action().PM_username, CommonElements.action().password);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER + CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			Pm_Transcription_Incorrect_SRT(SubmissionName, targetlanguage_1, false, true);
			Thread.sleep(1000);

		} catch (Exception e) {
			report("f", "Execution level error was encountered.\n\nError log:\n\n" + Verify.action().getErrorBuffer(e));
		}

	}

	public void Pm_Transcription_Incorrect_SRT(String SubmissionName, String target, boolean complete,boolean back) throws Exception {

		System.out.println("INSIDE Pm_Transcription_Ongoing_navigate_faster  method()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
		Thread.sleep(9000);
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
		Thread.sleep(2000);
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace);
		Thread.sleep(2000);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_ongoing_find_and_replace  after Search");
		}
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_keyboard_shortcuts);
		Thread.sleep(2000);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_keyboard_shortcuts, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_ongoing_keyboard_shortcuts  after Search");
		}
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file);
		Thread.sleep(2000);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Ongoing_Import_file  after Search");
		}
		
		
		Thread.sleep(3000);
		
		File afile=new File(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_IMPORT_WRONGSRT_FOLDER);
		File[] listOfFiles={afile};
    	if (afile.isDirectory())    
    	    listOfFiles = afile.listFiles();
    	   
    	   Thread.sleep(3000);
    	      
    	   //Process array
    	    for (int i = 0; i < listOfFiles.length; i++)
    	    { 
    	     if(listOfFiles[i].isDirectory()) 
    	    	 continue;
    	    
    	      path = listOfFiles[i].getAbsolutePath();
    	     
    	    }
    	 	Thread.sleep(3000);
    	 	
    	 	
    	 	System.out.println("INVALID PATH-------->"+path);
 
    	 	
    	 	//TODO THIS IMPL IS DUE TO AS FILE CANNOT BE IMPORTED PROPERLY IN FIREFOX THROUGH senKeys(), so FOR FIREFOX WE ARE USING ROBOT CLASS AND FOR CHROME WE ARE USING sendKeys()
        	System.out.println("WHTS IS THIS---->"+CommonElements.BROWSER);
        	if(CommonElements.BROWSER.contains("FIREFOX")) {
	        	System.out.println("------THIS IS FIREFOX-----");
	    		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file);
	    		Thread.sleep(3000);
	    		RobotExt.robot().delay(2000);
	    		RobotExt.robot().sendKeys(path);
	    		RobotExt.robot().processFilePath();
	    		RobotExt.robot().delay(2000);
        	}else {
            	System.out.println("------THIS IS CHROME-----");
        		General.action().type(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file_input,path);
        		
        		
        	}

	

	
         System.out.println("EOM Trans_Ongoing_Import_translation_InvalidFile()");
	}

		public void assertion() throws Exception {
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_InvalidImportFile_Error_message());
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_InvalidImportFile_Error_message(), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
			Thread.sleep(2000);
		} else {
			report("p", "All Assertion passed.");
		}
	}

	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),
				Subtitler_TestRail_Common_Properties.idTestPlan, Subtitler_TestRail_Common_Properties.idBuild, result,
				Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}


}
