package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Admin_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

public class Sub_277153 {

	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_277153"+CommonElements.BROWSER+"M1";

    String Targetlanguage_1[] = { "German (Germany)" };
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_ToClaim = "To Claim";
	String menu_Ongoing = "Ongoing";
	String TranslatorGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_277153");
		dataSet.put("TL_test_case_description","SUB-277153:Delete a submission");
		dataSet.put("TL_internal_testCase_ID", "277153");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			// Scenario1 test case:1 for delete submission with new status.
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			Thread.sleep(20000);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_APOSTROPHE_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	    	Thread.sleep(4000);	
//			PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
			//TODO NOT REQUIRED AS PER NEW IMPROVEMENT FOR FILTER SUBMISSION (1.22.0 RC1)
			//PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.ENTER.toString());
		    Thread.sleep(5000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			PM_user.action().deleteSubmisson(SubmissionName);
			Thread.sleep(2000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	    	Thread.sleep(4000);	
//			PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
			//TODO NOT REQUIRED AS PER NEW IMPROVEMENT FOR FILTER SUBMISSION (1.22.0 RC1)
			//PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.ENTER.toString());
		    Thread.sleep(5000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementNotPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			General.action().logoutMethod();

			// Scenario-2 Test case 2 Delete submission with Claimed status.

			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH	+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_APOSTROPHE_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
            General.action().logoutMethod();

			// trans login

			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(20000);
			// click sub in to claim*****
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ToClaim);
			Thread.sleep(1000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(3000);
			Translator.action().Trans_AssigneSubmisson(SubmissionName);
			Thread.sleep(2000);
			// open sub in On going*****
			Translator.action().Navigate(menu_Ongoing);
			Thread.sleep(1000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
            Translator.action().translate_onGoing_submission(SubmissionName,Targetlanguage_1[0], false, true);
            Thread.sleep(1000);
            
            Translator.action().Navigate(menu_Ongoing);
			Thread.sleep(1000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			// logout from trans
			General.action().logoutMethod();

			// PM Login

			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			Thread.sleep(20000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			PM_user.action().cancelDeleteSubmisson(SubmissionName);
			Thread.sleep(2000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	    	Thread.sleep(4000);	
//			PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
	    	//TODO NOT REQUIRED AS PER NEW IMPROVEMENT FOR FILTER SUBMISSION (1.22.0 RC1)
			//PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.ENTER.toString());
		    Thread.sleep(5000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
		    PM_user.action().cancelDeleteSubmisson_second_confirmation(SubmissionName);
			Thread.sleep(2000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	    	Thread.sleep(4000);	
//			PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
			//TODO NOT REQUIRED AS PER NEW IMPROVEMENT FOR FILTER SUBMISSION (1.22.0 RC1)
			//PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.ENTER.toString());
		    Thread.sleep(5000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			deleteSubmisson_InProgress(SubmissionName);
			Thread.sleep(2000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	    	Thread.sleep(4000);	
//			PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
			//TODO NOT REQUIRED AS PER NEW IMPROVEMENT FOR FILTER SUBMISSION (1.22.0 RC1)
			//PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.ENTER.toString());
		    Thread.sleep(5000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	 public void deleteSubmisson_InProgress(String SubmissionName) throws Exception {
			
			System.out.println("INSIDE METHOD deleteSubmisson_InProgress");
	        PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(1000);
	/*		Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
			Thread.sleep(2000);*/
			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().DeleteSubmission_Icon);
			Thread.sleep(1000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().DeleteSubmission_Icon);
			Thread.sleep(1000);
			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().DeleteSubmission_Alert_delete_button);
			Thread.sleep(1000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().DeleteSubmission_Alert_delete_button);
			Thread.sleep(2000);
			//TODOD NOT REQUIRED 
//			PM_user.action().Click(Pm_User_Submission_Locators.Locator().DeleteJobs_Second_confirmation_Alert_button);
//			Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().DeleteJobs_final_message, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
      if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().DeleteJobs_final_message));
				{
				System.out.println("INSIDE IF--------");
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().DeleteSubmission_Alert_Cancel_button);
				}
				
				Thread.sleep(2000);
				PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
				Thread.sleep(1000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
				Thread.sleep(2000);
				
			 System.out.println("EOM deleteSubmisson_InProgress()");
		}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName after delete_submission");
		} else {report("p", "All Assertion passed.");
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
