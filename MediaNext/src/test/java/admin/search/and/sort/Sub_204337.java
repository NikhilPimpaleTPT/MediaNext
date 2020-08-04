package admin.search.and.sort;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import locators.GroupLocators;
import locators.OrganizationLocators;
import locators.RoleLocators;
import locators.UserLocators;
import locators.WorkflowLocators;
import modules.Verify;
import modules.admin;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;

import conifg.CommonElements;

/**
 * 
 * @author psukhwani
 * 
 * Filter Resources

 *
 */

public class Sub_204337 {

	Boolean assertion = true;

	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	String RoleName = "Role_204337"+CommonElements.BROWSER+"M5";
	
	String OrganizationName_Org = "Organization_204337"+CommonElements.BROWSER+"M5";
	String description_Org = "Description for Organization 204337"+CommonElements.BROWSER+"M5";
	
	String GroupName = "Group_204337"+CommonElements.BROWSER+"M5";
	String description_Grp = "Description for Group 204337"+CommonElements.BROWSER+"M5";
	
	String firstname_Users = "VFirst_204337"+CommonElements.BROWSER+"M5";
	String lastName_Users = "VLast_204337"+CommonElements.BROWSER+"M5";
	String emailid_Users = "Vendor204337"+CommonElements.BROWSER+"M5";
	
	String WorkflowName_wrkflw = "Workflow_204337"+CommonElements.BROWSER+"M5";

	
	// Parameters for Roles

	String Roles_permission_Edit[] = { "Edit Subtitles" };

	// Parameters for Organizations

	String ParentOrganizationName_Org = "TransPerfect";
	String WorkflowName_Org[] = { "One_Step_Workflow", "Two_Step_Workflow" };
	String User_Org[] = { "admin" };

	// Parameters for Groups


	String OrganizationName_Grp = "TransPerfect";
	String User_Grp[] = { "admin" };
	String Username_Grp[] = { "admin", "linguist" };

	// Parameters for Users
	String sourcelanguage_Users = "en-US";
	String targetlanguage_Users = "de-DE";

	String selectedRole_Users = "PM";

	String UserType_Users = "Vendor";
	String Typename_Users = "Vendor";

	// Parameters for Workflows
	String OrganizationName_wrkflw = "TransPerfect";
	String StepName_wrkflw = "trans";
	String StepFromDropdown_wrkflw = "Translation";
	String StepName1_wrkflw = "review";
	String StepFromDropdown1_wrkflw = "Review";

