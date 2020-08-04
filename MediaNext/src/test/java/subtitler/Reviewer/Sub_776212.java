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
import org.openqa.selenium.By;

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

public class Sub_776212 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_776212"+CommonElements.BROWSER+"Q3";
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
	
	
	
	 String Bold_Tooltip = "Bold (Ctrl + B)";
	 String Italic_Tooltip = "Italic (Ctrl + I)";
	 String Underline_Tooltip = "Underline (Ctrl + U)";
	 String CopySourceToTarget_Tooltip_ForTrans = "Copy Source to Target (Alt + S";
	 String CopySourceToTarget_Tooltip_ForReview = "Copy Target to Modified Target";
	 String CancelCurrentEdit_Tooltip = "Cancel current edit (Esc)";
	 String ClearTheText_Tooltip ="Clear the text (Alt + X)";
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_776212");
		dataSet.put("TL_test_case_description", "Sub_776212 :Display shortcut key in all tooltips when there is.");
		dataSet.put("TL_internal_testCase_ID", "776212");
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
			
			
			 //For Transcription
			
			EditSubmission_ToVerify_ToolTips_ForTranscription();
		     
		     Thread.sleep(5000);
	         PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
	    	 Thread.sleep(2000);
	    	 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_review);
	         Thread.sleep(3000);
				
				
		     Thread.sleep(2000);
	         General.action().logoutMethod();
					
	    	// Trans login	
	        General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
	    		 
	        EditSubmission_ToVerify_ToolTips_ForTransAndReview();
	        
	        
	        assertion = Verify.action().verifyTextPresent("Clear the text (Alt + X)", 5);
    		if (assertion == false) {
    			report("f","Assertion failed while verifying tool tip for the delete icon");
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
	    		 
	    	EditSubmission_ToVerify_ToolTips_ForTransAndReview();
    	   
		}
	     

		catch (Exception e) {
				report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			}
			
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
	
	
	
	public void EditSubmission_ToVerify_ToolTips_ForTransAndReview()throws Exception {
		
		System.out.println("Inside Methode EditSubmission_ToVerify_ToolTips_ForTransAndReview() ");
		
		
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
		  General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
		  Thread.sleep(3000);
		 
		  General.action().waitforelemenetpresent(CommonLocartors.Locator().ongoing_multiOption_icon);
          Thread.sleep(2000);
          General.action().Click(CommonLocartors.Locator().ongoing_multiOption_icon);
          Thread.sleep(4000);
         
         assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_find_and_replace, 5);
	    	if(assertion==false){
	    	report("f","Assertion failed while verifying Find and Replace ToolTip ");
	    	}else {
	   	    	System.out.println("ToolTip For find and replace Is Present ");
	   	    }
	    		
	    assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_keyboard_shortcuts, 5);
	   	    if(assertion==false){
	   	    report("f","Assertion failed while verifying Keyboard Shortcut ToolTip ");
	   	    
	   	    }else {
	   	    	System.out.println("ToolTip For keyboard shortcuts Is Present ");
	   	    }
	   	    
	   	 Thread.sleep(3000);  
	   	 General.action().waitforelemenetpresent(CommonLocartors.Locator().ongoing_find_and_replace);
         Thread.sleep(3000);
         General.action().Click(CommonLocartors.Locator().ongoing_find_and_replace);
         Thread.sleep(4000);
	   	 
         
         Thread.sleep(3000);  
	   	 General.action().waitforelemenetpresent(CommonLocartors.Locator().Ongoing_FindandReplace_Close_button);
         Thread.sleep(3000);
         General.action().Click(CommonLocartors.Locator().Ongoing_FindandReplace_Close_button);
         Thread.sleep(4000);
	   	    
     	//TODO FUNCTIONALITY CHANGE FROM ITERATION 2.2.0 RC1
//		 assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().Ongoing_actionButtons_Tooltip(Bold_Tooltip), 5);
//    		if(assertion==false){
//    		report("f","Assertion failed while verifying Bold ToolTip ");
//    		}else {
//	   	    	System.out.println("ToolTip For Bold Is Present ");
//	   	    }
    		
    		General.action().mouseOver(CommonLocartors.Locator().Text_Formatting_Bold);
    		Thread.sleep(5000);
    		assertion = Verify.action().verifyTextPresent("Bold (Ctrl + B)", 5);
    		if (assertion == false) {
    			report("f","Assertion failed while verifying tool tip for the delete icon");
    		}
    		//TODO FUNCTIONALITY CHANGE FROM ITERATION 2.2.0 RC1
