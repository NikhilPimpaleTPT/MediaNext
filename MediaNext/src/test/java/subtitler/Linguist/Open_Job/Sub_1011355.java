package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

/**
 * 
 * @author pvaidya
 * This Test Case Is To Edit time-in / time-out of a segment
 */

import java.io.File;
import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.RobotExt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

public class Sub_1011355 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String SubmissionName = "SUB_1011355"+CommonElements.BROWSER+"U1";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String ReviewGroupName = "Common_group";
	
	
	
	//Values to Verify Overlap Massage
	String TimeInDateValue[]= {"09","09"};
    String DateValue1[]= {"09","08","11","04","16","28"};
    String DateValue2[]= {"18","28","11","28"};
	
	//Variables for Time In Slot
	
	String TimeInSlotSecond="NewTimeInSeconds";
	String TimeInSlotMilliSecond="NewTimeInFrames";
	
	//Variables for Time Out Slot
	
	String TimeOutSlotSecond="NewTimeOutSeconds";
	String TimeOutSlotMilliSecond="NewTimeOutFrames";
	
	//For dialogueBoxTime
	String timeInOfSixSegment;
	String timeInOfSixSegment_sec;
	String timeInOfSixSegment_milliSec;
	String timeOutOfSixSegment;
	String timeOutOfSixSegment_sec;
	String timeOutOfSixSegment_milliSec;
	
	

	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_1011355");
		dataSet.put("TL_test_case_description", "Sub_1011355:Edit time-in / time-out of a segment");
		dataSet.put("TL_internal_testCase_ID", "1011355");
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

						// trans login
						General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
						Thread.sleep(1000);
						
						EditTransAndReviewMethod(true , false);
						
						assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOut_smallerThan_timeIn, 5);
						if (assertion == false) {
							report("f","Assertion failed while verifying Massage When Time In > Time Out");
						}
						
						 General.action().waitforelemenetpresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_CancelBtn);
					     Thread.sleep(1000);
						 General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_CancelBtn);
						 Thread.sleep(3000);
						 //TODO Not needed as per modification in TC
//						General.action().waitforelemenetpresent(CommonLocartors.Locator().EditSegment_textarea(3));
//					     Thread.sleep(1000);
//						 General.action().Click(CommonLocartors.Locator().EditSegment_textarea(3));
//						 Thread.sleep(3000);
//						 
//						 General.action().Click(CommonLocartors.Locator().ongoing_EditTimeInOut_ToolTip);
//						 Thread.sleep(3000);
//						 
//						 General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
//						 Thread.sleep(3000);
//						 
//						 assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOut_smallerThan_timeIn, 5);
//						 if (assertion == false) {
//								report("f","Assertion failed while verifying Massage When Time In > Time Out");
//						 }
							
						 
						 
					      CompleteTaskForTransAndReviewAnd();
						
					    Thread.sleep(2000);
						General.action().logoutMethod();
						
						General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
						Thread.sleep(1000);
						
						EditTransAndReviewMethod(false , true);
						
					} catch (Exception e) {
						report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
					}
				}
	
	
	
