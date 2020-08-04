package admin.user;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.UserLocators;
import modules.Translator;
import modules.Verify;
import modules.admin;

public class Sub_277152 {


Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 String sourcelanguage = "en-US";
	 String targetlanguage = "de-DE";
	 String firstnameWithSpaces = " VFirst_277152"+CommonElements.BROWSER+"W ";
	 String lastNameWithSpaces = " VLast_277152"+CommonElements.BROWSER+"W ";
	 String firstname = "VFirst_277152"+CommonElements.BROWSER+"P";
	 String lastName = "VLast_277152"+CommonElements.BROWSER+"P";
	 String selectedRole = "Linguist";
	 String emailid = "Vendor277152"+CommonElements.BROWSER+"P@gmail.com";
	 String menu = "Users";
	 String UserType = "Vendor";
	 String Typename="Vendor";
	
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_277152");
	dataSet.put("TL_test_case_description","SUB-277152:Create User: Vendor type");
	dataSet.put("TL_internal_testCase_ID", "277152");
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
				CreateUser_Step1(UserType,firstnameWithSpaces,lastNameWithSpaces,firstname,lastName, emailid);
				admin.action().Create_And_EditUser_SelectRole(selectedRole);
				admin.action().CreateUser_SelectLanguage(sourcelanguage, targetlanguage);
				admin.action().Create_And_SaveUser();
				
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
				
				//create duplicate user
				admin.action().Navigate(menu);
				admin.action().CreateUser_Step1(UserType,firstname,lastName, emailid);
				admin.action().Create_And_EditUser_SelectRole(selectedRole);
				admin.action().CreateUser_SelectLanguage(sourcelanguage, targetlanguage);
				admin.action().Create_And_SaveUser();
				
				General.action().waitforelemenetpresent(UserLocators.Locator().UserAlreadyCreated_message());
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().UserAlreadyCreated_message(), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for User Created Succesfully");
				
				}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			}
	
	public void CreateUser_Step1(String typename,String firstnameWithSpaces,String lastnameSpaces, String firstname,String lastname,String emailid) throws Exception {
		
		System.out.println("INSIDE METHOD CreateUser_Step1");

		admin.action().waitforelemenetpresent(UserLocators.Locator().AddUserbutton);
		admin.action().Click(UserLocators.Locator().AddUserbutton);
		Thread.sleep(2000);
		admin.action().waitforelemenetpresent(UserLocators.Locator().type_dropdown);
		admin.action().Dropdownwithoutselect(UserLocators.Locator().type_dropdown, UserLocators.Locator().Typeselect_dropdown(typename));
		Thread.sleep(2000);
		admin.action().type(UserLocators.Locator().firstnamename_input, firstnameWithSpaces);
		Thread.sleep(2000);
		admin.action().Click(UserLocators.Locator().lastname_input);
		Thread.sleep(2000);
		assertion=Verify.action().verifyElementPresent(UserLocators.Locator().CreateUserName_WhitespaceError, 5);
		if(assertion==false) {
			report("f","Assertion failed while verifying message First Name With Spaces");
		
		}
	    Translator.action().ClearInputvalues(UserLocators.Locator().firstnamename_input);
		Thread.sleep(1000);
	    admin.action().type(UserLocators.Locator().firstnamename_input, firstname);
		Thread.sleep(2000);
		admin.action().type(UserLocators.Locator().lastname_input, lastnameSpaces);
		Thread.sleep(2000);
		admin.action().Click(UserLocators.Locator().firstnamename_input);
		Thread.sleep(2000);
		assertion=Verify.action().verifyElementPresent(UserLocators.Locator().CreateUserName_WhitespaceError, 5);
		if(assertion==false) {
			report("f","Assertion failed while verifying message First Name With Spaces");
		
		}
		Translator.action().ClearInputvalues(UserLocators.Locator().lastname_input);
		Thread.sleep(1000);
		admin.action().type(UserLocators.Locator().lastname_input, lastname);
		Thread.sleep(2000);
		admin.action().type(UserLocators.Locator().emailid_input, emailid);
		Thread.sleep(2000);

		System.out.println("EOM CreateUser_Step1()");

	}
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(UserLocators.Locator().UserAlreadyCreated_message(), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Username after search");
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
