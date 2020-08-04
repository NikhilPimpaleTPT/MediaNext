package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.PD_Pm_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:This test case is to verify if the characters are downloaded in Audio Scripts (DOCX, XLSX)
 *
 */

public class Sub_2353547 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_2353547"+CommonElements.BROWSER+"B8";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
    Boolean assertion = true;
    String SUBMISSION_FILES_PATH = "data" + File.separator + "Submission";
    String sourceDir = (new File(this.SUBMISSION_FILES_PATH).getAbsolutePath() + "\\");
    String characters[]= {"Segment No 1","Segment No 2"};
    
    
   	@BeforeMethod
   	public void setup() throws Exception {
   		General.action().startSystem("SUB_2353547");
   		dataSet.put("TL_test_case_description", "SUB_2353547:download the character info in Audio Scripts (DOCX, XLSX)");
   		dataSet.put("TL_internal_testCase_ID", "2353547");
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
   			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
   			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,"70137");
   			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
   			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
   			Thread.sleep(2000);
   		
   			//SEARCH SUBMISSION AND CHECK
   			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
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
   			PM_transcription_onGoing_ReadingSpeed_Submission(SubmissionName, targetlanguage, false, true);
			Thread.sleep(1000);
			PM_user.action().Navigate(menu_submission);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
   			Thread.sleep(2000);
   			
   			
   			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home")+ "\\Downloads\\");
			Thread.sleep(2000);
			
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_file_download);
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_download_dropdownArrow);
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_download_dropdownOption("Audio Scripts (DOCX, XLSX)"));
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_download_languageCheckbox("German (Germany)"));
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_download_file_button);
			Thread.sleep(2000);
			
			//Download file for Different browsers
			General.action ().FileDownloadMethodForDifferentBrowser();
	    	
	    	//Method To Get File name
	    	General.action().GetFileName(System.getProperty("user.home") + "\\Downloads\\");
	    	System.out.println("File is downloaded inside folder :"+System.getProperty("user.home") + "\\Downloads\\");
			 
			//To verify audio scripts file downloaded in Lical Drive
			assertion =General.action().DownloadedFile.contains(SubmissionName);
			System.out.println("Assertion Is======>"+assertion);
			if(assertion==false){
				report("f","audio scripts File is not Downloaded in Local Drive.");
			}else{
				System.out.println("audio scripts File is Downloaded in Local Drive.");		
			}
			 
			 
			System.out.println("Downloaded file in TC is:"+General.action().DownloadedFile);
			 
			//Copy file to Test case Data folder
			File file1 = new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile); 
			Thread.sleep(4000);
			 
			System.out.println("data folder file is:"+sourceDir);
			 
			FileUtil.deleteDir(sourceDir+"2353547\\");
			Thread.sleep(2000);
			
			//To move zip file into workspace data folder
			System.out.println("Source File:"+System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile);
			System.out.println("Destination File:"+sourceDir+"2353547\\"+General.action().DownloadedFile);
			FileUtils.copyFile(new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile),new File(sourceDir+"2353547\\"+General.action().DownloadedFile));
   			
   		} catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
       }
	}
   	
   	
   	
   	public void PM_transcription_onGoing_ReadingSpeed_Submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {

		System.out.println("INSIDE PM_transcription_onGoing_ReadingSpeed_Submission()");

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
        Thread.sleep(10000);
        
        
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
        
        General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_button);
		Thread.sleep(2000);
        General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_button);
		Thread.sleep(2000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_addButton);
		Thread.sleep(2000);
        General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_addButton);
		Thread.sleep(2000);
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input,characters[0]);
		Thread.sleep(2000);
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_AddCharacters_addButton);
		Thread.sleep(2000);
	    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_AddCharacters_addButton);
		Thread.sleep(2000);
		
		 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_button);
			Thread.sleep(2000);
	        General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_button);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_addButton);
			Thread.sleep(2000);
	        General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_addButton);
			Thread.sleep(2000);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input);
			Thread.sleep(2000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input);
			Thread.sleep(2000);
			General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_add_input,characters[1]);
			Thread.sleep(2000);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_AddCharacters_addButton);
			Thread.sleep(2000);
		    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_AddCharacters_addButton);
			Thread.sleep(2000);
			
			General.action().driver.navigate().refresh();
			Thread.sleep(2000);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Arrow(1));
			Thread.sleep(2000);
		    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Arrow(1));
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Options(characters[0]));
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Arrow(2));
			Thread.sleep(2000);
		    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Arrow(2));
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Options(characters[1]));
			Thread.sleep(2000);
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Options_Selected(1,characters[0]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying added character");
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Options_Selected(2,characters[1]), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying added character");
			}
			
			 if(back){
			    	System.out.println("IN BACK-----");
			    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
						General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
						Thread.sleep(1000);
						General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
				    	}
			  }
        
        
   	}
   			public void assertion() throws Exception {
   				
   				//Note : This TC is blocked as need to verify content from xlsx file and doc file (File is downloaded thorough script inside data->Submission->2353547 )
   				//Verify inside file
   				//1)Files should contain the characters info selected for subtitles between Duration and Source columns.
   				//2)it should be empty if the character is not added to the subtitle.
   				
   				assertion =General.action().DownloadedFile.contains(SubmissionName);
   				System.out.println("Assertion Is======>"+assertion);
   				if(assertion==false){
   					report("f","audio scripts File is not Downloaded in Local Drive.");
   				}else {
   						report("b", "Assertion is blocked");
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
   			
