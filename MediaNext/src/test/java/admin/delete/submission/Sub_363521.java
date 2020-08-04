package admin.delete.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_363521 {
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "Submission_363521"+CommonElements.BROWSER+"W6";
	String sourcelanguage = "en-US - English (United States)";
	String targetlanguage = "de-DE - German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileName = "common";
    String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_submissions = "Submissions";
	String TransColumnName[] = { "Id", "Time", "Source", "Target", "Time","Duration", "Reading Speed" };
	String ReviewColumnName[] = { "Id", "Time", "Source", "Target","Modified Target", "Time", "Duration", "Reading Speed" };
	String filterStatus="Completed";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_363521");
		dataSet.put("TL_test_case_description","SUB_363521:Align translation grid headers and cells");
		dataSet.put("TL_internal_testCase_ID", "363521");
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
			Thread.sleep(3000);
			PM_user.action().Navigate(menu_submissions);
			Thread.sleep(2000);
         // TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", ReviewGroupName, true);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_submissions);
			Thread.sleep(2000);
			pm_check_availabilityOf_colunms_trans(TransColumnName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_submissions);
			Thread.sleep(2000);
			PM_user.action().Pm_Complete_trans_Submission(SubmissionName, "New");
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_submissions);
			Thread.sleep(2000);
			pm_check_availabilityOf_colunms_review(ReviewColumnName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_submissions);
			Thread.sleep(2000);
			PM_user.action().Pm_Complete_review_Submission(SubmissionName,"In_Progress");
			Thread.sleep(3000);
			PM_user.action().Navigate(menu_submissions);
			Thread.sleep(2000);
			//TODO NOT NEEDED AS PER NEW FUNCTIONALITY IN 1.22.0 RC1
//			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Show_Completed);
//			Thread.sleep(2000);
			// This assertion will check submission status "Completed" in PM submission tab
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().applyFilters(false, false,OrganizationName, true,filterStatus, false,sourcelanguage );
			Thread.sleep(2000);
		
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName,"Completed"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying check_submission_status after Search");
			}

		} catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}
	/**
	 * @author psukhwani
	 * This method is used to check column names of trans submission
	 * @param TransColumnName
	 * @throws Exception
	 */

	public void pm_check_availabilityOf_colunms_trans(String[] TransColumnName)throws Exception {

		System.out.println("INSIDE pm_check_availabilityOf_colunms_trans  method()");

		PM_user.action().SearchSubmisson_andCheck(SubmissionName);
		Thread.sleep(6000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
		Thread.sleep(6000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
		Thread.sleep(4000);

		for (int i = 0; i <= 6; i++) {

			System.out.println("INSIDE For Loop");
            //This assertion will check TransColumnName while completing Pm_trans_submission
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_allindexof_colunms(TransColumnName[i]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			System.out.println("Identified colunmName is available:-" + TransColumnName[i]);
		}
		Thread.sleep(3000);
		if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
			Thread.sleep(2000);
	    	}
		System.out.println("EOM pm_check_availabilityOf_colunms_trans  method()");

	}
	/**
	 * This method is used to check column names of review submission
	 * @author psukhwani
	 * @param ReviewColumnName
	 * @throws Exception
	 */

	public void pm_check_availabilityOf_colunms_review(String[] ReviewColumnName)throws Exception {

		System.out.println("INSIDE pm_check_availabilityOf_colunms_review  method()");

		PM_user.action().SearchSubmisson_andCheck(SubmissionName);
		Thread.sleep(6000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
		Thread.sleep(6000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_review_header);
		Thread.sleep(6000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
		Thread.sleep(4000);

		for (int i = 0; i <= 7; i++) {

			System.out.println("INSIDE For Loop");
            //This Assertion will check ReviewColumnName while completing Pm_review_submission
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_allindexof_colunms(ReviewColumnName[i]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			System.out.println("Identified colunmName is available:-" + ReviewColumnName[i]);
		}
		Thread.sleep(3000);
		if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
			Thread.sleep(2000);
		}
		System.out.println("INSIDE pm_check_availabilityOf_colunms_review  method()");
	}

	public void assertion() throws Exception {
		// This assertion will check that sub is present
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName,"Completed"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying check_submission_status after Search");
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
