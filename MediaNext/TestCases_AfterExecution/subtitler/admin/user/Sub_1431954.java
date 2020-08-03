package subtitler.admin.user;

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
import locators.UserLocators;
import modules.Translator;
import modules.Verify;
import modules.admin;

/**                                                                                                                               
 * @author pvaidya                                                   
 *Summary:Create new user and reset the password.
 */  
public class Sub_1431954 {
	
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	 String firstname = "VFirst_1431954"+CommonElements.BROWSER+"M1";
	 String lastName = "VLast_1431954"+CommonElements.BROWSER+"M1";
	 String emailid = "vendor1431954test3@gmail.com";//"vendor1431954test5@gmail.com";
	 String emailPassword="Testcase1431954";
	 String incorrectEmailId="Vendor1431954testgmail.com";
	 String menu = "Users";
	 String UserType = "Vendor";
	 String menu_to_claim = "To Claim";
	 String menu_ongoing = "Ongoing";
	 String menu_completed = "Completed";
	 Boolean assertion = true;
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_1431954");
	dataSet.put("TL_test_case_description","SUB-1431954:Create new user and reset the password");
	dataSet.put("TL_internal_testCase_ID", "1431954");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
		try{
			//Log in as admin
			admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			//Got to user menu
			admin.action().Navigate(menu);
			CreateUser_Step1(UserType,firstname,lastName, incorrectEmailId,emailid);
			admin.action().Create_And_SaveUser();
			
			//Verify create User
			General.action().waitforelemenetpresent(UserLocators.Locator().UserCreated_message(firstname,lastName));
			assertion=Verify.action().verifyElementPresent(UserLocators.Locator().UserCreated_message(firstname,lastName), 5);
			if(assertion==false)
				report("f","Assertion failed while verifying message for User Created Succesfully");
			
			Thread.sleep(1000);
			admin.action().Searchuser(firstname);
			General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname));
			assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname), 5);
			if(assertion==false)
				report("f","Assertion failed while verifying Fullname on User page");
			
			
			//Log out from admin
		    Thread.sleep(2000);
			General.action().logoutMethod();
			Thread.sleep(8000);
			
			
			logEmail_resetPasswordLink(emailid,emailPassword);
			Thread.sleep(2000);
			
			
			
		}
		catch(Throwable e){
			   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			  }
		}

public void CreateUser_Step1(String typename, String firstname,String lastname,String incorrectEmailId,String emailid) throws Exception {
	
	System.out.println("INSIDE METHOD CreateUser_Step1");

	admin.action().waitforelemenetpresent(UserLocators.Locator().AddUserbutton);
	admin.action().Click(UserLocators.Locator().AddUserbutton);
	Thread.sleep(2000);
	admin.action().waitforelemenetpresent(UserLocators.Locator().type_dropdown);
	admin.action().Dropdownwithoutselect(UserLocators.Locator().type_dropdown, UserLocators.Locator().Typeselect_dropdown(typename));
	Thread.sleep(2000);
	
	assertion=Verify.action().verifyElementPresent(UserLocators.Locator().reference_input, 5);
	if(assertion==false) {
		report("f","Assertion failed while verifying reference label is not present after selecting type as customer.");
	
	}
	//Enter 1st and last name
	Thread.sleep(1000);
    Translator.action().ClearInputvalues(UserLocators.Locator().firstnamename_input);
	Thread.sleep(1000);
    admin.action().type(UserLocators.Locator().firstnamename_input, firstname);
	Thread.sleep(2000);

	Translator.action().ClearInputvalues(UserLocators.Locator().lastname_input);
	Thread.sleep(1000);
	admin.action().type(UserLocators.Locator().lastname_input, lastname);
	Thread.sleep(2000);
	
	//enter incorrect email id and verify create button desable
	admin.action().type(UserLocators.Locator().emailid_input, incorrectEmailId); 
	Thread.sleep(2000);
	assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Create_user_buttonDisabel, 5);
	if(assertion==false) {
		report("f","Assertion failed while verifying create button disable after entering wrong email id");
	
	}
	//Clear email id and enter valied email id
	Thread.sleep(2000);
	Translator.action().ClearInputvalues(UserLocators.Locator().emailid_input);
	Thread.sleep(1000);
	admin.action().type(UserLocators.Locator().emailid_input, emailid);
	Thread.sleep(2000);

	System.out.println("EOM CreateUser_Step1()");

}

public void logEmail_resetPasswordLink(String emailid,String gmail_password ) throws Exception {
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
	Thread.sleep(4000);
	
	admin.action().driver.navigate().refresh();
	
	Thread.sleep(2000);
	assertion=Verify.action().verifyElementPresent(LoginLocators.Locator().gmail_resetPassword_mail, 5);
	if(assertion==false){
		report("f","Assertion failed while verifying Reset Password mail");
	}
	
	admin.action().Click(LoginLocators.Locator().gmail_resetPassword_mail);
	Thread.sleep(2000);
	assertion=Verify.action().verifyElementPresent(LoginLocators.Locator().gmail_resetPassword_link, 5);
	if(assertion==false){
		report("f","Assertion failed while verifying Reset Password link");
	}
	
}

public void assertion() throws Exception {
	assertion=Verify.action().verifyElementPresent(LoginLocators.Locator().gmail_resetPassword_link, 5);
	if(assertion==false){
		report("f","Assertion failed while verifying Reset Password link");
	}
	else{
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

