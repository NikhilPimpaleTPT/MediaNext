package subtitler.project_manager.Open_Jobs;


/**
 * 
 * @author pvaidya
 *
 */

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;
import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

public class Sub_686911 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();


	String menu_Submission = "Submissions";
	String menu_AllJobs = "Jobs";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_686911 ");
		dataSet.put("TL_test_case_description", "Sub_686911   :Rename Column headers Captions and Word count");
		dataSet.put("TL_internal_testCase_ID", "686911");
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
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchSubmission_SegmentIcon, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Segment Icon after Search");

			}	
			
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchSubmission_SegmentIcon, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Segment Icon after Search");

			}
			
			verifyWordCountSourceIconFor_AllUsers();
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchSubmission_WordCountSource, 5);
			if (assertion == false) {
					report("f","Assertion failed while verifying WordCountSource Icon  after Search");
			}
			
			Thread.sleep(3000);
			General.action().logoutMethod();
			
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
    		Thread.sleep(2000);
    		
    		verifyWordCountSourceIconFor_AllUsers();
    		
    		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchSubmission_WordCountSource, 5);
			if (assertion == false) {
					report("f","Assertion failed while verifying WordCountSource Icon  after Search");
			}
    		
    		Thread.sleep(3000);
			General.action().logoutMethod();
    		
    		General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
    		
    		verifyWordCountSourceIconFor_AllUsers();
			
			}catch (Exception e) {
				report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			}
			
		}
	
	 public void verifyWordCountSourceIconFor_AllUsers() throws Exception {
		 
		    Thread.sleep(2000);
		    PM_user.action().Navigate(menu_to_claim);
		    
		    assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchSubmission_WordCountSource, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying WordCountSource Icon  after Search");

			}
			Thread.sleep(2000);
			
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchSubmission_WordCountSource, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying WordCountSource Icon  after Search");

			}
			
			Thread.sleep(2000);
			
			PM_user.action().Navigate(menu_completed);
			
			}
	  
		public void assertion() throws Exception {

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchSubmission_WordCountSource, 5);
				if (assertion == false) {
						report("f","Assertion failed while verifying WordCountSource Icon  after Search");
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
				