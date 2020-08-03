package locators;

import modules.Verify;

import org.openqa.selenium.By;

public class RoleLocators {
	public static RoleLocators role_object;
	
	 public static synchronized RoleLocators Locator() {
		  try {
		   if (role_object.equals(null)) {
			   role_object = new RoleLocators();
		   }
		  } catch (Exception NOSYSTEM) {
			  role_object = new RoleLocators();
		  }
		  return role_object;
		 }

	
//Add Role	
	public final By AddRolebutton=By.xpath("//span[text()='NEW ROLE']");
	public final By RoleName_input=By.xpath("//input[contains(@placeholder,'Name')]");//("//input[contains(@ng-reflect-name,'name')]");//By.xpath("//input[@name='name']");
	public final By RoleDiscription_input=By.xpath("//input[contains(@placeholder,'Description')]");//("//input[contains(@ng-reflect-name,'description')]");//By.xpath("//input[@name='description']");
	public final By Manage_role(String role_type){
		return By.xpath("//span[contains(text(),'"+role_type+"')]/preceding-sibling::div[contains(@class,'checkbox')]");//By.xpath("//input[@name='"+role_type+"']/../..//div[contains(@class,'checkbox')]");
	}
	
	public final By Manage_role_checked(String role_type){
		return By.xpath("//span[contains(text(),'"+role_type+"')]/ancestor::mat-checkbox[contains(@class,'mat-checkbox-checked')]");//("//span[contains(text(),'"+role_type+"')]/preceding-sibling::div[contains(@class,'checkbox')][input[contains(@aria-checked,'true')]]");
	}
	public final By Manage_role_unChecked(String role_type){
		return By.xpath("//span[contains(text(),'"+role_type+"')]/preceding-sibling::div[contains(@class,'checkbox')][input[contains(@aria-checked,'false')]]");
	}
	
	
	public final By CreateRole_button=By.xpath("//span[contains(text(),'CREATE')]");//By.xpath("//button[@type='submit']");
	public final By Rolecreated_message(String roleName){
		return By.xpath("//div[contains(text(),'Role "+roleName+" created.')]");
	}
	
	
//SearchRole
	public final By SearchRole_input=By.xpath("//input[@placeholder='Filter by name']");
	public final By SearchedRole_data(String SearchBy){
		return By.xpath("//mat-cell[contains(text(),'"+SearchBy+"')]");
	}
public final By SelectRole_Checkbox(String Role_Name){
	return By.xpath("//mat-cell[text()='"+Role_Name+"']/preceding-sibling::mat-cell//mat-checkbox[contains(@id,'checkbox')]");
}

public final By Select_all_Roles_checkbox=By.xpath("//mat-header-row//div[contains(@class,'checkbox')]");
//SearchEdit
	public final By searchEditIcon_button=By.xpath("//mat-icon[text()='mode_edit']");
	
	
	
//DeleteRole
	public final By DeleteRole_icon=By.xpath("//mat-icon[text()='delete']");
	public final By DeleteRole_Alert_button=By.xpath("//span[text()='DELETE']");
	public final By DeleteRole_message=By.xpath("//div[text()='Role has been deleted']");
	
	//RoleNameAscending/Descending
//	public static By AscendingRole_Name=By.xpath("//mat-table//div[//button[contains(text(),'Name')]]/following-sibling::span[contains(text(),'ascending')]");
//	public static By DescendingRole_name=By.xpath("//mat-table//div[//button[contains(text(),'Name')]]/following-sibling::span[contains(text(),'descending')]");
	public final By AscendingRole_Name=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
	public final By DescendingRole_name=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'descending')]]");
	public final By Role_Name=By.xpath("//mat-table//div//button[contains(text(),'Name')]");

	public final By DeleteRole_assigned_to_user_message() throws Exception{
		System.out.println("This role is assigned to user(s), you can not delete it.");
		if(Verify.action().verifyElementPresent(By.xpath("//div[contains(text(),'This role is assigned to user(s), you can not delete it.')]"),5))
			return By.xpath("//div[contains(text(),'This role is assigned to user(s), you can not delete it.')]");
		else{
			return By.xpath("//div[contains(text(),'This role is assigned to user(s), you can not delete it.')][contains(@class,'hidden')]");
		}
	}
	
	public final By RoleAlreadyExist_message() throws Exception{
		System.out.println("That role name already exists");
		if(Verify.action().verifyElementPresent(By.xpath("//div[contains(text(),'That role name already exists')]"),5))
			return By.xpath("//div[contains(text(),'That role name already exists')]");
		else{
			return By.xpath("//div[contains(text(),'That role name already exists')][contains(@class,'hidden')]");
		}
	}

}


