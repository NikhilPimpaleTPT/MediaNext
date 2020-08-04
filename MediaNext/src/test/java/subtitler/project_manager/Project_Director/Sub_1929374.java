package subtitler.project_manager.Project_Director;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
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
import modules.Translator;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:Create submission in Project Director tab and complete the workflow round trip.
 *
 */

public class Sub_1929374 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String SubmissionName = "SUB_1929374"+CommonElements.BROWSER+"C1";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_completed = "Completed";
	String CreationDateOfSubmission;
	String dueDateOfSubmission_x;
	String submissionID;
	File filepath;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1929374");
		dataSet.put("TL_test_case_description", "PD integration");
		dataSet.put("TL_internal_testCase_ID", "1929374");
	}
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			    // login using PM Crdentials 
			    General.action().login(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
			    Thread.sleep(20000);
			    
			    //Create Submission In PD
				PM_user.action().Navigate(menu_Submission);
				// TODO NEW IMPL OF SUBMISSION CREATION
				PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName);
				//Enter all the data and get due date and creation date, time
				String dueDateOfSubmission = Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				System.out.println("dueDateOfSubmission:"+dueDateOfSubmission);
				Thread.sleep(2000);
				
				//Get Creation date in required formate mmm dd, yyyy
				String dueDateOfSubmission_month=dueDateOfSubmission.substring(0, 3);
				System.out.println("dueDateOfSubmission_month:"+dueDateOfSubmission_month);
				String dueDateOfSubmission_day=dueDateOfSubmission.substring(5, 7);
				System.out.println("dueDateOfSubmission_day:"+dueDateOfSubmission_day);
				String dueDateOfSubmission_year=dueDateOfSubmission.substring(9, 13);
				System.out.println("dueDateOfSubmission_day:"+dueDateOfSubmission_year);
				
				dueDateOfSubmission_x=dueDateOfSubmission_month+" "+dueDateOfSubmission_day+", "+dueDateOfSubmission_year;
				System.out.println("dueDateOfSubmission_year:"+dueDateOfSubmission_x);
				
				//TODO Steps are changed form 2.7.0 RC1
				//Enter other details 
