package subtitler.project_manager.Open_Jobs;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;
import modules.admin;



/**
 * 
 * @author pvaidya
 *
 */
public class Sub_776220 {
	
Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 String SubmissionName1 = "SUB_776220"+CommonElements.BROWSER+"B3";
	 String SubmissionName2 = "SUB_776220"+CommonElements.BROWSER+"B4";
	 String sourcelanguage = "en-US - English (United States)";
     String OrganizationName = "Subtitle_Common_orgs";
	 String WorkflowName1 = "Two_Step_Workflow";
	 String WorkflowName2 = "transc_Step_Workflow";
	 String fileDirName = "common";
	 String menu_Submission = "Submissions";
	 String TranslatorGroupName = "Common_group";
	 String ReviewGroupName = "Common_group";
 	 String filePath=System.getProperty("user.dir")+"\\data\\Submission\\";
	String menu_ToClaim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String text ="Test";
	String TranscriptionScrean="Transcription";

	 
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_776220");
	dataSet.put("TL_test_case_description","SUB-776220:Implement Shift + F3 in a segment ");
	dataSet.put("TL_internal_testCase_ID", "776220");
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
				
				//Submission For Trans And Review 
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName1, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
				System.out.println("filePath------>"+filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_SRT_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName1, CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
                Thread.sleep(2000);
                PM_user.action().SearchSubmisson(SubmissionName1);
                
                assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName1), 5);
        		if(assertion==false){
        			report("f","Assertion failed while verifying SubmissionName after Search");
        		}
        		
        		//Submission For Transcription
        		PM_user.action().Navigate(menu_Submission);
        		PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName2, "trans", TranslatorGroupName, "review", "", false);
				System.out.println("filePath------>"+filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_TTML_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName2, CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
                Thread.sleep(2000);
                PM_user.action().SearchSubmisson(SubmissionName2);
                
                assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName2), 5);
        		if(assertion==false){
        			report("f","Assertion failed while verifying SubmissionName after Search");
        		}
        		
        		Thread.sleep(3000);
    			General.action().logoutMethod();
        		
        		General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
        		
        		Thread.sleep(2000);
        		SubmissionEdit(SubmissionName1);
        		
        		Thread.sleep(2000);
        		EditTextAreaMethodForTransAndReview();
        		
        		Thread.sleep(2000);
        		TextFormatForTransAndReview();
        		
        		Thread.sleep(2000);
        		CompleteTaskForTransAndReview();
        		
        		Thread.sleep(2000);
    			General.action().logoutMethod();
				
				
    			General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
    			
    			Thread.sleep(2000);
        		SubmissionEdit(SubmissionName1);
        		
        		Thread.sleep(2000);
        		EditTextAreaMethodForTransAndReview();
        		
        		Thread.sleep(2000);
        		TextFormatForTransAndReview();
        		
        		Thread.sleep(2000);
        		CompleteTaskForTransAndReview();
    			
    			Thread.sleep(3000);
    			General.action().logoutMethod();
        		
        		
        		
    		    General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
    			
                Thread.sleep(2000);
        		SubmissionEdit(SubmissionName2);
        		
        		Thread.sleep(2000);
        		EditTextAreaMethodForPM_Transcription();
        		
        		Thread.sleep(2000);
        		TextFormatForTranscription();
        		
        		}
			catch (Exception e){
				  report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			  }
			
	}
	
	
	public void TextFormatForTransAndReview ()throws Exception {
		
		System.out.println("INSIDE method Text Format()\n"); 
		
		 
	    General.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(2));
	    Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
	    Thread.sleep(3000);
	    General.action().type(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1),Keys.chord(Keys.CONTROL,"a"));
	    
	       Thread.sleep(5000);
	       Robot robot = new Robot(); 
		   robot.keyPress(KeyEvent.VK_SHIFT);  
	       Thread.sleep(5000);  	
	       robot.keyPress(KeyEvent.VK_F3);	
	       Thread.sleep(5000);	
	       robot.keyRelease(KeyEvent.VK_SHIFT);	
	       Thread.sleep(5000);	
	       robot.keyRelease(KeyEvent.VK_F3);
	       Thread.sleep(5000);
	    
	    
	    assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_Ongoing_Edit_TextFormat("Test"), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Text After Shift + f3");
		}
	    
	    Thread.sleep(5000);
	    
	    General.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(2));
	    Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
	    Thread.sleep(3000);
	    General.action().type(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1),Keys.chord(Keys.CONTROL,"a"));
	 
	   Thread.sleep(5000);
	   robot.keyPress(KeyEvent.VK_SHIFT);  
       Thread.sleep(5000);  	
       robot.keyPress(KeyEvent.VK_F3);	
       Thread.sleep(5000);	
       robot.keyRelease(KeyEvent.VK_SHIFT);	
       Thread.sleep(5000);	
       robot.keyRelease(KeyEvent.VK_F3);	
	   
       Thread.sleep(7000);
       
       assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_Ongoing_Edit_TextFormat("TEST"), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Text After Shift + f3");
		}
			
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(2));
	    Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
		Thread.sleep(3000);
	    General.action().type(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1),Keys.chord(Keys.CONTROL,"a"));	
        Thread.sleep(5000);
        
        robot.keyPress(KeyEvent.VK_SHIFT);  
        Thread.sleep(4000);  	
        robot.keyPress(KeyEvent.VK_F3);	
        Thread.sleep(4000);	
        robot.keyRelease(KeyEvent.VK_SHIFT);	
        Thread.sleep(4000);	
        robot.keyRelease(KeyEvent.VK_F3);	
        
        System.out.println(" EOM Text Format()\n"); 
        
        assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_Ongoing_Edit_TextFormat("test"), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Text After Shift + f3");
		}
	    
	}
