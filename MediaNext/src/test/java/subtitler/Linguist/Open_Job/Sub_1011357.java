package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

/**
 * 
 * @author pvaidya
 * This Test Case Is Paste text only, not formatting
 */

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.RobotExt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

public class Sub_1011357 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String SubmissionName = "SUB_1011357"+CommonElements.BROWSER+"M9";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_AllJobs = "Jobs";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String AutoRemovedFormatText;

	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_1011357");
		dataSet.put("TL_test_case_description", "Sub_1011357 :Paste text only, not formatting");
		dataSet.put("TL_internal_testCase_ID", "1011357");
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
						PM_user.action().SearchSubmisson(SubmissionName);
						Thread.sleep(2000);

						assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
						if (assertion == false) {
							report("f","Assertion failed while verifying SubmissionName  after Search");
						}
						Thread.sleep(2000);
						General.action().logoutMethod();
						
						// trans login
						General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
						Thread.sleep(1000);
						
						EditTransAndReviewMethod();
					
						assertion = Verify.action().verifyElementNotPresent(CommonLocartors.Locator().Translation_Submission_Edit_TextArea_RemovedBoldText(1), 5);
						if (assertion == false) {
							report("f","Assertion failed while verifying Removed Bold Text From Text Segment");
						}
							
						CompleteTaskForTransAndReviewAnd();
							
						Thread.sleep(2000);
						General.action().logoutMethod();
						
						
						//Review Login
						General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
						Thread.sleep(1000);
						
						EditTransAndReviewMethod();
						
						
						
					} catch (Exception e) {
						report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
					}
				}
	
	
	
	public void EditTransAndReviewMethod() throws Exception {
		
		
		Translator.action().Navigate(menu_to_claim);
		Thread.sleep(1000);
		Translator.action().trans_ToClaim(SubmissionName);
		Thread.sleep(2000);
        Translator.action().Navigate(menu_ongoing);
        Thread.sleep(2000);
        Translator.action().SearchSubmisson_andCheck(SubmissionName);
		
		General.action().waitforelemenetpresent(CommonLocartors.Locator().Submission_Edit_icon);
		Thread.sleep(2000);
		General.action().Click(CommonLocartors.Locator().Submission_Edit_icon);
		Thread.sleep(2000);
		
		General.action().waitforelemenetpresent(CommonLocartors.Locator().Translation_Submission_Edit_Glossaryterms_Icon);
		Thread.sleep(2000);
		doubleClick(CommonLocartors.Locator().Translation_Submission_Edit_Glossaryterms_Icon);
		Thread.sleep(2000);
		
		 General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
	     Thread.sleep(3000);
	     General.action().ClearInputvalues(CommonLocartors.Locator().EditSegment_textarea(1));
	     Thread.sleep(1000);
	     
	     Thread.sleep(5000);	
		 PM_user.action().type(CommonLocartors.Locator().EditSegment_textarea(1),Keys.chord(Keys.CONTROL,"v"));
		 Thread.sleep(5000);	
		
	      AutoRemovedFormatText = General.driver.findElement(CommonLocartors.Locator().EditSegment_textarea(1)).getText();
		  System.out.println(" Auto Removed Target Text Is=======>"+AutoRemovedFormatText);
		  Thread.sleep(1000);
		  
		 
	}
	
	public void doubleClick(By by) throws Exception{
		System.out.println("INSIDE METHOD doubleClick");
		  Actions act=new Actions(General.driver);
		  act.doubleClick(General.driver.findElement(by));
		  act.sendKeys(Keys.chord(Keys.CONTROL,"c"));
		  act.build().perform();
		  Thread.sleep(1000);
			System.out.println("EOM doubleClick()");
		  
		 }
	
	
	 public void CompleteTaskForTransAndReviewAnd() throws InterruptedException {
			
			System.out.println("INSIDE method CComplete Task For Trans , Review And Transcription()\n"); 
			

		    PM_user.action().waitforelemenetpresent(CommonLocartors.Locator().SubmissionEdit_complete_task);
	        Thread.sleep(1000);
	        PM_user.action().Click(CommonLocartors.Locator().SubmissionEdit_complete_task);
		    Thread.sleep(1000);

	        PM_user.action().waitforelemenetpresent(CommonLocartors.Locator().SubmissionEdit_complete_Task_Alert_button);
	        Thread.sleep(1000);
	        PM_user.action().Click(CommonLocartors.Locator().SubmissionEdit_complete_Task_Alert_button);
	        Thread.sleep(6000);
	        
	        System.out.println("EOM Complete Task For Trans , Review And Transcription()\n");
			
			
		}
	
	
	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementNotPresent(CommonLocartors.Locator().Translation_Submission_Edit_TextArea_RemovedBoldText(1), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Removed Bold Text From Text Segment");
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


