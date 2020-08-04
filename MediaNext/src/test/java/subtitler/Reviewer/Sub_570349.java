package subtitler.Reviewer;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;

public class Sub_570349 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_570349"+CommonElements.BROWSER+"O1";
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
		General.action().startSystem("Sub_570349");
		dataSet.put("TL_test_case_description","Sub_570349 :Review: Quality checks");
		dataSet.put("TL_internal_testCase_ID", "570349");
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
		Thread.sleep(1000);

		
       // check rule violation for:-4-1.Character limitation violation 4-2.Reading speed violation
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)),"Jellyfish at the Monterey Aquarium extra");
		Thread.sleep(2000);

		String Line_count_color = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea_line(1)).getCssValue("color");
		System.out.println("Character limitation violation Color:-"+ Line_count_color);

		boolean color = Line_count_color.equalsIgnoreCase("rgba(255, 0, 0, 1)");
		System.out.println("Character limitation violation Color is RED:- "+ color);

		// 4-3.check rule violation for empty subtitles
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2)),"");
		Thread.sleep(2000);

		//4-5. check rule violation for copy to source "Source = modified target"
        General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
		Thread.sleep(2000);

		//4-4. check rule violation for Too many lines in a subtitle: 2 lines maximum
        General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4)),"Pro Tip: , \n" + "Turn off the, \n" + "camera flash!");
        Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().qualitycheck_number_of_issues);
		Thread.sleep(1000);

		String NumberOfIssues = General.driver.findElement(ReviewerLocators.Locator().qualitycheck_number_of_issues).getText();
		System.out.println("NUMBER OF RULE VIOLATION IS:-" + NumberOfIssues);

		String ruleViolation_messages = General.driver.findElement(ReviewerLocators.Locator().qualitycheck_rule_violation_mesaages).getText();
		System.out.println("RULE VIOLATION MESSAGES:-" + ruleViolation_messages);

		Thread.sleep(2000);

		// check rule violation for:- Bold tags count not consistent from Source to Modified Target
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)),"Jellyfish at the Monterey Aquarium");
		Thread.sleep(2000);
		General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)),Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().Text_Formatting_Bold);
		Thread.sleep(1000);

		// check rule violation for:-Italic tags count not consistent from Source to Modified Target
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
		Thread.sleep(2000);
		General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2)),Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().Text_Formatting_Italic);
		Thread.sleep(1000);

		// check rule violation for:-Underlined tags count not consistent from Source to Modified Target
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
		Thread.sleep(2000);
		General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3)),Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().Text_Formatting_Underline);
		Thread.sleep(1000);

		Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().qualitycheck_number_of_issues);
		Thread.sleep(1000);

		String NumberOfIssues1 = General.driver.findElement(ReviewerLocators.Locator().qualitycheck_number_of_issues).getText();
		System.out.println("NUMBER OF RULE VIOLATION IS:-" + NumberOfIssues1);
		Thread.sleep(1000);
		String ruleViolation_messages1 = General.driver.findElement(ReviewerLocators.Locator().qualitycheck_rule_violation_mesaages).getText();
		System.out.println("RULE VIOLATION MESSAGES:-"+ ruleViolation_messages1);
		Thread.sleep(2000);

	  assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea_line(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying bold target segment is active");
			Thread.sleep(2000);
		}

		System.out.println("EOM review_onGoing_qualityCheck  method()");
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1), 5);
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



