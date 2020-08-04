package subtitler.project_manager.Project_Director;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.PD_Pm_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import modules.PD_PM_user;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:As a PM - mandatory attributes are always set when creating a new submission in PD
 *
 */

public class Sub_2432944 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	String SubmissionName = "SUB_2432944"+CommonElements.BROWSER+"A1";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_completed = "Completed";
	
	
	String submissionID;
	File filepath;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2432944");
		dataSet.put("TL_test_case_description", "As a PM - mandatory attributes are always set when creating a new submission in PD");
		dataSet.put("TL_internal_testCase_ID", "2432944");
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
			    Thread.sleep(4000);
			    //Create Submission In PD
				PM_user.action().Navigate(menu_Submission);
				// TODO NEW IMPL OF SUBMISSION CREATION
				PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName);
				PD_PM_user.action().Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
				PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				
				//Search and check submission and Verify for CRETE IN PD BUTTON
				PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button);
				Thread.sleep(2000);
				
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_createSubmissionInPDButton);
				Thread.sleep(2000);
				
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				
				//Verify Submission creation and due date
				PD_PM_user.action().SearchSubmisson(SubmissionName);
				Thread.sleep(2000);
				
				//Verify Submission status after creating 
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName,"New"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName status after Search");
				}
				
				submissionID =General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(1,3)).getText();
				System.out.println("Submission ID:"+submissionID);
				
				Thread.sleep(2000);
				PD_PM_user.action().Open_PD_Instance_URL();
				Thread.sleep(25000);
				
				PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				
				General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
		    	Thread.sleep(2000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
		    	Thread.sleep(2000);
		    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission);
		    	Thread.sleep(3000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission);
		    	Thread.sleep(5000);
		    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_customAttributes);
		    	Thread.sleep(3000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_customAttributes);
		    	Thread.sleep(5000);
		    	
		    	
		    	//Verify SubjectMatter , SubCategory , RecreatedPDF
		    	assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().PD_submission_customAttributes_customFields("SubjectMatter")).getAttribute("value").contains("Media");
					if (assertion == false) {
						report("f","Assertion failed while verifying custom attributes custom attributes value ");
				}
				assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().PD_submission_customAttributes_customFields("SubCategory")).getAttribute("value").contains("Corporate");
				if (assertion == false) {
						report("f","Assertion failed while verifying custom attributes custom attributes value ");
				}
				assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().PD_submission_customAttributes_customFields("RecreatedPDF")).getAttribute("value").contains("N/A");
				if (assertion == false) {
						report("f","Assertion failed while verifying custom attributes custom attributes value ");
				}
				
				
				//Enter the values for other mandatory custom attributes
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_subjectCategory_framesBetweenSubs_input);
		    	Thread.sleep(2000);
				General.action().ClearInputvalues(PD_Pm_User_Submission_Locators.Locator().PD_submission_subjectCategory_framesBetweenSubs_input);
				Thread.sleep(2000);
				General.action().type(PD_Pm_User_Submission_Locators.Locator().PD_submission_subjectCategory_framesBetweenSubs_input,"5");
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_customAttributes_okButton);
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission_saveButton);
				Thread.sleep(2000);
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_refreshButton);
				Thread.sleep(2000);
				
				General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_onHoldButton);
		    	Thread.sleep(2000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_onHoldButton);
		    	Thread.sleep(2000);
				General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
		    	Thread.sleep(2000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
		    	Thread.sleep(2000);
				
				PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
		    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission);
		    	Thread.sleep(3000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission);
		    	Thread.sleep(5000);
		    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_customAttributes);
		    	Thread.sleep(3000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_customAttributes);
		    	Thread.sleep(5000);
		    	
		    	
		    	//Verify entered value
		    	assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().PD_submission_customAttributes_customFields("MinCountOfFramesBetweenSubs")).getAttribute("value").contains("5");
				if (assertion == false) {
					report("f","Assertion failed while verifying custom attributes custom attributes value ");
			}
		    	

		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
           }
	}
 public void assertion() throws Exception {
	 assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().PD_submission_customAttributes_customFields("MinCountOfFramesBetweenSubs")).getAttribute("value").contains("5");
		if (assertion == false) {
			report("f","Assertion failed while verifying custom attributes custom attributes value ");
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
