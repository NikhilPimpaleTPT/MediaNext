package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

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

public class Sub_624804 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_624804"+CommonElements.BROWSER+"W3";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
    List <WebElement> listofIds3;
	List <WebElement> listofIds2;
	List <WebElement> listofIds1;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_624804");
		dataSet.put("TL_test_case_description",	"SUB-624804:Calibrate the system (adding offset to match the timecode on the picture)");
		dataSet.put("TL_internal_testCase_ID", "624804");
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
			Pm_Transcription_Calibrate(SubmissionName, "New");
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void Pm_Transcription_Calibrate(String SubmissionName,String status) throws Exception {

		System.out.println("INSIDE Pm_Transcription_Merge_Subtitle  method()");

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
		Thread.sleep(10000);
		
		 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Calibration_message);
		  Thread.sleep(2000);
		  
		  assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Calibration_message, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying PM_Transcription_Calibration_message  after Search");
			}
		  
			 Thread.sleep(2000);
			 
	    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		
		String first_row_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1)).getText();
		System.out.println("FIRST ROW CONTENT BEFORE MERGE:- " +first_row_before);
		Thread.sleep(2000);
		
		String TimeIN_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(1)).getText();
		System.out.println("TIME IN BEFORE CALIBRATION:- " +TimeIN_before);
		Thread.sleep(2000);
		
		
		String TimeOUT_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeOut(1)).getText();
		System.out.println("TIME OUT BEFORE CALIBRATION:- " +TimeOUT_before);
		Thread.sleep(2000);
		
		
		
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, "c"));
		Thread.sleep(5000);
		
		 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Calibration_Dialog);
		  Thread.sleep(2000);
		   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Calibration_Dialog, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying PM_Transcription_Calibration_Dialog  after Search");
			}
		   Thread.sleep(2000);
		   General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Calibration_TimeFrame);
		   Thread.sleep(2000);
		   General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Calibration_TimeFrame, "06");
		   Thread.sleep(2000);
		   General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Calibration_Dialog_Calibrate_Button);
		   Thread.sleep(2000);
		   General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Calibration_Dialog_Calibrate_Button);
		
		   Thread.sleep(5000);
		String  first_row_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1)).getText();
		System.out.println("FIRST ROW CONTENT AFTER MERGE:- " +first_row_after);
		
		String TimeIN_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(1)).getText();
		System.out.println("TIME IN AFTER CALIBRATION:- " +TimeIN_after);
		Thread.sleep(2000);
		
		
		String TimeOUT_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeOut(1)).getText();
		System.out.println("TIME OUT AFTER CALIBRATION:- " +TimeOUT_after);
		Thread.sleep(2000);
		
		assertion = TimeIN_before != TimeIN_after;
		System.out.println("TIME IN AFTER CALIBRATION IS NOT SAME AS EALIER:- " +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying TIME IN AFTER CALIBRATION IS NOT SAME AS EALIER after Search");
		}

		assertion = TimeOUT_before != TimeOUT_after;
		System.out.println("TIME OUT AFTER CALIBRATION IS NOT SAME AS EALIER:- " +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying TIME IN AFTER CALIBRATION IS NOT SAME AS EALIER after Search");
		}
		
		
			
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, "c"));
		Thread.sleep(5000);
		
		 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Calibration_Dialog_Calibrate_Cancel_Button);
		  Thread.sleep(2000);
		  General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Calibration_Dialog_Calibrate_Cancel_Button);
		  Thread.sleep(4000);
		  
		  String TimeIN_after_cancel_calibration = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(1)).getText();
			System.out.println("TIME IN AFTER CANCEL CALIBRATION:- " +TimeIN_after_cancel_calibration);
			Thread.sleep(2000);
			
			
			String TimeOUT_after_cancel_calibration = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeOut(1)).getText();
			System.out.println("TIME OUT AFTER CANCEL CALIBRATION:- " +TimeOUT_after_cancel_calibration);
			Thread.sleep(2000);
		  
		  
		   assertion = TimeIN_after.equalsIgnoreCase(TimeIN_after_cancel_calibration) && TimeOUT_after.equalsIgnoreCase(TimeOUT_after_cancel_calibration);
		   System.out.println("TIME IN AND TIME OUT AFTER CANCEL CALIBRATION IS SAME AS EARLIER:-" +assertion);
			if (assertion == false) {
				report("f","Assertion failed while verifying PM_Transcription_Calibration_Dialog_Calibrate_Cancel_Button  after Search");
			}
		   Thread.sleep(2000);
		 }
	
	
	    public void assertion() throws Exception {
			 assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Calibration_Dialog, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PM_Transcription_Calibration_Dialog  after Search");
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











