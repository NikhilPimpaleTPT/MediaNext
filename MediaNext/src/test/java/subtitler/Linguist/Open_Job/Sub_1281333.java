package subtitler.Linguist.Open_Job;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
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
 *Summary: This test case verifies that export current target in Translation and Review views
 *
 */ 


public class Sub_1281333 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1281333"+CommonElements.BROWSER+"R2";
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
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1281333");
		dataSet.put("TL_test_case_description", "SUB-1281333:Export current target in Translation and Review views");
		dataSet.put("TL_internal_testCase_ID", "1281333");
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
			Thread.sleep(1000);
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
	         Thread.sleep(1000);
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
		
		General.action().driver.navigate().refresh();
		Thread.sleep(8000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
		Thread.sleep(1000);
		General.action().Enter_keyEnvents(KeyEvent.VK_END);
		Thread.sleep(1000);
		for(int i=1;i<=5;i++) {
	  		Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_LEFT);
	        Thread.sleep(1000);
	        robot.keyRelease(KeyEvent.VK_LEFT);
	        Thread.sleep(1000);
	  		
	  	}
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
		General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_P);
		Thread.sleep(3000);
		}else {
		Translator.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(4),Keys.chord(Keys.ALT, "p"));
		Thread.sleep(1000);
		}
	
		Thread.sleep(9000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(8));
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(8));
		Thread.sleep(1000);
		Translator.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(8),Keys.chord(Keys.END));
		Thread.sleep(1000);
		if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
		General.action().type((TranslatorLocators.Locator().translator_TargetSegement_textarea(8)),"\n next line segment");
		Thread.sleep(2000);
		}else {
		Thread.sleep(1000);
		General.action().Enter_keyEnvents(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		General.action().type((TranslatorLocators.Locator().translator_TargetSegement_textarea(8)),"next line segment");
		Thread.sleep(2000);
		}
		
		
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
		Thread.sleep(1000);
		
		
		//********NOTE*********
		//Downloaded file is moved to folder to check because to read doc file we need to give complete path -C:\Users\pvaidya\Downloads\SUB_2355176_CHROME_F1_en-US_de-DE\SUB_2355176_CHROME_F1-de-DE-spellcheck-20200720-144157.docx
		//In downloaded file path we are getting 20200720-144157 (144157 changing)
		//As we don't know what 144157 is refer to,unable to find exact path
		
		Thread.sleep(2000);
		FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
		Thread.sleep(2000);
		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_multiOption_ExportWord);
		Thread.sleep(1000);
		General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_ExportWord);
		Thread.sleep(1000);
		
		
		//Download file for Different browsers
		General.action ().FileDownloadMethodForDifferentBrowser();
    	
    	//Method To Get File name
    	General.action().GetFileName(System.getProperty("user.home") + "\\Downloads\\");
    	System.out.println("File is downloaded inside folder :"+System.getProperty("user.home") + "\\Downloads\\");
		 
		//To verify audio scripts file downloaded in Lical Drive
		assertion =General.action().DownloadedFile.contains(SubmissionName);
		System.out.println("Assertion Is======>"+assertion);
		if(assertion==false){
			report("f","audio scripts File is not Downloaded in Local Drive.");
		}else{
			System.out.println("audio scripts File is Downloaded in Local Drive.");		
		}
		 
		 
		System.out.println("Downloaded file in TC is:"+General.action().DownloadedFile);
		 
		//Copy file to Test case Data folder
		File file1 = new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile); 
		Thread.sleep(4000);
		 
		System.out.println("data folder file is:"+CommonElements.action().FILE_ABSOLUTE_PATH);
		 
		FileUtil.deleteDir(CommonElements.action().FILE_ABSOLUTE_PATH+"1281333\\trans\\");
		Thread.sleep(2000);
		
		//To move zip file into workspace data folder
		System.out.println("Source File:"+System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile);
		System.out.println("Destination File:"+CommonElements.action().FILE_ABSOLUTE_PATH+"1281333\\trans\\"+General.action().DownloadedFile);
		FileUtils.copyFile(new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile),new File(CommonElements.action().FILE_ABSOLUTE_PATH+"1281333\\trans\\"+General.action().DownloadedFile));
		
		 
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
			    
			    
			    General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
				Thread.sleep(1000);
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
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4));
				Thread.sleep(1000);
				Translator.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4),Keys.chord(Keys.END));
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
				Thread.sleep(3000);
				}else {
				Translator.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(4),Keys.chord(Keys.ALT, "p"));
				Thread.sleep(1000);
					
				}
			    Thread.sleep(5000);
				
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(8));
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(8));
				Thread.sleep(1000);
				General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(8)),"\n next line segment");
				Thread.sleep(2000);
				
				General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_multiOption_icon);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().review_ongoing_multiOption_icon);
				Thread.sleep(1000);
				
				//********NOTE*********
				//Downloaded file is moved to folder to check because to read doc file we need to give complete path -C:\Users\pvaidya\Downloads\SUB_2355176_CHROME_F1_en-US_de-DE\SUB_2355176_CHROME_F1-de-DE-spellcheck-20200720-144157.docx
				//In downloaded file path we are getting 20200720-144157 (144157 changing)
				//As we don't know what 144157 is refer to,unable to find exact path
				
				Thread.sleep(2000);
				FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
				Thread.sleep(2000);
				General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_multiOption_ExportWord);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().review_ongoing_multiOption_ExportWord);
				Thread.sleep(1000);
				
				
				//Download file for Different browsers
				General.action ().FileDownloadMethodForDifferentBrowser();
		    	
		    	//Method To Get File name
		    	General.action().GetFileName(System.getProperty("user.home") + "\\Downloads\\");
		    	System.out.println("File is downloaded inside folder :"+System.getProperty("user.home") + "\\Downloads\\");
				 
				//To verify audio scripts file downloaded in Lical Drive
				assertion =General.action().DownloadedFile.contains(SubmissionName);
				System.out.println("Assertion Is======>"+assertion);
				if(assertion==false){
					report("f","audio scripts File is not Downloaded in Local Drive.");
				}else{
					System.out.println("audio scripts File is Downloaded in Local Drive.");		
				}
				 
				 
				System.out.println("Downloaded file in TC is:"+General.action().DownloadedFile);
				 
				//Copy file to Test case Data folder
				File file1 = new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile); 
				Thread.sleep(4000);
				 
				System.out.println("data folder file is:"+CommonElements.action().FILE_ABSOLUTE_PATH);
				 
				FileUtil.deleteDir(CommonElements.action().FILE_ABSOLUTE_PATH+"1281333\\review\\");
				Thread.sleep(2000);
				
				//To move zip file into workspace data folder
				System.out.println("Source File:"+System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile);
				System.out.println("Destination File:"+CommonElements.action().FILE_ABSOLUTE_PATH+"1281333\\review\\"+General.action().DownloadedFile);
				FileUtils.copyFile(new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile),new File(CommonElements.action().FILE_ABSOLUTE_PATH+"1281333\\review\\"+General.action().DownloadedFile));
			    
			   
			    
				 System.out.println("EOM trans_Ongoing  method()");
	}

	
