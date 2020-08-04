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
 *Summary:This test case is to verify the error for generating ASR
 *
 */

public class Sub_2630480 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String[] data = {"1", "0", "0", "0","1"};
	Boolean assertion = true;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2630480");
		dataSet.put("TL_test_case_description", "SUB-2630480:ASR - Support for error 500 - Internal Server Error");
		dataSet.put("TL_internal_testCase_ID", "2630480");
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
			// TODO NEW IMPL OF SUBMISSION CREATION
			CreateSubmisson_Step1(data[0], data[1], data[2], data[3],data[4]);
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,"69069");
			Thread.sleep(2000);
			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_materialSelection_speechToText_button);
			Thread.sleep(1000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_materialSelection_speechToText_button);
			Thread.sleep(1000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_materialSelection_speechToText_dataSettings(1,"Max lines per sub: "+data[0]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying setting data");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_materialSelection_speechToText_dataSettings(2,"Max chars per line: "+data[1]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying setting data");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_materialSelection_speechToText_dataSettings(3,"Min count of frames between subs: "+data[2]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying setting data");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_materialSelection_speechToText_dataSettings(4,"Min duration of a sub (sec): "+data[3]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying setting data");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_materialSelection_speechToText_dataSettings(5,"Max duration of a sub (sec): "+data[4]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying setting data");
			}
			
			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_materialSelection_speechToText_generateButton);
			Thread.sleep(1000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_materialSelection_speechToText_generateButton);
			Thread.sleep(500);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_materialSelection_speechToText_generate_error("Problem to generate the Speech to Text output, please retry"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying error after generating");
			}
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	 public void CreateSubmisson_Step1(String maxLinePerSub,String MaxCharperLine,String minCountOfFrames,String minDurationOfSub,String maxDurationOfSub) throws Exception{
		 System.out.println("INSIDE method CreateSubmisson_Step1()\n");
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		 Thread.sleep(1000);
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
		 Thread.sleep(1000);
		 
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input,maxLinePerSub);
		 Thread.sleep(1000);
		 
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,MaxCharperLine);
		 Thread.sleep(1000);
		 
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinCountOfFrames_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinCountOfFrames_input,minCountOfFrames);
		 Thread.sleep(1000);
		 
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,minDurationOfSub);
		 Thread.sleep(1000);
		 
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,maxDurationOfSub);
		 Thread.sleep(1000);
		 
		 
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		 Thread.sleep(1000);
			
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		 Thread.sleep(4000);
	     System.out.println("EOM CreateSubmisson_Step1()\n");

		}
	public void assertion() throws Exception {
		
		//Verify Error should should occur "Problem to generate the Speech to Text output, please retry."
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_materialSelection_speechToText_generate_error("Problem to generate the Speech to Text output, please retry"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying error after generating");
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
