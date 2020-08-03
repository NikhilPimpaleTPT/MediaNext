package locators;

import org.openqa.selenium.By;

import modules.Verify;

public class UserLocators {
	private static UserLocators user_object;
	
	 public static synchronized UserLocators Locator() {
		  try {
		   if (user_object.equals(null)) {
			   user_object = new UserLocators();
		   }
		  } catch (Exception NOSYSTEM) {
			  user_object = new UserLocators();
		  }
		  return user_object;
		 }
	
	public final By AddUserbutton=By.xpath("//span[text()='NEW USER']");
	public final By username_input=By.xpath("//input[@id='username']");
	public final By type_dropdown=By.xpath("//mat-select[contains(@placeholder,'Type')]");//By.xpath("//mat-select[@name='Type']");
	public final By type_Disabled=By.xpath("//mat-select[contains(@placeholder,'Type')][contains(@class,'disabled ')]");//("//mat-select[contains(@placeholder,'Type')]//div[contains(@ng-reflect-ng-switch,'false')]");
	public final By Typeselect_dropdown(String Type){
		return By.xpath("//span[contains(text(),'"+Type+"')]");
	}
	public final By reference_input=By.xpath("//input[contains(@ng-reflect-placeholder,'Reference')]");
	public final By userpasswrod_input=By.xpath("//input[@name='password']");
	public final By firstnamename_input=By.xpath("//input[contains(@placeholder,'First Name')]");//By.xpath("//input[@name='firstName']");
	public final By lastname_input=By.xpath("//input[contains(@placeholder,'Last Name')]");//By.xpath("//input[@name='lastName']");
	public final By userfullname_input=By.xpath("//input[@name='fullname']");
	public final By PhoneName_input=By.xpath("//input[contains(@formcontrolname,'telephone')]");//By.xpath("//input[@name='tel']");
	public final By selectRole_dropdown=By.xpath("//mat-select[contains(@placeholder,'Role')]");//By.xpath("//mat-select[@name='Role']");
	
	
	public final By selectRole_value_dropdown(String role){
		return By.xpath("//mat-option[contains(@class,'mat-option')]//span[contains(text(),'"+role+"')]");
	}
	
	public final By selecttype_dropdown=By.xpath("//mat-select[@name='Type']");
	public final By selecttype_value_dropdown(String role){
		return By.xpath("//mat-option[contains(@class,'mat-option')]//span[contains(text(),'"+role+"')]");
	}
	
	public final By LoginuserName(String fullname){
		return By.xpath("//*[contains(text(),'"+fullname+"')]");
	}
	public final By sourceLanguage_search=By.xpath("//input[contains(@placeholder,'Source Language')]");
	public final By SelectSearchsourcelanguage_dropdown(String languagename){
		return By.xpath("//span[contains(text(),'"+languagename+"')]");
	}
	
	public final By targetLanguage=By.xpath("//input[contains(@placeholder,'Target Language')]");
	public final By emailid_input=By.xpath("//input[contains(@placeholder,'Email')]");//By.xpath("//input[contains(@placeholder,'email')]");//By.xpath("//input[@name='email']");
	
	public final By AddLanguage_icon=By.xpath("//mat-icon[contains(text(),'add')]");//By.xpath("//button//mat-icon[text()='check']");
	public final By Create_user_button=By.xpath("//span[contains(text(),'CREATE')]");//By.xpath("//span[contains(text(),'Create')]");
	public final By Create_user_buttonDisabel=By.xpath("//button[contains(@ng-reflect-disabled,'true')]//span[contains(text(),'CREATE')]");
	public final By Searchuser_input=By.xpath("//input[contains(@placeholder,'Filter by first name, last name or e-mail address')]");//By.xpath("//input[contains(@placeholder,'Filter by first name or last n')]");//By.xpath("//input[@placeholder='Filter by email']");
	
	public final By Searcheduser_data(String SearchBy){
		return By.xpath("//mat-cell[contains(text(),'"+SearchBy+"')]");
	}
	
	public final By Searcheduser_date(String firstName,String date){
		return By.xpath("//mat-cell[contains(text(),'"+firstName+"')]/..//mat-cell[contains(text(),'"+date+"')]");
	}
public final By searchEditIcon_button=By.xpath("//mat-icon[text()='mode_edit']");
	
	public final By SelectName_Checkbox(String Name){
		return By.xpath("//mat-cell[text()='"+Name+"']/preceding-sibling::mat-cell//mat-checkbox[contains(@id,'checkbox')]");
	}
	
	
	public final By UserAlreadyCreated_message() throws Exception{
		System.out.println("That user already exists");
		if(Verify.action().verifyElementPresent(By.xpath("//div[contains(text(),'That user already exists')]"),5))
			return By.xpath("//div[contains(text(),'That user already exists')]");
		else{
			return By.xpath("//div[contains(text(),'That user already exists')][contains(@class,'hidden')]");
		}
	}
	
	public final By UserCreated_message(String FirstName, String LastName) throws Exception{
		System.out.println("User "+FirstName+" "+LastName+" created.");
		if(Verify.action().verifyElementPresent(By.xpath("//div[contains(text(),'User "+FirstName+" "+LastName+" created.')]"),5))
			return By.xpath("//div[contains(text(),'User "+FirstName+" "+LastName+" created.')]");
		else{
			return By.xpath("//div[contains(text(),'User "+FirstName+" "+LastName+" created.')][contains(@class,'hidden')]");
		}
	}
	
	public final By Userupdated_message(String FirstName, String LastName) throws Exception{
		if(Verify.action().verifyElementPresent(By.xpath("//div[contains(text(),'User "+FirstName+" "+LastName+" updated.')]"),5))
			return By.xpath("//div[contains(text(),'User "+FirstName+" "+LastName+" updated.')]");
		else{
			return By.xpath("//div[contains(text(),'User "+FirstName+" "+LastName+" updated.)'][contains(@class,'hidden')]");
		}
	}	
	
	public final By employee_type_label_message = By.xpath("//p[contains(text(),'Emails can not be changed. Please create a new user if needed.')]");
	
	public final By employee_type_label_Helpdeskmessage=By.xpath("//p[contains(text(),'Please use the same email than the one added by Helpdesk to \"GL-Subtitler_Production\" group.')]");
	//DeleteRole
		public final By DeleteUser_icon=By.xpath("//mat-icon[text()='delete']");
		public final By DeleteUser_Alert_button=By.xpath("//span[text()='DELETE']");
		public final By DeleteUser_message=By.xpath("//div[text()='Organization delete']");
		
  //UserNameAscending/Descending
		public final By Ascending_Email=By.xpath("//mat-table//div[button[contains(text(),'Email')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
		public final By Descending_Email=By.xpath("//mat-table//div[button[contains(text(),'Email')]]//div[../following-sibling::span[contains(text(),'descending')]]");
		public final By Email=By.xpath("//mat-table//div//button[contains(text(),'Email')]");	

		public final By CreateUserName_WhitespaceError=By.xpath("//mat-error[contains(@id,'mat-error')]//mat-error[contains(text(),'No whitespace allowed at the beginning/end neither consecutively.')]");	
	
}
