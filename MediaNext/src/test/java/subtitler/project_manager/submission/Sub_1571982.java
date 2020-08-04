package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
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
import modules.PM_user;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary:User with Manage Submission permission would be able to Re-Assign users from Submission details view.
 *Preconditions :Submission should be created and it should be in available status.
 */ 
public class Sub_1571982 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	List<String> CSVReportData;
	String SubmissionName = "Submission_1571982"+CommonElements.BROWSER+"B1";
	String sourcelanguage = "en-US - English (United States)";
	String targetlanguage = "de-DE - German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileName = "common";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String UserName1="Admin";
	String UserName2 = "Maya Gurnale";
	String GroupName = "Common_group";
	String menu_AllJobs = "Jobs";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String menu_submission = "Submissions";
	String targetlanguage_1 = "German (Germany)";
	Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1571982");
		dataSet.put("TL_test_case_description", "SUB-1571982:Re-assign a job into submission details view.");
		dataSet.put("TL_internal_testCase_ID", "1571982");
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
			PM_user.action().Navigate(menu_submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			//Navigate to submission and check submission
			PM_user.action().Navigate(menu_submission);
		
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(6000);
			//Click on open button
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			Thread.sleep(6000);   
			
			//Verify submission is present in availabale status
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_Reassignment_Status("trans","Available"), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying status available");
			}
			
			//Verify Re-assign icon and click on that
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_Reassignment, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying  Re-assign icon after Search");
			}
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_editSubmission_Reassignment);
			Thread.sleep(6000); 
			
			//Verify user radio button
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Users_radio_button, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying user radio button");
			}
			
			//add user from drop down
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Users_radio_button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Users_radio_button);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Users);
			Thread.sleep(1000);
			//Verify if users option is select then the user drop down list is shown sorted order by first name.
			Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Users, Pm_User_Submission_Locators.Locator().PM_Reassign_Job_from_Users_dropdown(UserName2));
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
			Thread.sleep(2000);
			
			//Verify after  job is assigned to user the status should changed to claimed 
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_Reassignment_Status("trans","Claimed"), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying status");
			}
			
			//Click on unclaimed task and unclaim job
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTask);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTask);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTask_unclaimButton);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_editSubmission_unclaimTask_unclaimButton);
			Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_Reassignment_Status("trans","Available"), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying status available");
			}
			
			//Again click on re assign job and assign job to group
			Thread.sleep(4000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_editSubmission_Reassignment);
			Thread.sleep(6000); 
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Groups_radio_button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Groups_radio_button);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Groups);
			Thread.sleep(1000);
			General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Groups, Pm_User_Submission_Locators.Locator().PM_Reassign_Job_from_Groups_dropdown(GroupName));
	    	Thread.sleep(General.action().defaultWaitPeriod*10);
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
			Thread.sleep(2000);
			
			//After assigned job to group the status should be unchanged (available )
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_Reassignment_Status("trans","Available"), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying status available");
			}
			
			//Verify grouop name
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_editSubmission_groupName("Common_group"), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying group name");
			}
			Thread.sleep(1000);
			
			
			//Verify that re-assign icon is not visible for 'To Claim/Completed' submissions.
			PM_user.action().Navigate(menu_to_claim);
	        Thread.sleep(1000);
	    	assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment ,5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying Re-assign icon in To Claim tab");
			}
	        PM_user.action().PM_ToClaim(SubmissionName);
	        Thread.sleep(1000);
	        PM_user.action().Navigate(menu_ongoing);
	        Thread.sleep(1000);
	        PM_user.action().PM_onGoing_submission(SubmissionName, targetlanguage_1, false, true);
	        Thread.sleep(2000);
	        PM_user.action().Navigate(menu_completed);
			Thread.sleep(2000);
		
	} catch (Exception e) {
		report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	}

}
	
	
	public void Dropdownwithoutselect(By bydropdwon,By byvalue) throws Exception{
		System.out.println("INSIDE METHOD Dropdownwithoutselect");
		General.action().driver.findElement(bydropdwon).click();
		Thread.sleep(2000);
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_from_Users_dropdown(UserName2), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying group name");
		}
		((JavascriptExecutor)General.action().driver).executeScript(
	    "arguments[0].scrollIntoView();",General.action().driver.findElement(byvalue));
		General.action().waitforelemenetpresent(byvalue);
		Thread.sleep(2000);
		Actions actions = new Actions(General.action().driver);
		actions.moveToElement(General.action().driver.findElement(byvalue));
		Thread.sleep(2000);
		actions.perform();
		General.action().driver.findElement(byvalue).click();	
		System.out.println("EOM Dropdownwithoutselect()");
	}

public void assertion() throws Exception {
	//Verify that re-assign icon is not visible for 'To Claim/Completed' submissions.
	assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment, 5);
	if (assertion == false) {
		report("f", "Assertion failed while verifying Re-assign icon in Completed tab");
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
