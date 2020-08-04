package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Admin_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;
import modules.admin;

public class Sub_184765 {
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String menu_Workflows = "Workflows";
	
	String WorkflowName = "Test Workflow"+CommonElements.BROWSER+"E1";
    String SubmissionName = "Submission_184765"+CommonElements.BROWSER+"E1";
    String VideoFile ="jellies.mp4";
    String SourceFile ="jellies.srt";
	String OrganizationName = "Subtitle_Common_orgs";
	String StepName = "trans";
	String StepFromDropdown = "Translation";
	String menu_submissions = "Submissions";
	String sourcelanguage = "en-US - English (United States)";
	String targetlanguage = "de-DE - German (Germany)";
	String targetlanguage_1 = "German (Germany)";
	String fileName = "common";
	String TranslatorGroupName = "Common_group";
    String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String trans_Target_max_chars_length = "jfytjjmmjmjmjmjmjmjmjmjmjmjmjmjmjmjmjmjr";
	String trans_Target_exceed_chars_length = "jytjjmmjmjmjmjmjmjmjmjmjmjmjmjmjmjmjmjmfr";
	String hex_red;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_184765");
		dataSet.put("TL_test_case_description","SUB-184765:Open submission created with test workflow");
		dataSet.put("TL_internal_testCase_ID", "184765");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
 			// login admin
			General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			admin.action().Navigate(menu_Workflows);
			admin.action().CreateWorkflow_Step1(WorkflowName);
			admin.action().CreateWorkflow_AddOrganization(OrganizationName);
			admin.action().CreateWorkflow_AddSteps(1,StepName, StepFromDropdown);
			admin.action().CreateWorkflow();
			Thread.sleep(3000);
			admin.action().Navigate(menu_Workflows);
			Thread.sleep(2000);
			admin.action().SearchWorkflow(WorkflowName);


			assertion = Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().admin_user_SearchResult_worklfowName(WorkflowName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			// logout admin
			Thread.sleep(3000);
			General.action().logoutMethod();

			// login PM
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			Thread.sleep(3000);
			PM_user.action().Navigate(menu_submissions);
			Thread.sleep(2000);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "40", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			//PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,true,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			
//			//TODO NEW IMPL
//			// To Click On Cancel button
////			Thread.sleep(2000);
////			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_uploadVideo_crossButton);
////			Thread.sleep(3000);
////			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_uploadSource_crossButton);
////			Thread.sleep(3000);
//			
//			List<WebElement> crossbtns = General.driver.findElements(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_Video_crossButton);
//			crossbtns.get(0).click();
//			Thread.sleep(1000);
//			crossbtns.get(1).click();
//			Thread.sleep(1000);
//			
//			// To Verify VideoFile No More Present 
//			assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_videoUpload_filePresent(VideoFile), 5);
//			if (assertion == false) {
//				report("f",	"Assertion failed while verifying VideoFile ");		}
//			
//			
//			//To Verify SourceFile No More Present 
//			assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_filePresent(SourceFile), 5);
//			if (assertion == false) {
//				report("f",	"Assertion failed while verifying SourceFile ");
//			}
//			
//			
//			// To Clock on VideoFile foR Video Dialog Box
//			Thread.sleep(3000);
//			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_videoUpload);
//			Thread.sleep(3000);			
//			// 	To click on cancel 
//			cancelFile();			
//			// To Clock on VideoFile for Source Dialog Box
//			Thread.sleep(3000);
//			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload);
//			Thread.sleep(3000);
//           // To click on cancel 
//			cancelFile();
//			
////			System.out.println("Upload New SRT File");
////			Thread.sleep(7000);
//			CreateSubmisson_Step3_videoFile_New(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER_SECOND);
			CreateSubmisson_Step3_VideoSRTFile_New(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER_SECOND);
			
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
            PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f",	"Assertion failed while verifying SubmissionName  after Search");
			}
			// logout PM
			Thread.sleep(3000);
			General.action().logoutMethod();

