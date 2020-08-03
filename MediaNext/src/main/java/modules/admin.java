package modules;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import conifg.CommonElements;
import locators.Admin_User_Submission_Locators;
import locators.CommonLocartors;
import locators.GroupLocators;
import locators.OrganizationLocators;
import locators.Pm_User_Submission_Locators;
import locators.RoleLocators;
import locators.TranslatorLocators;
import locators.UserLocators;
import locators.WorkflowLocators;





public class admin extends General{
	CommonLocartors commonl= new CommonLocartors();
	private static admin admin_objects;

	 /**
	  * Method used to self-instantiate the class. Will make sure one object, and
	  * one object only is created in memory for this class. The purpose is to be
	  * able to call this object dynamically from any JAVA class that includes
	  * this as an import.
	  * 
	  * @return Returns the object instantiated from the class.
	  */
	 public static synchronized admin action() {
	  try {
	   if (admin_objects.equals(null)) {
		   admin_objects = new admin();
	   }
	  } catch (Exception NOSYSTEM) {
		  admin_objects = new admin();
	  }
	  return admin_objects;
	 }
//############################################################################################################################
		
		public void CreateRole_Step1(String RoleName,String Description) throws Exception {
		
			 System.out.println("INSIDE CreateRole_Step1  method ");
				waitforelemenetpresent(RoleLocators.Locator().AddRolebutton);
				Click(RoleLocators.Locator().AddRolebutton);
				Thread.sleep(1000);
				ClearInputvalues(RoleLocators.Locator().RoleName_input);
				Thread.sleep(1000);
				type(RoleLocators.Locator().RoleName_input, RoleName);
				Thread.sleep(1000);
				ClearInputvalues(RoleLocators.Locator().RoleDiscription_input);
				Thread.sleep(1000);
				type(RoleLocators.Locator().RoleDiscription_input, Description);
				Thread.sleep(1000);
				System.out.println("EOM CreateRole_Step1()");
		}
		//##########################################################################################################################
		
		public void Create_AND_EditRole_Permission_manage(String[] manage_Role,Boolean booleanvalue) throws Exception {
			
			 System.out.println("INSIDE Create_AND_EditRole_Permission_manage  method ");
			 Thread.sleep(2000);
			 for(String MangeRoleName : manage_Role) {
//				   waitforelemenetpresent(RoleLocators.Locator().Manage_role(MangeRoleName));
				   Thread.sleep(2000);
//				   checkUncheck(RoleLocators.Locator().Manage_role(MangeRoleName), booleanvalue);
				   if(!booleanvalue){
					   System.out.println("@@@@@IF");
					   if(Verify.action().VerifyElementPresent(RoleLocators.Locator().Manage_role_checked(MangeRoleName))){
						   System.out.println("%%%%%IF%%%%%%%");
						   Click(RoleLocators.Locator().Manage_role(MangeRoleName));
						   Thread.sleep(2000);
					   }
				   }else{
					   if(Verify.action().verifyElementNotPresent(RoleLocators.Locator().Manage_role_checked(MangeRoleName), 5)){
						   System.out.println("######IF######");
						   Click(RoleLocators.Locator().Manage_role(MangeRoleName));
						   Thread.sleep(2000);
					   }
				   }
			 }
			
//			waitforelemenetpresent(RoleLocators.Locator().CreateRole_button);
//			Click(RoleLocators.Locator().CreateRole_button);
			System.out.println("EOM Create_AND_EditRole_Permission_manage()");
		}
		
	//##########################################################################################################################
		
		public void Create_AND_EditRole_Permission_Assign(String assignPriveleges_Role[], Boolean booleanvalue) throws Exception {
			 
			System.out.println("INSIDE Create_AND_EditRole_Permission_Assign  method ");
			
			 for(String AssignRoleName : assignPriveleges_Role) {
				   waitforelemenetpresent(RoleLocators.Locator().Manage_role(AssignRoleName));
				   checkUncheck(RoleLocators.Locator().Manage_role(AssignRoleName), booleanvalue);
				  }
			
//			waitforelemenetpresent(RoleLocators.Locator().CreateRole_button);
//			Click(RoleLocators.Locator().CreateRole_button);
			System.out.println("EOM Create_AND_EditRole_Permission_Assign()");
		}
		
		//##########################################################################################################################
		
			public void Create_AND_EditRole_Permission_Edit(String EditPriveleges_Role[],Boolean booleanvalue) throws Exception {
				
				System.out.println("INSIDE Create_AND_EditRole_Permission_Edit  method ");
				
			  for(String EditRoleName : EditPriveleges_Role) {
					   waitforelemenetpresent(RoleLocators.Locator().Manage_role(EditRoleName));
					   checkUncheck(RoleLocators.Locator().Manage_role(EditRoleName), booleanvalue);
					  }
				
			    //waitforelemenetpresent(RoleLocators.Locator().CreateRole_button);
				//Click(RoleLocators.Locator().CreateRole_button);
				System.out.println("EOM Create_AND_EditRole_Permission_Edit()");
			}
		//##########################################################################################################################
		
		public void Create_AND_EditRole_Permission_View(String ViewPriveleges_Role[],Boolean booleanvalue) throws Exception{
			
			System.out.println("INSIDE METHOD Create_AND_EditRole_Permission_View");
			 for(String viewRoleName : ViewPriveleges_Role) {
				   waitforelemenetpresent(RoleLocators.Locator().Manage_role(viewRoleName));
				   checkUncheck(RoleLocators.Locator().Manage_role(viewRoleName), booleanvalue);
				  }
			
//			waitforelemenetpresent(RoleLocators.Locator().CreateRole_button);
//			Click(RoleLocators.Locator().CreateRole_button);
			Thread.sleep(2000);
			System.out.println("EOM Create_AND_EditRole_Permission_View()");
		}
		//##########################################################################################################################
		
		public void Create_And_SaveRole_CreateStep() throws Exception {
			
			System.out.println("INSIDE METHOD Create_And_SaveRole_CreateStep");
			
		if(Verify.action().verifyElementPresent(RoleLocators.Locator().CreateRole_button,5)){	
			System.out.println("INSIDE IF--------");
			waitforelemenetpresent(RoleLocators.Locator().CreateRole_button);
			Click(RoleLocators.Locator().CreateRole_button);
			Thread.sleep(1000);
			System.out.println("CLICK DONE------");
		}
		else{
			System.out.println("IN ELSE------");
			waitforelemenetpresent(commonl.Update_button);
			Click(commonl.Update_button);
			Thread.sleep(3000);
			System.out.println("UPDATE DONE----");
			
			}
		System.out.println("EOM Create_And_SaveRole_CreateStep()");
		}
		
		//##########################################################################################################################	

		public void SearchRole(String RoleName) throws Exception{
			
			System.out.println("INSIDE METHOD SearchRole");
			
				waitforelemenetpresent(RoleLocators.Locator().SearchRole_input);
				Thread.sleep(1000);
				ClearInputvalues(RoleLocators.Locator().SearchRole_input);
				Thread.sleep(1000);
				type(RoleLocators.Locator().SearchRole_input, RoleName);
				Thread.sleep(1000);
				/*type(RoleLocators.Locator().SearchRole_input, Keys.chord(Keys.ENTER));
				Thread.sleep(1000);*/
				type(RoleLocators.Locator().SearchRole_input, (Keys.ENTER).toString());
				waitforelemenetpresent(RoleLocators.Locator().SearchRole_input);
				System.out.println("EOM SearchRole()");
		}
		
		//##########################################################################################################################
		
		public void EditRole_open(String RoleName) throws Exception {
			
			System.out.println("INSIDE METHOD EditRole_open");
			
			waitforelemenetpresent(RoleLocators.Locator().SearchedRole_data(RoleName));
			Click(RoleLocators.Locator().SelectRole_Checkbox(RoleName));
			Thread.sleep(2000);
			//doubleClick(RoleLocators.Locator().SearchedRole_data(RoleName));
			waitforelemenetpresent(RoleLocators.Locator().searchEditIcon_button);
			Click(RoleLocators.Locator().searchEditIcon_button);
			Thread.sleep(2000);
//			waitforelemenetpresent(commonl.Update_button);
			
			System.out.println("EOM EditRole_open()");
		}
		 //##########################################################################################################################
		
		public void EditRole_Step1(String RoleName,String Description)throws Exception {
			
			System.out.println("INSIDE METHOD EditRole_Step1");
		
				waitforelemenetpresent(RoleLocators.Locator().RoleName_input);
				Thread.sleep(2000);
				ClearInputvalues(RoleLocators.Locator().RoleName_input);
				Thread.sleep(1000);
				type(RoleLocators.Locator().RoleName_input, RoleName);
				Thread.sleep(1000);
				ClearInputvalues(RoleLocators.Locator().RoleDiscription_input);
				Thread.sleep(1000);
				type(RoleLocators.Locator().RoleDiscription_input, Description);
				Thread.sleep(1000);
				System.out.println("EOM EditRole_Step1()");
				
		}
		//##########################################################################################################################	

		public void DeleteRole(String RoleName)throws Exception {
			
			System.out.println("INSIDE METHOD DeleteRole");
			
				waitforelemenetpresent(RoleLocators.Locator().SearchedRole_data(RoleName));
				Click(RoleLocators.Locator().SelectRole_Checkbox(RoleName));
				waitforelemenetpresent(OrganizationLocators.Locator().DeleteOrganization_icon);
				Click(OrganizationLocators.Locator().DeleteOrganization_icon);
				waitforelemenetpresent(OrganizationLocators.Locator().DeleteOrganization_Alert_button);
				Click(OrganizationLocators.Locator().DeleteOrganization_Alert_button);
				
				System.out.println("EOM DeleteRole()");
				
		}
		//##########################################################################################################################	

		public void CreateUser_Step1(String typename,String firstname,String lastname,String emailid) throws Exception {
			
			System.out.println("INSIDE METHOD CreateUser_Step1");
	
			waitforelemenetpresent(UserLocators.Locator().AddUserbutton);
			Click(UserLocators.Locator().AddUserbutton);
			Thread.sleep(2000);
			waitforelemenetpresent(UserLocators.Locator().type_dropdown);
			Dropdownwithoutselect(UserLocators.Locator().type_dropdown, UserLocators.Locator().Typeselect_dropdown(typename));
			Thread.sleep(2000);
			type(UserLocators.Locator().firstnamename_input, firstname);
			Thread.sleep(2000);
			type(UserLocators.Locator().lastname_input, lastname);
			Thread.sleep(2000);
			type(UserLocators.Locator().emailid_input, emailid);
			Thread.sleep(2000);

			System.out.println("EOM CreateUser_Step1()");

		}
		//##########################################################################################################################	

