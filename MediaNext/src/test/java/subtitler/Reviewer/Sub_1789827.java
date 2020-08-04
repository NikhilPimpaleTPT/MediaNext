package subtitler.Reviewer;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.gs4tr.qa.util.FileZipUnzipThread;
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
 *Summary: This test case verifies the formatting for all lines in exported SRT/TTML/EBU1 file format
 *
 */

public class Sub_1789827 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String,String> dataset = new LinkedHashMap<String, String>();
	
	String SubmissionName = "SUB_1789827"+CommonElements.BROWSER+"Q4";
	String WorkflowName = "Two_Step_Workflow";
	String OrganizationName = "Subtitle_Common_orgs";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_submission = "Submissions";
	String targetlanguage = "German (Germany)";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String menu_completed = "Completed";
	String defaultValue_maxLinePerSub="2";
	String lengthOfTheSegment;
	String targetTwoLineText = "Target Segment line1\n" + "Target Segment line2_";
	String modifiedTargetTwoLineText= "Modified Target Segment line1\n" + "Modified Target Segmentline2_";
	String targetTwoLineText_f = "Target Segment line1\r\n" + "Target Segment line2_";
	String modifiedTargetTwoLineText_f= "Modified Target Segment line1\r\n" + "Modified Target Segmentline2_";
	File filepath1;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1789827");
		dataset.put("TL_test_case_description", "SUB-1789827:Check formatting for all lines in exported TTML/EBU1 file");
		dataset.put("TL_internal_testCase_ID", "1789827");
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
		PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
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
		translate_onGoing_submission(SubmissionName,targetlanguage,true,false);
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
		 
		 //Verify that formatting is applied to each line of translated text.
		 Reviewer.action().Navigate(menu_completed);
		 Thread.sleep(2000);
		 Thread.sleep(2000);
		 Translator.action().SearchSubmisson_andCheck(SubmissionName);
		 Thread.sleep(2000);
		 FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
		 Thread.sleep(4000);
		 General.action().Click(ReviewerLocators.Locator().review_file_download);
		 Thread.sleep(2000);
		 General.action().Click(ReviewerLocators.Locator().review_FileDownload_SRT);
		 Thread.sleep(7000);
		 if (CommonElements.BROWSER.contains("FIREFOX")) {
		 System.out.println("------THIS IS FIREFOX-----");
		 Thread.sleep(3000);
		 General.action().downloadFileFirefox();
		 Thread.sleep(3000);

			} 
			else {
				System.out.println("------THIS IS CHROME-----");
				Thread.sleep(5000);
			}
			// UNZIP THE FILE
			String foldertoUnZip = FileZipUnzipThread.utils().getFolderName(System.getProperty("user.home") + "\\Downloads\\", "SUB");
			System.out.println("foldertoUnZip--->" + foldertoUnZip);
			System.out.println("Download Path--->" + System.getProperty("user.home") + "\\Downloads\\" + foldertoUnZip);
			General.action().extractFolder(System.getProperty("user.home") + "\\Downloads\\" + foldertoUnZip,System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(1000);

			// GET PATH TILL PARENT FOLDER
			String srt_fileName_withoutExtension = General.action().getZipFilewithoutExtension(System.getProperty("user.home") + "\\Downloads\\");
			System.out.println("foldertoUnZip_new--->" + srt_fileName_withoutExtension);
			String srt_downloadPath_withoutExtension = System.getProperty("user.home") + "\\Downloads\\"+ srt_fileName_withoutExtension;
			System.out.println("downloadPath_withoutExtension--->" + srt_downloadPath_withoutExtension);

			readSrtFile(srt_downloadPath_withoutExtension);// this method will read srt file from download folder.
			
			System.out.println("srt file complete reading done");
			
			assertion = FileUtils.readFileToString(filepath1, CommonElements.UTF_8).contains("<b>Modified Target Segment line1\n" + 
					"Modified Target Segmentline2_1</b>");
			if (assertion == false) {
				report("f", "Assertion failed while verifying bold SRT File content.");
			}
			assertion = FileUtils.readFileToString(filepath1, CommonElements.UTF_8).contains("<i>Modified Target Segment line1\n" + 
					"Modified Target Segmentline2_2</i>");
			if (assertion == false) {
				report("f", "Assertion failed while verifying italic SRT File content.");
			}
			assertion = FileUtils.readFileToString(filepath1, CommonElements.UTF_8).contains("<u>Modified Target Segment line1\n" + 
					"Modified Target Segmentline2_3</u>");
			if (assertion == false) {
				report("f", "Assertion failed while verifying underlined SRT File content.");
			}
			
			 Translator.action().Navigate(menu_ongoing);
			 Thread.sleep(1000);
			 Translator.action().Navigate(menu_completed);
			 Thread.sleep(2000);
			 Translator.action().SearchSubmisson_andCheck(SubmissionName);
			 Thread.sleep(2000);
			// delete file
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(4000);
			General.action().Click(TranslatorLocators.Locator().Trans_file_download);
			Thread.sleep(2000);
			// download ttml file
			General.action().Click(TranslatorLocators.Locator().Trans_FileDownload_TTML);
			Thread.sleep(7000);
			if (CommonElements.BROWSER.contains("FIREFOX")) {
				System.out.println("------THIS IS FIREFOX-----");
				Thread.sleep(3000);
				General.action().downloadFileFirefox();
				Thread.sleep(3000);

			} else {
				System.out.println("------THIS IS CHROME-----");
				Thread.sleep(5000);
			}
			// UNZIP THE FILE
			System.out.println("inside ttml unzip");
			String foldertoUnZipttml = FileZipUnzipThread.utils().getFolderName(System.getProperty("user.home") + "\\Downloads\\", "SUB");
			System.out.println("inside ttml unzip step 1");
			System.out.println("foldertoUnZip--->" + foldertoUnZipttml);
			System.out.println("Download Path--->" + System.getProperty("user.home") + "\\Downloads\\" + foldertoUnZipttml);
			General.action().extractFolder(System.getProperty("user.home") + "\\Downloads\\" + foldertoUnZipttml,System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(1000);

			// GET PATH TILL PARENT FOLDER
			String ttml_fileName_withoutExtension = General.action().getZipFilewithoutExtension(System.getProperty("user.home") + "\\Downloads\\");
			System.out.println("foldertoUnZip_new--->" + ttml_fileName_withoutExtension);
			String ttml_downloadPath_withoutExtension = System.getProperty("user.home") + "\\Downloads\\"+ ttml_fileName_withoutExtension;
			System.out.println("downloadPath_withoutExtension--->" + ttml_downloadPath_withoutExtension);
			System.out.println("before calling read ttml");

			
			 readTTMLFile(ttml_downloadPath_withoutExtension);// this method will read ttml file from download folder.
			
			//VERIFY TRAGET SEGMENT IS PRESENT IN EBU FILE
				assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"bold\">Modified Target Segment");
				if (assertion == false) {
					report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
					Thread.sleep(2000);
				}
				
				assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"bold\">Modified Target Segmentline2");
				if (assertion == false) {
					report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
					Thread.sleep(2000);
				}
				
				assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"italic\">Modified Target Segment");
				if (assertion == false) {
					report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
					Thread.sleep(2000);
				}
				
				assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"italic\">Modified Target");
				if (assertion == false) {
					report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
					Thread.sleep(2000);
				}
				
				assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"underline\">Modified Target");
				if (assertion == false) {
					report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
					Thread.sleep(2000);
				}
				
				assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"underline\">Modified Target");
				if (assertion == false) {
					report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
					Thread.sleep(2000);
				}
			
			// read EBU file
			// download EBU file

			Translator.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			Translator.action().Navigate(menu_completed);
			Thread.sleep(2000);
			Translator.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			// delete file
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(4000);
			General.action().Click(TranslatorLocators.Locator().Trans_file_download);
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().Trans_FileDownload_EBU);
			Thread.sleep(5000);
			
			if(CommonElements.BROWSER.contains("FIREFOX")) {
		    	System.out.println("------THIS IS FIREFOX-----");
		    	Thread.sleep(2000);
				General.action().downloadFileFirefox();
				Thread.sleep(2000);		
				
				
	    	}else {
	        	System.out.println("------THIS IS CHROME-----");
	    		Thread.sleep(5000);
	    	}
			
			//UNZIP THE FILE
			String foldertoUnZip1 = FileZipUnzipThread.utils().getFolderName(System.getProperty("user.home")+ "\\Downloads\\","SUB");
			System.out.println("foldertoUnZip--->"+foldertoUnZip1);
			System.out.println("Download Path--->"+System.getProperty("user.home")+ "\\Downloads\\"  + foldertoUnZip1);
			General.action().extractFolder(System.getProperty("user.home")+ "\\Downloads\\"+foldertoUnZip1  , System.getProperty("user.home")+ "\\Downloads\\");
		    Thread.sleep(1000);
		    
		    //GET PATH TILL PARENT FOLDER
			String fileName_withoutExtension1 =General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
			System.out.println("foldertoUnZip_new--->"+fileName_withoutExtension1);
			String downloadPath_withoutExtension1=System.getProperty("user.home")+ "\\Downloads\\"+fileName_withoutExtension1;
			System.out.println("downloadPath_withoutExtension--->"+downloadPath_withoutExtension1);
			
			readEBUFile(downloadPath_withoutExtension1);
			Thread.sleep(2000);

			
			//VERIFY TRAGET SEGMENT IS PRESENT IN EBU FILE
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"bold\">Modified Target Segment");
			if (assertion == false) {
				report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
				Thread.sleep(2000);
			}
			
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"bold\">Modified Target Segmentline2");
			if (assertion == false) {
				report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
				Thread.sleep(2000);
			}
			
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"italic\">Modified Target Segment");
			if (assertion == false) {
				report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
				Thread.sleep(2000);
			}
			
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"italic\">Modified Target");
			if (assertion == false) {
				report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
				Thread.sleep(2000);
			}
			
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"underline\">Modified Target");
			if (assertion == false) {
				report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
				Thread.sleep(2000);
			}
			
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"underline\">Modified Target");
			if (assertion == false) {
				report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
				Thread.sleep(2000);
			}
			
			
	
	
	
	} catch (Exception e) {
		report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	}

}
	
	private void translate_onGoing_submission(String submissionName, String target, boolean complete, boolean back) throws Exception {
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
		Thread.sleep(15000);
		
		List<WebElement> listofIds1 = General.driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
		Thread.sleep(1000);
		//int idcount = listofIds1.size();
		//System.out.println("No of IDS--------" + idcount);

		for (int i = 1; i <= listofIds1.size(); i++) {
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(i), targetTwoLineText_f+"target_"+i); 	
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
					General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i), modifiedTargetTwoLineText_f+i); 	
					Thread.sleep(1000);
					
				
			}
			    
			 // apply bold,italic, underline to first 3 segment
			    General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
				Thread.sleep(1000);
				General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),Keys.chord(Keys.CONTROL, "a"));
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(ReviewerLocators.Locator().Text_Formatting_Bold);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().Text_Formatting_Bold);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
				Thread.sleep(1000);

				assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_TargetSegement_textarea_textformat("b", modifiedTargetTwoLineText+1), 5);
				if (assertion == false) {
						report("f", "Assertion failed while verifying target bold text");
					}

				

				General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
				Thread.sleep(2000);
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
				Thread.sleep(2000);
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
				Thread.sleep(2000);
				General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2),Keys.chord(Keys.CONTROL, "a"));
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(ReviewerLocators.Locator().Text_Formatting_Italic);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().Text_Formatting_Italic);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_TargetSegement_textarea_textformat("i", modifiedTargetTwoLineText+2), 5);
				if (assertion == false) {
					report("f", "Assertion failed while verifying target italic text");
				}

			

				General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
				Thread.sleep(1000);
				General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3),
						Keys.chord(Keys.CONTROL, "a"));
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(ReviewerLocators.Locator().Text_Formatting_Underline);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().Text_Formatting_Underline);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
				Thread.sleep(1000);

				assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator()
						.reviewer_TargetSegement_textarea_textformat("u", modifiedTargetTwoLineText+3), 5);
				if (assertion == false) {
					report("f", "Assertion failed while verifying target text");
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
	
	
	
	public void readSrtFile(String downloadPath_withoutExtension) throws Exception {
		try {
			System.out.println("inside read srt");
//			File filepath = new File(System.getProperty("user.home")+ "\\Downloads\\");
			File filepath = new File(downloadPath_withoutExtension);

			String[] filenames = filepath.list();
			int countofFiles = filenames.length;
			System.out.println("countofFiles##########  :- " + countofFiles);

			for (String filename : filenames) {
				System.out.println(filename);

				String path = downloadPath_withoutExtension + "\\" + filename;
				System.out.println("finalpath########### :- " + path);

				BufferedReader reader;

				reader = new BufferedReader(new FileReader(path));
				String line = reader.readLine();
				while (line != null) {
					System.out.println(line);
					// read next line
					line = reader.readLine();
				}
				reader.close();

				filepath1 = new File(downloadPath_withoutExtension + "\\" + filename);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readTTMLFile(String downloadPath_withoutExtension) throws Exception {
		try {

//			File filepath = new File(System.getProperty("user.home")+ "\\Downloads\\");
			File filepath = new File(downloadPath_withoutExtension);

			String[] filenames = filepath.list();
			int countofFiles = filenames.length;
			System.out.println("countofFiles##########  :- " + countofFiles);

			for (String filename : filenames) {
				System.out.println(filename);

				String path = downloadPath_withoutExtension + "\\" + filename;
				System.out.println("finalpath########### :- " + path);

				BufferedReader reader;

				reader = new BufferedReader(new FileReader(path));
				String line = reader.readLine();
				while (line != null) {
					System.out.println(line);
					// read next line
					line = reader.readLine();
				}
				reader.close();

				filepath1 = new File(downloadPath_withoutExtension + "\\" + filename);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readEBUFile(String downloadPath_withoutExtension) throws Exception {
		try {

//			File filepath = new File(System.getProperty("user.home")+ "\\Downloads\\");
			File filepath = new File(downloadPath_withoutExtension);

			String[] filenames = filepath.list();
			int countofFiles = filenames.length;
			System.out.println("countofFiles##########  :- " + countofFiles);

			for (String filename : filenames) {
				System.out.println(filename);

				String path = downloadPath_withoutExtension + "\\" + filename;
				System.out.println("finalpath########### :- " + path);

				BufferedReader reader;

				reader = new BufferedReader(new FileReader(path));
				String line = reader.readLine();
				while (line != null) {
					System.out.println(line);
					// read next line
					line = reader.readLine();
				}
				reader.close();

				filepath1 = new File(downloadPath_withoutExtension + "\\" + filename);
				

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	



	public void assertion() throws Exception {
		System.out.println("inside assertion");		
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"underline\">Modified Target");
		if (assertion == false) {
			report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
			Thread.sleep(2000);
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
	
	
