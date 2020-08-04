package subtitler.Linguist.Open_Job;

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

/**
 * 
 * @author pvaidya
 *Summary:this tc is to verify 'UPDATE' button should be active after editing job settings
 *
 */

public class Sub_2630044 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_2630044"+CommonElements.BROWSER+"O1";
	String Targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String setting_updateButton_Color;
	Boolean assertion = true;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2630044");
		dataSet.put("TL_test_case_description", "SUB-2630044:'UPDATE' button stays inactive after editing job settings");
		dataSet.put("TL_internal_testCase_ID", "2630044");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			// Scenario1 test case:1 for delete submission with new status.
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			//Edit the submission, and change one settings for a job (for example, the max number of characters per line)
			Thread.sleep(1000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
			Thread.sleep(8000);
			
			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxCharPerLine_input);
			Thread.sleep(1000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxCharPerLine_input);
			Thread.sleep(1000);
			 
			PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxCharPerLine_input);
			Thread.sleep(1000);
		    PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxCharPerLine_input,"50");
			Thread.sleep(2000);
			
			System.out.println("Update button Color:"+General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_settings_updateButton_color).getCssValue("background-color"));
			setting_updateButton_Color =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_settings_updateButton_color).getCssValue("background-color");		
			
			assertion = General.action().getFieldColor(setting_updateButton_Color,"#3d87bb");		
			if(assertion==false){
		    report("f","Failed at the checking Update button background color");			
			}
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
			
			
			public void assertion() throws Exception {
		        //Verify Once the change is done ,UPDATE button become active (blue)
				assertion = General.action().getFieldColor(setting_updateButton_Color,"#3d87bb");		
				if(assertion==false){
			    report("f","Failed at the checking Update button background color");			
				} else {
					report("b", "All Assertion blocked.");
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