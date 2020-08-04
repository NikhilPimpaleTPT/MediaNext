package subtitler.project_manager.Project_Director;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.PD_Pm_User_Submission_Locators;
import modules.PD_PM_user;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:This test case is to verify details on the number of captions and length of the videos when creating a group submission in PD
 *
 */

public class Sub_2423456 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	List<String> xlsxReportData;
	
	String SubmissionName = "SUB_2423456"+CommonElements.BROWSER+"A2";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_completed = "Completed";
	String SUBMISSION_FILES_PATH = "data" + File.separator + "Submission";
	String sourceDir = (new File(this.SUBMISSION_FILES_PATH).getAbsolutePath() + "\\");
	
	
	String submissionID;
	File filepath;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2423456");
		dataSet.put("TL_test_case_description", "PD integrationAs a PM, Verify details on the number of captions and length of the videos when creating a group submission in PD");
		dataSet.put("TL_internal_testCase_ID", "2423456");
	}
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			    // login using PM Crdentials 
			    General.action().login(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
			    Thread.sleep(2000);
			    //Create Submission In PD
				PM_user.action().Navigate(menu_Submission);
				// TODO NEW IMPL OF SUBMISSION CREATION
				PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName);
				PD_PM_user.action().Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
				PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				
				//Search and check submission and Verify for CRETE IN PD BUTTON
				PM_user.action().SearchSubmisson_andCheck(SubmissionName);
				Thread.sleep(2000);
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_button);
				Thread.sleep(2000);
				
				PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_createInPD_createSubmissionInPDButton);
				Thread.sleep(2000);
				
				PM_user.action().Navigate(menu_Submission);
				Thread.sleep(2000);
				
				//Verify Submission creation and due date
				PD_PM_user.action().SearchSubmisson(SubmissionName);
				Thread.sleep(2000);
				
				//Verify Submission status after creating 
				assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName,"New"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName status after Search");
				}
				
				submissionID =General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().check_Pm_submission_id(1,3)).getText();
				System.out.println("Submission ID:"+submissionID);
				
				Thread.sleep(2000);
				PD_PM_user.action().Open_PD_Instance_URL();
				Thread.sleep(25000);
				
				PD_PM_user.action().search_PD_submission_andClick("Submission ID",submissionID);
				Thread.sleep(2000);
				
				General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
		    	Thread.sleep(2000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
		    	Thread.sleep(2000);
		    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission);
		    	Thread.sleep(3000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission);
		    	Thread.sleep(5000);
		    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_ReferenceFile);
		    	Thread.sleep(5000);
		    	General.action().ClickX(PD_Pm_User_Submission_Locators.Locator().PD_submission_ReferenceFile);
		    	Thread.sleep(5000);
		    	
		    	assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_ReferenceFile_checked("reference.xlsx"), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying reference file checked by default");
				}
				
				Thread.sleep(2000);
				FileUtil.deleteDir(System.getProperty("user.home")+ "\\Downloads\\");
				Thread.sleep(5000);
		    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_ReferenceFile_downloadIcon);
		    	Thread.sleep(3000);
		    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_ReferenceFile_downloadIcon);
		    	Thread.sleep(5000);
		    	
		    	
		    	//Download file for Different browsers
				General.action ().FileDownloadMethodForDifferentBrowser();
		    	
		    	//Method To Get File name
		    	General.action().GetFileName(System.getProperty("user.home") + "\\Downloads\\");
		    	System.out.println("File is downloaded inside folder :"+System.getProperty("user.home") + "\\Downloads\\");
				 
				//To verify reference file downloaded in Lical Drive
				 assertion =General.action().DownloadedFile.contains("reference");
				 System.out.println("Assertion Is======>"+assertion);
				 if(assertion==false){
					report("f","reference File is not Downloaded in Local Drive.");
				 }else{
					System.out.println("reference File is Downloaded in Local Drive.");		
				 }
				 
				//Copy file to Test case Data folder
				 File file = new File(System.getProperty("user.home") + "\\Downloads\\reference.xlsx"); 
				 Thread.sleep(4000);
				 
				 System.out.println("data folder file is:"+sourceDir);
				 
				 FileUtil.deleteDir(sourceDir+"2423456\\");
				 Thread.sleep(2000);
					
				if(file.renameTo (new File(sourceDir+"2423456\\reference.xlsx"))) {
				file.delete(); 
				System.out.println("File moved successfully"); 
				}
		    	
		    	

		 } catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
           }
	}
 public void assertion() throws Exception {
	 
	 //This TC is block to check - The XLSX file should have the following information:
	 //File
	 //Number of captions
	 //Length
	 //Note : File is downloaded in data folder 2423456 , open the file and check file , number of captions and length manually 
	 assertion = Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_ReferenceFile_checked("reference.xlsx"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying reference file checked by default");
		}
 	else {
		report("b", "All assertions blocked");
		 }
		}
				
 
 
 public static List<String> readXLSXFileDataLineByLine(String filePath ,String SubmissionName) 
 { 
 	List<String> List = new ArrayList<String>();
   
     try { 
   
         // Create an object of filereader 
         // class with CSV file as a parameter.	    	
         FileReader filereader = new FileReader(filePath);
         
         
         // create csvReader object passing 
         // file reader as a parameter 
         CSVReader csvReader = new CSVReader(filereader); 
         String[] nextRecord; 
   
         // we are going to read data line by line 
         while ((nextRecord = csvReader.readNext()) != null) { 
             for (String cell : nextRecord) { 
             	//TODO if appointment id is found then the store data in array list
                 if(cell.contains(SubmissionName)) {	                	
                 	  for (String cell2 : nextRecord) {	                		
                 		  List.add(cell2);	  
                 		  
                 	  }
                 }
                 
             } 
            
         }    
       
     } 
     catch (Exception e) { 
         e.printStackTrace(); 
     }
     return List;
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



