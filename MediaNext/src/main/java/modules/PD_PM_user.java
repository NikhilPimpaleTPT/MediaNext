package modules;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.LoginLocators;
import locators.PD_Pm_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;

public class PD_PM_user extends General{
	
	
	private static PD_PM_user pd_pm_objects;

	 /**
	  * Method used to self-instantiate the class. Will make sure one object, and
	  * one object only is created in memory for this class. The purpose is to be
	  * able to call this object dynamically from any JAVA class that includes
	  * this as an import.
	  * 
	  * @return Returns the object instantiated from the class.
	  * @author pvaidya
	  */
	 public static synchronized PD_PM_user action() {
	  try {
	   if (pd_pm_objects.equals(null)) {
		   pd_pm_objects = new PD_PM_user();
	   }
	  } catch (Exception NOSYSTEM) {
		  pd_pm_objects = new PD_PM_user();
	  }
	  return pd_pm_objects;
	 }
	 
	 
	 public void logIn_to_PD(String user , String password) throws Exception {
		 
		    System.out.println(" Start of logIn_to_PD ()");
//		    Thread.sleep(5000);
//		    Click(PD_Pm_User_Submission_Locators.Locator().logIn_with_emailAddress);
			Thread.sleep(5000); 
	 
		    waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Email_input);
			ClearInputvalues(PD_Pm_User_Submission_Locators.Locator().Email_input);
			Thread.sleep(1000);
			type(PD_Pm_User_Submission_Locators.Locator().Email_input, user);
			Thread.sleep(1000);
			type(PD_Pm_User_Submission_Locators.Locator().Email_input,Keys.chord(Keys.TAB));			
			if(Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().continue_button, 5)){
				System.out.println("IN IF****************");
				Click(PD_Pm_User_Submission_Locators.Locator().continue_button);
				Thread.sleep(1000);
			}
			waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().password_input);
			ClearInputvalues(PD_Pm_User_Submission_Locators.Locator().password_input);
			Thread.sleep(1000);
			type(PD_Pm_User_Submission_Locators.Locator().password_input, password);
			Thread.sleep(2000);
			Click(PD_Pm_User_Submission_Locators.Locator().continue_button);
			Thread.sleep(10000);
			System.out.println("EOM logIn_to_PD ()");
			
	 }
		
	 public void logOut_to_PD() throws Exception {
		    System.out.println(" Start of logOut_to_PD ()");
		    Thread.sleep(5000);
		    waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().userIcon);
		    Click(PD_Pm_User_Submission_Locators.Locator().userIcon);
			Thread.sleep(5000); 
	 
		    waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().userIcon_options_logout);
		    Click(PD_Pm_User_Submission_Locators.Locator().userIcon_options_logout);
			Thread.sleep(4000);
			waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().userIcon_options_logout_alert);
			Thread.sleep(2000);
			
			waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().userIcon_options_logout_yesButton);
			Click(PD_Pm_User_Submission_Locators.Locator().userIcon_options_logout_yesButton);
			Thread.sleep(4000);
			
			if(Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().logIn_with_emailAddress,10)==true) {
			Thread.sleep(4000);
			General.action().Click(PD_Pm_User_Submission_Locators.Locator().logIn_with_emailAddress);
			Thread.sleep(4000);
				
			}
			
			Thread.sleep(10000);
			System.out.println("EOM logOut_to_PD()");
			
	 }
			
	 
	 
	 public  void Open_PD_Instance_URL() throws Exception {
		  
		    System.out.println("******INSIDE METHOD Open_PD_Instance_URL() ************");
		    
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			Thread.sleep(8000);
			robot.keyRelease(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
			
//			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
//			Thread.sleep(3000);
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(3000);
		    driver.get(CommonElements.action().PD_INSTANCE_URL);
//		    Thread.sleep(3000);
//			
//				
//			driver.get(CommonElements.action().PD_INSTANCE_URL);
			Thread.sleep(8000);	
				
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(8000);	
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(3000);
			
			
			driver.get(CommonElements.action().PD_INSTANCE_URL);
			Thread.sleep(8000);	
			
			driver.navigate().refresh();
			Thread.sleep(8000);	
			ClickX(PD_Pm_User_Submission_Locators.Locator().logIn_with_emailAddress);
			Thread.sleep(5000); 
			System.out.println("****** EOM Open_PD_Instance_URL() ************");
									
			}
	 
	 
	 public  void Open_PD_Instance_URL_Admin() throws Exception {
		  
		    System.out.println("******INSIDE METHOD Open_PD_Instance_URL() ************");
		    
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			Thread.sleep(8000);
			robot.keyRelease(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
			
//			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
//			Thread.sleep(3000);
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			Thread.sleep(3000);
		    driver.get(CommonElements.action().PD_INSTANCE_URL);
//		    Thread.sleep(3000);
//			
//				
//			driver.get(CommonElements.action().PD_INSTANCE_URL);
			Thread.sleep(8000);	
				
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(8000);	
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(3000);
			
			
			driver.get(CommonElements.action().PD_INSTANCE_URL);
			Thread.sleep(8000);	
			
			driver.navigate().refresh();
			Thread.sleep(8000);
			System.out.println("****** EOM Open_PD_Instance_URL() ************");
									
			}
	 
	 
	 public void admin_login(String username,String password) throws InterruptedException {
		 
		 
		 waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().loginWithLocalAccount);
		 Thread.sleep(2000);
		 General.action().Click(PD_Pm_User_Submission_Locators.Locator().loginWithLocalAccount);
	     Thread.sleep(2000);
	     waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().admin_username_input);
		 Thread.sleep(2000);
		 General.action().Click(PD_Pm_User_Submission_Locators.Locator().admin_username_input);
	     Thread.sleep(2000);
		 General.action().ClearInputvalues(PD_Pm_User_Submission_Locators.Locator().admin_username_input);
		 Thread.sleep(2000);
		 General.action().type(PD_Pm_User_Submission_Locators.Locator().admin_username_input,username);
		 Thread.sleep(2000);
		 General.action().Click(PD_Pm_User_Submission_Locators.Locator().admin_password_input);
	     Thread.sleep(2000);
		 General.action().ClearInputvalues(PD_Pm_User_Submission_Locators.Locator().admin_password_input);
		 Thread.sleep(2000);
		 General.action().type(PD_Pm_User_Submission_Locators.Locator().admin_password_input,password);
		 Thread.sleep(2000);
		 waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().admin_loginButton);
		 Thread.sleep(2000);
		 General.action().Click(PD_Pm_User_Submission_Locators.Locator().admin_loginButton);
	     Thread.sleep(2000);
	 }
			
	 
	  public void Create_PD_Submisson_Step1_ProjectDirector(String instance,String department,String workflow, String client, String jobNum ) throws Exception{
			System.out.println("INSIDE method Create_PD_Submisson_Step1_ProjectDirector()\n");

			waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
			Thread.sleep(1000);
			Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
			Thread.sleep(1000);
			waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_User_New_PD_Submission_Button);
			Thread.sleep(1000);
			Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_New_PD_Submission_Button);
			Thread.sleep(1000);
			
			waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instance);
			Thread.sleep(1000);
			Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instance);
			Thread.sleep(1000);
			Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instanceDropDownOptions(instance));
			Thread.sleep(5000);
			waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_department);
			Thread.sleep(1000);
			Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_department);
			Thread.sleep(1000);
			waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_departmentDropDownOptions(department));
			Thread.sleep(2000);
			Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_departmentDropDownOptions(department));
			Thread.sleep(4000);
			waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_workflow);
			Thread.sleep(4000);
			Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_workflow);
			Thread.sleep(6000);
			Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_workflowDropDownOptions(workflow));
			Thread.sleep(2000);
			Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_client);
	    	Thread.sleep(4000);
	    	Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_clientDropDownOptions(client));
			Thread.sleep(2000);
	    	Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_jobNo);
			Thread.sleep(2000);
		    type(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_jobNo, jobNum);
	    	Thread.sleep(4000);
	    	
	    	mouseOver(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
      	    Thread.sleep(1000);
	    	Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
	    	Thread.sleep(3000);
			 
			System.out.println("EOM Create_PD_Submisson_Step1_ProjectDirector()\n");
	    }
	    
	 
	  /**
	     * @updated : Pvaidya
	     * 
	     * @param DueDate
	     * @param SubmissionName
	     * @param SourceLanguage
	     * @param TargetLanguage
	     * @throws Exception
	     */
	  
	    public void Create_PD_Submisson_Step2_MetaData(String DueDate,String SubmissionName,String OrganizationName,String SourceLanguage,String TargetLanguage) throws Exception {
	    	System.out.println("INSIDE method Create_PD_Submisson_Step2_MetaData()\n"); 
	    	
	    	System.out.println("DATE IMPLEMENTATION");
	    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
			String newdate = GetDataPlus(Integer.valueOf(DueDate));
			System.out.println(newdate);
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);
	    	ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(2000);		
	    	
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_arrow);
	    	Thread.sleep(1000);		
	    	
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_year(newdate.substring(9, 13)));
	    	Thread.sleep(1000);		
	    	
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_month(newdate.substring(0, 3).toUpperCase()));
	    	Thread.sleep(1000);		
	    	if(newdate.substring(5, 6).contentEquals("0")){
	    		System.out.println("IF--->"+newdate.substring(6, 7));
	    		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(6, 7)));
	    		
	    		Thread.sleep(1000);		
	    	}else{
	    		System.out.println("ELSE--->"+newdate.substring(5, 7));
	    		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(5, 7)));
	    		Thread.sleep(1000);		
	    	}

	    	System.out.println("DATE IMPLEMENTATION END--------------");

	    	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input, SubmissionName);
	    	Thread.sleep(3000);
	    	
	    	Thread.sleep(defaultWaitPeriod*2);
	    	Thread.sleep(1000);
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	    	Thread.sleep(defaultWaitPeriod*10);
	    	Thread.sleep(2000);
	    	System.out.println("CLICKED");
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
	    	Thread.sleep(2000);
	    	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, SourceLanguage);
	    	Thread.sleep(3000);
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage));
	    	Thread.sleep(3000);
	    	if(TargetLanguage!=""){
	    		System.out.println("TARGET LANGUAGE NOT NULL");
		    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
		    	Thread.sleep(1000);
		    	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input, TargetLanguage);
		    	Thread.sleep(1000);
		    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(TargetLanguage));
		    	Thread.sleep(4000);
	    	}
	    	waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
	    	Thread.sleep(1000);
	    	Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
	    	Thread.sleep(20000);
	    	
  	    System.out.println("EOM Create_PD_Submisson_Step2_MetaData()\n");
	    }
	    
	    
	    public String Create_PD_Submisson_Step2_MetaData_returnDueDate(String DueDate,String SubmissionName,String OrganizationName,String SourceLanguage,String TargetLanguage) throws Exception {
	    	System.out.println("INSIDE method Create_PD_Submisson_Step2_MetaData()\n"); 
	    	
	    	System.out.println("DATE IMPLEMENTATION");
	    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
			String dueDate = PD_PM_user.action().GetDataPlus(Integer.valueOf(DueDate));
			System.out.println(dueDate);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);
	    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(2000);		
	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_arrow);
	    	Thread.sleep(1000);		
	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_year(dueDate.substring(9, 13)));
	    	Thread.sleep(1000);		
	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_month(dueDate.substring(0, 3).toUpperCase()));
	    	Thread.sleep(1000);		
	    	if(dueDate.substring(5, 6).contentEquals("0")){
	    		System.out.println("IF--->"+dueDate.substring(6, 7));
	    		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(dueDate.substring(6, 7)));
	    		
	    		Thread.sleep(1000);		
	    	}else{
	    		System.out.println("ELSE--->"+dueDate.substring(5, 7));
	    		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(dueDate.substring(5, 7)));
	    		Thread.sleep(1000);		
	    	}

	    	System.out.println("DATE IMPLEMENTATION END--------------");
	    	
	    	String dueDateTime=GetDataPlus_time(15);
	    	System.out.println("dueDateTime:"+dueDateTime);
	    	
	    	Thread.sleep(1000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueTime_input);
	    	Thread.sleep(2000);	
	    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueTime_input);
	    	Thread.sleep(2000);
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueTime_input, dueDateTime);
	    	Thread.sleep(3000);

	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueTime_input, SubmissionName);
	    	Thread.sleep(3000);
	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    	Thread.sleep(2000);	
	    	
	    	Thread.sleep(General.action().defaultWaitPeriod*2);
	    	Thread.sleep(1000);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	    	Thread.sleep(General.action().defaultWaitPeriod*10);
	    	Thread.sleep(2000);
	    	System.out.println("CLICKED");
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
	    	Thread.sleep(2000);
	    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, SourceLanguage);
	    	Thread.sleep(3000);
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage));
	    	Thread.sleep(3000);
	    	if(TargetLanguage!=""){
	    		System.out.println("TARGET LANGUAGE NOT NULL");
	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
		    	Thread.sleep(1000);
		    	General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input, TargetLanguage);
		    	Thread.sleep(1000);
		    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(TargetLanguage));
		    	Thread.sleep(4000);
	    	}
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
	    	Thread.sleep(20000);
	    	
		    System.out.println("EOM Create_PD_Submisson_Step2_MetaData()\n");
			return dueDate;
	    }
	    
	    
	 
	    
	    public void Create_PD_Submisson_Step3_videoFile(String FilePath , boolean uploadFromComputer ,String assetID) throws Exception{
	    	System.out.println("INSIDE method Create_PD_Submisson_Step3_videoFile()\n");
	    	Thread.sleep(3000);
	    
	    	if(uploadFromComputer) {
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectFromComputer_RadioButton);
    		Thread.sleep(3000);
    		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectFromComputer_RadioButton);
			Thread.sleep(2000);
	    	String path = null;
	    	File afile=new File(FilePath);
			File[] listOfFiles={afile};
	    	if (afile.isDirectory())    
	    	    listOfFiles = afile.listFiles();
	    	   
	    	   Thread.sleep(defaultWaitPeriod*2);
	    	      
	    	   //Process array
	    	    for (int i = 0; i < listOfFiles.length; i++)
	    	    { 
	    	     if(listOfFiles[i].isDirectory()) 
	    	    	 continue;
	    	    
	    	      path = listOfFiles[i].getAbsolutePath();
	    	     
	    	    }
	    	 	Thread.sleep(1000);
		   
		    	//TODO NEW IMPL
		    	System.out.println("path---->"+path);
		    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_videoUpload_input);
		    	Thread.sleep(1000);
		    	type(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_videoUpload_input,path);
		    	Thread.sleep(1000);
		    	
	    	}else {
	    		
	    		 System.out.println("INSIDE METHOD CreateSubmisson_Step3_SearchInCatalog()");
	    		 
	 		    Thread.sleep(3000);
//	 		    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_RadioButton);
//	 	    	Thread.sleep(3000);
//	 	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_RadioButton);
	 			Thread.sleep(2000);
	 			Thread.sleep(3000);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID);
			    Thread.sleep(3000);
			    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID);
				Thread.sleep(2000);
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID, assetID);
				Thread.sleep(2000);
			    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_SearchBtn);
				Thread.sleep(15000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_fileFirstRow(assetID));
				Thread.sleep(2000);
				
				 System.out.println("EOD Create_PD_Submisson_Step3_videoFile()");
	    		
	    	}
	    } 
	    
	  //##########################################################################################################################
	       
	    public String Create_PD_Submisson_Step3_VideoSRTFile(String FilePath) throws Exception {
	    	System.out.println("INSIDE method Create_PD_Submisson_Step3_VideoSRTFile()\n");
	
	    	String path = null;
	    	File afile=new File(FilePath);
			File[] listOfFiles={afile};
	    	if (afile.isDirectory())    
	    	    listOfFiles = afile.listFiles();
	    	   
	    	   Thread.sleep(defaultWaitPeriod*2);
	    	      
	    	   //Process array
	    	    for (int i = 0; i < listOfFiles.length; i++)
	    	    { 
	    	     if(listOfFiles[i].isDirectory()) 
	    	    	 continue;
	    	    
	    	      path = listOfFiles[i].getAbsolutePath();
	    	     
	    	    }
	    	 	Thread.sleep(1000);
	    	 	
		    	System.out.println("path---->"+path);
		    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input);
		    	Thread.sleep(1000);
		    	type(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input,path);
		    	Thread.sleep(1000);
                //TODO Steps are changed from 2.7.0 RC1
