package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_306373 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_306373"+CommonElements.BROWSER+"M1";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String Input_value_of_TargetSegement_textarea;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_306373");
		dataSet.put("TL_test_case_description","Sub_306373: Keyboard Shortcuts");
		dataSet.put("TL_internal_testCase_ID", "306373");
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

			// trans login
			General.action().login(CommonElements.action().Translator_username,CommonElements.action(). password);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Trans_ongoing_keyboard_shortcuts(SubmissionName, targetlanguage_1);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void Trans_ongoing_keyboard_shortcuts(String SubmissionName,String target) throws Exception {

		System.out.println("INSIDE Trans_ongoing_keyboard_shortcuts  method()");

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(TranslatorLocators.Locator().check_Trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_edit_button);
		Thread.sleep(2000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_keyboard_shortcuts);
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_ongoing_keyboard_shortcuts,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Trans_ongoing_keyboard_shortcuts  after Search");
		}
		Thread.sleep(2000);
		
        // click through mouse on Keyboard Shortcuts icon
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_keyboard_shortcuts);
		Thread.sleep(2000);

		WebElement table_element = General.driver.findElement(TranslatorLocators.Locator().table_keyboard_shortcuts);
		List<WebElement> tr_collection = table_element.findElements(TranslatorLocators.Locator().table_keyboard_shortcuts_row);

		System.out.println("NUMBER OF ROWS IN THIS TABLE = "+ tr_collection.size());
		int row_num;
		row_num = 1;
		for (WebElement trElement : tr_collection) {
			System.out.println("row # " + row_num + " " + trElement.getText());
			row_num++;
		}

		General.action().Click(TranslatorLocators.Locator().table_keyboard_shortcuts_cancel);
		Thread.sleep(2000);
		
		
         // keyboard shortcuts option will be open by SHIFT + ?
		General.driver.findElement(TranslatorLocators.Locator().trans_ongoing_press_keyboard_shortcut_keys).sendKeys(Keys.SHIFT, "?");
		Thread.sleep(2000);

		System.out.println("NUMBER OF ROWS IN THIS TABLE AFTER PRESS SHIFT+? = "+ tr_collection.size());
		row_num = 1;
		for (WebElement trElement : tr_collection) {
			System.out.println("row # " + row_num + " " + trElement.getText());
			row_num++;
		}

		General.action().Click(TranslatorLocators.Locator().table_keyboard_shortcuts_cancel);
		Thread.sleep(2000);

		Thread.sleep(2000);
		List<WebElement> listofIds1 = General.driver.findElements(TranslatorLocators.Locator().Trans_toClaim_ListofallIds);
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
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(i),Keys.chord(Keys.ALT, "s"));
			Thread.sleep(1000);
			}

		General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(2),Keys.chord(Keys.ALT, Keys.PAGE_UP));
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_TargetSegement_textarea(2)  after Search");
		}

		General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(3),Keys.chord(Keys.ALT, Keys.PAGE_DOWN));
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(4), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_TargetSegement_textarea(1)  after Search");
		}

		General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),Keys.chord(Keys.ALT, "g"));
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_GoToSubtitle, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Trans_GoToSubtitle  after Search");
		}

		General.action().Click(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Close_button);
		Thread.sleep(2000);

		General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),Keys.chord(Keys.ALT, "h"));
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_Find_and_Replace, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Trans_Find_and_Replace  after Search");
		}

		General.action().Click(TranslatorLocators.Locator().Trans_Find_and_Replace_Close);
		Thread.sleep(2000);

		General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(3),Keys.chord(Keys.ALT, Keys.ENTER));
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(4), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying translator_TargetSegement_textarea(4) after Search");
		}
		
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
		Thread.sleep(2000);

		General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(4),Keys.chord(Keys.ALT, "x"));
		Thread.sleep(5000);

		Input_value_of_TargetSegement_textarea = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(4)).getText();
		System.out.println("Input_value_of_TargetSegement_textarea:-"+ Input_value_of_TargetSegement_textarea);
		Thread.sleep(2000);

		boolean Input_value = Input_value_of_TargetSegement_textarea.isEmpty();
		System.out.println("INPUT VALUE OF TARGET SEGMENT ROW IS EMPTY:-"+ Input_value);
		Thread.sleep(2000);

		assertion = Input_value_of_TargetSegement_textarea.isEmpty();
		if (assertion == false) {
			report("f","Assertion failed while verifying Input_value_of_TargetSegement_textarea is empty  after Search");
		}

	}

	public void assertion() throws Exception {
		assertion = Input_value_of_TargetSegement_textarea.isEmpty();
		if (assertion == false) {
			report("f","Assertion failed while verifying Input_value_of_TargetSegement_textarea is empty  after Search");
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
