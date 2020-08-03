package Smoke;
import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import locators.CommonLocartors;
import locators.LoginLocators;
import modules.Verify;
import modules.admin;

public class Sub_54746 {
Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	 String adminuser = "Maya";
	 String password = "Password1!";
	 
	
@BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_54746");
	dataSet.put("TL_test_case_description","SUB-54746:Login User");
	dataSet.put("TL_internal_testCase_ID", "54746");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
			
				admin.action().login(General.action().Adminusername,General.action().Adminpassword);
			General.action().waitforelemenetpresent(CommonLocartors.Locator().GlobalLink_logo);
			assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().GlobalLink_logo, 5);
			if(assertion==false) {
				report("f","Assertion failed while verifying GlobalLink_logo");
			}
			Thread.sleep(1000);
			General.action().waitforelemenetpresent(LoginLocators.Locator().Logout_button);
			General.action().Click(LoginLocators.Locator().Logout_button);
			Thread.sleep(1000);
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
				
		
	}
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(LoginLocators.Locator().SignOut_link, 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Sign out Link");
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
