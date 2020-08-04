package subtitler.project_manager.submission;

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
 *Summary: Display the subtitle's duration in red when the min / max duration is not respected
 *Preconditions:While creating a submission set min duration of sub as 1 and max duration of sub as 8. Use transcr+trans_review workflow.
 */

public class Sub_1644898 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1644898"+CommonElements.BROWSER+"AQ";
	 String OrganizationName = "Subtitle_Common_orgs";
	    String WorkflowName = "Three_Step_Transc_Workflow";
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
		String durationColor_review;
		Boolean assertion = true;
		
		
		@BeforeMethod
		public void setup() throws Exception {
			General.action().startSystem("SUB_1644898");
			dataSet.put("TL_test_case_description", "SUB-1644898: :Display the subtitle's duration in red when the min / max duration is not respected.");
			dataSet.put("TL_internal_testCase_ID", "1644898");
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
				PM_user.action().CreateSubmisson_Step1("17", "35", "1", "8");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow_ThreeStepWorkflow(OrganizationName, WorkflowName,"transcription",transcGroupName,"Trans",TranslatorGroupName,true, "Review", ReviewGroupName, true);
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
				
				PM_user.action().Navigate(menu_to_claim);
				Thread.sleep(1000);
				PM_user.action().PM_ToClaim(SubmissionName);
				Thread.sleep(1000);
				//NAVIGATE TO ONGOING AND OPEN JOB
				PM_user.action().Navigate(menu_ongoing);
				Thread.sleep(1000);
				PM_transcription_onGoing_Submission(SubmissionName,targetlanguage);
				
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
	        Thread.sleep(40000);
	        
	        General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_rightArrow);
	        Thread.sleep(2000);
	        General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_rightArrow);
	        Thread.sleep(2000);
	        General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_rightArrow);
	        Thread.sleep(2000);
	        
	    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_TaskSetting_button);
			Thread.sleep(2000);
			
			String minDurationOfSub=General.driver.findElement(Pm_User_Submission_Locators.Locator().taskInfo_minDurationOfSub).getText();
			System.out.println("minDurationOfSub--->"+minDurationOfSub);
			String minDurationOfSub_number=minDurationOfSub.replaceAll("[^0-9]", "");
			System.out.println("minDurationOfSub_number-->"+minDurationOfSub_number);
			
			String maxDurationOfSub=General.driver.findElement(Pm_User_Submission_Locators.Locator().taskInfo_maxDurationOfSub).getText();
			System.out.println("maxDurationOfSub---->"+maxDurationOfSub);
			String maxDurationOfSub_number=maxDurationOfSub.replaceAll("[^0-9]", "");
			System.out.println("minDurationOfSub_number-->"+maxDurationOfSub_number);
			
			String DurationOfFirstSegment=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_duration(1,9)).getText();
			System.out.println("maxDurationOfSub---->"+DurationOfFirstSegment);
			
			String DurationOfSixthSegment=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_duration(6,9)).getText();
			System.out.println("DurationOfSixthSegment---->"+DurationOfSixthSegment);
			
			assertion =Float.parseFloat(maxDurationOfSub_number)<Float.parseFloat(DurationOfSixthSegment);
			if (assertion == false) {
			report("f","Assertion failed while verifying duration is greater than max duration of sub");
			}
			
			
			String durationColor = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_duration(6,8)).getCssValue("color");
			System.out.println("durationColor:-"+ durationColor);
	        
			if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH"))  {
			assertion=durationColor.equalsIgnoreCase("rgba(255, 255, 255, 1)");
			if (assertion == false) {
				report("f","Assertion failed while verifying color of duration");
			}
			}else{
			assertion=durationColor.equalsIgnoreCase("rgb(255, 255, 255)");
			if (assertion == false) {
			report("f","Assertion failed while verifying color of duration");
			}
				
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
		    
		    
		    General.action().Click(TranslatorLocators.Locator().Trans_ongoing_taskInfo_button);
			Thread.sleep(2000);
			
			String minDurationOfSub=General.driver.findElement(TranslatorLocators.Locator().taskInfo_minDurationOfSub).getText();
			System.out.println("minDurationOfSub--->"+minDurationOfSub);
			String minDurationOfSub_number=minDurationOfSub.replaceAll("[^0-9]", "");
			System.out.println("minDurationOfSub_number-->"+minDurationOfSub_number);
			
			String maxDurationOfSub=General.driver.findElement(TranslatorLocators.Locator().taskInfo_maxDurationOfSub).getText();
			System.out.println("maxDurationOfSub---->"+maxDurationOfSub);
			String maxDurationOfSub_number=maxDurationOfSub.replaceAll("[^0-9]", "");
			System.out.println("minDurationOfSub_number-->"+maxDurationOfSub_number);
			
			String DurationOfFirstSegment=General.driver.findElement(TranslatorLocators.Locator().translator_duration(1)).getText();
			System.out.println("maxDurationOfSub---->"+DurationOfFirstSegment);
			
			String DurationOfSixthSegment=General.driver.findElement(TranslatorLocators.Locator().translator_duration(6)).getText();
			System.out.println("DurationOfSixthSegment---->"+DurationOfSixthSegment);
			
			assertion =Float.parseFloat(maxDurationOfSub_number)<Float.parseFloat(DurationOfSixthSegment);
			if (assertion == false) {
			report("f","Assertion failed while verifying duration is greater than max duration of sub");
			}
			
			
			String durationColor = General.driver.findElement(TranslatorLocators.Locator().translator_duration(6)).getCssValue("color");
			System.out.println("durationColor:-"+ durationColor);
	        
			if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
			 assertion=durationColor.equalsIgnoreCase("rgba(255, 79, 100, 1)");
			 if (assertion == false) {
			 report("f","Assertion failed while verifying color of duration");
			}
			}else{
			 assertion=durationColor.equalsIgnoreCase("rgb(255, 79, 100)");
			 if (assertion == false) {
			 report("f","Assertion failed while verifying color of duration");
			}
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
				    
				    
			General.action().Click(ReviewerLocators.Locator().review_ongoing_taskInfo_button);
			Thread.sleep(2000);
					
			String minDurationOfSub=General.driver.findElement(ReviewerLocators.Locator().taskInfo_minDurationOfSub).getText();
			System.out.println("minDurationOfSub--->"+minDurationOfSub);
			String minDurationOfSub_number=minDurationOfSub.replaceAll("[^0-9]", "");
			System.out.println("minDurationOfSub_number-->"+minDurationOfSub_number);
					
			String maxDurationOfSub=General.driver.findElement(ReviewerLocators.Locator().taskInfo_maxDurationOfSub).getText();
			System.out.println("maxDurationOfSub---->"+maxDurationOfSub);
			String maxDurationOfSub_number=maxDurationOfSub.replaceAll("[^0-9]", "");
			System.out.println("minDurationOfSub_number-->"+maxDurationOfSub_number);
					
			String DurationOfFirstSegment=General.driver.findElement(ReviewerLocators.Locator().review_duration(1)).getText();
			System.out.println("maxDurationOfSub---->"+DurationOfFirstSegment);
					
			String DurationOfSixthSegment=General.driver.findElement(ReviewerLocators.Locator().review_duration(6)).getText();
			System.out.println("DurationOfSixthSegment---->"+DurationOfSixthSegment);
					
			assertion =Float.parseFloat(maxDurationOfSub_number)<Float.parseFloat(DurationOfSixthSegment);
			if (assertion == false) {
			report("f","Assertion failed while verifying duration is greater than max duration of sub");
			}
					
					
			durationColor_review = General.driver.findElement(ReviewerLocators.Locator().review_duration(6)).getCssValue("color");
			System.out.println("durationColor:-"+ durationColor_review);
			
			//TODO Main last assertion
			if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH"))  {
				assertion=durationColor_review.equalsIgnoreCase("rgba(255, 79, 100, 1)");
				if (assertion == false) {
					report("f","Assertion failed while verifying color of duration");
			}
			}else{
				assertion=durationColor_review.equalsIgnoreCase("rgb(255, 79, 100)");
				if (assertion == false) {
				report("f","Assertion failed while verifying color of duration");
			}
					
			}
				    
				    
		}
		
		public void assertion() throws Exception {
			
			//TODO last assertion is added in last review phase
			assertion=Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_duration(6), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying color of duration");
			}
		    else {
					report("p", "All assertions are passsed ");
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

