package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;
import modules.admin;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This testcase verifies if positioning tag are taking into account for TTML file format.
 *Preconditions :We should have TTML file with reasons mentioned before test (File should have segments Top/Bottom aligned)
 */  

public class Sub_1266214 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	String SubmissionName = "SUB_1266214"+CommonElements.BROWSER+"Y1";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";

	@BeforeMethod
	public void setup() throws Exception {General.action().startSystem("SUB_1266214");
		dataSet.put("TL_test_case_description", "SUB-1266214:Submission with TTML file to check positioning tag");
		dataSet.put("TL_internal_testCase_ID", "1266214");
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
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
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
			
			//CLICK ON TO CLAIM AND CLAIM SUBMISSION
			PM_user.action().Navigate(menu_to_claim);
	        Thread.sleep(1000);
	        PM_user.action().PM_ToClaim(SubmissionName);
	        Thread.sleep(1000);
	        //GO TO ONGOING AND OPEN SUBMISSION
	        PM_user.action().Navigate(menu_ongoing);
	        Thread.sleep(1000);
	        Pm_Complete_trans_Submission(SubmissionName,"trans");
			Thread.sleep(2000);
	        
			//VERIFY UPWARD ARROW IS DISPLAY FOR TOP CENTER SEGMENTS
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_Arrow_Upward(2), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying arrow upward present on subtitle grid");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_Arrow_Upward(3), 5);
		    if (assertion == false) {
			report("f","Assertion failed while verifying arrow upward present on subtitle grid");
			}	

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void Pm_Complete_trans_Submission(String SubmissionName, String status) throws Exception {
		
		 System.out.println("INSIDE Pm_Complete_trans_Submission  method()");
		 
		    PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, status)))
				{
				System.out.println("INSIDE IF--------");
				 PM_user.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
				}
			  Thread.sleep(6000);
			  PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			  Thread.sleep(6000);
			  
			  
		     

	}
	
	
	public void CreateSubmisson_Step4_MetaData(String DueDate,String SubmissionName,String SourceLanguage,String TargetLanguage) throws Exception {
    	System.out.println("INSIDE method CreateSubmisson_Step4_MetaData()\n"); 
    	System.out.println("DATE IMPLEMENTATION");
    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
		String newdate = PM_user.action().GetDataPlus(Integer.valueOf(DueDate));
		System.out.println(newdate);
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
    	PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, SourceLanguage);
    	Thread.sleep(3000);

    	System.out.println("CLICKED");
    	PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage));
    	Thread.sleep(3000);
    	if(TargetLanguage!=""){
    		System.out.println("TARGET LANGUAGE NOT NULL");
    		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
	    	Thread.sleep(1000);
	    	PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input, TargetLanguage);
	    	Thread.sleep(1000);
	    	PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(TargetLanguage));
	    	Thread.sleep(4000);
    	}
    	PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
    	Thread.sleep(1000);
    	PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
    	Thread.sleep(2000);
  
	    System.out.println("EOM CreateSubmisson_Step4_MetaData()\n");
    }

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_Arrow_Upward(5), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying arrow upward present on subtitle grid");
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
