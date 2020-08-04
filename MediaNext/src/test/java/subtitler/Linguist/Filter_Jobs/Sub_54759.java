package subtitler.Linguist.Filter_Jobs;

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
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;
import modules.admin;

public class Sub_54759 {

	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	
	String sourcelanguage = "en-US - English (United States)";
	String targetlanguage = "de-DE - German (Germany)";
	String SubmissionName = "Sub_54759"+CommonElements.BROWSER+"M1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String menu_ToClaim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
    String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_54759");
		dataSet.put("TL_test_case_description", "SUB-54759:Search by Job name");
		dataSet.put("TL_internal_testCase_ID", "54759");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {

			admin.action().login(CommonElements.action().PM_username, CommonElements.action().password);
			admin.action().Navigate(menu_submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			for (int i = 1; i <= 3; i++) {
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName + i,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);

			}
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(1000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			General.action().logoutMethod();

			// trans login
			// check sorting in Trans TOCLAIM tab
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ToClaim);
			Thread.sleep(1000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(1000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}

			Thread.sleep(2000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(2000);
            Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(1000);

			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}

			Thread.sleep(4000);
            Translator.action().translate_onGoing_submission(SubmissionName + "1", targetlanguage_1, true, false);
			Thread.sleep(2000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
			Thread.sleep(2000);
            Translator.action().translate_onGoing_submission(SubmissionName + "2", targetlanguage_1, true, false);
			Thread.sleep(2000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
			Thread.sleep(1000);
			Translator.action().translate_onGoing_submission(SubmissionName + "3", targetlanguage_1, true, false);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_completed);
			Thread.sleep(1000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(1000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Bad Credentials Message");
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
