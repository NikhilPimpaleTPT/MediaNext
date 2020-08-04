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
 * @author pvaidya                                                   
 *Summary: This test case verifies that if PM user can create submission using TTML with BOM.
 */ 

public class Sub_1596092 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
    String SubmissionName = "SUB_1596092"+CommonElements.BROWSER+"J1";
    String OrganizationName = "Subtitle_Common_orgs";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_AllJobs = "Jobs";
	Boolean assertion = true;
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1596092");
		dataSet.put("TL_test_case_description", "SUB-1596092:Add support for TTML with BOM.");
		dataSet.put("TL_internal_testCase_ID", "1596092");
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
				// TODO NEW IMPL OF SUBMISSION CREATION USING TTML BOM FILE
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_TTML_BOM_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				Thread.sleep(2000);

				//SEARCH SUBMISSION CREATED USING TTML WOTH BOM
				PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				Thread.sleep(2000);
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
				}
				
				
		} catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
            }
  }
	
	
	
	public void assertion() throws Exception {
		//SEARCH SUBMISSION CREATED USING TTML WOTH BOM
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName after Search");
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
