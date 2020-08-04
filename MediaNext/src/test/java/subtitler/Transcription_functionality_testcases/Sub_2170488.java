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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Reviewer;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: Transcription should be downloaded if no source file is provided.
 *
 */

public class Sub_2170488 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_2170488"+CommonElements.BROWSER+"A2";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String menu_completed = "Completed";;
    String fileFolder="2170488";
    String newAddedSegments[]= {"Segment 1","Segment 2","Segment 3","Segment 4"};
    Boolean assertion = true;
    File filepath1;
    
    @BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_2170488");
		dataSet.put("TL_test_case_description","Sub_2170488: To verify if the transcription is able to download when there is no source file");
		dataSet.put("TL_internal_testCase_ID", "2170488");
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
			PM_user.action().Navigate(menu_submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+fileFolder);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
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
			PM_transcription_onGoing_Submission(SubmissionName,targetlanguage,true,true);
		    Thread.sleep(2000);
		    
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public void PM_transcription_onGoing_Submission(String SubmissionName, String target,boolean back,boolean complete) throws Exception {

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
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
        Thread.sleep(8000);
        
        
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
      	Thread.sleep(2000);
      	PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
      	Thread.sleep(2000);
      	PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
      	Thread.sleep(2000);
      	PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea, "Segment 1");
      	Thread.sleep(2000);
      	PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
      	Thread.sleep(2000);
      	PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
      	Thread.sleep(2000);
      	PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
      	Thread.sleep(2000);
      	PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea, "Segment 2");
      	Thread.sleep(2000);
      	
      	
      	if(back){
	    	System.out.println("IN BACK-----");
	    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
		    	}
	  }
      	
      	Thread.sleep(2000);
		PM_user.action().Navigate(menu_submission);
		Thread.sleep(2000);
		//CHECK SUBMISSION AND DOWNLOAD SRT FILE
		PM_user.action().SearchSubmisson_andCheck(SubmissionName);
		Thread.sleep(2000);
		FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().submmission_file_download);
		Thread.sleep(2000);
		
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_ChooseFormat);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_SRT);
		Thread.sleep(3000);
		//CHECK HEADER CHECKBOX
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_headerCheckbox);
		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_downloadButton);
		Thread.sleep(3000);
		
		
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
		
		readSRTandTTMLFile(downloadPath_withoutExtension,targetlanguage);
		Thread.sleep(2000);
		
		System.out.println("SRT file data form submission tab: "+FileUtils.readFileToString(filepath1,CommonElements.UTF_8));
		
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(newAddedSegments[0]);;
		if (assertion == false) {
			report("f","Assertion failed while verifying File content with String.");
		}
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(newAddedSegments[1]);;
		if (assertion == false) {
			report("f","Assertion failed while verifying File content with String.");
		}
		
		
		PM_user.action().Navigate(menu_ongoing);
		Thread.sleep(2000);
		PM_user.action().SearchSubmisson_andCheck(SubmissionName);
		Thread.sleep(2000);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
        Thread.sleep(8000);
        
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
      	Thread.sleep(2000);
      	PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
      	Thread.sleep(2000);
      	PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
      	Thread.sleep(2000);
      	PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea, "Segment 3");
      	Thread.sleep(2000);
      	PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(4));
      	Thread.sleep(2000);
      	PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(4));
      	Thread.sleep(2000);
      	PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
      	Thread.sleep(2000);
      	PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea, "Segment 4");
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
        Thread.sleep(2000);
		Reviewer.action().Navigate(menu_completed);
		Thread.sleep(2000);
		PM_user.action().SearchSubmisson_andCheck(SubmissionName);
		Thread.sleep(2000);
		FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_file_download);
		Thread.sleep(2000);
		//CLICK ON SRT AND DOWNLOAD IT
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
		
		readSRTandTTMLFile(downloadPath_withoutExtension,targetlanguage);
		Thread.sleep(2000);
		
        System.out.println("SRT file data form copleted tab: "+FileUtils.readFileToString(filepath1,CommonElements.UTF_8));
		
        //verify SRT file should be download and newly added segments should be visible
		
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(newAddedSegments[0]);;
		if (assertion == false) {
			report("f","Assertion failed while verifying File content with String.");
		}
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(newAddedSegments[1]);;
		if (assertion == false) {
			report("f","Assertion failed while verifying File content with String.");
		}
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(newAddedSegments[2]);;
		if (assertion == false) {
			report("f","Assertion failed while verifying File content with String.");
		}
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(newAddedSegments[3]);;
		if (assertion == false) {
			report("f","Assertion failed while verifying File content with String.");
		}
		
      
        
	}
	
	
	public void readSRTandTTMLFile(String downloadPath_withoutExtension,String targetSegment) throws Exception {
		try {

					//File filepath = new File(System.getProperty("user.home") + "\\Downloads\\");
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
		                boolean Finalresult = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(targetSegment);
						System.out.println("Downloaded file contains string :-" +Finalresult);
		}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	public void assertion() throws Exception {
		
		//Segment should be display in SRT file
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(newAddedSegments[3]);;
		if (assertion == false) {
			report("f","Assertion failed while verifying File content with String.");
		}else {
			report("p", "Assertion is passed.");
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


