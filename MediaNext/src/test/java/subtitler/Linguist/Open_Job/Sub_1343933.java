package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.RobotExt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: Import by timecode after split/merge of segments.
 *Preconditions:Create a submission from PM credentials.
 */ 


public class Sub_1343933 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1343933"+CommonElements.BROWSER+"X1";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String menu_completed = "Completed";
	String path;
	File filepath1;
	String downloadedSrtFileName;
	String downloadedFileName;
	String DownloadedfileName;
	String downloadPath_withoutExtension;
	String TargetSegmentText_chrome[]= {"Two of Kenya's most iconic\n" + 
			"animals are under attack.","Twee iconische dieren\n" + 
			"uit Kenia worden aangevallen."};
	String TargetSegmentText_segment1[]= {"Two of Kenya's most iconic","animals are under attack."};
	String TargetSegmentText_segment2[]= {"Twee iconische dieren","uit Kenia","worden aangevallen."};
	
	boolean Finalresult1=true;
    
    Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1343933");
		dataSet.put("TL_test_case_description", "SUB-1343933:Import by timecode after split/merge of segments.");
		dataSet.put("TL_internal_testCase_ID", "1343933");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			//Create Submission
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_1343933_Source);
			PM_user.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, Targetlanguage);
			Thread.sleep(2000);
		
			//SEARCH SUBMISSION AND CHECK
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			Thread.sleep(2000);
			
			General.action().logoutMethod();
			Thread.sleep(2000);
			
			// trans login
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Translator.action().trans_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Thread.sleep(1000);
		    translate_onGoing_submission(SubmissionName,targetlanguage,false,false);
		    Thread.sleep(2000);
		    
		    
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void translate_onGoing_submission(String SubmissionName,String target,boolean complete ,boolean back) throws Exception {

		System.out.println("INSIDE Trans_Ongoing_Import_translation_InvalidFile  method()");

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_search_input, SubmissionName);
		Thread.sleep(2000);
		
		if(Verify.action().VerifyElementPresent(TranslatorLocators.Locator().check_trans_Target_Lang(SubmissionName, target)))
		{
			System.out.println("INSIDE IF--------");
			General.action().Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
		Thread.sleep(10000);
		
		
		
		  Thread.sleep(5000);
	      List <WebElement> listofIds1= General.action().driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
		  Thread.sleep(1000);
		  System.out.println("No of IDS--------"+listofIds1.size());
		  Thread.sleep(3000);
		  
	    for(int i=1;i<=listofIds1.size();i++){
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
			Thread.sleep(1000);
		
	    }
	    
	   // General.action().scrollup(5);
	    Thread.sleep(1000);
	    General.action().driver.navigate().refresh();
	    Thread.sleep(5000);
	    String timeInOfFristSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText();
	    System.out.println(timeInOfFristSegment);
	    Thread.sleep(1000);
	    String timeOutOfFristSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(1)).getText();
	    System.out.println(timeOutOfFristSegment);
	    Thread.sleep(1000);
	    String targetSegmentOfFristSegment=General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText();
	    System.out.println(targetSegmentOfFristSegment);
	    Thread.sleep(1000);
	    String timeInOfSecondSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(2)).getText();
	    System.out.println(timeInOfSecondSegment);
	    Thread.sleep(1000);
	    String timeOutOfSecondSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(2)).getText();
	    System.out.println(timeOutOfSecondSegment);
	    Thread.sleep(1000);
	    
	    //Click on any segment and split segment
	    General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		Thread.sleep(1000);
	  	General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		Thread.sleep(1000);
	  	if(CommonElements.BROWSER.contains("CHROME")) {
	  	General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_P);
	  	Thread.sleep(1000);
	  	}else {
	  		
	  	Translator.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(2),Keys.chord(Keys.ALT, "P"));
		Thread.sleep(1000);
			
	  		
	  	}
	
		//No of IDS of created submission.
		General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
	    Thread.sleep(1000); 
	    List<WebElement> listofIdsOfCreatedSubmission = General.driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
	    Thread.sleep(1000);
	    System.out.println("No of IDS of Created Submission--------" + listofIdsOfCreatedSubmission.size());
		      
		General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
	    Thread.sleep(1000);
	    
	    //Import File
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_Ongoing_Import_file);

		//Verify that importing a file.SRT (SRT in capital letters) would not return any error message.
		File afile=new File(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_1343933_Import);
		File[] listOfFiles={afile};
    	if (afile.isDirectory())    
    	    listOfFiles = afile.listFiles();
    	   
    	   Thread.sleep(3000);
    	      
    	   //Process array
    	    for (int i = 0; i < listOfFiles.length; i++)
    	    { 
    	     if(listOfFiles[i].isDirectory()) 
    	    	 continue;
    	    
    	      path = listOfFiles[i].getAbsolutePath();
    	     
    	    }
    	 	Thread.sleep(1000);

		//Trans_Ongoing_Import_file_input    	 
    	System.out.println("VALID PATH-------->"+path);
    	System.out.println("WHTS IS THIS---->"+CommonElements.BROWSER);
    	
	 	//TODO THIS IMPL IS DUE TO AS FILE CANNOT BE IMPORTED PROPERLY IN FIREFOX THROUGH senKeys(), so FOR FIREFOX WE ARE USING ROBOT CLASS AND FOR CHROME WE ARE USING sendKeys()
    	if(CommonElements.BROWSER.contains("FIREFOX")) {
	    	System.out.println("------THIS IS FIREFOX-----");
    		//General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
	    	Thread.sleep(1000);
	    	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
        	Thread.sleep(3000);
        	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByTimecode);
			Thread.sleep(3000);
			RobotExt.robot().delay(2000);
			RobotExt.robot().sendKeys(path);
			RobotExt.robot().processFilePath();
			RobotExt.robot().delay(2000);
			Thread.sleep(4000);
    	}else {
        	System.out.println("------THIS IS CHROME-----");
        	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
        	Thread.sleep(3000);
        	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByTimecode);
			Thread.sleep(3000);
			RobotExt.robot().delay(2000);
			RobotExt.robot().sendKeys(path);
			RobotExt.robot().processFilePath();
			RobotExt.robot().delay(2000);
			Thread.sleep(4000);
    	}
    	Thread.sleep(8000);
		
		//Verify if the timecode of imported segment matches with timecode of existing segment then it is imported for that segment.
		assertion = General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText().contains(timeInOfFristSegment);
		if (assertion == false) {
			report("f","Assertion failed while verifying time in after improting file ");
		}
		Thread.sleep(1000);
		assertion = General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(1)).getText().contains(timeOutOfFristSegment);
		if (assertion == false) {
			report("f","Assertion failed while verifying time out after improting file");
		}
		Thread.sleep(1000);
		assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText().contains(targetSegmentOfFristSegment);
		if (assertion == false) {
			report("f","Assertion failed while verifying 1st target segment");
		}
		Thread.sleep(1000);
		assertion = !General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText().contains("You have people\n" + 
				"who are targeting");
		if (assertion == false) {
			report("f","Assertion failed while verifying target segment");
		}
		  Thread.sleep(1000);
		//Verify if the timecode of imported segment does not match with any of the segment in the submission then that segment is imported as new subtitle with timecode from the translation file.
		assertion = !General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(2)).getText().contains(timeInOfSecondSegment);
		if (assertion == false) {
			report("f","Assertion failed while verifying time in of second segment is not as previous after importing file");
		}
		 Thread.sleep(1000);
		assertion = !General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(2)).getText().contains(timeOutOfSecondSegment);
		if (assertion == false) {
			report("f","Assertion failed while verifying time  out of second segment is not as previous after importing file");
		}
		 Thread.sleep(1000);
		assertion = General.driver.findElement(TranslatorLocators.Locator().translator_Sourcesegement_textarea(2)).getText().isEmpty();
		if (assertion == false) {
			report("f","Assertion failed while verifying source segment empty");
		}
		
		//TODO sometimes ff verify test with \r\n for two lone and sometime only for \n
		//TODO THIS LOGIC OF VERYFING 3 LINE TEXT CHANGED FROM 1.26.0 SNPASHOT AS TEXT IS PRESENT DIFFERENT //i 
		  Thread.sleep(1000);
		if(CommonElements.BROWSER.contains("CHROME")) {
		System.out.println(General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(2)).getText());
		for(int i=0;i<=2;i++) {
		assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(2)).getText().contains(TargetSegmentText_segment2[i]);
		if (assertion == false) {
			report("f","Assertion failed while verifying target segment");
		}
		}
		}else{
		System.out.println(General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(2)).getText());	
		for(int i=0;i<=2;i++) {
		assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(2)).getText().contains(TargetSegmentText_segment2[i]);//TargetSegmentText_ff
		if (assertion == false) {
				report("f","Assertion failed while verifying target segment");
		}
		}
			
		}
		  Thread.sleep(1000);
		//TODO CHANGED LOGIC AS PER FOR 1.26.0 SNAP
		if(CommonElements.BROWSER.contains("CHROME")) {
			
		assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText().contains(TargetSegmentText_segment1[0]);
		if (assertion == false) {
				report("f","Assertion failed while verifying target segment");
		}
		}else{
				
		assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText().contains(TargetSegmentText_segment1[1]);//TargetSegmentText_ff
		if (assertion == false) {
		report("f","Assertion failed while verifying target segment");
		}
				
		}
		  Thread.sleep(1000);
	    
	    
	}
	
	public void Trans_editText_alt_p_m(int event1,int event2) throws Exception {
		  
		  System.out.println("******Trans_editText_alt_p()************");
		  
		Robot robot = new Robot();
		robot.keyPress(event1);
        Thread.sleep(2000);
        robot.keyPress(event2);
        Thread.sleep(2000);
		robot.keyRelease(event2);
        Thread.sleep(2000);
        robot.keyRelease(event1);
        Thread.sleep(2000);
		    
		    System.out.println("******Trans_editText_alt_p()************");
	  }
	
	public void assertion() throws Exception {
		assertion = General.driver.findElement(TranslatorLocators.Locator().translator_Sourcesegement_textarea(2)).getText().isEmpty();
		if (assertion == false) {
			report("f","Assertion failed while verifying source segment empty");
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
