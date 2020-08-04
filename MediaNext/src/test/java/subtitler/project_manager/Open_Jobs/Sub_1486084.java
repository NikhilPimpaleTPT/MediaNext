package subtitler.project_manager.Open_Jobs;

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
import locators.Admin_User_Submission_Locators;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;
import modules.admin;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This testcase verifies that comments tab shows all the subtitles which has comments.
 Preconditions:Create a submission with trans+review workflow and claim the submission in translator UI.
 */ 

public class Sub_1486084 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName1 = "SUB_1486084_1"+CommonElements.BROWSER+"J2";
	String SubmissionName2 = "SUB_1486084_2"+CommonElements.BROWSER+"J2";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String targetlanguage_1 = "German (Germany)";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_Completed = "Completed";
	String text_firstSegment="text for first segment";
	String text_fourthSegment="text for fourth segment";
	String comment_firstSegment="Comment for first segment";
	String longComment="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	String comment_fourthSegment="Comment for fourth segment";
	String RoleName_trans="Role_Translator";
	String RoleName_review="Role_Review";
	String Roles_permission_View[] = {"View History Of Segments"};
	@BeforeMethod
	public void setup() throws Exception {General.action().startSystem("SUB_1486084");
		dataSet.put("TL_test_case_description", "SUB-1486084:Comments tab.");
		dataSet.put("TL_internal_testCase_ID", "1486084");
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
			Thread.sleep(20000);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION , USE SRT FILE ATTACHED IN TEST CASE
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName,true);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName1,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(15000);
			//VERIFY SUBMISSION NAME
			PM_user.action().SearchSubmisson(SubmissionName1);
			Thread.sleep(1000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName1), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(1000);
			General.action().logoutMethod();
			Thread.sleep(1000);
			
			
			
			// trans login
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(20000);
		    Translator.action().Navigate(menu_to_claim);
		    Thread.sleep(1000);
		    Translator.action().trans_ToClaim(SubmissionName1);
			Thread.sleep(1000);
		    Translator.action().Navigate(menu_ongoing);
		    Thread.sleep(1000);
		    translate_onGoing_submission(SubmissionName1,targetlanguage_1,true,true);     
		    Thread.sleep(1000);
		    
		    Thread.sleep(1000);
			General.action().logoutMethod();
			Thread.sleep(1000);
			
			
			//Review LogIn 
		     General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
		     Thread.sleep(20000);
	         Reviewer.action().Navigate(menu_to_claim);
	         Thread.sleep(1000);
	         Reviewer.action().review_ToClaim(SubmissionName1);
	         Thread.sleep(1000);
	         Reviewer.action().Navigate(menu_ongoing);
	         Thread.sleep(1000);
	         Reviewer_onGoing_submission(SubmissionName1, targetlanguage_1, true, false);
			 Thread.sleep(2000);
			 
			 Thread.sleep(1000);
			 General.action().logoutMethod();
			 Thread.sleep(1000);
			 
			General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			Thread.sleep(20000);
			 admin.action().Navigate("Roles");
			 Thread.sleep(1000);
			 admin.action().SearchRole(RoleName_trans);
			 Thread.sleep(1000);
			 admin.action().EditRole_open(RoleName_trans);
			 admin.action().Create_AND_EditRole_Permission_View(Roles_permission_View,true);
			 admin.action().Create_And_SaveRole_CreateStep();
			 
			 admin.action().Navigate("Roles");
			 Thread.sleep(1000);
			 admin.action().SearchRole(RoleName_review);
			 Thread.sleep(1000);
			 admin.action().EditRole_open(RoleName_review);
			 admin.action().Create_AND_EditRole_Permission_View(Roles_permission_View,true);
			 admin.action().Create_And_SaveRole_CreateStep();
			 
			 PM_user.action().Navigate(menu_Submission);
			 // TODO NEW IMPL OF SUBMISSION CREATION , USE SRT FILE ATTACHED IN TEST CASE
			 admin.action().CreateSubmisson_Step1("17", "35", "80", "100");
			 admin.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName,true);
			 admin.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			 admin.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			 admin.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName2,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			 Thread.sleep(8000);
			 //VERIFY SUBMISSION NAME
			 admin.action().SearchSubmisson(SubmissionName2);
			 Thread.sleep(1000);
				
			 assertion = Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName2), 5);
			 if (assertion == false) {
					report("f", "Assertion failed while verifying SubmissionName  after Search");
			 }
			 Thread.sleep(1000);
			 General.action().logoutMethod();
			 Thread.sleep(1000);
			 
			// trans login
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(20000);
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName2);
		    Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			translate_onGoing_submission(SubmissionName2,targetlanguage_1,true,true);     
			Thread.sleep(1000);
			    
			Thread.sleep(1000);
			General.action().logoutMethod();
			Thread.sleep(1000);
			
			//Review LogIn 
		     General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
		     Thread.sleep(20000);
	         Reviewer.action().Navigate(menu_to_claim);
	         Thread.sleep(1000);
	         Reviewer.action().review_ToClaim(SubmissionName2);
	         Thread.sleep(1000);
	         Reviewer.action().Navigate(menu_ongoing);
	         Thread.sleep(1000);
	         Reviewer_onGoing_submission_NoPermission(SubmissionName2, targetlanguage_1, true, false);
			 
				
				        
				        
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
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
		 Thread.sleep(3000);
		 
			
		 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		 Thread.sleep(1000);
		 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		 Thread.sleep(1000);
		 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		 Thread.sleep(1000);
		 General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),text_firstSegment);
		 Thread.sleep(5000);
		 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		 Thread.sleep(1000);
		 General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab);
		 Thread.sleep(1000);
		 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput);
		 Thread.sleep(1000);
		 General.action().type(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput,comment_firstSegment);
		 Thread.sleep(5000);
		 General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab_commentDialogBox_CommentButton);
		 Thread.sleep(1000);
		 
		 
		 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		 Thread.sleep(1000);
		 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		 Thread.sleep(1000);
		 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		 Thread.sleep(1000);
		 General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(2),text_fourthSegment);
		 Thread.sleep(5000);
		 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		 Thread.sleep(1000);
		 Thread.sleep(5000);
		 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		 Thread.sleep(1000);
		 General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab);
		 Thread.sleep(1000);
		 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput);
		 Thread.sleep(1000);
		 General.action().type(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput,longComment);
		 Thread.sleep(5000);
		 General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab_commentDialogBox_CommentButton);
		 Thread.sleep(1000);
		 //Not possible to verify
		/* //TODO this is to verify ... after entering long text for comment
		 assertion = General.driver.findElement(TranslatorLocators.Locator().translator_commentTab_longText).getCssValue("display").equalsIgnoreCase("flex");
			if (assertion == false) {
				report("f", "Assertion failed while verifying PastDuedate_Input_color");
			}
		 */
		 
		 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
		 Thread.sleep(1000);
		 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
		 Thread.sleep(1000);
		 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
		 Thread.sleep(1000);
		 General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(4),text_fourthSegment);
		 Thread.sleep(5000);
		 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
		 Thread.sleep(1000);
		 Thread.sleep(5000);
		 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
		 Thread.sleep(1000);
		 General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab);
		 Thread.sleep(1000);
		 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput);
		 Thread.sleep(1000);
		 General.action().type(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput,comment_fourthSegment);
		 Thread.sleep(5000);
		 General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab_commentDialogBox_CommentButton);
		 Thread.sleep(1000);
		 
		 assertion =General.driver.findElement(TranslatorLocators.Locator().translator_jobHeaderOptions(1)).getText().contains("Quality Check");
		 if (assertion == false) {
		 report("f", "Assertion failed while verifying Quality Check tab");
		 }
			
		 assertion =General.driver.findElement(TranslatorLocators.Locator().translator_jobHeaderOptions(2)).getText().contains("Comments");
		 if (assertion == false) {
		 report("f", "Assertion failed while verifying Comments tab");
		 }
		 
		 assertion =General.driver.findElement(TranslatorLocators.Locator().translator_jobHeaderOptions(3)).getText().contains("Task info");
		 if (assertion == false) {
		 report("f", "Assertion failed while verifying Task info tab");
		 }
		 Thread.sleep(2000);
		 General.action().Click(TranslatorLocators.Locator().translator_commentTab);
		 Thread.sleep(1000);
		
		 assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_commentTab_comments(1,comment_firstSegment), 5);
		 if (assertion == false) {
				report("f", "Assertion failed while comment in commment tab with id");
		 }
			
			
		 assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_commentTab_comments(4,comment_fourthSegment), 5);
		 if (assertion == false) {
		 report("f", "Assertion failed while verifying comment in commment tab with id");
		 }
		 
		 General.action().Click(TranslatorLocators.Locator().translator_commentTab_comments(1,comment_firstSegment));
		 Thread.sleep(1000);
		 
		 System.out.println(General.driver.findElement(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput).getAttribute("value"));
		 Thread.sleep(1000);
		 
		 //Verify history from trans
		 assertion =General.driver.findElement(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput).getAttribute("value").contains(comment_firstSegment);
		 if (assertion == false) {
		 report("f", "Assertion failed while verifying SubmissionName  after Search");
		 }
		
		 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput);
		 Thread.sleep(1000);
		 General.action().type(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput,"Edited Comment for fourth segment");
		 Thread.sleep(3000);
		 
		 General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab_commentDialogBox_CommentButton);
		 Thread.sleep(5000);
		 
		 //Verify edited comment is displayed
		 assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_commentTab_comments(1,"Edited Comment for fourth segment"), 5);
		 if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
		 }
		 
		 //completed task
		 if(complete){
		    	General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_Complete_Button);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_Complete_Button);
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_CompleteDailoge_Button);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_CompleteDailoge_Button);
				Thread.sleep(3000);
				if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_BackToSubmissionList_Menubutton_forCompleted);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().review_BackToSubmissionList_Menubutton_forCompleted);
			    }
			}
			
		    
		    if(back){
		    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
					General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_BackToSubmissionList_Button);
					Thread.sleep(1000);
					General.action().Click(ReviewerLocators.Locator().review_BackToSubmissionList_Button);
			    	}
		    }
		    
			 System.out.println("EOM trans_Ongoing  method()");
			
		 
			    
		System.out.println("EOM trans_Ongoing  method()");
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
			Thread.sleep(1000);
			
			
			
			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			 Thread.sleep(1000);
			 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			 Thread.sleep(1000);
			 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			 Thread.sleep(1000);
			 General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),text_firstSegment);
			 Thread.sleep(5000);
			 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			 Thread.sleep(1000);
			 General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
			 Thread.sleep(4000);
			 
			 //Verify comment history in review
			 assertion =General.driver.findElement(ReviewerLocators.Locator().review_segmentHistoryTab_comment_history).getText().contains("Edited Comment for fourth segment");
			 if (assertion == false) {
			 report("f", "Assertion failed while verifying SubmissionName  after Search");
			 }
			 General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CancelButton);
			 Thread.sleep(1000);
			
			 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
			 Thread.sleep(1000);
			 General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
			 Thread.sleep(1000); 
			 
			 //Verify comment history for 4th segment
			 assertion =General.driver.findElement(ReviewerLocators.Locator().review_segmentHistoryTab_comment_history).getText().contains(comment_fourthSegment);
			 if (assertion == false) {
			 report("f", "Assertion failed while verifying SubmissionName  after Search");
			 }
			 General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CancelButton);
			 Thread.sleep(1000);
			 
			 
			 if(complete){
			 General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_Complete_Button);
			 Thread.sleep(1000);
			 General.action().Click(ReviewerLocators.Locator().reviewer_Complete_Button);
			 Thread.sleep(1000);
			 General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_CompleteDailoge_Button);
			 Thread.sleep(1000);
			 General.action().Click(ReviewerLocators.Locator().reviewer_CompleteDailoge_Button);
			 Thread.sleep(3000);
			 if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
			 General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_BackToSubmissionList_Menubutton_forCompleted);
			 Thread.sleep(1000);
			 General.action().Click(ReviewerLocators.Locator().review_BackToSubmissionList_Menubutton_forCompleted);
			 }
				}
				
			    
			    if(back){
			    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
						General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_BackToSubmissionList_Button);
						Thread.sleep(1000);
						General.action().Click(ReviewerLocators.Locator().review_BackToSubmissionList_Button);
				    	}
			    }
			    
		
			    
				 System.out.println("EOM trans_Ongoing  method()");
	}
	
	public void Reviewer_onGoing_submission_NoPermission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
		
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
			Thread.sleep(6000);
			
			
			
			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			 Thread.sleep(1000);
			 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			 Thread.sleep(1000);
			 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			 Thread.sleep(1000);
			 General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),text_firstSegment);
			 Thread.sleep(5000);
			 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			 Thread.sleep(1000);
			 General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
			 Thread.sleep(1000);
			 
			 
			 //Verify comment history is not display (when permission to view history is disabled)
			 assertion = Verify.action().verifyElementNotPresent(ReviewerLocators.Locator().review_segmentHistoryTab_comment_history, 5);
			 if (assertion == false) {
					report("f", "Assertion failed while verifying comment history is not display ");
			 }
			 
			 General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CancelButton);
			 Thread.sleep(1000);
			
			 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
			 Thread.sleep(1000);
			 General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
			 Thread.sleep(1000); 
			 //Verify comment history is not display (when permission to view history is disabled)
			 assertion = Verify.action().verifyElementNotPresent(ReviewerLocators.Locator().review_segmentHistoryTab_comment_history, 5);
			 if (assertion == false) {
					report("f", "Assertion failed while verifying comment history is not display");
			 }
			     
				 System.out.println("EOM trans_Ongoing  method()");
	}
	
			
			
	public void assertion() throws Exception {
		 //Verify comment history is not display (when permission to view history is disabled)
		 assertion = Verify.action().verifyElementNotPresent(ReviewerLocators.Locator().review_segmentHistoryTab_comment_history, 5);
		 if (assertion == false) {
				report("f", "Assertion failed while verifying comment history is not display");
		 }else {
		  report("p", "All Assertion Passed.");
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
