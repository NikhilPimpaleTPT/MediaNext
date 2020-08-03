package Smoke;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
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

public class Sub_277132 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_277132"+CommonElements.BROWSER+"R1";

	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_ToClaim = "To Claim";
	String menu_Ongoing = "Ongoing";
	String menu_Completed = "Completed";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String Special_chars = "@#$%&*!*@";
    String Review_result_ModifiedTarget_Input;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_277132");
		dataSet.put("TL_test_case_description","SUB-277132:Create a submission with special characters");
		dataSet.put("TL_internal_testCase_ID", "277132");
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
			// TODO NEW IMPL OF SUBMISSION CREATION
			for(int i=1; i<=2; i++){
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName+i,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(2000);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
		    Pm_Complete_trans_Submission(SubmissionName+"1", "New");
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_Submission);
			Thread.sleep(2000);
			Pm_Complete_review_Submission(SubmissionName+"1", "In_Progress");
			Thread.sleep(2000);
			
			General.action().logoutMethod();
			
		 // trans login
	           General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
	           Thread.sleep(1000);
	           Translator.action().Navigate(menu_ToClaim);
	           Thread.sleep(1000);
	           Translator.action().trans_ToClaim(SubmissionName+"2");
			   Thread.sleep(2000);
			   PM_user.action().Navigate(menu_Ongoing);
			   Thread.sleep(2000);
			   translate_onGoing_submission(SubmissionName+"2",targetlanguage_1,true,false);
			   Thread.sleep(2000);
		       General.action().logoutMethod();
				
		// review login
				General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
				Thread.sleep(1000);
				Reviewer.action().Navigate(menu_ToClaim);
				Thread.sleep(1000);
				Reviewer.action().review_ToClaim(SubmissionName+"2");
				Thread.sleep(1000);
				Reviewer.action().Navigate(menu_Ongoing);
				Thread.sleep(1000);
				reviewer_onGoing_submission(SubmissionName+"2", targetlanguage_1, true,false);
				Thread.sleep(1000);
				
				

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public void Pm_Complete_trans_Submission(String SubmissionName, String status) throws Exception {
		
		 System.out.println("INSIDE Pm_Complete_trans_Submission  method()");
		 
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(2000);
			PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, status)))
				{
				System.out.println("INSIDE IF--------");
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
				}
			 /* Thread.sleep(2000);
			  PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_review_header);
			
			  Thread.sleep(2000);*/
			  Thread.sleep(6000);
			  PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			  Thread.sleep(6000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
			  Thread.sleep(5000);
			
		
		      List <WebElement> listofIds1= General.driver.findElements(Pm_User_Submission_Locators.Locator().Pm_Submission_ListofallIds);
				Thread.sleep(2000);
			   System.out.println("No of IDS--------"+listofIds1.size());
			   Thread.sleep(2000);
	
			    for(int i=1;i<=listofIds1.size();i++){
			    	PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i));
				Thread.sleep(1000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i));
				Thread.sleep(1000);
				PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i));
				Thread.sleep(1000);
				PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i),Special_chars);
				Thread.sleep(1000);
				
			    }
			    
//			    String PM_Trans_result_Target_Input= General.driver.findElement(By.xpath("//mat-list-item[contains(@class,'separator mat-list-item ng-star-inserted')]//shared-text-editor")).getText();
			    String PM_Trans_result_Target_Input= General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_ListofallIds).getText();

			    System.out.println("INPUT OF TARGET SEGMENT TEXT AREA IN OPEN SUBMISIION FOR TRANSLATOR:-" +PM_Trans_result_Target_Input );
				
				boolean result = PM_Trans_result_Target_Input.contains(Special_chars);
				System.out.println("PM TRANSLATOR TARGET SEGMENT TEXT AREA CONTAINS SPECIAL CHARACTERS:-" +result);
				
				
				Thread.sleep(1000);
			    PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
				Thread.sleep(1000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
				Thread.sleep(1000);
				PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_trans);
				Thread.sleep(1000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_trans);
				Thread.sleep(4000);
				if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
					General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
					Thread.sleep(1000);
					General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
			    	}

				
				 System.out.println("EOM Pm_Complete_trans_Submission  method()");

	}
	
	public void Pm_Complete_review_Submission(String SubmissionName, String step) throws Exception {
		
		 System.out.println("INSIDE Pm_Complete_review_Submission  method()");
		 
		   PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(1000);
			 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(1000);
			 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
			Thread.sleep(1000);
			 PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
			Thread.sleep(1000);
			
			if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, step)))
			{
			System.out.println("INSIDE IF--------");
			PM_user.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
			}
			
			  Thread.sleep(6000);
			  PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
			
			  Thread.sleep(6000);
			  PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_review_header);
			
			  Thread.sleep(4000);
			  General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
			  Thread.sleep(2000);
			
		
		      List <WebElement> listofIds1= General.driver.findElements(Pm_User_Submission_Locators.Locator().Pm_Submission_ListofallIds);
				Thread.sleep(2000);
			   System.out.println("No of IDS--------"+listofIds1.size());
			   Thread.sleep(2000);
	
			    for(int i=1;i<=listofIds1.size();i++){
			   	 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
				Thread.sleep(1000);
				 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
				Thread.sleep(1000);
				 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
				 Thread.sleep(1000);
					PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i),Special_chars);
					Thread.sleep(1000);
				
			    }
			    