public void EditTransAndReviewMethod(boolean trans,boolean review) throws Exception {
		
		
		Translator.action().Navigate(menu_to_claim);
		Thread.sleep(1000);
		Translator.action().trans_ToClaim(SubmissionName);
		Thread.sleep(2000);
        Translator.action().Navigate(menu_ongoing);
        Thread.sleep(2000);
        Translator.action().SearchSubmisson_andCheck(SubmissionName);
        Thread.sleep(2000);
		General.action().waitforelemenetpresent(CommonLocartors.Locator().Submission_Edit_icon);
		Thread.sleep(2000);
		General.action().Click(CommonLocartors.Locator().Submission_Edit_icon);
		Thread.sleep(10000);
				
		General.action().waitforelemenetpresent(CommonLocartors.Locator().EditSegment_textarea(1));
        Thread.sleep(1000);
	    General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
	    Thread.sleep(3000);
	     
	  //TODO BELOW CODE IS COMMENTTED BECAUSE OF THE CHNAGES IN LOCATORS FROM MEDIA.NEXT_2.2.0_RC1
//	     assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_EditTimeInOut_ToolTip, 5);
//			if (assertion == false) {
//				report("f","Assertion failed while verifying Time In Out ToolTip");
//		}
			
		General.action().mouseOver(TranslatorLocators.Locator().trans_ongoing_editTime_icon);	
		Thread.sleep(5000);
		//TODO Not in use
//		assertion = Verify.action().verifyTextPresent("Edit time-in / time-out", 5);
//		if (assertion == false) {
//			report("f","Assertion failed while verifying tool tip for the Edit Time icon");
//		}	
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_ongoing_editTime_tooltip, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Time In Out ToolTip");
	}
		
		String DefaultTimeInValue1= General.driver.findElement(CommonLocartors.Locator().ongoing_EditTimeInOut_DefaultValue(1)).getText();
		System.out.println("Time In Default Value Is"+DefaultTimeInValue1);
		
		General.action().type(CommonLocartors.Locator().EditSegment_textarea(1),Keys.chord(Keys.ALT, "t"));
		Thread.sleep(3000);	
		
		General.action().waitforelemenetpresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
	    Thread.sleep(1000);
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
		Thread.sleep(3000);
			
		String DefaultTimeInValue2= General.driver.findElement(CommonLocartors.Locator().ongoing_EditTimeInOut_DefaultValue(1)).getText();
		System.out.println("Time In Default Value Is"+DefaultTimeInValue2);	
		
	    assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_EditTimeInOut_DefaultValueText(DefaultTimeInValue1), 5);
		if (assertion == false) {
				report("f","Assertion failed while verifying Time In Out ToolTip");
		}
		
		String DefaultTimeInValue3= General.driver.findElement(CommonLocartors.Locator().ongoing_EditTimeInOut_DefaultValue(2)).getText();
		System.out.println("Time In Default Value Is"+DefaultTimeInValue3);	
		
		General.action().waitforelemenetpresent(CommonLocartors.Locator().EditSegment_textarea(1));
        Thread.sleep(1000);
	    General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
	    Thread.sleep(3000);
		
		General.action().type(CommonLocartors.Locator().EditSegment_textarea(1),Keys.chord(Keys.ALT, "t"));
		Thread.sleep(3000);	
		
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
	    Thread.sleep(3000);
	    General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
	    Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.BACK_SPACE));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.BACK_SPACE));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),TimeInDateValue[0]);
	    Thread.sleep(2000);
			 
	    General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond));
	    Thread.sleep(3000);
	    General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),TimeInDateValue[1]);
		
		General.action().waitforelemenetpresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
		Thread.sleep(1000);
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
		Thread.sleep(3000);
		 
		
		assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_EditTimeInOut_DefaultValueText(TimeInDateValue[0]+":"+TimeInDateValue[1]), 5);
			if (assertion == false) {
					report("f","Assertion failed while verifying Time In Out ToolTip");
	    }
			
		assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_EditTimeInOut_DefaultValueText(DefaultTimeInValue3), 5);
			if (assertion == false) {
					report("f","Assertion failed while verifying Time In Out ToolTip");
	    }
			
		// To Change Time In and Time out for 4th Segment.
		General.action().waitforelemenetpresent(CommonLocartors.Locator().EditSegment_textarea(4));
	    Thread.sleep(1000);
		General.action().Click(CommonLocartors.Locator().EditSegment_textarea(4));
		Thread.sleep(3000);
		
	   
		General.action().type(CommonLocartors.Locator().EditSegment_textarea(1),Keys.chord(Keys.ALT, "t"));
		Thread.sleep(3000);	
		
		
		// To Change Time In and Time out for 1st Segment.
		 
	     General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond));
	     Thread.sleep(3000);
	     General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),DateValue1[0]);
		 Thread.sleep(2000);
		 
		 
		 General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond));
	     Thread.sleep(3000);
	     General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),DateValue1[1]);
		 Thread.sleep(2000);
		 
		 General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond));
	     Thread.sleep(3000);
	     General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),DateValue1[2]);
		 Thread.sleep(2000);
		    
		 General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond));
	     Thread.sleep(3000);
	     General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),DateValue1[3]);
		 Thread.sleep(2000);
	     Thread.sleep(1000);
		     
		     
		 General.action().waitforelemenetpresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
	     Thread.sleep(1000);
		 General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
		 Thread.sleep(3000);
		       
		// To Change Time In and Time out for 2nd Segment and To Verify Overlap Massage After Changing Time Values  
		 
		 General.action().waitforelemenetpresent(CommonLocartors.Locator().EditSegment_textarea(2));
         Thread.sleep(1000);
	     General.action().Click(CommonLocartors.Locator().EditSegment_textarea(2));
	     Thread.sleep(3000);
	       
	     General.action().type(CommonLocartors.Locator().EditSegment_textarea(2),Keys.chord(Keys.ALT, "t"));
		 Thread.sleep(3000);
		   
		 General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
	     Thread.sleep(3000);
	     General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
	     Thread.sleep(3000);
	     General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
	     General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),DateValue1[2]);
		 Thread.sleep(2000);
			 
			 
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
        Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
	    Thread.sleep(3000);
	    General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),DateValue1[3]);
	    Thread.sleep(2000);
			 
	    General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
	    Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),Keys.chord(Keys.BACK_SPACE));
	    Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),Keys.chord(Keys.BACK_SPACE));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),DateValue1[4]);
		Thread.sleep(2000);
			    
		General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
	    Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
		Thread.sleep(3000);
	    General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
		Thread.sleep(3000);
		General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),DateValue1[5]);
		Thread.sleep(2000);
		
		assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_OverlapErrorMsg, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Overlap Massage");
			
		}
		//Not Needed