//		    	waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
//		    	Thread.sleep(1000);
//		    	Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
//		    	Thread.sleep(20000);
		    	
		    	String CreationDateOfSubmission = PD_PM_user.action().GetDataPlus_CreationDate();
				System.out.println("CreationDateOfSubmission:"+CreationDateOfSubmission);
				Thread.sleep(1000);
		    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
		    	Thread.sleep(1000);
		    	
		    	
				
		    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
		    	Thread.sleep(20000);
				
	    	    System.out.println("EOM Create_PD_Submisson_Step3_VideoSRTFile()\n");
				return CreationDateOfSubmission;

	    }
	    
	    
	    
	    
	    
	    
	    
	    
	 
	 public  void Create_PD_Submisson_Step4_MediSettings(String ReadingSpeed,String MaxCharperLine,String MindurationVSreadingSpeed,String MaxdurationVSreadingSpeed) throws Exception{
		 System.out.println("INSIDE method Create_PD_Submisson_Step4_MediSettings()\n");
	
		
			ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input);
			Thread.sleep(1000);
			type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input,ReadingSpeed);
			Thread.sleep(1000);
			
			ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
			Thread.sleep(1000);
			type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,MaxCharperLine);
			Thread.sleep(1000);
			
			ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
			Thread.sleep(1000);
			type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,MindurationVSreadingSpeed);
			Thread.sleep(1000);
			
			ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
			Thread.sleep(1000);
			type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,MaxdurationVSreadingSpeed);
			Thread.sleep(1000);
			//TODO Steps are changed from 2.7.0 RC1