//    	assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().Ongoing_actionButtons_Tooltip(Italic_Tooltip), 5);
//	    	if(assertion==false){
//	    	report("f","Assertion failed while verifying Italic ToolTip ");
//	    	}else {
//	   	    	System.out.println("ToolTip For Italic Is Present ");
//	   	    }
    		
    		
    		General.action().mouseOver(CommonLocartors.Locator().Text_Formatting_Italic);
    		Thread.sleep(5000);
    		assertion = Verify.action().verifyTextPresent("Italic (Ctrl + I)", 5);
    		if (assertion == false) {
    			report("f","Assertion failed while verifying tool tip for the delete icon");
    		}
    		//TODO FUNCTIONALITY CHANGE FROM ITERATION 2.2.0 RC1	
//	    assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().Ongoing_actionButtons_Tooltip(Underline_Tooltip), 5);
//	   	    if(assertion==false){
//	   	    report("f","Assertion failed while verifying Underline ToolTip ");
//	   	    }else {
//	   	    	System.out.println("ToolTip For Underline Is Present ");
//	   	    }
//	   	    	
    		General.action().mouseOver(CommonLocartors.Locator().Text_Formatting_Underline);
    		Thread.sleep(5000);
    		assertion = Verify.action().verifyTextPresent("Underline (Ctrl + U)", 5);
    		if (assertion == false) {
    			report("f","Assertion failed while verifying tool tip for the delete icon");
    		}
    		
    		//TODO FUNCTIONALITY CHANGE FROM ITERATION 2.2.0 RC1 		
//	   	assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().Ongoing_actionButtons_Tooltip(CancelCurrentEdit_Tooltip), 5);
//	   	    if(assertion==false){
//	   	    report("f","Assertion failed while verifying Cancel Current Edit ToolTip ");
//	   	    }else { 
//	   	    	System.out.println("ToolTip For Cancel Current Edit Is Present ");
//	   	    }
    		
    		
    		General.action().mouseOver(ReviewerLocators.Locator().reviewer_cancel_current_edit);
    		Thread.sleep(5000);
    		assertion = Verify.action().verifyTextPresent("Cancel current edit (Esc)", 5);
    		if (assertion == false) {
    			report("f","Assertion failed while verifying tool tip for the delete icon");
    		}
    		//TODO FUNCTIONALITY CHANGE FROM ITERATION 2.2.0 RC1 		
