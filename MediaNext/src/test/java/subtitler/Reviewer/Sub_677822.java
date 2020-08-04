package subtitler.Reviewer;

import static org.testng.AssertJUnit.assertTrue;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;

import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;

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

public class Sub_677822 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_677822"+CommonElements.BROWSER+"P3_";
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
	String specialCharacters = "+'';:.,<>?*&^%$#@!!`~";
	File filepath1;
	boolean Finalresult1=true;
	String downloadedFileName= SubmissionName+1+"-de-DE";
	String downloadedFileName2= SubmissionName+2+"-de-DE";
	String downloadedFileName3= SubmissionName+3+"-de-DE";
	String downloadPath_withoutExtension;
	String underlineText="textDecoration";
	String Srt_String="&amp";
    String Srt_String_SpanText="span";
	String Srt_String_nbspText="&nbsp";

File directory = new File(System.getProperty("user.home") + "\\Downloads\\");
	


	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_677822");
		dataSet.put("TL_test_case_description","Sub_677822: Download the srt file from completed job.");
		dataSet.put("TL_internal_testCase_ID", "677822");
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
			 for(int i=1;i<=3;i++){
			 PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			 PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", true);
			 System.out.println("filePath------>" + filePath);
			 PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			 PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_AN_TAG_SRT_FOLDER);
			 PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName+i,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			 Thread.sleep(3000);
			 PM_user.action().SearchSubmisson(SubmissionName+i);
			 Thread.sleep(2000);
			 
			 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName+i), 5);
			 if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			 }
			   }

			 
			Thread.sleep(2000);
			General.action().logoutMethod();
			 
			TransAndReviewPhaseMethod(SubmissionName+1);
			
			Thread.sleep(2000);
			Reviewer.action().Navigate(menu_completed);
			Thread.sleep(2000);
			Reviewer.action().SearchSubmisson_andCheck(SubmissionName+1);
			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(2000);
			General.action().Click(ReviewerLocators.Locator().review_file_download);
			Thread.sleep(2000);
			General.action().Click(ReviewerLocators.Locator().review_TargetSegement_FileDownLoad_format("SRT"));
			Thread.sleep(7000);
			unzipFile(false,"&amp");
			
			assertion = !FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(Srt_String);
			System.out.println("Assertion Is=====>"+assertion);
			if(assertion == false) {
				report("f","Srt File Contains & amp");
			}else {
				System.out.println("Srt File Does Not Contain & amp");
			}
			
			
			assertion = !FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(Srt_String_SpanText);
			System.out.println("Assertion Is=====>"+assertion);
			if(assertion == false) {
				report("f","Srt File Contains Span");
			}else {
				System.out.println("Srt File Does Not Contain Span Tag");
			}
			
			assertion = !FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(Srt_String_nbspText);
			System.out.println("Assertion Is=====>"+assertion);
			if(assertion == false) {
				report("f","Srt File Contains &nbsp ");
			}else {
				System.out.println("Srt File Does Not Contain &nbsp Tag");
			}
			
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(specialCharacters);
			System.out.println("Assertion Is=====>"+assertion);
			if(assertion == false) {
				report("f","Assertion failed while verifying File content with String.");
			}	
			
			
				
			Thread.sleep(1000);
			Reviewer.action().Navigate(menu_ongoing);
		    Thread.sleep(2000);
			Reviewer.action().Navigate(menu_completed);
			Thread.sleep(2000);
			Reviewer.action().SearchSubmisson_andCheck(SubmissionName+1);
			Thread.sleep(2000);
				
			
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(3000);
			General.action().Click(ReviewerLocators.Locator().review_file_download);
			Thread.sleep(2000);
			General.action().Click(ReviewerLocators.Locator().review_TargetSegement_FileDownLoad_format("SRT+AN"));
			Thread.sleep(7000);
			unzipFile(false,"");
			Thread.sleep(2000);
			
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(specialCharacters);
			System.out.println("Assertion Is=====>"+assertion);
			if(assertion == false) {
				report("f","Assertion failed while verifying File content with String.");
			}	
			
			Thread.sleep(2000);
			General.action().logoutMethod();

			TransAndReviewPhaseMethod(SubmissionName+2);
			Thread.sleep(2000);
			
			
			Reviewer.action().Navigate(menu_completed);
			Thread.sleep(2000);
			Reviewer.action().SearchSubmisson_andCheck(SubmissionName+2);
			Thread.sleep(2000);
					
				
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(2000);
			General.action().Click(ReviewerLocators.Locator().review_file_download);
			Thread.sleep(2000);
			General.action().Click(ReviewerLocators.Locator().review_TargetSegement_FileDownLoad_format("TTML1"));
			Thread.sleep(7000);
			unzipFileTTMLandEBU(true,"<p","");
			readFileforTTMLandEBU(downloadPath_withoutExtension,true,"<region xml:id=",downloadedFileName2);
			System.out.println(downloadPath_withoutExtension);
			System.out.println("TTML file contains <region xml:id= Tag ");
			readFileforTTMLandEBU(downloadPath_withoutExtension,true,"fontWeight=",downloadedFileName2);
			System.out.println("TTML file contains fontWeight=");
			readFileforTTMLandEBU(downloadPath_withoutExtension,true,"fontStyle=",downloadedFileName2);
			System.out.println("TTML file contains fontStyle=");
			readFileforTTMLandEBU(downloadPath_withoutExtension,true,"textDecoration=",downloadedFileName2);
			System.out.println("TTML file contains textDecoration=");
			Thread.sleep(2000);
			
			Thread.sleep(2000);
			General.action().logoutMethod();
			
			
			TransAndReviewPhaseMethod(SubmissionName+3);
			Thread.sleep(2000);
			 
			Reviewer.action().Navigate(menu_completed);
			Thread.sleep(2000);
			Reviewer.action().SearchSubmisson_andCheck(SubmissionName+3);
			Thread.sleep(2000);
				
