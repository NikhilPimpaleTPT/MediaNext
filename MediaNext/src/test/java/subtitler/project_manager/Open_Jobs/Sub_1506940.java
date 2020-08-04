package subtitler.project_manager.Open_Jobs;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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
 *Summary: Manually selected subtitle move to top of the grid having three subtitles showing prior to it.

 */ 

public class Sub_1506940 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1506940"+CommonElements.BROWSER+"W23";
	 String OrganizationName = "Subtitle_Common_orgs";
	    String WorkflowName = "transc_Step_Workflow";
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
		Boolean assertion = true;
		
		
		@BeforeMethod
		public void setup() throws Exception {
			General.action().startSystem("SUB_1506940");
			dataSet.put("TL_test_case_description", "SUB-1506940: :Manually selected subtitle move to top of the grid having three subtitles showing prior to it.");
			dataSet.put("TL_internal_testCase_ID", "1506940");
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
					Thread.sleep(2000);
					PM_user.action().Navigate(menu_Submission);
					// TODO NEW IMPL OF SUBMISSION CREATION USING 2K SRT
					PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
					PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
					PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_LARGE_VIDEO,false,CommonElements.action().FILE_COMMON_LARGE_VIDEO_ASSET_ID);
					PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_2K_SRT_FOLDER);
					PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
					Thread.sleep(50000);

					//SEARCH SUBMISSION AND CHECK
					PM_user.action().SearchSubmisson_andCheck(SubmissionName);
					Thread.sleep(2000);
					assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying SubmissionName  after Search");
					}
					
                    //CLAIM JOB
					Translator.action().Navigate(menu_to_claim);
					Thread.sleep(1000);
					PM_user.action().PM_ToClaim(SubmissionName);
					Thread.sleep(1000);
					//GO TO ONGOING
					PM_user.action().Navigate(menu_ongoing);
					Thread.sleep(2000);
					//Transcription steps
					PM_transcription_ongoing(SubmissionName,targetlanguage,false,true);
				    Thread.sleep(2000);
					
					
					
					
					
					
	
			} catch (Exception e) {
		         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	            }
	  }
		
		
		public void PM_transcription_ongoing(String SubmissionName, String target,boolean complete ,boolean back ) throws Exception {

			System.out.println("INSIDE PM_transcription_onGoing_ReadingSpeed_Submission()");

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
			
			//CLICK ON ENDIT BUTTON
			Thread.sleep(4000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
	        Thread.sleep(10000);
	        	
	        //CLICK ON SEGMENT NO 15 
	        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(15));
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(15));
			Thread.sleep(2000);
			
			//When manually selecting a subtitle on the grid view, it should not move to top of the grid and segment 1 sould display as before
			assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1)).getText().contains("Jellyfish at the Monterey Aquarium.");
		    if (assertion == false) {
			report("f","Assertion failed while verifying sement 1 of transcription screen .");
			}
		    
		    //CLICK ON 14 SEGMENT AND ADD COMMENT
	        PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(14));
			Thread.sleep(2000);
		    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Styling_button);
		    Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Styling_SegmentHistory);
			Thread.sleep(1000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentInput);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentInput,"Comment for 14 segment");
			Thread.sleep(5000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentDialogBox_CommentButton);
			Thread.sleep(2000);
			
			//VERIFY THAT 14TH SEGMENT GET HIGHLIGHTED
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_currentSegment(14), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 14th segment is get highlighted");
			}
			
			
			//CLICK ON 8 SEGMENT ADD COMMENT AND VERIFY 8 SEGMENT GET HIGHLIGHTED
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(8));
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Styling_button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Styling_SegmentHistory);
			Thread.sleep(1000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentInput);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentInput,"Comment for 8 segment");
			Thread.sleep(5000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentDialogBox_CommentButton);
			Thread.sleep(2000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_commentTab);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_commentIds(14,"Comment for 14 segment"));
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentDialogBox_CommentButton);
			Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_currentSegment(14), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 14th segment is get highlighted");
			}
		    
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_commentTab);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_commentIds(8,"Comment for 8 segment"));
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentDialogBox_CommentButton);
			Thread.sleep(2000);
		    
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_currentSegment(8), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 8th segment is get highlighted");
			}
			
			//VERIFY THAT THREE SEGMETS ARE PRESENT BEFORE CURREN
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_threeSegmentAfterChangingFeatures(1,"Id"), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(5), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(6), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(7), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			//CLICK ON QUALITY CHECK BUTTON AND CLICK ON 44 SEGMENT
         	General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_RUN_button);
			Thread.sleep(3000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentIds(1));
			Thread.sleep(3000);
         	General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentIds(1));
			Thread.sleep(3000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentIds(1));
			Thread.sleep(3000);
			
			if(CommonElements.BROWSER.contains("_EGCH_")) {
				scrollDown(41);
				Thread.sleep(3000);
			}else{
			
			scrollDown(41);
			Thread.sleep(3000);
			
			}
			

			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentIds(44));
			Thread.sleep(3000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentIds(44));
			Thread.sleep(3000);
			
			//VERIFY 44 SEGMENT GET HIIGHLIGHTED
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_currentSegment(44), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 44th segment is get highlighted");
			}
			
			
			//VERIFY THAT THREE SEGMETS ARE PRESENT BEFORE CURREN
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_threeSegmentAfterChangingFeatures(1,"Id"), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(40), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(41), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(42), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			
			//Verify 30 th segment is not displayed
			assertion = !Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(30), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
	        Thread.sleep(3000);
			//CLICK ON 45 SEGMENT AND PRESS ALT+G
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(45));
			Thread.sleep(3000);
			
			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_pause, 5)==true) {
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_pause);
				Thread.sleep(3000);
				
			}
			//Using the Go to Subtitle ID command.
			Thread.sleep(2000);
			PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_G);
			Thread.sleep(3000);	
			
			//GIVE THE SEARCH ID 
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_transcription_GO_to_Subtitle, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying Pm_transcription_GO_to_Subtitle  after Search");
			}
			
			Thread.sleep(3000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_transcription_GO_to_Subtitle_input);
			Thread.sleep(3000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_transcription_GO_to_Subtitle_input,"49");
			Thread.sleep(3000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_transcription_GO_to_Subtitle_GO_Button);
	    	Thread.sleep(2000);
	    				
			//VERIFY THAT THREE SEGMETS ARE PRESENT BEFORE CURREN
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_threeSegmentAfterChangingFeatures(1,"Id"), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(46), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(47), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(48), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			assertion =Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(49), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			//Verify 41 th segment is not displayed
			assertion =Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(40), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(50));
			Thread.sleep(2000);
			
			
			//Using the Find and Replace.
			PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_F);
			Thread.sleep(3000);	
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_transcription_Find_and_replace, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying  PM_transcription_Find_and_replace  after Search");
			}

			String NumberOfOcurrence_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_transcription_NumberOfOccurences).getText();
			System.out.println("NUMBER OF OCCURENCE BEFORE:-" + NumberOfOcurrence_before);

			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_transcription_Find_and_replace_FINDINPUTBOX);
			Thread.sleep(3000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_Find_and_replace_FINDINPUTBOX);
			Thread.sleep(2000);
			General.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_Find_and_replace_FINDINPUTBOX,"Traditionnellement");
			Thread.sleep(5000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_Find_and_replace_CANCELBUTTON);
			Thread.sleep(2000);
			
		    //Verify transcription segment after alt + f
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_currentSegment(43), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(40), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(41), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(42), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			//Verify 30 th segment is not displayed
			assertion = !Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(30), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying 3 segment after operation performed");
			}
			
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(52));
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(50));
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(53));
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(52));
			Thread.sleep(2000);
			//Using the Alt+Enter keyboard shortcut.
			Thread.sleep(2000);
			PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_PAGE_UP);
			Thread.sleep(3000);	
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentRecord(51), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying current record after alt + page down");
			}
			

			//Using the Alt+Enter keyboard shortcut.
			Thread.sleep(2000);
			PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_PAGE_UP);
			Thread.sleep(3000);	
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentRecord(50), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying current record after alt + page down");
			}
			
			
			//TODO THIS CODE IS NOT REQUIRED AS USING PAGE UP INSIST OD ALT + ENTER FROM 2.4.0
