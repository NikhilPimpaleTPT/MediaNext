package admin.workflow;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.TranslatorLocators;
import locators.WorkflowLocators;
import modules.Verify;
import modules.admin;

public class Sub_202241 {

Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 String WorkflowName = "Workflow_202241"+CommonElements.BROWSER+"Q2";

	 String OrganizationName = "TransPerfect";
	 String StepName = "trans";
	 String StepFromDropdown = "Translation";
	 String StepName1 = "review";
	 String StepFromDropdown1 = "Review";
	 String menu="Workflows";
	 String LongCharWorkflowName_updated="Workflow_202241"+CommonElements.BROWSER+"UpdatedWorkflowNameUpdatedWorkflowNameUpdatedWorkflowNameUpdatedWorkflowNameUpdatedWorkflowNameUpdatedWorkflowName_Q1";
	 String WorkflowName_updated = "Workflow_202241"+CommonElements.BROWSER+"UpdatedWorkflowName_Q1";
	 
	
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_202241");
	dataSet.put("TL_test_case_description","SUB-202241:Create Workflow");
	dataSet.put("TL_internal_testCase_ID", "202241");
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
				admin.action().CreateWorkflow_Step1(WorkflowName);
				admin.action().CreateWorkflow_AddOrganization(OrganizationName);
				admin.action().CreateWorkflow_AddSteps(1,StepName, StepFromDropdown);
				admin.action().CreateWorkflow_AddSteps_multi(2,StepName1, StepFromDropdown1);
				admin.action().CreateWorkflow();
				
				admin.action().SearchWorkflow(WorkflowName);
				admin.action().EditWorkflow_open(WorkflowName);
				//To Verify Limit of workflow name as Max 128
				admin.action().EditWorkflow_Step1(LongCharWorkflowName_updated.trim());
				Thread.sleep(2000);
				General.action().Click(WorkflowLocators.Locator().Workflow_AddOrganization_input);
				Thread.sleep(2000);
				
				assertion=Verify.action().verifyElementPresent(WorkflowLocators.Locator().EditWokflowName_ErrorMessege, 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name Limit Error Massege	");
				}
				Thread.sleep(2000);
				admin.action().EditWorkflow_Step1(WorkflowName_updated.trim());
				admin.action().CreateWorkflow();
				Thread.sleep(2000);
				admin.action().SearchWorkflow(WorkflowName_updated);
				Thread.sleep(2000);
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			
		
	}
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_updated), 5);
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
