package subtitler.project.manager.Sort_Submissions;

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
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary: This testcase verifies if created submissions are sorted by video length in ascending/descending order.
 *Preconditions:Before test, user should create multiple number of submissions.
 */

public class Sub_1766490 {
	
	
	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName1 = "Sub_1766490"+CommonElements.BROWSER+"A1"+"smallVideo";
	String SubmissionName2 = "Sub_1766490"+CommonElements.BROWSER+"A1"+"largeVideo";
	String searchSubmission="Sub_1766490"+CommonElements.BROWSER+"A1";
	String sourcelanguage = "en-US - English (United States)";
	String targetlanguage = "de-DE - German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String menu = "Submissions";
	String menu_Ongoing = "Ongoing";
	String TranslatorGroupName = "Common_group";
	String dueDate_1stRow_descending;
	String smallVideoDuration_default="00:00:45";
	String largeVideoDuration_default="00:56:30";
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1766490");
		dataSet.put("TL_test_case_description","SUB-1766490:Sort by video length");
		dataSet.put("TL_internal_testCase_ID", "1766490");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {

			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu);
	
			//Create Submission for small video file
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName1,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			
			PM_user.action().Navigate(menu);
			//create submission for large video file
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_LARGE_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName2,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);

			PM_user.action().Navigate(menu);
			Thread.sleep(4000);
			//SEARCH SUBMISSION AND CHECK
			PM_user.action().SearchSubmisson(SubmissionName1);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName1), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			PM_user.action().Navigate(menu);
			Thread.sleep(4000);
			PM_user.action().SearchSubmisson(SubmissionName2);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName2), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			
			PM_user.action().Navigate(menu_Ongoing);
			Thread.sleep(4000);
			PM_user.action().Navigate(menu);
			Thread.sleep(4000);
	
			//Sort sub by video length in Ascending order for Submission Menu
			sortSubmission_video(searchSubmission, true);
		    Thread.sleep(3000);
		    
		    assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sorting_sub_with_video(1,SubmissionName1,smallVideoDuration_default), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Due Date after search");
				
			}
		    
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sorting_sub_with_video(2,SubmissionName2,largeVideoDuration_default), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Due Date after search");
				
			}
			
			
			
			//Sort sub by video length in descending order for Submission Menu
			sortSubmission_video(searchSubmission, false);
		    Thread.sleep(3000);
		    
		    assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sorting_sub_with_video(1,SubmissionName2,largeVideoDuration_default), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Due Date after search");
				
			}
		    
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sorting_sub_with_video(2,SubmissionName1,smallVideoDuration_default), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Due Date after search");
				
			}
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	

	  public void sortSubmission_video(String submissionName, boolean sort) throws Exception{
      	
      	System.out.println("INSIDE METHOD sortSubmission_video");
      	
      	
      	PM_user.action().SearchSubmisson(submissionName);
      	Thread.sleep(4000);
      	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
      	General.action().Click(Pm_User_Submission_Locators.Locator().Submission_videoColumn);
      	
      	//THIS FOR ASCENDING
      	
      	if(sort){
      		//This is for ASC
  			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().DescendingSubmission_status, 5)){
  				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_videoColumn);
  			}
  		}
      	else{
      		//This is for DESC
      		if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().AscendingSubmission_status, 5)){
  				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_DueDate);
  			}
  		}
  		Thread.sleep(3000);	
  		
  		System.out.println("EOM sortSubmission_video()");
  		}
	
			public void assertion() throws Exception {
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sorting_sub_with_video(2,SubmissionName1,smallVideoDuration_default), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Due Date after search");
					
				} else {
					report("p", "All Assertion passed.");
				}
			}
//
			@AfterMethod
			public void tearDown() throws Exception {
				General.action().stopsystem();
			}

			public void report(String result, String notes) throws Exception {
				TestRailClient.testRailReportByID_production(
						dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
				if (result == "f")
					assertTrue(false);
			}
		}
