package subtitler.project_manager.Filter_PD_Submission;

import static org.testng.AssertJUnit.assertTrue;

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
import locators.ReviewerLocators;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary: Filter submission by status
 *Preconditions :Use below PM credentials for login glplay_tdc14_pm@fakemail.com/Password2!
                 Some PD submission should be created before test.
 *
 */


public class Sub_1947364 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataset = new LinkedHashMap<String, String>();
	String menu_submission = "Submissions";
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1947364");
		dataset.put("TL_test_case_description", "SUB-1947364:Filter submission by status");
		dataset.put("TL_internal_testCase_ID", "1947364");
	}
	
	@Test
	public void execute() throws Exception {
		testCase(dataset);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataset) throws Exception {
		
		
	try {
		
		General.action().login(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
		Thread.sleep(20000);
		PM_user.action().Navigate(menu_submission);
		Thread.sleep(1000);	
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
		Thread.sleep(1000);
		PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
		Thread.sleep(1000);
		PM_user.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_Filter_Status);
		Thread.sleep(1000);
		PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_Filter_Status);
		Thread.sleep(2000);
		
		
		assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_Filter_Status_Options("Pending"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying status filter options");
		}
		assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_Filter_Status_Options("New"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying status filter options");
		}
		assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_Filter_Status_Options("In_Progress"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying status filter options");
		}
		
		
		
		
		
		
		
		
	} catch (Exception e) {
		report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	}

} 
	
	public void assertion() throws Exception {
		System.out.println("inside assertion");		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PD_Submission_Filter_Status_Options("Completed"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying status filter options");
		}else {
				report("p", "All Assertion passed.");
			}
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(dataset.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}
}