package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
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

public class Sub_277144 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_277144"+CommonElements.BROWSER+"A24";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_AllJobs = "Jobs";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_277144");
		dataSet.put("TL_test_case_description","Sub_277144 :Quality Checks: Check the list of rules to validate for each translation segment");
		dataSet.put("TL_internal_testCase_ID", "277144");
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
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_BOLD_FOLDER);
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
			trans_onGoing_qualityCheck(SubmissionName, targetlanguage_1);
			Thread.sleep(1000);
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}

	public void trans_onGoing_qualityCheck(String SubmissionName, String target)throws Exception {

		System.out.println("INSIDE trans_onGoing_qualityCheck  method()");

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
		Thread.sleep(1000);

		// By default, after opening a translation, the quality check is not automatically launched.
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().qualitycheck_message_before_run);
		Thread.sleep(1000);

		String qualitycheck_before_run = General.driver.findElement(TranslatorLocators.Locator().qualitycheck_message_before_run).getText();
		System.out.println("QUALITY CHECK MESSAGE BEFORE RUN:-"+ qualitycheck_before_run);

		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().qualitycheck_message_before_run,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying qualitycheck_message_before_run");
			Thread.sleep(2000);
		}

		Thread.sleep(2000);
		List<WebElement> listofIds1 = General.driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
		Thread.sleep(1000);
		System.out.println("No of IDS--------" + listofIds1.size());

		// check for no rule violation
		for (int i = 1; i <= listofIds1.size(); i++) {
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().trans_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().trans_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().trans_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().type((TranslatorLocators.Locator().trans_TargetSegement_textarea(i)),"Test Quality check rule violation Test Quality check rule violation Test Quality check rule violationTest Quality check rule violation Test Quality check rule violation");
			Thread.sleep(2000);
		}
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_qualitycheck_Run_button);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().qualitycheck_norule_violation_mesaage);
		Thread.sleep(1000);
		
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_qualityChecks_rules("1","There are not enough frames between this segment and the next one."), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying quality check rules");
		}

		String qualitycheck_norule_violation = General.driver.findElement(TranslatorLocators.Locator().qualitycheck_norule_violation_mesaage).getText();
		System.out.println("QUALITY CHECK MESSAGE:-"+ qualitycheck_norule_violation);

		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().qualitycheck_norule_violation_mesaage,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying message There is no rule violation");
			Thread.sleep(2000);
		}

		// check rule violation for:- 1.Character limitation violation 2.Reading speed violation
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().trans_TargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().trans_TargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().trans_TargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().type((TranslatorLocators.Locator().trans_TargetSegement_textarea(1)),"Jellyfish at the Monterey Aquarium extra");
		Thread.sleep(2000);

		String Line_count_color = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea_line(1)).getCssValue("color");
		System.out.println("Character limitation violation Color:-"+ Line_count_color);

		boolean color = Line_count_color.equalsIgnoreCase("rgba(255, 0, 0, 1)");
		System.out.println("Character limitation violation Color is RED:- "+ color);

		// check rule violation for empty subtitles
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().trans_TargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().trans_TargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().trans_TargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().type((TranslatorLocators.Locator().trans_TargetSegement_textarea(2)),"");
		Thread.sleep(2000);

		// check rule violation for copy to source "Source = target"
        General.action().waitforelemenetpresent(TranslatorLocators.Locator().trans_TargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().trans_TargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().trans_TargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
		Thread.sleep(2000);

		// check rule violation for Too many lines in a subtitle: 2 lines maximum
        General.action().waitforelemenetpresent(TranslatorLocators.Locator().trans_TargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().trans_TargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().trans_TargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().type((TranslatorLocators.Locator().trans_TargetSegement_textarea(4)),"Pro Tip: , \n" + "Turn off the, \n" + "camera flash!");
        Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_qualitycheck_Run_button);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().qualitycheck_number_of_issues);
		Thread.sleep(1000);

		String NumberOfIssues = General.driver.findElement(TranslatorLocators.Locator().qualitycheck_number_of_issues).getText();
		System.out.println("NUMBER OF RULE VIOLATION IS:-" + NumberOfIssues);

		String ruleViolation_messages = General.driver.findElement(TranslatorLocators.Locator().qualitycheck_rule_violation_mesaages).getText();
		System.out.println("RULE VIOLATION MESSAGES:-" + ruleViolation_messages);

		Thread.sleep(2000);
		
      
      	// check rule violation for:- Bold tags count not consistent from Source to Modified Target
        General.action().waitforelemenetpresent(TranslatorLocators.Locator().trans_TargetSegement_textarea(1));
        Thread.sleep(1000);
        General.action().Click(TranslatorLocators.Locator().trans_TargetSegement_textarea(1));
        Thread.sleep(1000);
        General.action().ClearInputvalues(TranslatorLocators.Locator().trans_TargetSegement_textarea(1));
        Thread.sleep(1000);
        General.action().type((TranslatorLocators.Locator().trans_TargetSegement_textarea(1)),"Jellyfish at the Monterey Aquarium");
        Thread.sleep(2000);
		
	  //TODO This  Code is Not needed as per
	  //Verify that application is showing tag rule violation in quality check if source and target segment have different tag count.
	  //For example - Source text is in bold format but target text are in bold format.
      //  General.action().type((TranslatorLocators.Locator().trans_TargetSegement_textarea(1)),Keys.chord(Keys.CONTROL, "a"));
      //  Thread.sleep(2000);
      //  General.action().Click(TranslatorLocators.Locator().Text_Formatting_Bold);
      //  Thread.sleep(1000);
      //
		
		
		// check rule violation for:-Italic tags count not consistent from Source to Modified Target
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().trans_TargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().trans_TargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().trans_TargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
		Thread.sleep(2000);
		General.action().type((TranslatorLocators.Locator().trans_TargetSegement_textarea(2)),Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().Text_Formatting_Italic);
		Thread.sleep(1000);

		// check rule violation for:-Underlined tags count not consistent from Source to Modified Target
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().trans_TargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().trans_TargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().trans_TargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
		Thread.sleep(2000);
		General.action().type((TranslatorLocators.Locator().trans_TargetSegement_textarea(3)),Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().Text_Formatting_Underline);
		Thread.sleep(1000);

		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_qualitycheck_Run_button);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().qualitycheck_number_of_issues);
		Thread.sleep(1000);

		String NumberOfIssues1 = General.driver.findElement(TranslatorLocators.Locator().qualitycheck_number_of_issues).getText();
		System.out.println("NUMBER OF RULE VIOLATION IS:-" + NumberOfIssues1);
		Thread.sleep(1000);
		String ruleViolation_messages1 = General.driver.findElement(TranslatorLocators.Locator().qualitycheck_rule_violation_mesaages).getText();
		System.out.println("RULE VIOLATION MESSAGES:-"+ ruleViolation_messages1);
		Thread.sleep(2000);

		General.action().Click(TranslatorLocators.Locator().qualitycheck_rule_violation_message_for_bold(3));
		Thread.sleep(2000);
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_TargetSegement_textarea_line(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying bold target segment is active");
			Thread.sleep(2000);
		}
		
		General.action().Click(TranslatorLocators.Locator().Trans_qualityChecks_rules("1","There are not enough frames between this segment and the next one."));
		Thread.sleep(2000);
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_segments_currentSegment(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying bold target segment is active");
			Thread.sleep(2000);
		}
		
		

		System.out.println("EOM trans_onGoing_qualityCheck  method()");
	}

	public void assertion() throws Exception {
		
		//Verify when user clicks on the error, user should be redirected to the correct segment.
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_segments_currentSegment(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying bold target segment is active");
			Thread.sleep(2000);
		}
		 else {
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
