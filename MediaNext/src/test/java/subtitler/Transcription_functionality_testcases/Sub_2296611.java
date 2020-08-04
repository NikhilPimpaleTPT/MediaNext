package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This test case is to Validate for the Snap to shot changes settings
 *
 */


public class Sub_2296611 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_2296611"+CommonElements.BROWSER+"A2";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
    Boolean assertion = true;
    
    @BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_2296611");
		dataSet.put("TL_test_case_description","Sub_2296611: As a PM, Validate for the Snap to shot changes settings");
		dataSet.put("TL_internal_testCase_ID", "2296611");
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
			PM_user.action().Navigate(menu_submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			CreateSubmisson_Step1_SnapToShotChanges("17", "35", "80", "100",true,"7","12","4");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			//Edit Submission
			Thread.sleep(6000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			Thread.sleep(6000);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_EditSubmission_SnapToShot_Threshold_input);
			Thread.sleep(1000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_EditSubmission_SnapToShot_Threshold_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_EditSubmission_SnapToShot_Threshold_input,"12");
			Thread.sleep(1000);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_EditSubmission_SnapToShot_Gap_input);
			Thread.sleep(1000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_EditSubmission_SnapToShot_Gap_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_EditSubmission_SnapToShot_Gap_input,"12");
			Thread.sleep(1000);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_EditSubmission_SnapToShot_SnapBeforeShotChange_input);
			Thread.sleep(1000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_EditSubmission_SnapToShot_SnapBeforeShotChange_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_EditSubmission_SnapToShot_SnapBeforeShotChange_input,"4");
			Thread.sleep(1000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_EditSubmission_SnapToShotChanges_error("Value cannot be equal or bigger than Gap"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying error for SnapToShotChanges");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_settings_updateButton_disabled, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying PM_transcription_editSubmission_settings_updateButton_disabled");
			}
			
			
			
			
			
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	 public void CreateSubmisson_Step1_SnapToShotChanges(String ReadingSpeed,String MaxCharperLine,String MindurationVSreadingSpeed,String MaxdurationVSreadingSpeed,boolean snapToShotChange,String threshold,String gap,String snapBeforeShotChange) throws Exception{
		 System.out.println("INSIDE method CreateSubmisson_Step1_SnapToShotChanges()\n");
		    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
			Thread.sleep(1000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input,ReadingSpeed);
			Thread.sleep(1000);
			
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,MaxCharperLine);
			Thread.sleep(1000);
			
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,MindurationVSreadingSpeed);
			Thread.sleep(1000);
			
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,MaxdurationVSreadingSpeed);
			Thread.sleep(1000);
			
			if(snapToShotChange) {
				
			System.out.println("Snap To Shot Changes is not checked , Checked it");
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShotChanges_unchecked);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShotChanges_unchecked);
			Thread.sleep(1000);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShot_Gap_input);
			Thread.sleep(1000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShot_Gap_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShot_Gap_input,gap);
			Thread.sleep(1000);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShot_Threshold_input);
			Thread.sleep(1000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShot_Threshold_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShot_Threshold_input,threshold);
			Thread.sleep(1000);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShot_SnapBeforeShotChange_input);
			Thread.sleep(1000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShot_SnapBeforeShotChange_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShot_SnapBeforeShotChange_input,"5");
			Thread.sleep(1000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShotChanges_error("Value cannot be bigger than the Min count of frames between sub"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying error for threshold");
			}
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShot_SnapBeforeShotChange_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShot_SnapBeforeShotChange_input,snapBeforeShotChange);
			Thread.sleep(1000);
			}
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
			Thread.sleep(4000);
			System.out.println("EOM CreateSubmisson_Step1_SnapToShotChanges()\n");
			
			

		}
	
public void assertion() throws Exception {
		
		//Verify Update button should be disabled when portal detects that user has entered values that are not valid
	    assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_settings_updateButton_disabled, 5);
	    if (assertion == false) {
		report("f","Assertion failed while verifying PM_transcription_editSubmission_settings_updateButton_disabled");
	    }
		else {
			report("p", "Assertion is passed.");
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
