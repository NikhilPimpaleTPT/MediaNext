package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Admin_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

public class Sub_363522 {
	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_363522"+CommonElements.BROWSER+"V";
	String Targetlanguage_1[] = { "German (Germany)" };
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
    String TranslatorGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String Past_dueDate = "01/02/2018";



	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_363522");
		dataSet.put("TL_test_case_description", "SUB-363522: Due date checks");
		dataSet.put("TL_internal_testCase_ID", "363522");
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
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			checkPrepopulatedValues();
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			Thread.sleep(2000);
			checkdueDates(Past_dueDate,CommonElements.action().DATE_OFFSET);
			Thread.sleep(2000);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void checkPrepopulatedValues() throws Exception {

		try {
            System.out.println("INSIDE METHOD checkPrepopulatedValues");
            
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
			Thread.sleep(2000);

			// This will verify Pre-defined max-char value
			String maxChar = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input).getAttribute("value");
			System.out.println("Max Chars per line value is:- "+ maxChar.trim());

			assertion = Verify.action().verifyInputElementTextPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,maxChar.trim(), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying MaxChar Value Per Line");
			}

			// This will verify ReadingSpeed
			String ReadingSpeed = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input).getAttribute("value");
			System.out.println("Max Chars per line value is:- "+ ReadingSpeed.trim());

			assertion = Verify.action().verifyInputElementTextPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input,ReadingSpeed.trim(), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying ReadingSpeed Value");
			}

			// This will verify Min_Duration
			String Min_Duration = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input).getAttribute("value");
			System.out.println("Max Chars per line value is:- "+ Min_Duration.trim());

			assertion = Verify.action().verifyInputElementTextPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,Min_Duration.trim(), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Min_ReadingSpeed Value");
			}

			// This will verify Max_Duration
			String Max_Duration = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input).getAttribute("value");
			System.out.println("Max Chars per line value is:- "+ Max_Duration.trim());

			assertion = Verify.action().verifyInputElementTextPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,Max_Duration.trim(), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Max_Duration Value");
			}

			System.out.println("EOM checkPrepopulatedValues");

		} catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}

	
	public static String GetDataPlus(Integer Days) throws Exception {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, Days);
		DateFormat dateFormat = new SimpleDateFormat(". dd, yyyy HH:mm", Locale.getDefault());
		DateFormat dateFormat2 = new SimpleDateFormat("MMMM");
		String monthParsed = dateFormat2.format(cal.getTime());
		return (monthParsed.substring(0, 3) + dateFormat.format(cal.getTime()));
	}
	
	public void checkdueDates(String date1,  String DueDate) throws Exception {

		try {
           // check for current month/date/year
			
		
          PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
		   Thread.sleep(1000);

			DateFormat dateformate = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			date1 = dateformate.format(date);
			System.out.println("Calendar current date from system:-" +date1);

			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input,date1);
			Thread.sleep(2000);

			String Current_Month = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input).getAttribute("value");
			System.out.println("Calendar current Month from date Picker:-" + Current_Month);

			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input).getAttribute("value").equalsIgnoreCase(date1);
			if (assertion == false) {
				report("f","Assertion failed while verifying Current_Month  after Search");
			}
			Thread.sleep(1000);

		
			// check for empty due date
      
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
			
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input, Keys.chord(Keys.CONTROL, "a"));
	    	Thread.sleep(1000);	
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input, Keys.chord(Keys.DELETE));
	    	Thread.sleep(2000);
			

			String Empty_Duedate_Input_color = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input).getCssValue("caret-color");
			System.out.println("color of Empty_Duedate_Input is:-"+ Empty_Duedate_Input_color);
			System.out.println("HEREEEEEE");
			System.out.println("COLOR*********"+General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input).getCssValue("caret-color"));
		    assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input).getCssValue("caret-color").equalsIgnoreCase("rgb(255, 79, 100)");
			if (assertion == false) {
				report("f", "Assertion failed while verifying Empty_Duedate_Input_color");
			}
			Thread.sleep(1000);

			
			//TODO SCENARIO 3
			// Check for Past dueDate
			// This assertion should be failed due to bug SUB-561, SUB-302
		   // NOTE:-Due date input should be in red color if provided post duedate but due to above mention bug id's it's not showing in red.
			//TODO bug SUB-561, SUB-302 has been resolved

			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
			Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input,"01/03/2018");
			Thread.sleep(2000);

			String PastDuedate_Input_color = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input).getCssValue("caret-color");
			System.out.println("color of Due date Line is:-"+ PastDuedate_Input_color);

			System.out.println("COLOR--->"+General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input).getCssValue("caret-color"));
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input).getCssValue("caret-color").equalsIgnoreCase("rgb(255, 79, 100)");
			if (assertion == false) {
				report("f", "Assertion failed while verifying PastDuedate_Input_color");
			}
           Thread.sleep(2000);

		}

		catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName after delete_submission");
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
