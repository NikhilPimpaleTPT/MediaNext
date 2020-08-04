package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

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
 *Summary:This test case verifies that segments are not merged automatically when last line is empty and the number of lines is > 2.
 *Preconditions :Edit a submission that allows entering more than two max. lines per subtitle.
 *
 */ 

public class Sub_1648069 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_1648069"+CommonElements.BROWSER+"Z1";
	String targetlanguage_1 = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_1648069");
		dataSet.put("TL_test_case_description", "Sub_1648069:Segments are not merged automatically when last line is empty and the number of lines is > 2");
		dataSet.put("TL_internal_testCase_ID", "1648069");
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
			CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, false);
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
		   translate_onGoing_submission(SubmissionName, targetlanguage_1, true, false);
		   Thread.sleep(2000);
		   
	
	
		 } catch (Exception e) {
				report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			}

		}
	
	 public void CreateSubmisson_Step1(String ReadingSpeed,String MaxCharperLine,String MindurationVSreadingSpeed,String MaxdurationVSreadingSpeed) throws Exception{
		 System.out.println("INSIDE method CreateSubmisson_Step1()\n");
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
			Thread.sleep(1000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input,ReadingSpeed);
			Thread.sleep(1000);
	
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input,"3");
			Thread.sleep(1000);
			
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,MaxCharperLine);
			Thread.sleep(1000);
			
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,MindurationVSreadingSpeed);
			Thread.sleep(1000);
			
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,MaxdurationVSreadingSpeed);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
			Thread.sleep(1000);
		
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
			Thread.sleep(4000);
			 System.out.println("EOM CreateSubmisson_Step1()\n");

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
				  
		    Thread.sleep(5000);	  
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			if(CommonElements.BROWSER.contains("CHROME")) {
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),"a\n"+"b\n"+" ");
			Thread.sleep(2000);
			}else {
			Thread.sleep(1000);
			//TODO FOR FF
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),"a\r\n"+"b\r\n"+" ");
			Thread.sleep(2000);
			}
			
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			Thread.sleep(1000);
			
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			

			assertion=!General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText().contains("ab");
			if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
			}
		 
	}
	
	
	public void assertion() throws Exception {
		assertion=General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText().contains("a\n"+"b");
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