	// parameter for navigation
	String[] menu = { "Roles", "Clients", "Groups", "Users", "Workflows" };


	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_204337");
		dataSet.put("TL_test_case_description", "SUB-204337:Filter Resources");
		dataSet.put("TL_internal_testCase_ID", "204337");

	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
		try {
			admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			admin.action().Navigate(menu[0]);
			sortRoles();
			admin.action().Navigate(menu[1]);
			sortOrganization();
			admin.action().Navigate(menu[2]);
			sortGroups();
			admin.action().Navigate(menu[3]);
			Thread.sleep(4000);
			sortUsers();
			admin.action().Navigate(menu[4]);
			sortWorkflows();

		} catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}

	// Create sortRoles Method
	/**
	 * This method is used to sort roles in admin
	 * @author psukhwani
	 * @throws Exception
	 */

	public void sortRoles() throws Exception {

		try {

			System.out.println("INSIDE sortRoles()");
			for (int i = 1; i <= 3; i++) {
				Thread.sleep(4000);
				System.out.println("ROLE NAME----->" + RoleName + i);
				admin.action().CreateRole_Step1(RoleName + i,RoleName + i + "_desc");
				admin.action().Create_AND_EditRole_Permission_Edit(Roles_permission_Edit, true);
				admin.action().Create_And_SaveUser();
				Thread.sleep(2000);
			}
			Thread.sleep(4000);
			admin.action().sortRoles(RoleName, true);

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(RoleLocators.Locator().SearchedRole_data(RoleName + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(RoleLocators.Locator().SearchedRole_data(RoleName + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Role Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(RoleLocators.Locator().SearchedRole_data(RoleName + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(RoleLocators.Locator().SearchedRole_data(RoleName + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Role Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(RoleLocators.Locator().SearchedRole_data(RoleName + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(RoleLocators.Locator().SearchedRole_data(RoleName + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Role Name after search");
			}

			admin.action().sortRoles(RoleName, false);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(RoleLocators.Locator().SearchedRole_data(RoleName + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(RoleLocators.Locator().SearchedRole_data(RoleName + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Role Name after search");
			}

			//verify if role name is filtered properly
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(RoleLocators.Locator().SearchedRole_data(RoleName + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(RoleLocators.Locator().SearchedRole_data(RoleName + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Role Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(RoleLocators.Locator().SearchedRole_data(RoleName + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(RoleLocators.Locator().SearchedRole_data(RoleName + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Role Name after search");
			}

			System.out.println("EOM sortOrganization()");

		} catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}

	// Create sortOrganization Method
	/**
	 * This method is used to sort organization in admin
	 * @author psukhwani
	 * @throws Exception
	 */
	public void sortOrganization() throws Exception {
	try {
			System.out.println("INSIDE sortOrganizations()");
			
			for (int i = 1; i <= 3; i++) {
				Thread.sleep(4000);
				System.out.println("Organization NAME----->"+ OrganizationName_Org + i);
				admin.action().CreateOrganization_Step1(OrganizationName_Org + i,OrganizationName_Org + i + "_desc");
				admin.action().CreateOrganization_AddParentOrg(ParentOrganizationName_Org);
				admin.action().CreateOrganization_AddWorkflow(WorkflowName_Org);
				admin.action().CreateOrganization_AddUser(User_Org);
				admin.action().CreateOrganization_last();
				Thread.sleep(2000);
			}
			Thread.sleep(4000);
			admin.action().sortOrganizations(OrganizationName_Org, true);

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_Org + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_Org + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Organization Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_Org + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_Org + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Organization Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_Org + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_Org + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Organization Name after search");
			}

			admin.action().sortOrganizations(OrganizationName_Org, false);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_Org + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_Org + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Organization Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_Org + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_Org + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Organization Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_Org + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName_Org + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Organization Name after search");
			}

			System.out.println("EOM sortOrganization()");
		} catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}

	// Create sortGroups Method
	/**
	 * This method is used to sort Groups in admin
	 * @author psukhwani
	 * @throws Exception
	 */
	public void sortGroups() throws Exception {
	try {

			System.out.println("INSIDE sortGroups()");
			for (int i = 1; i <= 3; i++) {
				Thread.sleep(4000);
				System.out.println("Group NAME----->" + GroupName + i);
				admin.action().CreateGroup_Step1(GroupName + i,GroupName + i + "_desc");
				admin.action().CreateGroup_AddOrg(OrganizationName_Grp);
				admin.action().CreateGroup_AddUser(User_Grp);
				admin.action().CreateGroup_last();
				Thread.sleep(2000);
			}
			Thread.sleep(4000);
			admin.action().sortGroups(GroupName, true);

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Group Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Group Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Group Name after search");
			}

			admin.action().sortGroups(GroupName, false);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Group Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Group Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Group Name after search");
			}

			System.out.println("EOM sortGroups()");

		} catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}

	// Create sortUsers Method
	/**
	 * This method is used to sort Users in admin
	 * @author psukhwani
	 * @throws Exception
	 */
	public void sortUsers() throws Exception {
	try {

			System.out.println("INSIDE sortUsers()");
			for (int i = 1; i <= 3; i++) {
				
				Thread.sleep(4000);
				System.out.println("User NAME----->" + firstname_Users + i);
				admin.action().CreateUser_Step1(UserType_Users,firstname_Users+i, lastName_Users + i + "_desc",emailid_Users+i+"@gmail.com");
				admin.action().Create_And_EditUser_SelectRole(selectedRole_Users);
				admin.action().CreateUser_SelectLanguage(sourcelanguage_Users,targetlanguage_Users);
				admin.action().Create_And_SaveUser();
				Thread.sleep(5000);
			}

			Thread.sleep(4000);
			admin.action().sortUsers(firstname_Users, true);

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname_Users + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname_Users + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  User's First Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname_Users + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname_Users + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  User's First Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname_Users + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname_Users + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  User's First Name after search");
			}

			admin.action().sortUsers(firstname_Users, false);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname_Users + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname_Users + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  User's First Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname_Users + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname_Users + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  User's First Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(firstname_Users + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(UserLocators.Locator().Searcheduser_data(firstname_Users + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  User's First Name after search");
			}

			System.out.println("EOM sortUsers()");

		} catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}

	// Create sortWorkflows Method
	/**
	 * This method is used to sort Workflows in admin
	 * @author psukhwani
	 * @throws Exception
	 */
	public void sortWorkflows() throws Exception {
	try {

			System.out.println("INSIDE sortWorkflows()");
			for (int i = 1; i <= 3; i++) {
				Thread.sleep(4000);
				System.out.println("Workflow NAME----->" + WorkflowName_wrkflw+ i);

				admin.action().CreateWorkflow_Step1(WorkflowName_wrkflw+i);
				admin.action().CreateWorkflow_AddOrganization(OrganizationName_wrkflw);
				admin.action().CreateWorkflow_AddSteps(1,StepName_wrkflw,StepFromDropdown_wrkflw);
				admin.action().CreateWorkflow_AddSteps_multi(2,StepName1_wrkflw,StepFromDropdown1_wrkflw);
				admin.action().CreateWorkflow();
				Thread.sleep(2000);
			}
			Thread.sleep(4000);
			admin.action().sortWorkflows(WorkflowName_wrkflw, true);

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_wrkflw + "1"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_wrkflw + "1"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Workflow Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_wrkflw + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_wrkflw + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Workflow Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_wrkflw + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_wrkflw + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Workflow Name after search");
			}

			admin.action().sortWorkflows(WorkflowName_wrkflw, false);
			Thread.sleep(2000);
			General.action().waitforelemenetpresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_wrkflw + "3"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_wrkflw + "3"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying  Workflow Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_wrkflw + "2"));
			Thread.sleep(2000);
			assertion = Verify.action().verifyElementPresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_wrkflw + "2"), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying Workflow Name after search");
			}

			Thread.sleep(2000);
			General.action().waitforelemenetpresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_wrkflw + "1"));
			Thread.sleep(2000);

			System.out.println("EOM sortWorkflows()");

		} catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName_wrkflw + "1"), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Workflow Name after search");
		}else{
			report("p","All assertions passed.");
		}
	}

	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}

	public void report(String result, String notes) throws Exception {
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild,result,Subtitler_TestRail_Common_Properties.assignedTo,notes);
		if(result == "f")
			assertTrue(false);
		
	}

}
