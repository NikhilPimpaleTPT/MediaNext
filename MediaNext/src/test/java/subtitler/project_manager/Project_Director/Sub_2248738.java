package subtitler.project_manager.Project_Director;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

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
import locators.PD_Pm_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import modules.PD_PM_user;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:To verify if the submission is able to download as a VO Script at different levels of PD
 *
 */

public class Sub_2248738 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	String SubmissionName = "SUB_2248738"+CommonElements.BROWSER+"A2";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_completed = "Completed";
	String SUBMISSION_FILES_PATH = "data" + File.separator + "Submission";
	String sourceDir = (new File(this.SUBMISSION_FILES_PATH).getAbsolutePath() + "\\");
	
	
	String submissionID;
	File filepath;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2248738");
		dataSet.put("TL_test_case_description", "PD integration");
		dataSet.put("TL_internal_testCase_ID", "2248738");
	}
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			    // login using PM Crdentials 
			    General.action().login(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
			    Thread.sleep(20000);
			    
			    //Create Submission In PD
				PM_user.action().Navigate(menu_Submission);
				// TODO NEW IMPL OF SUBMISSION CREATION
				PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName);
				PD_PM_user.action().Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
				PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				
				
				//Search and check submission and Verify for CRETE IN PD BUTTON
				PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button);
				Thread.sleep(2000);
				
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_createSubmissionInPDButton);
				Thread.sleep(2000);
				
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				
				//Verify Submission creation and due date
				PD_PM_user.action().SearchSubmisson(SubmissionName);
				Thread.sleep(2000);
				
				//Verify Submission status after creating 
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName,"New"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName status after Search");
				}
				
				submissionID =General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(1,3)).getText();
				System.out.println("Submission ID:"+submissionID);
				
				Thread.sleep(2000);
				PD_PM_user.action().Open_PD_Instance_URL();
				Thread.sleep(25000);
				
				PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				PD_PM_user.action().analyse_PD_submission();
				Thread.sleep(5000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_wordCount("39"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName count");
				}
				
				PD_PM_user.action().switchToPopupWindow(0);
				Thread.sleep(8000);
				General.action().driver.navigate().refresh();
				Thread.sleep(8000);
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				Thread.sleep(1000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
				Thread.sleep(1000);
				//Verify Submission creation and due date
				PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				Thread.sleep(2000);
				Thread.sleep(2000);
				FileUtil.deleteDir(System.getProperty("user.home")+ "\\Downloads\\");
				Thread.sleep(2000);
				
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_button);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_dropdownArrow);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_dropdownOption("Audio Scripts (DOCX, XLSX)"));
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_languageCheckbox("German (Germany)"));
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_file_button);
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
				File file1 = new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile); 
				Thread.sleep(4000);
				 
				System.out.println("data folder file is:"+sourceDir);
				 
				FileUtil.deleteDir(sourceDir+"2248738\\analysis\\");
				Thread.sleep(2000);
				
				//To move zip file into workspace data folder
				System.out.println("Source File:"+System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile);
				System.out.println("Destination File:"+sourceDir+"2248738\\analysis\\"+General.action().DownloadedFile);
				FileUtils.copyFile(new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile),new File(sourceDir+"2248738\\analysis\\"+General.action().DownloadedFile));
			
				
				PD_PM_user.action().switchToPopupWindow(1);
				Thread.sleep(8000);
				
				
				Thread.sleep(1000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_onHoldButton);
		    	Thread.sleep(4000);
				Thread.sleep(1000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
		    	Thread.sleep(4000);
		    	PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
		    	
				
				PD_PM_user.action().createBudget_PD_submission(submissionID,CommonElements.action().REVENUE,CommonElements.action().RATE_TRANS,CommonElements.action().RATE_REVIEW);
				Thread.sleep(6000);
				
				Thread.sleep(1000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_onHoldButton);
		    	Thread.sleep(4000);
				Thread.sleep(1000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
		    	Thread.sleep(4000);
		    	PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_Status(submissionID,"In Progress"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName status after Search");
				}
				
				PD_PM_user.action().logOut_to_PD();
				Thread.sleep(2000);
				
				PD_PM_user.action().logIn_to_PD(CommonElements.action().PD_VENDOR1_username,CommonElements.action().PD_VENDOR1_password);
				Thread.sleep(6000);
				
				General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_available);
			    Thread.sleep(1000);
			    General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_available);
			    Thread.sleep(2000);
			    PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				PD_PM_user.action().vendor1_jobInfo(true,true,"German (Germany)");
				Thread.sleep(80000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_fileManagement_link(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName status after Search");
				}
		
				PD_PM_user.action().PD_submission_vendor1_TTML_link(SubmissionName);
				Thread.sleep(2000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().vendor1_submissionName_header(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName status after Search");
				}
		
				PD_PM_user.action().PD_submission_vendor1_onGoing_submission(true,false);
				Thread.sleep(2000);
				PD_PM_user.action().PD_submission_vendor1_completed();
				Thread.sleep(2000);
				PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_progressBar, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName 100% completed");
				}
				
				System.out.println("Progress Bar Color:"+General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_completed_color).getCssValue("background-color"));
				assertion=General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_completed_color).getCssValue("background-color").contains("(82, 192, 85");
				if (assertion == false) {
					report("f","Assertion failed while verifying submission completed status ");
	            }
				
				PD_PM_user.action().switchToPopupWindow(0);
				Thread.sleep(8000);
				
				Thread.sleep(2000);
				General.action().logoutMethod();
				
				PD_PM_user.action().logIn_to_PD(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
				Thread.sleep(6000);
				
				General.action().driver.navigate().refresh();
				Thread.sleep(10000);
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
				Thread.sleep(1000);
				//Verify Submission creation and due date
				PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				Thread.sleep(2000);
				Thread.sleep(2000);
				FileUtil.deleteDir(System.getProperty("user.home")+ "\\Downloads\\");
				Thread.sleep(2000);
				
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_button);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_dropdownArrow);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_dropdownOption("Audio Scripts (DOCX, XLSX)"));
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_languageCheckbox("German (Germany)"));
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_file_button);
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
				File file2 = new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile); 
				Thread.sleep(4000);
				 
				System.out.println("data folder file is:"+sourceDir);
				 
				FileUtil.deleteDir(sourceDir+"2248738\\trans\\");
				Thread.sleep(2000);
				
				//To move zip file into workspace data folder
				System.out.println("Source File:"+System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile);
				System.out.println("Destination File:"+sourceDir+"2248738\\trans\\"+General.action().DownloadedFile);
				FileUtils.copyFile(new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile),new File(sourceDir+"2248738\\trans\\"+General.action().DownloadedFile));
				
				
				PD_PM_user.action().switchToPopupWindow(1);
				Thread.sleep(8000);
				
				
				PD_PM_user.action().logOut_to_PD();
				Thread.sleep(2000);
				
				PD_PM_user.action().logIn_to_PD(CommonElements.action().PD_VENDOR2_username,CommonElements.action().PD_VENDOR2_password);
				Thread.sleep(4000);
			    General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_available);
			    Thread.sleep(2000);
			    PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				PD_PM_user.action().vendor2_jobInfo(true,true,"German (Germany)");
				Thread.sleep(4000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_fileManagement_link(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName status after Search");
				}
				PD_PM_user.action().PD_submission_vendor2_TTML_link(SubmissionName);
				Thread.sleep(2000);
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().vendor2_submissionName_header(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName status after Search");
				}
		
				PD_PM_user.action().PD_submission_vendor2_onGoing_submission(true,false);
				Thread.sleep(2000);
				PD_PM_user.action().PD_submission_vendor2_completed();
				Thread.sleep(2000);
				PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().vendor2_submissionName_completedChecked(submissionID), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying PD SubmissionName  completed");
				}
				PD_PM_user.action().switchToPopupWindow(0);
				Thread.sleep(8000);
				
				Thread.sleep(2000);
				General.action().logoutMethod();
				
				
				
				PD_PM_user.action().logIn_to_PD(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
				Thread.sleep(6000);
				
				General.action().driver.navigate().refresh();
				Thread.sleep(8000);
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
				Thread.sleep(1000);
				//Verify Submission creation and due date
				PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				Thread.sleep(2000);
				Thread.sleep(2000);
				FileUtil.deleteDir(System.getProperty("user.home")+ "\\Downloads\\");
				Thread.sleep(2000);
				
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_button);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_dropdownArrow);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_dropdownOption("Audio Scripts (DOCX, XLSX)"));
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_languageCheckbox("German (Germany)"));
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_download_file_button);
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
				File file3 = new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile); 
				Thread.sleep(4000);
				 
				System.out.println("data folder file is:"+sourceDir);
				 
				FileUtil.deleteDir(sourceDir+"2248738\\review\\");
				Thread.sleep(2000);
				
				//To move zip file into workspace data folder
				System.out.println("Source File:"+System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile);
				System.out.println("Destination File:"+sourceDir+"2248738\\review\\"+General.action().DownloadedFile);
				FileUtils.copyFile(new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile),new File(sourceDir+"2248738\\review\\"+General.action().DownloadedFile));
				
				assertion =General.action().DownloadedFile.contains(SubmissionName);
				System.out.println("Assertion Is======>"+assertion);
				if(assertion==false){
					report("f","audio scripts File is not Downloaded in Local Drive.");
				}else{
					System.out.println("audio scripts File is Downloaded in Local Drive.");		
				}
				
				

		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
           }
 }
	
	
	
	 public static String GetCurrentDate() throws Exception 	
		{		
			Calendar cal = Calendar.getInstance();		
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());				
			return (dateFormat.format(cal.getTime()));
			
		}
	
	
	public void assertion() throws Exception {
		assertion =General.action().DownloadedFile.contains(SubmissionName);
		System.out.println("Assertion Is======>"+assertion);
		if(assertion==false){
			report("f","audio scripts File is not Downloaded in Local Drive.");
		}
    	else {
		report("b", "All assertions blocked");
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