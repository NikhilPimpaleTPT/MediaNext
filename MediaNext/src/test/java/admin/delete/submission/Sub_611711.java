package admin.delete.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.Admin_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.Translator;
import modules.Verify;
import modules.admin;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;


/**
 * 
 * @author psukhwani
 * 
 * As an admin, delete a submission which has completed status.
[NOTE : here we have to cover 2 scenarios as per automation
1. Submission with 3 target languages. Out of which 2 should be in "In_progress" and 1 should be in "Completed". That means Partial(some JOBs) complete one submission and delete it.
2. Submission with 3 target languages. Out of which all 3 target languages should be in "Completed" status. That means Fully(Jobs) complete one submission and delete it.]
 *
 */
public class Sub_611711 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "Submission_611711"+CommonElements.BROWSER+"G6";

//	String sourcelanguage = "en-US - English (United States)";
	String sourcelanguage = "en-US";

//	String Targetlanguage [] = {"fr-FR - French (France)","de-DE - German (Germany)","ja-JP - Japanese (Japan)"}; 
	String Targetlanguage [] = {"fr-FR","de-DE","ja-JP"}; 

	
	String Targetlanguage_1 [] = {"French (France)","German (Germany)","Japanese (Japan)"}; 
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
    String TranslatorGroupName = "Common_group";
	String menu_submissions = "Submissions";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
	String menu_allJobs = "Jobs";
	String menu_completed = "Completed";
	String status= "Completed";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_611711");
		dataSet.put("TL_test_case_description", "SUB-611711:Delete Submission having completed status");
		dataSet.put("TL_internal_testCase_ID", "611711");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
            //[NOTE : here we have to cover 2 scenarios as per automation
			// SCENARIO 1 [1.  Submission with 3 target languages. Out of which all 3 target languages should be in "Completed" status.]
			//login admin
			General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			admin.action().Navigate(menu_submissions);

			//TODO NEW IMPL OF SUBMISSION CREATION
			admin.action().CreateSubmisson_Step1("17", "35", "80", "100");
			admin.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
			admin.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			admin.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_SRT_FOLDER);
			admin.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET,SubmissionName, sourcelanguage, Targetlanguage);
			
			
			Thread.sleep(2000);
			admin.action().Navigate(menu_submissions);
			admin.action().SearchSubmisson(SubmissionName);
			Thread.sleep(1000);
			assertion = Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
           // logout admin
			Thread.sleep(3000);
			General.action().logoutMethod();

			// trans login
			General.action().login(CommonElements.action().Translator_username_multilang, CommonElements.action().password);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Translator.action().translate_onGoing_submission(SubmissionName, Targetlanguage_1[0],true,false);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Translator.action().translate_onGoing_submission(SubmissionName,Targetlanguage_1[1],true,false);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Translator.action().translate_onGoing_submission(SubmissionName,Targetlanguage_1[2],true,false);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_completed);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(1000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translatorSearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			//logout trans 
			Thread.sleep(3000);
			General.action().logoutMethod();

			// admin login
			General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			admin.action().Navigate(menu_submissions);
			Thread.sleep(1000);
			admin.action().SearchSubmisson(SubmissionName);
			Thread.sleep(1000);
			deleteSubmisson_ClaimedStatus(SubmissionName);
			Thread.sleep(1000);
			
			//This assertion will check that sub is not present
			assertion = Verify.action().verifyElementNotPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName after delete_submission");
			}
			
			
			//logout trans 
			Thread.sleep(3000);
			General.action().logoutMethod();
			
			// SCENARIO 2 [2. Submission with 3 target languages. Out of which 2 should be in "In_progress" and 1 should be in "Completed"]
			  
				//login admin
				General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
				admin.action().Navigate(menu_submissions);
				//TODO NEW IMPL OF SUBMISSION CREATION
				admin.action().CreateSubmisson_Step1("17", "35", "80", "100");
				admin.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
				admin.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				admin.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_SRT_FOLDER);
				admin.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET,SubmissionName,sourcelanguage, Targetlanguage);
				
				Thread.sleep(1000);
				admin.action().Navigate(menu_submissions);
				admin.action().SearchSubmisson(SubmissionName);
				
				Thread.sleep(1000);
				assertion = Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
				}
				
	         // logout admin
				Thread.sleep(3000);
				General.action().logoutMethod();
				
			// trans login
				General.action().login(CommonElements.action().Translator_username_multilang, CommonElements.action().password);
				Thread.sleep(1000);
				Translator.action().Navigate(menu_to_claim);
				Thread.sleep(1000);
				Translator.action().trans_ToClaim(SubmissionName);
				Thread.sleep(1000);
				Translator.action().Navigate(menu_ongoing);
				Thread.sleep(1000);
				Translator.action().translate_onGoing_submission(SubmissionName, Targetlanguage_1[0],true,false);
				Thread.sleep(1000);
				Translator.action().Navigate(menu_ongoing);
				Thread.sleep(1000);
				Translator.action().translate_onGoing_submission(SubmissionName, Targetlanguage_1[1],false,true);
				Thread.sleep(1000);
				Translator.action().Navigate(menu_ongoing);
				Thread.sleep(1000);
				Translator.action().translate_onGoing_submission(SubmissionName, Targetlanguage_1[2],false,true);
				Thread.sleep(1000);
				Translator.action().Navigate(menu_ongoing);
				Thread.sleep(1000);
				Translator.action().SearchSubmisson(SubmissionName);
				Thread.sleep(1000);
				//TODO
				assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().check_trans_Target_Lang(SubmissionName,Targetlanguage_1[1]), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
				}
				Thread.sleep(1000);
				
				assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().check_trans_Target_Lang(SubmissionName,Targetlanguage_1[2]), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
				}
				Thread.sleep(1000);
				//TODO
				
				Translator.action().Navigate(menu_completed);
				Thread.sleep(1000);
				Translator.action().SearchSubmisson(SubmissionName);
				Thread.sleep(1000);
				//TODO
				assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().check_trans_Target_Lang(SubmissionName, Targetlanguage_1[0]), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
				}
				//TODO
				//logout trans 
				Thread.sleep(3000);
				General.action().logoutMethod();
				
				//login admin
				General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
				Thread.sleep(2000);
				
				admin.action().sort(menu_allJobs, "Target", true);
				Thread.sleep(2000);
				
				if(Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().Show_Complete_Sub_button, 5)){
					General.action().Click(Admin_User_Submission_Locators.Locator().Show_Complete_Sub_button);
				}
				admin.action().SearchSubmisson(SubmissionName);
				Thread.sleep(2000);
				
				//TODO
				//CLick on Target
				
				//TODO BELOW ASSERTION HAS ISSUE....STATUS SHOULD BE IN_PROGRESS BUT NOW IT IS SHOWING CLAIMED. REFER ISSUE SUB-378
				//This assersion will check submission status "Claimed" in admin_Jobs tab
				//TODO SUB-378 bug has been resolved.
				
				  //TODO
				//This assersion will check submission status "Completed" in admin_Jobs tab
				  assertion=Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().check_submission_row_status(1,SubmissionName, "Completed"), 5); 
				  if(assertion==false){
				  report("f","Assertion failed while verifying check_submission_status after Search"); 
				  }
				 
				  assertion=Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().check_submission_row_status(2,SubmissionName, "In_Progress"), 5); 
				  if(assertion==false){
				  report("f","Assertion failed while verifying check_submission_status after Search"); 
				  }
				  //TODO CHECK FOR OTHER SUB WITH RO NUM
				  assertion=Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().check_submission_row_status(3,SubmissionName, "In_Progress"), 5); 
				  if(assertion==false){
				  report("f","Assertion failed while verifying check_submission_status after Search"); 
				  }

					Thread.sleep(1000);					
					
					admin.action().Navigate(menu_submissions);
					deleteSubmisson(SubmissionName);
					Thread.sleep(1000);	
					admin.action().SearchSubmisson(SubmissionName);
					Thread.sleep(4000);
					//This assertion will check that sub is not present
					assertion = Verify.action().verifyElementNotPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName), 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying SubmissionName after delete_submission");
					}
					
					

		} catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}
		/**
		 * This method is used to delete submission of those test cases which has status as "In Progress", "Completed". [NOTE:- method for two confirmation alert boxes.]
		 * @author psukhwani
		 * @param SubmissionName
		 * @throws Exception
		 */
		  public void deleteSubmisson_ClaimedStatus(String SubmissionName) throws Exception {
			  System.out.println("INSIDE METHOD deleteSubmisson");
			  
			  //TODO Show completed status is no more present form 1.22.0 RC1
//			  if(General.driver.findElement(Admin_User_Submission_Locators.Locator().Show_Complete_Sub_button).isDisplayed()); {
//				  Thread.sleep(1000);
//				  General.action().waitforelemenetpresent(Admin_User_Submission_Locators.Locator().Show_Complete_Sub_button); 
//				  Thread.sleep(1000);
//				  General.action().Click(Admin_User_Submission_Locators.Locator().Show_Complete_Sub_button);
//			  }
//			  
			  General.action().waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input); 
			  Thread.sleep(1000);
			  General.action().ClearInputvalues(Admin_User_Submission_Locators.Locator() .admin_user_Search_Submission_input);
			  General.action().type(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input, SubmissionName);
			  Thread.sleep(3000);	  
