package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.RobotExt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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

public class Sub_210106 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_210106"+CommonElements.BROWSER+"H5";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_AllJobs = "Jobs";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String path;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_210106");
		dataSet.put("TL_test_case_description", "Sub_210106 :Import translation file");
		dataSet.put("TL_internal_testCase_ID", "210106");
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
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", true);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_CAPITAL_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
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
			Trans_Ongoing_Import_translation_File(SubmissionName,targetlanguage_1);
		    Thread.sleep(3000);
			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Trans_Ongoing_Import_translation_InvalidFile(SubmissionName,targetlanguage_1);
			Thread.sleep(1000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}

	public void Trans_Ongoing_Import_translation_File(String SubmissionName,String target)throws Exception {

		System.out.println("INSIDE Trans_Ongoing_Import_translation_File  method()");

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
		
		//No of IDS of created submission.
		General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
	    Thread.sleep(1000); 
	    List<WebElement> listofIdsOfCreatedSubmission = General.driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
	    Thread.sleep(1000);
	    System.out.println("No of IDS of Created Submission--------" + listofIdsOfCreatedSubmission.size());
		      
		General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
	    Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_Ongoing_Import_file);

		//Verify that importing a file.SRT (SRT in capital letters) would not return any error message.
		File afile=new File(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_CAPITAL_FOLDER);
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
    	 	
    	 	//TODO NOT REQUIRED BY AUTOIT
//    		General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
//    		Thread.sleep(2000);
//			Runtime.getRuntime().exec(System.getProperty("user.dir")+ "\\data\\ImportFile\\AutoIT\\AutoIT.exe");
//			Thread.sleep(9000);
    	 	
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
        	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
			Thread.sleep(3000);
			RobotExt.robot().delay(2000);
			RobotExt.robot().sendKeys(path);
			RobotExt.robot().processFilePath();
			RobotExt.robot().delay(2000);
			Thread.sleep(4000);
    	}else {
        	System.out.println("------THIS IS CHROME-----");
//    		General.action().type(TranslatorLocators.Locator().Trans_Ongoing_Import_file_captionNumber_input,path);
//    		Thread.sleep(3000);
//    		General.action().type(TranslatorLocators.Locator().Trans_Ongoing_Import_file_captionNumber_input,path);
//    		Thread.sleep(3000);
    		//General.action().type(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber,path);
        	//Thread.sleep(7000);
//        	General.action().mouseOver(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
//        	Thread.sleep(1000);
        	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
        	Thread.sleep(3000);
        	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
			Thread.sleep(3000);
			RobotExt.robot().delay(2000);
			RobotExt.robot().sendKeys(path);
			RobotExt.robot().processFilePath();
			RobotExt.robot().delay(2000);
			Thread.sleep(4000);
    	}
    	Thread.sleep(8000);
    	//TODO NOT REQUIRED AS AT FIRST INSTANCE WE DONT GET OVERWRITE
