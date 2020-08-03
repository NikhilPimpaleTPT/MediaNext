package Smoke;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.gs4tr.qa.util.FileZipUnzipThread;
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
import modules.PM_user;
import modules.Reviewer;
import modules.Verify;

public class Sub_765160 {
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_765160"+CommonElements.BROWSER+"A1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_ToClaim = "To Claim";
	String menu_Ongoing = "Ongoing";
	String menu_Completed = "Completed";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
    String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
    String modified_input = "Modified Target";
	File filepath1;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_765160");
		dataSet.put("TL_test_case_description","SUB-765160:Ongoing Jobs with transcription job");
		dataSet.put("TL_internal_testCase_ID", "765160");
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
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE, "");
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ToClaim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_Ongoing);
			Thread.sleep(2000);
			PM_onGoing_submission(SubmissionName, targetlanguage_1, true, false);
			Thread.sleep(2000);		
			
			Reviewer.action().Navigate(menu_Completed);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_file_download);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_SRT);
			Thread.sleep(7000);
			
			if(CommonElements.BROWSER.contains("FIREFOX")) {
		    	System.out.println("------THIS IS FIREFOX-----");
		    	Thread.sleep(3000);
				General.action().downloadFileFirefox();
				Thread.sleep(3000);
				
	    	}else {
	        	System.out.println("------THIS IS CHROME-----");
	    		Thread.sleep(5000);
	    	}
			
			//UNZIP THE FILE
    		String foldertoUnZip = FileZipUnzipThread.utils().getFolderName(System.getProperty("user.home")+ "\\Downloads\\","SUB");
    		System.out.println("foldertoUnZip--->"+foldertoUnZip);
    		System.out.println("Download Path--->"+System.getProperty("user.home")+ "\\Downloads\\"  + foldertoUnZip);
    		General.action().extractFolder(System.getProperty("user.home")+ "\\Downloads\\"+foldertoUnZip  , System.getProperty("user.home")+ "\\Downloads\\");
    	    Thread.sleep(1000);
    	    
    	    //GET PATH TILL PARENT FOLDER
    		String fileName_withoutExtension =General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
    		System.out.println("foldertoUnZip_new--->"+fileName_withoutExtension);
    		String downloadPath_withoutExtension=System.getProperty("user.home")+ "\\Downloads\\"+fileName_withoutExtension;
    		System.out.println("downloadPath_withoutExtension--->"+downloadPath_withoutExtension);
    		
    		
            readSrtFile(downloadPath_withoutExtension);// this method will read srt file from download folder.
			
			Thread.sleep(1000);

		  } catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		 }

	}
	public void readSrtFile(String downloadPath_withoutExtension) throws Exception {
		try {

//			File filepath = new File(System.getProperty("user.home") + "\\Downloads\\");
			File filepath = new File(downloadPath_withoutExtension);

			String[] filenames = filepath.list();
			int countofFiles = filenames.length;
			System.out.println("countofFiles##########  :- " + countofFiles);

			for (String filename : filenames) {
				System.out.println(filename);

				String path = downloadPath_withoutExtension+"\\"+ filename;
			
				System.out.println("finalpath########### :- " + path);

				BufferedReader reader;

				reader = new BufferedReader(new FileReader(path));
				String line = reader.readLine();
				while (line != null) {
					System.out.println(line);
					// read next line
					line = reader.readLine();
				}
              reader.close();

			
                filepath1 = new File(downloadPath_withoutExtension+"\\"+ filename);
                Thread.sleep(1000);
                boolean Finalresult = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("test");
				System.out.println("Downloaded file contains string :-" +Finalresult);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void PM_onGoing_submission(String SubmissionName, String target,boolean complete, boolean back) throws Exception {

		System.out.println("INSIDE PM_onGoing_submission  method()");

		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		Thread.sleep(1000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		Thread.sleep(1000);
		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		Thread.sleep(1000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName, target))) {
			
			System.out.println("INSIDE IF--------");
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName,target));
		}
		Thread.sleep(1000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
		Thread.sleep(8000);
		
		Thread.sleep(1000);
		
		// To Verify Trancsription Screen view.
		
		/*assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_transcription_Video_Screen,5);// To verify video Screen
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_ongoing_transcription_screen  after Search");
        }
		Thread.sleep(2000);
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_transcription_screen);
		Thread.sleep(1000);*/

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_transcription_screen,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_ongoing_transcription_screen  after Search");
		}
		
		// Verify List of Segments is present on transcription screen.
	    Thread.sleep(2000);
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_transcription_table);
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_transcription_table,5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PM_ongoing_transcription_table  after Search");
				}
		
	   // Verify Ellipse button(3dot button)is present on transcription screen.
	   Thread.sleep(2000);
	   PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
	   Thread.sleep(1000);

	   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon,5);
	   if (assertion == false) {
								report("f","Assertion failed while verifying PM_ongoing_multiOption_icon  after Search");
	  }
	   
	   Thread.sleep(2000);
	   PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_transcription_tab_group);
	   Thread.sleep(1000);

	   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_transcription_tab_group,5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PM_ongoing_transcription_tab_group  after Search");
	   }
		
	   
	 // Verify Styling, Quality Check & Task Settings is present on transcription screen.
	   
	   Thread.sleep(2000);
	   PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_button);
	   Thread.sleep(1000);

	   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_button,5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Video Styling button");
	   }
	
	   Thread.sleep(2000);
	   PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_button);
	   Thread.sleep(1000);

	   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_button,5);
	   if (assertion == false) {
								report("f","Assertion failed while verifying Quality Check button");
	   }
	   
	   
	   Thread.sleep(2000);
	   PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_rightArrow);
	   Thread.sleep(1000);					
       Thread.sleep(2000);
	   PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_rightArrow);
	   Thread.sleep(1000);

	   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_TaskSetting_button,5);
	   if (assertion == false) {
	   report("f","Assertion failed while verifying Task Setting button");
	   }
	   Thread.sleep(2000);
	   PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
	   Thread.sleep(1000);

	   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Complete_Button,5);
	   if (assertion == false) {
	   report("f","Assertion failed while verifying CompletE Task Button");
	   }
	   
	  List<WebElement> listofIds_beforeEditor = General.driver.findElements(Pm_User_Submission_Locators.Locator().ListofIDs);
		Thread.sleep(1000);
		System.out.println("No of IDS before video editor-------->"+ listofIds_beforeEditor.size());
		Thread.sleep(4000);
		
		String result_before = General.driver.findElement(By.xpath("//transcription-table")).getText();
		System.out.println("CONTENT OF TRANSCRIPTION BEFORE VIDEO EDITOR:-" +result_before);
		
		 //TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
