package locators;

import modules.Verify;

import org.openqa.selenium.By;

public class Pm_User_Submission_Locators {
	private static Pm_User_Submission_Locators pm_objects;
	
	 public static synchronized Pm_User_Submission_Locators Locator() {
		  try {
		   if (pm_objects.equals(null)) {
			   pm_objects = new Pm_User_Submission_Locators();
		   }
		  } catch (Exception NOSYSTEM) {
			  pm_objects = new Pm_User_Submission_Locators();
		  }
		  return pm_objects;
		 }
	
	public final By Pm_User_New_Submission_Button=By.xpath("//button[contains(@id,'new-submission')]");
	public final By Pm_user_Search_Submission_input=By.xpath("//input[contains(@placeholder,'Filter by name')]");
    public final By Pm_user_Search_Submission_allCheck=By.xpath("//mat-header-row//mat-header-cell//mat-checkbox[contains(@id,'checkbox')]");
	public final By Pm_user_CreateSubmission_Readingspeed_input=By.xpath("//input[@placeholder='Max reading speed']");//By.xpath("//input[@placeholder='Reading Speed']");
	public final By Pm_user_CreateSubmission_Minreadingspeed=By.xpath("//input[contains(@placeholder,'Min reading speed')]");//By.xpath("//input[@placeholder='Min reading speed (%)']");
	public final By Pm_user_Createsubmission_Maxreadingspeed=By.xpath("//input[contains(@placeholder,'Max reading speed')]");//By.xpath("//input[@placeholder='Max reading speed (%)']");
	public final By Pm_user_CreateSubmission_MaxCharPerLine_input=By.xpath("//input[@placeholder='Max chars per line']");
	public final By pm_user_CreateSubmission_MaxLinesPerSub_input=By.xpath("//input[@placeholder='Max lines per sub']");
	public final By Pm_user_CreateSubmission_MinDuration_input=By.xpath("//input[contains(@placeholder,'Min duration of a sub (sec)')]");//By.xpath("//input[contains(@placeholder,'Min duration vs. reading speed')]");//By.xpath("//input[@placeholder='Min duration vs. reading speed']");
	public final By Pm_user_CreateSubmission_MaxDuration_input=By.xpath("//input[contains(@placeholder,'Max duration of a sub (sec)')]");//By.xpath("//input[contains(@placeholder,'Max duration vs. reading speed')]");//By.xpath("//input[@placeholder='Max duration vs. reading speed']");
	public final By Pm_user_CreateSubmission_MinCountOfFrames_input=By.xpath("//input[contains(@placeholder,'Min count of frames between subs')]");
	public final By toolbarpanel_submission_label=By.xpath("//mat-toolbar//span[contains(text(),'Submissions')]");
	public final By Pm_user_CreateSubmission_createLabel=By.xpath("//h2[contains(text(),'Create')]");
	
	
	
	//Snap to Shot changes
	public final By Pm_user_CreateSubmission_SnapToShotChanges_checked=By.xpath("//mat-checkbox[@formcontrolname='isSnapToShot'][contains(@class,'mat-checkbox-checked')]");
	public final By Pm_user_CreateSubmission_SnapToShotChanges_unchecked=By.xpath("//mat-checkbox[@formcontrolname='isSnapToShot'][not(contains(@class,'mat-checkbox-checked'))]");
	public final By Pm_user_CreateSubmission_SnapInCuesAfterShotChange_checked=By.xpath("//mat-checkbox[@formcontrolname='snapInCue'][contains(@class,'mat-checkbox-checked')]");
	public final By Pm_user_CreateSubmission_SnapOutCuesBeforeShotChange_checked=By.xpath("//mat-checkbox[@formcontrolname='snapOutCue'][contains(@class,'mat-checkbox-checked')]");
	public final By Pm_user_CreateSubmission_SnapToShot_Threshold_input=By.xpath("//input[contains(@placeholder,'Threshold (frames)')]");
	public final By Pm_user_CreateSubmission_SnapToShot_Gap_input=By.xpath("//input[contains(@placeholder,'Gap (frames)')]");
	public final By Pm_user_CreateSubmission_SnapToShot_SnapBeforeShotChange_input=By.xpath("//input[contains(@placeholder,'Snap before shot change')]");
	public final By Pm_user_CreateSubmission_SnapToShotChanges_error(String error) {
		return By.xpath("//submission-wizard-media-data-form//mat-error[contains(text(),'"+error+"')]");
	}
	
	
	public final By Pm_user_EditSubmission_SnapToShotChanges_checked=By.xpath("//mat-checkbox[@formcontrolname='isSnapToShot'][contains(@class,'mat-checkbox-checked')]");
	public final By Pm_user_EditSubmission_SnapInCuesAfterShotChange_checked=By.xpath("//mat-checkbox[@formcontrolname='snapInCue'][contains(@class,'mat-checkbox-checked')]");
	public final By Pm_user_EditSubmission_SnapOutCuesBeforeShotChange_checked=By.xpath("//mat-checkbox[@formcontrolname='snapOutCue'][contains(@class,'mat-checkbox-checked')]");
	public final By Pm_user_EditSubmission_SnapToShot_Threshold_input=By.xpath("//input[contains(@placeholder,'Threshold (frames)')]");
	public final By Pm_user_EditSubmission_SnapToShot_Gap_input=By.xpath("//input[contains(@placeholder,'Gap (frames)')]");
	public final By Pm_user_EditSubmission_SnapToShot_SnapBeforeShotChange_input=By.xpath("//input[contains(@placeholder,'Snap before shot change')]");
	public final By Pm_user_EditSubmission_SnapToShotChanges_error(String error) {
		return By.xpath("//jobs-settings//mat-error[contains(text(),'"+error+"')]");
	}
	
	public final By PM_transcription_editSubmission_settings_updateButton_disabled=By.xpath("//mat-card-actions//button[@disabled='true']//span[contains(text(),'UPDATE')]");

	
	
	//Reeding Speed and duration locators for PM transcription
	public final By PM_transcription_editSubmission_MaxLinesPerSub_input=By.xpath("//input[@placeholder='Max lines per subtitle']");
	public final By PM_transcription_editSubmission_MaxCharPerLine_input=By.xpath("//input[@placeholder='Max chars per line']");
	public final By PM_transcription_editSubmission_settings_updateButton=By.xpath("//mat-card-actions//button//span[contains(text(),'UPDATE')]");
	public final By PM_transcription_editSubmission_settings_updateButton_color=By.xpath("//jobs-settings//mat-card-actions//button[2]");
	
	
	public final By PM_transcription_editSubmission_settings_updateMessage=By.xpath("//simple-snack-bar//span[contains(text(),'Job German (Germany) updated.')]");
	
	// Error Massage for Reading Speed
	
	
	public final By Pm_user_CreateSubmission_ErrorMsgForReadingSpeedAndDuration(String errorMassage ) {
		return By.xpath("//mat-error[contains(text(),'"+errorMassage+"')]");
	}
	public final By Pm_user_CreateSubmission_ErrorMsgForMaxLinesPerSub(String errorMassage ) {
		return By.xpath("//mat-error[contains(text(),'"+errorMassage+"')]");
	}
	
	public final By Pm_user_CreateSubmission_SafeArea_Input=By.xpath("//input[@placeholder='Safe Area']");
	public final By Pm_user_CreateSubmission_Font_input=By.xpath("//input[@placeholder='Font']");
	public final By Pm_user_CreateSubmission_Font_Size_input=By.xpath("//input[@placeholder='Font size']");
	public final By Pm_user_CreateSubmission_VideoFile_Button=By.xpath("//td-file-input//mat-icon[contains(text(),'attach_file')]");
	public final By Pm_user_CreateSubmission_VideoFile_input=By.xpath("//input[contains(@accept,'mp4')]");
	public final By Pm_user_CreateSubmission_SourceFile_Button=By.xpath("//td-file-input//span[contains(text(),'Source (TTML, DFXP or SRT) file')]");
	public final By Pm_user_CreateSubmission_Step2_OrganizationAndWorkflow_Button=By.xpath("//md-card-content//td-steps//div[2][@class='td-step']//div[contains(@class,'md-body')]");
	
	public final By Pm_user_createSubmission_videoUpload_input=By.xpath("//shared-input-file[contains(@placeholder,'OST (Optional)')]//input/..");
	public final By Pm_user_createSubmission_sourceUpload_input=By.xpath("//shared-input-file[contains(@placeholder,'Source')]//input");
	
	
	public final By Pm_user_createSubmission_videoUpload=By.xpath("//shared-input-file[contains(@placeholder,'OST (Optional)')]");
	public final By Pm_user_createSubmission_sourceUpload=By.xpath("//shared-input-file[contains(@placeholder,'Source')]//input/..");
	
	
	
	public final By header_toolbar_submissionName(String submissionName){
		return By.xpath("//mat-sidenav-content//mat-toolbar//span[contains(text(),'"+submissionName+"')]");
		
	}
	
	public final By Pm_user_createSubmission_videoUpload_filePresent(String fileName){
		return By.xpath("//shared-input-file[contains(@placeholder,'Video')]//span[contains(text(),'"+fileName+"')]");
		
	}
	
	public final By Pm_user_createSubmission_sourceUpload_filePresent(String fileName){
		return By.xpath("//shared-input-file[contains(@placeholder,'Source')]//span[contains(text(),'"+fileName+"')]");
		
	}
	
	public final By Pm_user_CreateSubmission_ErrorMsg_ForSubmissionNameWithSpaces=By.xpath("//mat-error//mat-error[contains(text(),' Spaces at the begining')]");
	
	public final By Pm_user_CreateSubmission_DisableCreateSubmission=By.xpath("//mat-accordion/div//button[span[contains(text(),'CREATE SUBMISSION')]][contains(@disabled,'true')]");//("//mat-accordion/div//button[span[contains(text(),'CREATE SUBMISSION')]][contains(@ng-reflect-disabled,'true')]");
	
	public final By Pm_user_createSubmission_uploadVideo_crossButton=By.xpath("//mat-form-field//div[shared-input-file[contains(@placeholder,'Video')]]/following-sibling::div//mat-icon[contains(text(),'cancel')]");
	public final By Pm_user_createSubmission_uploadSource_crossButton=By.xpath("//mat-form-field//div[shared-input-file[contains(@placeholder,'Source')]]/following-sibling::div//mat-icon[contains(text(),'cancel')]");
	public final By Pm_user_createSubmission_Video_crossButton = By.xpath("//mat-form-field//div//mat-icon");
	
	public final By Pm_user_createSubmission_ostUpload_input=By.xpath("//shared-input-file[contains(@placeholder,'OST (Optional)')]//div//input");

	
	public final By Pm_user_CreateSubmission_Step1Next_Button=By.xpath("//span[contains(text(),'NEXT')]");
	public final By Pm_user_CreateSubmission_step1_Next_Button=By.xpath("//app-create-submission-native-dialog//mat-expansion-panel[contains(@class,'mat-expanded')]//span[contains(text(),'Next')]");//("//app-create-submission-dialog//mat-expansion-panel[contains(@class,'mat-expanded')]//span[contains(text(),'Next')]");
	public final By Pm_user_CloneSubmission_Next_Button=By.xpath("//app-clone-submission-native//mat-expansion-panel[contains(@class,'mat-expanded')]//span[contains(text(),'Next')]");
	
	public final By Pm_user_CreateSubmission_SelectFromComputer_RadioButton=By.xpath("//mat-radio-button//div[contains(text(),'Select from computer')]");
	
	//PD MT locators
	
	public final By Pm_user_CreateSubmission_pdmt_instance=By.xpath("//pd-panel-form//mat-form-field//mat-select[contains(@placeholder,'Choose an instance')]");
	public final By Pm_user_CreateSubmission_pdmt_instanceDropDownOptions(String instance) {
		return By.xpath("//mat-option[contains(@id,'mat-option')]//span[contains(text(),'"+instance+"')]");
	}
	public final By Pm_user_CreateSubmission_pdmt_department=By.xpath("//pd-panel-form//mat-form-field//mat-select[contains(@placeholder,'Choose a department')]");
	public final By Pm_user_CreateSubmission_pdmt_departmentDropDownOptions(String department) {
		return By.xpath("//mat-option[contains(@id,'mat-option')]//span[contains(text(),'"+department+"')]");
	}
	
	public final By Pm_user_CreateSubmission_pdmt_workflow=By.xpath("//pd-panel-form//mat-form-field//mat-select[contains(@placeholder,'Choose a workflow')]");
	public final By Pm_user_CreateSubmission_pdmt_workflowDropDownOptions(String workflow) {
		return By.xpath("//mat-option[contains(@id,'mat-option')]//span[contains(text(),'"+workflow+"')]");
	}
	
	public final By Pm_user_CreateSubmission_pdmt_client=By.xpath("//pd-panel-form//mat-form-field//input[contains(@placeholder,'Type a Project A Client')]");
	
	public final By Pm_user_CreateSubmission_pdmt_nextButton=By.xpath("//app-create-submission-native-dialog//mat-expansion-panel[contains(@class,'mat-expanded')]//span[contains(text(),'Next')]");
	
