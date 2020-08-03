package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
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
import org.openqa.selenium.interactions.Actions;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_570015 {
	
	
		
		Boolean assertion = true;
		LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

		String SubmissionName = "SUB_570015"+CommonElements.BROWSER+"A2";
		String OrganizationName = "Subtitle_Common_orgs";
		String WorkflowName = "transc_Step_Workflow";
		String fileDirName = "common";
		String TranslatorGroupName = "Common_group";
		String targetlanguage_1 = "English (United States)";
		String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
		


		@BeforeMethod
		public void setup() throws Exception {
			General.action().startSystem("SUB_570015");
			dataSet.put("TL_test_case_description",	"SUB-570015:Forward shortcut");
			dataSet.put("TL_internal_testCase_ID", "570015");
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
				Pm_Transcription_Forward_shortcut(SubmissionName, "New");
				Thread.sleep(2000);

			} catch (Exception e) {
				report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			}

		}

		public void Pm_Transcription_Forward_shortcut(String SubmissionName,String status) throws Exception {

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
			
			//check for :- If the video is playing when the user do it, then the video moves back of 2 seconds and is still playing.
			System.out.println("CHECK FOR SCENARIO FIRST WHEN VIDEO IS IN PLAY MODE");
//	        TODO  This Logic is changes as the time is coming in different format			
//		    String Current_Time =General.driver.findElement(By.xpath("//app-time-box")).getAttribute("ng-reflect-current-time");
//		    Thread.sleep(1000);
//		    System.out.println("CURRENT TIME OF VIDEO:- "+ Current_Time);
//			Thread.sleep(3000);
			
			 String hh1 = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(1)).getText();
		     System.out.println("Hour in video time box--->"+hh1);
		     int hh1_int= Integer.parseInt(hh1);
		     String min1 = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(3)).getText();
		     System.out.println("min in video time box--->"+min1);
		     int min1_int= Integer.parseInt(min1);
		     String sec1 = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(5)).getText();
		     System.out.println("sec in video time box--->"+sec1);
		     int sec1_int= Integer.parseInt(sec1);
		     String frames1 = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(7)).getText();
		     System.out.println("frames in video time box--->"+frames1);
		     int frames1_int= Integer.parseInt(frames1);
		     
		     int Current_Sec_BeforeForwardKeys_Play=sec1_int;
		     System.out.println("CURRENT TIME OF VIDEO:- "+ Current_Sec_BeforeForwardKeys_Play);
			
			//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
//	       /* General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
//			Thread.sleep(1000);
//			General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
//			Thread.sleep(1000);
//			General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
//			Thread.sleep(1000);
//			General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
//			Thread.sleep(1000);*/
//			
//			PM_user.action().transcription_enterText_Video(KeyEvent.VK_ALT,KeyEvent.VK_SHIFT,KeyEvent.VK_RIGHT);
//			PM_user.action().transcription_enterText_Video(KeyEvent.VK_ALT,KeyEvent.VK_SHIFT,KeyEvent.VK_RIGHT);
//			PM_user.action().transcription_enterText_Video(KeyEvent.VK_ALT,KeyEvent.VK_SHIFT,KeyEvent.VK_RIGHT);
//			PM_user.action().transcription_enterText_Video(KeyEvent.VK_ALT,KeyEvent.VK_SHIFT,KeyEvent.VK_RIGHT);
			
			if(CommonElements.BROWSER.equalsIgnoreCase("_CHROME_")||CommonElements.BROWSER.equalsIgnoreCase("_EGCH_")) {
				System.out.println("THIS IS CHROME*******");
			  	WebElement tabContent = General.driver.findElement(By.xpath("//video[contains(@crossorigin,'anonymous')]"));
				Actions act1= new Actions(General.driver);
				//TODO TO DO ALT+SHIFT+RIGHT SHORTCUT
				for(int i=1;i<=12;i++) {
				act1.moveToElement(tabContent).click().sendKeys(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT).build().perform();
				Thread.sleep(1000);
				}
			}else if(CommonElements.BROWSER.equalsIgnoreCase("_FIREFOX_")) {
		        General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
				Thread.sleep(1000);
			}
	        