		public void Create_And_EditUser_SelectRole(String Rollname) throws Exception {
			
			System.out.println("INSIDE METHOD Create_And_EditUser_SelectRole");
		
			waitforelemenetpresent(UserLocators.Locator().selectRole_dropdown);
			Dropdownwithoutselect(UserLocators.Locator().selectRole_dropdown, UserLocators.Locator().selectRole_value_dropdown(Rollname));
			
			System.out.println("EOM Create_And_EditUser_SelectRole()");
			
		}
		
		//##########################################################################################################################	

		public void CreateUser_SelectLanguage(String Sourcelanguage,String Targetlanguage) throws Exception {
			Thread.sleep(2000);
			System.out.println("INSIDE METHOD CreateUser_SelectLanguage");
			type(UserLocators.Locator().sourceLanguage_search, Sourcelanguage);
			Thread.sleep(2000);
			Click(UserLocators.Locator().SelectSearchsourcelanguage_dropdown(Sourcelanguage));
			Thread.sleep(2000);
			type(UserLocators.Locator().targetLanguage, Targetlanguage);
			Thread.sleep(2000);
			Click(UserLocators.Locator().SelectSearchsourcelanguage_dropdown(Targetlanguage));
			Thread.sleep(2000);
			Click(UserLocators.Locator().AddLanguage_icon);
			Thread.sleep(2000);		
			System.out.println("EOM CreateUser_SelectLanguage()");
		}
		
		//##########################################################################################################################
		
		public void Create_And_SaveUser() throws Exception {
			
			System.out.println("INSIDE METHOD Create_And_SaveUser");
			
		if(VerifyElementPresent(UserLocators.Locator().Create_user_button)){	
			waitforelemenetpresent(UserLocators.Locator().Create_user_button);
			Thread.sleep(8000);
			Click(UserLocators.Locator().Create_user_button);
		}
		else{
			waitforelemenetpresent(commonl.Update_button);
			Click(commonl.Update_button);
		}
		
		System.out.println("EOM Create_And_SaveUser()");
		}
	
		
	//##########################################################################################################################	

	public void Searchuser(String Username) throws Exception{
		
		System.out.println("INSIDE METHOD Searchuser");
	
			waitforelemenetpresent(UserLocators.Locator().Searchuser_input);
			Thread.sleep(2000);
			ClearInputvalues(UserLocators.Locator().Searchuser_input);
			Thread.sleep(2000);
			type(UserLocators.Locator().Searchuser_input,Username);
			Thread.sleep(2000);
			/*type(UserLocators.Locator().Searchuser_input, Keys.chord(Keys.ENTER));*/
			type(UserLocators.Locator().Searchuser_input, Keys.ENTER.toString());
			System.out.println("EOM Searchuser()");
		
	}	
	//##########################################################################################################################	
	
	/**
     * This method is used to search and check submission
     */
    public void SearchUser_andCheck(String UserName) throws Exception {
    	System.out.println("INSIDE method SearchSubmisson()\n"); 

    	waitforelemenetpresent(UserLocators.Locator().Searchuser_input);
    	Thread.sleep(1000);	
    	General.action().ClearInputvalues(UserLocators.Locator().Searchuser_input);
    	Thread.sleep(1000);	
    	type(UserLocators.Locator().Searchuser_input, UserName);
    	Thread.sleep(1000);	
    	type(UserLocators.Locator().Searchuser_input, Keys.chord(Keys.ENTER));
    	Thread.sleep(1000);	
    	waitforelemenetpresent(UserLocators.Locator().Searchuser_input);
    	Thread.sleep(1000);	
		General.action().waitforelemenetpresent(UserLocators.Locator().SelectName_Checkbox(UserName));
		Thread.sleep(1000);			
		General.action().Click(UserLocators.Locator().SelectName_Checkbox(UserName));
		Thread.sleep(2000);
    	
	    System.out.println("EOM SearchSubmisson()\n");

    }
	//##########################################################################################################################

			public void DeleteUser(String UserName) throws Exception {
				
				System.out.println("INSIDE METHOD DeleteUser");
				
					waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(UserName));
					Click(UserLocators.Locator().SelectName_Checkbox(UserName));
					waitforelemenetpresent(UserLocators.Locator().DeleteUser_icon);
					Click(UserLocators.Locator().DeleteUser_icon);
					Thread.sleep(2000);
					waitforelemenetpresent(UserLocators.Locator().DeleteUser_Alert_button);
					Click(UserLocators.Locator().DeleteUser_Alert_button);
					Thread.sleep(2000);
					//waitforelemenetpresent(UserLocators.Locator().Searchuser_input);
					//TODO NOT REQUIRED
//					waitforelemenetpresent(UserLocators.Locator().Searcheduser_data(UserName));
//					Click(UserLocators.Locator().SelectName_Checkbox(UserName));
					System.out.println("EOM DeleteUser()");

			}
	//##########################################################################################################################
	
	public void EditUser_open(String UserName) throws Exception {
		
		System.out.println("INSIDE METHOD EditUser_open");
		
		waitforelemenetpresent(RoleLocators.Locator().SearchedRole_data(UserName));
		//doubleClick(RoleLocators.Locator().SearchedRole_data(UserName));
		Click(UserLocators.Locator().SelectName_Checkbox(UserName));
		waitforelemenetpresent(UserLocators.Locator().searchEditIcon_button);
		Click(UserLocators.Locator().searchEditIcon_button);
		waitforelemenetpresent(CommonLocartors.Locator().Update_button);
		
		System.out.println("EOM EditUser_open()");
	}
	 //##########################################################################################################################

	public void EditUser_Step1(String FirstName_update,String lastName_update,String Phone_Number,String emailid_update) throws Exception {
		
		System.out.println("INSIDE METHOD EditUser_Step1");
	
		waitforelemenetpresent(UserLocators.Locator().firstnamename_input);
		Thread.sleep(defaultWaitPeriod*20);
		ClearInputvalues(UserLocators.Locator().firstnamename_input);
		type(UserLocators.Locator().firstnamename_input, FirstName_update);
		ClearInputvalues(UserLocators.Locator().lastname_input);
		type(UserLocators.Locator().lastname_input, lastName_update);
		//TODO Not needed because Email ID is not editable
//		ClearInputvalues(UserLocators.Locator().emailid_input);
//		type(UserLocators.Locator().emailid_input, emailid_update);
//		ClearInputvalues(UserLocators.Locator().PhoneName_input);
//		type(UserLocators.Locator().PhoneName_input, Phone_Number);
		
		System.out.println("EOM EditUser_Step1()");
		
	}
	//##########################################################################################################################	

	public void Navigate(String menu) throws Exception {
		
		System.out.println("INSIDE METHOD Navigate");
		
		if(VerifyElementPresent(CommonLocartors.Locator().CollapseMenuSideBar)){
			Click(CommonLocartors.Locator().ExpandMenuSideBar);
		}
		
		if(menu!=null){
			waitforelemenetpresent(CommonLocartors.Locator().SelectMenu(menu));
			Click(CommonLocartors.Locator().SelectMenu(menu));
			/*System.out.println("//app-"+menu.toLowerCase()+"");
			if(VerifyElementPresent(by))
			waitforelemenetpresent(By.xpath("//app-"+menu.toLowerCase()+""));*/
			Thread.sleep(defaultWaitPeriod*8);
			Thread.sleep(3000);
			System.out.println("navigate to ------------------------------------------------------"+menu);
		}
		
		System.out.println("EOM Navigate()");
	}	
	
	//##########################################################################################################################	

	public void CreateWorkflow_Step1(String WorkflowName)throws Exception {
		
		System.out.println("INSIDE METHOD CreateWorkflow_Step1");

		waitforelemenetpresent(WorkflowLocators.Locator().AddWorkflow_button);
		Click(WorkflowLocators.Locator().AddWorkflow_button);
		waitforelemenetpresent(WorkflowLocators.Locator().WorkflowName_input);
		type(WorkflowLocators.Locator().WorkflowName_input, WorkflowName);
		
		System.out.println("EOM CreateWorkflow_Step1()");
	

	}
	
	//##########################################################################################################################	

	public void CreateWorkflow_AddOrganization(String OrganizationName) throws Exception {
		
		System.out.println("INSIDE METHOD CreateWorkflow_AddOrganization");

		waitforelemenetpresent(WorkflowLocators.Locator().Workflow_AddOrganization_input);
//		Dropdownwithoutselect(WorkflowLocators.Locator().Workflow_AddOrganization_input, WorkflowLocators.Locator().SelectSearchOrganization_dropdown(OrganizationName));
		Dropdownwithoutselect_with_javaScript(WorkflowLocators.Locator().Workflow_AddOrganization_input, WorkflowLocators.Locator().SelectSearchOrganization_dropdown(OrganizationName));
		Thread.sleep(defaultWaitPeriod*2);
		Thread.sleep(2000);
		type(WorkflowLocators.Locator().Workflow_AddOrganization_input,Keys.chord(Keys.TAB));
		Thread.sleep(2000);
		Click(WorkflowLocators.Locator().WorkflowName_input);
		Thread.sleep(2000);
		//Click(By.xpath("//h1[text()='Create']"));
		//Click(WorkflowLocators.Locator().SelectSearchOrganization_dropdown(OrganizationName));
		
		System.out.println("EOM CreateWorkflow_AddOrganization()");
	}
	
	//##########################################################################################################################	
	
public void CreateWorkflow_Multiple_AddOrganization(String Organization []) throws Exception {
		
		System.out.println("INSIDE METHOD CreateWorkflow_Multiple_AddOrganization");

		waitforelemenetpresent(WorkflowLocators.Locator().Workflow_AddOrganization_input);
		
		for(String OrganizationName : Organization) {
			Click(WorkflowLocators.Locator().Workflow_AddOrganization_input);
			Thread.sleep(defaultWaitPeriod*3);
			Thread.sleep(1000);
			((JavascriptExecutor)driver).executeScript(
				    "arguments[0].scrollIntoView();",driver.findElement(WorkflowLocators.Locator().SelectSearchOrganization_dropdown(OrganizationName)));
			Click(WorkflowLocators.Locator().SelectSearchOrganization_dropdown(OrganizationName));
			Thread.sleep(defaultWaitPeriod*2);
			  }
		
		doubleClick(WorkflowLocators.Locator().Workflow_AddOrganization_input);
		Thread.sleep(defaultWaitPeriod);	
		Thread.sleep(1000);
		
		/*Dropdownwithoutselect(WorkflowLocators.Locator().Workflow_AddOrganization_input, WorkflowLocators.Locator().SelectSearchOrganization_dropdown(OrganizationName));
		Thread.sleep(defaultWaitPeriod*2);
		type(WorkflowLocators.Locator().Workflow_AddOrganization_input,Keys.chord(Keys.TAB));
		Thread.sleep(1000);
		Click(WorkflowLocators.Locator().WorkflowName_input);
		Thread.sleep(1000);
		//Click(By.xpath("//h1[text()='Create']"));
		//Click(WorkflowLocators.Locator().SelectSearchOrganization_dropdown(OrganizationName));
*/		
		System.out.println("EOM CreateWorkflow_Multiple_AddOrganization()");
	}
	
	//############################################################################################################################

		public void CreateWorkflow_AddSteps(int index,String StepName,String StepFromDropdown) throws Exception {
			
			System.out.println("INSIDE METHOD CreateWorkflow_AddSteps");

			waitforelemenetpresent(WorkflowLocators.Locator().Workflow_StepName_input(index));
			Thread.sleep(3000);
			Click(WorkflowLocators.Locator().Workflow_StepName_input(index));
			Thread.sleep(2000);
			ClearInputvalues(WorkflowLocators.Locator().Workflow_StepName_input(index));
			Thread.sleep(2000);
			type(WorkflowLocators.Locator().Workflow_StepName_input(index),StepName);
			Thread.sleep(2000);
			//Click(WorkflowLocators.Locator().SelectStep_Dropdwon);
			Dropdownwithoutselect(WorkflowLocators.Locator().SelectStep_Dropdwon(index), WorkflowLocators.Locator().SelectStepFrom_dropdown(StepFromDropdown));
			Thread.sleep(2000);
			System.out.println("Step Added in the Workflow"+StepFromDropdown);
			
			System.out.println("EOM CreateWorkflow_AddSteps()");
		}
		
