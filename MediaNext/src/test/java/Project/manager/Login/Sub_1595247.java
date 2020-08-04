package Project.manager.Login;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import modules.PM_user;
import modules.Verify;
import modules.admin;

/**                                                                                                                                  
 * @author pvaidya                                                   
 *Summary:This testcase verifies the Routing Redirection of GLPLay.
 *
 */ 

public class Sub_1595247 {
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String submissionURL="https://qa.subtitler.xyz/submissions";
	String currentURL_afterPmLogIn;
	String currentURL_afterEditingURL;
	Boolean assertion = true;
	
	
	 @BeforeMethod	
		public void setup() throws Exception{
		General.action().startSystem("SUB_1595247");
		dataSet.put("TL_test_case_description","SUB-1595247:Routing redirection");
		dataSet.put("TL_internal_testCase_ID","1595247");
		
	 }

	@Test
		public void execute() throws Exception {
			testCase(dataSet);
			assertion();
	}
		
		
		public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				
				//log In as PM
				PM_user.action().login(CommonElements.action().PM_username,CommonElements.action().password);
				Thread.sleep(8000);
				
				//Get Current URL after pm login 
				currentURL_afterPmLogIn=General.action().driver.getCurrentUrl();
				System.out.println("URL after login to PM is--->"+currentURL_afterPmLogIn);
				
				//Verify after pm login the Current URL is equal to (https://qa.subtitler.xyz/submissions)
				assertion=currentURL_afterPmLogIn.equals(submissionURL);
				if(assertion==false){
					report("f","Assertion failed while verifying URL");
				}
				
				//Edit Current URL (Repalce submissions as chocolate)
				Thread.sleep(2000);
				General.action().driver.get("https://qa.subtitler.xyz/chocolate");
				Thread.sleep(4000);
				
				//After editing get URL
				currentURL_afterEditingURL=General.action().driver.getCurrentUrl();
				System.out.println("after Editing URL,Current URL is--->"+currentURL_afterEditingURL);
				
				//Verify that current URL is same as URL after PM logIn
				assertion=currentURL_afterEditingURL.equals(currentURL_afterPmLogIn);
				if(assertion==false){
					report("f","Assertion failed while verifying URL");
				}
				
				
				
			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			}
	
	
	public void assertion() throws Exception {
		//Verify that current URL is same as URL after PM logIn
		assertion=currentURL_afterEditingURL.equals(currentURL_afterPmLogIn);
		if(assertion==false){
			report("f","Assertion failed while verifying URL");
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