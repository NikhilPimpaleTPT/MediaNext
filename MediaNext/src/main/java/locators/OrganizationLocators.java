package locators;

import modules.Verify;

import org.openqa.selenium.By;

public class OrganizationLocators {
	
	public static OrganizationLocators org_object;
	 public static synchronized OrganizationLocators Locator() {
		  try {
		   if (org_object.equals(null)) {
			   org_object = new OrganizationLocators();
		   }
		  } catch (Exception NOSYSTEM) {
			  org_object = new OrganizationLocators();
		  }
		  return org_object;
		 }
	
	 
	 
	 
	//Create Organization 
    public final By AddOrganization_button=By.xpath("//span[text()='NEW CLIENT']");
	public final By OrganziationName_input=By.xpath("//input[@placeholder='Name']");
	public final By Add_description_input=By.xpath("//app-create-organization//input[contains(@placeholder,'Description')]");//("//app-create-organization//input[contains(@ng-reflect-name,'description')]");//By.xpath("//input[@name='description']");
	public final By selectParentOrganziation_dropdown_Button=By.xpath("//mat-select[contains(@placeholder,'Parent Client')]");//By.xpath("//mat-select[@name='Parent Organization']");
	public final By SelectSearchParentOrganizationFrom_dropdown(String OrganizationName){
		return By.xpath("//span[contains(text(),'"+OrganizationName+"')]");
	}
	
	public final By AddWorkflow_input=By.xpath("//input[contains(@placeholder,'Workflows')]");//By.xpath("//input[@placeholder='Add workflow']");
	public final By SelectWorkflowFrom_dropdown(String WorkflowName){
		return By.xpath("//mat-option//span[contains(text(),'"+WorkflowName+"')]");
	}
	public final By AddUser_input=By.xpath("//input[contains(@placeholder,'Users')]");//("//input[contains(@ng-reflect-placeholder,'Users')]");//By.xpath("//input[@placeholder='Add user']");
	public final By SelectUserFrom_dropdown(String UserName){
		return By.xpath("//mat-option//span[contains(text(),'"+UserName+"')]");
	}
	public final By SelectUserFrom_dropdownWithIndex(int index,String UserName){
		return By.xpath("//mat-option["+index+"]//span[contains(text(),'"+UserName+"')]");
	}
	public final By Create_Organization_button=By.xpath("//app-create-organization//button//span[contains(text(),'CREATE')]");//By.xpath("//button[@type='submit']");
	public final By OrganizationCreated_message(String OrganizationName){
		return By.xpath("//span[contains(text(),'Client "+OrganizationName+" created.')]");//By.xpath("//div[contains(text(),'Organization "+OrganizationName+" created.')]");
	}
	public final By Organizationmodified_message(String OrganizationName){
		return By.xpath("//span[contains(text(),'Client "+OrganizationName+" updated.')]");
	}
	
	//Search Workflow	
	public final By SearchOrganziation_input=By.xpath("//input[@placeholder='Filter by name']");
	public  By Search_Organziation_Result_verify(String OrganizationName){
		return By.xpath("//mat-cell[contains(text(),'"+OrganizationName+"')]");
	}
	
	//Edit Organization
	public final By Add_Updated_description_input=By.xpath("//app-edit-organization//input[contains(@placeholder,'Description')]");//("//app-edit-organization//input[contains(@ng-reflect-name,'description')]");//("//app-edit-group//input[contains(@ng-reflect-name,'description')]");//("//app-edit-organization//input[contains(@ng-reflect-name,'description')]");
	public final By Organization_AllAddedWorkflow_Icon=By.xpath("//shared-autocomplete-chips-workflows//mat-chip[contains(@role,'option')]");//By.xpath("//mat-chip[contains(@role,'option')]");//By.xpath("//td-chips[@placeholder='Add workflow']//div//md-basic-chip");
	
	public final By Organization_RemoveAddedWorflow_CancelIcon(int numeber){
		return By.xpath("//shared-autocomplete-chips-workflows//mat-chip["+numeber+"]//mat-icon[contains(text(),'cancel')]");//By.xpath("//shared-autocomplete-chips-workflows//mat-chip["+numeber+"]//mat-icon[contains(text(),'cancel')]");//By.xpath("//shared-autocomplete-chips-workflows//mat-chip[contains(@role,'option')]["+numeber+"]//mat-icon[contains(text(),'cancel')]");//By.xpath("//mat-chip[contains(@role,'option')]["+numeber+"]//mat-icon[contains(text(),'cancel')]");//By.xpath("//td-chips[@placeholder='Add workflow']//div//md-basic-chip["+numeber+"]//md-icon[contains(text(),'cancel')]");
	}
	public final By Organization_RemoveAddedWorflow_CancelIcon=By.xpath("//shared-autocomplete-chips-workflows//mat-chip//mat-icon[contains(@class,'remove')]");
	
