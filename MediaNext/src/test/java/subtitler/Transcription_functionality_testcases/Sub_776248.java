package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

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

public class Sub_776248 {

	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_776248" + CommonElements.BROWSER + "F7";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	int timeIn_sixthSegment_frames_afterALTR_int_forward;
	int timeIn_sixthSegment_frames_beforeALTR_int_forward;
	
	

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_776248");
		dataSet.put("TL_test_case_description","SUB-776248:As a Transcriber, I need to move one /multiple subtitle right/left by frames or by seconds");
		dataSet.put("TL_internal_testCase_ID", "776248");
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
			Pm_Transcription_move_subtitle(SubmissionName, targetlanguage_1, false, true);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f", "Execution level error was encountered.\n\nError log:\n\n" + Verify.action().getErrorBuffer(e));
		}

	}

	public void Pm_Transcription_move_subtitle(String SubmissionName, String target, boolean complete,boolean back) throws Exception {

		System.out.println("INSIDE Pm_Transcription_Ongoing_navigate_faster  method()");

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
		Thread.sleep(25000);

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		
		
		

		  int timeIn_firstSegment_frames_beforeALTR_int_forward=getTimeIn_frames_of_segment(1);
	      System.out.println("timeIn_firstSegment_frames_beforeALTR_int_forward :"+timeIn_firstSegment_frames_beforeALTR_int_forward);
	      int timeIn_secondSegment_frames_beforeALTR_int_forward=getTimeIn_frames_of_segment(2);
	      System.out.println("timeIn_secondSegment_frames_beforeALTR_int_forward :"+timeIn_secondSegment_frames_beforeALTR_int_forward);
	      int timeIn_thirdSegment_frames_beforeALTR_int_forward=getTimeIn_frames_of_segment(3);
	      System.out.println("timeIn_thirdSegment_frames_beforeALTR_int_forward :"+timeIn_thirdSegment_frames_beforeALTR_int_forward);
	      int timeIn_fourthSegment_frames_beforeALTR_int_forward=getTimeIn_frames_of_segment(4);
	      System.out.println("timeIn_fourthSegment_frames_beforeALTR_int_forward :"+timeIn_fourthSegment_frames_beforeALTR_int_forward);
	      int timeIn_fifthSegment_frames_beforeALTR_int_forward=getTimeIn_frames_of_segment(5);
	      System.out.println("timeIn_fifthSegment_frames_beforeALTR_int_forward :"+timeIn_fifthSegment_frames_beforeALTR_int_forward);
	      timeIn_sixthSegment_frames_beforeALTR_int_forward=getTimeIn_frames_of_segment(6);
	      System.out.println("timeIn_sixthSegment_frames_beforeALTR_int_forward :"+timeIn_sixthSegment_frames_beforeALTR_int_forward);
	
	      Thread.sleep(2000);
	      General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
	      Thread.sleep(2000);
	    	
	      if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("_EGCH_")) {
	      General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_R);
	      Thread.sleep(1000);
	      }else {
	    	
	      General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, "R"));
	      Thread.sleep(3000);	
	      }
	      
	      assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_frames_radioButton, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying PM_transcription_ongoing_MoveSegments_frames_radioButton");
		  }
		 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_frames, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying PM_transcription_ongoing_MoveSegments_time_frames");
		  }
		 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_forward, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying PM_transcription_ongoing_MoveSegments_forward");
		  }
		 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_move, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying PM_transcription_ongoing_MoveSegments_move");
			}
		  assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_backward, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying PM_transcription_ongoing_MoveSegments_move");
		  }
	      
		 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_cancel, 5);
		 if (assertion == false) {
				report("f", "Assertion failed while verifying PM_transcription_ongoing_MoveSegments_move");
		  }
	      
	      
	      //Verify frames are moving forward
	      Thread.sleep(2000);
		  General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_AllSegments);
		  Thread.sleep(2000);
		  General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_frames_radioButton);
		  Thread.sleep(2000);
		  General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_frames);
		  Thread.sleep(2000);
		  General.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_frames,"2");
		  Thread.sleep(2000);
		  General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_forward);
		  Thread.sleep(2000);   
		  General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_move);
		  Thread.sleep(1000);
		  assertion = Verify.action().verifyTextPresent("Range selected has been moved successfully", 5);
		  if (assertion == false) {
				report("f","Assertion failed while verifying notification message for segment moved ");
		   }
		  
		  int timeIn_firstSegment_frames_afterALTR_int_forward=getTimeIn_frames_of_segment(1);
	      System.out.println("timeIn_firstSegment_frames_afterALTR_int_forward :"+timeIn_firstSegment_frames_afterALTR_int_forward);
	      int timeIn_secondSegment_frames_afterALTR_int_forward=getTimeIn_frames_of_segment(2);
	      System.out.println("timeIn_secondSegment_frames_afterALTR_int_forward :"+timeIn_secondSegment_frames_afterALTR_int_forward);
	      int timeIn_thirdSegment_frames_afterALTR_int_forward=getTimeIn_frames_of_segment(3);
	      System.out.println("timeIn_thirdSegment_frames_afterALTR_int_forward :"+timeIn_thirdSegment_frames_afterALTR_int_forward);
	      int timeIn_fourthSegment_frames_afterALTR_int_forward=getTimeIn_frames_of_segment(4);
	      System.out.println("timeIn_fourthSegment_frames_afterALTR_int :"+timeIn_fourthSegment_frames_afterALTR_int_forward);
	      int timeIn_fifthSegment_frames_afterALTR_int_forward=getTimeIn_frames_of_segment(5);
	      System.out.println("timeIn_fifthSegment_frames_afterALTR_int :"+timeIn_fifthSegment_frames_afterALTR_int_forward);
	      timeIn_sixthSegment_frames_afterALTR_int_forward=getTimeIn_frames_of_segment(6);
	      System.out.println("timeIn_sixthSegment_frames_afterALTR_int_forward :"+timeIn_sixthSegment_frames_afterALTR_int_forward);
	      
	      
	      System.out.println(timeIn_firstSegment_frames_afterALTR_int_forward);
	      System.out.println(timeIn_firstSegment_frames_beforeALTR_int_forward);
	      assertion =timeIn_firstSegment_frames_afterALTR_int_forward==timeIn_firstSegment_frames_beforeALTR_int_forward+2;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeIn_secondSegment_frames_afterALTR_int_forward==timeIn_secondSegment_frames_beforeALTR_int_forward+2;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeIn_thirdSegment_frames_afterALTR_int_forward==timeIn_thirdSegment_frames_beforeALTR_int_forward+2;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeIn_fourthSegment_frames_afterALTR_int_forward==timeIn_fourthSegment_frames_beforeALTR_int_forward+2;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeIn_fifthSegment_frames_afterALTR_int_forward==timeIn_fifthSegment_frames_beforeALTR_int_forward+2;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeIn_sixthSegment_frames_afterALTR_int_forward==timeIn_sixthSegment_frames_beforeALTR_int_forward+2;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			
			//Verify frames are moving backward
			  int timeIn_firstSegment_frames_beforeALTR_int_backward=getTimeIn_frames_of_segment(1);
		      System.out.println("timeIn_firstSegment_frames_beforeALTR_int_backward :"+timeIn_firstSegment_frames_beforeALTR_int_backward);
		      int timeIn_secondSegment_frames_beforeALTR_int_backward=getTimeIn_frames_of_segment(2);
		      System.out.println("timeIn_secondSegment_frames_beforeALTR_int_backward :"+timeIn_secondSegment_frames_beforeALTR_int_backward);
		      int timeIn_thirdSegment_frames_beforeALTR_int_backward=getTimeIn_frames_of_segment(3);
		      System.out.println("timeIn_thirdSegment_frames_beforeALTR_int_backward :"+timeIn_thirdSegment_frames_beforeALTR_int_backward);
		      int timeIn_fourthSegment_frames_beforeALTR_int_backward=getTimeIn_frames_of_segment(4);
		      System.out.println("timeIn_fourthSegment_frames_beforeALTR_int_backward :"+timeIn_fourthSegment_frames_beforeALTR_int_backward);
		      int timeIn_fifthSegment_frames_beforeALTR_int_backward=getTimeIn_frames_of_segment(5);
		      System.out.println("timeIn_fifthSegment_frames_beforeALTR_int_backward :"+timeIn_fifthSegment_frames_beforeALTR_int_backward);
		      int timeIn_sixthSegment_frames_beforeALTR_int_backward=getTimeIn_frames_of_segment(6);
		      System.out.println("timeIn_sixthSegment_frames_beforeALTR_int_backward :"+timeIn_sixthSegment_frames_beforeALTR_int_backward);
		
		      Thread.sleep(2000);
		      General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		      Thread.sleep(2000);
		    	
		      if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("_EGCH_")) {
		      General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_R);
		      Thread.sleep(1000);
		      }else {
		    	
		      General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, "R"));
		      Thread.sleep(3000);	
		      }
		      
		      
		      
		      Thread.sleep(2000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_AllSegments);
			  Thread.sleep(2000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_frames_radioButton);
			  Thread.sleep(2000);
			  General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_frames);
			  Thread.sleep(2000);
			  General.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_frames,"2");
			  Thread.sleep(2000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_backward);
			  Thread.sleep(2000);   
			  General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_move);
			  Thread.sleep(1000);
			  assertion = Verify.action().verifyTextPresent("Range selected has been moved successfully", 5);
			  if (assertion == false) {
					report("f","Assertion failed while verifying notification message for segment moved ");
			   }
			  
			  
			  int timeIn_firstSegment_frames_afterALTR_int_backward=getTimeIn_frames_of_segment(1);
		      System.out.println("timeIn_firstSegment_frames_afterALTR_int_backward :"+timeIn_firstSegment_frames_afterALTR_int_backward);
		      int timeIn_secondSegment_frames_afterALTR_int_backward=getTimeIn_frames_of_segment(2);
		      System.out.println("timeIn_secondSegment_frames_afterALTR_int_backward :"+timeIn_secondSegment_frames_afterALTR_int_backward);
		      int timeIn_thirdSegment_frames_afterALTR_int_backward=getTimeIn_frames_of_segment(3);
		      System.out.println("timeIn_thirdSegment_frames_afterALTR_int_backward :"+timeIn_thirdSegment_frames_afterALTR_int_backward);
		      int timeIn_fourthSegment_frames_afterALTR_int_backward=getTimeIn_frames_of_segment(4);
		      System.out.println("timeIn_fourthSegment_frames_afterALTR_intbackward :"+timeIn_fourthSegment_frames_afterALTR_int_backward);
		      int timeIn_fifthSegment_frames_afterALTR_int_backward=getTimeIn_frames_of_segment(5);
		      System.out.println("timeIn_fifthSegment_frames_afterALTR_int_backward :"+timeIn_fifthSegment_frames_afterALTR_int_backward);
		      int timeIn_sixthSegment_frames_afterALTR_int_backward=getTimeIn_frames_of_segment(6);
		      System.out.println("timeIn_sixthSegment_frames_afterALTR_int_backward :"+timeIn_sixthSegment_frames_afterALTR_int_backward);
		      
		      
		      System.out.println(timeIn_firstSegment_frames_afterALTR_int_forward);
		      System.out.println(timeIn_firstSegment_frames_beforeALTR_int_forward);
		      assertion =timeIn_firstSegment_frames_afterALTR_int_forward-2==timeIn_firstSegment_frames_beforeALTR_int_forward;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
				assertion =timeIn_secondSegment_frames_afterALTR_int_forward-2==timeIn_secondSegment_frames_beforeALTR_int_forward;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
				assertion =timeIn_thirdSegment_frames_afterALTR_int_forward-2==timeIn_thirdSegment_frames_beforeALTR_int_forward;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
				assertion =timeIn_fourthSegment_frames_afterALTR_int_forward-2==timeIn_fourthSegment_frames_beforeALTR_int_forward;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
				assertion =timeIn_fifthSegment_frames_afterALTR_int_forward-2==timeIn_fifthSegment_frames_beforeALTR_int_forward;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
				assertion =timeIn_sixthSegment_frames_afterALTR_int_forward-2==timeIn_sixthSegment_frames_beforeALTR_int_forward;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
				
	}
	
	
	
	public int getTimeIn_frames_of_segment(int segment) {
		String timeOut=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(segment)).getText();
		System.out.println("timeOut :"+timeOut);
		String timeOut_int=timeOut.replaceAll("[^0-9]", "");
		System.out.println("timeOut_int :"+timeOut_int);
		
		String timeOut_sec=timeOut_int.substring(6,8);
		System.out.println("timeOut_sec :"+timeOut_sec);
		int timeOut_int_sec=Integer.parseInt(timeOut_sec);
		System.out.println("timeOut_int_sec :  "+timeOut_int_sec);	
		return timeOut_int_sec;
		
	}
	
	
	public int getTimeOut_frames_of_segment(int segment) {
		String timeOut=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeOut(segment)).getText();
		System.out.println("timeOut :"+timeOut);
		String timeOut_int=timeOut.replaceAll("[^0-9]", "");
		System.out.println("timeOut_int :"+timeOut_int);
		
		String timeOut_sec=timeOut_int.substring(6,8);
		System.out.println("timeOut_sec :"+timeOut_sec);
		int timeOut_int_sec=Integer.parseInt(timeOut_sec);
		System.out.println("timeOut_int_sec :  "+timeOut_int_sec);	
		return timeOut_int_sec;
		
	}
		
	public void assertion() throws Exception {

		assertion =timeIn_sixthSegment_frames_afterALTR_int_forward-2==timeIn_sixthSegment_frames_beforeALTR_int_forward;
		if (assertion == false) {
			report("f","Assertion failed while verifying all segments are moved(Sec)");
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
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),
				Subtitler_TestRail_Common_Properties.idTestPlan, Subtitler_TestRail_Common_Properties.idBuild, result,
				Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}
}
