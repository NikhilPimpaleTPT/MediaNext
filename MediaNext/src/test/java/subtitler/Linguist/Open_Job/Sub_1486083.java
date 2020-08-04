package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

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
 *Summary: This testcase verifies the message on the complete task dialog.
 *
 */


public class Sub_1486083 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Sub_1486083"+CommonElements.BROWSER+"B2";
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
    String videoTime_seconds_after_alt_up;
    String Text_ToCheck_ThreeStep_Workflow[]= {"Jellyfish at the Monterey Aquarium.","Dude get out of the way!","Shaky Hands","Ah yes, this is better","Thanks for watching and I hope you'll have fun with the VideoSub library.","Thanks for watching and I hope you'll have fun with the VideoSub library."};
    Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1486083");
		dataSet.put("TL_test_case_description", "SUB-1486083:Message displayed when completing a task (Transcription / Translation / Review).");
		dataSet.put("TL_internal_testCase_ID", "1486083");
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
			
			 PM_user.action().Navigate(menu_to_claim);
	         Thread.sleep(1000);
	         PM_user.action().PM_ToClaim(SubmissionName);
	         Thread.sleep(1000);
	         PM_user.action().Navigate(menu_ongoing);
	         Thread.sleep(1000);
	         PM_transcription_onGoing_Submission(SubmissionName,targetlanguage,Text_ToCheck_ThreeStep_Workflow);
	         Thread.sleep(2000);
	         PM_user.action().Navigate(menu_completed);
	         
	         PM_user.action().SearchSubmisson(SubmissionName);
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
			Translator.action().Navigate(menu_completed);
			Thread.sleep(2000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
					
			}
				
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
		    Reviewer.action().Navigate(menu_completed);
			Thread.sleep(2000);
			Reviewer.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void PM_transcription_onGoing_Submission(String SubmissionName, String target,String[] Text_ToCheck_ThreeStep_Workflow ) throws Exception {

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
        
        for(int i=1;i<=6;i++){
        	
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(i));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(i));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		
		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Text_ToCheck_ThreeStep_Workflow[i-1]);
		Thread.sleep(4000);
       
        }
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
		Thread.sleep(1000);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_completeTask_message("By marking this task as completed, you confirm that the QA and spell checks have been performed."), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying complete tas message");
		}
	    
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_completeTask_message("You will not be able to edit this task anymore."), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying complete tas message");
		}
		
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_cancelButton);
		Thread.sleep(1000);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying target segment");
		}
		
		
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
		Thread.sleep(500);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_completedTask_completeTaskPopUp, 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying complete task pop up");
		 
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
		Thread.sleep(5000);
		
		

		 Thread.sleep(5000);
	     List <WebElement> listofIds1= General.action().driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
		 Thread.sleep(1000);
		 System.out.println("No of IDS--------"+listofIds1.size());
		 Thread.sleep(3000);
		  
	    for(int i=1;i<=listofIds1.size();i++){
	        Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
			Thread.sleep(1000);
			
		
	    }
	    
	    General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
		Thread.sleep(1000);
	    General.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
		Thread.sleep(1000);
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Translator_completeTask_message("By marking this task as completed, you confirm that the QA and spell checks have been performed."), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying complete tas message");
		}
	    
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Translator_completeTask_message("You will not be able to edit this task anymore."), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying complete tas message");
		}
		
		General.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_cancelButton);
		Thread.sleep(1000);
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying target segment");
		}
		
		
		General.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_completeButton);
		Thread.sleep(500);
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Translator_completedTask_completeTaskPopUp, 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying complete task pop up");
		 
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
				
				Thread.sleep(5000);
				List <WebElement> listofIds1= General.action().driver.findElements(ReviewerLocators.Locator().reviewer_toClaim_ListofallIds);
				Thread.sleep(1000);
			    System.out.println("No of IDS--------"+listofIds1.size());
				Thread.sleep(3000);
					  
				for(int i=1;i<=listofIds1.size();i++){
				Thread.sleep(2000);
				General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
			    Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
				Thread.sleep(1000);
				General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
				Thread.sleep(1000);
					
				}
				
				
				General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_Complete_Button);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_Complete_Button);
				Thread.sleep(1000);
				assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_completeTask_message("By marking this task as completed, you confirm that the QA and spell checks have been performed."), 5);
				if (assertion == false) {
				report("f","Assertion failed while verifying complete tas message");
				}
			    
				assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_completeTask_message("You will not be able to edit this task anymore."), 5);
				if (assertion == false) {
				report("f","Assertion failed while verifying complete tas message");
				}
				
				General.action().Click(ReviewerLocators.Locator().reviewer_CompleteDailoge_cancelButton);
				Thread.sleep(1000);
				
				assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1), 5);
				if (assertion == false) {
				report("f","Assertion failed while verifying target segment");
				}
				Thread.sleep(2000);
				
				General.action().Click(ReviewerLocators.Locator().reviewer_Complete_Button);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_CompleteDailoge_Button);
				Thread.sleep(500);
				
				assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_completedTask_completeTaskPopUp, 5);
				if (assertion == false) {
				report("f","Assertion failed while verifying complete task pop up");
				 
				}
				System.out.println("EOM trans_Ongoing  method()");
		}

	
	
	
	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
			
		}else {
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
