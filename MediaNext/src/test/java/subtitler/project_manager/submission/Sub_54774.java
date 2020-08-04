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
import modules.PM_user;
import modules.Verify;
/**
 * 
 * @author pvaidya
 *
 */
public class Sub_54774 {
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName ="SUB_54774"+CommonElements.BROWSER+"Q2_";

    String targetlanguage_1 = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_AllJobs = "Jobs";
	String menu_ToClaim = "To Claim";
	String menu_Ongoing  = "Ongoing";	
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String menu_completed = "Completed";
	String UserName = "Maya Gurnale";
	String GroupName = "Common_group_multi2";
	String Status_Available="Available";
	String Status_Claimed="Claimed";
	String Status_In_Progress="In_Progress";
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_54774");
		dataSet.put("TL_test_case_description","Sub_54774:Sort by status ");
		dataSet.put("TL_internal_testCase_ID", "54774");
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
			PM_user.action().Navigate(menu_Submission);
			
			for(int i=1;i<=3;i++) {
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
				System.out.println("filePath------>" + filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName+i,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				Thread.sleep(2000);
				
				System.out.println("SUBMISSION CREATED--->"+SubmissionName+i);
			}
		   
			Thread.sleep(2000);	
				
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(2000);
			PM_user.action().PM_Submission_Reassignment(SubmissionName+"2", true, UserName, false, GroupName);
		    Thread.sleep(2000);
		    General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		    Thread.sleep(5000);
		    PM_user.action().SearchSubmisson(SubmissionName+"2");
		    Thread.sleep(4000);
		   
			PM_user.action().Navigate(menu_ToClaim);
	        Thread.sleep(1000);
			PM_user.action().PM_ToClaim(SubmissionName+"3");
			Thread.sleep(1000);
			PM_user.action().Navigate(menu_Ongoing);
			Thread.sleep(1000);
			PM_user.action().PM_onGoing_submission(SubmissionName+"3", targetlanguage_1, false, true);
			Thread.sleep(3000);
		

			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(4000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(5000);
			if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().pmUser_AllJobs_showComplete_toggleButton_off)) {
			System.out.println("BUTTON IS OFF NOW********");
			General.action().Click(Pm_User_Submission_Locators.Locator().pmUser_AllJobs_showComplete_toggleButton_off);
			Thread.sleep(2000);
			System.out.println("BUTTON SWITCHED ON*********");
			Thread.sleep(2000);
			}else {
					System.out.println("BUTTON IS ON NOW");
			}

			//Sort sub by status(Ascending)
	        PM_user.action().sortSubmission_Status(SubmissionName, true);
	    	Thread.sleep(2000);
	    		
	    		
	    		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_status_AscDsc(1,SubmissionName+"1",Status_Available), 5);
				if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
	    		}
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_status_AscDsc(2,SubmissionName+"2",Status_Claimed), 5);
				if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
	    		}
				
			    assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_status_AscDsc(3,SubmissionName+"3",Status_In_Progress), 5);
				if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
	    		}
	    		
				//Sort sub by status(Descending)
	        	PM_user.action().sortSubmission_Status(SubmissionName, false);
	    		Thread.sleep(2000);
	    		
	    		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_status_AscDsc(3,SubmissionName+"1",Status_Available), 5);
				if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
	    		}
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_status_AscDsc(2,SubmissionName+"2",Status_Claimed), 5);
				if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
	    		}
				
				
				
			}
		
			    catch (Exception e) {
					report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				}
	}
	
			    public void assertion() throws Exception {
			    	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_status_AscDsc(1,SubmissionName+"3",Status_In_Progress), 5);
					if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
		    		}else {
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
