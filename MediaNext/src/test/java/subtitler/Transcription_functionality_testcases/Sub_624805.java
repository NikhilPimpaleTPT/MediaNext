package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
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
import modules.PM_user;
import modules.Verify;

public class Sub_624805 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_624805"+CommonElements.BROWSER+"M2";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String Duration_OfGrid_after;
	String Duration_OfGrid_before;
	


	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_624805");
		dataSet.put("TL_test_case_description",	"SUB-624805:Split a subtitle into 2 subtitles with automatic duration.");
		dataSet.put("TL_internal_testCase_ID", "624805");
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
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			Pm_Transcription_Split_Subtitle(SubmissionName, "New");
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void Pm_Transcription_Split_Subtitle(String SubmissionName,String status) throws Exception {

		System.out.println("INSIDE Pm_Complete_trans_Submission  method()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, status))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
		}
		Thread.sleep(6000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
		Thread.sleep(6000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
		Thread.sleep(10000);
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		
		String first_row_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1)).getText();
		System.out.println("FIRST ROW CONTENT BEFORE SPLIT:- " +first_row_before);
		
		 Duration_OfGrid_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Duration(1)).getText();
		System.out.println("DURATION BEFORE RE_EDITING SUBTITLE OF GRID:- " +Duration_OfGrid_before);
		
		
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
//		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ARROW_RIGHT));
//		Thread.sleep(2000);
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
//		General.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea, Keys.ARROW_RIGHT, false);
//		Thread.sleep(2000);
////		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ARROW_RIGHT));
////		Thread.sleep(2000);
//		General.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea, Keys.ARROW_RIGHT, false);
//		Thread.sleep(2000);
//		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, "s"));
//		Thread.sleep(5000);
		
		 PM_user.action().transcription_keyEnvents(KeyEvent.VK_RIGHT);
		 
		 PM_user.action().transcription_keyEnvents(KeyEvent.VK_RIGHT);
		 //TODO FROM GL PLAY 1.22.0 ALT+S changed to ALT+P
		 PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_P);
		
		String first_row_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1)).getText();
		System.out.println("FIRST ROW CONTENT BEFORE SPLIT:- " +first_row_after);
		
		String second_row_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2)).getText();
		System.out.println("FIRST ROW CONTENT BEFORE SPLIT:- " +second_row_after);
		
		assertion = first_row_before != first_row_after && first_row_before != second_row_after;
		System.out.println("FIRST ROW AFTER SPLIT IS NOT SAME AS EALIER:- " +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
		}
		
		assertion = first_row_before.contains(first_row_after) && first_row_before.contains(second_row_after);
		System.out.println("FIRST ROW CONTAINS CONTENT OF FIRST ROW AND SECOND ROW:- " +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
		}
		
		 Duration_OfGrid_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Duration(1)).getText();
		System.out.println("DURATION OF GRID AFTER RE_EDITING SUBTITLE OF GRID:- " +Duration_OfGrid_after);
		
		assertion = Duration_OfGrid_before != Duration_OfGrid_after;
		System.out.println("DURATION  OF GRID AFTER RE_EDITING SUBTITLE IS UPDATED:- " +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying DURATION  OF GRID AFTER RE_EDITING SUBTITLE IS UPDATED  after Search");
		}
		
	
	}

		public void assertion() throws Exception {
			assertion = Duration_OfGrid_before != Duration_OfGrid_after;
			System.out.println("DURATION  OF GRID AFTER RE_EDITING SUBTITLE IS UPDATED:- " +assertion);
			if (assertion == false) {
				report("f","Assertion failed while verifying DURATION  OF GRID AFTER RE_EDITING SUBTITLE IS UPDATED  after Search");
			}  else {
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






