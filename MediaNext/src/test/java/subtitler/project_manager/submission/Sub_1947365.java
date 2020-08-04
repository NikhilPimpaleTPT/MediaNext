package subtitler.project_manager.submission;

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
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary:This testcase verifies that ampersand character is exported as encoded entity in the downloaded TTML file.
 *
 */

public class Sub_1947365 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1947365"+CommonElements.BROWSER+"A1";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String menu_completed = "Completed";
    File filepath1;
	String path;
    
    Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1947365");
		dataSet.put("TL_test_case_description", "SUB1947365:Ampersand character in downloaded TTML format");
		dataSet.put("TL_internal_testCase_ID", "1947365");
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
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",	TranslatorGroupName, "review", "", true);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER_AMPERSAND);
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
		    Translator.action().translate_onGoing_submission(SubmissionName, targetlanguage, true, false);
			Thread.sleep(2000);
		    Translator.action().Navigate(menu_completed);
			Thread.sleep(2000);
			Translator.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
				
			//Verify submission is completed in trans phase
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
			}
				
			Thread.sleep(2000);
			General.action().logoutMethod();
				
			// Review LogIn 
			General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
		    Thread.sleep(1000);
		    Reviewer.action().Navigate(menu_to_claim);
		    Thread.sleep(1000);
		    Reviewer.action().review_ToClaim(SubmissionName);
		    Thread.sleep(1000);
		    Reviewer.action().Navigate(menu_ongoing);
		    Thread.sleep(1000);
		    Reviewer.action().Reviewer_onGoing_submission(SubmissionName, targetlanguage, true, false);
			Thread.sleep(2000);
			Reviewer.action().Navigate(menu_completed);
			Thread.sleep(2000);
			Reviewer.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			
			 Thread.sleep(2000);
			 Translator.action().SearchSubmisson_andCheck(SubmissionName);
			 Thread.sleep(2000);
			// delete file
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(4000);
			General.action().Click(TranslatorLocators.Locator().Trans_file_download);
			Thread.sleep(2000);
			// download ttml file
			General.action().Click(TranslatorLocators.Locator().Trans_FileDownload_TTML);
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
			 System.out.println("TTML FILE CONTENTS:"+FileUtils.readFileToString(filepath1, CommonElements.UTF_8));
				
			
			 //Verify that ampersand character is exported as encoded entity in the downloaded TTML file. 
			 System.out.println("this is chrome");
			 assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("112Jellyfish at &quot;the Monterey &amp; New Monterey&quot; Aquarium");
			 if(assertion == false) {
			 report("f","Assertion failed while verifying ampersand character is exported as encoded entity in the downloaded TTML file.");
			 }
			 assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("55Thanks for watching. Me &amp; team hope you&apos;ll have fun with the VideoSub library.");
			 if(assertion == false) {
			 report("f","Assertion failed while verifying ampersand character is exported as encoded entity in the downloaded TTML file.");
			 }
			 assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("65All Rights Reserved by Transperfect India &amp; Translations.");
			 if(assertion == false) {
			 report("f","Assertion failed while verifying ampersand character is exported as encoded entity in the downloaded TTML file."); 
			 }
				
			    
				
		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
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

				String path = downloadPath_withoutExtension + "\\" + filename;
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

				filepath1 = new File(downloadPath_withoutExtension + "\\" + filename);

			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void assertion() throws Exception {
		 assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("65All Rights Reserved by Transperfect India &amp; Translations.");
		 if(assertion == false) {
		 report("f","Assertion failed while verifying italic tml File content."); 
		 }else {
				report("p", "Verify that ampersand character is exported as encoded entity in the downloaded TTML file.");
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
		
