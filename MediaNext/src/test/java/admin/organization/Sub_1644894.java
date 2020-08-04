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
import locators.Pm_User_Submission_Locators;
import modules.Verify;
import modules.admin;

/**                                                                                                                                     
 * @author pvaidya                                                   
 *Summary: This test case verifies that user list in create and edit organization.
 */ 

public class Sub_1644894 {

	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
     String WorkflowName []= {"One_Step_Workflow","Two_Step_Workflow"};
	 String OrganizationName = "Organization_1644894"+CommonElements.BROWSER+"A2";
	 String description = "Description for Organization 1644894"+CommonElements.BROWSER+"A2";
	 String ParentOrganizationName = "TransPerfect";
	 String User[] = {"admin","Maya"};
     String listOfUsersInDropDown[]= {"Admin","Customer","Linguist","PM","Maya","Subtitler_PM","Subtitler_Translator_1","Subtitler_Reviewer_1"};
     String listOfUsersAdded[]= {"admin@server.com","customer@server.com","linguist@server.com","pmanager@server.com","mayagurnale06@gmail.com","prashant.dongare80@gmail.com"," subtitlertrans@gmail.com ","subtitlerreviewer@gmail.com"};
     
     String menu="Clients";
     String Workflow[]={"One_Step_Workflow"};

	 String NewUser[]={"admin"};
	 
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_1644894");
	dataSet.put("TL_test_case_description","SUB-1644894:User list in create/edit organization");
	dataSet.put("TL_internal_testCase_ID", "1644894");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
				Thread.sleep(3000);
				admin.action().Navigate(menu);
				admin.action().CreateOrganization_Step1(OrganizationName, description);
				admin.action().CreateOrganization_AddParentOrg(ParentOrganizationName);
				admin.action().CreateOrganization_AddWorkflow(WorkflowName);
				
				General.action().Click(OrganizationLocators.Locator().AddUser_input);
		        Thread.sleep(5000);
				
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[0]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[1]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[2]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[3]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[4]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[5]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[6]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[7]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				
				
				admin.action().CreateOrganization_AddUser(listOfUsersInDropDown);
			
				assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[0]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[1]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[2]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[3]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[4]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[5]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[6]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[7]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				
				General.action().Click(OrganizationLocators.Locator().Organization_RemoveAddedUser_Cancel(listOfUsersAdded[0]));
		        Thread.sleep(1000);
		    	General.action().Click(OrganizationLocators.Locator().Organization_RemoveAddedUser_Cancel(listOfUsersAdded[1]));
		        Thread.sleep(1000);
		        General.action().Click(OrganizationLocators.Locator().Organization_RemoveAddedUser_Cancel(listOfUsersAdded[2]));
		        Thread.sleep(1000);
		        General.action().Click(OrganizationLocators.Locator().Organization_RemoveAddedUser_Cancel(listOfUsersAdded[3]));
		        Thread.sleep(1000);
		        General.action().Click(OrganizationLocators.Locator().Organization_RemoveAddedUser_Cancel(listOfUsersAdded[4]));
		        Thread.sleep(1000);
		        General.action().Click(OrganizationLocators.Locator().Organization_RemoveAddedUser_Cancel(listOfUsersAdded[5]));
		        Thread.sleep(1000);
		        General.action().Click(OrganizationLocators.Locator().Organization_RemoveAddedUser_Cancel(listOfUsersAdded[6]));
		        Thread.sleep(1000);
		        General.action().Click(OrganizationLocators.Locator().Organization_RemoveAddedUser_Cancel(listOfUsersAdded[7]));
		        Thread.sleep(1000);
		        
		        assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[0]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[1]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[2]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[3]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[4]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[5]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[6]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				
				
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			
		
	}
	
	public void verifyUserList() {
		
	}
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdown(listOfUsersInDropDown[7]), 5);
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