//	   	assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().Ongoing_actionButtons_Tooltip(ClearTheText_Tooltip), 5);
//	   	    if(assertion==false){
//	   	    report("f","Assertion failed while verifying Clear The Text ToolTip ");
//	   	    }else {
//	   	    	System.out.println("ToolTip For Clear The Text Is Present ");
//	   	    }
    		
    		
    		General.action().mouseOver(ReviewerLocators.Locator().reviewer_delete_icon);
    		Thread.sleep(5000);
    		assertion = Verify.action().verifyTextPresent("Clear the text (Alt + X)", 5);
    		if (assertion == false) {
    			report("f","Assertion failed while verifying tool tip for the delete icon");
    		}
	   	    
	   	 System.out.println(" EOM EditSubmission_ToVerify_ToolTips_ForTransAndReview() ");
	   	    
		
	}
	
	
	public void EditSubmission_ToVerify_ToolTips_ForTranscription()throws Exception {
		
		System.out.println("Inside Methode EditSubmission_ToVerify_ToolTips_ForTranscription() ");
		
		     Thread.sleep(2000);
	         PM_user.action().Navigate(menu_to_claim);
	         Thread.sleep(2000);
	         PM_user.action().PM_ToClaim(SubmissionName);
	         Thread.sleep(2000);
	         PM_user.action().Navigate(menu_ongoing);
		     Thread.sleep(2000);
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
	     	//TODO FUNCTIONALITY CHANGE FROM ITERATION 2.2.0 RC1
	         //TODO NOT WORKING
//	         assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_Transcription_Bold_Tooltip, 5);
//	    		if(assertion==false){
//	    		report("f","Assertion failed while verifying Bold ToolTip");
//	    		}else {
//		   	    	System.out.println("ToolTip For Bold Is Present ");
//		   	    }
	         
	         
	        General.action().mouseOver(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_BoldFormat_button);
	 		Thread.sleep(5000);
	 		assertion = Verify.action().verifyTextPresent("Ctrl + B", 5);
	 		if (assertion == false) {
	 			report("f","Assertion failed while verifying tool tip for the delete icon");
	 		}
	 		
	 		//TODO FUNCTIONALITY CHANGE FROM ITERATION 2.2.0 RC1
	 		 //TODO NOT WORKING	
//	    	 assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_Transcription_Italic_Tooltip, 5);
//		    	if(assertion==false){
//		    	report("f","Assertion failed while verifying Italic ToolTip");
//		    	}else {
//		   	    	System.out.println("ToolTip For Italic Is Present ");
//		   	    }
	 		
	 	    General.action().mouseOver(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_ItalicFormat_button);
	 		 		Thread.sleep(5000);
	 		 		assertion = Verify.action().verifyTextPresent("Ctrl + I", 5);
	 		 		if (assertion == false) {
	 		 			report("f","Assertion failed while verifying tool tip for the delete icon");
	 		 		}
	 		 	//TODO FUNCTIONALITY CHANGE FROM ITERATION 2.2.0 RC1
	 		 	 //TODO NOT WORKING		
//		     assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_Transcription_Underline_Tooltip, 5);
//		   	    if(assertion==false){
//		   	    report("f","Assertion failed while verifying Underline ToolTip ");
//		   	    }else {
//		   	    	System.out.println("ToolTip For Underline Is Present ");
//		   	    }
	 		 		
	 		 	General.action().mouseOver(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Styling_UnderLineFormat_button);
	 		 	Thread.sleep(5000);
	 		 	assertion = Verify.action().verifyTextPresent("Ctrl + U", 5);
	 		 	if (assertion == false) {
	 		 			report("f","Assertion failed while verifying tool tip for the delete icon");
	 		 	}	
		   	    	
		   	    
		   	 Thread.sleep(3000);  
		   	 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_button);
	         Thread.sleep(3000);
	         General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_button);
	         Thread.sleep(4000);
	         
	         assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_QualityCheck_RUN_button, 5);
		   	 if(assertion==false){
		   	    report("f","Assertion failed while verifying Run Button Icon ");
		   	    
		   	 }else {
		   	    	System.out.println(" Run Icon Is Present ");
		   	 }
		   	 Thread.sleep(2000);
		   	 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
	         Thread.sleep(2000);
	         General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
	         Thread.sleep(4000);
	         
	         assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace, 5);
		    	if(assertion==false){
		    	report("f","Assertion failed while verifying Find and Replace ToolTip ");
		    	}else {
		   	    	System.out.println("ToolTip For find and replace Is Present ");
		   	    }
		    		
		    assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_keyboard_shortcuts, 5);
		   	    if(assertion==false){
		   	    report("f","Assertion failed while verifying Keyboard Shortcut ToolTip ");
		   	    
		   	    }else {
		   	    	System.out.println("ToolTip For keyboard shortcuts Is Present ");
		   	    }
		   	    
		   	 Thread.sleep(3000);  
		   	 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace);
	         Thread.sleep(3000);
	         General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_find_and_replace);
	         Thread.sleep(4000);
		   	 
	         
	         Thread.sleep(3000);  
		   	 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_Close_button);
	         Thread.sleep(3000);
	         General.action().Click(Pm_User_Submission_Locators.Locator().PM_Ongoing_FindandReplace_Close_button);
	         Thread.sleep(4000);
	         
	         System.out.println("EOM EditSubmission_ToVerify_ToolTips_ForTranscription() ");
		   	
		    }
	
	
	
	public void assertion() throws Exception {

		assertion = Verify.action().verifyTextPresent("Clear the text (Alt + X)", 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying tool tip for the delete icon");
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
