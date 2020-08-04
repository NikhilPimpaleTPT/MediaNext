package subtitler.project_manager.submission;


import static org.testng.AssertJUnit.assertTrue;

/**
 * 
 * @author pvaidya
 * This Test Case Is To Create/Edit Submission - No validation is showing if user set min RS > max RS. and min/max duration of a sub
 */


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

public class Sub_1011354 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_1011354"+CommonElements.BROWSER+"Q5";
	String menu_Submission = "Submissions";
	String ErrorMassage_MinReadingSpeed="Value can not be equal or bigger than Max reading speed";
	String ErrorMassage_MaxReadingSpeed="Value can not be equal or smaller than Min reading speed";
	String ErrorMassage_MinDuration="Value is above the limit 99";
	String ErrorMassage_MaxDuration="Value can not be equal or smaller than Min duration";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_1011354");
		dataSet.put("TL_test_case_description", "Create/Edit Submission - No validation is showing if user set min RS > max RS. and min/max duration of a sub");
		dataSet.put("TL_internal_testCase_ID", "1011354");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			// PM login
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			CreateSubmisson_Step1("35", "35", "100", "100");
	
	
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_ErrorMsgForReadingSpeedAndDuration(ErrorMassage_MinReadingSpeed), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying ErrorMsg For Reading Speed And Duration");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_ErrorMsgForReadingSpeedAndDuration(ErrorMassage_MaxReadingSpeed), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Error Msg For Reading Speed And Duration");
			}
			
		}	
			
			catch (Exception e) {
				report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			}
}
	
	public void CreateSubmisson_Step1(String MinReadingSpeed,String MaxReadingSpeed,String Minduration,String Maxduration) throws Exception{
		 System.out.println("INSIDE method CreateSubmisson_Step1()\n");
		 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
			Thread.sleep(1000);
			
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Minreadingspeed);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Minreadingspeed,MinReadingSpeed);
			Thread.sleep(1000);
			
			
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed,MaxReadingSpeed);
			Thread.sleep(1000);
			
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,Minduration);
			Thread.sleep(1000);
			
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,Maxduration);
			Thread.sleep(1000);
			

		}
		public void assertion() throws Exception {
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_ErrorMsgForReadingSpeedAndDuration(ErrorMassage_MinDuration), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Error Msg For Reading Speed And Duration");
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
