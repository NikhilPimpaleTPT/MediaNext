package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.Color;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;

import modules.PM_user;
import modules.Translator;
import modules.Verify;

public class Sub_763913 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_763913"+CommonElements.BROWSER+"X4";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";

	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_763913");
		dataSet.put("TL_test_case_description","Sub_763913: Reading Speed Color code is not intuitive enough: Translation view. ");
		dataSet.put("TL_internal_testCase_ID", "763913");
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
			CreateSubmisson_Step1("3", "20","80", "3", "30");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
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
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			
			transcription_onGoing_ReadingSpeed_color_check(SubmissionName,targetlanguage_1);
			Thread.sleep(1000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void CreateSubmisson_Step1(String MinreadingSpeed,String MaxreadingSpeed,String MaxCharperLine,String MinDuration,String MaxDuration) throws Exception {
		System.out.println("INSIDE method CreateSubmisson_Step1()\n");
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		Thread.sleep(1000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		Thread.sleep(1000);
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
		Thread.sleep(1000);
		Translator.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Minreadingspeed);
		Thread.sleep(1000);
		Translator.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Minreadingspeed,MinreadingSpeed);
		Thread.sleep(1000);

		Translator.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed);
		Thread.sleep(1000);
		Translator.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed,MaxreadingSpeed);
		Thread.sleep(1000);


		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
		Thread.sleep(1000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,MaxCharperLine);
		Thread.sleep(1000);

		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
		Thread.sleep(1000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,MinDuration);
		Thread.sleep(1000);

		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
		Thread.sleep(1000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,MaxDuration);
		Thread.sleep(1000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		Thread.sleep(2000);
		
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		Thread.sleep(2000);
		
		//TODO PD INTEGRATION WILL BE IMPL FROM SUB1.13.0
//		PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
//		Thread.sleep(1000);
		System.out.println("EOM CreateSubmisson_Step1()\n");

	}

	public void transcription_onGoing_ReadingSpeed_color_check(String SubmissionName, String target) throws Exception {

		System.out.println("INSIDE transcription_onGoing_ReadingSpeed_color_check  method()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
        Thread.sleep(10000);
        
		// Check Target Segment for Minimum Reading Speed.
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		
		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Test".trim());
		Thread.sleep(4000);
		
		
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		 Thread.sleep(2000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		 
		String Min_ReadingSpeed_color = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(1)).getCssValue("color");
		System.out.println("Min_ReadingSpeed_color######### :-"+ Min_ReadingSpeed_color);
		Thread.sleep(2000);
		
		String hex_grey = Color.fromString(Min_ReadingSpeed_color).asHex();
		System.out.println("Chars color value:-" +hex_grey);
		
		// verify assertion for color
		 assertion = hex_grey.equalsIgnoreCase("#808080");
		System.out.println("COLOR FOR MIN READING SPEED IS GREY:-"+assertion );
		 if (assertion == false) {
			report("f","Assertion failed while verifying COLOR FOR MIN READING SPEED IS GREY after Search	");
		}
			Thread.sleep(3000);	
	
	
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_button);
		Thread.sleep(3000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_button);
		Thread.sleep(3000);
		
		
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_RUN_button);
		Thread.sleep(3000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_RUN_button);
		Thread.sleep(3000);
		
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().qualitycheck_rule_violation_message_for_Min_RS, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying qualitycheck_rule_violation_message_for_Min_RS  after Search");
		}
		Thread.sleep(4000);
		
		
		
      // Check Target Segment for Maximum Reading Speed.
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		
		PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Test target segment for maximum reading Speed Test target segment for maximum reading Speed  maximum reading Speed Test target segment for maximum reading Speed");
		Thread.sleep(3000);
		
		
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		 Thread.sleep(2000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));

		String Max_ReadingSpeed_color = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(2)).getCssValue("color");
		System.out.println("Max_ReadingSpeed_color######### :-"+ Max_ReadingSpeed_color);
		Thread.sleep(2000);
		
		String hex_Red = Color.fromString(Max_ReadingSpeed_color).asHex();
		System.out.println("Chars color value:-" +hex_Red);
		
		// verify assertion for color
		 assertion = hex_Red.equalsIgnoreCase("#FF4F64");
		 System.out.println("COLOR FOR MAX READING SPEED IS RED:-"+assertion );
		 if (assertion == false) {
			report("f","Assertion failed while verifying COLOR FOR MAX READING SPEED IS RED:- after Search	");
		}
			Thread.sleep(3000);	
		
		
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_RUN_button);
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_RUN_button);
		Thread.sleep(2000);

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().qualitycheck_rule_violation_message_for_Max_RS, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying qualitycheck_rule_violation_message_for_Max_RS  after Search");
		}

		
		
		// Check Target Segment for Range between minimum to maximum
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Test target segment for range between minimum to maximum RS");
		Thread.sleep(3000);

		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
		
		
		String ReadingSpeed_color_between_range = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(3)).getCssValue("color");
		System.out.println("ReadingSpeed_color_between_range######### :-"+ ReadingSpeed_color_between_range);
		Thread.sleep(2000);
		
		
		String hex_White = Color.fromString(ReadingSpeed_color_between_range).asHex();
		System.out.println("Chars color value:-" +hex_White);
		
		// verify assertion for color
		 assertion = hex_White.equalsIgnoreCase("#FFFFFF");
		 System.out.println("COLOR FOR READING SPEED IS WHITE FOR RANGE BETWEEN MIN TO MAX:-:-"+assertion );
		 if (assertion == false) {
			report("f","Assertion failed while verifying COLOR FOR MAX READING SPEED IS RED:- after Search	");
		}
			Thread.sleep(3000);	
		
		

			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_RUN_button);
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_RUN_button);
			Thread.sleep(2000);

		String Quality_check_issues1 = General.driver.findElement(Pm_User_Submission_Locators.Locator().qualitycheck_number_of_issues).getText();
		System.out.println("NUMBER OF QUALITY CHECK ISSUES:- "+ Quality_check_issues1);

		String Quality_check_issues_messages1 = General.driver.findElement(Pm_User_Submission_Locators.Locator().qualitycheck_rule_violation_mesaages).getText();
		System.out.println("QUALITY CHECK RULE VIOLATION MESSAGES FOR READING SPEED:-"+ Quality_check_issues_messages1);
		Thread.sleep(5000);

		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().qualitycheck_rule_violation_mesaages_for_segment(3), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying qualitycheck_norule_violation_mesaage  after Search");
		}

		System.out.println("EOM translate_onGoing_ReadingSpeed_color_check()");

	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().qualitycheck_rule_violation_mesaages_for_segment(3), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying qualitycheck_norule_violation_mesaage  after Search");
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
