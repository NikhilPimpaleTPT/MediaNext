package admin.delete.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.Admin_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;
import modules.admin;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;
/**
 * 
 * @author psukhwani
 *Delete Submission
 */
public class Sub_306369 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "Submission_306369"+CommonElements.BROWSER+"P1";
	String sourcelanguage = "en-US - English (United States)";
	String targetlanguage = "de-DE - German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String TranslatorGroupName = "Common_group";
	String menu_submission = "Submissions";
	String menu_toClaim = "To Claim";
    String menu_Ongoing = "Ongoing";
	String status= "In_Progress";
	String status_new= "New";
	String Targetlanguage_1 [] = {"German (Germany)"}; 



	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_306369");
		dataSet.put("TL_test_case_description", "SUB-306369:Delete Submission");
		dataSet.put("TL_internal_testCase_ID", "306369");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			//SCENARIO 1 admin login
			General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			admin.action().Navigate(menu_submission);
			
     		//TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			
			
			admin.action().Navigate(menu_submission);
			//TODO NOT REQUIRED
//		    admin.action().SearchSubmisson(SubmissionName);
			admin.action().cancelDeleteSubmisson(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			admin.action().Navigate(menu_submission);
			Thread.sleep(2000);
			deleteSubmisson(SubmissionName);

			admin.action().SearchSubmisson(SubmissionName);

			//This assertion will check that sub is not present
			assertion = Verify.action().verifyElementNotPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName after delete_submission");
			}
			
            // logout
			Thread.sleep(3000);
			General.action().logoutMethod();

			
			//SCENARIO 2 admin login
			General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			admin.action().Navigate(menu_submission);
            // create sub
          //TODO NEW IMPL OF SUBMISSION CREATION
			admin.action().CreateSubmisson_Step1("17", "35", "80", "100");
			admin.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
			admin.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			admin.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_SRT_FOLDER);
			admin.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			
			
			admin.action().SearchSubmisson(SubmissionName);
			assertion = Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			// logout
			Thread.sleep(3000);
			General.action().logoutMethod();

			// trans login
			General.action().login(CommonElements.action().Translator_username,CommonElements.action().password );
            // click sub in to claim*****
			Thread.sleep(1000);
			Translator.action().Navigate(menu_toClaim);
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
		
			Translator.action().translate_onGoing_submission(SubmissionName,Targetlanguage_1[0],false,true);
			Thread.sleep(2000);
			
			Translator.action().Navigate(menu_Ongoing);
			Thread.sleep(2000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);


			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			// logout from trans
			General.action().logoutMethod();

			// login to admin
			General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			Thread.sleep(1000);
			admin.action().Navigate(menu_submission);
			Thread.sleep(1000);
			admin.action().SearchSubmisson(SubmissionName);
			Thread.sleep(1000);
			deleteSubmisson_ClaimedStatus(SubmissionName);
			Thread.sleep(1000);
			admin.action().SearchSubmisson(SubmissionName);
			assertion = Verify.action().verifyElementNotPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName after delete_submission");
			}
			

		} catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	/**
	 * @author psukhwani
	 * This method is used to delete submission of IN_Progress and Complete Status
	 * @param SubmissionName
	 * @throws Exception
	 */
	  public void deleteSubmisson_ClaimedStatus(String SubmissionName) throws Exception {
	  System.out.println("INSIDE METHOD deleteSubmisson_ClaimedStatus");

	  admin.action().SearchSubmisson_andCheck(SubmissionName);
	  Thread.sleep(2000);
	  
	  //CHECK SUB STATUS in SUBMISSIONS
	  //TEST WILL FAIL HERE	  
	  //TODO TEST CASE WILL FAIL HERE DUE TO BUG SUB-378 
      assertion=Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().check_submission_status(SubmissionName, status), 5); 
	  if(assertion==false){
	  report("f","Assertion failed while verifying check_submission_status after Search"); 
	  }
	  
	  //TODO TILL HERE
	  
	  General.action ().waitforelemenetpresent(Admin_User_Submission_Locators.Locator().DeleteSubmission_Icon); 
	  Thread.sleep(1000);
	  General.action().Click(Admin_User_Submission_Locators.Locator().DeleteSubmission_Icon); 
	  Thread.sleep(3000); 
	 
	  //assertion=DLETE SUB
	 
	  assertion=Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().DeleteSubmission_Alert_button, 5); 
	  if(assertion==false){
	  report("f","Assertion failed while verifying DeleteSubmission_Alert_button after Search"); 
	  }
	 
	  //assertion = CANCEL
	  
	  assertion=Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().DeleteSubmission_Alert_Cancel_button, 5);
	  if(assertion==false){ 
	  report("f", "Assertion failed while verifying DeleteSubmission_Alert_Cancel_button after Search" ); 
	  }
	  Thread.sleep(1000);
	 General.action().waitforelemenetpresent(Admin_User_Submission_Locators .Locator().DeleteSubmission_Alert_button); 
	 Thread.sleep(3000);
	  General.action().Click(Admin_User_Submission_Locators.Locator().DeleteSubmission_Alert_button);
	 Thread.sleep(2000);
	 
	 //assertion = CANCEL JOBS second confirm box
	  //TODO TEST CASE WILL FAIL HERE DUE TO BUG SUB-378 
	  assertion=Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().DeleteJobs_Second_confirmation_Alertt_Cancel_button, 5);
	  if(assertion==false){ 
	  report("f","Assertion failed while verifying DeleteJobs_Second_confirmation_Alertt_Cancel_button after Search	" );
	  }
	 
	  //assertion=DLETE JOBS second confirm box
	 
	  assertion=Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().DeleteJobs_Second_confirmation_Alert_button, 5);
	  if(assertion==false){ 
	  report("f", "Assertion failed while verifying DeleteJobs_Second_confirmation_Alert_button after Search	" ); 
	  } 
	  Thread.sleep(1000);
	  General.action().waitforelemenetpresent(Admin_User_Submission_Locators .Locator().DeleteJobs_Second_confirmation_Alert_button);
	  Thread.sleep(3000);
	  General.action().Click(Admin_User_Submission_Locators. Locator().DeleteJobs_Second_confirmation_Alert_button);
	 //TODO TILL HERE
	 
	  Thread.sleep(2000);
	  General.action().waitforelemenetpresent(Admin_User_Submission_Locators. Locator().admin_user_Search_Submission_input);
	  Thread.sleep(1000);
		admin.action().SearchSubmisson(SubmissionName);
		Thread.sleep(1000);
	  System.out.println("EOM deleteSubmisson_ClaimedStatus()"); 
	  }
	 