//			//VERIFY THAT THREE SEGMETS ARE PRESENT BEFORE CURREN
//			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_threeSegmentAfterChangingFeatures(1,"Id"), 5);
//			if (assertion == false) {
//			report("f","Assertion failed while verifying 3 segment after operation performed");
//			}
//			
//			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(49), 5);
//			if (assertion == false) {
//			report("f","Assertion failed while verifying 3 segment after operation performed");
//			}
//			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(50), 5);
//			if (assertion == false) {
//			report("f","Assertion failed while verifying 3 segment after operation performed");
//			}
//			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(52), 5);
//			if (assertion == false) {
//			report("f","Assertion failed while verifying 3 segment after operation performed");
//			}
//			
//			//Verify 43 th segment is not displayed
//			assertion = !Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentId(43), 5);
//			if (assertion == false) {
//			report("f","Assertion failed while verifying 3 segment after operation performed");
//			}
//			
//			Thread.sleep(4000);
//			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(53));
//			Thread.sleep(2000);
			
			 
			General.action().driver.navigate().refresh();
			
	        
		}
		
		 public void scrollDown(int no) throws Exception{
				Actions act=new Actions(General.driver);
				for(int i=1;i<=no;i++)
				{
					act.sendKeys(Keys.PAGE_DOWN);
					act.build().perform();
					Thread.sleep(6000);
					System.out.println("Scroll time"+i);
					General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_SegmentIds(i+4));
					Thread.sleep(3000);
				}	
			}
		
		
		public void assertion() throws Exception {
			assertion = Verify.action().verifyTextPresent("Go to #50", 5);
			//assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_indexCaption("Go to #53"), 5);
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