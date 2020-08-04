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

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary: This testcase verifies the date format of submission is 'MMM dd, y HH:mm'
 *
 */

public class Sub_1923702 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataset = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1923702" + CommonElements.BROWSER + "H1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Three_Step_Transc_Workflow";
	String fileDirName = "common";
	String menu_submission = "Submissions";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String menu_jobs = "Jobs";
	String CreationDateOfSubmission;
	String dueDateOfSubmission_x;
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1923702");
		dataset.put("TL_test_case_description", "SUB-1923702:This testcase verifies the date format of submission is 'MMM dd, y HH:mm'");
		dataset.put("TL_internal_testCase_ID", "1923702");
	}
	
	@Test
	public void execute() throws Exception {
		testCase(dataset);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataset) throws Exception {
		
		
	try {
		
		General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
		Thread.sleep(2000);
		PM_user.action().Navigate(menu_submission);
		Thread.sleep(1000);		
		PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
		PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow_ThreeStepWorkflow(OrganizationName, WorkflowName,"transcription",transcGroupName,"Trans",TranslatorGroupName,true, "Review", ReviewGroupName, true);
		PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
		PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
	
		
		String dueDateOfSubmission = CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
		System.out.println("dueDateOfSubmission:"+dueDateOfSubmission);
		
		
		System.out.println("CreationDateOfSubmission:"+CreationDateOfSubmission);
		
		String dueDateOfSubmission_month=dueDateOfSubmission.substring(0, 3);
		System.out.println("dueDateOfSubmission_month:"+dueDateOfSubmission_month);
		String dueDateOfSubmission_day=dueDateOfSubmission.substring(5, 7);
		System.out.println("dueDateOfSubmission_day:"+dueDateOfSubmission_day);
		String dueDateOfSubmission_year=dueDateOfSubmission.substring(9, 13);
		System.out.println("dueDateOfSubmission_day:"+dueDateOfSubmission_year);
		
		dueDateOfSubmission_x=dueDateOfSubmission_month+" "+dueDateOfSubmission_day+", "+dueDateOfSubmission_year;
		System.out.println("dueDateOfSubmission_year:"+dueDateOfSubmission_x);
	
		//SEARCH SUBMISSION
		
		//Verify that time is not displayed for Media.NEXT due date in below folders
		//Submissions view
		//All jobs view
		//To Claim view
		//On Going view
		//Completed view
		PM_user.action().SearchSubmisson(SubmissionName);
		Thread.sleep(2000);
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_submissionDueDate(SubmissionName,dueDateOfSubmission_x), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName date after Search");
		}
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName,CreationDateOfSubmission), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName date after Search");
		}
		
		PM_user.action().Navigate(menu_to_claim);
        Thread.sleep(1000);
        //SEARCH SUBMISSION
 		PM_user.action().SearchSubmisson(SubmissionName);
 		Thread.sleep(2000);
 		
 		System.out.println("CreationDateOfSubmission:"+CreationDateOfSubmission);
 		
 		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName,CreationDateOfSubmission), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName date after Search");
		}
 		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_submissionDueDate(SubmissionName,dueDateOfSubmission_x), 5);
 		if (assertion == false) {
 			report("f","Assertion failed while verifying SubmissionName date after Search");
 		}
         
         PM_user.action().PM_ToClaim(SubmissionName);
         Thread.sleep(1000);
         
         PM_user.action().Navigate(menu_ongoing);
         Thread.sleep(1000);
         PM_user.action().SearchSubmisson_andCheck(SubmissionName);
		 Thread.sleep(2000);
		 assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName,CreationDateOfSubmission), 5);
		 if (assertion == false) {
		 report("f","Assertion failed while verifying SubmissionName date after Search");
		 }
         assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
		 if (assertion == false) {
		 report("f","Assertion failed while verifying SubmissionName date after Search");
         }
		
