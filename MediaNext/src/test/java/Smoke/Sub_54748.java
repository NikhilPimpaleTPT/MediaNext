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
import locators.WorkflowLocators;
import modules.Verify;
import modules.admin;

public class Sub_54748 {

Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 String WorkflowName = "Workflow_54748"+CommonElements.BROWSER+"M1";
	 String OrganizationName = "TransPerfect";
	 String StepName = "trans";
	 String StepFromDropdown = "Translation";
	 String StepName1 = "review";
	 String StepFromDropdown1 = "Review";
	 String menu="Workflows";
	
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_54748");
	dataSet.put("TL_test_case_description","SUB-54748:Create Workflow");
	dataSet.put("TL_internal_testCase_ID", "54748");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				admin.action().login(General.action().Adminusername,General.action().Adminpassword);
				admin.action().Navigate(menu);
				
				admin.action().CreateWorkflow_Step1(WorkflowName);
				admin.action().CreateWorkflow_AddOrganization(OrganizationName);
				admin.action().CreateWorkflow_AddSteps(1,StepName, StepFromDropdown);
				admin.action().CreateWorkflow_AddSteps_multi(2,StepName1, StepFromDropdown1);
				CreateWorkflow();
				
				assertion=Verify.action().verifyElementPresent(WorkflowLocators.Locator().WorkflowCreated_message(WorkflowName), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Created message");
				}
				
				admin.action().SearchWorkflow(WorkflowName);
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }

	}
	
	public void CreateWorkflow() throws Exception {
		
		System.out.println("INSIDE METHOD CreateWorkflow");
		
		Verify.action().waitforelemenetpresent(WorkflowLocators.Locator().AddWorkflow_Save_button);
		Verify.action().Click(WorkflowLocators.Locator().AddWorkflow_Save_button);
		Thread.sleep(1000);
		
		System.out.println("EOM CreateWorkflow()");
	}	
	
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName), 5);
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