//			    String Pm_review_result_ModifiedTarget_Input= General.driver.findElement(By.xpath("//mat-list-item[contains(@class,'separator mat-list-item ng-star-inserted')]//shared-text-editor")).getText();
			    String Pm_review_result_ModifiedTarget_Input= General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_ListofallIds).getText();

			    System.out.println("INPUT OF MODIFIED TARGET SEGMENT TEXT AREA IN OPEN SUBMISIION FOR REVIEWER:-" +Pm_review_result_ModifiedTarget_Input );
				
				boolean result = Pm_review_result_ModifiedTarget_Input.contains(Special_chars);
				System.out.println("PM REVIEWER MODIFIED TARGET SEGMENT TEXT AREA CONTAINS SPECIAL CHARACTERS:-" +result);
				Thread.sleep(1000);
				
				 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
				Thread.sleep(1000);
				 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
				Thread.sleep(1000);
				 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_review);
				Thread.sleep(1000);
				 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_review);
				 Thread.sleep(3000);
					if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
						General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
						Thread.sleep(1000);
						General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
				    	}
				 
				 System.out.println("EOM Pm_Complete_review_Submission  method()");

	}
	
	public void translate_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
		
		 System.out.println("INSIDE trans_Ongoing  method()");
		 
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
			
				  Thread.sleep(4000);
			      List <WebElement> listofIds1= General.driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
				  Thread.sleep(1000);
				  System.out.println("No of IDS--------"+listofIds1.size());
					Thread.sleep(2000);
					
			    for(int i=1;i<=listofIds1.size();i++){
			    	Thread.sleep(2000);
			    	General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i),Special_chars);
					Thread.sleep(1000);
					
			    }
				    
//				    String Trans_result_Target_Input= General.driver.findElement(By.xpath("//mat-list-item[contains(@class,'separator mat-list-item ng-star-inserted')]//shared-text-editor")).getText();
				    String Trans_result_Target_Input= General.driver.findElement(TranslatorLocators.Locator().translator_toClaim_ListofallIds).getText();
					System.out.println("INPUT OF TARGET SEGMENT TEXT AREA IN TRANSLATOR:-" +Trans_result_Target_Input );
					
					boolean result = Trans_result_Target_Input.contains(Special_chars);
					System.out.println("TRANSLATOR TARGET SEGMENT TEXT AREA CONTAINS SPECIAL CHARACTERS:-" +result);
			    
			    
			    if(complete){
			    	General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
					Thread.sleep(1000);
					General.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
					Thread.sleep(1000);
					General.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
					Thread.sleep(3000);
					if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
						General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
						Thread.sleep(1000);
						General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
				    	}
				}
			    
			    if(back){
			    	System.out.println("IN BACK-----");
			    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
						General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
						General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
				    	}
			    }
			    
				 System.out.println("EOM trans_Ongoing  method()");
	}

	public void reviewer_onGoing_submission(String SubmissionName,String target, boolean complete, boolean back) throws Exception {

		System.out.println("INSIDE reviewer_onGoing_submission  method()");

		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		General.action().type(ReviewerLocators.Locator().review_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(ReviewerLocators.Locator().reviewer_selectSubmission_checkbox(SubmissionName,target));
		}
		Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);

		Thread.sleep(4000);
		List<WebElement> listofIds1 = General.driver.findElements(ReviewerLocators.Locator().reviewer_toClaim_ListofallIds);
		Thread.sleep(1000);
		System.out.println("No of IDS--------" + listofIds1.size());
		Thread.sleep(3000);
		
		for (int i = 1; i <= listofIds1.size(); i++) {
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
			PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i),Special_chars);
			Thread.sleep(1000);
			
	    }
		    
//		    Review_result_ModifiedTarget_Input= General.driver.findElement(By.xpath("//mat-list-item[contains(@class,'separator mat-list-item ng-star-inserted')]//shared-text-editor")).getText();
	    	Review_result_ModifiedTarget_Input= General.driver.findElement(ReviewerLocators.Locator().reviewer_toClaim_ListofallIds).getText();

	    	System.out.println("INPUT OF MODIFIED TARGET SEGMENT TEXT AREA IN REVIEWER:-" +Review_result_ModifiedTarget_Input );
			
			boolean result = Review_result_ModifiedTarget_Input.contains(Special_chars);
			System.out.println("REVIEWER MODIFIED TARGET SEGMENT TEXT AREA CONTAINS SPECIAL CHARACTERS:-" +result);

		if (complete) {
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_Complete_Button);
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().reviewer_Complete_Button);
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_CompleteDailoge_Button);
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().reviewer_CompleteDailoge_Button);
			Thread.sleep(3000);
			if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
		    	}
		}

		if(back){
	    	System.out.println("IN BACK-----");
	    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
		    	}
		}

		System.out.println("EOM reviewer_onGoing_submission  method()");
	}
	
	
	public void assertion() throws Exception {
		assertion =Review_result_ModifiedTarget_Input.contains(Special_chars);
		if (assertion == false) {
			report("f","Assertion failed while verifying Review_result_ModifiedTarget_Input contains special characters  after Search");
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
