package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: Verify that if user can create a submission for more than 2k subtitles.
 *Preconditions :Use attached file to create submission.
 */ 
public class Sub_1445560 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	String SubmissionName = "SUB_1445560"+CommonElements.BROWSER+"M1";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewlatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String folder_1445560 = "1445560";
	@BeforeMethod
	public void setup() throws Exception {General.action().startSystem("SUB_1445560");
		dataSet.put("TL_test_case_description", "SUB-1445560:Display more than 2000 subtitles");
		dataSet.put("TL_internal_testCase_ID", "1445560");
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
			// TODO NEW IMPL OF SUBMISSION CREATION AND USE 2K SRT FILE
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewlatorGroupName, true);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+folder_1445560);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(8000);
			//VERIFY SUBMISSION NAME
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			
			General.action().logoutMethod();

			// tran login
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(20000);
			
			//CLICK ON TO CLAIM AND CLAIM SUBMISSION
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
	        transAndReview_Submission(SubmissionName,"trans");
			Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_submission_sourceSegments("1599","Vraiment ?"), 5);
			if (assertion == false) {
			report("f", "Assertion failed while verifying 1st segment of 2k srt file.");
			}
			
			//Completed submission
			Translator.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
			Thread.sleep(1000);
			Translator.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
			Thread.sleep(1000);
			Translator.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
			Thread.sleep(1000);
			Translator.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
			Thread.sleep(3000);
			
			if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
				Thread.sleep(1000);
				General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
		    }
		    
		
	    
	       
	    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
				Thread.sleep(1000);
				General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
		  
	        }
	    	Thread.sleep(1000);
			General.action().logoutMethod();
			
			General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
			Thread.sleep(20000);
			Reviewer.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Reviewer.action().review_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			transAndReview_Submission(SubmissionName,"review");
		    Thread.sleep(2000);
		    
		    
				

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void transAndReview_Submission(String SubmissionName, String status) throws Exception {
		
		 System.out.println("INSIDE Pm_Complete_trans_Submission  method()");
		 
		    PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, status)))
				{
				System.out.println("INSIDE IF--------");
				 PM_user.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
				}
			  Thread.sleep(6000);
			  PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			  Thread.sleep(6000);     
			  
		  
			  assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_submission_sourceSegments("1","Jellyfish at the Monterey Aquarium."), 5);
			  if (assertion == false) {
			  report("f", "Assertion failed while verifying 1st segment of 2k srt file.");
			  }
			  
			  Thread.sleep(2000);
			  PM_user.action().Click(TranslatorLocators.Locator().trans_TargetSegement_textarea(1));
			  Thread.sleep(6000);
			  
			  for(int i=1;i<=3;i++) {
			  Thread.sleep(1000); 
			  General.action().scrollDown(3);
			  General.action().scrollDown_end(6);
			  }
			  
			  

	}
	
	
	
	
	public void assertion() throws Exception {
		 assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_submission_sourceSegments("1599","Vraiment ?"), 5);
		  if (assertion == false) {
		  report("f", "Assertion failed while verifying 1st segment of 2k srt file.");
		 } else {
	    report("p", "This assertion is Passed");
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