//			String CreationDateOfSubmission = PD_PM_user.action().GetDataPlus_CreationDate();
//			System.out.println("CreationDateOfSubmission:"+CreationDateOfSubmission);
//			Thread.sleep(1000);
//	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
//	    	Thread.sleep(1000);
//	    	
//	    	
//			
//	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
//	    	Thread.sleep(20000);
			
			waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
	    	Thread.sleep(1000);
	    	Click(PD_Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
	    	Thread.sleep(20000);
	    	
			System.out.println("EOM Create_PD_Submisson_Step4_MediSettings()\n");
			
			//TODO Steps are changed from 2.7.0 RC1
			//return CreationDateOfSubmission;

		}
	 
	   public void search_PD_submission_andClick(String option,String submissionID) throws InterruptedException {
		    Thread.sleep(20000);
		    
		    Thread.sleep(2000);
	    	General.action().ClearInputvalues(PD_Pm_User_Submission_Locators.Locator().PD_submission_chooseFilter_input);
	    	Thread.sleep(1000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_arrow);
	    	Thread.sleep(3000);
	    	General.action().ClickX(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_arrow);
	    	Thread.sleep(4000);	
	    	
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_options(option));
	    	Thread.sleep(3000);
	    	General.action().ClickX(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_options(option));
	    	Thread.sleep(4000);	
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_input);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_input);
	    	Thread.sleep(5000);
	    	General.action().ClearInputvalues(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_input);
	    	Thread.sleep(1000);
	    	General.action().type(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_input, submissionID);
	    	Thread.sleep(5000);
	    	PM_user.action().type(PD_Pm_User_Submission_Locators.Locator().PD_submission_filter_input, Keys.chord(Keys.ENTER));
	    	Thread.sleep(3000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_searchSubmission_firstRow(submissionID));
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_searchSubmission_firstRow(submissionID));
	    	Thread.sleep(4000);
	    	
	    	
	    }
	   
	   
	   public void search_PD_project_andClick(String option,String projectName) throws InterruptedException {
		    Thread.sleep(20000);
		    General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_projectTab);
		    Thread.sleep(2000);
