package admin.organization;
import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import locators.CommonLocartors;
import locators.OrganizationLocators;
import modules.Verify;
import modules.admin;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

public class Sub_54738 {


Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
     String WorkflowName []= {"One_Step_Workflow","Two_Step_Workflow"};
	 String OrganizationName = "Organization_54738"+CommonElements.BROWSER+"A1";
	 String description = "Description for Organization 54738"+CommonElements.BROWSER+"A1";
	 String OrganizationName_updated = "Organization_54738"+CommonElements.BROWSER+"updated_A1";
	 String description_updated = "Description for Organization 54738 "+CommonElements.BROWSER+" Updated_A1";
	 String ParentOrganizationName = "TransPerfect";
	 String User[] = {"admin","Customer"};

	 String menu="Clients";
     String Workflow[]={"One_Step_Workflow"};

	 String NewUser[]={"admin","Customer"};

	 
	 @BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_54738");
	dataSet.put("TL_test_case_description","SUB-54738:Edit Organization");
	dataSet.put("TL_internal_testCase_ID", "54738");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
			try{
				admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
				Thread.sleep(20000);
				admin.action().Navigate(menu);
				admin.action().CreateOrganization_Step1(OrganizationName, description);
				admin.action().CreateOrganization_AddParentOrg(ParentOrganizationName);
				admin.action().CreateOrganization_AddWorkflow(WorkflowName);
				admin.action().CreateOrganization_AddUser(User);
				admin.action().CreateOrganization_last();
				Thread.sleep(3000);
				admin.action().SearchOrganization(OrganizationName);
				Thread.sleep(2000);
				admin.action().EditOrganization_open(OrganizationName);
				Thread.sleep(2000);
				admin.action().EditOrganization_Step1(OrganizationName_updated, description_updated);
				Thread.sleep(3000);
				admin.action().EditOrganization_Workflow(Workflow);
				Thread.sleep(2000);

				Thread.sleep(2000);
				CreateOrganization_last();		
				//Organization Organization_54738_CHROME_updated_S5 updated.
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Organizationmodified_message(OrganizationName_updated), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}
				
				admin.action().SearchOrganization(OrganizationName_updated);
				Thread.sleep(2000);
				assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_updated), 5);
				if(assertion==false){
					report("f","Assertion failed while verifying Workflow Name after Search	");
				}

			}
			catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		    }
	}
	
	public void CreateOrganization_last() throws Exception {
		
		System.out.println("INSIDE METHOD CreateOrganization_last");
		
	if(Verify.action().VerifyElementPresent(OrganizationLocators.Locator().Create_Organization_button))	{
		General.action().waitforelemenetpresent(OrganizationLocators.Locator().Create_Organization_button);
		Thread.sleep(1000);
		General.action().Click(OrganizationLocators.Locator().Create_Organization_button);
		Thread.sleep(4000);
	}else{
		General.action().waitforelemenetpresent(CommonLocartors.Locator().Update_button);
			Thread.sleep(1000);
			General.action().Click(CommonLocartors.Locator().Update_button);
			if(Verify.action().VerifyElementPresent(CommonLocartors.Locator().Update_button)){
				Thread.sleep(General.action().defaultWaitPeriod);
				General.action().Click(CommonLocartors.Locator().Update_button);
			}
		}
	System.out.println("EOM CreateOrganization_last()");
	}
	
	public void EditOrganization_User(String User[]) throws Exception {		
		System.out.println("INSIDE METHOD EditOrganization_User");

//		List <WebElement> li=driver.findElements(OrganizationLocators.Locator().Organization_AllAddedUser_Icon);
//		System.out.println("NO Of Worflows to remove-------->"+li.size());
		
		//TODO FOR LOOP WAS NOT WORKING SO DONE WITH WHILE LOOP
//		for(int i=1;i<=li.size();i++){
//			Click(OrganizationLocators.Locator().Organization_RemoveAddedUser_CancelIcon(i));
//			Thread.sleep(defaultWaitPeriod*3);
//			Thread.sleep(1000);
//			
//		}
		
		List <WebElement> li=General.action().driver.findElements(OrganizationLocators.Locator().Organization_RemoveAddedUser_CancelIcon);
		System.out.println("NO Of Worflows to remove-------->"+li.size());
		Thread.sleep(2000);
		General.action().Click(OrganizationLocators.Locator().Add_Updated_description_input);
		Thread.sleep(4000);
		//TODO 
//		while(Verify.action().VerifyElementPresent(OrganizationLocators.Locator().Organization_RemoveAddedUser_CancelIcon)){
//			System.out.println("INSIDE WHILE----------");
//			Thread.sleep(3000);
//			General.action().Click(OrganizationLocators.Locator().Organization_EditOrg_RemoveAddedUser_CancelIcon(User[0]));
//			Thread.sleep(3000);
//		}
		//TODO NO NEED OF THIS LOOP AS REMOVING ONLY ONE USER
		//for(int i=0;i<User.length-1;i++) {
		//	General.action().Click(OrganizationLocators.Locator().Organization_EditOrg_RemoveAddedUser_CancelIcon(User[0]));
		//	Thread.sleep(3000);
		//}
		General.action().ClickX(OrganizationLocators.Locator().Organization_EditOrg_RemoveAddedUser_CancelIcon(User[0]));
		Thread.sleep(3000);
		 General.action().Click(OrganizationLocators.Locator().AddUser_input);
		 Thread.sleep(2000);
	    ((JavascriptExecutor)General.action().driver).executeScript(
	    "arguments[0].scrollIntoView();",General.action().driver.findElement(OrganizationLocators.Locator().SelectUserFrom_dropdownWithIndex(2,User[0])));
		Thread.sleep(4000);
		assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdownWithIndex(2,User[0]), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Workflow Name after Search	");
		}
		
		Thread.sleep(2000);
		assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().SelectUserFrom_dropdownWithIndex(4,User[1]), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Workflow Name after Search	");
		}

		
		General.action().doubleClick(OrganizationLocators.Locator().OrganziationName_input);
		Thread.sleep(1000);
		
		System.out.println("EOM EditOrganization_User()");
	}	
	
	
	
	public void assertion() throws Exception {
		assertion=Verify.action().verifyElementPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_updated), 5);
		if(assertion==false){
			report("f","Assertion failed while verifying Workflow Name after Search	");
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
