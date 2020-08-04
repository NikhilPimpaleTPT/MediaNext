package subtitler.project_manager.Open_Jobs;

/**
 * 
 * @author pvaidya
 *
 */

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


public class Sub_776260 {

Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	 
	 String SubmissionName = " "+"SUB_776260"+" "+CommonElements.BROWSER+" "+"M1"+" ";
     String OrganizationName = "Subtitle_Common_orgs";
	 String WorkflowName = "One_Step_Workflow";
	 String menu_Submission = "Submissions";
     String TranslatorGroupName="Common_group";
 	 String filePath=System.getProperty("user.dir")+"\\data\\Submission\\";

	 
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_776260");
	dataSet.put("TL_test_case_description","SUB-776260:Prevent getting consecutive spaces in a submission name");
	dataSet.put("TL_internal_testCase_ID", "776260");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
				PM_user.action().Navigate(menu_Submission);
				//TODO NEW IMPL OF SUBMISSION CREATION
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
				System.out.println("filePath------>"+filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_SRT_FOLDER);
				CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
                Thread.sleep(2000);
                
                
                assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_ErrorMsg_ForSubmissionNameWithSpaces, 5);
        		if(assertion==false){
        			report("f","Assertion failed while verifying Error Msg for Submission Name With Spaces");
        		}
                
			}
			catch (Exception e){
				  report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			  }
			
	}
	
           public void CreateSubmisson_Step4_MetaData(String DueDate,String SubmissionName,String SourceLanguage,String TargetLanguage) throws Exception {
	    	
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
	    	
 	    System.out.println("EOM CreateSubmisson_Step4_MetaData()\n");
	    }

	
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DisableCreateSubmission, 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Create Submission Button Disable");
		}else{
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
