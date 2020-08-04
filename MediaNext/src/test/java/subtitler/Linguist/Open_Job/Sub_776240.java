package subtitler.Linguist.Open_Job;


import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * 
 * @author pvaidya
 *
 */

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;
import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;
import org.openqa.selenium.Keys;

public class Sub_776240 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_776240"+CommonElements.BROWSER+"A1";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_AllJobs = "Jobs";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String EditSub_AltLeftArrow1_Speed ="0.75 X";
	String EditSub_AltLeftArrow2_Speed ="0.5 X";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_776240");
		dataSet.put("TL_test_case_description", "Sub_776240   :Use the same shortcut for Slow video in translation than in Transcription");
		dataSet.put("TL_internal_testCase_ID", "776240");
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
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
				}
			
			
			Thread.sleep(3000);
			General.action().logoutMethod();
			
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
    		Thread.sleep(2000);
			
    		VerifyReadingSpeedMethodUsingShortCuts();
		    assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().Ongoing_edit_shortKey_ForSlowPlay, 5);
		    if(assertion==false){
			report("f","Assertion failed while verifying Key Board Shortcut for Slow Play  After Shift+ ? ");
		    }
			
		    
		    PM_user.action().waitforelemenetpresent(TranslatorLocators.Locator().Translator_Ongoing_Edit_KeyboardShortCut_CancelBtn);
	        Thread.sleep(1000);
	        PM_user.action().Click(TranslatorLocators.Locator().Translator_Ongoing_Edit_KeyboardShortCut_CancelBtn);
		    Thread.sleep(1000);
		    
		    
		    PM_user.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
	        Thread.sleep(1000);
	        PM_user.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
		    Thread.sleep(1000);

	        PM_user.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_completeButton);
	        Thread.sleep(1000);
	        PM_user.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_completeButton);
	       	Thread.sleep(6000);
			
			
			Thread.sleep(3000);
			General.action().logoutMethod();
    		
    		General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
			
			
    		VerifyReadingSpeedMethodUsingShortCuts();
			
			
			
			
			
			} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
			
  
	public void VerifyReadingSpeedMethodUsingShortCuts() throws Exception {
		
		Thread.sleep(2000);
		Translator.action().Navigate(menu_to_claim);
		Thread.sleep(2000);
		Translator.action().trans_ToClaim(SubmissionName);
        Thread.sleep(2000);
        Translator.action().Navigate(menu_ongoing);
        Thread.sleep(2000);
        Translator.action().SearchSubmisson_andCheck(SubmissionName);
        Thread.sleep(4000);
		General.action().waitforelemenetpresent(CommonLocartors.Locator().Submission_Edit_icon);
		Thread.sleep(4000);
		General.action().Click(CommonLocartors.Locator().Submission_Edit_icon);
		Thread.sleep(10000);
		
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
			System.out.println("---IN CHROME---");
			PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_LEFT);
		}else {
			System.out.println("---IN FF---");
		General.action().type(CommonLocartors.Locator().EditSubmission_VideoUpperBorder,Keys.chord(Keys.ALT,Keys.ARROW_LEFT));
	    Thread.sleep(2000);
		}
	
		assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().EditSubmission_KeyboardShortcut_ForSpeed(EditSub_AltLeftArrow1_Speed), 5);
	    if(assertion==false){
		report("f","Assertion failed while verifying Reading Speed 0.75 X After ALT + Left Arrow");
	    }
		
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
			System.out.println("---IN CHROME---");
			PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_LEFT);
		}else {
			System.out.println("---IN FF---");
		General.action().type(CommonLocartors.Locator().EditSubmission_VideoUpperBorder,Keys.chord(Keys.ALT,Keys.ARROW_LEFT));
	    Thread.sleep(2000);
		}
		
		assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().EditSubmission_KeyboardShortcut_ForSpeed(EditSub_AltLeftArrow2_Speed), 5);
	    if(assertion==false){
		report("f","Assertion failed while verifying Reading Speed 0.5 X After ALT + Left Arrow");
	    }
	    
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
			System.out.println("---IN CHROME---");
			PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_LEFT);
		}else {
			System.out.println("---IN FF---");
		General.action().type(CommonLocartors.Locator().EditSubmission_VideoUpperBorder,Keys.chord(Keys.ALT,Keys.ARROW_LEFT));
	    Thread.sleep(2000);
		}
		
		assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().EditSubmission_KeyboardShortcut_ForSpeed(EditSub_AltLeftArrow2_Speed), 5);
	    if(assertion==false){
		report("f","Assertion failed while verifying Reading Speed Should Same (0.5 X) After ALT + Left Arrow");
	    }

	    
	    General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		Thread.sleep(4000);
		Translator.action().Click(TranslatorLocators.Locator().translator_submissionName_header(SubmissionName));
		Thread.sleep(500);
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
		System.out.println("---IN CHROME---");
		transcription_enterText_Video(KeyEvent.VK_SHIFT,KeyEvent.VK_SLASH);
		}else {
		System.out.println("---IN FF---");
		transcription_enterText_Video(KeyEvent.VK_SHIFT,KeyEvent.VK_SLASH);
		//General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.SHIFT, "?"));
//		Thread.sleep(2000);
	    Thread.sleep(4000);
		}

	    
	}
	
	 public void transcription_enterText_Video(int event1,int event2) throws Exception {
		  
		  System.out.println("******INSIDE METHOD transcription_enterText_Video()************");
		  
		  Robot robot = new Robot();
		  robot.keyPress(event1);
         Thread.sleep(500);
         robot.keyPress(event2);
         Thread.sleep(500);
		  robot.keyRelease(event2);
         Thread.sleep(500);
         robot.keyRelease(event1);
         Thread.sleep(500);
		    
		    System.out.println("******END OF METHOD transcription_enterText_Video()************");
	  }
				
			
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().Ongoing_edit_shortKey_ForSlowPlay, 10);
	    if(assertion==false){
		report("f","Assertion failed while verifying Key Board Shortcut for Slow Play  After Shift+ ? ");
	    }else {
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



