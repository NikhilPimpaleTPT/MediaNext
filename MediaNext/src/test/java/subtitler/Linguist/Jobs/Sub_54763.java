package subtitler.Linguist.Jobs;

/**
 * 
 * @author pvaidya
 *
 */

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
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

public class Sub_54763 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_54763"+CommonElements.BROWSER+"Y1";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String DownloadedfileName;
	String downloadPath_withoutExtension;
	String downloadedSrtFileName;
	File filepath1;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_54763");
		dataSet.put("TL_test_case_description", "Sub_54763 : Download Jobs ");
		dataSet.put("TL_internal_testCase_ID", "54763");
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
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
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
			
			Thread.sleep(2000);
    		General.action().logoutMethod();
				
    		// Trans login	
    		 General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			
			 Thread.sleep(1000);
	         Translator.action().Navigate(menu_to_claim);
	         Thread.sleep(1000);
	         Translator.action().trans_ToClaim(SubmissionName);
	         Thread.sleep(1000);
	         Translator.action().Navigate(menu_ongoing);
	         Thread.sleep(1000);
	         Translator.action().translate_onGoing_submission(SubmissionName, targetlanguage_1, true, false);
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
			
			
			downloadedSrtFileName=GetDownloadedFileNameMethod();
			System.out.println("downloaded Srt File Name Is---->"+downloadedSrtFileName);
			
			
		   } catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
		
	}
	
	
	
	public String GetDownloadedFileNameMethod() throws Exception {
		
		String downloadedSRTFile="";
       
		//Get Downloaded File Name
		
		System.out.println(" INSIDE Get Downloaded File Name Method ");
		Thread.sleep(2000);
		 if(CommonElements.BROWSER.contains("FIREFOX")) {
		    	System.out.println("------THIS IS FIREFOX-----");
		    	Thread.sleep(3000);
				General.action().downloadFileFirefox();
				Thread.sleep(3000);
				
	    	}else {
	        	System.out.println("------THIS IS CHROME-----");
	    		Thread.sleep(5000);
	    	}
		 
		DownloadedfileName=General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
		System.out.println("Downloaded File Name--->"+DownloadedfileName);
		
		
		//UNZIP THE FILE
 		String foldertoUnZip = FileZipUnzipThread.utils().getFolderName(System.getProperty("user.home")+ "\\Downloads\\","SUB");
 		System.out.println("foldertoUnZip--->"+foldertoUnZip);
 		System.out.println("Download Path--->"+System.getProperty("user.home")+ "\\Downloads\\"  + foldertoUnZip);
 		General.action().extractFolder(System.getProperty("user.home")+ "\\Downloads\\"+foldertoUnZip  , System.getProperty("user.home")+ "\\Downloads\\");
 	    Thread.sleep(1000);
 	    
 	    //GET PATH TILL PARENT FOLDER
 		String fileName_withoutExtension =General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
 		System.out.println("foldertoUnZip_new--->"+fileName_withoutExtension);
 		downloadPath_withoutExtension=System.getProperty("user.home")+ "\\Downloads\\"+fileName_withoutExtension;
 		System.out.println("downloadPath_withoutExtension--->"+downloadPath_withoutExtension);
 		
 		downloadedSRTFile=readFile(downloadPath_withoutExtension);
		
		System.out.println(" EOM Get Downloaded File Name");
		return downloadedSRTFile;
	}
	
	
	public String readFile(String downloadPath_withoutExtension) throws Exception {
		String srtFileName="";
		try {

//			File filepath = new File(System.getProperty("user.home")+ "\\Downloads\\");
			File filepath = new File(downloadPath_withoutExtension);
			String[] filenames = filepath.list();
			int countofFiles = filenames.length;
			System.out.println("countofFiles##########  :- " + countofFiles);

			for (String filename : filenames) {
				System.out.println(filename);
				srtFileName=filename;
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
                
                System.out.println(downloadPath_withoutExtension);
                System.out.println(filename);
                
                System.out.println("filepath1-------->"+filepath1);
                System.out.println("downloadedFileName--->"+SubmissionName);
                
                assertion=filepath1.getName().contains(SubmissionName);
                if(assertion==false) {
                	report("f", "File name is not valid");
                
       			
			
				
                }
             
                
              }
			
			} catch (Exception e) {
			e.printStackTrace();
		}
		
		return srtFileName;
	}

	
	public void assertion() throws Exception {

			assertion = downloadedSrtFileName.contains(SubmissionName);
			if (assertion == false) {
				report("f","Assertion failed while verifying File Name Same As Submission Name.");
			}else {
			report("p", " Downloaded File Name Same As Submission Name");
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
			
			