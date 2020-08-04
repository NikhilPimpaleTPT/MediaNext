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

public class Sub_764784 {
	
Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 String sourcelanguage = "en-US";
	 String targetlanguage = "de-DE";
	 String firstname = "VFirst_764784"+CommonElements.BROWSER+"P1";
	 String lastName = "VLast_764784"+CommonElements.BROWSER+"P1";
	 String selectedRole = "Linguist";
	 String emailid = "Vendor_764784"+CommonElements.BROWSER+"P1@gmail.com";
	 String menu = "Users";
	 String UserType = "Vendor";
	 String Typename="Vendor";
	
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_764784");
	dataSet.put("TL_test_case_description","SUB-764784:Create duplicate user");
	dataSet.put("TL_internal_testCase_ID", "764784");
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
				
				Thread.sleep(1000);
				admin.action().Searchuser(firstname);
				General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying Fullname on User page");
				
				//create duplicate user
				admin.action().Navigate(menu);
				admin.action().CreateUser_Step1(UserType,firstname,lastName, emailid);
				admin.action().Create_And_EditUser_SelectRole(selectedRole);
				admin.action().CreateUser_SelectLanguage(sourcelanguage, targetlanguage);
				admin.action().Create_And_SaveUser();
				
				General.action().waitforelemenetpresent(UserLocators.Locator().UserAlreadyCreated_message());
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().UserAlreadyCreated_message(), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for User Created Succesfully");
				
				}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			}
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(UserLocators.Locator().UserAlreadyCreated_message(), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Username after search");
		}else{
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



