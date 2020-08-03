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
 *                                                                   
 * @author pvaidya                                                   
 *Summary: Create User: Customer type
 *Preconditions:Admin Credentials needed.
 */  
public class Sub_1444393 {
	
	 LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	 String firstname = "VFirst_1444393"+CommonElements.BROWSER+"M4";
	 String lastName = "VLast_1444393"+CommonElements.BROWSER+"M4";
	 String emailid = "vendor1444393test02@gmail.com";//"vendor1444393test01@gmail.com";//Testcase144493902
	 String emailPassword="Testcase144493901";
	 String newPassword="Testcase144493902";//new01
	 String incorrectEmailId="vendor1444393test01testgmail.com";
	 String menu = "Users";
	 String UserType = "Customer";
	 String menu_to_claim = "To Claim";
	 String menu_ongoing = "Ongoing";
	 String menu_completed = "Completed";
	 Boolean assertion = true;
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_1444393");
	dataSet.put("TL_test_case_description","SUB-1444393:As a PM, I need to declare users as Customer");
	dataSet.put("TL_internal_testCase_ID", "1444393");
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
				
				//Log ou from admin
			    Thread.sleep(2000);
				General.action().logoutMethod();
				Thread.sleep(8000);
				
				
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
				
				
				logEmail_resetPasswordLink(emailid,emailPassword,newPassword);
				
				General.action().driver.get("https://qa.subtitler.xyz/");
				Thread.sleep(2000);
				
				admin.action().login(emailid,newPassword);
				
				assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().SelectMenu(menu_to_claim), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Fullname on User page");
				}
				assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().SelectMenu(menu_ongoing), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Fullname on User page");
				}
				
				
				
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
		
		//Verify reference input is not present after selecting type as customer
		assertion=Verify.action().verifyElementNotPresent(UserLocators.Locator().reference_input, 5);
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
	
	public void logEmail_resetPasswordLink(String emailid,String gmail_password ,String newPassword) throws Exception {
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
		
		admin.action().Click(LoginLocators.Locator().gmail_resetPassword_link);
		Thread.sleep(2000);
		
		General.action().switchToPopupWindow(1);		
		Thread.sleep(10000);
		
		admin.action().ClearInputvalues(LoginLocators.Locator().gmail_resetPassword_link_email);
		Thread.sleep(1000);
		admin.action().type(LoginLocators.Locator().gmail_resetPassword_link_email, emailid);
		Thread.sleep(2000);
		admin.action().Click(LoginLocators.Locator().gmail_resetPassword_link_email);
		Thread.sleep(2000);
		
		admin.action().ClearInputvalues(LoginLocators.Locator().gmail_resetPassword_link_password);
		Thread.sleep(1000);
		System.out.println("--->"+newPassword);
		admin.action().type(LoginLocators.Locator().gmail_resetPassword_link_password, newPassword);
		Thread.sleep(2000);
		admin.action().Click(LoginLocators.Locator().gmail_resetPassword_link_password);
		Thread.sleep(2000);
		
		admin.action().ClearInputvalues(LoginLocators.Locator().gmail_resetPassword_link_confirmPassword);
		Thread.sleep(1000);
		admin.action().type(LoginLocators.Locator().gmail_resetPassword_link_confirmPassword, newPassword);
		Thread.sleep(2000);
		admin.action().Click(LoginLocators.Locator().gmail_resetPassword_link_confirmPassword);
		Thread.sleep(2000);
		
		admin.action().Click(LoginLocators.Locator().gmail_resetPassword_link_reset);
		Thread.sleep(2000);
		
		admin.action().Click(LoginLocators.Locator().gmail_resetPassword_link_clickHereToLogIn);
		Thread.sleep(2000);
		
		
		
		
		
	}
	
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(CommonLocartors.Locator().SelectMenu(menu_completed), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Fullname on User page");
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

