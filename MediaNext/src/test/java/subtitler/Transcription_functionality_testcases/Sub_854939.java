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
import org.openqa.selenium.Keys;

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

public class Sub_854939 {
	

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_854939" + CommonElements.BROWSER + "G21";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Three_Step_Transc_Workflow";
	String fileDirName = "common";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "English (United States)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	 File filepath1;

	
	

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_854939");
		dataSet.put("TL_test_case_description","SUB-854939:Download target file that contains OST");
		dataSet.put("TL_internal_testCase_ID", "854939");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
		try {
			// PM login  OST FOR TRANS
			General.action().login(CommonElements.action().PM_username, CommonElements.action().password);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName,"transcription",transcGroupName,"Trans",TranslatorGroupName,true, "Review", ReviewGroupName, true);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			//PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER + CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step3_source_OSTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER + CommonElements.action().FILE_COMMON_SRT_FOLDER,CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER + CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(4000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			PM_user.action().PM_onGoing_submission_transc(SubmissionName, targetlanguage_1, true, false);
			Thread.sleep(2000);
			Translator.action().Navigate(menu_completed);
			Thread.sleep(2000);
			SearchSubmisson_andCheck(SubmissionName,"transcription");
			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(2000);
		    General.action().Click(Pm_User_Submission_Locators.Locator().PM_file_download);
			Thread.sleep(5000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_SRT);
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
			Thread.sleep(4000);
			
			
			
			// OST FOR REVIEW
			Thread.sleep(4000);
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(4000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			PM_user.action().PM_onGoing_submission_review(SubmissionName, targetlanguage_1, true, false);
			Thread.sleep(2000);
			Translator.action().Navigate(menu_completed);
			Thread.sleep(2000);
			SearchSubmisson_andCheck(SubmissionName,"Trans");
			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(2000);
		    General.action().Click(Pm_User_Submission_Locators.Locator().PM_file_download);
			Thread.sleep(5000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_SRT);
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
			Thread.sleep(4000);
			
			
			
			// OST FOR Transcription
			Thread.sleep(4000);
			 PM_user.action().Navigate(menu_to_claim);
			 Thread.sleep(4000);
			 PM_user.action().PM_ToClaim(SubmissionName);
			 Thread.sleep(2000);
			 PM_user.action().Navigate(menu_ongoing);
			 Thread.sleep(2000);
			 Pm_Transcription_ongoing_OST(SubmissionName, targetlanguage_1, true, false);
			 Thread.sleep(2000);
			 Translator.action().Navigate(menu_completed);
			 Thread.sleep(2000);
			 SearchSubmisson_andCheck(SubmissionName,"Review");
			 Thread.sleep(2000);
			 FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			 Thread.sleep(2000);
			 General.action().Click(Pm_User_Submission_Locators.Locator().PM_file_download);
			 Thread.sleep(5000);
						
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_SRT);
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
			    String foldertoUnZip2 = FileZipUnzipThread.utils().getFolderName(System.getProperty("user.home")+ "\\Downloads\\","SUB");
			    System.out.println("foldertoUnZip--->"+foldertoUnZip2);
			    System.out.println("Download Path--->"+System.getProperty("user.home")+ "\\Downloads\\"  + foldertoUnZip2);
			    General.action().extractFolder(System.getProperty("user.home")+ "\\Downloads\\"+foldertoUnZip2  , System.getProperty("user.home")+ "\\Downloads\\");
			    Thread.sleep(1000);
			    	    
			   //GET PATH TILL PARENT FOLDER
			    String fileName_withoutExtension2 =General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
			    System.out.println("foldertoUnZip_new--->"+fileName_withoutExtension2);
			    String downloadPath_withoutExtension2=System.getProperty("user.home")+ "\\Downloads\\"+fileName_withoutExtension2;
			    System.out.println("downloadPath_withoutExtension--->"+downloadPath_withoutExtension2);
			    		
			    readSrtFile(downloadPath_withoutExtension2);// this method will read srt file from download folder.
				Thread.sleep(2000);
			
			
			
			
		} catch (Exception e) {
			report("f", "Execution level error was encountered.\n\nError log:\n\n" + Verify.action().getErrorBuffer(e));
		}

	}
	
	
	 public void SearchSubmisson_andCheck(String SubmissionName, String Step) throws Exception {
	    	System.out.println("INSIDE method SearchSubmisson()\n"); 

	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
	    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	    	Thread.sleep(1000);	

	    	General.action().type_withKEYS(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.ENTER,false);
	    	Thread.sleep(1000);	
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_step(SubmissionName,Step));
			Thread.sleep(1000);			
			General.action().Click(Pm_User_Submission_Locators.Locator().check_Pm_submission_step(SubmissionName,Step));
			Thread.sleep(2000);
	    	
 	    System.out.println("EOM SearchSubmisson()\n");

	    }
	
	
	 public void CreateSubmisson_Step2_OrganizationAndWorkflow(String OrganizationName,String WorkflowName,String transcStep,String transcGroupName,String transStep,String TransGroupName,Boolean Trans,String reviewStep,String ReviewGroupName,Boolean Review) throws Exception {
	    	System.out.println("INSIDE method CreateSubmisson_Step2_OrganizationAndWorkflow()\n"); 
	    	
	    	Thread.sleep(2000);
	    
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    
//	    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	        General.action().Dropdownwithoutselect_with_javaScript(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown);
	    	Thread.sleep(2000);
	    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown,Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown(WorkflowName));
	    	Thread.sleep(2000);

	    	
	    	
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transcStep));
	    	Thread.sleep(2000);
	    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transcStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_trans(transcGroupName));
	    	Thread.sleep(2000);
	    	
	    	if(Trans){

	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep));
	    		Thread.sleep(1000);
	    		General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_review(TransGroupName));
		    	Thread.sleep(1000);
	    	}
	    	
	    	if(Review){

	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep));
	    		Thread.sleep(1000);
	    		General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_transc(ReviewGroupName));
		    	Thread.sleep(1000);
	    	}
	    	Thread.sleep(2000);

	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
			Thread.sleep(1000);
	    	
		    System.out.println("EOM CreateSubmisson_Step2_OrganizationAndWorkflow()\n");
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
                boolean Finalresult = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("JELLYFISH AT THE MONTEREY AQUARIUM.");
				System.out.println("Downloaded file contains string :-" +Finalresult);
}
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Pm_Transcription_ongoing_OST(String SubmissionName, String target, boolean complete,boolean back) throws Exception {

		System.out.println("INSIDE Pm_Transcription_ongoing_OST  method()");

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
		    
		    
	}
		
	public void assertion() throws Exception {

		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("JELLYFISH AT THE MONTEREY AQUARIUM.");;
		if (assertion == false) {
			report("f","Assertion failed while verifying File content with String.");
		}else {
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
