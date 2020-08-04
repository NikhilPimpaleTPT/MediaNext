package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.gs4tr.qa.util.FileZipUnzipThread;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Verify;

/**                                                                                                                             
 * @author pvaidya                                                   
 *Summary:Allow the export to final format from the submission view.
 *Preconditions:Create a submission before test.
 */ 
public class Sub_1571984 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1571984"+CommonElements.BROWSER+"B3";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = { "fr-FR", "de-DE"};
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
	String menu_allJobs = "Jobs";
	String menu_completed = "Completed";
	
	
	Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1571984");
		dataSet.put("TL_test_case_description", "SUB-1571984:Allow the export to final format from the submission view.");
		dataSet.put("TL_internal_testCase_ID", "1571984");
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
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
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
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(2000);
			
			//VERIFY DOWNLOAD BUTTON AND CLICK ON IT
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_file_download, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_file_download);
			Thread.sleep(2000);
			
			//VERIFY SUBMISSION NAME ON DIALOG BOX AS IT IS DISABLED
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_submissionNameDisable, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName is non editable ");
			}
			
			//SELECT FORMAT
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_ChooseFormat);
			Thread.sleep(2000);
			//VERIFY ALL FORMATES
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_SRT, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying srt formate on download dialog box");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_SRTAN, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SRT+ANT formate on download dialog box");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_TTML, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying TTML1 formate on download dialog box");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_EBU, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying EBU-TT-D formate on download dialog box");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_editedTTML, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Edited TTML formate on download dialog box");
			}
			
			//CLICK ON SRT
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_SRT);
			Thread.sleep(5000);
			
			//VERIFY ALL THE JOB LIST
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_header("Target"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Lists all the jobs ");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_header("Step"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Lists all the jobs ");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_header("Status (step)"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Lists all the jobs ");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_header("Assignee"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Lists all the jobs ");
			}
			
			//CHECK HEADER CHECKBOX
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_headerCheckbox);
			Thread.sleep(5000);
			
			//VERIFY THAT ONCE HEADER CHECKBOX SELECTED IT SELECT MULTIPLE JOBS
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_FileDownload_dialogBox_CheckBox("German (Germany)"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying submission for German (Germany) is selected once click on hear checkbox ");
			}
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}


	public void assertion() throws Exception {
		//VERIFY THAT ONCE HEADER CHECKBOX SELECTED IT SELECT MULTIPLE JOBS
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_FileDownload_dialogBox_CheckBox("French (France)"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying submission for French (France) is selected once click on hear checkbox");
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