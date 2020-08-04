package subtitler.Linguist.Open_Job;

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
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:This test case is to verify the characters are displaying on the Translation and Review environments
 *
 */

public class Sub_2353554 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_2353554"+CommonElements.BROWSER+"F2";
	String WorkflowName = "Two_Step_Workflow";
	String OrganizationName = "Subtitle_Common_orgs";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_submission = "Submissions";
	String targetlanguage = "German (Germany)";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String folder_2353554="2353554";
    
    
Boolean assertion = true;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2353554");
		dataSet.put("TL_test_case_description", "SUB-2353554:Translator / Reviewer - selected character for each subtitle should display on the Translation and Review environments.");
		dataSet.put("TL_internal_testCase_ID", "2353554");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			//Create Submission
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			Thread.sleep(20000);
			PM_user.action().Navigate(menu_submission);
			Thread.sleep(1000);
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", true);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+folder_2353554);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(20000);
		
			//SEARCH SUBMISSION AND CHECK
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
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
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			translate_onGoing_submission(SubmissionName,targetlanguage,true,false);
			Thread.sleep(2000);
			
			Thread.sleep(2000);
		    General.action().logoutMethod();
	           
	        // Review LogIn 
			General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
			Thread.sleep(20000);
			Reviewer.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Reviewer.action().review_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			reviewer_onGoing_submission(SubmissionName, targetlanguage);
			Thread.sleep(2000);
		    
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void translate_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
		
		 System.out.println("INSIDE trans_Ongoing  method()");
		 
		    General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_search_input);
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_search_input, SubmissionName);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(TranslatorLocators.Locator().check_trans_Target_Lang(SubmissionName, target)))
			{
				System.out.println("INSIDE IF--------");
				General.action().Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName, target));
			}
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
			Thread.sleep(8000);
			
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_segments_characterList(1,"Fun"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying character from attached file");
			}
			
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_segments_characterList(2,"Dude"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying characters from attached file");
			}
			
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_segments_characterList(3,"Shaky"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying characters from attached file");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_segments_characterList(4,"Fun"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying characters from attached file");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_segments_characterList(5,"Dude"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying characters from attached file");
			}
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_segments_characterList(6,"All"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying characters from attached file");
			}
				  
			if(complete){
				General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
				Thread.sleep(1000);
				General.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
				Thread.sleep(1000);
			    General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
				Thread.sleep(1000);
				General.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
				Thread.sleep(3000);
				if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
						General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
						Thread.sleep(1000);
						General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
					    }
				}
					
				    
				if(back){
				    if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
					  General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
					  Thread.sleep(1000);
					  General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
					}
				 }
				    
			System.out.println("EOM trans_Ongoing  method()");
			      
			      
	}
	
	public void reviewer_onGoing_submission(String SubmissionName, String target)throws Exception {

		System.out.println("INSIDE reviewer_onGoing_submission  method()");

		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().review_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(ReviewerLocators.Locator().reviewer_selectSubmission_checkbox(SubmissionName,target));
		}
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
		Thread.sleep(5000);
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_segments_characterList(1,"Fun"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying character from attached file");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_segments_characterList(2,"Dude"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying characters from attached file");
		}
		
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_segments_characterList(3,"Shaky"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying characters from attached file");
		}
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_segments_characterList(4,"Fun"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying characters from attached file");
		}
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_segments_characterList(5,"Dude"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying characters from attached file");
		}
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_segments_characterList(6,"All"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying characters from attached file");
		}	
	}
	
	
	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_segments_characterList(6,"All"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying characters from attached file");
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


