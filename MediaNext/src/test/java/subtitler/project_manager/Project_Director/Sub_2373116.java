package subtitler.project_manager.Project_Director;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.PD_Pm_User_Submission_Locators;
import modules.Verify;
import modules.admin;

/**
 * 
 * @author pvaidya
 *Summary:This test case verifies that one can add/edit or remove PD instances in MediaNext
 *
 */

public class Sub_2373116 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	String menu = "Settings";
	String name="Test qa-tdc15";
	String url="https://qa-tdc15.translations.com/PD";
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_2373116");
		dataSet.put("TL_test_case_description", "Provide a way to manage PD instances");
		dataSet.put("TL_internal_testCase_ID", "2373116");
	}
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {

			admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			admin.action().Navigate(menu);
			
			
			assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().selectMenu_settings_header, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Project Director header");
			}
			
			General.action().waitforelemenetpresent(CommonLocartors.Locator().setting_newInstanceButton);
	    	Thread.sleep(2000);
	    	General.action().Click(CommonLocartors.Locator().setting_newInstanceButton);
	    	Thread.sleep(2000);
			
	    	Thread.sleep(2000);
			General.action().Click(CommonLocartors.Locator().setting_newInstance_name);
	    	Thread.sleep(2000);
			General.action().ClearInputvalues(CommonLocartors.Locator().setting_newInstance_name);
			Thread.sleep(2000);
			General.action().type(CommonLocartors.Locator().setting_newInstance_name,name);
			Thread.sleep(2000);
			
			
			Thread.sleep(2000);
			General.action().Click(CommonLocartors.Locator().setting_newInstance_url);
	    	Thread.sleep(2000);
			General.action().ClearInputvalues(CommonLocartors.Locator().setting_newInstance_url);
			Thread.sleep(2000);
			General.action().type(CommonLocartors.Locator().setting_newInstance_url,url);
			Thread.sleep(2000);
			
			Thread.sleep(2000);
			General.action().Click(CommonLocartors.Locator().setting_newInstance_cancel);
	    	Thread.sleep(2000);
			
	    	assertion = Verify.action().verifyElementNotPresent(CommonLocartors.Locator().setting_newInstance_name, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Add Instance should be close");
			}
			
			assertion = Verify.action().verifyElementNotPresent(CommonLocartors.Locator().setting_newInstance_url, 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Add Instance should be close");
			}
			
			General.action().waitforelemenetpresent(CommonLocartors.Locator().setting_newInstanceButton);
	    	Thread.sleep(2000);
	    	General.action().Click(CommonLocartors.Locator().setting_newInstanceButton);
	    	Thread.sleep(2000);
	    	
	    	General.action().waitforelemenetpresent(CommonLocartors.Locator().setting_newInstance_name);
	    	Thread.sleep(2000);
			General.action().Click(CommonLocartors.Locator().setting_newInstance_name);
	    	Thread.sleep(2000);
			General.action().ClearInputvalues(CommonLocartors.Locator().setting_newInstance_name);
			Thread.sleep(2000);
			General.action().type(CommonLocartors.Locator().setting_newInstance_name,name);
			Thread.sleep(2000);
			
			General.action().waitforelemenetpresent(CommonLocartors.Locator().setting_newInstance_url);
	    	Thread.sleep(2000);
			Thread.sleep(2000);
			General.action().Click(CommonLocartors.Locator().setting_newInstance_url);
	    	Thread.sleep(2000);
			General.action().ClearInputvalues(CommonLocartors.Locator().setting_newInstance_url);
			Thread.sleep(2000);
			General.action().type(CommonLocartors.Locator().setting_newInstance_url,url);
			Thread.sleep(2000);
			
			General.action().waitforelemenetpresent(CommonLocartors.Locator().setting_newInstance_add);
	    	Thread.sleep(2000);
			Thread.sleep(2000);
			General.action().Click(CommonLocartors.Locator().setting_newInstance_add);
	    	Thread.sleep(4000);
	    	
	    	//This assertion to verify last step to check added instance is present in the list
	    	assertion = Verify.action().verifyElementPresent(CommonLocartors.Locator().setting_newInstance_addedInstance(name,url), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Added Instance");
			}
			
			//We are removing this instance so that in application there will be no duplicate instances present (Not in steps but need to remove added instance)
			General.action().waitforelemenetpresent(CommonLocartors.Locator().setting_newInstance_addedInstance_delete(name,url));
			Thread.sleep(2000);
			General.action().Click(CommonLocartors.Locator().setting_newInstance_addedInstance_delete(name,url));
	    	Thread.sleep(4000);
	    	General.action().waitforelemenetpresent(CommonLocartors.Locator().setting_newInstance_addedInstance_deleteButton);
			Thread.sleep(2000);
			General.action().Click(CommonLocartors.Locator().setting_newInstance_addedInstance_deleteButton);
	    	Thread.sleep(5000);
	    	
	    	assertion = Verify.action().verifyElementNotPresent(CommonLocartors.Locator().setting_newInstance_addedInstance(name,url), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Added Instance");
			}

		} catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
          }
	}
public void assertion() throws Exception {
	assertion = Verify.action().verifyElementNotPresent(CommonLocartors.Locator().setting_newInstance_addedInstance(name,url), 5);
	if (assertion == false) {
		report("f","Assertion failed while verifying Added Instance");
	}
	else {
		report("p", "All assertions passed");
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
