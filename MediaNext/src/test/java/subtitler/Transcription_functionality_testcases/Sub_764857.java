package subtitler.Transcription_functionality_testcases;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.Color;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;
import modules.admin;

public class Sub_764857 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String WorkflowName_admin = "Trans+transc_764857"+ CommonElements.BROWSER + "A2";
	String GroupName="Common_group_764857"+ CommonElements.BROWSER + "A2";
	//String GroupName="Common_group";
	String organizationName="Common_org_764857"+ CommonElements.BROWSER + "A2";
	String SubmissionName = "SUB_764857" + CommonElements.BROWSER + "A2";

	
	String OrganizationName_admin = "TransPerfect";
	String StepName = "trans";
	String StepFromDropdown = "Translation";
	String StepName1 = "transc";
	String StepFromDropdown1 = "Transcription";
	String menu_workflow = "Workflows";
	String menu_Organizations = "Organizations";
	String Org_Sub = "Subtitle_Common_orgs";
    String WorkflowName_InOrg []= {WorkflowName_admin};

	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = WorkflowName_admin;
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String TranscriptionGroupName="Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String hex_Reading_speed_color_after;
	String Organization_CommonOrg_User[]={"Subtitler_PM",CommonElements.action().Translator_username_firstName,CommonElements.action().Reviwer_username_firstName,CommonElements.action().admin_username_firstName};


	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_764857");
		dataSet.put("TL_test_case_description","Sub_764857: Reading Speed Color code is not intuitive enough: Translation view. ");
		dataSet.put("TL_internal_testCase_ID", "764857");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
		try {

			// admin login to create trans+transcrpyn workflow
			admin.action().login(CommonElements.action().admin_username, CommonElements.action().admin_passowrd);
			Thread.sleep(20000);	
			//Navigate To Organization	
			admin.action().Navigate("Clients");
			Thread.sleep(1000);	
			//Create a Organization
			admin.action().CreateOrganization_Step1(organizationName, "Common Organization 764857");
			admin.action().CreateOrganization_AddParentOrg("TransPerfect");
			//TODO below mention single line code is not required.
			//admin.action().CreateOrganization_AddWorkflow(WorkflowName);
			admin.action().CreateOrganization_AddUser(Organization_CommonOrg_User);
			admin.action().CreateOrganization_last();
			
			
			//Navigate To Groups	
			admin.action().Navigate("Groups");

		
			//Create a Group		
			admin.action().CreateGroup_Step1(GroupName, "Common group for Subtitler Submission 764857");
			admin.action().CreateGroup_AddOrg(organizationName);
			admin.action().CreateGroup_AddUser(Organization_CommonOrg_User);
			admin.action().CreateGroup_last();
			
			

			admin.action().Navigate(menu_workflow);
			admin.action().CreateWorkflow_Step1(WorkflowName_admin);
			admin.action().CreateWorkflow_AddOrganization(organizationName);
			admin.action().CreateWorkflow_AddSteps(1,StepName, StepFromDropdown);
			admin.action().CreateWorkflow_AddSteps_multi(2,StepName1, StepFromDropdown1);
			admin.action().CreateWorkflow();
			Thread.sleep(1000);
			
			//TODO NOT NEEDED AS WE HAVE CREATED
//			admin.action().Navigate(menu_Organizations);
//			Thread.sleep(1000);
//			admin.action().SearchOrganization(Org_Sub);
//			Thread.sleep(1000);
//			admin.action().EditOrganization_open(Org_Sub);
//			Thread.sleep(2000);
//			admin.action().EditOrganization_Workflow(WorkflowName_InOrg);
//			Thread.sleep(2000);
//			admin.action().CreateOrganization_last();
//			Thread.sleep(2000);
			General.action().logoutMethod();

			// PM login
			General.action().login(CommonElements.action().PM_username, CommonElements.action().password);
			
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
//			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "transc",TranscriptionGroupName , true);
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(organizationName, WorkflowName, "trans",GroupName, "transc",GroupName , true);

			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_LARGE_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER + CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(1000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			Pm_Transcription_Ongoing_Update_ReadingSpeed_translator(SubmissionName, targetlanguage_1, false, true);
			Thread.sleep(2000);
			
			//CHANGE READING SPEED TO 17
		    PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, "In_Progress"))) {
				System.out.println("INSIDE IF--------");
				General.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
			}
			Thread.sleep(6000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			Thread.sleep(4000);
			General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed);
			Thread.sleep(2000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed, "17");
		    Thread.sleep(2000);
		    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_OpenSubmission_Update_button);
			Thread.sleep(4000);
			
			
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			Pm_Transcription_Ongoing_Update_ReadingSpeed_transcripton(SubmissionName, targetlanguage_1, false, true);

		} catch (Exception e) {
			report("f", "Execution level error was encountered.\n\nError log:\n\n" + Verify.action().getErrorBuffer(e));
		}

	}
	
	public void Pm_Transcription_Ongoing_Update_ReadingSpeed_translator(String SubmissionName, String target,boolean complete, boolean back) throws Exception {

		System.out.println("INSIDE Pm_Transcription_Ongoing_Update_ReadingSpeed_translator  method()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		Thread.sleep(1000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input, SubmissionName);
		Thread.sleep(2000);
		
		if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName, target)))
		{
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		}
			  Thread.sleep(1000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
		Thread.sleep(10000);
		
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
		Thread.sleep(1000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1), "vbebvrebrejbjrebjhvnvrnbnrenbvrenbnbrevnvehhm");
		Thread.sleep(3000);
		
		String Reading_speed_color_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_readingSpeed_value(1)).getCssValue("color");
		System.out.println("READING SPEED COLOR OF TRANSLATION GRID SUBTITLE SHOULD BE RED:- " + Reading_speed_color_before);

		String hex_Reading_speed_color_before = Color.fromString(Reading_speed_color_before).asHex();
		System.out.println("Chars color value:-" + hex_Reading_speed_color_before);

		// verify assertion for color
		assertion = hex_Reading_speed_color_before.equalsIgnoreCase("#ff4f64");
		if (assertion == false) {
			report("f","Assertion failed while verifying READING SPEED COLOR OF TRANSCRIPTION GRID SUBTITLE SHOULD BE RED after Search");
		}
		Thread.sleep(3000);
	     System.out.println("IN BACK-----");
	   //condition is not needed as the element is visible but bot displyed
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
	    Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
			  
    
    Thread.sleep(5000); 
    PM_user.action().Navigate(menu_Submission);
	Thread.sleep(2000);
	PM_user.action().SearchSubmisson(SubmissionName);
	Thread.sleep(2000);
	if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, "In_Progress"))) {
		System.out.println("INSIDE IF--------");
		General.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
	}
	Thread.sleep(6000);
	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
	Thread.sleep(4000);
	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed);
	Thread.sleep(1000);
	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed, "19");
    Thread.sleep(2000);
    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_OpenSubmission_Update_button);
	Thread.sleep(4000);
    
    PM_user.action().Navigate(menu_ongoing);
	Thread.sleep(2000);
	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	Thread.sleep(2000);
	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	Thread.sleep(2000);
	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	Thread.sleep(2000);
	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	Thread.sleep(2000);

	if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
		System.out.println("INSIDE IF--------");
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
	}
	Thread.sleep(1000);
	General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
	Thread.sleep(10000);

	PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
	Thread.sleep(2000);
	PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1));
	Thread.sleep(2000);

	String Reading_speed_color_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_readingSpeed_value(1)).getCssValue("color");
	System.out.println("READING SPEED COLOR OF TRANSCRIPTION GRID SUBTITLE SHOULD BE WHITE:- " + Reading_speed_color_after);

	String hex_Reading_speed_color_after = Color.fromString(Reading_speed_color_after).asHex();
	System.out.println("hex_Reading_speed_color_after:-" + hex_Reading_speed_color_after);

	// verify assertion for color
	assertion = hex_Reading_speed_color_after.equalsIgnoreCase("#ffffff");
	if (assertion == false) {
		report("f","Assertion failed while verifying READING SPEED COLOR OF TRANSCRIPTION GRID SUBTITLE SHOULD BE WHITE after Search");
	}
    Thread.sleep(2000);
	
	  System.out.println("IN COMPLETED-----");
	  General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
		Thread.sleep(1000);
		 General.action().Click(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
		Thread.sleep(1000);
		 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
		Thread.sleep(1000);
		 General.action().Click(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
		Thread.sleep(3000);
		if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
	    	}
	}
	
	
	

	public void Pm_Transcription_Ongoing_Update_ReadingSpeed_transcripton(String SubmissionName, String target,boolean complete, boolean back) throws Exception {

		System.out.println("INSIDE Pm_Transcription_Ongoing_Update_ReadingSpeed_transcripton  method()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(
				Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
		Thread.sleep(10000);

		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);

		String ReadingSpeed_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(1)).getText();
		System.out.println("ReadingSpeed######### :-" + ReadingSpeed_before);
		Thread.sleep(2000);

		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,"Jellyfish at the Monterey Aquarium.hhhyhyht5n54");
		Thread.sleep(4000);

		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);

		String Reading_speed_color_before = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(1)).getCssValue("color");
		System.out.println("READING SPEED COLOR OF TRANSCRIPTION GRID SUBTITLE SHOULD BE RED:- " + Reading_speed_color_before);

		String hex_Reading_speed_color_before = Color.fromString(Reading_speed_color_before).asHex();
		System.out.println("Chars color value:-" + hex_Reading_speed_color_before);

		// verify assertion for color
		assertion = hex_Reading_speed_color_before.equalsIgnoreCase("#ff4f64");
		if (assertion == false) {
			report("f","Assertion failed while verifying READING SPEED COLOR OF TRANSCRIPTION GRID SUBTITLE SHOULD BE RED after Search");
		}
		Thread.sleep(2000);
		//condition is not needed as the element is visible but bot displyed
		//if (Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)) {
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
			Thread.sleep(1000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
		//}

		PM_user.action().Navigate(menu_Submission);
		Thread.sleep(2000);
		PM_user.action().SearchSubmisson(SubmissionName);
		Thread.sleep(2000);
		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, "In_Progress"))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
		}
		Thread.sleep(6000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
		Thread.sleep(4000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed);
		Thread.sleep(1000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Createsubmission_Maxreadingspeed, "20");
		Thread.sleep(2000);
		 General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_OpenSubmission_Update_button);
		Thread.sleep(4000);
		
		PM_user.action().Navigate(menu_ongoing);
		Thread.sleep(2000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
		Thread.sleep(10000);

		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);

		
		String Reading_speed_color_after = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(1)).getCssValue("color");
		System.out.println("READING SPEED COLOR OF TRANSCRIPTION GRID SUBTITLE SHOULD BE WHITE:- " + Reading_speed_color_after);

	    hex_Reading_speed_color_after = Color.fromString(Reading_speed_color_after).asHex();
		System.out.println("RS color value:-" + hex_Reading_speed_color_after);

		// verify assertion for color
		assertion = hex_Reading_speed_color_after.equalsIgnoreCase("#ffffff");
		if (assertion == false) {
			report("f","Assertion failed while verifying READING SPEED COLOR OF TRANSCRIPTION GRID SUBTITLE SHOULD BE WHITE after Search");
		}
		Thread.sleep(2000);

		
	}


		
		
		
	

  public void assertion() throws Exception {
		assertion = hex_Reading_speed_color_after.equalsIgnoreCase("#ffffff");
		if (assertion == false) {
			report("f","Assertion failed while verifying READING SPEED COLOR OF TRANSCRIPTION GRID SUBTITLE SHOULD BE WHITE after Search");
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
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),
				Subtitler_TestRail_Common_Properties.idTestPlan, Subtitler_TestRail_Common_Properties.idBuild, result,
				Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}

}
