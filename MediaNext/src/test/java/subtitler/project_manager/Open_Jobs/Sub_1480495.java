package subtitler.project_manager.Open_Jobs;

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
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: Check accented characters in translated file after download.
 *Preconditions :Use the attached file to create the submission.
 */ 
public class Sub_1480495 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "SUB_1480495"+CommonElements.BROWSER+"T6";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_Completed = "Completed";
	String chineseText[]= {"Jellyfish at the Monterey Aquarium.","Dude - get out of the way.","Siōng \"bô siu-tēng\" ê bûn-chiuⁿ","Siōng phó·-phiàn ê kong-lêng sī kái-piàn ki-chhó· jī-bó ê hoat-im.","All Rights Reserved by QA Transperfect India.","Thanks for watching and I hope you'll have fun with the VideoSub library."};
	File filepath1;
	
	@BeforeMethod
	public void setup() throws Exception {General.action().startSystem("SUB_1480495");
		dataSet.put("TL_test_case_description", "SUB-1480495:Check accented characters in translated file after download.");
		dataSet.put("TL_internal_testCase_ID", "1480495");
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
			// TODO NEW IMPL OF SUBMISSION CREATION , USE SRT FILE ATTACHED IN TEST CASE
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "",false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_ACCENTED_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(8000);
			//VERIFY SUBMISSION NAME
			PM_user.action().SearchSubmisson(SubmissionName);
			Thread.sleep(000);
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f", "Assertion failed while verifying SubmissionName  after Search");
			}
			
			//CLAIM JOB
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(1000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(1000);
			//NAVIGATE TO ONGOING AND OPEN JOB
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(1000);
			//ENTER ACCENTED TEXT IN TARGET SEGMENT
			PM_onGoing_submission(SubmissionName,"trans",true,true);
			Thread.sleep(1000);
			
			//GO TO COMPLETED TAB
			Reviewer.action().Navigate(menu_Completed);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home") + "\\Downloads\\");
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_file_download);
			Thread.sleep(2000);
			//CLICK ON SRT AND DOWNLOAD IT
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_FileDownload_SRT);
			Thread.sleep(7000);
			
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
    		String downloadPath_withoutExtension=System.getProperty("user.home")+ "\\Downloads\\"+fileName_withoutExtension;
    		System.out.println("downloadPath_withoutExtension--->"+downloadPath_withoutExtension);
    		
    		
            readSrtFile(downloadPath_withoutExtension);
			
			Thread.sleep(1000);

			//VERIFY SRT FILE CONTAINS ENTER ACCENTED SEGMENTS 
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(chineseText[1]);;
			if (assertion == false) {
				report("f","Assertion failed while verifying File content with String.");
			}
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(chineseText[2]);;
			if (assertion == false) {
				report("f","Assertion failed while verifying File content with String.");
			}
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(chineseText[3]);;
			if (assertion == false) {
				report("f","Assertion failed while verifying File content with String.");
			}
			assertion = FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains(chineseText[4]);;
			if (assertion == false) {
				report("f","Assertion failed while verifying File content with String.");
			}
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	
	
	public void PM_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
		
		 System.out.println("INSIDE PM_Ongoing  method()");
		 
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		 Thread.sleep(1000);
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		 Thread.sleep(1000);
		 PM_user.action().type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input, SubmissionName);
		 Thread.sleep(2000);
			
		 if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName, target)))
		 {
		  System.out.println("INSIDE IF--------");
		  PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
		 }
		 Thread.sleep(2000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
			
		 Thread.sleep(2000);
		 List <WebElement> listofIds1= PM_user.action().driver.findElements(Pm_User_Submission_Locators.Locator().PM_toClaim_ListofallIds);
		 Thread.sleep(1000);
		 System.out.println("No of IDS--------"+listofIds1.size());
	
		 for(int i=1;i<=listofIds1.size();i++){
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(i));
		 Thread.sleep(1000);
		 PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(i));
		 Thread.sleep(1000);
		 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(i));
		 Thread.sleep(1000);
		 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(i));
		 Thread.sleep(1000);
		 //ENTER CHINESE SEGMENT
		 General.action().type(TranslatorLocators.Locator().translator_TargetSegement_textarea(i),chineseText[i-1]);
		 Thread.sleep(3000);
		 }
		 //VERIFY TARGET SEGMENT CONTAINS ACCENTED TEXT
		 assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1)).getText().contains(chineseText[0]);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying target sement contains accented  text .");
				
		 }
	     assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(2)).getText().contains(chineseText[1]);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying target sement contains accented  text ");
				
		 }
	     assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(3)).getText().contains(chineseText[2]);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying target sement contains accented  text ");
				
		 }
	     assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(4)).getText().contains(chineseText[3]);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying target sement contains accented  text ");
				
		 }
	     assertion =General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(5)).getText().contains(chineseText[4]);
	     if (assertion == false) {
		 report("f","Assertion failed while verifying target sement contains accented  text.");
				
		 }
				
		
			    
		//COMPLTE JOB
		if(complete){
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
		Thread.sleep(1000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
		Thread.sleep(1000);
		PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
		Thread.sleep(1000);
		PM_user.action().Click(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
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
	    Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
		}
			  }
			    
		System.out.println("EOM PM_Ongoing  method()");
	}
	
	
	public void readSrtFile(String downloadPath_withoutExtension) throws Exception {
		try {
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
                
                //CONDITION
                boolean Finalresult = !FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("??????");
				System.out.println("Downloaded file NOT contains string :-" +Finalresult);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void assertion() throws Exception {
		//VERIFY SRT FILE DO NO NOT CONTAINS ??????
		assertion = !FileUtils.readFileToString(filepath1,CommonElements.UTF_8).contains("??????");;
		if (assertion == false) {
			report("f","Assertion failed while verifying File content with String.");
		}else {
			report("p", "All Assertion Passed.");
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
