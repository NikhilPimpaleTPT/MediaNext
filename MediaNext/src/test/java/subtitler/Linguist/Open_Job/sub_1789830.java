package subtitler.Linguist.Open_Job;

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
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

//@author : swati
//summary : This test case verifies that if user can enter more then two lines in target column.

public class sub_1789830 {
	
	Boolean assertion = true;
	LinkedHashMap<String,String> dataset = new LinkedHashMap<String, String>();
	
	String SubmissionName = "SUB_1789830"+CommonElements.BROWSER+"Q1";
	String WorkflowName = "One_Step_Workflow";
	String OrganizationName = "Subtitle_Common_orgs";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_submission = "Submissions";
	String targetlanguage = "German (Germany)";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
	String defaultValue_maxLinePerSub="2";
	String lengthOfTheSegment;
	String threelinetext = "aaaa sss\n" + "bbbb bfsjdkfvb\n" + 	"cccc kdfdsj";

	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1789830");
		dataset.put("TL_test_case_description", "SUB-1789830:Enter more that 2 lines in target column");
		dataset.put("TL_internal_testCase_ID", "1789830");
	}
	
	@Test
	public void execute() throws Exception {
		testCase(dataset);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataset) throws Exception {
		
		try {
		
		General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
		Thread.sleep(20000);
		PM_user.action().Navigate(menu_submission);
		Thread.sleep(1000);		
		CreateSubmisson_Step1("17", "35", "80", "100");
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
		PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "", "", true);
		PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
		PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
		PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
		Thread.sleep(20000);
	
		//SEARCH SUBMISSION AND CHECK
		PM_user.action().SearchSubmisson_andCheck(SubmissionName);
		Thread.sleep(2000);
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
		}
		
		Thread.sleep(2000);
		General.action().logoutMethod();
		
		//translator login
		
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
	private void translate_onGoing_submission(String submissionName, String target, boolean complete, boolean back) throws Exception {
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
		Thread.sleep(15000);
		
		List<WebElement> listofIds1 = General.driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
		Thread.sleep(1000);
		//int idcount = listofIds1.size();
		//System.out.println("No of IDS--------" + idcount);

		for (int i = 1; i <= listofIds1.size(); i++) {
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(i), threelinetext); 	
			
			
		}	
			assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText().contains(threelinetext);
			System.out.println(assertion);
			if(assertion==false) {
				report("f","Assertion failed while verifying subtitles");
			}
			
			assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(2)).getText().contains(threelinetext);
			System.out.println(assertion);
			if(assertion==false) {
				report("f","Assertion failed while verifying subtitles");
			}
			assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(3)).getText().contains(threelinetext);
			System.out.println(assertion);
			if(assertion==false) {
				report("f","Assertion failed while verifying subtitles");
			}
			assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(4)).getText().contains(threelinetext);
			System.out.println(assertion);
			if(assertion==false) {
				report("f","Assertion failed while verifying subtitles");
			}
			assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(5)).getText().contains(threelinetext);
			System.out.println(assertion);
			if(assertion==false) {
				report("f","Assertion failed while verifying subtitles");
			}
			assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(6)).getText().contains(threelinetext);
			System.out.println(assertion);
			if(assertion==false) {
				report("f","Assertion failed while verifying subtitles");
			}
			
			
	}

	public void CreateSubmisson_Step1(String ReadingSpeed,String MaxCharperLine,String MindurationVSreadingSpeed,String MaxdurationVSreadingSpeed) throws Exception{
		System.out.println("INSIDE method CreateSubmisson_Step1()\n");
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		 Thread.sleep(1000);
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
		 Thread.sleep(1000);
		 
		 assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input).getAttribute("value").contains(defaultValue_maxLinePerSub);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying default value for the 'Max line per sub' is 2. ");
				
		 }
	     
	     PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input,"3");
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		 Thread.sleep(1000);
		 System.out.println("EOM CreateSubmisson_Step1()\n");
		 Thread.sleep(2000);
		
	}	

	public void assertion() throws Exception {
		System.out.println("inside assertion");		
		assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(6)).getText().contains(threelinetext);
		System.out.println(assertion);
		if(assertion==false) {
			report("f","Assertion failed while verifying subtitles");
		}else {
				report("p", "All Assertion passed.");
			}
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(dataset.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}
}
	
	

	