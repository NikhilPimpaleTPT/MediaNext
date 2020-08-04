package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

public class Sub_686907 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_686907"+CommonElements.BROWSER+"G1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	int id;
	String video_text;
 

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_686907");
		dataSet.put("TL_test_case_description",	"SUB-686907:Preview mode: allow Grid navigation with keyboard");
		dataSet.put("TL_internal_testCase_ID", "686907");
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
			Pm_Transcription_Ongoing_Preview_mode(SubmissionName, targetlanguage_1,false, true);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public void Pm_Transcription_Ongoing_Preview_mode(String SubmissionName,String target, boolean complete, boolean back) throws Exception {

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
		//This time is required to come at segment 
        Thread.sleep(10000);
        
        //Now pause the video.(Checking when video is paused)
	    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(4));
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(4));
		Thread.sleep(2000);
		
		
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, Keys.PAGE_UP));
		Thread.sleep(3000);
		 
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentRecord(3), 5);
	    System.out.println("GRID SUBTITLE IS HIGHLIGHTED:-" +assertion);
		if (assertion == false) {
		report("f","Assertion failed while verifying GRID SUBTITLE IS HIGHLIGHTED  after Search");
		}
		
		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentRecord(4), 5);
	    System.out.println("GRID SUBTITLE IS HIGHLIGHTED:-" +assertion);
		if (assertion == false) {
		report("f","Assertion failed while verifying GRID SUBTITLE IS HIGHLIGHTED  after Search");
		}
		//Verify video should be synchronized accordingly.
		String Video_Duration_Segment3 = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_video_Duration).getAttribute("value");
		System.out.println("VIDEO DURAION segment:-" +Video_Duration_Segment3);
		 
		String TextArea_Duration_Segment3 = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_duration(3,9)).getText();
		System.out.println("TEXTAREA DURAION segment:-" +TextArea_Duration_Segment3);
		Thread.sleep(2000);
		
		assertion = TextArea_Duration_Segment3.equalsIgnoreCase(Video_Duration_Segment3);
		System.out.println("TEXTAREA DURATION IS SAME AS VIDEO DURATION:-" +assertion);
	    if (assertion == false) {
		report("f","Assertion failed while verifying TEXTAREA DURATION IS SAME AS VIDEO DURATION");
		}
	   
	    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		Thread.sleep(2000);
		
		
		for(int i=1;i<=3;i++) {
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, Keys.PAGE_DOWN));
		Thread.sleep(3000);
		}
				
		 
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentRecord(5), 5);
	    System.out.println("GRID SUBTITLE IS HIGHLIGHTED:-" +assertion);
		if (assertion == false) {
		report("f","Assertion failed while verifying GRID SUBTITLE IS HIGHLIGHTED  after Search");
		}
		
		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentRecord(2), 5);
	    System.out.println("GRID SUBTITLE IS HIGHLIGHTED:-" +assertion);
		if (assertion == false) {
		report("f","Assertion failed while verifying GRID SUBTITLE IS HIGHLIGHTED  after Search");
		}
		//Verify video should be synchronized accordingly.
		String Video_Duration_Segment5 = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_video_Duration).getAttribute("value");
		System.out.println("VIDEO DURAION VALUE:-" +Video_Duration_Segment5);
		 
		String TextArea_Duration_Segment5 = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_duration(5,9)).getText();
		System.out.println("TEXTAREA DURAION VALUE:-" +TextArea_Duration_Segment5);
		Thread.sleep(2000);
		
		assertion = TextArea_Duration_Segment5.equalsIgnoreCase(Video_Duration_Segment5);
		System.out.println("TEXTAREA DURATION IS SAME AS VIDEO DURATION:-" +assertion);
	    if (assertion == false) {
		report("f","Assertion failed while verifying TEXTAREA DURATION IS SAME AS VIDEO DURATION");
		}
		
	
	    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(6));
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(6));
		Thread.sleep(2000);
		
		video_text = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea).getText();
		System.out.println("INPUT TEXT OF VIDEO:-" +video_text);
		
		//Verify Segment should get selected & same should be displayed under video.
		assertion = video_text.equalsIgnoreCase("All Rights Reserved by QA Transperfect India.");
	    if (assertion == false) {
		report("f","Assertion failed while verifying TEXTAREA IS SAME AS SEGMENT");
	    }
		
 }
	
	
	    public void assertion() throws Exception {
	    	//Verify Segment should get selected & same should be displayed under video.
	    	assertion = video_text.equalsIgnoreCase("All Rights Reserved by QA Transperfect India.");
		    if (assertion == false) {
			report("f","Assertion failed while verifying TEXTAREA IS SAME AS SEGMENT");
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



