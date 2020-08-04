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

public class Sub_54739 {


Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	 String WorkflowName []= {"One_Step_Workflow"};
	 String OrganizationName_without_User = "Organization_54739"+CommonElements.BROWSER+"M1";
	 String OrganizationName_with_User = "Organization_54739"+CommonElements.BROWSER+"M1";
	 String description = "Description for Organization 54739"+CommonElements.BROWSER+"M1";
	 String ParentOrganizationName = "TransPerfect";
	 String User[] = {"Maya"};

	 String menu="Clients";
	 
	 
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_54739");
	dataSet.put("TL_test_case_description","SUB-54739:Delete Organization");
	dataSet.put("TL_internal_testCase_ID", "54739");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				
				//Organization created without any user
				admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
				admin.action().Navigate(menu);
				admin.action().CreateOrganization_Step1(OrganizationName_without_User, description);
				admin.action().CreateOrganization_AddParentOrg(ParentOrganizationName);
				admin.action().CreateOrganization_AddWorkflow(WorkflowName);
			    //Unable to Delete User if we add users to it.	
				//admin.action().CreateOrganization_AddUser(User);
				admin.action().CreateOrganization_last();
				Thread.sleep(2000);
				admin.action().SearchOrganization(OrganizationName_without_User);
				Thread.sleep(2000);
				admin.action().DeleteOrganization(OrganizationName_without_User);
				Thread.sleep(2000);
				admin.action().SearchOrganization(OrganizationName_without_User);
				Thread.sleep(2000);
				assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_without_User), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Organization Name after Search	");
				}
				Thread.sleep(2000);
				//Organization created with user
				admin.action().Navigate(menu);
				admin.action().CreateOrganization_Step1(OrganizationName_with_User, description);
				admin.action().CreateOrganization_AddParentOrg(ParentOrganizationName);
				admin.action().CreateOrganization_AddWorkflow(WorkflowName);
				admin.action().CreateOrganization_AddUser(User);
				admin.action().CreateOrganization_last();
				Thread.sleep(2000);
				admin.action().SearchOrganization(OrganizationName_with_User);
				Thread.sleep(3000);
				admin.action().DeleteOrganization(OrganizationName_with_User);
				
				}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
		}
	public void assertion() throws Exception {

		assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().DeleteOrganization_withUser_message(), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying message for Organization having user after Search	");
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
