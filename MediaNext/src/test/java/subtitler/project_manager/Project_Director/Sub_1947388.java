package subtitler.project_manager.Project_Director;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.PD_Pm_User_Submission_Locators;
import modules.PD_PM_user;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:Error handling in case of error when submission is created in PD.
 *
 */

public class Sub_1947388 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String ProjectName="PRO_1947388"+CommonElements.BROWSER+"Q1";
	String SubmissionName = "SUB_1947388"+CommonElements.BROWSER+"Q1";
	String OrganizationName = "TransPerfect";
	String WorkflowName = "One_Step_Workflow";
    String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1947388");
		dataSet.put("TL_test_case_description", "Error handling in case of error when submission is created in PD");
		dataSet.put("TL_internal_testCase_ID", "1947388");
	}
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			    // login using PM Crdentials 
			    Thread.sleep(2000);
			    PD_PM_user.action().Open_PD_Instance_URL_Admin();
			    Thread.sleep(15000);
			    
			    PD_PM_user.action().admin_login("admin", "password1!");
			    Thread.sleep(2000);
			    PD_PM_user.action().search_PD_project_andClick("Project Name","GLPlay_Test_Project");
			    Thread.sleep(2000);
			    PD_PM_user.action().copy_project(ProjectName);
			    Thread.sleep(2000);
			    General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_organizations);
			    Thread.sleep(2000);
			    General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_organizations);
				Thread.sleep(2000);
			    PD_PM_user.action().search_PD_project_andClick("Project Name",ProjectName);
			    Thread.sleep(2000);
			    
			    assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_searchProject_firstRow(ProjectName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying enable project pop up");
				}
				
				
				//Switch to the pop up window 0
				 PD_PM_user.action().switchToPopupWindow(0);
				 Thread.sleep(8000);
				 
				 General.action().login(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
				 Thread.sleep(4000);
					
				 General.action().driver.navigate().refresh();
				 Thread.sleep(10000);
				 PM_user.action().Navigate(menu_Submission);
				 Thread.sleep(2000);
					
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
				 Thread.sleep(2000);
				 PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, ProjectName, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName);
				 PD_PM_user.action().Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				 PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
				 PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				 PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				 Thread.sleep(2000);
				 PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				 Thread.sleep(2000);
				 assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
				 if (assertion == false) {
				 report("f", "Assertion failed while verifying SubmissionName  after Search");
				 }
				 
				 Thread.sleep(4000);
				 PD_PM_user.action().switchToPopupWindow(1);
				 Thread.sleep(8000);
				 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_organizations);
				 Thread.sleep(2000);
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_organizations);
				 Thread.sleep(2000);
				 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_projectTab);
				 Thread.sleep(2000);
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_projectTab);
				 Thread.sleep(2000);
				 PD_PM_user.action().search_PD_project_andClick("Project Name",ProjectName);
				 Thread.sleep(2000);
				 
				 Thread.sleep(4000);
			     General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_project_enableDisable_button);
			     Thread.sleep(1000);
			     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_project_enableDisable_button);
			     Thread.sleep(5000);
					
			     assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_project_disablePopUp, 5);
				 if (assertion == false) {
						report("f","Assertion failed while verifying enable project pop up");
				 }
					
					
				 Thread.sleep(4000);
				 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_project_disablePopUp_continueButton);
				 Thread.sleep(1000);
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_project_disablePopUp_continueButton);
				 Thread.sleep(5000);
				
				 //Switch to the pop up window 0
				 PD_PM_user.action().switchToPopupWindow(0);
				 Thread.sleep(8000);
				 General.action().driver.navigate().refresh();
				 Thread.sleep(10000);
				 PM_user.action().Navigate(menu_Submission);
				 Thread.sleep(2000);
					
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
				 Thread.sleep(2000);
				 
				 //Search and check submission and Verify for CRETE IN PD BUTTON
			     PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				 Thread.sleep(2000);
				 PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button);
				 Thread.sleep(2000);
					
				 PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_createSubmissionInPDButton);
				
				
		} catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
         }
	}
public void assertion() throws Exception {
	 //If error occurs then user can retry creation.
	assertion = Verify.action().verifyTextPresent(" disabled!", 5);
	if(assertion==false) {
		report("f","Assertion failed while verifying PM user can not create admin user");
 }
	else {
		report("p", "All assertions passed");
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