//			  General.action().type(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input, Keys.chord(Keys.ENTER));
			  //TODO NOT REQUIRED AS PER NEW IMPROVEMENT FOR FILTER SUBMISSION
//			  General.action().type(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input, Keys.ENTER.toString());
//			  Thread.sleep(2000);
			  General.action().waitforelemenetpresent(Admin_User_Submission_Locators. Locator().SelectSubmission_Checkbox(SubmissionName)); 
			  Thread.sleep(2000);
			  General.action().Click(Admin_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName)); 
			  Thread.sleep(2000);
			  
			  //CHECK SUB STATUS in SUBMISSIONS
			  assertion=Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().check_submission_status(SubmissionName, status), 5); 
			  if(assertion==false){
			  report("f","Assertion failed while verifying check_submission_status after Search"); 
			  }
			  
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
			  assertion=Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().DeleteJobs_Second_confirmation_Alertt_Cancel_button, 5);
			  if(assertion==false){ 
			  report("f","Assertion failed while verifying DeleteJobs_Second_confirmation_Alertt_Cancel_button after Search	" );
			  }
			 
			  //assertion=DLETE JOBS second confirm box
			  assertion=Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().DeleteJobs_Second_confirmation_Alert_button, 5);
			  if(assertion==false){ 
			  report("f", "Assertion failed while verifying DeleteJobs_Second_confirmation_Alert_button after Search"); 
			  } 
			  Thread.sleep(1000);
			  General.action().waitforelemenetpresent(Admin_User_Submission_Locators .Locator().DeleteJobs_Second_confirmation_Alert_button);
			  Thread.sleep(3000);
			  General.action().Click(Admin_User_Submission_Locators. Locator().DeleteJobs_Second_confirmation_Alert_button);
		      Thread.sleep(2000);
			  General.action().waitforelemenetpresent(Admin_User_Submission_Locators. Locator().admin_user_Search_Submission_input);
			  Thread.sleep(1000);
			  admin.action().SearchSubmisson(SubmissionName);
			  Thread.sleep(1000);
			  System.out.println("EOM deleteSubmisson()"); 
			   }	
		  
		  /**
		   * This method is used to delete submission of those test cases which has status as "NEW" [NOTE:- method for one confirmation alert box. ]
		   * @author psukhwani
		   * @param SubmissionName
		   * @throws Exception
		   */
		  
			public void deleteSubmisson(String SubmissionName) throws Exception {

				System.out.println("INSIDE METHOD deleteSubmisson");
				General.action().waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input);
				Thread.sleep(1000);
				General.action().ClearInputvalues(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input);
				General.action().type(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input,SubmissionName);
//				General.action().type(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input,Keys.chord(Keys.ENTER));
				//TODO NOT REQUIRED AS PER NEW IMPROVEMENT FROM 1.22.0 RC1
				//General.action().type(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input,Keys.ENTER.toString());
		        General.action().waitforelemenetpresent(Admin_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
				Thread.sleep(1000);
				General.action().Click(Admin_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
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
				General.action().waitforelemenetpresent(Admin_User_Submission_Locators .Locator().DeleteJobs_Second_confirmation_Alert_button);
				  Thread.sleep(3000);
				  General.action().Click(Admin_User_Submission_Locators. Locator().DeleteJobs_Second_confirmation_Alert_button);
			      Thread.sleep(2000);
				  General.action().waitforelemenetpresent(Admin_User_Submission_Locators. Locator().admin_user_Search_Submission_input);
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