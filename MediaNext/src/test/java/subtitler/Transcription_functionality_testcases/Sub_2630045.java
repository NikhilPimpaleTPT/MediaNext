package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.RobotExt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This tc is to verify Characters should be populated for each line
 *
 */

public class Sub_2630045 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_2630045"+CommonElements.BROWSER+"A2";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String path;
    String [] charactersFromTTML= {"Amb_baby","lolo"};
    Boolean assertion = true;
    
    @BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_2630045");
		dataSet.put("TL_test_case_description","Sub_2630045: Transcription - Characters should be populated for each line");
		dataSet.put("TL_internal_testCase_ID", "2630045");
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
			PM_user.action().Navigate(menu_submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
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
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			PM_transcription_onGoing_Submission(SubmissionName,targetlanguage);
		    Thread.sleep(2000);
		    
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void PM_transcription_onGoing_Submission(String SubmissionName, String target) throws Exception {

		System.out.println("INSIDE PM_transcription_onGoing_ReadingSpeed_Submission()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
        Thread.sleep(8000);
        
        General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_multiOption_icon);
		Thread.sleep(2000);
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file);
		Thread.sleep(4000);
		
		File afile=new File(CommonElements.action().FILE_ABSOLUTE_PATH+"2630045\\");
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
    	 	Thread.sleep(3000);
    	 	
    	 	
    	 	System.out.println("INVALID PATH-------->"+path);
 
    	 	
    	 	//TODO THIS IMPL IS DUE TO AS FILE CANNOT BE IMPORTED PROPERLY IN FIREFOX THROUGH senKeys(), so FOR FIREFOX WE ARE USING ROBOT CLASS AND FOR CHROME WE ARE USING sendKeys()
        	System.out.println("WHTS IS THIS---->"+CommonElements.BROWSER);
        	if(CommonElements.BROWSER.contains("FIREFOX")) {
	        	System.out.println("------THIS IS FIREFOX-----");
	    		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file);
	    		Thread.sleep(3000);
	    		RobotExt.robot().delay(2000);
	    		RobotExt.robot().sendKeys(path);
	    		RobotExt.robot().processFilePath();
	    		RobotExt.robot().delay(2000);
        	}else {
            	System.out.println("------THIS IS CHROME-----");
        		General.action().type(Pm_User_Submission_Locators.Locator().PM_Ongoing_Import_file_input,path);
        		
        		
        	}
        	
        	Thread.sleep(5000);
        	if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_ongoing_overwrite_current_translation,10)) {
        		General.action().Click(Pm_User_Submission_Locators.Locator().PM_ongoing_overwrite_current_translation);
        		Thread.sleep(20000);
        	}
        	
        	General.action().Enter_keyEnvents(KeyEvent.VK_ENTER);
        	Thread.sleep(4000);
        	General.action().driver.navigate().refresh();
        	Thread.sleep(4000);
        	PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
    		Thread.sleep(2000);
    		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
    		Thread.sleep(2000);
    		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
    		Thread.sleep(2000);
            
            General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_button);
    		Thread.sleep(2000);
            General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_Characters_button);
        	Thread.sleep(5000);
        	assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_AddedCharactersRow(1,charactersFromTTML[0]), 5);
    		if (assertion == false) {
    			report("f","Assertion failed while verifying added charater1 in row");
    		}
    		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_AddedCharactersRow(2,charactersFromTTML[1]), 5);
    		if (assertion == false) {
    			report("f","Assertion failed while verifying added charater2 in row");
    		}
    		
    		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Options_Selected(3,charactersFromTTML[0]), 5);
    		if (assertion == false) {
    		report("f","Assertion failed while verifying character should be added for that segment");
    		}
    		
    		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Options_Selected(1,charactersFromTTML[1]), 5);
    		if (assertion == false) {
    		report("f","Assertion failed while verifying character should be added for that segment");
    		}
    		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Options_Selected(2,charactersFromTTML[1]), 5);
    		if (assertion == false) {
    		report("f","Assertion failed while verifying character should be added for that segment");
    		}
        
        
	}

	public void assertion() throws Exception {
		
		//Verify the character selection per line should be populated and reflect the selection from the TTML file.
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Characters_Options_Selected(2,charactersFromTTML[1]), 5);
		if (assertion == false) {
		report("f","Assertion failed while verifying character should be added for that segment");
		}
		else {
			report("p", "Assertion is passed.");
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