//	    	General.action().ClearInputvalues(PD_Pm_User_Submission_Locators.Locator().PD_project_chooseFilter_input);
//	    	Thread.sleep(1000);
//	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_project_filter_arrow);
//	    	Thread.sleep(3000);
//	    	General.action().ClickX(PD_Pm_User_Submission_Locators.Locator().PD_project_filter_arrow);
//	    	Thread.sleep(4000);	
//	    	
//	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_project_filter_options(option));
//	    	Thread.sleep(3000);
//	    	General.action().ClickX(PD_Pm_User_Submission_Locators.Locator().PD_project_filter_options(option));
//	    	Thread.sleep(4000);	
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_project_filter_input);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_project_filter_input);
	    	Thread.sleep(5000);
	    	General.action().ClearInputvalues(PD_Pm_User_Submission_Locators.Locator().PD_project_filter_input);
	    	Thread.sleep(1000);
	    	General.action().type(PD_Pm_User_Submission_Locators.Locator().PD_project_filter_input, projectName);
	    	Thread.sleep(5000);
	    	PM_user.action().type(PD_Pm_User_Submission_Locators.Locator().PD_project_filter_input, Keys.chord(Keys.ENTER));
	    	Thread.sleep(3000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_searchProject_firstRow(projectName));
	    	Thread.sleep(3000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_searchProject_firstRow(projectName));
	    	Thread.sleep(4000);
	    	
	    	
	    }
	   
	   
	   public void analyse_PD_submission() throws InterruptedException {
			
			General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_analyzeButton);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_analyzeButton);
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_analyze_yesButton);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_analyze_yesButton);
	    	Thread.sleep(5000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_refreshButton);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_refreshButton);
	    	Thread.sleep(5000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_refreshButton);
	    	Thread.sleep(5000);
			
			
			
		}

	    
	    
	    public void editSubmission_addCustomAttributes(String attribute1,String attribute2,String attribute3) throws InterruptedException {
			
			General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
	    	Thread.sleep(2000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_activeButton);
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission);
	    	Thread.sleep(3000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission);
	    	Thread.sleep(5000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_customAttributes);
	    	Thread.sleep(5000);
	    	General.action().ClickX(PD_Pm_User_Submission_Locators.Locator().PD_submission_customAttributes);
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_subjectMatter_arrow);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_subjectMatter_arrow);
	    	Thread.sleep(2000);	
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_subjectMatter(attribute1));
	    	Thread.sleep(2000);	
	    	
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_subjectCategory_arrow);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_subjectCategory_arrow);
	    	Thread.sleep(2000);	
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_subjectCategory(attribute2));
	    	Thread.sleep(2000);	
	    	
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_reCreatedPDF_arrow);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_reCreatedPDF_arrow);
	    	Thread.sleep(2000);	
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_reCreatedPDF(attribute3));
	    	Thread.sleep(2000);	
	    	
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_Submission_customAttributes_okButton);
	    	Thread.sleep(2000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_editSubmission_saveButton);
	    	Thread.sleep(2000);	
			
			
			
		}
	    
	    
	    public void createBudget_PD_submission(String submissionID,String revenue , String transRate, String reviewRate) throws InterruptedException {
	    	Thread.sleep(3000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_searchSubmission_firstRow(submissionID));
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_searchSubmission_firstRow(submissionID));
	    	Thread.sleep(4000);
			General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget);
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_window);
	    	Thread.sleep(4000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_revenue);
	    	Thread.sleep(2000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_revenue);
	    	Thread.sleep(5000);	
	    	General.action().ClearInputvalues(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_revenue);
	    	Thread.sleep(1000);
	    	General.action().type(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_revenue,revenue);
	    	Thread.sleep(1000);
	    	
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_trans_rateUSD);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_trans_rateUSD);
	    	Thread.sleep(2000);
	    	PM_user.action().type(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_trans_rateUSD_input, Keys.chord(Keys.BACK_SPACE));
	    	Thread.sleep(3000);
	    	General.action().type(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_trans_rateUSD_input,transRate);
	    	Thread.sleep(3000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_window);
	    	Thread.sleep(5000);
	    	General.action().ClickX(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_NextButton);
	    	Thread.sleep(5000);	
	    	
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_translationPhase_rateUSD);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_translationPhase_rateUSD);
	    	Thread.sleep(5000);	
	    	PM_user.action().type(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_review_rateUSD_input, Keys.chord(Keys.BACK_SPACE));
	    	Thread.sleep(2000);
	    	General.action().type(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_review_rateUSD_input,reviewRate);
	    	Thread.sleep(2000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_window);
	    	Thread.sleep(5000);
	    
	    	
	    	General.action().ClickX(PD_Pm_User_Submission_Locators.Locator().PD_submission_createBudget_finishButton);
	    	Thread.sleep(2000);	
	    
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_selectVendors);
	    	Thread.sleep(2000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_selectVendors);
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_selectVendors_applyButton);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_selectVendors_applyButton);
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_selectVendors_OKButton);
	    	Thread.sleep(4000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_selectVendors_OKButton);
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_startSubmission_alert_Yes);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_startSubmission_alert_Yes);
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_refreshButton);
	    	Thread.sleep(5000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_refreshButton);
	    	Thread.sleep(2000);
	    	
	    	
	    	
	    	
	    }
	    
	    
	 public void vendor1_jobInfo(boolean transAccept ,boolean reviewAccept,String language) throws Exception {
		 
		 
		 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_available_jobInfo);
	     Thread.sleep(1000);
	     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_available_jobInfo);
	     Thread.sleep(2000);
	     General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_available_jobInfo_jobInfoIcon);
	     Thread.sleep(1000);
	     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_available_jobInfo_jobInfoIcon);
	     Thread.sleep(2000);
	     General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jobInfo_Language(language));
	     Thread.sleep(2000);
	     if(transAccept) {
	    	 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_acceptRadioButton);
		     Thread.sleep(1000);
		     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_acceptRadioButton);
		     Thread.sleep(2000);
	    	 
	     }else {
	    	 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_declineRadioButton);
		     Thread.sleep(1000);
		     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_declineRadioButton);
		     Thread.sleep(2000);
	    	 
	     }
	     Thread.sleep(2000);
	     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_confirmCheckbox);
	     Thread.sleep(2000);
	     General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_submitButton);
	     Thread.sleep(2000);
	     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_submitButton);
	     Thread.sleep(2000);
	     General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_infoAlter);
	     Thread.sleep(2000);
	     General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_infoAlter_close);
	     Thread.sleep(2000);
	     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_infoAlter_close);
	     Thread.sleep(2000);
	     
	     
	 }
	 
	 public void PD_submission_vendor1_TTML_link(String submissionName) throws Exception {
		 Thread.sleep(10000);
	     General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_fileManagement_link(submissionName));
	     Thread.sleep(5000);
	     General.action().ClickX(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_fileManagement_link(submissionName));
	     Thread.sleep(2000);
	     //Switch the pop up window 1
	 	 switchToPopupWindow(2);		
		 Thread.sleep(10000);
		 General.driver.manage().window().maximize();
		 Thread.sleep(2000);	 
	 }
	 
	 public void PD_submission_vendor1_onGoing_submission(boolean complete,boolean back) throws Exception {
			
		 System.out.println("INSIDE PD_submission_vendor1_onGoing_submission  method()");
			
				  Thread.sleep(5000);
			      List <WebElement> listofIds1= driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
				  Thread.sleep(1000);
				  System.out.println("No of IDS--------"+listofIds1.size());
				  Thread.sleep(3000);
				  
			    for(int i=1;i<=listofIds1.size();i++){
			    	  Thread.sleep(2000);
					waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					waitforelemenetpresent(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
					Thread.sleep(1000);
					Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
					Thread.sleep(1000);
				
			    }
			    
			    if(complete){
					waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
					Thread.sleep(1000);
					Click(TranslatorLocators.Locator().translator_Complete_Button);
					Thread.sleep(1000);
					waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
					Thread.sleep(1000);
					Click(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
					Thread.sleep(3000);
					if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
						General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
						Thread.sleep(1000);
						General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
				    	}
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().SelectMenu("To Claim"));
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().SelectMenu("Ongoing"));
					Thread.sleep(1000);
					General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().SelectMenu("Completed"));
					Thread.sleep(1000);
					
					
			    }
		
				
			    
			    if(back){
			    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
						General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
						Thread.sleep(1000);
						General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
				    	}
			    }
			    
				General.driver.close();
				Thread.sleep(2000);
				//Switch to the pop up window 0
				switchToPopupWindow(1);
				Thread.sleep(5000);
			    
			    Thread.sleep(2000);
				Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_saveButton);
				Thread.sleep(3000);
				Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_jovInfo_close);
				Thread.sleep(3000);
			    
				 System.out.println("EOM PD_submission_vendor1_onGoing_submission  method()");
	}
	 
	 
	 public void PD_submission_vendor1_completed() throws InterruptedException {
		 
		 waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_sent);
		 Thread.sleep(1000);
		 Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor1_sent);
		 Thread.sleep(1000);
		 
	 }
	 
	 
	 
	 
