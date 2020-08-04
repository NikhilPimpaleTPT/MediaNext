package subtitler.project_manager.Additional_Testcases;

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

public class Sub_764757 {
	

Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	

     String WorkflowName []= {"One_Step_Workflow","Two_Step_Workflow"};
     String description = "Description for White Space Organization";
	 String ParentOrganizationName = "TransPerfect";
	 String User[] = {"admin","Maya"};
	 String menu="Clients";
     String Workflow[]={"One_Step_Workflow"};
	 
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_764757");
	dataSet.put("TL_test_case_description","SUB-764757:Creating Organization with name only whitespaces");
	dataSet.put("TL_internal_testCase_ID", "764757");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
				admin.action().Navigate(menu);
				admin.action().CreateOrganization_Step1("                       ", description);
				admin.action().CreateOrganization_AddParentOrg(ParentOrganizationName);
				admin.action().CreateOrganization_AddWorkflow(WorkflowName);
				
				admin.action().CreateOrganization_AddUser(User);
				CreateOrganization_last();
			
				General.action().waitforelemenetpresent(OrganizationLocators.Locator().InvalidOrganization_message());
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().InvalidOrganization_message(), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for User Created Succesfully");
				
				}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			}
	
	
	public void CreateOrganization_last() throws Exception {
		
		System.out.println("INSIDE METHOD CreateOrganization_last");
		
	if(General.action().VerifyElementPresent(OrganizationLocators.Locator().Create_Organization_button))	{
		General.action().waitforelemenetpresent(OrganizationLocators.Locator().Create_Organization_button);
		Thread.sleep(1000);
		General.action().Click(OrganizationLocators.Locator().Create_Organization_button);
		Thread.sleep(4000);
	}
	//Thread.sleep(2000);
	System.out.println("EOM CreateOrganization_last()");
	}
	
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().InvalidOrganization_message(), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Invalid Organization after search");
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



