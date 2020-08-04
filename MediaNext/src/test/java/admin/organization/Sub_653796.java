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

public class Sub_653796 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 String OrganizationName = "Organization_653796"+CommonElements.BROWSER+"S1";
	 String WorkflowName []= {"One_Step_Workflow"};
	 String description = "Description for Organization 653796"+CommonElements.BROWSER+"S1";
	 String ParentOrganizationName = "TransPerfect";
	 String User[] = {"admin"};
	 String menu="Clients";
	  
	 @BeforeMethod	
	public void setup() throws Exception{
		 General.action().startSystem("Sub_653796");
		 dataSet.put("TL_test_case_description","SUB-653796:Verify that the same organization which is added, cannot be selected as Parent organization for self.");
		 dataSet.put("TL_internal_testCase_ID", "653796");
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
				
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				Thread.sleep(2000);
				admin.action().EditOrganization_open(OrganizationName);
				Thread.sleep(2000);
				
				}catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			}
		
	}
	

	public void assertion() throws Exception {
		General.action().waitforelemenetpresent(OrganizationLocators.Locator().selectParentOrganziation_dropdown_Button);
		Thread.sleep(4000);
		General.action().Click(OrganizationLocators.Locator().selectParentOrganziation_dropdown_Button);
		Thread.sleep(2000);
		assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().SelectOrganization_AsParentOrg(OrganizationName),5);
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