package admin.user;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.CommonLocartors;
import locators.UserLocators;
import modules.Verify;
import modules.admin;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_653801 {
	
Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	 String sourcelanguage = "en-US";
	 String targetlanguage = "de-DE";
	 
	 String firstname_emp = "EMP_FirstName_653801"+CommonElements.BROWSER+"M4";
	 String lastName_emp = "EMP_LastName_653801"+CommonElements.BROWSER+"M4";
	 String emailid_emp = "Employee653801"+CommonElements.BROWSER+"M4@gmail.com";
	 String firstname_Ven = "VEN_FirstName_653801"+CommonElements.BROWSER+"M4";
	 String lastName_Ven = "VEN_LastName_653801"+CommonElements.BROWSER+"M4";
	 String emailid_Ven = "Vendor653801"+CommonElements.BROWSER+"M4@gmail.com";


	 String selectedRole_emp = "PM";
	 String UserType_emp = "Employee";
	 String Typename_emp="Employee";
	 String menu = "Users";
	 String UserType_Ven = "Vendor";
	 String Typename_Ven="Vendor";

	 String selectedRole_Ven = "Linguist";
	
	
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_653801");
	dataSet.put("TL_test_case_description","SUB-653801:User Form Employee - Add information label on email field");
	dataSet.put("TL_internal_testCase_ID", "653801");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				//Scenario:-1
				// create EMPLOYEE user
				admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
				Thread.sleep(1000);
				admin.action().Navigate(menu);
				Thread.sleep(1000);
				CreateUser_Step1_with_label_verification_Employee(UserType_emp,firstname_emp,lastName_emp, emailid_emp);
				Thread.sleep(1000);
				admin.action().Create_And_EditUser_SelectRole(selectedRole_emp);
				Thread.sleep(1000);
				admin.action().CreateUser_SelectLanguage(sourcelanguage, targetlanguage);
				Thread.sleep(1000);
				Create_And_SaveUser();				
				General.action().waitforelemenetpresent(UserLocators.Locator().UserCreated_message(firstname_emp,lastName_emp));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().UserCreated_message(firstname_emp,lastName_emp), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for Employee User Created Succesfully");
				
				Thread.sleep(1000);
				admin.action().Searchuser(firstname_emp);
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname_emp));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname_emp), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying Employee name on User page");
				Thread.sleep(1000);
				// create VENDOR user
				Thread.sleep(3000);
				admin.action().Navigate(menu);
				Thread.sleep(1000);
				CreateUser_Step1_with_label_verification_Vendor(UserType_Ven,firstname_Ven,lastName_Ven, emailid_Ven);
				Thread.sleep(1000);
				admin.action().Create_And_EditUser_SelectRole(selectedRole_Ven);
				Thread.sleep(1000);
				admin.action().CreateUser_SelectLanguage(sourcelanguage, targetlanguage);
				Thread.sleep(1000);
				Create_And_SaveUser();
				
				General.action().waitforelemenetpresent(UserLocators.Locator().UserCreated_message(firstname_Ven,lastName_Ven));
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().UserCreated_message(firstname_Ven,lastName_Ven), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying message for User Created Succesfully");
				
				Thread.sleep(1000);
				admin.action().Searchuser(firstname_Ven);
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname_Ven));
				Thread.sleep(1000);
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname_Ven), 5);
				if(assertion==false)
					report("f","Assertion failed while verifying Vendor Name on User page");
				Thread.sleep(3000);
				
				//Scenario:-2
				Thread.sleep(1000);
				admin.action().Navigate(menu);
			    Thread.sleep(1000);
				admin.action().EditUser_open(firstname_Ven);
				Thread.sleep(1000);
			
				assertion=Verify.action().verifyElementNotPresent(UserLocators.Locator().employee_type_label_Helpdeskmessage, 5);
				if(assertion==false)
					report("f","Assertion failed while verifying label_message initially");
				Thread.sleep(1000);
				//TODO NOT Required as while editing user type can not select
//				admin.action().waitforelemenetpresent(UserLocators.Locator().type_dropdown);
//				Thread.sleep(1000);
//				admin.action().Dropdownwithoutselect_with_javaScript(UserLocators.Locator().type_dropdown, UserLocators.Locator().Typeselect_dropdown(Typename_emp));
//				Thread.sleep(2000);
				
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().type_Disabled, 5);
				if(assertion==false)
					report("f","Assertion failed while editing user type can not select.");
				Thread.sleep(1000);
				
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().employee_type_label_message, 5);
				if(assertion==false)
					report("f","Assertion failed while verifying employee_type_label_message");
				Thread.sleep(1000);
				admin.action().waitforelemenetpresent(CommonLocartors.Locator().Update_button);
				Thread.sleep(1000);
				admin.action().Click(CommonLocartors.Locator().Update_button);
				Thread.sleep(2000);

				//Scenario:-3
				admin.action().Navigate(menu);
			    Thread.sleep(1000);
				admin.action().EditUser_open(firstname_emp);
				Thread.sleep(1000);
				admin.action().waitforelemenetpresent(UserLocators.Locator().employee_type_label_message);
				Thread.sleep(1000);
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().employee_type_label_message, 5);
				if(assertion==false)
					report("f","Assertion failed while verifying label_message for employee type");
				Thread.sleep(1000);
				//TODO NOT Required as while editing user type can not select