	//For Search In catalog Radio Button
	public final By Pm_user_CreateSubmission_searchInMediaLibrary_RadioButton=By.xpath("//mat-radio-button//div[contains(text(),'Search in Media Library')]");;//By.xpath("//mat-radio-button//div[contains(text(),'Search in catalog')]");
	
	public final By Pm_user_CreateSubmission_searchInMediaLibrary_Owner=By.xpath("//input[contains(@placeholder,'Owner')]");
	public final By Pm_user_CreateSubmission_searchInMediaLibrary_AssetID=By.xpath("//input[contains(@placeholder,'Asset ID')]");
	public final By Pm_user_CreateSubmission_searchInMediaLibrary_Title=By.xpath("//input[contains(@placeholder,'Title')]");
	
	public final By Pm_user_CreateSubmission_searchInMediaLibrary_HeaderOptions(String HeaderOpetions ) {
		return By.xpath("//table//tr//div//button[contains(text(),'"+HeaderOpetions+"')]");
	}
	
	public final By Pm_user_CreateSubmission_searchInMediaLibrary_fileFirstRow(String assetid ) {
		return By.xpath("//table[contains(@class,'mat-table')]//tr//td[contains(text(),'"+assetid+"')]");
	}
	public final By Pm_user_CreateSubmission_searchInMediaLibrary_Header_Owener=By.xpath("//table//tr//div//button[contains(text(),'Owner')]");
	public final By Pm_user_CreateSubmission_searchInMediaLibrary_OwnerAsc=By.xpath("//th//div//button[contains(text(),'Owner')]//ancestor::th[contains(@aria-sort,'ascending')]");
	public final By Pm_user_CreateSubmission_searchInMediaLibrary_OwnerDsc=By.xpath("//th//div//button[contains(text(),'Owner')]//ancestor::th[contains(@aria-sort,'descending')]");
	public final By searchInMediaLibrary=By.xpath("//th//div//button[contains(text(),'Owner')]//ancestor::th[contains(@aria-sort,'descending')]");
    public final By Pm_user_CreateSubmission_searchInMediaLibrary_SearchBtn=By.xpath("//button//span[contains(text(),'Search')]");
    
    
    //Speech to text
    public final By Pm_user_CreateSubmission_materialSelection_speechToText_button=By.xpath("//app-media-assets-table//button//span[text()='Speech to Text']");
    public final By Pm_user_CreateSubmission_materialSelection_speechToText_dataSettings(int row,String data) {
    	return By.xpath("//mat-dialog-content//mat-list-item["+row+"]//h4[contains(text(),'"+data+"')]");
    }
    public final By Pm_user_CreateSubmission_materialSelection_speechToText_generateButton=By.xpath("//mat-dialog-actions//button//span[text()='GENERATE']");
    public final By Pm_user_CreateSubmission_materialSelection_speechToText_cancelButton=By.xpath("//mat-dialog-actions//button//span[text()='CANCEL']");
    
    public final By Pm_user_CreateSubmission_materialSelection_speechToText_generate_error(String error) {
    	return By.xpath("//simple-snack-bar//span[contains(text(),'"+error+"')]");
    }
    
    
  
    
    //IT WILL GIVE COUNT OF ITEMS PER PAGE 
    public final By Pm_user_CreateSubmission_AllRow=By.xpath("//table[contains(@class,'mat-table')]//tbody//tr");
    
    public final By Pm_user_createSubmission_searchCatalog_videoFile(int index) {
    	return By.xpath("//tr["+index+"][contains(@class,'mat-row')]//td");////tr["+index+"]//td
    }
    
    public final By Pm_user_CreateSubmission_Searchincatalog_NoVideoAttachedMassage=By.xpath("//snack-bar-container");
    
    
    public final By Pm_user_CreateSubmission_SearchFromCatalog_ItemPerPage=By.xpath("//mat-select[contains(@aria-label,'Items per page:')]");
    
    public final By Pm_user_CreateSubmission_SearchFromCatalog_ItemPerPage_Value(int itemPerPage) {
    	return By.xpath("//mat-option//span[contains(text(),'"+itemPerPage+"')]");
    }
    
	public final By Pm_user_CreateSubmission_Next_Button=By.xpath("//div[contains(@fxlayoutalign,'stretch')]/following-sibling::button//span[contains(text(),'NEXT')]");
	public final By Pm_user_CreateSubmission_SelectOrganization_dropdown=By.xpath("//mat-select[contains(@formcontrolname,'organization')]");//("//mat-select[contains(@ng-reflect-name,'organization')]");//By.xpath("//mat-select[@name='Organization']");///following-sibling::span//label[contains(text(),'Organization')]
	public final By Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(String OrganizationName){
		return By.xpath("//mat-option[@role='option']//span[contains(text(),'"+OrganizationName+"')]");
	}
	public final By Pm_user_CreateSubmission_SelectOrganization=By.xpath("//mat-select[contains(@formcontrolname,'organization')]//span//span[1]");
	public final By Pm_user_CreateSubmission_SelectWorkflow_label=By.xpath("//mat-panel-title[contains(text(),'Client & Workflow')]");
	public final By Pm_user_CreateSubmission_SelectWorkflow_dropdown=By.xpath("//mat-select[contains(@formcontrolname,'workflow')][contains(@placeholder,'Choose one workflow')]");//("//mat-select[contains(@ng-reflect-name,'workflow')][contains(@placeholder,'Choose one workflow')]");//By.xpath("//mat-select[contains(@ng-reflect-name,'workflow')]");//By.xpath("//mat-select[@name='Workflow']");
	public final By Pm_user_CreateSubmission_SelectWorkflow_dropdown(String WorkflowName){
		return By.xpath("//mat-option[@role='option']//span[contains(text(),'"+WorkflowName+"')]");
	}
	
	public final By Pm_user_CreateSubmission_AddGroup_Review_input= By.xpath("//h5[contains(text(),'review')]/..//following-sibling::div//input[@placeholder='Add Group']");
	public final By Pm_user_CreateSubmission_AddGroup_Trans_input= By.xpath("//h5[contains(text(),'trans')]/..//following-sibling::div//input[@placeholder='Add Group']");
	
	public final By Pm_user_CreateSubmission_AddGroup_select(String stepName){
		return By.xpath("//h5[contains(text(),'"+stepName+"')]/..//following-sibling::mat-form-field//mat-select[contains(@formcontrolname,'group')]");
	}
	
	
	public final By Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_trans(String groupName){
		return By.xpath("//mat-option//span[contains(text(),'"+groupName+"')]");//By.xpath("//span[text()='"+groupName+"']");
	}	
	
	public final By Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_review(String groupName){
		//return By.xpath("//mat-list[contains(@class,'mat-list')]//mat-list-item//div[h5[contains(text(),'"+phase+"')]]/following-sibling::div//input");
		return By.xpath("//mat-option//span[contains(text(),'"+groupName+"')]");//By.xpath("//span[text()='"+groupName+"']");By.xpath("//div[contains(@id,'cdk-overlay')]//span[contains(text(),'"+groupName+"')]");//("//h5[contains(text(),'review')]/..//following-sibling::div//div//span[contains(text(),'"+groupName+"')]");
	}	
	
	public final By Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_transc(String groupName){
		//return By.xpath("//mat-list[contains(@class,'mat-list')]//mat-list-item//div[h5[contains(text(),'"+phase+"')]]/following-sibling::div//input");
		return By.xpath("//mat-option//span[contains(text(),'"+groupName+"')]");//By.xpath("//span[text()='"+groupName+"']");By.xpath("//div[contains(@id,'cdk-overlay')]//span[contains(text(),'"+groupName+"')]");//("//h5[contains(text(),'review')]/..//following-sibling::div//div//span[contains(text(),'"+groupName+"')]");
	}	
	
        public final By Pm_user_CreateSubmission_DueDate_input=By.xpath("//input[contains(@placeholder,'Due date')]");//By.xpath("//input[@id='dueDatePicker']");	
		public final By Pm_user_CreateSubmission_Name_input=By.xpath("//input[contains(@placeholder,'Name')]");//By.xpath("//input[@placeholder='Name']");
		public final By Pm_user_CreateSubmission_SourceLanguage_input=By.xpath("//input[@placeholder='Source Language']");//By.xpath("//input[contains(@placeholder,'Source Language')]/../..");
		public final By Pm_user_CreateSubmission_TargetLanguage_input=By.xpath("//input[contains(@placeholder,'Target languages')]");//By.xpath("//input[contains(@placeholder,'Target Languages')]");
		public final By Pm_user_date_description=By.xpath("//mat-icon[contains(text(),'description')]");
		public final By Pm_user_CreateSubmission_DueDate_today=By.xpath("//div[contains(@class,'calendar-body-today')]");
		public final By Pm_user_CreateSubmission_DueTime_input=By.xpath("//input[contains(@placeholder,'Due time')]");
		
		public final By m_user_CreateSubmission_SourceLanguage_selectedValue=By.xpath("//submission-wizard-submission-details-form//app-autocomplete-single-chip//mat-chip");
		public final By Pm_user_CreateSubmission_TargetLanguage_selectedValue=By.xpath("//submission-wizard-submission-details-form//app-autocomplete-multi-chip//mat-chip");
		//For multi Language

		public final By Pm_user_AllJobs_SubmissionName_MultiLanguage(String SubmissionName,String  language ,String assignee) {
			return By.xpath("//mat-table//mat-row//mat-cell//span[contains(text(),'"+SubmissionName+"')]//ancestor::mat-row//mat-cell[contains(text(),'"+language+"')]//ancestor::mat-row//mat-cell//span[contains(text(),'"+assignee+"')]");
		}
		
		public final By Pm_user_CreateSubmission_DueDate_arrow=By.xpath("//button[contains(@class,'period-button')]//div[contains(@class,'arrow')]");
		public final By Pm_user_CreateSubmission_DueDate_select_year=By.xpath("//mat-multi-year-view//table//td//div[contains(@class,'calendar-body-today')]");
		public final By Pm_user_CreateSubmission_DueDate_select_month=By.xpath("//mat-year-view//table//td//div[contains(@class,'calendar-body-today')]");
		public final By Pm_user_CreateSubmission_DueDate_select_date(String date){
			return By.xpath("//mat-month-view//table//td//div[contains(@class,'calendar-body')][contains(text(),'"+date+"')]");
		}
		
		public final By Pm_user_CreateSubmission_DueDate_error=By.xpath("//mat-error[contains(text(),'Invalid time, date has passed.')]");
		
		public final By Pm_user_CreateSubmission_DueDate_select_month(String month){
			return By.xpath("//mat-year-view//table//td//div[contains(@class,'body-cell-content')][contains(text(),'"+month+"')]");
		}

		public final By Pm_user_CreateSubmission_DueDate_select_year(String year){
			return By.xpath("//mat-multi-year-view//table//td//div[contains(@class,'body-cell-content')][contains(text(),'"+year+"')]");
		}

		public final By Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(String LanguageName){
			return By.xpath("//mat-option[@role='option']//span[contains(text(),'"+LanguageName+"')]");
		}
		
		public final By PM_user_CreateSubmission_SourceLanguage_cancelButton(String LanguageName) {
			return By.xpath("//mat-chip[contains(text(),'"+LanguageName+"')]//mat-icon[contains(text(),'cancel')]");
		}
		public final By Pm_user_CreateSubmission_Create_Button=By.xpath("//span[contains(text(),'CREATE SUBMISSION')]");//By.xpath("//span[contains(text(),'Create submission')]");//By.xpath("//span[contains(text(),'CREATE')]");
		
		public final By Pm_user_CreateSubmission_Create_invalidFileVideoMessage=By.xpath("//simple-snack-bar//span[contains(text(),'Error parsing video stream URL. Please make sure to use a video that can be played in the platform.')]");
		
