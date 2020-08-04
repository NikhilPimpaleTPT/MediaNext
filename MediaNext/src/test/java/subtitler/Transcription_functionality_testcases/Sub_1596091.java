package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary:This test case verifies that if user can move multiple segments in transcription phase.
 *Preconditions: Create a submission with transcription workflow.
 */ 

public class Sub_1596091 {

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1596091"+CommonElements.BROWSER+"Q1";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    int addFramesBy=2;
    int timeOut_thirdSegment_seconds_int_after;
    int timeOut_thirdSegment_seconds_int;
    Boolean assertion = true;
    int timeIn_sixthSegment_frames_afterALTR_int_selected;
    int timeIn_sixthSegment_frames_beforeALTR_int_selected;
    
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1596091");
		dataSet.put("TL_test_case_description", "SUB-1596091:Move multiple segments");
		dataSet.put("TL_internal_testCase_ID", "1596091");
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
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, Targetlanguage);
			Thread.sleep(2000);
		
			//SEARCH SUBMISSION AND CHECK
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			

			 PM_user.action().Navigate(menu_to_claim);
	         Thread.sleep(1000);
	         PM_user.action().PM_ToClaim(SubmissionName);
	         Thread.sleep(1000);
	         PM_user.action().Navigate(menu_ongoing);
	         Thread.sleep(1000);
	         PM_transcription_onGoing_ReadingSpeed_Submission(SubmissionName,targetlanguage);
	         Thread.sleep(1000);
	         
	         
		
			
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void PM_transcription_onGoing_ReadingSpeed_Submission(String SubmissionName, String target) throws Exception {

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
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
        Thread.sleep(8000);
    
        //For All segments
        //Forward
        int timeIn_firstSegment_sec_beforeALTR_int=getTimeIn_sec_of_segment(1);
    	System.out.println("timeIn_firstSegment_sec_beforeALTR_int :"+timeIn_firstSegment_sec_beforeALTR_int);
    	int timeIn_secondSegment_sec_beforeALTR_int=getTimeIn_sec_of_segment(2);
    	System.out.println("timeIn_secondSegment_sec_beforeALTR_int :"+timeIn_secondSegment_sec_beforeALTR_int);
    	int timeIn_thirdSegment_sec_beforeALTR_int=getTimeIn_sec_of_segment(3);
     	System.out.println("timeIn_thirdSegment_sec_beforeALTR_int :"+timeIn_thirdSegment_sec_beforeALTR_int);
     	int timeIn_fourthSegment_sec_beforeALTR_int=getTimeIn_sec_of_segment(4);
     	System.out.println("timeIn_fourthSegment_sec_beforeALTR_int :"+timeIn_fourthSegment_sec_beforeALTR_int);
     	int timeIn_fifthSegment_sec_beforeALTR_int=getTimeIn_sec_of_segment(5);
     	System.out.println("timeIn_fifthSegment_sec_beforeALTR_int :"+timeIn_fifthSegment_sec_beforeALTR_int);
     	int timeIn_sixthSegment_sec_beforeALTR_int=getTimeIn_sec_of_segment(6);
     	System.out.println("timeIn_sixthSegment_sec_beforeALTR_int :"+timeIn_sixthSegment_sec_beforeALTR_int);
		
		
     	int timeOut_firstSegment_sec_beforeALTR_int=getTimeOut_sec_of_segment(1);
      	System.out.println("timeOut_firstSegment_sec_beforeALTR_int :"+timeOut_firstSegment_sec_beforeALTR_int);
      	int timeOut_secondSegment_sec_beforeALTR_int=getTimeOut_sec_of_segment(2);
      	System.out.println("timeOut_secondSegment_sec_beforeALTR_int :"+timeOut_secondSegment_sec_beforeALTR_int);
      	int timeOut_thirdSegment_sec_beforeALTR_int=getTimeOut_sec_of_segment(3);
       	System.out.println("timeOut_thirdSegment_sec_beforeALTR_int :"+timeOut_thirdSegment_sec_beforeALTR_int);
       	int timeOut_fourthSegment_sec_beforeALTR_int=getTimeOut_sec_of_segment(4);
       	System.out.println("timeOut_fourthSegment_sec_beforeALTR_int :"+timeOut_fourthSegment_sec_beforeALTR_int);
       	int timeOut_fifthSegment_sec_beforeALTR_int=getTimeOut_sec_of_segment(5);
       	System.out.println("timeOut_fifthSegment_sec_beforeALTR_int :"+timeOut_fifthSegment_sec_beforeALTR_int);
       	int timeOut_sixthSegment_sec_beforeALTR_int=getTimeOut_sec_of_segment(6);
       	System.out.println("timeOut_sixthSegment_sec_beforeALTR_int :"+timeOut_sixthSegment_sec_beforeALTR_int);
		
		
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
    	
    	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_fromSubtitleId, 5);
    	if (assertion == false) {
    		report("f","Assertion failed while verifying SubmissionName  after Search");
    	}
    	Thread.sleep(2000);
    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_AllSegments);
    	Thread.sleep(2000);
    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_radioButton);
    	Thread.sleep(2000);
    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_sec);
    	Thread.sleep(2000);
    	General.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_sec,"2");
    	Thread.sleep(2000);
    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_forward);
    	Thread.sleep(2000);   
    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_move);
    	Thread.sleep(1000);
    	
    	assertion = Verify.action().verifyTextPresent("Range selected has been moved successfully", 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying notification message for segment moved ");
		}	
		
	        int timeIn_firstSegment_sec_afterALTR_int=getTimeIn_sec_of_segment(1);
	    	System.out.println("timeIn_firstSegment_sec_afterALTR_int :"+timeIn_firstSegment_sec_afterALTR_int);
	    	int timeIn_secondSegment_sec_afterALTR_int=getTimeIn_sec_of_segment(2);
	    	System.out.println("timeIn_secondSegment_sec_afterALTR_int :"+timeIn_secondSegment_sec_afterALTR_int);
	    	int timeIn_thirdSegment_sec_afterALTR_int=getTimeIn_sec_of_segment(3);
	     	System.out.println("timeIn_thirdSegment_sec_afterALTR_int :"+timeIn_thirdSegment_sec_afterALTR_int);
	     	int timeIn_fourthSegment_sec_afterALTR_int=getTimeIn_sec_of_segment(4);
	     	System.out.println("timeIn_fourthSegment_sec_afterALTR_int :"+timeIn_fourthSegment_sec_afterALTR_int);
	     	int timeIn_fifthSegment_sec_afterALTR_int=getTimeIn_sec_of_segment(5);
	     	System.out.println("timeIn_fifthSegment_sec_afterALTR_int :"+timeIn_fifthSegment_sec_afterALTR_int);
	     	int timeIn_sixthSegment_sec_afterALTR_int=getTimeIn_sec_of_segment(6);
	     	System.out.println("timeIn_sixthSegment_sec_afterALTR_int :"+timeIn_sixthSegment_sec_afterALTR_int);
			
			
	     	int timeOut_firstSegment_sec_afterALTR_int=getTimeOut_sec_of_segment(1);
	      	System.out.println("timeOut_firstSegment_sec_afterALTR_int :"+timeOut_firstSegment_sec_afterALTR_int);
	      	int timeOut_secondSegment_sec_afterALTR_int=getTimeOut_sec_of_segment(2);
	      	System.out.println("timeOut_secondSegment_sec_afterALTR_int :"+timeOut_secondSegment_sec_afterALTR_int);
	      	int timeOut_thirdSegment_sec_afterALTR_int=getTimeOut_sec_of_segment(3);
	       	System.out.println("timeOut_thirdSegment_sec_afterALTR_int :"+timeOut_thirdSegment_sec_afterALTR_int);
	       	int timeOut_fourthSegment_sec_afterALTR_int=getTimeOut_sec_of_segment(4);
	       	System.out.println("timeIn_secondSegment_sec_beforeALTR_int :"+timeOut_fourthSegment_sec_afterALTR_int);
	       	int timeOut_fifthSegment_sec_afterALTR_int=getTimeOut_sec_of_segment(5);
	       	System.out.println("timeOut_fifthSegment_sec_afterALTR_int :"+timeOut_fifthSegment_sec_afterALTR_int);
	       	int timeOut_sixthSegment_sec_afterALTR_int=getTimeOut_sec_of_segment(6);
	       	System.out.println("timeOut_sixthSegment_sec_afterALTR_int :"+timeOut_sixthSegment_sec_afterALTR_int);

		
		assertion =timeIn_firstSegment_sec_afterALTR_int==timeIn_firstSegment_sec_beforeALTR_int+2;
		if (assertion == false) {
			report("f","Assertion failed while verifying all segments are moved(Sec)");
		}
		assertion =timeIn_secondSegment_sec_afterALTR_int==timeIn_secondSegment_sec_beforeALTR_int+2;
		if (assertion == false) {
			report("f","Assertion failed while verifying all segments are moved(Sec)");
		}
		assertion =timeIn_thirdSegment_sec_afterALTR_int==timeIn_thirdSegment_sec_beforeALTR_int+2;
		if (assertion == false) {
			report("f","Assertion failed while verifying all segments are moved(Sec)");
		}
		assertion =timeIn_fourthSegment_sec_afterALTR_int==timeIn_fourthSegment_sec_beforeALTR_int+2;
		if (assertion == false) {
			report("f","Assertion failed while verifying all segments are moved(Sec)");
		}
		assertion =timeIn_fifthSegment_sec_afterALTR_int==timeIn_fifthSegment_sec_beforeALTR_int+2;
		if (assertion == false) {
			report("f","Assertion failed while verifying all segments are moved(Sec)");
		}
		assertion =timeIn_sixthSegment_sec_afterALTR_int==timeIn_sixthSegment_sec_beforeALTR_int+2;
		if (assertion == false) {
			report("f","Assertion failed while verifying all segments are moved(Sec)");
		}
		assertion =timeOut_firstSegment_sec_afterALTR_int==timeOut_firstSegment_sec_beforeALTR_int+2;
		if (assertion == false) {
			report("f","Assertion failed while verifying all segments are moved(Sec)");
		}
		assertion =timeOut_secondSegment_sec_afterALTR_int==timeOut_secondSegment_sec_beforeALTR_int+2;
		if (assertion == false) {
			report("f","Assertion failed while verifying all segments are moved(Sec)");
		}
		assertion =timeOut_thirdSegment_sec_afterALTR_int==timeOut_thirdSegment_sec_beforeALTR_int+2;
		if (assertion == false) {
			report("f","Assertion failed while verifying all segments are moved(Sec)");
		}
		assertion =timeOut_fourthSegment_sec_afterALTR_int==timeOut_fourthSegment_sec_beforeALTR_int+2;
		if (assertion == false) {
			report("f","Assertion failed while verifying all segments are moved(Sec)");
		}
		assertion =timeOut_fifthSegment_sec_afterALTR_int==timeOut_fifthSegment_sec_beforeALTR_int+2;
		if (assertion == false) {
			report("f","Assertion failed while verifying all segments are moved(Sec)");
		}
		assertion =timeOut_sixthSegment_sec_afterALTR_int==timeOut_sixthSegment_sec_beforeALTR_int+2;
		if (assertion == false) {
			report("f","Assertion failed while verifying all segments are moved(Sec)");
		}
		
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
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
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_radioButton);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_sec);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_sec);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_sec,"2");
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_backward);
		Thread.sleep(2000);   
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_move);
		Thread.sleep(1000); 
		assertion = Verify.action().verifyTextPresent("Range selected has been moved successfully", 5);
		if (assertion == false) {


		}
		
		    int timeIn_firstSegment_sec_afterALTR_int_Backward=getTimeIn_sec_of_segment(1);
	    	System.out.println("timeIn_firstSegment_sec_afterALTR_int_Backward :"+timeIn_firstSegment_sec_afterALTR_int_Backward);
	    	int timeIn_secondSegment_sec_afterALTR_int_Backward=getTimeIn_sec_of_segment(2);
	    	System.out.println("timeIn_secondSegment_sec_afterALTR_int_Backward :"+timeIn_secondSegment_sec_afterALTR_int_Backward);
	    	int timeIn_thirdSegment_sec_afterALTR_int_Backward=getTimeIn_sec_of_segment(3);
	     	System.out.println("timeIn_thirdSegment_sec_afterALTR_int_Backward :"+timeIn_thirdSegment_sec_afterALTR_int_Backward);
	     	int timeIn_fourthSegment_sec_afterALTR_int_Backward=getTimeIn_sec_of_segment(4);
	     	System.out.println("timeIn_fourthSegment_sec_afterALTR_int_Backward :"+timeIn_fourthSegment_sec_afterALTR_int_Backward);
	     	int timeIn_fifthSegment_sec_afterALTR_int_Backward=getTimeIn_sec_of_segment(5);
	     	System.out.println("timeIn_fifthSegment_sec_afterALTR_int_Backward :"+timeIn_fifthSegment_sec_afterALTR_int_Backward);
	     	int timeIn_sixthSegment_sec_afterALTR_int_Backward=getTimeIn_sec_of_segment(6);
	     	System.out.println("timeIn_sixthSegment_sec_afterALTR_int_Backward :"+timeIn_sixthSegment_sec_afterALTR_int_Backward);
			
			
	     	int timeOut_firstSegment_sec_afterALTR_int_Backward=getTimeOut_sec_of_segment(1);
	      	System.out.println("timeOut_firstSegment_sec_afterALTR_int_Backward :"+timeOut_firstSegment_sec_afterALTR_int_Backward);
	      	int timeOut_secondSegment_sec_afterALTR_int_Backward=getTimeOut_sec_of_segment(2);
	      	System.out.println("timeOut_secondSegment_sec_afterALTR_int_Backward :"+timeOut_secondSegment_sec_afterALTR_int_Backward);
	      	int timeOut_thirdSegment_sec_afterALTR_int_Backward=getTimeOut_sec_of_segment(3);
	       	System.out.println("timeOut_thirdSegment_sec_afterALTR_int_Backward :"+timeOut_thirdSegment_sec_afterALTR_int_Backward);
	       	int timeOut_fourthSegment_sec_afterALTR_int_Backward=getTimeOut_sec_of_segment(4);
	       	System.out.println("timeIn_secondSegment_sec_beforeALTR_int_Backward :"+timeOut_fourthSegment_sec_afterALTR_int_Backward);
	       	int timeOut_fifthSegment_sec_afterALTR_int_Backward=getTimeOut_sec_of_segment(5);
	       	System.out.println("timeOut_fifthSegment_sec_afterALTR_int_Backward :"+timeOut_fifthSegment_sec_afterALTR_int_Backward);
	       	int timeOut_sixthSegment_sec_afterALTR_int_Backward=getTimeOut_sec_of_segment(6);
	       	System.out.println("timeOut_sixthSegment_sec_afterALTR_int_Backward :"+timeOut_sixthSegment_sec_afterALTR_int_Backward);
		
		
		//Note: Comparing with the segment value after backward move and the previous value before backward move
	    	assertion =timeIn_firstSegment_sec_afterALTR_int-2==timeIn_firstSegment_sec_afterALTR_int_Backward;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeIn_secondSegment_sec_afterALTR_int-2==timeIn_secondSegment_sec_afterALTR_int_Backward;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeIn_thirdSegment_sec_afterALTR_int-2==timeIn_thirdSegment_sec_afterALTR_int_Backward;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeIn_fourthSegment_sec_afterALTR_int-2==timeIn_fourthSegment_sec_afterALTR_int_Backward;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeIn_fifthSegment_sec_afterALTR_int-2==timeIn_fifthSegment_sec_afterALTR_int_Backward;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeIn_sixthSegment_sec_afterALTR_int-2==timeIn_sixthSegment_sec_afterALTR_int_Backward;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeOut_firstSegment_sec_afterALTR_int-2==timeOut_firstSegment_sec_afterALTR_int_Backward;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeOut_secondSegment_sec_afterALTR_int-2==timeOut_secondSegment_sec_afterALTR_int_Backward;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeOut_thirdSegment_sec_afterALTR_int-2==timeOut_thirdSegment_sec_afterALTR_int_Backward;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeOut_fourthSegment_sec_afterALTR_int-2==timeOut_fourthSegment_sec_afterALTR_int_Backward;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeOut_fifthSegment_sec_afterALTR_int-2==timeOut_fifthSegment_sec_afterALTR_int_Backward;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
			assertion =timeOut_sixthSegment_sec_afterALTR_int-2==timeOut_sixthSegment_sec_afterALTR_int_Backward;
			if (assertion == false) {
				report("f","Assertion failed while verifying all segments are moved(Sec)");
			}
        
        
           
			
			  int timeIn_firstSegment_frames_beforeALTR_int=getTimeIn_frames_of_segment(1);
		      System.out.println("timeIn_firstSegment_frames_beforeALTR_int :"+timeIn_firstSegment_frames_beforeALTR_int);
		      int timeIn_secondSegment_frames_beforeALTR_int=getTimeIn_frames_of_segment(2);
		      System.out.println("timeIn_secondSegment_frames_beforeALTR_int :"+timeIn_secondSegment_frames_beforeALTR_int);
		      int timeIn_thirdSegment_frames_beforeALTR_int=getTimeIn_frames_of_segment(3);
		      System.out.println("timeIn_thirdSegment_frames_beforeALTR_int :"+timeIn_thirdSegment_frames_beforeALTR_int);
		      int timeIn_fourthSegment_frames_beforeALTR_int=getTimeIn_frames_of_segment(4);
		      System.out.println("timeIn_fourthSegment_frames_beforeALTR_int :"+timeIn_fourthSegment_frames_beforeALTR_int);
		      int timeIn_fifthSegment_frames_beforeALTR_int=getTimeIn_frames_of_segment(5);
		      System.out.println("timeIn_fifthSegment_frames_beforeALTR_int :"+timeIn_fifthSegment_frames_beforeALTR_int);
		      int timeIn_sixthSegment_frames_beforeALTR_int=getTimeIn_frames_of_segment(6);
		      System.out.println("timeIn_sixthSegment_frames_beforeALTR_int :"+timeIn_sixthSegment_frames_beforeALTR_int);
		
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
			  
			  int timeIn_firstSegment_frames_afterALTR_int=getTimeIn_frames_of_segment(1);
		      System.out.println("timeIn_firstSegment_frames_afterALTR_int :"+timeIn_firstSegment_frames_afterALTR_int);
		      int timeIn_secondSegment_frames_afterALTR_int=getTimeIn_frames_of_segment(2);
		      System.out.println("timeIn_secondSegment_frames_afterALTR_int :"+timeIn_secondSegment_frames_afterALTR_int);
		      int timeIn_thirdSegment_frames_afterALTR_int=getTimeIn_frames_of_segment(3);
		      System.out.println("timeIn_thirdSegment_frames_afterALTR_int :"+timeIn_thirdSegment_frames_afterALTR_int);
		      int timeIn_fourthSegment_frames_afterALTR_int=getTimeIn_frames_of_segment(4);
		      System.out.println("timeIn_fourthSegment_frames_afterALTR_int :"+timeIn_fourthSegment_frames_afterALTR_int);
		      int timeIn_fifthSegment_frames_afterALTR_int=getTimeIn_frames_of_segment(5);
		      System.out.println("timeIn_fifthSegment_frames_afterALTR_int :"+timeIn_fifthSegment_frames_afterALTR_int);
		      int timeIn_sixthSegment_frames_afterALTR_int=getTimeIn_frames_of_segment(6);
		      System.out.println("timeIn_sixthSegment_frames_afterALTR_int :"+timeIn_sixthSegment_frames_afterALTR_int);
		      
		      
		      System.out.println(timeIn_firstSegment_frames_afterALTR_int);
		      System.out.println(timeIn_firstSegment_frames_beforeALTR_int);
		      assertion =timeIn_firstSegment_frames_afterALTR_int==timeIn_firstSegment_frames_beforeALTR_int-2;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
				assertion =timeIn_secondSegment_frames_afterALTR_int==timeIn_secondSegment_frames_beforeALTR_int-2;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
				assertion =timeIn_thirdSegment_frames_afterALTR_int==timeIn_thirdSegment_frames_beforeALTR_int-2;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
