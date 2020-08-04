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

public class Sub_54744 {

Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	 String Roles_permission_Manage[] = {"Manage Roles","Manage Users","Manage Groups","Manage Workflows","Manage Clients","Manage Server"};
	 String Roles_permission_Assigne[] = {"Assign Steps"};
	 String Roles_permission_Edit[] = {"Edit Subtitles"};
	 String Roles_permission_View[] = {"View All Submissions"};
	 String RoleName = "Role_54744"+CommonElements.BROWSER+"P3";
	 String Description = "Role for 54744"+CommonElements.BROWSER+"P3";
	 
	
@BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_54744");
	dataSet.put("TL_test_case_description","SUB-54744:Delete Role");
	dataSet.put("TL_internal_testCase_ID", "54744");
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
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(RoleLocators.Locator().Rolecreated_message(RoleName));
				assertion=Verify.action().verifyElementPresent(RoleLocators.Locator().Rolecreated_message(RoleName), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for Role created");
				Thread.sleep(1000);
				admin.action().SearchRole(RoleName);
				Thread.sleep(1000);
				admin.action().DeleteRole(RoleName);
				Thread.sleep(3000);
				assertion=Verify.action().verifyElementPresent(RoleLocators.Locator().DeleteRole_message, 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for Role Delete");
				admin.action().SearchRole(RoleName);
				
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			
		
	}
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementNotPresent(RoleLocators.Locator().SearchedRole_data(RoleName), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Bad Credentials Message");
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
