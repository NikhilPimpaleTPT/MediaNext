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
 *Summary: This test case verifies PD integration
 */ 

public class Sub_1281334 {
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String SubmissionName = "SUB_1281334"+CommonElements.BROWSER+"O1";
    String OrganizationName = "Subtitle_Common_orgs";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_completed = "Completed";
	String pd_instance="qa-tdc13";
	String pd_department="GLPlay_Test_Project";
	String pd_workflow="MT_Subtitler";
	String pd_client="Subtitler_PAClient_4";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String pdTansletedTargetSource[]= {"Quallen im Monterey Aquarium.","Alter, geh aus dem Weg!","Wackelige Hände","Ah ja, das ist besser","Danke fürs Zuschauen und ich hoffe","Alle Rechte vorbehalten von QA Transperfect India."};
//	String pdTansletedTargetSource[]= {"Quallen im Monterey Aquarium.","Dude raus aus dem Weg!","Shaky Hands","Ah ja, das ist besser","Vielen Dank f�r das Zuschauen und ich hoffe, Sie'll Spa� mit der VideoSub-Bibliothek haben.","Alle Rechte vorbehalten von QA Transperfect India."};

	File filepath1;
	
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1281334");
		dataSet.put("TL_test_case_description", "PD integration");
		dataSet.put("TL_internal_testCase_ID", "1281334");
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
				PM_user.action().CreateSubmisson_Step1_PD_Config("17", "35", "80", "100");
				PM_user.action().CreateSubmisson_Step2_PD_Config(pd_instance, pd_department, pd_workflow, pd_client);
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
				System.out.println("filePath------>" + filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				Thread.sleep(2000);
				//VERIFY SUBMISSION NAME
				PM_user.action().SearchSubmisson(SubmissionName);
				Thread.sleep(2000);
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
				if (assertion == false) {
					report("f", "Assertion failed while verifying SubmissionName  after Search");
				}
				
				//CHECK SUBMISSION
				PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				Thread.sleep(2000);
				
				//EDIT SUBMISSION
				PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
				Thread.sleep(1000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
				Thread.sleep(6000);
				
				//CLCIK ON TRANS
				PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_transStatus);
				Thread.sleep(1000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_transStatus);
				Thread.sleep(6000);
				
				//TRANSLATE SUBMISSION
				PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_translateButton);
				Thread.sleep(1000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_translateButton);
				Thread.sleep(3000);
				
