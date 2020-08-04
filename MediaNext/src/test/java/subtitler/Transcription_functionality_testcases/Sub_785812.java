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

public class Sub_785812 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_785812" + CommonElements.BROWSER + "X3";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String Time_IN_After;
	String Time_IN_Before;
	
	

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_785812");
		dataSet.put("TL_test_case_description","SUB-785812:Transcription - 'ALT+g\" shortcut, to quickly go to a specific subtitle");
		dataSet.put("TL_internal_testCase_ID", "785812");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
		try {
			// PM login
			General.action().login(CommonElements.action().PM_username, CommonElements.action().password);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER + CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			Pm_Transcription_specific_subtitle(SubmissionName, targetlanguage_1, false, true);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f", "Execution level error was encountered.\n\nError log:\n\n" + Verify.action().getErrorBuffer(e));
		}

	}

	public void Pm_Transcription_specific_subtitle(String SubmissionName, String target, boolean complete,boolean back) throws Exception {

		System.out.println("INSIDE Pm_Transcription_Ongoing_navigate_faster  method()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
		Thread.sleep(9000);
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		Thread.sleep(3000);*/
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		
		
//		 Time_IN_Before =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(1)).getText();
//		  System.out.println("TIME IN BEFORE :-" +Time_IN_Before);
		
		//General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, "g"));
		//Thread.sleep(3000);
		
		 PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_G);
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_transcription_GO_to_Subtitle);
		Thread.sleep(3000);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_transcription_GO_to_Subtitle, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying Pm_transcription_GO_to_Subtitle  after Search");
		}
		
		Thread.sleep(3000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_transcription_GO_to_Subtitle_input);
		Thread.sleep(3000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_transcription_GO_to_Subtitle_input,"2");
		Thread.sleep(3000);
		
		boolean Go_enable = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_transcription_GO_to_Subtitle_GO_Button).isEnabled();
		System.out.println("SUBTITLE GO BUTTON IS ENABLE FOR INTEGER VALUE:- " +Go_enable);
		
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_transcription_gotosubtitle_count);
		Thread.sleep(3000);
		

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_transcription_gotosubtitle_count, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying Pm_transcription_gotosubtitle_count  after Search");
		}
		
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_transcription_GO_to_Subtitle_GO_Button);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_transcription_GO_to_Subtitle_GO_Button);
    	Thread.sleep(2000);
    	
    	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_textarea(2)  after Search");
		}
		

		/*General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, "g"));
		Thread.sleep(3000);*/

		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		 PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_G);
		 
		Thread.sleep(3000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_TranscriptionGO_to_Subtitle_TimeSec);
		Thread.sleep(3000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_TranscriptionGO_to_Subtitle_TimeSec);
		Thread.sleep(1000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_TranscriptionGO_to_Subtitle_TimeSec,"11");
		Thread.sleep(3000);
		
        General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_GO_to_Subtitle_TimeFrame);
		Thread.sleep(3000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_GO_to_Subtitle_TimeFrame);
		Thread.sleep(1000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_GO_to_Subtitle_TimeFrame,"04");
		Thread.sleep(3000);
		
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_transcription_GO_to_Subtitle_GO_Button);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_transcription_GO_to_Subtitle_GO_Button);
    	Thread.sleep(2000);
		
		
    	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_textarea(1)  after Search");
		}
		
	}
		
	public void assertion() throws Exception {

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_textarea(1)  after Search");
		}else {
			report("p", "All Assertion passed.");
		}
	}

	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),
				Subtitler_TestRail_Common_Properties.idTestPlan, Subtitler_TestRail_Common_Properties.idBuild, result,
				Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}

}
