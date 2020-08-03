package Smoke;

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
import locators.WorkflowLocators;
import modules.PM_user;
import modules.Verify;
import modules.admin;

public class Sub_765158 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	//Workflows
	
	String OrganizationName_transc = "Org_transc_765158"+CommonElements.BROWSER+"S1";
	String description_org_transc = "Desc for Organization_transc_765158"+CommonElements.BROWSER+"S1";
	String GroupName_transc = "Gp_transc_765158"+CommonElements.BROWSER+"S1";
	String description_grp_transc = "Desc for transc_765158"+CommonElements.BROWSER+"S1";
	String TranslatorGroupName = "Gp_transc_765158"+CommonElements.BROWSER+"S1";
	String ReviewerGroupName = "Gp_transc_765158"+CommonElements.BROWSER+"S1";
	String TranscriptionGroupName = "Gp_transc_765158"+CommonElements.BROWSER+"S1";
	String SubmissionName = "SUB_765158_Transc"+CommonElements.BROWSER+"S1";
    String menu_Workflows = "Workflows";
	String WorkflowName_tansc = "WF_transc_765158"+CommonElements.BROWSER+"S1";
	String OrganizationName = "TransPerfect";
	String StepName_trans = "trans";
	String StepFromDropdown_trans = "Translation";
	String StepName_review = "review";
	String StepFromDropdown_review = "Review";
	String StepName_transc = "Transc";
	String StepFromDropdown_transc = "Transcription";
	
	//Organizations
	String menu_org ="Clients";

	String ParentOrganizationName = "TransPerfect";
	String WorkflowName []= {"One_Step_Workflow","Two_Step_Workflow",WorkflowName_tansc};
	String User[] = {CommonElements.action().PM_username_firstName,CommonElements.action().PM_username_second_manual_firstName,CommonElements.action().Translator_username_firstName,CommonElements.action().Reviwer_username_firstName,CommonElements.action().admin_username_firstName};
	
	//Groups
	 String menu_groups="Groups";

	
	
	//PM Submission
	String menu_Submission = "Submissions";
	String menu_AllJobs = "Jobs";
	String fileDirName = "common";

	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {General.action().startSystem("SUB_765158");
		dataSet.put("TL_test_case_description", "SUB-765158:Create transcription submission");
		dataSet.put("TL_internal_testCase_ID", "765158");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			// admin login //Create Workflow for transcription
			admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			admin.action().Navigate(menu_Workflows);
			admin.action().CreateWorkflow_Step1(WorkflowName_tansc.trim());
			admin.action().CreateWorkflow_AddOrganization(OrganizationName);
			admin.action().CreateWorkflow_AddSteps(1,StepName_transc, StepFromDropdown_transc);
			admin.action().CreateWorkflow_AddSteps_multi(2,StepName_trans, StepFromDropdown_trans);
			admin.action().CreateWorkflow_AddSteps_multi(3,StepName_review, StepFromDropdown_review);
	        admin.action().CreateWorkflow();
			Thread.sleep(1000);
			admin.action().SearchWorkflow(WorkflowName_tansc);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_tansc), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			//Create Organization for transcription
		   Thread.sleep(2000);
		   admin.action().Navigate(menu_org);
		   Thread.sleep(3000);
		   admin.action().CreateOrganization_Step1(OrganizationName_transc, description_org_transc);
		   admin.action().CreateOrganization_AddParentOrg(ParentOrganizationName);
		   admin.action().CreateOrganization_AddWorkflow(WorkflowName);
		   admin.action().CreateOrganization_AddUser(User);
		   admin.action().CreateOrganization_last();
			Thread.sleep(3000);
			
			//Create group for transcription
			admin.action().Navigate(menu_groups);
			admin.action().CreateGroup_Step1(GroupName_transc, description_grp_transc);
			Thread.sleep(1000);
			admin.action().CreateGroup_AddOrg(OrganizationName_transc);
			Thread.sleep(1000);
			admin.action().CreateGroup_AddUser(User);
			Thread.sleep(1000);
			admin.action().CreateGroup_last();
			Thread.sleep(2000);
		    General.action().logoutMethod();

			// PM login
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
		    CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName_transc, WorkflowName_tansc, "Transc",TranscriptionGroupName, "trans", TranslatorGroupName, true,"review",ReviewerGroupName,true);
			System.out.println("filePath------>" + filePath);
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
		
		   PM_user.action().Navigate(menu_AllJobs);
		   Thread.sleep(2000);
		   PM_user.action().SearchSubmisson(SubmissionName);
		   Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public void CreateSubmisson_Step2_OrganizationAndWorkflow(String OrganizationName,String WorkflowName,String TranscStep,String TranscGroupName,String TransStep,String TransGroupName,Boolean Trans,String reviewStep,String ReviewGroupName,Boolean Review) throws Exception {
    	System.out.println("INSIDE method CreateSubmisson_Step2_OrganizationAndWorkflow()\n"); 
    
    	Thread.sleep(1000);
    	PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
    	Thread.sleep(1000);
    	PM_user.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
        Thread.sleep(2000);
    	PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown);
    	Thread.sleep(2000);
    	PM_user.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown,Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown(WorkflowName));
    	Thread.sleep(2000);
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(TranscStep));
    	Thread.sleep(2000);
    	PM_user.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(TranscStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_trans(TranscGroupName));
    	Thread.sleep(2000);
    	
    	if(Trans){//Trans
    		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(TransStep));
    		Thread.sleep(1000);
    		PM_user.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(TransStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_review(TransGroupName));
	    	Thread.sleep(1000);
    	}
    	
    	if(Review){
    		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep));
    		Thread.sleep(1000);
    		PM_user.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_review(ReviewGroupName));
	    	Thread.sleep(1000);
    	}
    	Thread.sleep(2000);

    	
    	PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		Thread.sleep(1000);
    	
	    System.out.println("EOM CreateSubmisson_Step2_OrganizationAndWorkflow()\n");
    }
    
    

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
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


