package subtitler.Reviewer;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import locators.RoleLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;
import modules.admin;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary:This testcase verifies the versioning support of Subtitle.
 *
 */ 
public class Sub_1345708 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1345708"+CommonElements.BROWSER+"F2";
	String WorkflowName = "MultiReview_Step_Workflow";
	String OrganizationName = "Subtitle_Common_orgs";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String ReviewGroupName1 = "Common_group_review1";
	String ReviewGroupName2 = "Common_group_review2";
	String ReviewGroupName3 = "Common_group_review3";
	String targetlanguage = "German (Germany)";
	String menu_submission = "Submissions";
	String folder_1345708 = "1345708";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String segmentText[]= {"Jellyfish at the Monterey Aquarium.","Dude get out of the way!","Shaky Hands","Ah yes, this is better","Thanks for watching and I hope you'll have fun with","All Rights Reserved by QA Transperfect India."};
    String RoleName_trans="Role_Translator";
	String RoleName_review="Role_Review";
	String Roles_permission_View[] = {"View History Of Segments"};
    Boolean assertion = true;
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1345708");
		dataSet.put("TL_test_case_description", "SUB-1345708:Support the versioning of subtitles.");
		dataSet.put("TL_internal_testCase_ID", "1345708");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			 // Checked role View History Of Segments if unchecked.
			 General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			 Thread.sleep(2000);
	
			 admin.action().Navigate("Roles");
			 Thread.sleep(1000);
			 admin.action().SearchRole(RoleName_trans);
			 Thread.sleep(1000);
			 admin.action().EditRole_open(RoleName_trans);
			 
			 if(Verify.action().verifyElementPresent(RoleLocators.Locator().Manage_role_checked(Roles_permission_View[0]),5)) {
			 Thread.sleep(2000);
			 System.out.println("View History Of Segments is checked");
			 }else {
			 System.out.println("View History Of Segments is UnChecked");
			 Thread.sleep(2000);
			 admin.action().Create_AND_EditRole_Permission_View(Roles_permission_View,true);
			 Thread.sleep(2000);
			 }
			 admin.action().Create_And_SaveRole_CreateStep();
			 
			 admin.action().Navigate("Roles");
			 Thread.sleep(1000);
			 admin.action().SearchRole(RoleName_review);
			 Thread.sleep(1000);
			 admin.action().EditRole_open(RoleName_review);
			 if(Verify.action().verifyElementPresent(RoleLocators.Locator().Manage_role_checked(Roles_permission_View[0]),5)) {
			 Thread.sleep(2000);
			 System.out.println("View History Of Segments is checked");
			 }else {
			 System.out.println("View History Of Segments is UnChecked");
			 Thread.sleep(2000);
			 admin.action().Create_AND_EditRole_Permission_View(Roles_permission_View,true);
			 Thread.sleep(2000);
			 }
			 admin.action().Create_And_SaveRole_CreateStep();
			 
			 Thread.sleep(2000);
			 General.action().logoutMethod();
			
			
			//Create Submission
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_submission);
			Thread.sleep(1000);
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow_MultiReview_StepWorkflow(OrganizationName, WorkflowName,"trans",TranslatorGroupName,true,"review", ReviewGroupName, true,"review1", ReviewGroupName1, true,"review2", ReviewGroupName2, true,"review3", ReviewGroupName3, true);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+folder_1345708);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
		
			//SEARCH SUBMISSION AND CHECK
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			General.action().logoutMethod();

			// tran login
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Thread.sleep(1000);
			translate_onGoing_submission(SubmissionName, targetlanguage,true, false);
			Thread.sleep(1000);
			
			
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
			reviewer_onGoing_submission(SubmissionName, targetlanguage,true ,false);
			Thread.sleep(2000);
			
			
			Thread.sleep(2000);
		    General.action().logoutMethod();
		    
		    // Review LogIn 
			General.action().login(CommonElements.action().Reviwer_username_QMReview,"Password1!");
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Reviewer.action().review_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			reviewer1_onGoing_submission(SubmissionName, targetlanguage,true,false);
			Thread.sleep(2000);
			
			
			Thread.sleep(2000);
		    General.action().logoutMethod();
		    
		    // Review LogIn 
			General.action().login(CommonElements.action().Reviwer_username_correction, CommonElements.action().password);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Reviewer.action().review_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			reviewer2_onGoing_submission(SubmissionName, targetlanguage,true,false);
			Thread.sleep(2000);
			
			Thread.sleep(2000);
		    General.action().logoutMethod();
		    	    
		    // Review LogIn 
			General.action().login(CommonElements.action().Reviwer_username_finalReview, CommonElements.action().password);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Reviewer.action().review_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			reviewer3_onGoing_submission(SubmissionName, targetlanguage,false,false);
			Thread.sleep(2000);
			
			
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void translate_onGoing_submission(String SubmissionName,String target,boolean complete, boolean back) throws Exception {

		System.out.println("INSIDE Trans_Ongoing_Import_translation_InvalidFile  method()");

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
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
		Thread.sleep(15000);
		

		enterSegments_text(1,segmentText[0]+" Target");
		enterSegments_text(2,segmentText[1]+" Target");
		enterSegments_text(3,segmentText[2]+" Target");
		enterSegments_text(4,segmentText[3]+" Target");
		enterSegments_text(5,segmentText[4]+" Target");
		enterSegments_text(6,segmentText[5]+" Target");
	    
		
		
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		Thread.sleep(1000);
		
		General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab);
		Thread.sleep(3000);
		
		DateFormat dateformate = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String date1 = dateformate.format(date);
		System.out.println("Calendar current date from system:-" +date1);
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_segmentHistory_User, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_segmentHistory_Step, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_segmentHistory_translation, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_segmentHistory_firstRow("Source"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Segment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_segmentHistory_firstRow("Subtitler_PM Subtitler_PM"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Segment History user Name");
		}
		
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_segmentHistory_firstRow(date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation");
		}
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_segmentHistory_firstRow("Jellyfish at the Monterey Aquarium."), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History source segments ");
		}
		
		General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab_commentDialogBox_CancelButton);
		Thread.sleep(2000);
		
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab);
		Thread.sleep(1000);
		
		
		assertion = General.driver.findElement(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput).getText().isEmpty();
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History source segments ");
		}
		

		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput,"Comment for 1st Target segment");
		Thread.sleep(5000);
		General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(2000);
		
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab);
		Thread.sleep(1000);
		
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput,"Comment for 3rd Target segment");
		Thread.sleep(5000);
		General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(2000);
		
		
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
		Thread.sleep(1000);
		Thread.sleep(1000);
		General.action().scrollDown(3);
		Thread.sleep(1000);
		General.action().scrollDown_end(6);
		Thread.sleep(1000);
		
		
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1599));
		Thread.sleep(1000);
		
		enterSegments_text(1599,"Vraiment ?"+" Target");
		
		General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab);
		Thread.sleep(1000);
		
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput,"Comment for 1599th Target segment");
		Thread.sleep(5000);
		General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(2000);
		
		
		
		
		
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
				    
			System.out.println("EOM trans_Ongoing  method()");
		
		
		
	}
	
	
	public void reviewer_onGoing_submission(String SubmissionName, String target,boolean complete, boolean back)throws Exception {

		System.out.println("INSIDE reviewer_onGoing_submission  method()");

		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().review_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(ReviewerLocators.Locator().reviewer_selectSubmission_checkbox(SubmissionName,target));
		}
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
		Thread.sleep(5000);
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewe_source_readyOnly(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewe_source_readyOnly(3), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewe_target_readyOnly(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewe_target_readyOnly(3), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)).getText().isEmpty();
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3)).getText().isEmpty();
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		Thread.sleep(15000);
		enterSegments_text(1,segmentText[0]+" Modified Target");
		enterSegments_text(2,segmentText[1]+" Modified Target");
		enterSegments_text(3,segmentText[2]+" Modified Target");
		enterSegments_text(4,segmentText[3]+" Modified Target");
		enterSegments_text(5,segmentText[4]+" Modified Target");
		enterSegments_text(6,segmentText[5]+" Modified Target");
		
		
		Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(2000);
	
		
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
		Thread.sleep(1000);
		
		DateFormat dateformate = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String date1 = dateformate.format(date);
		System.out.println("Calendar current date from system:-" +date1);
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_User, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_Step, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_translation, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"Source"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Segment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"Subtitler_PM Subtitler_PM"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Segment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Segment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,segmentText[0]), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation ");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"trans"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Segment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"Subtitler_Translator_1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,segmentText[0]+" Target"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_commentHistory(1,"Comment for 1st Target segment"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History comment ");
		}
		
		
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput,"Comment for 1st Modified Target segment");
		Thread.sleep(5000);
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(2000);
		
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
		Thread.sleep(1000);
		
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput,"Comment for 3rd Modified Target segment");
		Thread.sleep(5000);
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(2000);
		
		
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		
		if(CommonElements.BROWSER.contains("FIREFOX")) {
			System.out.println("*****FIREFOX*******");
			Robot robot = new Robot();  
			for(int i=1;i<=3;i++) {
            robot.keyPress(KeyEvent.VK_PAGE_DOWN);
            Thread.sleep(1000);
            robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
            
			}
			
		   for(int i=1;i<=2;i++) {
	       robot.keyPress(KeyEvent.VK_END); 
	       Thread.sleep(1000);
	       robot.keyRelease(KeyEvent.VK_END);
		   }
		}
		
		else {
		Thread.sleep(1000);
		General.action().scrollDown(3);
		Thread.sleep(1000);
		General.action().scrollDown_end(6);
		Thread.sleep(1000);
		}
		
		
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea_x(1599));
		Thread.sleep(1000);
		
		enterSegments_text(1599,"Vraiment ?"+" Modified Target");
		
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
		Thread.sleep(1000);
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_User, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_Step, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_translation, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"Source"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"Subtitler_PM Subtitler_PM"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"Vraiment ?"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History source segments ");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"trans"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"Subtitler_Translator_1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"Vraiment ?"+" Target"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation ");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_commentHistory(1,"Comment for 1599th Target segment"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History commment ");
		}
		
		
		
		
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput,"Comment for 1599th Modified Target segment");
		Thread.sleep(5000);
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(2000);
		
		
		if(complete){
			   General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_Complete_Button);
			   Thread.sleep(1000);
			   General.action().Click(ReviewerLocators.Locator().review_Complete_Button);
			   Thread.sleep(1000);
			   General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_CompleteDailoge_Button);
			   Thread.sleep(1000);
			   General.action().Click(ReviewerLocators.Locator().review_CompleteDailoge_Button);
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
	
	
	public void reviewer1_onGoing_submission(String SubmissionName, String target,boolean complete, boolean back)throws Exception {

		System.out.println("INSIDE reviewer_onGoing_submission  method()");

		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().review_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(ReviewerLocators.Locator().reviewer_selectSubmission_checkbox(SubmissionName,target));
		}
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
		Thread.sleep(15000);
		
		enterSegments_text(1,segmentText[0]+" Modified Target QM review");
		enterSegments_text(2,segmentText[1]+" Modified Target QM review");
		enterSegments_text(3,segmentText[2]+" Modified Target QM review");
		enterSegments_text(4,segmentText[3]+" Modified Target QM review");
		enterSegments_text(5,segmentText[4]+" Modified Target QM review");
		enterSegments_text(6,segmentText[5]+" Modified Target QM review");
		
		
		Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().multiReviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(2000);
	
		
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
		Thread.sleep(3000);
		
		DateFormat dateformate = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String date1 = dateformate.format(date);
		System.out.println("Calendar current date from system:-" +date1);
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_User, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_Step, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_translation, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,"Source"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,"Subtitler_PM Subtitler_PM"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,segmentText[0]), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"trans"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"Subtitler_Translator_1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,segmentText[0]+" Target"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_commentHistory(2,"Comment for 1st Target segment"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History comment ");
		}
		
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"review"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"Subtitler_Reviewer_1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,segmentText[0]+" Modified Target"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation ");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_commentHistory(1,"Comment for 1st Modified Target segment"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History comment ");
		}
		
		

		General.action().ClearInputvalues(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput,"Comment for 1st QM Modified Target segment");
		Thread.sleep(5000);
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(2000);
		
		General.action().Click(ReviewerLocators.Locator().multiReviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
		Thread.sleep(1000);
		
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput,"Comment for 3rd Modified Target segment");
		Thread.sleep(5000);
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(2000);
		
		Thread.sleep(4000);
		General.action().Click(ReviewerLocators.Locator().multiReviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		Thread.sleep(1000);
		General.action().scrollDown(3);
		Thread.sleep(1000);
		General.action().scrollDown_end(6);
		Thread.sleep(1000);
		
		
		General.action().Click(ReviewerLocators.Locator().multiReviewer_ModifiedTargetSegement_textarea(1599));
		Thread.sleep(1000);
		
		enterSegments_text(1599,"Vraiment ?"+" Modified Target");
		Thread.sleep(1000);
		
		
		Thread.sleep(5000);
		General.action().Click(ReviewerLocators.Locator().multiReviewer_ModifiedTargetSegement_textarea(1599));
		Thread.sleep(1000);
		
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
		Thread.sleep(4000);
		
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CancelButton);
		Thread.sleep(4000);
		
		Thread.sleep(5000);
		General.action().Click(ReviewerLocators.Locator().multiReviewer_ModifiedTargetSegement_textarea(1599));
		Thread.sleep(1000);
		
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
		Thread.sleep(4000);
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_User, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_Step, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_translation, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,"Source"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,"Subtitler_PM Subtitler_PM"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,"Vraiment ?"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History traslation ");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"trans"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"Subtitler_Translator_1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"Vraiment ?"+" Target"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation ");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_commentHistory(2,"Comment for 1599th Target segment"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History comment");
		}
		
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"review"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"Subtitler_Reviewer_1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"Vraiment ? Modified Target"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_commentHistory(1,"Comment for 1599th Modified Target segment"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History comment ");
		}
		
		
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput,"Comment for 1599th Modified Target segment");
		Thread.sleep(5000);
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(2000);
		
		
		if(complete){
			   General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_Complete_Button);
			   Thread.sleep(1000);
			   General.action().Click(ReviewerLocators.Locator().review_Complete_Button);
			   Thread.sleep(1000);
			   General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_CompleteDailoge_Button);
			   Thread.sleep(1000);
			   General.action().Click(ReviewerLocators.Locator().review_CompleteDailoge_Button);
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
				    
			System.out.println("EOM review_Ongoing  method()");
	}
	
	
	public void reviewer2_onGoing_submission(String SubmissionName, String target,boolean complete, boolean back)throws Exception {

		System.out.println("INSIDE reviewer_onGoing_submission  method()");

		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().review_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(ReviewerLocators.Locator().reviewer_selectSubmission_checkbox(SubmissionName,target));
		}
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
		Thread.sleep(15000);
		
		enterSegments_text(1,segmentText[0]+" Modified Target Correction");
		enterSegments_text(2,segmentText[1]+" Modified Target Correction");
		enterSegments_text(3,segmentText[2]+" Modified Target Correction");
		enterSegments_text(4,segmentText[3]+" Modified Target Correction");
		enterSegments_text(5,segmentText[4]+" Modified Target Correction");
		enterSegments_text(6,segmentText[5]+" Modified Target Correction");
		
		
		Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().multiReviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(2000);
	
		
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
		Thread.sleep(4000);
		
		DateFormat dateformate = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String date1 = dateformate.format(date);
		System.out.println("Calendar current date from system:-" +date1);
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_User, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_Step, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_translation, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(4,"Source"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(4,"Subtitler_PM Subtitler_PM"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(4,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(4,segmentText[0]), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History source segments ");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,"trans"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,"Subtitler_Translator_1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,segmentText[0]+" Target"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History stranslation");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_commentHistory(3,"Comment for 1st Target segment"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History comment");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"review"), 5);
//		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"review2"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"Subtitler_Reviewer_1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,segmentText[0]+" Modified Target"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History stranslation");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_commentHistory(2,"Comment for 1st Modified Target segment"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History comment ");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"review1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"Subtitler_Reviewer_QMReview"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,segmentText[0]+" Modified Target QM review"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation ");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_commentHistory(1,"Comment for 1st QM Modified Target segment"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History comments ");
		}
		
         //Step 10 add spece
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput,"");
		Thread.sleep(6000);
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(2000);
		
		General.action().Click(ReviewerLocators.Locator().multiReviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
		Thread.sleep(1000);
		
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput,"Comment for 3rd Correction Modified Target segment");
		Thread.sleep(5000);
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(2000);
		
		
		if(complete){
			   General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_Complete_Button);
			   Thread.sleep(1000);
			   General.action().Click(ReviewerLocators.Locator().review_Complete_Button);
			   Thread.sleep(1000);
			   General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_CompleteDailoge_Button);
			   Thread.sleep(1000);
			   General.action().Click(ReviewerLocators.Locator().review_CompleteDailoge_Button);
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
				    
			System.out.println("EOM review_Ongoing  method()");
		
	}
	
	public void reviewer3_onGoing_submission(String SubmissionName, String target,boolean complete, boolean back)throws Exception {

		System.out.println("INSIDE reviewer_onGoing_submission  method()");

		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().review_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(ReviewerLocators.Locator().reviewer_selectSubmission_checkbox(SubmissionName,target));
		}
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
		Thread.sleep(15000);
		enterSegments_text(1,segmentText[0]+" Modified Target Final");
		enterSegments_text(2,segmentText[1]+" Modified Target Final");
		enterSegments_text(3,segmentText[2]+" Modified Target Final");
		enterSegments_text(4,segmentText[3]+" Modified Target Final");
		enterSegments_text(5,segmentText[4]+" Modified Target Final");
		enterSegments_text(6,segmentText[5]+" Modified Target Final");
		
		
		Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().multiReviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(2000);
	
		
		General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
		Thread.sleep(1000);
		
		DateFormat dateformate = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String date1 = dateformate.format(date);
		System.out.println("Calendar current date from system:-" +date1);
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_User, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_Step, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_translation, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History Header");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(5,"Source"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(5,"Subtitler_PM Subtitler_PM"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(5,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(5,segmentText[0]), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(4,"trans"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(4,"Subtitler_Translator_1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(4,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(4,segmentText[0]+" Target"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_commentHistory(4,"Comment for 1st Target segment"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History comment ");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,"review"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,"Subtitler_Reviewer_1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(3,segmentText[0]+" Modified Target"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_commentHistory(3,"Comment for 1st Modified Target segment"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History comment");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"review1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,"Subtitler_Reviewer_QMReview"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(2,segmentText[0]+" Modified Target QM review"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation ");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_commentHistory(2,"Comment for 1st QM Modified Target segment"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History comment");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"review2"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History step");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,"Subtitler_Reviewer_Correction"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History user");
		}
		
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,date1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History date");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,segmentText[0]+" Modified Target Correction"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation");
		}
		
		assertion = !Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_commentHistory(1,"Comment:"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History comment");
		}
		
		
		
	
				    
	  System.out.println("EOM review_Ongoing  method()");
	}
	
	
	public void enterSegments_text(int index,String segmentText) throws InterruptedException {
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(index));
		Thread.sleep(3000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(index));
		Thread.sleep(3000);
		General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(index),segmentText);
		Thread.sleep(5000);
	}
	
	
	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_segmentHistory_historyRows(1,segmentText[0]+" Modified Target Correction"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Coment History translation");
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
