package subtitler.Reviewer;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class Sub_184763 {

	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_184763"+CommonElements.BROWSER+"QA1";
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
	String modified_textarea;
	File filepath1;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_184763");
		dataSet.put("TL_test_case_description", "SUB-184763:In Progress jobs");
		dataSet.put("TL_internal_testCase_ID", "184763");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
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

		Thread.sleep(5000);
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
		Thread.sleep(5000);

		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(3000);
		General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),"Test Modified");
		Thread.sleep(3000);

		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(3000);

		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(3000);
		//THis code is not working hence using robot class
//		General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),Keys.chord(Keys.CONTROL, "a"));
//		Thread.sleep(3000);
//	
//		General.action().type_withKEYS(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1), Keys.DELETE, false);
//		
		Thread.sleep(2000);
		General.action().Enter_keyEnvents(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		Thread.sleep(3000);	
		General.action().Enter_keyEnvents(KeyEvent.VK_DELETE);;
		Thread.sleep(3000);	
		
	    General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_BackToSubmissionList_Button);
		Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().reviewer_BackToSubmissionList_Button);
		
		if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
	    	}

		Thread.sleep(3000);
		Reviewer.action().Navigate(menu_ongoing);
		Thread.sleep(1000);
		Reviewer.action().SearchSubmisson_andCheck(SubmissionName);
		Thread.sleep(3000);
		General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
		Thread.sleep(4000);

		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(3000);
		//THis code is not working hence using robot class
//	    General.action().type_withKEYS(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3), Keys.SPACE, false);
		Thread.sleep(3000);	
		General.action().Enter_keyEnvents(KeyEvent.VK_SPACE);
		Thread.sleep(3000);	
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_modifiedTarget_Line, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying review_modifiedTarget_Line is active  after Search");
		}
		Thread.sleep(3000);
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4), "a");
		Thread.sleep(3000);
		
        General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
		Thread.sleep(1000);
		String modified_lineCount = General.driver.findElement(By.xpath("//mat-list-item[contains(@id,'4')]//div[contains(@class,'item-content')]//div[7]//span[contains(text(),'L1')]")).getText();
		System.out.println("LINE COUNT OF MODIFIED TEXT AREA:- "+ modified_lineCount);

		assertion = modified_lineCount.trim().contains("L1:");
		if (assertion == false) {
			report("f","Assertion failed while verifying modified_lineCount is two  after Search");
		}
		
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
		Thread.sleep(1000);
		
		//THis code is not working hence using robot class
//		Thread.sleep(3000);
//		General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4),Keys.chord(Keys.CONTROL, "a"));
//		Thread.sleep(3000);
//	    General.action().type_withKEYS(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1), Keys.DELETE, false);
		
		Thread.sleep(2000);
		General.action().Enter_keyEnvents(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		Thread.sleep(3000);	
		General.action().Enter_keyEnvents(KeyEvent.VK_DELETE);;
		Thread.sleep(3000);	
	   
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_BackToSubmissionList_Button);
		Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().reviewer_BackToSubmissionList_Button);
        Thread.sleep(3000);
	    if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
	    	}
	    Thread.sleep(2000);
		Reviewer.action().Navigate(menu_ongoing);
		Thread.sleep(1000);
		Reviewer.action().SearchSubmisson_andCheck(SubmissionName);
		Thread.sleep(3000);
		General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
		Thread.sleep(4000);

		modified_textarea = General.driver.findElement(By.xpath("//shared-text-editor//div[contains(@class,'mousetrap')]")).getText();
		System.out.println(" MODIFIED TEXT AREA IS EMPTY:- "+modified_textarea.isEmpty());

		assertion = modified_textarea.isEmpty();
		if (assertion == false) {
			report("f","Assertion failed while verifying modified_textarea is empty  after Search");
		}
		System.out.println("EOM reviewer_onGoing_submission  method()");
	}

	public void assertion() throws Exception {

		assertion = modified_textarea.isEmpty();
		if (assertion == false) {
			report("f","Assertion failed while verifying modified_textarea is empty  after Search");
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
