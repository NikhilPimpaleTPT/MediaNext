package Smoke;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.gs4tr.qa.util.FileZipUnzipThread;
import org.gs4tr.qa.util.RobotExt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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

public class Sub_184531 {
	
	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_184531"+CommonElements.BROWSER+"E1";
    String targetlanguage_1 = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
    String TranslatorGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String trans_target_text = "Login + Logout";
	String review_target_text = "Login & Logout";
    String input_1000_chars = "I promised to look after a friends cat for the week. My place has a glass atrium that goes through two levels, I have put the cat in there with enough food and water to last the week. I am looking forward to the end of the week. It is just sitting there glaring at me, it doesn't do anything else. I can tell it would like to kill me. If I knew I could get a perfect replacement cat, I would kill this one now and replace it Friday afternoon. As we sit here glaring at each other I have already worked out several ways to kill itThe simplest would be to drop heavy items on it from the upstairs bedroom though I have enough basic engineering knowledge to assume that I could build some form of 'spear like' projectile device from parts in the downstairs shed. If the atrium was waterproof, the most entertaining would be to flood it with water. It wouldn't have to be that deep, just deeper than the cat.I don't know how long cats can swim but I doubt it would be for a whole week. If it kept the swimming up for too long I could always try dropping things on it as well. I have read that drowning is one of the most peaceful ways to die so really it would be a win win situation for me and the cat I think.";
	String input_multi_line = "It was the best of times, it was the worst of times,\n"
         + "it was the age of wisdom, it was the age of foolishness,\n"
         + "it was the epoch of belief, it was the epoch of incredulity,\n";
	
	String target_input = "Jellyfish at the Monterey Aquarium.";
	  File filepath1;

	 

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_184531");
		dataSet.put("TL_test_case_description","Ongoing jobs");
		dataSet.put("TL_internal_testCase_ID", "184531");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
            // TODO NEW IMPL OF SUBMISSION CREATION
            PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
            PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
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
			
     		General.action().logoutMethod();

			// tran login and check for normal submission
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			translate_onGoing_submission(SubmissionName, targetlanguage_1,true, true);
			Thread.sleep(1000);
					
			Thread.sleep(2000);
			Translator.action().Navigate(menu_completed);
			Thread.sleep(2000);
			Translator.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().Trans_file_download);
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().Trans_FileDownload_SRT);
			Thread.sleep(5000);
			
			if(CommonElements.BROWSER.contains("FIREFOX")) {
		    	System.out.println("------THIS IS FIREFOX-----");
		    	Thread.sleep(2000);
				General.action().downloadFileFirefox();
				Thread.sleep(2000);		
				
				
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
			Thread.sleep(2000);

	

		
} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}

	public void readSrtFile(String downloadPath_withoutExtension) throws Exception {
		try {

			//File filepath = new File(System.getProperty("user.home") + "\\Downloads\\");
			File filepath = new File(downloadPath_withoutExtension);

			String[] filenames = filepath.list();
			int countofFiles = filenames.length;
			System.out.println("countofFiles##########  :- " + countofFiles);

			for (String filename : filenames) {
				System.out.println(filename);

				String path = downloadPath_withoutExtension+"\\" + filename;
			
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
                boolean Finalresult = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(input_1000_chars);
				System.out.println("Downloaded file contains string :-" +Finalresult);
}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public void translate_onGoing_submission(String SubmissionName,String target, boolean complete, boolean back) throws Exception {

		System.out.println("INSIDE translate_onGoing_submission  method()");

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(TranslatorLocators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);

		Thread.sleep(2000);
	
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		    Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
			Thread.sleep(1000);
			
			assertion =General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText().contains("Jellyfish at the Monterey Aquarium.");
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
		
			String target_segment_endWith_dot = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText();
			System.out.println("target_segment_endsWith_dot*********:- " +target_segment_endWith_dot.endsWith("."));
			Thread.sleep(1000);
			
			assertion = target_segment_endWith_dot.endsWith(".");
			if (assertion == false) {
				report("f","Assertion failed while verifying target_segment_endsWith_dot after search.");
			}
			
			String target_segment_contains_tag_br = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText();
			System.out.println("target_segment_contains_tag_br*********:- " +target_segment_contains_tag_br.endsWith("<br/>"));
			Thread.sleep(1000);
			
			assertion = !target_segment_contains_tag_br.endsWith("<br/>");
			if (assertion == false) {
				report("f","Assertion failed while verifying target_segment_contains_tag_br after search.");
			}
			
			
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		    Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(2), input_multi_line);
			
			String target_segment_contains_multiLine = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(2)).getText();
			System.out.println("target_segment_contains_multiLine*********:- " +target_segment_contains_multiLine.contains("\n"));
			Thread.sleep(1000);
			
			assertion = target_segment_contains_multiLine.contains("\n");
			if (assertion == false) {
				report("f","Assertion failed while verifying target_segment_endsWith_dot after search.");
			}
			

			
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
		    Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(3), input_1000_chars);
			
			String target_segment_contains_1000_chars = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(3)).getText();
			System.out.println("target_segment_contains_1000_chars*********:- " +target_segment_contains_1000_chars.length());
			Thread.sleep(1000);
			
			int char_lenth = target_segment_contains_1000_chars.length();
			if(char_lenth>=1300){
				System.out.println("TARGET SEGMENT CAN CONTAINS UPTO 1000 CHARS");
			}
			else{
				System.out.println("TARGET SEGMENT CAN NOT CONTAINS UPTO 1000 CHARS");
			}
			
		if (complete) {
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
			Thread.sleep(3000);
			if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
		    	}
		}

		if (back) {
			System.out.println("IN BACK-----");
			if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
		    	}
		}

		System.out.println("EOM translate_onGoing_submission  method()");
	}
		
	
	public void assertion() throws Exception {
	
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(target_input);;
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