				if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_translate_overwrite,5)) {
					General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_translate_overwrite);
				}
				Thread.sleep(10000);
				//VERIFY MESSAGE AFTER TRANSLATION
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_translateMessage("Translation has been queued in PD. This process may take a while, come back later to see the result."), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying message after translation of pd submission");
					
				}
				
				//WAIT TILL TRANSLATION COMPLETE
				Thread.sleep(30000);
				
				//CLICK ON SUBMISSION TAB AND OPEN SUBMISSION
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(4000);
				PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				Thread.sleep(2000);
				PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
				Thread.sleep(1000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
				Thread.sleep(10800);
				
				//VERIFY MESSAGE AFTER TRANSLATION 
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_translateMessage("PD PreTranslation Complete."), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying message after translation of pd submission");
					
				}
				
				//CLICK ON TRANSLATE BUTTON
				PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_translateButton);
				Thread.sleep(1000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_translateButton);
				Thread.sleep(4000);
				
				//VERIFY OVERITE AND CLOSE BUTTON
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_translateOverwriteMessage, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying overwrite button");
					
				}
				
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_translate_canceloverwrite, 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying close button");
					
				}
				
				//CLICK ON CLOSE
				Thread.sleep(2000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_translate_canceloverwrite);
				Thread.sleep(10800);
				
				//VERITY NOTHING IS HAPPEN
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_translateMessage("PD PreTranslation Complete"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying message after COMPLETED translation of pd submission");
					
				}
				
				//AGAIN CLICK ON TRANSLATE BUTTON
				PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_translateButton);
				Thread.sleep(1000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_translateButton);
				Thread.sleep(4000);
				
				//CLICK ON OVEWRITE
				PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_translate_overwrite);
				Thread.sleep(1000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_translate_overwrite);
				Thread.sleep(5000);
				
				//VERIFY MESSAGE
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_translateMessage("Translation has been queued in PD. This process may take a while, come back later to see the result"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying message after translation of pd submission");
					
				}
				
				//CLICK ON OPEN JOBS
				PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
				Thread.sleep(1000);
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
				Thread.sleep(4000);
				
				//VERIFY TRANSLATION IS PRESENT IN TARGET SEGMENTS
				String targetSegment_01=General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(1)).getText();
				System.out.println(targetSegment_01);
				Thread.sleep(2000);
				assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(1)).getText().contains(pdTansletedTargetSource[0]);
				if (assertion == false) {
					report("f","Assertion failed while verifying translation is present in a target column.");
					
				}
				Thread.sleep(2000);
				assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(2)).getText().contains(pdTansletedTargetSource[1]);
				if (assertion == false) {
					report("f","Assertion failed while verifying translation is present in a target column.");
					
				}
				Thread.sleep(2000);
				assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(3)).getText().contains(pdTansletedTargetSource[2]);
				if (assertion == false) {
					report("f","Assertion failed while verifying translation is present in a target column.");
					
				}
				Thread.sleep(2000);
				assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(4)).getText().contains(pdTansletedTargetSource[3]);
				if (assertion == false) {
					report("f","Assertion failed while verifying translation is present in a target column.");
					
				}
				Thread.sleep(2000);
				assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(5)).getText().contains(pdTansletedTargetSource[4]);
				if (assertion == false) {
					report("f","Assertion failed while verifying translation is present in a target column.");
					
				}
				Thread.sleep(2000);
				assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(6)).getText().contains(pdTansletedTargetSource[5]);
				if (assertion == false) {
					report("f","Assertion failed while verifying translation is present in a target column.");
					
				}
				Thread.sleep(2000);
				//COMPLETE SUBMISSION 
				Thread.sleep(1000);
			    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_trans);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_trans);
				Thread.sleep(5000);
				
				//GO TO Jobs
				PM_user.action().Navigate("Jobs");
				Thread.sleep(2000);
                
				//CLICK ON SHOW COMPLETED BUTTON
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Show_Completed);
				Thread.sleep(2000);
				
				//CHECK SUBMISSION AND DOWNLOAD SRT FILE
				PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				Thread.sleep(2000);
				FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
				Thread.sleep(2000);
				General.action().Click(Pm_User_Submission_Locators.Locator().submmission_file_download);
				Thread.sleep(2000);
				General.action().Click(Pm_User_Submission_Locators.Locator().submmission_FileDownload_SRT);
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
	    		String foldertoUnZip = FileZipUnzipThread.utils().getFolderName(System.getProperty("user.home")+ "\\Downloads\\","SUB");
	    		System.out.println("foldertoUnZip--->"+foldertoUnZip);
	    		System.out.println("Download Path--->"+System.getProperty("user.home")+ "\\Downloads\\"  + foldertoUnZip);
	    		General.action().extractFolder(System.getProperty("user.home")+ "\\Downloads\\"+foldertoUnZip  , System.getProperty("user.home")+ "\\Downloads\\");
	    	    Thread.sleep(1000);
	    	    
	    	    //GET PATH TILL PARENT FOLDER
	    		String fileName_withoutExtension =General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
	    		System.out.println("foldertoUnZip_new--->"+fileName_withoutExtension);
	    		String downloadPath_withoutExtension=System.getProperty("user.home")+ "\\Downloads\\"+fileName_withoutExtension;
	    		System.out.println("downloadPath_withoutExtension--->"+downloadPath_withoutExtension);
	    		
	    		//VERIFY SEGMENT ARE PRESENT IN SRT FILE
	    		readSRTandTTMLFile(downloadPath_withoutExtension,pdTansletedTargetSource[0]);
				Thread.sleep(2000);
				readSRTandTTMLFile(downloadPath_withoutExtension,pdTansletedTargetSource[1]);
				Thread.sleep(2000);
				readSRTandTTMLFile(downloadPath_withoutExtension,pdTansletedTargetSource[2]);
				Thread.sleep(2000);
				readSRTandTTMLFile(downloadPath_withoutExtension,pdTansletedTargetSource[3]);
				Thread.sleep(2000);
				readSRTandTTMLFile(downloadPath_withoutExtension,pdTansletedTargetSource[4]);
				Thread.sleep(2000);
			
				
				//CHECK SUBMISSION AND DOWNLOAD TTML FILE
				FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
				Thread.sleep(2000);
				General.action().Click(Pm_User_Submission_Locators.Locator().submmission_file_download);
				Thread.sleep(2000);
				General.action().Click(Pm_User_Submission_Locators.Locator().submmission_FileDownload_TTML);
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
	    		String foldertoUnZip_ttml = FileZipUnzipThread.utils().getFolderName(System.getProperty("user.home")+ "\\Downloads\\","SUB");
	    		System.out.println("foldertoUnZip--->"+foldertoUnZip_ttml);
	    		System.out.println("Download Path--->"+System.getProperty("user.home")+ "\\Downloads\\"  + foldertoUnZip_ttml);
	    		General.action().extractFolder(System.getProperty("user.home")+ "\\Downloads\\"+foldertoUnZip_ttml  , System.getProperty("user.home")+ "\\Downloads\\");
	    	    Thread.sleep(1000);
	    	    
	    	    //GET PATH TILL PARENT FOLDER
	    		String fileName_withoutExtension_ttml =General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
	    		System.out.println("foldertoUnZip_new--->"+fileName_withoutExtension_ttml);
	    		String downloadPath_withoutExtension_ttml=System.getProperty("user.home")+ "\\Downloads\\"+fileName_withoutExtension_ttml;
	    		System.out.println("downloadPath_withoutExtension--->"+downloadPath_withoutExtension_ttml);
	    		
	    		readSRTandTTMLFile(downloadPath_withoutExtension_ttml,pdTansletedTargetSource[0]);
				Thread.sleep(2000);
				readSRTandTTMLFile(downloadPath_withoutExtension_ttml,pdTansletedTargetSource[1]);
				Thread.sleep(2000);
				readSRTandTTMLFile(downloadPath_withoutExtension_ttml,pdTansletedTargetSource[2]);
				Thread.sleep(2000);
				readSRTandTTMLFile(downloadPath_withoutExtension_ttml,pdTansletedTargetSource[3]);
				Thread.sleep(2000);
				readSRTandTTMLFile(downloadPath_withoutExtension_ttml,pdTansletedTargetSource[4]);
				Thread.sleep(2000);
				
				// DOWNLOAD EBU FILE
				FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
				Thread.sleep(2000);
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
				assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(pdTansletedTargetSource[0]);
				if (assertion == false) {
					report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
					Thread.sleep(2000);
				}
				
				assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(pdTansletedTargetSource[1]);
				if (assertion == false) {
					report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
					Thread.sleep(2000);
				}
				
				assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(pdTansletedTargetSource[2]);
				if (assertion == false) {
					report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
					Thread.sleep(2000);
				}
				
				assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(pdTansletedTargetSource[3]);
				if (assertion == false) {
					report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
					Thread.sleep(2000);
				}
				
				assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(pdTansletedTargetSource[5]);
				if (assertion == false) {
					report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
					Thread.sleep(2000);
				}
				
		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
           }
 }
	
	
	
	
	
	
	public void assertion() throws Exception {
		assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(pdTansletedTargetSource[5]);
		if (assertion == false) {
			report("f","Assertion failed while verifying message for Trans_InvalidImportFile_Error_message");
			Thread.sleep(2000);
		}else {
				report("p", "String Is NOT Same , Not Contains Extra Spaces and Extra Return(String Without Spaces and Extra Return)");
			}
	}
	
	
	public void readSRTandTTMLFile(String downloadPath_withoutExtension,String targetSegment) throws Exception {
		try {

					//File filepath = new File(System.getProperty("user.home") + "\\Downloads\\");
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
		                Thread.sleep(1000);
		                boolean Finalresult = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(targetSegment);
						System.out.println("Downloaded file contains string :-" +Finalresult);
		}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
	
	public void readEBUFile(String downloadPath_withoutExtension) throws Exception {
		try {

			//File filepath = new File(System.getProperty("user.home") + "\\Downloads\\");
			File filepath = new File(downloadPath_withoutExtension);

			String[] filenames = filepath.list();
			int countofFiles = filenames.length;
			System.out.println("countofFiles##########  :- " + countofFiles);

			for (String filename : filenames) {
				System.out.println(filename);

				String path = downloadPath_withoutExtension+"\\" + filename;
			
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
                Thread.sleep(1000);
                boolean Finalresult = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("</p>");
				System.out.println("Downloaded file contains string :-" +Finalresult);
        }

		} catch (Exception e) {
			e.printStackTrace();
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
