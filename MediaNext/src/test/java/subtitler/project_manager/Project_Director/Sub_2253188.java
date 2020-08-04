package subtitler.project_manager.Project_Director;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;

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
 *Summary:verifies the date format of PD submission is 'MMM dd, y HH:mm'
 *
 */

public class Sub_2253188 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_2253188"+CommonElements.BROWSER+"G4";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_submission = "Submissions";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String menu_jobs = "Jobs";
	String dueDateOfSubmission_x;
	String CreationDateOfSubmission;
	String hours="05";
	String min="10";
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2253188");
		dataSet.put("TL_test_case_description", "Date Format for PD submission");
		dataSet.put("TL_internal_testCase_ID", "2253188");
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
		    Thread.sleep(20000);
		   
//			//Create Submission In From PD Tab
		    PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName);
		    String dueDateOfSubmission=Create_PD_Submisson_Step2_MetaData_returnDueDate(CommonElements.action().DATE_OFFSET,SubmissionName,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			System.out.println("dueDateOfSubmission:"+dueDateOfSubmission);
			PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
			Thread.sleep(2000);
			PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			CreationDateOfSubmission=PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			System.out.println("CreationDateOfSubmission"+CreationDateOfSubmission);
			
			
			//Get Creation date in required formate mmm dd, yyyy
			String dueDateOfSubmission_month=dueDateOfSubmission.substring(0, 3);
			System.out.println("dueDateOfSubmission_month:"+dueDateOfSubmission_month);
			String dueDateOfSubmission_day=dueDateOfSubmission.substring(5, 7);
			System.out.println("dueDateOfSubmission_day:"+dueDateOfSubmission_day);
			String dueDateOfSubmission_year=dueDateOfSubmission.substring(9, 13);
			System.out.println("dueDateOfSubmission_day:"+dueDateOfSubmission_year);
			
			dueDateOfSubmission_x=dueDateOfSubmission_month+" "+dueDateOfSubmission_day+", "+dueDateOfSubmission_year+" "+hours+":"+min;
			System.out.println("dueDateOfSubmission_year:"+dueDateOfSubmission_x);
			
			
			
			PD_PM_user.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
			Thread.sleep(1000);
			PD_PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
			
			//Verify created submission in pending status
			PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				
			}
			
			//Verify Submission creation and due date
			assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName,dueDateOfSubmission_x), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName date after Search");
			}
			
			assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName,CreationDateOfSubmission), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName date after Search");
			}
			
		} catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
      }
	}
	
	
	 public String Create_PD_Submisson_Step2_MetaData_returnDueDate(String DueDate,String SubmissionName,String OrganizationName,String SourceLanguage,String TargetLanguage) throws Exception {
	    	System.out.println("INSIDE method Create_PD_Submisson_Step2_MetaData()\n"); 
	    	
	    	System.out.println("DATE IMPLEMENTATION");
	    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
			String dueDate = PD_PM_user.action().GetDataPlus(Integer.valueOf(DueDate));
			System.out.println(dueDate);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);
	    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(2000);		
	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_arrow);
	    	Thread.sleep(1000);		
	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_year(dueDate.substring(9, 13)));
	    	Thread.sleep(1000);		
	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_month(dueDate.substring(0, 3).toUpperCase()));
	    	Thread.sleep(1000);		
	    	if(dueDate.substring(5, 6).contentEquals("0")){
	    		System.out.println("IF--->"+dueDate.substring(6, 7));
	    		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(dueDate.substring(6, 7)));
	    		
	    		Thread.sleep(1000);		
	    	}else{
	    		System.out.println("ELSE--->"+dueDate.substring(5, 7));
	    		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(dueDate.substring(5, 7)));
	    		Thread.sleep(1000);		
	    	}

	    	System.out.println("DATE IMPLEMENTATION END--------------");
	    	
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input, SubmissionName);
	    	Thread.sleep(3000);
	    	
	    	Thread.sleep(1000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueTime_input);
	    	Thread.sleep(5000);	
	    	General.action().Enter_keyEnvents(KeyEvent.VK_BACK_SPACE);
	        Thread.sleep(5000);
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueTime_input, hours);
	    	Thread.sleep(3000);
	    	General.action().Enter_keyEnvents(KeyEvent.VK_RIGHT);
	        Thread.sleep(5000);
	        General.action().Enter_keyEnvents(KeyEvent.VK_BACK_SPACE);
	        Thread.sleep(5000);
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueTime_input, min);
	    	Thread.sleep(3000);

	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input);
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
			return dueDate;
	    }


	
	public void assertion() throws Exception {
		
		//Verify Submission creation and due date
		assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName,dueDateOfSubmission_x), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName date after Search");
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

	