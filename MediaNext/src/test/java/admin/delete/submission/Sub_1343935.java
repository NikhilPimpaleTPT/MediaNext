package admin.delete.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Admin_User_Submission_Locators;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import modules.Verify;
import modules.admin;

/**
 * 
 * @author pvaidya
 *Summary: This testcase verifies if user can export submission list.
 */

public class Sub_1343935 {
	
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	List<String> CSVReportData;
	String SubmissionName = "Submission_1343935"+CommonElements.BROWSER+"B4";
	String sourcelanguage = "en-US - English (United States)";
	String targetlanguage = "de-DE - German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String TranslatorGroupName = "Common_group";
	String menu_submission = "Submissions";
	String enteredDueDate;  
	File filepath1;
	String submissionCreationDate;
	Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1343935");
		dataSet.put("TL_test_case_description", "SUB-1343935:Export submission list");
		dataSet.put("TL_internal_testCase_ID", "1343935");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			
			General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			admin.action().Navigate(menu_submission);
			
     		//TODO NEW IMPL OF SUBMISSION CREATION
			admin.action().CreateSubmisson_Step1("17", "35", "80", "100");
			admin.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
			admin.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			admin.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_SRT_FOLDER);
			admin.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(1000);
			admin.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			
			//Navigate to submission menu
			admin.action().Navigate(menu_submission);
			Thread.sleep(2000);
			
			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home")+ "\\Downloads\\");
			Thread.sleep(2000);
			//Click on export csv
			General.action ().waitforelemenetpresent(CommonLocartors.Locator().submission_exportCSV);
			Thread.sleep(1000);
			General.action ().Click(CommonLocartors.Locator().submission_exportCSV);
			Thread.sleep(2000);		
			
			//Download file for Different browsers
			General.action ().FileDownloadMethodForDifferentBrowser();
			
			submissionCreationDate=GetCurrentDate();
			System.out.println("Submission creation date---->"+submissionCreationDate);
			
		    //Read donloaded file
			CSVReportData=General.readCSVFileDataLineByLine(System.getProperty("user.home") + "\\Downloads\\"+"submissions-"+submissionCreationDate+".csv",SubmissionName);
			System.out.println(CSVReportData);
			
			//Verify data 
			assertion=CSVReportData.contains(SubmissionName);
			if(assertion==true){
			System.out.println("csv Report Contains-->"+SubmissionName);
			}else {
			System.out.println("csv Report not Contains-->"+SubmissionName);
			report("f","Activity Report not Contains"+SubmissionName);
			}
			
		} catch (Throwable e) {
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
				//This assertion is blocked as need to check submission due and creation date and other information.(As dates in csv file and console displaying on different format)
				assertion=CSVReportData.contains(SubmissionName);
				if(assertion==false){
				System.out.println("csv Report not Contains-->"+SubmissionName);
				report("f","csv Report not Contains"+SubmissionName);
				}else {
				System.out.println("csv Report Contains-->"+SubmissionName);
				report("b","Final assertion has Blocked.");
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
