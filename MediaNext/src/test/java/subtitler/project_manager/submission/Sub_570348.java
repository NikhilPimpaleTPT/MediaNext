package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;

public class Sub_570348 {
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_570348"+CommonElements.BROWSER+"M2";
	String targetlanguage_1 = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
    String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String trans_target_text = "Testing target segment";
	String review_target_text = "modified_target";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_570348");
		dataSet.put("TL_test_case_description","Sub_570348:Translation/Review Screen > 'Cancel current edit' button");
		dataSet.put("TL_internal_testCase_ID", "570348");
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
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
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

			// tran login
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			translate_onGoing_submission(SubmissionName, targetlanguage_1,true, false);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_completed);
			Thread.sleep(1000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translatorSearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
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
			reviewer_onGoing_submission(SubmissionName, targetlanguage_1, true,false);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_completed);
			Thread.sleep(1000);
			Reviewer.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
		

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void translate_onGoing_submission(String SubmissionName,String target, boolean complete, boolean back) throws Exception {

		System.out.println("INSIDE trans_Ongoing  method()");

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(TranslatorLocators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
		Thread.sleep(9000);
		General.action().Click(TranslatorLocators.Locator().translator_Video_play);
		Thread.sleep(2000);
		List<WebElement> listofIds1 = General.driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
		Thread.sleep(1000);
		System.out.println("No of IDS--------" + listofIds1.size());

		for (int i = 1; i <= listofIds1.size(); i++) {
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(i),trans_target_text);
			Thread.sleep(2000);

			String TARGET_SEGMENT_TEXT = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(i)).getText();
			System.out.println("TARGET SEGMENT INPUT TEXT:-"+ TARGET_SEGMENT_TEXT);
			Thread.sleep(3000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(3000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_cancel_current_edit);
			Thread.sleep(3000);
			General.action().Click(TranslatorLocators.Locator().translator_cancel_current_edit);
			Thread.sleep(3000);
			assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(i)).getText().isEmpty();
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
		}
		Thread.sleep(2000);

		if (complete) {
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
			Thread.sleep(3000);
			if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
		    	}
		}

		if (back) {
			System.out.println("IN BACK-----");
			if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
		    	}
		}

		System.out.println("EOM trans_Ongoing  method()");
	}

	public void reviewer_onGoing_submission(String SubmissionName,String target, boolean complete, boolean back) throws Exception {

		System.out.println("INSIDE reviewer_onGoing_submission  method()");

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
			General.action().Click(ReviewerLocators.Locator().reviewer_selectSubmission_checkbox(SubmissionName,target));
		}
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
		Thread.sleep(9000);
		General.action().Click(TranslatorLocators.Locator().translator_Video_play);
		Thread.sleep(2000);

		Thread.sleep(2000);
		List<WebElement> listofIds1 = General.driver.findElements(ReviewerLocators.Locator().reviewer_toClaim_ListofallIds);
		Thread.sleep(1000);
		System.out.println("No of IDS--------" + listofIds1.size());

		for (int i = 1; i <= listofIds1.size(); i++) {
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i),review_target_text);
			Thread.sleep(1000);

			String MODIFIED_TARGET_SEGMENT_TEXT = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i)).getText();
			System.out.println("MODIFIED_TARGET_SEGMENT_TEXT:-"+ MODIFIED_TARGET_SEGMENT_TEXT);
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_cancel_current_edit);
			General.action().Click(ReviewerLocators.Locator().reviewer_cancel_current_edit);
			//TODO NOT NEEDED
////		Thread.sleep(1000);
//			General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
////			Thread.sleep(1000);
//			String Modified_Target_segment_after_cancel_current_edit = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i)).getText();
//			if (Modified_Target_segment_after_cancel_current_edit.isEmpty()) {
//				System.out.println("Input field is empty :-" + i);
//			}
//			Thread.sleep(2000);
//			assertion = Modified_Target_segment_after_cancel_current_edit.isEmpty();
//			if (assertion == false) {
//				report("f","Assertion failed while Modified_Target_segment_after_cancel_current_edit PastDuedate_Input_color");
//			}
			
			Thread.sleep(1000);
			assertion = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i)).getText().isEmpty();
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
		}
		Thread.sleep(2000);

		if (complete) {
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_Complete_Button);
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().reviewer_Complete_Button);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_CompleteDailoge_Button);
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().reviewer_CompleteDailoge_Button);
			Thread.sleep(3000);
			if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
		    	}
		}

		if (back) {
			System.out.println("IN BACK-----");
			if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
		    	}
		}

		System.out.println("EOM reviewer_onGoing_submission  method()");
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewerSearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
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