//		 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
//		 Thread.sleep(3000);
//		
//		 General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, "n"));
//		 Thread.sleep(1000);
		
		 PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_N);

		
	   //TODO not used below code change this with above code
//		WebElement tabContent = General.driver.findElement(By.xpath("//div[contains(@class,'tab-conten')]"));
//	    WebElement tabContent = General.driver.findElement(By.xpath("//video[contains(@crossorigin,'anonymous')]"));
//		
//		Actions act= new Actions(General.driver);
//		act.moveToElement(tabContent).click().sendKeys(Keys.ALT, "n").build().perform();
		
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_video_editor);
		 Thread.sleep(5000);	
		
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_ongoing_video_editor,"test");
		 Thread.sleep(5000);	
			
		 if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_calibration_button)){
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_calibration_button);
		 Thread.sleep(2000);	
			}
			
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_ongoing_video_editor,Keys.chord(Keys.ALT, Keys.ENTER));
		 Thread.sleep(2000);
			
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_table_content);
		 Thread.sleep(2000);
		// PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_video_editor);
		 Thread.sleep(5000);	
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		 Thread.sleep(5000);	
		 
		 PM_user.action().transcription_keyEnvents(KeyEvent.VK_CONTROL,KeyEvent.VK_A);
		// PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_ongoing_video_editor,Keys.chord(Keys.CONTROL,"a"));
		 Thread.sleep(5000);	
		// PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_ongoing_video_editor,Keys.chord(Keys.DELETE));
		 PM_user.action().transcription_keyEnvents(KeyEvent.VK_DELETE);
		 Thread.sleep(5000);	
			
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_ongoing_video_editor,modified_input);
		 Thread.sleep(5000);
		 PM_user.action().transcription_keyEnvents(KeyEvent.VK_ALT,KeyEvent.VK_ENTER);
		// PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_ongoing_video_editor,Keys.chord(Keys.ALT, Keys.ENTER));
		// Thread.sleep(2000);
			
		 List<WebElement> listofIds_afterEditor = General.driver.findElements(Pm_User_Submission_Locators.Locator().ListofIDs);
		 Thread.sleep(1000);
		 System.out.println("No of IDS After edit subtitles in video editor-------->"+ listofIds_afterEditor.size());		
		 Thread.sleep(2000);	
			
		 String result_after = General.driver.findElement(By.xpath("//transcription-table")).getText();
		 System.out.println("CONTENT OF TRANSCRIPTION AFTER VIDEO EDITOR:-" +result_after );
			
		 boolean result = result_after.contains(modified_input);
		 System.out.println("CONTENT OF TRANSCRIPTION AFTER VIDEO EDITOR CONTAINS CHINESE STRING:-" +result);


	    Thread.sleep(2000);
	    PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
	    Thread.sleep(1000);

        // Verify Import file is present on transcription screen.
	    Thread.sleep(2000);
	    PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file);
	    Thread.sleep(1000);

	    assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file,5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PM_Ongoing_Import_file  after Search");
				}

       // Verify Find & Replace is present on transcription screen.
	   Thread.sleep(2000);
	   PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace);
	   Thread.sleep(1000);

	   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace,5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PM_ongoing_find_and_replace  after Search");
				}
       // Verify Keyboard Shortcuts is present on transcription screen.
	   Thread.sleep(2000);
	   PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_keyboard_shortcuts);
	   Thread.sleep(1000);

	   assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_keyboard_shortcuts,5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PM_ongoing_keyboard_shortcuts  after Search");
				}
	   Thread.sleep(1000);
       PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace);
	   Thread.sleep(1000);
	   PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_Close_button);		
	   Thread.sleep(3000);
	
	
if (complete) {
			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
			Thread.sleep(1000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
			Thread.sleep(1000);
			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
			Thread.sleep(1000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
			Thread.sleep(3000);
			if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
		    }
		}
	    
	    if(back){
	    	System.out.println("IN BACK-----");
	    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
		    	}
	    }

		System.out.println("EOM PM_Ongoing  method()");
	}

	public void assertion() throws Exception {
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(modified_input);;
		if (assertion == false) {
			report("f","Assertion failed while verifying File content with String.");
		} else {
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
