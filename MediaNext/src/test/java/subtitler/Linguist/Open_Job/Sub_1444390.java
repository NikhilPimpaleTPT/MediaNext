package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
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
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary:This testcase verifies the watermark as email of connected user on videos in 
1. Translation 2. Review 3. Transcription
 *
 */ 


public class Sub_1444390 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1444390"+CommonElements.BROWSER+"T8";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Three_Step_Transc_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String menu_completed = "Completed";
	String path;
	Boolean assertion = true;
	File filepath1;
	
	
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1444390");
		dataSet.put("TL_test_case_description", "SUB-1444390:Watermark on videos - email of user connected.");
		dataSet.put("TL_internal_testCase_ID", "1444390");
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
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow_ThreeStepWorkflow(OrganizationName, WorkflowName,"transcription",transcGroupName,"Trans",TranslatorGroupName,true, "Review", ReviewGroupName, true);
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
			 PM_user.action().Navigate(menu_to_claim);
	         Thread.sleep(1000);
	         PM_user.action().PM_ToClaim(SubmissionName);
	         Thread.sleep(1000);
	         PM_user.action().Navigate(menu_ongoing);
	         Thread.sleep(1000);
	         PM_transcription_onGoing_ReadingSpeed_Submission(SubmissionName,targetlanguage);
	         
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
			
			General.action().logoutMethod();
			
			// Review LogIn 
		     General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
	         Thread.sleep(1000);
	         Reviewer.action().Navigate(menu_to_claim);
	         Thread.sleep(1000);
	         Reviewer.action().review_ToClaim(SubmissionName);
	         Thread.sleep(1000);
	         Reviewer.action().Navigate(menu_ongoing);
	         Thread.sleep(1000);
	         Reviewer_onGoing_submission(SubmissionName, targetlanguage, false, false);
			 Thread.sleep(2000);
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void PM_transcription_onGoing_ReadingSpeed_Submission(String SubmissionName, String target ) throws Exception {

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
        
        
        assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().transcription_ongoing_waterMark, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying logged in user email id is showing on the video as watermark.");
		}
        
        
        
        General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
		Thread.sleep(3000);
		if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
		}
        
        
        
	}
	
	
	public void translate_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
		
		 System.out.println("INSIDE trans_Ongoing  method()");
		 
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
			Thread.sleep(5000);
				  
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_ongoing_waterMark, 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying logged in user email id is showing on the video as watermark.");
			}
				  
				  
				  
			if(complete){
					  General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
						Thread.sleep(1000);
						General.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
						Thread.sleep(1000);
						General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
						Thread.sleep(1000);
						General.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
						Thread.sleep(3000);
						if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
							General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
							Thread.sleep(1000);
							General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
					    	}
					}
					
				    
				 if(back){
				    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
							General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
							Thread.sleep(1000);
							General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
					    	}
				    }
				    
			
			      
	}
	
	
	public void Reviewer_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
		
		 System.out.println("INSIDE trans_Ongoing  method()");
		 
		    General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().review_search_input);
			Thread.sleep(1000);
			General.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
			Thread.sleep(1000);
			General.action().type(ReviewerLocators.Locator().review_search_input, SubmissionName);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target)))
			{
				System.out.println("INSIDE IF--------");
				General.action().Click(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target));
			}
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
				  
				  
		    assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_ongoing_waterMark, 5);
		    if (assertion == false) {
		    report("f","Assertion failed while verifying logged in user email id is showing on the video as watermark.");
		    }
	}
	
	
	
	public void assertion() throws Exception {
		 assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_ongoing_waterMark, 5);
		    if (assertion == false) {
		    report("f","Assertion failed while verifying logged in user email id is showing on the video as watermark.");
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
