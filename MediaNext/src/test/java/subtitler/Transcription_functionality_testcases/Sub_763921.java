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

public class Sub_763921 {
	

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_763921"+CommonElements.BROWSER+"X4";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String Subtitle_after;
	String ReadingSpeed_after;
	String Subtitle_before;
	String ReadingSpeed_before;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_763921");
		dataSet.put("TL_test_case_description",	"SUB-763921:Slow & Super Slow player speed");
		dataSet.put("TL_internal_testCase_ID", "763921");
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
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			Pm_Transcription_Ongoing_Slow_player_speed(SubmissionName, targetlanguage_1,false, true);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public void Pm_Transcription_Ongoing_Slow_player_speed(String SubmissionName,String target, boolean complete, boolean back) throws Exception {

		System.out.println("INSIDE Pm_Transcription_Ongoing_Preview_mode  method()");

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
        Thread.sleep(5000);
       
        
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_default_speed);
		Thread.sleep(1000);
	
		
	    String  Default_VideoSpeed = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_default_speed).getText();
		System.out.println("DEFAULT VIDEO SPEED######### :-"+ Default_VideoSpeed);
		Thread.sleep(1000);
		
		//TODO
	
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
//		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
//		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
//		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
	    //General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.ARROW_LEFT));
		//Thread.sleep(5000);
		
		 PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_LEFT);
		
		
		String VideoSpeed_forSlow = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_default_speed).getText();
		System.out.println("SLOW VIDEO SPEED######### :-"+ VideoSpeed_forSlow);
		Thread.sleep(1000);
		
		
		assertion = VideoSpeed_forSlow.equalsIgnoreCase("0.75 X");
		System.out.println("AFTER ALT + ARROW_LEFT VIDEO SPEED IS 0.75:-" +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying AFTER ALT + ARROW_LEFT VIDEO SPEED IS 0.75  after Search");
		}
		
		//TODO FOR CHROME AND FF CLICK ON VIDEO IS NOT NEEDED
//		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
//		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.ARROW_LEFT));
		Thread.sleep(5000);*/
		 PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_LEFT);
		
		String VideoSpeed_forSuperSlow = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_default_speed).getText();
		System.out.println("SUPER SLOW VIDEO SPEED######### :-"+ VideoSpeed_forSuperSlow);
		Thread.sleep(1000);
		
		
		assertion = VideoSpeed_forSuperSlow.equalsIgnoreCase("0.5 X");
		System.out.println("AFTER ALT + ARROW_LEFT VIDEO SPEED IS 0.5:-" +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying AFTER ALT + ARROW_LEFT VIDEO SPEED IS 0.5  after Search");
		}
		
		Thread.sleep(1000);
		
		//TODO FOR CHROME AND FF CLICK ON VIDEO IS NOT NEEDED
//		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
//		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.ARROW_LEFT));
		Thread.sleep(5000);*/
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_LEFT);
		
		
		String VideoSpeed_NoEffect = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_default_speed).getText();
		System.out.println("SUPER SLOW VIDEO SPEED######### :-"+ VideoSpeed_NoEffect);
		Thread.sleep(1000);
		
		
		assertion = VideoSpeed_forSuperSlow.equalsIgnoreCase(VideoSpeed_NoEffect);
		System.out.println("AFTER ALT + ARROW_LEFT FOR THIRD TIME NOTHING WILL HAPPEN VIDEO SPEED SHOULD BE SAME AS 0.5:-" +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying AFTER ALT + ARROW_LEFT FOR THIRD TIME NOTHING WILL HAPPEN VIDEO SPEED SHOULD BE SAME AS 0.5  after Search");
		}
		
		
		Thread.sleep(1000);
		
		//TODO FOR CHROME AND FF CLICK ON VIDEO IS NOT NEEDED
//		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
//		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.ARROW_RIGHT));
		Thread.sleep(5000);*/
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_RIGHT);
		
		
		
		String VideoSpeed_SlowPlay = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_default_speed).getText();
		System.out.println("SLOW PLAY VIDEO SPEED######### :-"+ VideoSpeed_SlowPlay);
		Thread.sleep(1000);
		
		
		assertion = VideoSpeed_SlowPlay.equalsIgnoreCase("0.75 X");
		System.out.println("AFTER ALT + ARROW_LEFT VIDEO SPEED IS 0.75:-" +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying AFTER ALT + ARROW_RIGHT VIDEO SPEED IS 0.75  after Search");
		}	
		
		
		Thread.sleep(1000);
		
		//TODO FOR CHROME AND FF CLICK ON VIDEO IS NOT NEEDED
		//PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
//		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.ARROW_RIGHT));
		Thread.sleep(5000);*/
		
		
		
		/*PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_play_arrow);
		Thread.sleep(2000);*/
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_RIGHT);
		
		
		
		
		String VideoSpeed_NormalPlay = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_default_speed).getText();
		System.out.println("NORMAL PLAY VIDEO SPEED######### :-"+ VideoSpeed_NormalPlay);
		Thread.sleep(3000);
		
		//
		assertion = VideoSpeed_NormalPlay.equalsIgnoreCase("1 X");
		System.out.println("AFTER ALT + ARROW_LEFT VIDEO SPEED IS 1 X:-" +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying AFTER ALT + ARROW_LEFT VIDEO SPEED IS 1 X  after Search");
		}	
		
		
		
		Thread.sleep(2000);
		//TODO FOR CHROME AND FF CLICK ON VIDEO IS NOT NEEDED
//		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
//		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
	/*	General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.ARROW_RIGHT));
		Thread.sleep(5000);
		*/
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_RIGHT);
		
		String VideoSpeed_FastFwdPlay = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_default_speed).getText();
		System.out.println("FAST FWD PLAY VIDEO SPEED######### :-"+ VideoSpeed_FastFwdPlay);
		Thread.sleep(2000);
		
		
		assertion = VideoSpeed_FastFwdPlay.equalsIgnoreCase("1.25 X");
		System.out.println("AFTER ALT + ARROW_LEFT VIDEO SPEED IS 1.25 X:-" +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying AFTER ALT + ARROW_LEFT VIDEO SPEED IS 1.25 X  after Search");
		}	
		
		Thread.sleep(5000);
		//TODO FOR CHROME AND FF CLICK ON VIDEO IS NOT NEEDED
//		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
//		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.SHIFT,"?"));
		Thread.sleep(5000);*/
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_SHIFT,KeyEvent.VK_SLASH);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_KeyBoardShortcut, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_Transcription_KeyBoardShortcut  after Search");
		}
		
		String ShortCut_for_Play_faster = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_shortcutFor_arrowLeft).getText();
		System.out.println("SHORTCUT FOR Play - play faster IS:-" +ShortCut_for_Play_faster);
		
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_shortcutFor_arrowLeft, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_Transcription_shortcutFor_arrowLeft  after Search");
		}
		
		
		String ShortCut_for_Slow_play = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_shortcutFor_arrowLeft).getText();
		System.out.println("SHORTCUT FOR Slow play / Super slow play  IS:-" +ShortCut_for_Slow_play);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_shortcutFor_arrowRight, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_Transcription_shortcutFor_arrowRight  after Search");
		}
		
	}
	
	
	    public void assertion() throws Exception {

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_shortcutFor_arrowRight, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying PM_Transcription_shortcutFor_arrowRight  after Search");
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




