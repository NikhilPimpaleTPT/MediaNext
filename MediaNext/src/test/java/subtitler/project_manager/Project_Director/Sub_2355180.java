package subtitler.project_manager.Project_Director;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.PD_Pm_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import modules.PD_PM_user;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:This test case is to verify Snap to Shot changes fields are not visible all the time
 *
 */


public class Sub_2355180 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName_PD = "SUB_2355180"+CommonElements.BROWSER+"G5";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String menu_jobs = "Jobs";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2355180");
		dataSet.put("TL_test_case_description", "Project Director | Snap to Shot changes fields are visible even if disabled");
		dataSet.put("TL_internal_testCase_ID", "2355180");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			 // login using PM Credentials 
		    General.action().login(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
		    Thread.sleep(20000);
		   
			//Create Submission In From PD Tab
		    PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName_PD);
			PD_PM_user.action().Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName_PD,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
			PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			Thread.sleep(2000);
			
			PD_PM_user.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
			Thread.sleep(1000);
			PD_PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
			
			//Verify created submission in pending status
			PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName_PD);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_PD), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				
			}
			
			Thread.sleep(2000);
			PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_edit_button);
			Thread.sleep(8000);
			
			
			assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_edit_settings_snapToShotChangeCheckbox_unchecked, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Snap to Shot Changes should not be visible as the setting is disabled.");
				
			}
			
			assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_edit_settings_snapToShotChangeCheckbox_checked, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Snap to Shot Changes should not be visible as the setting is disabled.");
				
			}
			
			} catch (Exception e) {
		         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	        }
		}


		
		public void assertion() throws Exception {
			assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_edit_settings_snapToShotChangeCheckbox_checked, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Snap to Shot Changes should not be visible as the setting is disabled.");
				
			}else {
			report("p", "All assertions passed");
			 }
			}
		
		
		
		@AfterMethod
		public void tearDown() throws Exception {
		General.action().stopsystem();
		}

		public void report(String result, String notes) throws Exception
		{
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild,result,Subtitler_TestRail_Common_Properties.assignedTo,notes);
		if(result == "f")
		assertTrue(false);
	}
	}

		