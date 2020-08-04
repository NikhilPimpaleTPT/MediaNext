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
import modules.PM_user;
import modules.Translator;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This test case verifies video calibration behavior.
 *
 */

public class Sub_1739942 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1739942"+CommonElements.BROWSER+"A1";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String menu_completed = "Completed";
    String calibrateBy_hour="01";
    String calibrationTimeCode;
	Boolean assertion = true;
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1739942");
		dataSet.put("TL_test_case_description", "SUB-1739942:New video's calibration behavior");
		dataSet.put("TL_internal_testCase_ID", "1739942");
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
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_DROPFRAME_VIDEO_ASSET_ID);
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
			
			Thread.sleep(2000);
			
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			PM_transcription_onGoing_Submission(SubmissionName,targetlanguage);
	        Thread.sleep(1000);
			
			
			

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void PM_transcription_onGoing_Submission(String SubmissionName, String target) throws Exception {

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
        
    	String timeInOfFristSegment_before=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(1)).getText();
	    System.out.println(timeInOfFristSegment_before);
	    int firstSegment_hour_before = Integer.parseInt(timeInOfFristSegment_before.substring(0,2));
	    System.out.println("firstSegment_hour:"+firstSegment_hour_before);
	    String timeInOfsecondegment_before=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(2)).getText();
	    System.out.println(timeInOfsecondegment_before);
	    int secondSegment_hour_before = Integer.parseInt(timeInOfsecondegment_before.substring(0,2));
	    System.out.println("firstSegment_hour:"+secondSegment_hour_before);
	   
		
	    Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
	  	
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
		  	General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_C);
		  	Thread.sleep(1000);
		  	}else {
//		  	Translator.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1),Keys.chord(Keys.ALT, "c"));
//			Thread.sleep(1000);
		  	General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_C);
		}
		
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_calibration_hour);
	  	Thread.sleep(1000);
	  	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_calibration_hour);
	  	Thread.sleep(1000);
	  	General.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_calibration_hour, calibrateBy_hour);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_calibration_calibrateButton);
	  	Thread.sleep(1000);
	  	 
	  	String timeInOfFristSegment_after=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(1)).getText();
	    System.out.println(timeInOfFristSegment_after);
	    int firstSegment_hour_after = Integer.parseInt(timeInOfFristSegment_before.substring(0,2));
	    System.out.println("firstSegment_hour:"+firstSegment_hour_after);
	    String timeInOfsecondegment_after=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(2)).getText();
	    System.out.println(timeInOfsecondegment_after);
	    int secondSegment_hour_after = Integer.parseInt(timeInOfsecondegment_before.substring(0,2));
	    System.out.println("firstSegment_hour:"+secondSegment_hour_after);
	    
	    //Verify no captions in the table should be updated.
	    assertion=firstSegment_hour_after==firstSegment_hour_before;
	    if (assertion == false) {
		report("f","Assertion failed while verifying the table timecode(hour) is not changed");
	    }
	    assertion=secondSegment_hour_after==secondSegment_hour_before;
	    if (assertion == false) {
		report("f","Assertion failed while verifying the table timecode(hour) is not changed");
	    }
	    
	    
	    //Verify video time counter on the left-bottom player side is updated with +1 hour.
	    assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_transcription_ongoing_videoTime_hour).getText().contains(calibrateBy_hour);
	  	 if (assertion == false) {
	  	report("f","Assertion failed while verifying video time hour is calibrated");
		}
        
	  	 
	  	//Verify when creating new captions will reflect the calibration on the caption table.
	    Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
		Thread.sleep(2000);
		
		String videoTime_hour_beforSegmentEnter= General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(1)).getText();
		System.out.println("videoTime_hour_beforSegmentEnter-->"+videoTime_hour_beforSegmentEnter);

	  	 
	  	String videoTime_Min_beforSegmentEnter= General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(3)).getText();
		System.out.println("videoTime_Min_beforSegmentEnter-->"+videoTime_Min_beforSegmentEnter);
		  
		String videoTime_Sec_beforSegmentEnter= General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(5)).getText();
		System.out.println("videoTime_Sec_beforSegmentEnter-->"+videoTime_Sec_beforSegmentEnter);
		
		String videoTime_frames_beforSegmentEnter= General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(5)).getText();
		System.out.println("videoTime_Sec_beforSegmentEnter-->"+videoTime_frames_beforSegmentEnter);
		
		String calibrationTimeCode_before=videoTime_hour_beforSegmentEnter+":"+videoTime_Min_beforSegmentEnter+":"+videoTime_Sec_beforSegmentEnter+":"+videoTime_frames_beforSegmentEnter;
		System.out.println(calibrationTimeCode_before);
	  	 
	  	PM_user.action().transcription_keyEnvents(KeyEvent.VK_ESCAPE);
		  
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_N);
		  
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea," Testing transcription New Segment");
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.SHIFT,Keys.ALT, Keys.ENTER));
		Thread.sleep(3000);
		 
		String videoTime_hour_afterSegmentEnter= General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(1)).getText();
		System.out.println("videoTime_hour_afterSegmentEnter-->"+videoTime_hour_afterSegmentEnter);

	  	 
	  	String videoTime_Min_afterSegmentEnter= General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(3)).getText();
		System.out.println("videoTime_Min_afterSegmentEnter-->"+videoTime_Min_afterSegmentEnter);

		  
		String videoTime_Sec_afterSegmentEnter= General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(5)).getText();
		System.out.println("videoTime_Sec_afterSegmentEnter-->"+videoTime_Sec_afterSegmentEnter);

		
		String videoTime_frames_afterSegmentEnter= General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(7)).getText();
		System.out.println("videoTime_frames_afterSegmentEnter-->"+videoTime_frames_afterSegmentEnter);
		calibrationTimeCode=videoTime_hour_afterSegmentEnter+":"+videoTime_Min_afterSegmentEnter+":"+videoTime_Sec_afterSegmentEnter+":"+videoTime_frames_afterSegmentEnter;
		System.out.println(calibrationTimeCode);
		
		
		//Verify calibration on new created caption timecode
		assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(7)).getText().contains(calibrationTimeCode_before);
	  	 if (assertion == false) {
	  	report("f","Assertion failed while verifying video time hour is calibrated");
		}

		assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(7)).getText().contains(calibrationTimeCode);
	  	 if (assertion == false) {
	  	report("f","Assertion failed while verifying video time hour is calibrated");
		}
    
        
	}
		
		
		
		public void assertion() throws Exception {
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(7)).getText().contains(calibrationTimeCode);
		  	 if (assertion == false) {
		  	report("f","Assertion failed while verifying video time hour is calibrated");
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
