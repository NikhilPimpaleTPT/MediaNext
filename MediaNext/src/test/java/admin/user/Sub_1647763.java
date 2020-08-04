package admin.user;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.gs4tr.qa.util.FileUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Admin_User_Submission_Locators;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import modules.Verify;
import modules.admin;

/**
 * 
 * @author pvaidya
 *Summary: This testcase verifies if user can export user list in CSV format.
 */


public class Sub_1647763 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	List<String> CSVReportData;
	String menu_user = "Users";
	String enteredDueDate;  
	File filepath1;
	String usersCreationDate;
	Boolean assertion = true;
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1647763");
		dataSet.put("TL_test_case_description", "SUB-1647763:Export user list in CSV format");
		dataSet.put("TL_internal_testCase_ID", "1647763");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			
			
			General.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			
			//Navigate to user menu
			admin.action().Navigate(menu_user);
			Thread.sleep(2000);
			
			Thread.sleep(2000);
			FileUtil.deleteDir(System.getProperty("user.home")+ "\\Downloads\\");
			Thread.sleep(2000);
			//Click on export csv
			General.action ().waitforelemenetpresent(Admin_User_Submission_Locators.Locator().user_exportCSV);
			Thread.sleep(1000);
			General.action ().Click(Admin_User_Submission_Locators.Locator().user_exportCSV);
			Thread.sleep(2000);		
			
			//Download file for Different browsers
			General.action ().FileDownloadMethodForDifferentBrowser();
			
			usersCreationDate=GetCurrentDate();
			System.out.println("users creation date---->"+usersCreationDate);
			
		    //Read donloaded file
			CSVReportData=General.readCSVFileDataLineByLine(System.getProperty("user.home") + "\\Downloads\\"+"users-"+usersCreationDate+".csv","Type");//System.getProperty("user.home") + "\\Downloads\\"+"submissions-"+submissionCreationDate+".csv",SubmissionName
			System.out.println(CSVReportData);
			System.out.println(CSVReportData.get(0));
			System.out.println(CSVReportData.get(1));
			System.out.println(CSVReportData.get(2));
			System.out.println(CSVReportData.get(3));
			System.out.println(CSVReportData.get(4));
			System.out.println(CSVReportData.get(5));
			System.out.println(CSVReportData.get(6));
			//Verify CSV file have columns type, email, first name, last name, role, language, organization groups.
			assertion=CSVReportData.get(0).contains("Type");
			if(assertion==true){
			System.out.println("csv Report Contains-->"+"Type");
			}else {
			System.out.println("csv Report not Contains-->"+"Type");
			report("f","Activity Report not Contains"+"Type");
			}
			//Verify data 
			assertion=CSVReportData.get(1).contains("Email");
			if(assertion==true){
			System.out.println("csv Report Contains-->"+"Email");
			}else {
			System.out.println("csv Report not Contains-->"+"Email");
			report("f","Activity Report not Contains"+"Email");
			}
			//Verify data 
			assertion=CSVReportData.get(2).contains("First Name");
			if(assertion==true){
			System.out.println("csv Report Contains-->"+"First Name");
			}else {
			System.out.println("csv Report not Contains-->"+"First Name");
			report("f","Activity Report not Contains"+"First Name");
			}
			//Verify data 
			assertion=CSVReportData.get(3).contains("Last Name");
			if(assertion==true){
			System.out.println("csv Report Contains-->"+"Last Name");
			}else {
			System.out.println("csv Report not Contains-->"+"Last Name");
			report("f","Activity Report not Contains"+"Last Name");
			}
			//Verify data 
			assertion=CSVReportData.get(4).contains("Role");
			if(assertion==true){
			System.out.println("csv Report Contains-->"+"Role");
			}else {
			System.out.println("csv Report not Contains-->"+"Role");
			report("f","Activity Report not Contains"+"Role");
			}
			//Verify data 
			assertion=CSVReportData.get(5).contains("Language");
			if(assertion==true){
			System.out.println("csv Report Contains-->"+"Language");
			}else {
			System.out.println("csv Report not Contains-->"+"Language");
			report("f","Activity Report not Contains"+"Language");
			}
			
			assertion=CSVReportData.get(6).contains("Groups");
			if(assertion==true){
			System.out.println("csv Report Contains-->"+"Groups");
			}else {
			System.out.println("csv Report Contains-->"+"Groups");
			report("f","Activity Report not Contains"+"Groups");
			}
			
			assertion=!CSVReportData.get(6).contains("Organization Groups");
			if(assertion==true){
			System.out.println("csv Report not Contains-->"+"Organization Groups");
			}else {
			System.out.println("csv Report Contains-->"+"Organization Groups");
			report("f","Activity Report Contains"+"Organization Groups");
			}
		
			
			
			
		} catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	public static String GetCurrentDate() throws Exception 	
	{		
		Calendar cal = Calendar.getInstance();		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());				
		return (dateFormat.format(cal.getTime()));
		
	}
	
	
	  public static String readCSVFileDataLineByLine(String filePath ,String SubmissionName) 
	  { 
	  	List<String> List = new ArrayList<String>();
	  	String cellValue = null;
	    
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
	                  		  cellValue=cell2;
	                  		  
	                  		  
	                  	  }
	                  }
	                  
	              } 
	             
	          }    
	        
	      } 
	      catch (Exception e) { 
	          e.printStackTrace(); 
	      }
	      return cellValue;
	  }
	  
	  

	
	
	public void assertion() throws Exception {
		//Verify that organization Groups is renamed to Groups in CSV report.
		assertion=CSVReportData.get(6).contains("Groups");
		if(assertion==true){
		System.out.println("csv Report Contains-->"+"Groups");
		report("p","Activity Report Contains"+"Groups");
		}else {
		System.out.println("csv Report Contains-->"+"Groups");
		report("f","Activity Report not Contains"+"Groups");
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
