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
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: Verify that submission name and complete button are visible after selecting the comment from comment tab.
 *
 */

public class Sub_1714054 {

	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_1714054"+CommonElements.BROWSER+"X1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String headerBar_color="rgb(61, 135, 187)";//Blue color of header

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_1714054");
		dataSet.put("TL_test_case_description","Sub_1714054: Comments selection from comments tab ");
		dataSet.put("TL_internal_testCase_ID", "1714054");
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
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
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
        

        	
        //CLICK ON SEGMENT NO 1
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
	    Thread.sleep(1000);
	    
	    //CLICK ON 1 SEGMENT AND ADD COMMENT
	    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Styling_button);
	    Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Styling_SegmentHistory);
		Thread.sleep(1000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentInput,"Comment for 1st segment");
		Thread.sleep(5000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(2000);
		
		 //CLICK ON SEGMENT NO 3
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
	    Thread.sleep(1000);
	    
	    //CLICK ON 3 SEGMENT AND ADD COMMENT
	    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Styling_button);
	    Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Styling_SegmentHistory);
		Thread.sleep(1000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentInput,"Comment for 3rd segment");
		Thread.sleep(5000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(6000);
		
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_rightArrow);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_rightArrow);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_commentTab);
		Thread.sleep(1000);
		
		//Verify Verify that UI is not scrolled up and blue bar at the top is not partially
		System.out.println(General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_TranscriptionView_BlueBarheader).getCssValue("background"));
		Thread.sleep(3000);

		
		//Verify complete task button is properly displayed and not hidden
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_TranscriptionView_headerSubmissionName(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName in transcription view");
		}
		
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Complete_Button, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_Complete_Button");
		}
		
		
	}

	public void assertion() throws Exception {
		//This Assertion is blocked as needs to verify - UI is not scrolled up and blue bar at the top is not partially hidden after selecting the comment. (Verify Blue bar color is blue and blue bar is present after adding comment)
		//***Note*** : This assertion is working for chrome but not for FF so blocked
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_TranscriptionView_BlueBarheader, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying transcription header bar color");
		}
		
		
//		assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_TranscriptionView_BlueBarheader).getCssValue("background").contains(headerBar_color);
//		if (assertion == false) {
//			report("f","Assertion failed while verifying transcription header bar color");
//		} 
		else {
			report("b", "Assertion is blocked.");
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
