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

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_306372 {
	

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_306372"+CommonElements.BROWSER+"Q3";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
    String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed ="Completed";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_306372");
		dataSet.put("TL_test_case_description","Sub_306372: Go to subtitle # in Reviewer view");
		dataSet.put("TL_internal_testCase_ID", "306372");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			//PM login
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
				Review_GoToSubtitle(SubmissionName, targetlanguage_1,2,"2");
				Thread.sleep(2000);

			

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void Review_GoToSubtitle(String SubmissionName, String target,int subtitleNum, String subtitleNumber) throws Exception {

		System.out.println("INSIDE Review_GoToSubtitle  method()");

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
        Thread.sleep(5000);
		General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),Keys.chord(Keys.ALT, "g"));
		Thread.sleep(2000);
		
		 assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().Review_GoToSubtitleID_Menu, 5);
		 if (assertion == false) {
		  report("f","Assertion failed while verifying Go To Subtittle ID Menu");
		  }
		
	    General.action().type(ReviewerLocators.Locator().review_GoToSubtitle,subtitleNumber);
		Thread.sleep(2000);
        General.action().Click(ReviewerLocators.Locator().review_GoToSubtitle_Go_button);
		Thread.sleep(2000);
		
        assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().SubtitleRowId(subtitleNum), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying subtitle text area is active  after Search");
		}

		Thread.sleep(2000);
		General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),Keys.chord(Keys.ALT, "g"));
		Thread.sleep(1000);
        General.action().type(ReviewerLocators.Locator().review_GoToSubtitle, "abc");
		Thread.sleep(2000);

		boolean review_GoToSubtitle_Go_Button_alphabets = General.driver.findElement(ReviewerLocators.Locator().review_GoToSubtitle_Go_button).isDisplayed();
        System.out.println("Review_GoToSubtitle_Go_Button is disabled for alphabets values:- "+ review_GoToSubtitle_Go_Button_alphabets);
		Thread.sleep(2000);

		assertion = General.driver.findElement(ReviewerLocators.Locator().review_GoToSubtitle_Go_button).isDisplayed();
		if (assertion == false) {
			report("f","Assertion failed while verifying Review_GoToSubtitle_Go_Button values  after Search");
		}

		Thread.sleep(2000);
		General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),Keys.chord(Keys.ALT, "g"));
		Thread.sleep(1000);

		General.action().type(ReviewerLocators.Locator().review_GoToSubtitle, ".0");
		Thread.sleep(2000);

		boolean review_GoToSubtitle_Go_Button_decimal = General.driver.findElement(ReviewerLocators.Locator().review_GoToSubtitle_Go_button).isDisplayed();

		System.out.println("Review_GoToSubtitle_Go_button is disabled for decimal values:- "+ review_GoToSubtitle_Go_Button_decimal);
		Thread.sleep(2000);

		assertion = General.driver.findElement(ReviewerLocators.Locator().review_GoToSubtitle_Go_button).isDisplayed();
		if (assertion == false) {
			report("f","Assertion failed while verifying review_GoToSubtitle_Go_Button_decimal values  after Search");
		}
		General.action().Click(ReviewerLocators.Locator().review_GoToSubtitle_Close_button);
		Thread.sleep(2000);

	    General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),Keys.chord(Keys.ALT, "g"));
		Thread.sleep(1000);

		General.action().type(ReviewerLocators.Locator().review_GoToSubtitle, "7");
		Thread.sleep(2000);

		boolean review_GoToSubtitle_Go_Button_outOfrange_value = General.driver.findElement(ReviewerLocators.Locator().review_GoToSubtitle_Go_button).isDisplayed();

		System.out.println("Review_GoToSubtitle_Go_button is disabled for Out of range values:- "+ review_GoToSubtitle_Go_Button_outOfrange_value);
		Thread.sleep(2000);

		String review_GoToSubtitle_Go_Button_outOfrange_error_message = General.driver.findElement(ReviewerLocators.Locator().review_GoToSubtitle_message).getText();
		System.out.println("ERROR MESSAGE*********"+ review_GoToSubtitle_Go_Button_outOfrange_error_message);

		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_GoToSubtitle_message,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying review_GoToSubtitle_Go_Button_outOfrange_error_message  after Search");
		}
		Thread.sleep(2000);
          System.out.println("EOM Review_GoToSubtitle  method()");
	}

public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_GoToSubtitle_message,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Trans_GoToSubtitle_Go_Button_outOfrange_error_message  after Search");
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





