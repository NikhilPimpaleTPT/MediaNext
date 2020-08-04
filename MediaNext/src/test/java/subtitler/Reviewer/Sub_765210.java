package subtitler.Reviewer;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

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

public class Sub_765210 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_765210"+CommonElements.BROWSER+"M1";
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
	String Input_value_of_MTargetSegement_textarea;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_765210");
		dataSet.put("TL_test_case_description","Sub_765210: Review Screen > Keyboard Shortcuts > Alt+enter");
		dataSet.put("TL_internal_testCase_ID", "765210");
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
			  reviewer_ongoing_keyboard_shortcuts(SubmissionName, targetlanguage_1);
			  Thread.sleep(2000);


		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void reviewer_ongoing_keyboard_shortcuts(String SubmissionName,String target) throws Exception {

		System.out.println("INSIDE reviewer_ongoing_keyboard_shortcuts  method()");

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

		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().review_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_keyboard_shortcuts);
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_ongoing_keyboard_shortcuts,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying review_ongoing_keyboard_shortcuts  after Search");
		}
		Thread.sleep(2000);
		
        // click through mouse on Keyboard Shortcuts icon
		General.action().Click(ReviewerLocators.Locator().review_ongoing_keyboard_shortcuts);
		Thread.sleep(2000);

		WebElement table_element = General.driver.findElement(ReviewerLocators.Locator().table_keyboard_shortcuts);
		List<WebElement> tr_collection = table_element.findElements(ReviewerLocators.Locator().table_keyboard_shortcuts_row);

		System.out.println("NUMBER OF ROWS IN THIS TABLE = "+ tr_collection.size());
		
		int row_num;
		row_num = 1;
	
		for (WebElement trElement : tr_collection) {
			System.out.println("row # " + row_num + " " + trElement.getText());
			row_num++;
		Thread.sleep(1000);
		}
		
		String keyborad_shortcut = General.driver.findElement(ReviewerLocators.Locator().keyborad_shortcut_save_and_go_to_next).getText();
		System.out.println("VALUE OF AIT+ENTER SHORTCUT KEY IS:-" +keyborad_shortcut);

		assertion = Verify.action().verifyElementValuePresent(ReviewerLocators.Locator().keyborad_shortcut_save_and_go_to_next, keyborad_shortcut, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Trans_ongoing_keyboard_shortcuts  after Search");
		}
		
		
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().table_keyboard_shortcuts_cancel);
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
			General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i),Keys.chord(Keys.ALT, "s"));
			Thread.sleep(1000);
			}


		General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3),Keys.chord(Keys.ALT, Keys.ENTER));
		Thread.sleep(1000);
		
		Input_value_of_MTargetSegement_textarea = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3)).getText();
			System.out.println("Input_value_of_TargetSegement_textarea:-"+ Input_value_of_MTargetSegement_textarea);
			Thread.sleep(2000);

		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_TargetSegement_textarea(4)  after Search");
		}

	    General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4),Keys.chord(Keys.ALT, Keys.ENTER));
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying reviewer_ModifiedTargetSegement_textarea(4)  after Search");
		}
		
		System.out.println("EOM reviewer_ongoing_keyboard_shortcuts  method()");
	}
	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying reviewer_ModifiedTargetSegement_textarea(4)  after Search");
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






