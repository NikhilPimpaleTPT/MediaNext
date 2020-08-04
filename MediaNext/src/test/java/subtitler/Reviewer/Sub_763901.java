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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_763901 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_763901"+CommonElements.BROWSER+"K3";
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
	String Modifiedtarget_segment_text_after_escape;
	String Modifiedtarget_segment_text_before_modify;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_763901");
		dataSet.put("TL_test_case_description","Sub_763901: Clear the text of a segment (Alt+x): Review Screen");
		dataSet.put("TL_internal_testCase_ID", "763901");
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
				reviewer_onGoing_keyboard_shortcut(SubmissionName, targetlanguage_1);
				Thread.sleep(2000);
			
			} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void reviewer_onGoing_keyboard_shortcut(String SubmissionName,String target) throws Exception {

		System.out.println("INSIDE reviewer_onGoing_keyboard_shortcut  method()");

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
		Translator.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		Translator.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		Translator.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),"Test target for clear text by alt+x");
		Thread.sleep(3000);

		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_delete_icon);
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_delete_icon, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying trans_ongoing_delete_icon  after Search");
		}
		Thread.sleep(1000);
		//TODO FUNCTIONALITY CHANGE FROM ITERATION 2.2.0 RC1
//		Actions builder = new Actions(General.driver);
//		Thread.sleep(1000);
//		WebElement delete_icon_tooltip = General.driver.findElement(ReviewerLocators.Locator().reviewer_delete_icon_tooltip_message);
//		Thread.sleep(1000);
//		builder.moveToElement(delete_icon_tooltip).perform();
//		Thread.sleep(1000);
//		String tooltip_message = delete_icon_tooltip.getAttribute("ng-reflect-message");
//		Thread.sleep(1000);
//		System.out.println("TOOL TIP MESSAGE FOR DELETE ICON IS:- "+ tooltip_message);
//		Thread.sleep(3000);
		

		Translator.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);

		Translator.action().Click(ReviewerLocators.Locator().reviewer_delete_icon);
		Thread.sleep(1000);

		String first_target_segment_after_delete = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)).getText();
		System.out.println("first_target_segment_after_delete is empty :-"+ first_target_segment_after_delete.isEmpty());
        General.action().type_withKEYS((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)), Keys.ESCAPE, false);
		Thread.sleep(1000);

		String first_target_segment_after_escape = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)).getText();
		System.out.println("first_target_segment_after_escape is empty :-"+ first_target_segment_after_escape.isEmpty());

		System.out.println("**********END OF SCENARIO ONE***********");

		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		Translator.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		Translator.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		Translator.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),"Test target for clear text by alt+x");
		Thread.sleep(3000);

		Modifiedtarget_segment_text_before_modify = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)).getText().trim();
		System.out.println("target_segment_text_before_modify*********** :-"+ Modifiedtarget_segment_text_before_modify+"*************");

		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(1000);
		Translator.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(1000);

		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		Translator.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		Translator.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),"  Modified");
		Thread.sleep(3000);

		String target_segment_text_after_modify = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)).getText();
		System.out.println("target_segment_text_after_modify************ :-"+ target_segment_text_after_modify);
		
		Translator.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(1000);

		Translator.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),Keys.chord(Keys.ALT, "x"));
		Thread.sleep(1000);
        General.action().type_withKEYS((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)), Keys.ESCAPE, false);
		Thread.sleep(1000);

		Modifiedtarget_segment_text_after_escape = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)).getText().trim();
		System.out.println("target_segment_text_after_escape************* :-"+ Modifiedtarget_segment_text_after_escape+"********");

		boolean result = Modifiedtarget_segment_text_before_modify.trim().equalsIgnoreCase(Modifiedtarget_segment_text_after_escape.trim());
		System.out.println("The previous data entered for the first time(before modification) should be same after escape key******:-"+ result);

		System.out.println("**********END OF SCENARIO TWO***********");

		System.out.println("EOM translate_onGoing_keyboard_shortcut  method()");
	}

	public void assertion() throws Exception {
		assertion = Modifiedtarget_segment_text_before_modify.equalsIgnoreCase(Modifiedtarget_segment_text_after_escape);
		if (assertion == false) {
			report("f","Assertion failed while verifying The previous data entered for the first time(before modification) should get retrieved after Search");
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



