package locators;
import org.openqa.selenium.By;

public class LoginLocators {
	public static LoginLocators Login_object;
	
	 public static synchronized LoginLocators Locator() {
		  try {
		   if (Login_object.equals(null)) {
			   Login_object = new LoginLocators();
		   }
		  } catch (Exception NOSYSTEM) {
			  Login_object = new LoginLocators();
		  }
		  return Login_object;
		 }
	
	
	
	public final By username_input=By.xpath("//input[@name='username']");
	public final By Email_input=By.xpath("//input[@placeholder='Email Address']");
	public final By continue_button=By.xpath("//button[text()='Continue']");
	public final By password_input=By.xpath("//input[@placeholder='Password']");
	public final By signin_button=By.xpath("//button[text()='Sign In']");
	
	public final By forgotPassword_link=By.xpath("//form[contains(@name,'loginForm')]//a[contains(text(),'Forgot password?')]");
	public final By forgotPassword_emailId=By.xpath("//input[@placeholder='User Name / Email']");
	public final By forgotPassword_submitButton=By.xpath("//button[contains(text(),'Submit')]");
	
	public final By passwordreset_message=By.xpath("//div[contains(@class,'pseudomodal')]//p[contains(text(),'Please check your email to reset your password.')]");
	
	public final By Submission_tab=By.xpath("//md-list-item//md-icon[contains(text(),'format_list_numbered')]");
	public final By NewSubmission_link=By.xpath("//span[text()='NEW SUBMISSION']");
	
	public final By SubmissionSearch_input=By.xpath("//md-input-container//input[@type='search']");
	public final By SubmissionSearch_Button=By.xpath("//md-icon[text()='search']");
	public final By SelectTheCheckbox = By.xpath("//md-pseudo-checkbox[contains(@class,'pseudo-checkbox')]");
	public final By OpenSubmission_Icon = By.xpath("//app-main//md-card[contains(@class,'mat-card')]//md-toolbar//md-icon[@mdtooltip='Open submission'][contains(text(),'description')]");
	public final By OpenDetailSubmission_Link = By.xpath("//span[contains(text(),'OPEN')]");
	public final By Video_link = By.xpath("//md-card-content[contains(@class,'mat-card-content')]//video[contains(@id,'video')]");
	
	
	
	public final By InvalidCredentials_message = By.xpath("//div[contains(text(),'The email address or password you have entered is incorrect.')]");
	
	
	
	//Logout
	public final By  Logout_button=By.xpath("//button/..//app-user-menu-toolbar");
	public final By SignOut_link=By.xpath("//span[text()='Sign out']");
	
	public final By trans_directLogOut_button=By.xpath("//translation-toolbar//app-user-menu-toolbar//span[contains(@class,'initials')][contains(text(),'SS')]");
	public final By review_directLogOut_button=By.xpath("//review-toolbar//app-user-menu-toolbar//span[contains(@class,'initials')][contains(text(),'SS')]");
	//Gmail locators
	
	public final By gmail_userName=By.xpath("//input[contains(@aria-label,'Email or phone')]");
	public final By gmail_password=By.xpath("//input[contains(@aria-label,'Enter your password')]");
	public final By gmail_nextButton=By.xpath("//span[contains(text(),'Next')]");
	
	public final By gmail_resetPassword_mail=By.xpath("//table//tr//td//span[contains(@class,'bog')]//span[contains(text(),'Reset Password!')]");
	public final By gmail_resetPassword_link=By.xpath("//table//tr//a[contains(text(),'here')]");
	
	public final By gmail_resetPassword_link_email=By.xpath("//input[contains(@id,'Email')]");
	public final By gmail_resetPassword_link_password=By.xpath("//input[(@name='Password')]");
	public final By gmail_resetPassword_link_confirmPassword=By.xpath("//input[contains(@id,'ConfirmPassword')]");
	public final By gmail_resetPassword_link_reset=By.xpath("//button[contains(text(),'Reset')]");
	public final By gmail_resetPassword_link_clickHereToLogIn=By.xpath("//p//a[contains(text(),'Click here to log in')]");
	
	
	
}