//				assertion =timeIn_fourthSegment_frames_afterALTR_int==timeIn_fourthSegment_frames_beforeALTR_int-2;
//				if (assertion == false) {
//					report("f","Assertion failed while verifying all segments are moved(Sec)");
//				}
				assertion =timeIn_fifthSegment_frames_afterALTR_int==timeIn_fifthSegment_frames_beforeALTR_int-2;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
				assertion =timeIn_sixthSegment_frames_afterALTR_int==timeIn_sixthSegment_frames_beforeALTR_int-2;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
		      
				//Selected Segment
		      
				 int timeIn_firstSegment_sec_beforeALTR_int_selected_forward=getTimeIn_sec_of_segment(1);
			     System.out.println("timeIn_firstSegment_sec_beforeALTR_int_selected_forward :"+timeIn_firstSegment_sec_beforeALTR_int_selected_forward);
			     int timeIn_secondSegment_sec_beforeALTR_int_selected_forward=getTimeIn_sec_of_segment(2);
			     System.out.println("timeIn_secondSegment_sec_beforeALTR_int_selected_forward :"+timeIn_secondSegment_sec_beforeALTR_int_selected_forward);
		      
			     int timeOut_firstSegment_sec_beforeALTR_int_selected_forward=getTimeOut_sec_of_segment(1);
			     System.out.println("timeOut_firstSegment_sec_beforeALTR_int_selected_forward :"+timeOut_firstSegment_sec_beforeALTR_int_selected_forward);
			     int timeOut_secondSegment_sec_beforeALTR_int_selected_forward=getTimeOut_sec_of_segment(2);
			     System.out.println("timeOut_secondSegment_sec_beforeALTR_int_selected_forward :"+timeOut_secondSegment_sec_beforeALTR_int_selected_forward);
		      
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
		    	
		       assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_fromSubtitleId, 5);
		    	if (assertion == false) {
		    		report("f","Assertion failed while verifying SubmissionName  after Search");
		    	}
		    	Thread.sleep(2000);
		    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_segmentSelected);
		    	Thread.sleep(2000);
		    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_fromSubtitleId);
		    	Thread.sleep(2000);
		    	General.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_fromSubtitleId,"1");
		    	Thread.sleep(2000);
		    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_toSubtitleId);
		    	Thread.sleep(2000);
		    	General.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_toSubtitleId,"2");
		    	Thread.sleep(2000);
		    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_radioButton);
		    	Thread.sleep(2000);
		    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_sec);
		    	Thread.sleep(2000);
		    	General.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_sec,"2");
		    	Thread.sleep(2000);
		    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_forward);
		    	Thread.sleep(2000);   
		    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_move);
		    	Thread.sleep(1000);
		    	
		    	int timeIn_firstSegment_sec_afterALTR_int_selected_forward=getTimeIn_sec_of_segment(1);
			    System.out.println("timeIn_firstSegment_sec_afterALTR_int_selected_forward :"+timeIn_firstSegment_sec_afterALTR_int_selected_forward);
			    int timeIn_secondSegment_sec_afterALTR_int_selected_forward=getTimeIn_sec_of_segment(2);
			    System.out.println("timeIn_secondSegment_sec_afterALTR_int_selected_forward :"+timeIn_secondSegment_sec_afterALTR_int_selected_forward);
		    	
			    int timeOut_firstSegment_sec_afterALTR_int_selected_forward=getTimeOut_sec_of_segment(1);
		      	System.out.println("timeOut_firstSegment_sec_afterALTR_int_selected_forward_forward :"+timeOut_firstSegment_sec_afterALTR_int_selected_forward);
		      	int timeOut_secondSegment_sec_afterALTR_int_selected_forward=getTimeOut_sec_of_segment(2);
		      	System.out.println("timeOut_secondSegment_sec_afterALTR_int_selected_forwardi :"+timeOut_secondSegment_sec_afterALTR_int_selected_forward);
		    	
		      	assertion =timeIn_firstSegment_sec_afterALTR_int_selected_forward==timeIn_firstSegment_sec_beforeALTR_int_selected_forward+2;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
				assertion =timeIn_secondSegment_sec_afterALTR_int_selected_forward==timeIn_secondSegment_sec_beforeALTR_int_selected_forward+2;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
				
				assertion =timeOut_firstSegment_sec_afterALTR_int_selected_forward==timeOut_firstSegment_sec_beforeALTR_int_selected_forward+2;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
				assertion =timeOut_secondSegment_sec_afterALTR_int_selected_forward==timeOut_secondSegment_sec_beforeALTR_int_selected_forward+2;
				if (assertion == false) {
					report("f","Assertion failed while verifying all segments are moved(Sec)");
				}
				
				

				 int timeIn_firstSegment_sec_beforeALTR_int_selected_backward=getTimeIn_sec_of_segment(1);
			     System.out.println("timeIn_firstSegment_sec_beforeALTR_int_selected_backward :"+timeIn_firstSegment_sec_beforeALTR_int_selected_backward);
			     int timeIn_secondSegment_sec_beforeALTR_int_selected_backward=getTimeIn_sec_of_segment(2);
			     System.out.println("timeIn_secondSegment_sec_beforeALTR_int_selected_backward :"+timeIn_secondSegment_sec_beforeALTR_int_selected_backward);
		      
			     int timeOut_firstSegment_sec_beforeALTR_int_selected_backward=getTimeOut_sec_of_segment(1);
			     System.out.println("timeOut_firstSegment_sec_beforeALTR_int_selected_backward :"+timeOut_firstSegment_sec_beforeALTR_int_selected_backward);
			     int timeOut_secondSegment_sec_beforeALTR_int_selected_backward=getTimeOut_sec_of_segment(2);
			     System.out.println("timeOut_secondSegment_sec_beforeALTR_int_selected_backward :"+timeOut_secondSegment_sec_beforeALTR_int_selected_backward);
		      
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
			     
			     assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_fromSubtitleId, 5);
			     if (assertion == false) {
			    		report("f","Assertion failed while verifying SubmissionName  after Search");
			     }
			    	Thread.sleep(2000);
			    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_segmentSelected);
			    	Thread.sleep(2000);
			    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_fromSubtitleId);
			    	Thread.sleep(2000);
			    	General.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_fromSubtitleId,"1");
			    	Thread.sleep(2000);
			    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_toSubtitleId);
			    	Thread.sleep(2000);
			    	General.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_toSubtitleId,"2");
			    	Thread.sleep(2000);
			    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_radioButton);
			    	Thread.sleep(2000);
			    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_sec);
			    	Thread.sleep(2000);
			    	General.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_time_sec,"2");
			    	Thread.sleep(2000);
			    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_backward);
			    	Thread.sleep(2000);   
			    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_move);
			    	Thread.sleep(1000);
			    	
			    	int timeIn_firstSegment_sec_afterALTR_int_selected_backward=getTimeIn_sec_of_segment(1);
				    System.out.println("timeIn_firstSegment_sec_afterALTR_int_selected_backward :"+timeIn_firstSegment_sec_afterALTR_int_selected_backward);
				    int timeIn_secondSegment_sec_afterALTR_int_selected_backward=getTimeIn_sec_of_segment(2);
				    System.out.println("timeIn_secondSegment_sec_afterALTR_int_selected_backward :"+timeIn_secondSegment_sec_afterALTR_int_selected_backward);
			    	
				    int timeOut_firstSegment_sec_afterALTR_int_selected_backward=getTimeOut_sec_of_segment(1);
			      	System.out.println("timeOut_firstSegment_sec_afterALTR_int_selected_forward_forward :"+timeOut_firstSegment_sec_afterALTR_int_selected_backward);
			      	int timeOut_secondSegment_sec_afterALTR_int_selected_backward=getTimeOut_sec_of_segment(2);
			      	System.out.println("timeOut_secondSegment_sec_afterALTR_int_selected__backward :"+timeOut_secondSegment_sec_afterALTR_int_selected_backward);
			    	
			      	assertion =timeIn_firstSegment_sec_afterALTR_int_selected_backward==timeIn_firstSegment_sec_beforeALTR_int_selected_backward-2;
					if (assertion == false) {
						report("f","Assertion failed while verifying all segments are moved(Sec)");
					}
					assertion =timeIn_secondSegment_sec_afterALTR_int_selected_backward==timeIn_secondSegment_sec_beforeALTR_int_selected_backward-2;
					if (assertion == false) {
						report("f","Assertion failed while verifying all segments are moved(Sec)");
					}
					
					assertion =timeOut_firstSegment_sec_afterALTR_int_selected_backward==timeOut_firstSegment_sec_beforeALTR_int_selected_backward-2;
					if (assertion == false) {
						report("f","Assertion failed while verifying all segments are moved(Sec)");
					}
					assertion =timeOut_secondSegment_sec_afterALTR_int_selected_backward==timeOut_secondSegment_sec_beforeALTR_int_selected_backward-2;
					if (assertion == false) {
						report("f","Assertion failed while verifying all segments are moved(Sec)");
					}
					
					
					int timeIn_fifthSegment_frames_beforeALTR_int_selected=getTimeOut_frames_of_segment(5);
				    System.out.println("timeIn_fifthSegment_frames_beforeALTR_int_selected :"+timeIn_fifthSegment_frames_beforeALTR_int_selected);
				    timeIn_sixthSegment_frames_beforeALTR_int_selected=getTimeOut_frames_of_segment(6);
				    System.out.println("timeIn_sixthSegment_frames_beforeALTR_int_selected :"+timeIn_sixthSegment_frames_beforeALTR_int_selected);
				
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
					 General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_forward);
					 Thread.sleep(2000);   
					 General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_MoveSegments_move);
					 Thread.sleep(1000);
					 assertion = Verify.action().verifyTextPresent("Range selected has been moved successfully", 5);
					 if (assertion == false) {
							report("f","Assertion failed while verifying notification message for segment moved ");
					  }
				     
				     int timeIn_fifthSegment_frames_afterALTR_int_selected=getTimeOut_frames_of_segment(5);
				     System.out.println("timeIn_fifthSegment_frames_afterALTR_int_selected :"+timeIn_fifthSegment_frames_afterALTR_int_selected);
				     timeIn_sixthSegment_frames_afterALTR_int_selected=getTimeOut_frames_of_segment(6);
				     System.out.println("timeIn_sixthSegment_frames_afterALTR_int_selected :"+timeIn_sixthSegment_frames_afterALTR_int_selected);
				     
				     assertion =timeIn_fifthSegment_frames_afterALTR_int_selected==timeIn_fifthSegment_frames_beforeALTR_int_selected+2;
						if (assertion == false) {
							report("f","Assertion failed while verifying all segments are moved(Sec)");
					 }
					 assertion =timeIn_sixthSegment_frames_afterALTR_int_selected==timeIn_sixthSegment_frames_beforeALTR_int_selected+2;
					 if (assertion == false) {
							report("f","Assertion failed while verifying all segments are moved(Sec)");
					 }
				      
					
				
				
		    	
			
		
        
	}
	
	public int getTimeIn_sec_of_segment(int segment) {
		String timeIn=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(segment)).getText();
		System.out.println("timeIn :"+timeIn);
		String timeIn_int=timeIn.replaceAll("[^0-9]", "");
		System.out.println("timeIn_int :"+timeIn_int);
		
		String timeIn_sec=timeIn_int.substring(4,6);
		System.out.println("timeIn_int_sec :"+timeIn_sec);
		int timeIn_int_sec=Integer.parseInt(timeIn_sec);
		System.out.println("timeIn_int_sec :  "+timeIn_int_sec);
		return timeIn_int_sec;	
		
	}
	
	
	public int getTimeOut_sec_of_segment(int segment) {
		String timeOut=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeOut(segment)).getText();
		System.out.println("timeOut :"+timeOut);
		String timeOut_int=timeOut.replaceAll("[^0-9]", "");
		System.out.println("timeOut_int :"+timeOut_int);
		
		String timeOut_sec=timeOut_int.substring(4,6);
		System.out.println("timeOut_sec :"+timeOut_sec);
		int timeOut_int_sec=Integer.parseInt(timeOut_sec);
		System.out.println("timeOut_int_sec :  "+timeOut_int_sec);	
		return timeOut_int_sec;
		
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
    		
        	 assertion =timeIn_sixthSegment_frames_afterALTR_int_selected==timeIn_sixthSegment_frames_beforeALTR_int_selected+2;
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
    		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
    		if (result == "f")
    			assertTrue(false);

    	}
    	
    }