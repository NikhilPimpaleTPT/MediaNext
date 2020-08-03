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
import locators.RoleLocators;
import modules.PM_user;
import modules.Verify;
import modules.admin;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary:This test case is to verify if we can delete MediaNext submissions in any status
 *
 */

public class Sub_2671808 {

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName1 = "Submission_2671808"+CommonElements.BROWSER+"B1";
	String SubmissionName2 = "Submission_2671808"+CommonElements.BROWSER+"B2";
	String SubmissionName3 = "Submission_2671808"+CommonElements.BROWSER+"B3";
	
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String RoleName_PM="Role_PM";
	String Roles_permission_View[] = {"Manage Submissions","Manage Server"};
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
    
    Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2671808");
		dataSet.put("TL_test_case_description", "SUB-2671808:Delete a Media.NEXT type submission in any status");
		dataSet.put("TL_internal_testCase_ID", "2671808");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			 // Checked Manage Submissions if unchecked.
			 General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			 Thread.sleep(2000);
	
			 admin.action().Navigate("Roles");
			 Thread.sleep(1000);
			 admin.action().SearchRole(RoleName_PM);
			 Thread.sleep(1000);
			 admin.action().EditRole_open(RoleName_PM);
			 
			 if(Verify.action().verifyElementPresent(RoleLocators.Locator().Manage_role_checked(Roles_permission_View[0]),5)) {
			 Thread.sleep(2000);
			 System.out.println("Manage Submissions permission is checked");
			 }else {
			 System.out.println("Manage Submissions permission is UnChecked");
			 Thread.sleep(2000);
			 Create_AND_EditRole_Permission_View(Roles_permission_View[0],true);
			 Thread.sleep(2000);
			 }
			 admin.action().Create_And_SaveRole_CreateStep();
			 Thread.sleep(2000);
			 admin.action().Navigate("Roles");
			 Thread.sleep(1000);
			 admin.action().SearchRole(RoleName_PM);
			 Thread.sleep(1000);
			 admin.action().EditRole_open(RoleName_PM);
			 
			 if(Verify.action().verifyElementPresent(RoleLocators.Locator().Manage_role_checked(Roles_permission_View[1]),5)) {
			 Thread.sleep(2000);
			 System.out.println("Manage server permission is checked");
			 }else {
			 System.out.println("Manage server permission is UnChecked");
			 Thread.sleep(2000);
			 Create_AND_EditRole_Permission_View(Roles_permission_View[1],true);
			 Thread.sleep(2000);
			 }
			 admin.action().Create_And_SaveRole_CreateStep();
			 
			 Thread.sleep(2000);
			 General.action().logoutMethod();
			 
