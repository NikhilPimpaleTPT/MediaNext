package subtitler.project_manager.Open_Jobs;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This testcase verifies the max. number of lines for a subtitle at the submission level
 *
 */ 

public class Sub_1583092 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1583092"+CommonElements.BROWSER+"ZF4";
	String WorkflowName = "Three_Step_Transc_Workflow";
	String OrganizationName = "Subtitle_Common_orgs";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_submission = "Submissions";
	String defaultValue_maxLinePerSub="2";
	String fourLineSegment="Transcription Segment line 1\n" + 
			"Transcription Segment line 2\n" + 
			"Transcription Segment line 3\n" + 
			"Transcription Segment line 4";
	String segmentText="Shaky Hands Shaky Hands";
	String lengthOfTheSegment;
	Boolean assertion = true;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1583092");
		dataSet.put("TL_test_case_description", "SUB-1583092:Define the max. number of lines for a subtitle at the submission level.");
		dataSet.put("TL_internal_testCase_ID", "1583092");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			//Create Submission
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_submission);
			Thread.sleep(1000);
			CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow_ThreeStepWorkflow(OrganizationName, WorkflowName,"transcription",transcGroupName,"Trans",TranslatorGroupName,true, "Review", ReviewGroupName, true);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
		
			//SEARCH SUBMISSION AND CHECK
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			Thread.sleep(6000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			Thread.sleep(4000);
			
			assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input).getAttribute("value").contains(defaultValue_maxLinePerSub);
		     if (assertion == false) {
			 report("f","Assertion failed while verifying default value for the 'Max line per sub' is 2. ");
					
			}
			
		    PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input);
			Thread.sleep(1000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input);
			Thread.sleep(1000);
			 
			PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input);
			Thread.sleep(1000);
		    PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input,"4");
			Thread.sleep(2000);
			
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_settings_updateButton);			
			Thread.sleep(6000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
			Thread.sleep(8000);
			
			 General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_rightArrow);
		     Thread.sleep(2000);
		     General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_rightArrow);
		     Thread.sleep(2000);
		     General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_rightArrow);
		     Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_TaskSetting_button, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying task setting button");
			}
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_TaskSetting_button);
			Thread.sleep(4000);
			  
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_openJob_TaskSetting_linePerSub(4), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying task setting button");
			}
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
			Thread.sleep(4000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
			Thread.sleep(4000);
			PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
			Thread.sleep(1000);
			
			if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
			
			General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,fourLineSegment);
			Thread.sleep(3000);
			}else {
				
			General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Transcription Segment line 1\r\n" + 
					"Transcription Segment line 2\r\n" + 
					"Transcription Segment line 3\r\n" + 
					"Transcription Segment line 4\r\n" + 
					"");
			Thread.sleep(3000);
				
			}
			
			System.out.println(fourLineSegment);
			String text1=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1)).getText();
			System.out.println(text1);
			Thread.sleep(3000);
			 assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1)).getText().contains(fourLineSegment);
		     if (assertion == false) {
			 report("f","Assertion failed while verifying sement of transcription screen .");
					
			 }
			
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
			Thread.sleep(4000);
		     
		    Thread.sleep(2000);
			General.action().driver.navigate().back();
			Thread.sleep(1000);
			 
			General.action().driver.navigate().refresh();
			Thread.sleep(1000);
			PM_user.action().Navigate(menu_submission);
			Thread.sleep(1000);
			 
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
			}
				
			Thread.sleep(6000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
		    Thread.sleep(4000);
				
			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input);
			Thread.sleep(1000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input);
			Thread.sleep(1000);
				 
		    PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input);
			Thread.sleep(1000);
		    PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input,"2");
		    Thread.sleep(2000);
				
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_settings_updateButton);			
			Thread.sleep(6000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
			Thread.sleep(4000);
			 
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_button);
			Thread.sleep(3000);
			 
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_RUN_button);
			Thread.sleep(2000);
			 
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().qualitycheck_rule_violation_message_forIndex_TooManyLines(1), 5);
			if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
			}
		     
		     
		     General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
			 Thread.sleep(2000);
			 General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
			 Thread.sleep(4000);
			 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
			 Thread.sleep(1000);
			 General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,segmentText);
			 Thread.sleep(3000);
			 
			 int size=segmentText.length();
			 lengthOfTheSegment=Integer.toString(size);
			 System.out.println("Length of the text INT is--->"+size);
			 System.out.println("Length of the text STRING  is--->"+lengthOfTheSegment);
			 assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_sixOptions_ReadingSpeed(2)).getText().contains("3.98");
		     if (assertion == false) {
			 report("f","Assertion failed while verifying reading speed.");
					
			 }
			 
		     assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_LineCount(2)).getText().contains(lengthOfTheSegment);
		     if (assertion == false) {
			 report("f","Assertion failed while verifying reading speed.");
					
		     }
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	
	
	 public void CreateSubmisson_Step1(String ReadingSpeed,String MaxCharperLine,String MindurationVSreadingSpeed,String MaxdurationVSreadingSpeed) throws Exception{
		 System.out.println("INSIDE method CreateSubmisson_Step1()\n");
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		 Thread.sleep(1000);
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
		 Thread.sleep(1000);
		 
		 assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input).getAttribute("value").contains(defaultValue_maxLinePerSub);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying default value for the 'Max line per sub' is 2. ");
				
		 }
	     
	     PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input,"-1");
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Minreadingspeed);
		 Thread.sleep(1000);
		 
		 
		 assertion =Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_ErrorMsgForMaxLinesPerSub("Value is below the limit 1"), 5);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying Error message ");
				
		 }
	     
	     PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input,"7");
		 Thread.sleep(1000);
		 
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Minreadingspeed);
		 Thread.sleep(1000);
		 
		 
		 assertion =Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_ErrorMsgForMaxLinesPerSub("Value is above the limit 4"), 5);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying Error message ");
				
		 }
	     
	     PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input,"2");
		 Thread.sleep(1000);
	     
		 
		 
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input,ReadingSpeed);
		 Thread.sleep(1000);
			
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,MaxCharperLine);
		 Thread.sleep(1000);
			
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,MindurationVSreadingSpeed);
		 Thread.sleep(1000);
			
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,MaxdurationVSreadingSpeed);
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		 Thread.sleep(1000);
			
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		 Thread.sleep(4000);
	     System.out.println("EOM CreateSubmisson_Step1()\n");

		}
	
	
	
	
	public void assertion() throws Exception {
		 assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_LineCount(2)).getText().contains(lengthOfTheSegment);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying reading speed.");
				
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
