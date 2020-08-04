package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
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

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This test case verifies that the segments can be splitted into 2 segments by shortcut key Alt+p.
 *
 */ 

public class Sub_1281340 {
	
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1281340"+CommonElements.BROWSER+"B1";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String menu_completed = "Completed";
	String path;
	Boolean assertion = true;
	File filepath1;
	String durationOfSecondSegment_string;
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1281340");
		dataSet.put("TL_test_case_description", "SUB-1281340:In translation and Review - Split a segment into 2 segments");
		dataSet.put("TL_internal_testCase_ID", "1281340");
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
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step5_MetaData_MultiLanguages(CommonElements.action().DATE_OFFSET, SubmissionName, CommonElements.action().COMMON_SOURCE_LANGUAGE, Targetlanguage);
			Thread.sleep(2000);
		
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
			translate_onGoing_submission(SubmissionName,targetlanguage,true,false);
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
	         Reviewer_onGoing_submission(SubmissionName, targetlanguage, false, false);
			 Thread.sleep(2000);
			
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	public void translate_onGoing_submission(String SubmissionName,String target,boolean complete ,boolean back) throws Exception {

		System.out.println("INSIDE Trans_Ongoing_Import_translation_InvalidFile  method()");

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
		
		
		
		 Thread.sleep(5000);
	     List <WebElement> listofIds1= General.action().driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
		 Thread.sleep(1000);
		 System.out.println("No of IDS--------"+listofIds1.size());
		 Thread.sleep(3000);
		  
	    for(int i=1;i<=listofIds1.size();i++){
	        Thread.sleep(2000);
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
	    
	    String timeInOfFristSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText();
	    System.out.println(timeInOfFristSegment);
	    String timeOutOfFristSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(1)).getText();
	    System.out.println(timeOutOfFristSegment);
	    
	    General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
	  	Thread.sleep(1000);
	  	General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
	  	Thread.sleep(1000);
	  	General.action().Enter_keyEnvents(KeyEvent.VK_END);
	  	Thread.sleep(1000);
	  	
	  	for(int i=1;i<=9;i++) {
	  		Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_LEFT);
	        Thread.sleep(1000);
	        robot.keyRelease(KeyEvent.VK_LEFT);
	        Thread.sleep(1000);
	  		
	  	}
	  	
	  	
	  	if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
	  	General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_P);
	  	Thread.sleep(1000);
	  	}else {
	  	Translator.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),Keys.chord(Keys.ALT, "p"));
		Thread.sleep(1000);
	  	}
	  	
	  	Thread.sleep(3000);
	    String timeInOfFristSegment_afterSplit_X=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText();
	    System.out.println(timeInOfFristSegment_afterSplit_X);
	 	Thread.sleep(3000);
	    String timeOutOfFristSegment_afterSplit_X=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(1)).getText();
	    System.out.println(timeOutOfFristSegment_afterSplit_X);
	 	Thread.sleep(3000);
	    String timeInOfSecondSegment_afterSplit_X=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(2)).getText();
	    System.out.println(timeInOfSecondSegment_afterSplit_X);
	 	Thread.sleep(3000);
	    String timeOutOfSecondSegment_afterSplit_X=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(2)).getText();
	    System.out.println(timeOutOfSecondSegment_afterSplit_X);
	 	Thread.sleep(3000);
	    assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText().contains("Jellyfish at the Monterey");
		if (assertion == false) {
			report("f","Assertion failed while verifying reading speed after entering seegment");
		}
	 	Thread.sleep(3000);
	    System.out.println("timeInOfFristSegment--->"+timeInOfFristSegment);
	    System.out.println("timeOutOfFristSegment--->"+timeOutOfFristSegment);
	    assertion = General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText().contains(timeInOfFristSegment);
		if (assertion == false) {
			report("f","Assertion failed while verifying reading speed after entering seegment");
		}
	 	Thread.sleep(3000);
		assertion = General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(2)).getText().contains(timeOutOfFristSegment);
		if (assertion == false) {
				report("f","Assertion failed while verifying reading speed after entering seegment");
		}
	 	Thread.sleep(3000);
		String timeOutOffirstSegmentAfterSplit=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(1)).getText();
		System.out.println("timeOutOffirstSegmentAfterSplit--->"+timeOutOffirstSegmentAfterSplit);  
	 	Thread.sleep(3000);
		String numberOfFrameOfTimeOutOfFirstSegment=timeOutOffirstSegmentAfterSplit.substring(9,11);
		System.out.println("numberOfFrameOfTimeOutOfFirstSegment-->"+numberOfFrameOfTimeOutOfFirstSegment);
	 	Thread.sleep(3000);
		String timeinOfSecondSegmentAfterSplit=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(2)).getText();
		System.out.println("timeinOfSecondSegmentAfterSplit--->"+timeinOfSecondSegmentAfterSplit);  
		String numberOfFrameOftimeinOfSecondSegment=timeinOfSecondSegmentAfterSplit.substring(9,11);
		System.out.println("numberOfFrameOftimeinOfSecondSegment-->"+numberOfFrameOftimeinOfSecondSegment);
		
		Thread.sleep(5000);
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_taskInfo_button);
		Thread.sleep(1000);
		 
		String minCountOfFramesBetweenSubs=General.driver.findElement(TranslatorLocators.Locator().taskInfo_minCountOfFrames).getText();
		minCountOfFramesBetweenSubs= minCountOfFramesBetweenSubs.replaceAll("[^0-9]", "");
		int minCountOfFramesBetweenSubs_int=Integer.parseInt(minCountOfFramesBetweenSubs);
		System.out.println("minCountOfFramesBetweenSubs_int-->"+minCountOfFramesBetweenSubs_int);
				
		
		
		int frameDifference=Integer.parseInt(numberOfFrameOftimeinOfSecondSegment)-Integer.parseInt(numberOfFrameOfTimeOutOfFirstSegment);
		System.out.println("Frame difference between time out of 1st segment and time in of second segment---->"+frameDifference);
		
		
		assertion=minCountOfFramesBetweenSubs_int==frameDifference;
		if (assertion == false) {
			report("f","Assertion failed while verifying frame difference");
		}
		
		assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(2)).getText().contains("Aquarium");
		if (assertion == false) {
			report("f","Assertion failed while verifying reading speed after entering seegment");
		}
		
		
		String redingSpeedOfSplitedSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_ReadingSpeed(2)).getText();
		float redingSpeedOfSplitedSegment_float=Float.parseFloat(redingSpeedOfSplitedSegment);
		System.out.println("redingSpeedOfSplitedSegment_float-->"+redingSpeedOfSplitedSegment_float);
		
		String numberOfCharactersInSplitedSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_segmentLength_L(2)).getText();
		numberOfCharactersInSplitedSegment= numberOfCharactersInSplitedSegment.replaceAll("[^0-9]", "");
		System.out.println(numberOfCharactersInSplitedSegment);
		numberOfCharactersInSplitedSegment=numberOfCharactersInSplitedSegment.substring(1,2);
		int numberOfCharactersInSplitedSegment_int=Integer.parseInt(numberOfCharactersInSplitedSegment);
		System.out.println("numberOfCharactersInSplitedSegment_int-->"+numberOfCharactersInSplitedSegment_int);
		
		float durationOfSecondSegment=numberOfCharactersInSplitedSegment_int/redingSpeedOfSplitedSegment_float;
		System.out.println("durationOfSecondSegment-->"+durationOfSecondSegment);
		
		String durationOfSecondSegment_string=Float.toString(durationOfSecondSegment);
		System.out.println("durationOfSecondSegment_string-->"+durationOfSecondSegment_string);
		
		assertion=durationOfSecondSegment_string.contains("0.70");
		if (assertion == false) {
			report("f","Assertion failed while verifying frame difference");
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
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
		}
		
		}
		
	    
	    if(back){
	    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
				General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
				Thread.sleep(1000);
				General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
		    	}
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
			    
			    Thread.sleep(3000);
			    String timeInOfFristSegment=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeIn(1)).getText();
			    System.out.println(timeInOfFristSegment);
			    Thread.sleep(3000);
			    String timeOutOfFristSegment=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeOut(1)).getText();
			    System.out.println(timeOutOfFristSegment);
			    Thread.sleep(3000);
			    General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
			    Thread.sleep(3000);
			  	General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
			  	Thread.sleep(3000);
				Thread.sleep(1000);
			  	General.action().Enter_keyEnvents(KeyEvent.VK_END);
			  	Thread.sleep(1000);
			  	
			  	for(int i=1;i<=8;i++) {
			  		Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_LEFT);
			        Thread.sleep(1000);
			        robot.keyRelease(KeyEvent.VK_LEFT);
			        Thread.sleep(1000);
			  		
			  	}
			  	
			  	
			  	if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
			  	General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_P);
			  	Thread.sleep(1000);
			  	}else {
			  	Translator.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),Keys.chord(Keys.ALT, "p"));
				Thread.sleep(1000);
			  	}
			  	
			  	Thread.sleep(3000);
			    String timeInOfFristSegment_afterSplit_X=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeIn(1)).getText();
			    System.out.println(timeInOfFristSegment_afterSplit_X);
			    Thread.sleep(3000);
			    String timeOutOfFristSegment_afterSplit_X=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeOut(1)).getText();
			    System.out.println(timeOutOfFristSegment_afterSplit_X);
			    Thread.sleep(3000);
			    String timeInOfSecondSegment_afterSplit_X=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeIn(2)).getText();
			    System.out.println(timeInOfSecondSegment_afterSplit_X);
			    Thread.sleep(3000);
			    String timeOutOfSecondSegment_afterSplit_X=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeOut(2)).getText();
			    System.out.println(timeOutOfSecondSegment_afterSplit_X);
			    Thread.sleep(3000);
			    assertion = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)).getText().contains("Jellyfish at the");
				if (assertion == false) {
					report("f","Assertion failed while verifying reading speed after entering seegment");
				}
				Thread.sleep(3000);
			    assertion = General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeIn(1)).getText().contains(timeInOfFristSegment);
				if (assertion == false) {
					report("f","Assertion failed while verifying reading speed after entering seegment");
				}
				Thread.sleep(3000);
				assertion = General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeOut(2)).getText().contains(timeOutOfFristSegment);
				if (assertion == false) {
						report("f","Assertion failed while verifying reading speed after entering seegment");
				}
				Thread.sleep(3000);
				String timeOutOffirstSegmentAfterSplit_X=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeOut(1)).getText();
				System.out.println("timeOutOffirstSegmentAfterSplit--->"+timeOutOffirstSegmentAfterSplit_X);  
				Thread.sleep(3000);
				String numberOfFrameOfTimeOutOfFirstSegment_X=timeOutOffirstSegmentAfterSplit_X.substring(9,11);
				Thread.sleep(3000);
				System.out.println("numberOfFrameOfTimeOutOfFirstSegment-->"+numberOfFrameOfTimeOutOfFirstSegment_X);
				String timeinOfSecondSegmentAfterSplit_X=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeIn(2)).getText();
				Thread.sleep(3000);
				System.out.println("timeinOfSecondSegmentAfterSplit--->"+timeinOfSecondSegmentAfterSplit_X);  
				String numberOfFrameOftimeinOfSecondSegment_X=timeinOfSecondSegmentAfterSplit_X.substring(9,11);
				Thread.sleep(3000);
				System.out.println("numberOfFrameOftimeinOfSecondSegment-->"+numberOfFrameOftimeinOfSecondSegment_X);
				
				
				General.action().Click(ReviewerLocators.Locator().review_ongoing_taskInfo_button);
				Thread.sleep(1000);
				 
				String minCountOfFramesBetweenSubs=General.driver.findElement(ReviewerLocators.Locator().taskInfo_minCountOfFrames).getText();
				Thread.sleep(3000);
				minCountOfFramesBetweenSubs= minCountOfFramesBetweenSubs.replaceAll("[^0-9]", "");
				int minCountOfFramesBetweenSubs_int=Integer.parseInt(minCountOfFramesBetweenSubs);
				System.out.println("minCountOfFramesBetweenSubs_int-->"+minCountOfFramesBetweenSubs_int);
						
				
				
				int frameDifference=Integer.parseInt(numberOfFrameOftimeinOfSecondSegment_X)-Integer.parseInt(numberOfFrameOfTimeOutOfFirstSegment_X);
				System.out.println("Frame difference between time out of 1st segment and time in of second segment---->"+frameDifference);
				Thread.sleep(3000);
				
				assertion=minCountOfFramesBetweenSubs_int==frameDifference;
				if (assertion == false) {
					report("f","Assertion failed while verifying frame difference");
				}
				
				assertion = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2)).getText().contains("Monterey");
				if (assertion == false) {
					report("f","Assertion failed while verifying reading speed after entering seegment");
				}
				Thread.sleep(3000);
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
				Thread.sleep(1000);
				
				String redingSpeedOfSplitedSegment=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_ReadingSpeed(2)).getText();
				Thread.sleep(3000);
				float redingSpeedOfSplitedSegment_float=Float.parseFloat(redingSpeedOfSplitedSegment);
				System.out.println("redingSpeedOfSplitedSegment_float-->"+redingSpeedOfSplitedSegment_float);
				Thread.sleep(4000);
				String numberOfCharactersInSplitedSegment=General.driver.findElement(ReviewerLocators.Locator().review_segmentLength_L(2)).getText();
				Thread.sleep(3000);
				String numberOfCharactersInSplitedSegment_X= numberOfCharactersInSplitedSegment.replaceAll("[^0-9]", "");
				Thread.sleep(3000);
				System.out.println("numberOfCharactersInSplitedSegment_X"+numberOfCharactersInSplitedSegment_X);
				System.out.println("numberOfCharactersInSplitedSegment"+numberOfCharactersInSplitedSegment);  
				String numberOfCharactersInSplitedSegment_X_X=numberOfCharactersInSplitedSegment_X.substring(1,2);
				Thread.sleep(3000);
				int numberOfCharactersInSplitedSegment_int=Integer.parseInt(numberOfCharactersInSplitedSegment_X_X);
				System.out.println("numberOfCharactersInSplitedSegment_int-->"+numberOfCharactersInSplitedSegment_int);
				Thread.sleep(3000);
				float durationOfSecondSegment=numberOfCharactersInSplitedSegment_int/redingSpeedOfSplitedSegment_float;
				System.out.println("durationOfSecondSegment-->"+durationOfSecondSegment);
				Thread.sleep(3000);
				durationOfSecondSegment_string=Float.toString(durationOfSecondSegment);
				System.out.println("durationOfSecondSegment_string-->"+durationOfSecondSegment_string);
				Thread.sleep(3000);
				assertion=durationOfSecondSegment_string.contains("0.60");
				if (assertion == false) {
					report("f","Assertion failed while verifying frame difference");
				}
				
				 
				 
				 
	}
	
	
	
	public void assertion() throws Exception {
		assertion=durationOfSecondSegment_string.contains("0.60");
		if (assertion == false) {
			report("f","Assertion failed while verifying frame difference");
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