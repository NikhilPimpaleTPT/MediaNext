package admin.user;
import static org.testng.AssertJUnit.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.UserLocators;
import modules.Verify;
import modules.admin;

public class Sub_54734 {

Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 String sourcelanguage = "en-US";
	 String targetlanguage = "de-DE";
	 String sourcelanguage1 = "ab";
	 String targetlanguage1 = "ak";
	 String First_name = "first_54734"+CommonElements.BROWSER+"R2";
	 String firstname_updated = "User_54734"+CommonElements.BROWSER+"R2_updated";
	 String phoneno_updated = "7894562311";
	 String last_name = "last_54734"+CommonElements.BROWSER+"R2";
	 String lastname_update = "User 54734"+CommonElements.BROWSER+"R2Updated";
	 String selectedRole = "PM";
	 String selectedRole1 = "admin";
	 String emailid = "abc54734"+CommonElements.BROWSER+"R2@gmail.com";
	 String emailid_updated = "abc54734"+CommonElements.BROWSER+"R2_updated@gmail.com";
	 String menu = "Users";
	 String UserType = "Employee";
	 String submissionCreationDate;
	 
	
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_54734");
	dataSet.put("TL_test_case_description","SUB-54734:Edit User");
	dataSet.put("TL_internal_testCase_ID", "54734");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
				admin.action().Navigate(menu);
				admin.action().CreateUser_Step1(UserType, First_name, last_name, emailid);
				admin.action().Create_And_EditUser_SelectRole(selectedRole);
				admin.action().CreateUser_SelectLanguage(sourcelanguage, targetlanguage);
				admin.action().Create_And_SaveUser();
				submissionCreationDate=GetDataPlus_CreationDate();
				System.out.println("users creation date---->"+submissionCreationDate);
				
				
				General.action().waitforelemenetpresent(UserLocators.Locator().UserCreated_message(First_name,last_name));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().UserCreated_message(First_name,last_name), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for User Created Succesfully");
				
				Thread.sleep(1000);
				admin.action().Searchuser(First_name);
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_date(First_name,submissionCreationDate), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying submissionCreationDate");
				}
				admin.action().EditUser_open(First_name);
				admin.action().EditUser_Step1(firstname_updated, lastname_update, phoneno_updated, emailid_updated);
				admin.action().Create_And_EditUser_SelectRole(selectedRole1);
				admin.action().CreateUser_SelectLanguage(sourcelanguage1, targetlanguage1);
				admin.action().Create_And_SaveUser();
				
				General.action().waitforelemenetpresent(UserLocators.Locator().Userupdated_message(firstname_updated,lastname_update));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Userupdated_message(firstname_updated,lastname_update), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for User Updated Succesfully");
				admin.action().Searchuser(firstname_updated);
				
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(emailid), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying updated User Full Name Visible on page");
				
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_date(firstname_updated,submissionCreationDate), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying submissionCreationDate");
				}
				
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			
		
	}
	public void assertion() throws Exception {
		//TODO email is not editable so , can not verify updated email id
//		assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(emailid_updated), 5);
//		if(assertion==false){
//			report("f","Assertion failed while verifying Updated Username visible on page");
//		}
		
		assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_date(firstname_updated,submissionCreationDate), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying submissionCreationDate");
		}else{
		    report("p", "All Assertion passed.");
		}
	}
	
	public static String GetDataPlus_CreationDate() throws Exception {
		
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
		//DateFormat dateFormat2 = new SimpleDateFormat("MMMM");
		String monthParsed = dateFormat.format(cal.getTime());
		return monthParsed;
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
