package locators;

import org.openqa.selenium.By;

public class GroupLocators {
	public static GroupLocators group_object;
	
	 public static synchronized GroupLocators Locator() {
		  try {
		   if (group_object.equals(null)) {
			   group_object = new GroupLocators();
		   }
		  } catch (Exception NOSYSTEM) {
			  group_object = new GroupLocators();
		  }
		  return group_object;
		 }
	public final By AddGroup_button=By.xpath("//span[text()='NEW GROUP']");
	public final By AddGroupName_input=By.xpath("//input[contains(@placeholder,'Name')]");//("//input[contains(@ng-reflect-placeholder,'Name')]");//By.xpath("//app-create-group//input[contains(@ng-reflect-placeholder,'Name')]");//By.xpath("//input[@placeholder='Name']");
	public final By AddGroup_description_input=By.xpath("//input[contains(@placeholder,'Description')]");//("//input[contains(@ng-reflect-placeholder,'Description')]");//By.xpath("//app-create-group//input[contains(@ng-reflect-placeholder,'Description')]");//By.xpath("//input[@name='description']");
	
	public final By selectGroupOrganization_dropdown_Button=By.xpath("//mat-select[contains(@placeholder,'Client')]");//By.xpath("//mat-select[contains(@name,'Organization')]");
	public final By SelectSearchGroupOrganizationFrom_dropdown(String OrganizationName){
		return By.xpath("//span[contains(text(),'"+OrganizationName+"')]");
	}
	
	
	public final By Group_SelectUserFrom_dropdown_user(int rowNumber,String UserMailId){
		return By.xpath("//mat-option//span[contains(text(),'"+UserMailId+"')]");//By.xpath("//mat-option["+rowNumber+"]//span[contains(text(),'"+UserMailId+"')]");
	}
	
	
	public final By Group_AddUser_input=By.xpath("//input[contains(@placeholder,'Users')]");//By.xpath("//td-chips[@placeholder='Add user']//mat-form-field");//input[@placeholder='Add user']/following-sibling::span//label[contains(text(),'Add user')]");
	public final By Group_SelectUserFrom_dropdown(String UserName){
		return By.xpath("//span[contains(text(),'"+UserName+"')]");
	}
	
	public final By Group_SelectUserFrom_dropdown_verifyUserName(int rowNumber,String mailid){
		return By.xpath("//mat-option["+rowNumber+"]//span[contains(text(),'"+mailid+"')]");
	}
	
	
	public final By Create_Group_button=By.xpath("//app-create-group//button//span[contains(text(),'CREATE')]");//By.xpath("//button[@type='submit']");
	public final By GroupCreated_message(String GroupName){
		return By.xpath("//div[contains(text(),'Group "+GroupName+" created.')]");
	}
	
	//Edit groups
		public final By Group_AllAddedWorkflow_Icon=By.xpath("//td-chips[@placeholder='Add workflow']//div//md-basic-chip");
		public final By Group_RemoveAddedWorflow_CancelIcon(int numeber){
			return By.xpath("//td-chips[@placeholder='Add workflow']//div//md-basic-chip["+numeber+"]//md-icon[contains(text(),'cancel')]");
		}
	/*	public final By Organization_AllAddedUser_Icon=By.xpath("//td-chips[@placeholder='Add user']//div//md-basic-chip");
		public final By Organization_RemoveAddedUser_CancelIcon(int numeber){
			return By.xpath("//td-chips[@placeholder='Add user']//div//md-basic-chip["+numeber+"]//md-icon[contains(text(),'cancel')]");
		}*/
		
		//Edit groups
		public final By Group_AllAddedUser_Icon=By.xpath("//mat-chip[contains(@role,'option')]");
		public final By Group_RemoveAddedUser_CancelIcon(int numeber){
			return By.xpath("//mat-chip[contains(@role,'option')]["+numeber+"]//mat-icon[contains(text(),'cancel')]");
		}
		
		public final By Group_RemoveAddedUser_CancelIcon=By.xpath("//shared-autocomplete-chips-users//mat-chip//mat-icon[contains(@class,'remove')]");
		
		public final By SelectUserFrom_dropdown(String UserName){
			return By.xpath("//span[contains(text(),'"+UserName+"')]");
		}
		public final By SelectGroup_Checkbox(String Group_Name){
			return By.xpath("//mat-cell[text()='"+Group_Name+"']/preceding-sibling::mat-cell//mat-checkbox[contains(@id,'checkbox')]");
		}
		
		public final By searchEditIcon_button=By.xpath("//mat-icon[text()='mode_edit']");
	
	//Search Workflow	
		public final By SearchGroup_input=By.xpath("//input[@placeholder='Filter by name']");
		public final By Search_Group_Result_verify(String GroupName){
			return By.xpath("//mat-cell[text()='"+GroupName+"']");
		}
		
	//GroupNameAscending/Descending
//		public static By AscendingGroup_Name=By.xpath("//mat-table//div[//button[contains(text(),'Name')]]/following-sibling::span[contains(text(),'ascending')]");
//		public static By DescendingGroup_Name=By.xpath("//mat-table//div[//button[contains(text(),'Name')]]/following-sibling::span[contains(text(),'descending')]");
		public final By AscendingGroup_Name=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
		public final By DescendingGroup_Name=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'descending')]]");
		public final By Group_Name=By.xpath("//mat-table//div//button[contains(text(),'Name')]");	


}
