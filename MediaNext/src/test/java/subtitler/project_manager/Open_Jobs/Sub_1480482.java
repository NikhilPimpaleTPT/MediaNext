package subtitler.project_manager.Open_Jobs;

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

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This testcase verifies if long subtitles with bold and italic format are displaying properly on video screen
 *Preconditions :Create a submission with attached test files and claimed it in translator login.
 */ 

public class Sub_1480482 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1480482"+CommonElements.BROWSER+"B1";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String longTargetSegment="Verify that Long subtitle with bold and italic format displaying properly  on video screen ";
	
	Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1480482");
		dataSet.put("TL_test_case_description", "SUB-1480482:Long subtitle with bold and italic format display on video screen.");
		dataSet.put("TL_internal_testCase_ID", "1480482");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			//Create Submission
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_BOLDANDITALIC_FOLDER);
			PM_user.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, Targetlanguage);
			Thread.sleep(2000);
		
			//SEARCH SUBMISSION AND CHECK
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
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
			Trans_edit_targetSegments(SubmissionName,"trans");
			Thread.sleep(1000);
			
			
			
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void Trans_edit_targetSegments(String SubmissionName,String step) throws Exception {

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
		Thread.sleep(2000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),longTargetSegment);
		Thread.sleep(1000);
		
		General.action().type((TranslatorLocators.Locator().translator_TargetSegement_textarea(1)),Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(2000);
		
		General.action().Click(TranslatorLocators.Locator().Text_Formatting_Bold);
		Thread.sleep(1000);
		
		General.action().type((TranslatorLocators.Locator().translator_TargetSegement_textarea(1)),Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().Text_Formatting_Italic);
		Thread.sleep(5000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		Thread.sleep(1000);
		
		
		
		
	System.out.println("EOM Trans_cancel_current_edit_transScreen  method()");

	}


	public void assertion() throws Exception {
		//TODO This assertion is blocked (Needs to verify long subtitles with bold and italic format are displaying properly on video screen.)
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying job step");
		}else {
			report("b", "All Assertion blocked.");
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