		public final By Pm_user_CreateSubmission_Create_error(String error) {
			return By.xpath("//simple-snack-bar//span[contains(text(),'"+error+"')]");
		}
	//Search
		public final By Pm_user_SearchResult(String SubmissionName){
			return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]");
				}
		public final By pmUser_AllJobs_showComplete_toggleButton_off=By.xpath("//mat-slide-toggle[not(contains(@class,'mat-checked'))]");
		public final By pmUser_AllJobs_showComplete_toggleButton_on=By.xpath("//mat-slide-toggle[contains(@class,'mat-checked')]");

		public final By Pm_user_AllJobs_status_header_Button=By.xpath("//button[contains(text(),'Status(Step)')]");

		//Search
			public final By Pm_user_SearchResult_withRow(String SubmissionName, int rownum){
				return By.xpath("//mat-row["+rownum+"]//mat-cell//span[contains(text(),'"+SubmissionName+"')]");
					}
		    public final By Pm_FirstRowAssertion(String Name){
			return By.xpath("//mat-table//mat-row[1]//mat-cell[2][contains(text(),'"+Name+"')]");
		}
		//Select Submission checkbox
		public final By SelectSubmission_Checkbox(String Submission_Name){
			return By.xpath("//mat-cell//span[contains(text(),'"+Submission_Name+"')]/../preceding-sibling::mat-cell//mat-checkbox[contains(@id,'checkbox')]");

			}
		public final By SelectSubmission_UnCheckbox(String Submission_Name){
			return By.xpath("//mat-cell//span[contains(text(),'"+Submission_Name+"')]/../preceding-sibling::mat-cell//mat-checkbox[not(contains(@class,'checked'))]");
			//return By.xpath("//mat-cell//span[contains(text(),'"+Submission_Name+"')]/../preceding-sibling::mat-cell//mat-checkbox[contains(@ng-reflect-checked,'false')]");

			}
		//Text Message For re-assign Submission For Different Orgs
		 public final By Pm_user_Search_Submission_Re_Assign_DiffOrgs_Text=By.xpath("//div[contains(text(),'You can not reassign all those tasks.       All tasks must be available and part of the same organization.       Please change your selection.')]");
		 
		 
		
		//click open button in PM user submission tab
		public final By Pm_submission_open_button=By.xpath("//mat-icon[contains(text(),'mode_edit')]");//By.xpath("//mat-icon[contains(text(),'description')]");//("//mat-icon[contains(@mattooltip,'Open submission')]");
		
		public final By Pm_submission_open_SubmissionName_input=By.xpath("//mat-expansion-panel[contains(@class,'mat-expansion-panel')]//div[contains(@fxlayoutalign,'space-between center')]//mat-form-field[contains(@class,'mat-form-field')]//input");//By.xpath("//mat-expansion-panel[contains(@class,'mat-expansion-panel')]//div[contains(@fxlayoutalign,'start center')]//mat-form-field[contains(@class,'mat-form-field')]//input");
		public final By Pm_submission_open_SubmissionName_onlySpaces_message=By.xpath("//simple-snack-bar/span[contains(text(),'name cannot be blank.')]");
		public final By Pm_submission_open_SubmissionName_validationMessage=By.xpath("//mat-error[contains(text(),' Spaces at the begining/end are not allowed, neither two consecutives. ')]");
		//mat-expansion-panel//mat-chip[contains(text(),'New')]
		public final By Pm_submission_open_SubmissionName_newStatus=By.xpath("//mat-expansion-panel//mat-chip[contains(text(),'New')]");

		public final By Pm_submission_review_header=By.xpath("//mat-step-header//div[contains(text(),'review')]");
		
		public final By Pm_submission_open_transStatus=By.xpath("//mat-step-header//div[contains(text(),'trans')]");
		
		public final By Pm_submission_open_translateMessage(String message) {
			return By.xpath("//div[contains(@role,'tabpanel')]//p[contains(text(),'"+message+"')]");
		}
		
		public final By Pm_submission_open_translateButton=By.xpath("//mat-card-actions//button//span[contains(text(),'TRANSLATE')]");
		public final By Pm_submission_open_translateOverwriteMessage=By.xpath("//app-overwriting-target-dialog//h1[contains(text(),'There is already content in Target, do you want to overwrite it?')]");
		public final By Pm_submission_open_translate_canceloverwrite=By.xpath("//app-overwriting-target-dialog//button//span[contains(text(),'CANCEL')]");
		public final By Pm_submission_open_translate_overwrite=By.xpath("//app-overwriting-target-dialog//button//span[contains(text(),'OVERWRITE')]");
		
		//click open button inside pm user submission tab
		public final By Pm_inside_submission_open_button=By.xpath("//span[contains(text(),'OPEN JOB')]");
		
		public final By Pm_submission_openButton_VideoDetails=By.xpath("//app-submission-details-panel//span//mat-panel-title[contains(text(),'Video details')]");
		public final By Pm_submission_openButtonVideoDetails_StreamURL(String StreamURL) {
			return By.xpath("//submission-video-details//div[contains(text(),'"+StreamURL+"')]");
		
		}
		
		
		public final By Pm_submission_edit_tmgrInput=By.xpath("//input[@formcontrolname='tmgr']");
		public final By Pm_submission_edit_tmgr_connect=By.xpath("//button//span[contains(text(),'CONNECT')]");
		public final By submission_edit_tmgr_message(String error) {
			return By.xpath("//simple-snack-bar//span[contains(text(),'"+error+"')]");
		}
		
		
		public final By  Pm_inside_submission_open_targetLanguages(String languages) {
			return By.xpath("//mat-tab-header//div[contains(text(),'"+languages+"')]");
		}
		//click on COMPLETE TASK inside pm user submission tab
		public final By Pm_submission_complete_task=By.xpath("//mat-toolbar//button//span[contains(text(),'COMPLETE TASK')]");//By.xpath("//span[contains(text(),'COMPLETE TASK')]");
		public final By Pm_Complete_Task_Alert_button_trans=By.xpath("//mat-dialog-actions//span[contains(text(),'COMPLETE TASK')]");//By.xpath("//button//span[contains(text(),'COMPLETE TASK')]");
		public final By Pm_Complete_Task_Alert_button_review=By.xpath("//mat-dialog-actions//span[contains(text(),'COMPLETE')]");//By.xpath("//button//span[contains(text(),'COMPLETE')]");	
		public final By Pm_Cancel_Alert_button=By.xpath("//button//span[contains(text(),'CANCEL')]");
		
		public final By PM_Submission_CancelDialogBoxMassege=By.xpath("//app-cancel-submission-native//h1[contains(text(),'Leave Create Submission?')]//ancestor::app-cancel-submission-native//mat-dialog-content//p[contains(text(),'You did not complete the form. Are your sure you want to leave this page and lose the data?')]");//("//app-cancel-submission//h1[contains(text(),'Leave Create Submission?')]//ancestor::app-cancel-submission//mat-dialog-content//p[contains(text(),'You did not complete the form. Are your sure you want to leave this page and lose the data?')]");
		public final By PM_Submission_CancelDialogBoxMassege_StayOption=By.xpath("//mat-dialog-actions//button//span[contains(text(),'STAY')]");
		public final By PM_Submission_CancelDialogBoxMassege_LeaveOption=By.xpath("//mat-dialog-actions//button//span[contains(text(),'LEAVE')]");
		//index of header colunm
		//public final By pm_Indexof_colunms=By.xpath("//div[contains(@class,'data-list-header')]");
		
		     public final By Pm_allindexof_colunms(String colunmName){
			return By.xpath("//div[contains(@class,'data-list-header')]//div[contains(text(),'"+colunmName+"')]");

			}
		   //Select status of Submission in PM_Submission tab
				public final By check_Pm_submission_status(String SubmissionName, String status){
					return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+status+"')]");

					}
				public final By check_Pm_submission_client(String SubmissionName, String client){
					return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+client+"')]");

					}
		 public final By Pm_Submission_ListofallIds=By.xpath("//mat-list-item[contains(@class,'separator ng-star-inserted')]");//By.xpath("//mat-list-item[contains(@class,'separator mat-list-item ng-star-inserted')]");//NOTE:- work on that locator.
		 public final By Pm_Submission_Segement_row(int index){
				return By.xpath("//mat-list-item[(@id='"+index+"')]");
			}
		 public final By Pm_Submission_TargetSegement_textarea(int index){
				return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]");//("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::textarea[contains(@id,'textarea')]");
			}
		 
		 public final By Pm_Submission_TargetSegement1_textarea(int index){
				return By.xpath("//mat-list-item[@id='"+index+"']//shared-text-editor//div[contains(@class,'mousetrap')]");
			}
		 public final By Pm_Submission_TargetSegement_textarea_Input = By.xpath("//mat-list-item[contains(@class,'separator mat-list-item ng-star-inserted')]//shared-text-editor");
		
		 
		 public final By Pm_Submission_Modified_TargetSegement_textarea(int index){
				return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]");//("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::textarea[contains(@id,'textarea')]");
			}
		
		 public final By Pm_CopySource_toTarget_Button_tooltip_message=By.xpath("//button[contains(@ng-reflect-message,'Copy Source to Target (Alt + S)')]");//By.xpath("//button[contains(@ng-reflect-message,'Ctrl + S')]");//By.xpath("//button[contains(@mattooltip,'Copy source to target')]");
			public final By Pm_CopySource_toTarget_Button=By.xpath("//mat-icon[contains(text(),'content_copy')]");
			
			
		 //public final By Pm_CopySource_toTarget_Button=By.xpath("//button[contains(@ng-reflect-message,'Ctrl + S')]");//By.xpath("//button[contains(@mattooltip,'Copy source to target')]");
		 public final By Pm_CopySource_toModifiedTarget_Button=By.xpath("//mat-icon[contains(text(),'content_copy')]");//By.xpath("//button[contains(@ng-reflect-message,'Ctrl + S')]");//By.xpath("//button[contains(@mattooltip,'Copy target to modified target')]");
		 
		 //click on back button inside pm submission tab
		 public final By Pm_back_button=By.xpath("//mat-icon[contains(text(),'arrow_back')]");
		 
		 //click on help link in pm login
		 public final By Pm_HelpLink = By.xpath("//span[contains(text(),'Help')]");//By.xpath("//h4[contains(text(),'Help')]");
		  //help link new window title 
		 public final By Pm_help_window_title= By.xpath("//span[contains(text(),'Subtitler Online Help')]");
		 
		    public final By PM_user_CreateSubmission_DueDate_input=By.xpath("//input[contains(@placeholder,'Due date')]");//By.xpath("//input[@id='dueDatePicker']");	
			public final By PM_user_CreateSubmission_Name_input=By.xpath("//input[contains(@placeholder,'Name')]");//By.xpath("//input[@placeholder='Name']");
			public final By PM_user_CreateSubmission_SourceLanguage_input=By.xpath("//input[contains(@placeholder,'Source Language')]/../..");//By.xpath("//input[@placeholder='Source Language']");
			public final By PM_user_CreateSubmission_TargetLanguage_input=By.xpath("//input[contains(@placeholder,'Target languages')]");//By.xpath("//input[contains(@placeholder,'Target Languages')]");
			
			public final By PM_user_CreateSubmission_SourceLanguageFrom_dropdown(String LanguageName){
				return By.xpath("//mat-option[@role='option']//span[contains(text(),'"+LanguageName+"')]");
				
			}
			public final By PM_user_CreateSubmission_Create_Button=By.xpath("//span[contains(text(),'CREATE SUBMISSION')]");//By.xpath("//span[contains(text(),'Create submission')]");//By.xpath("//span[contains(text(),'CREATE')]");
			
			public final By PM_user_Search_Submission_input=By.xpath("//input[contains(@placeholder,'Filter by name')]");
			
			//select header checkbox in trans toClaim tab
			public final By PM_toClaim_select_header_checkbox=By.xpath("//mat-header-cell//mat-checkbox[contains(@id,'checkbox')]");
			
			public final By PM_AllJobs_select_header_checkbox=By.xpath("//mat-header-cell//mat-checkbox[contains(@id,'checkbox')]");
			public final By PM_Complted_select_header_checkbox=By.xpath("//mat-header-cell//mat-checkbox[contains(@id,'checkbox')]");
			public final By PM_toClaim_checkbox_checked=By.xpath("//mat-header-cell//mat-checkbox[contains(@class,'checkbox-checked')]");
			public final By PM_AllJobs_checkbox_checked=By.xpath("//mat-header-cell//mat-checkbox[contains(@class,'checkbox-checked')]");
			//Assign Submission	
			public final By PM_clamSubmission_icon=By.xpath("//button[contains(@mattooltip,'Claim task')]");//("//mat-icon[@mattooltip='Claim task']");//("//md-icon[@mdtooltip='Claim task']");
			public final By PM_UnclamSubmission_icon=By.xpath("//mat-icon[contains(text(),'assignment_return')]");  
			
			public final By PM_claimAlert_claim_button=By.xpath("//span[contains(text(),'CLAIM')]");
			public final By PM_claimAlert_cancel_button=By.xpath("//span[contains(text(),'CANCEL')]");
			//Select Target_Lang of Submission in PM_Ongoing tab
			public final By check_PM_Target_Lang(String SubmissionName, String target){
				return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]");
			}
			
			public final By check_PM_Submission_org(String SubmissionName, String orgnization){
				return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+orgnization+"')]");
			}
			
				
				public final By PM_selectSubmission_checkbox(String SubmissionName,String target){
					//return By.xpath("//mat-cell[contains(text(),'"+SubmissionName+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
					//return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'mat-checkbox-background')]");//("//mat-cell[contains(text(),'"+SubmissionName+"')]/following-sibling::mat-cell[contains(text(),'"+target+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
					return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
				}
				public final By PM_Submission_Edit_icon=By.xpath("//button[contains(@mattooltip,'Edit')]");//("//mat-icon[@mattooltip='Edit']");
				
				public final By PM_Submission_Edit_TranscriptionView_BlueBarheader=By.xpath("//app-transcription//mat-toolbar");
				public final By PM_Submission_Edit_TranscriptionView_headerSubmissionName(String submissionName) {
					return By.xpath("//app-transcription//mat-toolbar//span[contains(text(),'"+submissionName+"')]");
				}
				
				public final By PM_Submission_editSubmissionName_input=By.xpath("//app-submission-details-panel//input[contains(@formcontrolname,'name')]");
				public final By PM_Submission_editSubmissionName_updateButton=By.xpath("//app-submission-details-panel//input[contains(@formcontrolname,'name')]");
			    public final By PM_toClaim_ListofallIds=By.xpath("//mat-list-item[contains(@class,'indicator separator')]");//("//mat-list-item[contains(@class,'separator mat-list-item ng-star-inserted')]");//NOTE:- work on that locator.
			    public final By ListofIDs = By.xpath("//mat-list-item[contains(@class,'separator segment')]");//("//mat-list-item[contains(@ng-reflect-klass,'separator segment')]");
			    
			    
			    public final By Pm_user_SearchSubmission_SegmentIcon=By.xpath("//mat-header-cell//div//span[contains(text(),'Segments')]");
				public final By Pm_user_SearchSubmission_WordCountSource=By.xpath("//mat-header-cell//div//span[contains(text(),'Word count (source)')]");

			    
				public final By PM_TargetSegement_textarea(int index){
					return By.xpath("//mat-list-item[@id='"+index+"']//shared-text-editor//div[contains(@class,'mousetrap')]");//("//mat-list-item[contains(@id,'"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]");//("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::textarea[contains(@id,'textarea')]");
				}
				
				
				public final By PM_SourceSegement_textarea(int index,String sourceSegment){
					return By.xpath("//mat-list-item["+index+"]//app-text-segment//span[contains(text(),'"+sourceSegment+"')]");
				}
				
				public final By PM_SourceSegement_textarea_highlightedTerms(int index,String term){
					return By.xpath("//mat-list-item["+index+"]//app-text-segment[@id='source']//span[contains(@class,'highlighted')][contains(text(),'"+term+"')]");
				}
				
				//public final By PM_CopySource_toTarget_Button=By.xpath("//button[contains(@mattooltip,'Copy source to target')]");
				
				public final By PM_Complete_Button=By.xpath("//mat-toolbar//button//span[contains(text(),'COMPLETE TASK')]");//By.xpath("//mat-icon[contains(text(),'check')]");

				public final By PM_ongoing_indexCaption(String indexCaption) {
					return By.xpath("//button[contains(@class,'mat-button')]//span[contains(text(),'"+indexCaption+"')]");
				}
				public final By PM_CompleteDailoge_Button=By.xpath("//mat-dialog-actions//span[contains(text(),'COMPLETE')]");//("//mat-dialog-container//mat-dialog-actions//button//span[text()='COMPLETE TASK']");
	//TODO	PM_BackToSubmissionList_Button locator has been changed from back to menu in Sub 1.10	
				public final By PM_BackToSubmissionList_Button=By.xpath("//mat-toolbar[1]//div//button[1]//span[1]//mat-icon[contains(@role,'img')]");//By.xpath("//mat-toolbar//button//mat-icon[contains(text(),'arrow_back')]");
				
				public final By PM_CompleteDailoge_cancelButton=By.xpath("//mat-dialog-actions//button//span[contains(text(),'CANCEL')]");
				public final By PM_completeTask_message(String message) {
					return By.xpath("//mat-dialog-content//p[contains(text(),'"+message+"')]");
				}
				public final By PM_completedTask_completeTaskPopUp=By.xpath("//simple-snack-bar//span[contains(text(),'Task now marked as completed.')]");
				
				
				
				
				
				public final By PM_BackToSubmissionList_Menubutton_forCompleted=By.xpath("//mat-toolbar[1]//button[1]//span[1]//mat-icon[contains(@role,'img')]");
				public final By PM_BackToSubmissionList_Menubutton=By.xpath("//standard-toolbar//mat-toolbar[1]//button[1]//span[1]//mat-icon[contains(@role,'img')]");
				//Select Target_Lang of Submission in PM_Ongoing tab
				public final By check_trans_Target_Lang(String SubmissionName, String target){
					return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]");

					}	
				//Click on Re-Assignment tab on All Jobs in PM login
				
				public final By PM_AllJobs_Reassignment=By.xpath("//button[contains(@mattooltip,'Re-assign Job')]//span//mat-icon[contains(text(),'assignment_ind')]");
				public final By PM_AllJobs_Reassignment_multipleJobs=By.xpath("//button[contains(@mattooltip,'Re-assign multiple Job')]//span//mat-icon[contains(text(),'assignment_ind')]");
				public final By PM_editSubmission_Reassignment=By.xpath("//mat-icon[contains(text(),'assignment_ind')]");
				public final By PM_editSubmission_Reassignment_Status(String step,String status) {
					return By.xpath("//mat-step-header//div[contains(text(),'"+step+"')]//small/i[contains(text(),'"+status+"')]");
				}
				public final By PM_editSubmission_groupName(String groupName) {
					return By.xpath("//submission-job-assignments//mat-list-item//span[contains(text(),'"+groupName+"')]");
				}
				public final By PM_editSubmission_unclaimTask=By.xpath("//mat-icon[contains(text(),'assignment_return')]");
				public final By PM_editSubmission_unclaimTaskPopUp_message(String message) {
					return By.xpath("//app-confirm-unclaim-dialog//p[contains(text(),'"+message+"')]");
				}
				public final By PM_editSubmission_unclaimTask_unclaimButton=By.xpath("//app-confirm-unclaim-dialog//button//span[contains(text(),'UNCLAIM')]");
				public final By PM_editSubmission_unclaimTask_cancelButton=By.xpath("//app-confirm-unclaim-dialog//button//span[contains(text(),'CANCEL')]");
				
				
				public final By PM_AllJobs_JobHistory=By.xpath("//mat-icon[contains(text(),'event_note')]");
				public final By PM_AllJobs_JobHistory_steps(String stepName) {
					return By.xpath("//job-history-table//mat-header-cell[contains(text(),'Step')]/../..//mat-cell[contains(text(),'"+stepName+"')]");
				}
				public final By PM_AllJobs_JobHistory_status(String statusName) {
					return By.xpath("//job-history-table//mat-header-cell[contains(text(),'Status')]/../..//mat-cell[contains(text(),'"+statusName+"')]");
				}
				public final By PM_AllJobs_JobHistoryDialogBox_AllHeaderIcons(String JobHistoryHeaderName) {
					return By.xpath("//mat-header-row//mat-header-cell[contains(text(),'"+JobHistoryHeaderName+"')]");
				}
				
				public final By PM_Submission_AllHeaderIcons(String headerName) {
					return By.xpath("//mat-header-row//mat-header-cell//button[contains(text(),'"+headerName+"')]");//inside button
				}
				
				public final By PM_Submission_AllHeaderIcons_x(String headerName) {
					return By.xpath("//mat-header-row//mat-header-cell//button//span[contains(text(),'"+headerName+"')]");//Inside span
				}
				
				public final By PM_AllJobs_JobHistory_DialogBox_StepNo_Icon=By.xpath("//mat-header-row//mat-header-cell[contains(text(),'Step No')]");
				
				public final By Pm_User_AllJobs_JobHistory_DialogBox_Icons(String StepName, String Status , String UserName ) {
					return By.xpath("//job-history-table//mat-row//mat-cell[contains(text(),'"+StepName+"')]//ancestor::mat-row//mat-cell[contains(text(),'"+Status+"')]//ancestor::mat-row//mat-cell[contains(text(),'"+UserName+"')]");
				}
				
				public final By Pm_User_AllJobs_JobHistory_DialogBox_CancelButton=By.xpath("//mat-dialog-actions//button//span[contains(text(),'CANCEL')]");
				
				//Radio button inside Re-Assignment tab on All Jobs in PM login
			/*	public final By PM_Reassign_job_radio_button(String radiobutton){
					return By.xpath("//div[contains(text(),'"+radiobutton+"')]");

					}*/	
				public final By PM_Reassign_job_Users_radio_button=By.xpath("//div[contains(text(),'Users')]");
				public final By PM_Reassign_job_Groups_radio_button=By.xpath("//div[contains(text(),'Groups')]");
				public final By PM_MultiReassign_job=By.xpath("//app-reassign-multiple-job//h1[contains(text(),'Multiple Reassign Job')]");
				//Reassign Job dropdown inside Re-Assignment tab on All Jobs in PM login
		
				public final By PM_Reassign_Job_dropdown_Users=By.xpath("//mat-select[contains(@placeholder,'Users')]");//("//mat-select[contains(@name,'Users')]");//("//span[contains(text(),'Users')]");
				public final By PM_Reassign_Job_dropdown_Groups=By.xpath("//mat-select[contains(@placeholder,'Groups')]");//("//mat-select[contains(@name,'Groups')]");//("//span[contains(text(),'Groups')]");
				
				public final By PM_Reassign_Job_from_Users_dropdown(String UserName){
					return By.xpath("//mat-option[contains(@role,'option')]//span[contains(text(),'"+UserName+"')]");
					}
				public final By PM_Reassign_Job_from_Users_dropdownWithIndex(String UserName,int index){
					return By.xpath("//mat-option["+index+"][contains(@role,'option')]//span[contains(text(),'"+UserName+"')]");
					}
				
				public final By PM_Reassign_Job_from_Groups_dropdown(String GroupName){
					return By.xpath("//mat-option[contains(@role,'option')]//span[contains(text(),'"+GroupName+"')]");
					}
				
				
				public final By Pm_EditSubmission_OstTage_ForText(int id, String text) {
					return By.xpath("//mat-list-item[@id='"+id+"']//div//mat-icon[contains(text(),'tv')]//ancestor::mat-list-item//app-text-segment//div[1][contains(text(),'"+text+"')]");
				}
				
				//Not Needed
