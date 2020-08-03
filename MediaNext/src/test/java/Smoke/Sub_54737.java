package Smoke;
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

public class Sub_54737 {


Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 String WorkflowName [] = {"One_Step_Workflow"};
	 String OrganizationName = "Organization_54737"+CommonElements.BROWSER+"M7";
	 String description = "Description for "+OrganizationName;
	 String ParentOrganizationName = "TransPerfect";
	 String User[] = {"Subtitler_PM","Subtitler_Translator_1"};
	 String menu="Clients";
	
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_54737");
	dataSet.put("TL_test_case_description","SUB-54737:Create Organization");
	dataSet.put("TL_internal_testCase_ID", "54737");	
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				admin.action().login(General.action().Adminusername,General.action().Adminpassword);
				Thread.sleep(20000);
				admin.action().Navigate(menu);
				admin.action().CreateOrganization_Step1(OrganizationName, description);
				admin.action().CreateOrganization_AddParentOrg(ParentOrganizationName);
				admin.action().CreateOrganization_AddWorkflow(WorkflowName);
				admin.action().CreateOrganization_AddUser(User);
				CreateOrganization_last();
				
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().OrganizationCreated_message(OrganizationName), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Organization Created message");
				}
				
				admin.action().SearchOrganization(OrganizationName);
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
	}
	
	public void CreateOrganization_last() throws Exception {
		
		System.out.println("INSIDE METHOD CreateOrganization_last");
		
	if(Verify.action().VerifyElementPresent(OrganizationLocators.Locator().Create_Organization_button))	{
		General.action().waitforelemenetpresent(OrganizationLocators.Locator().Create_Organization_button);
		Thread.sleep(1000);
		General.action().Click(OrganizationLocators.Locator().Create_Organization_button);
		Thread.sleep(1000);
	}
		
	
	System.out.println("EOM CreateOrganization_last()");
	}
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Workflow Name after Search	");
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
