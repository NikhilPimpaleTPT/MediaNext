package admin.roles;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.RoleLocators;
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

public class Sub_764776 {
	
Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 //for role
	 String Roles_permission_Edit[] = { "Edit Subtitles" };
     String RoleName = "Role_764776"+CommonElements.BROWSER+"M2";
	 String Description = "Role for 764776"+CommonElements.BROWSER+"M2";
	 
     //for user
	 String sourcelanguage = "en-US";
	 String targetlanguage = "de-DE";
	 String firstname = "VFirst_764776"+CommonElements.BROWSER+"M2";
	 String lastName = "VLast_764776"+CommonElements.BROWSER+"M2";
	 String selectedRole =RoleName;/* "Role_764776"+CommonElements.BROWSER+"PS";*/
	 String emailid = "Vendor764776"+CommonElements.BROWSER+"M2@gmail.com";
	 String menu = "Users";
	 String UserType = "Vendor";
	 String Typename="Vendor";
	 
	
@BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_764776");
	dataSet.put("TL_test_case_description","SUB-764776:Delete a Role having which is assigned to some users");
	dataSet.put("TL_internal_testCase_ID", "764776");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
		    admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
		    admin.action().Navigate("Roles");
			admin.action().CreateRole_Step1(RoleName, Description);
			admin.action().Create_AND_EditRole_Permission_manage(Roles_permission_Edit,true);
			admin.action().Create_And_SaveRole_CreateStep();
			
			General.action().waitforelemenetpresent(RoleLocators.Locator().Rolecreated_message(RoleName));
			assertion=Verify.action().verifyElementPresent(RoleLocators.Locator().Rolecreated_message(RoleName), 5);
			if(assertion==false)
				report("f","Assertion failed while verifying message for Role created");
			
			Thread.sleep(3000);
			admin.action().Navigate(menu);
			admin.action().CreateUser_Step1(UserType,firstname,lastName, emailid);
			admin.action().Create_And_EditUser_SelectRole(selectedRole);
			admin.action().CreateUser_SelectLanguage(sourcelanguage, targetlanguage);
			admin.action().Create_And_SaveUser();
			admin.action().Searchuser(firstname);
			Thread.sleep(1000);
			
			General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname));
			assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname), 5);
			if(assertion==false)
				report("f","Assertion failed while verifying Fullname on User page");
			Thread.sleep(3000);
			
			admin.action().Navigate("Roles");
			Thread.sleep(1000);
			admin.action().SearchRole(RoleName);
			Thread.sleep(1000);
			admin.action().DeleteRole(RoleName);
			Thread.sleep(1000);
			
			General.action().waitforelemenetpresent(RoleLocators.Locator().DeleteRole_assigned_to_user_message());
			assertion=Verify.action().verifyElementPresent(RoleLocators.Locator().DeleteRole_assigned_to_user_message(), 5);
			if(assertion==false)
				report("f","Assertion failed while verifying message for DeleteRole_assigned_to_user_message");
			
			}
	
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
	}
		
	
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(RoleLocators.Locator().SearchedRole_data(RoleName), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying searching created Role");
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