//		 //TODO Chips date in the 3 editors. Refer SUB-1488(Ticket is updated need to check only for trans and review)
//		 PM_user.action().Navigate(menu_submission);
//	     Thread.sleep(1000);
//         Thread.sleep(1000);
//         PM_user.action().SearchSubmisson_andCheck(SubmissionName);
//		 Thread.sleep(2000);
//			
//		 Thread.sleep(6000);
//		 General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
//		 Thread.sleep(6000);
//	        
//	     assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Submission_editView_chips(2)).getText().contains((CharSequence) dueDateOfSubmission_x);
//		 if (assertion == false) {
//		 report("f","Assertion failed while verifying due date chip");
//         }
//		 Thread.sleep(1000);
//		 General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
		 
		 
		 Thread.sleep(1000);
         PM_user.action().Navigate(menu_submission);
         Thread.sleep(1000);
		 Thread.sleep(1000);
         PM_user.action().Navigate(menu_ongoing);
         Thread.sleep(1000);
         //SEARCH SUBMISSION
   		 PM_user.action().SearchSubmisson(SubmissionName);
   		 Thread.sleep(2000);
   		 assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName,CreationDateOfSubmission), 5);
		 if (assertion == false) {
		 report("f","Assertion failed while verifying SubmissionName date after Search");
		 }
   		 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_submissionDueDate(SubmissionName,dueDateOfSubmission_x), 5);
   		 if (assertion == false) {
   			report("f","Assertion failed while verifying SubmissionName date after Search");
   		 }
         PM_user.action().PM_onGoing_submission_transc(SubmissionName,"trans",true,false);
         Thread.sleep(2000);
   
  
         PM_user.action().Navigate(menu_completed);
		 Thread.sleep(2000);
	     //SEARCH SUBMISSION
	  	 PM_user.action().SearchSubmisson(SubmissionName);
	  	 Thread.sleep(2000);
	  	 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_submissionDueDate(SubmissionName,dueDateOfSubmission_x), 5);
	  	 if (assertion == false) {
	  			report("f","Assertion failed while verifying SubmissionName  after Search");
	  	 }
	  	 
	  	 PM_user.action().Navigate(menu_jobs);
		 Thread.sleep(2000);
	     //SEARCH SUBMISSION
	  	 PM_user.action().SearchSubmisson(SubmissionName);
	  	 Thread.sleep(2000);
	  	 assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName,CreationDateOfSubmission), 5);
		 if (assertion == false) {
		 report("f","Assertion failed while verifying SubmissionName date after Search");
		 }
	  	 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_submissionDueDate(SubmissionName,dueDateOfSubmission_x), 5);
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
	    //SEARCH SUBMISSION
 		PM_user.action().SearchSubmisson(SubmissionName);
 		Thread.sleep(2000);
 		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName,CreationDateOfSubmission), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName date after Search");
		}
 		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_submissionDueDate(SubmissionName,dueDateOfSubmission_x), 5);
 		if (assertion == false) {
 			report("f","Assertion failed while verifying SubmissionName date after Search");
 		}
 		Thread.sleep(1000);
	    Translator.action().trans_ToClaim(SubmissionName);
	    Thread.sleep(1000);
	    Translator.action().Navigate(menu_ongoing);
	    Thread.sleep(1000);
	    Thread.sleep(1000);
        //SEARCH SUBMISSION
  		PM_user.action().SearchSubmisson(SubmissionName);
  		Thread.sleep(2000);
  		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName,CreationDateOfSubmission), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying SubmissionName date after Search");
		}
  		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName,dueDateOfSubmission_x), 5);
  		if (assertion == false) {
  			report("f","Assertion failed while verifying SubmissionName date after Search");
  		}
	    Translator.action().translate_onGoing_submission(SubmissionName, targetlanguage, true, false);
		Thread.sleep(2000);
	    Translator.action().Navigate(menu_completed);
		Thread.sleep(2000);
		Translator.action().SearchSubmisson(SubmissionName);
		Thread.sleep(2000);
	  	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_submissionDueDate(SubmissionName,dueDateOfSubmission_x), 5);
	  	if (assertion == false) {
	  		report("f","Assertion failed while verifying SubmissionName  after Search");
	  	}
		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName,CreationDateOfSubmission), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying SubmissionName date after Search");
		}
	  	
	  	Thread.sleep(2000);
		General.action().logoutMethod();
		
		// Review LogIn 
	     General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
         Thread.sleep(1000);
         Reviewer.action().Navigate(menu_to_claim);
         Thread.sleep(1000);
 	    //SEARCH SUBMISSION
  		PM_user.action().SearchSubmisson(SubmissionName);
  		Thread.sleep(2000);
  		assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName,CreationDateOfSubmission), 5);
 		if (assertion == false) {
 			report("f","Assertion failed while verifying SubmissionName date after Search");
 		}
  		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_submissionDueDate(SubmissionName,dueDateOfSubmission_x), 5);
  		if (assertion == false) {
  			report("f","Assertion failed while verifying SubmissionName date after Search");
  		}
         
         Reviewer.action().review_ToClaim(SubmissionName);
         Thread.sleep(1000);
         Reviewer.action().Navigate(menu_ongoing);
         Thread.sleep(1000);
         //SEARCH SUBMISSION
   		 PM_user.action().SearchSubmisson(SubmissionName);
   		 Thread.sleep(2000);
   		 assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName,CreationDateOfSubmission), 5);
 		 if (assertion == false) {
 		 report("f","Assertion failed while verifying SubmissionName date after Search");
 		 }
   		 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_submissionDueDate(SubmissionName,dueDateOfSubmission_x), 5);
   		 if (assertion == false) {
   			report("f","Assertion failed while verifying SubmissionName date after Search");
   		 }
         Reviewer.action(). Reviewer_onGoing_submission(SubmissionName, targetlanguage, true, false);
		 Thread.sleep(2000);
		 Reviewer.action().Navigate(menu_completed);
		 Thread.sleep(2000);
		 Translator.action().SearchSubmisson(SubmissionName);
		 Thread.sleep(2000);
		 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_submissionDueDate(SubmissionName,dueDateOfSubmission_x), 5);
		 if (assertion == false) {
		  		report("f","Assertion failed while verifying SubmissionName  after Search");
		 }
		 assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().check_sub_with_creationDate(SubmissionName,CreationDateOfSubmission), 5);
		 if (assertion == false) {
		 report("f","Assertion failed while verifying SubmissionName date after Search");
		 }
		
		
	} catch (Exception e) {
		report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	}

} 

	
	 public String  CreateSubmisson_Step4_MetaData(String DueDate,String SubmissionName,String SourceLanguage,String TargetLanguage) throws Exception {
	    	System.out.println("INSIDE method CreateSubmisson_Step4_MetaData()\n"); 
	    	System.out.println("DATE IMPLEMENTATION");
	    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
			String dueDate = GetDataPlus_DueDate(Integer.valueOf(DueDate));
			System.out.println(dueDate);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);
	    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(2000);		
	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_arrow);
	    	Thread.sleep(1000);		
	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_year(dueDate.substring(9, 13)));
	    	Thread.sleep(1000);		
	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_month(dueDate.substring(0, 3).toUpperCase()));
	    	Thread.sleep(1000);		
	    	if(dueDate.substring(5, 6).contentEquals("0")){
	    		System.out.println("IF--->"+dueDate.substring(6, 7));
	    		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(dueDate.substring(6, 7)));
	    		
	    		Thread.sleep(1000);		
	    	}else{
	    		System.out.println("ELSE--->"+dueDate.substring(5, 7));
	    		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(dueDate.substring(5, 7)));
	    		Thread.sleep(1000);		
	    	}
	    

	    	System.out.println("DATE IMPLEMENTATION END--------------");

	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input, SubmissionName);
	    	Thread.sleep(3000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
	    	Thread.sleep(2000);
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, SourceLanguage);
	    	Thread.sleep(3000);
