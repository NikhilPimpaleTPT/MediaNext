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
import locators.Pm_User_Submission_Locators;
import modules.PD_PM_user;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:Clone a PD submission
 *Preconditions :Some PD submissions should be created before test.
 *
 */

public class Sub_1923689 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName_PD = "SUB_1923689"+CommonElements.BROWSER+"A3";
	String SubmissionName_PD_2 = "SUB_1923689_2"+CommonElements.BROWSER+"A3";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String menu_jobs = "Jobs";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1923689");
		dataSet.put("TL_test_case_description", "Clone a PD submission");
		dataSet.put("TL_internal_testCase_ID", "1923689");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			 // login using PM Credentials 
		    General.action().login(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
		    Thread.sleep(5000);
		   
			//Create Submission In From PD Tab
		    PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName_PD);
			PD_PM_user.action().Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName_PD,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			String creationDate=PD_PM_user.action().GetDataPlus_formate(Integer.valueOf(CommonElements.action().DATE_OFFSET));
			System.out.println("creationDate :"+ creationDate);
			PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
			PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			
			//Verify created submission in pending status
			PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName_PD);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_PD), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				
			}
			
			Thread.sleep(2000);
			PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_clone_button);
			Thread.sleep(8000);
			
			System.out.println("Instance-->"+General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instance_selectedOption).getText());
			assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instance_selectedOption).getText().contains(CommonElements.action().INSTANCE);
			if (assertion == false) {
			report("f","Assertion failed while verifying instance of clone submission");
	        }
			
			System.out.println("department-->"+General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_department).getAttribute("value"));
			assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_department).getAttribute("value").contains(CommonElements.action().DEPARTMENT);
			if (assertion == false) {
			report("f","Assertion failed while verifying department of clone submission");
	        }
			
			System.out.println("workflow-->"+General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_workflow).getAttribute("value"));
			assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_workflow).getAttribute("value").contains(CommonElements.action().WORKFLOW);
			if (assertion == false) {
			report("f","Assertion failed while verifying workflow of clone submission");
	        }
			
			System.out.println("client-->"+General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_client).getAttribute("value"));
			assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_client).getAttribute("value").contains(CommonElements.action().CLIENT);
			if (assertion == false) {
			report("f","Assertion failed while verifying client of clone submission");
	        }
			
			System.out.println("jobNumber-->"+General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_jobNo).getAttribute("value"));
			assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_jobNo).getAttribute("value").contains(SubmissionName_PD);
			if (assertion == false) {
			report("f","Assertion failed while verifying jobNumber of clone submission");
	        }
			
			PD_PM_user.action().mouseOver(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
      	    Thread.sleep(1000);
      	    PD_PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
	    	Thread.sleep(3000);
	    	
	    	
	    	System.out.println("date-->"+General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input).getAttribute("value"));
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input).getAttribute("value").contains(creationDate);
			if (assertion == false) {
			report("f","Assertion failed while verifying date");
			}
			
			System.out.println("submission name-->"+General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input).getAttribute("value"));
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input).getAttribute("value").contains(SubmissionName_PD);
			if (assertion == false) {
			report("f","Assertion failed while verifying submission name ");
			}
			
			System.out.println("submission name-->"+General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization).getText());
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization).getText().contains(OrganizationName);
			if (assertion == false) {
			report("f","Assertion failed while verifying submission name ");
			}
			
			System.out.println("submission name-->"+General.driver.findElement(Pm_User_Submission_Locators.Locator().m_user_CreateSubmission_SourceLanguage_selectedValue).getText());
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().m_user_CreateSubmission_SourceLanguage_selectedValue).getText().contains("en-US - English (United States)");
			if (assertion == false) {
			report("f","Assertion failed while verifying submission source language ");
			}
			System.out.println("submission name-->"+General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_selectedValue).getText());
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_selectedValue).getText().contains("de-DE - German (Germany)");
			if (assertion == false) {
			report("f","Assertion failed while verifying submission target language ");
			}
			
	    	Thread.sleep(3000);
	    	PD_PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input);
			Thread.sleep(1000);
			PD_PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input, SubmissionName_PD_2);
	    	Thread.sleep(3000);
			
			
			PD_PM_user.action().mouseOver(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
      	    Thread.sleep(1000);
      	    PD_PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
	    	Thread.sleep(3000);
	    	
	    	PD_PM_user.action().mouseOver(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
      	    Thread.sleep(1000);
      	    PD_PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
	    	Thread.sleep(3000);
	    	
	    	System.out.println("submission name-->"+General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID).getAttribute("value"));
			assertion = !General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID).getAttribute("value").contains(CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			if (assertion == false) {
			report("f","Assertion failed while verifying submission name ");
			}
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID);
			Thread.sleep(2000);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID, CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_SearchBtn);
			Thread.sleep(15000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_fileFirstRow(CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID));
			Thread.sleep(2000);
			
			System.out.println("submission name-->"+General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input).getAttribute("value"));
			assertion = !General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input).getAttribute("value").contains("jellies.srt");
			if (assertion == false) {
			report("f","Assertion failed while verifying submission name ");
			}
			
			PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			Thread.sleep(2000);
	    	
	    	PM_user.action().Navigate(menu_jobs);
	    	Thread.sleep(3000);
	    	PM_user.action().Navigate(menu_Submission);
	    	Thread.sleep(3000);
            PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName_PD_2);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_PD_2), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				
			}
	    	
	    	
			
		} catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
        }
	}


	
	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_PD_2), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
			
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

	
	