public void vendor2_jobInfo(boolean transAccept ,boolean reviewAccept,String language) throws Exception {
		 
		 
		 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_available_jobInfo);
	     Thread.sleep(1000);
	     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_available_jobInfo);
	     Thread.sleep(2000);
	     General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_available_jobInfo_jobInfoIcon);
	     Thread.sleep(1000);
	     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_available_jobInfo_jobInfoIcon);
	     Thread.sleep(2000);
	     General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jobInfo_Language(language));
	     Thread.sleep(2000);
	     if(transAccept) {
	    	 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_acceptRadioButton);
		     Thread.sleep(1000);
		     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_acceptRadioButton);
		     Thread.sleep(2000);
	    	 
	     }else {
	    	 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_declineRadioButton);
		     Thread.sleep(1000);
		     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_declineRadioButton);
		     Thread.sleep(2000);
	    	 
	     }
	     Thread.sleep(2000);
	     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_confirmCheckbox);
	     Thread.sleep(2000);
	     General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_submitButton);
	     Thread.sleep(2000);
	     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_submitButton);
	     Thread.sleep(2000);
	     General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_infoAlter);
	     Thread.sleep(2000);
	     General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_infoAlter_close);
	     Thread.sleep(2000);
	     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_infoAlter_close);
	     Thread.sleep(2000);
	     
	     
	 }

