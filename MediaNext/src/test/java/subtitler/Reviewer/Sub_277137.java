package subtitler.Reviewer;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_277137 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String SubmissionName = "SUB_277137"+CommonElements.BROWSER+"P2";
    String targetlanguage_1 = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String Modified_Textarea_Input;
	String Target_Textarea_Input;
	
	File filepath1;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_277137");
		dataSet.put("TL_test_case_description", "SUB-277137:Check tool tips present for Copy all source, cancel, Undo buttons present in Translation/Review window");
		dataSet.put("TL_internal_testCase_ID", "277137");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)throws Exception {
		try {
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
			System.out.println("filePath------>" + filePath);
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
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Reviewer.action().review_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			reviewer_onGoing_submission(SubmissionName, targetlanguage_1);
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
		List<WebElement> listofIds1 = General.driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
		Thread.sleep(1000);
		System.out.println("No of IDS--------" + listofIds1.size());

		for (int i = 1; i <= listofIds1.size(); i++) {
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

	public void reviewer_onGoing_submission(String SubmissionName, String target)throws Exception {

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
		Thread.sleep(4000);

		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		
		//Check copy  source to target  button is active
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
		Thread.sleep(1000);
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying reviewer_CopySource_toTarget_Button  after Search");
		}
		//TODO below assertion for tooltip message will fail due to bug 777
		//verify tool tip message for  copy source to target button
		//TODO FUNCTIONALITY CHANGE FROM ITERATION 2.2.0 RC1
//		Actions builder = new Actions(General.driver);
//		Thread.sleep(1000);
//		WebElement CopyToSource_icon_tooltip = General.driver.findElement(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button_tooltip_message);
		Thread.sleep(4000);
		
		General.action().mouseOver(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
		Thread.sleep(5000);
		assertion = Verify.action().verifyTextPresent("Copy Target to Modified Target (Alt + S)", 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying tool tip for the delete icon");
		}
		//TODO FUNCTIONALITY CHANGE FROM ITERATION 2.2.0 RC1
		//TODO NOT Working
//		builder.moveToElement(CopyToSource_icon_tooltip).perform();
//		Thread.sleep(1000);
//		String tooltip_message = CopyToSource_icon_tooltip.getText();
//		Thread.sleep(1000);
//		System.out.println("TOOL TIP MESSAGE FOR COPY TO SOURCE ICON IS:- "+ tooltip_message);
//		Thread.sleep(3000);
//		
////		assertion = Verify.action().verifyElementTextPresent(tooltip_message, "Copy Target to Modified Target", 10);
//		assertion=tooltip_message.equals("Copy Target to Modified Target");
//		if(assertion==false) {
//			report("f","Assertion failed while verifying tool tip message Copy target to Modified target (Alt + s)  after Search");
//		}

		
		//Check Cancel current edit button is active
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_cancel_current_edit);
		Thread.sleep(1000);
				
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_cancel_current_edit, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying reviewer_cancel_current_edit  after Search");
		}
		//TODO FUNCTIONALITY CHANGE FROM ITERATION 2.2.0 RC1
		//verify tool tip message for  Cancel current edit button 
		//TODO below assertion for tooltip message will fail due to bug 777
//		Actions builder1 = new Actions(General.driver);
//		Thread.sleep(1000);
//		WebElement CancelCurrentEdit_icon_tooltip = General.driver.findElement(ReviewerLocators.Locator().reviewer_cancel_current_edit_tooltip_message);
//		Thread.sleep(1000);
//		builder1.moveToElement(CancelCurrentEdit_icon_tooltip).perform();
//		Thread.sleep(1000);
//		String tooltip_message1 = CancelCurrentEdit_icon_tooltip.getText();
//		Thread.sleep(1000);
//		System.out.println("TOOL TIP MESSAGE FOR CANCEL CURRENT EDIT ICON IS:- "+ tooltip_message1);
//		Thread.sleep(3000);
//		
////		assertion = Verify.action().verifyElementTextPresent(tooltip_message1, "Cancel current edit", 10);
//		assertion=tooltip_message1.equals("Cancel current edit (Esc)");
//		if (assertion == false) {
//			report("f","Assertion failed while verifying tool tip message Cancel current edit  after Search");
//		}
		
		
		General.action().mouseOver(ReviewerLocators.Locator().reviewer_cancel_current_edit);
		Thread.sleep(5000);
		assertion = Verify.action().verifyTextPresent("Cancel current edit (Esc)", 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying tool tip for the delete icon");
		}
		
		
		//Check delete button is active
        Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_delete_icon);
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_delete_icon, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying reviewer_clear_the_text(delete icon)  after Search");
		}
		Thread.sleep(1000);
		
		//TODO below assertion for tooltip message will fail due to bug 777
		//TODO FUNCTIONALITY CHANGE FROM ITERATION 2.2.0 RC1
//		Actions builder2 = new Actions(General.driver);
//		Thread.sleep(1000);
//		WebElement delete_icon_tooltip = General.driver.findElement(ReviewerLocators.Locator().reviewer_delete_icon_tooltip_message);
//		Thread.sleep(1000);
//		builder2.moveToElement(delete_icon_tooltip).perform();
//		Thread.sleep(1000);
//		String tooltip_message2 = delete_icon_tooltip.getText();
//		Thread.sleep(1000);
//		System.out.println("TOOL TIP MESSAGE FOR DELETE ICON IS:- "+ tooltip_message2);
//		Thread.sleep(3000);
//		
////        assertion = Verify.action().verifyElementTextPresent(tooltip_message2, "Clear the text", 10);
//        assertion = tooltip_message2.equals("Clear the text (Alt + X)");
//        if (assertion == false) {
//			report("f","Assertion failed while verifying tool tip message Clear the text  after Search");
//		}
		
		
		General.action().mouseOver(ReviewerLocators.Locator().reviewer_delete_icon);
		Thread.sleep(5000);
		assertion = Verify.action().verifyTextPresent("Clear the text (Alt + X)", 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying tool tip for the delete icon");
		}
		 Target_Textarea_Input = General.driver.findElement(By.xpath("//mat-list-item[contains(@id,'1')]//div[contains(@class,'editor-read-only')]")).getText();
		System.out.println("TARGET TEXT AREA INPUT IS:-" +Target_Textarea_Input);
		Thread.sleep(3000);
		
		
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(3000);
		General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),Keys.chord(Keys.ALT,"s"));
		Thread.sleep(3000);
		
		 Modified_Textarea_Input = General.driver.findElement(By.xpath("//mat-list-item[contains(@id,'1')]//shared-text-editor//div[contains(@class,'mousetrap')]")).getText();
		System.out.println("MODIFIED TARGET TEXT AREA INPUT IS:-" +Modified_Textarea_Input);
		
		boolean Result = Target_Textarea_Input.equals(Modified_Textarea_Input);
		System.out.println("TARGET AND MODIFIED TARGET INPUT TEXT IS SAME AFTER ALT+S :-" +Result);

		Thread.sleep(3000);
		System.out.println("EOM reviewer_onGoing_submission  method()");
	}

	public void assertion() throws Exception {

		assertion = Target_Textarea_Input.equals(Modified_Textarea_Input);
		if (assertion == false) {
			report("f","Assertion failed while verifying modified_textarea is same as target_textarea  after Search");
		} else {
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


