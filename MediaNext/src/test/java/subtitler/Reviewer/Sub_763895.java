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

public class Sub_763895 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_763895"+CommonElements.BROWSER+"P";
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
		General.action().startSystem("Sub_763895");
		dataSet.put("TL_test_case_description","Sub_763895: Show Align on Top indicator on grid: Review Screen");
		dataSet.put("TL_internal_testCase_ID", "763895");
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
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_AN_TAG_SRT_FOLDER);
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
				reviewer_onGoing_Align_on_top(SubmissionName, targetlanguage_1);
				Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void reviewer_onGoing_Align_on_top(String SubmissionName,String target) throws Exception {

		System.out.println("INSIDE reviewer_onGoing_Align_on_top  method()");

		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		Translator.action().Click(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		Translator.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		Translator.action().type(ReviewerLocators.Locator().review_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");

			Translator.action().Click(ReviewerLocators.Locator().reviewer_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		Translator.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
		Thread.sleep(2000);

		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().modifiedtarget_AN_tag_upward(1));
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().modifiedtarget_AN_tag_upward(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying modifiedtarget_AN_tag_upward is upward  after Search");
		}

		Thread.sleep(1000);
		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
	
       assertion = Verify.action().verifyElementNotPresent(ReviewerLocators.Locator().modifiedtarget_AN_tag_upward(3), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying modifiedtarget_AN_tag_upward is not present after Search");
		}
        Thread.sleep(1000);
        
		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().modifiedtarget_AN_tag_vertical_align_top);
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().modifiedtarget_AN_tag_vertical_align_top,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying modifiedtarget_AN_tag_vertical_align_top is present after Search");
		}
		Thread.sleep(1000);
		
		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().modifiedtarget_AN_tag_vertical_align_bottom);
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().modifiedtarget_AN_tag_vertical_align_bottom,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying modifiedtarget_AN_tag_vertical_align_bottom is present after Search");
		}

		Thread.sleep(1000);
		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		Translator.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
	    Translator.action().Click(ReviewerLocators.Locator().modifiedtarget_AN_tag_vertical_align_top);
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().modifiedtarget_AN_tag_upward(3), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying modifiedtarget_AN_tag is upward  after Search");
		}
		Thread.sleep(1000);
		Translator.action().Click(ReviewerLocators.Locator().modifiedtarget_AN_tag_vertical_align_bottom);
		Thread.sleep(2000);
		System.out.println("EOM reviewer_onGoing_Align_on_top ");
		

	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementNotPresent(ReviewerLocators.Locator().modifiedtarget_AN_tag_upward(3), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying modifiedtarget_AN_tag is upward  after Search");
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



