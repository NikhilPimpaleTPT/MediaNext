package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

public class Sub_764740 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName ="SUB_764740"+CommonElements.BROWSER+"H2";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_764740");
		dataSet.put("TL_test_case_description", "SUB-764740:Switch continuously to edit / preview / edit / ... mode");
		dataSet.put("TL_internal_testCase_ID", "764740");
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
			Pm_Transcription_Switch_continuously_toedit(SubmissionName, targetlanguage_1, false, true);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f", "Execution level error was encountered.\n\nError log:\n\n" + Verify.action().getErrorBuffer(e));
		}

	}

	public void Pm_Transcription_Switch_continuously_toedit(String SubmissionName, String target, boolean complete,boolean back) throws Exception {

		System.out.println("INSIDE Pm_Transcription_Ongoing_Preview_mode  method()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(
				Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
		Thread.sleep(20000);

		System.out.println("**********STRAT SCENARIO 1***********");
		
		//TODO CLICK Video
        //Not Needed
		/*PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		Thread.sleep(3000);*/

		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		Thread.sleep(2000);

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentRecord, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_tesxtarea_currentRecord  after Search");
		}
		Thread.sleep(3000);

		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
        
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*General.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.ESCAPE, false);
		Thread.sleep(5000);*/
		
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		Thread.sleep(2000);
		
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ESCAPE);
		Thread.sleep(2000);
		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Preview_Mode_text("Dude get out of the way!"), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Preview_Mode  after Search");
		}

		Thread.sleep(3000);
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
/*
		General.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video, Keys.ESCAPE,false);
		Thread.sleep(5000);*/
		
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ESCAPE);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Preview_Mode_text("Dude get out of the way!"), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Preview_Mode  after Search");
		}

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Edit_Mode, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Edit_Mode  after Search");
		}

		System.out.println("**********END SCENARIO 1***********");

		Thread.sleep(3000);

		System.out.println("**********STRAT SCENARIO 2***********");

		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentRecord, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_tesxtarea_currentRecord  after Search");
		}
		Thread.sleep(3000);
		
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*General.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video, Keys.ESCAPE,false);
		Thread.sleep(5000);
*/
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ESCAPE); 
		
		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Preview_Mode_text("Jellyfish at the Monterey Aquarium."), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Preview_Mode  after Search");
		}

		Thread.sleep(3000);
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*General.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video, Keys.ESCAPE,false);
		Thread.sleep(5000);
*/
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ESCAPE); 
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Preview_Mode_text("Jellyfish at the Monterey Aquarium."), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Preview_Mode  after Search");
		}
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Edit_Mode, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Edit_Mode  after Search");
		}

		System.out.println("**********END SCENARIO 2***********");

		Thread.sleep(3000);
		
		System.out.println("**********START SCENARIO 3***********");

		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
		Thread.sleep(2000);
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, Keys.PAGE_UP));
		Thread.sleep(5000);*/
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_PAGE_UP); 

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_textarea(1)  after Search");
		}


		Thread.sleep(2000);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Preview_Mode_text("Dude get out of the way!"), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Preview_Mode  after Search");
		}
		

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Edit_Mode, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Edit_Mode  after Search");
		}
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		
/*
		General.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video, Keys.ESCAPE,false);
		Thread.sleep(5000);*/
		
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ESCAPE); 

		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Preview_Mode_text("Shaky Hands"), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Preview_Mode  after Search");
		}

		Thread.sleep(3000);
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD

		/*General.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video, Keys.ESCAPE,false);
		Thread.sleep(5000);*/
		
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ESCAPE); 

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Edit_Mode, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Edit_Mode  after Search");
		}

		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(4));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(4));
		Thread.sleep(2000);
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD

		/*General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, Keys.PAGE_DOWN));
		Thread.sleep(5000);*/
		
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_PAGE_DOWN); 

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(5), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_textarea(3)  after Search");
		}

		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		Thread.sleep(2000);

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Edit_Mode, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Edit_Mode  after Search");
		}

		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*General.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video, Keys.ESCAPE,false);
		Thread.sleep(5000);
		
*/
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ESCAPE); 
		
		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Preview_Mode_text("Ah yes, this is better"), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Preview_Mode  after Search");
		}

		Thread.sleep(3000);
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
	/*	General.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video, Keys.ESCAPE,false);
		Thread.sleep(5000);*/
		
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ESCAPE); 

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Edit_Mode, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Edit_Mode  after Search");
		}

		System.out.println("**********END SCENARIO 3***********");
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Edit_Mode, 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying PM_Transcription_Video_Preview_Mode  after Search");
		} else {
			report("p", "All Assertion passed.");
		}
	}

	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan, Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}

}
