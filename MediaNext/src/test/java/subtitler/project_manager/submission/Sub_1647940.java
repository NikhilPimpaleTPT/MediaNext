package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
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
import modules.admin;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: Filter submissions of a org which has a + plus in its name.
 *Preconditions:Create a organization "P+T networks" and create some submission for this org.
 */ 

public class Sub_1647940 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	Boolean assertion = true;
	
	String OrganizationName = "P+T networks"+CommonElements.BROWSER+"R2";
    String WorkflowName []= {"One_Step_Workflow","Two_Step_Workflow"};
	String description_org = "Description for P+T networks"+CommonElements.BROWSER+"R2";
	String ParentOrganizationName = "TransPerfect";
	String User[] = {"admin","Subtitler_PM","Subtitler_Translator_1","Subtitler_Reviewer_1"};
	 
	String GroupName = "Group_1647940"+CommonElements.BROWSER+"R2";
	String description_group = "Description for Group 1647940"+CommonElements.BROWSER+"R2";
	String menu_Groups="Groups";
	String menu_Organizations="Clients";
	
	String SubmissionName = "P+T SUB_1647940"+CommonElements.BROWSER+"R2";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String fileName = "common";
	String menu_submission = "Submissions";
	String WorkflowName_Sub="One_Step_Workflow";
	String path;
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1647940");
		dataSet.put("TL_test_case_description", "SUB-1647940:Filter submissions of a org that has a + plus in its name.");
		dataSet.put("TL_internal_testCase_ID", "1647940");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			//Create orgnization and group
			
			admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			
			admin.action().Navigate(menu_Organizations);
			admin.action().CreateOrganization_Step1(OrganizationName, description_org);
			admin.action().CreateOrganization_AddParentOrg(ParentOrganizationName);
			admin.action().CreateOrganization_AddWorkflow(WorkflowName);
			
			admin.action().CreateOrganization_AddUser(User);
			admin.action().CreateOrganization_last();
			Thread.sleep(3000);
			admin.action().SearchOrganization(OrganizationName);
			Thread.sleep(2000);
			
			
			
			admin.action().Navigate(menu_Groups);
			admin.action().CreateGroup_Step1(GroupName, description_group);
			Thread.sleep(1000);
			admin.action().CreateGroup_AddOrg(OrganizationName);
			Thread.sleep(1000);
			admin.action().CreateGroup_AddUser(User);
			Thread.sleep(1000);
			admin.action().CreateGroup_last();
			Thread.sleep(2000);
			admin.action().SearchGroup(GroupName);
			Thread.sleep(1000);
			
			General.action().logoutMethod();
			
			//Create Submission using same org and group
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName_Sub, "trans", GroupName, "review", "", false);
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
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Submission_org(SubmissionName,OrganizationName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName and orgnization");
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