public void CreateWorkflow_AddSteps_multi(int index,String StepName,String StepFromDropdown) throws Exception {
			
			System.out.println("INSIDE METHOD CreateWorkflow_AddSteps");
			Thread.sleep(2000);
			Click(WorkflowLocators.Locator().AddStepWorkflow_icon);
			Thread.sleep(2000);
			waitforelemenetpresent(WorkflowLocators.Locator().Workflow_StepName_input(index));
			Thread.sleep(3000);
			Click(WorkflowLocators.Locator().Workflow_StepName_input(index));
			Thread.sleep(2000);
			ClearInputvalues(WorkflowLocators.Locator().Workflow_StepName_input(index));
			Thread.sleep(2000);
			type(WorkflowLocators.Locator().Workflow_StepName_input(index),StepName);
			Thread.sleep(2000);
			//Click(WorkflowLocators.Locator().SelectStep_Dropdwon);
			Dropdownwithoutselect(WorkflowLocators.Locator().SelectStep_Dropdwon(index), WorkflowLocators.Locator().SelectStepFrom_dropdown(StepFromDropdown));
			Thread.sleep(2000);
			System.out.println("Step Added in the Workflow"+StepFromDropdown);
			
			System.out.println("EOM CreateWorkflow_AddSteps()");
		}
		
	//##########################################################################################################################
		
		public void CreateWorkflow() throws Exception {
			
			System.out.println("INSIDE METHOD CreateWorkflow");
			
			waitforelemenetpresent(WorkflowLocators.Locator().AddWorkflow_Save_button);
			Click(WorkflowLocators.Locator().AddWorkflow_Save_button);
			Thread.sleep(6000);
			//waitforelemenetpresent(WorkflowLocators.Locator().AddWorkflow_button);
			
			System.out.println("EOM CreateWorkflow()");
		}	
		
	//##########################################################################################################################	

		public void SearchWorkflow(String WorkflowName) throws Exception {
			
			System.out.println("INSIDE METHOD SearchWorkflow");
		
				waitforelemenetpresent(WorkflowLocators.Locator().SearchWorkflow_input);
				Thread.sleep(defaultWaitPeriod*2);
				Thread.sleep(3000);
				ClearInputvalues(WorkflowLocators.Locator().SearchWorkflow_input);
				Thread.sleep(3000);
				type(WorkflowLocators.Locator().SearchWorkflow_input,WorkflowName);
				Thread.sleep(3000);
				//type(WorkflowLocators.Locator().SearchWorkflow_input, Keys.chord(Keys.ENTER));
				type(WorkflowLocators.Locator().SearchWorkflow_input, Keys.ENTER.toString());
				System.out.println("EOM SearchWorkflow()");
			
		}
