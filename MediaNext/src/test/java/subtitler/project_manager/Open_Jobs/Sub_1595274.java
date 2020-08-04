package subtitler.project_manager.Open_Jobs;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.RobotExt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This testcase verifies that if search & replace all works for literal characters.
 *
 */ 

public class Sub_1595274 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1595274"+CommonElements.BROWSER+"Y2";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String UserName = "Maya Gurnale";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_Completed = "Completed";
	String path;
	@BeforeMethod
	public void setup() throws Exception {General.action().startSystem("SUB_1595274");
		dataSet.put("TL_test_case_description", "SUB-1595274:Search&ReplaceAll for dots.");
		dataSet.put("TL_internal_testCase_ID", "1595274");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
		    // PM login
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION.
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_DOT_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			Thread.sleep(000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			
			
			//CLAIM JOB
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(1000);
			//NAVIGATE TO ONGOING AND OPEN JOB
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			PM_onGoing_submission(SubmissionName,"trans",false,false);
			
			
			
			
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	
	public void PM_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
		
		 System.out.println("INSIDE PM_Ongoing  method()");
		 
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		 Thread.sleep(1000);
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input, SubmissionName);
		 Thread.sleep(2000);
			
		 if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName, target)))
		 {
		  System.out.println("INSIDE IF--------");
		  PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		 }
		 Thread.sleep(2000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
		 Thread.sleep(2000);	
		 
		 General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
		    Thread.sleep(1000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Trans_ongoing_multiOption_icon);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Trans_ongoing_multiOption_icon);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
			Thread.sleep(1000);

	    	File afile=new File(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_DOT_SRT_FOLDER);
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
	    	 	
	        	System.out.println("WHTS IS THIS---->"+CommonElements.BROWSER);
	        	if(CommonElements.BROWSER.contains("FIREFOX")) {
		        System.out.println("------THIS IS FIREFOX-----");
		        Thread.sleep(3000);
		        General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
		        Thread.sleep(3000);
		        General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
				Thread.sleep(3000);
		    	RobotExt.robot().delay(2000);
		    	RobotExt.robot().sendKeys(path);
		    	RobotExt.robot().processFilePath();
		    	RobotExt.robot().delay(2000);
	        	}else {
	            System.out.println("------THIS IS CHROME-----");
	            General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
	            Thread.sleep(3000);
	            General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
	    		Thread.sleep(3000);
	    		RobotExt.robot().delay(2000);
	    		RobotExt.robot().sendKeys(path);
	    		RobotExt.robot().processFilePath();
	    		RobotExt.robot().delay(2000);
	    		Thread.sleep(4000);
	        	}

	    	Thread.sleep(2000);
			if(Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_ongoing_overwrite_current_translation, 10)) {
				General.action().Click(TranslatorLocators.Locator().Trans_ongoing_overwrite_current_translation);
				Thread.sleep(2000);
			}
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Trans_ongoing_multiOption_icon);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Trans_ongoing_multiOption_icon);
			Thread.sleep(1000);
			
			//Repace a.b by a,b
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace);
			Thread.sleep(1000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_find);
			Thread.sleep(1000);
			PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_find,"a.b");
			Thread.sleep(2000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_replaceBy);
			Thread.sleep(1000);
			PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_replaceBy,"a,b");
			Thread.sleep(2000);
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_ReplaceAll_button_active, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_ReplaceAll_button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_Close_button);
			Thread.sleep(1000);
			
			
			Thread.sleep(4000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Trans_ongoing_multiOption_icon);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Trans_ongoing_multiOption_icon);
			Thread.sleep(1000);

			Thread.sleep(1000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace);
			Thread.sleep(1000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_find);
			Thread.sleep(1000);
			PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_find,"a.b");
			Thread.sleep(2000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_find);
			Thread.sleep(1000);
			    
		System.out.println("EOM PM_Ongoing  method()");
	}
	
	
	public void assertion() throws Exception {
		//Verify that replaced text is no more present and find and replace working.
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_Occurrrences("0"+" occurrences found."), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying status available");
		}else {
			report("p", "All Assertion Passed.");
		}
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}
}
