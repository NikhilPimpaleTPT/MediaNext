package subtitler.project_manager.Sort_Jobs;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_306370 {
	
	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();


	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = { "fr-FR", "de-DE", "ja-JP" };
	String Targetlanguage_1[] = { "French (France)", "German (Germany)","Japanese (Japan)" };
	String SubmissionName = "Sub_306370"+CommonElements.BROWSER+"Y1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String menu_AllJobs = "Jobs";
	String menu_ToClaim = "To Claim";
	String TranslatorGroupName = "Common_group";
	

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_306370");
		dataSet.put("TL_test_case_description","SUB-306370:Sort by Assignee");
		dataSet.put("TL_internal_testCase_ID", "306370");
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
			PM_user.action().Navigate(menu_submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
           for (int i = 1; i <= 3; i++) {
		   PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
		   PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
		   PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
		   PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
		   PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName + i,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
           Thread.sleep(1000);
           }
           PM_user.action().SearchSubmisson(SubmissionName);
           Thread.sleep(1000);
           PM_user.action().Navigate(menu_ToClaim);
           Thread.sleep(1000);
           PM_user.action().PM_ToClaim(SubmissionName + "1");
           Thread.sleep(1000);
           General.action().logoutMethod();
           
           //trans login
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ToClaim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName + "2");
			Thread.sleep(1000);
			General.action().logoutMethod();
			
			// review login
			General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_ToClaim);
			Thread.sleep(1000);
			Reviewer.action().review_ToClaim(SubmissionName + "3");
			Thread.sleep(1000);
			General.action().logoutMethod();
			
			//login PM
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_AllJobs);
		    Thread.sleep(1000);
			
			
			//sort submission in Jobs by Name
			PM_user.action().sortSubmission_Assignee(SubmissionName, true);

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_Assignee(SubmissionName + "1", "Subtitler_PM"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_Assignee(SubmissionName + "1","Subtitler_PM"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in Jobs after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_Assignee(SubmissionName + "2","Subtitler_Translator"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_Assignee(SubmissionName + "2","Subtitler_Translator" ), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in Jobs after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_Assignee(SubmissionName + "3","Subtitler_Reviewer"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_Assignee(SubmissionName + "3","Subtitler_Reviewer"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in Jobs after search");
			}

			PM_user.action().sortSubmission_Assignee(SubmissionName, false);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_Assignee(SubmissionName + "3","Subtitler_Reviewer"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_Assignee(SubmissionName + "3","Subtitler_Reviewer"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in Jobs after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_Assignee(SubmissionName + "2","Subtitler_Translator"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_Assignee(SubmissionName + "2","Subtitler_Translator"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in Jobs after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_Assignee(SubmissionName + "1","Subtitler_PM"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_Assignee(SubmissionName + "1","Subtitler_PM"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in Jobs after search");
			}

			System.out.println("EOM sortSubmission()");
            Thread.sleep(4000);
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_Assignee(SubmissionName + "1","Subtitler_PM"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying  Submission Name in Jobs after search");
		} else {
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


