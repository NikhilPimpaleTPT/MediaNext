package subtitler.project_manager.Open_Jobs;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Admin_User_Submission_Locators;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

public class Sub_184549 {
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_184549"+CommonElements.BROWSER+"M1";
    String targetlanguage_1 = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String trans_target_text = "target segment";
	String review_target_text = "modified_target";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_184549");
		dataSet.put("TL_test_case_description","Sub_184549:Cancel current edit");
		dataSet.put("TL_internal_testCase_ID", "184549");
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
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			//General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			PM_cancel_current_edit_transScreen(SubmissionName, "New");
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			PM_cancel_current_edit_reviewScreen(SubmissionName, "In_Progress");
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			//TODO AS PER GL PLAY 1.22.0 SHOW COMPLETED TOGGLE BUTTON IS REMOVED
//			 if(General.driver.findElement(Admin_User_Submission_Locators.Locator().Show_Complete_Sub_button).isDisplayed()); {
//				  Thread.sleep(1000);
//				  General.action().waitforelemenetpresent(Admin_User_Submission_Locators.Locator().Show_Complete_Sub_button); 
//				  Thread.sleep(1000);
//				  General.action().Click(Admin_User_Submission_Locators.Locator().Show_Complete_Sub_button);
//			  }
			
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void PM_cancel_current_edit_transScreen(String SubmissionName, String status) throws Exception {
		
		 System.out.println("INSIDE Pm_Complete_trans_Submission  method()");
		 
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, status)))
				{
				System.out.println("INSIDE IF--------");
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, status));
				}
			  Thread.sleep(6000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			  Thread.sleep(6000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
			  Thread.sleep(4000);
			
		
		      List <WebElement> listofIds1= General.driver.findElements(Pm_User_Submission_Locators.Locator().Pm_Submission_ListofallIds);
				Thread.sleep(2000);
			   System.out.println("No of IDS--------"+listofIds1.size());
			   Thread.sleep(2000);
			    for(int i=1;i<=listofIds1.size();i++){
			    	 Thread.sleep(2000);
			    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i));
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i));
				Thread.sleep(1000);
				General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i));
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i));
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i),trans_target_text);
				Thread.sleep(1000);

				String TARGET_SEGMENT_TEXT = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i)).getText();
				System.out.println("TARGET SEGMENT INPUT TEXT:-"+ TARGET_SEGMENT_TEXT);

				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_cancel_current_edit);
//				Thread.sleep(500);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_cancel_current_edit);
//				Thread.sleep(500);

				String Target_segment_after_cancel_current_edit = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i)).getText();
				if (Target_segment_after_cancel_current_edit.isEmpty()) {
					System.out.println("Input field is empty :-" + i);
				}
				Thread.sleep(1000);
				assertion = Target_segment_after_cancel_current_edit.isEmpty();
				if (assertion == false) {
					report("f","Assertion failed while verifying Target_segment_after_cancel_current_edit");
				}
			}
			    Thread.sleep(1000);
			    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_trans);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_trans);
				Thread.sleep(3000);
				if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
					Thread.sleep(1000);
					General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
			    	}
				
				System.out.println("EOM Pm_Complete_trans_Submission  method()");

	}


	public void PM_cancel_current_edit_reviewScreen(String SubmissionName, String status) throws Exception {
		
		 System.out.println("INSIDE Pm_Complete_review_Submission  method()");
		 
		    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, status)))
				{
				System.out.println("INSIDE IF--------");
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, status));
				}
			  Thread.sleep(6000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			  Thread.sleep(6000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_review_header);
			  Thread.sleep(6000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
			  Thread.sleep(4000);
			
		
		      List <WebElement> listofIds1= General.driver.findElements(Pm_User_Submission_Locators.Locator().Pm_Submission_ListofallIds);
				Thread.sleep(2000);
			   System.out.println("No of IDS--------"+listofIds1.size());
	
			    for(int i=1;i<=listofIds1.size();i++){
			    	Thread.sleep(2000);
			    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
				Thread.sleep(1000);
				General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i),review_target_text);
				Thread.sleep(1000);

				String MODIFIED_TARGET_SEGMENT_TEXT  = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i)).getText();
				System.out.println("MODIFIED_TARGET_SEGMENT_TEXT:-"+ MODIFIED_TARGET_SEGMENT_TEXT );

				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_cancel_current_edit);
//				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_cancel_current_edit);
//				Thread.sleep(1000);

				String Modified_Target_segment_after_cancel_current_edit  = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i)).getText();
				if (Modified_Target_segment_after_cancel_current_edit .isEmpty()) {
					System.out.println("Input field is empty :-" + i);
				}
				
				assertion = Modified_Target_segment_after_cancel_current_edit.isEmpty();
				if (assertion == false) {
					report("f","Assertion failed while verifying Target_segment_after_cancel_current_edit");
				}
			}
			    Thread.sleep(1000);
			    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_review);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_review);
				
				Thread.sleep(3000);
				if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
					Thread.sleep(1000);
					General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
			    	}
				System.out.println("EOM Pm_Complete_trans_Submission  method()");

	}
		 
	public void assertion() throws Exception {
		// This assertion will check that sub is present
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName,"Completed"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying check_submission_status after Search");
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