public void PD_submission_vendor2_TTML_link(String submissionName) throws Exception {
    General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_fileManagement_link(submissionName));
    Thread.sleep(4000);
    General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_fileManagement_link(submissionName));
    Thread.sleep(2000);
    //Switch the pop up window 1
	 switchToPopupWindow(2);		
	 Thread.sleep(10000);
	 General.driver.manage().window().maximize();
	 Thread.sleep(2000);	 
}

public void PD_submission_vendor2_onGoing_submission(boolean complete,boolean back) throws Exception {
	
	 System.out.println("INSIDE PD_submission_vendor2_onGoing_submission  method()");
		
	 
		
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
		    
			General.driver.close();
			Thread.sleep(2000);
			//Switch to the pop up window 1
			switchToPopupWindow(1);
			Thread.sleep(5000);
		    
		    Thread.sleep(2000);
			Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_saveButton);
			Thread.sleep(3000);
			Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_jovInfo_close);
			Thread.sleep(3000);
		    
			 System.out.println("EOM PD_submission_vendor1_onGoing_submission  method()");
}

public void PD_submission_vendor2_completed() throws InterruptedException {
	 
	 waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_completed);
	 Thread.sleep(1000);
	 Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_vendor2_completed);
	 Thread.sleep(1000);
	 
}


