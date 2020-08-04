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
import locators.Pm_User_Submission_Locators;
import modules.PD_PM_user;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:Actions available upon submissions selection
 *
 */

public class Sub_1923698 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String SubmissionName = "SUB_1923698"+CommonElements.BROWSER+"A2_";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_jobs = "Jobs";
	String submissionID="187";
	File filepath;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1923698");
		dataSet.put("TL_test_case_description", "Actions available upon submissions selection");
		dataSet.put("TL_internal_testCase_ID", "1923698");
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
			    General.action().login(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
			    Thread.sleep(20000);
			    
			    //Create Submission In PD
				PM_user.action().Navigate(menu_Submission);
				// TODO NEW IMPL OF SUBMISSION CREATION
				for(int i=1;i<=2;i++) {
					PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName+i);
					PD_PM_user.action().Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName+i,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
					PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
					PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
					PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				}
				//Verify created submission in pending status
				PD_PM_user.action().SearchSubmisson(SubmissionName+1);
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+1), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
					
				}
				
				//Verify created submission in pending status
				PD_PM_user.action().SearchSubmisson(SubmissionName+2);
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+2), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
					
				}
				PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName+1);
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName edit button");
					
				}
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_edit_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName edit button");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_clone_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName clone button");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_delete_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName delete button");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName download button");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_compare_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName compare button");
					
				}
				
				PM_user.action().Navigate(menu_jobs);
				Thread.sleep(2000);
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
				Thread.sleep(2000);
				
				PD_PM_user.action().SearchMultiSubmissons_andCheck(SubmissionName);
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button, 5);
				if (assertion ==false) {
					report("f","Assertion failed while verifying SubmissionName edit button");
					
				}
				
				assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_edit_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName edit button");
					
				}
				
				

				PM_user.action().Navigate(menu_jobs);
				Thread.sleep(2000);
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
				Thread.sleep(2000);
				//Search and check submission and Verify for CRETE IN PD BUTTON
				PM_user.action().SearchSubmisson_andCheck(SubmissionName+1);
				Thread.sleep(2000);
				Thread.sleep(1000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_createSubmissionInPDButton);
				Thread.sleep(2000);
				
				//Search and check submission and Verify for CRETE IN PD BUTTON
				PM_user.action().SearchSubmisson_andCheck(SubmissionName+2);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_createSubmissionInPDButton);
				Thread.sleep(2000);
				
                PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName+1);
				
				assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName edit button");
					
				}
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_edit_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName edit button");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_clone_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName clone button");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_delete_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName delete button");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName download button");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_compare_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName compare button");
					
				}
				PM_user.action().Navigate(menu_jobs);
				Thread.sleep(2000);
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
				Thread.sleep(2000);
				
				PD_PM_user.action().SearchMultiSubmissons_andCheck(SubmissionName);
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName edit button");
					
				}
				
				assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_edit_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName edit button");
					
				}
				assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_clone_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName clone button");
					
				}
				assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_delete_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName delete button");
					
				}
				assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName download button");
					
				}
				assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_compare_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName compare button");
					
				}
				
				
				
		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
           }
 }
	
	
	
	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_compare_button, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName compare button");
			
		}else {
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
