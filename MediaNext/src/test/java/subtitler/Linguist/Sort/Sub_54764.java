package subtitler.Linguist.Sort;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_54764 {
	
	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
    String targetlanguage_1 = "German (Germany)";
	String SubmissionName = "Sub_54764 "+CommonElements.BROWSER+"M4";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
    String menu_ToClaim = "To Claim";
    String menu_ongoing = "Ongoing";
    String menu_completed = "Completed";
	String TranslatorGroupName = "Common_group";

	

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_54764 ");
		dataSet.put("TL_test_case_description","SUB-54764:Sort by Job name");
		dataSet.put("TL_internal_testCase_ID", "54764");
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
			PM_user.action().Navigate(menu_submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
           for (int i = 1; i <= 3; i++) {
		   PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
		   PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
		   PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
		   PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
		   PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName + i,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
           Thread.sleep(1000);
           }
           PM_user.action().SearchSubmisson(SubmissionName);
           Thread.sleep(1000);
           assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			General.action().logoutMethod();
			
           //trans login
		  //check sorting in Trans TOCLAIM tab
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ToClaim);
			Thread.sleep(1000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(1000);
			
	       //sort submission in TOClaim by Name
		  //method to check submission by ascending order
			Translator.action().sortSubmission(SubmissionName, true);

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			
			//method to check submission by descending order
			Translator.action().sortSubmission(SubmissionName, false);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			System.out.println("EOM sortSubmission()");
            Thread.sleep(4000);
            Translator.action().SearchSubmisson(SubmissionName);
            Thread.sleep(2000);
            Translator.action().trans_ToClaim(SubmissionName);
            Thread.sleep(2000);
            
            
            //check sorting in Trans ONGOING tab
            
            Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(1000);
			
	       //sort submission in ONGOING by Name
		  //method to check submission by ascending order
			Translator.action().sortSubmission(SubmissionName, true);

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			
			//method to check submission by descending order
			Translator.action().sortSubmission(SubmissionName, false);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			System.out.println("EOM sortSubmission()");
          
            Thread.sleep(4000);
          
            Translator.action().translate_onGoing_submission(SubmissionName +"1", targetlanguage_1, true, false);
            Thread.sleep(2000);
            General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
            Thread.sleep(2000);
        
            Translator.action().translate_onGoing_submission(SubmissionName +"2", targetlanguage_1, true, false);
            Thread.sleep(2000);
            General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
            Thread.sleep(1000);
             Translator.action().translate_onGoing_submission(SubmissionName +"3", targetlanguage_1, true, false);
            Thread.sleep(1000);
            
          //check sorting in Trans COMPLETED tab
            
            Translator.action().Navigate(menu_completed);
			Thread.sleep(1000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(1000);
			
	       //sort submission in COMPLETED by Name
		  //method to check submission by ascending order
			Translator.action().sortSubmission(SubmissionName, true);

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			
			//method to check submission by descending order
			Translator.action().sortSubmission(SubmissionName, false);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
			}

			System.out.println("EOM sortSubmission()");
            Thread.sleep(4000);
            
            
            
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying  Submission Name in To_Claim after search");
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



