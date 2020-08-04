package subtitler.Reviewer;

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
 *Summary:This test case is to verify line breaks should be converted to spaces for 2 line subtitle in Trans/Review.
 *
 */

public class Sub_2355176 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_2355176"+CommonElements.BROWSER+"F4";
	String WorkflowName = "Two_Step_Workflow";
	String OrganizationName = "Subtitle_Common_orgs";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_submission = "Submissions";
	String targetlanguage = "German (Germany)";
	String menu_to_claim = "To Claim";
    String menu_ongoing = "Ongoing";
    String fourLineSegment="Transcription Segment line 1\n" + 
			"Transcription Segment line 2";
	String fourLineSegment_f ="Transcription Segment line 1\r\n" + 
			"Transcription Segment line 2";
    
Boolean assertion = true;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2355176");
		dataSet.put("TL_test_case_description", "SUB-2355176:Export current target in Translation and Review views.");
		dataSet.put("TL_internal_testCase_ID", "2355176");
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
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
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
			translate_onGoing_submission(SubmissionName,targetlanguage,true,false);
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
			Thread.sleep(8000);
			
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
			
			
			//CLICK ON SEGMENT NO 1 
	        PM_user.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		    Thread.sleep(1000);
		    General.action().ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
		    Thread.sleep(2000);
		    if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),fourLineSegment+" Seg 1");
			Thread.sleep(2000);
			}else {
			General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),fourLineSegment_f+" Seg 1");
			Thread.sleep(2000);
			}
			
			

			General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
			Thread.sleep(1000);
			General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
			Thread.sleep(1000);
			
			//********NOTE*********
			//Downloaded file is moved to folder to check because to read doc file we need to give complete path -C:\Users\pvaidya\Downloads\SUB_2355176_CHROME_F1_en-US_de-DE\SUB_2355176_CHROME_F1-de-DE-spellcheck-20200720-144157.docx
			//In downloaded file pass we are getting 20200720-144157 (144157 changing)
			//As we dont know what 144157 is refer to unable to find exact path
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
			 
			FileUtil.deleteDir(CommonElements.action().FILE_ABSOLUTE_PATH+"2355176\\trans\\");
			Thread.sleep(2000);
			
			//To move zip file into workspace data folder
			System.out.println("Source File:"+System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile);
			System.out.println("Destination File:"+CommonElements.action().FILE_ABSOLUTE_PATH+"2355176\\trans\\"+General.action().DownloadedFile);
			FileUtils.copyFile(new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile),new File(CommonElements.action().FILE_ABSOLUTE_PATH+"2355176\\trans\\"+General.action().DownloadedFile));
			
				  
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
		
		//CLICK ON SEGMENT NO 1 
        PM_user.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		Thread.sleep(2000);
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
	    Thread.sleep(1000);
	    General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
	    Thread.sleep(2000);
	    if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
		General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2),fourLineSegment+" Seg 2");
		Thread.sleep(2000);
		}else {
		General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2),fourLineSegment_f+" Seg 2");
		Thread.sleep(2000);
		}
	    
		General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_ongoing_multiOption_icon);
		Thread.sleep(1000);
		General.action().Click(ReviewerLocators.Locator().review_ongoing_multiOption_icon);
		Thread.sleep(1000);
		
		//********NOTE*********
		//Downloaded file is moved to folder to check because to read doc file we need to give complete path -C:\Users\pvaidya\Downloads\SUB_2355176_CHROME_F1_en-US_de-DE\SUB_2355176_CHROME_F1-de-DE-spellcheck-20200720-144157.docx
		//In downloaded file pass we are getting 20200720-144157 (144157 changing)
		//As we dont know what 144157 is refer to unable to find exact path
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
		 
		FileUtil.deleteDir(CommonElements.action().FILE_ABSOLUTE_PATH+"2355176\\review\\");
		Thread.sleep(2000);
		
		//To move zip file into workspace data folder
		System.out.println("Source File:"+System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile);
		System.out.println("Destination File:"+CommonElements.action().FILE_ABSOLUTE_PATH+"2355176\\review\\"+General.action().DownloadedFile);
		FileUtils.copyFile(new File(System.getProperty("user.home") + "\\Downloads\\"+ General.action().DownloadedFile),new File(CommonElements.action().FILE_ABSOLUTE_PATH+"2355176\\review\\"+General.action().DownloadedFile));
	}
	
	
	public String[] ReadDocFile(String filePath)
	{
	    
	        File file = null;
	        WordExtractor extractor;
	        String[] fileData = null;
	        
	        try
	        {

	            file = new File(filePath);
	            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
	            HWPFDocument document = new HWPFDocument(fis);
	            extractor = new WordExtractor(document);
	            fileData = extractor.getParagraphText();
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
			return fileData;
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

//	     newPath = zipFile.substring(zipFile.lastIndexOf("/"), zipFile.length() - 4);
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
		//********NOTE*********
		//Downloaded file is moved to folder to check because to read doc file we need to give complete path -C:\Users\pvaidya\Downloads\SUB_2355176_CHROME_F1_en-US_de-DE\SUB_2355176_CHROME_F1-de-DE-spellcheck-20200720-144157.docx
		//In downloaded file pass we are getting 20200720-144157 (144157 changing)
	    //As we dont know what 144157 is refer to unable to find exact path
		
		//Assertion is blocked as need to check trans and review doc file inside folder 2355176
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