//			String After_Time = General.driver.findElement(By.xpath("//app-time-box")).getAttribute("ng-reflect-current-time");
//			Thread.sleep(1000);
//			System.out.println("TIME OF VIDEO AFTER FORWARD SHORTCUT:- "+ After_Time);
//			Thread.sleep(3000);
//			
//			float f_After_Time=Float.parseFloat(After_Time);
//			System.out.println("FLOT VALUE OF AFTER TIME IS:- " +f_After_Time);
			
			 String sec2 = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(5)).getText();
		     System.out.println("sec in video time box--->"+sec2);
		     int sec2_int= Integer.parseInt(sec2);
		     int Current_Sec_AfterForwadKeys_Play=sec2_int;
		     System.out.println("CURRENT TIME OF VIDEO:- "+ Current_Sec_AfterForwadKeys_Play);
			
			
			assertion = Current_Sec_AfterForwadKeys_Play > Current_Sec_BeforeForwardKeys_Play;
			System.out.println("TIME OF VIDEO AFTER FORWARD SHORTCUT IS GREATER THAN CURRENT TIME:-" +assertion);
			if (assertion == false) {
				report("f","Assertion failed while verifying TIME OF VIDEO AFTER FORWARD SHORTCUT IS GREATER THAN CURRENT TIME  after Search");
			} 
			
			//check for :- If the video is paused when the user do it, then the video moves back of 2 seconds and is still paused.
			System.out.println("CHECK FOR SCENARIO SECOND WHEN VIDEO IS IN PAUSE MODE");
			Thread.sleep(13000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
	        Thread.sleep(3000);
	        General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
	        Thread.sleep(3000);
//	       TODO This Logic is changes as the time is comming in different formate
//			String Current_Time_1 =General.driver.findElement(By.xpath("//app-time-box")).getAttribute("ng-reflect-current-time");
//			Thread.sleep(1000);
//			System.out.println("CURRENT TIME OF VIDEO:- "+ Current_Time_1);
//		    Thread.sleep(3000);
//				
//			float f_Current_Time_1=Float.parseFloat(Current_Time_1);
//			System.out.println("FLOT VALUE OF CURRENT TIME IS:- " +f_Current_Time_1);
//			
//			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
//	        Thread.sleep(3000);
	        
	        String sec3 = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(5)).getText();
	        System.out.println("sec in video time box--->"+sec3);
		    int sec3_int= Integer.parseInt(sec3);
		    int Current_SecBeforeForwardkwordKeys_Pause=sec3_int;
		    System.out.println("CURRENT TIME OF VIDEO:- "+ Current_SecBeforeForwardkwordKeys_Pause);
				
				//TODO CLICK AND SHORT KEYS NOT WORKING FOR VIDEO SO CREATED BELOW ROBOT METHOD
	            /*General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
				Thread.sleep(3000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video);
				Thread.sleep(3000);
				General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
				Thread.sleep(1000);
				*/
				
//				PM_user.action().transcription_enterText_Video(KeyEvent.VK_ALT,KeyEvent.VK_SHIFT,KeyEvent.VK_RIGHT);
//				PM_user.action().transcription_enterText_Video(KeyEvent.VK_ALT,KeyEvent.VK_SHIFT,KeyEvent.VK_RIGHT);
//				PM_user.action().transcription_enterText_Video(KeyEvent.VK_ALT,KeyEvent.VK_SHIFT,KeyEvent.VK_RIGHT);
//				PM_user.action().transcription_enterText_Video(KeyEvent.VK_ALT,KeyEvent.VK_SHIFT,KeyEvent.VK_RIGHT);
				
		    if(CommonElements.BROWSER.equalsIgnoreCase("_CHROME_") || CommonElements.BROWSER.equalsIgnoreCase("_EGCH_")) {
			 Robot robot1 = new Robot();
		     robot1.keyPress(KeyEvent.VK_ALT);
		     robot1.delay(1000); 
		     robot1.keyPress(KeyEvent.VK_SHIFT);
		     robot1.delay(1000); 
		     robot1.keyPress(KeyEvent.VK_RIGHT);
		     robot1.delay(1000); 
		     robot1.keyRelease(KeyEvent.VK_RIGHT);
		     robot1.delay(1000); 
		     robot1.keyRelease(KeyEvent.VK_SHIFT);
		     robot1.delay(1000); 
		     robot1.keyRelease(KeyEvent.VK_ALT);
		     robot1.delay(1000); 
		    }else {
		    	General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video,Keys.chord(Keys.ALT, Keys.SHIFT,Keys.ARROW_RIGHT));
				 Thread.sleep(1000);
		    }
		     
//	          TODO Below code is not required as video date is coming in different format	
//		    String After_Time_paused = General.driver.findElement(By.xpath("//app-time-box")).getAttribute("ng-reflect-current-time");
//		    Thread.sleep(1000);
//			System.out.println("TIME OF VIDEO AFTER FORWARD SHORTCUT:- "+ After_Time_paused);
//			Thread.sleep(3000);
//				
//			float f_After_Time_1=Float.parseFloat(After_Time_paused);
//			System.out.println("FLOT VALUE OF AFTER TIME IS:- " +f_After_Time_1);
		     
		     
		     String sec4 = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_vedioTime(5)).getText();
		     System.out.println("sec in video time box--->"+sec4);
		     int sec4_int= Integer.parseInt(sec4);
		     int Current_Sec_AfterForwardKeys_Pause=sec4_int;
		     System.out.println("CURRENT TIME OF VIDEO:- "+ Current_Sec_AfterForwardKeys_Pause);
			
				
		     assertion = Current_Sec_AfterForwardKeys_Pause == Current_SecBeforeForwardkwordKeys_Pause+2;
			System.out.println("TIME OF VIDEO AFTER FORWARD SHORTCUT IS GREATER THAN CURRENT TIME:-" +assertion);
			if (assertion == false) {
					report("f","Assertion failed while verifying TIME OF VIDEO AFTER FORWARD SHORTCUT IS GREATER THAN CURRENT TIME  after Search");
			} 
				
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_keyboard_shortcuts);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_keyboard_shortcuts);
			Thread.sleep(2000);
				
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Forward_Shortcut, 5);
			if (assertion == false) {
					report("f","Assertion failed while verifying PM_Transcription_Forward_Shortcut is present  after Search");
			}
				
		}

			public void assertion() throws Exception {
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Forward_Shortcut, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PM_Transcription_Forward_Shortcut is present  after Search");
				}  else {
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
