package subtitler.project.manager.Sort_Submissions;

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

public class Sub_54775 {

	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String sourcelanguage = "en-US - English (United States)";
	String targetlanguage = "de-DE - German (Germany)";
	String SubmissionName = "Sub_54775"+CommonElements.BROWSER+"Z1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String menu = "Submissions";
    String TranslatorGroupName = "Common_group";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_54775");
		dataSet.put("TL_test_case_description","SUB-54775:Sort by submission name");
		dataSet.put("TL_internal_testCase_ID", "54775");
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
			PM_user.action().Navigate(menu);
			// TODO NEW IMPL OF SUBMISSION CREATION
			for (int i = 1; i <= 3; i++) {
				
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName + i,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);

			}
            Thread.sleep(4000);
            
            //sort submission
			PM_user.action().sortSubmission(SubmissionName, true);

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name after search");
			}

			PM_user.action().sortSubmission(SubmissionName, false);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name after search");
			}

			System.out.println("EOM sortSubmission()");

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Bad Credentials Message");
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
