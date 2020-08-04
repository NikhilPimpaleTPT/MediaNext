package admin.groups;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.GroupLocators;
import modules.Verify;
import modules.admin;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_202235 {
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	 
	 String GroupName = "Group_202235"+CommonElements.BROWSER+"W2";
	 String description = "Description for Group 202235"+CommonElements.BROWSER+"W2";
	 String GroupName_updated = "Group_202235"+CommonElements.BROWSER+"W2_update";
	 String description_updated = "Description for Organization 202235"+CommonElements.BROWSER+"W2_update";
	 
	 String OrganizationName = "TransPerfect";
	 String User[] = {"admin"};
	 String Username[] = {"admin","linguist"};
	 String menu="Groups";

	
	
	@BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_202235");
	dataSet.put("TL_test_case_description","SUB-202235:Edit Group");
	dataSet.put("TL_internal_testCase_ID", "202235");
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
				admin.action().CreateGroup_Step1(GroupName, description);
				Thread.sleep(1000);
				admin.action().CreateGroup_AddOrg(OrganizationName);
				Thread.sleep(1000);
				admin.action().CreateGroup_AddUser(User);
				Thread.sleep(1000);
				admin.action().CreateGroup_last();
				Thread.sleep(2000);
				admin.action().SearchGroup(GroupName);
				Thread.sleep(1000);
				admin.action().EditGroup_open(GroupName);
				Thread.sleep(1000);
				admin.action().EditGroup_Step1(GroupName_updated, description_updated);
				Thread.sleep(1000);
				admin.action().EditGroup_User(Username);
				Thread.sleep(1000);
				admin.action().CreateGroup_last();
				Thread.sleep(2000);
				admin.action().SearchGroup(GroupName_updated);
				Thread.sleep(1000);
				
				
				assertion=Verify.action().verifyElementPresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName_updated), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying Group Name after Search");
				
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			
		
	}
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName_updated), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Group Descrition after update");
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
