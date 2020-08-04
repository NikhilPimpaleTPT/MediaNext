package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_570017 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

    String SubmissionName = "SUB_570017"+CommonElements.BROWSER+"A2";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String menu_Submission = "Submissions";
	String targetlanguage_1 = "English (United States)";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String Transcription_textarea_text;
	String Transcription_textarea_text_after_editing;
	String folder_570017 = "570017\\srt";
	String firstText="Entering First text on transcription";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_570017");
		dataSet.put("TL_test_case_description",	"SUB-570017:Transcription - Set text as Format");
		dataSet.put("TL_internal_testCase_ID", "570017");
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
			PM_user.action().Navigate(menu_Submission);
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			//PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ folder_570017);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			Pm_Transcription_Set_text_Format(SubmissionName, "New");
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void Pm_Transcription_Set_text_Format(String SubmissionName,String status) throws Exception {

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
		Thread.sleep(2000);

		
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_N);
		Thread.sleep(8000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,firstText);
		Thread.sleep(8000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.ALT, Keys.ENTER));
		Thread.sleep(6000);
		
		// Steps(11,12,13,14) in TC are duplicate , steps are covered here
	    assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1)).getText().contains(firstText);
			if (assertion == false) {
				report("f","Assertion failed while verifying PM_Transcription_Textarea after entering text");
		 }
		
		int timeIn_firstSegment_frames_afterALTR_int_forward=getTimeIn_frames_of_segment(1);
	    System.out.println("timeIn_firstSegment_frames_afterALTR_int_forward :"+timeIn_firstSegment_frames_afterALTR_int_forward);
	    int timeOut_firstSegment_frames_afterALTR_int_forward=getTimeOut_frames_of_segment(1);
	    System.out.println("timeOut_firstSegment_frames_afterALTR_int_forward :"+timeOut_firstSegment_frames_afterALTR_int_forward);
	    
	    assertion=timeOut_firstSegment_frames_afterALTR_int_forward>timeIn_firstSegment_frames_afterALTR_int_forward;
	    if (assertion == false) {
			report("f","Assertion failed while verifying the time and out of secound segmet");
		}
	    
	    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
	    Thread.sleep(3000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_button);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_button);
		Thread.sleep(2000);
	
		
		
		// check for bold format
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_BoldFormat_button,5);
		if (assertion == false) {
		report("f","Assertion failed while verifying PM_Transcription_Video_Styling_BoldFormat_button  after Search");
		}
		Thread.sleep(2000);
		// check for Italic format
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_ItalicFormat_button,5);
		if (assertion == false) {
		report("f","Assertion failed while verifying PM_Transcription_Video_Styling_ItalicFormat_button  after Search");
		}

		Thread.sleep(2000);
		// check for Underline format
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_UnderLineFormat_button,5);
		if (assertion == false) {
		report("f","Assertion failed while verifying PM_Transcription_Video_Styling_UnderLineFormat_button  after Search");
		}

		Thread.sleep(2000);
		// check for Align subtitle up
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_AlignUP_button,5);
		if (assertion == false) {
		report("f","Assertion failed while verifying PM_Transcription_Video_Styling_AlignUP_button  after Search");
		}

		Thread.sleep(2000);
		// check for Align subtitle down
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_AlignDOWN_button,5);
		if (assertion == false) {
		report("f","Assertion failed while verifying PM_Transcription_Video_Styling_AlignDOWN_button  after Search");
		}
		
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		Thread.sleep(5000);

				
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_BoldFormat_button);
		Thread.sleep(1000);
		
		
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);

		String video_bold_format = General.driver.findElement(By.xpath("//shared-text-editor//div//b")).getTagName();
		System.out.println("VIDEO TEXT INPUT STYLE IS BOLD:- "+ video_bold_format);

		String video_bold_format_text = General.driver.findElement(By.xpath("//shared-text-editor//div//b")).getCssValue("font-weight");
		System.out.println("VIDEO TEXT INPUT VALUE IS BOLD:- "+ video_bold_format_text);

		String TextArea_bold_format = General.driver.findElement(By.xpath("//mat-list-item[contains(@id,'1')]//div[contains(@class,'editor-read-only')]//b")).getTagName();
		System.out.println("TEXTAREA TEXT INPUT STYLE IS BOLD:- "+ TextArea_bold_format);

		assertion = video_bold_format.equalsIgnoreCase(TextArea_bold_format);
		System.out.println("VIDEO AND TEXTAREA FONT STYLE IS BOLD:-"+ assertion);
		if (assertion == false) {
		report("f","Assertion failed while verifying VIDEO AND TEXTAREA FONT TEXT STYLE IS BOLD  after Search");
		}
		
		//To Remove the bold format from text
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		Thread.sleep(5000);	
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_BoldFormat_button);
		Thread.sleep(1000);
		
		
		//To add the italic format from text
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		Thread.sleep(5000);	
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_ItalicFormat_button);
		Thread.sleep(8000);
		String video_Italic_format = General.driver.findElement(By.xpath("//shared-text-editor//div//i")).getTagName();
		System.out.println("VIDEO TEXT INPUT STYLE IS ITALIC:- "+ video_Italic_format);

		String video_Italic_format_text = General.driver.findElement(By.xpath("//shared-text-editor//div//i")).getCssValue("font-style");
		System.out.println("VIDEO TEXT INPUT VALUE IS ITALIC:- "+ video_Italic_format_text);

		String TextArea_Italic_format = General.driver.findElement(By.xpath("//mat-list-item[contains(@id,'1')]//div[contains(@class,'editor-read-only')]//i")).getTagName();
		System.out.println("TEXTAREA TEXT INPUT STYLE IS ITALIC:- "+ TextArea_Italic_format);

		String Textarea_Italic_format_text = General.driver.findElement(By.xpath("//mat-list-item[contains(@id,'1')]//div[contains(@class,'editor-read-only')]//i")).getCssValue("font-style");
		System.out.println("TEXTAREA TEXT INPUT VALUE IS ITALIC:- "+ Textarea_Italic_format_text);

		assertion = video_Italic_format.equalsIgnoreCase(TextArea_Italic_format);
		System.out.println("VIDEO AND TEXTAREA FONT STYLE IS ITALIC:-"+ assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying VIDEO AND TEXTAREA FONT STYLE IS ITALIC  after Search");
		}

		assertion = video_Italic_format_text.equalsIgnoreCase(Textarea_Italic_format_text);
		System.out.println("VIDEO AND TEXTAREA FONT TEXT STYLE IS ITALIC:-"+ assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying VIDEO AND TEXTAREA FONT STYLE IS ITALIC  after Search");
		}

		
		//To Remove the italic format from text
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		Thread.sleep(5000);	
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_ItalicFormat_button);
		Thread.sleep(8000);
		
				
		//To add the underline format from text
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		Thread.sleep(5000);	
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_UnderLineFormat_button);
		Thread.sleep(8000);
		
		String video_Underline_format = General.driver.findElement(By.xpath("//shared-text-editor//div//u")).getTagName();
		System.out.println("VIDEO TEXT INPUT STYLE IS UNDERLINED:- "+ video_Underline_format);

		String video_Underline_format_text = General.driver.findElement(By.xpath("//shared-text-editor//div//u")).getCssValue("text-decoration");
		System.out.println("VIDEO TEXT INPUT VALUE IS UNDERLINED:- "+ video_Underline_format_text);

		String TextArea_Underline_format = General.driver.findElement(By.xpath("//mat-list-item[contains(@id,'1')]//div[contains(@class,'editor-read-only')]//u")).getTagName();
		System.out.println("TEXTAREA TEXT INPUT STYLE IS UNDERLINED:- "+ TextArea_Underline_format);

		String Textarea_Underline_format_text = General.driver.findElement(By.xpath("//mat-list-item[contains(@id,'1')]//div[contains(@class,'editor-read-only')]//u")).getCssValue("text-decoration");
		System.out.println("TEXTAREA TEXT INPUT VALUE IS UNDERLINED:- "+ Textarea_Underline_format_text);

		assertion = video_Underline_format.equalsIgnoreCase(TextArea_Underline_format);
		System.out.println("VIDEO AND TEXTAREA FONT STYLE IS UNDERLINED:-"+ assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying VIDEO AND TEXTAREA FONT STYLE IS UNDERLINED  after Search");
		}

		assertion = video_Underline_format_text.equalsIgnoreCase(Textarea_Underline_format_text);
		System.out.println("VIDEO AND TEXTAREA FONT TEXT STYLE IS UNDERLINED:-"+ assertion);
		if (assertion == false) {
			report("f","Assertion failed while verifying VIDEO AND TEXTAREA FONT STYLE IS UNDERLINED after Search");
		}
		
		
		//To remove the underline format from text
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		Thread.sleep(5000);	
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_UnderLineFormat_button);
		Thread.sleep(8000);
		
		//To add the up align format from text
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		Thread.sleep(5000);	
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_AlignUP_button);
		Thread.sleep(1000);
		
		
		assertion = Verify.action().verifyElementPresent(By.xpath("//shared-video/../div[(@class='top')]"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Subtitles is on the top of video  after Search");
		}
		
		assertion = Verify.action().verifyElementPresent(By.xpath("//shared-video/../div[(@class='top')]//div[contains(text(),'Entering First text on transcription')]"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Subtitles is on the top of video  after Search");
		}
		
		
		//To remove the up align format from text
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		Thread.sleep(5000);	
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_AlignUP_button);
		Thread.sleep(8000);
		
		//To add the down align format from text
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		PM_user.action().transcription_keyEnvents(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		Thread.sleep(5000);	
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_AlignDOWN_button);
		Thread.sleep(8000);
		
		assertion = Verify.action().verifyElementPresent(By.xpath("//shared-video/../div[not(@class='top')]//div[contains(text(),'Entering First text on transcription')]"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Subtitles is on the down of video  after Search");
		}
		
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(By.xpath("//shared-video/../div[not(@class='top')]//div[contains(text(),'Entering First text on transcription')]"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Subtitles is on the bottom of video  after Search");
		} else {
			report("p", "All Assertion passed.");
		}
	}
	
	
	public int getTimeIn_frames_of_segment(int segment) {
		String timeIn=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeIn(segment)).getText();
		System.out.println("timeIn :"+timeIn);
		String timeIn_int=timeIn.replaceAll("[^0-9]", "");
		System.out.println("timeOut_int :"+timeIn_int);
		
		String timeIn_sec=timeIn_int.substring(4,6);
		System.out.println("timeOut_sec :"+timeIn_sec);
		int timeIn_int_sec=Integer.parseInt(timeIn_sec);
		System.out.println("timeOut_int_sec :  "+timeIn_int_sec);	
		return timeIn_int_sec;
		
	}
	
	public int getTimeOut_frames_of_segment(int segment) {
		String timeOut=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_TimeOut(segment)).getText();
		System.out.println("timeOut :"+timeOut);
		String timeOut_int=timeOut.replaceAll("[^0-9]", "");
		System.out.println("timeOut_int :"+timeOut_int);
		
		String timeOut_sec=timeOut_int.substring(4,6);
		System.out.println("timeOut_sec :"+timeOut_sec);
		int timeOut_int_sec=Integer.parseInt(timeOut_sec);
		System.out.println("timeOut_int_sec :  "+timeOut_int_sec);	
		return timeOut_int_sec;
		
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
