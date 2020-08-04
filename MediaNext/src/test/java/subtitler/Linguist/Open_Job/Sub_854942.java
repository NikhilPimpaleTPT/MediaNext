package subtitler.Linguist.Open_Job;

/**
 * 
 * @author pvaidya
 *
 */
import static org.testng.AssertJUnit.assertTrue;

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
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

public class Sub_854942 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName1 = "SUB_854942_1"+CommonElements.BROWSER+"Y1";
	String SubmissionName2 = "SUB_854942_2"+CommonElements.BROWSER+"Y1";
    String OrganizationName = "Subtitle_Common_orgs";
    String WorkflowName1 = "Three_Step_Transc_Workflow";
	String WorkflowName2 = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String DownloadedfileName;
	String TextWithSpacesAtBeginningAndEnd ="  JellyfishattheMontereyAquarium.  "+"\n" +" ";
	String TextWithoutSpacesAtBeginningAndEnd;
	String path;
	String IMPORT_SRTFile_WithSpacesAndReturn="854942";
	String Source_textarea;
	String Target_textarea;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_854942");
		dataSet.put("TL_test_case_description", "Sub_854942 : Auto. remove extra return/spaces detected at the beginning /end of a segment.");
		dataSet.put("TL_internal_testCase_ID", "854942");
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
			
			// TODO NEW IMPL OF SUBMISSION CREATION For Translator , Review And Transcription.(For Scenario 1)
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName1,"transcription",transcGroupName,"Trans",TranslatorGroupName,true, "Review", ReviewGroupName, true);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName1,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName1);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName1), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION For Translator.(For Scenario 2)
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName2, "trans",TranslatorGroupName, "review", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName2,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName2);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName2), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
	        Thread.sleep(2000);
			
			//========= Scenario 1 ==========
			//FOR TRANSCRIPTION
		    Edit_Transcription_segemnt_WithSpaces_AndVerify();
			
			CompleteTaskForTransAndReviewAndTranscription();
			
		    Thread.sleep(2000);
    		General.action().logoutMethod();
    		
    		// Translator login	
    		General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
    		
    		Edit_TransAndReview_segemnt_WithSpaces_AndVerify();
    				    
			CompleteTaskForTransAndReviewAndTranscription();
			 
			Thread.sleep(2000);
    	    General.action().logoutMethod();
    	   
			// Review LogIn 
		    General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
			 
			Edit_TransAndReview_segemnt_WithSpaces_AndVerify();		
	 		    
	 		CompleteTaskForTransAndReviewAndTranscription();
	 		   
	 		Thread.sleep(2000);
	    	General.action().logoutMethod();
	    	    
	    	    
	    	//========= Scenario 2 ==========
	    	    
	    	 // Trans login	
	    	General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
	    		
	    		
	    	Thread.sleep(2000);
		    Translator.action().Navigate(menu_to_claim);
		    Thread.sleep(2000);
		    Translator.action().trans_ToClaim(SubmissionName2);
		    Thread.sleep(2000);
		    Translator.action().Navigate(menu_ongoing);
		    Thread.sleep(2000);
		  
			Trans_Ongoing_Import_translation_File(SubmissionName2,targetlanguage_1);
			
			Thread.sleep(5000);
			//TODO CLICK ON SUBMISISON NAME HEADER OR IT WILL FAIL
			General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
	        Thread.sleep(2000);
			 
	        TextWithoutSpacesAtBeginningAndEnd=General.driver.findElement(CommonLocartors.Locator().EditSegment_textarea(1)).getText();
	         
	        System.out.println("Text After Clicking on next Segment and again previous Segmaent for Trans and Review Segments"+TextWithoutSpacesAtBeginningAndEnd );
	         
			
			
			 
		}
			catch (Exception e){
				  report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
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
	
	
	public void Edit_TransAndReview_segemnt_WithSpaces_AndVerify() throws Exception {
		
		System.out.println("Inside Method Edit_TransAndReview_segemnt_WithSpaces_AndVerify()");
		
		 Thread.sleep(1000);
         Translator.action().Navigate(menu_to_claim);
         Thread.sleep(1000);
         Translator.action().trans_ToClaim(SubmissionName1);
         Thread.sleep(1000);
         Translator.action().Navigate(menu_ongoing);
         Thread.sleep(1000);
		 
		 PM_user.action().SearchSubmisson_andCheck(SubmissionName1);
	     General.action().waitforelemenetpresent(CommonLocartors.Locator().Submission_Edit_icon);
	     Thread.sleep(2000);
	     General.action().Click(CommonLocartors.Locator().Submission_Edit_icon);
	     Thread.sleep(4000);
		 
		 General.action().waitforelemenetpresent(CommonLocartors.Locator().EditSegment_textarea(1));
         Thread.sleep(1000);
	     General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
	     Thread.sleep(3000);
	     General.action().ClearInputvalues(CommonLocartors.Locator().EditSegment_textarea(1));
	     Thread.sleep(1000);
	     PM_user.action().type(CommonLocartors.Locator().EditSegment_textarea(1),TextWithSpacesAtBeginningAndEnd);
	     Thread.sleep(2000);
	     General.action().Click(CommonLocartors.Locator().EditSegment_textarea(2));
         Thread.sleep(2000);
		 General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
         Thread.sleep(2000);
		 
         TextWithoutSpacesAtBeginningAndEnd=General.driver.findElement(CommonLocartors.Locator().EditSegment_textarea(1)).getText();
         
         System.out.println("Text After Clicking on next Segment and again previous Segmaent for Trans and Review Segments"+TextWithoutSpacesAtBeginningAndEnd );
         
 		System.out.println("TextWithoutSpacesAtBeginningAndEnd--->"+TextWithoutSpacesAtBeginningAndEnd+"********");
		 
 		assertion = TextWithoutSpacesAtBeginningAndEnd.contains("  ");
		    if (assertion == true) {
				report("f"," String Is Same, String Contains Extra Spaces and Extra Return");
			}else {
				System.out.println( "String Is Not Same, String Not Contains Any Extra Spaces and Extra Return");
			}
		    
         
         
         System.out.println(" EOM Edit_TransAndReview_segemnt_WithSpaces_AndVerify()");
		
		
	}
	
	
	public void Edit_Transcription_segemnt_WithSpaces_AndVerify() throws Exception {
		
		
		System.out.println("Inside Method Edit_Transcription_segemnt_WithSpaces_AndVerify()");
		
		
		 Thread.sleep(2000);
		 PM_user.action().Navigate(menu_to_claim);
         Thread.sleep(2000);
         PM_user.action().PM_ToClaim(SubmissionName1);
         Thread.sleep(3000);
         PM_user.action().Navigate(menu_ongoing);
         Thread.sleep(3000);
		 
		 PM_user.action().SearchSubmisson_andCheck(SubmissionName1);
		 General.action().waitforelemenetpresent(CommonLocartors.Locator().Submission_Edit_icon);
		 Thread.sleep(2000);
		 General.action().Click(CommonLocartors.Locator().Submission_Edit_icon);
		 Thread.sleep(10000);
			
		 
		 
	 	 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
	     Thread.sleep(1000);
	     General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
	     Thread.sleep(3000);
	     General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
	     Thread.sleep(1000);
	     General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
	     Thread.sleep(3000);
	     General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
	     Thread.sleep(1000);
	     PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,TextWithSpacesAtBeginningAndEnd);
		 Thread.sleep(1000);
	     General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
	     Thread.sleep(3000);
		 Thread.sleep(1000);
	     General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
	     Thread.sleep(3000);
	     General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
	     Thread.sleep(3000);
		
	     TextWithoutSpacesAtBeginningAndEnd=General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea).getText();
	     
	     
	     System.out.println("Text After Clicking on next Segment and again previous Segmaent for TransCription Segment "+TextWithoutSpacesAtBeginningAndEnd );
	     
		    System.out.println("TextWithoutSpacesAtBeginningAndEnd--->"+TextWithoutSpacesAtBeginningAndEnd+"********");

		    assertion = TextWithoutSpacesAtBeginningAndEnd.contains("  ");
		    if (assertion == true) {
				report("f"," String Is Same, String Contains Extra Spaces and Extra Return");
			}else {
				System.out.println( "String Is Not Same, String Not Contains Any Extra Spaces and Extra Return");
			}
	     
	     System.out.println("EOM Edit_Transcription_segemnt_WithSpaces_AndVerify()");
	}
	
	
