package admin.organization;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.OrganizationLocators;
import modules.Verify;
import modules.admin;

public class Sub_776195 {
	
	

Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String OrganizationName = "Organization_776195"+CommonElements.BROWSER+"A1";
	String description = "Description for Organization "+CommonElements.BROWSER+"A1";
	String ParentOrganizationName = "TransPerfect";
    String WorkflowName []= {"One_Step_Workflow"};
	String User[] = {"admin","Maya"};
    String menu="Clients";
    

	 
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_776195");
	dataSet.put("TL_test_case_description","SUB-776195:Edit Organization");
	dataSet.put("TL_internal_testCase_ID", "776195");
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
				admin.action().CreateOrganization_Step1(OrganizationName, description);
				admin.action().CreateOrganization_AddParentOrg(ParentOrganizationName);
				admin.action().CreateOrganization_AddWorkflow(WorkflowName);
				admin.action().CreateOrganization_AddUser(User);
				admin.action().CreateOrganization_last();
				Thread.sleep(3000);
				
				admin.action().SearchOrganization(OrganizationName);
				Thread.sleep(2000);
				
				admin.action().EditOrganization_open(OrganizationName);
				Thread.sleep(3000);
				
				General.action().waitforelemenetpresent(OrganizationLocators.Locator().Organization_EditOrg_RemoveAddedUser_CancelIcon(CommonElements.action().PM_username_second_manual));
				Thread.sleep(2000);
				General.action().Click(OrganizationLocators.Locator().Organization_EditOrg_RemoveAddedUser_CancelIcon(CommonElements.action().PM_username_second_manual));
				Thread.sleep(2000);
				General.action().waitforelemenetpresent(OrganizationLocators.Locator().Organization_EditOrg_RemoveAddedUser_CancelIcon(CommonElements.action().admin_username));
				Thread.sleep(2000);
				General.action().Click(OrganizationLocators.Locator().Organization_EditOrg_RemoveAddedUser_CancelIcon(CommonElements.action().admin_username));
				Thread.sleep(3000);
				General.action().Click(OrganizationLocators.Locator().OrganziationName_input);
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(OrganizationLocators.Locator().Organization_CancelUsers);
				Thread.sleep(2000);
				General.action().Click(OrganizationLocators.Locator().Organization_CancelUsers);
				Thread.sleep(4000);
				admin.action().Navigate(menu);
				Thread.sleep(3000);
				admin.action().SearchOrganization(OrganizationName);
				Thread.sleep(3000);
				
				admin.action().EditOrganization_open(OrganizationName);
				Thread.sleep(3000);

			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			
			}
	
	
	



	public void assertion() throws Exception {
		
		assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Organization_EditOrg_UserName(CommonElements.action().PM_username_second_manual), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying User1	");
		}
		
		assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Organization_EditOrg_UserName(CommonElements.action().admin_username), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying User2	");
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