//				admin.action().waitforelemenetpresent(UserLocators.Locator().type_dropdown);
//				Thread.sleep(1000);
//				admin.action().Dropdownwithoutselect(UserLocators.Locator().type_dropdown, UserLocators.Locator().Typeselect_dropdown(Typename_Ven));
//				Thread.sleep(2000);
//				
				assertion=Verify.action().verifyElementPresent(UserLocators.Locator().type_Disabled, 5);
				if(assertion==false)
					report("f","Assertion failed while editing user type can not select.");
				Thread.sleep(1000);
				assertion=Verify.action().verifyElementNotPresent(UserLocators.Locator().employee_type_label_Helpdeskmessage, 5);
				if(assertion==false)
					report("f","Assertion failed while verifying label_message for vendor type");
				
			
				}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
				  }
			}
	
	public void Create_And_SaveUser() throws Exception {
		
		System.out.println("INSIDE METHOD Create_And_SaveUser");
		
	if(Verify.action().VerifyElementPresent(UserLocators.Locator().Create_user_button)){	
		Verify.action().waitforelemenetpresent(UserLocators.Locator().Create_user_button);
		Thread.sleep(1000);
		Verify.action().Click(UserLocators.Locator().Create_user_button);
	}
	else{
		Verify.action().waitforelemenetpresent(CommonLocartors.Locator().Update_button);
		Verify.action().Click(CommonLocartors.Locator().Update_button);
	}
	
	System.out.println("EOM Create_And_SaveUser()");
	}
	
	
	public void CreateUser_Step1_with_label_verification_Employee(String typename,String firstname,String lastname,String emailid) throws Exception {
		
		System.out.println("INSIDE METHOD CreateUser_Step1_with_label_verification_Employee");

		admin.action().waitforelemenetpresent(UserLocators.Locator().AddUserbutton);
		Thread.sleep(1000);
		admin.action().Click(UserLocators.Locator().AddUserbutton);
		Thread.sleep(2000);
		
		assertion=Verify.action().verifyElementNotPresent(UserLocators.Locator().employee_type_label_message, 5);
		if(assertion==false)
			report("f","Assertion failed while verifying label_message initially");
		Thread.sleep(1000);
		admin.action().waitforelemenetpresent(UserLocators.Locator().type_dropdown);
		Thread.sleep(1000);
		admin.action().Dropdownwithoutselect(UserLocators.Locator().type_dropdown, UserLocators.Locator().Typeselect_dropdown(Typename_emp));
		Thread.sleep(2000);
		
		assertion=Verify.action().verifyElementPresent(UserLocators.Locator().employee_type_label_Helpdeskmessage, 5);
		if(assertion==false)
			report("f","Assertion failed while verifying employee_type_label_message");
		
		Thread.sleep(4000);
		admin.action().type(UserLocators.Locator().firstnamename_input, firstname_emp);
		Thread.sleep(2000);
		admin.action().type(UserLocators.Locator().lastname_input, lastName_emp);
		Thread.sleep(2000);
		admin.action().type(UserLocators.Locator().emailid_input, emailid_emp);
		Thread.sleep(2000);

		System.out.println("EOM CreateUser_Step1_with_label_verification_Employee()");

	}
	
	
  public void CreateUser_Step1_with_label_verification_Vendor(String typename,String firstname,String lastname,String emailid) throws Exception {
		
		System.out.println("INSIDE METHOD CreateUser_Step1_with_label_verification_Vendor");

		admin.action().waitforelemenetpresent(UserLocators.Locator().AddUserbutton);
		Thread.sleep(1000);
		admin.action().Click(UserLocators.Locator().AddUserbutton);
		Thread.sleep(2000);
		
		assertion=Verify.action().verifyElementNotPresent(UserLocators.Locator().employee_type_label_message, 5);
		if(assertion==false)
			report("f","Assertion failed while verifying label_message hidden initially");
		Thread.sleep(1000);
		admin.action().waitforelemenetpresent(UserLocators.Locator().type_dropdown);
		Thread.sleep(1000);
		admin.action().Dropdownwithoutselect(UserLocators.Locator().type_dropdown, UserLocators.Locator().Typeselect_dropdown(Typename_Ven));
		Thread.sleep(2000);
		
		assertion=Verify.action().verifyElementNotPresent(UserLocators.Locator().employee_type_label_message, 5);
		if(assertion==false)
			report("f","Assertion failed while verifying employee_type_label_message");
		Thread.sleep(1000);
	    admin.action().type(UserLocators.Locator().firstnamename_input, firstname_Ven);
		Thread.sleep(2000);
		admin.action().type(UserLocators.Locator().lastname_input, lastName_Ven);
		Thread.sleep(2000);
		admin.action().type(UserLocators.Locator().emailid_input, emailid_Ven);
		Thread.sleep(2000);

		System.out.println("EOM CreateUser_Step1_with_label_verification_Vendor()");

	}
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementNotPresent(UserLocators.Locator().employee_type_label_Helpdeskmessage, 5);
		if(assertion==false)
			report("f","Assertion failed while verifying label_message for vendor type");
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



