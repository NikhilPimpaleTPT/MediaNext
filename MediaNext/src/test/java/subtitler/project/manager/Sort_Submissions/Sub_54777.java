package subtitler.project.manager.Sort_Submissions;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.Admin_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_54777 {
	
	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = { "fr-FR", "de-DE", "ja-JP" };
	String Targetlanguage_1[] = { "French (France)", "German (Germany)","Japanese (Japan)" };
	String SubmissionName = "Sub_54777"+CommonElements.BROWSER+"B1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String menu_ToClaim = "To Claim";
	String menu_Ongoing ="Ongoing";
    String TranslatorGroupName = "Common_group";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_54777");
		dataSet.put("TL_test_case_description","SUB-54777:Sort by status");
		dataSet.put("TL_internal_testCase_ID", "54777");
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
			PM_user.action().Navigate(menu_submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			//Submission for language "French (France)"
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName +"1",CommonElements.action().COMMON_SOURCE_LANGUAGE,"French (France)");
            Thread.sleep(2000);
            
            //Submission for language "German (Germany)"
        	PM_user.action().Navigate(menu_submission);
        	PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName +"2",CommonElements.action().COMMON_SOURCE_LANGUAGE,"German (Germany)");
            Thread.sleep(2000);
            
            
            //Submission for language "Japanese (Japan)"
        	PM_user.action().Navigate(menu_submission);
        	PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName +"3",CommonElements.action().COMMON_SOURCE_LANGUAGE,"Japanese (Japan)");
            Thread.sleep(4000);
			
            PM_user.action().Navigate(menu_ToClaim);
        	Thread.sleep(1000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(1000);
			PM_user.action().Navigate(menu_Ongoing);
			Thread.sleep(1000);
			PM_user.action().PM_onGoing_submission(SubmissionName+"2", Targetlanguage_1[1], false, true);
			Thread.sleep(1000);
			PM_user.action().Navigate(menu_Ongoing);
			Thread.sleep(1000);
			PM_user.action().PM_onGoing_submission(SubmissionName+"3", Targetlanguage_1[2], true, false);
			Thread.sleep(1000);
			PM_user.action().Navigate(menu_submission);
			Thread.sleep(3000);
			
			//TODO AS PER GL PLAY 1.22.0 SHOW COMPLETED TOGGLE BUTTON IS REMOVED
//			 if(General.driver.findElement(Admin_User_Submission_Locators.Locator().Show_Complete_Sub_button).isDisplayed()); {
//				  Thread.sleep(1000);
//				  General.action().waitforelemenetpresent(Admin_User_Submission_Locators.Locator().Show_Complete_Sub_button); 
//				  Thread.sleep(1000);
//				  General.action().Click(Admin_User_Submission_Locators.Locator().Show_Complete_Sub_button);
//			  }
			 			
			Thread.sleep(3000);
			 //Sort sub by status
        	PM_user.action().sortSubmission_Status(SubmissionName, true);
    		Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_status(SubmissionName ,"Completed"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_status(SubmissionName,"Completed"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Status after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_status(SubmissionName ,"In_Progress"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_status(SubmissionName ,"In_Progress"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Status after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_status(SubmissionName, "New"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_status(SubmissionName , "New"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Status after search");
			}

			PM_user.action().sortSubmission_Status(SubmissionName, false);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_status(SubmissionName , "New"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_status(SubmissionName , "New"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Status after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_status(SubmissionName  , "In_Progress"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_status(SubmissionName  , "In_Progress"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Status after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_status(SubmissionName , "Completed"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_status(SubmissionName , "Completed"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Status after search");
			}

			System.out.println("EOM sortSubmission_Status()");

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_status(SubmissionName , "Completed"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Status after search");
		} else {
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

