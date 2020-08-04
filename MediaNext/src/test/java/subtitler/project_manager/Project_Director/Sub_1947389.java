package subtitler.project_manager.Project_Director;

import static org.testng.AssertJUnit.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.PD_Pm_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import modules.PD_PM_user;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:This testcase verifies that if user can create a PD submission with accented characters, spaces and push it PD.
 *Preconditions :Login with PM user(glplay_tdc14_pm@fakemail.com). to MediaNext
 *
 */

public class Sub_1947389 {
	
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "âîô âîô âîô âîô"+CommonElements.BROWSER+"A1";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String menu_jobs = "Jobs";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String submissionID;
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1947389");
		dataSet.put("TL_test_case_description", "Create a PD submission with accented characters in name field.");
		dataSet.put("TL_internal_testCase_ID", "1947389");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			 // login using PM Credentials 
		    General.action().login(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
		    Thread.sleep(4000);
		   
			//Create Submission In From PD Tab
		    PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,"SubmissionName");
		   
		    //Verify that if the due date has passed, the field is displayed in red with a "Date has passed" message. SUB-1468
		    String pastDate=GetDataPlus(10);
		    System.out.println("Past Date for verification:"+pastDate);
		    Thread.sleep(2000);
		    PD_PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);
	    	PD_PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);	
	    	General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input).sendKeys(pastDate);
	    	Thread.sleep(3000);		
	    	PD_PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueTime_input);
	    	Thread.sleep(3000);	
	    	PD_PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input);
	    	Thread.sleep(3000);	
		    
	    	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_error, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName status after Search");
			}
		    
			PD_PM_user.action().Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
			PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			
			//Verify created submission in pending status
			PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				
			}
			
			Thread.sleep(2000);
			PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button);
			Thread.sleep(2000);
			
			PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_createSubmissionInPDButton);
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_jobs);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			
			//Verify Submission creation and due date
			PD_PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			
			//Verify Submission status after creating 
			assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName,"New"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName status after Search");
			}
			Thread.sleep(3000);
			submissionID =General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(1,3)).getText();
			System.out.println("Submission ID:"+submissionID);
	
			assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(SubmissionName,submissionID), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName status after Search");
			}
			
			
	
		} catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
       }
	}
	
	
	  public void Create_PD_Submisson_Step2_MetaData(String DueDate,String SubmissionName,String OrganizationName,String SourceLanguage,String TargetLanguage) throws Exception {
	    	System.out.println("INSIDE method Create_PD_Submisson_Step2_MetaData()\n"); 
	    	
	    	System.out.println("DATE IMPLEMENTATION");
	    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
			String newdate = GetDataPlus(Integer.valueOf(DueDate));
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
	    	
	    	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_error, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName status after Search");
			}

	    	System.out.println("DATE IMPLEMENTATION END--------------");

	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input, SubmissionName);
	    	Thread.sleep(3000);
	    	
	    	Thread.sleep(General.action().defaultWaitPeriod*2);
	    	Thread.sleep(1000);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	    	Thread.sleep(General.action().defaultWaitPeriod*10);
	    	Thread.sleep(2000);
	    	System.out.println("CLICKED");
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
	    	Thread.sleep(2000);
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, SourceLanguage);
	    	Thread.sleep(3000);
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
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
	    	Thread.sleep(20000);
	    	
	    System.out.println("EOM Create_PD_Submisson_Step2_MetaData()\n");
	    }
	  
	  public static String GetDataPlus(Integer Days) throws Exception {
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -Days);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy", Locale.getDefault());
	
			String monthParsed = dateFormat.format(cal.getTime());
			return monthParsed;
		}
	    

	
	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(SubmissionName,submissionID), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName status after Search");
		}else {
		report("p", "All assertions passed");
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

	
	
