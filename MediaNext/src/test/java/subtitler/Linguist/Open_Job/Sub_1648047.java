package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.RobotExt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

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

public class Sub_1648047 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1648047"+CommonElements.BROWSER+"S1";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
	String path;
    
    Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1648047");
		dataSet.put("TL_test_case_description", "SUB-1648047:Save last session working subtitle ID");
		dataSet.put("TL_internal_testCase_ID", "1648047");
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
			
			Thread.sleep(2000);
            General.action().logoutMethod();
            
           // tran login
           General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
           Thread.sleep(1000);
           Translator.action().Navigate(menu_to_claim);
           Thread.sleep(1000);
           Translator.action().trans_ToClaim(SubmissionName);
		   Thread.sleep(2000);
		   PM_user.action().Navigate(menu_ongoing);
		   Thread.sleep(2000);
		   
		   translate_onGoing_submission(SubmissionName,targetlanguage,false,true);
		   
		   General.action().mouseOver(LoginLocators.Locator().trans_directLogOut_button);
           Thread.sleep(1000);
           General.action().Click(LoginLocators.Locator().trans_directLogOut_button);
		   Thread.sleep(1000);
		   General.action().Click(LoginLocators.Locator().SignOut_link);
		   Thread.sleep(1000);
			
		
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
			Thread.sleep(500);
			
			
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
	    List <WebElement> listofIds1=  General.action().driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
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
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		Thread.sleep(2000);
		
		 
		General.action().driver.navigate().refresh();
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_ongoing_indexCaption("Go to #1"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		
		
		Thread.sleep(4000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(5));
		Thread.sleep(2000);
		
		if(CommonElements.BROWSER.contains("CHROME")) {
			General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_P);
			Thread.sleep(1000);
			}else {
		General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(5),Keys.chord(Keys.ALT, "p"));
		Thread.sleep(3000);	
			}

		navigateTabs();
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_ongoing_indexCaption("Go to #1"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		
		assertion = Verify.action().verifyElementNotPresent(TranslatorLocators.Locator().trans_ongoing_indexCaption("Go to #5"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		
		Thread.sleep(4000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		Thread.sleep(2000);
		
		if(CommonElements.BROWSER.contains("CHROME")) {
			General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_M);
			Thread.sleep(1000);
			}else {
		General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(2),Keys.chord(Keys.ALT, "m"));
		Thread.sleep(3000);	
			}
        navigateTabs();
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_ongoing_indexCaption("Go to #1"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		
		assertion = Verify.action().verifyElementNotPresent(TranslatorLocators.Locator().trans_ongoing_indexCaption("Go to #2"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
		Thread.sleep(2000);
		
		General.action().Click(TranslatorLocators.Locator().trans_ongoing_editTime_icon);
		Thread.sleep(3000);	
		
		General.action().waitforelemenetpresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
	    Thread.sleep(1000);
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
		Thread.sleep(3000);
			
		navigateTabs();
		 
		 
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_ongoing_indexCaption("Go to #1"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
			
		assertion = Verify.action().verifyElementNotPresent(TranslatorLocators.Locator().trans_ongoing_indexCaption("Go to #3"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab);
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_segmentHistoryTab_commentInput,"Comment for segment");
		Thread.sleep(5000);
		General.action().Click(TranslatorLocators.Locator().translator_segmentHistoryTab_commentDialogBox_CommentButton);
		Thread.sleep(1000);
		
		navigateTabs();
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_ongoing_indexCaption("Go to #1"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
			
		assertion = Verify.action().verifyElementNotPresent(TranslatorLocators.Locator().trans_ongoing_indexCaption("Go to #2"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().trans_ongoing_delete_icon);
		Thread.sleep(2000);
		
		
		navigateTabs();
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_ongoing_indexCaption("Go to #1"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
			
		assertion = Verify.action().verifyElementNotPresent(TranslatorLocators.Locator().trans_ongoing_indexCaption("Go to #4"), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying caption id");
		}
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(5));
		Thread.sleep(2000);

		
         System.out.println("EOM Trans_Ongoing_Import_translation_InvalidFile()");
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
	

	public void assertion() throws Exception {
		//Verify caption ID
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_ongoing_indexCaption("Go to #5"), 5);
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