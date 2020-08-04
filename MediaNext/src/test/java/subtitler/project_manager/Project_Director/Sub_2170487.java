package subtitler.project_manager.Project_Director;

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
import locators.PD_Pm_User_Submission_Locators;
import modules.PD_PM_user;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:to verify disabled PD projects are not showing under create PD submission in Medianext
 *
 */

public class Sub_2170487 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String ProjectName="PRO_2170487"+CommonElements.BROWSER+"A4";
	String SubmissionName = "SUB_2170487"+CommonElements.BROWSER+"A4";
	String menu_Submission = "Submissions";
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2170487");
		dataSet.put("TL_test_case_description", "Disabled PD projects should not be displayed in create submission tab for PD submissions");
		dataSet.put("TL_internal_testCase_ID", "2170487");
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
					
				 Thread.sleep(2000);
				 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_User_New_PD_Submission_Button);
				 Thread.sleep(1000);
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_New_PD_Submission_Button);
				 Thread.sleep(1000);
					
				 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instance);
				 Thread.sleep(1000);
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instance);
				 Thread.sleep(1000);
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instanceDropDownOptions(CommonElements.action().INSTANCE));
				 Thread.sleep(5000);
				 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_department);
				 Thread.sleep(1000);
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_department);
				 Thread.sleep(1000);
				
				 assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_departmentDropDownOptions(ProjectName), 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying enable project pop up");
				 }
				
				 Thread.sleep(4000);
				 PD_PM_user.action().switchToPopupWindow(1);
				 Thread.sleep(8000);
				 
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_organizations);
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
					
				 Thread.sleep(2000);
				 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_User_New_PD_Submission_Button);
				 Thread.sleep(1000);
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_New_PD_Submission_Button);
				 Thread.sleep(1000);
					
				 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instance);
				 Thread.sleep(1000);
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instance);
				 Thread.sleep(1000);
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instanceDropDownOptions(CommonElements.action().INSTANCE));
				 Thread.sleep(5000);
				 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_department);
				 Thread.sleep(1000);
				 General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_department);
				 Thread.sleep(1000);
				
				 
				 //verify if the disabled project is available - project should not be available in the list
				 assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_departmentDropDownOptions(ProjectName), 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying enable project pop up");
				 }
				
				
				

		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
          }
	}
public void assertion() throws Exception {
	 //verify if the disabled project is available - project should not be available in the list
	 assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_departmentDropDownOptions(ProjectName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying enable project pop up");
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

