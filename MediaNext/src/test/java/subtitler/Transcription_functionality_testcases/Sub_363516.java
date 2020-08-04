package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

public class Sub_363516 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_363516"+CommonElements.BROWSER+"Q1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
    String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
    String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
    List <WebElement> listofIds5;
	

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_363516");
		dataSet.put("TL_test_case_description","SUB-363516:Delete a Subtitle");
		dataSet.put("TL_internal_testCase_ID", "363516");
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
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(1000);
		    PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			Pm_Transcription_Submission(SubmissionName, "New");
			Thread.sleep(2000);
			
			
			
			
			} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void Pm_Transcription_Submission(String SubmissionName, String status) throws Exception {
		
		 System.out.println("INSIDE Pm_Complete_trans_Submission  method()");
		 
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, status)))
				{
				System.out.println("INSIDE IF--------");
				General.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
				}
			  Thread.sleep(6000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			  Thread.sleep(6000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
			  Thread.sleep(4000);
			  
			  for(int i =1; i<=2; i++){
			  
			  General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
			  Thread.sleep(2000);
			  List <WebElement> listofIds1=General.driver.findElements(Pm_User_Submission_Locators.Locator().ListofIDs);
				Thread.sleep(2000);
			   System.out.println("No of IDS before Alt + Enter--------"+listofIds1.size());
			  
			  
			  General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
			  Thread.sleep(3000);
			  
			  //TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
//			  General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//			  Thread.sleep(2000);
//			  General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, "n"));
//			  Thread.sleep(1000);
			  
			  PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_N);
			  
			  
			  General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Testing transcription");
			  Thread.sleep(1000);
			  General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, Keys.ENTER));
			  Thread.sleep(3000);
			  

			  List <WebElement> listofIds2=General.driver.findElements(Pm_User_Submission_Locators.Locator().ListofIDs);
				Thread.sleep(2000);
			   System.out.println("No of IDS after Alt + Enter--------"+listofIds2.size());
			  
			
			   assertion = listofIds1.size() != listofIds2.size();
			   System.out.println("SIZE OF SUBTITLE WOULD NOT BE SAME AFTER ALT + ENTER:-" +assertion);
				if (assertion == false) {
					report("f","Assertion failed while verifying Subtitle count after Alt + Enter  after Search");
				}
		     
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
				Thread.sleep(2000);
			    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
				Thread.sleep(2000);
				
				String Transcription_textarea_text = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1)).getText();
				System.out.println("INPUT TEXT OF VIDEO:-" +Transcription_textarea_text);
				
				Thread.sleep(2000);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
				
				String video_text = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea).getText();
				System.out.println("INPUT TEXT OF VIDEO:-" +video_text);
				
				
				assertion = Transcription_textarea_text.equalsIgnoreCase(video_text);
				System.out.println("TRANSCRIPTION AND VIDEO INPUT TEAXT IS SIMILAR:-" +assertion);
				if (assertion == false) {
					report("f","Assertion failed while verifying transcription textarea Subtitle is same as video text subtitle after Search");
				}
				
				//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
				/*Thread.sleep(2000);
				General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.BACK_SPACE));
				Thread.sleep(2000);
				*/
				
				 PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_BACK_SPACE);
				 
				 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
				 Thread.sleep(2000);
				  List <WebElement> listofIds3=General.driver.findElements(Pm_User_Submission_Locators.Locator().ListofIDs);
				  Thread.sleep(2000);
				  System.out.println("No of IDS after Alt + Back-Space--------"+listofIds3.size());
				  
		        
		          assertion = listofIds3.size() == listofIds1.size();
				   System.out.println("SIZE OF SUBTITLE WOULD BE SAME AS INITIAL SUBTITLES AFTER ALT + BACKSPACE:-" +assertion);
					if (assertion == false) {
						report("f","Assertion failed while verifying Subtitle count after Alt + Enter  after Search");
					}
			  }
			  
			  
			  General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
			  Thread.sleep(2000);
			  List <WebElement> listofIds4=General.driver.findElements(Pm_User_Submission_Locators.Locator().ListofIDs);
			   Thread.sleep(2000);
			   System.out.println("No of IDS Of subtitles--------"+listofIds4.size());
			  
				for(int i=1; i<listofIds4.size()-2; i++){
			  General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(i));
			  Thread.sleep(2000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(i));
			  Thread.sleep(2000);
			//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
			  /*General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, Keys.BACK_SPACE));
			  Thread.sleep(2000);*/
			  PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_BACK_SPACE);
			  
			  
				}
			  
			  
       General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
	  Thread.sleep(2000);
	  listofIds5=General.driver.findElements(Pm_User_Submission_Locators.Locator().ListofIDs);
	  Thread.sleep(2000);
	  System.out.println("No of IDS Of subtitles--------"+listofIds5.size());
	
	  
	   General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
	  Thread.sleep(2000);
	  assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_Transcription_textarea  after Search");
		}
	  
	  
	}
			
    
	
	

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_Transcription_textarea  after Search");
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

	




