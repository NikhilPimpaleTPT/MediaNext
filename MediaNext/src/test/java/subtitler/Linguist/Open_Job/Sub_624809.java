package subtitler.Linguist.Open_Job;

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
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

public class Sub_624809 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_624809"+CommonElements.BROWSER+"M1";
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
	String Time_SMPTE;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_624809");
		dataSet.put("TL_test_case_description","Sub_624809: Display time code in different formats");
		dataSet.put("TL_internal_testCase_ID", "624809");
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
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Trans_ongoing_Time_Formats(SubmissionName, targetlanguage_1);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void Trans_ongoing_Time_Formats(String SubmissionName, String target)throws Exception {

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

		for (int i = 1; i <= 1; i++) {
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);

			String Time_format = General.driver.findElement(TranslatorLocators.Locator().time_format).getText();
			System.out.println("SMPTE FORMAT############ :-" + Time_format);

			assertion = Verify.action().verifyElementValuePresent(TranslatorLocators.Locator().time_format, Time_format, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SMPTE FORMAT  after Search");
			}
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(i),Keys.chord(Keys.ALT, "e"));
			Thread.sleep(1000);
			String Frame_Number = General.driver.findElement(TranslatorLocators.Locator().time_format).getText();
			System.out.println("FRAME NUMBER############ :-" + Frame_Number);

			assertion = Verify.action().verifyElementValuePresent(TranslatorLocators.Locator().time_format, Frame_Number, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Frame_Number  after Search");
			}

			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(i),Keys.chord(Keys.ALT, "e"));
			Thread.sleep(1000);
			String Time_forMiliSec = General.driver.findElement(TranslatorLocators.Locator().time_format).getText();
			System.out.println("TIME FOR MILISECONDS############ :-"+ Time_forMiliSec);

			assertion = Verify.action().verifyElementValuePresent(TranslatorLocators.Locator().time_format, Time_forMiliSec,5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Frame_Number  after Search");
			}
			
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(i),Keys.chord(Keys.ALT, "e"));
			Thread.sleep(1000);
			Time_SMPTE = General.driver.findElement(TranslatorLocators.Locator().time_format).getText();
			System.out.println("TIME FOR SMPTE############ :-"+ Time_SMPTE);

			assertion = Verify.action().verifyElementValuePresent(TranslatorLocators.Locator().time_format, Time_SMPTE,5);
			if (assertion == false) {
				report("f","Assertion failed while verifying TIME FOR SMPTE  after Search");
			}
			
		}
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementValuePresent(TranslatorLocators.Locator().time_format, Time_SMPTE, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying TIME FOR SMPTE  after Search");
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