	public final By Organization_AllAddedUser_Icon=By.xpath("//shared-autocomplete-chips-users//mat-chip[contains(@role,'option')]");//By.xpath("//mat-chip[contains(@role,'option')]");//By.xpath("//td-chips[@placeholder='Add user']//div//md-basic-chip");
	public final By Organization_AllAddedUser_Icon_Cancel=By.xpath("//mat-chip-list//mat-chip[contains(@role,'option')]");
	public final By Organization_RemoveAddedUser_CancelIcon(int numeber){
		return By.xpath("//shared-autocomplete-chips-users//mat-chip[contains(@role,'option')]["+numeber+"]//mat-icon[contains(@class,'remove')");//By.xpath("//mat-chip[contains(@role,'option')]["+numeber+"]//mat-icon[contains(text(),'cancel')]");//By.xpath("//td-chips[@placeholder='Add user']//div//md-basic-chip["+numeber+"]//md-icon[contains(text(),'cancel')]");
	}
	public final By Organization_RemoveAddedUser_Cancel(String addedUser) {
		return By.xpath("//mat-chip[contains(text(),'"+addedUser+"')]//mat-icon[contains(text(),'cancel')]");
	}
	
	public final By Organization_RemoveAddedUser_CancelIcon=By.xpath("//mat-chip-list//mat-chip//mat-icon[contains(@class,'remove')]");//By.xpath("//autocomplete-chips-users-async//mat-chip//mat-icon[contains(@class,'remove')]");//By.xpath("//mat-chip-list//mat-chip//mat-icon[contains(@class,'remove')]");//("//shared-autocomplete-chips-users//mat-chip//mat-icon[contains(@class,'remove')]");
	public final By Organization_RemoveAddedUsers_CancelButton(int index) {
		return By.xpath("//autocomplete-chips-users-async//mat-chip["+index+"]//mat-icon[contains(@class,'remove')]");
	}
	
	public final By Organization_CancelUsers=By.xpath("//button//span[contains(text(),'CANCEL')]");
	
	
	public final By Organization_EditOrg_UserName(String UserName){
		return By.xpath("//mat-chip-list//mat-chip[contains(text(),'"+UserName+"')]");
		
	}
	public final By Organization_UserOptions(String UserName){
	    return By.xpath("//mat-option//span[contains(text(),'"+UserName+"')]");
	}
	
			
	public final By Organization_EditOrg_RemoveAddedUser_CancelIcon(String UserName){
		return By.xpath("//mat-chip-list//mat-chip[contains(text(),'"+UserName+"')]//mat-icon[contains(text(),'cancel')]");
	}
	
	public final By Organization_User_SearchUser=By.xpath("//mat-chip-list//div//input[contains(@placeholder,'Users')]");//("//mat-chip-list//div//input[contains(@ng-reflect-placeholder,'Users')]");
	
	public final By SelectOrganization_Checkbox(String Organization_Name){
		return By.xpath("//mat-cell[text()='"+Organization_Name+"']/preceding-sibling::mat-cell//mat-checkbox[contains(@id,'checkbox')]");
	}
	
	
	public final By SelectOrganization_AsParentOrg(String OrganizationName){
		return By.xpath("//mat-option//span[contains(text(),'"+OrganizationName+"')]");
	}
	public final By searchEditIcon_button=By.xpath("//mat-icon[text()='mode_edit']");

		
	//DeleteRole
	public final By DeleteOrganization_icon=By.xpath("//mat-icon[text()='delete']");
	public final By DeleteOrganization_Alert_button=By.xpath("//span[text()='DELETE']");
	public final By DeleteOrganization_message=By.xpath("//div[text()='Organization delete']");	

	public final By DeleteOrganization_withUser_message() throws Exception{
		System.out.println("Organization can't be deleted while it contains users");
		
		//Organization can't be deleted while it contains users
//		if(Verify.action().verifyElementPresent(By.xpath("//div[contains(text(),'deleted while it contains users')]"),5))
//			return By.xpath("//div[contains(text(),'be deleted while it contains users')]");
//		else{
//			return By.xpath("//div[contains(text(),'be deleted while it contains users')][contains(@class,'hidden')]");
//		}
		
		if(Verify.action().verifyElementPresent(By.xpath("//span[contains(text(),'deleted while it contains users')]"),5))
			return By.xpath("//span[contains(text(),'be deleted while it contains users')]");
		else{
			return By.xpath("//span[contains(text(),'be deleted while it contains users')][contains(@class,'hidden')]");
		}
	}

	public final By InvalidOrganization_message() throws Exception{
		System.out.println("Invalid organization name");
		if(Verify.action().verifyElementPresent(By.xpath("//div[contains(text(),'Invalid organization name')]"),5))
			return By.xpath("//div[contains(text(),'Invalid organization name')]");
		else{
			return By.xpath("//div[contains(text(),'Invalid organization name')][contains(@class,'hidden')]");
		}
	}
	
	
	

	//OrganizationNameAscending/Descending
//		public static By AscendingOrganization_Name=By.xpath("//mat-table//div[//button[contains(text(),'Name')]]/following-sibling::span[contains(text(),'ascending')]");
//		public static By DescendingOrganization_Name=By.xpath("//mat-table//div[//button[contains(text(),'Name')]]/following-sibling::span[contains(text(),'descending')]");
	public final By AscendingOrganization_Name=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
	public final By DescendingOrganization_Name=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'descending')]]");
		public final By Organization_Name=By.xpath("//mat-table//div//button[contains(text(),'Name')]");


}
