package subtitler.project_manager.Open_Jobs;

import static org.testng.AssertJUnit.assertTrue;
import java.io.File;
import java.util.LinkedHashMap;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.RobotExt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: Import a file by caption number when there is empty segment.
 *Preconditions :1.Create a submission with attached file.
                 2.Claim the submission.
 */ 

public class Sub_1480496 {
	
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	String SubmissionName = "SUB_1480496"+CommonElements.BROWSER+"D1";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewlatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String importedFileSegments[]= {"Buon pomeriggio. Mi chiamo\n" + 
			"Sebastien Stormacq.","stata una lunga giornata. Oggi\n" + 
					"abbiamo parlato di molti servizi."};
	String path;
	@BeforeMethod
	public void setup() throws Exception {General.action().startSystem("SUB_1480496");
		dataSet.put("TL_test_case_description", "SUB-1480496:Import a file by caption number when there is empty segment");
		dataSet.put("TL_internal_testCase_ID", "1480496");
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
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewlatorGroupName, true);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_EMPTY_SEGMENTS_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(15000);
			//VERIFY SUBMISSION NAME
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			
			
			//CLICK ON TO CLAIM AND CLAIM SUBMISSION
			PM_user.action().Navigate(menu_to_claim);
	        Thread.sleep(1000);
	        PM_user.action().PM_ToClaim(SubmissionName);
	        Thread.sleep(1000);
	        //GO TO ONGOING AND OPEN SUBMISSION
	        PM_user.action().Navigate(menu_ongoing);
	        Thread.sleep(1000);
	        Pm_Complete_trans_Submission(SubmissionName,"trans");
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(1));
			Thread.sleep(1000);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Trans_ongoing_multiOption_icon);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Trans_ongoing_multiOption_icon);
			Thread.sleep(1000);
		
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Trans_Ongoing_Import_file);

			//Import file by caption number
			File afile=new File(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_EMPTY_SEGMENTS_FOLDER);
			File[] listOfFiles={afile};
	    	if (afile.isDirectory())    
	    	    listOfFiles = afile.listFiles();
	    	   
	    	   Thread.sleep(3000);
	    	      
	    	   //Process array
	    	    for (int i = 0; i < listOfFiles.length; i++)
	    	    { 
	    	     if(listOfFiles[i].isDirectory()) 
	    	    	 continue;
	    	    
	    	      path = listOfFiles[i].getAbsolutePath();
	    	     
	    	    }
	    	 	Thread.sleep(1000);
	    	 	
	    	 	
			//Trans_Ongoing_Import_file_input    	 
	    	System.out.println("VALID PATH-------->"+path);
	    	System.out.println("WHTS IS THIS---->"+CommonElements.BROWSER);
	    	
		 	//TODO THIS IMPL IS DUE TO AS FILE CANNOT BE IMPORTED PROPERLY IN FIREFOX THROUGH senKeys(), so FOR FIREFOX WE ARE USING ROBOT CLASS AND FOR CHROME WE ARE USING sendKeys()
	    	if(CommonElements.BROWSER.contains("FIREFOX")) {
		    	System.out.println("------THIS IS FIREFOX-----");
		    	Thread.sleep(1000);
		    	General.action().Click(Pm_User_Submission_Locators.Locator().Trans_Ongoing_Import_file);
	        	Thread.sleep(3000);
	        	General.action().Click(Pm_User_Submission_Locators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
				Thread.sleep(3000);
				RobotExt.robot().delay(2000);
				RobotExt.robot().sendKeys(path);
				RobotExt.robot().processFilePath();
				RobotExt.robot().delay(2000);
				Thread.sleep(4000);
	    	}else {
	        	System.out.println("------THIS IS CHROME-----");
	        	General.action().Click(Pm_User_Submission_Locators.Locator().Trans_Ongoing_Import_file);
	        	Thread.sleep(3000);
	        	General.action().Click(Pm_User_Submission_Locators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
				Thread.sleep(3000);
				RobotExt.robot().delay(2000);
				RobotExt.robot().sendKeys(path);
				RobotExt.robot().processFilePath();
				RobotExt.robot().delay(2000);
				Thread.sleep(4000);
	    	}
	    	Thread.sleep(8000);
	    	if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Trans_ongoing_overwrite_current_translation, 10)) {
				General.action().Click(Pm_User_Submission_Locators.Locator().Trans_ongoing_overwrite_current_translation);
				Thread.sleep(20000);
			}
	    	System.out.println(importedFileSegments);
	    	
	    	//Verify 1st segment of imported file
	    	String targetSegment1=General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(1)).getText();
	    	System.out.println(targetSegment1);
	    	assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(1)).getText().contains(importedFileSegments[0]);
	    	if (assertion == false) {
				report("f","Assertion failed while verifying segment imported from empty file.");
				
			}
	    	
	    	//Verify empty segment is present in target segment without any error
	    	assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(2)).getText().isEmpty();
	    	if (assertion == false) {
				report("f","Assertion failed while verifying target sement is  empty.");
				
			}
	    	System.out.println(importedFileSegments);
	    	String targetSegment3=General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(3)).getText();
	    	
	    	//Verify 3rd segment is present after importing file with empty segments
	    	System.out.println(targetSegment3);
	    	assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(3)).getText().contains(importedFileSegments[1]);
	    	if (assertion == false) {
				report("f","Assertion failed while verifying segment imported from empty file.");
				
			}
	    	

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void Pm_Complete_trans_Submission(String SubmissionName, String status) throws Exception {
		
		 System.out.println("INSIDE Pm_Complete_trans_Submission  method()");
		 
		    PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, status)))
				{
				System.out.println("INSIDE IF--------");
				 PM_user.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
				}
			  Thread.sleep(6000);
			  PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			  Thread.sleep(6000);
			  
			  
		     

	}
	
	public void assertion() throws Exception {
		
		//Verify empty segment is present in target segment without any error
		assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(4)).getText().isEmpty();
    	if (assertion == false) {
			report("f","Assertion failed while verifying target sement is  empty.");
			
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