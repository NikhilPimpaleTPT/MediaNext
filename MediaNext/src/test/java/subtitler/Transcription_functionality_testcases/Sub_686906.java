package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

public class Sub_686906 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_686906"+CommonElements.BROWSER+"O1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	int id;
 

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_686906");
		dataSet.put("TL_test_case_description",	"SUB-686906:Increase visibility of current subtitle in preview mode");
		dataSet.put("TL_internal_testCase_ID", "686906");
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
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			Pm_Transcription_Ongoing_Increase_visibility(SubmissionName, targetlanguage_1,false, true);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public void Pm_Transcription_Ongoing_Increase_visibility(String SubmissionName,String target, boolean complete, boolean back) throws Exception {

		System.out.println("INSIDE Pm_Transcription_Ongoing_Align_On_Top  method()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(4000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
        Thread.sleep(8000);
        
        //Verify the Subtitles are highlighted as per video played.
        assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentPlayingSegment(1), 5);
		System.out.println("GRID SUBTITLE IS HIGHLIGHTED********************************************************:-" +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_Transcription_tesxtarea_currentRecord  after Search");
		}
		
		Thread.sleep(2000);
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentPlayingSegment(2), 5);
		System.out.println("GRID SUBTITLE IS HIGHLIGHTED:-" +assertion);
		if (assertion == false) {
		report("f","Assertion failed while verifying PM_Transcription_tesxtarea_currentRecord  after Search");
		}
		
        
//      This Step is not requird as per Test Case       
//      Thread.sleep(5000);
//      String Video_time = General.driver.findElement(By.xpath("//app-time-box")).getAttribute("ng-reflect-current-time");
//		System.out.println("VIDEO TIME:- " +Video_time);
//		Thread.sleep(1000);
//		
//		float f = Float.parseFloat(Video_time);
//        System.out.println(f);
//        Thread.sleep(1000);
//        
//        
//		assertion = f > 2;
//		System.out.println("VIDEO TIME IS GREATER THAN 2");
//		if (assertion == false) {
//			report("f","Assertion failed while verifying VIDEO TIME  after Search");
//		}
      
		Thread.sleep(5000);
		List <WebElement> listofIds1=General.driver.findElements(Pm_User_Submission_Locators.Locator().ListofIDs);
		Thread.sleep(5000);
	    System.out.println("No of IDS --------"+listofIds1.size());
		
        for (int i=1; i<=listofIds1.size(); i++) {
        General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(i));
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(i));
		Thread.sleep(2000);
		
		String Video_Subtitle = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea).getText();
		System.out.println("VIDEO SUBTITLE:- " +Video_Subtitle);
		Thread.sleep(2000);	
			
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentRecord, 5);
		System.out.println("GRID SUBTITLE IS HIGHLIGHTED:-" +assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_Transcription_tesxtarea_currentRecord  after Search");
		}
		
		Thread.sleep(2000);	
	    String Subtitle_Id = General.driver.findElement(By.xpath("//mat-list-item[contains(@class,'current-record')][contains(@id,'"+i+"')]")).getCssValue("font-weight");
		System.out.println("VIDEO SUBTITLE:- " +Subtitle_Id);//Getting 400
		Thread.sleep(2000);	
		
		 id = Integer.parseInt(Subtitle_Id);
		
		if(id >=400) {
			System.out.println("SUBTITLE ID IS BOLD");
		}else {
			System.out.println("SUBTITLE ID IS NOT BOLD");
		}
        }
        
       //Verify Only one segment should be highlighted and shown as active.
      	General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(6));
      	Thread.sleep(2000);
      	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentRecord(6), 5);
      	System.out.println("GRID SUBTITLE IS HIGHLIGHTED:-" +assertion);
      	if (assertion == false) {
      	report("f","Assertion failed while verifying PM_Transcription_tesxtarea_currentRecord  after Search");
      	}
			
        
		
 }
	
	
	    public void assertion() throws Exception {
	    	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_tesxtarea_currentRecord(6), 5);
	      	System.out.println("GRID SUBTITLE IS HIGHLIGHTED:-" +assertion);
	      	if (assertion == false) {
	      	report("f","Assertion failed while verifying PM_Transcription_tesxtarea_currentRecord  after Search");
	      	}
			   else 
			   {
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


