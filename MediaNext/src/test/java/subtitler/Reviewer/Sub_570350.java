package subtitler.Reviewer;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
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

public class Sub_570350 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_570350"+CommonElements.BROWSER+"A1";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
    String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String RS_Value_OfFilledTarget;
	String RS_Value_AfterRemoveTextEntered_ToModifiedTarget;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_570350");
		dataSet.put("TL_test_case_description","Sub_570350:-Translation/Review Screen > Reading Speed wrong after update - Modified Target");
		dataSet.put("TL_internal_testCase_ID", "570350");
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
			General.action().logoutMethod();
			
			//trans login
			 General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
	         Thread.sleep(2000);
	         Translator.action().Navigate(menu_to_claim);
	         Thread.sleep(2000);
	         Translator.action().trans_ToClaim(SubmissionName);
	         Thread.sleep(2000);
	         Translator.action().Navigate(menu_ongoing);
	         Thread.sleep(2000);
	         translate_onGoing_submission(SubmissionName, targetlanguage_1, true, false);
	         Thread.sleep(2000);
	         Translator.action().Navigate(menu_completed);
	         Thread.sleep(2000);
	         Translator.action().SearchSubmisson(SubmissionName);
	         Thread.sleep(2000);

			 assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translatorSearchResult(SubmissionName), 5);
			 if (assertion == false) {
			  report("f","Assertion failed while verifying SubmissionName  after Search");
			  }
			  Thread.sleep(2000);
			  General.action().logoutMethod();

			  // review login
			  General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
			  Thread.sleep(2000);
			  Reviewer.action().Navigate(menu_to_claim);
			  Thread.sleep(2000);
			  Reviewer.action().review_ToClaim(SubmissionName);
			  Thread.sleep(2000);
			  Reviewer.action().Navigate(menu_ongoing);
			  Thread.sleep(2000);
			  reviewer_ongoing_keyboard_shortcuts(SubmissionName, targetlanguage_1);
			  Thread.sleep(2000);


		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public void translate_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
		
		 System.out.println("INSIDE translate_onGoing_submission  method()");
		 
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
				  Thread.sleep(2000);
					General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
			
				  Thread.sleep(5000);
			      List <WebElement> listofIds1= General.driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
				  Thread.sleep(2000);
				  System.out.println("No of IDS--------"+listofIds1.size());
	
			    for(int i=2;i<=listofIds1.size();i++){
			    	General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
					Thread.sleep(1000);
					General.action().Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
					Thread.sleep(1000);
				
			    }
			    
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
			    
				 System.out.println("EOM translate_onGoing_submission  method()");
	}

	public void reviewer_ongoing_keyboard_shortcuts(String SubmissionName,String target) throws Exception {

		System.out.println("INSIDE reviewer_ongoing_keyboard_shortcuts  method()");

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
			General.action().Click(ReviewerLocators.Locator().reviewer_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(4000);
		General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
		Thread.sleep(5000);
		
		General.action().driver.navigate().refresh();
		Thread.sleep(5000);
		
		//check RS:- user enters text to modified target the Reading Speed column gets updated as per text entered.
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_readingSpeed_value(2));
		 Thread.sleep(2000);
		 
		 String RS_Value_OfEmptyMTarget = General.driver.findElement(ReviewerLocators.Locator().review_readingSpeed_value(2)).getText();
		 System.out.println("RS VALUE BEFORE TEXT ENTERED TO MODIFIED TARGET:-" +RS_Value_OfEmptyMTarget);
		
		 General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		 Thread.sleep(2000);
		 General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		 Thread.sleep(2000);
		 General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		 Thread.sleep(2000);
		 General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2)),"Test ReadingSpeed");
		 Thread.sleep(2000);
		 
		 String RS_Value_OfFilledMTarget = General.driver.findElement(ReviewerLocators.Locator().review_readingSpeed_value(2)).getText();
		 System.out.println("RS VALUE AFTER TEXT ENTERED TO MODIFIED TARGET:-" +RS_Value_OfFilledMTarget);
		 
		 boolean RS_Value = RS_Value_OfEmptyMTarget != RS_Value_OfFilledMTarget;
		 System.out.println("RS VALUE IS NOT SAME AFTER ENTERED TEXT IN MODIFIED TARGET:-" +RS_Value);
		 
		 Thread.sleep(2000);
		assertion = RS_Value_OfEmptyMTarget != RS_Value_OfFilledMTarget;
		if (assertion == false) {
			report("f","Assertion failed while verifying reading speed value is different after Search");
		}
		
		//check RS:- modified target segment whose target segment field is empty.
		
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_readingSpeed_value(1));
		 Thread.sleep(2000);
		 
		 String RS_Value_OfEmptyTarget = General.driver.findElement(ReviewerLocators.Locator().review_readingSpeed_value(1)).getText();
		 System.out.println("RS VALUE BEFORE TEXT ENTERED TO MODIFIED TARGET:-" +RS_Value_OfEmptyTarget);
		
		 General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		 Thread.sleep(2000);
		 General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		 Thread.sleep(2000);
		 General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		 Thread.sleep(2000);
		 General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)),"Test ReadingSpeed");
		 Thread.sleep(2000);
		 
		 String RS_Value_AfterTextEntered_ToMTarget = General.driver.findElement(ReviewerLocators.Locator().review_readingSpeed_value(1)).getText();
		 System.out.println("RS VALUE AFTER TEXT ENTERED TO MODIFIED TARGET:-" +RS_Value_AfterTextEntered_ToMTarget);
		 
		 boolean RS_Value1 = RS_Value_OfEmptyTarget != RS_Value_AfterTextEntered_ToMTarget;
		 System.out.println("RS VALUE IS NOT SAME AFTER ENTERED TEXT IN MODIFIED TARGET:-" +RS_Value1);
		 
		 Thread.sleep(2000);
		assertion = RS_Value_OfEmptyTarget != RS_Value_AfterTextEntered_ToMTarget;
		if (assertion == false) {
			report("f","Assertion failed while verifying reading speed value is different after Search");
		}
		
		
		General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)),Keys.chord(Keys.CONTROL,"a"));
		
		/*General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)),Keys.chord(Keys.DELETE));
		Thread.sleep(3000);*/
		
		General.action().type_withKEYS((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)), Keys.DELETE, false);
		Thread.sleep(3000);
		String RS_Value_AfterRemoveTextEntered_ToMTarget = General.driver.findElement(ReviewerLocators.Locator().review_readingSpeed_value(1)).getText();
		System.out.println("RS VALUE AFTER TEXT REMOVED TO MODIFIED TARGET:-" +RS_Value_AfterRemoveTextEntered_ToMTarget);
		
		boolean RS_Value3 = RS_Value_OfEmptyTarget.equalsIgnoreCase(RS_Value_AfterRemoveTextEntered_ToMTarget);
		System.out.println("RS VALUE AFTER TEXT REMOVED FROM MT WOULD BE SAME AS RS FOR SOURCE SUBTITLE:-" +RS_Value3);
		
		//check RS for:- modified target segment whose target segment field is filled with characters.
		
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_readingSpeed_value(3));
		 Thread.sleep(2000);
		 
		 RS_Value_OfFilledTarget = General.driver.findElement(ReviewerLocators.Locator().review_readingSpeed_value(3)).getText();
		 System.out.println("RS VALUE BEFORE TEXT ENTERED TO MODIFIED TARGET:-" +RS_Value_OfFilledTarget);
		
		 General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		 Thread.sleep(2000);
		 General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		 Thread.sleep(2000);
		 General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		 Thread.sleep(2000);
		 General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3)),"Test ReadingSpeed");
		 Thread.sleep(2000);
		 
		  String  RS_Value_AfterTextEntered_ToMOdifiedTarget = General.driver.findElement(ReviewerLocators.Locator().review_readingSpeed_value(3)).getText();
		 System.out.println("RS VALUE AFTER TEXT ENTERED TO MODIFIED TARGET:-" +RS_Value_AfterTextEntered_ToMOdifiedTarget);
		 
		 boolean RS_Value4 = RS_Value_OfFilledTarget != RS_Value_AfterTextEntered_ToMOdifiedTarget;
		 System.out.println("RS VALUE IS NOT SAME AFTER ENTERED TEXT IN MODIFIED TARGET:-" +RS_Value4);
		 
		 Thread.sleep(2000);
		assertion = RS_Value_OfFilledTarget != RS_Value_AfterTextEntered_ToMOdifiedTarget;
		if (assertion == false) {
			report("f","Assertion failed while verifying reading speed value is different after Search");
		}
		
		
		General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3)),Keys.chord(Keys.CONTROL,"a"));
		General.action().type_withKEYS((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3)), Keys.DELETE, false);
		Thread.sleep(5000);
		
		 RS_Value_AfterRemoveTextEntered_ToModifiedTarget = General.driver.findElement(ReviewerLocators.Locator().review_readingSpeed_value(3)).getText();
		System.out.println("RS VALUE AFTER TEXT REMOVED TO MODIFIED TARGET:-" +RS_Value_AfterRemoveTextEntered_ToModifiedTarget);
		Thread.sleep(2000);
		boolean RS_Value5 = RS_Value_OfFilledTarget.equalsIgnoreCase(RS_Value_AfterRemoveTextEntered_ToModifiedTarget);
		System.out.println("RS VALUE AFTER TEXT REMOVED FROM MT WOULD BE SAME AS RS FOR SOURCE SUBTITLE:-" +RS_Value5);

		
		
		System.out.println("EOM reviewer_ongoing_keyboard_shortcuts  method()");
	}
	public void assertion() throws Exception {
		assertion =  RS_Value_OfFilledTarget.equalsIgnoreCase(RS_Value_AfterRemoveTextEntered_ToModifiedTarget);
		if (assertion == false) {
			report("f","Assertion failed while verifying reviewer_ModifiedTargetSegement_textarea(4)  after Search");
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









