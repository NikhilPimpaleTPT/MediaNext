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

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This testcase verifies that user can create a submission for Telugu (India) language.

 */ 


public class Sub_1644892 {
	
	
	    LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	    String SubmissionName = "SUB_1644892"+CommonElements.BROWSER+"J1";
	    String OrganizationName = "Subtitle_Common_orgs";
	    String WorkflowName = "One_Step_Workflow";
		String menu_Submission = "Submissions";
		String TranslatorGroupName = "Common_group";
		String COMMON_TARGET_LANGUAGE="te-IN";
		String menu_AllJobs = "Jobs";
		String folder_1644892 = "1644892";
		Boolean assertion = true;
		
		
		@BeforeMethod
		public void setup() throws Exception {
			General.action().startSystem("SUB_1644892");
			dataSet.put("TL_test_case_description", "SUB-1644892:Add Telugu (India) to language list.");
			dataSet.put("TL_internal_testCase_ID", "1644892");
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
					PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
					PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
					PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
					PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
					PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,COMMON_TARGET_LANGUAGE);
					Thread.sleep(2000);

					//SEARCH SUBMISSION AND CHECK TELGU AS TARGET LANGUAGE
					PM_user.action().SearchSubmisson_andCheck(SubmissionName);
					Thread.sleep(2000);
					assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying SubmissionName  after Search");
					}
					PM_user.action().Navigate(menu_AllJobs);
					Thread.sleep(1000);
					PM_user.action().SearchSubmisson(SubmissionName);
					Thread.sleep(2000);
					
					
					
			} catch (Exception e) {
		         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	            }
	  }
		
		
		
		public void assertion() throws Exception {
			//SEARCH SUBMISSION AND CHECK TELGU AS TARGET LANGUAGE
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName,"Telugu (India)"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search with target language Telgu");
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