public void CompleteTaskForTransAndReviewAndTranscription() throws InterruptedException {
		
		System.out.println("INSIDE method CComplete Task For Trans , Review And Transcription()\n"); 
		

	    PM_user.action().waitforelemenetpresent(CommonLocartors.Locator().SubmissionEdit_complete_task);
        Thread.sleep(1000);
        PM_user.action().Click(CommonLocartors.Locator().SubmissionEdit_complete_task);
	    Thread.sleep(1000);

        PM_user.action().waitforelemenetpresent(CommonLocartors.Locator().SubmissionEdit_complete_Task_Alert_button);
        Thread.sleep(1000);
        PM_user.action().Click(CommonLocartors.Locator().SubmissionEdit_complete_Task_Alert_button);
        Thread.sleep(6000);
        
        System.out.println("EOM Complete Task For Trans , Review And Transcription()\n");
		
		
	}
		

public void Trans_Ongoing_Import_translation_File(String SubmissionName,String target)throws Exception {

	System.out.println("INSIDE Trans_Ongoing_Import_translation_File  method()");

	General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
	Thread.sleep(2000);
	General.action().Click(TranslatorLocators.Locator().translator_search_input);
	Thread.sleep(2000);
	General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
	Thread.sleep(2000);
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
	      
	
	General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
	Thread.sleep(1000);
	General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
	Thread.sleep(1000);
	General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_Ongoing_Import_file);

	File afile=new File(CommonElements.action().FILE_ABSOLUTE_PATH+ IMPORT_SRTFile_WithSpacesAndReturn);
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
		//General.action().type(TranslatorLocators.Locator().Trans_Ongoing_Import_file_input,path);

		General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
    	Thread.sleep(3000);
    	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
		Thread.sleep(3000);
		RobotExt.robot().delay(2000);
		RobotExt.robot().sendKeys(path);
		RobotExt.robot().processFilePath();
		RobotExt.robot().delay(2000);
		Thread.sleep(4000);
    	
		Thread.sleep(8000);
	}
	Thread.sleep(8000);
	
	if(Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_ongoing_overwrite_current_translation, 10)) {
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_overwrite_current_translation);
		Thread.sleep(20000);
	}
	
	System.out.println("EOM Trans_Ongoing_Import_translation_File()");
}



			
			 
			 public void assertion() throws Exception {
				 assertion = TextWithoutSpacesAtBeginningAndEnd.contains("  ");
				    if (assertion == true) {
						report("f"," String Is Same, String Contains Extra Spaces and Extra Return");
					}else {
						report("p", "String Is NOT Same , Not Contains Extra Spaces and Extra Return(String Without Spaces and Extra Return)");
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