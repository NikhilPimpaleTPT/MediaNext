package subtitler.Reviewer;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary: This test case verifies that if user can enter more then two lines in modified target column.
 *
 */

public class Sub_1789831 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataset = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1789831" + CommonElements.BROWSER + "J2";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String modifiedTargetThreeLineText = "Modified Target Segment line1\n" + "Modified Target Segment line2\n"+"Modified Target Segment line3_";
	String maxLinePerSub="3";
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1789831");
		dataset.put("TL_test_case_description", "SUB-1789831:Enter more then two lines in target column");
		dataset.put("TL_internal_testCase_ID", "1789831");
	}
	
	@Test
	public void execute() throws Exception {
		testCase(dataset);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataset) throws Exception {
		
		
	try {
		
		General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
		Thread.sleep(20000);
		PM_user.action().Navigate(menu_submission);
		Thread.sleep(1000);		
		PM_user.action().CreateSubmisson_Step1_forCustomizedLine("17", "35", "80", "100",maxLinePerSub);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
		PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",	TranslatorGroupName, "review", "", true);
		PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
		PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
		PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
		Thread.sleep(20000);
	
		//SEARCH SUBMISSION AND CHECK
		PM_user.action().SearchSubmisson_andCheck(SubmissionName);
		Thread.sleep(2000);
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying SubmissionName  after Search");
		}
		
		Thread.sleep(2000);
		General.action().logoutMethod();
		
		//translator login
		
		General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
		Thread.sleep(20000);
		Translator.action().Navigate(menu_to_claim);
		Thread.sleep(1000);
		Translator.action().trans_ToClaim(SubmissionName);
		Thread.sleep(1000);
		Translator.action().Navigate(menu_ongoing);
		Thread.sleep(1000);
		Translator.action().translate_onGoing_submission(SubmissionName,targetlanguage,true,false);
		Thread.sleep(2000);
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
         Reviewer_onGoing_submission(SubmissionName, targetlanguage, true, false);
		 Thread.sleep(2000);
		 
		 
		 
	} catch (Exception e) {
		report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
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
					General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i), modifiedTargetThreeLineText+i); 	
					Thread.sleep(1000);
					
				
			}
			    
			//Verify that if max lines per sub is set to 3 then reviewer user is able to add three lines of review for each subtitle in modified target column.
			assertion = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)).getText().contains(modifiedTargetThreeLineText+1);
			if (assertion == false) {
			report("f","Assertion failed while verifying three line text in modified target segment");
					
			}
			assertion = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2)).getText().contains(modifiedTargetThreeLineText+2);
			if (assertion == false) {
			report("f","Assertion failed while verifying three line text in modified target segment");
					
			}
			
			assertion = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3)).getText().contains(modifiedTargetThreeLineText+3);
			if (assertion == false) {
			report("f","Assertion failed while verifying three line text in modified target segment");
					
			}
			assertion = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4)).getText().contains(modifiedTargetThreeLineText+4);
			if (assertion == false) {
			report("f","Assertion failed while verifying three line text in modified target segment");
					
			}
			assertion = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(5)).getText().contains(modifiedTargetThreeLineText+5);
			if (assertion == false) {
			report("f","Assertion failed while verifying three line text in modified target segment");
					
			}
			    
			
			    
	}

	public void assertion() throws Exception {
		System.out.println("inside assertion");		
		assertion = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(6)).getText().contains(modifiedTargetThreeLineText+6);
		if (assertion == false) {
		report("f","Assertion failed while verifying three line text in modified target segment");
				
		}else {
				report("p", "All Assertion passed.");
			}
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(dataset.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}
}