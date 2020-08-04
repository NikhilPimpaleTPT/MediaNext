package subtitler.Reviewer;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.gs4tr.qa.util.FileZipUnzipThread;
import org.gs4tr.qa.util.RobotExt;
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
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary:Download the translated job in TTML/EBU-TT-D format if segments are merged/splitted.
 *Preconditions :Create a submission using file of create.zip. Refer SUB-1176
                 Claim a job in translator login.
 */

public class Sub_1480484 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1480484"+CommonElements.BROWSER+"S1";
	 String OrganizationName = "Subtitle_Common_orgs";
	    String WorkflowName = "Two_Step_Workflow";
		String fileDirName = "common";
		String menu_Submission = "Submissions";
		String transcGroupName = "Common_group";
		String TranslatorGroupName = "Common_group";
		String ReviewGroupName = "Common_group";
		String targetlanguage = "German (Germany)";
		String menu_AllJobs = "Jobs";
		String menu_to_claim = "To Claim";
		String menu_ongoing = "Ongoing";
		String menu_completed = "Completed";
		String path;
		String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
		String DownloadedfileName;
		String downloadPath_withoutExtension;
		String downloadedSrtFileName;
		File filepath1;
		Boolean assertion = true;
		
		
		@BeforeMethod
		public void setup() throws Exception {
			General.action().startSystem("SUB_1480484");
			dataSet.put("TL_test_case_description", "SUB-1480484: :Download the translated job in TTML/EBU-TT-D format if segments are merged/splitted..");
			dataSet.put("TL_internal_testCase_ID", "1480484");
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
					// TODO NEW IMPL OF SUBMISSION CREATION USING 2K SRT
					PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
					PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", true);
					PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_LARGE_VIDEO,false,CommonElements.action().FILE_COMMON_LARGE_VIDEO_ASSET_ID);
					PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_1480484_SRT);
					PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
					Thread.sleep(120000);

					//SEARCH SUBMISSION AND CHECK
					PM_user.action().SearchSubmisson_andCheck(SubmissionName);
					Thread.sleep(2000);
					assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying SubmissionName  after Search");
					}
					General.action().logoutMethod();
					
					
					// tran login
					General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
					Thread.sleep(1000);
					Translator.action().Navigate(menu_to_claim);
					Thread.sleep(1000);
					Translator.action().trans_ToClaim(SubmissionName);
					Thread.sleep(1000);
					Translator.action().Navigate(menu_ongoing);
					Thread.sleep(1000);
					Thread.sleep(1000);
					translate_onGoing_submission(SubmissionName, targetlanguage,true, false);
					Thread.sleep(1000);
					Translator.action().Navigate(menu_completed);
					Thread.sleep(1000);
					Thread.sleep(2000);
					 Translator.action().SearchSubmisson_andCheck(SubmissionName);
					Thread.sleep(2000);
					
					assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Trans_file_download, 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying Download file Icon after Search");
					}
					
					Thread.sleep(2000);
					FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
					Thread.sleep(2000);
					General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_file_download);
					Thread.sleep(1000);
					General.action().Click(TranslatorLocators.Locator().Trans_file_download);
					Thread.sleep(2000);
					General.action().Click(TranslatorLocators.Locator().Trans_FileDownload_TTML);
					Thread.sleep(7000);
					
					
					
					downloadedSrtFileName=GetDownloadedFileNameMethod();
					System.out.println("downloaded Srt File Name Is---->"+downloadedSrtFileName);
					
					assertion = downloadedSrtFileName.contains(SubmissionName);
					if (assertion == false) {
						report("f","Assertion failed while verifying File Name Same As Submission Name.");
					}
					
					
					Thread.sleep(2000);
					FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
					Thread.sleep(2000);
					General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_file_download);
					Thread.sleep(1000);
					General.action().Click(TranslatorLocators.Locator().Trans_file_download);
					Thread.sleep(2000);
					General.action().Click(TranslatorLocators.Locator().Trans_FileDownload_TTML);
					Thread.sleep(7000);
					
					downloadedSrtFileName=GetDownloadedFileNameMethod();
					System.out.println("downloaded Srt File Name Is---->"+downloadedSrtFileName);
					
					
			} catch (Exception e) {
		         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	            }
	  }
		
		public String GetDownloadedFileNameMethod() throws Exception {
			
			String downloadedSRTFile="";
	       
			//Get Downloaded File Name
			
			System.out.println(" INSIDE Get Downloaded File Name Method ");
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
			 
			DownloadedfileName=General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
			System.out.println("Downloaded File Name--->"+DownloadedfileName);
			
			
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
	 		
	 		downloadedSRTFile=readFile(downloadPath_withoutExtension);
			
			System.out.println(" EOM Get Downloaded File Name");
			return downloadedSRTFile;
		}
		
		public String readFile(String downloadPath_withoutExtension) throws Exception {
			String srtFileName="";
			try {

//				File filepath = new File(System.getProperty("user.home")+ "\\Downloads\\");
				File filepath = new File(downloadPath_withoutExtension);
				String[] filenames = filepath.list();
				int countofFiles = filenames.length;
				System.out.println("countofFiles##########  :- " + countofFiles);

				for (String filename : filenames) {
					System.out.println(filename);
					srtFileName=filename;
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
	                System.out.println("downloadedFileName--->"+SubmissionName);
	                
	                assertion=filepath1.getName().contains(SubmissionName);
	                if(assertion==false) {
	                	report("f", "File name is not valid");
	                
	       			
				
					
	                }
	             
	                
	              }
				
				} catch (Exception e) {
				e.printStackTrace();
			}
			
			return srtFileName;
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
				
					  
				Thread.sleep(10000);
					 List <WebElement> listofIds1=  General.action().driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
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
			  	General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(2));
			  	Thread.sleep(1000);
			  	General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			  	Thread.sleep(1000);
			  	General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(1));
			  	Thread.sleep(10000);
			  	Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_END);
			  	
			  	
			  	for(int i=1;i<=9;i++) {
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
				
				 assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(1)).getText().contains("112Jellyfish at \"the Monterey\"");
				 if (assertion == false) {
						report("f","Assertion failed while verifying segment after spliting");
				 }
					
				 Thread.sleep(2000);
				assertion = General.driver.findElement(TranslatorLocators.Locator().translator_TargetSegement_textarea(2)).getText().contains("Aquarium.");
				if (assertion == false) {
						report("f","Assertion failed while verifying segment after spliting");
				}
				
				//Import File
				General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
				Thread.sleep(1000);
				General.action().Click(TranslatorLocators.Locator().Trans_ongoing_multiOption_icon);
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(TranslatorLocators.Locator().Trans_Ongoing_Import_file);

				//Verify that importing a file.SRT (SRT in capital letters) would not return any error message.
				File afile=new File(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_1480484_SRT);
				File[] listOfFiles={afile};
		    	if (afile.isDirectory())    
		    	    listOfFiles = afile.listFiles();
		    	   
		    	   Thread.sleep(3000);
		    	      
		    	   //Process array
		    	    for (int i = 0; i < listOfFiles.length; i++)
		    	    { 
		    	     if(listOfFiles[i].isDirectory()) 
		    	    	 continue;
		    	    
		    	      path = listOfFiles[i].getAbsolutePath();
		    	     
		    	    }
		    	 	Thread.sleep(1000);

				//Trans_Ongoing_Import_file_input    	 
		    	System.out.println("VALID PATH-------->"+path);
		    	System.out.println("WHTS IS THIS---->"+CommonElements.BROWSER);
		    	
			 	//TODO THIS IMPL IS DUE TO AS FILE CANNOT BE IMPORTED PROPERLY IN FIREFOX THROUGH senKeys(), so FOR FIREFOX WE ARE USING ROBOT CLASS AND FOR CHROME WE ARE USING sendKeys()
		    	if(CommonElements.BROWSER.contains("FIREFOX")) {
			    	System.out.println("------THIS IS FIREFOX-----");
		    		//General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByCaptionNumber);
			    	Thread.sleep(1000);
			    	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
		        	Thread.sleep(3000);
		        	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByTimecode);
					Thread.sleep(3000);
					RobotExt.robot().delay(2000);
					RobotExt.robot().sendKeys(path);
					RobotExt.robot().processFilePath();
					RobotExt.robot().delay(2000);
					Thread.sleep(4000);
		    	}else {
		        	System.out.println("------THIS IS CHROME-----");
		        	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Import_file);
		        	Thread.sleep(3000);
		        	General.action().Click(TranslatorLocators.Locator().Trans_Ongoing_Importfile_ByTimecode);
					Thread.sleep(3000);
					RobotExt.robot().delay(2000);
					RobotExt.robot().sendKeys(path);
					RobotExt.robot().processFilePath();
					RobotExt.robot().delay(2000);
					Thread.sleep(4000);
		    	}
		    	Thread.sleep(4000);
				
					
			  	
		    	General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(5));
			  	Thread.sleep(1000);
			  	General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(4));
			  	Thread.sleep(1000);
			  	General.action().Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(5));
			  	Thread.sleep(1000);
			  	
			  	if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) {
				  	General.action().Trans_editText_alt_p_m(KeyEvent.VK_ALT,KeyEvent.VK_M);
				  	Thread.sleep(1000);
				  	}else {
				  	Translator.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(1),Keys.chord(Keys.ALT, "m"));
					Thread.sleep(1000);
				}
					  
			
			   Thread.sleep(8000);
				    
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

		
		
		public void assertion() throws Exception {
			assertion = downloadedSrtFileName.contains(SubmissionName);
			if (assertion == false) {
				report("f","Assertion failed while verifying File Name Same As Submission Name.");
			}else {
					report("p", "All assertion passed ");
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



