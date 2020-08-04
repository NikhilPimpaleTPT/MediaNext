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
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;


/**
 * 
 * @author pvaidya
 *Summary:This test case is to verify not enough frames error is not considering OST segments
 *
 */

public class Sub_2291114 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_2291114"+CommonElements.BROWSER+"B4";
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
	String folder="854940";
    Boolean assertion = true;
    
    
   	@BeforeMethod
   	public void setup() throws Exception {
   		General.action().startSystem("SUB_2291114");
   		dataSet.put("TL_test_case_description", "SUB_2291114:As a linguist, I want the OST ignored when running the QC check validating the min number of frame in between subtitles");
   		dataSet.put("TL_internal_testCase_ID", "2291114");
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
   			//TODO NEW IMPL OF SUBMISSION CREATION
   			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
   			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
   			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
   			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
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
			 
			 
			// trans login
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(20000);
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			translate_onGoing_submission(SubmissionName,targetlanguage,true,false);
			Thread.sleep(2000);
   			
   			
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
			Thread.sleep(8000);
			
			
			//1st of all Run QC and check "Not Enough frames" error for 1st segment
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_qualitycheck_button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().Trans_ongoing_qualitycheck_button);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_qualitycheck_Run_button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().Trans_ongoing_qualitycheck_Run_button);
			Thread.sleep(1000);
			
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().qualitycheck_rule_notEnoughFrames(1), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  the 'Not enough frames' error is calculating for the subtitles not having");
			}
			
			
			//Click on segment 2 and make it OST segment
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			Thread.sleep(1000);
			
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().trans_ongoing_onScreenText_icon);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().trans_ongoing_onScreenText_icon);
			Thread.sleep(1000);
			
			//Run QC and check OST segments should not taken into consideration when calculating not enough frames error.
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_qualitycheck_Run_button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().Trans_ongoing_qualitycheck_Run_button);
			Thread.sleep(1000);
			
			
			//Verify OST segments should not taken into consideration when calculating not enough frames error.
			//Error should not display after adding ost to 2nd seg
			assertion = Verify.action().verifyElementNotPresent(TranslatorLocators.Locator().qualitycheck_rule_notEnoughFrames(1), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  the 'Not enough frames' error is calculating for the subtitles not having");
			}
			
			
			
   	}
   	public void assertion() throws Exception {
			
   	    //Verify OST segments should not taken into consideration when calculating not enough frames error.
   		assertion = Verify.action().verifyElementNotPresent(TranslatorLocators.Locator().qualitycheck_rule_notEnoughFrames(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying  the 'Not enough frames' error is calculating for the subtitles not having");
		}else {
					report("p", "Assertion is passed");
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


