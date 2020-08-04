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

public class Sub_785817 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	 
	 String GroupName = "Group_785817"+CommonElements.BROWSER+"A2";
	 String description = "Description for Group 785817"+CommonElements.BROWSER+"A2";
	 String OrganizationName = "Subtitle_Common_orgs";
	 String OrganizationName1 = "Subtitle_Common_orgs_multi";
	 String User[] = {"Maya", "Subtitler_PM"};

	 String menu="Groups";
	
	
	@BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_785817");
	dataSet.put("TL_test_case_description","SUB-785817:Edit Group / Change Organization: assigned users are not in the list.");
	dataSet.put("TL_internal_testCase_ID", "785817");
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
				Thread.sleep(2000);
				admin.action().CreateGroup_AddUser(User);
				Thread.sleep(1000);
				admin.action().CreateGroup_last();
				Thread.sleep(2000);
				admin.action().SearchGroup(GroupName);
				Thread.sleep(1000);
				admin.action().EditGroup_open(GroupName);
				Thread.sleep(2000);
				admin.action().CreateGroup_AddOrg(OrganizationName1);
				Thread.sleep(2000);
				General.action().waitforelemenetpresent(OrganizationLocators.Locator().Organization_User_SearchUser);
				Thread.sleep(2000);
				General.action().Click(OrganizationLocators.Locator().Organization_User_SearchUser);
				Thread.sleep(2000);
				
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().PM_username), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Group Descrition after update");
				}
				
				
				}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }	
	}
				
			public void assertion() throws Exception {
					assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().PM_username_second_manual), 5);
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
