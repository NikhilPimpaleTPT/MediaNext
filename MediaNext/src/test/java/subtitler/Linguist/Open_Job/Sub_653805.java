package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

public class Sub_653805 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_653805"+CommonElements.BROWSER+"M2";
	String SubmissionName_transc = "SUB_653805_transc"+CommonElements.BROWSER+"M2";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String WorkflowName_tranc = "transc_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_653805");
		dataSet.put("TL_test_case_description","Sub_653805: Add menu in Transcription and Translation/Review");
		dataSet.put("TL_internal_testCase_ID", "653805");
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
			// sub for transcription
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName_tranc, "trans",TranslatorGroupName, "", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName_transc,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName_transc);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_transc), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			General.action().logoutMethod();
			
			
			//trans login
			//Go to subtitle # in translation view:- Normal Submission
	         General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
	         Thread.sleep(1000);
	         Translator.action().Navigate(menu_to_claim);
	         Thread.sleep(1000);
	         Translator.action().trans_ToClaim(SubmissionName);
	         Thread.sleep(1000);
	         Translator.action().Navigate(menu_ongoing);
	         Thread.sleep(1000);
	         Trans_Ongoing_3dot_menu_options(SubmissionName, targetlanguage_1);
			 Thread.sleep(2000);

			//Go to subtitle # in translation view:- Transcription Submission
			 Thread.sleep(2000);
			 Translator.action().Navigate(menu_to_claim);
	         Thread.sleep(1000);
	         Translator.action().trans_ToClaim(SubmissionName_transc);
	         Thread.sleep(1000);
	         Translator.action().Navigate(menu_ongoing);
	         Thread.sleep(1000);
	         Trans_Ongoing_3dot_menu_options_transc(SubmissionName_transc, targetlanguage_1);
			 Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void Trans_Ongoing_3dot_menu_options(String SubmissionName, String target) throws Exception {

		System.out.println("INSIDE Trans_Ongoing_3dot_menu_options  method()");

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
			General.action().Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName,target));
		}
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_edit_button);
        Thread.sleep(2000);
        General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
        Thread.sleep(1000);
        General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
        Thread.sleep(1000);
        
        assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_Ongoing_Import_file, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Import File after Search");
		}
		
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_find_and_replace);
        Thread.sleep(1000);
        General.action().Click(TranslatorLocators.Locator().Trans_ongoing_find_and_replace);
		Thread.sleep(1000);
        
        assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_Find_and_Replace, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Find and Replace after Search");
		}
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_Find_and_Replace_Close);
		Thread.sleep(1000);
		
		Thread.sleep(1000);
        General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_keyboard_shortcuts);
        Thread.sleep(1000);
        General.action().Click(TranslatorLocators.Locator().Trans_ongoing_keyboard_shortcuts);
		Thread.sleep(1000);
        
        assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().table_keyboard_shortcuts_cancel, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Keyboard Shortcuts after Search");
		}
		General.action().Click(TranslatorLocators.Locator().table_keyboard_shortcuts_cancel);
	
		Thread.sleep(3000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
		General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);

		System.out.println("EOM Trans_Ongoing_3dot_menu_options  method()");
	}

	public void Trans_Ongoing_3dot_menu_options_transc(String SubmissionName, String target) throws Exception {

		System.out.println("INSIDE Trans_Ongoing_3dot_menu_options_transc  method()");

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
			General.action().Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName,target));
		}
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_edit_button);
        Thread.sleep(2000);
        General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
        Thread.sleep(1000);
        General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
        Thread.sleep(1000);
        
        assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_Ongoing_Import_file, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Import File after Search");
		}
		
		Thread.sleep(1000);
		
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_find_and_replace);
        Thread.sleep(1000);
        General.action().Click(TranslatorLocators.Locator().Trans_ongoing_find_and_replace);
		Thread.sleep(1000);
        
        assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_Find_and_Replace, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Find and Replace after Search");
		}
		General.action().Click(TranslatorLocators.Locator().Trans_Find_and_Replace_Close);
		Thread.sleep(1000);
		
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_keyboard_shortcuts);
        Thread.sleep(1000);
        General.action().Click(TranslatorLocators.Locator().Trans_ongoing_keyboard_shortcuts);
		Thread.sleep(1000);
        
        assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().table_keyboard_shortcuts_cancel, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Keyboard Shortcuts after Search");
		}
		General.action().Click(TranslatorLocators.Locator().table_keyboard_shortcuts_cancel);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(3000);
	
        System.out.println("EOM Trans_Ongoing_3dot_menu_options_transc  method()");
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_ongoing_keyboard_shortcuts,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Trans_ongoing_keyboard_shortcuts  after Search");
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





