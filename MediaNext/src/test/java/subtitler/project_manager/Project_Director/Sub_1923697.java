package subtitler.project_manager.Project_Director;

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
import locators.PD_Pm_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import modules.PD_PM_user;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:Actions available upon submissions selection
 *
 */

public class Sub_1923697 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String SubmissionName = "SUB_1923697"+CommonElements.BROWSER+"A2";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_jobs = "Jobs";
	String submissionID;
	File filepath;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1923697");
		dataSet.put("TL_test_case_description", "UI of Edited PD submission");
		dataSet.put("TL_internal_testCase_ID", "1923697");
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
				Thread.sleep(1000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_createSubmissionInPDButton);
				Thread.sleep(2000);
				
				PM_user.action().Navigate(menu_jobs);
				Thread.sleep(2000);
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
				Thread.sleep(2000);
				//Search and check submission and Verify for CRETE IN PD BUTTON
				PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				Thread.sleep(2000);
				
				submissionID =General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(1,3)).getText();
				System.out.println("Submission ID:"+submissionID);

			
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_edit_button, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName edit button");
					
				}
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_edit_button);
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_SubmissionName(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName ");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_PDID(submissionID), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName id");
					
				}
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_client(OrganizationName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName orgnization");
				
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_sourceLanuage("en-US"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName source language");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_captionCount("6"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName caption count");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_wordCount("38"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName word count");
					
				}
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_globalSetting);
				Thread.sleep(2000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_minReadingSpeed("0"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName min reading speed");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_maxReadingSpeed("17"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying  max reading speed");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_maxCharPerLine("35"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying  max char per line");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_maxLinePerSubtitler("2"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying  max line per subtiler");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_minSubtitlerDuration("80"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying min subtitle duration");
					
				}
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_maxSubtitlerDuration("100"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifyingmax subtitle duration");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_dropFrames, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying drop frames");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_minCountOfFramesBetweenSub("4"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying min count of frames");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_snapToChanges, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying snap shot");
					
				}
				
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_videoDetails);
				Thread.sleep(2000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_videoDetails("Media Library ID: 68723"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying video details");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_videoDetails("Width: 640"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying video details");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_videoDetails("Height: 352"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying video details");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_videoDetails("Mime type: video/mp4"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying video details");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_videoDetails("Frame rate: 29.97"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying video details");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_videoDetails("Aspect Ratio: 20:11"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying video details");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_videoDetails("Duration: 00:00:45"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying video details");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_videoDetails("Duration: 00:00:45"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying video details");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_streamURL("Stream Url: https://cdn01.tptgms.com/vods3"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying video details");
					
				}
				
				Thread.sleep(2000);
				General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_PDConnector);
				Thread.sleep(2000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_PDConnectorDetails("Instance: qa-tdc13"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD Connector details");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_PDConnectorDetails("Department: 2"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD Connector details");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_PDConnectorDetails("Job number: "+SubmissionName+""), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD Connector details");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_rightTabs("Status"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Status TAB");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_rightTabs("Term Manager"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying TMGR tab");
					
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_rightTabs("Settings"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying setting tab");
					
				}
				
				
				
				
				
				
				

		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
           }
 }
	
	
	
	public void assertion() throws Exception {
		//TODO This TC is blocked as need to check Submission name,PD submission ID,Client,Source Language,Caption count,word count,Global settings,Instance........... fields should be non editable.
		assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_editView_panel_rightTabs("Settings"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying setting tab");
			
		}else {
		report("b", "All assertions passed");
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