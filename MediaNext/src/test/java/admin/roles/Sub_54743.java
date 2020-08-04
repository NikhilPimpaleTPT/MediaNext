package admin.roles;
import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.RoleLocators;
import modules.Verify;
import modules.admin;

public class Sub_54743 {

Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	 String Roles_permission_Manage[] = { "Manage Roles","Manage Users","Manage Groups","Manage Workflows","Manage Clients","Manage Server" };
	 String Roles_permission_Manage1[] = { "Manage Roles"};
	 String Roles_permission_Assigne[] = {"Assign Steps"};
	 String Roles_permission_Edit[] = { "Edit Subtitles" };
	 String Roles_permission_View[] = { "View All Submissions"};
	 String RoleName = "Role_54743"+CommonElements.BROWSER+"U1";
	 String Description = "Role for 54743"+CommonElements.BROWSER+"U1";
	 String UpdatedRoleName = "Role_54743_updated"+CommonElements.BROWSER+"U1";
	 String UpdatedDescription = "Role for 54743 updated"+CommonElements.BROWSER+"U1";
	 
	
@BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_54743");
	dataSet.put("TL_test_case_description","SUB-54743:Edit Role");
	dataSet.put("TL_internal_testCase_ID", "54743");
	
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
				admin.action().Create_AND_EditRole_Permission_manage(Roles_permission_Manage,true);
				admin.action().Create_AND_EditRole_Permission_Assign(Roles_permission_Assigne,true);
				admin.action().Create_AND_EditRole_Permission_Edit(Roles_permission_Edit,true);
				admin.action().Create_AND_EditRole_Permission_View(Roles_permission_View,true);
				admin.action().Create_And_SaveRole_CreateStep();
				admin.action().SearchRole(RoleName);
				Thread.sleep(1000);
				System.out.println("DONE SEARCH");
				General.action().waitforelemenetpresent(RoleLocators.Locator().SearchedRole_data(RoleName));
				assertion=Verify.action().verifyElementPresent(RoleLocators.Locator().SearchedRole_data(RoleName), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for Role created");
			
				admin.action().EditRole_open(RoleName);
				admin.action().EditRole_Step1(UpdatedRoleName, UpdatedDescription);
				admin.action().Create_AND_EditRole_Permission_manage(Roles_permission_Manage1,false);
				admin.action().Create_And_SaveRole_CreateStep();
				admin.action().SearchRole(UpdatedRoleName);
				assertion=Verify.action().verifyElementPresent(RoleLocators.Locator().SearchedRole_data(UpdatedRoleName), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying update Role Name");
				
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			
		
	}
	
	
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(RoleLocators.Locator().SearchedRole_data(UpdatedDescription), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying updated Role Description");
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
