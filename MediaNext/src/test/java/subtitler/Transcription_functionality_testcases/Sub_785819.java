package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

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
import modules.Translator;
import modules.Verify;

public class Sub_785819 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName1 = "SUB_785819_1" + CommonElements.BROWSER + "W4";
	String SubmissionName2 = "SUB_785819_2" + CommonElements.BROWSER + "W4";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileDirName = "common";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String path;
	String menu_completed = "Completed";
	 File filepath1;
	
	
	

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_785819");
		dataSet.put("TL_test_case_description","SUB-785819:Import a TTML : <br> are not correctly handled");
		dataSet.put("TL_internal_testCase_ID", "785819");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
		try {
			// PM login
			General.action().login(CommonElements.action().PM_username, CommonElements.action().password);
			// TODO NEW IMPL OF SUBMISSION CREATION
			
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER + CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName1,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName1);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName1), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			

			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER + CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName2,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName2);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName2), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}

			Thread.sleep(2000);
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName1);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			Pm_Transcription_ImportTTML_SRT(SubmissionName1, targetlanguage_1, true, false);
			Thread.sleep(2000);
			//for TTML file
			Thread.sleep(2000);
			Translator.action().Navigate(menu_completed);
			Thread.sleep(2000);
			Translator.action().SearchSubmisson_andCheck(SubmissionName1);
			Thread.sleep(2000);
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
			
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName2);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			Pm_Transcription_ImportTTML_SRT(SubmissionName2, targetlanguage_1, true, false);
			Thread.sleep(2000);
			//for TTML file
			Thread.sleep(2000);
			Translator.action().Navigate(menu_completed);
			Thread.sleep(2000);
			Translator.action().SearchSubmisson_andCheck(SubmissionName2);
			Thread.sleep(2000);
					
			
			//for EBU file
			/*Thread.sleep(5000);
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(2000);
			Translator.action().Navigate(menu_completed);
			Thread.sleep(2000);
			Translator.action().SearchSubmisson_andCheck(SubmissionName2);
			Thread.sleep(2000);*/
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().Trans_file_download);
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().Trans_FileDownload_EBU);
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
    		String foldertoUnZip1 = FileZipUnzipThread.utils().getFolderName(System.getProperty("user.home")+ "\\Downloads\\","SUB");
    		System.out.println("foldertoUnZip--->"+foldertoUnZip1);
    		System.out.println("Download Path--->"+System.getProperty("user.home")+ "\\Downloads\\"  + foldertoUnZip1);
    		General.action().extractFolder(System.getProperty("user.home")+ "\\Downloads\\"+foldertoUnZip1  , System.getProperty("user.home")+ "\\Downloads\\");
    	    Thread.sleep(1000);
    	    
    	    //GET PATH TILL PARENT FOLDER
    		String fileName_withoutExtension1 =General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
    		System.out.println("foldertoUnZip_new--->"+fileName_withoutExtension1);
    		String downloadPath_withoutExtension1=System.getProperty("user.home")+ "\\Downloads\\"+fileName_withoutExtension1;
    		System.out.println("downloadPath_withoutExtension--->"+downloadPath_withoutExtension1);
    		
    		 readSrtFile(downloadPath_withoutExtension1);// this method will read srt file from download folder.
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f", "Execution level error was encountered.\n\nError log:\n\n" + Verify.action().getErrorBuffer(e));
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
                boolean Finalresult = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("</p>");
				System.out.println("Downloaded file contains string :-" +Finalresult);
}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Pm_Transcription_ImportTTML_SRT(String SubmissionName, String target, boolean complete,boolean back) throws Exception {

		System.out.println("INSIDE Pm_Transcription_Ongoing_navigate_faster  method()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
		Thread.sleep(9000);
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
		Thread.sleep(2000);
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file);
		Thread.sleep(2000);
		
		
		
	   File afile=new File(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_TTML_WITH_BR_TAG_FOLDER);
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
	    		RobotExt.robot().delay(2000);
	    		RobotExt.robot().sendKeys(path);
	    		RobotExt.robot().processFilePath();
	    		RobotExt.robot().delay(2000);
	    		
        	}else {
            	System.out.println("------THIS IS CHROME-----");
        		General.action().type(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file_input,path);
        		//TODO NOT REQUIRED FOR FIRST TIME WHEN BLANK
//        		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_overwrite_current_translation);
//        		Thread.sleep(1000);
//        		General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_overwrite_current_translation);
//        		Thread.sleep(20000);
        	}
        	Thread.sleep(5000);
        	if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_overwrite_current_translation,10)) {
        		General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_overwrite_current_translation);
        		Thread.sleep(20000);
        	}
        	
        	if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file,10)) {
        	General.action().Click(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file);
    		Thread.sleep(2000);
        	}
    		
    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
    		Thread.sleep(2000);
    		General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
    		Thread.sleep(2000);
        	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_Close_button);
			Thread.sleep(1000);
        	
        	

    	Thread.sleep(2000);
	
    	 if(complete){
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
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
					Thread.sleep(1000);
					General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
			    	}
		     }  
    	
    	 System.out.println("EOM Trans_Ongoing_Import_translation_InvalidFile()");
	}

		public void assertion() throws Exception {
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("<tt");
		if (assertion == false) {
			report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
			Thread.sleep(2000);
		} else {
			report("p", "All Assertion passed.");
		}
	}

	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),
				Subtitler_TestRail_Common_Properties.idTestPlan, Subtitler_TestRail_Common_Properties.idBuild, result,
				Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}



}
