package subtitler.project_manager.Open_Jobs;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
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
 *Summary: Un-claim a Task into Submission details view.
 *Preconditions:Submission should be created and it should be re-assigned to any user or group.
 */ 
public class Sub_1571983 {
	

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1571983"+CommonElements.BROWSER+"Y3";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String UserName = "Maya Gurnale";
	
	@BeforeMethod
	public void setup() throws Exception {General.action().startSystem("SUB_1571983");
		dataSet.put("TL_test_case_description", "SUB-1571983:Un-claim a Task into Submission details view.");
		dataSet.put("TL_internal_testCase_ID", "1571983");
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
			// TODO NEW IMPL OF SUBMISSION CREATION.
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			Thread.sleep(000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			
			//Open Submission
			Thread.sleep(6000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			Thread.sleep(4000);
			
			//Verify Re-assign icon and click on that
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_Reassignment, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying  Re-assign icon after Search");
			}
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_editSubmission_Reassignment);
			Thread.sleep(6000); 
			
			//add user from drop down
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Users_radio_button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Users_radio_button);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Users);
			Thread.sleep(1000);
			General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Users, Pm_User_Submission_Locators.Locator().PM_Reassign_Job_from_Users_dropdown(UserName));
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
			Thread.sleep(2000);
			
			//Verify Unclaim Task button and Click on the unclaim task
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTask, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying uncliam task button");
			}
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTask);
			Thread.sleep(2000);
			
			//Verify  Message in the popup 
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTaskPopUp_message("This task has been started, do you really want to unclaim it?"), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying  unclaim task message");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTaskPopUp_message("Content will not be deleted."), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying  unclaim task message");
			}
			
			//Click on Cancel Button
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTask_cancelButton);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTask_cancelButton);
			Thread.sleep(2000);
			
			//verify after clicking on cancel button the unclaim button should still present(Nothing should happen.)
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTask, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying  unclaim button");
			}
			
			//Click on unclain task
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTask);
			Thread.sleep(2000);
			
			//Click on unclaim Button
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTask_unclaimButton);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTask_unclaimButton);
			Thread.sleep(2000);
			
			//Verify status should change as available after unclaim job
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_Reassignment_Status("trans","Available"), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying status available");
			}
			
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public void assertion() throws Exception {
		//Verify status should change as available after unclaim job
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_Reassignment_Status("trans","Available"), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying status available");
		}else {
			report("p", "All Assertion Passed.");
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

