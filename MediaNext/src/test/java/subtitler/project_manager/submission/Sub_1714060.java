package subtitler.project_manager.submission;

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
import modules.PM_user;
import modules.Verify;

public class Sub_1714060 {
	
	
	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_1714060"+CommonElements.BROWSER+"O2";
	String sourcelanguage = "en-US";
	String Targetlanguage[] = { "ar-AE","de-DE","pt-AO","sv-FI","cs-CZ","nl-BE" };
	String Targetlanguage_1[] = { "Arabic (United Arab Emirates)", "German (Germany)","Portuguese (Angola)","Swedish (Finland)","Czech (Czech Republic)","Dutch (Belgium)"};
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_AllJobs = "Jobs";
    String TranslatorGroupName = "Common_group";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1714060");
		dataSet.put("TL_test_case_description","SUB-1714060:Submission with multiple target languages");
		dataSet.put("TL_internal_testCase_ID", "1714060");
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
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			// Submission with multiple language
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_APOSTROPHE_FOLDER);
			PM_user.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, Targetlanguage);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			PM_user.action().Navigate(menu_Submission);
			
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			
			Thread.sleep(3000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			Thread.sleep(3000);
		
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_targetLanguages(Targetlanguage_1[0]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_targetLanguages(Targetlanguage_1[1]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_targetLanguages(Targetlanguage_1[2]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_targetLanguages(Targetlanguage_1[3]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_targetLanguages(Targetlanguage_1[4]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
            PM_user.action().Navigate(menu_AllJobs);
            Thread.sleep(2000);
            
            
            PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			
            
            assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName,Targetlanguage_1[5]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying target language");
			}
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName,Targetlanguage_1[1]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying target language");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName,Targetlanguage_1[2]), 5);
		    if (assertion == false) {
				report("f","Assertion failed while verifying target language");
			}
		    
		    assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName,Targetlanguage_1[3]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying target language");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName,Targetlanguage_1[4]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying target language");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName,Targetlanguage_1[0]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying target language");
			}
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName after Search");
		} else {
			report("p", "All Assertion passed.");
		}
	}

	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(
				dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}
}