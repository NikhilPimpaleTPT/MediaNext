package subtitler.project_manager.Open_Jobs;

/**
 * 
 * @author pvaidya
 *
 */

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
	import modules.Translator;
	import modules.Verify;

	public class Sub_785826 {
		
		
		Boolean assertion = true;
		LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

		String SubmissionName = "SUB_785826"+CommonElements.BROWSER+"J1_";
	    String OrganizationName = "Subtitle_Common_orgs";
		String WorkflowName = "One_Step_Workflow";
		String menu_Submission = "Submissions";
		String TranslatorGroupName = "Common_group";
		String menu_AllJobs = "Jobs";
		String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
		String JobHistory_Step="trans";
		String JobHistory_Status="Claimed";
		String JobHistory_User="Maya Gurnale";
		String UserName = "Maya Gurnale";
		String GroupName = "Common_group";
		String JobHistoryHeaderName_Step ="Step";
		String JobHistoryHeaderName_Status ="Status";
		String JobHistoryHeaderName_Date ="Date";
		String JobHistoryHeaderName_User ="User";
		
		
		@BeforeMethod
		public void setup() throws Exception {
			General.action().startSystem("Sub_785826");
			dataSet.put("TL_test_case_description", "Sub_785826  :Job history");
			dataSet.put("TL_internal_testCase_ID", "785826");
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
				for(int i=1;i<=2;i++) {
				// TODO NEW IMPL OF SUBMISSION CREATION
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
				System.out.println("filePath------>" + filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName+i,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				Thread.sleep(2000);
				
				}
				
				PM_user.action().SearchSubmisson(SubmissionName+"1");
				Thread.sleep(2000);

				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+"1"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
				}
				
			
				Thread.sleep(1000);
		        Translator.action().Navigate(menu_AllJobs);
		        Thread.sleep(3000);
				PM_user.action().PM_Submission_Reassignment(SubmissionName+"1", true, UserName, false, GroupName);
				Thread.sleep(2000);
				PM_user.action().SearchSubmisson_andCheck(SubmissionName+"1");
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_JobHistory, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Job History Icon after search");
					}
				
				Thread.sleep(2000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_AllJobs_JobHistory);
				Thread.sleep(2000);//PM_AllJobs_JobHistoryDialogBox_AllIcons
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_JobHistoryDialogBox_AllHeaderIcons(JobHistoryHeaderName_Step), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Dialog Box All Header Icons after Clicking on JobHistory");
					
				}
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_JobHistoryDialogBox_AllHeaderIcons(JobHistoryHeaderName_Status), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Dialog Box All Header Icons after Clicking on JobHistory");
					
				}
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_JobHistoryDialogBox_AllHeaderIcons(JobHistoryHeaderName_Date), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Dialog Box All Header Icons after Clicking on JobHistory");
					
				}
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_JobHistoryDialogBox_AllHeaderIcons(JobHistoryHeaderName_User), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Dialog Box All Header Icons after Clicking on JobHistory");
					
				}
				
				assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_JobHistory_DialogBox_StepNo_Icon, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Dialog Box Step No Icon Present");
					
				}
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_User_AllJobs_JobHistory_DialogBox_Icons(JobHistory_Step,JobHistory_Status,JobHistory_User), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Dialog Box All Icons after Clicking on JobHistory");
				}
				
			    
				Thread.sleep(4000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_AllJobs_JobHistory_DialogBox_CancelButton);
				Thread.sleep(2000);
				
				
				
		        Translator.action().Navigate(menu_Submission);
				Thread.sleep(2000);
		        Translator.action().Navigate(menu_AllJobs);
		        Thread.sleep(3000);
				PM_user.action().SearchSubmisson(SubmissionName);
				Thread.sleep(2000);
				
				Thread.sleep(4000);
				if (Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_checkbox_checked,5)) {
					System.out.println("INSIDE IF--------");
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_select_header_checkbox);
					Thread.sleep(1000);
					General.action().Click(Pm_User_Submission_Locators.Locator().PM_AllJobs_select_header_checkbox);
					Thread.sleep(1000);
					System.out.println("CLICK DONE------");
				}
				
				
				assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_JobHistory, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Job History Icon without selecting Submission");
					
				
				}
				if (Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_checkbox_checked,5)) {
					System.out.println("INSIDE IF--------");
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_select_header_checkbox);
					Thread.sleep(1000);
					General.action().Click(Pm_User_Submission_Locators.Locator().PM_AllJobs_select_header_checkbox);
					Thread.sleep(1000);
					System.out.println("CLICK DONE------");
				}
				
				
}
		
		
		
		
	 catch (Exception e) {
		report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	}

}
public void assertion() throws Exception {
	assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_JobHistory, 5);
	if (assertion == false) {
		report("f","Assertion failed while verifying Job History Icon without selecting Submission");
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