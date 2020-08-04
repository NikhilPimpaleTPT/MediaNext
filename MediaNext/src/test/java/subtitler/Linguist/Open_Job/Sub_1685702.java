package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
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
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This test case verifies video calibration behavior.
 *
 */
public class Sub_1685702 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1685702"+CommonElements.BROWSER+"K1";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String menu_completed = "Completed";
    String calibrateBy_hour="01";
    String TimeInSlot_sec_frames[]= {"NewTimeInSeconds","NewTimeInFrames"};
    String TimeInValues_sec_frames[]= {"15","20"};
    int secondSegment_frames;
	Boolean assertion = true;
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1685702");
		dataSet.put("TL_test_case_description", "SUB-1685702:Timecode synchronization Player & video for drop frame video format");
		dataSet.put("TL_internal_testCase_ID", "1685702");
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
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
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
			
			Thread.sleep(2000);
			
			General.action().logoutMethod();

			// trans login
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			translate_onGoing_submission(SubmissionName,targetlanguage,true,false);
			Thread.sleep(2000);
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void translate_onGoing_submission(String SubmissionName,String target,boolean complete ,boolean back) throws Exception {

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
		
		String timeInOfFristSegment_before=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText();
	    System.out.println(timeInOfFristSegment_before);
	    int firstSegment_hour_before = Integer.parseInt(timeInOfFristSegment_before.substring(0,2));
	    System.out.println("firstSegment_hour:"+firstSegment_hour_before);
	    String timeInOfsecondegment_before=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(2)).getText();
	    System.out.println(timeInOfsecondegment_before);
	    int secondSegment_hour_before = Integer.parseInt(timeInOfsecondegment_before.substring(0,2));
	    System.out.println("firstSegment_hour:"+secondSegment_hour_before);
	   
		
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
	  	Thread.sleep(1000);
	  	
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
		  	General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_C);
		  	Thread.sleep(1000);
		  	}else {
		  	Translator.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),Keys.chord(Keys.ALT, "c"));
			Thread.sleep(1000);
		}
		
		General.action().Click(TranslatorLocators.Locator().trans_ongoing_calibration_hour);
	  	Thread.sleep(1000);
	  	General.action().ClearInputvalues(TranslatorLocators.Locator().trans_ongoing_calibration_hour);
	  	Thread.sleep(1000);
	  	General.action().type(TranslatorLocators.Locator().trans_ongoing_calibration_hour, calibrateBy_hour);
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().trans_ongoing_calibration_calibrateButton);
	  	Thread.sleep(1000);
	  	 
	  	String timeInOfFristSegment_after=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText();
	    System.out.println(timeInOfFristSegment_after);
	    int firstSegment_hour_after = Integer.parseInt(timeInOfFristSegment_before.substring(0,2));
	    System.out.println("firstSegment_hour:"+firstSegment_hour_after);
	    String timeInOfsecondegment_after=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(2)).getText();
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
	    assertion = General.driver.findElement(TranslatorLocators.Locator().trans_ongoing_videoTime_hour).getText().contains(calibrateBy_hour);
	  	 if (assertion == false) {
	  	report("f","Assertion failed while verifying video time hour is calibrated");
		}

	  	//Verify Able to edit the TC of any segment.
	 	General.action().waitforelemenetpresent(CommonLocartors.Locator().EditSegment_textarea(3));
        Thread.sleep(1000);
	    General.action().Click(CommonLocartors.Locator().EditSegment_textarea(3));
	    Thread.sleep(3000);
		
		General.action().type(CommonLocartors.Locator().EditSegment_textarea(3),Keys.chord(Keys.ALT, "t"));
		Thread.sleep(3000);	
		
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlot_sec_frames[0]));
		Thread.sleep(3000);
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlot_sec_frames[0]));
		Thread.sleep(3000);
		General.action().Enter_keyEnvents(KeyEvent.VK_END);
	  	Thread.sleep(1000);
		General.action().Enter_keyEnvents(KeyEvent.VK_BACK_SPACE);
	  	Thread.sleep(1000);
		General.action().Enter_keyEnvents(KeyEvent.VK_BACK_SPACE);
	  	Thread.sleep(1000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlot_sec_frames[0]), TimeInValues_sec_frames[0]);
		Thread.sleep(2000);
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlot_sec_frames[0]));
		Thread.sleep(3000);
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlot_sec_frames[1]));
		Thread.sleep(3000);
		
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
		Thread.sleep(5000);
		
		General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
		Thread.sleep(3000);
		
		General.action().Click(CommonLocartors.Locator().EditSegment_textarea(3));
		Thread.sleep(3000);
			
	    General.action().type(CommonLocartors.Locator().EditSegment_textarea(3),Keys.chord(Keys.ALT, "t"));
	    Thread.sleep(3000);	
			
        General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlot_sec_frames[1]));
        Thread.sleep(3000);
		General.action().Enter_keyEnvents(KeyEvent.VK_END);
	  	Thread.sleep(1000);
		General.action().Enter_keyEnvents(KeyEvent.VK_BACK_SPACE);
	  	Thread.sleep(1000);
		General.action().Enter_keyEnvents(KeyEvent.VK_BACK_SPACE);
	  	Thread.sleep(1000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlot_sec_frames[1]), TimeInValues_sec_frames[1]);
		Thread.sleep(2000);
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlot_sec_frames[1]));
		Thread.sleep(3000);
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlot_sec_frames[0]));
		Thread.sleep(3000);
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
		Thread.sleep(3000);
		
	  	String timeInOfThirdSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(3)).getText();
	    System.out.println(timeInOfThirdSegment);
	    int firstSegment_sec = Integer.parseInt(timeInOfThirdSegment.substring(6,8));
	    System.out.println("firstSegment_sec:"+firstSegment_sec);
	    secondSegment_frames = Integer.parseInt(timeInOfThirdSegment.substring(9,11));
	    System.out.println("secondSegment_frames:"+secondSegment_frames);
			
		//Verify edited time code is displayed in the segment time code
		assertion =firstSegment_sec== Integer.parseInt(TimeInValues_sec_frames[0]);
	  	 if (assertion == false) {
	  	report("f","Assertion failed while verifying the edited time code of 3rd segment (seconds)");
		}
	 	assertion = secondSegment_frames== Integer.parseInt(TimeInValues_sec_frames[1]);
	  	 if (assertion == false) {
	  	report("f","Assertion failed while verifying the edited time code of 3rd segment (frames)");
	  	 }     
		
	
}
		
	public void assertion() throws Exception {
		assertion = secondSegment_frames== Integer.parseInt(TimeInValues_sec_frames[1]);
	  	 if (assertion == false) {
	  	report("f","f\",\"Assertion failed while verifying the edited time code of 3rd segment (frames)");
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