//##########################################################################################################################	
		public void DeleteWorkflow(String Workflow_Name) throws Exception {
			
			System.out.println("INSIDE METHOD DeleteWorkflow");
			
			waitforelemenetpresent(WorkflowLocators.Locator().SearchWorkflow_input);
			Thread.sleep(2000);
			waitforelemenetpresent(WorkflowLocators.Locator().SelectWorkflow_Checkbox(Workflow_Name));
			Thread.sleep(2000);
			Click(WorkflowLocators.Locator().SelectWorkflow_Checkbox(Workflow_Name));
			Thread.sleep(2000);
			Thread.sleep(defaultWaitPeriod*2);
			waitforelemenetpresent(WorkflowLocators.Locator().DeleteWorkflow_icon);
			Thread.sleep(2000);
			Click(WorkflowLocators.Locator().DeleteWorkflow_icon);
			Thread.sleep(2000);
			waitforelemenetpresent(WorkflowLocators.Locator().DeleteWorkflow_Alert_button);
			Thread.sleep(2000);
			Click(WorkflowLocators.Locator().DeleteWorkflow_Alert_button);
			Thread.sleep(defaultWaitPeriod*2);
			Thread.sleep(2000);
			waitforelemenetpresent(WorkflowLocators.Locator().SearchWorkflow_input);
			Thread.sleep(2000);
			System.out.println("EOM DeleteWorkflow()");
	}			
		
	//##########################################################################################################################
		
		public void EditWorkflow_open(String WorkflowName) throws Exception {
			
			System.out.println("INSIDE METHOD EditWorkflow_open");
			
			waitforelemenetpresent(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName));
			Thread.sleep(2000);
			Click(WorkflowLocators.Locator().SelectWorkflow_Checkbox(WorkflowName));
			Thread.sleep(2000);
			waitforelemenetpresent(WorkflowLocators.Locator().searchEditIcon_button);
			Thread.sleep(2000);
			Click(WorkflowLocators.Locator().searchEditIcon_button);
			Thread.sleep(2000);
			//doubleClick(WorkflowLocators.Locator().Search_Workflow_Result_verify(WorkflowName));
			//waitforelemenetpresent(CommonLocartors.Locator().Save_button);
			
			System.out.println("EOM EditWorkflow_open()");
		}
	//##########################################################################################################################	

		public void EditWorkflow_Step1(String WorkflowName) throws Exception {
			
			System.out.println("INSIDE METHOD EditRole_Step1");

			waitforelemenetpresent(WorkflowLocators.Locator().WorkflowName_input);
			Thread.sleep(2000);
			Thread.sleep(defaultWaitPeriod*20);
			ClearInputvalues(WorkflowLocators.Locator().WorkflowName_input);
			Thread.sleep(2000);
			type(WorkflowLocators.Locator().WorkflowName_input, WorkflowName.trim());
			Thread.sleep(2000);
			System.out.println("EOM EditWorkflow_Step1()");
		
		}	
		
	//##########################################################################################################################	

		public void CreateOrganization_Step1(String OrganizationName,String description) throws Exception {
			
			System.out.println("INSIDE METHOD CreateOrganization_Step1");
			Thread.sleep(1000);
			waitforelemenetpresent(OrganizationLocators.Locator().AddOrganization_button);
			Thread.sleep(1000);
			Click(OrganizationLocators.Locator().AddOrganization_button);
			Thread.sleep(1000);
			waitforelemenetpresent(OrganizationLocators.Locator().OrganziationName_input);
			Thread.sleep(1000);
			type(OrganizationLocators.Locator().OrganziationName_input, OrganizationName);
			Thread.sleep(1000);
			waitforelemenetpresent(OrganizationLocators.Locator().Add_description_input);
			Thread.sleep(1000);
			type(OrganizationLocators.Locator().Add_description_input, description);
			Thread.sleep(1000);
			
			System.out.println("EOM CreateOrganization_Step1()");
	
		}
		
	//##########################################################################################################################	

		public void CreateOrganization_AddParentOrg(String ParentOrganizationName) throws Exception {
			
			System.out.println("INSIDE METHOD CreateOrganization_AddParentOrg");

			waitforelemenetpresent(OrganizationLocators.Locator().selectParentOrganziation_dropdown_Button);
			Thread.sleep(2000);
			Thread.sleep(defaultWaitPeriod*2);
			//TODO WORKS WITH JS FOR FIREFOX
//			Dropdownwithoutselect(OrganizationLocators.Locator().selectParentOrganziation_dropdown_Button, OrganizationLocators.Locator().SelectSearchParentOrganizationFrom_dropdown(ParentOrganizationName));
			Dropdownwithoutselect_with_javaScript(OrganizationLocators.Locator().selectParentOrganziation_dropdown_Button, OrganizationLocators.Locator().SelectSearchParentOrganizationFrom_dropdown(ParentOrganizationName));
			Thread.sleep(4000);
			//TODO BELOW STEP ARE NOT REQUIRED SO COMMENTED
//			Click(OrganizationLocators.Locator().selectParentOrganziation_dropdown_Button);
//			Thread.sleep(1000);
//			Thread.sleep(defaultWaitPeriod*50);
//			((JavascriptExecutor)driver).executeScript(
//				    "arguments[0].scrollIntoView();",driver.findElement(OrganizationLocators.Locator().SelectSearchParentOrganizationFrom_dropdown(ParentOrganizationName)));
//			Thread.sleep(1000);
//			Click(OrganizationLocators.Locator().SelectSearchParentOrganizationFrom_dropdown(ParentOrganizationName));
//			Thread.sleep(2000);
			System.out.println("EOM CreateOrganization_AddParentOrg()");
		}
	//##########################################################################################################################
		public void CreateOrganization_AddWorkflow(String [] WorkflowName) throws Exception {
			
			System.out.println("INSIDE METHOD CreateOrganization_AddWorkflow");

			for(String Workflow : WorkflowName) {	
			waitforelemenetpresent(OrganizationLocators.Locator().AddWorkflow_input);
			Thread.sleep(1000);
			//Dropdownwithoutselect(OrganizationLocators.Locator().AddWorkflow_input, OrganizationLocators.Locator().SelectWorkflowFrom_dropdown(Workflow));
			Click(OrganizationLocators.Locator().AddWorkflow_input);
			Thread.sleep(2000);
			//Thread.sleep(defaultWaitPeriod*3);
			((JavascriptExecutor)driver).executeScript(
				    "arguments[0].scrollIntoView();",driver.findElement(OrganizationLocators.Locator().SelectWorkflowFrom_dropdown(Workflow)));
			Click(OrganizationLocators.Locator().SelectWorkflowFrom_dropdown(Workflow));
			Thread.sleep(3000);
			Click(OrganizationLocators.Locator().Add_description_input);
			Thread.sleep(3000);
			Thread.sleep(defaultWaitPeriod*2);
			}
			
			System.out.println("EOM CreateOrganization_AddWorkflow()");
		}
		
	//##########################################################################################################################	

		public void CreateOrganization_AddUser(String Users[]) throws Exception {
			
			System.out.println("INSIDE METHOD CreateOrganization_AddUser");

			
			for(String Username : Users) {
				System.out.println("ADDING USER--->"+Username);
				   waitforelemenetpresent(OrganizationLocators.Locator().AddUser_input);
				   Thread.sleep(defaultWaitPeriod*2);
				   Thread.sleep(2000);
				   //Dropdownwithoutselect(OrganizationLocators.Locator().AddUser_input, OrganizationLocators.Locator().SelectUserFrom_dropdown(Username));
				   Click(OrganizationLocators.Locator().AddUser_input);
				   Thread.sleep(3000);
				   Thread.sleep(defaultWaitPeriod*50);
				   if(CommonElements.BROWSER.contains("CHROME")||CommonElements.BROWSER.contains("EGCH")) { 
				   type(OrganizationLocators.Locator().AddUser_input,Username);
				   Thread.sleep(2000);
				   type(OrganizationLocators.Locator().AddUser_input,Keys.chord(Keys.SPACE));
				   Thread.sleep(2000);
				   type(OrganizationLocators.Locator().AddUser_input,Keys.chord(Keys.BACK_SPACE));
				   }else {
				   type(OrganizationLocators.Locator().AddUser_input,Username);
				   Thread.sleep(2000);
					((JavascriptExecutor)driver).executeScript(
						    "arguments[0].scrollIntoView();",driver.findElement(OrganizationLocators.Locator().SelectUserFrom_dropdown(Username)));
				   }
					Thread.sleep(4000);
					Click(OrganizationLocators.Locator().SelectUserFrom_dropdown(Username));
					Thread.sleep(2000);
					Click(OrganizationLocators.Locator().Add_description_input);
					Thread.sleep(1000);
		}
			type(OrganizationLocators.Locator().AddUser_input,Keys.chord(Keys.TAB));
			Thread.sleep(2000);
			System.out.println("EOM CreateOrganization_AddUser()");
			
		}
			
	//##########################################################################################################################
		
		public void CreateOrganization_last() throws Exception {
			
			System.out.println("INSIDE METHOD CreateOrganization_last");
			
		if(VerifyElementPresent(OrganizationLocators.Locator().Create_Organization_button))	{
			waitforelemenetpresent(OrganizationLocators.Locator().Create_Organization_button);
			Thread.sleep(1000);
			Click(OrganizationLocators.Locator().Create_Organization_button);
			Thread.sleep(4000);
		}else{
				waitforelemenetpresent(commonl.Update_button);
				Thread.sleep(1000);
				Click(commonl.Update_button);
				Thread.sleep(1000);
				if(VerifyElementPresent(commonl.Update_button)){
					Thread.sleep(defaultWaitPeriod);
					Click(commonl.Update_button);
					Thread.sleep(1000);
				}
			}
		Thread.sleep(2000);
		System.out.println("EOM CreateOrganization_last()");
		}
		
	//##########################################################################################################################	

		public void SearchOrganization(String OrganizationName) throws Exception {
			
			System.out.println("INSIDE METHOD SearchOrganization");
		
				waitforelemenetpresent(OrganizationLocators.Locator().SearchOrganziation_input);
				Thread.sleep(defaultWaitPeriod*2);
				ClearInputvalues(OrganizationLocators.Locator().SearchOrganziation_input);
				type(OrganizationLocators.Locator().SearchOrganziation_input,OrganizationName);
				/*type(OrganizationLocators.Locator().SearchOrganziation_input, Keys.chord(Keys.ENTER));*/
				type(OrganizationLocators.Locator().SearchOrganziation_input, (Keys.ENTER).toString());
				
				System.out.println("EOM SearchOrganization()");
			
		}	
	//##########################################################################################################################
		
		public void EditOrganization_open(String OrganizationName) throws Exception {
			
			System.out.println("INSIDE METHOD EditOrganization_open");
			
			waitforelemenetpresent(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName));
			//doubleClick(OrganizationLocators.Locator().Search_Organziation_Result_verify(OrganizationName));
			waitforelemenetpresent(OrganizationLocators.Locator().SelectOrganization_Checkbox(OrganizationName));
			Click(OrganizationLocators.Locator().SelectOrganization_Checkbox(OrganizationName));
			waitforelemenetpresent(OrganizationLocators.Locator().searchEditIcon_button);
			Click(OrganizationLocators.Locator().searchEditIcon_button);
			
			System.out.println("EOM EditOrganization_open()");
		}
		
	//##########################################################################################################################	

		public void EditOrganization_Step1(String OrganizationName,String description)throws Exception {
			
			System.out.println("INSIDE METHOD EditOrganization_Step1");

		
			waitforelemenetpresent(OrganizationLocators.Locator().OrganziationName_input);
			Thread.sleep(1000);
			ClearInputvalues(OrganizationLocators.Locator().OrganziationName_input);
			Thread.sleep(1000);
			type(OrganizationLocators.Locator().OrganziationName_input, OrganizationName);
			Thread.sleep(1000);
			waitforelemenetpresent(OrganizationLocators.Locator().Add_Updated_description_input);
			Thread.sleep(1000);
			ClearInputvalues(OrganizationLocators.Locator().Add_Updated_description_input);
			Thread.sleep(1000);
			type(OrganizationLocators.Locator().Add_Updated_description_input, description);
			
			System.out.println("EOM EditOrganization_Step1()");
	
		}
		//##########################################################################################################################	
		public void EditOrganization_Workflow(String Workflow[]) throws Exception {
			
			System.out.println("INSIDE METHOD EditOrganization_Workflow");
			
			Click(OrganizationLocators.Locator().AddWorkflow_input);
			Thread.sleep(1000);

//			List <WebElement> li=driver.findElements(OrganizationLocators.Locator().Organization_AllAddedWorkflow_Icon);
//			System.out.println("NO Of Worflows to remove-------->"+li.size());

			//TODO FOR LOOP WAS NOT WORKING SO DONE WITH WHILE LOOP
//			for(int i=1;i<=li.size();i++){
//				Thread.sleep(3000);
////				Click(OrganizationLocators.Locator().AddWorkflow_input);
////				Thread.sleep(2000);
//				Click(OrganizationLocators.Locator().Organization_RemoveAddedWorflow_CancelIcon(i));//mat-chip[contains(@role,'option')]["+numeber+"]//mat-icon[contains(text(),'cancel')]
////				Click(By.xpath("//mat-chip[contains(@role,'option')]["+i+"]//mat-icon[contains(text(),'cancel')]"));
//
////			for(int i=1;i<=li.size();i++){
////				Click(OrganizationLocators.Locator().Organization_RemoveAddedWorflow_CancelIcon(i));
//
//				Thread.sleep(defaultWaitPeriod*3);
//				Thread.sleep(3000);
//				
//			}
			
			List <WebElement> li=driver.findElements(OrganizationLocators.Locator().Organization_RemoveAddedWorflow_CancelIcon);
			System.out.println("NO Of Worflows to remove-------->"+li.size());
			Thread.sleep(2000);
			Click(OrganizationLocators.Locator().Add_Updated_description_input);
			Thread.sleep(1000);
			while(Verify.action().VerifyElementPresent(OrganizationLocators.Locator().Organization_RemoveAddedWorflow_CancelIcon)){
				System.out.println("INSIDE WHILE----------");
				Thread.sleep(1000);
				Click(OrganizationLocators.Locator().Organization_RemoveAddedWorflow_CancelIcon);
				Thread.sleep(3000);
			}
			
			
			for(String workflowname : Workflow) {
				Click(OrganizationLocators.Locator().Add_Updated_description_input);
				Thread.sleep(2000);
				Click(OrganizationLocators.Locator().AddWorkflow_input);
				Thread.sleep(2000);
				Thread.sleep(defaultWaitPeriod*3);
				Thread.sleep(2000);
				((JavascriptExecutor)driver).executeScript(
					    "arguments[0].scrollIntoView();",driver.findElement(OrganizationLocators.Locator().SelectWorkflowFrom_dropdown(workflowname)));
				Thread.sleep(2000);
				Click(OrganizationLocators.Locator().SelectWorkflowFrom_dropdown(workflowname));
				Thread.sleep(defaultWaitPeriod*2);
				
			}
			
			doubleClick(OrganizationLocators.Locator().OrganziationName_input);
			Thread.sleep(defaultWaitPeriod);	
			Thread.sleep(1000);
			
			System.out.println("EOM EditOrganization_Workflow()");
		}
		
	//##########################################################################################################################	
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
		
		List <WebElement> li=driver.findElements(OrganizationLocators.Locator().Organization_RemoveAddedUser_CancelIcon);
		System.out.println("NO Of Worflows to remove-------->"+li.size());
		Thread.sleep(2000);
		Click(OrganizationLocators.Locator().Add_Updated_description_input);
		Thread.sleep(4000);
		while(Verify.action().VerifyElementPresent(OrganizationLocators.Locator().Organization_RemoveAddedUser_CancelIcon)){
			System.out.println("INSIDE WHILE----------");
			Thread.sleep(1000);
			Click(OrganizationLocators.Locator().Organization_EditOrg_RemoveAddedUser_CancelIcon(User[0]));
			Thread.sleep(3000);
		}
		
		for(String UserName : User) {
				Click(OrganizationLocators.Locator().AddUser_input);
			   waitforelemenetpresent(OrganizationLocators.Locator().AddUser_input);
			   Thread.sleep(defaultWaitPeriod*2);
			   //Dropdownwithoutselect(OrganizationLocators.Locator().AddUser_input, OrganizationLocators.Locator().SelectUserFrom_dropdown(Username));
			   Click(OrganizationLocators.Locator().AddUser_input);
				Thread.sleep(defaultWaitPeriod*50);
				Thread.sleep(1000);
//				((JavascriptExecutor)driver).executeScript(
//					    "arguments[0].scrollIntoView();",driver.findElement(OrganizationLocators.Locator().SelectUserFrom_dropdown(UserName)));
				if(CommonElements.BROWSER.contains("CHROME")) { 
				
				type(OrganizationLocators.Locator().AddUser_input,UserName);
				Thread.sleep(2000);
				type(OrganizationLocators.Locator().AddUser_input,Keys.chord(Keys.SPACE));
				Thread.sleep(2000);
				type(OrganizationLocators.Locator().AddUser_input,Keys.chord(Keys.BACK_SPACE).trim());
				}else {
				type(OrganizationLocators.Locator().AddUser_input,UserName);
				Thread.sleep(2000);
			    ((JavascriptExecutor)driver).executeScript(
			    "arguments[0].scrollIntoView();",driver.findElement(OrganizationLocators.Locator().SelectUserFrom_dropdown(UserName)));
				Thread.sleep(4000);
				}
				Thread.sleep(2000);
				Click(OrganizationLocators.Locator().SelectUserFrom_dropdown(UserName));
				Thread.sleep(2000);
			  }
		
		doubleClick(OrganizationLocators.Locator().OrganziationName_input);
		Thread.sleep(defaultWaitPeriod);
		Thread.sleep(1000);
		
		System.out.println("EOM EditOrganization_User()");
	}	
	//##########################################################################################################################	

			public void DeleteOrganization(String Organization_Name) throws Exception {
				
				System.out.println("INSIDE METHOD DeleteOrganization");
				
					waitforelemenetpresent(OrganizationLocators.Locator().SearchOrganziation_input);
					waitforelemenetpresent(OrganizationLocators.Locator().SelectOrganization_Checkbox(Organization_Name));
					Click(OrganizationLocators.Locator().SelectOrganization_Checkbox(Organization_Name));
					Thread.sleep(3000);
					waitforelemenetpresent(OrganizationLocators.Locator().DeleteOrganization_icon);
					Click(RoleLocators.Locator().DeleteRole_icon);
					Thread.sleep(3000);
					waitforelemenetpresent(RoleLocators.Locator().DeleteRole_Alert_button);
					Click(RoleLocators.Locator().DeleteRole_Alert_button);
					Thread.sleep(3000);
					waitforelemenetpresent(OrganizationLocators.Locator().SearchOrganziation_input);
					
					System.out.println("EOM DeleteOrganization()");
			}	
	//##########################################################################################################################	

		public void CreateGroup_Step1(String GroupName,String description) throws Exception {
			
			System.out.println("INSIDE METHOD CreateGroup_Step1");

			waitforelemenetpresent(GroupLocators.Locator().AddGroup_button);
			Click(GroupLocators.Locator().AddGroup_button);
			Thread.sleep(1000);
			waitforelemenetpresent(GroupLocators.Locator().AddGroupName_input);
			type(GroupLocators.Locator().AddGroupName_input, GroupName);
			Thread.sleep(1000);
			waitforelemenetpresent(GroupLocators.Locator().AddGroup_description_input);
			type(GroupLocators.Locator().AddGroup_description_input, description);
			Thread.sleep(1000);
			System.out.println("EOM CreateGroup_Step1()");
	
		}
		
	//##########################################################################################################################	

		public void CreateGroup_AddOrg(String OrganizationName) throws Exception {
			
			System.out.println("INSIDE METHOD CreateGroup_AddOrg");

			waitforelemenetpresent(GroupLocators.Locator().selectGroupOrganization_dropdown_Button);
			//doubleClick(GroupLocators.Locator().selectGroupOrganization_dropdown_Button);
			Thread.sleep(3000);
			Thread.sleep(defaultWaitPeriod*2);
			//Dropdownwithoutselect(GroupLocators.Locator().selectGroupOrganization_dropdown_Button, GroupLocators.Locator().SelectSearchGroupOrganizationFrom_dropdown(OrganizationName));
			Dropdownwithoutselect_with_javaScript(GroupLocators.Locator().selectGroupOrganization_dropdown_Button, GroupLocators.Locator().SelectSearchGroupOrganizationFrom_dropdown(OrganizationName));
			Thread.sleep(4000);
			System.out.println("EOM CreateGroup_AddOrg()");
		}
	//##########################################################################################################################	

		public void CreateGroup_AddUser(String Users[]) throws Exception {
			
			System.out.println("INSIDE METHOD CreateGroup_AddUser");

			
			for(String Username : Users) {
				   waitforelemenetpresent(GroupLocators.Locator().Group_AddUser_input);
				   Thread.sleep(defaultWaitPeriod*2);
				   Thread.sleep(1000);
				   if(CommonElements.BROWSER.contains("CHROME")) { 
				   //Dropdownwithoutselect(GroupLocators.Locator().Group_AddUser_input, GroupLocators.Locator().Group_SelectUserFrom_dropdown(Username));
				   type(OrganizationLocators.Locator().AddUser_input,Username);
				   Thread.sleep(2000);
				   type(OrganizationLocators.Locator().AddUser_input,Keys.chord(Keys.SPACE));
				   Thread.sleep(2000);
				   type(OrganizationLocators.Locator().AddUser_input,Keys.chord(Keys.BACK_SPACE));
				   Thread.sleep(4000);
				   }else {
				   type(OrganizationLocators.Locator().AddUser_input,Username);
				   Thread.sleep(2000);
				   ((JavascriptExecutor)driver).executeScript(
				    "arguments[0].scrollIntoView();",driver.findElement(OrganizationLocators.Locator().SelectUserFrom_dropdown(Username)));
				    Thread.sleep(4000);
					   
				   }
					Click(OrganizationLocators.Locator().SelectUserFrom_dropdown(Username));
					Thread.sleep(2000);
				   Click(GroupLocators.Locator().AddGroup_description_input);
				   Thread.sleep(1000);
				  }
			     type(GroupLocators.Locator().Group_AddUser_input,Keys.chord(Keys.TAB));
			
			
			System.out.println("EOM CreateGroup_AddUser()");
		}
			
	//##########################################################################################################################
		
		public void CreateGroup_last()throws Exception {
			
			System.out.println("INSIDE METHOD CreateGroup_last");
			
		if(VerifyElementPresent(GroupLocators.Locator().Create_Group_button))	{
			Thread.sleep(1000);
			waitforelemenetpresent(GroupLocators.Locator().Create_Group_button);
			Thread.sleep(1000);
			Click(GroupLocators.Locator().Create_Group_button);
			Thread.sleep(2000);
		}else{
				waitforelemenetpresent(commonl.Update_button);
				Click(commonl.Update_button);
			}
		
		System.out.println("EOM CreateGroup_last()");
		}	
	//##########################################################################################################################	

		public void SearchGroup(String GroupName) throws Exception {
			
			System.out.println("INSIDE METHOD SearchGroup");
		
				waitforelemenetpresent(GroupLocators.Locator().SearchGroup_input);
				ClearInputvalues(GroupLocators.Locator().SearchGroup_input);
				type(GroupLocators.Locator().SearchGroup_input,GroupName);
				//type(GroupLocators.Locator().SearchGroup_input, Keys.chord(Keys.ENTER));
				
				System.out.println("EOM SearchGroup()");
			
		}	
	//##########################################################################################################################

		public void EditGroup_open(String GroupName) throws Exception {
			
			System.out.println("INSIDE METHOD EditGroup_open");
			
			waitforelemenetpresent(GroupLocators.Locator().Search_Group_Result_verify(GroupName));
			Click(GroupLocators.Locator().SelectGroup_Checkbox(GroupName));
			Thread.sleep(1000);
			//doubleClick(GroupLocators.Locator().Search_Group_Result_verify(GroupName));
			waitforelemenetpresent(GroupLocators.Locator().searchEditIcon_button);
			Click(GroupLocators.Locator().searchEditIcon_button);
			Thread.sleep(1000);
			System.out.println("EOM EditGroup_open()");
		}	
	//##########################################################################################################################	

			public void EditGroup_Step1(String GroupName,String description) throws Exception {
				
				System.out.println("INSIDE METHOD EditGroup_Step1");

				waitforelemenetpresent(GroupLocators.Locator().AddGroupName_input);
				Thread.sleep(defaultWaitPeriod*50);
				Click(GroupLocators.Locator().AddGroupName_input);
				ClearInputvalues(GroupLocators.Locator().AddGroupName_input);
				type(GroupLocators.Locator().AddGroupName_input, GroupName);
				Thread.sleep(1000);
				waitforelemenetpresent(GroupLocators.Locator().AddGroup_description_input);
				Click(GroupLocators.Locator().AddGroup_description_input);
				ClearInputvalues(GroupLocators.Locator().AddGroup_description_input);
				Thread.sleep(1000);
				type(GroupLocators.Locator().AddGroup_description_input, description);
				
				System.out.println("EOM EditGroup_Step1()");
		
			}
		//##########################################################################################################################	

			public void EditGroup_User(String Users[]) throws Exception {
				
				System.out.println("INSIDE METHOD EditGroup_User");
				
				//TODO FOR LOOP WAS NOT WORKING SO DONE WITH WHILE LOOP
//				List <WebElement> li=driver.findElements(GroupLocators.Locator().Group_AllAddedUser_Icon);
//				System.out.println("NO Of Users to remove-------->"+li.size());
//				for(int i=1;i<=li.size();i++){
//					Thread.sleep(1000);
//					Click(GroupLocators.Locator().Group_RemoveAddedUser_CancelIcon(i));
//					Thread.sleep(2000);
//					
//				}
				
				
				List <WebElement> li=driver.findElements(GroupLocators.Locator().Group_RemoveAddedUser_CancelIcon);
				System.out.println("NO Of Users to remove-------->"+li.size());
				Thread.sleep(2000);
//				TODO Not Needed
//				Click(OrganizationLocators.Locator().Add_Updated_description_input);
				Thread.sleep(1000);
				while(Verify.action().VerifyElementPresent(GroupLocators.Locator().Group_RemoveAddedUser_CancelIcon)){
					System.out.println("INSIDE WHILE----------");
					Thread.sleep(3000);
					Click(OrganizationLocators.Locator().Organization_RemoveAddedUser_CancelIcon);
					Thread.sleep(3000);
				}
				
				
				for(String UserName : Users) {
						Click(GroupLocators.Locator().AddGroupName_input);
						Thread.sleep(1000);
					   waitforelemenetpresent(GroupLocators.Locator().Group_AddUser_input);
					   Thread.sleep(1000);
					   Click(GroupLocators.Locator().Group_AddUser_input);
						Thread.sleep(3000);
						((JavascriptExecutor)driver).executeScript(
							    "arguments[0].scrollIntoView();",driver.findElement(GroupLocators.Locator().SelectUserFrom_dropdown(UserName)));
						Click(GroupLocators.Locator().SelectUserFrom_dropdown(UserName));
					  }
					  
				
				doubleClick(GroupLocators.Locator().AddGroupName_input);
				Thread.sleep(1000);	
				
				System.out.println("EOM EditGroup_User()");
				
			}		
	//########################################################################################################################################################
	
   
    //#########################################################################################################################################################
    
    public void sortRoles(String roleName,boolean sort) throws Exception{
    	
    	System.out.println("INSIDE METHOD sortRoles");
	 	
		admin.action().SearchRole(roleName);
		Thread.sleep(4000);
				
		//THIS FOR ASCENDING
		if(sort){
			if(Verify.action().verifyElementPresent(RoleLocators.Locator().DescendingRole_name, 5)){
				General.action().Click(RoleLocators.Locator().Role_Name);
			}
		}
		else{
			if(Verify.action().verifyElementPresent(RoleLocators.Locator().AscendingRole_Name, 5)){
				General.action().Click(RoleLocators.Locator().Role_Name);
			}
		}
		Thread.sleep(3000);		
		
		System.out.println("EOM sortRoles()");
		}
    

    //##############################################################################################################################################################
    public void sortOrganizations(String organizationName, boolean sort) throws Exception{
    	
    	System.out.println("INSIDE METHOD sortOrganizations");
    	
    	
    	admin.action().SearchOrganization(organizationName);
    	Thread.sleep(4000);
    	
    	//THIS FOR ASCENDING
    	
    	if(sort){
			if(Verify.action().verifyElementPresent(OrganizationLocators.Locator().DescendingOrganization_Name, 5)){
				General.action().Click(OrganizationLocators.Locator().Organization_Name);
			}
		}
    	else{
			if(Verify.action().verifyElementPresent(OrganizationLocators.Locator().AscendingOrganization_Name, 5)){
				General.action().Click(OrganizationLocators.Locator().Organization_Name);
			}
		}
		Thread.sleep(3000);	
		
		System.out.println("EOM sortOrganizations()");
		}
    	
    
    

