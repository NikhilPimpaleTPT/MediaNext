package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: Manually selected subtitle move to top of the grid having three subtitles showing prior to it.

 */ 

public class Sub_1648051 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1648051"+CommonElements.BROWSER+"A5";
	 String OrganizationName = "Subtitle_Common_orgs";
	    String WorkflowName = "One_Step_Workflow";
		String fileDirName = "common";
		String menu_Submission = "Submissions";
		String transcGroupName = "Common_group";
		String TranslatorGroupName = "Common_group";
		String ReviewGroupName = "Common_group";
		String targetlanguage = "German (Germany)";
		String menu_AllJobs = "Jobs";
		String menu_to_claim = "To Claim";
		String menu_ongoing = "Ongoing";
		String menu_completed = "Completed";
		 String folder_1648051 = "1648051";
		Boolean assertion = true;
		
		
		@BeforeMethod
		public void setup() throws Exception {
			General.action().startSystem("SUB_1648051");
			dataSet.put("TL_test_case_description", "SUB-1648051: :Manually selected subtitle move to top of the grid having three subtitles showing prior to it.");
			dataSet.put("TL_internal_testCase_ID", "1648051");
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
					Thread.sleep(10000);
					PM_user.action().Navigate(menu_Submission);
					// TODO NEW IMPL OF SUBMISSION CREATION USING 2K SRT
					PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
					PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
					PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_LARGE_VIDEO,false,CommonElements.action().FILE_COMMON_LARGE_VIDEO_ASSET_ID);
					PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+folder_1648051);
					PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
					//As large file
					Thread.sleep(80000);

					//SEARCH SUBMISSION AND CHECK
					PM_user.action().SearchSubmisson_andCheck(SubmissionName);
					Thread.sleep(2000);
					assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying SubmissionName  after Search");
					}
					General.action().logoutMethod();

					// tran login
					General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
					Thread.sleep(10000);
					Translator.action().Navigate(menu_to_claim);
					Thread.sleep(1000);
					Translator.action().trans_ToClaim(SubmissionName);
					Thread.sleep(1000);
					Translator.action().Navigate(menu_ongoing);
					Thread.sleep(1000);
					translate_onGoing_submission(SubmissionName, targetlanguage,true, false);
					Thread.sleep(1000);
					
			} catch (Exception e) {
		         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	            }
	  }
		
		
		
		public void translate_onGoing_submission(String SubmissionName,String target,boolean complete, boolean back) throws Exception {

			System.out.println("INSIDE Trans_Ongoing_Import_translation_InvalidFile  method()");

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
			Thread.sleep(10000);
			//Not Needed
//			General.action().Click(TranslatorLocators.Locator().translator_Video_play_arrow);
//			Thread.sleep(5000);
			
			PM_user.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(2000);
			   
			General.action().scrollDown(3);
		    Thread.sleep(3000);
			
			//CLICK ON SEGMENT NO 15 
	        PM_user.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(17));
			Thread.sleep(2000);
			PM_user.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(17));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(12), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying more 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(13), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying more 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(14), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying more 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(15), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying more 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(16), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying more 3 segment after operation performed");
			}
			
			//When manually selecting a subtitle on the grid view, it should not move to top of the grid and segment 1 sould display as before
		    
		    //CLICK ON 14 SEGMENT AND ADD COMMENT
	        PM_user.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(16));
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab);
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput);
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput,"Comment for 16 segment");
			Thread.sleep(5000);
			General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab_commentDialogBox_CommentButton);
			Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(11), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying more 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(12), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying more 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(13), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying more 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(14), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying more 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(15), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying more 3 segment after operation performed");
			}
			
			//VERIFY THAT 14TH SEGMENT GET HIGHLIGHTED
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_currentSegment(16), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 14th segment is get highlighted");
			}
			
			General.action().scrollup(1);
		    Thread.sleep(3000);
			
			//CLICK ON 8 SEGMENT ADD COMMENT AND VERIFY 8 SEGMENT GET HIGHLIGHTED
			PM_user.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(8));
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab);
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput);
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput,"Comment for 8 segment");
			Thread.sleep(5000);
			General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab_commentDialogBox_CommentButton);
			Thread.sleep(2000);
			
			General.action().Click(TranslatorLocators.Locator().Trans_ongoing_comment_button);
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().translator_commentIds(16,"Comment for 16 segment"));
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab_commentDialogBox_CommentButton);
			Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_currentSegment(16), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 14th segment is get highlighted");
			}
			
		    //TODO NOT REQUIRED
