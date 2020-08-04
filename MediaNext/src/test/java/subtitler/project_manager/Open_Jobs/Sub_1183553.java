package subtitler.project_manager.Open_Jobs;

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

public class Sub_1183553 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_1183553"+CommonElements.BROWSER+"O1";
    String targetlanguage_1 = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String targetlanguage = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_1183553");
		dataSet.put("TL_test_case_description", "Sub_1183553:Select all checkbox of Completed folder");
		dataSet.put("TL_internal_testCase_ID", "1183553");
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
			for(int i=1;i<=3;i++) {
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
				System.out.println("filePath------>" + filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName+i,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				Thread.sleep(2000);
				
				System.out.println("SUBMISSION CREATED--->"+SubmissionName+i);
			}
		   
			Thread.sleep(2000);	
			
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
			Translator.action().translate_onGoing_submission(SubmissionName+1, targetlanguage,true, false);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Translator.action().translate_onGoing_submission(SubmissionName+2, targetlanguage,true, false);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Translator.action().translate_onGoing_submission(SubmissionName+3, targetlanguage,true, false);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_completed);
			Thread.sleep(1000);
			
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Complted_select_header_checkbox);
			Thread.sleep(1000);
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName+1), 5);
			if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
					
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName+2), 5);
			if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
					
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName+3), 5);
			if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
					
			}
			Thread.sleep(4000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Complted_select_header_checkbox);
			Thread.sleep(2000);
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().SelectSubmission_UnCheckbox(SubmissionName+1), 5);
			if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
					
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().SelectSubmission_UnCheckbox(SubmissionName+2), 5);
			if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
					
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().SelectSubmission_UnCheckbox(SubmissionName+3), 5);
			if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
					
			}
			
			

		} catch (Exception e) {
		report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	}

}
	
	
	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName+3), 5);
		if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				
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