package subtitler.project_manager.Open_Jobs;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

/**                                                                                                                                  
 * @author pvaidya                                                   
 *Summary: This testcase verifies that Admin and PM user UI has 'Jobs' folder.
 */ 

public class Sub_1583489 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String menu_allJobs = "Jobs";
	Boolean assertion = true;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1583489");
		dataSet.put("TL_test_case_description", "SUB-1583489:Restore the Jobs view and functionalities.");
		dataSet.put("TL_internal_testCase_ID", "1583489");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			//Log In as Admin
			General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			
			//Verify Jobs folder
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().SelectMenu(menu_allJobs), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying job step");
			}
			
			//Navigate to Jobs
			PM_user.action().Navigate(menu_allJobs);
			
			//Verify Jobs folder have columns like Name, Organization, Step, Status, Assignee, Source, Taget, Video, Segments and Due date.
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Name"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Client"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Step"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Status(Step)"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Assignee"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Source"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Target"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Video"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns_segment, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Due Date"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			
			//Log out from admin
			Thread.sleep(2000);
			General.action().logoutMethod();
			
			//Log in as PM
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			
			//Verify Jobs folder
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().SelectMenu(menu_allJobs), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying job step");
			}
			
			//Navigate to Jobs
			PM_user.action().Navigate(menu_allJobs);
			
			//Verify Jobs folder have columns like Name, Organization, Step, Status, Assignee, Source, Taget, Video, Segments and Due date.
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Name"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Client"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Step"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Status(Step)"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Assignee"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Source"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Target"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Video"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns_segment, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
			}
			
			} catch (Exception e) {
				report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			}

		}


		public void assertion() throws Exception {
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().allJobs_columns("Due Date"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Jobs columns.");
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
