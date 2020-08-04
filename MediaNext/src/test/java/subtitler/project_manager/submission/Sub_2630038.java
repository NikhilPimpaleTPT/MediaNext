package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.RobotExt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Admin_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:This tc is to verify Source subtitles should not be duplicated on the video player
 *
 */

public class Sub_2630038 {
	

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_2630038"+CommonElements.BROWSER+"O2";
	String Targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String path;
	Boolean assertion = true;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2630038");
		dataSet.put("TL_test_case_description", "SUB-2630038:Source subtitles should not be duplicated on the video player");
		dataSet.put("TL_internal_testCase_ID", "2630038");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			 PM_user.action().Navigate(menu_to_claim);
	         Thread.sleep(1000);
	         PM_user.action().PM_ToClaim(SubmissionName);
	         Thread.sleep(1000);
	         PM_user.action().Navigate(menu_ongoing);
	         Thread.sleep(1000);
	         PM_onGoing_submission(SubmissionName, Targetlanguage, false, true);
	         Thread.sleep(1000);
	         
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public void PM_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
		
		 System.out.println("INSIDE PM_Ongoing  method()");
		 
		    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
			Thread.sleep(1000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input, SubmissionName);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName, target)))
			{
				System.out.println("INSIDE IF--------");
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
			}
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
			
			
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(1));
			Thread.sleep(1000);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Trans_ongoing_multiOption_icon);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Trans_ongoing_multiOption_icon);
			Thread.sleep(1000);
		
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Trans_Ongoing_Import_file);

			//Import file by timecode
			File afile=new File(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
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
	    	 	Thread.sleep(1000);
	    	 	
	    	 	
			//Trans_Ongoing_Import_file_input    	 
	    	System.out.println("VALID PATH-------->"+path);
	    	System.out.println("WHTS IS THIS---->"+CommonElements.BROWSER);
	    	
		 	//TODO THIS IMPL IS DUE TO AS FILE CANNOT BE IMPORTED PROPERLY IN FIREFOX THROUGH senKeys(), so FOR FIREFOX WE ARE USING ROBOT CLASS AND FOR CHROME WE ARE USING sendKeys()
	    	if(CommonElements.BROWSER.contains("FIREFOX")) {
		    	System.out.println("------THIS IS FIREFOX-----");
		    	Thread.sleep(1000);
		    	General.action().Click(Pm_User_Submission_Locators.Locator().Trans_Ongoing_Import_file);
	        	Thread.sleep(3000);
	        	General.action().Click(Pm_User_Submission_Locators.Locator().Trans_Ongoing_Importfile_ByTimecode);
				Thread.sleep(3000);
				RobotExt.robot().delay(2000);
				RobotExt.robot().sendKeys(path);
				RobotExt.robot().processFilePath();
				RobotExt.robot().delay(2000);
				Thread.sleep(4000);
	    	}else {
	        	System.out.println("------THIS IS CHROME-----");
	        	General.action().Click(Pm_User_Submission_Locators.Locator().Trans_Ongoing_Import_file);
	        	Thread.sleep(3000);
	        	General.action().Click(Pm_User_Submission_Locators.Locator().Trans_Ongoing_Importfile_ByTimecode);
				Thread.sleep(3000);
				RobotExt.robot().delay(2000);
				RobotExt.robot().sendKeys(path);
				RobotExt.robot().processFilePath();
				RobotExt.robot().delay(2000);
				Thread.sleep(4000);
	    	}
	    	Thread.sleep(8000);
	    	if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Trans_ongoing_overwrite_current_translation, 10)) {
				General.action().Click(Pm_User_Submission_Locators.Locator().Trans_ongoing_overwrite_current_translation);
				Thread.sleep(20000);
			}
	    	
	    	Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(1));
			Thread.sleep(1000);
	    	
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Trans_ongoing_video_multiOption_icon);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Trans_ongoing_video_multiOption_icon);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Trans_ongoing_video_multiOption_options("Source"));
			Thread.sleep(1000);
	    	
				  
	}

	public void assertion() throws Exception {
		//This TC is blocked as need to verify text on video screen (Dont have locator to check text on video screen)
		//Source subtitles should not be duplicated on the video player(refer image and last test case step)
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Trans_ongoing_video_multiOption_options("Source"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Verify source option");
		} else {
			report("b", "All Assertion blocked.");
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
