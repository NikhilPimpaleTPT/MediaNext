package locators;

import org.openqa.selenium.By;

public class Admin_User_Submission_Locators {
	
	private static Admin_User_Submission_Locators admin_objects;
	
	 public static synchronized Admin_User_Submission_Locators Locator() {
		  try {
		   if (admin_objects.equals(null)) {
			   admin_objects = new Admin_User_Submission_Locators();
		   }
		  } catch (Exception NOSYSTEM) {
			  admin_objects = new Admin_User_Submission_Locators();
		  }
		  return admin_objects;
		 }
	
	public final By admin_User_New_Submission_Button=By.xpath("//button[contains(@id,'new-submission')]");
	public final By admin_user_Search_Submission_input=By.xpath("//input[contains(@placeholder,'Filter by name')]");//By.xpath("//input[@placeholder='Filter by name']");
	public final By admin_user_CreateSubmission_Readingspeed_input=By.xpath("//input[@placeholder='Reading Speed']");
	public final By admin_user_CreateSubmission_MaxCharPerLine_input=By.xpath("//input[@placeholder='Max chars per line']");
	public final By admin_user_CreateSubmission_MinDuration_input=By.xpath("//input[@placeholder='Min duration vs. reading speed']");
	public final By admin_user_CreateSubmission_MaxDuration_input=By.xpath("//input[@placeholder='Max duration vs. reading speed']");
	public final By admin_user_CreateSubmission_SafeArea_Input=By.xpath("//input[@placeholder='Safe Area']");
	public final By admin_user_CreateSubmission_Font_input=By.xpath("//input[@placeholder='Font']");
	public final By admin_user_CreateSubmission_Font_Size_input=By.xpath("//input[@placeholder='Font size']");
	public final By admin_user_CreateSubmission_VideoFile_Button=By.xpath("//td-file-input//mat-icon[contains(text(),'attach_file')]");
	public final By admin_user_CreateSubmission_VideoFile_input=By.xpath("//input[contains(@accept,'mp4')]");
	public final By admin_user_CreateSubmission_SourceFile_Button=By.xpath("//td-file-input//span[contains(text(),'Source (TTML, DFXP or SRT) file')]");
	public final By admin_user_CreateSubmission_Step2_OrganizationAndWorkflow_Button=By.xpath("//md-card-content//td-steps//div[2][@class='td-step']//div[contains(@class,'md-body')]");
	public final By admin_user_CreateSubmission_Step1Next_Button=By.xpath("//span[contains(text(),'NEXT')]");
	public final By admin_user_CreateSubmission_Next_Button=By.xpath("//div[contains(@fxlayoutalign,'stretch')]/following-sibling::button//span[contains(text(),'NEXT')]");
	public final By admin_user_CreateSubmission_SelectOrganization_dropdown=By.xpath("//mat-select[@name='Organization']");///following-sibling::span//label[contains(text(),'Organization')]
	public final By admin_user_CreateSubmission_SelectOrganizationFrom_dropdown(String OrganizationName){
		return By.xpath("//mat-option[@role='option']//span[contains(text(),'"+OrganizationName+"')]");
	}
	public final By admin_user_CreateSubmission_SelectWorkflow_dropdown=By.xpath("//mat-select[@name='Workflow']");
	public final By admin_user_CreateSubmission_SelectWorkflow_dropdown(String WorkflowName){
		return By.xpath("//mat-option[@role='option']//span[contains(text(),'"+WorkflowName+"')]");
	}
	