//				
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			
			/*File file =new File(System.getProperty("user.home") + "\\Downloads\\");
			if(file.delete()) {
				
				System.out.println("Downloaded Folder is empty");
				
				}
		        else System.out.println("Downloaded Folder is Not empty");*/
			
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(2000);
			Thread.sleep(2000);
			General.action().Click(ReviewerLocators.Locator().review_file_download);
			Thread.sleep(2000);
			General.action().Click(ReviewerLocators.Locator().review_TargetSegement_FileDownLoad_format("EBU-TT-D"));
			Thread.sleep(7000);
			unzipFileTTMLandEBU(true," <tt:p","");
			readFileforTTMLandEBU(downloadPath_withoutExtension,true,"<region xml:id=",downloadedFileName3);
			System.out.println(downloadPath_withoutExtension);
			System.out.println("TTML file contains <region xml:id= Tag ");
			readFileforTTMLandEBU(downloadPath_withoutExtension,true,"fontWeight=",downloadedFileName3);
			readFileforTTMLandEBU(downloadPath_withoutExtension,true,"fontStyle=",downloadedFileName3);
			readFileforTTMLandEBU(downloadPath_withoutExtension,true,"textDecoration=",downloadedFileName3);
			Thread.sleep(2000);
				
		}
				
		 catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	}

	}
	
	
	
	
	public void TransAndReviewPhaseMethod(String submssionName) throws Exception{
		
		//trans login
		 General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
        Thread.sleep(1000);
        Translator.action().Navigate(menu_to_claim);
        Thread.sleep(1000);
        Translator.action().trans_ToClaim(submssionName);
        Thread.sleep(1000);
        Translator.action().Navigate(menu_ongoing);
        Thread.sleep(1000);
        Translator.action().translate_onGoing_submission(submssionName, targetlanguage_1, true, false);
        Thread.sleep(1000);
        Translator.action().Navigate(menu_completed);
        Thread.sleep(1000);
        Translator.action().SearchSubmisson(submssionName);
        Thread.sleep(2000);

		 assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translatorSearchResult(submssionName), 5);
		 if (assertion == false) {
		  report("f","Assertion failed while verifying SubmissionName  after Search");
		  }
		  Thread.sleep(2000);
		  General.action().logoutMethod();
		  
		// review login
		General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
		Thread.sleep(1000);
		Reviewer.action().Navigate(menu_to_claim);
		Thread.sleep(1000);
		Reviewer.action().review_ToClaim(submssionName);
		Thread.sleep(1000);
		Reviewer.action().Navigate(menu_ongoing);
		Thread.sleep(1000);
		reviewer_onGoing(submssionName, targetlanguage_1,true,false);
		Thread.sleep(2000);
		Reviewer.action().Navigate(menu_completed);
		Thread.sleep(1000);
		Reviewer.action().SearchSubmisson(submssionName);
		Thread.sleep(2000);
		assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewerSearchResult(submssionName), 5);
		if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
		}
	}
	
	// For SRT AND SRT + AN
	 public void unzipFile(boolean passCharacter,String characterInput) throws Exception {
			Thread.sleep(2000);
		 if(CommonElements.BROWSER.contains("FIREFOX")) {
		    	System.out.println("------THIS IS FIREFOX-----");
		    	Thread.sleep(3000);
				General.action().downloadFileFirefox();
				Thread.sleep(3000);
				
	    	}else {
	        	System.out.println("------THIS IS CHROME-----");
	    		Thread.sleep(5000);
	    	}
			
			
			
			//UNZIP THE FILE
 		String foldertoUnZip = FileZipUnzipThread.utils().getFolderName(System.getProperty("user.home")+ "\\Downloads\\","SUB");
 		System.out.println("foldertoUnZip--->"+foldertoUnZip);
 		System.out.println("Download Path--->"+System.getProperty("user.home")+ "\\Downloads\\"  + foldertoUnZip);
 		General.action().extractFolder(System.getProperty("user.home")+ "\\Downloads\\"+foldertoUnZip  , System.getProperty("user.home")+ "\\Downloads\\");
 	    Thread.sleep(1000);
 	    
 	    //GET PATH TILL PARENT FOLDER
 		String fileName_withoutExtension =General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
 		System.out.println("foldertoUnZip_new--->"+fileName_withoutExtension);
 		downloadPath_withoutExtension=System.getProperty("user.home")+ "\\Downloads\\"+fileName_withoutExtension;
 		System.out.println("downloadPath_withoutExtension--->"+downloadPath_withoutExtension);
 		
 		
 		readFile(downloadPath_withoutExtension,passCharacter,characterInput);
         
	 }
	
	public void readFile(String downloadPath_withoutExtension,boolean passCharacter,String characterInput) throws Exception {
		try {

//			File filepath = new File(System.getProperty("user.home")+ "\\Downloads\\");
			File filepath = new File(downloadPath_withoutExtension);

			String[] filenames = filepath.list();
			int countofFiles = filenames.length;
			System.out.println("countofFiles##########  :- " + countofFiles);

			for (String filename : filenames) {
				System.out.println(filename);

				String path = downloadPath_withoutExtension+"\\"+ filename;
			
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

                filepath1 = new File(downloadPath_withoutExtension+"\\"+ filename);
                
                System.out.println(downloadPath_withoutExtension);
                System.out.println(filename);
                
                System.out.println("filepath1-------->"+filepath1);
                System.out.println("downloadedFileName--->"+downloadedFileName);
                
                assertion=filepath1.getName().contains(downloadedFileName);
                if(assertion==false) {
                	report("f", "File name is not valid");
                }
             
                if(passCharacter) {
                    System.out.println("characterInput---->"+characterInput);	
                    System.out.println(FileUtils.readFileToString(filepath1,CommonElements.UTF_8));
                    Finalresult1 = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(characterInput);
       			    System.out.println("Downloaded file does not contains"+":- characterInput"+Finalresult1);
       			    if(Finalresult1==false) {
       			    	report("f", "File does Not contains"+characterInput);
       			    }
       			
			
				
                }
             
                
              }
			
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	 public void unzipFileTTMLandEBU(boolean passCharacter,String characterInput,String DownloadedFileName) throws Exception {
			Thread.sleep(2000);
		 if(CommonElements.BROWSER.contains("FIREFOX")) {
		    	System.out.println("------THIS IS FIREFOX-----");
		    	Thread.sleep(3000);
				General.action().downloadFileFirefox();
				Thread.sleep(3000);
				
	    	}else {
	        	System.out.println("------THIS IS CHROME-----");
	    		Thread.sleep(5000);
	    	}
			
			
			
			//UNZIP THE FILE
	 		String foldertoUnZip = FileZipUnzipThread.utils().getFolderName(System.getProperty("user.home")+ "\\Downloads\\","SUB");
	 		System.out.println("foldertoUnZip--->"+foldertoUnZip);
	 		System.out.println("Download Path--->"+System.getProperty("user.home")+ "\\Downloads\\"  + foldertoUnZip);
	 		General.action().extractFolder(System.getProperty("user.home")+ "\\Downloads\\"+foldertoUnZip  , System.getProperty("user.home")+ "\\Downloads\\");
	 	    Thread.sleep(1000);
	 	    
	 	    //GET PATH TILL PARENT FOLDER
	 		String fileName_withoutExtension =General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
	 		System.out.println("foldertoUnZip_new--->"+fileName_withoutExtension);
	 		downloadPath_withoutExtension=System.getProperty("user.home")+ "\\Downloads\\"+fileName_withoutExtension;
	 		System.out.println("downloadPath_withoutExtension--->"+downloadPath_withoutExtension);
		
		readFileforTTMLandEBU(downloadPath_withoutExtension,passCharacter,characterInput,DownloadedFileName );// this method will read srt file from download folder.
      
	 }
	
	public void readFileforTTMLandEBU(String downloadPath_withoutExtension,boolean passCharacter,String characterInput,String DownloadedFileName) throws Exception {
		try {

//			File filepath = new File(System.getProperty("user.home")+ "\\Downloads\\");
			File filepath = new File(downloadPath_withoutExtension);

			String[] filenames = filepath.list();
			int countofFiles = filenames.length;
			System.out.println("countofFiles##########  :- " + countofFiles);

			for (String filename : filenames) {
				System.out.println(filename);

				String path = downloadPath_withoutExtension+"\\"+ filename;
			
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

             filepath1 = new File(downloadPath_withoutExtension+"\\"+ filename);
             
             System.out.println("filepath1-------->"+filepath1);
             System.out.println("downloadedFileName--->"+DownloadedFileName);
             
             assertion=filepath1.getName().contains(DownloadedFileName);
             if(assertion==false) {
             	report("f", "File name is not valid");
             }
             
         
             if(passCharacter) {
             System.out.println("characterInput---->"+characterInput);	
             System.out.println(FileUtils.readFileToString(filepath1,CommonElements.UTF_8));
             Finalresult1 = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(characterInput);
			    System.out.println("Downloaded file does not contains"+":- characterInput"+Finalresult1);
			    if(Finalresult1==false) {
			    	report("f", "File does Not contains"+characterInput);
			    }
			
				
             }
             }
			
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reviewer_onGoing(String SubmissionName,String target, boolean complete, boolean back) throws Exception {

		System.out.println("INSIDE reviewer_onGoing  method()");

		Translator.action().waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		Translator.action().Click(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		Translator.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
		Thread.sleep(1000);
		Translator.action().type(ReviewerLocators.Locator().review_search_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");

			Translator.action().Click(ReviewerLocators.Locator().reviewer_selectSubmission_checkbox(SubmissionName, target));
		}
		Thread.sleep(1000);
		Translator.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
		Thread.sleep(5000);

		 Thread.sleep(2000);
	      List <WebElement> listofIds1= General.driver.findElements(ReviewerLocators.Locator().reviewer_toClaim_ListofallIds);
		  Thread.sleep(1000);
		  System.out.println("No of IDS--------"+listofIds1.size());

	    for(int i=1;i<=listofIds1.size();i++){
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
			Thread.sleep(1000);
			General.action().type((ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i)),specialCharacters);
			Thread.sleep(2000);
			
			
            }
	    
	    General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
		Thread.sleep(1000);
		
		Thread.sleep(5000);	
		PM_user.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),Keys.chord(Keys.CONTROL,"a"));
		Thread.sleep(5000);	
		General.action().Click(ReviewerLocators.Locator().Text_Formatting_Bold);
		Thread.sleep(1000);
		
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2));
		
		Thread.sleep(5000);	
		PM_user.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(2),Keys.chord(Keys.CONTROL,"a"));
		Thread.sleep(5000);	
		General.action().Click(ReviewerLocators.Locator().Text_Formatting_Italic);
		Thread.sleep(1000);
		
		General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
		Thread.sleep(5000);	
		PM_user.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3),Keys.chord(Keys.CONTROL,"a"));
		Thread.sleep(5000);	
		General.action().Click(ReviewerLocators.Locator().Text_Formatting_Underline);
		Thread.sleep(1000);
	    
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
	    
		 System.out.println("EOM reviewer_onGoing  method()");
}
		

	

	public void assertion() throws Exception {
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(underlineText);
		assertion = Finalresult1;
		if (assertion == false) {
			report("f","Assertion failed while verifying File content with String.");
		}  else {
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




	
	


