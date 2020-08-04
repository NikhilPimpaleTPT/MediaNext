package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This test case verifies if the downloaded translated file have the splitted & merged segments from the submission
 *Preconditions:Create submission with TTML files
 */ 


public class Sub_1343932 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1343932"+CommonElements.BROWSER+"A3";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String menu_completed = "Completed";
	String path;
	File filepath1;
	String downloadedSrtFileName;
	String downloadedFileName;
	String DownloadedfileName;
	String downloadPath_withoutExtension;
	boolean Finalresult1=true;
    
    Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1343932");
		dataSet.put("TL_test_case_description", "SUB-1343932:Download final translation after split & merge segments.");
		dataSet.put("TL_internal_testCase_ID", "1343932");
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
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_TTML_FOLDER);
			PM_user.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, Targetlanguage);
			Thread.sleep(2000);
		
			//SEARCH SUBMISSION AND CHECK
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			Thread.sleep(2000);
			
			General.action().logoutMethod();

			// trans login
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Thread.sleep(1000);
		    translate_onGoing_submission(SubmissionName,targetlanguage,true,false);
		    Thread.sleep(2000);
		    Translator.action().Navigate(menu_completed);
			 Thread.sleep(2000);
			 Translator.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_file_download, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Download file Icon after Search");
			}
			
			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_file_download);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().Trans_file_download);
			Thread.sleep(2000);
			General.action().Click(ReviewerLocators.Locator().review_TargetSegement_FileDownLoad_format("SRT"));
			Thread.sleep(7000);
			
			
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
			
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("<i>Jellyfish at the Monterey</i>");;
			if (assertion == false) {
				report("f","Assertion failed while verifying File content with String.");
			}
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("<i>Aquarium</i>");;
			if (assertion == false) {
				report("f","Assertion failed while verifying File content with String.");
			}
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("<i>Shaky Hands...</i>\n" + 
					"<i>Ah yes, this is better...</i>");;
			if (assertion == false) {
				report("f","Assertion failed while verifying File content with String.");
			}
			
			
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().Trans_file_download);
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().Trans_FileDownload_TTML);
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
    		foldertoUnZip = FileZipUnzipThread.utils().getFolderName(System.getProperty("user.home")+ "\\Downloads\\","SUB");
    		System.out.println("foldertoUnZip--->"+foldertoUnZip);
    		System.out.println("Download Path--->"+System.getProperty("user.home")+ "\\Downloads\\"  + foldertoUnZip);
    		General.action().extractFolder(System.getProperty("user.home")+ "\\Downloads\\"+foldertoUnZip  , System.getProperty("user.home")+ "\\Downloads\\");
    	    Thread.sleep(1000);
    	    
    	    //GET PATH TILL PARENT FOLDER
    		fileName_withoutExtension =General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
    		System.out.println("foldertoUnZip_new--->"+fileName_withoutExtension);
    		downloadPath_withoutExtension=System.getProperty("user.home")+ "\\Downloads\\"+fileName_withoutExtension;
    		System.out.println("downloadPath_withoutExtension--->"+downloadPath_withoutExtension);
    		
    		readSrtFile(downloadPath_withoutExtension);// this method will read srt file from download folder.
			Thread.sleep(2000);
		    
			
//			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("Jellyfish at the Monterey Aquarium</p>");;
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("<span tts:fontStyle=\"italic\">Jellyfish at the Monterey</span>");
			if (assertion == false) {
				report("f","Assertion failed while verifying File content with String.");
			}
			
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("<span tts:fontStyle=\"italic\">Aquarium</span>");
			if (assertion == false) {
				report("f","Assertion failed while verifying File content with String.");
			}

			
//			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("JDude - get out of the way!</p>");
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("JDude - get out of the way!</span>");
			if (assertion == false) {
				report("f","Assertion failed while verifying File content with String.");
			}
			
//			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("<br />Ah yes, this is better...</p>");
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("<span tts:fontStyle=\"italic\">Shaky Hands...</span>\n" + 
					"        <br/>\n" + 
					"        <span tts:fontStyle=\"italic\">Ah yes, this is better...</span>");
			if (assertion == false) {
				report("f","Assertion failed while verifying File content with String.");
			}
			

		    
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	
	public void translate_onGoing_submission(String SubmissionName,String target,boolean complete ,boolean back) throws Exception {

		System.out.println("INSIDE Trans_Ongoing_Import_translation_InvalidFile  method()");

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_search_input, SubmissionName);
		Thread.sleep(2000);
		
		if(Verify.action().VerifyElementPresent(TranslatorLocators.Locator().check_trans_Target_Lang(SubmissionName, target)))
		{
			System.out.println("INSIDE IF--------");
			General.action().Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
		Thread.sleep(10000);
		
		
		
		  Thread.sleep(5000);
	      List <WebElement> listofIds1= General.action().driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
		  Thread.sleep(1000);
		  System.out.println("No of IDS--------"+listofIds1.size());
		  Thread.sleep(3000);
		  
	    for(int i=1;i<=listofIds1.size();i++){
	    	  Thread.sleep(2000);
	    	  General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
			Thread.sleep(1000);
		
	    }
	    
//	   /* General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
//		Thread.sleep(1000);
//		Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_P);
//		Thread.sleep(1000);
//		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
//		Thread.sleep(1000);
//		Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_M);
//		Thread.sleep(1000);*/
		
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().Enter_keyEnvents(KeyEvent.VK_END);
		Thread.sleep(1000);
		for(int i=1;i<=9;i++) {
	  		Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_LEFT);
	        Thread.sleep(1000);
	        robot.keyRelease(KeyEvent.VK_LEFT);
	        Thread.sleep(1000);
	  		
	  	}
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		Thread.sleep(1000);
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
			General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_P);
			Thread.sleep(1000);
			}else {
			Translator.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),Keys.chord(Keys.ALT, "p"));
			Thread.sleep(1000);
			}
		
		
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().Enter_keyEnvents(KeyEvent.VK_END);
		Thread.sleep(1000);
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
			General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_M);
			Thread.sleep(1000);
			}else {
			Translator.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(4),Keys.chord(Keys.ALT, "m"));
			Thread.sleep(1000);
			}
		
		
	    if(complete){
	    	General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
			Thread.sleep(3000);
			if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
				Thread.sleep(1000);
				General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
		    	}
		}
		
	    
	    if(back){
	    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
				Thread.sleep(1000);
				General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
		    	}
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
                boolean Finalresult = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("<i>Ah yes, this is better...</i>\r\n" + 
                		"<i></i> <i>Pro Tip: Turn off the camera flash!</i>");
				System.out.println("Downloaded file contains string :-" +Finalresult);
}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Trans_editText_alt_p_m(int event1,int event2) throws Exception {
		  
		  System.out.println("******Trans_editText_alt_p()************");
		  
		Robot robot = new Robot();
		robot.keyPress(event1);
      Thread.sleep(2000);
      robot.keyPress(event2);
      Thread.sleep(2000);
		robot.keyRelease(event2);
      Thread.sleep(2000);
      robot.keyRelease(event1);
      Thread.sleep(2000);
		    
		    System.out.println("******Trans_editText_alt_p()************");
	  }
	
	public void assertion() throws Exception {
//		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("Pro Tip: Turn off the camera flash!</p>");
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("<span tts:fontStyle=\"italic\">Shaky Hands...</span>\n" + 
				"        <br/>\n" + 
				"        <span tts:fontStyle=\"italic\">Ah yes, this is better...</span>");
		if (assertion == false) {
			report("f","Assertion failed while verifying File content with String.");
		}
	    else {
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
