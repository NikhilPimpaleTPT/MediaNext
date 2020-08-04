package subtitler.project_manager.Open_Jobs;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: Create a submission from the attached test files.
  Claimed it in translator login
 */ 

public class Sub_1595275 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1595275"+CommonElements.BROWSER+"A1";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String targetlanguage_1 = "German (Germany)";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_Completed = "Completed";
	String secondLineText="Transcription Segment line 1\n" + 
			"Transcription Segment line 2";
	String firstSegment_timeIn;
	String firstSegment_timeout;
	String secoundSegment_timeIn;
	String secoundSegment_timeOut;
	String secoundSegment_readingSpeed;
	@BeforeMethod
	public void setup() throws Exception {General.action().startSystem("SUB_1595275");
		dataSet.put("TL_test_case_description", "SUB-1595275:Reading speed after segment split.");
		dataSet.put("TL_internal_testCase_ID", "1595275");
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
			// TODO NEW IMPL OF SUBMISSION CREATION , USE SRT FILE ATTACHED IN TEST CASE
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "",false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_ACCENTED_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(8000);
			//VERIFY SUBMISSION NAME
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(1000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(1000);
			General.action().logoutMethod();
			Thread.sleep(1000);
			// trans login
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
	        Thread.sleep(1000);
	        Translator.action().Navigate(menu_to_claim);
	        Thread.sleep(1000);
	        Translator.action().trans_ToClaim(SubmissionName);
	        Thread.sleep(1000);
	        Translator.action().Navigate(menu_ongoing);
	        Thread.sleep(1000);
	        translate_onGoing_submission(SubmissionName, targetlanguage_1, true, false);
			Thread.sleep(2000);
	         
			
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	
	public void translate_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
		
		 System.out.println("INSIDE trans_Ongoing  method()");
		 
		 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
		 Thread.sleep(1000);
		 General.action().Click(TranslatorLocators.Locator().translator_search_input);
		 Thread.sleep(1000);
		 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
		 Thread.sleep(1000);
		 General.action().type(TranslatorLocators.Locator().translator_search_input, SubmissionName);
		 Thread.sleep(2000);
			
		 if(Verify.action().VerifyElementPresent(TranslatorLocators.Locator().check_trans_Target_Lang(SubmissionName, target)))
		 {
		 System.out.println("INSIDE IF--------");
		 General.action().Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName, target));
		 }
		 Thread.sleep(1000);
		 General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
		 Thread.sleep(8000);
		 
		 General.action().Click(TranslatorLocators.Locator().translator_Video_play);
		 Thread.sleep(4000);
		 
		 //Get time in, time out and reading speed of 1st and 2nd segment
		 firstSegment_timeIn=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText();
		 System.out.println(firstSegment_timeIn);
		 firstSegment_timeout=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(1)).getText();
		 System.out.println(firstSegment_timeout);
		 secoundSegment_timeIn=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(2)).getText();
		 System.out.println(secoundSegment_timeIn);
	     secoundSegment_timeOut=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(2)).getText();
	     System.out.println(secoundSegment_timeOut);
	     secoundSegment_readingSpeed=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_ReadingSpeed(2)).getText();
	     System.out.println(secoundSegment_readingSpeed);
			
	     //Enter 2 line text in 1st segment and Split it
		 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		 Thread.sleep(1000);
		 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		 Thread.sleep(1000);
		 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		 Thread.sleep(1000);
		 
		 if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
		 General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),secondLineText);
		 Thread.sleep(5000);
		 }else {
			 General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),"Transcription Segment line 1\r\n" + 
			 		"Transcription Segment line 2");
			 
		 }
		 
	     
	     General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		 Thread.sleep(1000);
		 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		 Thread.sleep(10000);
		 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		 Thread.sleep(10000);
	     General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),Keys.chord(Keys.ARROW_UP));
		 Thread.sleep(3000);
		 for(int i=1;i<=7;i++) {
		 //General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),Keys.chord(Keys.ARROW_LEFT));
		 //Thread.sleep(3000);
		 Robot robot = new Robot();
		 robot.keyPress(KeyEvent.VK_LEFT);
		 Thread.sleep(2000);
		 robot.keyPress(KeyEvent.VK_LEFT);
		 Thread.sleep(2000);
		 }
		 Thread.sleep(2000);
		 if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
		 General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_P);
		 Thread.sleep(1000);
		 }else {	
		 Translator.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),Keys.chord(Keys.ALT, "P"));
		 Thread.sleep(1000);
		       }
		 Thread.sleep(5000);
		 
		 //Verify Time in and reading speed of other segments should not effect after splitting the segment.
		 String thirdSegment_timeIn=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(3)).getText();
		 System.out.println("thirdSegment_timeIn"+thirdSegment_timeIn);
		 assertion =General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(3)).getText().contains(secoundSegment_timeIn);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying sement of transcription screen .");
				
		 }
	     String thirdSegment_timeOut=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(3)).getText();
	     System.out.println("thirdSegment_timeOut"+thirdSegment_timeOut);
	     assertion =General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(3)).getText().contains(secoundSegment_timeOut);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying sement of transcription screen .");
				
		 }
			    
	     String thirdSegment_readingSpeed=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_ReadingSpeed(3)).getText();
	     System.out.println("thirdSegment_readingSpeed"+thirdSegment_readingSpeed);
	     assertion =General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_ReadingSpeed(3)).getText().contains(secoundSegment_readingSpeed);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying sement of transcription screen .");
				
		 }
	     
	     //Verify Reading speed of any subtitle should not set to infinity.
	     assertion =!General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_ReadingSpeed(3)).getText().contains("Infinity");
	     if (assertion == false) {
		 report("f","Assertion failed while verifying sement of transcription screen .");
				
		 }
			    
			    
		System.out.println("EOM trans_Ongoing  method()");
	}
	
	
	public void Trans_editText_alt_p(int event1,int event2) throws Exception {
		  
		  System.out.println("******Trans_editText_alt_p()************");
		  
		Robot robot = new Robot();
		robot.keyPress(event1);
        Thread.sleep(2000);
        robot.keyPress(event2);
        Thread.sleep(2000);
		robot.keyRelease(event2);
        Thread.sleep(2000);
        robot.keyRelease(event1);
        Thread.sleep(2000);
		    
		    System.out.println("******Trans_editText_alt_p()************");
	  }
	 public void assertion() throws Exception {
	 //Verify Reading speed of any subtitle should not set to infinity.
	  assertion =!General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_ReadingSpeed(3)).getText().contains("Infinity");
	  if (assertion == false) {
	  report("f","Assertion failed while verifying sement of transcription screen .");
						
	  }else {
	  report("p", "All Assertion Passed.");
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