//##############################################################################################################################################################
public void sortGroups(String groupName, boolean sort) throws Exception{
	
	System.out.println("INSIDE METHOD sortGroups");
	
	
	admin.action().SearchGroup(groupName);
	Thread.sleep(2000);
	
	//THIS FOR ASCENDING If  sort=true
	
	if(sort){
		if(Verify.action().verifyElementPresent(GroupLocators.Locator().DescendingGroup_Name, 5)){
			General.action().Click(GroupLocators.Locator().Group_Name);
		}
	}
	else{
		if(Verify.action().verifyElementPresent(GroupLocators.Locator().AscendingGroup_Name, 5)){
			General.action().Click(GroupLocators.Locator().Group_Name);
		}
	}
	Thread.sleep(3000);		
	
	System.out.println("EOM sortGroups()");
	}
	



//##############################################################################################################################################################
public void sortUsers(String usernName, boolean sort) throws Exception{
	
	System.out.println("INSIDE METHOD sortUsers");
	
	
	admin.action().Searchuser(usernName);
	Thread.sleep(4000);
	
	//THIS FOR ASCENDING
	
	if(sort){
		System.out.println("IN IF----------");
		if(Verify.action().verifyElementPresent(UserLocators.Locator().Descending_Email, 5)){
			General.action().Click(UserLocators.Locator().Email);
		}
	}else{
		System.out.println("IN ELSE----------");
		if(Verify.action().verifyElementPresent(UserLocators.Locator().Ascending_Email, 5)){
			General.action().Click(UserLocators.Locator().Email);
		}
	}
	Thread.sleep(3000);		
	
	System.out.println("EOM sortUsers()");
	}
	



