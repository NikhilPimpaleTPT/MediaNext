package Smoke;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;
import modules.admin;

public class Sub_54783 {
	

Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	 
	 String password = "Password1!";
	 
	 //TODO THIS HAS ISSUE
	 String sourcelanguage = "en-US - English (United States)";//en-US - English (United States)
//	 String sourcelanguage = "English (United States)";
	 String sourcelanguage2 = "en-CA";

	 String targetlanguage = "de-DE - German (Germany)";
	 String targetlanguage2 = "fr-FR";
	 String targetlanguage3 = "es-ES";
	 String SubmissionName = "SUB_54783"+CommonElements.BROWSER+"F1";
	 String OrganizationName = "Subtitle_Common_orgs";
	 String WorkflowName = "One_Step_Workflow";
	 String fileDirName = "common";
	 String menu = "Submissions";
	 String menu_allJobs = "Jobs";
	 String TranslatorGroupName="Common_group";
	 String AssigneeName="Common_group";
	 String TargetlanguageGerman="German (Germany)";
	 String TargetlanguageSpanish="Spanish (Spain)";
	 String TargetlanguageFrench="French (France)";
	
	String filePath=System.getProperty("user.dir")+"\\data\\Submission\\";
	 
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_54783");
	dataSet.put("TL_test_case_description","SUB-54783:Create Submission");
	dataSet.put("TL_internal_testCase_ID", "54783");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				
				admin.action().login(CommonElements.action().PM_username,password);
				admin.action().Navigate(menu);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
				Thread.sleep(2000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
				VerifyReadingSpeedInputMethod();
				Thread.sleep(2000);
				//TODO NEW IMPL OF SUBMISSION CREATION
				admin.action().Navigate(menu);
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
				System.out.println("filePath------>"+filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_SRT_FOLDER);
				CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE,false,sourcelanguage2, CommonElements.action().COMMON_TARGET_LANGUAGE);
				
				Thread.sleep(2000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_Cancel_Alert_button);
				Thread.sleep(2000);
				
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_CancelDialogBoxMassege, 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Submission Cancel Dialog Box Massege");
				}
				
				Thread.sleep(2000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_CancelDialogBoxMassege_StayOption);
				Thread.sleep(2000);
				
				Thread.sleep(2000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_Cancel_Alert_button);
				Thread.sleep(2000);
				
				Thread.sleep(2000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_CancelDialogBoxMassege_LeaveOption);
				Thread.sleep(2000);
				
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button, 5);
				if(assertion==false){
					report("f","Assertion failed while verifying New Submission_Button after clicking leave button ");
				}
				
				admin.action().Navigate(menu);
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
				System.out.println("filePath------>"+filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_TTML_WITH_BR_TAG_FOLDER);
				CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE,true,sourcelanguage2, CommonElements.action().COMMON_TARGET_LANGUAGE);

				
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
		    	Thread.sleep(1000);
		    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input, targetlanguage2);
		    	Thread.sleep(1000);
		    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(targetlanguage2));
		    	Thread.sleep(1000);
		    	
		    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
		    	Thread.sleep(1000);
		    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input, targetlanguage3);
		    	Thread.sleep(1000);
		    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(targetlanguage3));
		    	Thread.sleep(1000);
				
				
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
		    	Thread.sleep(1000);
		    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
		    	Thread.sleep(25000);
		    	
				PM_user.action().SearchSubmisson(SubmissionName);
				
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Bad Credentials Message");
				}
				
	
				admin.action().Navigate(menu_allJobs);
				Thread.sleep(2000);
				PM_user.action().SearchSubmisson(SubmissionName);
				
				
				
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_AllJobs_SubmissionName_MultiLanguage(SubmissionName ,TargetlanguageGerman,AssigneeName), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Muti Target Language for same Submission With Assignee Name");
				}
				
				assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_AllJobs_SubmissionName_MultiLanguage(SubmissionName ,TargetlanguageSpanish,AssigneeName), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Muti Target Language for same Submission With Assignee Name");
				}
				
				}
			catch (Exception e){
				  report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			  }
			
	}
	
	
	
	public void CreateSubmisson_Step4_MetaData(String DueDate,String SubmissionName,String SourceLanguage,Boolean Language2, String SourceLanguage2,String TargetLanguage) throws Exception {
    	System.out.println("INSIDE method CreateSubmisson_Step4_MetaData()\n"); 
    	System.out.println("DATE IMPLEMENTATION");
    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
		String newdate = PM_user.action().GetDataPlus(Integer.valueOf(DueDate));
		System.out.println(newdate);
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
    	Thread.sleep(3000);
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
    	Thread.sleep(2000);
    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, SourceLanguage);
    	Thread.sleep(3000);
        System.out.println("CLICKED");
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage));
    	
    	if(Language2) {
    	Thread.sleep(2000);
    	General.action().Click(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_SourceLanguage_cancelButton(sourcelanguage));
    	Thread.sleep(2000);
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
    	Thread.sleep(2000);
    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, SourceLanguage2);
    	Thread.sleep(3000);
        System.out.println("CLICKED");
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage2));
    	Thread.sleep(5000);
    	}
    	
    	
    	if(TargetLanguage!=""){
    		System.out.println("TARGET LANGUAGE NOT NULL");
    		Thread.sleep(1000);
    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
	    	Thread.sleep(1000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
	    	Thread.sleep(1000);
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input, TargetLanguage);
	    	Thread.sleep(1000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(TargetLanguage));
	    	Thread.sleep(1000);
	    	
	    	
    	}
    	
	    System.out.println("EOM CreateSubmisson_Step4_MetaData()\n");
    }
	
	
	public void VerifyReadingSpeedInputMethod() throws Exception{
		
		assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Minreadingspeed, 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Min reading speed");
		}
		assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed, 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Max reading speed");
		}
		
		assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input, 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Max Char Per Line");
		}
		
		assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input, 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Min Duration");
		}
		
		assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input, 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Max Duration");
		}
		
		assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinCountOfFrames_input, 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Min Count Of Frames");
		}
		
	}
	
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Muti Target Language for same Submission With Assignee Name ");
		}
		else{
		    report("p", "All Assertion passed.");
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