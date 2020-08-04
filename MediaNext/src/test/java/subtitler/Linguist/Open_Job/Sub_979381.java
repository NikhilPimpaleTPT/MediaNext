package subtitler.Linguist.Open_Job;
import static org.testng.AssertJUnit.assertTrue;


/**
 * 
 * @author pvaidya
 * This Test Case Is Claim and start task in one click.
 */
import java.util.LinkedHashMap;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

public class Sub_979381 {
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String SubmissionName = "SUB_979381"+CommonElements.BROWSER+"M6";
    String OrganizationName = "Subtitle_Common_orgs";
    String WorkflowName = "Three_Step_Transc_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_979381");
		dataSet.put("TL_test_case_description", "Sub_979381 :Claim and start task in one click.");
		dataSet.put("TL_internal_testCase_ID", "979381");
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
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow_ThreeStepWorkflow(OrganizationName, WorkflowName,"transcription",transcGroupName,"Trans",TranslatorGroupName,true, "Review", ReviewGroupName, true);
				System.out.println("filePath------>" + filePath);
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
				
				
				EditSubmissionInTransReviewAndTranscriptionPhase();
				
				 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1), 5);
	 			 if (assertion == false) {
	 				report("f","Assertion failed while verifying 1st Text segment After Opening Task by clicking on Open Task button displyed in Masssage");
	 			 }
				
	 			CompleteTaskForTransAndReviewAndTranscription();
	 			 
				Thread.sleep(2000);
				General.action().logoutMethod();
						
				// trans login
				General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
				Thread.sleep(1000);
				
				EditSubmissionInTransReviewAndTranscriptionPhase();
				
				assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1), 5);
	 			 if (assertion == false) {
	 				report("f","Assertion failed while verifying 1st Text segment After Opening Task by clicking on Open Task button displyed in Masssage)");
	 			 }
				
				CompleteTaskForTransAndReviewAndTranscription();
				
				Thread.sleep(2000);
				General.action().logoutMethod();
				
				// Review LogIn 
			    General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
			    
			    EditSubmissionInTransReviewAndTranscriptionPhase();
				
			    
				
		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
            }
  }
	
	
	
	
	 public void EditSubmissionInTransReviewAndTranscriptionPhase() throws Exception {
			
			
			Translator.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			
			Translator.action().SearchSubmisson_andCheck(SubmissionName);
			
			
			Translator.action().Click(TranslatorLocators.Locator().translator_clamSubmission_icon);
			Thread.sleep(3000);
			Translator.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_claimAlert_claim_button);
			Thread.sleep(2000);
			Translator.action().Click(TranslatorLocators.Locator().translator_claimAlert_claim_button);
			Thread.sleep(1000);
			
			Translator.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_claimAlert_claim_button_OpenTaskMassegeButton);
			Thread.sleep(1000);
			Translator.action().Click(TranslatorLocators.Locator().translator_claimAlert_claim_button_OpenTaskMassegeButton);
			Thread.sleep(3000);
			
			
			
	 }
	 
	 public void CompleteTaskForTransAndReviewAndTranscription() throws InterruptedException {
			
			System.out.println("INSIDE method CompleteTaskForTransAndReviewAndTranscription()\n"); 
			

		    PM_user.action().waitforelemenetpresent(CommonLocartors.Locator().SubmissionEdit_complete_task);
	        Thread.sleep(1000);
	        PM_user.action().Click(CommonLocartors.Locator().SubmissionEdit_complete_task);
		    Thread.sleep(1000);

	        PM_user.action().waitforelemenetpresent(CommonLocartors.Locator().SubmissionEdit_complete_Task_Alert_button);
	        Thread.sleep(1000);
	        PM_user.action().Click(CommonLocartors.Locator().SubmissionEdit_complete_Task_Alert_button);
	        Thread.sleep(6000);
	        
	        System.out.println("EOM CompleteTaskForTransAndReviewAndTranscription()\n");
			
			
		}
	 
	public void assertion() throws Exception {
		 assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1), 5);
		 if (assertion == false) {
			report("f","Assertion failed while verifying 1st Text segment After Opening Task by clicking on Open Task button displyed in Masssage");
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
