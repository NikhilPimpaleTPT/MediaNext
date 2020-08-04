package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.gs4tr.qa.util.FileZipUnzipThread;
import org.gs4tr.qa.util.RobotExt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:This test case verifies ttml export and import for charters
 *
 */

public class Sub_2498939 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_2498939"+CommonElements.BROWSER+"Y";
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
    File filepath1;
    String downloadedFilePath;
    String path;
    
    
   	@BeforeMethod
   	public void setup() throws Exception {
   		General.action().startSystem("SUB_2498939");
   		dataSet.put("TL_test_case_description", "SUB_2498939:As a PM, Character - TTML export and import");
   		dataSet.put("TL_internal_testCase_ID", "2498939");
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
   			
   			for(int i=1;i<=2;i++) {
   			PM_user.action().Navigate(menu_submission);
   			//TODO NEW IMPL OF SUBMISSION CREATION USING AMPERSAND CHARACTERS
   			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
   			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
   			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,"70137");
   			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
   			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName+i,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
   			Thread.sleep(2000);
   			}
   		
   			//SEARCH SUBMISSION AND CHECK
   			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
   			Thread.sleep(2000);
   			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+1), 5);
   			if (assertion == false) {
   				report("f","Assertion failed while verifying SubmissionName  after Search");
   			}
   			
   			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+2), 5);
   			if (assertion == false) {
   				report("f","Assertion failed while verifying SubmissionName  after Search");
   			}
   			
   			Thread.sleep(2000);
   			PM_user.action().Navigate(menu_to_claim);
   			Thread.sleep(2000);
   			PM_user.action().PM_ToClaim(SubmissionName+1);
   			Thread.sleep(2000);
   			PM_user.action().Navigate(menu_ongoing);
   			Thread.sleep(2000);
   			PM_transcription_onGoing_Submission1(SubmissionName+1, targetlanguage, false, true);
			Thread.sleep(1000);
			PM_user.action().Navigate(menu_submission);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson_andCheck(SubmissionName+1);
   			Thread.sleep(2000);
   			
   			
   			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home")+ "\\Downloads\\");
			Thread.sleep(2000);
			
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_file_download);
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_download_dropdownArrow);
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_download_dropdownOption("TTML1"));
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_download_languageCheckbox("German (Germany)"));
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_download_file_button);
			Thread.sleep(2000);
			
			Thread.sleep(7000);
			if (CommonElements.BROWSER.contains("FIREFOX")) {
				System.out.println("------THIS IS FIREFOX-----");
				Thread.sleep(3000);
				General.action().downloadFileFirefox();
				Thread.sleep(3000);

			} else {
				System.out.println("------THIS IS CHROME-----");
				Thread.sleep(5000);
			}
			// UNZIP THE FILE
			System.out.println("inside ttml unzip");
			String foldertoUnZipttml = FileZipUnzipThread.utils().getFolderName(System.getProperty("user.home") + "\\Downloads\\", "SUB");
			System.out.println("inside ttml unzip step 1");
			System.out.println("foldertoUnZip--->" + foldertoUnZipttml);
			System.out.println("Download Path--->" + System.getProperty("user.home") + "\\Downloads\\" + foldertoUnZipttml);
			General.action().extractFolder(System.getProperty("user.home") + "\\Downloads\\" + foldertoUnZipttml,System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(1000);

			// GET PATH TILL PARENT FOLDER
			String ttml_fileName_withoutExtension = General.action().getZipFilewithoutExtension(System.getProperty("user.home") + "\\Downloads\\");
			System.out.println("foldertoUnZip_new--->" + ttml_fileName_withoutExtension);
			String ttml_downloadPath_withoutExtension = System.getProperty("user.home") + "\\Downloads\\"+ ttml_fileName_withoutExtension;
			System.out.println("downloadPath_withoutExtension--->" + ttml_downloadPath_withoutExtension);
			System.out.println("before calling read ttml");

			
			readTTMLFile(ttml_downloadPath_withoutExtension);// this method will read ttml file from download folder.
			
			System.out.println("this is chrome");
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("id=\"Segment No 2\" type=\"character\"");
			if(assertion == false) {
			report("f","Assertion failed while verifying charecter in  ttml File content.");
			}
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("Segment No 1\" type=\"character");
			if(assertion == false) {
			report("f","Assertion failed while verifying charecter in  ttml File content.");
			}
			Thread.sleep(2000);
   			PM_user.action().Navigate(menu_to_claim);
   			Thread.sleep(2000);
   			PM_user.action().PM_ToClaim(SubmissionName+2);
   			Thread.sleep(2000);
   			PM_user.action().Navigate(menu_ongoing);
   			Thread.sleep(2000);
   			
   			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
   			Thread.sleep(2000);
   			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
   			Thread.sleep(2000);
   			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
   			Thread.sleep(2000);
   			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input,SubmissionName);
   			Thread.sleep(2000);

   			if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, targetlanguage))) {
   				System.out.println("INSIDE IF--------");
   				General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, targetlanguage));
   			}
   			Thread.sleep(4000);
   			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
   	        Thread.sleep(10000);
   	    
	        PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
	        Thread.sleep(2000);
	        PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
	        Thread.sleep(2000);
   	        
   			
   	        Thread.sleep(2000);
 	        PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
 	        Thread.sleep(2000);
 	       
 	        
   			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file, 5);
   			if (assertion == false) {
   				report("f", "Assertion failed while verifying PM_Ongoing_Import_file  after Search");
   			}
   			
   			
   			Thread.sleep(3000);
   			
   			File afile=new File(CommonElements.action().FILE_ABSOLUTE_PATH+"2498939");
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
   	    	Thread.sleep(3000);
   	    	System.out.println("INVALID PATH-------->"+path);
   	        //TODO THIS IMPL IS DUE TO AS FILE CANNOT BE IMPORTED PROPERLY IN FIREFOX THROUGH senKeys(), so FOR FIREFOX WE ARE USING ROBOT CLASS AND FOR CHROME WE ARE USING sendKeys()
   	        System.out.println("WHTS IS THIS---->"+CommonElements.BROWSER);
   	        if(CommonElements.BROWSER.contains("FIREFOX")) {
   		    System.out.println("------THIS IS FIREFOX-----");
   		    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file);
   		    Thread.sleep(3000);
   		    PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_overwrite_current_translation);
	        Thread.sleep(2000);
   		    RobotExt.robot().delay(2000);
   		    RobotExt.robot().sendKeys(path);
   		    RobotExt.robot().processFilePath();
   		    RobotExt.robot().delay(2000);
   	        }else {
   	        System.out.println("------THIS IS CHROME-----");
   	        General.action().type(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file_input,path);
   	        }
   	        Thread.sleep(5000);
   	        PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_overwrite_current_translation);
            Thread.sleep(8000);
           
           General.action().Enter_keyEnvents(KeyEvent.VK_ENTER);
           Thread.sleep(8000);
   	       General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Arrow(1));
		   Thread.sleep(8000);
		   General.action().mouseOver(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Arrow(1));
		   Thread.sleep(8000);
	       General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Arrow(1));
		   Thread.sleep(6000);
		   
		   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Options(characters[0]), 5);
		   if (assertion == false) {
				report("f","Assertion failed while verifying imported character");
		   }
		   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Options(characters[1]), 5);
		   if (assertion == false) {
				report("f","Assertion failed while verifying imported character");
		   }
		   General.action().Enter_keyEnvents(KeyEvent.VK_ENTER);
           Thread.sleep(5000);
           General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Arrow(2));
		   Thread.sleep(8000);
		   General.action().mouseOver(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Arrow(2));
		   Thread.sleep(8000);
	       General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Arrow(2));
		   Thread.sleep(6000);
		   
		   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Options(characters[0]), 5);
		   if (assertion == false) {
				report("f","Assertion failed while verifying imported character");
		   }
		   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Options(characters[1]), 5);
		   if (assertion == false) {
				report("f","Assertion failed while verifying imported character");
		   }
			
			
   		} catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
      }
	}
   	
   	
   	public void PM_transcription_onGoing_Submission1(String SubmissionName, String target,boolean complete,boolean back) throws Exception {

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
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Arrow(2));			Thread.sleep(2000);
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
   	
   	public void readTTMLFile(String downloadPath_withoutExtension) throws Exception {
		try {

//			File filepath = new File(System.getProperty("user.home")+ "\\Downloads\\");
			File filepath = new File(downloadPath_withoutExtension);

			String[] filenames = filepath.list();
			int countofFiles = filenames.length;
			System.out.println("countofFiles##########  :- " + countofFiles);

			for (String filename : filenames) {
				System.out.println(filename);

				downloadedFilePath = downloadPath_withoutExtension + "\\" + filename;
				System.out.println("finalpath########### :- " + downloadedFilePath);

				BufferedReader reader;

				reader = new BufferedReader(new FileReader(downloadedFilePath));
				String line = reader.readLine();
				while (line != null) {
					System.out.println(line);
					// read next line
					line = reader.readLine();
				}
				reader.close();

				filepath1 = new File(downloadPath_withoutExtension + "\\" + filename);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
   	
   	public void assertion() throws Exception {
	   //Verify the list of characters should be populated for the subtitles characters defined for each subtitle is selected on the subtitles' table,
   	   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Options(characters[1]), 5);
	   if (assertion == false) {
			report("f","Assertion failed while verifying imported character");
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
		


