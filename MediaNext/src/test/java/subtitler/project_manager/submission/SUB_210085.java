package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class SUB_210085 {

	Boolean assertion = true;
	Boolean user = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName1 = "SUB_210085_Re-Assignee_ToUser" + CommonElements.BROWSER + "FT";
	String SubmissionName2 = "SUB_210085_Re-Assignee_ToGroup" + CommonElements.BROWSER + "FT";
	
	String SubmissionName3 = "SUB_210085_Re-Assignee_ofSameOrg_Q1" + CommonElements.BROWSER + "A_FT";
	String SubmissionName4 = "SUB_210085_Re-Assignee_ofSameOrg_Q1" + CommonElements.BROWSER + "B_FT";
	
	String SubmissionName5 = "SUB_210085_Re-Assignee_ofDifferentOrg_Q1" + CommonElements.BROWSER + "A_FT";
	String SubmissionName6 = "SUB_210085_Re-Assignee_ofDifferentOrg_Q1" + CommonElements.BROWSER + "B_FT";
	String SubmissionName7="SUB_210085_Re-Assignee_Q1" + CommonElements.BROWSER + "C_FT";;
	String submissionForSearch_forSameOrg = "SUB_210085_Re-Assignee_ofSameOrg_Q1" + CommonElements.BROWSER;
	String submissionForSearch_forDifferentOrg = "SUB_210085_Re-Assignee_ofDifferentOrg_Q1" + CommonElements.BROWSER;
	
	String OrganizationName = "Subtitle_Common_orgs_multi";
	String OrganizationName2 = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_AllJobs = "Jobs";
	String TranslatorGroupName = "Common_group_multi1";
	String TranslatorGroupName2 = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String UserName1 = "Maya Gurnale";
	String UserName2 = "Admin";
	String GroupName = "Common_group_multi2";
	String GroupName1 = "Common_group";

	String menu_to_claim = "To Claim";
	String menu_completed = "Completed";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_210085");
		dataSet.put("TL_test_case_description", "SUB-210085:Re-assign Submission");
		dataSet.put("TL_internal_testCase_ID", "210085");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
		try {
			General.action().login(CommonElements.action().PM_username, CommonElements.action().password);
			Thread.sleep(20000);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION

			// Re-Assign by User...1

			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH + CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_APOSTROPHE_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName1,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName1);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(
					Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName1), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(2000);
			PM_user.action().PM_Submission_Reassignment(SubmissionName1, true, UserName1, false, GroupName);
			Thread.sleep(2000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(5000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName1);
			Thread.sleep(6000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_status_assignee("Claimed", "Maya Gurnale"), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying Status and Assignee  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson_andCheck(SubmissionName1);
			Thread.sleep(4000);
			assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying  Re-assign icon after Search")
				;
			}

			

			// Re-Assign by Group...1
			Thread.sleep(4000);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_LARGE_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH + CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_APOSTROPHE_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName2,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName2);
			Thread.sleep(4000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName2), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(1000);
			PM_user.action().PM_Submission_Reassignment(SubmissionName2, false, UserName1, true, GroupName);
			Thread.sleep(5000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(5000);
			PM_user.action().SearchSubmisson(SubmissionName2);
			Thread.sleep(4000);
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName2);
			Thread.sleep(6000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_status_assignee("Available", "Common_group_multi1"), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying Status and Assignee  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(2000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(5000);
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson_andCheck(SubmissionName2);
			Thread.sleep(15000);

			
			

		// Re-Assign by User...2 (Same Org)
			Thread.sleep(8000);
			PM_user.action().Navigate(menu_Submission);
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_LARGE_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH + CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_APOSTROPHE_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName3,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName3);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName3), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			
			
			// Re-Assign by User...2 (Same Org)
			Thread.sleep(4000);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_LARGE_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH + CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_APOSTROPHE_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName4,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName4);
			Thread.sleep(4000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName4), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			
			
			Thread.sleep(6000);
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(2000);
			PM_Submission_Reassignment(submissionForSearch_forSameOrg, true, UserName1, false, GroupName);
			Thread.sleep(5000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(5000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(submissionForSearch_forSameOrg);
			Thread.sleep(6000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_status_assignee_forSubmission(SubmissionName3,"Claimed", "Maya Gurnale"), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying Status and Assignee  after Search");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_status_assignee_forSubmission(SubmissionName4,"Claimed", "Maya Gurnale"), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying Status and Assignee  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(2000);
			SearchSubmisson_andCheck(submissionForSearch_forSameOrg);
			Thread.sleep(4000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment_multipleJobs, 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying  Re-assign icon after Search");
			}
			//TODO NOT NEEDED
			
			 // Thread.sleep(2000); PM_user.action().Navigate(menu_AllJobs);
			 //Thread.sleep(2000);
			 // PM_user.action().SearchSubmisson_andCheck(SubmissionName2);
			 //Thread.sleep(4000); assertion =
			 // Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator()
			 //.PM_AllJobs_Reassignment,5); if (assertion == false) {
			 // report("f","Assertion failed while verifying  Re-assign icon after Search");
			 //}
			 
			
			

			// Re-Assign by org...1
			Thread.sleep(15000);
			PM_user.action().Navigate(menu_Submission);
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_LARGE_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH + CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_APOSTROPHE_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName5,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(5000);
			PM_user.action().SearchSubmisson(SubmissionName5);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName5), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			

			// Re-Assign by Org...2
			Thread.sleep(4000);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName2, WorkflowName, "trans",TranslatorGroupName2, "", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_LARGE_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH + CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_APOSTROPHE_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName6,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName6);
			Thread.sleep(4000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName6), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(1000);
			PM_Submission_ReassignmentForDifferentOrg(submissionForSearch_forDifferentOrg, false, "", true, GroupName);
			Thread.sleep(9000);
       
			
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(9000);
			assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment_multipleJobs,5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying  Re-assign icon after Search");
			}
			
			PM_user.action().Navigate(menu_completed);
			Thread.sleep(9000);
			assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment_multipleJobs,5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying  Re-assign icon after Search");
			}
			
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(1000);
			
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_LARGE_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH + CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_APOSTROPHE_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName7,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName7);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(
					Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName7), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson_andCheck(SubmissionName7);
			Thread.sleep(4000);
			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment);
		    Thread.sleep(4000);
		    PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment);
			Thread.sleep(4000);
				
			if(user){
			System.out.println("INSIDE IF FOR USERS-----");
			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Users_radio_button);
		    Thread.sleep(1000);
		    PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Users_radio_button);
			Thread.sleep(1000);
			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Users);
			Thread.sleep(1000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Users);
			Thread.sleep(1000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_from_Users_dropdownWithIndex(UserName1,3),5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying  Re-assign icon after Search");
		    } 
		}
			
			

		} catch (Exception e) {
			report("f", "Execution level error was encountered.\n\nError log:\n\n" + Verify.action().getErrorBuffer(e));
		}

		
	}
	
	
	public void PM_Submission_Reassignment(String SubmissionName,boolean user, String UserName, boolean group,String GroupName) throws Exception {
		
		 System.out.println("INSIDE PM_Submission_Reassignment  method()");
		     
		    SearchSubmisson_andCheck(SubmissionName);
		    Thread.sleep(6000);
		    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment_multipleJobs);
			Thread.sleep(4000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment_multipleJobs);
			Thread.sleep(4000);
			
		    if(user){
		    	System.out.println("INSIDE IF FOR USERS-----");
		    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Users_radio_button);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Users_radio_button);
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Users);
				Thread.sleep(1000);
				General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Users, Pm_User_Submission_Locators.Locator().PM_Reassign_Job_from_Users_dropdown(UserName));
		    	Thread.sleep(2000);
		    	Thread.sleep(2000);
		    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
				Thread.sleep(2000);
				System.out.println("OUTSIDE IF FOR USERS-----");
			}
		    
		    if(group){
		    	System.out.println("INSIDE IF FOR GROUPS-----");
		    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Groups_radio_button);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Groups_radio_button);
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Groups);
				Thread.sleep(1000);
				General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Groups, Pm_User_Submission_Locators.Locator().PM_Reassign_Job_from_Groups_dropdown(GroupName));
		    	Thread.sleep(General.action().defaultWaitPeriod*10);
		    	Thread.sleep(2000);
		    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
				Thread.sleep(2000);
		      System.out.println("OUTSIDE IF FOR GROUPS-");
		    
			 System.out.println("EOM PM_Submission_Reassignment  method()");
}
		    Thread.sleep(1000);
		    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_allCheck);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
		    
	}		
	
	
	
	public void PM_Submission_ReassignmentForDifferentOrg(String SubmissionName,boolean user, String UserName, boolean group,String GroupName) throws Exception {
		
		 System.out.println("INSIDE PM_Submission_Reassignment  method()");
		     
		    SearchSubmisson_andCheck(SubmissionName);
		    Thread.sleep(4000);
		    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment_multipleJobs);
			Thread.sleep(4000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment_multipleJobs);
			Thread.sleep(1000);
			/*
			//IT WILL FAIL HERE NEED TO FIX
			assertion = Verify.action().verifyElementTextPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment.toString(), "You can not reassign all those tasks. All tasks must be available and part of the same organization. Please change your selection.", 10);
			if (assertion == false) {
				report("f", "Assertion failed while verifying message is not displayed at the bottom of the screen.");
			}
			System.out.println("OUTSIDE PM_Submission_Reassignment  method()");*/
			
			//TODO NEED WORK 
			
			assertion = Verify.action().verifyTextPresent("You can not reassign all those tasks. All tasks must be available and part of the same client. Please change your selection.", 10);
			if (assertion == false) {
				report("f", "Assertion failed while verifying message is not displayed at the bottom of the screen");
			} 
			
		
		    
	}	
	
	
	public void SearchSubmisson_andCheck(String SubmissionName) throws Exception {
    	System.out.println("INSIDE method SearchSubmisson()\n"); 

    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
    	Thread.sleep(1000);	
    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
    	Thread.sleep(1000);	
    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
    	Thread.sleep(1000);	
//    	type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
//    	Thread.sleep(1000);
    	//TODO NOT REQUIRED AS PER JAVA 8
    	General.action().type_withKEYS(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.ENTER,false);
    	Thread.sleep(1000);	
    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
    	Thread.sleep(8000);	
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_allCheck);
		Thread.sleep(5000);			
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_allCheck);
		Thread.sleep(2000);
    	
	    System.out.println("EOM SearchSubmisson()\n");

    }

	public void assertion() throws Exception {
		
		Thread.sleep(9000);
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_from_Users_dropdownWithIndex(UserName2,1),5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying  Re-assign icon after Search");
	    } else {
			report("p", "All Assertion passed.");
		}
	}

	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),
				Subtitler_TestRail_Common_Properties.idTestPlan, Subtitler_TestRail_Common_Properties.idBuild, result,
				Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}
}
