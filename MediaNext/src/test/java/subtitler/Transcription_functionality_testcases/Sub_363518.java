package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_363518 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_363518"+CommonElements.BROWSER+"A1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
    String TranslatorGroupName = "Common_group";
    String menu_Submission = "Submissions";
	String targetlanguage_1 = "English (United States)";
    String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
    String TextArea_RS;
    String Video_RS;
	
	

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_363518");
		dataSet.put("TL_test_case_description","SUB-363518: Add a Subtitle with automatic out point");
		dataSet.put("TL_internal_testCase_ID", "363518");
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
			PM_user.action().Navigate(menu_Submission);
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
			Pm_Transcription_Time_Out(SubmissionName, "New");
			Thread.sleep(2000);
			
			
			
			
			} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void Pm_Transcription_Time_Out(String SubmissionName, String status) throws Exception {
		
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
			  
			//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
			/*  General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
			  Thread.sleep(4000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
			  Thread.sleep(2000);
			  General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, "n"));
			  Thread.sleep(1000);*/
			  
			  PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_N);
			  
			  General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea," Testing transcription");
			  Thread.sleep(1000);
			  General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, Keys.ENTER));
			  Thread.sleep(3000);
			  
			 
			  General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
			  Thread.sleep(3000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
			  Thread.sleep(2000);
			  
			  String TIME_IN = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(6)).getText();
			  System.out.println("TIME IN INPUT VALUE:-" +TIME_IN);
			  Thread.sleep(2000);
			  String TIME_IN_1=TIME_IN.replace(";", ":");
			  System.out.println("TIME IN INPUT VALUE TIME_IN_2:-" + TIME_IN_1);

			  String TIME_OUT = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeOut(5)).getText();
			  System.out.println("TIME OUT INPUT VALUE:-" +TIME_OUT);
			  String TIME_IN_2=TIME_OUT.replace(";", ":");
			  System.out.println("TIME OUT INPUT VALUE TIME_IN_2:-" + TIME_IN_2);
			  Thread.sleep(2000);
			  
			  
			  
			  DateFormat sdf = new SimpleDateFormat("HH:MM:SS:FF");
			  Date date = sdf.parse(TIME_IN); 
			  System.out.println("date-->"+date);
	          Date date1 = sdf.parse(TIME_OUT);  
	          System.out.println("date-->"+date1);
	          if(assertion =date.after(date1)){
	            	System.out.println("OUT IS GREATER THAN IN");
	            }else{
	            	System.out.println("OUT IS LESSER THAN IN");
	            }
			  
	          String Textarea_header = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Textarea_header).getText();
	          System.out.println("TRANSCRIPTION TEXTAREA HEADER:- " +Textarea_header);
			  
	          General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Textarea_header);
			  Thread.sleep(1000);
			  
			  assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Textarea_header).getText().contains("Time in");
				if (assertion == false) {
					report("f","Assertion failed while verifying PM_Transcription_Textarea_header  after Search");
			  }
			  Thread.sleep(3000);
			  assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Textarea_header).getText().contains("Time out");
				if (assertion == false) {
					report("f","Assertion failed while verifying PM_Transcription_Textarea_header  after Search");
			  }
			  assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Textarea_header).getText().contains("Duration");
					if (assertion == false) {
						report("f","Assertion failed while verifying PM_Transcription_Textarea_header  after Search");
			  }
			  assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Textarea_header).getText().contains("Reading Speed");
						if (assertion == false) {
							report("f","Assertion failed while verifying PM_Transcription_Textarea_header  after Search");
			  }
			  
	}	
			  
				
	
	

	public void assertion() throws Exception {
		 assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Textarea_header).getText().contains("Reading Speed");
			if (assertion == false) {
				report("f","Assertion failed while verifying PM_Transcription_Textarea_header  after Search");
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

	







