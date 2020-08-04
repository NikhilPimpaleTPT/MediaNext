package admin.user;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

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

public class Sub_290234 {


Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 String sourcelanguage = "en-US";
	 String targetlanguage = "de-DE";
	 String firstname = "VFirst_290234"+CommonElements.BROWSER+"R1";
	 String lastName = "VLast_290234"+CommonElements.BROWSER+"R1";
	 String selectedRole = "PM";
	 String emailid = "Vendor290234"+CommonElements.BROWSER+"R1@gmail.com";
	 String menu = "Users";
	 String menu_Roles = "Roles";
	 String UserType = "Vendor";
	 String Typename="Vendor";
	
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_290234");
	dataSet.put("TL_test_case_description","SUB-290234:Delete User");
	dataSet.put("TL_internal_testCase_ID", "290234");
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
				Thread.sleep(5000);
				General.action().waitforelemenetpresent(UserLocators.Locator().UserCreated_message(firstname,lastName));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().UserCreated_message(firstname,lastName), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for User Created Succesfully");
				admin.action().Searchuser(firstname);
				Thread.sleep(2000);
				General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying Fullname on User page");
				Thread.sleep(2000);
				admin.action().DeleteUser(firstname);
				Thread.sleep(3000);
				admin.action().Navigate(menu_Roles);
				Thread.sleep(3000);
				admin.action().Navigate(menu);
				Thread.sleep(3000);

				admin.action().Searchuser(firstname);
				Thread.sleep(2000);
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			
		
	}
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementNotPresent(UserLocators.Locator().Searcheduser_data(firstname), 5);
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
