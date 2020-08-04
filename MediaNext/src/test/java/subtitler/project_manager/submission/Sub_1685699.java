package subtitler.project_manager.submission;

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

public class Sub_1685699 {
	
	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_1685699"+CommonElements.BROWSER+"O2";
	String sourcelanguage[] = { "German (Greece)","English (Hong Kong)","English (Israel)","Spanish (Belize)","Spanish (Equatorial Guinea)","Filipino","French (Congo)","French (Guadeloupe)","French (Guyana)","French (Haiti)","French (Lebanon)","French (Madagascar)","French (Martinique)","French (Réunion)","French (Seychelles)","French (Saint Vincent and the Grenadines)","Haitian (Haiti)","Italian (San Marino)","Central Khmer (Cambodia)","Kannada (India)","Kirghiz (Kyrgyzstan)","Lao (Laos)","Burmese (Myanmar)","Pashto (Afghanistan)","Portuguese (Angola)","Sinhalese (Sri Lanka)","Sinhalese (Sri Lanka)","Tajik (Tajikistan)","Tigrinya (Eritrea)","Tigrinya (Ethiopia)","Turkmen (Turkmenistan)","Uzbek (Uzbekistan)"};
	String Targetlanguage[] = { "German (Greece)","English (Hong Kong)","English (Israel)","Spanish (Belize)","Spanish (Equatorial Guinea)","Filipino","French (Congo)","French (Guadeloupe)","French (Guyana)","French (Haiti)","French (Lebanon)","French (Madagascar)","French (Martinique)","French (Réunion)","French (Seychelles)","French (Saint Vincent and the Grenadines)","Haitian (Haiti)","Italian (San Marino)","Central Khmer (Cambodia)","Kannada (India)","Kirghiz (Kyrgyzstan)","Lao (Laos)","Burmese (Myanmar)","Pashto (Afghanistan)","Portuguese (Angola)","Sinhalese (Sri Lanka)","Sinhalese (Sri Lanka)","Tajik (Tajikistan)","Tigrinya (Eritrea)","Tigrinya (Ethiopia)","Turkmen (Turkmenistan)","Uzbek (Uzbekistan)"};
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_AllJobs = "Jobs";
    String TranslatorGroupName = "Common_group";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1685699");
		dataSet.put("TL_test_case_description","SUB-1685699:Synchronize PD language list with GL PLay");
		dataSet.put("TL_internal_testCase_ID", "1685699");
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
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			// Submission with multiple language
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_APOSTROPHE_FOLDER);
			CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName, sourcelanguage, Targetlanguage);
			Thread.sleep(2000);
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void CreateSubmisson_Step5_MetaData_MultiLanguages(String DueDate,String SubmissionName,String [] SourceLanguage,String [] Targetlanguage) throws Exception {
		
		System.out.println("INSIDE METHOD CreateSubmisson_Step5_MetaData_MultiLanguages()\n");
		
		System.out.println("DATE IMPLEMENTATION");
    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
		String newdate = PM_user.action().GetDataPlus(Integer.valueOf(DueDate));

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
    	Thread.sleep(1000);
    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
    	Thread.sleep(1000);
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
    	Thread.sleep(2000);		
    	
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_arrow);
    	Thread.sleep(1000);		
    	
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_year(newdate.substring(9, 13)));
    	Thread.sleep(1000);		
    	
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_month(newdate.substring(0, 3).toUpperCase()));
    	Thread.sleep(1000);		
    	if(newdate.substring(5, 6).contentEquals("0")){
    		System.out.println("IF--->"+newdate.substring(6, 7));
    		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(6, 7)));
    		
    		Thread.sleep(1000);		
    	}else{
    		System.out.println("ELSE--->"+newdate.substring(5, 7));
    		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(5, 7)));
    		Thread.sleep(1000);		
    	}

    	System.out.println("DATE IMPLEMENTATION END--------------");

    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input, SubmissionName);
    	Thread.sleep(3000);
    	
    	
    	for(String language : SourceLanguage) {	
    		
    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
		Thread.sleep(General.action().defaultWaitPeriod*2);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
		Thread.sleep(General.action().defaultWaitPeriod*2);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, language);
	    Thread.sleep(2000);    		
    		
    	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(language), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying SubmissionName  after Search");
		}
		Thread.sleep(2000);    	
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
		Thread.sleep(2000);
		
    	}
		
		System.out.println("Start for loop");
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input);
		Thread.sleep(2000);
		for(String language : Targetlanguage) {	
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_TargetLanguage_input);
		    Thread.sleep(General.action().defaultWaitPeriod*2);
		    Thread.sleep(2000);
		    General.action().Click(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_TargetLanguage_input);
		    Thread.sleep(General.action().defaultWaitPeriod*2);
		    Thread.sleep(2000);
		    General.action().type(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_TargetLanguage_input, language);
			Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_SourceLanguageFrom_dropdown(language), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying target languge");
			}
			Thread.sleep(2000);    	
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_TargetLanguage_input);
			Thread.sleep(4000);

		}
		
		
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_SourceLanguageFrom_dropdown(Targetlanguage[31]), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying target languge ");
		} else {
			report("p", "All Assertion passed.");
		}
	}

	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(
				dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}
}