public void PD_submission_pm_completed() throws InterruptedException {
	 
	 waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_submission_pm_completed);
	 Thread.sleep(1000);
	 Click(PD_Pm_User_Submission_Locators.Locator().PD_submission_pm_completed);
	 Thread.sleep(1000);
	 
}

	    	
	 
	 
	 
	 public void SearchSubmisson(String SubmissionName) throws Exception {
	    	System.out.println("INSIDE method SearchSubmisson()\n"); 
	    	
	    	if(Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().filters_clearFilterButton_enabled, 5)) {
	    		Click(PD_Pm_User_Submission_Locators.Locator().filters_clearFilterButton);
	    		Thread.sleep(2000);	
	    	}

	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
	    	
	    	ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
	    	type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	    	Thread.sleep(4000);	

         //type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
	    	//TODO NOT REQUIRED AS PER JAVA 8
	    	//type_withKEYS(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.ENTER,false);
	    	Thread.sleep(1000);	
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
 	    System.out.println("EOM SearchSubmisson()\n");

	    }
	    
	    //#######################################################################################################################
	    /**
	     * This method is used to search and check submission
	     */
	    public void SearchSubmisson_andCheck(String SubmissionName) throws Exception {
	    	System.out.println("INSIDE method SearchSubmisson()\n"); 
	    	
	    	
	    	if(Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().filters_clearFilterButton_enabled, 5)) {
	    		Click(PD_Pm_User_Submission_Locators.Locator().filters_clearFilterButton);
	    		Thread.sleep(4000);	
	    	}

	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
	    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
	    	type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	    	Thread.sleep(1000);	
//	    	type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
//	    	Thread.sleep(1000);
	    	//TODO NOT REQUIRED AS PER JAVA 8
	    	//TODO FOR GL PLAY 1.22.0 WITH KEYS IS NOT WORKING
