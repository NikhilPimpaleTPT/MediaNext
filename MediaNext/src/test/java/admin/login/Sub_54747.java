package admin.login;
import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.LoginLocators;
import modules.Verify;
import modules.admin;

public class Sub_54747 {

Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 
	
@BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_54747");
	dataSet.put("TL_test_case_description","SUB-54747:Logout User");
	dataSet.put("TL_internal_testCase_ID", "54747");
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
			
			General.action().waitforelemenetpresent(CommonLocartors.Locator().GlobalLink_logo);
			assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().GlobalLink_logo, 5);
			if(assertion==false)
				report("f","Assertion failed while verifying Help Link");
			System.out.println("DEMO TEST");
			General.action().logoutMethod();
			Thread.sleep(3000);
			assertion=Verify.action().verifyElementPresent(LoginLocators.Locator().Email_input, 5);
			if(assertion==false)
				report("f","Assertion failed while verifying userinput on login page");
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			
		
	}
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(LoginLocators.Locator().password_input, 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Password input button");
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
