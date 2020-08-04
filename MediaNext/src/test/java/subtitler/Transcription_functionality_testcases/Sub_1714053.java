package subtitler.Transcription_functionality_testcases;

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
import modules.PM_user;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This testcase verifies that 4 lines in the subtitle are displaying properly in transcription view.
 *
 */

public class Sub_1714053 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_1714053"+CommonElements.BROWSER+"A2";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String maxLinePerSub="4";
	//While entering four line text we need \r in FF so creating separate variable for FF
	String fourLineSegment="Transcription Segment line 1\n" + 
			"Transcription Segment line 2\n" + 
			"Transcription Segment line 3\n" + 
			"Transcription Segment line 4";
	String fourLineSegment_f ="Transcription Segment line 1\r\n" + 
			"Transcription Segment line 2\r\n" + 
			"Transcription Segment line 3\r\n" + 
			"Transcription Segment line 4";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_1714053");
		dataSet.put("TL_test_case_description","Sub_1714053: 4 lines of subtitle display in video and transcription phase ");
		dataSet.put("TL_internal_testCase_ID", "1714053");
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
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1_forCustomizedLine("17", "35", "80", "100",maxLinePerSub);
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
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
			PM_transcription_ongoing(SubmissionName,targetlanguage_1,false,true);
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
        Thread.sleep(5000);
        
        //CLICK ON SEGMENT NO 2
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
	    Thread.sleep(1000);
	    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
	    Thread.sleep(1000);
	    General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
	    Thread.sleep(2000);
	    if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,fourLineSegment);
		Thread.sleep(2000);
		}else {
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,fourLineSegment_f);//fourLineSegment_f (Sometimes work with /n and sometimes needs /r/n)
		Thread.sleep(2000);
		}
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
	    Thread.sleep(1000);
	    System.out.println("four Line Segment"+fourLineSegment);
	    System.out.println("four Line Segment"+fourLineSegment_f);
	    if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
	    assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2)).getText().contains(fourLineSegment);
		if (assertion == false) {
			report("f","Assertion failed while verifying fourLineSegment text");
		}
	    }else {
	    assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2)).getText().contains(fourLineSegment);
		if (assertion == false) {
		report("f","Assertion failed while verifying fourLineSegment text");
		}
	    	
	    }
		Thread.sleep(4000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		Thread.sleep(1000);
		
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
		assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea).getText().contains(fourLineSegment);
		if (assertion == false) {
				report("f","Assertion failed while verifying fourLineSegment text");
		}
		}else {
		assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea).getText().contains(fourLineSegment);
	    if (assertion == false) {
		report("f","Assertion failed while verifying fourLineSegment text");
		}
		}
		
        
	}

	public void assertion() throws Exception {
		//This TC is block as need to verify scroll bar for four line segment(Locator is not present for scroll bar).
		//TC Step No 3 :lines for a subtitle should displayed properly on the video and in subtitle table. For four lines of subtitle in grid should have scroll bar.
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea).getText().contains(fourLineSegment);
			if (assertion == false) {
					report("f","Assertion failed while verifying fourLineSegment text");
			}else {
				report("b", "Last assertion is blocked.");
			}
			}else {
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea).getText().contains(fourLineSegment);
		    if (assertion == false) {
			report("f","Assertion failed while verifying fourLineSegment text");
			}else {
				report("b", "This TC is blocked .");
			}
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

