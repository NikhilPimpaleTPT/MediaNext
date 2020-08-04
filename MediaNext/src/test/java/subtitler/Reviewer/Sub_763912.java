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

public class Sub_763912 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_763912"+CommonElements.BROWSER+"H1";
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
		General.action().startSystem("Sub_763912");
		dataSet.put("TL_test_case_description","Sub_763912: Reading Speed Color code is not intuitive enough: Reviewer view.");
		dataSet.put("TL_internal_testCase_ID", "763912");
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
			// TODO NEW IMPL OF SUBMISSION CREATION SO COMMENTED
//			CreateSubmisson_Step1("17", "75", "80", "120");
			CreateSubmisson_Step1("3", "20","80", "3", "30");
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
			  reviewer_onGoing_ReadingSpeed_color_check(SubmissionName, targetlanguage_1);
			  Thread.sleep(2000);

			

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void CreateSubmisson_Step1(String MinreadingSpeed,String MaxreadingSpeed,String MaxCharperLine,String MinDuration,String MaxDuration) throws Exception {
		System.out.println("INSIDE method CreateSubmisson_Step1()\n");
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		Thread.sleep(1000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		Thread.sleep(1000);
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
		Thread.sleep(1000);
		Translator.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Minreadingspeed);
		Thread.sleep(1000);
		Translator.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Minreadingspeed,MinreadingSpeed);
		Thread.sleep(1000);

		Translator.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed);
		Thread.sleep(1000);
		Translator.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed,MaxreadingSpeed);
		Thread.sleep(1000);


		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
		Thread.sleep(1000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,MaxCharperLine);
		Thread.sleep(1000);

		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
		Thread.sleep(1000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,MinDuration);
		Thread.sleep(1000);

		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
		Thread.sleep(1000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,MaxDuration);
		Thread.sleep(1000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		Thread.sleep(1000);
		
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		Thread.sleep(1000);
		
		//TODO PD INTEGRATION WILL BE IMPL FROM SUB1.13.0
//		PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
//		Thread.sleep(1000);
		System.out.println("EOM CreateSubmisson_Step1()\n");

	}

	public void reviewer_onGoing_ReadingSpeed_color_check(String SubmissionName, String target) throws Exception {

		System.out.println("INSIDE reviewer_onGoing_ReadingSpeed_color_check  method()");

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

		Thread.sleep(5000);
		// Check Target Segment for Minimum Reading Speed.
		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(2000);
		Translator.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(2000);
		Translator.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(2000);
		Translator.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),"Test");
		Thread.sleep(3000);

		String Min_ReadingSpeed_color = General.driver.findElement(ReviewerLocators.Locator().review_readingSpeed_value(1)).getCssValue("color");
		System.out.println("Min_ReadingSpeed_color######### :-"+ Min_ReadingSpeed_color);
		Thread.sleep(2000);
		boolean Min_ReadingSpeed_color_result = Min_ReadingSpeed_color.equalsIgnoreCase("rgba(128, 128, 128, 1)");
		System.out.println("COLOR FOR MIN READING SPEED IS GREY:-"+ Min_ReadingSpeed_color_result);
		Thread.sleep(2000);
		
		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		Thread.sleep(2000);
		Translator.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		Thread.sleep(2000);

		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().qualitycheck_rule_violation_message_for_Min_RS, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying qualitycheck_rule_violation_message_for_Min_RS  after Search");
		}
		
		
      // Check Target Segment for Maximum Reading Speed.
		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(2000);
		Translator.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(2000);
		Translator.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(2000);
		Translator.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2),"Test target segment for maximum reading Speed Test target segment for maximum reading Speed Test target segment for maximum reading Speed");
		Thread.sleep(3000);

		String Max_ReadingSpeed_color = General.driver.findElement(ReviewerLocators.Locator().review_readingSpeed_value(2)).getCssValue("color");
		System.out.println("Max_ReadingSpeed_color######### :-"+ Max_ReadingSpeed_color);
		Thread.sleep(2000);
		boolean Max_ReadingSpeed_color_result = Max_ReadingSpeed_color.equalsIgnoreCase("rgba(255, 79, 100, 1)");
		System.out.println("COLOR FOR MAX READING SPEED IS RED:-"+ Max_ReadingSpeed_color_result);
		Thread.sleep(2000);
		
		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		Thread.sleep(2000);
		Translator.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		Thread.sleep(2000);

		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().qualitycheck_rule_violation_message_for_Max_RS, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying qualitycheck_rule_violation_message_for_Max_RS  after Search");
		}

		// Check Target Segment for Range between minimum to maximum
		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(2000);
		Translator.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(2000);
		Translator.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(2000);
		Translator.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3),"target segment for range between minimum to maximum RS.");
		Thread.sleep(3000);

		String ReadingSpeed_color_between_range = General.driver.findElement(ReviewerLocators.Locator().review_readingSpeed_value(3)).getCssValue("color");
		System.out.println("ReadingSpeed_color_between_range######### :-"+ ReadingSpeed_color_between_range);
		Thread.sleep(2000);
		boolean ReadingSpeed_color_between_range_result = ReadingSpeed_color_between_range.equalsIgnoreCase("rgba(255, 255, 255, 1)");
		System.out.println("COLOR FOR READING SPEED IS WHITE FOR RANGE BETWEEN MIN TO MAX:-"+ ReadingSpeed_color_between_range_result);
		Thread.sleep(2000);

		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		Thread.sleep(2000);
		Translator.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		Thread.sleep(2000);

		String Quality_check_issues1 = General.driver.findElement(ReviewerLocators.Locator().qualitycheck_number_of_issues).getText();
		System.out.println("NUMBER OF QUALITY CHECK ISSUES:- "+ Quality_check_issues1);

		String Quality_check_issues_messages1 = General.driver.findElement(ReviewerLocators.Locator().qualitycheck_rule_violation_mesaages).getText();
		System.out.println("QUALITY CHECK RULE VIOLATION MESSAGES FOR READING SPEED:-"+ Quality_check_issues_messages1);
		Thread.sleep(5000);

		assertion = Verify.action().verifyElementNotPresent(ReviewerLocators.Locator().qualitycheck_rule_violation_mesaages_for_segment(3), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying qualitycheck_norule_violation_mesaage  after Search");
		}

		System.out.println("EOM reviewer_onGoing_ReadingSpeed_color_check()");

	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementNotPresent(ReviewerLocators.Locator().qualitycheck_rule_violation_mesaages_for_segment(3), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying qualitycheck_norule_violation_mesaage  after Search");
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



