package subtitler.Reviewer;


/**
 * 
 * @author pvaidya
 *
 */

import static org.testng.AssertJUnit.assertTrue;
import java.util.LinkedHashMap;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

public class Sub_796133 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_796133"+CommonElements.BROWSER+"W4";
    String OrganizationName = "Subtitle_Common_orgs";
    String WorkflowName = "Three_Step_Transc_Workflow";
	String menu_Submission = "Submissions";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_796133");
		dataSet.put("TL_test_case_description", "Sub_796133 : Add tooltip to OST icon.");
		dataSet.put("TL_internal_testCase_ID", "796133");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			// PM login
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			// TODO NEW IMPL OF SUBMISSION CREATION For Translator , Review And Transcription.
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
		    CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName,"transcription",transcGroupName,"Trans",TranslatorGroupName,true, "Review", ReviewGroupName, true);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
    	   
    			
    			
    	     Thread.sleep(1000);
    	     PM_user.action().Navigate(menu_to_claim);
   	         Thread.sleep(1000);
   	         PM_user.action().PM_ToClaim(SubmissionName);
   	         Thread.sleep(1000);
   	         PM_user.action().Navigate(menu_ongoing);
  		     Thread.sleep(1000);
   	         PM_user.action().SearchSubmisson_andCheck(SubmissionName);
   	         
   		     General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
   		     Thread.sleep(2000);
   		     General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
   		     Thread.sleep(4000);
   		     
   		     
   		     General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
  		     Thread.sleep(2000);
  		     General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
  		     Thread.sleep(3000);
   		     
   		     General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_button);
		     Thread.sleep(2000);
		     General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_button);
		     Thread.sleep(4000);
   		     
   		     assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().SubmissionEdit_OnScreenToolTip, 5);
   	    		if(assertion==false){
   	    			report("f","Assertion failed while verifying Mark/unmark this segment as On Screen Text ");
   	    		}
    		
    	    Thread.sleep(2000);
            PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
    	    Thread.sleep(2000);
    	    PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_review);
            Thread.sleep(3000);
			
			
			Thread.sleep(2000);
    		General.action().logoutMethod();
				
    		// Trans login	
    		 General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
    		 
    		 EditSubmission_ToVerify_OnScreenToolTip();
			
		     assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().SubmissionEdit_OnScreenToolTip, 5);
	    	 if(assertion==false){
	    	    report("f","Assertion failed while verifying Mark/unmark this segment as On Screen Text");
	    	}
	    		
	    	Thread.sleep(1000);
	        PM_user.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
	    	Thread.sleep(1000);
	    	PM_user.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_completeButton);
	        Thread.sleep(3000);
						
	    		
	    	Thread.sleep(2000);
    		General.action().logoutMethod();
				
			//Review Login	
    		General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
    			
    		EditSubmission_ToVerify_OnScreenToolTip();
    		
		}
	     

		catch (Exception e) {
				report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			}
			
}
	
	
	public void EditSubmission_ToVerify_OnScreenToolTip()throws Exception {
		
		
		 Thread.sleep(2000);
         Translator.action().Navigate(menu_to_claim);
         Thread.sleep(2000);
         Translator.action().trans_ToClaim(SubmissionName);
         Thread.sleep(2000);
         Translator.action().Navigate(menu_ongoing);
		 Thread.sleep(2000);
		 Translator.action().SearchSubmisson_andCheck(SubmissionName);
         Thread.sleep(2000);
	     General.action().waitforelemenetpresent(CommonLocartors.Locator().Submission_Edit_icon);
	     Thread.sleep(2000);
	     General.action().Click(CommonLocartors.Locator().Submission_Edit_icon);
	     Thread.sleep(4000);
	     
	     Thread.sleep(2000);
		 General.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
		 Thread.sleep(3000);
	     
		
	}
	
	
	
	
	public void CreateSubmisson_Step2_OrganizationAndWorkflow(String OrganizationName,String WorkflowName,String transcStep,String transcGroupName,String transStep,String TransGroupName,Boolean Trans,String reviewStep,String ReviewGroupName,Boolean Review) throws Exception {
    	System.out.println("INSIDE method CreateSubmisson_Step2_OrganizationAndWorkflow()\n"); 
    	
    	Thread.sleep(2000);
    
    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
    
//    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
        General.action().Dropdownwithoutselect_with_javaScript(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
    	Thread.sleep(2000);
    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown);
    	Thread.sleep(2000);
    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown,Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown(WorkflowName));
    	Thread.sleep(2000);

    	
    	
    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transcStep));
    	Thread.sleep(2000);
    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transcStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_trans(transcGroupName));
    	Thread.sleep(2000);
    	
    	if(Trans){

    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep));
    		Thread.sleep(1000);
    		General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_review(TransGroupName));
	    	Thread.sleep(1000);
    	}
    	
    	if(Review){

    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep));
    		Thread.sleep(1000);
    		General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_transc(ReviewGroupName));
	    	Thread.sleep(1000);
    	}
    	Thread.sleep(2000);

    	
    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		Thread.sleep(1000);
    	
	    System.out.println("EOM CreateSubmisson_Step2_OrganizationAndWorkflow()\n");
    }
	
	
	
	public void assertion() throws Exception {

		 assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().SubmissionEdit_OnScreenToolTip, 5);
    	 if(assertion==false){
    	    report("f","Assertion failed while verifying Mark/unmark this segment as On Screen Text");
    	}
		else {
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
