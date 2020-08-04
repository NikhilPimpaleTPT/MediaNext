package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
//import locators.TranslatorLocators;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author swati thakare
 * This test case verifies that space in between words is not lost when importing TTML files with formatting.
 * precondition : Use the attached file while creating submission.
 */
public class Sub_1766884 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_1766884"+CommonElements.BROWSER+"S1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
    String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String folder_1766884 = "1766884";
	
	@BeforeMethod
	public void setup() throws Exception
	{
		General.action().startSystem("SUB_1766884");
		dataSet.put("TL_test_case_description", "SUB-1766884:space in between words is not lost when importing TTML files with formatting.");
		dataSet.put("TL_internal_testCase_ID", "1766884");
	}
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	public void testCase(LinkedHashMap<String, String> dataSet)	throws Exception {
		
			// PM login
				General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
				Thread.sleep(10000);
				PM_user.action().Navigate(menu_Submission);
				//submission creation using TTML file
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "", "", false);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+folder_1766884);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				Thread.sleep(2000);
				//SEARCH SUBMISSION AND CHECK
				PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				Thread.sleep(2000);
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
				}
				
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
		    	Thread.sleep(4000);
		    	
		    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
		    	assertion = General.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
		    	System.out.println("open job button verified");
		    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
		    	Thread.sleep(2000);
		   
		      	System.out.println("above");
		    
		      	Thread.sleep(1000);
		      
		      	System.out.println("found");
		      	for(int i=1;i<16;i++) {
		      	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_Segement_row(i));
		    	Thread.sleep(2000);
		      	}
		    	System.out.println("below");
		    	
		    	for(int i=1;i<=16;i++) {
			  		Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_DOWN);
			        Thread.sleep(1000);
			        robot.keyRelease(KeyEvent.VK_DOWN);
			        Thread.sleep(1000);			  		
			  	}   	  	
		    	
				
		    	
				
					}
	public void assertion() throws Exception {
		assertion = Verify.action().verifyTextPresent("La raison de ma visite ici \n" + "est différente de celle de mon père.", 5);
		if(assertion==false)
			report("f","Assertion failed while verifying the space between the words");
		else{
		    report("p", "All Assertion passed.");
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