//##############################################################################################################################################################
public void sortWorkflows(String workflowName, boolean sort) throws Exception{
	
	System.out.println("INSIDE METHOD sortWorkflows");
	
	
	admin.action().SearchOrganization(workflowName);
	Thread.sleep(4000);
	
	//THIS FOR ASCENDING
	
	if(sort){
		if(Verify.action().verifyElementPresent(WorkflowLocators.Locator().DescendingWorkflow_Name, 5)){
			General.action().Click(WorkflowLocators.Locator().Workflow_Name);
		}
	}
	else{
		if(Verify.action().verifyElementPresent(WorkflowLocators.Locator().AscendingWorkflow_Name, 5)){
			General.action().Click(WorkflowLocators.Locator().Workflow_Name);
		}
	}
	Thread.sleep(3000);		
	
	System.out.println("EOM sortWorkflows()");
	}

	




//##################################################METHODS FOR CREATE SUBMISSION#######################################################################


public void CreateSubmisson_Step1(String ReadingSpeed,String MaxCharperLine,String MindurationVSreadingSpeed,String MaxdurationVSreadingSpeed) throws Exception{
	
	 System.out.println("INSIDE method CreateSubmisson_Step1()\n");
		waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		Thread.sleep(1000);
		Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
		Thread.sleep(1000);
		waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
		Thread.sleep(1000);
		ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input);
		Thread.sleep(1000);
		type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input,ReadingSpeed);
		Thread.sleep(1000);
		
		ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
		Thread.sleep(1000);
		type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,MaxCharperLine);
		Thread.sleep(1000);
		
		ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
		Thread.sleep(1000);
		type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,MindurationVSreadingSpeed);
		Thread.sleep(1000);
		
		ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
		Thread.sleep(1000);
		type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,MaxdurationVSreadingSpeed);
		Thread.sleep(1000);
		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		Thread.sleep(3000);
		
		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		Thread.sleep(2000);
		 System.out.println("EOM CreateSubmisson_Step1()\n");

}


//##########################################################################################################################

//TODO AS PER NEW SCENARIO FROM SUBTITLER 1.6.0 SUBMISSION CREATION STEPS HAVE BEEN CHANGED SO COMMINTING BELOW CODE
//public void CreateSubmisson_Step2_videoFile(String FolderName) throws Exception {
//	
//	System.out.println("INSIDE method CreateSubmisson_Step2_videoFile()\n");
//	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_VideoFile_Button);
//	Thread.sleep(1000);
//	
//	/*File afile=new File(FilePath);
//	File[] listOfFiles={afile};
//	if (afile.isDirectory())    
//	    listOfFiles = afile.listFiles();
//	   
//	   Thread.sleep(defaultWaitPeriod*2);
//	      
//	   //Process array
//	    for (int i = 0; i < listOfFiles.length; i++)
//	    { 
//	     if (listOfFiles[i].isDirectory()) continue;
//	    
//	     String path = listOfFiles[i].getAbsolutePath();*/
//	   
//	     System.out.println("FolderName is------>"+FolderName);
//	    waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_VideoFile_Button);
//		Thread.sleep(1000);
//    	Click(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_VideoFile_Button);
//    	/*JavascriptExecutor jse = (JavascriptExecutor)driver;
//    	Document doc = (Document) Jsoup.connect(driver.getCurrentUrl());
//
//    			doc.getElementsByClass("td-file-input-hidden");
//    	jse.executeScript("document.document.getElementsByClassName('td-file-input-hidden')[0].setAttribute('class', 'td-file-input');");
//    	Thread.sleep(defaultWaitPeriod*3);*/
//    
//    	Thread.sleep(2000);
//    	System.out.println("Video FIle---------"+System.getProperty("user.dir")+"\\data\\Autoit\\"+FolderName+"\\Video\\FileUpload_Video.exe");
//    	Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\data\\Autoit\\"+FolderName+"\\Video\\FileUpload_Video.exe");
//    	//Runtime.getRuntime().exec("C:\\Ameya\\Subtitle\\Autoit\\Video\\FileUpload.exe");
//	    Thread.sleep(defaultWaitPeriod*10);	    
//
//    	System.out.println("Ho gya bhai");
//    	//driver.switchTo().activeElement().sendKeys(path);
//    	//General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button).sendKeys(path);
//    	//type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button, path);    
//    	
//    	 System.out.println("EOM CreateSubmisson_Step2_videoFile()\n");
//	    } 



//##############################################################################################################################

public void CreateSubmisson_Step2_OrganizationAndWorkflow(String OrganizationName,String WorkflowName,String transStep,String TransGroupName,String reviewStep,String ReviewGroupName,Boolean Review) throws Exception {
	System.out.println("INSIDE method CreateSubmisson_Step2_OrganizationAndWorkflow()\n"); 
	Thread.sleep(defaultWaitPeriod*2);
	Thread.sleep(1000);
	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	//Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	//Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	Dropdownwithoutselect_with_javaScript(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	Thread.sleep(defaultWaitPeriod*10);
	Thread.sleep(2000);
	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown);
	Thread.sleep(1000);
	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown,Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown(WorkflowName));
	Thread.sleep(2000);
//	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_Trans_input);
//	Thread.sleep(1000);
//	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_Trans_input, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_trans(TransGroupName));
//	Thread.sleep(2000);
	
	
	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep));
	Thread.sleep(1000);
	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_trans(TransGroupName));
	Thread.sleep(2000);
	
	if(Review){
//		waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_Review_input);
//		Thread.sleep(1000);
//    	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_Review_input, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_review(ReviewGroupName));
//    	Thread.sleep(1000);
		waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep));
		Thread.sleep(1000);
    	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_review(ReviewGroupName));
    	Thread.sleep(1000);
	}
	
	Thread.sleep(2000);
//	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Next_Button);
//	Thread.sleep(1000);
//	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Next_Button);
//	Thread.sleep(2000);
	
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
	Thread.sleep(1000);
	
    System.out.println("EOM CreateSubmisson_Step2_OrganizationAndWorkflow()\n");
}





//#######################################################################################################################

/**
 * @updated : Pranali
 * 
 * @param FolderName
 * @throws Exception
 *
 */
public void CreateSubmisson_Step3_videoFile(String FilePath , boolean uploadFromComputer ,String assetID) throws Exception{
	System.out.println("INSIDE method CreateSubmisson_Step2_videoFile()\n");
	Thread.sleep(1000);
	if(uploadFromComputer) {
	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectFromComputer_RadioButton);
	Thread.sleep(3000);
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectFromComputer_RadioButton);
	Thread.sleep(2000);
//	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button);
//	Thread.sleep(1000);
	String path = null;
	File afile=new File(FilePath);
	File[] listOfFiles={afile};
	if (afile.isDirectory())    
	    listOfFiles = afile.listFiles();
	   
	   Thread.sleep(defaultWaitPeriod*2);
	      
	   //Process array
	    for (int i = 0; i < listOfFiles.length; i++)
	    { 
	     if(listOfFiles[i].isDirectory()) 
	    	 continue;
	    
	      path = listOfFiles[i].getAbsolutePath();
	     
	    }
	 	Thread.sleep(1000);

//	     System.out.println("FolderName is------>"+FolderName);
//	 	Thread.sleep(1000);
//	    waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button);
//		Thread.sleep(1000);
//    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button);
//    	Thread.sleep(1000);
//    	/*JavascriptExecutor jse = (JavascriptExecutor)driver;
//    	Document doc = (Document) Jsoup.connect(driver.getCurrentUrl());
//
//    			doc.getElementsByClass("td-file-input-hidden");
//    	jse.executeScript("document.document.getElementsByClassName('td-file-input-hidden')[0].setAttribute('class', 'td-file-input');");
//    	Thread.sleep(defaultWaitPeriod*3);*/
	 	

    
//    	Thread.sleep(2000);
//    	System.out.println("Video FIle---------"+System.getProperty("user.dir")+"\\data\\Autoit\\"+FolderName+"\\Video\\FileUpload_Video.exe");
//    	Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\data\\Autoit\\"+FolderName+"\\Video\\FileUpload_Video.exe");
    	//Runtime.getRuntime().exec("C:\\Ameya\\Subtitle\\Autoit\\Video\\FileUpload.exe");
    	
    	
    	//TODO NEW IMPL
    	System.out.println("path---->"+path);
    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_videoUpload_input);
    	Thread.sleep(1000);
    	type(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_videoUpload_input,path);
    	Thread.sleep(1000);
    	
	}else {
		
		 System.out.println("INSIDE METHOD CreateSubmisson_Step3_SearchInCatalog()");
		 
//		Thread.sleep(3000);
//		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_RadioButton);
//	    Thread.sleep(3000);
//	    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_RadioButton);
		Thread.sleep(2000);
		Thread.sleep(3000);
		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID);
	    Thread.sleep(3000);
	    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID);
		Thread.sleep(2000);
		Thread.sleep(1000);
		General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID, assetID);
		Thread.sleep(2000);
	    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_SearchBtn);
		Thread.sleep(15000);
		General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_fileFirstRow(assetID));
		Thread.sleep(2000);
		
		 System.out.println("EOD CreateSubmisson_Step3_SearchInCatalog()");
		
	}
    	
    //TODO
    System.out.println("Ho gya bhai");
    	//driver.switchTo().activeElement().sendKeys(path);
    	//General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button).sendKeys(path);
    	//type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button, path);    
    	
    System.out.println("EOM CreateSubmisson_Step2_videoFile()\n");
} 
//##########################################################################################################################