//		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_overwrite_current_translation);
//		Thread.sleep(1000);
		if(Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_ongoing_overwrite_current_translation, 10)) {
			General.action().Click(TranslatorLocators.Locator().Trans_ongoing_overwrite_current_translation);
			Thread.sleep(20000);
		}

		//No of submission after importing file
		General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
	    Thread.sleep(1000); 
        List<WebElement> listofIdsAfterImport = General.driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
		Thread.sleep(1000);
		System.out.println("No of IDS after importing file--------" + listofIdsAfterImport.size());
		Thread.sleep(5000);
		//verify submission and import file has same number of subtitles/segments then file is imported
		 assertion=listofIdsOfCreatedSubmission.size()==listofIdsAfterImport.size();
		 if (assertion == false) {
				report("f","Assertion failed while verifying Submission id is not equal to importing file id");
			}
		
		
		
		for (int i = 1; i <= listofIdsAfterImport.size(); i++) {
			Thread.sleep(5000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_Sourcesegement_textarea(i));
			Thread.sleep(1000);

			String Source_textarea = General.driver.findElement(TranslatorLocators.Locator().translator_Sourcesegement_textarea(i)).getText();
			System.out.println("SOURCE TEXTAREA INPUT VALUE:- "+ Source_textarea.trim());

			String Target_textarea = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(i)).getText();
			System.out.println("TARGET TEXT INPUT VALUE:- " + Target_textarea.trim());

			boolean Compare_Source_to_Target_InputText = Source_textarea.trim().equalsIgnoreCase(Target_textarea.trim());
			System.out.println("TARGET INPUT TEXT IS SIMILAR TO SOURCE INPUT TEXT:- "+ Compare_Source_to_Target_InputText);

			//Verify that importing a file.SRT (SRT in capital letters) would not return any error message.
			assertion = Source_textarea.equals(Target_textarea);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}

		}
		Thread.sleep(5000);
		
		// To verify After overwrite it should be in same page
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_OpenSubmission_Text(SubmissionName),10);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
		}
        //TODO No Need Logo is hidden by default
		//if(Verify.action().verifyElementPresent(CommonLocartors.Locator().GlobalLink_logo_hidden, 5)){
		
		Thread.sleep(5000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
		Thread.sleep(5000);
        General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
		Thread.sleep(6000);
		//}
		
		System.out.println("EOM Trans_Ongoing_Import_translation_File()");
	}

	public void Trans_Ongoing_Import_translation_InvalidFile(String SubmissionName,String target) throws Exception {

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
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
		Thread.sleep(1000);

    	File afile=new File(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_IMPORT_SRT_FOLDER);
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
    	 	//TODO NOT REQUIRED
//    		General.action().type(TranslatorLocators.Locator().Trans_Ongoing_Import_file_input,path);
//    		Thread.sleep(2000);
        	//TODO NOT REQUIRED BY AUTOIT
//    		General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
//    		Thread.sleep(2000);
//		    Runtime.getRuntime().exec(System.getProperty("user.dir")+ "\\data\\InvalidImportFile\\AutoIT\\AutoIT.exe");
//		    Thread.sleep(3000);
    	 	
           //TODO THIS IMPL IS DUE TO AS FILE CANNOT BE IMPORTED PROPERLY IN FIREFOX THROUGH senKeys(), so FOR FIREFOX WE ARE USING ROBOT CLASS AND FOR CHROME WE ARE USING sendKeys()
        	System.out.println("WHTS IS THIS---->"+CommonElements.BROWSER);
        	if(CommonElements.BROWSER.contains("FIREFOX")) {
	        	System.out.println("------THIS IS FIREFOX-----");
	        	Thread.sleep(1000);
	    		//General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
	    		//Thread.sleep(3000);
	        	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
	        	Thread.sleep(3000);
	        	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
				Thread.sleep(3000);
	    		RobotExt.robot().delay(2000);
	    		RobotExt.robot().sendKeys(path);
	    		RobotExt.robot().processFilePath();
	    		RobotExt.robot().delay(2000);
        	}else {
            	System.out.println("------THIS IS CHROME-----");
        		//General.action().type(TranslatorLocators.Locator().Trans_Ongoing_Import_file_input,path);
        		//Thread.sleep(5000);
            	//General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
            	//Thread.sleep(3000);
            	//General.action().type(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber,path);
            	//General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
            	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
            	Thread.sleep(3000);
            	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
    			Thread.sleep(3000);
    			RobotExt.robot().delay(2000);
    			RobotExt.robot().sendKeys(path);
    			RobotExt.robot().processFilePath();
    			RobotExt.robot().delay(2000);
    			Thread.sleep(4000);
        	}

    	Thread.sleep(2000);
    	//TODO NOT REQUIRED AS AT FIRST INSTANCE WE DONT GET OVERWRITE
//		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_overwrite_current_translation);
//		Thread.sleep(2000);
		if(Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_ongoing_overwrite_current_translation, 10)) {
			General.action().Click(TranslatorLocators.Locator().Trans_ongoing_overwrite_current_translation);
			Thread.sleep(2000);
		}
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_InvalidImportFile_Error_message());

		
		// To verify After overwrite it should be in same page
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_OpenSubmission_Text(SubmissionName),10);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
		}

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_InvalidImportFile_Error_message());
		
         System.out.println("EOM Trans_Ongoing_Import_translation_InvalidFile()");
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_InvalidImportFile_Error_message(), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
			Thread.sleep(2000);
		} else {
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
