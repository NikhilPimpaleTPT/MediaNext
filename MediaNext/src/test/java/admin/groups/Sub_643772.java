package admin.groups;

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

public class Sub_643772 {
	
	
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	 
	 String GroupName = "Group_643772"+CommonElements.BROWSER+"A1";
	 String description = "Description for Group 643772"+CommonElements.BROWSER+"A1";
	 String OrganizationName = "Subtitle_Common_orgs";
	 String OrganizationName1 = "TransPerfect";
	 String menu="Groups";
	
	
	@BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_643772");
	dataSet.put("TL_test_case_description","SUB-643772:While creating group, user drop down list should be as per selected organization.");
	dataSet.put("TL_internal_testCase_ID", "643772");
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
				General.action().waitforelemenetpresent(OrganizationLocators.Locator().Organization_User_SearchUser);
				Thread.sleep(2000);
				General.action().Click(OrganizationLocators.Locator().Organization_User_SearchUser);
				Thread.sleep(2000);
				
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().PM_username_second_manual), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying User list for Subtitle_Common_orgs");
				}
				
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().PM_username), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying User list for Subtitle_Common_orgs");
				}


				Thread.sleep(6000);
				admin.action().CreateGroup_AddOrg(OrganizationName1);
				Thread.sleep(2000);
				General.action().waitforelemenetpresent(OrganizationLocators.Locator().Organization_User_SearchUser);
				Thread.sleep(2000);
				General.action().Click(OrganizationLocators.Locator().Organization_User_SearchUser);
				Thread.sleep(2000);
				
				assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().PM_username_second_manual), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying User list for Subtitle_Common_orgs");
				}
				
				assertion=Verify.action().verifyElementNotPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().PM_username), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying User list for Subtitle_Common_orgs");
				}
				
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().PM_username_second_group), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying User list for Subtitle_Common_orgs");
				}
				
				
				
				
				}		
			
			          catch(Throwable e){
					   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
					  }
				
			
		}
		public void assertion() throws Exception {
			assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Organization_UserOptions(CommonElements.action().PM_username_second_org), 5);
			if(assertion==false){
				report("f","Assertion failed while verifying User list for TransPerfect");
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

