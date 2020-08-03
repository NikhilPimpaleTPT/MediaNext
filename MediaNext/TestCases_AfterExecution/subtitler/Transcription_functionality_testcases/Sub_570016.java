package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;

import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.Color;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_570016 {
	

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_570016"+CommonElements.BROWSER+"K1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
    String Reading_speed_color;
	String hex_Reading_speed_color;
	


	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_570016");
		dataSet.put("TL_test_case_description",	"SUB-570016:Current Segment data under the player");
		dataSet.put("TL_internal_testCase_ID", "570016");
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
			Thread.sleep(20000);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			Pm_Transcription_Current_Segment_data(SubmissionName, "New");
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void Pm_Transcription_Current_Segment_data(String SubmissionName,String status) throws Exception {

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
		Thread.sleep(4000);
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		Thread.sleep(3000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, "n"));
		Thread.sleep(1000);*/
		
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ESCAPE);
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_N);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea," Testing transcription");
		Thread.sleep(1000);
	
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, Keys.ENTER));
		Thread.sleep(4000);
		
		
		assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1)).getText().contains("Testing transcription");
		if (assertion == false) {
			report("f","Assertion failed while verifying enter text");
		}
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		
		
		assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea).getText().contains("Testing transcription");
		if (assertion == false) {
			report("f","Assertion failed while verifying enter text in video text box");
		}
		
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		String Reading_speed_OfGrid_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(1)).getText();
		System.out.println("READING SPEED BEFORE RE_EDITING SUBTITLE OF GRID:- " +Reading_speed_OfGrid_before);
		Thread.sleep(2000);
		String Reading_speed_OfVideo_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_video_ReadingSpeed).getText();
		System.out.println("READING SPEED BEFORE RE_EDITING SUBTITLE OF VIDEO:- " +Reading_speed_OfVideo_before);
		Thread.sleep(2000);
		String Time_Out_OfGrid_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeOut(1)).getText();
		System.out.println("TIME OUT BEFORE RE_EDITING SUBTITLE OF GRID:- " +Time_Out_OfGrid_before);
		Thread.sleep(2000);
		String Time_Out_OfVideo_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_video_TimeOut).getText();
		System.out.println("TIME OUT BEFORE RE_EDITING SUBTITLE OF VIDEO:- " +Time_Out_OfGrid_before);
		Thread.sleep(2000);
		
		String Duration_OfGrid_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Duration(1)).getText();
		System.out.println("DURATION BEFORE RE_EDITING SUBTITLE OF GRID:- " +Duration_OfGrid_before);
		Thread.sleep(2000);
		String Duration_OfVideo_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_video_Duration).getAttribute("value");
		System.out.println("DURATION BEFORE RE_EDITING SUBTITLE OF VIDEO:- " +Duration_OfVideo_before);
		
		Thread.sleep(3000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		
		 //TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		Thread.sleep(2000);*/
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.DELETE));
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"RE-EDITED");
		Thread.sleep(2000);
		//General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_SHIFT,KeyEvent.VK_RIGHT);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, "o"));
		Thread.sleep(5000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ESCAPE));
		Thread.sleep(2000);
		
		/*PM_user.action().transcription_enterText_Video(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		
		PM_user.action().transcription_enterText_Video(KeyEvent.VK_DELETE);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"RE-EDITED");*/
		//Thread.sleep(2000);
		//PM_user.action().transcription_enterText_Video(KeyEvent.VK_ALT,KeyEvent.VK_SHIFT,KeyEvent.VK_RIGHT);
		//PM_user.action().transcription_enterText_Video(KeyEvent.VK_ALT,KeyEvent.VK_O);
		//PM_user.action().transcription_enterText_Video(KeyEvent.VK_ESCAPE);
				
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		String Reading_speed_OfGrid_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(1)).getText();
		System.out.println("READING SPEED OF GRID AFTER RE_EDITING SUBTITLE OF GRID:- " +Reading_speed_OfGrid_after);
		Thread.sleep(2000);
		String Reading_speed_OfVideo_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_video_ReadingSpeed).getText();
		System.out.println("READING SPEED OF VIDEO AFTER RE_EDITING SUBTITLE OF VIDEO:- " +Reading_speed_OfVideo_after);
		Thread.sleep(2000);
		String Time_Out_OfGrid_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeOut(1)).getText();
		System.out.println("TIME OUT OF GRID AFTER RE_EDITING SUBTITLE OF GRID:- " +Time_Out_OfGrid_after);
		Thread.sleep(2000);
		String Time_Out_OfVideo_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_video_TimeOut).getText();
		System.out.println("TIME OUT OF VIDEO AFTER RE_EDITING SUBTITLE OF VIDEO:- " +Time_Out_OfVideo_after);
		Thread.sleep(2000);
		
		String Duration_OfGrid_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Duration(1)).getText();
		System.out.println("DURATION OF GRID AFTER RE_EDITING SUBTITLE OF GRID:- " +Duration_OfGrid_after);
		Thread.sleep(2000);
		String Duration_OfVideo_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_video_Duration).getAttribute("value");
		Thread.sleep(2000);
		
		assertion = Reading_speed_OfGrid_before != Reading_speed_OfGrid_after;
		System.out.println("READING SPEED OF GRID AFTER RE_EDITING SUBTITLE IS UPDATED:- " +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying READING SPEED OF GRID AFTER RE_EDITING SUBTITLE IS UPDATED  after Search");
		}
		Thread.sleep(2000);
		assertion = Reading_speed_OfVideo_before != Reading_speed_OfVideo_after;
		System.out.println("READING SPEED OF VIDEO AFTER RE_EDITING SUBTITLE IS UPDATED:- " +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying READING SPEED OF VIDEO AFTER RE_EDITING SUBTITLE IS UPDATED after Search");
		}
		Thread.sleep(2000);
		assertion = Time_Out_OfGrid_before != Time_Out_OfGrid_after;
		System.out.println("TIME OUT OF GRID AFTER RE_EDITING SUBTITLE IS UPDATED:- " +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying TIME OUT OF GRID AFTER RE_EDITING SUBTITLE IS UPDATED  after Search");
		}
		Thread.sleep(2000);
		assertion = Time_Out_OfVideo_before != Time_Out_OfVideo_after;
		System.out.println("TIME OUT OF VIDEO AFTER RE_EDITING SUBTITLE IS UPDATED:- " +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying TIME OUT OF VIDEO AFTER RE_EDITING SUBTITLE IS UPDATED  after Search");
		}
		Thread.sleep(2000);
		assertion = Duration_OfGrid_before != Duration_OfGrid_after;
		System.out.println("DURATION  OF GRID AFTER RE_EDITING SUBTITLE IS UPDATED:- " +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying DURATION  OF GRID AFTER RE_EDITING SUBTITLE IS UPDATED  after Search");
		}
		Thread.sleep(2000);
		System.out.println("Duration_OfVideo_before"+Duration_OfVideo_before);
		System.out.println("Duration_OfVideo_after"+Duration_OfVideo_after);
		assertion = Duration_OfVideo_before != Duration_OfVideo_after;
		System.out.println("DURATION  OF VIDEO AFTER RE_EDITING SUBTITLE IS UPDATED:- " +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying DURATION  OF VIDEO AFTER RE_EDITING SUBTITLE IS UPDATED  after Search");
		}
		
		Thread.sleep(2000);
		
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		//General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		//Thread.sleep(3000);
		//General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ESCAPE));
		//Thread.sleep(1000);
		//General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		//Thread.sleep(2000);
		//General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, "n"));
		Thread.sleep(1000);
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ESCAPE);
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_N);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea," Testing transcription");
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_pause);
		Thread.sleep(3000);
		
		
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, Keys.ENTER));
		Thread.sleep(3000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		Thread.sleep(3000);
		
		
		String Reading_speed_grid = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(2)).getText();
		System.out.println("READING SPEED OF TRANSCRIPTION GRID SUBTITLE:- " +Reading_speed_grid);
		
		String Reading_speed_video = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_video_ReadingSpeed).getText();
		System.out.println("READING SPEED OF VIDEO SUBTITLE:- " +Reading_speed_video);
		
		
		assertion = Reading_speed_grid.equalsIgnoreCase(Reading_speed_video);
		System.out.println("READING SPEED OF TRANSCRIPTION GRID AND VIDEO ARE SAME:- " +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying READING SPEED OF TRANSCRIPTION GRID AND VIDEO ARE SAME  after Search");
		}
		
		String LineCount_grid = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_LineCount(2)).getText();
		System.out.println("LINE COUNT OF TRANSCRIPTION GRID SUBTITLE:- " +LineCount_grid);
		
		String LineCount_video = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_video_LineCount).getText();
		System.out.println("LINE COUNT OF VIDEO SUBTITLE:- " +LineCount_video);
		
	
		
		Thread.sleep(3000);
		for (int i=1; i<=3; i++){
		//TODO NOT WORKING	
		/*General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		Thread.sleep(3000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ESCAPE));
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, "n"));
		Thread.sleep(1000);*/
			
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ESCAPE);
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_N);	
			
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"transcription Testing transcription Testing transcriptionTesting transcription transcription transcription transcription transcription transcription transcription transcription");
		Thread.sleep(1000);
		
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, Keys.ENTER));
		Thread.sleep(3000);
		}
		
		
		 Reading_speed_color = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(2)).getCssValue("color");
		 System.out.println("READING SPEED COLOR OF TRANSCRIPTION GRID SUBTITLE:- " +Reading_speed_color);
		
		
		
		 hex_Reading_speed_color = Color.fromString(Reading_speed_color).asHex();
		System.out.println("Chars color value:-" +hex_Reading_speed_color);
		
		// verify assertion for color
		 assertion = hex_Reading_speed_color.equalsIgnoreCase("#ff4f64");
		 if (assertion == false) {
			report("f","Assertion failed while verifying READING SPEED COLOR OF TRANSCRIPTION GRID SUBTITLE after Search	");
		}
	}
		
	public void assertion() throws Exception {
		 assertion = hex_Reading_speed_color.equalsIgnoreCase("#ff4f64");
		 if (assertion == false) {
			report("f","Assertion failed while verifying READING SPEED COLOR OF TRANSCRIPTION GRID SUBTITLE after Search	");
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