//		assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBoxFrameMsg, 5);
//		if (assertion == false) {
//			report("f","Assertion failed while verifying Enough frame Massage");
//			
//		}
		 General.action().waitforelemenetpresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
	     Thread.sleep(1000);
		 General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_UpdateBtn);
		 Thread.sleep(3000);
		 
		 General.action().waitforelemenetpresent(CommonLocartors.Locator().EditSegment_textarea(6));
         Thread.sleep(1000);
	     General.action().Click(CommonLocartors.Locator().EditSegment_textarea(6));
	     Thread.sleep(3000);
	     
	     General.action().type(CommonLocartors.Locator().EditSegment_textarea(6),Keys.chord(Keys.ALT, "t"));
		 Thread.sleep(3000);
	     
	     //To Verify that time code edit box is providing the proper current timecode.
		 
		 if(trans) {
	     
	     timeInOfSixSegment= General.driver.findElement(CommonLocartors.Locator().Trans_ongoing_TimeIn(6)).getText();
		 System.out.println("Time In Default Value Is"+timeInOfSixSegment);	
		 timeInOfSixSegment_sec=timeInOfSixSegment.substring(6,8 );
		 System.out.println("timeInOfSixSegment_sec-->"+timeInOfSixSegment_sec);
		 timeInOfSixSegment_milliSec=timeInOfSixSegment.substring(9,11);
		 System.out.println("timeInOfSixSegment_sec-->"+timeInOfSixSegment_milliSec);
		 
		 timeOutOfSixSegment= General.driver.findElement(CommonLocartors.Locator().Trans_ongoing_TimeOut(6)).getText();
		 System.out.println("Time Out Default Value Is"+timeOutOfSixSegment);
		 timeOutOfSixSegment_sec=timeOutOfSixSegment.substring(6,8 );
		 System.out.println("timeOutOfSixSegment_sec-->"+timeOutOfSixSegment_sec);
		 timeOutOfSixSegment_milliSec=timeOutOfSixSegment.substring(9,11);
		 System.out.println("timeOutOfSixSegment_milliSec-->"+timeOutOfSixSegment_milliSec);
		 }
		 
		 if(review) {
			 
	     timeInOfSixSegment= General.driver.findElement(CommonLocartors.Locator().review_ongoing_TimeIn(6)).getText();
		 System.out.println("Time In Default Value Is"+timeInOfSixSegment);	
         timeInOfSixSegment_sec=timeInOfSixSegment.substring(6,8 );
		 System.out.println("timeInOfSixSegment_sec-->"+timeInOfSixSegment_sec);
		 timeInOfSixSegment_milliSec=timeInOfSixSegment.substring(9,11);
		 System.out.println("timeInOfSixSegment_sec-->"+timeInOfSixSegment_milliSec);
			 
		 timeOutOfSixSegment= General.driver.findElement(CommonLocartors.Locator().review_ongoing_TimeOut(6)).getText();
		 System.out.println("Time Out Default Value Is"+timeOutOfSixSegment);
		 timeOutOfSixSegment_sec=timeOutOfSixSegment.substring(6,8 );
		 System.out.println("timeOutOfSixSegment_sec-->"+timeOutOfSixSegment_sec);
		 timeOutOfSixSegment_milliSec=timeOutOfSixSegment.substring(9,11);
		 System.out.println("timeOutOfSixSegment_milliSec-->"+timeOutOfSixSegment_milliSec);
		 }
		 
		 String timeInOfSixSegment_dialogueBox_sec= General.driver.findElement(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond)).getAttribute("value");
		 System.out.println("Time In sec Value Is"+timeInOfSixSegment_dialogueBox_sec);	
		 String timeInOfSixSegment_dialogueBox_milliSec= General.driver.findElement(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond)).getAttribute("value");
		 System.out.println("Time In millisec Value Is"+timeInOfSixSegment_dialogueBox_milliSec);
		 String timeOutOfSixSegment_dialogueBox_sec= General.driver.findElement(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond)).getAttribute("value");
		 System.out.println("Time out sec Value Is"+timeOutOfSixSegment_dialogueBox_sec);	
		 String timeOutOfSixSegment_dialogueBox_milliSec= General.driver.findElement(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond)).getAttribute("value");
		 System.out.println("Time out millisec Value Is"+timeOutOfSixSegment_dialogueBox_milliSec);
		 
		 assertion = General.driver.findElement(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond)).getAttribute("value").contains(timeInOfSixSegment_sec);
			if (assertion == false) {
				report("f","Assertion failed while verifying dialog box time in sec");
		 }
			
		 assertion = General.driver.findElement(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond)).getAttribute("value").contains(timeInOfSixSegment_milliSec);
		 if (assertion == false) {
				report("f","Assertion failed while verifying dialog box time in MilliSec");
		 }
		 
		 
		 assertion = General.driver.findElement(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond)).getAttribute("value").contains(timeOutOfSixSegment_sec);
			if (assertion == false) {
				report("f","Assertion failed while verifying dialog box time OUT sec");
		 }
			
		 assertion = General.driver.findElement(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond)).getAttribute("value").contains(timeOutOfSixSegment_milliSec);
		 if (assertion == false) {
				report("f","Assertion failed while verifying dialog box time OUT MilliSec");
		 }
		 
		 
		 General.action().waitforelemenetpresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_CancelBtn);
	     Thread.sleep(1000);
		 General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_CancelBtn);
		 Thread.sleep(3000);
		
		// To Change Time In and Time out for 3rd Segment and To Verify Massage when Time in > Time Out
		 
		General.action().waitforelemenetpresent(CommonLocartors.Locator().EditSegment_textarea(3));
        Thread.sleep(1000);
	    General.action().Click(CommonLocartors.Locator().EditSegment_textarea(3));
	    Thread.sleep(3000);
	       
	    General.action().type(CommonLocartors.Locator().EditSegment_textarea(3),Keys.chord(Keys.ALT, "t"));
		Thread.sleep(3000);
		
		 General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond));
	     Thread.sleep(3000);
	     General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotSecond),DateValue2[0]);
		 Thread.sleep(2000);
		 
		 
		 General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond));
	     Thread.sleep(3000);
	     General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(TimeInSlotMilliSecond),DateValue2[1]);
		 Thread.sleep(2000);
		 
		 General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond));
	     Thread.sleep(3000);
	     General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotSecond),DateValue2[2]);
		 Thread.sleep(2000);
		    
		 General.action().Click(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond));
	     Thread.sleep(3000);
	     General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),Keys.chord(Keys.ARROW_RIGHT));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),Keys.chord(Keys.BACK_SPACE));
		 Thread.sleep(3000);
		 General.action().type(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(TimeOutSlotMilliSecond),DateValue2[3]);
		 Thread.sleep(2000);
		
		
	}


public void CompleteTaskForTransAndReviewAnd() throws InterruptedException {
		
		System.out.println("INSIDE method CComplete Task For Trans , Review And Transcription()\n"); 
		

	   PM_user.action().waitforelemenetpresent(CommonLocartors.Locator().SubmissionEdit_complete_task);
       Thread.sleep(1000);
       PM_user.action().Click(CommonLocartors.Locator().SubmissionEdit_complete_task);
	   Thread.sleep(1000);

       PM_user.action().waitforelemenetpresent(CommonLocartors.Locator().SubmissionEdit_complete_Task_Alert_button);
       Thread.sleep(1000);
       PM_user.action().Click(CommonLocartors.Locator().SubmissionEdit_complete_Task_Alert_button);
       Thread.sleep(6000);
       
       System.out.println("EOM Complete Task For Trans , Review And Transcription()\n");
		
		
	}
	

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().ongoing_TimeInOutSegmentDialogBox_TimeOut_smallerThan_timeIn, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Massage When Time In > Time Out");
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
