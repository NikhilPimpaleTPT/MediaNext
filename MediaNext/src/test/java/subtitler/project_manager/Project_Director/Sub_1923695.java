package subtitler.project_manager.Project_Director;

import static org.testng.AssertJUnit.assertTrue;

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
import locators.PD_Pm_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import modules.PD_PM_user;
import modules.PM_user;
import modules.Verify;
import modules.admin;

/**
 * 
 * @author pvaidya
 *Summary:This testcase verifies if user can export submission list.
 *
 */

public class Sub_1923695 {
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	List<String> CSVReportData_mediaSubmission;
	List<String> CSVReportData_pdSubmission;
	List<String> CSVReportData_header;
	
	String SubmissionName_MediaNext = "SUB_1923695_MediaNext"+CommonElements.BROWSER+"A3";
	String SubmissionName_PD = "SUB_1923695_PD"+CommonElements.BROWSER+"A3";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_jobs = "Jobs";
	String submissionCreationDate;
	String submissionID;
	File filepath;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1923695");
		dataSet.put("TL_test_case_description", "Export submission list");
		dataSet.put("TL_internal_testCase_ID", "1923695");
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
		    Thread.sleep(5000);
		    
		    //Create Submission In PD
			PM_user.action().Navigate(menu_Submission);
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow("Subtitle_Common_orgs", WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName_MediaNext,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			
			General.action().driver.navigate().refresh();
			Thread.sleep(2000);
			
			// TODO NEW IMPL OF SUBMISSION CREATION
			PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName_PD);
			PD_PM_user.action().Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName_PD,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
			PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			
			//Verify created submission in pending status
			PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName_PD);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_PD), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				
			}
			
			Thread.sleep(2000);
			PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button);
			Thread.sleep(2000);
			
			PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_createSubmissionInPDButton);
			Thread.sleep(2000);
			
			//Verify Submission creation and due date
			PD_PM_user.action().SearchSubmisson(SubmissionName_PD);
			Thread.sleep(2000);
			
			//Verify Submission status after creating 
			assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName_PD,"New"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName status after Search");
			}
			
			submissionID =General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(1,3)).getText();
			System.out.println("Submission ID:"+submissionID);
			
			General.action().logoutMethod();
			
			admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			
			Thread.sleep(1000);
			admin.action().Navigate(menu_Submission);
			Thread.sleep(2000);

			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home")+ "\\Downloads\\");
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Admin_User_Submission_Locators.Locator().Export_Csv_button);
			Thread.sleep(2000);
			General.action().Click(Admin_User_Submission_Locators.Locator().Export_Csv_button);
			Thread.sleep(5000);
			//readCSVFile_Sub();
			
			
			//Download file for Different browsers
			General.action ().FileDownloadMethodForDifferentBrowser();
			
			submissionCreationDate=GetCurrentDate();
			System.out.println("users creation date---->"+submissionCreationDate);
			
		    //Read donloaded file Media Submission
			CSVReportData_mediaSubmission=General.readCSVFileDataLineByLine(System.getProperty("user.home") + "\\Downloads\\"+"submissions-"+submissionCreationDate+".csv",SubmissionName_MediaNext);//System.getProperty("user.home") + "\\Downloads\\"+"submissions-"+submissionCreationDate+".csv",SubmissionName
			System.out.println(CSVReportData_mediaSubmission);
			System.out.println(CSVReportData_mediaSubmission.get(0));
			System.out.println(CSVReportData_mediaSubmission.get(1));
			System.out.println(CSVReportData_mediaSubmission.get(2));
			System.out.println(CSVReportData_mediaSubmission.get(3));
			System.out.println(CSVReportData_mediaSubmission.get(4));
			System.out.println(CSVReportData_mediaSubmission.get(5));
			System.out.println(CSVReportData_mediaSubmission.get(6));
			//Verify CSV file have columns type, email, first name, last name, role, language, organization groups.
			assertion=CSVReportData_mediaSubmission.get(4).contains(SubmissionName_MediaNext);
			if(assertion==true){
			System.out.println("csv Report Contains-->"+" SubmissionName_MediaNext");
			}else {
			System.out.println("csv Report not Contains-->"+" SubmissionName_MediaNext");
			report("f","Activity Report not Contains"+" SubmissionName_MediaNext");
			}
			
			assertion=CSVReportData_mediaSubmission.get(3).contains("MEDIA");
			if(assertion==true){
			System.out.println("csv Report Contains-->"+"MEDIA");
			}else {
			System.out.println("csv Report not Contains-->"+"MEDIA");
			report("f","Activity Report not Contains"+"MEDIA");
			}
			
			assertion=CSVReportData_mediaSubmission.get(3).contains("MEDIA");
			if(assertion==true){
			System.out.println("csv Report Contains-->"+"MEDIA");
			}else {
			System.out.println("csv Report not Contains-->"+"MEDIA");
			report("f","Activity Report not Contains"+"MEDIA");
			}
				
			 //Read donloaded file Media Submission
			CSVReportData_pdSubmission=General.readCSVFileDataLineByLine(System.getProperty("user.home") + "\\Downloads\\"+"submissions-"+submissionCreationDate+".csv",SubmissionName_PD);//System.getProperty("user.home") + "\\Downloads\\"+"submissions-"+submissionCreationDate+".csv",SubmissionName
			System.out.println(CSVReportData_pdSubmission);
			System.out.println(CSVReportData_pdSubmission.get(0));
			System.out.println(CSVReportData_pdSubmission.get(1));
			System.out.println(CSVReportData_pdSubmission.get(2));
			System.out.println(CSVReportData_pdSubmission.get(3));
			System.out.println(CSVReportData_pdSubmission.get(4));
			System.out.println(CSVReportData_pdSubmission.get(5));
			System.out.println(CSVReportData_pdSubmission.get(6));
			//Verify CSV file have columns type, email, first name, last name, role, language, organization groups.
			assertion=CSVReportData_pdSubmission.get(4).contains(SubmissionName_PD);
			if(assertion==true){
			System.out.println("csv Report Contains-->"+"SubmissionName_PD");
			}else {
			System.out.println("csv Report not Contains-->"+"SubmissionName_PD");
			report("f","Activity Report not Contains"+"SubmissionName_PD");
			}
			
			System.out.println("Subission ID:"+CSVReportData_pdSubmission.get(1));
			assertion=CSVReportData_pdSubmission.get(1).contains(submissionID);
			if(assertion==true){
			System.out.println("csv Report Contains-->"+"submissionID");
			}else {
			System.out.println("csv Report not Contains-->"+"submissionID");
			report("f","Activity Report not Contains"+"submissionID");
			}
			
			assertion=CSVReportData_pdSubmission.get(3).contains("PD");
			if(assertion==true){
			System.out.println("csv Report Contains-->"+"PD");
			}else {
			System.out.println("csv Report not Contains-->"+"PD");
			report("f","Activity Report not Contains"+"PD");
			}
			
			
			CSVReportData_header=General.readCSVFileDataLineByLine(System.getProperty("user.home") + "\\Downloads\\"+"submissions-"+submissionCreationDate+".csv","Name");//System.getProperty("user.home") + "\\Downloads\\"+"submissions-"+submissionCreationDate+".csv",SubmissionName
			System.out.println(CSVReportData_header);
			System.out.println(CSVReportData_header.get(0));
			System.out.println(CSVReportData_header.get(1));
			System.out.println(CSVReportData_header.get(2));
			System.out.println(CSVReportData_header.get(3));
			System.out.println(CSVReportData_header.get(4));
			System.out.println(CSVReportData_header.get(5));
			System.out.println(CSVReportData_header.get(6));
			//Verify CSV file have columns type, email, first name, last name, role, language, organization groups.
			assertion=CSVReportData_header.get(5).contains("Client");
			if(assertion==true){
			System.out.println("csv Report Contains-->"+"Client");
			}else {
			System.out.println("csv Report not Contains-->"+"Client");
			report("f","Activity Report not Contains"+"Client");
			}
				

		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
          }
}
	
	
	
	public void assertion() throws Exception {
		//Verify that organizations is renamed to client everywhere in the CSV report.
		assertion=CSVReportData_header.get(5).contains("Client");
		if(assertion==true){
		System.out.println("csv Report Contains-->"+"Client");
		report("p","Activity Report Contains"+"Client");
		}else {
		System.out.println("csv Report not Contains-->"+"Client");
		report("f","Activity Report not Contains"+"Client");
		}
		}
	
	public static String GetCurrentDate() throws Exception 	
	{		
		Calendar cal = Calendar.getInstance();		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());				
		return (dateFormat.format(cal.getTime()));
		
	}
	
	
	public void readCSVFile_Sub() throws Exception {
		try {

			File filepath = new File(System.getProperty("user.home")+ "\\Downloads\\");

			String[] filenames = filepath.list();
			int countofFiles = filenames.length;
			System.out.println("countofFiles##########  :- " + countofFiles);

			for (String filename : filenames) {
				System.out.println(filename);

               filepath = new File(System.getProperty("user.home")+ "\\Downloads\\" + filename);


              //assertion = FileUtils.readFileToString(filepath1,"UTF-8").contains("ExportCSV_763919_P");
              assertion = FileUtils.readFileToString(filepath,"UTF-8").contains(SubmissionName_MediaNext);
              System.out.println(FileUtils.readFileToString(filepath,"UTF-8"));

               System.out.println("ExportCSVresult_Sub Downloaded file contains string :-"+ assertion);
				
				if(assertion==false){
					report("f", "Assertion failed while verifying File content with String");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
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


