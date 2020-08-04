package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.OrganizationLocators;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import modules.PM_user;
import modules.Verify;

/**                                                                                                                                     
 * @author pvaidya                                                   
 *Summary: This testcase verifies that user cannot rename a submission with spaces only.
 */ 

public class Sub_1647939 {
	
	
	 LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	    String SubmissionName = "SUB_1647939"+CommonElements.BROWSER+"M3";
	    String SubmissionName_edited="                        ";
	    String OrganizationName = "Subtitle_Common_orgs";
	    String WorkflowName = "One_Step_Workflow";
		String menu_Submission = "Submissions";
		String TranslatorGroupName = "Common_group";
		String menu_AllJobs = "Jobs";
		Boolean assertion = true;
		
		
		@BeforeMethod
		public void setup() throws Exception {
			General.action().startSystem("SUB_1647939");
			dataSet.put("TL_test_case_description", "SUB-1647939:Prevent renaming submissions with an empty name.");
			dataSet.put("TL_internal_testCase_ID", "1647939");
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
					// TODO NEW IMPL OF SUBMISSION CREATION USING 2K SRT
					PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
					PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
					PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
					PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
					PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
					Thread.sleep(2000);

					//SEARCH SUBMISSION AND CHECK TELGU AS TARGET LANGUAGE
					PM_user.action().SearchSubmisson_andCheck(SubmissionName);
					Thread.sleep(2000);
					assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying SubmissionName  after Search");
					}
					
					General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
					Thread.sleep(4000);
					General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_SubmissionName_input);
			        Thread.sleep(3000);
			        General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_submission_open_SubmissionName_input);
					Thread.sleep(2000);
			        General.action().type(Pm_User_Submission_Locators.Locator().Pm_submission_open_SubmissionName_input, SubmissionName_edited);
			        Thread.sleep(1000);
					General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_SubmissionName_newStatus);
			        Thread.sleep(2000);
				
			} catch (Exception e) {
		         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	            }
	  }
		
		
		
		public void assertion() throws Exception {
			//SEARCH SUBMISSION NAME WITH ONLY SPACES ERROR
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_SubmissionName_validationMessage, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName only spaces error");
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
