package admin.Additional_Testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Admin_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;
import modules.admin;

/**
 * 
 * @author Swati Thakare
 * Summary : This testcase verifies if created submissions are sorted by Due date in ascending/descending order.
 * precondition : Before test, user should create multiple number of submissions.
 */
public class Sub_1767277 {
	
	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	String SubmissionName = "Submission_1767277"+CommonElements.BROWSER+"A1";
	String sourcelanguage = "en-US";
	String targetlanguage = "de-DE";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String TranslatorGroupName = "Common_group";
	String menu_submission = "Submissions";
	String menu_toClaim = "To Claim";
    String menu_Ongoing = "Ongoing";
	String DueDate_1stRow_ascending;
	String DueDate_2stRow_ascending;
	String DueDate_3stRow_ascending;

	
    
    @BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1767277");
		dataSet.put("TL_test_case_description","SUB-1767277:sort the created submission by due date");
		dataSet.put("TL_internal_testCase_ID", "1767277");
	}
    
    @Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
    
    public void testCase(LinkedHashMap<String, String> dataSet)	throws Exception {
    	try {
			// create submission through admin
			admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			admin.action().Navigate(menu_submission);
			Thread.sleep(2000);
			
			// TODO NEW IMPL OF SUBMISSION CREATION
			
			for(int i=1;i<=3;i++) {
			admin.action().CreateSubmisson_Step1("17", "35", "80", "100");
			admin.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "", "", false);
			admin.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			admin.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			admin.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET+i, SubmissionName+i,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(1000);
			
			System.out.println("SUBMISSION CREATED--->"+SubmissionName+i);
			
			}			
			PM_user.action().Navigate(menu_submission);
			Thread.sleep(4000);
			//SEARCH SUBMISSION AND CHECK
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+1), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+2), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+3), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_AllHeaderIcons("Name"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Submission headers");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_AllHeaderIcons("Client"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Submission headers");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_AllHeaderIcons("Status"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Submission headers");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_AllHeaderIcons("Video"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Submission headers");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_AllHeaderIcons_x("Segments"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Submission headers");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_AllHeaderIcons("Source"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Submission headers");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_AllHeaderIcons("Creation Date"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Submission headers");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_AllHeaderIcons("Due Date"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Submission headers");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().AscendingSubmission_status, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Submission headers");
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_AllHeaderIcons("Due Date"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Submission headers");
			}
			PM_user.action().Navigate(menu_Ongoing);
			Thread.sleep(4000);
			PM_user.action().Navigate(menu_submission);
			Thread.sleep(4000);
	
			//Sort sub by DueDtae in Ascending order for Submission Menu
			PM_user.action().sortSubmission_dueDate(SubmissionName, true);
		    Thread.sleep(3000);
			
		    SortDueDateForAsc();
		    
		    
		  //Sort sub by DueDtae in Descending order for Submission Menu
			PM_user.action().sortSubmission_dueDate(SubmissionName, false);
		    Thread.sleep(3000);
		    
		    SortDueDateForDsc();		
    }
	
    catch (Exception e) {
		report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	}
}
    public void SortDueDateForAsc() throws Exception {
    	System.out.println("INSIDE METHOD Sorting Due Date In Ascending Order ");
    	
    	DueDate_1stRow_ascending = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_dueDate(1)).getText();
		System.out.println("CREATIONDATE##################:-" +DueDate_1stRow_ascending);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sorting_sub_with_dueDate(1,SubmissionName + "1",DueDate_1stRow_ascending), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Due Date after search");
		}

		DueDate_2stRow_ascending = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_dueDate(2)).getText();
		System.out.println("CREATIONDATE##################:-" +DueDate_2stRow_ascending);
			
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sorting_sub_with_dueDate(2,SubmissionName + "2",DueDate_2stRow_ascending), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Due Date after search");
			
		}
		DueDate_3stRow_ascending = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_dueDate(3)).getText();
		System.out.println("CREATIONDATE##################:-" +DueDate_3stRow_ascending);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sorting_sub_with_dueDate(3,SubmissionName + "3",DueDate_3stRow_ascending), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Due Date after search");
			
			System.out.println(" EOM Sorting Due Date In Ascending Order ");
			
		}
    }
    
    public void	SortDueDateForDsc() throws Exception {
    	 System.out.println("INSIDE METHOD Sorting Due Date In Descending Order ");
    	 
    	 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sorting_sub_with_dueDate(3,SubmissionName + "1",DueDate_1stRow_ascending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Due Date after search");
			}
				
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sorting_sub_with_dueDate(2,SubmissionName + "2",DueDate_2stRow_ascending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Due Date after search");
					
			}
		
			System.out.println(" EOM Sorting Due Date In Descending Order ");
    }
            
			public void assertion() throws Exception {
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sorting_sub_with_dueDate(1,SubmissionName + "3",DueDate_3stRow_ascending), 5);
				if (assertion == false) {
				report("f","Assertion failed while verifying Due Date after search");
	    		}else {
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
