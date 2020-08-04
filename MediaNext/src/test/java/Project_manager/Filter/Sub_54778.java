package Project_manager.Filter;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import locators.UserLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;
import modules.admin;
/**
 * 
 * @author pvaidya
 * Summary : This Test Case Is Remove segment count column from To Claim, Ongoing, Completed folder
 * Preconditions : Before test, user should create multiple number of submissions.
 */
public class Sub_54778 {



Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 
	 String sourcelanguage = "en-US - English (United States)";
	 String targetlanguage = "German (Germany)";
	 String SubmissionName = "Sub_54778_@_P"+CommonElements.BROWSER+"QA1_@_";
	 String SubmissionName_search = "Sub_54778";
	 String OrganizationName = "Subtitle_Common_orgs";
	 String WorkflowName = "One_Step_Workflow";
	 String fileName = "common";
	 String menu = "Submissions";
	 String TranslatorGroupName="Common_group";
	 String menu_toClaim = "To Claim";
	 String menu_Ongoing = "Ongoing";
	 String menu_completed = "Completed";
	 String menu_allJobs = "Jobs";
	 String SubmissionName_StartWith="Sub_54778_@_P";////Should be stat of submission name
	 String SubmissionName_contain="54778_@_P"+CommonElements.BROWSER+"QA1";//Should be content of submission name
	 
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_54778");
	dataSet.put("TL_test_case_description","SUB-54778:Filter submission by name");
	dataSet.put("TL_internal_testCase_ID", "54778");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				
				admin.action().login(CommonElements.action().PM_username,CommonElements.action().password);
				admin.action().Navigate(menu);
			
				//TODO NEW IMPL OF SUBMISSION CREATION
				for(int i=1; i<=3; i++){
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_SRT_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName+i, CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
				
				}
				//Search submission on submission menu
				PM_user.action().SearchSubmisson(SubmissionName);
				
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+1), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+2), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+3), 5);
				if(assertion==false){
					report("f","Assertion failed while verifyingSubmission Name");
				}
				
				//Search submission on  To Claim menu
				Thread.sleep(1000);
				Translator.action().Navigate(menu_toClaim);
				Thread.sleep(1000);
				Translator.action().SearchSubmisson(SubmissionName_StartWith);
				Thread.sleep(1000);
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+1), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+2), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+3), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				
				PM_user.action().PM_ToClaim(SubmissionName+1);
				
				//Search submission on Ongoing menu
				PM_user.action().Navigate(menu_Ongoing);
				
				Translator.action().SearchSubmisson(SubmissionName_StartWith);
				
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+1), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				
				Thread.sleep(1000);
		        Translator.action().translate_onGoing_submission(SubmissionName+1, targetlanguage, true, false);
				Thread.sleep(2000);
				
				//Search submission on completed menu
				
                PM_user.action().Navigate(menu_completed);
				
				PM_user.action().SearchSubmisson(SubmissionName_StartWith);
			
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+1), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				
				//Search submission on jobs menu
				PM_user.action().Navigate(menu_allJobs);
				
				PM_user.action().SearchSubmisson(SubmissionName_StartWith);

				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+2), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+3), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				
				PM_user.action().Navigate(menu);
				Thread.sleep(2000);	
				
				if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().filters_clearFilterButton_enabled, 5)) {
					PM_user.action().Click(Pm_User_Submission_Locators.Locator().filters_clearFilterButton);
		    		Thread.sleep(2000);	
		    	}

				PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		    	Thread.sleep(1000);	
		    	
		    	PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		    	Thread.sleep(1000);	
		    	PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName_search);
		    	Thread.sleep(4000);	
		    	
		    	PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
		    	Thread.sleep(1000);
		    	
		    	
		    	//Verify on the Submissions page, when typing some text on the Filter by name field and pressing enter on the keyboard, is not opens the Create Submission page. 
		    	assertion=Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_createLabel, 5);
				if(assertion==false){
					report("f","Assertion failed while verifying the create submission form functionality");
				}
		    	
		    	assertion=Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input, 5);
				if(assertion==false){
					report("f","Assertion failed while verifying the create submission form functionality");
				}
				assertion=Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed, 5);
				if(assertion==false){
					report("f","Assertion failed while verifying the create submission form functionality");
				}
				
				//Step 6 :
				//Verify that user can filter the submissions by contained text.
				//Verify same for All Jobs, To Claim, In Progress, Completed tabs also.
				
				//Step 7 : Submission itself contains special characters.
				
				PM_user.action().Navigate(menu_toClaim);
				Thread.sleep(2000);
				PM_user.action().Navigate(menu);
				Thread.sleep(2000);
				PM_user.action().SearchSubmisson(SubmissionName_contain);
				Thread.sleep(2000);
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+2), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+3), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				
				
				//Search submission on  To Claim menu
				Thread.sleep(1000);
				Translator.action().Navigate(menu_toClaim);
				Thread.sleep(1000);
				Translator.action().SearchSubmisson(SubmissionName_contain);
				Thread.sleep(1000);
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+2), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+3), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				
				PM_user.action().PM_ToClaim(SubmissionName+2);
				
				//Search submission on Ongoing menu
				PM_user.action().Navigate(menu_Ongoing);
				
				Translator.action().SearchSubmisson(SubmissionName_contain);
				
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+2), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				
				Thread.sleep(1000);
		        Translator.action().translate_onGoing_submission(SubmissionName+2, targetlanguage, true, false);
				Thread.sleep(2000);
				
				//Search submission on completed menu
				
                PM_user.action().Navigate(menu_completed);
				
				PM_user.action().SearchSubmisson(SubmissionName_contain);
			
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+2), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
				
				//Search submission on jobs menu
				PM_user.action().Navigate(menu_allJobs);
				
				PM_user.action().SearchSubmisson(SubmissionName_contain);
			
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+3), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Name");
				}
			
				
			}
			catch (Exception e){
				  report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			  }
			
	}
	
	public void assertion() throws Exception {
		//Verify submission name in all jobs which contains text from submission name
		assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+3), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Submission Name");
		}
		else{
		    report("p", "All Assertion passed.");
		}
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}
	
	public void report(String result, String notes) throws Exception
	{
	TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild,result,Subtitler_TestRail_Common_Properties.assignedTo,notes);
		if(result == "f")
			assertTrue(false);
	}
}
