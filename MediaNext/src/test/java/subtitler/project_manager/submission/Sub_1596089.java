package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.OrganizationLocators;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

public class Sub_1596089 {
	
	
	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_1596089"+CommonElements.BROWSER+"I1";
	String sourcelanguage[] = {"en-US","fr-FR","ja-JP"};
	String Targetlanguage= "de-DE";
	String Targetlanguage_1[] = { "French (France)", "German (Germany)","Japanese (Japan)" };
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_ToClaim = "To Claim";
	String menu_Ongoing = "Ongoing";
	String menu_Completed = "Completed";
	String menu_AllJobs = "Jobs";
    String TranslatorGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1596089");
		dataSet.put("TL_test_case_description","SUB-1596089:Filter Submissions by name, Org, Status, Source language");
		dataSet.put("TL_internal_testCase_ID", "1596089");
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
			// TODO NEW IMPL OF SUBMISSION CREATION
			// Submission with multiple language
			for (int i=0;i<3;i++) {
			PM_user.action().Navigate(menu_Submission);
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_APOSTROPHE_FOLDER);
			CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName+i,sourcelanguage[i], Targetlanguage);
			Thread.sleep(2000);
			
			PM_user.action().SearchSubmisson(SubmissionName+i);
			Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+i), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			}
			
			PM_user.action().Navigate(menu_Submission);
			PM_user.action().SearchSubmisson(SubmissionName);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+0), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+1), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+2), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ToClaim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName+"1");
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_Ongoing);
			Thread.sleep(2000);
			PM_user.action().PM_onGoing_submission(SubmissionName+"1", "", true, false);
			Thread.sleep(2000);	
			
			PM_user.action().Navigate(menu_Submission);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_organizationFilter);
			Thread.sleep(1000);
			
			((JavascriptExecutor)General.driver).executeScript(
				    "arguments[0].scrollIntoView();",General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_submission_organizationFilter_dropDownOptions(OrganizationName)));
			Thread.sleep(2000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_organizationFilter_dropDownOptions(OrganizationName));
			Thread.sleep(1000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			General.action().Click(Pm_User_Submission_Locators.Locator().filters_clearFilterButton_enabled);
			Thread.sleep(3000);
			
			
			PM_user.action().Navigate(menu_Submission);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_statusFilter);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_statusFilter_dropDownOptions("Completed"));
			Thread.sleep(1000);
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+"1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			General.action().Click(Pm_User_Submission_Locators.Locator().filters_clearFilterButton_enabled);
			Thread.sleep(1000);
			
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_sourceFilter);
			Thread.sleep(1000);
			
			((JavascriptExecutor)General.driver).executeScript(
				    "arguments[0].scrollIntoView();",General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_submission_sourceFilter_dropDownOptions(sourcelanguage[0])));
			Thread.sleep(2000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_sourceFilter_dropDownOptions(sourcelanguage[0]));
			Thread.sleep(1000);
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+"0"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_sourceFilter);
			Thread.sleep(1000);
			
			((JavascriptExecutor)General.driver).executeScript(
				    "arguments[0].scrollIntoView();",General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_submission_sourceFilter_dropDownOptions(sourcelanguage[1])));
			Thread.sleep(2000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_sourceFilter_dropDownOptions(sourcelanguage[1]));
			Thread.sleep(1000);
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+"1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_sourceFilter);
			Thread.sleep(1000);
			
			((JavascriptExecutor)General.driver).executeScript(
				    "arguments[0].scrollIntoView();",General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_submission_sourceFilter_dropDownOptions(sourcelanguage[2])));
			Thread.sleep(2000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_sourceFilter_dropDownOptions(sourcelanguage[2]));
			Thread.sleep(1000);
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+"2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().filters_clearFilterButton_enabled);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_organizationFilter);
			Thread.sleep(1000);
			
			((JavascriptExecutor)General.driver).executeScript(
				    "arguments[0].scrollIntoView();",General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_submission_organizationFilter_dropDownOptions(OrganizationName)));
			Thread.sleep(2000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_organizationFilter_dropDownOptions(OrganizationName));
			Thread.sleep(1000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_statusFilter);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_statusFilter_dropDownOptions("Completed"));
			Thread.sleep(1000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_sourceFilter);
			Thread.sleep(1000);
			
			((JavascriptExecutor)General.driver).executeScript(
				    "arguments[0].scrollIntoView();",General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_submission_sourceFilter_dropDownOptions(sourcelanguage[0])));
			Thread.sleep(2000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_submission_sourceFilter_dropDownOptions(sourcelanguage[1]));
			Thread.sleep(1000);
			
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void CreateSubmisson_Step5_MetaData_MultiLanguages(String DueDate,String SubmissionName,String sourcelanguage2,String Targetlanguage) throws Exception {
		
		System.out.println("INSIDE METHOD CreateSubmisson_Step5_MetaData_MultiLanguages()\n");
		
		System.out.println("DATE IMPLEMENTATION");
    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
		String newdate = PM_user.action().GetDataPlus(Integer.valueOf(DueDate));

		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
    	Thread.sleep(1000);
    	PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
    	Thread.sleep(1000);
    	PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
    	Thread.sleep(2000);		
    	
    	PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_arrow);
    	Thread.sleep(1000);		
    	
    	PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_year(newdate.substring(9, 13)));
    	Thread.sleep(1000);		
    	
    	PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_month(newdate.substring(0, 3).toUpperCase()));
    	Thread.sleep(1000);		
    	if(newdate.substring(5, 6).contentEquals("0")){
    		System.out.println("IF--->"+newdate.substring(6, 7));
    		PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(6, 7)));
    		
    		Thread.sleep(1000);		
    	}else{
    		System.out.println("ELSE--->"+newdate.substring(5, 7));
    		PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(5, 7)));
    		Thread.sleep(1000);		
    	}

    	System.out.println("DATE IMPLEMENTATION END--------------");

    	PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input, SubmissionName);
    	Thread.sleep(3000);
    	PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
    	Thread.sleep(2000);
    	PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, sourcelanguage2);
    	Thread.sleep(3000);
    	PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(sourcelanguage2));
    	Thread.sleep(1000);
		
		System.out.println("Start for loop");

		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_TargetLanguage_input);
		Thread.sleep(PM_user.action().defaultWaitPeriod*2);
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_TargetLanguage_input);
		Thread.sleep(PM_user.action().defaultWaitPeriod*2);
		Thread.sleep(2000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_TargetLanguage_input, Targetlanguage);
		Thread.sleep(2000);
		   

		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_SourceLanguageFrom_dropdown(Targetlanguage));
		 Thread.sleep(PM_user.action().defaultWaitPeriod*2);
		 Thread.sleep(2000);
	
		System.out.println("End for loop)");

		Thread.sleep(2000);
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_Create_Button);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_Create_Button);
		Thread.sleep(25000);
	
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		   System.out.println("EOM CreateSubmisson_Step5_MetaData_MultiLanguages()\n");
	}
	

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+"1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
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
