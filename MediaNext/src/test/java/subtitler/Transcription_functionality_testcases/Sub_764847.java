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

public class Sub_764847 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_764847" + CommonElements.BROWSER + "W1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	double f_UPdwn;
	double f_dwn;
	int frames1_int_Before_x;
	int frames1_int_After_x;
	

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_764847");
		dataSet.put("TL_test_case_description", "SUB-764847:Press Next Frame/Prev frame at the same time : prev frame does not work anymore");
		dataSet.put("TL_internal_testCase_ID", "764847");
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
			Pm_Transcription_Frame_check(SubmissionName, targetlanguage_1, false, true);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f", "Execution level error was encountered.\n\nError log:\n\n" + Verify.action().getErrorBuffer(e));
		}

	}

	public void Pm_Transcription_Frame_check(String SubmissionName, String target, boolean complete,boolean back) throws Exception {

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
		Thread.sleep(10000);
		
		 //TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
//		/*General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		Thread.sleep(2000);
//		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.ARROW_UP));
//		Thread.sleep(5000);*/
		
		//Clicking on quality check button because alt + up is not performing any action (it doesn't stop video)
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_button);
      	Thread.sleep(2000);
		
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_UP);
		Thread.sleep(8000);
		
        //Verify The video should get paused.
		//IMP NOTE : THE VIDEO PLAY AND PAUSE BUTTON THE LOCATOR IS SHOWING APPOSITE(WHEN VIDEO PLAY - pause(TEXT IN HTML , WHEN VIDEO PAUSE - play-arrow(TEXT IN HTML)))
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_VideoBar_playButton, 5);
    	System.out.println("VIDEO STOPED:-" +assertion);
      	if (assertion == false) {
      	report("f","Assertion failed while verifying video pause after alt + up");
      	}
    	assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_VideoBar_pauseButton, 5);
    	System.out.println("VIDEO STOPED:-" +assertion);
      	if (assertion == false) {
      	report("f","Assertion failed while verifying video pause after alt + up");
      	}
     
      	General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
      	Thread.sleep(2000);
      	String frames_Before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(7)).getText();
	    System.out.println("frames_Before--->"+frames_Before);
	    int frames1_int_Before= Integer.parseInt(frames_Before);
	    System.out.println("frames1_int_Before-->"+frames1_int_Before);
      	
      	
      	Thread.sleep(5000);
      	PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_DOWN);
      	
     	String frames_After = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(7)).getText();
	    System.out.println("frames_After--->"+frames_After);
	    int frames1_int_After= Integer.parseInt(frames_After);
	    System.out.println("frames1_int_After-->"+frames1_int_After);
	    
	    String frames_Before_x = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(7)).getText();
	    System.out.println("frames_Before_x--->"+frames_Before_x);
	    frames1_int_Before_x= Integer.parseInt(frames_Before_x);
	    System.out.println("frames1_int_Before_x-->"+frames1_int_Before_x);
	    
	    //Observe the navigation frame per frame backward (Backward by 33)
	    System.out.println("frames1_int_After:"+frames1_int_After);
	    System.out.println("frames1_int_Before:"+frames1_int_Before);
	    assertion = frames1_int_After ==frames1_int_Before-1;
		if (assertion == false) {
			report("f", "Assertion failed while verifying  navigation frame per frame backward  after Search");
		}
		
		assertion = frames1_int_After <frames1_int_Before;
		if (assertion == false) {
			report("f", "Assertion failed while verifying  navigation frame per frame backward  after Search");
		}
		
		Thread.sleep(5000);
      	PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_DOWN);
		
      	String frames_After_x = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(7)).getText();
	    System.out.println("frames in video time box--->"+frames_After_x);
	    frames1_int_After_x= Integer.parseInt(frames_After_x);
	    System.out.println("FRAME BEFORE ALT DOWN-->"+frames1_int_After_x);
	    
		System.out.println("frames1_int_After_x:"+frames1_int_After_x);
		System.out.println("frames1_int_Before_x:"+frames1_int_Before_x);
	    assertion = frames1_int_After_x <frames1_int_Before_x; // alt down again
		if (assertion == false) {
			report("f", "Assertion failed while verifying  navigation frame per frame backward  after Search");
		}
		
		assertion = frames1_int_After_x ==frames1_int_Before_x-1;
		if (assertion == false) {
			report("f", "Assertion failed while verifying  navigation frame per frame backward  after Search");
		}
	}
		
		
		
	public void assertion() throws Exception {
		assertion = frames1_int_After_x ==frames1_int_Before_x-1;
		if (assertion == false) {
			report("f", "Assertion failed while verifying  navigation frame per frame backward  after Search");
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
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan, Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}


}
