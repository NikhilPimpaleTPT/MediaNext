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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary:To verify if the time is normalized when performing split and time editing.
 *
 */

public class Sub_2202026 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_2202026"+CommonElements.BROWSER+"A3";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String TimeInSlotMilliSecond="NewTimeInFrames";
	String folder_1345708="2202026";
    
    Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2202026");
		dataSet.put("TL_test_case_description", "SUB-2202026:Normalize time when running split and time editing actions");
		dataSet.put("TL_internal_testCase_ID", "2202026");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			//Create Submission
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_submission);
			//TODO NEW IMPL OF SUBMISSION CREATION USING AMPERSAND CHARACTERS
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",	TranslatorGroupName, "review", "", true);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,"70137");
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+folder_1345708);
			PM_user.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, Targetlanguage);
			Thread.sleep(2000);
		
			//SEARCH SUBMISSION AND CHECK
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			PM_user.action().Navigate(menu_to_claim);
	        Thread.sleep(1000);
	        PM_user.action().PM_ToClaim(SubmissionName);
	        Thread.sleep(1000);
	        PM_user.action().Navigate(menu_ongoing);
	        Thread.sleep(1000);
	        PM_onGoing_submission(SubmissionName, "", true, false);
			Thread.sleep(2000);	
			
			
			
			
			
		} catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
         }
	}
	
	
	
	public void PM_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
		
		 System.out.println("INSIDE PM_Ongoing  method()");
		 
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
			Thread.sleep(1000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input, SubmissionName);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName, target)))
			{
				System.out.println("INSIDE IF--------");
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
			}
				  Thread.sleep(2000);
				  General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
			
				  Thread.sleep(2000);
			      List <WebElement> listofIds1= General.action().driver.findElements(Pm_User_Submission_Locators.Locator().PM_toClaim_ListofallIds);
				  Thread.sleep(1000);
				  System.out.println("No of IDS--------"+listofIds1.size());
				  
				  General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_E);
				  Thread.sleep(2000);
				  General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_E);
				  Thread.sleep(2000);
	
			    
			    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
					Thread.sleep(1000);
					General.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
					Thread.sleep(1000);
					General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_CopySource_toTarget_Button);
					Thread.sleep(1000);
					General.action().Click(Pm_User_Submission_Locators.Locator().Pm_CopySource_toTarget_Button);
					Thread.sleep(1000);
					
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(3));
					Thread.sleep(1000);
					General.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(3));
					Thread.sleep(1000);
					General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(3));
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(3));
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_CopySource_toTarget_Button);
					Thread.sleep(1000);
					General.action().Click(Pm_User_Submission_Locators.Locator().Pm_CopySource_toTarget_Button);
					Thread.sleep(1000);
				
			    
	      
			
			    General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
				Thread.sleep(1000);
				General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
				Thread.sleep(1000);
				
				String timeInOfThirdSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(3)).getText();
			    System.out.println(timeInOfThirdSegment);
			    String timeOutOfThirdSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(3)).getText();
			    System.out.println(timeOutOfThirdSegment);
				
				
				General.action().Enter_keyEnvents(KeyEvent.VK_UP);
				Thread.sleep(1000);
				General.action().Enter_keyEnvents(KeyEvent.VK_UP);
				Thread.sleep(1000);
				General.action().Enter_keyEnvents(KeyEvent.VK_END);
				Thread.sleep(1000);
				
				if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
				General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_P);
				Thread.sleep(1000);
				}else {
				Translator.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),Keys.chord(Keys.ALT, "p"));
				
				}
				Thread.sleep(5000);
				assertion = General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(3)).getText().contains(timeInOfThirdSegment);
				if (assertion == false) {
					report("f","Assertion failed while verifying time in after splitting");
				}
				System.out.println(General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(3)).getText());
				assertion = General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(3)).getText().contains("00:00:33:200");
				if (assertion == false) {
					report("f","Assertion failed while verifying time in after splitting");
				}
				System.out.println(General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(4)).getText());
				assertion = General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(4)).getText().contains("00:00:33:360");
				if (assertion == false) {
					report("f","Assertion failed while verifying time in after splitting");
				}
				
				assertion = General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(4)).getText().contains(timeOutOfThirdSegment);
				if (assertion == false) {
				report("f","Assertion failed while verifying time out after splitting");
				}
				
				
				General.action().waitforelemenetpresent(CommonLocartors.Locator().EditSegment_textarea(1));
		        Thread.sleep(1000);
			    General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
			    Thread.sleep(3000);
				
				General.action().type(CommonLocartors.Locator().EditSegment_textarea(1),Keys.chord(Keys.ALT, "t"));
				Thread.sleep(3000);
					 
			    General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond));
			    Thread.sleep(3000);
			    General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
				Thread.sleep(3000);
				General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
				Thread.sleep(3000);
				General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
				Thread.sleep(3000);
				General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
				Thread.sleep(3000);
				General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),"3");
				
				General.action().waitforelemenetpresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
				Thread.sleep(1000);
				General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
				Thread.sleep(5000);
				
				assertion = !General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText().contains("00:00:09:121");
				if (assertion == false) {
					report("f","Assertion failed while verifying time in after splitting");
				}
				
				assertion = General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText().contains("00:00:09:120");
				if (assertion == false) {
					report("f","Assertion failed while verifying time in after splitting");
				}
				
				General.action().waitforelemenetpresent(CommonLocartors.Locator().EditSegment_textarea(1));
		        Thread.sleep(1000);
			    General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
			    Thread.sleep(3000);
				
				General.action().type(CommonLocartors.Locator().EditSegment_textarea(1),Keys.chord(Keys.ALT, "t"));
				Thread.sleep(3000);
					 
			    General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond));
			    Thread.sleep(3000);
			    General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
				Thread.sleep(3000);
				General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
				Thread.sleep(3000);
				General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
				Thread.sleep(3000);
				General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
				Thread.sleep(3000);
				General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),"5");
				
				General.action().waitforelemenetpresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
				Thread.sleep(1000);
				General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
				Thread.sleep(5000);
				
				
				assertion = !General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText().contains("00:00:09:201");
				if (assertion == false) {
					report("f","Assertion failed while verifying time in after splitting");
				}
				
				assertion = General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText().contains("00:00:09:200");
				if (assertion == false) {
					report("f","Assertion failed while verifying time in after splitting");
				}
    
	 System.out.println("EOM PM_Ongoing  method()");
}

	public void assertion() throws Exception {
		//Verify - select a segment and click on edit time-in/time-out modify the time and click on update
		assertion = General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText().contains("00:00:09:200");
		if (assertion == false) {
			report("f","Assertion failed while verifying time in after splitting");
		}else {
				report("p", "All assertion passed");
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