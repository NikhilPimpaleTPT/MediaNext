package subtitler.project_manager.Open_Jobs;

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

public class Sub_776191 {
	
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName ="SUB_776191"+CommonElements.BROWSER+"H1";

	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String menu_Submission = "Submissions";
	String menu_AllJobs = "Jobs";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String DueDate_1stRow_ascending;
	String DueDate_2stRow_ascending;
	String DueDate_3stRow_ascending;
	
	String DATE_OFFSET ="1";
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_776191");
		dataSet.put("TL_test_case_description","Sub_776191:Submissions and Jobs views - Sort by Due date does not work correctly ");
		dataSet.put("TL_internal_testCase_ID", "776191");
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
			
			for(int i=1;i<=3;i++) {
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
				System.out.println("filePath------>" + filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(DATE_OFFSET+i,SubmissionName+i,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				Thread.sleep(2000);
				
				System.out.println("SUBMISSION CREATED--->"+SubmissionName+i);
			}
		   
			
			//CHECK SORTING FOR Jobs
			PM_user.action().Navigate(menu_AllJobs);
			Thread.sleep(4000);
			
		    //Sort sub by DueDtae in Ascending order for Jobs Menu
	        PM_user.action().sortSubmission_dueDate(SubmissionName, true);
	    	Thread.sleep(3000);
	    	
	    	SortDueDateForAsc();
				
			
			//Sort sub by DueDtae in Descending order for Jobs Menu
	        PM_user.action().sortSubmission_dueDate(SubmissionName, false);
	    	Thread.sleep(3000);
	    	
	    	SortDueDateForDsc();
	    		
	    	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sorting_sub_with_dueDate(1,SubmissionName + "3",DueDate_3stRow_ascending), 5);
			if (assertion == false) {
			    report("f","Assertion failed while verifying Due Date after search");
	    	}
	    	
			//CHECK SORTING FOR SUBMISSIONS
			PM_user.action().Navigate(menu_Submission);
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
