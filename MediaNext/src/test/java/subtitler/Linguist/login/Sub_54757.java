package subtitler.Linguist.login;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.CommonLocartors;
import locators.LoginLocators;
import modules.Verify;
import modules.admin;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_54757 {
	
Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String menu_Submission = "Submissions";
	String menu_completed = "Completed";
	String menu_AllJobs = "Jobs";
	String menu_ToClaim = "To Claim";
	String menu_Ongoing  = "Ongoing";
	
@BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_54757");
	dataSet.put("TL_test_case_description","SUB-54757:Linguist Logout");
	dataSet.put("TL_internal_testCase_ID", "54757");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
			
			admin.action().login(CommonElements.action().Translator_username,CommonElements.action().password);
			//TODO Not needed as per TC
//			General.action().waitforelemenetpresent(CommonLocartors.Locator().SelectMenu("Help"));
//			assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().SelectMenu("Help"), 5);
//			if(assertion==false)
//		    report("f","Assertion failed while verifying Help Link");
//			
//			General.action().logoutMethod();
//			Thread.sleep(3000);
//			assertion=Verify.action().verifyElementPresent(LoginLocators.Locator().password_input, 5);
//			if(assertion==false)
//				report("f","Assertion failed while verifying userinput on login page");
//			}
				
				
			assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().SelectMenu(menu_ToClaim), 5);
			if(assertion==false)
			   report("f","Assertion failed while verifying userinput on login page");
			
			assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().SelectMenu(menu_Ongoing), 5);
			if(assertion==false)
			   report("f","Assertion failed while verifying userinput on login page");
			assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().SelectMenu(menu_completed), 5);
			if(assertion==false)
			   report("f","Assertion failed while verifying userinput on login page");
				
		    
				
			General.action().waitforelemenetpresent(CommonLocartors.Locator().GlobalLink_logo);
			assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().GlobalLink_logo, 5);
			if(assertion==false)
			report("f","Assertion failed while verifying Help Link");
			Thread.sleep(2000);
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