			// trans login
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			
	        General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().translator_search_input);
			Thread.sleep(2000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
			Thread.sleep(2000);
			General.action().type(TranslatorLocators.Locator().translator_search_input,SubmissionName);
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName,targetlanguage_1));
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
			Thread.sleep(2000);
			
			//It will check IF Statement
			translate_onGoing_submission_check_MaxChar(SubmissionName,targetlanguage_1, 40);
			Thread.sleep(1000);
			//It will check ELSE Statement
			translate_onGoing_submission_check_MaxChar(SubmissionName,targetlanguage_1, 41);
			Thread.sleep(1000);

		} catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
		
		}
	
	public void CreateSubmisson_Step3_VideoSRTFile(String FilePath) throws Exception {
    	System.out.println("INSIDE method CreateSubmisson_Step3_VideoSRTFile()\n"); 
//    	
    	String path = null;
    	File afile=new File(FilePath);
		File[] listOfFiles={afile};
    	if (afile.isDirectory())    
    	    listOfFiles = afile.listFiles();
    	   
    	   Thread.sleep(General.action().defaultWaitPeriod*2);
    	      
    	   //Process array
    	    for (int i = 0; i < listOfFiles.length; i++)
    	    { 
    	     if(listOfFiles[i].isDirectory()) 
    	    	 continue;
    	    
    	      path = listOfFiles[i].getAbsolutePath();
    	     
    	    }
    	 	Thread.sleep(1000);
    	 	
	    	System.out.println("path---->"+path);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input);
	    	Thread.sleep(1000);
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input,path);
	    	Thread.sleep(1000);

	    	
			
    	    System.out.println("EOM CreateSubmisson_Step3_VideoSRTFile()\n");

    }
	
	
	public void CreateSubmisson_Step3_videoFile_New(String FilePath) throws Exception{
    	System.out.println("INSIDE method CreateSubmisson_Step2_videoFile()\n_newVideoFile");
    	Thread.sleep(1000);
//    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button);
//    	Thread.sleep(1000);
    	String path = null;
    	File afile=new File(FilePath);
		File[] listOfFiles={afile};
    	if (afile.isDirectory())    
    	    listOfFiles = afile.listFiles();
    	   
    	   Thread.sleep(General.action().defaultWaitPeriod*2);
    	      
    	   //Process array
    	    for (int i = 0; i < listOfFiles.length; i++)
    	    { 
    	     if(listOfFiles[i].isDirectory()) 
    	    	 continue;
    	    
    	      path = listOfFiles[i].getAbsolutePath();
    	     
    	    }
    	 	Thread.sleep(1000);

	    	//TODO NEW IMPL
	    	System.out.println("path---->"+path);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_videoUpload_input);
	    	Thread.sleep(1000);
	    	
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_videoUpload_input,path);
	    	Thread.sleep(1000);
	    	
	    	//TODO

	    	System.out.println("Ho gya bhai");
	    	//driver.switchTo().activeElement().sendKeys(path);
	    	//General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button).sendKeys(path);
	    	//type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button, path);    
	    	
	    	 System.out.println("EOM CreateSubmisson_Step2_videoFile()\n_newVideoFile");
    	    } 
	
	 public void CreateSubmisson_Step3_VideoSRTFile_New(String FilePath) throws Exception {
	    	System.out.println("INSIDE method CreateSubmisson_Step3_VideoSRTFile()\n"); 
//	    	
	    	
	    	//TODO NEW IMPLEMENTAION AS PER 1.6.0RC1
	    	
	    	String path = null;
	    	File afile=new File(FilePath);
			File[] listOfFiles={afile};
	    	if (afile.isDirectory())    
	    	    listOfFiles = afile.listFiles();
	    	   
	    	   Thread.sleep(General.action().defaultWaitPeriod*2);
	    	      
	    	   //Process array
	    	    for (int i = 0; i < listOfFiles.length; i++)
	    	    { 
	    	     if(listOfFiles[i].isDirectory()) 
	    	    	 continue;
	    	    
	    	      path = listOfFiles[i].getAbsolutePath();
	    	     
	    	    }
	    	 	Thread.sleep(1000);
	    	 	
		    	System.out.println("path---->"+path);
		    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input);
		    	Thread.sleep(1000);
		    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input,path);
		    	Thread.sleep(1000);
		    	
		    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
				Thread.sleep(1000);

		    	
				
	    	    System.out.println("EOM CreateSubmisson_Step3_VideoSRTFile()\n");

	    }
	 
	 public void cancelFile() throws Exception{
		 
		 System.out.println("******INSIDE METHOD CanelFile************");
		  
		    Robot robot = new Robot();  
         robot.keyPress(KeyEvent.VK_TAB);  // press arrow down key of keyboard to navigate and select Save radio button	
         Thread.sleep(2000);  	
         robot.keyPress(KeyEvent.VK_TAB);	
         Thread.sleep(2000);	
         robot.keyPress(KeyEvent.VK_TAB);	
         Thread.sleep(2000);	
         robot.keyPress(KeyEvent.VK_ENTER);	
		    Thread.sleep(5000);
		    
		    System.out.println("******END OF METHOD CanelFile ************");
	 }
	    
	
	 
	 

	public void translate_onGoing_submission_check_MaxChar(String SubmissionName, String target, int maxChar) throws Exception {
		
		 System.out.println("INSIDE translate_onGoing_submission_check_MaxChar  method()");
         System.out.println("maxchar:-" + maxChar);

		if(maxChar <= 40) {
			System.out.println("***************INSIDE IF*********************");
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),trans_Target_max_chars_length.trim());
			// verify 40
			Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_ongoing_sub_target_Chars_Length(40), 5);
			// get color value
			String color = General.driver.findElement(TranslatorLocators.Locator().trans_ongoing_sub_target_Chars_Length(40)).getCssValue("color");
			System.out.println("Chars color value:-" + color);
			String hex_wht = Color.fromString(color).asHex();
			System.out.println("Chars color value:-" +hex_wht);
			
			// verify assertion for color
			 assertion = hex_wht.equalsIgnoreCase("#FFFFFF");
			 if (assertion == false) {
				report("f","Assertion failed while verifying Workflow Name after Search	");
			}
		}

		else {
			System.out.println("***************INSIDE ELSE****************");
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(2),trans_Target_exceed_chars_length.trim());
			Thread.sleep(1000);
			// verify 41
			Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_ongoing_sub_target_exceed_Chars_Length(41), 5);
			// get color value
			String color_red = General.driver.findElement(TranslatorLocators.Locator().trans_ongoing_sub_target_exceed_Chars_Length(41)).getCssValue("color");
			System.out.println("Chars color:-" + color_red);
			 hex_red = Color.fromString(color_red).asHex();
			System.out.println(hex_red);
			
			// verify assertion for color
			 assertion = hex_red.equalsIgnoreCase("#ff4f64");
		   if (assertion == false) {
				report("f","Assertion failed while verifying Workflow Name after Search	");
		}

		}
		
    Thread.sleep(3000);

	}

	public void assertion() throws Exception {
		// This assertion will check exceed char limit

		assertion =  hex_red.equalsIgnoreCase("#ff4f64");
	    if (assertion == false) {
			report("f","Assertion failed while verifying Workflow Name after Search	");
		}else {
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
