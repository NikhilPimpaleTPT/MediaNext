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
import locators.GroupLocators;
import locators.OrganizationLocators;
import modules.Verify;
import modules.admin;

public class Sub_54750 {
Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	 String GroupName = "Group_54750"+CommonElements.BROWSER+"M1";
	 String description = "Description_for_Organization_54750"+CommonElements.BROWSER+"M1";
	 String OrganizationName = "TransPerfect";
	 String User[] = {"Admin","Linguist","PM"};
	 String menu="Groups";
	
	@BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_54750");
	dataSet.put("TL_test_case_description","SUB-54750:Create Group");
	dataSet.put("TL_internal_testCase_ID", "54750");
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
				admin.action().CreateGroup_Step1(GroupName, description);
				admin.action().CreateGroup_AddOrg(OrganizationName);
				
				General.action().Click(GroupLocators.Locator().Group_AddUser_input);
				Thread.sleep(2000);
				//Verify sorting for
				//admin
				assertion=Verify.action().verifyElementPresent(GroupLocators.Locator().Group_SelectUserFrom_dropdown_user(1, User[0]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying GroupName Created message");
				}
				//Linguist
				assertion=Verify.action().verifyElementPresent(GroupLocators.Locator().Group_SelectUserFrom_dropdown_user(2, User[1]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying GroupName Created message");
				}
				//PM
				assertion=Verify.action().verifyElementPresent(GroupLocators.Locator().Group_SelectUserFrom_dropdown_user(3, User[2]), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying GroupName Created message");
				}

				admin.action().CreateGroup_AddUser(User);
                admin.action().CreateGroup_last();
				
				assertion=Verify.action().verifyElementPresent(GroupLocators.Locator().GroupCreated_message(GroupName), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying GroupName Created message");
				}
				
				admin.action().SearchGroup(GroupName);
				
				assertion=Verify.action().verifyElementPresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying GroupName  after Search	");
				
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			
		
	}
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(GroupName), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying GroupName after Search	");
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
