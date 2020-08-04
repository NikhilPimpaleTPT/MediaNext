package subtitler.project_manager.submission;
import static org.testng.AssertJUnit.assertTrue;
/**
 * 
 * @author pvaidya
 * 
 */


import java.util.LinkedHashMap;
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
import modules.Verify;

public class Sub_854940 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_854940"+CommonElements.BROWSER+"N2";
	String targetlanguage_1 = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String menu_completed = "Completed";
	String menu_ToClaim = "To Claim";
	String menu_Ongoing  = "Ongoing";
	String path;
	String folder="854940";
	String ostFiletext1="JELLYFISH AT THE MONTEREY AQUARIUM.";//2
	String ostFiletext2="DUDE GET OUT OF THE WAY!";//4
	String ostFiletext3="SHAKY HANDS";//6
	String sourceFileText1="AH YES, THIS IS BETTER";//7
	String sourceFileText2="THANKS FOR WATCHING";//8
	String defaultSegmentText1="Jellyfish at the Monterey Aquarium.";
	String defaultSegmentText2="Dude get out of the way!";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub__854940");
		dataSet.put("TL_test_case_description", "New Submission - Import OST source .");
		dataSet.put("TL_internal_testCase_ID", "854940");
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
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_source_OSTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ folder,CommonElements.action().FILE_ABSOLUTE_PATH+CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_OST_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);


			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				
			}
			
			PM_user.action().Navigate(menu_ToClaim);
			Thread.sleep(1000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(1000);
			PM_user.action().Navigate(menu_Ongoing);
			Thread.sleep(2000);	  
			
			Trans_Ongoing_Import_translation_File(SubmissionName,targetlanguage_1);
			Thread.sleep(5000);	  
			
			assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_EditSubmission_NoOstTage_ForText(defaultSegmentText1), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying On Screen Tag for default segment 1");
				
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_EditSubmission_OstTage_ForText(2,ostFiletext1), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying On Screen Tag for Capital Letter Text");
				
			}
			
			assertion = Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().Pm_EditSubmission_NoOstTage_ForText(defaultSegmentText1), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying On Screen Tag for default segment 2");
				
			}
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_EditSubmission_OstTage_ForText(4,ostFiletext2), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying On Screen Tag for Capital Letter Text");
				
			}
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_EditSubmission_OstTage_ForText(6,ostFiletext3), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying On Screen Tag for Capital Letter Text");
				
			}
			
			
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_EditSubmission_OstTage_ForText(7,sourceFileText1), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying On Screen Tag for Capital Letter Text");
				
			}
			
			
			
}	
		 catch (Exception e) {
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
		
		System.out.println("EOM Trans_Ongoing_Import_translation_File  method()");
	}
	
	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_EditSubmission_OstTage_ForText(8,sourceFileText2), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying On Screen Tag for Capital Letter Text");
			
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

	
			
			