package admin.Additional_Testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Admin_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import locators.UserLocators;
import modules.Verify;
import modules.admin;

public class Sub_763919 {

	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String menu_users = "Users";
	String menu_Submission = "Submissions";
	String downloadPath = System.getProperty("user.home") + "\\Downloads";
   String SubmissionName = "ExportCSV_763919"+CommonElements.BROWSER+"Q5";
   String firstname = "ExportCSV_763919"+CommonElements.BROWSER+"VFirst2_Q5";
	String lastName = "ExportCSV_763919"+CommonElements.BROWSER+"VLast2_Q5";
	String emailid = "Vendor763919"+CommonElements.BROWSER+"ExportCSV_Q5@gmail.com";


	String sourcelanguage = "en-US";
	String targetlanguage = "de-DE";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
    String TranslatorGroupName = "Common_group";
	String menu_submission = "Submissions";
	String UserType = "Vendor";
	String Typename = "Vendor";

	String selectedRole = "Linguist";
     File filepath1;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_763919");
		dataSet.put("TL_test_case_description","SUB-763919:As a PDM, I need to get a report of submissions and users");
		dataSet.put("TL_internal_testCase_ID", "763919");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			// create submission through admin and verify Export CSV
			admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			admin.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			// TODO NEW IMPL OF SUBMISSION CREATION
			admin.action().CreateSubmisson_Step1("17", "35", "80", "100");
			admin.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			admin.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			admin.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			admin.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			admin.action().SearchSubmisson(SubmissionName);

			assertion = Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			admin.action().ClearInputvalues(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input);
			Thread.sleep(1000);
			admin.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	    	Thread.sleep(4000);	
//			admin.action().type(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input, Keys.chord(Keys.ENTER));
	    	//TODO NOT REQUIRED AS PER NEW IMPROVEMENT FOR FILTER SUBMISSION (1.22.0 RC1)
			//admin.action().type(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input, Keys.ENTER.toString());
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
			readCSVFile_Sub();

			// Create user through admin and verify Export CSV.

			admin.action().Navigate(menu_users);
			admin.action().CreateUser_Step1(UserType, firstname, lastName,emailid);
			admin.action().Create_And_EditUser_SelectRole(selectedRole);
			admin.action().CreateUser_SelectLanguage(sourcelanguage,targetlanguage);
			admin.action().Create_And_SaveUser();
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(UserLocators.Locator().UserCreated_message(firstname,lastName));
			assertion = Verify.action().verifyElementPresent(UserLocators.Locator().UserCreated_message(firstname,lastName), 5);
			if (assertion == false)
				report("f","Assertion failed while verifying message for User Created Succesfully");

			Thread.sleep(1000);
			admin.action().Searchuser(firstname);
			General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname));
			assertion = Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname), 5);
			if (assertion == false)
				report("f","Assertion failed while verifying Fullname on User page");

			Thread.sleep(2000);
			admin.action().Navigate(menu_users);
			Thread.sleep(2000);

			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home")+ "\\Downloads\\");
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Admin_User_Submission_Locators.Locator().Export_Csv_button);
			Thread.sleep(2000);
			General.action().Click(Admin_User_Submission_Locators.Locator().Export_Csv_button);
			Thread.sleep(5000);
			readCSVFile_User();

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}

	public void readCSVFile_Sub() throws Exception {
		try {

			File filepath = new File(System.getProperty("user.home")+ "\\Downloads\\");

			String[] filenames = filepath.list();
			int countofFiles = filenames.length;
			System.out.println("countofFiles##########  :- " + countofFiles);

			for (String filename : filenames) {
				System.out.println(filename);

               filepath1 = new File(System.getProperty("user.home")+ "\\Downloads\\" + filename);


              //assertion = FileUtils.readFileToString(filepath1,"UTF-8").contains("ExportCSV_763919_P");
              assertion = FileUtils.readFileToString(filepath1,"UTF-8").contains(SubmissionName);

               System.out.println("ExportCSVresult_Sub Downloaded file contains string :-"+ assertion);
				
				if(assertion==false){
					report("f", "Assertion failed while verifying File content with String");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readCSVFile_User() throws Exception {
		try {
			File filepath = new File(System.getProperty("user.home")+ "\\Downloads\\");

			String[] filenames = filepath.list();
			int countofFiles = filenames.length;
			System.out.println("countofFiles##########  :- " + countofFiles);

			for (String filename : filenames) {
				System.out.println(filename);

		
         filepath1 = new File(System.getProperty("user.home")+ "\\Downloads\\" + filename);

			assertion = FileUtils.readFileToString(filepath1,"UTF-8").contains(firstname);
              System.out.println("ExportCSVresult_Sub Downloaded file contains string :-"+ assertion);
				if(assertion==false){
					report("f", "Assertion failed while verifying File content with String");
				}
			}
				
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void assertion() throws Exception {
		
		assertion = FileUtils.readFileToString(filepath1,"UTF-8").contains(firstname);
		if (assertion == false) {
			report("f","Assertion failed while verifying File content with String.");
		} else {
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
