package subtitler.Linguist.Open_Job;

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
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

public class Sub_54771 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_54771"+CommonElements.BROWSER+"Q";
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
	String review_target_text = "Testing modified_target segment";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_54771");
		dataSet.put("TL_test_case_description", "Sub_54771:Cancel current edit");
		dataSet.put("TL_internal_testCase_ID", "54771");
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
			Trans_cancel_current_edit_transScreen(SubmissionName, "trans");
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_completed);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void Trans_cancel_current_edit_transScreen(String SubmissionName,String step) throws Exception {

		System.out.println("INSIDE Trans_cancel_current_edit_transScreen  method()");

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(TranslatorLocators.Locator().check_trans_Target_Lang(SubmissionName, step))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName, step));
		}
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);

		Thread.sleep(4000);
		List<WebElement> listofIds1 = General.driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
		Thread.sleep(3000);
		System.out.println("No of IDS--------" + listofIds1.size());
		Thread.sleep(2000);
		for (int i = 1; i <= listofIds1.size(); i++) {
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i),trans_target_text);
			Thread.sleep(1000);

			String TARGET_SEGMENT_TEXT = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(i)).getText();
			System.out.println("TARGET SEGMENT INPUT TEXT:-"+ TARGET_SEGMENT_TEXT);

			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_cancel_current_edit);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().Trans_cancel_current_edit);
			Thread.sleep(1000);

			String Target_segment_after_cancel_current_edit = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(i)).getText();
			if (Target_segment_after_cancel_current_edit.isEmpty()) {
				System.out.println("Input field is empty :-" + i);
			}
			Thread.sleep(1000);
			assertion = Target_segment_after_cancel_current_edit.isEmpty();
			if (assertion == false) {
				report("f","Assertion failed while verifying Target_segment_after_cancel_current_edit");
			}
		}
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
		Thread.sleep(1000);
		
		if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
	    	}
	
		
	System.out.println("EOM Trans_cancel_current_edit_transScreen  method()");

	}


	public void assertion() throws Exception {
		// This assertion will check that sub is present
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName,"trans"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying check_submission_status after Search");
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