	public final By admin_user_CreateSubmission_AddGroup_Review_input= By.xpath("//h5[contains(text(),'review')]/..//following-sibling::div//input[@placeholder='Add Group']");
	public final By admin_user_CreateSubmission_AddGroup_Trans_input= By.xpath("//h5[contains(text(),'trans')]/..//following-sibling::div//input[@placeholder='Add Group']");
	
	
	public final By admin_user_CreateSubmission_AddGroupValue_Dropdown(String groupName){
		return By.xpath("//span[text()='"+groupName+"']");
	}	
		public final By admin_user_CreateSubmission_DueDate_input=By.xpath("//input[contains(@placeholder,'Due date')]");//By.xpath("//input[@id='dueDatePicker']");	
		public final By admin_user_CreateSubmission_Name_input=By.xpath("//input[contains(@placeholder,'Name')]");//By.xpath("//input[@placeholder='Name']");
		public final By admin_user_CreateSubmission_SourceLanguage_input=By.xpath("//input[@placeholder='Source Language']");//By.xpath("//input[contains(@placeholder,'Source Language')]/../..");
		public final By admin_user_CreateSubmission_TargetLanguage_input=By.xpath("//input[contains(@placeholder,'Target languages')]");//By.xpath("//input[contains(@placeholder,'Target Languages')]");
		
		public final By admin_user_CreateSubmission_SourceLanguageFrom_dropdown(String LanguageName){
			return By.xpath("//mat-option[@role='option']//span[contains(text(),'"+LanguageName+"')]");
			
		}
		
		public final By admin_user_CreateSubmission_TargetLanguageFrom_dropdown(String LanguageName){
	       return By.xpath("//mat-option[@role='option']//span[contains(text(),'"+LanguageName+"')]");
			//return By.xpath("//span[contains(text(),'"+LanguageName+"')]");
		}
		
		public final By admin_user_CreateSubmission_Create_Button=By.xpath("//span[contains(text(),'CREATE SUBMISSION')]");//By.xpath("//span[contains(text(),'Create submission')]");//By.xpath("//span[contains(text(),'CREATE')]");
	

	//Search
		public final By admin_user_SearchResult_worklfowName(String WorkflowName){
			return By.xpath("//mat-cell[contains(text(),'"+WorkflowName+"')]");
		}
		
