package subtitler.Linguist.Open_Job;

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

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: Display in red the segment's time in if the minimum number of frames between subtitles is not respected.
 *Preconditions:Use a SRT file to create a submission such as some segments/subtitles do not have minimum frames between the them.
 */ 

public class Sub_1644893 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_1644893"+CommonElements.BROWSER+"A9I";
	String targetlanguage_1 = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String durationColor_review;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_1644893");
		dataSet.put("TL_test_case_description", "Sub_1644893:Translation & Review - Display in red the segment's time in if the minimum number of frames between subtitles is not respected");
		dataSet.put("TL_internal_testCase_ID", "1644893");
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
			Thread.sleep(20000);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, true);
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
            
           // tran login
		   General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
		   Thread.sleep(20000);
           Translator.action().Navigate(menu_to_claim);
           Thread.sleep(1000);
           Translator.action().trans_ToClaim(SubmissionName);
		   Thread.sleep(2000);
		   PM_user.action().Navigate(menu_ongoing);
		   Thread.sleep(2000);
		   translate_onGoing_submission(SubmissionName, targetlanguage_1, true, false);
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
		    Reviewer_onGoing_submission(SubmissionName, targetlanguage_1, false, false);
		    Thread.sleep(2000);
			

     } catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

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
			
				  Thread.sleep(5000);
			      List <WebElement> listofIds1= General.action().driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
				  Thread.sleep(1000);
				  System.out.println("No of IDS--------"+listofIds1.size());
				  Thread.sleep(3000);
				   
		    Thread.sleep(1000);
		   // General.action().Click(TranslatorLocators.Locator().Trans_ongoing_tabes_rightArrow);
			Thread.sleep(2000);	  
			General.action().Click(TranslatorLocators.Locator().Trans_ongoing_taskInfo_button);
			Thread.sleep(2000);
					
			String minCountOfFramesBetweenSubs=General.driver.findElement(TranslatorLocators.Locator().taskInfo_minCountOfFramesBetweenSubs).getText();
			System.out.println("minCountOfFramesBetweenSubs--->"+minCountOfFramesBetweenSubs);
			String minCountOfFramesBetweenSubs_number=minCountOfFramesBetweenSubs.replaceAll("[^0-9]", "");
			System.out.println("minCountOfFramesBetweenSubs_number-->"+minCountOfFramesBetweenSubs_number);	  
				  
			String timeInOfFristSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(1)).getText();
			System.out.println(timeInOfFristSegment);
			String timeOutOfFristSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(1)).getText();
			System.out.println(timeOutOfFristSegment);
			
			String timeInOfSecondSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(2)).getText();
			System.out.println(timeInOfSecondSegment);
			String timeOutOfSecondSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(2)).getText();
			System.out.println(timeOutOfSecondSegment);
			
			String timeInOfThirdSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(3)).getText();
			System.out.println(timeInOfThirdSegment);
			String timeOutOfThirdSegment=General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeOut(3)).getText();
			System.out.println(timeOutOfThirdSegment);
			
			String numberOfFrames_timeOut_firstSegment=getNumberOfFrames(timeOutOfFristSegment);
			System.out.println("numberOfFrames_timeOut_firstSegment->"+numberOfFrames_timeOut_firstSegment);
			
			String numberOfFrames_timeIn_SecondSegment=getNumberOfFrames(timeInOfSecondSegment);
			System.out.println("numberOfFrames_timeIn_SecondSegment->"+numberOfFrames_timeIn_SecondSegment);
			String numberOfFrames_timeOut_secondSegment=getNumberOfFrames(timeOutOfSecondSegment);
			System.out.println("numberOfFrames_timeOut_secondSegment->"+numberOfFrames_timeOut_secondSegment);
			
			String numberOfFrames_timeIn_thirdSegment=getNumberOfFrames(timeInOfThirdSegment);
			System.out.println("numberOfFrames_timeIn_thirdSegment->"+numberOfFrames_timeIn_thirdSegment);
			String numberOfFrames_timeOut_thirdSegment=getNumberOfFrames(timeOutOfThirdSegment);
			System.out.println("numberOfFrames_timeOut_thirdSegment->"+numberOfFrames_timeOut_thirdSegment);
			
			if(Integer.parseInt(numberOfFrames_timeOut_firstSegment)-Integer.parseInt(numberOfFrames_timeIn_SecondSegment)>=Integer.parseInt(minCountOfFramesBetweenSubs_number))
			{
			System.out.println("Frame difference betwen 1st segment and second segment is > or equal to 4");
				
			} else {
			System.out.println("Frame difference betwen 1st segment and second segment is <  to 4");
			String durationColor = General.driver.findElement(TranslatorLocators.Locator().Trans_ongoing_TimeIn(2)).getCssValue("color");
			System.out.println("durationColor:-"+ durationColor);
			
			if(CommonElements.BROWSER.contains("_CHROME_")||CommonElements.BROWSER.contains("_EGCH_")){
			assertion=durationColor.equalsIgnoreCase("rgba(255, 79, 100, 1)");
			if (assertion == false) {
			report("f","Assertion failed while verifying color of duration");
			}else {
			assertion=durationColor.equalsIgnoreCase("rgba(255, 79, 100, 1)");
			if (assertion == false) {
			report("f","Assertion failed while verifying color of duration");
			}
			}
				
			}
				
				
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
			    
				 System.out.println("EOM trans_Ongoing  method()");
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
				 
//		General.action().Click(ReviewerLocators.Locator().review_ongoing_tabes_rightArrow);
//		Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().review_ongoing_taskInfo_button);
		Thread.sleep(2000);
		
		
		String minCountOfFramesBetweenSubs=General.driver.findElement(ReviewerLocators.Locator().taskInfo_minCountOfFramesBetweenSubs).getText();
		System.out.println("minCountOfFramesBetweenSubs--->"+minCountOfFramesBetweenSubs);
		String minCountOfFramesBetweenSubs_number=minCountOfFramesBetweenSubs.replaceAll("[^0-9]", "");
		System.out.println("minCountOfFramesBetweenSubs_number-->"+minCountOfFramesBetweenSubs_number);	  
			  
		String timeInOfFristSegment=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeIn(1)).getText();
		System.out.println(timeInOfFristSegment);
		String timeOutOfFristSegment=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeOut(1)).getText();
		System.out.println(timeOutOfFristSegment);
		
		String timeInOfSecondSegment=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeIn(2)).getText();
		System.out.println(timeInOfSecondSegment);
		String timeOutOfSecondSegment=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeOut(2)).getText();
		System.out.println(timeOutOfSecondSegment);
		
		String timeInOfThirdSegment=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeIn(3)).getText();
		System.out.println(timeInOfThirdSegment);
		String timeOutOfThirdSegment=General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeOut(3)).getText();
		System.out.println(timeOutOfThirdSegment);
		
		String numberOfFrames_timeOut_firstSegment=getNumberOfFrames(timeOutOfFristSegment);
		System.out.println("numberOfFrames_timeOut_firstSegment->"+numberOfFrames_timeOut_firstSegment);
		
		String numberOfFrames_timeIn_SecondSegment=getNumberOfFrames(timeInOfSecondSegment);
		System.out.println("numberOfFrames_timeIn_SecondSegment->"+numberOfFrames_timeIn_SecondSegment);
		String numberOfFrames_timeOut_secondSegment=getNumberOfFrames(timeOutOfSecondSegment);
		System.out.println("numberOfFrames_timeOut_secondSegment->"+numberOfFrames_timeOut_secondSegment);
		
		String numberOfFrames_timeIn_thirdSegment=getNumberOfFrames(timeInOfThirdSegment);
		System.out.println("numberOfFrames_timeIn_thirdSegment->"+numberOfFrames_timeIn_thirdSegment);
		String numberOfFrames_timeOut_thirdSegment=getNumberOfFrames(timeOutOfThirdSegment);
		System.out.println("numberOfFrames_timeOut_thirdSegment->"+numberOfFrames_timeOut_thirdSegment);
		
		if(Integer.parseInt(numberOfFrames_timeOut_firstSegment)-Integer.parseInt(numberOfFrames_timeIn_SecondSegment)>=Integer.parseInt(minCountOfFramesBetweenSubs_number))
		{
		System.out.println("Frame difference betwen 1st segment and second segment is > or equal to 4");
			
		} else {
			
		System.out.println("Frame difference betwen 1st segment and second segment is <  to 4");
		durationColor_review = General.driver.findElement(ReviewerLocators.Locator().review_ongoing_TimeIn(2)).getCssValue("color");
		System.out.println("durationColor_review:-"+ durationColor_review);
			
		}
		
		
		
	}
	public String  getNumberOfFrames(String time) {
		String countOfFramesBetweenSubs= time.substring(9,11);
		return countOfFramesBetweenSubs;
		
	}
	
	public void assertion() throws Exception {
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("_EGCH_")){
		assertion=durationColor_review.equalsIgnoreCase("rgba(255, 79, 100, 1)");
		if (assertion == false) {
		report("f","Assertion failed while verifying color of duration");
		}else {
			report("p", "All Assertion passed.");
		}
		}
		
		if(CommonElements.BROWSER.contains("FIREFOX")){
			assertion=durationColor_review.equalsIgnoreCase("rgb(255, 79, 100)");
			if (assertion == false) {
			report("f","Assertion failed while verifying color of duration");
			}else {
				report("p", "All Assertion passed.");
			}
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