public void CreateSubmisson_Step3_VideoSRTFile(String FilePath) throws Exception {
	System.out.println("INSIDE method CreateSubmisson_Step3_VideoSRTFile()\n"); 
	
	//TODO BELOW CODE NOT REQUIRED FORM SUB 1.6.0
//	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceFile_Button);
//	File afile=new File(FilePath);
//	File[] listOfFiles={afile};
//	if (afile.isDirectory())    
//	    listOfFiles = afile.listFiles();
//	   
//	   Thread.sleep(defaultWaitPeriod*2);
//	      
//	   //Process array
//	    for (int i = 0; i < listOfFiles.length; i++)
//	    { 
//	     if (listOfFiles[i].isDirectory()) continue;
//	    
//	     String path = listOfFiles[i].getAbsolutePath();
//	   
//	    System.out.println("Path is------>"+path);
//	    waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceFile_Button);
//		Thread.sleep(1000);
//    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceFile_Button);
////    	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceFile_Button, path);    
//	    
//    	
//    	Thread.sleep(2000);
//    	System.out.println("Source Path---------"+System.getProperty("user.dir")+"\\data\\Autoit\\"+FolderName+"\\Source\\FileUpload_Source.exe");
//    	Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\data\\Autoit\\"+FolderName+"\\Source\\FileUpload_Source.exe");
//    	//Runtime.getRuntime().exec("C:\\Ameya\\Subtitle\\Autoit\\Source\\FileUpload_Source.exe");
//	    
//	    Thread.sleep(defaultWaitPeriod*10);	    
//
//	    Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Step1Next_Button);
//		Thread.sleep(1000);
	
	//TODO NEW IMPLEMENTAION AS PER 1.6.0RC1
	
	String path = null;
	File afile=new File(FilePath);
	File[] listOfFiles={afile};
	if (afile.isDirectory())    
	    listOfFiles = afile.listFiles();
	   
	   Thread.sleep(defaultWaitPeriod*2);
	      
	   //Process array
	    for (int i = 0; i < listOfFiles.length; i++)
	    { 
	     if(listOfFiles[i].isDirectory()) 
	    	 continue;
	    
	      path = listOfFiles[i].getAbsolutePath();
	     
	    }
	 	Thread.sleep(1000);
	 	
    	System.out.println("path---->"+path);
    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input);
    	Thread.sleep(1000);
    	type(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input,path);
    	Thread.sleep(1000);

    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
		Thread.sleep(1000);
		
	    System.out.println("EOM CreateSubmisson_Step3_VideoSRTFile()\n");
}


//##########################################################################################################################

//TODO AS PER NEW SCENARIO FROM SUBTITLER 1.6.0 SUBMISSION CREATION STEPS HAVE BEEN CHANGED SO COMMINTING BELOW CODE

//public void CreateSubmisson_Step4_OrganizationAndWorkflow(String OrganizationName,String WorkflowName,String TransGroupName,String ReviewGroupName,Boolean Review) throws Exception {
//	System.out.println("INSIDE method CreateSubmisson_Step4_OrganizationAndWorkflow()\n"); 
//	Thread.sleep(defaultWaitPeriod*2);
//	Thread.sleep(1000);
//	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_SelectOrganization_dropdown);
//	Thread.sleep(1000);
//	//Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
//	//Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
//	Dropdownwithoutselect(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_SelectOrganization_dropdown, Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
//	//Thread.sleep(defaultWaitPeriod*10);
//	Thread.sleep(2000);
//	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_SelectWorkflow_dropdown);
//	Thread.sleep(1000);
//	Dropdownwithoutselect(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_SelectWorkflow_dropdown,Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_SelectWorkflow_dropdown(WorkflowName));
//	Thread.sleep(2000);
//	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_AddGroup_Trans_input);
//	Thread.sleep(1000);
//	Dropdownwithoutselect(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_AddGroup_Trans_input, Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_AddGroupValue_Dropdown(TransGroupName));
//	Thread.sleep(2000);
//	if(Review){
//		waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_AddGroup_Review_input);
//		Thread.sleep(1000);
//    	Dropdownwithoutselect(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_AddGroup_Review_input,Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_AddGroupValue_Dropdown(ReviewGroupName));
//    	}
//	Thread.sleep(2000);
//	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_Next_Button);
//	Thread.sleep(1000);
//	Click(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_Next_Button);
//	Thread.sleep(2000);
//	  System.out.println("EOM CreateSubmisson_Step4_OrganizationAndWorkflow()\n");
//}

//##########################################################################################################################

public void CreateSubmisson_Step4_MetaData(String DueDate,String SubmissionName,String SourceLanguage,String TargetLanguage) throws Exception {
	System.out.println("INSIDE method CreateSubmisson_Step4_MetaData()\n"); 
	//TODO NOT REQUIRED
//	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
//	Thread.sleep(1000);
//	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
//	Thread.sleep(1000);
	
	System.out.println("DATE IMPLEMENTATION");
	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
	String newdate = GetDataPlus(Integer.valueOf(DueDate));
    System.out.println("newdate---->"+newdate);
	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	Thread.sleep(1000);
	ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	Thread.sleep(1000);
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	Thread.sleep(2000);		
	
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_arrow);
	Thread.sleep(1000);		
	
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_year(newdate.substring(9, 13)));
	Thread.sleep(1000);		
	
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_month(newdate.substring(0, 3).toUpperCase()));
	Thread.sleep(1000);		
	if(newdate.substring(5, 6).contentEquals("0")){
		System.out.println("IF--->"+newdate.substring(6, 7));
		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(6, 7)));
		
		Thread.sleep(1000);		
	}else{
		System.out.println("ELSE--->"+newdate.substring(5, 7));
		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(5, 7)));
		Thread.sleep(1000);		
	}

	System.out.println("DATE IMPLEMENTATION END--------------");

	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input, SubmissionName);
	Thread.sleep(3000);
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
	Thread.sleep(2000);
	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, SourceLanguage);
	Thread.sleep(3000);
//	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
//	Thread.sleep(1000);
	System.out.println("CLICKED");
//	((JavascriptExecutor) General.driver).executeScript(
//            "arguments[0].scrollIntoView();", General.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage).toString()));
	
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage));
	Thread.sleep(1000);
	if(TargetLanguage!=""){
		System.out.println("TARGET LANGUAGE NOT NULL");
    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
    	Thread.sleep(1000);
    	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input, TargetLanguage);
    	Thread.sleep(1000);
    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(TargetLanguage));
    	Thread.sleep(1000);
	}
	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
	Thread.sleep(1000);
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
	Thread.sleep(25000);
	//TODO NOT REQUIRED
//	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
//	Thread.sleep(1000);
	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	Thread.sleep(1000);
    System.out.println("EOM CreateSubmisson_Step4_MetaData()\n");
}

//##############################################################################################################################

public void CreateSubmisson_Step5_MetaData(String DueDate,String SubmissionName,String SourceLanguage,String TargetLanguage) throws Exception {
	
	System.out.println("INSIDE METHOD CreateSubmisson_Step5_MetaData()\n");
	
	System.out.println("DATE IMPLEMENTATION");
	String newdate = GetDataPlus(Integer.valueOf(DueDate));
	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	Thread.sleep(1000);
	ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	Thread.sleep(1000);
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	Thread.sleep(2000);		
	
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_arrow);
	Thread.sleep(1000);		
	
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_year);
	Thread.sleep(1000);		
	
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_month);
	Thread.sleep(1000);		
	if(newdate.substring(5, 6).contentEquals("0")){
		System.out.println("IF--->"+newdate.substring(6, 7));
		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(6, 7)));
		
		Thread.sleep(1000);		
	}else{
		System.out.println("ELSE--->"+newdate.substring(5, 7));
		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(5, 7)));
		Thread.sleep(1000);		
	}

	System.out.println("DATE IMPLEMENTATION END--------------");

	//TODO DATE FUNCTIOANLITY CHANGED
///*	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_DueDate_input);
//	ClearInputvalues(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_DueDate_input);
//	type(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_DueDate_input, DueDate);
//	Thread.sleep(2000);*/
	type(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_Name_input, SubmissionName);
	Thread.sleep(2000);
	type(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_SourceLanguage_input, SourceLanguage);
	Thread.sleep(2000);
	Click(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage));
	Thread.sleep(2000);
	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_TargetLanguage_input);
	type(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_TargetLanguage_input, TargetLanguage);
	Thread.sleep(2000);
	
	Click(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_SourceLanguageFrom_dropdown(TargetLanguage));
	Thread.sleep(2000);
	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_Create_Button);
	Click(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_Create_Button);
	Thread.sleep(2000);
	//waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_User_New_Submission_Button);
	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input);
	   System.out.println("EOM CreateSubmisson_Step5_MetaData()\n");
}
//##########################################################################################################################
public void CreateSubmisson_Step5_MetaData_MultiLanguages(String DueDate,String SubmissionName,String SourceLanguage,String [] Targetlanguage) throws Exception {
	
	System.out.println("INSIDE METHOD CreateSubmisson_Step5_MetaData_MultiLanguages()\n");
	 
    System.out.println("DATE IMPLEMENTATION");
	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
	String newdate = GetDataPlus(Integer.valueOf(DueDate));

	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	Thread.sleep(1000);
	ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	Thread.sleep(1000);
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	Thread.sleep(2000);		
	
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_arrow);
	Thread.sleep(1000);		
	
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_year(newdate.substring(9, 13)));
	Thread.sleep(1000);		
	
	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_month(newdate.substring(0, 3).toUpperCase()));
	Thread.sleep(1000);		
	if(newdate.substring(5, 6).contentEquals("0")){
		System.out.println("IF--->"+newdate.substring(6, 7));
		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(6, 7)));
		
		Thread.sleep(1000);		
	}else{
		System.out.println("ELSE--->"+newdate.substring(5, 7));
		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(5, 7)));
		Thread.sleep(1000);		
	}

	System.out.println("DATE IMPLEMENTATION END--------------");
	
	
	
	//TODO DATE FUNCTIOANLITY CHANGED

	type(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_Name_input, SubmissionName);
	Thread.sleep(2000);
	type(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_SourceLanguage_input, SourceLanguage.trim());
	Thread.sleep(2000);
	Click(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage));
	Thread.sleep(2000);
	
	System.out.println("Start for loop");

	for(String language : Targetlanguage) {	
	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_TargetLanguage_input);
	 Thread.sleep(defaultWaitPeriod*2);
	   Thread.sleep(2000);
	Click(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_TargetLanguage_input);
	 Thread.sleep(defaultWaitPeriod*2);
	   Thread.sleep(2000);
	   type(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_TargetLanguage_input, language);
		Thread.sleep(2000);
	   