		public final By admin_user_SearchResult(String SubmissionName){
			return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]");
		}
		//Assign Submission	
				public final By admin_clamSubmission_icon=By.xpath("//mat-icon[@mattooltip='Claim task']");//("//md-icon[@mdtooltip='Claim task']");
				
				public final By admin_claimAlert_claim_button=By.xpath("//span[contains(text(),'CLAIM')]");
				
			
		
		public final By admin_FirstRowAssertion(String Name){
			return By.xpath("//mat-table//mat-row[1]//mat-cell[2][contains(text(),'"+Name+"')]");
		}
		
		public final By admin_toClaim_checkbox_checked=By.xpath("//mat-header-cell//mat-checkbox[contains(@class,'checkbox-checked')]");
		//select header checkbox in trans toClaim tab
		public final By admin_toClaim_select_header_checkbox=By.xpath("//mat-header-cell//mat-checkbox[contains(@id,'checkbox')]");
		
		//Select Submission checkbox
		public final By SelectSubmission_Checkbox(String Submission_Name){
			return By.xpath("//mat-cell//span[contains(text(),'"+Submission_Name+"')]/../preceding-sibling::mat-cell//mat-checkbox[contains(@id,'checkbox')]");

			}
		
		//delete submission
		public final By DeleteSubmission_Icon=By.xpath("//mat-icon[text()='delete']");
		public final By DeleteSubmission_Alert_button=By.xpath("//span[contains(text(),'DELETE SUBMISSION')]");
		public final By DeleteSubmission_Alert_Cancel_button=By.xpath("//span[contains(text(),'CANCEL')]");
		
		//delete submission second confirmation box "Delete also jobs content?"
		public final By DeleteJobs_Second_confirmation_Alert_button=By.xpath("//span[contains(text(),'DELETE JOBS')]");
		public final By DeleteJobs_Second_confirmation_Alertt_Cancel_button=By.xpath("//span[contains(text(),'CANCEL')]");
		
		//Select status of Submission in submissions tab with row number
		public final By check_submission_row_status(int row,String SubmissionName, String status){
			return By.xpath("//mat-row["+row+"]//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+status+"')]");

			}
		//Select status of Submission in submissions tab 
				public final By check_submission_status(String SubmissionName, String status){
					return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+status+"')]");

					}
				
				
				//sorting Ascending/descending
				//public final By Ascending_sort=By.xpath("//mat-table//div[button[contains(text(),'"+colunmName+"')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
			
				public final By Ascending_sort(String colunmName){
					return By.xpath("//mat-table//div[button[contains(text(),'"+colunmName+"')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
}
				//public final By Descending_sort=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'descending')]]");
				public final By descending_sort(String colunmName){
					return By.xpath("//mat-table//div[button[contains(text(),'"+colunmName+"')]]//div[../following-sibling::span[contains(text(),'descending')]]");
}
				//public final By colunm_Name=By.xpath("//mat-table//div//button[contains(text(),'"+colunmname+"')]");
				
				public final By colunm_Name(String colunmName){
					return By.xpath("//mat-table//div//button[contains(text(),'"+colunmName+"')]");
				}
	public final By Export_Csv_button = By.xpath("//button[contains(@mattooltip,'Export CSV')]");
	public final By Totla_EntriesOfSubmission = By.xpath("//mat-paginator[contains(@class,'mat-paginator')]");
	
	public final By Show_Complete_Sub_button= By.xpath("//mat-slide-toggle[contains(@class, 'mat-accent')]");
		
		 //TODO THESE ARE COMMON LOCATORS FOR ADMIN AND PM LOCATORS
/*		//Search Submission in Translator user
		 public final By translator_search_input=By.xpath("//input[@placeholder='Filter by name']");
		 
			public final By translatorSearchResult(String SubmissionName){
				return By.xpath("//mat-cell[contains(text(),'"+SubmissionName+"')]");
			}
			
			//select header checkbox in trans toClaim tab
			public final By trans_toClaim_select_header_checkbox=By.xpath("//mat-header-cell//mat-checkbox[contains(@id,'checkbox')]");
			
			public final By trans_toClaim_checkbox_checked=By.xpath("//mat-header-cell//mat-checkbox[contains(@class,'checkbox-checked')]");
			
			//Claim  Submission	in Translator user
			
			public final By translator_selectSubmission_checkbox_1(String SubmissionName){
				return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
			}
			
				public final By translator_clamSubmission_icon=By.xpath("//mat-icon[@mattooltip='Claim task']");
				
				public final By translator_selectSubmission_checkbox(String SubmissionName,String target){
					//return By.xpath("//mat-cell[contains(text(),'"+SubmissionName+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
					return By.xpath("//mat-cell[contains(text(),'"+SubmissionName+"')]/following-sibling::mat-cell[contains(text(),'"+target+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
				}
				public final By translator_claimAlert_claim_button=By.xpath("//span[contains(text(),'CLAIM')]");
				
				//Ongoing Submission in Translator User
			    public final By translator_toClaim_ListofallIds=By.xpath("//mat-list-item[contains(@class,'separator mat-list-item ng-star-inserted')]");//NOTE:- work on that locator.
				public final By Translator_Submission_Edit_icon=By.xpath("//mat-icon[@mattooltip='Edit']");
				public final By translator_Complete_Button=By.xpath("//mat-icon[contains(text(),'check')]");
				public final By translator_CompleteDailoge_Button=By.xpath("//mat-dialog-container//mat-dialog-actions//button//span[text()='COMPLETE TASK']");
				
				public final By translator_CopySource_toTarget_Button=By.xpath("//button[contains(@mattooltip,'Copy source to target')]");
				
				public final By translator_TargetSegement_textarea(int index){
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]");
				}
				
			
				
				//Select Target_Lang of Submission in trans_Ongoing tab
				public final By check_trans_Target_Lang(String SubmissionName, String target){
					return By.xpath("//mat-cell[contains(text(),'"+SubmissionName+"')]/following-sibling::mat-cell[contains(text(),'"+target+"')]");

					}	
				*/
	
	
	
	public final By user_exportCSV =By.xpath("//shared-export-csv//mat-icon[contains(text(),'content_paste')]");
	

}