			 General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			 PM_user.action().Navigate(menu_submission);
			 //TODO NEW IMPL OF SUBMISSION CREATION
			 PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			 PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",	TranslatorGroupName, "review", "", false);
			 PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			 PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			 PM_user.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName1, CommonElements.action().COMMON_SOURCE_LANGUAGE, Targetlanguage);
			 Thread.sleep(2000);
			
			 //SEARCH SUBMISSION AND CHECK
			 PM_user.action().SearchSubmisson_andCheck(SubmissionName1);
			 Thread.sleep(2000);
			 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName1), 5);
			 if (assertion == false) {
			 report("f","Assertion failed while verifying SubmissionName after Search");
			 }
			 Thread.sleep(5000);
			 PM_user.action().Navigate(menu_to_claim);
			 Thread.sleep(2000);
			 PM_user.action().Navigate(menu_submission);
			 Thread.sleep(2000);
			 PM_user.action().deleteSubmisson(SubmissionName1);
			 Thread.sleep(2000);
			 General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			 Thread.sleep(2000);
			 General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName1);
		     Thread.sleep(4000);
			 Thread.sleep(5000);
			 PM_user.action().SearchSubmisson(SubmissionName1);
			 Thread.sleep(2000);
			 assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName1), 5);
			 if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			 }

			 PM_user.action().Navigate(menu_to_claim);
			 Thread.sleep(2000);
			 PM_user.action().Navigate(menu_submission);
			 //TODO NEW IMPL OF SUBMISSION CREATION
			 PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			 PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",	TranslatorGroupName, "review", "", false);
			 PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			 PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			 PM_user.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName2, CommonElements.action().COMMON_SOURCE_LANGUAGE, Targetlanguage);
			 Thread.sleep(2000);
			
			 //SEARCH SUBMISSION AND CHECK
			 PM_user.action().SearchSubmisson_andCheck(SubmissionName2);
			 Thread.sleep(2000);
			 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName2), 5);
			 if (assertion == false) {
			 report("f","Assertion failed while verifying SubmissionName after Search");
			 }
			 
			 PM_user.action().Navigate(menu_to_claim);
	         Thread.sleep(1000);
	         PM_user.action().PM_ToClaim(SubmissionName2);
	         Thread.sleep(1000);
	         PM_user.action().Navigate(menu_ongoing);
	         Thread.sleep(1000);
	         PM_user.action().PM_onGoing_submission(SubmissionName2, targetlanguage, false, true);
	         Thread.sleep(1000);
	         PM_user.action().Navigate(menu_submission);
	         Thread.sleep(1000);
	         PM_user.action().SearchSubmisson_andCheck(SubmissionName2);
			 Thread.sleep(2000);
			 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName2,"In_Progress"), 5);
			 if (assertion == false) {
			 report("f","Assertion failed while verifying SubmissionName after Search in inprogress status");
			 }
			 Thread.sleep(5000);
			 PM_user.action().Navigate(menu_to_claim);
			 Thread.sleep(2000);
			 PM_user.action().Navigate(menu_submission);
			 Thread.sleep(2000);
			 PM_user.action().deleteSubmisson(SubmissionName2);
			 Thread.sleep(2000);
			 General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			 Thread.sleep(2000);
			 General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName2);
		     Thread.sleep(4000);
			 Thread.sleep(5000);
			 PM_user.action().SearchSubmisson(SubmissionName2);
			 Thread.sleep(2000);
			 assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName2), 5);
			 if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			 }
			 
			 PM_user.action().Navigate(menu_submission);
			 //TODO NEW IMPL OF SUBMISSION CREATION
			 PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			 PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",	TranslatorGroupName, "review", "", false);
			 PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			 PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			 PM_user.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName3, CommonElements.action().COMMON_SOURCE_LANGUAGE, Targetlanguage);
			 Thread.sleep(2000);
			
			 //SEARCH SUBMISSION AND CHECK
			 PM_user.action().SearchSubmisson_andCheck(SubmissionName3);
			 Thread.sleep(2000);
			 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName3), 5);
			 if (assertion == false) {
			 report("f","Assertion failed while verifying SubmissionName after Search");
			 }
			 
			 PM_user.action().Navigate(menu_to_claim);
	         Thread.sleep(1000);
	         PM_user.action().PM_ToClaim(SubmissionName3);
	         Thread.sleep(1000);
	         PM_user.action().Navigate(menu_ongoing);
	         Thread.sleep(1000);
	         PM_user.action().PM_onGoing_submission(SubmissionName3, targetlanguage, true, false);
	         Thread.sleep(2000);
	         PM_user.action().Navigate(menu_submission);
	         Thread.sleep(1000);
	         PM_user.action().SearchSubmisson_andCheck(SubmissionName3);
			 Thread.sleep(2000);
			 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName3," Completed"), 5);
			 if (assertion == false) {
			 report("f","Assertion failed while verifying SubmissionName after Search in complete status");
			 }
			 
			 PM_user.action().Navigate(menu_to_claim);
			 Thread.sleep(2000);
			 PM_user.action().Navigate(menu_submission);
			 Thread.sleep(2000);
			 Thread.sleep(2000);
			 PM_user.action().deleteSubmisson(SubmissionName3);
			 Thread.sleep(2000);
			 General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			 Thread.sleep(2000);
			 General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName3);
		     Thread.sleep(4000);
			 Thread.sleep(5000);
			 PM_user.action().SearchSubmisson(SubmissionName3);
			 Thread.sleep(2000);
			 assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName3), 5);
			 if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			 }
	
	
		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
          }
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName3), 5);
		 if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
		 }else {
				report("p", "All assertion passed");
			}
	}
	
	
	public void Create_AND_EditRole_Permission_View(String ViewPriveleges_Role,Boolean booleanvalue) throws Exception{
		
		System.out.println("INSIDE METHOD Create_AND_EditRole_Permission_View");
		
		General.action().waitforelemenetpresent(RoleLocators.Locator().Manage_role(ViewPriveleges_Role));
		General.action().checkUncheck(RoleLocators.Locator().Manage_role(ViewPriveleges_Role), booleanvalue);
		Thread.sleep(2000);
		System.out.println("EOM Create_AND_EditRole_Permission_View()");
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
