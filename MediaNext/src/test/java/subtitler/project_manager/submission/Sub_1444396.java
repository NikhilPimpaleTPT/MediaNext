package subtitler.project_manager.submission;

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
 * @author pvaidya                                                   
 *Summary:This testcase verifies that step name is showing in every phase of job flow overview.
 *Preconditions:Create and complete a submission workflow before test.
 */ 

public class Sub_1444396 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1444396"+CommonElements.BROWSER+"B4";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
	String menu_allJobs = "Jobs";
	String menu_completed = "Completed";
	
	Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1444396");
		dataSet.put("TL_test_case_description", "SUB-1444396:Job flow overview should contain step names instead of step types.");
		dataSet.put("TL_internal_testCase_ID", "1444396");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			//Create Submission
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, Targetlanguage);
			Thread.sleep(2000);
		
			//SEARCH SUBMISSION AND CHECK
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			//Go to Jobs
			PM_user.action().Navigate(menu_allJobs);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			//Click on job history
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_AllJobs_JobHistory);
			Thread.sleep(2000);
			
			//Veify submission status and step
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_JobHistory_steps("trans"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying step names instead of step types");
			}
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_JobHistory_status("Available"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying job status");
			}
			
			//Click on dialogue box cancel button
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_AllJobs_JobHistory_DialogBox_CancelButton);
			Thread.sleep(2000);
			
			//Clock on claim jobs and verify step
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_step_X(SubmissionName,"trans"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying job step");
			}
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(1000);
			//Click on ongoing and verify step
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_step_X(SubmissionName,"trans"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying job step");
			}
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			PM_user.action().PM_onGoing_submission(SubmissionName, "German (Germany)", true, false);
			
			//Click on complted and verify step names instead of step types.
			PM_user.action().Navigate(menu_completed);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			
			
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}


	public void assertion() throws Exception {
		//Click on complted and verify step names instead of step types.
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_step_X(SubmissionName,"trans"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying job step");
		}else {
			report("p", "All Assertion passed.");
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