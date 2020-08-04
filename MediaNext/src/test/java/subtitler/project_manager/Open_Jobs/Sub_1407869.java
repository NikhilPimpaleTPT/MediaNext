package subtitler.project_manager.Open_Jobs;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.RobotExt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
 *Summary: Reading speed refactoring.
 *
 */ 

public class Sub_1407869 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1407869"+CommonElements.BROWSER+"OR1";
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
	String path;
	String duration_first_x;
	String duration_second_x;
	String duration_first_x_edited;
	String duration_third_x;
	String duration_x;
    Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1407869");
		dataSet.put("TL_test_case_description", "SUB-1407869:Reading speed refactoring.");
		dataSet.put("TL_internal_testCase_ID", "1407869");
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
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
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
		    translate_onGoing_submission(SubmissionName,targetlanguage);
		    Thread.sleep(2000);
			
			
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void translate_onGoing_submission(String SubmissionName,String target) throws Exception {

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
		   
		      
		General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
	    Thread.sleep(1000);
	    
	    //Import File
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_Ongoing_Import_file);

		//Verify that importing a file.SRT (SRT in capital letters) would not return any error message.
		File afile=new File(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SPLIT_AND_MERGED);
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
		
        	//Verify 4th source and target segment is empty and Verify that reading speed after import is showing correctly for merged and splitted segments.
        	assertion = General.driver.findElement(TranslatorLocators.Locator().translator_Sourcesegement_textarea(4)).getText().isEmpty();
			if (assertion == false) {
				report("f","Assertion failed while verifying source segment is empty");
			}
        	assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(4)).getText().isEmpty();
			if (assertion == false) {
				report("f","Assertion failed while verifying target segment is empty");
			}
			
			//Verify default reading speed
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_readingSpeed_value(4)).getText().contains("0.0");
			if (assertion == false) {
				report("f","Assertion failed while verifying default Reading speed");
			}
			
        	
        	assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_readingSpeed_value(1)).getText().contains("16.2");
			if (assertion == false) {
				report("f","Assertion failed while verifying default Reading speed");
			}
			
			//Scenario 2: In Translation and Review, after any changes (content change, time is, time out, calibration)
			float thirdSourceSegment =General.driver.findElement(TranslatorLocators.Locator().translator_Sourcesegement_textarea(3)).getText().length();
			System.out.println("third Segment length--->"+thirdSourceSegment);
			String thirdSegmentDuration=General.driver.findElement(TranslatorLocators.Locator().translator_duration(3)).getText();
			System.out.println("third Segment reading speed--->"+thirdSegmentDuration);
        	float thirdSegmentDuration_int=Float.parseFloat(thirdSegmentDuration);	
        	System.out.println("third Segment reading speed int--->"+thirdSegmentDuration_int);
        	
        	float edited_third_segmentDuration=thirdSourceSegment/thirdSegmentDuration_int;
        	System.out.println("Secound Source Segment duration int--->"+edited_third_segmentDuration);
        	
        	String duration_third =Float.toString(edited_third_segmentDuration);
        	duration_third_x=duration_third.substring(0,3);
        	System.out.println(duration_third_x);
			
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_readingSpeed_value(3)).getText().contains(duration_third_x);
			if (assertion == false) {
				report("f","Assertion failed while verifying default Reading speed of splited segment");
			}
			
			//Click on segment 2 and split the segment( Scenario 4: Split one segment into two.) and 
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			Thread.sleep(1000);
			
			for(int i=1;i<=4;i++) {
		  		Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_LEFT);
		        Thread.sleep(1000);
		        robot.keyRelease(KeyEvent.VK_LEFT);
		        Thread.sleep(1000);
		  		
		  	}
			
			Thread.sleep(1000);
			General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_P);
			Thread.sleep(1000);
			
			
			float SecondSourceSegment =General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(2)).getText().length();
			System.out.println("Secound Segment length--->"+SecondSourceSegment);
			String SecondSegmentDuration=General.driver.findElement(TranslatorLocators.Locator().translator_duration(2)).getText();
			System.out.println("Secound Segment Duration--->"+SecondSegmentDuration);
        	float SecondSegmentDuration_int=Float.parseFloat(SecondSegmentDuration);	
        	System.out.println("Secound Segment Duration int--->"+SecondSegmentDuration_int);
        	
        	float edited_Second_segmentReadingSpeed=SecondSourceSegment/SecondSegmentDuration_int;
        	System.out.println("Secound Source Segment duration int--->"+edited_Second_segmentReadingSpeed);
        	
        	String duration_Second =Float.toString(edited_Second_segmentReadingSpeed);
        	duration_second_x=duration_Second.substring(0,3);
        	System.out.println(duration_second_x);
			
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_readingSpeed_value(2)).getText().contains(duration_second_x);
			if (assertion == false) {
				report("f","Assertion failed while verifying default Reading speed of splited segment");
			}
			
			//Scenario 3: Merge 2 subtitles

			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			Thread.sleep(1000);
			General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_M);
			Thread.sleep(1000);
			
			 String legthOfSegment_L1=General.driver.findElement(TranslatorLocators.Locator().Trans_segmentLength_L_multiPleTLines(1)).getText();
			 String legthOfSegment_L1_numberOnly= legthOfSegment_L1.replaceAll("[^0-9]", "");
			 legthOfSegment_L1_numberOnly=legthOfSegment_L1_numberOnly.substring(1,3);
			 float legthOfSegment_L1_float=Float.parseFloat(legthOfSegment_L1_numberOnly);
			 System.out.println(legthOfSegment_L1_float);
			 
			 String legthOfSegment_L2=General.driver.findElement(TranslatorLocators.Locator().Trans_segmentLength_L_multiPleTLines(2)).getText();
			 String legthOfSegment_L2_numberOnly= legthOfSegment_L2.replaceAll("[^0-9]", "");
			 legthOfSegment_L2_numberOnly=legthOfSegment_L2_numberOnly.substring(1,3);
			 float legthOfSegment_L2_float=Float.parseFloat(legthOfSegment_L2_numberOnly);
			 System.out.println(legthOfSegment_L2_float);
			
			 float totalNumberOfCharacterInSegments=legthOfSegment_L1_float+legthOfSegment_L2_float+2-1;
			 System.out.println("Total length of the segments-->"+totalNumberOfCharacterInSegments);
			 
			float firstSourceSegment =General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText().length();
			System.out.println("first Segment length--->"+firstSourceSegment);
			String firstSegmentDuration=General.driver.findElement(TranslatorLocators.Locator().translator_duration(1)).getText();
			System.out.println("first Segment reading speed--->"+firstSegmentDuration);
        	float firstSegmentDuration_int=Float.parseFloat(firstSegmentDuration);	
        	System.out.println("first Segment reading speed int--->"+firstSegmentDuration_int);
        	
        	float edited_first_segmentDuration=totalNumberOfCharacterInSegments/firstSegmentDuration_int;
        	System.out.println("first Source Segment duration int--->"+edited_first_segmentDuration);
        	
        	String duration_first =Float.toString(edited_first_segmentDuration);
        	duration_first_x=duration_first.substring(0,3);
        	System.out.println(duration_first_x);
			
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_readingSpeed_value(1)).getText().contains("7");
			if (assertion == false) {
				report("f","Assertion failed while verifying default Reading speed of merge segment");
			}
			
			
			//Change segement and verify reading speed (Scenario 2: In Translation and Review, after any changes )
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),"Thanks for watching and I hope");
			Thread.sleep(5000);
			
			 String legthOfSegment_L1_edited=General.driver.findElement(TranslatorLocators.Locator().Trans_segmentLength_L_multiPleTLines(1)).getText();
			 String legthOfSegment_L1_numberOnly_edited= legthOfSegment_L1_edited.replaceAll("[^0-9]", "");
			 legthOfSegment_L1_numberOnly_edited=legthOfSegment_L1_numberOnly_edited.substring(1,3);
			 float legthOfSegment_L1_float_edited=Float.parseFloat(legthOfSegment_L1_numberOnly_edited);
			 System.out.println(legthOfSegment_L1_float_edited);
			 
