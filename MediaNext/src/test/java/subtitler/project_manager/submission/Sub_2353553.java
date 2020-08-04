package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

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

/**
 * 
 * @author pvaidya
 *Summary:this test case is to verify reading speed for multi lined subtitles
 *
 */

public class Sub_2353553 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_2353553"+CommonElements.BROWSER+"A3";
	String sourcelanguage = "en-US - English (United States)";
	String Targetlanguage[] = {"de-DE"};
	String targetlanguage = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "transc_Step_Workflow";
	String fileName = "common";
	String menu_submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String folder_2353553="2353553";
	String maxLinePerSub="4";
	String fourLineSegment="Transcription Segment line 1\n" + 
			"Transcription Segment line 2\n" + 
			"Transcription Segment line 3\n" + 
			"Transcription Segment line 4";
	String fourLineSegment_f ="Transcription Segment line 1\r\n" + 
			"Transcription Segment line 2\r\n" + 
			"Transcription Segment line 3\r\n" + 
			"Transcription Segment line 4";
	String readingSpeed_x_final;
	
    
    Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2353553");
		dataSet.put("TL_test_case_description", "SUB_2353553:Reading speed calculation for multi lines subtitles");
		dataSet.put("TL_internal_testCase_ID", "2353553");
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
			PM_user.action().Navigate(menu_submission);
			//TODO NEW IMPL OF SUBMISSION CREATION USING AMPERSAND CHARACTERS
			PM_user.action().CreateSubmisson_Step1_forCustomizedLine("17", "35", "80", "100",maxLinePerSub);
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,"70137");
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+folder_2353553);
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
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			PM_transcription_ongoing(SubmissionName,targetlanguage,false,true);
		    Thread.sleep(2000);
		  

		} catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
        }
	}
	
	
	public void PM_transcription_ongoing(String SubmissionName, String target,boolean complete ,boolean back ) throws Exception {

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
		
		//CLICK ON ENDIT BUTTON
		Thread.sleep(4000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
        Thread.sleep(5000);
        
        //CLICK ON SEGMENT NO 2
        PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1));
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2));
	    Thread.sleep(1000);
	    General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
	    Thread.sleep(1000);
	    General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea);
	    Thread.sleep(2000);
	    if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,fourLineSegment);
		Thread.sleep(2000);
		}else {
		General.action().type(Pm_User_Submission_Locators.Locator().PM_Transcription_Video_textarea,fourLineSegment_f);//fourLineSegment_f (Sometimes work with /n and sometimes needs /r/n)
		Thread.sleep(2000);
		}
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(3));
	    Thread.sleep(1000);
	    System.out.println("four Line Segment"+fourLineSegment);
	    System.out.println("four Line Segment"+fourLineSegment_f);
	    if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
	    assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2)).getText().contains(fourLineSegment);
		if (assertion == false) {
			report("f","Assertion failed while verifying fourLineSegment text");
		}
	    }else {
	    assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(2)).getText().contains(fourLineSegment);
		if (assertion == false) {
		report("f","Assertion failed while verifying fourLineSegment text");
		}
	    }
		
		
		String legthOfSegment_L1=General.driver.findElement(Pm_User_Submission_Locators.Locator().TPM_Transcription_segmentLength_L_multiPleTLines(1)).getText();
		 String legthOfSegment_L1_numberOnly= legthOfSegment_L1.replaceAll("[^0-9]", "");
		 legthOfSegment_L1_numberOnly=legthOfSegment_L1_numberOnly.substring(1,3);
		 float legthOfSegment_L1_float=Float.parseFloat(legthOfSegment_L1_numberOnly);
		 System.out.println(legthOfSegment_L1_float);
		 
		 String legthOfSegment_L2=General.driver.findElement(Pm_User_Submission_Locators.Locator().TPM_Transcription_segmentLength_L_multiPleTLines(2)).getText();
		 String legthOfSegment_L2_numberOnly= legthOfSegment_L2.replaceAll("[^0-9]", "");
		 legthOfSegment_L2_numberOnly=legthOfSegment_L2_numberOnly.substring(1,3);
		 float legthOfSegment_L2_float=Float.parseFloat(legthOfSegment_L2_numberOnly);
		 System.out.println(legthOfSegment_L2_float);
		 
		 String legthOfSegment_L3=General.driver.findElement(Pm_User_Submission_Locators.Locator().TPM_Transcription_segmentLength_L_multiPleTLines(3)).getText();
		 String legthOfSegment_L3_numberOnly= legthOfSegment_L3.replaceAll("[^0-9]", "");
		 legthOfSegment_L3_numberOnly=legthOfSegment_L3_numberOnly.substring(1,3);
		 float legthOfSegment_L3_float=Float.parseFloat(legthOfSegment_L3_numberOnly);
		 System.out.println(legthOfSegment_L3_float);
		 
		 String legthOfSegment_L4=General.driver.findElement(Pm_User_Submission_Locators.Locator().TPM_Transcription_segmentLength_L_multiPleTLines(4)).getText();
		 String legthOfSegment_L4_numberOnly= legthOfSegment_L4.replaceAll("[^0-9]", "");
		 legthOfSegment_L4_numberOnly=legthOfSegment_L4_numberOnly.substring(1,3);
		 float legthOfSegment_L4_float=Float.parseFloat(legthOfSegment_L4_numberOnly);
		 System.out.println(legthOfSegment_L4_float);
		 
		 float totalNumberOfCharacterInSegments=legthOfSegment_L1_float+legthOfSegment_L2_float+legthOfSegment_L3_float+legthOfSegment_L4_float;
		 System.out.println("totalNumberOfCharacterInSegments-->"+totalNumberOfCharacterInSegments);
		 
		 String duration =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_Duration(2)).getText();
		 System.out.println("Duration of segment---->"+duration);
		 
		 float duration_float=Float.parseFloat(duration);
		 System.out.println("Duration segment int---->"+duration_float);
		 
		 float readingSpeed=totalNumberOfCharacterInSegments/duration_float;
		 System.out.println(readingSpeed);
		
		 String readingSpeed_x=Float.toString(readingSpeed);
		 System.out.println("readingSpeed_x--->"+readingSpeed_x);
		 
		 int readingSpeed_x_length=readingSpeed_x.length();
		 readingSpeed_x_final =readingSpeed_x.substring(0, readingSpeed_x_length-4);
		 
		 System.out.println("Final Reading speed------->"+readingSpeed_x_final);
					 
		 assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(2)).getText().contains(readingSpeed_x_final);
		 if (assertion == false) {
		 report("f","Assertion failed while verifying reading speed  ");
		 }
	}

	     
		

	
	
	public void assertion() throws Exception {
		//Verify - sreading speed calculation should be accurate using the formula -(L1+L2+L3...)/duration
		 assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea_ReadingSpeed(2)).getText().contains(readingSpeed_x_final);
		 if (assertion == false) {
		 report("f","Assertion failed while verifying reading speed  ");
		 }else {
				report("p", "All assertion passed");
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
	
