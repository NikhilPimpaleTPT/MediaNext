package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

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

public class Sub_653802 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
			
			
			String SubmissionName = "SUB_653802"+CommonElements.BROWSER+"M1";
	         String menu_Submission = "Submissions";
		
			
			@BeforeMethod
			public void setup() throws Exception {
				General.action().startSystem("Sub_653802");
				dataSet.put("TL_test_case_description","Sub_653802:Create Submission - Change RS default settings");
				dataSet.put("TL_internal_testCase_ID", "653802");
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
					Thread.sleep(1000);
				    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
					Thread.sleep(1000);
					General.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
					Thread.sleep(1000);
					
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
					Thread.sleep(1000);
					
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
					Thread.sleep(1000);
					
					
					assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input, 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying MaxCharPerLine  after Search");
					}
					
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input);
					Thread.sleep(1000);
					
					
					assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input, 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying Readingspeed  after Search");
					}
					
					
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Minreadingspeed);
					Thread.sleep(1000);
					
					assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Minreadingspeed, 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying Minreadingspeed  after Search");
					}
					
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed);
					Thread.sleep(1000);
					
					
					assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed, 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying Maxreadingspeed  after Search");
					}
					
						} catch (Exception e) {
					report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				}

			}
			
			
			public void assertion() throws Exception {
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Maxreadingspeed  after Search");
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





