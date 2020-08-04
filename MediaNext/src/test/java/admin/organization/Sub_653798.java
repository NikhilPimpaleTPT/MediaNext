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

public class Sub_653798 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 String OrganizationName = "Organization_653798"+CommonElements.BROWSER+"B1";
	 String WorkflowName []= {"One_Step_Workflow"};
	 String description = "Description for Organization 653798"+CommonElements.BROWSER+"B1";
	 String ParentOrganizationName = "TransPerfect";
	 String User[] = {"admin"};
	 String menu="Clients";
	
	 
	 String GroupName = "Group_653798"+CommonElements.BROWSER+"B1";
	 String GroupNamedescription = "Description for Group 653798"+CommonElements.BROWSER+"B1";
	 String GroupOganizationName = "TransPerfect";
	 String GroupUser[] = {"admin"};
	 String Groupmenu="Groups";
	  
	 @BeforeMethod	
	public void setup() throws Exception{
		 General.action().startSystem("Sub_653798");
	dataSet.put("TL_test_case_description","SUB-653798:Organization / Group: Add User, the list should be filtered while typing and it should be shown in sorted order.");
	dataSet.put("TL_internal_testCase_ID", "653798");
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
			
			assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName), 10);
			if(assertion==false){
				report("f","Assertion failed while verifying Workflow Name after Search	");
			}
			Thread.sleep(2000);
			admin.action().EditOrganization_open(OrganizationName);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(OrganizationLocators.Locator().Organization_User_SearchUser);
			Thread.sleep(2000);
			General.action().Click(OrganizationLocators.Locator().Organization_User_SearchUser);
			Thread.sleep(2000);
			admin.action().type(OrganizationLocators.Locator().Organization_User_SearchUser,"p");
			Thread.sleep(1000);

            //TODO User is Change No Need to verify this user
//			assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().PM_username_second_manual), 10);
//			if(assertion==false){
//				report("f","Assertion failed while verifying User List	");
//			}
			
			assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().PM_username), 10);
			if(assertion==false){
				report("f","Assertion failed while verifying User List	");
			}
			assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().PM_username_second_org), 10);
			if(assertion==false){
				report("f","Assertion failed while verifying User List	");
			}
			
			assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().Translator_username), 5);
			if(assertion==false){
				report("f","Assertion failed while verifying User List	");
			}
			
			
			assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().Reviwer_username), 5);
			if(assertion==false){
				report("f","Assertion failed while verifying User List");
			}
			
			
			admin.action().Navigate(Groupmenu);
			admin.action().CreateGroup_Step1(GroupName, GroupNamedescription);
			Thread.sleep(2000);
			admin.action().CreateGroup_AddOrg(GroupOganizationName);
			Thread.sleep(2000);
			admin.action().CreateGroup_AddUser(GroupUser);
			Thread.sleep(2000);
			admin.action().CreateGroup_last();
			Thread.sleep(2000);
			admin.action().SearchGroup(GroupName);
			Thread.sleep(2000);
			admin.action().EditGroup_open(GroupName);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(OrganizationLocators.Locator().Organization_User_SearchUser);
			Thread.sleep(2000);
			General.action().Click(OrganizationLocators.Locator().Organization_User_SearchUser);
			Thread.sleep(2000);
			admin.action().type(OrganizationLocators.Locator().Organization_User_SearchUser,"p");
			Thread.sleep(2000);
			
			assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().PM_username_second_org), 5);
			if(assertion==false){
				report("f","Assertion failed while verifying User List	");
			}
			
			assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().PM_username_second_group), 5);
			if(assertion==false){
				report("f","Assertion failed while verifying User List	");
			}
	
	

		}
	catch(Throwable e){
		   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		  }

}
	
public void assertion() throws Exception {
		
	assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().admin_username), 5);
	if(assertion==false){
		report("f","Assertion failed while verifying User List	");
	}
		
		else{
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


