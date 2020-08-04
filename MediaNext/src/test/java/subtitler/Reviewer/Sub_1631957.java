package subtitler.Reviewer;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

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
 *Summary: This testcase verifies the max. number of lines for a subtitle at the submission level
 *
 */ 

public class Sub_1631957 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1631957"+CommonElements.BROWSER+"F1";
	String WorkflowName = "Two_Step_Workflow";
	String OrganizationName = "Subtitle_Common_orgs";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_submission = "Submissions";
	String targetlanguage = "German (Germany)";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
	String defaultValue_maxLinePerSub="2";
	String fourLineSegment_c="Transcription Segment line 1\n" + 
			"Transcription Segment line 2\n" + 
			"Transcription Segment line 3\n" + 
			"Transcription Segment line 4";
	String fourLineSegment_f ="Transcription Segment line 1\r\n" + 
			"Transcription Segment line 2\r\n" + 
			"Transcription Segment line 3\r\n" + 
			"Transcription Segment line 4";
	String segmentText="Shaky Hands Shaky Hands";
	
	String lengthOfTheSegment;
	Boolean assertion = true;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1631957");
		dataSet.put("TL_test_case_description", "SUB-1631957:Define the max. number of lines for a subtitle at the submission level.");
		dataSet.put("TL_internal_testCase_ID", "1631957");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			//Create Submission
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			Thread.sleep(20000);
			PM_user.action().Navigate(menu_submission);
			Thread.sleep(1000);
			CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", true);
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
			 
			 
			// trans login
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
		    General.action().logoutMethod();
	           
	        // Review LogIn 
			General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
			Thread.sleep(20000);
			Reviewer.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			Reviewer.action().review_ToClaim(SubmissionName);
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			reviewer_onGoing_submission(SubmissionName, targetlanguage);
			Thread.sleep(2000);
			
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void CreateSubmisson_Step1(String ReadingSpeed,String MaxCharperLine,String MindurationVSreadingSpeed,String MaxdurationVSreadingSpeed) throws Exception{
		 System.out.println("INSIDE method CreateSubmisson_Step1()\n");
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		 Thread.sleep(1000);
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
		 Thread.sleep(1000);
		 
		 assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input).getAttribute("value").contains(defaultValue_maxLinePerSub);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying default value for the 'Max line per sub' is 2. ");
				
		 }
	     
	     PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input,"-1");
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Minreadingspeed);
		 Thread.sleep(1000);
		 
		 
		 assertion =Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_ErrorMsgForMaxLinesPerSub("Value is below the limit 1"), 5);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying Error message ");
				
		 }
	     
	     PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input,"7");
		 Thread.sleep(1000);
		 
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Minreadingspeed);
		 Thread.sleep(1000);
		 
		 
		 assertion =Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_ErrorMsgForMaxLinesPerSub("Value is above the limit 4"), 5);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying Error message ");
				
		 }
	     
	     PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input,"4");
		 Thread.sleep(1000);
	     
		 
		 
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input,ReadingSpeed);
		 Thread.sleep(1000);
			
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,MaxCharperLine);
		 Thread.sleep(1000);
			
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,MindurationVSreadingSpeed);
		 Thread.sleep(1000);
			
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,MaxdurationVSreadingSpeed);
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		 Thread.sleep(1000);
			
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		 Thread.sleep(4000);
	     System.out.println("EOM CreateSubmisson_Step1()\n");

		}
	 

public void reviewer_onGoing_submission(String SubmissionName, String target)throws Exception {

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
	Thread.sleep(1000);
	General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
	Thread.sleep(5000);
	//TODO USE ONLY WHEN RESOLUTION ISSUE
//	Thread.sleep(1000);
//	General.action().Click(ReviewerLocators.Locator().review_ongoing_tabes_rightArrow);
//	Thread.sleep(2000);
	General.action().Click(ReviewerLocators.Locator().review_ongoing_taskInfo_button);
	Thread.sleep(2000);
	

	assertion =General.driver.findElement(ReviewerLocators.Locator().taskInfo_maxLinePerSub).getText().contains("4");
     if (assertion == false) {
	 report("f","Assertion failed while verifying default value for the 'Max line per sub' is 4. ");
			
	}
	
    General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
	Thread.sleep(1000);
	General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
	Thread.sleep(1000);
	General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
	Thread.sleep(1000);
	
	if(CommonElements.BROWSER.contains("CHROME")){
	General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),fourLineSegment_c);
	Thread.sleep(2000);
	}else {
	General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),fourLineSegment_f);
	Thread.sleep(2000);
		
	}
	Thread.sleep(5000);
	General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
	Thread.sleep(5000);
	
	//TODO USE ONLY WHEN RESOLUTION ISSUE
