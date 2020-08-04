package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author swati thakare
 * This test case verifies that user cannot create /rename a submission to existing submission name.
 * precondition : Some submission should be created before test.
 */
public class Sub_1766489 {

	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	String SubmissionName = "SUB_1766489"+CommonElements.BROWSER+"O1";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String sourcelanguage = "en-US - English (United States)";
	String newsubmission = "SUB_1766489"+CommonElements.BROWSER+"O2";
	

	@BeforeMethod
	public void setup() throws Exception
	{General.action().startSystem("SUB_1766489");
		dataSet.put("TL_test_case_description", "SUB-1766489:user cannot create /rename a submission to existing submission name.");
		dataSet.put("TL_internal_testCase_ID", "1766489");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)	throws Exception {
		
		    // PM login
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_TOPCENTER_SRT_FOLDER);
			CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
		
			//VERIFY SUBMISSION NAME
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			//create existing submission name
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_TOPCENTER_SRT_FOLDER);
			CreateSubmisson_Step4_MetaData("10",SubmissionName,"en-US","de-DE");
			
			assertion = Verify.action().verifyTextPresent("Cannot create submission. There is a submission with the same name already.", 5);
			if(assertion==false)
				report("f","Assertion failed while updating the submission name");
			Thread.sleep(2000);
			
		
			PM_user.action().Navigate(menu_Submission);
			// create new submission 
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_TOPCENTER_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,newsubmission,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			
			//VERIFY SUBMISSION NAME
			PM_user.action().SearchSubmisson(newsubmission);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(newsubmission), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
	    	
	    	//try to Update Submission name
	    	PM_user.action().SearchSubmisson(SubmissionName);
	    	Thread.sleep(2000);
	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_allCheck);
	    	Thread.sleep(2000);
	    		    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
	    	Thread.sleep(2000);
	    	
	    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Submission_editSubmissionName_input);
	    	Thread.sleep(1000);
	    	General.action().type(Pm_User_Submission_Locators.Locator().PM_Submission_editSubmissionName_input,newsubmission);
	    	Thread.sleep(2000);
	    	General.action().Click(CommonLocartors.Locator().Update_button);
	    	Thread.sleep(2000);
	    	
	    	assertion = Verify.action().verifyTextPresent("Cannot update submission. There is a submission with the same name already.", 5);
			if(assertion==false)
				report("f","Assertion failed while updating the submission name");
			  Thread.sleep(2000);
	    	
	 
  	
	    	
	}			
	

	private void CreateSubmisson_Step4_MetaData(String DueDate,String SubmissionName,String SourceLanguage,String TargetLanguage) throws Exception {
		
		System.out.println("INSIDE method CreateSubmisson_Step4_MetaData()\n"); 
    	//TODO NOT REQUIRED
//    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
//    	Thread.sleep(1000);
//    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
//    	Thread.sleep(1000);
    	
    	System.out.println("DATE IMPLEMENTATION");
    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
		String newdate = PM_user.action().GetDataPlus(Integer.valueOf(DueDate));
		System.out.println(newdate);
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
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
    	Thread.sleep(2000);
    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, SourceLanguage);
    	Thread.sleep(3000);
//    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
//    	Thread.sleep(1000);
    	System.out.println("CLICKED");
//    	((JavascriptExecutor) General.driver).executeScript(
//                "arguments[0].scrollIntoView();", General.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage).toString()));
    	
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage));
    	Thread.sleep(3000);
    	if(TargetLanguage!=""){
    		System.out.println("TARGET LANGUAGE NOT NULL");
    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
	    	Thread.sleep(1000);
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input, TargetLanguage);
	    	Thread.sleep(1000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(TargetLanguage));
	    	Thread.sleep(4000);
    	}
    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
    	Thread.sleep(1000);
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
    	Thread.sleep(500);
	    System.out.println("EOM CreateSubmisson_Step4_MetaData()\n");

	}

	public void assertion() throws Exception {
													   	
		assertion = Verify.action().verifyTextPresent("Cannot update submission. There is a submission with the same name already.", 5);
		if(assertion==false)
			report("f","Assertion failed while updating the submission name");
		else{
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
