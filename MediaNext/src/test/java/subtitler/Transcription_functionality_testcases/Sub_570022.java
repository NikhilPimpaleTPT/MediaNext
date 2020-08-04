package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.List;

import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_570022 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_570022"+CommonElements.BROWSER+"L2";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	


	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_570022");
		dataSet.put("TL_test_case_description",	"SUB-570022:Scroll down when adding a new segment");
		dataSet.put("TL_internal_testCase_ID", "570022");
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
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			Pm_Transcription_Scroll_down(SubmissionName, "New");
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void Pm_Transcription_Scroll_down(String SubmissionName,String status) throws Exception {

		System.out.println("INSIDE Pm_Complete_trans_Submission  method()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, status))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
		}
		Thread.sleep(6000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
		Thread.sleep(6000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
		Thread.sleep(10000);
		
		for(int i=1; i<=12; i++){
	    //TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		Thread.sleep(3000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, "n"));
		Thread.sleep(1000);*/
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_N);		
			
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Testing transcription");
		Thread.sleep(1000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, Keys.ENTER));
		Thread.sleep(3000);
		}
		
		 List <WebElement> numberOfRows= General.driver.findElements(Pm_User_Submission_Locators.Locator().PM_Transcription_NumberOFRows);
			Thread.sleep(2000);
		   System.out.println("NUMBER OF ROWS BEFORE-------->"+numberOfRows.size());
		
		 //TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
		/*General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
		Thread.sleep(3000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, "n"));
		Thread.sleep(1000);*/
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_N);		
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Testing transcription");
		Thread.sleep(1000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, Keys.ENTER));
		Thread.sleep(3000);
		
		
		 List <WebElement> numberOfRows1= General.driver.findElements(Pm_User_Submission_Locators.Locator().PM_Transcription_NumberOFRows);
			Thread.sleep(2000);
		   System.out.println("NUMBER OF ROWS AFTER-------->"+numberOfRows1.size());
		
		   Thread.sleep(5000);
		   WebElement frst_row = General.driver.findElement(By.xpath("//mat-list-item[contains(@id,'1')]"));
		   Thread.sleep(5000);
		  ((JavascriptExecutor) General.driver).executeScript("arguments[0].scrollIntoView(true);", frst_row);
		   Thread.sleep(5000);   	   
	}

		public void assertion() throws Exception {
			//Assertion is blocked as needs to verify -	
			//User should be able to scroll the grid upwards & should be able to see the first created segment.
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_NumberOFRows, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}else {
				report("b", " Assertion is BOCKED.");
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



