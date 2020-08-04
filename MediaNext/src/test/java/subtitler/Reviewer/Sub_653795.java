package subtitler.Reviewer;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;
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

public class Sub_653795 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_653795"+CommonElements.BROWSER+"T";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_653795");
		dataSet.put("TL_test_case_description","Sub_653795 :Character count per line of Target column");
		dataSet.put("TL_internal_testCase_ID", "653795");
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
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", true);
			System.out.println("filePath------>" + filePath);
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
			Thread.sleep(2000);
			General.action().logoutMethod();
			
			//trans login
			 General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
	         Thread.sleep(1000);
	         Translator.action().Navigate(menu_to_claim);
	         Thread.sleep(1000);
	         Translator.action().trans_ToClaim(SubmissionName);
	         Thread.sleep(1000);
	         Translator.action().Navigate(menu_ongoing);
	         Thread.sleep(1000);
	         Translator.action().translate_onGoing_submission(SubmissionName, targetlanguage_1, true, false);
	         Thread.sleep(1000);
	         Translator.action().Navigate(menu_completed);
	         Thread.sleep(1000);
	         Translator.action().SearchSubmisson(SubmissionName);
	         Thread.sleep(2000);

			 assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translatorSearchResult(SubmissionName), 5);
			 if (assertion == false) {
			  report("f","Assertion failed while verifying SubmissionName  after Search");
			  }
			  Thread.sleep(2000);
			  General.action().logoutMethod();
			  
			// review login
			General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Reviewer.action().review_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			review_onGoing_qualityCheck(SubmissionName, targetlanguage_1);
			Thread.sleep(2000);
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}
	public void review_onGoing_qualityCheck(String SubmissionName, String target)throws Exception {

		System.out.println("INSIDE review_onGoing_qualityCheck  method()");

		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().review_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(ReviewerLocators.Locator().reviewer_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
		Thread.sleep(5000);

		
       // check for Character count per line of Target column
		
		for (int i=1; i<=4;i++){
			Thread.sleep(2000);
			
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
		Thread.sleep(2000);
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea_line(i), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying bold target segment is active");
			Thread.sleep(4000);
		} 
	}
		
    System.out.println("EOM review_onGoing_qualityCheck  method()");
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea_line(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying bold target segment is active");
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
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}
}






