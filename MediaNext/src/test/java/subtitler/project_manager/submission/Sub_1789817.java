package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.gs4tr.qa.util.FileZipUnzipThread;
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

//author : swati thakare
//This test case verifies the formatting for all lines in exported SRT/TTML/EBU1 file format.

public class Sub_1789817 {
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_1789817" + CommonElements.BROWSER + "D1";
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
	String Input_value_of_TargetSegement_textarea;
	File filepath1;

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_1789817");
		dataSet.put("TL_test_case_description",
				"Sub_1789817: formatting for all lines in exported SRT/TTML/EBU1 file format.");
		dataSet.put("TL_internal_testCase_ID", "1789817");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
		// PM login
		General.action().login(CommonElements.action().PM_username, CommonElements.action().password);
		PM_user.action().Navigate(menu_Submission);
		// TODO NEW IMPL OF SUBMISSION CREATION
		PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
		PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",	TranslatorGroupName, "review", "", true);
		PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH + CommonElements.action().FILE_COMMON_FOLDER + CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false, CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
		PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH + CommonElements.action().FILE_COMMON_FOLDER + CommonElements.action().FILE_COMMON_SRT_FOLDER);
		PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE, CommonElements.action().COMMON_TARGET_LANGUAGE);
		Thread.sleep(2000);
		PM_user.action().SearchSubmisson(SubmissionName);
		Thread.sleep(3000);

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying SubmissionName  after Search");
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

		Trans_ongoing_Text_Formatting(SubmissionName, targetlanguage_1);
		Thread.sleep(2000);
		Translator.action().Navigate(menu_completed);
		Thread.sleep(2000);
		Translator.action().SearchSubmisson_andCheck(SubmissionName);
		Thread.sleep(2000);
		FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
		Thread.sleep(4000);
		General.action().Click(TranslatorLocators.Locator().Trans_file_download);
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().Trans_FileDownload_SRT);
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
		
		assertion = FileUtils.readFileToString(filepath1, CommonElements.UTF_8).contains("<b>its a lock down\n" + 
				"period,stay home</b>");
		if (assertion == false) {
			report("f", "Assertion failed while verifying bold SRT File content.");
		}
		assertion = FileUtils.readFileToString(filepath1, CommonElements.UTF_8).contains("<i>its a lock down\n" + 
				"period,stay home</i>");
		if (assertion == false) {
			report("f", "Assertion failed while verifying italic SRT File content.");
		}
		assertion = FileUtils.readFileToString(filepath1, CommonElements.UTF_8).contains("<i>its a lock down\n" + 
				"period,stay home</i>");
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
		
			 System.out.println("this is chrome");
			 assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"bold\">its a lock down");
			 if(assertion == false) {
			 report("f","Assertion failed while verifying bold ttml File content.");
			 }
			 assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"bold\">period,stay home");
			 if(assertion == false) {
			 report("f","Assertion failed while verifying bold ttml File content.");
			 }
			 assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"italic\">its a lock down");
			 if(assertion == false) {
			 report("f","Assertion failed while verifying italic tml File content."); 
			 }
			 assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"italic\">period,stay home");
			 if(assertion == false) {
			 report("f","Assertion failed while verifying italic tml File content."); 
			 }
			 assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"underline\">its a lock down");
			 if(assertion == false) {
			 report("f","Assertion failed while verifying underlined tml File content.");
			  }
			 assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"underline\">its a lock down");
			 if(assertion == false) {
			 report("f","Assertion failed while verifying underlined tml File content.");
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
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"bold\">its a lock down");
		if (assertion == false) {
			report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
			Thread.sleep(2000);
		}
		
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"bold\">period,stay ");
		if (assertion == false) {
			report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
			Thread.sleep(2000);
		}
		
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"italic\">period,stay ");
		if (assertion == false) {
			report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
			Thread.sleep(2000);
		}
		
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"underline\">its a lock down");
		if (assertion == false) {
			report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
			Thread.sleep(2000);
		}
		
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"underline\">period,stay");
		if (assertion == false) {
			report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
			Thread.sleep(2000);
		}
		
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

	public void Trans_ongoing_Text_Formatting(String SubmissionName, String target) throws Exception {
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_search_input, SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(TranslatorLocators.Locator().check_Trans_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_edit_button);
		Thread.sleep(5000);
		// Enter two lines of translation for each subtitle.
		for (int i = 1; i <= 6; i++) {
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
			Thread.sleep(1000);
			if (CommonElements.BROWSER.contains("FIREFOX")) {
				System.out.println("------THIS IS FIREFOX-----");
				Thread.sleep(3000);
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(i),"its a lock down\r\n" +"period,stay home");
			}
			else
			{	
				System.out.println("------THIS IS chrome-----");
				General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(i),"its a lock down\n" + "period,stay home");
			}
				
		}
		// apply bold,italic, underline to first 3 segment
		General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Text_Formatting_Bold);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Text_Formatting_Bold);
		Thread.sleep(1000);
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_TargetSegement_textarea_textformat("b", "its a lock down\n" + 
				"period,stay home"), 5);
		if (assertion == false) {
				report("f", "Assertion failed while verifying target bold text");
			}
			
		String font_weight = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea_demo(1, "b")).getCssValue("font-weight");
		System.out.println("BOLD TEXT######### :-" + font_weight);

		if (font_weight.contains("700")) {
			System.out.println("FIRST ROW TEXT FORMAT IS BOLD");
		} else {
			System.out.println("FIRST ROW TEXT FORMAT IS NOT BOLD");
		}

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
		Thread.sleep(2000);
		General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(2),Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Text_Formatting_Italic);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Text_Formatting_Italic);
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_TargetSegement_textarea_textformat("i", "its a lock down\n" + 
				"period,stay home"), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying target italic text");
		}

		String font_style = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea_demo(2, "i")).getCssValue("font-style");
		System.out.println("ITALIC TEXT######### :-" + font_style);

		if (font_style.contains("italic")) {System.out.println("SECOND ROW TEXT FORMAT IS ITALIC");
		} else {
			System.out.println("SECOND ROW TEXT FORMAT IS NOT ITALIC");
		}

		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(3));
		Thread.sleep(1000);
		General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(3),
				Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Text_Formatting_Underline);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Text_Formatting_Underline);
		Thread.sleep(1000);

		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator()
				.trans_TargetSegement_textarea_textformat("u", "its a lock down\n" + 
						"period,stay home"), 5);
		if (assertion == false) {
			report("f", "Assertion failed while verifying target text");
		}

		String text_decoration = General.driver
				.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea_demo(3, "u"))
				.getCssValue("text-decoration");
		System.out.println("UNDERLINED TEXT######### :-" + text_decoration);

		if (text_decoration.contains("underline")) {
			System.out.println("THIRD ROW TEXT FORMAT IS UNDERLINED");
		} else {
			System.out.println("THIRD ROW TEXT FORMAT IS NOT UNDERLINED");
		}

		Thread.sleep(3000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_Complete_Button);
		Thread.sleep(1000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
		Thread.sleep(2000);
		General.action().Click(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
		Thread.sleep(3000);

		if (Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)) {
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
			Thread.sleep(1000);
		}

	}

	public void assertion() throws Exception {
		
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("=\"underline\">period,stay");
		if (assertion == false) {
			report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
			Thread.sleep(2000);
		} else {
			report("p", "All Assertion passed.");
		}
	}

	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),
				Subtitler_TestRail_Common_Properties.idTestPlan, Subtitler_TestRail_Common_Properties.idBuild, result,
				Subtitler_TestRail_Common_Properties.assignedTo, notes);
		if (result == "f")
			assertTrue(false);

	}

}
