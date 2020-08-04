package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary:This test case verifies text alignment for bidi lang submissions on downloading as Audio Scripts
 *
 */

public class Sub_2353555 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_2353555"+CommonElements.BROWSER+"A4";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"ar-AE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String SUBMISSION_FILES_PATH = "data" + File.separator + "Submission";
	String sourceDir = (new File(this.SUBMISSION_FILES_PATH).getAbsolutePath() + "\\");
    
    Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2353555");
		dataSet.put("TL_test_case_description", "SUB-2353555:As a PM - Audio Scripts (DOCX, XLSX) for bidi languages");
		dataSet.put("TL_internal_testCase_ID", "2353555");
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
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_ARABIC_FOLDER);
			PM_user.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, Targetlanguage);
			Thread.sleep(2000);
		
			//SEARCH SUBMISSION AND CHECK
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			//VERIFY DOWNLOAD BUTTON AND CLICK ON IT
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_file_download, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_file_download);
			Thread.sleep(2000);
			
			//VERIFY SUBMISSION NAME ON DIALOG BOX AS IT IS DISABLED
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_submissionNameDisable, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName is non editable ");
			}
			
			//SELECT FORMAT
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_ChooseFormat);
			Thread.sleep(2000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_audioScripts);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_languageCheckbox("Arabic (United Arab Emirates)"));
			Thread.sleep(2000);
			
			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home")+ "\\Downloads\\");
			Thread.sleep(5000);
			
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_dailogBox_downloadButton);
			Thread.sleep(2000);
			
			//Download file for Different browsers
			General.action ().FileDownloadMethodForDifferentBrowser();
	    	
	    	//Method To Get File name
	    	General.action().GetFileName(System.getProperty("user.home") + "\\Downloads\\");
	    	System.out.println("File is downloaded inside folder :"+System.getProperty("user.home") + "\\Downloads\\");
			 
			//To verify audio scripts file downloaded in Lical Drive
			 assertion =General.action().DownloadedFile.contains(SubmissionName);
			 System.out.println("Assertion Is======>"+assertion);
			 if(assertion==false){
				report("f","audio scripts File is not Downloaded in Local Drive.");
			 }else{
				System.out.println("audio scripts File is Downloaded in Local Drive.");		
			 }
			 
			 
			 System.out.println("Downloaded file in TC is:"+General.action().DownloadedFile);
			 
			//Copy file to Test case Data folder
			 File file = new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile); 
			 Thread.sleep(4000);
			 
			 System.out.println("data folder file is:"+sourceDir);
			 
			 FileUtil.deleteDir(sourceDir+"2353555\\");
			 Thread.sleep(2000);
			
			//To move zip file into workspace data folder
			 System.out.println("Source File:"+System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile);
			 System.out.println("Destination File:"+sourceDir+"2353555\\"+General.action().DownloadedFile);
			 FileUtils.copyFile(new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile),new File(sourceDir+"2353555\\"+General.action().DownloadedFile));
			 
			

			
		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
         }
	}

	public void assertion() throws Exception {
		//This TC is bolcked as need to check arabic segment is displayed right to left, right aligned
		//***Note***
		//Doc and xlsx file is downloaded though scripts inside data folder 2353555 (User this zip file to manually check the last condition of tc (Also refer test case :C2353555))
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_file_download, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying download button");
		}else {
				report("b", "Verify arabic segments should displayed right to left, right aligned - This tc IS BLOCKED");
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
		

