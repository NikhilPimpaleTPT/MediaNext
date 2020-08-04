package subtitler.Transcription_functionality_testcases;

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
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

public class Sub_763920 {

	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_763920"+CommonElements.BROWSER+"P2";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String Subtitle_after;
	String ReadingSpeed_after;
	String Subtitle_before;
	String ReadingSpeed_before;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_763920");
		dataSet.put("TL_test_case_description",	"SUB-763920:Update segment : no need to Alt + u to validate the change");
		dataSet.put("TL_internal_testCase_ID", "763920");
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
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			Pm_Transcription_Ongoing_Update_segment(SubmissionName, targetlanguage_1,false, true);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public void Pm_Transcription_Ongoing_Update_segment(String SubmissionName,String target, boolean complete, boolean back) throws Exception {

		System.out.println("INSIDE Pm_Transcription_Ongoing_Preview_mode  method()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
        Thread.sleep(10000);
        
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		
	    ReadingSpeed_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(1)).getText();
		System.out.println("ReadingSpeed######### :-"+ ReadingSpeed_before);
		Thread.sleep(2000);
		
	    Subtitle_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1)).getText();
		System.out.println("SUBTITLE BEFORE######### :-"+ Subtitle_before);
		Thread.sleep(2000);
		
		
		
		
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_Transcription_Video_textarea  after Search");
		}
		
		
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		
		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Test target for min RS.");
		Thread.sleep(4000);
		
		
		
	    ReadingSpeed_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(1)).getText();
		System.out.println("ReadingSpeed######### :-"+ ReadingSpeed_after);
		Thread.sleep(2000);
		
		
		 Subtitle_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1)).getText();
		System.out.println("SUBTITLE AFTER######### :-"+ Subtitle_after);
		Thread.sleep(2000);
		
		assertion = ReadingSpeed_before != ReadingSpeed_after && Subtitle_before != Subtitle_after;
		if (assertion == false) {
			report("f","Assertion failed while verifying Reading Speed and Subtitle is not same after editing  after Search");
		}
		
		
 }
	
	
	    public void assertion() throws Exception {
	    	assertion = ReadingSpeed_before != ReadingSpeed_after && Subtitle_before != Subtitle_after;
			if (assertion == false) {
				report("f","Assertion failed while verifying Reading Speed and Subtitle is not same after editing  after Search");
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