//				public final By Pm_EditSubmission_NoOstTage_ForText(int id) {
//					return By.xpath("//mat-list-item[@id='"+id+"']//div//mat-icon[contains(text(),'tv')]");
//				}
				
				public final By Pm_EditSubmission_NoOstTage_ForText(String segmentText) {
					return By.xpath("//mat-list-item//div[contains(@class,'backdrop')]/div[contains(text(),'"+segmentText+"')]//ancestor::mat-list-item//mat-icon[contains(text(),'tv')]");
				}
				
				public final By PM_Reassign_Job_REASSIGN_Button=By.xpath("//span[contains(text(),'REASSIGN')]");
				
				public final By check_status_assignee(String Status, String Assignee){
					return By.xpath("//mat-cell[contains(text(),'"+Status+"')]/following-sibling::mat-cell//span[contains(text(),'"+Assignee+"')]");
				}
				
				
				public final By check_status_assignee_forSubmission(String submissionName,String Status, String Assignee){
					return By.xpath("//mat-cell//span[contains(text(),'"+submissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+Status+"')]/following-sibling::mat-cell//span[contains(text(),'"+Assignee+"')]");
				}
				
				//delete submission
				public final By DeleteSubmission_Icon=By.xpath("//mat-icon[contains(text(),'delete')]");
				public final By DeleteSubmission_Alert_delete_button=By.xpath("//span[contains(text(),'DELETE SUBMISSION')]");
				public final By DeleteSubmission_Alert_Cancel_button=By.xpath("//span[contains(text(),'CANCEL')]");
				public final By DeleteSubmission_Second_Alert_button=By.xpath("//span[contains(text(),'DELETE JOBS')]");
				
				//==
				public final By PM_Submission_Ongoing_Edit_TextFormat(String TextFormat) {
					return By.xpath("//shared-text-editor//div[contains(text(),'"+TextFormat+"')]");
				}
			    //==	
			   //delete submission second confirmation box "Delete also jobs content?"
				public final By DeleteJobs_Second_confirmation_Alert_button=By.xpath("//span[contains(text(),'DELETE JOBS')]");
				public final By DeleteJobs_Second_confirmation_Alertt_Cancel_button=By.xpath("//span[contains(text(),'CANCEL')]");
				public final By DeleteJobs_final_message=By.xpath("//mat-dialog-content[contains(text(),'Some jobs are in progress or completed. It will remove content permanently.')]");//("//mat-dialog-content[contains(text(),'Some jobs are in progress or completed. It will remove content permanently.");
				public final By deleteJobs_OK_button=By.xpath("//span[contains(text(),'OK')]");
				
				// Clone Submission
				public final By CloneSubmission_Icon=By.xpath("//mat-icon[contains(text(),'content_copy')]");
				
				// calendar 
				public final By Calendar=By.xpath("//button[contains(@aria-label,'Choose month and year')]");
				
				//calendar current month 
				public final By Calendar_CurrentMonth(String CurrentMonth){
					return By.xpath("//mat-calendar[contains(@class,'mat-calendar')]//span[contains(text(),'"+CurrentMonth+"')]");
}
				public final By DueDate_color= By.xpath("//input[contains(@placeholder,'Due date')]");
				
				public final By dueDate_calender_currentDate= By.xpath("//td[contains(@class,'mat-calendar-body-active')]");
				
				public final By demo= By.xpath("//mat-calendar[contains(@id,'mat-datepicker-2')]");
				
				public final By  selectDate(String date){
					return By.cssSelector("td[aria-label='" + date + "']");	//return By.xpath("//td[contains(@class,'mat-calendar-body-active')]//div[contains(text(),'"+date+"')]"); return By.cssSelector("td[aria-label='" + date + "']");
}
				
				
				
				public final By PM_ToClaim_message() throws Exception{
					System.out.println("The task has been assigned to you.");
					if(Verify.action().verifyElementPresent(By.xpath("//div[contains(text(),'The task has been assigned to you.')]"),5))//By.xpath("//div[contains(text(),'The task(s) has been assigned to you.')]")
						return By.xpath("//div[contains(text(),'The task has been assigned to you.')]");//By.xpath("//div[contains(text(),'The task(s) has been assigned to you.')]");
					else{
						return By.xpath("//div[contains(text(),'The task has been assigned to you.')][contains(@class,'hidden')]");//By.xpath("//div[contains(text(),'The task(s) has been assigned to you.')][contains(@class,'hidden')]");
					}
				}
				

				public final By PM_ToClaim_message_MultipleTask() throws Exception{
					System.out.println("The tasks has been assigned to you.");
					if(Verify.action().verifyElementPresent(By.xpath("//div[contains(text(),'The tasks has been assigned to you.')]"),5))//By.xpath("//div[contains(text(),'The task(s) has been assigned to you.')]")
						return By.xpath("//div[contains(text(),'The tasks has been assigned to you.')]");//By.xpath("//div[contains(text(),'The task(s) has been assigned to you.')]");
					else{
						return By.xpath("//div[contains(text(),'The tasks has been assigned to you.')][contains(@class,'hidden')]");//By.xpath("//div[contains(text(),'The task(s) has been assigned to you.')][contains(@class,'hidden')]");
					}
				}
				// this will click on check box based on step or submission name.
				public final By PM_ToClaim_Step_checkbox(String Submission, String step){
					return  By.xpath("//mat-cell[contains(text(),'"+step+"')]/preceding-sibling::mat-cell/span[contains(text(),'"+Submission+"')]/../preceding-sibling::mat-cell//mat-checkbox[contains(@id,'mat-checkbox')]");
							//By.xpath("//mat-cell[contains(text(),'"+step+"')]/preceding-sibling::mat-cell[contains(text(),'"+Submission+"')]/preceding-sibling::mat-cell//mat-checkbox[contains(@id,'mat-checkbox')]");
}		
				public final By PM_UnclaimJob_button=By.xpath("//button[contains(@mattooltip,'Unclaim Task')]");
				public final By PM_cancel_current_edit = By.xpath("//button[contains(@mattooltip,'Cancel current edit (Esc)')]");
				
				
				public final By check_Pm_submission_step(String SubmissionName, String step){
					return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+step+"')]");
				}		
				
				public final By check_Pm_submission_step_X(String SubmissionName, String step){
					return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+step+"')]");
				}	
				
				public final By PM_Trans_GoToSubtitleID_Menu = By.xpath("//label//span[contains(text(),'Go to subtitle ID')]");
				public final By PM_Transc_GoToSubtitleID_Menu = By.xpath("//label[contains(text(),'Subtitle ID')]");
				public final By PM_GoToSubtitle = By.xpath("//input[contains(@placeholder,'Go to subtitle ID')]");
				public final By PM_GoToSubtitle_transc = By.xpath("//input[contains(@name,'id')]");
				public final By PM_GoToSubtitle_Go_button = By.xpath("//button//span[contains(text(),'GO')]");
				public final By PM_GoToSubtitle_Close_button = By.xpath("//button//span[contains(text(),'CANCEL')]");
				public final By PM_GoToSubtitle_transc_textarea=By.xpath("//shared-text-editor//div[contains(@class,'mousetrap')]");
				public final By PM_GoToSubtitle_message=By.xpath("//mat-hint[contains(@class,'mat-hint')]");
				
				public final By SubtitleRowId(int id){
					return By.xpath("//mat-list-item[contains(@id,'"+id+"')]");
				}	
				public final By PM_GoToSubtitle_transc(int index){
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//div[contains(@class,'editor-read-only')]");
				}	
				
				public final By AscendingSubmission_Name=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
				public final By DescendingSubmission_Name=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'descending')]]");
				public final By Submission_Name=By.xpath("//mat-table//div//button[contains(text(),'Name')]");
				public final By Submission_DueDate=By.xpath("//mat-table//div//button[contains(text(),'Due Date')]");
				public final By Submission_creationDate=By.xpath("//mat-table//div//button[contains(text(),'Creation Date')]");
				public final By AscendingSubmission_creationDate=By.xpath("//mat-table//div[button[contains(text(),'Creation Date')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
				public final By DescendingSubmission_creationDate=By.xpath("//mat-table//div[button[contains(text(),'Creation Date')]]//div[../following-sibling::span[contains(text(),'descending')]]");
				public final By AscendingSubmission_dueDate=By.xpath("//mat-table//div[button[contains(text(),'Due Date')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
				public final By DescendingSubmission_dueDate=By.xpath("//mat-table//mat-header-cell[div[button[contains(text(),'Due Date')]]][contains(@aria-sort,'descending')]");
				public final By Submission_status=By.xpath("//mat-table//div//button[contains(text(),'Status')]");
				public final By Submission_Assignee=By.xpath("//mat-table//div//button[contains(text(),'Assignee')]");
				public final By AscendingSubmission_status=By.xpath("//mat-header-cell[contains(@aria-sort,'ascending')]//div[contains(@class,'mat-sort-header-stem')]");//("//mat-table//mat-header-cell[div[button[contains(text(),'Status')][contains(@aria-sort,'ascending')]");//By.xpath("//mat-table//div[button[contains(text(),'Status')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
				public final By DescendingSubmission_status=By.xpath("//mat-header-cell[contains(@aria-sort,'descending')]//div[contains(@class,'mat-sort-header-stem')]");//("//mat-table//mat-header-cell[div[button[contains(text(),'Status')][contains(@aria-sort,'descending')]");//By.xpath("//mat-table//div[button[contains(text(),'Status')]]//div[../following-sibling::span[contains(text(),'descending')]]");
				
				//Video length sorting 
				public final By Submission_videoColumn=By.xpath("//mat-header-cell//button[contains(text(),'Video')]");
				
				public final By AscendingSubmission_Assignee=By.xpath("//mat-table//div[button[contains(text(),'Assignee')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
				public final By DescendingSubmission_Assignee=By.xpath("//mat-table//div[button[contains(text(),'Assignee')]]//div[../following-sibling::span[contains(text(),'descending')]]");
				
				public final By check_sub_with_dueDate(String submissionName, String dueDate){
					return By.xpath("//mat-row//mat-cell//span[contains(text(),'"+submissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+dueDate+"')]");
}
				
				public final By check_sub_with_submissionDueDate(String submissionName, String dueDate){
					return By.xpath("//mat-row//mat-cell//span[contains(text(),'"+submissionName+"')]/../following-sibling::mat-cell[(text()='"+dueDate+"')]");
}
	
				public final By check_sorting_sub_with_dueDate(int index,String submissionName, String dueDate){
					return By.xpath("//mat-row["+index+"]//mat-cell//span[contains(text(),'"+submissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+dueDate+"')]");
}
				public final By check_sub_with_status(String submissionName, String status){
					return By.xpath("//mat-row//mat-cell//span[contains(text(),'"+submissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+status+"')]");//("//mat-row//mat-cell//span[contains(text(),'"+submissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+status+"')]");
				}
				
				public final By check_sorting_sub_with_video(int index,String submissionName, String dueDate){
					return By.xpath("//mat-row["+index+"]//mat-cell//span[contains(text(),'"+submissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+dueDate+"')]");
}
				
				
				public final By check_sub_with_statusAndStep(String submissionName, String step, String status){
					return By.xpath("//mat-row//mat-cell//span[contains(text(),'"+submissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+step+"')]/following-sibling::mat-cell[contains(text(),'"+status+"')]");
				}
				
				
					
					public final By check_sub_with_status_AscDsc(int index,String submissionName, String status){
						return By.xpath("//mat-row["+index+"]//mat-cell//span[contains(text(),'"+submissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+status+"')]");
}

				
				public final By check_sub_with_creationDate(String submissionName, String creationDate){
					return By.xpath("//mat-row//mat-cell//span[contains(text(),'"+submissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+creationDate+"')]");
}
				public final By PM_creationDate(int rowNum){
					return By.xpath("//mat-row["+rowNum+"]//mat-cell[contains(@class,'mat-column-creationDate')]");
				}	
				
				public final By PM_dueDate(int rowNum){
					return By.xpath("//mat-row["+rowNum+"]//mat-cell[contains(@class,'mat-column-dueDate')]");
				}
				
				public final By check_sub_with_Assignee(String submissionName, String Assignee){
					return By.xpath("//mat-row//mat-cell//span[contains(text(),'"+submissionName+"')]/../following-sibling::mat-cell//span[contains(text(),'"+Assignee+"')]");
				}
				
				public final By PM_Ongoing_edit_button = By.xpath("//mat-icon[contains(text(),'mode_edit')]");
				
				public final By PM_ongoing_transcription_screen=By.xpath("//span[contains(text(),'trans')]");
				public final By PM_ongoing_transcription_Video=By.xpath("//shared-video//video[contains(@crossorigin,'anonymous')]");//By.xpath("//app-transcription-video//mat-card[contains(@class,'mat-card')]");
				public final By PM_ongoing_transcription_table=By.xpath("//transcription-table//div[contains(@id,'data-grid')]");
				public final By PM_ongoing_transcription_tab_group=By.xpath("//mat-tab-group[contains(@class,'mat-tab-group')]");
				public final By PM_ongoing_transcription_Video_Screen=By.xpath("//app-transcription-video//mat-icon[contains(@class,'animation mat-icon material-icons')]");
				
				public final By PM_ongoing_multiOption_icon= By.xpath("//mat-icon[contains(text(),'more_vert')]");	
				public final By PM_Ongoing_Import_file=By.xpath("//span[contains(text(),'Import File')]");
				public final By PM_ongoing_find_and_replace = By.xpath("//span[contains(text(),'Find & Replace')]");
				public final By PM_ongoing_keyboard_shortcuts = By.xpath("//span[contains(text(),'Keyboard Shortcuts')]");
				
				public final By PM_Ongoing_Import_file_input=By.xpath("//button//span[contains(text(),'Import')]/following-sibling::input");
				public final By PM_ongoing_overwrite_current_translation = By.xpath("//span[contains(text(),'OVERWRITE')]");
				
				
				
				//find and replace locators
				
				public final By PM_Ongoing_FindandReplace_find=By.xpath("//find-replace-dialog//input[contains(@placeholder,'Find')]");
				public final By PM_Ongoing_FindandReplace_replaceBy=By.xpath("//find-replace-dialog//mat-form-field//input[contains(@placeholder,'Replace by')]");
				public final By PM_Ongoing_FindandReplace_findIn=By.xpath("//mat-dialog-content//mat-select[contains(@placeholder,'Find in')]");
				public final By PM_Ongoing_FindandReplace_findInOptions(String option) {
					return By.xpath("//mat-option[contains(@class,'mat-option')]//span[contains(text(),'"+option+"')]");
				}
				public final By PM_Ongoing_FindandReplace_Close_button =By.xpath("//span[contains(text(),'CANCEL')]");
				public final By PM_Ongoing_FindandReplace_Find_button =By.xpath("//span[contains(text(),'FIND')]");
				public final By PM_Ongoing_FindandReplace_Replace_button =By.xpath("//span[text()=' REPLACE ']");
				public final By PM_Ongoing_FindandReplace_ReplaceAll_button =By.xpath("//span[contains(text(),'REPLACE ALL')]");
				public final By PM_Ongoing_FindandReplace_ReplaceAll_button_active=By.xpath("//button[not(contains(@disabled,'true'))]//span[contains(text(),'REPLACE ALL')]");//("//button[contains(@ng-reflect-disabled,'false')]//span[contains(text(),'REPLACE ALL')]");
				public final By PM_Ongoing_FindandReplace_Occurrrences(String occurrences) {
					return By.xpath("//section//h5[contains(text(),'"+occurrences +"')]");
				}
				
				// Locators For ToolTip (TRANSCRIPTION)
				public final By PM_ongoing_Transcription_Bold_Tooltip = By.xpath("//button[contains(@ng-reflect-message,'Ctrl + B')]");//mattooltip
				public final By PM_ongoing_Transcription_Italic_Tooltip = By.xpath("//button[contains(@ng-reflect-message,'Ctrl + I')]");
				public final By PM_ongoing_Transcription_Underline_Tooltip = By.xpath("//button[contains(@ng-reflect-message,'Ctrl + U')]");
				
				
				
				public final By PM_InvalidImportFile_Error_message() throws Exception{
					System.out.println("The extension file is not valid..");
//					if(Verify.action().verifyElementPresent(By.xpath("//div[contains(text(),'The extension file is not valid.')]"),5))
//						return By.xpath("//div[contains(text(),'The extension file is not valid.')]");
//					else{
//						return By.xpath("//div[contains(text(),'The extension file is not valid.')][contains(@class,'hidden')]");
//					}
					
					if(Verify.action().verifyElementPresent(By.xpath("//div[contains(text(),'The extension file is not valid.')]"),5))
						return By.xpath("//span[contains(text(),'The extension file is not valid.')]");
					else{
						return By.xpath("//span[contains(text(),'The extension file is not valid.')][contains(@class,'hidden')]");
					}
				}
				
				
				
				public final By PM_ongoing_table_content = By.xpath("//div[contains(@class,'editor-read-only')]");//By.xpath("//div[contains(@class,'source-read-only')]");
				public final By PM_ongoing_video_editor = By.xpath("//shared-text-editor//div[contains(@class,'mousetrap')]");
				public final By PM_ongoing_tab_group_calibration = By.xpath("//span[contains(text(),'Calibration')]");
				public final By PM_ongoing_demo = By.xpath("//mat-list-item//div[contains(@class,'mat-list-item-content')]");//("//div[contains(@class,'tab-content calibration-content')]");
				
			/*	public final By PM_OngoingSubtitle_transc(int index){
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//div[contains(@class,'source-read-only')]");
				}
				
				public final By PM_Ongoing_transc_textarea=By.xpath("//shared-text-editor//div[contains(@class,'mousetrap')]");*/
				
				public final By PM_readingSpeed_value(int index){
					return By.xpath("//mat-list-item[@id='"+index+"']//span[contains(@class,'reading-speed')]");// By.xpath("//mat-list-item[contains(@id,'"+index+"')]//span[contains(@class,'reading-speed')]");
				}
				
				
				
			public final By Pm_calibration_button = By.xpath("//span[contains(text(),'CALIBRATE')]");
			public final By PM_file_download =By.xpath("//mat-icon[contains(text(),'file_download')]");	
			public final By PM_final_files_download = By.xpath("//span[text()='DOWNLOAD']");
			
			public final By PM_FileDownload_SRT=By.xpath("//button[text()='SRT']");
			public final By PM_FileDownload_dailogBox_ChooseFormat=By.xpath("//mat-select[contains(@placeholder,'Choose a format')]");
			public final By PM_FileDownload_dailogBox_header(String header) {
				return By.xpath("//app-download-submissions-native-final-files-dialog//mat-header-row//button[contains(text(),' "+header+"')]");
			}
			
			public final By PM_FileDownload_dailogBox_submissionNameDisable(String submissionName) {
				return By.xpath("//app-download-submissions-native-final-files-dialog//input[contains(@ng-reflect-disabled,'disabled')][contains(@ng-reflect-value,'"+submissionName+"')]");
			}
			public final By PM_FileDownload_dailogBox_submissionNameDisable=By.xpath("//app-download-submissions-native-final-files-dialog//input[@disabled='']");
			
			public final By PM_FileDownload_dailogBox_headerCheckbox=By.xpath("//app-download-submissions-native-final-files-dialog//mat-header-row//mat-checkbox[not(contains(@class,'checkbox-checked'))]");//("//app-download-submissions-native-final-files-dialog//mat-header-row//mat-checkbox[contains(@ng-reflect-checked,'false')]");
			public final By PM_FileDownload_dailogBox_SRT= By.xpath("//mat-option//span[(text()=' SRT ')]");
			public final By PM_FileDownload_dailogBox_SRTAN = By.xpath("//mat-option//span[(text()=' SRT+AN ')]");
			public final By PM_FileDownload_dailogBox_TTML = By.xpath("//mat-option//span[(text()=' TTML1 ')]");
			public final By PM_FileDownload_dailogBox_EBU= By.xpath("//mat-option//span[(text()=' EBU-TT-D ')]");
			public final By PM_FileDownload_dailogBox_editedTTML= By.xpath("//mat-option//span[(text()=' Edited TTML ')]");
			public final By PM_FileDownload_dailogBox_audioScripts= By.xpath("//mat-option//span[(text()=' Audio Scripts (DOCX, XLSX) ')]");
			public final By  PM_FileDownload_dailogBox_languageCheckbox(String language) {
			    	return By.xpath("//mat-cell[contains(text(),'"+language+"')]");
			}
			public final By PM_FileDownload_dialogBox_CheckBox(String language) {
				return By.xpath("//app-download-submissions-native-final-files-dialog//mat-cell[contains(text(),'"+language+"')]//ancestor::mat-row//mat-checkbox[contains(@class,'checked')]");
				//return By.xpath("//app-download-submissions-native-final-files-dialog//mat-cell[contains(text(),'"+language+"')]//ancestor::mat-row//mat-checkbox[contains(@ng-reflect-checked,'true')]");
			}
			public final By PM_FileDownload_dailogBox_downloadButton=By.xpath("//mat-dialog-actions//span[contains(text(),'DOWNLOAD')]");
			public final By PM_FileDownload_dailogBox_cancelButton=By.xpath("//mat-dialog-actions//span[contains(text(),'CANCEL')]");
			
			public By PM_Show_Completed = By.xpath("//span[contains(text(),'Show completed')]");
			
           //Pm transcription locators
			
			public final By PM_Transcription_textarea(int index){
				return By.xpath("//mat-list-item[@id='"+index+"']//div[contains(@class,'editor-read-only')]");//("//mat-list-item[contains(@id,'"+index+"')]//div[contains(@class,'editor-read-only')]");
			}
			
			public final By PM_Transcription_textarea_readOnly(int index){
				return By.xpath("//mat-list-item[contains(@id,'"+index+"')]["+index+"]//div[contains(@class,'editor-read-only')]");
			}
			
			public final By PM_Transcription_textarea(String Contents){
			return By.xpath("//mat-list//mat-list-item[contains(@id,'1')]//div[contains(text(),'"+Contents+"')]");
			}
			
			public final By PM_Transcription_textareaText(String Contents){
				return By.xpath("//mat-list//mat-list-item//div[contains(text(),'"+Contents+"')]");
			}
			
			public final By PM_Transcription_vedioTime(int index) {
				return By.xpath("//app-time-box//span["+index+"]");
				
			}
			
			public final By PM_Transcription_Video = By.xpath("//shared-video//video[contains(@crossorigin,'anonymous')]");//By.xpath("//video[contains(@crossorigin,'anonymous')]");	
			public final By PM_Transcription_Video_textarea = By.xpath("//shared-text-editor//div[contains(@class,'mousetrap')]");	
			public final By PM_Transcription_Video_pause = By.xpath("//mat-icon[contains(text(),'pause')]");
			public final By PM_Transcription_Video_play_arrow = By.xpath("//div[contains(@class,'play-pause-box')]//mat-icon[contains(text(),'play_arrow')]");
			public final By Pm_transcription_gotosubtitle_count = By.xpath("//mat-hint//small[contains(text(),'Last')]");
			
			
			public final By PM_Transcription_VideoBar_playButton=By.xpath("//mat-icon[contains(text(),'play_arrow')]");
			public final By PM_Transcription_VideoBar_pauseButton=By.xpath("//mat-icon[contains(text(),'pause')]");
			
			
			public final By Pm_transcription_GO_to_Subtitle = By.xpath("//h1[contains(text(),'Go to Subtitle')]");
			public final By Pm_transcription_GO_to_Subtitle_input = By.xpath("//input[contains(@type,'number')]");
			public final By Pm_transcription_GO_to_Subtitle_GO_Button = By.xpath("//span[contains(text(),' GO')]");
			public final By PM_Transcription_GO_to_Subtitle_TimeFrame= By.xpath("//mat-form-field[contains(@class,'calibration-input')]//input[contains(@name,'frames')]");//("//mat-form-field[contains(@class,'calibration-input')]//input[contains(@ng-reflect-name,'frames')]");
			public final By PM_TranscriptionGO_to_Subtitle_TimeSec= By.xpath("//mat-form-field[contains(@class,'calibration-input')]//input[contains(@name,'seconds')]");//("//mat-form-field[contains(@class,'calibration-input')]//input[contains(@ng-reflect-name,'seconds')]");
		
		   public final By PM_Transcription_TimeIn(int index){
			return By.xpath("//mat-list-item[contains(@id,'"+index+"')]/div[contains(@class,'mat-list-item-content')]/div[7]");//("//mat-list-item[contains(@id,'"+index+"')]/div[contains(@class,'mat-list-item-content')]//div[contains(text(),'8')]");//("//mat-list-item[contains(@id,'"+index+"')]//div[contains(text(),'"+index+"')]//following-sibling::div[3]");//("//mat-list-item[contains(@id,'"+index+"')]//div[contains(text(),'"+index+"')]//following-sibling::div[3]");
		   }	
		
		  public final By PM_Transcription_TimeOut(int index){
			return By.xpath("//mat-list-item[contains(@id,'"+index+"')]/div[contains(@class,'mat-list-item-content')]/div[8]");//("//mat-list-item[contains(@id,'"+index+"')]//div[contains(text(),'"+index+"')]//following-sibling::div[4]");
		  }
		  
		  public final By TPM_Transcription_segmentLength_L_multiPleTLines(int index) {
				return By.xpath("//mat-list-item[2]//shared-line-counter/div/div[contains(@class,'ng-star-inserted')]["+index+"]");
				
			}
		
			
			public final By PM_Transcription_Duration(int index){
				return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//div[contains(text(),'"+index+"')]//following-sibling::div[6]");
			}
			
			
			public final By PM_Transcription_Textarea_header = By.xpath("//mat-list//div[contains(@class,'data-list-header')]");
			
			public final By PM_Transcription_textarea_ReadingSpeed(int index){
				return By.xpath("//mat-list-item["+index+"][contains(@id,'"+index+"')]//shared-reading-speed//span[contains(@class,'reading-speed')]");
			}	
			public final By PM_Transcription_textarea_ReadingSpeed_color(int index){
				return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//shared-reading-speed//span[contains(@class,'reading-speed')]");
			}
			public final By PM_Transcription_textarea_LineCount(int index){
				return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//shared-line-counter//span");
			}

			public final By PM_Transcription_duration(int index) {
				return By.xpath("//mat-list-item["+index+"][contains(@class,'mat-list-item separator')]//div[8]");
			}
			public final By PM_Transcription_duration(int index ,int colunmIndex) {
				return By.xpath("//mat-list-item["+index+"][contains(@class,'mat-list-item mat-focus-indicator')]//div["+colunmIndex+"]");//("//mat-list-item["+index+"][contains(@class,'mat-list-item separator')]//div[8]");

			}
			
			
			public final By PM_Transcription_video_ReadingSpeed = By.xpath("//app-combo-box//div//shared-reading-speed//span[contains(@class,'reading-speed')]");//("//app-combo//div//shared-reading-speed//span[contains(@class,'reading-speed')]");
			public final By PM_Transcription_video_LineCount = By.xpath("//app-combo-box//shared-line-counter//span");//("//app-combo//shared-line-counter//span");
			public final By PM_Transcription_video_TimeOut= By.xpath("//app-combo-box//input[contains(@placeholder,'Time Out')]");//("//app-combo//input[contains(@placeholder,'Time Out')]");
			public final By PM_Transcription_video_TimeIn= By.xpath("//app-combo-box//input[contains(@placeholder,'Time In')]");//("//app-combo//input[contains(@placeholder,'Time In')]");
			public final By PM_Transcription_video_Duration= By.xpath("//app-combo-box//input[contains(@name,'duration')]");//("//app-combo-box//div[contains(@class,'mat-form-field-infix')]//input[contains(@name,'duration')]");
			
			/*public final By PM_Transcription_ReadingSpeed_Id(int index){
				return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//div[contains(@class,'id-selected')]");
			}*/
			
			public final By PM_Transcription_ReadingSpeed_Id(int index){
				return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//div[contains(text(),'"+index+"')]");
			}
			
			public final By PM_Transcription_tesxtarea_currentRecord= By.xpath("//mat-list-item[contains(@class,'current-record')]");	
			public final By PM_Transcription_tesxtarea_currentRecord(int index) {
				return By.xpath("//mat-list-item[contains(@id,'"+index+"')][contains(@class,'current-record')]");//("//mat-list-item["+index+"][contains(@class,'current-record')]");
			}
			public final By PM_Transcription_tesxtarea_currentPlayingSegment(int index) {
				return By.xpath("//mat-list-item["+index+"][contains(@class,'playing-segment')]");
			}
			public final By PM_Transcription_Video_Styling_button=By.xpath("//mat-tab-group//mat-tab-header//div[contains(text(),'Styling')]");
			public final By PM_Transcription_Video_Styling_onScreenButton=By.xpath("//button//mat-icon[contains(text(),'tv')]");
			public final By PM_Transcription_Video_Styling_BoldFormat_button	= By.xpath("//mat-icon[contains(text(),'format_bold')]");
			public final By PM_Transcription_Video_Styling_ItalicFormat_button	= By.xpath("//mat-icon[contains(text(),'format_italic')]");
			public final By PM_Transcription_Video_Styling_UnderLineFormat_button	=  By.xpath("//mat-icon[contains(text(),'format_underline')]");
			public final By PM_Transcription_Video_Styling_AlignUP_button	=  By.xpath("//mat-icon[contains(text(),'vertical_align_top')]");
			public final By PM_Transcription_Video_Styling_AlignDOWN_button	=  By.xpath("//mat-icon[contains(text(),'vertical_align_bottom')]");
			
			public final By PM_Transcription_Video_Characters_button=By.xpath("//mat-tab-group//mat-tab-header//div[contains(text(),'Characters')]");
			public final By PM_Transcription_Video_Characters_addButton=By.xpath("//button//span[contains(text(),'Add')]");
			public final By PM_Transcription_Video_Characters_add_input=By.xpath("//input[contains(@formcontrolname,'name')]");
			public final By PM_Transcription_Video_AddCharacters_addButton=By.xpath("//mat-dialog-actions//button//span[contains(text(),'ADD')]");
			public final By PM_Transcription_Video_AddCharacters_cancelButton=By.xpath("//mat-dialog-actions//button//span[contains(text(),'CANCEL')]");
			public final By PM_Transcription_Video_AddedCharactersRow(int row,String character) {
				return By.xpath("//mat-tab-body//mat-list-item["+row+"]//div[contains(@class,'item-content')][contains(text(),'"+character+"')]");
			}
			public final By PM_Transcription_Video_AddCharacters_Error=By.xpath("//mat-dialog-container//mat-error[contains(text(),'Character name already exists')]");
			public final By PM_Transcription_Video_AddCharacters_addButton_disabled=By.xpath("//mat-dialog-actions//button[@disabled='true']//span[contains(text(),'ADD')]");
			public final By PM_Transcription_Video_AddCharacters_Message=By.xpath("//label//span[contains(text(),'Insert one or several characters separated with a semicolon')]");
			public final By PM_Transcription_Characters_Arrow(int index) {
				return By.xpath("//mat-list-item["+index+"]//mat-select//div[contains(@class,'mat-select-arrow-wrapper')]");
			}
			public final By PM_Transcription_Characters_Options(String option) {
				return By.xpath("//mat-option//span[contains(@class,'mat-option-text')][contains(text(),'"+option+"')]");
			}
			
			public final By PM_Transcription_Characters_OptionsWithRow(int row,String option) {
				return By.xpath("//mat-option["+row+"]//span[contains(@class,'mat-option-text')][contains(text(),'"+option+"')]");
			}
			public final By PM_Transcription_Characters_Options_Selected(int index,String option) {
				return By.xpath("//mat-list-item["+index+"]//mat-select[contains(@placeholder,'Character')]//span[contains(text(),'"+option+"')]");
			}
			
			
			public final By PM_Transcription_Backward_Shortcut= By.xpath("//tbody//tr//td//span[contains(text(),'alt + ⇧ + →')]");
			public final By PM_Transcription_Forward_Shortcut= By.xpath("//tbody//tr//td//span[contains(text(),'alt + ⇧ + ←')]");
			
			public final By PM_Transcription_NumberOFRows= By.xpath("//mat-list-item[contains(@class,'focus-indicator separator segment')]");
			
			public final By PM_Transcription_Calibration_message = By.xpath("//mat-tab-body//div[contains(@class,'tab-content calibration-content')]//p");
			public final By PM_Transcription_Calibration_TimeFrame= By.xpath("//mat-form-field[contains(@class,'calibration-input')]//input[contains(@name,'frames')]");//("//mat-form-field[contains(@class,'calibration-input')]//input[contains(@ng-reflect-name,'frames')]");
			public final By PM_Transcription_Calibration_TimeSec= By.xpath("//mat-form-field[contains(@class,'calibration-input')]//input[contains(@name,'seconds')]");//("//mat-form-field[contains(@class,'calibration-input')]//input[contains(@ng-reflect-name,'seconds')]");
			public final By PM_Transcription_Calibration_Dialog= By.xpath("//app-calibration-dialog//h1[contains(text(),'Calibration')]");
			public final By PM_Transcription_Calibration_Dialog_Calibrate_Button= By.xpath("//mat-dialog-actions//button//span[contains(text(),'CALIBRATE')]");
			public final By PM_Transcription_Calibration_Dialog_Calibrate_Cancel_Button= By.xpath("//mat-dialog-actions//button//span[contains(text(),'CANCEL')]");
			
			public final By PM_Transcription_tesxtarea_Arrow_Upward(int index) {
				return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//mat-icon[contains(text(),'arrow_upward')]");	
			}
			
			public final By PM_Transcription_Video_Subtitle_top	=  By.xpath("//div[contains(@class,'top')]");	
			public final By PM_Transcription_Video_Subtitle_bottom	=  By.xpath("//div[contains(@id,'overlay')]");	
			public final By PM_Transcription_Video_leftArrow	=  By.xpath("//mat-tab-header//div[1][contains(@class,'pagination')]//div[contains(@class,'mat-tab-header-pagination-chevron')]");
			public final By PM_Transcription_Video_rightArrow	=  By.xpath("//mat-tab-header//div[3][contains(@class,'pagination')]//div[contains(@class,'mat-tab-header-pagination-chevron')]");
			
			public final By PM_Transcription_QualityCheck_button=  By.xpath("//mat-tab-header//div[contains(text(),'Quality Check')]");	
			public final By PM_Transcription_TaskSetting_button=  By.xpath("//mat-tab-header//div[contains(text(),'Task Setting')]");
			public final By PM_Transcription_TaskSetting_info(String data) {
				return By.xpath("//div[contains(@class,'settings-content')]//mat-list-item//span[contains(text(),'"+data+"')]");
			}
			public final By PM_Transcription_rightArrow =By.xpath("//mat-tab-header//div[3]//div[contains(@class,'chevron')]");
			public final By PM_Transcription_QualityCheck_RUN_button=  By.xpath("//button//span[contains(text(),'Run')]");
			public final By qualitycheck_rule_violation_message_for_Min_RS= By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'Reading speed is too low')]");
			public final By qualitycheck_rule_violation_message_for_Max_RS= By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'Reading speed is too high')]");
			public final By qualitycheck_number_of_issues= By.xpath("//mat-chip-list//mat-chip[contains(text(),'issues')]");
			public final By qualitycheck_rule_violation_mesaages= By.xpath("//quality-check//mat-nav-list[contains(@class,'mat-nav-list')]");//By.xpath("//mat-nav-list[contains(@class,'mat-nav-list')]");//("//mat-list-item[contains(@class,'qc-list-item')]");
			public final By PM_Transcription_Styling_button=By.xpath("//div[contains(text(),'Styling')]");
			public final By PM_Transcription_Styling_SegmentHistory=By.xpath("//mat-list-item[contains(@class,'style-list')]//mat-icon[contains(text(),'comment')]");
			public final By PM_Transcription_segmentHistoryTab_commentInput=By.xpath("//mat-form-field//textarea[contains(@formcontrolname,'comment')]");//("//mat-form-field//textarea[contains(@ng-reflect-name,'comment')]");
			public final By PM_Transcription_segmentHistoryTab_commentDialogBox_CommentButton=By.xpath("//mat-dialog-actions//SPAN[contains(text(),'COMMENT')]");
			public final By PM_Transcription_segmentHistoryTab_commentDialogBox_CancelButton=By.xpath("//mat-dialog-actions//SPAN[contains(text(),'CANCEL')]");
			public final By PM_Transcription_commentTab=By.xpath("//div[contains(text(),'Comments')]");
			
			public final By taskInfo_minDurationOfSub=By.xpath("//mat-list-item//span[contains(text(),'Min duration of a sub')]");
			public final By taskInfo_maxDurationOfSub=By.xpath("//mat-list-item//span[contains(text(),'Max duration of a sub')]");
			public final By taskInfo_minCountOfFramesBetweenSubs=By.xpath("//mat-list-item//h4[contains(text(),'Min count of frames between subs')]");
			public final By PM_Transcription_commentIds(int id,String comment) {
				return By.xpath("//mat-list-item//span[(text()="+id+")]//following-sibling::span[contains(text(),'"+comment+"')]");
			}
			
			public final By PM_Transcription_SegmentIds(int id) {
				return By.xpath("//mat-list-item//span[(text()="+id+")]");
			}
			public final By PM_Transcription_SegmentIds_CharLimitations(int id) {
				return By.xpath("//mat-list-item//span[(text()="+id+")]/..//span[contains(text(),'Character Limitation')]");
			}
			public final By PM_Transcription_SegmentIds_segmentText(int id,String text) {
				return By.xpath("//mat-list-item//span[(text()="+id+")]//following-sibling::span[contains(text(),'"+text+"')]");
			}
			public final By PM_Transcription_currentSegment(int id) {
				return By.xpath("//mat-list-item[contains(@id,"+id+")][contains(@class,'current-record')]//div[contains(@class,'editor-read-only')]");
			}
			
			public final By PM_Transcription_threeSegmentAfterChangingFeatures(int index,String id) {
				return By.xpath("//transcription-table//mat-list-item["+index+"]//div[contains(text(),'"+id+"')]");
			}
			
			public final By PM_Transcription_SegmentId(int Id) {
				return By.xpath("//mat-list-item[(@id='"+Id+"')]");
			}
			
			//Task setting locators
			public final By PM_Transcription_openJob_TaskSetting_linePerSub(int lines) {
				return By.xpath("//mat-list-item//span[contains(text(),'Max lines per sub')]//span[contains(text(),'"+lines+"')]");
			}
			
			
			
			public final By qualitycheck_rule_violation_mesaages_for_segment(int index){
				return By.xpath("//quality-check//mat-nav-list[contains(@class,'mat-nav-list')]//span[contains(text(),'"+index+"')]");//By.xpath("//mat-nav-list[contains(@class,'mat-nav-list')]//span[contains(text(),'"+index+"')]");
			}	
			
			public final By PM_Transcription_Video_default_speed=  By.xpath("//div[contains(@id,'video-controls')]//div[contains(@class,'flex')]//span[contains(@class,'playback-rate')]");//("//div[contains(@class,'overlay-speed')]");
			
			
			
			public final By PM_Transcription_shortcutFor_arrowLeft	=  By.xpath("//tr//td[contains(text(),'Slow play / Super slow play')]");//tr[3]
			public final By PM_Transcription_shortcutFor_arrowRight	=  By.xpath("//tr//td[contains(text(),'Fast play / Super fast play')]");//("//tr//td[contains(text(),'Play - play faster')]");//tr[2]
			
			public final By PM_Transcription_KeyBoardShortcut=  By.xpath("//transcription-shortcut-handler//h4[contains(text(),'Keyboard Shortcuts')]");//("//h4[contains(text(),'Keyboard Shortcuts:')]");
			public final By qualitycheck_rule_violation_message_for_EmptySubtitle= By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'Empty Subtitle')]");
			public final By qualitycheck_rule_violation_message(int id,String error) {
				return By.xpath("//mat-list-item//span[contains(@class,'col-spacing')][(text()='"+id+"')]//following-sibling::span[contains(text(),'"+error+"')]");
			}
			
			public final By qualitycheck_rule_violation_message_for_TooManyLines= By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'Too many lines')]");	
			public final By PM_Transcription_Video_Edit_Mode=  By.xpath("//app-transcription-video//shared-text-editor//div[not(@hidden)]");//("//app-transcription-video[contains(@ng-reflect-focus,'true')]");
			public final By PM_Transcription_Video_Preview_Mode_text(String segmentText) {
				return By.xpath("//shared-text-editor//div[contains(text(),'"+segmentText+"')]");//("//app-transcription-video[contains(@ng-reflect-focus,'false')]");
			}
			
			public final By Pm_user_OpenSubmission_Readingspeed_input=By.xpath("//input[@placeholder='Reading speed']");
			public final By qualitycheck_rule_violation_message_forIndex_TooManyLines(int index) {
				return By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'Too many lines')]//preceding-sibling::span[contains(text(),'"+index+"')]");	
			}
			public final By Pm_user_OpenSubmission_Update_button=By.xpath("//span[text()='UPDATE']");
			
			
			public final By PM_transcription_keyboard_shortcuts = By.xpath("//span[contains(text(),'Keyboard Shortcuts')]");
			public final By table_keyboard_shortcuts = By.xpath("//div[contains(@class,'cfp-hotkeys-container fade in')]//table//tbody");
			public final By table_keyboard_shortcuts_row = By.xpath("//div[contains(@class,'cfp-hotkeys-container fade in')]//table//tbody//tr");
			public final By table_keyboard_shortcuts_column= By.xpath("//div[contains(@class,'cfp-hotkeys-container fade in')]//table//tbody//tr//td");
			public final By table_keyboard_shortcuts_cancel = By.xpath("//div[contains(@class,'cfp-hotkeys-close')]");
			
			public final By PM_transcription_Find_and_replace = By.xpath("//h1[contains(text(),'Find and Replace')]");	
			public final By PM_transcription_NumberOfOccurences = By.xpath("//h5[contains(text(),'occurrences found.')]");	
			public final By PM_transcription_Find_and_replace_FINDINPUTBOX = By.xpath("//input[contains(@placeholder,'Find')]");
			public final By PM_transcription_Find_and_replace_REPLACEBUTTON = By.xpath("//button[3]//span[contains(text(),'REPLACE')]");
			public final By PM_transcription_Find_and_replace_FINDBUTTON = By.xpath("//span[contains(text(),'FIND')]");
			public final By PM_transcription_Find_and_replace_CANCELBUTTON = By.xpath("//span[contains(text(),'CANCEL')]");
			public final By PM_transcription_Find_and_replace_REPLACEBY = By.xpath("//input[contains(@placeholder,'Replace by')]");	
			public final By PM_transcription_DeleteSegment = By.xpath("//h1[contains(text(),'Delete Segments')]");	
			public final By PM_transcription_DeleteSegment_fromSubID = By.xpath("//input[contains(@placeholder,'From Subtitle ID')]");	
			public final By PM_transcription_DeleteSegment_ToSubId = By.xpath("//input[contains(@placeholder,'To Subtitle ID')]");  
			public final By PM_transcription_DeleteSegment_DeleteButton= By.xpath("//span[contains(text(),'DELETE')]");	
			public final By PM_transcription_Move_Segment = By.xpath("//h3[contains(text(),'Move Segments')]");
			public final By PM_transcription_MoveSegment_fromSubID = By.xpath("//mat-dialog-content//p[contains(text(),'From')]/..//input[contains(@name,'from')]");//By.xpath("//input[contains(@placeholder,'From Subtitle ID')]");	
			public final By PM_transcription_MoveSegment_ToSubId = By.xpath("//mat-dialog-content//p[contains(text(),'To')]/..//input[contains(@name,'to')]");//By.xpath("//input[contains(@placeholder,'To Subtitle ID')]");	
			public final By PM_transcription_MoveSegment_AddRemove = By.xpath("//input[contains(@placeholder,'Add/Remove Frames')]");
			public final By PM_transcription_MoveSegment_MoveButton = By.xpath("//span[contains(text(),'MOVE')]");
			
			
			//Filters
			public final By filters_clearFilters=By.xpath("//button//span[contains(text(),'Clear filters')]");
			
			public final By filters_organization_dropDown=By.xpath("//mat-select[contains(@formcontrolname,'organization')]//div[contains(@class,'arrow-wrapper')]");//("//mat-select[contains(@ng-reflect-name,'organization')]//div[contains(@class,'arrow-wrapper')]");
			public final By filters_status_dropDown=By.xpath("//mat-select[contains(@formcontrolname,'status')]//div[contains(@class,'arrow-wrapper')]");//("//mat-select[contains(@ng-reflect-name,'status')]//div[contains(@class,'arrow-wrapper')]");
			public final By filters_language_dropDown=By.xpath("//mat-select[contains(@formcontrolname,'sourceLang')]//div[contains(@class,'arrow-wrapper')]");//("//mat-select[contains(@ng-reflect-name,'sourcelang')]//div[contains(@class,'arrow-wrapper')]");
			public final By filters_clearFilterButton_enabled=By.xpath("//app-submissions-native-table//button[not(contains(@disabled,'true'))]//span[contains(text(),'Clear filters')]");//("//app-submissions-native-table//button[contains(@ng-reflect-disabled,'false')]//span[contains(text(),'Clear filters')]");//("//app-submissions-table//button[contains(@ng-reflect-disabled,'false')]//span[contains(text(),'Clear filters')]");
			public final By filters_clearFilterButton_disabled=By.xpath("//app-submissions-native-table//button[contains(@disabled,'true')]//span[contains(text(),'Clear filters')]");//("//app-submissions-native-table//button[contains(@ng-reflect-disabled,'false')]//span[contains(text(),'Clear filters')]");
			public final By filters_clearFilterButton = By.xpath("//app-submissions-native-table//button//span[contains(text(),'Clear filters')]");
			
			
			public final By filters_selectOption(String option){
				return By.xpath("//div[contains(@class,'transformPanel')]//mat-option//span[contains(text(),'"+option+"')]");
			}
			
			
			public final By submmission_file_download =By.xpath("//mat-icon[contains(text(),'file_download')]");	
			public final By submmission_FileDownload_SRT= By.xpath("//button[text()='SRT']");
			public final By submmission_FileDownload_TTML= By.xpath("//button[text()='TTML1']");
			public final By submmission_FileDownload_EBU= By.xpath("//button[text()='EBU-TT-D']");
			
			
			public final By Trans_ongoing_multiOption_icon=By.xpath("//mat-icon[contains(text(),'more_vert')]");
			public final By Trans_Ongoing_Import_file=By.xpath("//span[contains(text(),'Import File')]");
			public final By Trans_Ongoing_Importfile_ByCaptionNumber=By.xpath("//div[contains(@class,'transformMenu')]//button[contains(text(),'By caption numbers')]");
			public final By Trans_Ongoing_Importfile_ByTimecode=By.xpath("//div[contains(@class,'transformMenu')]//button[contains(text(),'By timecode')]");
			public final By Trans_ongoing_overwrite_current_translation = By.xpath("//span[contains(text(),'OVERWRITE')]");

			public final By Trans_ongoing_video_multiOption_icon=By.xpath("//shared-video//mat-icon[contains(text(),'more_vert')]");
			public final By Trans_ongoing_video_multiOption_options(String option) {
				return By.xpath("//div[contains(@class,'mat-menu-content')]//button[contains(text(),'"+option+"')]");
			}
			
			
            public final By transcription_ongoing_waterMark=By.xpath("//app-transcription-video//span[contains(text(),'prashant.dongare80@gmail.com')]");
            public final By transcription_ongoing_videoPauseButton=By.xpath("//app-transcription-video//div[contains(@class,'play-pause-box')]//mat-icon[contains(text(),'pause')]");
            public final By transcription_ongoing_videoPlayButton=By.xpath("//app-transcription-video//div[contains(@class,'play-pause-box')]//mat-icon[contains(text(),'play')]");
            public final By transcription_ongoing_video_seconds=By.xpath("//app-time-box//div[contains(@class,'time-code')]//span[5]");
            public final By transcription_ongoing_video_frames=By.xpath("//app-time-box//div[contains(@class,'time-code')]//span[7]");

            //Filter Submission Locators
            
            public final By PM_submission_organizationFilter=By.xpath("//mat-select[contains(@formcontrolname,'organization')]");//("//mat-select[contains(@ng-reflect-name,'organization')]");
            public final By PM_submission_organizationFilter_dropDownOptions(String org) {
            	return By.xpath("//mat-option[contains(@class,'mat-option')]//span[text()=' "+org+" ']");
            }

            public final By PM_submission_statusFilter=By.xpath("//mat-select[contains(@formcontrolname,'status')]");//("//mat-select[contains(@ng-reflect-name,'status')]");
            public final By PM_submission_statusFilter_dropDownOptions(String status) {
            	return By.xpath("//mat-option[contains(@class,'mat-option')]//span[text()=' "+status+" ']");
            }
            
            public final By PM_submission_sourceFilter=By.xpath("//mat-select[contains(@formcontrolname,'sourceLang')]");//("//mat-select[contains(@ng-reflect-name,'sourceLang')]");
            public final By PM_submission_sourceFilter_dropDownOptions(String sourceLang) {
            	return By.xpath("//mat-option[contains(@class,'mat-option')]//span[text()=' "+sourceLang+" ']");
            }
            
            
            
          //Locators for Move Segments(Alt + r)
            public final By PM_transcription_ongoing_MoveSegments_AllSegments=By.xpath("//div[contains(@class,'radio-label')][contains(text(),'All segments')]");
            public final By PM_transcription_ongoing_MoveSegments_segmentSelected=By.xpath("//div[contains(@class,'radio-label')][contains(text(),'Selected segments')]");
        	public final By PM_transcription_ongoing_MoveSegments_fromSubtitleId=By.xpath("//app-move-multiple-segments//input[contains(@placeholder,'Subtitle ID')][contains(@name,'from')]");
        	public final By PM_transcription_ongoing_MoveSegments_toSubtitleId=By.xpath("//app-move-multiple-segments//input[contains(@placeholder,'Subtitle ID')][contains(@name,'to')]");
        	public final By PM_transcription_ongoing_MoveSegments_frames_radioButton=By.xpath("//mat-radio-button//div[contains(text(),'Frames')]//preceding-sibling::div[contains(@class,'mat-radio-container')]");
        	public final By PM_transcription_ongoing_MoveSegments_time_radioButton=By.xpath("//mat-radio-button//div[contains(text(),'Time')]//preceding-sibling::div[contains(@class,'mat-radio-container')]");
        	public final By PM_transcription_ongoing_MoveSegments_add_remove_framesInput=By.xpath("//mat-form-field//input[contains(@name,'frames')]");
        	public final By PM_transcription_ongoing_MoveSegments_add_remove_secondsInput=By.xpath("//mat-form-field//input[contains(@placeholder,'Add/Remove Seconds')]");
        	public final By PM_transcription_ongoing_MoveSegments_cancel=By.xpath("//mat-dialog-actions//button//span[contains(text(),'CANCEL')]");
        	public final By PM_transcription_ongoing_MoveSegments_move=By.xpath("//mat-dialog-actions//button//span[contains(text(),'MOVE')]");
        	public final By PM_transcription_ongoing_MoveSegments_time_hour=By.xpath("//input[contains(@name,'hours')]");
        	public final By PM_transcription_ongoing_MoveSegments_time_min=By.xpath("//input[contains(@name,'minutes')]");
        	public final By PM_transcription_ongoing_MoveSegments_time_sec=By.xpath("//input[contains(@name,'seconds')]");
        	public final By PM_transcription_ongoing_MoveSegments_time_frames=By.xpath("//input[contains(@name,'frames')][contains(@type,'number')]");
        	public final By PM_transcription_ongoing_MoveSegments_forward=By.xpath("//div[contains(@class,'radio-label')][contains(text(),'Forward')]");
        	public final By PM_transcription_ongoing_MoveSegments_backward=By.xpath("//div[contains(@class,'radio-label')][contains(text(),'Backward')]");
        	
        	public final By PM_transcription_find_and_replace_allSegments_selected=By.xpath("//mat-radio-button//label//div[contains(text(),'All segments')]//ancestor::mat-radio-button[contains(@class,'mat-radio-checked')]");
			public final By PM_transcription_find_and_replace_toField_grayedOut=By.xpath("//input[contains(@placeholder,'Subtitle ID')][@name='to'][contains(@aria-invalid,'false')]");
			public final By PM_transcription_find_and_replace_fromField_grayedOut=By.xpath("//input[contains(@placeholder,'Subtitle ID')][@name='from'][contains(@aria-invalid,'false')]");
        	 
			public final By PM_transcription_find_and_replace_time_selected=By.xpath("//mat-radio-button//label//div[contains(text(),'Time')]//ancestor::mat-radio-button[contains(@class,'mat-radio-checked')]");
			public final By PM_transcription_find_and_replace_frameField_grayedOut=By.xpath("//mat-form-field[contains(@class,'mat-form-field-disabled')]//input[@name='frames'][@type='number']");
			
			public final By PM_transcription_find_and_replace_frames_selected=By.xpath("//mat-radio-button//label//div[contains(text(),'Frames')]//ancestor::mat-radio-button[contains(@class,'mat-radio-checked')]");
			public final By PM_transcription_find_and_replace_timeField_grayedOut(String field) {
				return By.xpath("//mat-form-field[contains(@class,'mat-form-field-disabled')]//input[@name='"+field+"']");
			}
			//Alt + c (Calibration)
	         public final By PM_transcription_ongoing_calibration_hour=By.xpath("//mat-form-field//input[contains(@name,'hours')]");
	         public final By PM_transcription_ongoing_calibration_calibrateButton=By.xpath("//app-calibration-dialog//button//span[contains(text(),'CALIBRATE')]");
	         public final By PM_transcription_ongoing_calibration_cancelButton=By.xpath("//app-calibration-dialog//button//span[contains(text(),'CANCEL')]");
	         public final By PM_transcription_ongoing_videoTime_hour=By.xpath("//app-time-box//span[1]");

	         
	         //Mat chip
	         public final By PM_Submission_editView_chips(int index) {
	        	 return By.xpath("//mat-chip-list//mat-chip["+index+"]");
	        	 
	         }
	         public final By PM_Transcription_textarea_sixOptions_ReadingSpeed(int index){
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//shared-reading-speed//span[contains(@class,'reading-speed')]");
				}
	         
	         
	         
	         //PD TAB LOCATORS
	         public final By Pm_User_ProjectDirector_Tab=By.xpath("//div[contains(text(),'Project Director')]");//("//span[contains(text(),'Project Director')]");
	         public final By Pm_User_New_PD_Submission_Button=By.xpath("//button//span[contains(text(),'NEW PD SUBMISSION')]");
	         public final By PD_Submission_Filter_Status=By.xpath("//mat-select[contains(@formcontrolname,'status')]");
	         public final By PD_Submission_Filter_Status_Options(String options) {
	        	 return By.xpath("//mat-option[contains(@class,'option')]//span[contains(text(),'"+options+"')]");
	         }
	         
	         
	         public final By Pm_Submission_download_dropdownArrow=By.xpath("//mat-form-field//mat-select[contains(@placeholder,'Choose a format')]");
	         public final By Pm_Submission_download_dropdownOption(String option) {
	         	return By.xpath("//div[contains(@class,'mat-select-panel')]//mat-option//span[contains(text(),'"+option+"')]");//Audio Scripts (DOCX, XLSX)
	         }
	         public final By Pm_Submission_download_languageCheckbox(String language) {
	         	return By.xpath("//mat-cell[contains(text(),'"+language+"')]");
	         }
	         public final By Pm_Submission_download_file_button=By.xpath("//button//span[contains(text(),'DOWNLOAD')]");
	         
}