/**
 * @author psukhwani
 * This method is used to delete Submission of NEW status
 * @param SubmissionName
 * @throws Exception
 */
	public void deleteSubmisson(String SubmissionName) throws Exception {

		System.out.println("INSIDE METHOD deleteSubmisson");

		admin.action().SearchSubmisson_andCheck(SubmissionName);
		Thread.sleep(2000);
		General.action().waitforelemenetpresent(Admin_User_Submission_Locators.Locator().DeleteSubmission_Icon);
		Thread.sleep(1000);
		General.action().Click(Admin_User_Submission_Locators.Locator().DeleteSubmission_Icon);
		Thread.sleep(3000);
		
		
		// assertion=DLETE SUB

		assertion = Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().DeleteSubmission_Alert_button,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying DeleteSubmission_Alert_button after Search	");
		}

		// assertion = CANCEL

		assertion = Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().DeleteSubmission_Alert_Cancel_button,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying DeleteSubmission_Alert_Cancel_button after Search	");
		}

		Thread.sleep(1000);
		General.action().waitforelemenetpresent(Admin_User_Submission_Locators.Locator().DeleteSubmission_Alert_button);
		Thread.sleep(3000);
		General.action().Click(Admin_User_Submission_Locators.Locator().DeleteSubmission_Alert_button);
		Thread.sleep(2000);
		General.action().waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input);
		Thread.sleep(1000);
		System.out.println("EOM deleteSubmisson()");
	}

	public void assertion() throws Exception {
		//This assertion will check that sub is not present
		assertion = Verify.action().verifyElementNotPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName after delete_submission");
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
