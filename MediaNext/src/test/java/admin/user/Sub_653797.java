package admin.user;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.UserLocators;
import modules.Verify;
import modules.admin;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_653797 {
	
Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 String sourcelanguage = "en-US";
	 String targetlanguage = "de-DE";
	 String firstname = "VFirstName_653797"+CommonElements.BROWSER+"R2";
	 String lastName = "VLastName_653797"+CommonElements.BROWSER+"R2";
	 String selectedRole = "Linguist";
	 String emailid = "Vendor653797"+CommonElements.BROWSER+"R2@gmail.com";
	 String menu = "Users";
	 String UserType = "Vendor";
	 String Typename="Vendor";
	
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_653797");
	dataSet.put("TL_test_case_description","SUB-653797:Filtering users on Users list - First Name & Last name");
	dataSet.put("TL_internal_testCase_ID", "653797");
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
				admin.action().CreateUser_Step1(UserType,firstname,lastName, emailid);
				admin.action().Create_And_EditUser_SelectRole(selectedRole);
				admin.action().CreateUser_SelectLanguage(sourcelanguage, targetlanguage);
				admin.action().Create_And_SaveUser();
				
				General.action().waitforelemenetpresent(UserLocators.Locator().UserCreated_message(firstname,lastName));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().UserCreated_message(firstname,lastName), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for User Created Succesfully");
				
				
				// Filter user name with first name
				Thread.sleep(1000);
				admin.action().Searchuser(firstname);
				General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying FirstName after search on User page");
				
				// Filter user name with partial search
				Thread.sleep(2000);
				admin.action().Searchuser("VFirstName");
				General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data("VFirstName"));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data("VFirstName"), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying partial search on User page");
				

				// Filter user name with last name
				Thread.sleep(2000);
				admin.action().Searchuser(lastName);
				General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(lastName));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(lastName), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying LastName after search on User page");
				
				//Verify result should show all the users with the particular e-mail address
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(emailid), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying LastName after search on User page");
				
				General.action().waitforelemenetpresent(UserLocators.Locator().Searchuser_input);
				Thread.sleep(2000);
				General.action().ClearInputvalues(UserLocators.Locator().Searchuser_input);
				Thread.sleep(2000);
				
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searchuser_input, 5);
				if(assertion==false)
					report("f","Assertion failed while verifying input search by e-mail , first name and last name address");
				
				
				
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			
		
	}
	public void assertion() throws Exception {
		
		//Verify The tool tip on the user's search field should mention that the search by e-mail , first name and last name address is possible
		assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searchuser_input, 5);
		if(assertion==false)
			report("f","Assertion failed while verifying input search by e-mail , first name and last name address");else{
		    report("p", "All Assertion passed.");
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



