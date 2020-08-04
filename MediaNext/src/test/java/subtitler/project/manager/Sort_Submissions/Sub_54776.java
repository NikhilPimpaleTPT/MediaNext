package subtitler.project.manager.Sort_Submissions;

import static org.testng.AssertJUnit.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;

import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_54776 {
	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String sourcelanguage = "en-US - English (United States)";
	String targetlanguage = "de-DE - German (Germany)";
	String SubmissionName = "Sub_54776"+CommonElements.BROWSER+"A1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String menu = "Submissions";
	String TranslatorGroupName = "Common_group";
	String dueDate_1stRow_descending;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_54776");
		dataSet.put("TL_test_case_description","SUB-54776:Sort by creation/due date");
		dataSet.put("TL_internal_testCase_ID", "54776");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {

			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu);
			
			for(int i=1; i<=3; i++){
			// TODO NEW IMPL OF SUBMISSION CREATION
			
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET_INTEGER+i ,SubmissionName +i,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			
            Thread.sleep(2000);
			}
          
            
         //sort submission by creation date
        	PM_user.action().sortSubmission_creationDate(SubmissionName, true);
    		Thread.sleep(2000);
    		
    		String CreationDate_1stRow_ascending = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_creationDate(1)).getText();
    		System.out.println("CREATIONDATE##################:-" +CreationDate_1stRow_ascending);
    		
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName + "1",CreationDate_1stRow_ascending));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName + "1",CreationDate_1stRow_ascending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  creation date after search");
			}

			Thread.sleep(2000);
			
			String CreationDate_2ndtRow_ascending = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_creationDate(2)).getText();
    		System.out.println("CREATIONDATE##################:-" +CreationDate_2ndtRow_ascending);
    		
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName + "2",CreationDate_2ndtRow_ascending));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName + "2",CreationDate_2ndtRow_ascending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  creation date after search");
			}

			Thread.sleep(2000);
			

			String CreationDate_3rdtRow_ascending = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_creationDate(3)).getText();
    		System.out.println("CREATIONDATE##################:-" +CreationDate_3rdtRow_ascending);
    		
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName + "3",CreationDate_3rdtRow_ascending));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName + "3",CreationDate_3rdtRow_ascending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  creation date after search");
			}

			PM_user.action().sortSubmission_creationDate(SubmissionName, false);
			Thread.sleep(2000);
			

			String CreationDate_1stRow_descending = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_creationDate(1)).getText();
    		System.out.println("CREATIONDATE##################:-" +CreationDate_1stRow_descending);
    		
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName + "3",CreationDate_1stRow_descending));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName + "3",CreationDate_1stRow_descending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  creation date after search");
			}

			Thread.sleep(2000);
			

			String CreationDate_2ndtRow_descending = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_creationDate(2)).getText();
    		System.out.println("CREATIONDATE##################:-" +CreationDate_2ndtRow_descending);
    		
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName + "2",CreationDate_2ndtRow_descending));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName + "2",CreationDate_2ndtRow_descending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  creation date after search");
			}

			Thread.sleep(2000);
			

			String CreationDate_3rdtRow_descending = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_creationDate(3)).getText();
    		System.out.println("CREATIONDATE##################:-" +CreationDate_3rdtRow_descending);
    		
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName + "1",CreationDate_3rdtRow_descending));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName + "1",CreationDate_3rdtRow_descending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  creation date after search");
			}

			System.out.println("EOM sortSubmission_creationDate()");
			
			
			
			 //Sort sub by dueDate
        	PM_user.action().sortSubmission_dueDate(SubmissionName, true);
    		Thread.sleep(2000);
    		
    		 dueDate_1stRow_descending = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_dueDate(1)).getText();
    		System.out.println("DUEDATE 1ST ROW ##################:-" +dueDate_1stRow_descending);

		    
    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName + "1",dueDate_1stRow_descending));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName + "1",dueDate_1stRow_descending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying dueDate after search");
			}

			Thread.sleep(2000);
			
			String dueDate_2ndtRow_descending = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_dueDate(2)).getText();
    		System.out.println("DUEDATE 2ND ROW ##################:-" +dueDate_2ndtRow_descending);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName + "2",dueDate_2ndtRow_descending));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName + "2",dueDate_2ndtRow_descending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  dueDate after search");
			}

			Thread.sleep(2000);
			
		     String	dueDate_3rdtRow_descending = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_dueDate(3)).getText();
    		System.out.println("DUEDATE 3RD ROW ##################:-" +dueDate_3rdtRow_descending);
    		
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName + "3",dueDate_3rdtRow_descending));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName + "3",dueDate_3rdtRow_descending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  dueDate after search");
			}

			PM_user.action().sortSubmission_dueDate(SubmissionName, false);
			Thread.sleep(2000);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName + "3",dueDate_3rdtRow_descending));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName + "3",dueDate_3rdtRow_descending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  dueDate after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName + "2",dueDate_2ndtRow_descending));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName + "2",dueDate_2ndtRow_descending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying dueDate after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName + "1",dueDate_1stRow_descending));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName + "1",dueDate_1stRow_descending), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  dueDate after search");
			}

			System.out.println("EOM sortSubmission_dueDate()");
     
     
            

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
    public void CreateSubmisson_Step4_MetaData(Integer DueDate,String SubmissionName,String SourceLanguage,String TargetLanguage) throws Exception {
    	System.out.println("INSIDE method CreateSubmisson_Step4_MetaData()\n"); 
    	//TODO NOT REQUIRED
//    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
//    	Thread.sleep(1000);
//    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
//    	Thread.sleep(1000);
    	
    	System.out.println("DATE IMPLEMENTATION");
    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
		String newdate = GetDataPlus(Integer.valueOf(DueDate));

    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
    	Thread.sleep(1000);
    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
    	Thread.sleep(1000);
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
    	Thread.sleep(2000);		
    	
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_arrow);
    	Thread.sleep(1000);		
    	
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_year(newdate.substring(9, 13)));
    	Thread.sleep(1000);		
    	
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_month(newdate.substring(0, 3).toUpperCase()));
    	Thread.sleep(1000);		
    	if(newdate.substring(5, 6).contentEquals("0")){
    		System.out.println("IF--->"+newdate.substring(6, 7));
    		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(6, 7)));
    		
    		Thread.sleep(1000);		
    	}else{
    		System.out.println("ELSE--->"+newdate.substring(5, 7));
    		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(5, 7)));
    		Thread.sleep(1000);		
    	}

    	System.out.println("DATE IMPLEMENTATION END--------------");

    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input, SubmissionName);
    	Thread.sleep(1000);
    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, SourceLanguage);
    	Thread.sleep(1000);
