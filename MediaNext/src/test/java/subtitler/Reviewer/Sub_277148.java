package subtitler.Reviewer;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

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

public class Sub_277148 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_277148"+CommonElements.BROWSER+"M1";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String folder_277148 = "277148\\srt";
	String charLimitation_ruleViolation;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_277148");
		dataSet.put("TL_test_case_description","Sub_277148 :Quality Checks: Check the list of rules to validate that the merge of Target+ Modified Target fits with rules.");
		dataSet.put("TL_internal_testCase_ID", "277148");
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
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", true);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			//PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ folder_277148);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			General.action().logoutMethod();

			// tran login
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(20000);
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			translate_onGoing_submission(SubmissionName, targetlanguage_1,true, false);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_completed);
			Thread.sleep(1000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translatorSearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			General.action().logoutMethod();

			// review login
			General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
			Thread.sleep(20000);
			Reviewer.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Reviewer.action().review_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			review_onGoing_qualityCheck(SubmissionName, targetlanguage_1);
			Thread.sleep(2000);

			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}
	
	public void translate_onGoing_submission(String SubmissionName,String target, boolean complete, boolean back) throws Exception {

		System.out.println("INSIDE trans_Ongoing  method()");

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(TranslatorLocators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);

		Thread.sleep(2000);
		
		    General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1), "");
			Thread.sleep(2000);
			
			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			 Thread.sleep(1000);
			 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			 Thread.sleep(1000);
			 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		     Thread.sleep(1000);
			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			 Thread.sleep(1000);
			 General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(2), "Test rule violation");
			 Thread.sleep(2000);
			 
			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
			 Thread.sleep(1000);
			 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
			 Thread.sleep(1000);
			 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
			 Thread.sleep(1000);
			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
			 Thread.sleep(1000);
			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
			 Thread.sleep(1000);
			 General.action().Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
			 Thread.sleep(1000);

			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
			 Thread.sleep(1000);
			 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
			 Thread.sleep(1000);
			 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
			 Thread.sleep(1000);
			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
			 Thread.sleep(1000);
			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
			 Thread.sleep(1000);
			 General.action().Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
			 Thread.sleep(1000);
			 
			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(5));
			 Thread.sleep(1000);
			 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(5));
			 Thread.sleep(1000);
			 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(5));
			 Thread.sleep(1000);
			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(5));
			 Thread.sleep(1000);
			 General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(5), "Test rule violation for RS");
			 Thread.sleep(2000);
			 
			 
			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(6));
			 Thread.sleep(1000);
			 General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(6));
			 Thread.sleep(1000);
			 General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(6));
			 Thread.sleep(1000);
			 General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(6));
			 Thread.sleep(1000);
			 General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(6), "Pro Tip: , \n" + "Turn off the, \n" + "camera flash!");
			 Thread.sleep(2000);
			 
		if (complete) {
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
			Thread.sleep(3000);
			if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
		    	}
		}

		if (back) {
			System.out.println("IN BACK-----");
			if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
		    	}
		}

		System.out.println("EOM trans_Ongoing  method()");
	}
	
	
	

	public void review_onGoing_qualityCheck(String SubmissionName, String target)throws Exception {

		System.out.println("INSIDE review_onGoing_qualityCheck  method()");

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
			General.action().Click(ReviewerLocators.Locator().reviewer_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
	
		Thread.sleep(2000);
	  // check rule violation for:- 5-i.an empty Target (Empty Target - Should be displayed as Empty subtitles for that Target/Modified Target segment.)
		 General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		 Thread.sleep(1000);
		 General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		 Thread.sleep(1000);
		 General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		 Thread.sleep(1000);
		 General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)),"");
		 Thread.sleep(2000);
		 
		 General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		 General.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		 Thread.sleep(1000);
		 
		 
		String Empty_ruleViolation = General.driver.findElement(By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'1')]/following-sibling::span")).getText();
		System.out.println("Rule violation message for Empty subtitles for that Target/Modified Target segment:-" +Empty_ruleViolation);
		
		 Thread.sleep(1000);
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().qualityCheck_EmptySubtitle_message, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying qualityCheck_EmptySubtitle_message  after Search");
		}
		
		 
		 
		
		 Thread.sleep(2000);
		// check rule violation for:- 5-ii.an fulfilled Target with an empty Modified Target  ( An fulfilled Target with an empty Modified Target - Should not raise a rule violation)
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(1000);
	    General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2)),"");
		Thread.sleep(2000);
		
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		General.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		Thread.sleep(1000);
				 
	    assertion = Verify.action().verifyElementNotPresent(ReviewerLocators.Locator().qualityCheck_EmptyMT_message, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying there is no rule violation for an fulfilled Target with an empty Modified Target after Search");
		}
		System.out.println("THERE IS NO RULE VIOLATION FOR EMPTY MODIFIED TAERGET");
		
		 Thread.sleep(2000);
		// check rule violation for:- 5-iv.a Target = Source and an empty Modified Target(A Target = Source and an empty Modified Target - Should raise a rule violation)
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3)),"");
		Thread.sleep(2000);
	
		 General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		 General.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		 Thread.sleep(1000);
						 
		String TCopytosource_ruleViolation = General.driver.findElement(By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'3')]/following-sibling::span")).getText();
		System.out.println("Rule violation message for a Target = Source and an empty Modified Target:-" +TCopytosource_ruleViolation);
		 Thread.sleep(1000);
		 
		 assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().qualityCheck_TCopytosource_message, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying qualityCheck_TCopytosource_message  after Search");
			}
		 Thread.sleep(2000);	
		// check rule violation for:- 5-v.a Target = Source and a Modified Target = Source ( A Target = Source and a Modified Target = Source - Should raise a rule violation)
		// check rule violation for:- 7.Check the chars Length behavior(2. Equal to the limit should be accepted.3. In the quality check, it should not trigger a Character Limitation rule violation)
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
		Thread.sleep(1000);
		
		 General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		 General.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
		 Thread.sleep(1000);
								 
		String MTCopytosource_ruleViolation = General.driver.findElement(By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'4')]/following-sibling::span")).getText();
		System.out.println("Rule violation message for a Target = Source and a Modified Target = Source:-" +MTCopytosource_ruleViolation);
        Thread.sleep(1000);
		 
		 assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().qualityCheck_MTCopytosource_message, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying qualityCheck_MTCopytosource_message  after Search");
			}
		 Thread.sleep(1000);
		 
		String Line_count_color = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea_line(4)).getCssValue("color");
		 System.out.println("Character limitation violation Color:-"+ Line_count_color);

		 boolean color = Line_count_color.equalsIgnoreCase("rgba(255, 255, 255, 1)");
		 System.out.println("Character limitation violation Color is WHITE:- "+ color);
		 Thread.sleep(2000);
		 
		// check rule violation for:- 7.Check the chars Length behavior
			// check rule violation for:- 5-iii)a correct reading speed in Target and a wrong reading speed in Modified Target (A correct reading speed in Target and a wrong reading speed in Modified Target - Should raise a rule violation)
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(5));
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(5));
			Thread.sleep(1000);
			General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(5));
			Thread.sleep(1000);
			General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(5)),"Jellyfish at the Monterey Aquarium extra for high reading Aquarium extra for high reading Jellyfish at the Monterey Aquarium extra for high reading Aquarium extra for high");
			Thread.sleep(2000);
			
			
             String Line_count_color_forextrachar = General.driver.findElement(By.xpath("//span[contains(@class,'counter exceed-limit ng-star-inserted')]")).getCssValue("color");
			System.out.println("Character limitation violation Color:-"+ Line_count_color_forextrachar);
			 Thread.sleep(3000);
			boolean color_extrachars = Line_count_color_forextrachar.equalsIgnoreCase("rgba(255, 79, 100, 1)");
			System.out.println("Character limitation violation Color is RED:- "+ color_extrachars);
			
			String RS_color_forincorrectRS = General.driver.findElement(By.xpath("//shared-reading-speed//div[contains(@class,'rs-above')]")).getCssValue("color");
			System.out.println("Character limitation violation Color:-"+ RS_color_forincorrectRS);
			 Thread.sleep(3000);
			boolean color_RS = RS_color_forincorrectRS.equalsIgnoreCase("rgba(255, 79, 100, 1)");
			System.out.println("Reading Speed limitation violation Color is RED:- "+ color_RS);
			
			
			 General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
			 General.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
			 Thread.sleep(1000);
			 
			 assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().qualityCheck_wrongreadingSpeed_message, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying qualityCheck_wrongreadingSpeed_message  after Search");
				}
		      Thread.sleep(1000);

			
			 General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
			 General.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
			 Thread.sleep(1000);
			 
			charLimitation_ruleViolation = General.driver.findElement(By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'5')]/following-sibling::span")).getText();
			System.out.println("Rule violation message for char limitation rule violation:-" +charLimitation_ruleViolation);
							
			Thread.sleep(1000);
			 
			 assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().qualityCheck_charLimitation_message, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying qualityCheck_charLimitation_message  after Search");
				}
				Thread.sleep(1000);
				
				 //6 :-check To get the "report" of quality check
				 Thread.sleep(2000);
				 General.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
				 Thread.sleep(1000);
				 General.action().waitforelemenetpresent(ReviewerLocators.Locator().qualitycheck_number_of_issues);
				 Thread.sleep(1000);

				 String NumberOfIssues1 = General.driver.findElement(ReviewerLocators.Locator().qualitycheck_number_of_issues).getText();
				 System.out.println("NUMBER OF RULE VIOLATION IS:-" + NumberOfIssues1);
				 Thread.sleep(1000);
				 String ruleViolation_messages1 = General.driver.findElement(ReviewerLocators.Locator().qualitycheck_rule_violation_mesaages).getText();
				 System.out.println("RULE VIOLATION MESSAGES:-"+ ruleViolation_messages1);
				 Thread.sleep(2000);
		 
		 
		// check rule violation for:- 5-vi. 3 lines in Target and 2 lines in Modified Target ( 3 lines in Target and 2 lines in Modified Target - Will not raise a rule violation)
		 General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(6));
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(6));
			Thread.sleep(1000);
			General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(6));
			Thread.sleep(1000);
		    General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(6)),"Pro Tip: , \n" + "Turn off the,");
			Thread.sleep(2000);
			
			General.action().type_withKEYS(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(6), Keys.BACK_SPACE, false);
		
			
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
			General.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
			Thread.sleep(1000);
					 
		    assertion = Verify.action().verifyElementNotPresent(ReviewerLocators.Locator().qualityCheck_MultiLine_message, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying there is no rule violation for 3 lines in Target and 2 lines in Modified Target  after Search");
			}
			System.out.println("THERE IS NO RULE VIOLATION FOR 3 LINE IN TARGET AND 2 LINE IN MODIFIED TAERGET");
			
			 Thread.sleep(2000);
		 System.out.println("EOM review_onGoing_qualityCheck  method()");
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementNotPresent(ReviewerLocators.Locator().qualityCheck_MultiLine_message, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying there is no rule violation for 3 lines in Target and 2 lines in Modified Target  after Search");
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