//	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
//	    	Thread.sleep(1000);
	    	System.out.println("CLICKED");
//	    	((JavascriptExecutor) General.driver).executeScript(
//	                "arguments[0].scrollIntoView();", General.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage).toString()));
	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage));
	    	Thread.sleep(3000);
	    	if(TargetLanguage!=""){
	    		System.out.println("TARGET LANGUAGE NOT NULL");
	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
		    	Thread.sleep(1000);
		    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input, TargetLanguage);
		    	Thread.sleep(1000);
		    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(TargetLanguage));
		    	Thread.sleep(4000);
	    	}	    	
			
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
	    	Thread.sleep(1000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
	    	Thread.sleep(5000);
	    	CreationDateOfSubmission=GetDataPlus_CreationDate();
			System.out.println("CreationDateOfSubmission:"+CreationDateOfSubmission);
	    	
 	    System.out.println("EOM CreateSubmisson_Step4_MetaData()\n");
 	    
 	    return dueDate;
	    }
	
public static String GetDataPlus_DueDate(Integer Days) throws Exception {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, Days);
		DateFormat dateFormat = new SimpleDateFormat(". dd, yyyy HH:mm", Locale.getDefault());
		DateFormat dateFormat2 = new SimpleDateFormat("MMMM");
		String monthParsed = dateFormat2.format(cal.getTime());
		return (monthParsed.substring(0, 3) + dateFormat.format(cal.getTime()));
	}
	

public static String GetDataPlus_CreationDate() throws Exception {
	
	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
	//DateFormat dateFormat2 = new SimpleDateFormat("MMMM");
	String monthParsed = dateFormat.format(cal.getTime());
	return monthParsed;
}

public void assertion() throws Exception {
	System.out.println("inside assertion");		
	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_submissionDueDate(SubmissionName,dueDateOfSubmission_x), 5);
 	 if (assertion == false) {
 			report("f","Assertion failed while verifying SubmissionName  after Search");
 	 }else {
			report("p", "All Assertion passed.");
		}
	
}

	
	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(dataset.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}
}