//	((JavascriptExecutor)driver).executeScript(
//		    "arguments[0].scrollIntoView();",driver.findElement(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_TargetLanguageFrom_dropdown(language)));
//	 Thread.sleep(defaultWaitPeriod*2);
	   Thread.sleep(2000);
	Click(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_TargetLanguageFrom_dropdown(language));
	 Thread.sleep(defaultWaitPeriod*2);
	   Thread.sleep(2000);
	}
	
	System.out.println("End for loop)");

	Thread.sleep(2000);
	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_Create_Button);
	Click(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_Create_Button);
	Thread.sleep(25000);
	//waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_User_New_Submission_Button);
	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input);
	   System.out.println("EOM CreateSubmisson_Step5_MetaData_MultiLanguages()\n");
}


//###########################################################################################################################
/**
 * @author Mandar
 * This method is used to get date with specifed Offset
 */
	public static String GetDataPlus(Integer Days) throws Exception {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, Days);
		DateFormat dateFormat = new SimpleDateFormat(". dd, yyyy HH:mm", Locale.getDefault());
		DateFormat dateFormat2 = new SimpleDateFormat("MMMM");
		String monthParsed = dateFormat2.format(cal.getTime());
		return (monthParsed.substring(0, 3) + dateFormat.format(cal.getTime()));
	}

//##########################################################################################################################
	 public void SearchSubmisson(String SubmissionName) throws Exception {
	    	System.out.println("INSIDE method SearchSubmisson()\n"); 

	    	if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().filters_clearFilterButton_enabled, 5)) {
	    		Click(Pm_User_Submission_Locators.Locator().filters_clearFilterButton);
	    		Thread.sleep(2000);	
	    	}
	    	
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
	    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);
	    	type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	    	Thread.sleep(4000);	

            //type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
	    	//TODO NOT REQUIRED AS PER JAVA 8
	    	
	    	//TODO NOT REQUIRED AS PER NEW IMPROVEMENT FOR FILTER SUBMISSION (1.22.0 RC1)
	    	//type_withKEYS(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.ENTER,false);
	    	Thread.sleep(1000);	
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
 	    System.out.println("EOM SearchSubmisson()\n");

	    }
//################################################################################################################################
/**
 * This method is used to search and check submission
 */
public void SearchSubmisson_andCheck(String SubmissionName) throws Exception {
	System.out.println("INSIDE method SearchSubmisson_andCheck()\n"); 

	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	Thread.sleep(1000);	
	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	Thread.sleep(1000);	
	type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	Thread.sleep(1000);	
	
	//TODO NOT REQUIRED FOR build 1.22.0 RC1 AS FUNCTIONALITY CHANGED
//	type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
//	type(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input, Keys.ENTER.toString());
//	Thread.sleep(1000);	
//	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
//	Thread.sleep(1000);	
	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
	Thread.sleep(1000);			
	General.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
	Thread.sleep(2000);
	
    System.out.println("EOM SearchSubmisson_andCheck()\n");

}

//##########################################################################################################################################

public void deleteSubmisson(String SubmissionName) throws Exception {
	
	System.out.println("INSIDE METHOD deleteSubmisson");
/*	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input);
	Thread.sleep(1000);
	ClearInputvalues(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input);
	type(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input, SubmissionName);
	type(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input, Keys.chord(Keys.ENTER));
	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));*/
	
	admin.action().SearchSubmisson_andCheck(SubmissionName);
	Thread.sleep(1000);
	Click(Admin_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
	Thread.sleep(2000);
	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().DeleteSubmission_Icon);
	Thread.sleep(1000);
	Click(Admin_User_Submission_Locators.Locator().DeleteSubmission_Icon);
	Thread.sleep(1000);
	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().DeleteSubmission_Alert_button);
	Thread.sleep(1000);
	Click(Admin_User_Submission_Locators.Locator().DeleteSubmission_Alert_button);
	Thread.sleep(2000);
//	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input);
	Thread.sleep(1000);
	System.out.println("EOM deleteSubmisson()");
}
//##############################################################################################

public void cancelDeleteSubmisson(String SubmissionName) throws Exception {
	
	System.out.println("INSIDE METHOD cancelSubmisson");

	admin.action().SearchSubmisson_andCheck(SubmissionName);
	Thread.sleep(1000);
//	Click(Admin_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
//	Thread.sleep(2000);
	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().DeleteSubmission_Icon);
	Thread.sleep(1000);
	Click(Admin_User_Submission_Locators.Locator().DeleteSubmission_Icon);
	Thread.sleep(1000);
	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().DeleteSubmission_Alert_Cancel_button);
	Thread.sleep(1000);
	Click(Admin_User_Submission_Locators.Locator().DeleteSubmission_Alert_Cancel_button);
	Thread.sleep(2000);
	waitforelemenetpresent(Admin_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
	Thread.sleep(1000);
	Click(Admin_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
	Thread.sleep(2000);
	//waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input);
	Thread.sleep(1000);
	System.out.println("EOM cancelSubmisson()");
}
//###################################################################################################

public void trans_SearchSubmisson(String SubmissionName) throws Exception {
	
	System.out.println("INSIDE METHOD Trans_SearchSubmisson");
	 
	waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
	Thread.sleep(1000);
	ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
	Thread.sleep(1000);
	type(TranslatorLocators.Locator().translator_search_input, SubmissionName);
	Thread.sleep(1000);
	type(TranslatorLocators.Locator().translator_search_input, Keys.chord(Keys.ENTER));
	Thread.sleep(1000);
	waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
	Thread.sleep(1000);
	
	System.out.println("EOM Trans_SearchSubmisson()");
}

//#####################################################################################################
public void Trans_AssigneSubmisson(String SubmissionName) throws Exception {
	
	System.out.println("INSIDE METHOD Trans_AssigneSubmisson");
	 
	waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
	Thread.sleep(1000);
	Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox_1(SubmissionName));
	Thread.sleep(1000);
	waitforelemenetpresent(TranslatorLocators.Locator().translator_clamSubmission_icon);
	Thread.sleep(1000);
	Click(TranslatorLocators.Locator().translator_clamSubmission_icon);
	Thread.sleep(1000);
	waitforelemenetpresent(TranslatorLocators.Locator().translator_claimAlert_claim_button);
	Thread.sleep(1000);
	Click(TranslatorLocators.Locator().translator_clamSubmission_icon);
	Thread.sleep(1000);
	Thread.sleep(defaultWaitPeriod*5);
	
	System.out.println("EOM Trans_AssigneSubmisson()");
}
//#####################################################################################################

public void trans_ToClaim(String SubmissionName) throws Exception {
	
	 System.out.println("INSIDE trans_ToClaim  method()");
	 
		waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		Click(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
		Thread.sleep(1000);
		type(TranslatorLocators.Locator().translator_search_input, SubmissionName);
		Thread.sleep(1000);
		
		   if(Verify.action().verifyElementNotPresent(TranslatorLocators.Locator().trans_toClaim_checkbox_checked,5))
		   {	
			System.out.println("INSIDE IF--------");
			Thread.sleep(1000);
			waitforelemenetpresent(TranslatorLocators.Locator().trans_toClaim_select_header_checkbox);
			Thread.sleep(1000);
			Click(TranslatorLocators.Locator().trans_toClaim_select_header_checkbox);
			Thread.sleep(1000);
			System.out.println("CLICK DONE------");
		   }
		Click(TranslatorLocators.Locator().translator_clamSubmission_icon);
		Thread.sleep(3000);
		waitforelemenetpresent(TranslatorLocators.Locator().translator_claimAlert_claim_button);
		Thread.sleep(1000);
		Click(TranslatorLocators.Locator().translator_claimAlert_claim_button);
		Thread.sleep(2000);
		Thread.sleep(defaultWaitPeriod*5);
		
		System.out.println("EOM trans_ToClaim()");

}

//#################################################################################################
		public void translate_onGoing_submission(String SubmissionName, String target) throws Exception {
			
			 System.out.println("INSIDE trans_Ongoing  method()");
			 
				waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
				Thread.sleep(1000);
				Click(TranslatorLocators.Locator().translator_search_input);
				Thread.sleep(1000);
				ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
				Thread.sleep(1000);
				type(TranslatorLocators.Locator().translator_search_input, SubmissionName);
				Thread.sleep(1000);
				
				if(Verify.action().VerifyElementPresent(TranslatorLocators.Locator().check_trans_Target_Lang(SubmissionName, target)))
					{
					System.out.println("INSIDE IF--------");
					Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName, target));
					}
				  Thread.sleep(1000);
			      Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
				
				  Thread.sleep(2000);
			      List <WebElement> listofIds1= driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
				  Thread.sleep(1000);
				   System.out.println("No of IDS--------"+listofIds1.size());
		
				    for(int i=1;i<=listofIds1.size();i++){
					waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
					Thread.sleep(1000);
					waitforelemenetpresent(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
					Thread.sleep(1000);
					Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
					Thread.sleep(1000);
					
				    }
					waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
					Thread.sleep(1000);
					Click(TranslatorLocators.Locator().translator_Complete_Button);
					Thread.sleep(1000);
					waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
					Thread.sleep(1000);
					Click(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
					 System.out.println("EOM trans_Ongoing  method()");

		}
		//#####################################################################################################################
	    public void sort(String menu, String columnName,boolean sort) throws Exception{
	    	
	    	System.out.println("INSIDE METHOD sort");
		 	
			admin.action().Navigate(menu);
			Thread.sleep(4000);
			General.action().Click(Admin_User_Submission_Locators.Locator().colunm_Name(columnName));
					
			//THIS FOR ASCENDING
			if(sort){
				if(Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().descending_sort(columnName), 5 )){
					General.action().Click(Admin_User_Submission_Locators.Locator().colunm_Name(columnName));
				}
			}
			else{
				if(Verify.action().verifyElementPresent(Admin_User_Submission_Locators.Locator().Ascending_sort(columnName), 5 )){
					General.action().Click(Admin_User_Submission_Locators.Locator().colunm_Name(columnName));
				}
			}
			Thread.sleep(3000);		
			
			System.out.println("EOM sort()");
			}
	    
			
			
		//###############################################################################################################	
	
		
		

}





  
  