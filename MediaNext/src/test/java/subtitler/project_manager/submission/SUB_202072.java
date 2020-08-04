package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;
import modules.admin;

public class SUB_202072 {
	
Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	
	 String organization="Org_202072"+CommonElements.BROWSER+"D3";
	 String SubmissionName1 = "SUB_202072_1"+CommonElements.BROWSER+"D3";
	 String SubmissionName2 = "SUB_202072_2"+CommonElements.BROWSER+"D3";
	 String SubmissionName3 = "SUB_202072_3"+CommonElements.BROWSER+"D3";
	 String SubmissionName2_udated = "SUB_202072_2_updated"+CommonElements.BROWSER+"D3";
	 String sourcelanguage = "en-US - English (United States)";
	 String targetlanguage = "de-DE - German (Germany)";
     String OrganizationName = "Subtitle_Common_orgs";
	 String WorkflowName = "One_Step_Workflow";
	 String fileDirName = "common";
	 String menu_Submission = "Submissions";
     String TranslatorGroupName="Common_group";
     String menu_to_claim = "To Claim";
 	 String menu_ongoing = "Ongoing";
     String Organization_CommonOrg_User[]={"Admin"};
 	 String filePath=System.getProperty("user.dir")+"\\data\\Submission\\";
 	 String StreamURL="Stream Url: https://cdn01.tptgms.com/vods3/_definst";//(Only verified the part of string as discussed becoused complete string always changes).
 	 String DueDateMonth;
 	 String DueDateDay;
 	 String DueDateYear;
 	 
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_202072");
	dataSet.put("TL_test_case_description","SUB-202072:Create Submission with TTML file");
	dataSet.put("TL_internal_testCase_ID", "202072");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				
				//Create ad org and add only admin user to the organization (TC Step No : 5(SUB-1317))
				
				General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
				//Navigate To Organization	
				admin.action().Navigate("Clients");
				Thread.sleep(1000);	
		        //Create a Organization
				admin.action().CreateOrganization_Step1(organization, "organization_dec");
				admin.action().CreateOrganization_AddParentOrg("TransPerfect");
				//TODO below mention single line code is not required.
				//admin.action().CreateOrganization_AddWorkflow(WorkflowName);
				admin.action().CreateOrganization_AddUser(Organization_CommonOrg_User);
				admin.action().CreateOrganization_last();
				
				General.action().logoutMethod();
				
				General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
				PM_user.action().Navigate(menu_Submission);
				//TODO NEW IMPL OF SUBMISSION CREATION
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
				System.out.println("filePath------>"+filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_TTML_FOLDER);
				String dueDate=CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName1, CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
                Thread.sleep(2000);
                System.out.println("Submission Due Date Is-->"+dueDate);
                DueDateMonth=dueDate.substring(0, 3);
                DueDateDay=dueDate.substring(5, 7);//(5,7) or(6,7) Will WORK DEPENDS ON DATE DIGIT
                DueDateYear=dueDate.substring(8, 13);
                String SubmissionDueDate=DueDateMonth+" "+DueDateDay+","+DueDateYear;
                System.out.println("Submission Due Date Is-->"+SubmissionDueDate);
                PM_user.action().SearchSubmisson(SubmissionName1);
                Thread.sleep(2000);
                
