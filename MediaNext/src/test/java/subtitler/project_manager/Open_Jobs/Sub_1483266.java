package subtitler.project_manager.Open_Jobs;

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

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This testcase verifies that RTL subtitle splitted in two is not rendered in one line on the video.
 *Preconditions :Use attached file to create the submission.
 */ 


public class Sub_1483266 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1483266"+CommonElements.BROWSER+"D1";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewlatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String hebrewText="נחנו נשחק את השעה הראשונהשל משחק מסדרת טריפל";
	String hebrewTextSplit_c="נחנו נשחק את השעה הראשונהש\n" + 
			"ל משחק מסדרת טריפל";
	String hebrewTextSplit_f="נחנו נשחק את השעה הראשונה\n" + 
			"של משחק מסדרת טריפל";
	
	@BeforeMethod
	public void setup() throws Exception {General.action().startSystem("SUB_1483266");
		dataSet.put("TL_test_case_description", "SUB-1483266:Splitted segments of RTL langauges on the video.");
		dataSet.put("TL_internal_testCase_ID", "1483266");
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
			Thread.sleep(20000);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION , USE SRT FILE ATTACHED IN TEST CASE
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewlatorGroupName, true);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_ARABIC_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(8000);
			//VERIFY SUBMISSION NAME
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			
			//CLAIM JOB
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(1000);
			//NAVIGATE TO ONGOING AND OPEN JOB
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			PM_onGoing_submission(SubmissionName,"trans");
			Thread.sleep(1000);
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public void PM_onGoing_submission(String SubmissionName, String target) throws Exception {
		
		 System.out.println("INSIDE PM_Ongoing  method()");
		 
		    PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
			Thread.sleep(1000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
			Thread.sleep(1000);
			PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
			Thread.sleep(1000);
			PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input, SubmissionName);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName, target)))
			{
				System.out.println("INSIDE IF--------");
				 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
			}
			Thread.sleep(2000);
			//OPEN JOB 
		    PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
		    Thread.sleep(15000);
		    //STOP VIDEO
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_play_arrow);
			Thread.sleep(2000);
			
			//CLEAR TARGET SEGMENT 1 AND ENTER HEBREW TEXT
			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
		    Thread.sleep(1000);
		    PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
		    Thread.sleep(1000);
		    PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
		    Thread.sleep(1000);
		    General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),hebrewText);
			Thread.sleep(1000);
			//CLICK ON SAME SEGMENT(Note :No need to change writing direction from right to left as, working for both the browser after entering Hebrew text.)
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
		    Thread.sleep(1000);
		    Thread.sleep(1000);
			General.action().Enter_keyEnvents(KeyEvent.VK_END);
			Thread.sleep(1000);
		    
		    
		    //******This is use to add carriage return(Enter) in center or anywhere of the segment******
		    Robot robot= new Robot();
			for(int i=1;i<20;i++){
			robot.keyPress(KeyEvent.VK_RIGHT);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_RIGHT);
			}
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(8000);

	
			    
		   System.out.println("EOM PM_Ongoing  method()");
	}
	
	
	public void assertion() throws Exception {
		 //*****IMP****
		//TODO This assertion is verifying 1st  target segment But BLOKED as we also need to verify 1st segment on video after adding carriage return and it should  not displaying into one single line on the video subtitle.
//		assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1)).getText().contains("× ×—× ×• × ×©×—×§ ×�×ª ×”×©×¢×” ×”×¨×�×©×•× ×”×©\n" + 
//				"×œ ×ž×©×—×§ ×ž×¡×“×¨×ª ×˜×¨×™×¤×œ");
		if(CommonElements.BROWSER.contains("CHROME")) {
		assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1)).getText().contains(hebrewTextSplit_c);
    	if (assertion == false) {
			report("f","Assertion failed while verifying target sement after adding carriage return .");
			
		}else {
			report("b", "All Assertion blocked.");
		}
		}else {
			
			assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1)).getText().contains(hebrewTextSplit_f);
	    	if (assertion == false) {
				report("f","Assertion failed while verifying target sement after adding carriage return .");
				
			}else {
				report("b", "All Assertion blocked.");
			}
			
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