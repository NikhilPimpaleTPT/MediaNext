package Smoke;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;

public class THREE_STEP_WORKFLOW_CHECK {
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String SubmissionName = "THREE_STEP_WORKFLOW_CHECK"+CommonElements.BROWSER+"OO1";
    String OrganizationName = "Subtitle_Common_orgs";
    String WorkflowName = "Three_Step_Transc_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_AllJobs = "All Jobs";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	
	String Text_ToCheck_ThreeStep_Workflow[]= {"Jellyfish at the Monterey Aquarium.","Dude get out of the way!","Shaky Hands","Ah yes, this is better","Thanks for watching and I hope you'll have fun with the VideoSub library.","Thanks for watching and I hope you'll have fun with the VideoSub library."};
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("THREE_STEP_WORKFLOW_CHECK");
		dataSet.put("TL_test_case_description", "THREE_STEP_WORKFLOW_CHECK :Check Three Step Workflow");
		dataSet.put("TL_internal_testCase_ID", "000000");
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
				
				 PM_user.action().Navigate(menu_to_claim);
		         Thread.sleep(1000);
		         PM_user.action().PM_ToClaim(SubmissionName);
		         Thread.sleep(1000);
		         PM_user.action().Navigate(menu_ongoing);
		         Thread.sleep(1000);
		         PM_transcription_onGoing_ReadingSpeed_Submission(SubmissionName,targetlanguage_1,Text_ToCheck_ThreeStep_Workflow);
		         Thread.sleep(2000);
		         PM_user.action().Navigate(menu_completed);
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
		         Translator.action().translate_onGoing_submission(SubmissionName, targetlanguage_1, true, false);
				 Thread.sleep(2000);
				 Translator.action().Navigate(menu_completed);
				 Thread.sleep(2000);
				 Translator.action().SearchSubmisson(SubmissionName);
				 Thread.sleep(2000);
				
				
				 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying SubmissionName  after Search");
						
					}
				
				Thread.sleep(2000);
				General.action().logoutMethod();
				
				// Review LogIn 
			     General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
		         Thread.sleep(1000);
		         Reviewer.action().Navigate(menu_to_claim);
		         Thread.sleep(1000);
		         Reviewer.action().review_ToClaim(SubmissionName);
		         Thread.sleep(1000);
		         Reviewer.action().Navigate(menu_ongoing);
		         Thread.sleep(1000);
		         Reviewer_onGoing_submission(SubmissionName, targetlanguage_1, true, false);
				 Thread.sleep(2000);
				 Reviewer.action().Navigate(menu_completed);
				 Thread.sleep(2000);
				 Reviewer.action().SearchSubmisson(SubmissionName);
				 Thread.sleep(2000);
			    
				
		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
            }
  }
	
	
	
	public void PM_transcription_onGoing_ReadingSpeed_Submission(String SubmissionName, String target,String[] Text_ToCheck_ThreeStep_Workflow ) throws Exception {

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
		Thread.sleep(4000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
        Thread.sleep(10000);
        
        for(int i=1;i<=6;i++){
        	
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(i));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(i));
		Thread.sleep(2000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		
		PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
		Thread.sleep(2000);
		PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,Text_ToCheck_ThreeStep_Workflow[i-1]);
		Thread.sleep(4000);
       
        }
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
	
	
	public void Reviewer_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
		
		 System.out.println("INSIDE trans_Ongoing  method()");
		 
		    General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().review_search_input);
			Thread.sleep(1000);
			General.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
			Thread.sleep(1000);
			General.action().type(ReviewerLocators.Locator().review_search_input, SubmissionName);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target)))
			{
				System.out.println("INSIDE IF--------");
				General.action().Click(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target));
			}
				  Thread.sleep(1000);
				  General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
			
				  Thread.sleep(5000);
			      List <WebElement> listofIds1= General.action().driver.findElements(ReviewerLocators.Locator().reviewer_toClaim_ListofallIds);
				  Thread.sleep(1000);
				  System.out.println("No of IDS--------"+listofIds1.size());
				  Thread.sleep(3000);
				  
			    for(int i=1;i<=listofIds1.size();i++){
			    	Thread.sleep(2000);
			    	General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
					Thread.sleep(1000);
					General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
					Thread.sleep(1000);
					General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
					Thread.sleep(1000);
					General.action().Click(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
					Thread.sleep(1000);
				
			    }
			    
			    if(complete){
			    	General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_Complete_Button);
					Thread.sleep(1000);
					General.action().Click(ReviewerLocators.Locator().reviewer_Complete_Button);
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_CompleteDailoge_Button);
					Thread.sleep(1000);
					General.action().Click(ReviewerLocators.Locator().reviewer_CompleteDailoge_Button);
					Thread.sleep(3000);
					if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
					General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_BackToSubmissionList_Menubutton_forCompleted);
					Thread.sleep(1000);
					General.action().Click(ReviewerLocators.Locator().review_BackToSubmissionList_Menubutton_forCompleted);
				    }
				}
				
			    
			    if(back){
			    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
						General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_BackToSubmissionList_Button);
						Thread.sleep(1000);
						General.action().Click(ReviewerLocators.Locator().review_BackToSubmissionList_Button);
				    	}
			    }
			    
				 System.out.println("EOM trans_Ongoing  method()");
	}





public void assertion() throws Exception {
	 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
			
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
	