                //Verify that due date in the grid is same as the one selecting when creating the submission.
                assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName1,SubmissionDueDate), 5);
    			if(assertion==false){
    				report("f","Assertion failed while verifying SubmissionName Due Date is same as due date of submission created.");
    			}
                
                PM_user.action().SearchSubmisson_andCheck(SubmissionName1);
                Thread.sleep(6000);
    			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
    			Thread.sleep(6000);
    			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
    			Thread.sleep(1000);
    			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
    			Thread.sleep(1000);
    			 
    			PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
    			Thread.sleep(1000);
    		    PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,"40");
    			Thread.sleep(2000);
    			
    			PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input);
    			Thread.sleep(1000);
    			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input);
    			Thread.sleep(1000);
    			 
    			PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input);
    			Thread.sleep(1000);
    		    PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input,"4");
    			Thread.sleep(2000);
    			
    			PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_settings_updateButton);			
    			Thread.sleep(500);
    			 
    			assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_settings_updateMessage, 5);
     			if(assertion==false){
     				report("f","Assertion failed while verifying update message");
     			}
     			Thread.sleep(4000);
     			
     			//Verify Edit the settings of the created submission (SUB-1596)
     			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input).getAttribute("value").contains("40");
    			if (assertion == false) {
    			report("f","Assertion failed while verifying udated settings");
    	        }
    			 
    			assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_transcription_editSubmission_MaxLinesPerSub_input).getAttribute("value").contains("4");
    			if (assertion == false) {
    			report("f","Assertion failed while verifying udated settings");
    	        }
    			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_openButton_VideoDetails);
    			Thread.sleep(4000);
    			assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_submission_openButtonVideoDetails_StreamURL(StreamURL), 5);
    			if(assertion==false){
    				report("f","Assertion failed while verifying SubmissionName after Search");
    			}
    			
    			
    			PM_user.action().Navigate(menu_Submission);
				//TODO NEW IMPL OF SUBMISSION CREATION
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
				System.out.println("filePath------>"+filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_SUB_TTML_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName2, CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
                Thread.sleep(2000);
                PM_user.action().SearchSubmisson(SubmissionName2);
                Thread.sleep(2000);
                
                //Verify that due date in the grid is same as the one selecting when creating the submission.
                assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().check_sub_with_dueDate(SubmissionName2,SubmissionDueDate), 5);
    			if(assertion==false){
    				report("f","Assertion failed while verifying SubmissionName using TTML file given in the ticket");
    			}
    			
    			PM_user.action().Navigate(menu_Submission);
    			Thread.sleep(2000);
    			PM_user.action().SearchSubmisson_andCheck(SubmissionName2);
                Thread.sleep(6000);
                
                Thread.sleep(1000);
        		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
                Thread.sleep(10000);
                General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Submission_editSubmissionName_input);
    			Thread.sleep(20000);
    			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_editSubmissionName_input);
    			Thread.sleep(20000);
    			General.action().type(Pm_User_Submission_Locators.Locator().PM_Submission_editSubmissionName_input,SubmissionName2_udated);
    			Thread.sleep(2000);
    			General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_editSubmissionName_updateButton);
    			Thread.sleep(6000);
    			System.out.println();
    			
    			assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Submission_editSubmissionName_input).getAttribute("value").contains(SubmissionName2_udated);
    			if (assertion == false) {
    				report("f","Assertion failed while verifying SOURCE SEGMENTS from ttml file");
    			}
    			
    			//Step No 8
    			PM_user.action().Navigate(menu_Submission);
				//TODO NEW IMPL OF SUBMISSION CREATION
				PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans", TranslatorGroupName, "review", "", false);
				System.out.println("filePath------>"+filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+CommonElements.action().FILE_COMMON_XML_FOLDER);
				CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName2+"_New", CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
                
				
				assertion =Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_error("Error analyzing captions, no captions imported. Please check your files."), 5);
    			if (assertion == false) {
    				report("f","Assertion failed while verifying SOURCE SEGMENTS from ttml file");
    			}
    			General.action().logoutMethod();
    			
    			
    		 //trans login
   	         General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
   	         Thread.sleep(1000);
   	         Translator.action().Navigate(menu_to_claim);
   	         Thread.sleep(1000);
   	         Translator.action().trans_ToClaim(SubmissionName2);
   	         Thread.sleep(1000);
   	         Translator.action().Navigate(menu_ongoing);
   	         Thread.sleep(1000);
   	         Thread.sleep(2000);
   	         Translator.action().SearchSubmisson_andCheck(SubmissionName2);
             Thread.sleep(6000);
   	       
			Thread.sleep(1000);
			Translator.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
			Thread.sleep(4000);
			
			
			assertion =Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SourceSegement(1,"BEGIN Netflix Subtitle and SDH Sample"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SOURCE SEGMENTS from ttml file");
			}
			assertion =Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_SourceSegement(4,"Max 42 Character Width Test"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SOURCE SEGMENTS from ttml file");
			}
			
			
			General.action().logoutMethod();
			
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_Submission);
			//TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    	Thread.sleep(2000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    	Thread.sleep(2000);
	    	
	    	//Verify that only those organization to which PM user is assigned are visible under organization drop down
	    	assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName), 5);
			if(assertion==false){
				report("f","Assertion failed while verifying organization name for added users.");
			}
	    
			//in the org only admin user is added (Not PM user and veryfing step no 5)
			assertion=Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(organization), 5);
			if(assertion==false){
				report("f","Assertion failed while verifying organization name for added users.");
			}
			
    			
    			
    			
			}
			catch (Exception e){
				  report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			  }
			
	}

	
	public void assertion() throws Exception {
		//in the org only admin user is added (Not PM user and veryfing step no 5)
		assertion=Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(organization), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying organization name for added users.");
		}else{
		    report("p", "All Assertion passed.");
		}
	}
	
	
	   public void CreateSubmisson_Step2_OrganizationAndWorkflow(String OrganizationName,String WorkflowName,String transStep,String TransGroupName,String reviewStep,String ReviewGroupName,Boolean Review) throws Exception {
	    	System.out.println("INSIDE method CreateSubmisson_Step2_OrganizationAndWorkflow()\n"); 
	    	Thread.sleep(General.action().defaultWaitPeriod*2);
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    	Thread.sleep(2000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
			Thread.sleep(1000);
			
			//Verify Select Organization, group and test workflow in steps 2. Complete the other details and create submission.
			assertion=Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown("Subtitle_Common_orgs"), 5);
			if(assertion==false){
				report("f","Assertion failed while verifying SubmissionName after Search");
			}
			assertion=Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown(organization), 5);
			if(assertion==false){
				report("f","Assertion failed while verifying SubmissionName after Search");
			}
			
			Thread.sleep(1000);
			General.action().Enter_keyEnvents(KeyEvent.VK_ESCAPE);
			Thread.sleep(1000);
			
	    	General.action().Dropdownwithoutselect_with_javaScript(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	    	
	    	Thread.sleep(General.action().defaultWaitPeriod*10);
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown);
	    	Thread.sleep(2000);
	    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown,Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown(WorkflowName));
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep));
	    	Thread.sleep(2000);
	    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_trans(TransGroupName));
	    	Thread.sleep(2000);
	    	
	    	if(Review){
	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep));
	    		Thread.sleep(2000);
	    		General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_review(ReviewGroupName));
		    	Thread.sleep(2000);
	    	}
	    	
	    	Thread.sleep(2000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
			Thread.sleep(1000);
	    	
   	    System.out.println("EOM CreateSubmisson_Step2_OrganizationAndWorkflow()\n");
	    }
	
	  public String CreateSubmisson_Step4_MetaData(String DueDate,String SubmissionName,String SourceLanguage,String TargetLanguage) throws Exception {
	    	System.out.println("INSIDE method CreateSubmisson_Step4_MetaData()\n"); 
	    	//TODO NOT REQUIRED
//	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
//	    	Thread.sleep(1000);
//	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
//	    	Thread.sleep(1000);
	    	
	    	System.out.println("DATE IMPLEMENTATION");
	    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
			String newdate = GetDataPlus(Integer.valueOf(DueDate));
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
//	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
//	    	Thread.sleep(1000);
	    	System.out.println("CLICKED");
//	    	((JavascriptExecutor) General.driver).executeScript(
//	                "arguments[0].scrollIntoView();", General.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage).toString()));
	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage));
	    	Thread.sleep(3000);
	    	if(TargetLanguage!=""){
	    		System.out.println("TARGET LANGUAGE NOT NULL");
	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
		    	Thread.sleep(1000);
		    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input, TargetLanguage);
		    	Thread.sleep(1000);
		    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(TargetLanguage));
		    	Thread.sleep(4000);
	    	}
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
	    	Thread.sleep(1000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
	    	Thread.sleep(1000);
	    	//TODO NOT REQUIRED
//	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
//	    	Thread.sleep(1000);
  	    System.out.println("EOM CreateSubmisson_Step4_MetaData()\n");
		return newdate;
	    }
	  
	  
	  
	    
	
	  public static String GetDataPlus(Integer Days) throws Exception {
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, Days);
			DateFormat dateFormat = new SimpleDateFormat(". dd, yyyy HH:mm", Locale.getDefault());
			DateFormat dateFormat2 = new SimpleDateFormat("MMMM");
			String monthParsed = dateFormat2.format(cal.getTime());
			return (monthParsed.substring(0, 3) + dateFormat.format(cal.getTime()));
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