public void readWordFile(String completeDocFilePath) {
		
		try {
			
			System.out.println("Inside Method readWordFile");
			
			File file = null;
	        WordExtractor extractor = null;
	        try
	        {

	            file = new File(completeDocFilePath);
	            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
	            HWPFDocument document = new HWPFDocument(fis);
	            extractor = new WordExtractor(document);
	            String[] fileData = extractor.getParagraphText();
	            for (int i = 0; i < fileData.length; i++)
	            {
	                if (fileData[i] != null)
	                    System.out.println(fileData[i]);
	            }
	        }
	        catch (Exception exep)
	        {
	            exep.printStackTrace();
	        }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("EOD Method readWordFile");
	}

public File extractFolder(String zipFile,String destPath) throws Exception 
{
    //System.out.println(zipFile);
    int BUFFER = 2048;
    String newPath="";
    File file = new File(zipFile);
    File filepath1 = null;

    ZipFile zip = new ZipFile(file);
    System.out.println("zipFile-->"+zipFile);
    System.out.println("zipFile.lastIndexOf(\"/\")-->"+zipFile.lastIndexOf("/"));
    System.out.println("zipFile.length()-->"+zipFile.length());

//     newPath = zipFile.substring(zipFile.lastIndexOf("/"), zipFile.length() - 4);
    newPath = General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
    System.out.println("newPath--->"+newPath);
    
   // System.out.println(zipFile.length());
    File destfile=new File(destPath);
    //System.out.println(newPath);
    destPath=destPath+"/"+newPath;

    new File(destPath).mkdir();
    //destfile
    Enumeration zipFileEntries = zip.entries();

    // Process each entry
    while (zipFileEntries.hasMoreElements())
    {
        // grab a zip file entry
        ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
        String currentEntry = entry.getName();
        filepath1 = new File(destPath, currentEntry);
        //destFile = new File(newPath, destFile.getName());
        File destinationParent = filepath1.getParentFile();

        // create the parent directory structure if needed
        destinationParent.mkdirs();

        if (!entry.isDirectory())
        {
            BufferedInputStream is = new BufferedInputStream(zip
            .getInputStream(entry));
            int currentByte;
            // establish buffer for writing file
            byte data[] = new byte[BUFFER];

            // write the current file to disk
            System.out.println("destFile--->"+filepath1);
            FileOutputStream fos = new FileOutputStream(filepath1);
            BufferedOutputStream dest = new BufferedOutputStream(fos,
            BUFFER);

            // read and write until last byte is encountered
            while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                dest.write(data, 0, currentByte);
            }
            
            dest.flush();
            dest.close();
            is.close();
        }

        if (currentEntry.endsWith(".zip"))
        {
             //found a zip file, try to open
            //extractFolder();
        }
    }
    
    System.out.println("Unzip Complete");
	
	return filepath1;
}

	
	
	
	public void assertion() throws Exception {
		
		//This assertion is blocked as we can find the exact file path to read data.Also after reading data it is not displaying in proper format to verify its contents 
		//****Not**** : Verify step 4 form C1281333 in downloaded files form data folder 1281333
		assertion =General.action().DownloadedFile.contains(SubmissionName);
		System.out.println("Assertion Is======>"+assertion);
		if(assertion==false){
			report("f","audio scripts File is not Downloaded in Local Drive.");
		}else {
			report("b", "This Assertion is blocked");
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