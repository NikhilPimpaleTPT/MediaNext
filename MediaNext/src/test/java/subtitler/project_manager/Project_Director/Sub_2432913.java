package subtitler.project_manager.Project_Director;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

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
 *Summary:this test case verifies the options for sending group submissions to pd
 *
 */
public class Sub_2432913 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	String SubmissionName1 = "SUB_2432913"+CommonElements.BROWSER+"I";
	String SubmissionName2 = "SUB_2432913"+CommonElements.BROWSER+"J";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_completed = "Completed";
	
	
	
	String submissionID1;
	String submissionID2;
	File filepath;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2432913");
		dataSet.put("TL_test_case_description", "As a PM, for PD submission,verify the possibility to send one file per batch in PD submission");
		dataSet.put("TL_internal_testCase_ID", "2432913");
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
			    Thread.sleep(2000);
			    //Create Submission In PD
			    for(int i=1;i<=3;i++) {
				PM_user.action().Navigate(menu_Submission);
				// TODO NEW IMPL OF SUBMISSION CREATION
				PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName1);
				PD_PM_user.action().Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName1+i,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
				PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				
			    }
				//Search and check submission and Verify for CRETE IN PD BUTTON
			  //try to Update Submission name
		    	PM_user.action().SearchSubmisson(SubmissionName1);
		    	Thread.sleep(2000);
		    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_allCheck);
		    	Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button);
				Thread.sleep(2000);
				
			    if(Verify.action().VerifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_dialogBox_allFilesInOneBatch_checked))
			    {
			    System.out.println("Radio All files in one batch is checked by default");
			    	
			    }else{
			    System.out.println("Radio All files in one batch is not checked by default");
			    PM_user.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_dialogBox_allFilesInOneBatch_unchecked);
			    Thread.sleep(2000);
			    PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_dialogBox_allFilesInOneBatch_unchecked);
				Thread.sleep(2000);
			    	
			    }
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_createSubmissionInPDButton);
				Thread.sleep(2000);
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				
				//Verify Submission creation and due date
				PD_PM_user.action().SearchSubmisson(SubmissionName1);
				Thread.sleep(2000);
				
				//Verify Submission status after creating 
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName1+1,"New"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName status after Search");
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName1+2,"New"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName status after Search");
				}
				
				submissionID1 =General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(1,3)).getText();
				System.out.println("Submission ID:"+submissionID1);
				
				assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(1,3)).getText().contains(submissionID1);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD id is same");
				}
				assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(2,3)).getText().contains(submissionID1);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD id is same");
				}
				assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(3,3)).getText().contains(submissionID1);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD id is same");
				}  
				
				
				General.action().driver.navigate().refresh();
				Thread.sleep(5000);
				for(int i=1;i<=3;i++) {
					PM_user.action().Navigate(menu_Submission);
					// TODO NEW IMPL OF SUBMISSION CREATION
					PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName2);
					PD_PM_user.action().Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName2+i,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
					PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
					PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
					PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
					
				    }
					//Search and check submission and Verify for CRETE IN PD BUTTON
				  //try to Update Submission name
			    	PM_user.action().SearchSubmisson(SubmissionName2);
			    	Thread.sleep(2000);
			    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_allCheck);
			    	Thread.sleep(2000);
					PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button);
					Thread.sleep(2000);
					
				    if(Verify.action().VerifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_dialogBox_oneFilesInOneBatch_unchecked))
				    {
				    System.out.println("Radio All files in one batch is not checked by default");
					PM_user.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_dialogBox_oneFilesInOneBatch_unchecked);
					Thread.sleep(2000);
					PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_dialogBox_oneFilesInOneBatch_unchecked);
				    Thread.sleep(2000);
				    System.out.println("Radio one files in one batch is not checked by default");
				    	
				    }else{
				    System.out.println("Radio one files in one batch is checked by default");
				    
				    	
				    }
					PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_createSubmissionInPDButton);
					Thread.sleep(2000);
					PM_user.action().Navigate(menu_Submission);
					Thread.sleep(2000);
					
					//Verify Submission creation and due date
					PD_PM_user.action().SearchSubmisson(SubmissionName2);
					Thread.sleep(2000);
					
					//Verify Submission status after creating 
					assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName2+1,"New"), 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying SubmissionName status after Search");
					}
					assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName2+2,"New"), 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying SubmissionName status after Search");
					}
					
					submissionID2 =General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(1,3)).getText();
					System.out.println("Submission ID:"+submissionID2);
					
					assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(1,3)).getText().contains(submissionID2);
					if (assertion == false) {
						report("f","Assertion failed while verifying PD id is same");
					}
					assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(2,3)).getText().contains(submissionID2);
					if (assertion == false) {
						report("f","Assertion failed while verifying PD id is same");
					}
					assertion = General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(3,3)).getText().contains(submissionID2);
					if (assertion == false) {
						report("f","Assertion failed while verifying PD id is same");
					}
				
				
				
				Thread.sleep(2000);
				PD_PM_user.action().Open_PD_Instance_URL();
				Thread.sleep(25000);
				
				//******Not Needed*******
				/*PD_PM_user.action().logIn_to_PD(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
				Thread.sleep(2000);*/
				//TODO ******Not Needed*******
				//PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				//Thread.sleep(4000);
//				PD_PM_user.action().editSubmission_addCustomAttributes("m","a","x");
//				Thread.sleep(4000);
				
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_onHoldButton);
		    	Thread.sleep(4000);
				Thread.sleep(1000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
		    	Thread.sleep(4000);
		    	PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID1);
				Thread.sleep(2000);
				
				General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission);
		    	Thread.sleep(3000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission);
		    	Thread.sleep(5000);
		    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_edit_batchFile);
		    	Thread.sleep(3000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_edit_batchFile);
		    	Thread.sleep(8000);
		    	
		    	assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_edit_batchFile_submission_ttmlFiles(1,SubmissionName1+1), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName ttml");
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_edit_batchFile_submission_ttmlFiles(2,SubmissionName1+2), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName ttml");
				}
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_edit_batchFile_submission_ttmlFiles(3,SubmissionName1+3), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName ttml");
				}
				Thread.sleep(5000);
		    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission_batch_saveButton);
		    	Thread.sleep(3000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission_batch_saveButton);
		    	Thread.sleep(8000);
		    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission_saveButton);
		    	Thread.sleep(3000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission_saveButton);
		    	Thread.sleep(8000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_onHoldButton);
		    	Thread.sleep(4000);
				Thread.sleep(1000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
		    	Thread.sleep(4000);
		    	PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID2);
				Thread.sleep(2000);
				
				General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission);
		    	Thread.sleep(3000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission);
		    	Thread.sleep(5000);
		    	
		    	assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_edit_batchFile_oneFilesPerBatch(SubmissionName2+1), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName ttml");
				}
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_edit_batchFile_oneFilesPerBatch(SubmissionName2+2), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName ttml");
				}
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_edit_batchFile_oneFilesPerBatch(SubmissionName2+3), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName ttml");
				}
				
				
		    	
		    	


		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
          }
	}
public void assertion() throws Exception {
	assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_edit_batchFile_oneFilesPerBatch(SubmissionName2+3), 5);
	if (assertion == false) {
		report("f","Assertion failed while verifying SubmissionName ttml");
	}
	else {
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