//			General.action().Click(TranslatorLocators.Locator().Trans_ongoing_comment_button);
//			Thread.sleep(2000);
//			General.action().Click(TranslatorLocators.Locator().translator_commentIds(18,"Comment for 18th segment"));
//			Thread.sleep(2000);
//			General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab_commentDialogBox_CommentButton);
//			Thread.sleep(2000);
//		    
//			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_currentSegment(18), 5);
//			if (assertion == false) {
//			report("f","Assertion failed while verifying 8th segment is get highlighted");
//			}
			
			//VERIFY THAT THREE SEGMETS ARE PRESENT BEFORE CURRENT
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(15), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(16), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(17), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			//CLICK ON QUALITY CHECK BUTTON AND CLICK ON 44 SEGMENT
         	General.action().Click(TranslatorLocators.Locator().Trans_ongoing_qualitycheck_button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().Trans_ongoing_qualitycheck_Run_button);
			Thread.sleep(3000);
			
			General.action().Click(TranslatorLocators.Locator().translator_commentTab_SegmentIds(1));
			Thread.sleep(3000);
			General.action().Click(TranslatorLocators.Locator().translator_commentTab_SegmentIds(1));
			Thread.sleep(3000);
			if(CommonElements.action().BROWSER.contains("FIREFOX")) {
			for(int i=1;i<=19;i++){
			PM_user.action().transcription_keyEnvents(KeyEvent.VK_PAGE_DOWN);
			}
		    }else {
		    Thread.sleep(1000);
			General.action().scrollDown(19);
			Thread.sleep(3000);
			
			
		    }
			Thread.sleep(3000);
			General.action().Click(TranslatorLocators.Locator().translator_commentTab_SegmentIds_String(44));
			Thread.sleep(3000);
			
			//VERIFY 44 SEGMENT GET HIIGHLIGHTED
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_currentSegment(44), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 44th segment is get highlighted");
			}
			
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(41), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(42), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(43), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			//Verify 30 th segment is not displayed
			assertion = !Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(30), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			
			//CLICK ON 45 SEGMENT AND PRESS ALT+G
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(45));
			Thread.sleep(3000);
			//Using the Go to Subtitle ID command.
			Thread.sleep(2000);
			PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_G);
			Thread.sleep(3000);	
			
			//GIVE THE SEARCH ID 
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_GO_to_Subtitle, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying Pm_transcription_GO_to_Subtitle  after Search");
			}
			
			Thread.sleep(3000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_GO_to_Subtitle_input);
			Thread.sleep(3000);
			General.action().type(TranslatorLocators.Locator().translator_GO_to_Subtitle_input,"49");
			Thread.sleep(3000);
			General.action().Click(TranslatorLocators.Locator().translator_GO_to_Subtitle_GO_Button);
	    	Thread.sleep(2000);
			
			//VERIFY THAT THREE SEGMETS ARE PRESENT BEFORE CURREN
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(46), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(47), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(48), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			assertion =Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(49), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			//Verify 41 th segment is not displayed
			assertion =!Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(41), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			PM_user.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(50));
			Thread.sleep(2000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(48),"Traditionnellement");
			Thread.sleep(5000);
			PM_user.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(50));
			Thread.sleep(2000);
			
			//Using the Find and Replace.
			PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_H);
			Thread.sleep(3000);	
			
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_Find_and_Replace, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying  PM_transcription_Find_and_replace  after Search");
			}

			String NumberOfOcurrence_before = General.driver.findElement(TranslatorLocators.Locator().Trans_NumberOfOccurences).getText();
			System.out.println("NUMBER OF OCCURENCE BEFORE:-" + NumberOfOcurrence_before);

			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_Find_and_replace_findInputBox);
			Thread.sleep(3000);
			General.action().Click(TranslatorLocators.Locator().Trans_Find_and_replace_findInputBox);
			Thread.sleep(2000);
			General.action().type(TranslatorLocators.Locator().Trans_Find_and_replace_findInputBox,"Traditionnellement");
			Thread.sleep(5000);
			
			General.action().Click(TranslatorLocators.Locator().Trans_Find_and_replace_cancelButton);
			Thread.sleep(2000);
			
		    //Verify transcription segment after alt + f
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_currentSegment(48), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(45), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(46), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(47), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			//Verify 30 th segment is not displayed
			assertion = !Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(38), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			PM_user.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(52));
			Thread.sleep(2000);
			
			//Using the Alt+Enter keyboard shortcut.
			Thread.sleep(2000);
			PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_ENTER);
			Thread.sleep(3000);	
			
			//VERIFY THAT THREE SEGMETS ARE PRESENT BEFORE CURREN
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(49), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(50), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(52), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			//Verify 43 th segment is not displayed
			assertion = !Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SegmentArea_segmentId(43), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			Thread.sleep(4000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(53));
			Thread.sleep(2000);
			
			 
			General.action().driver.navigate().refresh();
			
			
			
			
			
		}
		
		
		public void assertion() throws Exception {
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_ongoing_indexCaption("Go to #53"), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying caption id after refresh");
			}else {
					report("p", "String Is NOT Same , Not Contains Extra Spaces and Extra Return(String Without Spaces and Extra Return)");
				}
		}
					
					
					@AfterMethod
					public void tearDown() throws Exception {
					General.action().stopsystem();
					}

					public void report(String result, String notes) throws Exception
					{
					TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild,result,Subtitler_TestRail_Common_Properties.assignedTo,notes);
					if(result == "f")
						assertTrue(false);

					}


					}