//			 String legthOfSegment_L2_edited=General.driver.findElement(TranslatorLocators.Locator().Trans_segmentLength_L_multiPleTLines(2)).getText();
//			 String legthOfSegment_L2_numberOnly_edited= legthOfSegment_L2_edited.replaceAll("[^0-9]", "");
//			 legthOfSegment_L2_numberOnly_edited=legthOfSegment_L2_numberOnly_edited.substring(1,3);
//			 float legthOfSegment_L2_float_edited=Float.parseFloat(legthOfSegment_L2_numberOnly_edited);
//			 System.out.println(legthOfSegment_L2_float_edited);
			
			 float totalNumberOfCharacterInSegments_edited=legthOfSegment_L1_float_edited;
			 
			 System.out.println("Total length of the segments_edited-->"+totalNumberOfCharacterInSegments_edited);
			String firstSegmentDuration_edited=General.driver.findElement(TranslatorLocators.Locator().translator_duration(1)).getText();
			System.out.println("first Segment reading speed--->"+firstSegmentDuration_edited);
        	float firstSegmentDuration_edited_int=Float.parseFloat(firstSegmentDuration_edited);	
        	System.out.println("first Segment reading speed int--->"+firstSegmentDuration_edited_int);
        	
        	float edited_first_segmentDuration_edited=totalNumberOfCharacterInSegments_edited/firstSegmentDuration_edited_int;
        	System.out.println("first Source Segment duration int--->"+edited_first_segmentDuration_edited);
        	
        	String duration_first_edited =Float.toString(edited_first_segmentDuration_edited);
        	duration_first_x_edited=duration_first_edited.substring(0,3);
        	System.out.println(duration_first_x_edited);
			
			
			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_readingSpeed_value(1)).getText().contains(duration_first_x_edited);
			if (assertion == false) {
				report("f","Assertion failed while verifying reading speed after entering seegment");
			}
			
			
			//Scenario 2: In Translation and Review, after any changes(erify that if in translation view, target segment is empty, then should show the RS of the source and if the target gets at least one character, then calculate the RS for target.)
			assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(3)).getText().isEmpty();
			if (assertion == false) {
				report("f","Assertion failed while verifying target segment is empty");
			}
			
			
			thirdSourceSegment =General.driver.findElement(TranslatorLocators.Locator().translator_Sourcesegement_textarea(3)).getText().length();
			System.out.println("Secound Source Segment length--->"+thirdSourceSegment);
			thirdSegmentDuration=General.driver.findElement(TranslatorLocators.Locator().translator_duration(3)).getText();
			System.out.println("Secound Source Segment reading speed--->"+thirdSegmentDuration);
        	float fifthSegmentDuration_int=Float.parseFloat(thirdSegmentDuration);	
        	System.out.println("Secound Source Segment reading speed int--->"+fifthSegmentDuration_int);
        	
        	float edited_segmentDuration=thirdSourceSegment/fifthSegmentDuration_int;
        	System.out.println("Secound Source Segment duration int--->"+edited_segmentDuration);
        	
        	String duration =Float.toString(edited_segmentDuration);
        	duration_x=duration.substring(0,3);
        	System.out.println(duration_x);
        	assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_readingSpeed_value(3)).getText().contains(duration_x);
			if (assertion == false) {
				report("f","Assertion failed while verifying reading speed after entering seegment");
			}
        	
		
	

		
         System.out.println("EOM Trans_Ongoing_Import_translation_InvalidFile()");
	}
	
	
	
	

	public void assertion() throws Exception {
		assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_readingSpeed_value(3)).getText().contains(duration_x);
		if (assertion == false) {
			report("f","Assertion failed while verifying reading speed after entering seegment");
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

