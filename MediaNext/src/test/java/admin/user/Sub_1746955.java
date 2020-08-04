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
import modules.PM_user;
import modules.Verify;
import modules.admin;

/**
 * 
 * @author swati thakare
 * This test case verifies that only admin user can create another admin user.
  */

public class Sub_1746955 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	String firstname = "VFirstName_1746955"+CommonElements.BROWSER+"A1";
	String lastName = "VLastName_1746955"+CommonElements.BROWSER+"A1";
	String selectedRole = "admin";
	String emailid = "Vendor1746955"+CommonElements.BROWSER+"A1@gmail.com";
	String menu = "Users";
	String UserType = "Vendor";
	String Typename="Vendor";
	
	String PMfirstname = "gym";
	String PMlastName = "sys";
	String PMselectedRole = "admin";
	String PMemailid = "D9@gmail.com";
	String PMmenu = "Users";
	String PMUserType = "Vendor";
	String PMTypename="Vendor";
	
	 @BeforeMethod	
		public void setup() throws Exception{
		General.action().startSystem("SUB_1746955");
		dataSet.put("TL_test_case_description","SUB-1746955:only admin user can create another admin user");
		dataSet.put("TL_internal_testCase_ID", "1746955");
	}
	 
	 @Test
		public void execute() throws Exception {
			testCase(dataSet);
			assertion();
		}

	 public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			
				admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
				admin.action().Navigate(menu);
				admin.action().CreateUser_Step1(UserType,firstname,lastName, emailid);
				admin.action().Create_And_EditUser_SelectRole(selectedRole);
				admin.action().Create_And_SaveUser();
				Thread.sleep(2000);
				General.action().waitforelemenetpresent(UserLocators.Locator().UserCreated_message(firstname,lastName));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().UserCreated_message(firstname,lastName), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for User Created Succesfully");
				Thread.sleep(1000);
				
				admin.action().Searchuser(firstname);
				General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname), 5);
				System.out.println(assertion);
				if(assertion==false)
					report("f","Assertion failed while verifying FirstName after search on User page");
				Thread.sleep(2000);
				
				General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(selectedRole));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(selectedRole), 5);
				System.out.println(assertion);
				if(assertion==false)
					report("f","Assertion failed while verifying role of a user");
				Thread.sleep(2000);
				
				General.action().logoutMethod();
				
				//pm login
				PM_user.action().login(CommonElements.action().PM_username,CommonElements.action().password);
				Thread.sleep(8000);
				admin.action().Navigate(menu);
				admin.action().CreateUser_Step1(PMUserType,PMfirstname,PMlastName,PMemailid);
				admin.action().Create_And_EditUser_SelectRole(PMselectedRole);
				admin.action().Create_And_SaveUser();
				Thread.sleep(2000);
				
				assertion = Verify.action().verifyTextPresent("Only admin users can create/edit users with the admin role.", 5);
				if(assertion==false)
					report("f","Assertion failed while verifying PM user can not create admin user");
				Thread.sleep(2000);

	 }
			
	 
	 public void assertion() throws Exception {
		 assertion = Verify.action().verifyTextPresent("Only admin users can create/edit users with the admin role.", 5);
			if(assertion==false)
				report("f","Assertion failed while verifying PM user can not create admin user");
			else{
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