//    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
//    	Thread.sleep(1000);
    	System.out.println("CLICKED");
//    	((JavascriptExecutor) General.driver).executeScript(
//                "arguments[0].scrollIntoView();", General.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage).toString()));
    	
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage));
    	Thread.sleep(1000);
    	if(TargetLanguage!=""){
    		System.out.println("TARGET LANGUAGE NOT NULL");
    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
	    	Thread.sleep(1000);
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input, TargetLanguage);
	    	Thread.sleep(1000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(TargetLanguage));
	    	Thread.sleep(1000);
    	}
    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
    	Thread.sleep(1000);
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
    	Thread.sleep(25000);
    	//TODO NOT REQUIRED
//    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
//    	Thread.sleep(1000);
    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
    	Thread.sleep(1000);
	    System.out.println("EOM CreateSubmisson_Step4_MetaData()\n");
    }

    
    public static String GetDataPlus(Integer Days) throws Exception {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, Days);
		DateFormat dateFormat = new SimpleDateFormat(". dd, yyyy HH:mm", Locale.getDefault());
		DateFormat dateFormat2 = new SimpleDateFormat("MMMM");
		String monthParsed = dateFormat2.format(cal.getTime());
		return (monthParsed.substring(0, 3) + dateFormat.format(cal.getTime()));
	}
	
	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName + "1",dueDate_1stRow_descending), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying  Submission Name after search");
		} else {
			report("p", "All Assertion passed.");
		}
	}

	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(
				dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);
	}
}
