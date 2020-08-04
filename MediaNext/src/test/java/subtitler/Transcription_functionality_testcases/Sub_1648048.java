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
import locators.LoginLocators;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary:This testcase verifies that if last working subtitle id is save in submission after navigated from other tabs/folders.
 *
 */ 

public class Sub_1648048 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1648048"+CommonElements.BROWSER+"F1";
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
	String path;
    
    Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1648048");
		dataSet.put("TL_test_case_description", "SUB-1648048:Save last session working subtitle ID");
		dataSet.put("TL_internal_testCase_ID", "1648048");
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
	         
	         General.action().logoutMethod();
	         
	         
		
			General.action().login(CommonElements.action().PM_username, CommonElements.action().password);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
			Thread.sleep(200);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_indexCaption("Go to #5"), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying caption id");
			}
			
			
			
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
        Thread.sleep(10000);
        
        Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		 
		General.action().driver.navigate().refresh();
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_indexCaption("Go to #1"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		
		
		//Split segment
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(5));
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
			General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_P);
			Thread.sleep(1000);
			}else {
		//TODO NOT WORKING
//		General.action().type(CommonLocartors.Locator().EditSegment_textarea(5),Keys.chord(Keys.ALT, "p"));
//		Thread.sleep(3000);	
			General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_P);
			Thread.sleep(1000);
		
		}

		navigateTabs();
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_indexCaption("Go to #1"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		
		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_indexCaption("Go to #5"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		
		//Merge segments
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		
		if(CommonElements.BROWSER.contains("CHROME")) {
			General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_M);
			Thread.sleep(1000);
		}else {
		
			General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_M);
			Thread.sleep(1000);
		//TODO NOT WORKING
//		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, "m"));
//		Thread.sleep(3000);	
		}
		
        navigateTabs();
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_indexCaption("Go to #1"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		
		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_indexCaption("Go to #2"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		
		
		//Add comment
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_button);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Styling_SegmentHistory);
		Thread.sleep(1000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentInput,"Comment for segment");
		Thread.sleep(5000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(1000);
		
		navigateTabs();
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_indexCaption("Go to #1"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
			
		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_indexCaption("Go to #2"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		
		
		//TODO This functionality is not For Transcription
		//Delete segment
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(4));
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_BACK_SPACE);
		Thread.sleep(1000);
		Thread.sleep(2000);
		
		
		navigateTabs();
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_indexCaption("Go to #1"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
			
		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_indexCaption("Go to #4"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(5));
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Typing in th segment");
        
	}
	public void navigateTabs() throws Exception {
		
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
		Thread.sleep(1000);
		General.action().mouseOver(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
     	Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
		Thread.sleep(1000);
		General.action().Click(CommonLocartors.Locator().SelectMenu(menu_to_claim));
		Thread.sleep(1000);
		General.action().Click(CommonLocartors.Locator().SelectMenu(menu_ongoing));
		Thread.sleep(1000);
		PM_user.action().SearchSubmisson_andCheck(SubmissionName);
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
		Thread.sleep(500);
		   
	}
	
	 public void login(String username,String password) throws Exception{
		 System.out.println("INSIDE General login method ");
		    PM_user.action().waitforelemenetpresent(LoginLocators.Locator().Email_input);
		    PM_user.action().ClearInputvalues(LoginLocators.Locator().Email_input);
			Thread.sleep(1000);
			PM_user.action().type(LoginLocators.Locator().Email_input, username);
			Thread.sleep(1000);
			PM_user.action().type(LoginLocators.Locator().Email_input,Keys.chord(Keys.TAB));			
			if(Verify.action().verifyElementPresent(LoginLocators.Locator().continue_button, 5)){
				System.out.println("IN IF****************");
				PM_user.action().Click(LoginLocators.Locator().continue_button);
				Thread.sleep(1000);
			}
			PM_user.action().waitforelemenetpresent(LoginLocators.Locator().password_input);
			PM_user.action().ClearInputvalues(LoginLocators.Locator().password_input);
			Thread.sleep(1000);
			PM_user.action().type(LoginLocators.Locator().password_input, password);
			Thread.sleep(1000);
			PM_user.action().Click(LoginLocators.Locator().continue_button);
			Thread.sleep(1000);
			System.out.println("EOM General login ()");
		}
	
	
	

	public void assertion() throws Exception {
		//Verify caption ID
		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_indexCaption("Go to #1"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
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