//	 General.action().Click(ReviewerLocators.Locator().review_ongoing_tabes_leftArrow);
//	 Thread.sleep(1000);
	 General.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_button);
	 Thread.sleep(1000);
	 General.action().Click(ReviewerLocators.Locator().review_ongoing_qualitycheck_Run_button);
	 Thread.sleep(1000);

	
	assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_qualityChecks_rules("1","Reading speed is too high"), 5);
	if (assertion == false) {
		report("f","Assertion failed while verifying quality check rules");
	}
	
	assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().review_qualityChecks_rules("1","There are not enough frames between this segment and the next one."), 5);
	if (assertion == false) {
		report("f","Assertion failed while verifying quality check rules");
	}
	
	assertion = Verify.action().verifyElementNotPresent(ReviewerLocators.Locator().review_qualityChecks_rules("1","Too many lines"), 5);
	if (assertion == false) {
		report("f","Assertion failed while verifying quality check rules");
	}
	
	 String legthOfSegment_L1=General.driver.findElement(ReviewerLocators.Locator().review_segmentLength_L_multipleLines_L1).getText();
	 String legthOfSegment_L1_numberOnly= legthOfSegment_L1.replaceAll("[^0-9]", "");
	 legthOfSegment_L1_numberOnly=legthOfSegment_L1_numberOnly.substring(1,3);
	 float legthOfSegment_L1_float=Float.parseFloat(legthOfSegment_L1_numberOnly);
	 System.out.println(legthOfSegment_L1_float);
	 
	 String legthOfSegment_L2=General.driver.findElement(ReviewerLocators.Locator().review_segmentLength_L_multipleLines(2)).getText();
	 String legthOfSegment_L2_numberOnly= legthOfSegment_L2.replaceAll("[^0-9]", "");
	 legthOfSegment_L2_numberOnly=legthOfSegment_L2_numberOnly.substring(1,3);
	 float legthOfSegment_L2_float=Float.parseFloat(legthOfSegment_L2_numberOnly);
	 System.out.println(legthOfSegment_L2_float);
	 
	 String legthOfSegment_L3=General.driver.findElement(ReviewerLocators.Locator().review_segmentLength_L_multipleLines(3)).getText();
	 String legthOfSegment_L3_numberOnly= legthOfSegment_L3.replaceAll("[^0-9]", "");
	 legthOfSegment_L3_numberOnly=legthOfSegment_L3_numberOnly.substring(1,3);
	 float legthOfSegment_L3_float=Float.parseFloat(legthOfSegment_L3_numberOnly);
	 System.out.println(legthOfSegment_L3_float);
	 
	 String legthOfSegment_L4=General.driver.findElement(ReviewerLocators.Locator().review_segmentLength_L_multipleLines(4)).getText();
	 String legthOfSegment_L4_numberOnly= legthOfSegment_L4.replaceAll("[^0-9]", "");
	 legthOfSegment_L4_numberOnly=legthOfSegment_L4_numberOnly.substring(1,3);
	 float legthOfSegment_L4_float=Float.parseFloat(legthOfSegment_L4_numberOnly);
	 System.out.println(legthOfSegment_L4_float);
	 
	 //TODO LOGIC OF READING SPEED CALCULATION IS CHANGED FORM 2.5.0 RC1 (No need to add line count)(4-1)
//	 float totalNumberOfCharacterInSegments=legthOfSegment_L1_float+legthOfSegment_L1_float+legthOfSegment_L1_float+legthOfSegment_L1_float+4-1;//4: number of lines -1
//	 System.out.println("totalNumberOfCharacterInSegments-->"+totalNumberOfCharacterInSegments);
	 
	 float totalNumberOfCharacterInSegments=legthOfSegment_L1_float+legthOfSegment_L1_float+legthOfSegment_L1_float+legthOfSegment_L1_float;//4: number of lines -1
	 System.out.println("totalNumberOfCharacterInSegments-->"+totalNumberOfCharacterInSegments);
	
	 String duration =General.driver.findElement(ReviewerLocators.Locator().review_duration(1)).getText();
	 System.out.println("Duration of segment---->"+duration);
	 
	 float duration_float=Float.parseFloat(duration);
	 System.out.println("Duration segment int---->"+duration_float);
	 
	 float readingSpeed=totalNumberOfCharacterInSegments/duration_float;
	 System.out.println(readingSpeed);
	
	 String readingSpeed_x=Float.toString(readingSpeed);
	 System.out.println("readingSpeed_x--->"+readingSpeed_x);
				 
	 assertion = General.driver.findElement(ReviewerLocators.Locator().review_readingSpeed_value(1)).getText().contains(readingSpeed_x.substring(0, 4));
	 if (assertion == false) {
	 report("f","Assertion failed while verifying reading speed  ");
	 }
	 
	//TODO USE ONLY WHEN RESOLUTION ISSUE
//	 Thread.sleep(5000);
//	 General.action().Click(ReviewerLocators.Locator().review_ongoing_tabes_rightArrow);
	 Thread.sleep(1000);
	 Thread.sleep(5000);
	 General.action().Click(ReviewerLocators.Locator().review_ongoing_taskInfo_button);
	 Thread.sleep(1000);
	 
	 
	 assertion =General.driver.findElement(ReviewerLocators.Locator().taskInfo_charPerLine).getText().contains("35");// 35 Given
     if (assertion == false) {
	 report("f","Assertion failed while verifying default value for the 'Max chars per line. ");
			
     }
	
	
	
}


public void assertion() throws Exception {
	assertion =General.driver.findElement(ReviewerLocators.Locator().taskInfo_charPerLine).getText().contains("35");// 35 Given
    if (assertion == false) {
	 report("f","Assertion failed while verifying default value for the 'Max chars per line. ");
			
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