//	    	type_withKEYS(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.ENTER,false);
	    	Thread.sleep(1000);	
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
			Thread.sleep(1000);			
			General.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
			Thread.sleep(2000);
	    	
 	    System.out.println("EOM SearchSubmisson()\n");

	    }
	    
	    
	    public void SearchMultiSubmissons_andCheck(String SubmissionName) throws Exception {
	    	System.out.println("INSIDE method SearchSubmisson()\n"); 
	    	
	    	
	    	if(Verify.action().verifyElementPresent(PD_Pm_User_Submission_Locators.Locator().filters_clearFilterButton_enabled, 5)) {
	    		Click(PD_Pm_User_Submission_Locators.Locator().filters_clearFilterButton);
	    		Thread.sleep(4000);	
	    	}

	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
	    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
	    	type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	    	Thread.sleep(1000);	
//	    	type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
//	    	Thread.sleep(1000);
	    	//TODO NOT REQUIRED AS PER JAVA 8
	    	//TODO FOR GL PLAY 1.22.0 WITH KEYS IS NOT WORKING
//	    	type_withKEYS(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.ENTER,false);
	    	Thread.sleep(1000);	
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
			General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().SelectMultiSubmissions_Checkbox);
			Thread.sleep(1000);			
			General.action().Click(PD_Pm_User_Submission_Locators.Locator().SelectMultiSubmissions_Checkbox);
			Thread.sleep(2000);
	    	
 	    System.out.println("EOM SearchSubmisson()\n");

	    }
	 
	 
	 
	 
	 
	 public static String GetDataPlus(Integer Days) throws Exception {
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, Days);
			DateFormat dateFormat = new SimpleDateFormat(". dd, yyyy HH:mm", Locale.getDefault());
			DateFormat dateFormat2 = new SimpleDateFormat("MMMM");
			String monthParsed = dateFormat2.format(cal.getTime());
			return (monthParsed.substring(0, 3) + dateFormat.format(cal.getTime()));
		}
	 
	 public static String GetDataPlus_time(Integer sec) throws Exception {
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.SECOND, sec);
			
			DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
			String monthParsed = dateFormat.format(cal.getTime());
			return monthParsed;
		}
	 
	 public static String GetDataPlus_DueDate(Integer Days) throws Exception {
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, Days);
			DateFormat dateFormat = new SimpleDateFormat(". dd, yyyy HH:mm", Locale.getDefault());
			DateFormat dateFormat2 = new SimpleDateFormat("MMMM");
			String monthParsed = dateFormat2.format(cal.getTime());
			return (monthParsed.substring(0, 3) + dateFormat.format(cal.getTime()));
		}
		

	public static String GetDataPlus_CreationDate() throws Exception {
		
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
		//DateFormat dateFormat2 = new SimpleDateFormat("MMMM");
		String monthParsed = dateFormat.format(cal.getTime());
		return monthParsed;
	}
	
	 public static String GetDataPlus_formate(Integer Days) throws Exception {
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, Days);
			DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy", Locale.getDefault());
			String monthParsed = dateFormat.format(cal.getTime());
			return monthParsed;
		}
	
	public void search_PD_submission(String submissionId) throws InterruptedException {
		

		waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
    	Thread.sleep(1000);	
    	
    	ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
    	Thread.sleep(1000);	
    	type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, submissionId);
    	Thread.sleep(4000);	

    	Thread.sleep(1000);	
    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
    	Thread.sleep(1000);
		
		
	}
	
	
	public void switchToPopupWindow(int windowIndex) throws Exception{
		System.out.println("\nIn vendor.java -  switchToPopupWindow()\n");
		int noOfWindows=General.driver.getWindowHandles().toArray().length;
		System.out.println("No of Windows - "+noOfWindows);
		for(int i=0;i<noOfWindows;i++)
		{
			System.out.println("Printing window handles - "+General.driver.getWindowHandles().toArray()[i]);
		}
		
		General.driver.switchTo().window(General.driver.getWindowHandles().toArray()[windowIndex].toString());
		Thread.sleep(2000);
		  System.out.println("\nEOM - switchToPopupWindow().\n");
	}
	
	
	 public void copy_project(String projectName) throws InterruptedException {
		    Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_project_copyButton);
	    	Thread.sleep(3000);
	    	General.action().ClickX(PD_Pm_User_Submission_Locators.Locator().PD_project_copyButton);
	    	Thread.sleep(4000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_project_copyProject_input);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_project_copyProject_input);
	    	Thread.sleep(5000);
	    	General.action().ClearInputvalues(PD_Pm_User_Submission_Locators.Locator().PD_project_copyProject_input);
	    	Thread.sleep(1000);
	    	General.action().type(PD_Pm_User_Submission_Locators.Locator().PD_project_copyProject_input, projectName);
	    	Thread.sleep(5000);
	    	General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_project_copyProject_okButton);
	    	Thread.sleep(1000);
	    	General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_project_copyProject_okButton);
	    	Thread.sleep(5000);
	    	
	    	
	    	
	    }
	 
	 //Method to log out if there is tpt auth page 
	 public void tptAuthLogoutPage() throws InterruptedException {
		 
		 if(General.action().VerifyElementPresent(PD_Pm_User_Submission_Locators.Locator().PD_tptAuthLogoutPage_yesButton)) {
			 
		 General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_tptAuthLogoutPage_yesButton);
	     Thread.sleep(1000);
	     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_tptAuthLogoutPage_yesButton);
	     Thread.sleep(4000);
	     General.action().waitforelemenetpresent(PD_Pm_User_Submission_Locators.Locator().PD_tptAuthLogoutPage_here);
	     Thread.sleep(1000);
	     General.action().Click(PD_Pm_User_Submission_Locators.Locator().PD_tptAuthLogoutPage_here);
	     Thread.sleep(4000);
		 }
		 
		 
	 }
	 
	 
	 
    
	

}
