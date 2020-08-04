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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

public class Sub_764737 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_764737"+CommonElements.BROWSER+"Y1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String menu_Submission = "Submissions";
	


	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_764737");
		dataSet.put("TL_test_case_description",	"SUB-764737:Transcription Quality Checks: Use of Alt+q key");
		dataSet.put("TL_internal_testCase_ID", "764737");
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
			Pm_Transcription_Quality_Checks(SubmissionName, targetlanguage_1,false, true);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public void Pm_Transcription_Quality_Checks(String SubmissionName,String target, boolean complete, boolean back) throws Exception {

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
        Thread.sleep(10000);
        
        
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_button);
		Thread.sleep(3000);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_button, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_Transcription_QualityCheck_button  after Search");
		}
		Thread.sleep(3000);
		
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		 Thread.sleep(2000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		 Thread.sleep(2000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		 Thread.sleep(2000);
			
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		 Thread.sleep(2000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Test target for min RS,");
		 PM_user.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea, Keys.ENTER, false);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Test target for min RS," );
		 PM_user.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea, Keys.ENTER, false);
		 Thread.sleep(2000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Test target for min RS," );
		 PM_user.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea, Keys.ENTER, false);
//		 PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Too_manyLines);
		 
		 Thread.sleep(2000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		 Thread.sleep(2000);
		 PM_user.action().type_withKEYS(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea, Keys.ENTER, false);
		 
//			WebElement tabContent = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
//			Actions act= new Actions(General.driver);
//			act.sendKeys(tabContent, Keys.ENTER);
//			act.build().perform();

		 
		 
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		 Thread.sleep(2000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		 Thread.sleep(2000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		 Thread.sleep(2000);
			
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		 Thread.sleep(4000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea," ");
		 Thread.sleep(4000);
		 
		//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/* General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, "q"));
		 Thread.sleep(5000);*/
		 
		 PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_Q);
		 
		 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_RUN_button, 5);
		 if (assertion == false) {
				report("f","Assertion failed while verifying PM_Transcription_QualityCheck_RUN_button  after Search");
		 }
		 Thread.sleep(3000);
			
		 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().qualitycheck_rule_violation_message_for_EmptySubtitle, 5);
			 if (assertion == false) {
					report("f","Assertion failed while verifying qualitycheck_rule_violation_message_for_EmptySubtitle  after Search");
		 }
		 Thread.sleep(3000);
		 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().qualitycheck_rule_violation_message(2,"Empty Subtitle"), 5);
		 if (assertion == false) {
				report("f","Assertion failed while verifying qualitycheck_rule_violation_message_for_EmptySubtitle with id");
	     }
	     Thread.sleep(3000);
		//TODO Not required	
//		 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().qualitycheck_rule_violation_message_for_TooManyLines, 5);
//				 if (assertion == false) {
//						report("f","Assertion failed while verifying qualitycheck_rule_violation_message_for_TooManyLines  after Search");
//					}
		 Thread.sleep(3000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().qualitycheck_rule_violation_message(2,"Empty Subtitle"));
		 Thread.sleep(2000);
			 
			 
		 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2), 5);
		 if (assertion == false) {
		 report("f","Assertion failed while verifying qualitycheck_rule_violation_message_for_TooManyLines  after Search");
		 }
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentRecord(2), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying current record after Search");
		}
		Thread.sleep(3000);
				
				
				 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
				 Thread.sleep(2000);
				 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
				 Thread.sleep(2000);
				 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
				 Thread.sleep(2000);
					
				 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
				 Thread.sleep(4000);
				 PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Two line text\n" + 
				 		"Two line text");
 			     Thread.sleep(4000);
				 
				 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
				 Thread.sleep(2000);
			     General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
			     Thread.sleep(5000); 
			     PM_user.action().Navigate(menu_Submission);
			 	 Thread.sleep(2000);
			 	 PM_user.action().SearchSubmisson(SubmissionName);
			 	 Thread.sleep(2000);
			 	if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, "In_Progress"))) {
			 		System.out.println("INSIDE IF--------");
			 		General.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
			 	}
			 	Thread.sleep(6000);
			 	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			 	Thread.sleep(5000);
			 	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input);
			 	Thread.sleep(1000);
			 	General.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input, "1");
			    Thread.sleep(2000);
			    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_OpenSubmission_Update_button);
				Thread.sleep(4000);
				PM_user.action().Navigate(menu_ongoing);
				Thread.sleep(2000);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
				Thread.sleep(2000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
				Thread.sleep(2000);
				General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
				Thread.sleep(2000);
				General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
				Thread.sleep(2000);

				if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
					System.out.println("INSIDE IF--------");
						General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
				}
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
				Thread.sleep(10000);
				
				
				//Verify erify that more then two lines of errors are displayed under the Quality Check results. Refer SUB-1204
				PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_Q);
				 
				 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_RUN_button, 5);
				 if (assertion == false) {
						report("f","Assertion failed while verifying PM_Transcription_QualityCheck_RUN_button  after Search");
				 }
				 Thread.sleep(3000);
					
				 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().qualitycheck_rule_violation_message_for_EmptySubtitle, 5);
					 if (assertion == false) {
							report("f","Assertion failed while verifying qualitycheck_rule_violation_message_for_EmptySubtitle  after Search");
				 }
				 Thread.sleep(3000);
				 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().qualitycheck_rule_violation_message(1,"Too many lines"), 5);
				 if (assertion == false) {
						report("f","Assertion failed while verifying qualitycheck_rule_violation_message_for_EmptySubtitle with id");
			     }
			     Thread.sleep(3000);
				
			    PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_rightArrow);
				Thread.sleep(2000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_rightArrow);
				Thread.sleep(2000);
				PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_TaskSetting_button);
				Thread.sleep(2000);
			    PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_TaskSetting_button);
				Thread.sleep(2000);
				
				String Task_Setting = General.driver.findElement(By.xpath("//mat-tab-body//mat-list-item")).getText();
			    System.out.println("TASK SETTING DATA:-" +Task_Setting.length());
				System.out.println("TASK SETTING DATA:-" +Task_Setting);
				
				 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_TaskSetting_info("Max reading speed:"), 5);
				 if (assertion == false) {
						report("f","Assertion failed while verifying Max reading speed: ");
			     }
				 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_TaskSetting_info("Max chars per line:"), 5);
				 if (assertion == false) {
						report("f","Assertion failed while verifying Max chars per line: ");
			     }
				 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_TaskSetting_info("Min duration of a sub (sec):"), 5);
				 if (assertion == false) {
						report("f","Assertion failed while verifying Min duration of a sub (sec):");
			     }
				 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_TaskSetting_info("Max duration of a sub (sec):"), 5);
				 if (assertion == false) {
						report("f","Assertion failed while verifying Max duration of a sub (sec):");
			     }
				 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_TaskSetting_info("Min count of frames between subs:"), 5);
				 if (assertion == false) {
						report("f","Assertion failed while verifying Min count of frames between subs:");
			     }
				 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_TaskSetting_info("Max lines per sub:"), 5);
				 if (assertion == false) {
						report("f","Assertion failed while verifying Max lines per sub:");
			     }
				 
				 
				
 }
	
	
	    public void assertion() throws Exception {
	    	
	    	//The following data have to be displayed, in read only, in the below's order:
	    	//Note : This tc is block as need to check that all the data in task setting is read-only
	    	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_TaskSetting_info("Max lines per sub:"), 5);
			 if (assertion == false) {
					report("f","Assertion failed while verifying Max lines per sub:");
		     }
			   else {
				report("b", "All Assertion blocked.");
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


