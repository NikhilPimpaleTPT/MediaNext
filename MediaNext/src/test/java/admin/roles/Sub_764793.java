package admin.roles;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.RoleLocators;
import modules.Verify;
import modules.admin;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_764793 {
	
Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 //for role
	 String Roles_permission_Edit[] = { "Edit Subtitles" };
     String RoleName = "Role_764793"+CommonElements.BROWSER+"M1";
	 String Description = "Role for 764793"+CommonElements.BROWSER+"M1";
	 
	    @BeforeMethod	
		public void setup() throws Exception{
		General.action().startSystem("SUB_764793");
		dataSet.put("TL_test_case_description","SUB-764793:Create a duplicate role");
		dataSet.put("TL_internal_testCase_ID", "764793");
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
				
				//Duplicate role
				Thread.sleep(3000);
			    admin.action().Navigate("Roles");
				admin.action().CreateRole_Step1(RoleName, Description);
				admin.action().Create_AND_EditRole_Permission_manage(Roles_permission_Edit,true);
				admin.action().Create_And_SaveRole_CreateStep();
				General.action().waitforelemenetpresent(RoleLocators.Locator().RoleAlreadyExist_message());
				assertion=Verify.action().verifyElementPresent(RoleLocators.Locator().RoleAlreadyExist_message(), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for Role already exist");
				}
				
		
				catch(Throwable e){
					   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
					  }
		}
		
		
		public void assertion() throws Exception {
			assertion=Verify.action().verifyElementPresent(RoleLocators.Locator().RoleAlreadyExist_message(), 5);
			if(assertion==false)
				report("f","Assertion failed while verifying message for Role already exist");
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






