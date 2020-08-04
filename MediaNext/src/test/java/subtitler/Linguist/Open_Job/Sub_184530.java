package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_184530 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_184530"+CommonElements.BROWSER+"M1";
	String targetlanguage_1 = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_184530");
		dataSet.put("TL_test_case_description", "Sub_184530:To claim job");
		dataSet.put("TL_internal_testCase_ID", "184530");
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
       	   Trans_ToClaim(SubmissionName);
		   Thread.sleep(2000);
		   PM_user.action().Navigate(menu_ongoing);
		   Thread.sleep(2000);
		   PM_user.action().SearchSubmisson(SubmissionName);
		   Thread.sleep(2000); 
		   
     } catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void Trans_ToClaim(String SubmissionName) throws Exception {

		System.out.println("INSIDE Trans_ToClaim  method()");

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_search_input,SubmissionName);
		Thread.sleep(1000);

		if (Verify.action().verifyElementNotPresent(TranslatorLocators.Locator().Trans_toClaim_checkbox_checked,5)) {
			System.out.println("INSIDE IF--------");
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_toClaim_select_header_checkbox);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().Trans_toClaim_select_header_checkbox);
			Thread.sleep(1000);
			System.out.println("CLICK DONE------");
		}
		General.action().Click(TranslatorLocators.Locator().Trans_clamSubmission_icon);
		Thread.sleep(3000);

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_claimAlert_cancel_button);
		Thread.sleep(1000);

		// assertion = CANCEL
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_claimAlert_cancel_button,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Trans_claimAlert_cancel_button after Search");
		}

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_claimAlert_claim_button);
		Thread.sleep(1000);

		// assertion= CLAIM
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_claimAlert_claim_button,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Trans_claimAlert_claim_button after Search");
		}

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_claimAlert_claim_button);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_claimAlert_claim_button);
		Thread.sleep(2000);

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ToClaim_message());
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_ToClaim_message(), 5);
		if (assertion == false)
			report("f","Assertion failed while verifying message for Trans_ToClaim_message");

		System.out.println("EOM Trans_ToClaim()");

	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f",	"Assertion failed while verifying SubmissionName after Search");
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



