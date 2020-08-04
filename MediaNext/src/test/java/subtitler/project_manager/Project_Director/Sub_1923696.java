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
 *Summary:Information for PD vs Media.Next submissions views are saved separately
 *
 */

public class Sub_1923696 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String SubmissionName_MediaNext = "SUB_1923696_MediaNext"+CommonElements.BROWSER+"G1";
	String SubmissionName_PD = "SUB_1923696_PD"+CommonElements.BROWSER+"G1";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_jobs = "Jobs";
	String submissionID;
	File filepath;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1923696");
		dataSet.put("TL_test_case_description", "UI of Edited PD submission");
		dataSet.put("TL_internal_testCase_ID", "1923696");
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
			    Thread.sleep(4000);
			    
			    //Verify that Media.NEXT is the default and first tab displayed when clicking on Submission the first time. SUB-1446
			    PM_user.action().Navigate(menu_Submission);
			    Thread.sleep(5000);
			    assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().Pm_User_mediaNext_Tab_active, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Media.Next Tab ");
					
				}
			    
			    //Create Submission In PD
				PM_user.action().Navigate(menu_Submission);
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow("Subtitle_Common_orgs", WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, false);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName_MediaNext,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				Thread.sleep(2000);
				PM_user.action().SearchSubmisson(SubmissionName_MediaNext);
				Thread.sleep(2000);
				
				// TODO NEW IMPL OF SUBMISSION CREATION
				PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName_PD);
				PD_PM_user.action().Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName_PD,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
				PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				
				//Verify created submission in pending status
				PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName_PD);
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_PD), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
					
				}
				General.action().logoutMethod();
				
				General.action().login(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
			    Thread.sleep(10000);
			    General.action().driver.navigate().refresh();
			    Thread.sleep(2000);
			    
			    PM_user.action().Navigate(menu_Submission);
			    Thread.sleep(5000);
			    //Verify that last selected tab is saved, so that user is redirected to the correct tab when moving back to the submissions page.
			    assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab_active, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Media.Next Tab ");
					
				}
				
				//Verify that filtering information is stored and preserved for each type of submission.
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_MEDIANext_Tab);
				Thread.sleep(2000);
				
				Thread.sleep(2000);
				PM_user.action().SearchSubmisson(SubmissionName_MediaNext);
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_MediaNext), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
					
				}
				
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
				Thread.sleep(2000);
				
				PD_PM_user.action().SearchMultiSubmissons_andCheck(SubmissionName_PD);
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_PD), 5);
				if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				}
				
				PM_user.action().Navigate(menu_jobs);
				Thread.sleep(2000);
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementNotPresent(PD_Pm_User_Submission_Locators.Locator().Pm_User_mediaNext_Tab_active, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Media.Next Tab ");
						
				}
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab_active, 5);
				if (assertion == false) {
						report("f","Assertion failed while verifying Media.Next Tab ");
						
				}
				
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().filters_clearFilterButton);
	    		Thread.sleep(4000);
				
	    		General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_clientFilter_input);
	    		Thread.sleep(4000);
	    		General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_clientFilter_option("TransPerfect"));
	    		Thread.sleep(4000);
				
	    		General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_statusFilter_input);
	    		Thread.sleep(4000);
	    		General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_StatusFilter_option("Pending"));
	    		Thread.sleep(4000);
				
	    		General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_sourceFilter_input);
	    		Thread.sleep(4000);
	    		General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_SourceFilter_otion("en-US"));
	    		Thread.sleep(4000);
	    		
	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		    	Thread.sleep(1000);	
		    	
		    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		    	Thread.sleep(1000);	
		    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName_PD);
		    	Thread.sleep(4000);
		 
		    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		    	Thread.sleep(1000);	
	    		
				
	    		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_PD), 5);
				if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				}
				
				PM_user.action().Navigate(menu_jobs);
				Thread.sleep(2000);
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				
	    		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_PD), 5);
				if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				}
				

		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
          }
}
	
	
	
	public void assertion() throws Exception {
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_PD), 5);
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