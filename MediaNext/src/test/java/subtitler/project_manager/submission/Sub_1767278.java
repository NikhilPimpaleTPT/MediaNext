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
import locators.OrganizationLocators;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import modules.PM_user;
import modules.Verify;
import modules.admin;

/**
 * 
 * @author pvaidya
 *Summary: This test case verifies that modified org name is showing for previously created submissions.
 *PreconditionsCreate some submissions in a organization.
 */

public class Sub_1767278 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1767278"+CommonElements.BROWSER+"W2";
	String OrganizationName="Organization_1767278"+CommonElements.BROWSER+"W2";
	String Workflow="Workflow_1767278"+CommonElements.BROWSER+"W2";
	String GroupName="Group_1767278"+CommonElements.BROWSER+"W2";
	String description="Organization_Discription_1767278"+CommonElements.BROWSER+"W2";
	String WorkflowName[]= {"One_Step_Workflow","Two_Step_Workflow"};
	String OrganizationName_updated="Organization_Updated_1767278"+CommonElements.BROWSER+"W2";
	String description_updated="Organization_Discription_Updated_1767278"+CommonElements.BROWSER+"W2";
	String User[] = {"Subtitler_PM","admin"};
		String fileDirName = "common";
		String menu_Submission = "Submissions";
		String menu="Clients";
		String ParentOrganizationName = "TransPerfect";
		String targetlanguage = "German (Germany)";
		String menu_AllJobs = "Jobs";
		String menu_to_claim = "To Claim";
		String menu_ongoing = "Ongoing";
		String menu_completed = "Completed";
		String durationColor_review;
		Boolean assertion = true;
		
		
		@BeforeMethod
		public void setup() throws Exception {
			General.action().startSystem("SUB_1767278");
			dataSet.put("TL_test_case_description", "SUB-1767278: :Modified org name is showing for previously created submissions");
			dataSet.put("TL_internal_testCase_ID", "1767278");
		}

		@Test
		public void execute() throws Exception {
			testCase(dataSet);
			assertion();
		}

		public void testCase(LinkedHashMap<String, String> dataSet)
				throws Exception {
			try {
				//Admin login
				admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
				Thread.sleep(2000);
				admin.action().Navigate(menu);
				admin.action().CreateOrganization_Step1(OrganizationName, description);
				admin.action().CreateOrganization_AddParentOrg(ParentOrganizationName);
				admin.action().CreateOrganization_AddWorkflow(WorkflowName);
				admin.action().CreateOrganization_AddUser(User);
				admin.action().CreateOrganization_last();
				Thread.sleep(3000);
				admin.action().SearchOrganization(OrganizationName);
				Thread.sleep(2000);
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				
				admin.action().Navigate("Workflows");		

				//Create a Two Step Workflow
				admin.action().CreateWorkflow_Step1(Workflow);
				admin.action().CreateWorkflow_AddOrganization(OrganizationName);
				admin.action().CreateWorkflow_AddSteps(1,"trans", "Translation");
				admin.action().CreateWorkflow();
				
				
				//Navigate To Groups	
				admin.action().Navigate("Groups");
			
		        //Create a Group		
				admin.action().CreateGroup_Step1(GroupName, " group for Subtitler Submission");
				admin.action().CreateGroup_AddOrg(OrganizationName);
				admin.action().CreateGroup_AddUser(User);
				admin.action().CreateGroup_last();
				
				General.action().logoutMethod();
				
				General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
				PM_user.action().Navigate(menu_Submission);
				// TODO NEW IMPL OF SUBMISSION CREATION
				PM_user.action().CreateSubmisson_Step1("17", "35", "1", "8");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, Workflow, "trans", GroupName, "review", "", false);
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
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_client(SubmissionName,OrganizationName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
					
				}
				
				General.action().logoutMethod();
				
				admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
				Thread.sleep(2000);
				admin.action().Navigate(menu);
				Thread.sleep(2000);
				admin.action().SearchOrganization(OrganizationName);
				Thread.sleep(2000);
				admin.action().EditOrganization_open(OrganizationName);
				Thread.sleep(2000);
				admin.action().EditOrganization_Step1(OrganizationName_updated, description_updated);
				Thread.sleep(3000);
				admin.action().CreateOrganization_last();
				Thread.sleep(3000);
				General.action().logoutMethod();
				
				General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
				PM_user.action().Navigate(menu_Submission);
				PM_user.action().SearchSubmisson(SubmissionName);
				Thread.sleep(2000);

				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_client(SubmissionName,OrganizationName_updated), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying updated org name");
					
				}
				
				
				
			} catch (Exception e) {
		         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	            }
	  }
		
		
		
public void assertion() throws Exception {
			
	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_client(SubmissionName,OrganizationName_updated), 5);
	if (assertion == false) {
		report("f","Assertion failed while verifying updated org name");
		
	}
		    else {
					report("p", "All assertions are passsed ");
				}
		}

		@AfterMethod
		public void tearDown() throws Exception {
		General.action().stopsystem();
		}

		public void report(String result, String notes) throws Exception
		{
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild,result,Subtitler_TestRail_Common_Properties.assignedTo,notes);
		if(result == "f")
			assertTrue(false);

		}


		}

