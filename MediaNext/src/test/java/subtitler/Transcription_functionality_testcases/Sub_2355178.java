package subtitler.Transcription_functionality_testcases;

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
 *Summary: duplicate characters can not be added
 *
 */

public class Sub_2355178 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_2355178"+CommonElements.BROWSER+"A1";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String characters="Test";
    Boolean assertion = true;
    
    @BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_2355178");
		dataSet.put("TL_test_case_description","Sub_2355178: Duplicate Characters can not be added");
		dataSet.put("TL_internal_testCase_ID", "2355178");
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
			PM_user.action().Navigate(menu_submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			PM_transcription_onGoing_Submission(SubmissionName,targetlanguage);
		    Thread.sleep(2000);
		    
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void PM_transcription_onGoing_Submission(String SubmissionName, String target) throws Exception {

		System.out.println("INSIDE PM_transcription_onGoing_ReadingSpeed_Submission()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
        Thread.sleep(8000);
        
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
        
        General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_button);
		Thread.sleep(2000);
        General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_button);
		Thread.sleep(2000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_addButton);
		Thread.sleep(2000);
        General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_addButton);
		Thread.sleep(2000);
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input,characters);
		Thread.sleep(2000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_AddCharacters_addButton);
		Thread.sleep(2000);
	    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_AddCharacters_addButton);
		Thread.sleep(2000);
		
		//To add same character
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_addButton);
		Thread.sleep(2000);
        General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_addButton);
		Thread.sleep(2000);
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input,characters);
		Thread.sleep(2000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_AddCharacters_addButton);
		Thread.sleep(2000);
	    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_AddCharacters_addButton);
		Thread.sleep(2000);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_AddCharacters_addButton_disabled, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying ADD button is disabled ");
		}
		
		//Creating Character with same name should not be allowed.
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_AddCharacters_Error, 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying error - Character name already exists");
		}
        
	}

	public void assertion() throws Exception {
		
		//Verify Creating Character with same name should not be allowed.
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_AddCharacters_Error, 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying error - Character name already exists");
		}
		else {
			report("p", "Assertion is passed.");
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