//				PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
//				PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
				//Enter other details 
				PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				CreationDateOfSubmission=PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				System.out.println("CreationDateOfSubmission"+CreationDateOfSubmission);
				
				
				//Verify created submission in pending status
				PM_user.action().SearchSubmisson(SubmissionName);
				Thread.sleep(2000);
				PM_user.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_Filter_Status);
				Thread.sleep(1000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_Filter_Status);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_Filter_Status_Options("Pending"));
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
				if (assertion == false) {
					report("f", "Assertion failed while verifying SubmissionName  after Search");
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
				
				//Search and check submission and Verify for CRETE IN PD BUTTON
				PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_cancelButton);
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
				if (assertion == false) {
					report("f", "Assertion failed while verifying SubmissionName  after Search");
				}
				
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button);
				Thread.sleep(2000);
				
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_createSubmissionInPDButton);
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
				
				submissionID =General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(1,3)).getText();
				System.out.println("Submission ID:"+submissionID);
				
				
				Thread.sleep(2000);
				PD_PM_user.action().Open_PD_Instance_URL();
				Thread.sleep(25000);
				
				//******Not Needed*******
				/*PD_PM_user.action().logIn_to_PD(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
				Thread.sleep(2000);*/
				//TODO ******Not Needed*******
				//PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				//Thread.sleep(4000);
				//PD_PM_user.action().editSubmission_addCustomAttributes("m","a","x");
				//Thread.sleep(4000);
	
				PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				PD_PM_user.action().analyse_PD_submission();
				Thread.sleep(5000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_wordCount("39"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName count");
				}
				Thread.sleep(1000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_onHoldButton);
		    	Thread.sleep(4000);
				Thread.sleep(1000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
		    	Thread.sleep(4000);
		    	PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
		    	
				
				PD_PM_user.action().createBudget_PD_submission(submissionID,CommonElements.action().REVENUE,CommonElements.action().RATE_TRANS,CommonElements.action().RATE_REVIEW);
				Thread.sleep(6000);
				
				Thread.sleep(1000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_onHoldButton);
		    	Thread.sleep(4000);
				Thread.sleep(1000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
		    	Thread.sleep(4000);
		    	PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_Status(submissionID,"In Progress"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName status after Search");
				}
				
				PD_PM_user.action().logOut_to_PD();
				Thread.sleep(2000);
				
				PD_PM_user.action().logIn_to_PD(CommonElements.action().PD_VENDOR1_username,CommonElements.action().PD_VENDOR1_password);
				Thread.sleep(6000);
				
				General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_available);
			    Thread.sleep(1000);
			    General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_available);
			    Thread.sleep(2000);
			    PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				PD_PM_user.action().vendor1_jobInfo(true,true,"German (Germany)");
				Thread.sleep(80000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_fileManagement_link(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName status after Search");
				}
		
				PD_PM_user.action().PD_submission_vendor1_TTML_link(SubmissionName);
				Thread.sleep(2000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().vendor1_submissionName_header(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName status after Search");
				}
		
				PD_PM_user.action().PD_submission_vendor1_onGoing_submission(true,false);
				Thread.sleep(2000);
				PD_PM_user.action().PD_submission_vendor1_completed();
				Thread.sleep(2000);
				PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_progressBar, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName 100% completed");
				}
				
				System.out.println("Progress Bar Color:"+General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_completed_color).getCssValue("background-color"));
				assertion=General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_completed_color).getCssValue("background-color").contains("(82, 192, 85");
				if (assertion == false) {
					report("f","Assertion failed while verifying submission completed status ");
	            }
				
				PD_PM_user.action().logOut_to_PD();
				Thread.sleep(2000);
				
				PD_PM_user.action().logIn_to_PD(CommonElements.action().PD_VENDOR2_username,CommonElements.action().PD_VENDOR2_password);
				Thread.sleep(4000);
			    General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_available);
			    Thread.sleep(2000);
			    PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				PD_PM_user.action().vendor2_jobInfo(true,true,"German (Germany)");
				Thread.sleep(4000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_fileManagement_link(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName status after Search");
				}
				PD_PM_user.action().PD_submission_vendor2_TTML_link(SubmissionName);
				Thread.sleep(2000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().vendor2_submissionName_header(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName status after Search");
				}
		
				PD_PM_user.action().PD_submission_vendor2_onGoing_submission(true,false);
				Thread.sleep(2000);
				PD_PM_user.action().PD_submission_vendor2_completed();
				Thread.sleep(2000);
				PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().vendor2_submissionName_completedChecked(submissionID), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName  completed");
				}
				Thread.sleep(10000);
				PD_PM_user.action().logOut_to_PD();
				Thread.sleep(5000);
				
				PD_PM_user.action().logIn_to_PD(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
				Thread.sleep(6000);
				
				PD_PM_user.action().PD_submission_pm_completed();
				Thread.sleep(2000);
				PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_pm_progressBar(submissionID), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName 100% completed");
				}
				
				System.out.println("Progress Bar Color:"+General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().PD_submission_pm_completed_color).getCssValue("background-color"));
				assertion=General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_completed_color).getCssValue("background-color").contains("(82, 192, 85");
				if (assertion == false) {
					report("f","Assertion failed while verifying submission completed status ");
	            }
				
				General.driver.close();
				Thread.sleep(2000);
				//Switch to the pop up window 0
				PD_PM_user.action().switchToPopupWindow(0);
				Thread.sleep(5000);
				
				PD_PM_user.action().driver.navigate().refresh();
				Thread.sleep(5000);
				
				Thread.sleep(2000);
				General.action().logoutMethod();
				Thread.sleep(10000);
				
				//TODO for tpt auth page 
//				//https://sso-stg.transperfect.com/Account/Logout
//				//To log out from tpt auth page
//				PD_PM_user.action().tptAuthLogoutPage();
				
				//TODO the 1st window redirect to tpt auth page so need to launch media next again
				System.out.println("Media.Next URL-->"+General.action().properties.getProperty("BrowserURL"));
				PD_PM_user.action().driver.get(General.action().properties.getProperty("BrowserURL"));
				
				PD_PM_user.action().driver.navigate().refresh();
				Thread.sleep(8000);
				
				//When we launch URL and refresh it directly open the the user , so no need to enter creadentials 
//				General.action().login(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
//				Thread.sleep(20000);
				General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
				Thread.sleep(2000);
				
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				PM_user.action().SearchSubmisson(SubmissionName);
				Thread.sleep(2000);

				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName,"Completed"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
					
				}
				
				
				
				
				
				
				
		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
           }
 }
	
	
	
	
	

public void search_PD_submission_andClick(String option,String submissionID) throws InterruptedException {
	
	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_arrow);
	Thread.sleep(1000);
//	General.action().ClearInputvalues(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
//	Thread.sleep(1000);
	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_arrow);
	Thread.sleep(2000);	
	
	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_options(option));
	Thread.sleep(1000);
	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_options(option));
	Thread.sleep(5000);	
	
	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_input);
	Thread.sleep(1000);
	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_input);
	Thread.sleep(5000);
	General.action().ClearInputvalues(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_input);
	Thread.sleep(1000);
	General.action().type(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_input, submissionID);
	Thread.sleep(5000);
	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_searchSubmission_firstRow(submissionID));
	Thread.sleep(1000);
	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_searchSubmission_firstRow(submissionID));
	Thread.sleep(5000);
	
	
}
	
	
	public String Create_PD_Submisson_Step2_MetaData(String DueDate,String SubmissionName,String OrganizationName,String SourceLanguage,String TargetLanguage) throws Exception {
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
	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName,"Completed"), 5);
	if (assertion == false) {
	report("f","Assertion failed while verifying SubmissionName Status after Search");
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
