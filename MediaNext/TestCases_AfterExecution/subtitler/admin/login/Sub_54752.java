package subtitler.admin.login;

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

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: Forgot Password
 *
 */ 


//Imp Note : Bofore executing this test case need to clear all email form Glplayqmreview@gmail.com



public class Sub_54752 {
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String emailid = "Glplayqmreview@gmail.com";
	String emailPassword="Subtitlerautomation2!";//"Password1!";
	Boolean assertion = true;
	
	
	 @BeforeMethod	
		public void setup() throws Exception{
		General.action().startSystem("SUB_54752");
		dataSet.put("TL_test_case_description","SUB-54752:Forgot Password");
		dataSet.put("TL_internal_testCase_ID","54752");
		
	 }

	@Test
		public void execute() throws Exception {
			testCase(dataSet);
			assertion();
	}
		
		
		public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				
				Thread.sleep(5000);
				admin.action().Click(LoginLocators.Locator().forgotPassword_link);
				Thread.sleep(2000);
				admin.action().Click(LoginLocators.Locator().forgotPassword_emailId);
				Thread.sleep(2000);
				admin.action().type(LoginLocators.Locator().forgotPassword_emailId, emailid);
				Thread.sleep(2000);
				admin.action().Click(LoginLocators.Locator().forgotPassword_submitButton);
				Thread.sleep(2000);
					
				assertion=Verify.action().verifyElementPresent(LoginLocators.Locator().passwordreset_message, 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Please check your email to reset your password message");
				}
				
				
				logEmail_resetPasswordLink(emailid,emailPassword);
				
				admin.action().driver.navigate().refresh();
				
				Thread.sleep(2000);
				assertion=Verify.action().verifyElementPresent(LoginLocators.Locator().gmail_resetPassword_mail, 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Reset Password mail");
				}
				
				admin.action().Click(LoginLocators.Locator().gmail_resetPassword_mail);
				Thread.sleep(2000);
				
				
					
					
					
				}
				catch(Throwable e){
					   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
					  }
				}
		
	
		
		public void logEmail_resetPasswordLink(String emailid,String gmail_password ) throws InterruptedException {
			Thread.sleep(2000);
			General.action().driver.get("https://mail.google.com/");
			Thread.sleep(2000);
			admin.action().ClearInputvalues(LoginLocators.Locator().gmail_userName);
			Thread.sleep(1000);
			admin.action().type(LoginLocators.Locator().gmail_userName, emailid);
			Thread.sleep(2000);
			admin.action().Click(LoginLocators.Locator().gmail_nextButton);
			Thread.sleep(2000);
			admin.action().ClearInputvalues(LoginLocators.Locator().gmail_password);
			Thread.sleep(1000);
			admin.action().type(LoginLocators.Locator().gmail_password,gmail_password);
			Thread.sleep(2000);
			admin.action().Click(LoginLocators.Locator().gmail_nextButton);
			Thread.sleep(5000);
		
			
		}
		
		
		public void assertion() throws Exception {
			assertion=Verify.action().verifyElementPresent(LoginLocators.Locator().gmail_resetPassword_link, 5);
			if(assertion==false){
				report("f","Assertion failed while verifying Reset Password link");
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



