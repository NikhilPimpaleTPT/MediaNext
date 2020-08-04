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

public class Sub_653979 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
			
			
			String SubmissionName = "SUB_653979_SiÃ…Â�ng phÃƒÂ³Ã‚"+CommonElements.BROWSER+"M1";
	        String OrganizationName = "Subtitle_Common_orgs";
			String WorkflowName = "Two_Step_Workflow";
			String fileDirName = "common";
			String menu_Submission = "Submissions";
			String TranslatorGroupName = "Common_group";
			String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
			
			@BeforeMethod
			public void setup() throws Exception {
				General.action().startSystem("Sub_653979");
				dataSet.put("TL_test_case_description","Sub_653979:Create submission with submission name using accented characters.");
				dataSet.put("TL_internal_testCase_ID", "653979");
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
					PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", true);
					System.out.println("filePath------>" + filePath);
					PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
					PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
					PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
					Thread.sleep(2000);
					PM_user.action().SearchSubmisson("SUB_653979_Si");
					Thread.sleep(2000);
					
					//TODO this assertion will be fail due to bug id SUB-610

					assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying SubmissionName  after Search");
					}
					
					
				} catch (Exception e) {
					report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				}

			}
			public void assertion() throws Exception {
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
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
				TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
				if (result == "f")
					assertTrue(false);

			}
		}