public void TextFormatForTranscription ()throws Exception {
		
		System.out.println("INSIDE method Text Format()\n"); 
		
		 
	    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
	    Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
	    Thread.sleep(3000);
	    General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.CONTROL,"a"));
	    
	       Thread.sleep(4000);
	       Robot robot = new Robot(); 
		   robot.keyPress(KeyEvent.VK_SHIFT);  
	       Thread.sleep(3000);  	
	       robot.keyPress(KeyEvent.VK_F3);	
	       Thread.sleep(4000);	
	       robot.keyRelease(KeyEvent.VK_SHIFT);	
	       Thread.sleep(4000);	
	       robot.keyRelease(KeyEvent.VK_F3);
	       Thread.sleep(4000);
	    
	    
	    assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_Ongoing_Edit_TextFormat("Test"), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Text After Shift + f3");
		}
	    
	    Thread.sleep(4000);
	    
	    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
	    Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
	    Thread.sleep(2000);
	    General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.CONTROL,"a"));
	  
	   Thread.sleep(4000);
	   robot.keyPress(KeyEvent.VK_SHIFT);  
       Thread.sleep(4000);  	
       robot.keyPress(KeyEvent.VK_F3);	
       Thread.sleep(4000);	
       robot.keyRelease(KeyEvent.VK_SHIFT);	
       Thread.sleep(4000);	
       robot.keyRelease(KeyEvent.VK_F3);	
	   
       Thread.sleep(6000);
       
       assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_Ongoing_Edit_TextFormat("TEST"), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Text After Shift + f3");
		}
	    
			
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
	    Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
	    General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.CONTROL,"a"));	
        Thread.sleep(3000);
        
        robot.keyPress(KeyEvent.VK_SHIFT);  
        Thread.sleep(4000);  	
        robot.keyPress(KeyEvent.VK_F3);	
        Thread.sleep(4000);	
        robot.keyRelease(KeyEvent.VK_SHIFT);	
        Thread.sleep(4000);	
        robot.keyRelease(KeyEvent.VK_F3);	
        
        System.out.println(" EOM Text Format()\n"); 
	    
	}
	
	public void SubmissionEdit(String sumbmissionName) throws Exception {
		
		System.out.println("INSIDE method SubmissionEdit()\n"); 
		
		Thread.sleep(2000);
		Translator.action().Navigate(menu_ToClaim);
		Thread.sleep(2000);
		Translator.action().trans_ToClaim(sumbmissionName);
		Thread.sleep(2000);
        Translator.action().Navigate(menu_ongoing);
		Thread.sleep(1000);
		PM_user.action().SearchSubmisson_andCheck(sumbmissionName);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
		Thread.sleep(2000);
		
		System.out.println(" EOM SubmissionEdit()\n"); 
	}
	
	
	public void EditTextAreaMethodForTransAndReview() throws InterruptedException {
		
		System.out.println("INSIDE method Edit Text Area Method For Trans And Review()\n"); 
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
	    Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
		Thread.sleep(3000);
		admin.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
		Thread.sleep(1000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1),text);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
	    Thread.sleep(2000);
	    General.action().type(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1),Keys.chord(Keys.CONTROL,"a"));
		
	    System.out.println("EOM Edit Text Area Method For Trans And Review()\n");
	}
	
	public void EditTextAreaMethodForPM_Transcription() throws InterruptedException {
		
		System.out.println("INSIDE method Edit Text Area Method For PM_Transcription()\n"); 
		
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(3000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(3000);
		admin.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(1000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,text);
		
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
	    Thread.sleep(2000);
	    General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Keys.chord(Keys.CONTROL,"a"));
		
	    System.out.println("EOM Edit Text Area Method For PM_Transcription()\n"); 
		
	}
	
	
	
	
	public void CompleteTaskForTransAndReview() throws InterruptedException {
		
		System.out.println("INSIDE method Complete Task For Trans And Review()\n"); 
		

	    PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
        Thread.sleep(1000);
        PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
	    Thread.sleep(1000);

        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_review);
        Thread.sleep(1000);
        PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_review);
        Thread.sleep(3000);
        Thread.sleep(6000);
        
        System.out.println("EOM Complete Task For Trans And Review()\n");
		
		
	}
 

	
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Submission_Ongoing_Edit_TextFormat("test"), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Text After Shift + f3");
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
