package locators;

import org.openqa.selenium.By;

public class PD_Pm_User_Submission_Locators {
	
	private static PD_Pm_User_Submission_Locators pd_pm_objects;
	
	 public static synchronized PD_Pm_User_Submission_Locators Locator() {
		  try {
		   if (pd_pm_objects.equals(null)) {
			   pd_pm_objects = new PD_Pm_User_Submission_Locators();
		   }
		  } catch (Exception NOSYSTEM) {
			  pd_pm_objects = new PD_Pm_User_Submission_Locators();
		  }
		  return pd_pm_objects;
		 }
	 
	 public final By logIn_with_emailAddress=By.xpath("//button//span[contains(text(),'Login with your email addres')]");
	 
	 public final By username_input=By.xpath("//input[@name='Email']");
		public final By Email_input=By.xpath("//input[@placeholder='Email Address']");
		public final By continue_button=By.xpath("//button[text()='Continue']");
		public final By password_input=By.xpath("//input[@placeholder='Password']");
		public final By signin_button=By.xpath("//button[text()='Sign In']");
		
		
		public final By loginWithLocalAccount=By.xpath("//div[contains(@class,'login-with-local')]//a[contains(text(),'Login with local account')]");
		public final By admin_username_input=By.xpath("//input[@name='username']");
		public final By admin_password_input=By.xpath("//input[@name='password']");
		public final By admin_loginButton=By.xpath("//button[contains(@id,'loginwindow-button')]//span[contains(text(),'Login')]");
		//Log Out
		
		public final By userIcon = By.xpath("//span[contains(@id,'AppSettingsAccountButton')][contains(@class,'btn-icon-el')]");
		public final By userIcon_options_logout=By.xpath("//div[contains(@class,'menu')]//span[contains(text(),'Logout')]");
		public final By userIcon_options_logout_alert=By.xpath("//div[contains(text(),'Are you sure you want to log out?')]");
		public final By userIcon_options_logout_yesButton=By.xpath("//span[contains(text(),'Yes')]");
		public final By userIcon_options_logout_noButton=By.xpath("//span[contains(text(),'No')]");
		
	 
	 public final By Pm_user_SearchResult(String SubmissionName){
			return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]");
				}
	 public final By Pm_user_CreateSubmission_pdmt_instance=By.xpath("//mat-form-field//mat-select[contains(@placeholder,'Choose an instance')]");
		public final By Pm_user_CreateSubmission_pdmt_instanceDropDownOptions(String instance) {
			return By.xpath("//mat-option[contains(@id,'mat-option')]//span[contains(text(),'"+instance+"')]");
		}
		public final By Pm_user_CreateSubmission_pdmt_instance_selectedOption=By.xpath("//mat-form-field//mat-select[contains(@placeholder,'Choose an instance')]//span[contains(@class,'ng-star-inserted')][not(contains(@class,'mat-select-value'))]");
		
		public final By Pm_user_CreateSubmission_pdmt_department=By.xpath("//mat-form-field//input[contains(@placeholder,'Choose a department')]");
		public final By Pm_user_CreateSubmission_pdmt_departmentDropDownOptions(String department) {
			return By.xpath("//mat-option[contains(@id,'mat-option')]//span[contains(text(),'"+department+"')]");
		}
		public final By Pm_user_CreateSubmission_pdmt_department_selectedOption=By.xpath("");
		public final By Pm_user_CreateSubmission_pdmt_workflow=By.xpath("//mat-form-field//input[contains(@placeholder,'Choose a workflow')]");
		public final By Pm_user_CreateSubmission_pdmt_workflowDropDownOptions(String workflow) {
			return By.xpath("//mat-option[contains(@id,'mat-option')]//span[contains(text(),'"+workflow+"')]");
		}
		
		public final By Pm_user_CreateSubmission_pdmt_client=By.xpath("//mat-form-field//input[contains(@placeholder,'Choose a Project A Client')]");
		public final By Pm_user_CreateSubmission_clientDropDownOptions(String client) {
			return By.xpath("//mat-option[contains(@id,'mat-option')]//span[contains(text(),'"+client+"')]");
		}
		public final By Pm_user_CreateSubmission_pdmt_jobNo=By.xpath("//mat-form-field//input[contains(@placeholder,'Job Number')]");
		public final By Pm_user_CreateSubmission_pdmt_nextButton=By.xpath("//mat-expansion-panel[contains(@class,'mat-expanded')]//span[contains(text(),'Next')]");
	
		public final By check_Pm_submission_status(String SubmissionName, String status){
			return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+status+"')]");

			}
		
		public final By check_Pm_submission_id(String SubmissionName, String id){
			return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+id+"')]");

			}
		public final By check_Pm_submission_id(int row, int cell) {
			return By.xpath("//mat-row["+row+"]//mat-cell["+cell+"]");
		}
		
		public final By check_sub_with_creationDate(String submissionName, String creationDate){
			return By.xpath("//mat-row//mat-cell//span[contains(text(),'"+submissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+creationDate+"')]");
}
		
		public final By check_sub_with_dueDate(String submissionName, String dueDate){
			return By.xpath("//mat-row//mat-cell//span[contains(text(),'"+submissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+dueDate+"')]");
}
	
	//PD TAB LOCATORS
    public final By Pm_User_ProjectDirector_Tab=By.xpath("//div[contains(text(),'Project Director')]");
    public final By Pm_User_ProjectDirector_Tab_barColor=By.xpath("//div[contains(@class,'mat-tab-list')]//div[contains(text(),'Project Director')]//ancestor::div//div[contains(@class,'mat-tab-list')]//mat-ink-bar[contains(@class,'mat-ink-bar')]");
    public final By Pm_User_MEDIANext_Tab=By.xpath("//mat-tab-header//div[contains(text(),'Media.NEXT')]");
    public final By Pm_User_MEDIANext_Tab_barColor=By.xpath("//div[contains(@class,'mat-tab-list')]//div[contains(text(),'Media.NEXT')]//ancestor::div//div[contains(@class,'mat-tab-list')]//mat-ink-bar[contains(@class,'mat-ink-bar')]");
    public final By Pm_User_mediaNext_Tab_active=By.xpath("//div[contains(@class,'label-active')]//div[contains(text(),'Media.NEXT')]");
    public final By Pm_User_ProjectDirector_Tab_active=By.xpath("//div[contains(@class,'label-active')]//div[contains(text(),'Project Director')]");
    public final By Pm_User_New_PD_Submission_Button=By.xpath("//button//span[contains(text(),'NEW PD SUBMISSION')]");
    public final By PD_Submission_Filter_Status=By.xpath("//mat-select[contains(@formcontrolname,'status')]");
    public final By PD_Submission_Filter_Status_Options(String options) {
   	 return By.xpath("//mat-option[contains(@class,'option')]//span[contains(text(),'"+options+"')]");
    }
    
    
    public final By PD_Submission_createInPD_button=By.xpath("//mat-icon[contains(text(),'open_in_new')]");
    public final By PD_Submission_createInPD_cancelButton=By.xpath("//button//span[contains(text(),'CANCEL')]");
    public final By PD_Submission_createInPD_createSubmissionInPDButton=By.xpath("//button//span[contains(text(),'CREATE SUBMISSION IN PD')]");
    public final By PD_Submission_createInPD_dialogBox_sumissionName(int index,String name) {
    	return By.xpath("//mat-dialog-container//mat-list-item["+index+"]//div[contains(text(),'"+name+"')]");
    }
    public final By PD_Submission_createInPD_dialogBox_allFilesInOneBatch_checked=By.xpath("//mat-dialog-container//mat-radio-button[contains(@class,'mat-radio-checked')]//div[contains(text(),'All files in one batch')]");
    public final By PD_Submission_createInPD_dialogBox_allFilesInOneBatch_unchecked=By.xpath("//mat-dialog-container//mat-radio-button[contains(@class,'radio-button mat-accent')]//div[contains(text(),'All files in one batch')]");
    public final By PD_Submission_createInPD_dialogBox_oneFilesInOneBatch_unchecked=By.xpath("//mat-dialog-container//mat-radio-button[contains(@class,'radio-button mat-accent')]//div[contains(text(),'One file per batch')]");
    
    public final By filters_clearFilterButton_enabled=By.xpath("//app-submissions-pd-table//button[not(contains(@disabled,'true'))]//span[contains(text(),'Clear filters')]");
    public final By filters_clearFilterButton = By.xpath("//app-submissions-pd-table//button//span[contains(text(),'Clear filters')]");
    
    public final By PD_Submission_edit_button=By.xpath("//mat-icon[contains(text(),'mode_edit')]");
    public final By PD_Submission_clone_button=By.xpath("//mat-icon[contains(text(),'content_copy')]");
    public final By PD_Submission_delete_button=By.xpath("//mat-icon[contains(text(),'delete')]");
    public final By PD_Submission_download_button=By.xpath("//mat-icon[contains(text(),'file_download')]");
    public final By PD_Submission_compare_button=By.xpath("//mat-icon[contains(text(),'compare_arrows')]");
    
    public final By PD_Submission_download_file_button=By.xpath("//button//span[contains(text(),'DOWNLOAD')]");
    
    
    public final By PD_Submission_download_dropdownArrow=By.xpath("//mat-form-field//mat-select[contains(@placeholder,'Choose a format')]");
    public final By PD_Submission_download_dropdownOption(String option) {
    	return By.xpath("//div[contains(@class,'mat-select-panel')]//mat-option//span[contains(text(),'"+option+"')]");//Audio Scripts (DOCX, XLSX)
    }
    public final By PD_Submission_download_languageCheckbox(String language) {
    	return By.xpath("//mat-cell[contains(text(),'"+language+"')]");
    }
    
    public final By PD_Submission_compare_dialogHeaders(String header) {
    	return By.xpath("//mat-dialog-container//mat-header-cell[contains(text(),'"+header+"')]");
    }
    
    public final By PD_Submission_compare_dialog_Versions(int index,String information ) {
    	return By.xpath("//mat-dialog-container//mat-cell["+index+"][contains(text(),'"+information+"')]");
    }
    
    public final By PD_Submission_edit_settings_snapToShotChangeCheckbox_unchecked=By.xpath("//mat-checkbox//input[contains(@aria-checked,'false')]/../..//span[contains(text(),'Snap to shot changes')]");
    public final By PD_Submission_edit_settings_snapToShotChangeCheckbox_checked=By.xpath("//mat-checkbox//input[contains(@aria-checked,'true')]/../..//span[contains(text(),'Snap to shot changes')]");
    
    
    //Edit PD Submision locators
    public final By PD_Submission_editView_panel_SubmissionName(String submissionName) {
    	return By.xpath("//mat-list-item//h4[contains(text(),'Name: "+submissionName+"')]");
    }
    public final By PD_Submission_editView_panel_PDID(String id) {
    	return By.xpath("//mat-list-item//h4[contains(text(),'PD ID: "+id+"')]");
    }
    public final By PD_Submission_editView_panel_client(String client) {
    	return By.xpath("//mat-list-item//h4[contains(text(),'Client: "+client+"')]");
    }//Source Language: en-US
    public final By PD_Submission_editView_panel_sourceLanuage(String sourceLanguage) {
    	return By.xpath("//mat-list-item//h4[contains(text(),'Source Language: "+sourceLanguage+"')]");
    }
    public final By PD_Submission_editView_panel_captionCount(String captionCount) {
    	return By.xpath("//mat-list-item//h4[contains(text(),'Caption count: "+captionCount+"')]");
    }
    public final By PD_Submission_editView_panel_wordCount(String wordCount) {
    	return By.xpath("//mat-list-item//h4[contains(text(),'Word count: "+wordCount+"')]");
    }
    
    ////mat-panel-title[contains(text(),'Video Details')]
    
    public final By PD_Submission_editView_panel_videoDetails=By.xpath("//mat-expansion-panel-header[not(contains(@class,'mat-expanded'))]//mat-panel-title[contains(text(),'Video Details')]");
    public final By PD_Submission_editView_panel_globalSetting=By.xpath("//mat-expansion-panel-header[not(contains(@class,'mat-expanded'))]//mat-panel-title[contains(text(),'Global Settings')]");
    public final By PD_Submission_editView_panel_PDConnector=By.xpath("//mat-expansion-panel-header[not(contains(@class,'mat-expanded'))]//mat-panel-title[contains(text(),'PD Connector')]");
    
    public final By PD_Submission_editView_panel_minReadingSpeed(String speed) {
    	return By.xpath("//mat-list-item//h4[contains(text(),'Min reading speed: "+speed+"')]");
    }
    public final By PD_Submission_editView_panel_maxReadingSpeed(String speed) {
    	return By.xpath("//mat-list-item//h4[contains(text(),'Max reading speed: "+speed+"')]");
    }
    public final By PD_Submission_editView_panel_maxCharPerLine(String chars) {
    	return By.xpath("//mat-list-item//h4[contains(text(),'Max chars per line: "+chars+"')]");
    }
    
    public final By PD_Submission_editView_panel_maxLinePerSubtitler(String lines) {
    	return By.xpath("//mat-list-item//h4[contains(text(),'Max lines per subtitle: "+lines+"')]");
    }
    public final By PD_Submission_editView_panel_minSubtitlerDuration(String duration) {
    	return By.xpath("//mat-list-item//h4[contains(text(),'Min subtitler duration: "+duration+"')]");
    }
    public final By PD_Submission_editView_panel_maxSubtitlerDuration(String duration) {
    	return By.xpath("//mat-list-item//h4[contains(text(),'Max subtitler duration: "+duration+"')]");
    }
    public final By PD_Submission_editView_panel_dropFrames=By.xpath("//mat-list-item//h4[contains(text(),'Drop frame: false')]");
    public final By PD_Submission_editView_panel_minCountOfFramesBetweenSub(String count) {
    	return By.xpath("//mat-list-item//h4[contains(text(),'Min count of frames between subs: "+count+"')]");
    }
    
    public final By PD_Submission_editView_panel_snapToChanges=By.xpath("//mat-list-item//h4[contains(text(),'Snap to shot changes: false')]");//"Snap to shot changes: false");
    
    
    public final By PD_Submission_editView_panel_videoDetails(String data) {
    	return By.xpath("//submission-video-details-pd//h4[contains(text(),'"+data+"')]");
    }
    public final By PD_Submission_streamURL(String URL) {
    	return By.xpath("//submission-video-details-pd//div[contains(@class,'stream-url')][contains(text(),'"+URL+"')]");
    }
    
    public final By PD_Submission_editView_panel_PDConnectorDetails(String data) {
    	return By.xpath("//submission-pd-connector//h4[contains(text(),'"+data+"')]");
    }
    
    public final By PD_Submission_editView_panel_rightTabs(String header) {
    	return By.xpath("//mat-tab-body//mat-card-subtitle//span[contains(text(),'"+header+"')]");
    }
    
    
    //PD-TDC LOCATORS
    public final By PD_submission_chooseFilter_input=By.xpath("//input[contains(@name,'fieldLabelCombo')]");
    public final By PD_submission_filter_arrow=By.xpath("//input[contains(@placeholder,'Choose filter')]/../../div[contains(@class,'form-arrow')]");
    public final By PD_submission_filter_options(String option) {
    	return By.xpath("//ul[contains(@class,'list-plain')]//li[contains(@class,'boundlist-item')][contains(text(),'"+option+"')]");//(" //li[contains(@class,'boundlist')][contains(text(),'"+option+"')]')]");//Submission ID
    
    }
    public final By PD_submission_filter_input=By.xpath("//input[contains(@name,'numberFieldFilter')]");
 
    public final By PD_searchSubmission_firstRow(String submissionId) {
    	return By.xpath("//table//td[contains(@role,'gridcell')]//div[contains(text(),'"+submissionId+"')]");
    	
    }
    
    
    ////a[contains(@id,'edit_submission')]
    ////a[contains(@id,'button')]//span[contains(text(),'Custom Attributes')]
    
    public final By PD_submission_onHoldButton=By.xpath("//span[contains(@id,'trnavigationgroupfolderbutton')]//span[contains(text(),'On Hold')]");
    
  //div[@name='SubCategory']//div[contains(@class,'arrow')]
    public final By PD_submission_activeButton=By.xpath("//div[contains(@id,'trnavigationgrouptoolbar')]//a[1]//span[contains(text(),'Active')]");
    public final By PD_submission_editSubmission=By.xpath("//a[contains(@id,'edit_submission')]"); 
    public final By PD_submission_editSubmission_workflow(String workflow) {
    	return By.xpath("//div[contains(@class,'EditSubmissionDialog-batchPanel')]//tr//td[2]//span[contains(text(),'"+workflow+"')]");
    }
    
    public final By PD_submission_customAttributes=By.xpath("//a[contains(@id,'button')]//span[contains(text(),'Custom Attributes')]"); 
    
    public final By PD_submission_customAttributes_customFields(String customFields) {
    	return By.xpath("//div[contains(@id,'pdcustomfieldsdialog')]//label//span[contains(text(),'"+customFields+"')]/../..//input");
    }
    public final By PD_submission_ReferenceFile=By.xpath("//a[contains(@id,'button')]//span[contains(text(),'Reference Files')]");
    public final By PD_submission_ReferenceFile_checked(String fileName) {
    	return By.xpath("//table//tr[contains(@class,'grid-tree-node-leaf')]//div[contains(@class,'tree-checkbox-checked')]//following-sibling::span[contains(text(),'"+fileName+"')]");
    }
    
    public final By PD_submission_edit_batchFile=By.xpath("//div[contains(@id,'pdeditsubmissiondialog')]//a[@class='batchLink']");
    public final By PD_submission_edit_batchFile_oneFilesPerBatch(String submissionName) {
    	return By.xpath("//div[contains(@id,'pdeditsubmissiondialog')]//a[@class='batchLink'][(text()='"+submissionName+"')]");
    }
    public final By PD_submission_edit_batchFile_submission_ttmlFiles(int index,String SubmssionName) {
    	return By.xpath("//div[contains(@id,'batchpreviewdialogedit')]//table["+index+"]//div[contains(text(),'"+SubmssionName+"')]");
    }
    
    
    public final By PD_submission_ReferenceFile_downloadIcon=By.xpath("//div[contains(@class,'icon_download')]");
    
    public final By PD_submission_subjectMatter(String option) {
    	return By.xpath("//li[contains(@role,'option')]//div[contains(text(),'"+option+"')]");
    }
    public final By PD_submission_subjectMatter_arrow=By.xpath("//div[@name='SubjectMatter']//div[contains(@class,'arrow')]");
    public final By PD_submission_subjectCategory(String option) {
    	return By.xpath("//li[contains(@role,'option')]//div[contains(text(),'"+option+"')]");
    }
    public final By PD_submission_subjectCategory_framesBetweenSubs_input=By.xpath("//div[contains(@id,'pdcustomfieldsdialog')]//label//span[contains(text(),'MinCountOfFramesBetweenSubs')]/../..//input");
    
    
    public final By PD_submission_subjectCategory_arrow=By.xpath("//div[@name='SubCategory']//div[contains(@class,'arrow')]");
    public final By PD_submission_reCreatedPDF(String option) {
    	return By.xpath("//li[contains(@role,'option')]//div[contains(text(),'"+option+"')]");
    }
    
    public final By PD_submission_reCreatedPDF_arrow=By.xpath("//div[@name='RecreatedPDF']//div[contains(@class,'arrow')]");
    
    public final By PD_Submission_customAttributes_okButton=By.xpath("//div[contains(@id,'pdcustomfieldsdialog')]//span[contains(text(),'OK')]");
    
    public final By PD_submission_editSubmission_saveButton=By.xpath("//span[(text()='Save')]");
    public final By PD_submission_editSubmission_batch_saveButton=By.xpath("//div[contains(@id,'batchpreviewdialogedit')]//span[(text()='Save')]");
    
    
    public final By PD_submission_analyzeButton=By.xpath("//a[contains(@id,'analyze_submission')]"); 
    public final By PD_submission_refreshButton=By.xpath("//span[(text()='Refresh')]"); 
    public final By PD_submission_analyze_yesButton=By.xpath("//span[(text()='Yes')]");
    public final By PD_submission_analyze_noButton=By.xpath("//span[(text()='No')]"); ////table[contains(@id,'treeview')]//div[contains(text(),'39')]
    
    public final By PD_submission_wordCount(String count) {
    	return By.xpath("//table[contains(@id,'treeview')]//div[contains(text(),'"+count+"')]");
    }
    
    
    public final By PD_submission_createBudget=By.xpath("//a[contains(@id,'create_budget')]");
    public final By PD_submission_createBudget_window=By.xpath("//div[contains(text(),'Create budget by phases')]");
    public final By PD_submission_createBudget_revenue=By.xpath("//div[contains(@name,'revenue')]//input[contains(@class,'form-invalid')][contains(@name,'revenue')]");
    
    public final By PD_submission_createBudget_trans_rateUSD=By.xpath("//div[contains(@id,'createbudget')]//div[contains(@class,'x-panel x-fit-item x-panel-default')][not(contains(@style,'display: none'))]//table//td[10]");//("//fieldset[contains(@class,'x-box-item x-fieldset')]//table[contains(@id,'gridview')]//td[10]");
    public final By PD_submission_createBudget_trans_rateUSD_input=By.xpath("//table[contains(@class,'grid-item')]//tr[2]//td[10]//div[contains(@name,'numberfield')]//input[contains(@id,'numberfield')][contains(@aria-disabled,'false')]");//("//input[@id='numberfield-1578-inputEl']");
    public final By PD_submission_createBudget_review_rateUSD=By.xpath("//div[contains(@id,'createbudget')]//div[contains(@class,'x-panel x-fit-item x-panel-default')][not(contains(@style,'display: none'))]//fieldset[contains(@aria-label,'REVIEW PHASE field set')]//table//td[10]");//("//fieldset[contains(@class,'x-box-item x-fieldset')]//table[contains(@id,'gridview')]//td[10]//div[contains(@id,'ext-element-66')]");
    public final By PD_submission_createBudget_review_rateUSD_input=By.xpath("//table[contains(@class,'grid-item')]//tr[2]//td[10]//div[contains(@name,'numberfield')]//input[contains(@id,'numberfield')][contains(@aria-disabled,'false')]");
    public final By PD_submission_createBudget_batch =By.xpath("//fieldset[contains(@class,'x-box-item x-fieldset')]//table[contains(@id,'gridview')]//div[contains(text(),'Batch1')]");
    public final By PD_submission_createBudget_NextButton=By.xpath("//span[contains(text(),'Next')]");
    public final By PD_submission_createBudget_finishButton=By.xpath("//span[contains(text(),'Finish')]");
    
    
    
    public final By PD_submission_createBudget_translationPhase_rateUSD=By.xpath("//div[contains(@id,'createbudget')]//div[contains(@class,'x-panel x-fit-item x-panel-default')][not(contains(@style,'display: none'))]//fieldset[contains(@aria-label,'TRANSLATION PHASE field set')]//table//td[10]");
    public final By PD_submission_createBudget_translationPhase_rateUSD_input=By.xpath("//table[contains(@class,'grid-item')]//tr[2]//td[10]//div[contains(@name,'numberfield')]//input[contains(@id,'numberfield')][contains(@aria-disabled,'false')]");//("//input[@id='numberfield-1578-inputEl']");
    public final By PD_submission_createBudget_reviewPhase_rateUSD=By.xpath("//div[contains(@id,'createbudget')]//div[contains(@class,'x-panel x-fit-item x-panel-default')][not(contains(@style,'display: none'))]//fieldset[contains(@aria-label,'REVIEW PHASE field set')]//table//td[10]");//("//fieldset[contains(@class,'x-box-item x-fieldset')]//table[contains(@id,'gridview')]//td[10]//div[contains(@id,'ext-element-66')]");
    public final By PD_submission_createBudget_reviewPhase_rateUSD_input=By.xpath("//table[contains(@class,'grid-item')]//tr[2]//td[10]//div[contains(@name,'numberfield')]//input[contains(@id,'numberfield')][contains(@aria-disabled,'false')]");
    
    
    public final By PD_submission_selectVendors= By.xpath("//span[contains(@class,'icon_doublerightarrow')]");//("//a[2][contains(@class,'submission_vendor_select_Action_right')]//span[contains(@class,'icon_doublerightarrow')]");
    public final By PD_submission_selectVendors_applyButton=By.xpath("//span[contains(text(),'Apply')]");
    public final By PD_submission_selectVendors_OKButton=By.xpath("//div[contains(@class,'client_select_vendor_Dialog')]//span[contains(text(),'OK')]");//("//span[contains(text(),'OK')]");//("//div[contains(@class,'plain x-window-default-plain x-closable x-win')]//span[contains(text(),'OK')]");
    public final By PD_submission_selectVendors_close=By.xpath("//div[contains(@class,'close')]");
    
    
    public final By PD_submission_createBudget_informationAlert=By.xpath("//div[contains(text(),'Phases without vendors')]");
    public final By PD_submission_createBudget_informationAlert_ok=By.xpath("//*[@id='button-1801-btnInnerEl']");
    public final By PD_submission_createBudget_informationAlert_close=By.xpath("");
    
    
    public final By PD_submission_startSubmission_alert=By.xpath("//div[contains(text(),'Start Submission')]");
    public final By PD_submission_startSubmission_alert_Yes=By.xpath("//span[contains(text(),'Yes')]");
    public final By PD_submission_startSubmission_alert_close=By.xpath("//span[contains(text(),'No')]");
    
    
    public final By PD_submission_Status(String id,String status) {
    	return By.xpath("//tr[1]//td[1]//div[contains(text(),'"+id+"')]/../..//td[12]//div[contains(text(),'"+status+"')]");//180 ,In Progress
    }
    
    
    
    
    
    
    
    //Vendor 1 Locators
    public final By PD_submission_vendor1_available=By.xpath("//span[contains(@id,'trnavigationgroupfolderbutton')]//span[contains(text(),'Available')]");
    public final By PD_submission_vendor1_available_jobInfo=By.xpath("//span[contains(@id,'tab')]//span[contains(text(),'Job Info')]");
    public final By PD_submission_vendor1_available_jobInfo_jobInfoIcon=By.xpath("//span[contains(@id,'pd_job_info_action')]//span[contains(text(),'Job Info')]");
    public final By PD_submission_vendor1_jobInfo_Language (String Language) {
    	return By.xpath("//td[contains(@class,'grid-cell')]//div[contains(text(),'"+Language+"')]");//German (Germany)
    }
    
    public final By PD_submission_vendor1_jovInfo_acceptRadioButton=By.xpath("//div[contains(@id,'SubmissionBudgetJobInfoTabPanel')]//div[1][contains(@id,'SubmissionBudgetJobInfoPhaseGrid')]//table[1]//td[5][contains(@class,'grid-cell')]//div[contains(@class,'grid-radio')]");
    public final By PD_submission_vendor1_jovInfo_declineRadioButton=By.xpath("//div[contains(@id,'SubmissionBudgetJobInfoTabPanel')]//div[1][contains(@id,'SubmissionBudgetJobInfoPhaseGrid')]//table[1]//td[6][contains(@class,'grid-cell')]//div[contains(@class,'grid-radio')]");
    public final By PD_submission_vendor1_jovInfo_confirmCheckbox=By.xpath("//span[contains(@id,'checkbox')]");
    public final By PD_submission_vendor1_jovInfo_submitButton=By.xpath("//span[contains(@id,'button')][contains(text(),'Submit')]");
    public final By PD_submission_vendor1_jovInfo_infoAlter=By.xpath("//div[contains(text(),'This job will be moved to your inbox.')]");
    public final By PD_submission_vendor1_jovInfo_infoAlter_close=By.xpath("//span[contains(@id,'button-1013-btnInnerEl')][contains(text(),'Close')]");//need to change
    public final By PD_submission_vendor1_jovInfo_fileManagement_link(String submission) {
    	return By.xpath("//span[contains(@class,'submission_management_base_component_batchfile_grid_file_Grid_hyperlink')][contains(text(),'"+submission+"')]");
    }
    public final By PD_submission_vendor1_jovInfo_saveButton=By.xpath("//span[(text()='Save')]");
    public final By PD_submission_vendor1_jovInfo_close=By.xpath("//div[contains(@class,'close')]");
    
    
    public final By PD_submission_vendor1_sent=By.xpath("//span[contains(@id,'trnavigationgroupfolderbutton')]//span[contains(text(),'Sent')]");
    public final By PD_submission_vendor1_progressBar=By.xpath("//div[contains(text(),'100%')]");
    public final By PD_submission_vendor1_completed_color=By.xpath("//div[contains(@class,'Util-divTemplate pd-progress-color')]");
    
    
    public final By vendor1_submissionName_header(String  submissionName){
		return By.xpath("//mat-toolbar//span[contains(text(),'"+submissionName+"')]");
	}
    
    
    
  //Vendor 2 Locators
    public final By PD_submission_vendor2_available=By.xpath("//span[contains(@id,'trnavigationgroupfolderbutton')]//span[contains(text(),'Available')]");
    public final By PD_submission_vendor2_available_jobInfo=By.xpath("//span[contains(@id,'tab')]//span[contains(text(),'Job Info')]");
    public final By PD_submission_vendor2_available_jobInfo_jobInfoIcon=By.xpath("//span[contains(@id,'pd_job_info_action')]//span[contains(text(),'Job Info')]");
    public final By PD_submission_vendor2_jobInfo_Language (String Language) {
    	return By.xpath("//td[contains(@class,'grid-cell')]//div[contains(text(),'"+Language+"')]");//German (Germany)
    }
    
    public final By PD_submission_vendor2_jovInfo_acceptRadioButton=By.xpath("//div[contains(@id,'SubmissionBudgetJobInfoTabPanel')]//div[1][contains(@id,'SubmissionBudgetJobInfoPhaseGrid')]//table[1]//td[5][contains(@class,'grid-cell')]//div[contains(@class,'grid-radio')]");
    public final By PD_submission_vendor2_jovInfo_declineRadioButton=By.xpath("//div[contains(@id,'SubmissionBudgetJobInfoTabPanel')]//div[1][contains(@id,'SubmissionBudgetJobInfoPhaseGrid')]//table[1]//td[6][contains(@class,'grid-cell')]//div[contains(@class,'grid-radio')]");
    public final By PD_submission_vendor2_jovInfo_confirmCheckbox=By.xpath("//span[contains(@id,'checkbox')]");
    public final By PD_submission_vendor2_jovInfo_submitButton=By.xpath("//span[contains(@id,'button')][contains(text(),'Submit')]");
    public final By PD_submission_vendor2_jovInfo_infoAlter=By.xpath("//div[contains(text(),'This job will be moved to your inbox.')]");
    public final By PD_submission_vendor2_jovInfo_infoAlter_close=By.xpath("//span[contains(@id,'button-1013-btnInnerEl')][contains(text(),'Close')]");//need to change
    public final By PD_submission_vendor2_jovInfo_fileManagement_link(String submission) {
    	return By.xpath("//span[contains(@class,'submission_management_base_component_batchfile_grid_file_Grid_hyperlink')][contains(text(),'"+submission+"')]");
    }
    public final By PD_submission_vendor2_jovInfo_saveButton=By.xpath("//span[(text()='Save')]");
    public final By PD_submission_vendor2_jovInfo_close=By.xpath("//div[contains(@class,'close')]");
    
    
    public final By PD_submission_vendor2_completed=By.xpath("//span[contains(@id,'trnavigationgroupfolderbutton')]//span[contains(text(),'Completed')]");
    public final By PD_submission_vendor2_progressBar=By.xpath("//div[contains(text(),'100%')]");
    public final By PD_submission_vendor2_completed_color=By.xpath("//div[contains(@class,'Util-divTemplate pd-progress-color')]");
    
    public final By vendor2_submissionName_header(String  submissionName){
		return By.xpath("//mat-toolbar//span[contains(text(),'"+submissionName+"')]");
	}
    
    public final By vendor2_submissionName_completedChecked(String submissionName) {
    	return By.xpath("//table//td[contains(@role,'gridcell')]//div[contains(text(),'"+submissionName+"')]/../..//div[contains(@class,'icon_checkedsign')]");
    }
    
    public final By SelectMultiSubmissions_Checkbox=By.xpath("//mat-header-cell//mat-checkbox[contains(@id,'checkbox')]");
		
    
    public final By PD_submission_pm_completed=By.xpath("//div[contains(@id,'gs4trnavigationgrouptoolbar')]//a[5]//span[contains(text(),'Completed')]");
    public final By PD_submission_pm_progressBar(String submissionId) {
    	return By.xpath("//table//td[contains(@role,'gridcell')]//div[contains(text(),'"+submissionId+"')]/../..//div[contains(text(),'100')]");
    }
    public final By PD_submission_pm_completed_color=By.xpath("//div[contains(@class,'Util-divTemplate pd-progress-color')]");
    

    public final By SelectMenu(String menuname){
		return By.xpath("//h4[text()='"+menuname+"']/../..//mat-icon");
	}
    
    
    public final By PD_submission_clientFilter_input=By.xpath("//mat-select[contains(@formcontrolname,'organization')]");
    public final By PD_submission_clientFilter_option(String client) {
    	return By.xpath("//mat-option//span[contains(@class,'mat-option-text')][contains(text(),'"+client+"')]");
    }
    public final By PD_submission_statusFilter_input=By.xpath("//mat-select[contains(@formcontrolname,'status')]");
    public final By PD_submission_StatusFilter_option(String client) {
    	return By.xpath("//mat-option//span[contains(@class,'mat-option-text')][contains(text(),'"+client+"')]");
    }
    public final By PD_submission_sourceFilter_input=By.xpath("//mat-select[contains(@formcontrolname,'sourceLang')]");
    public final By PD_submission_SourceFilter_otion(String client) {
    	return By.xpath("//mat-option//span[contains(@class,'mat-option-text')][contains(text(),'"+client+"')]");
    }
    
    public final By PD_organizations=By.xpath("//span[contains(@id,'gs4trnavigationgroupfolderbutton')]//span[contains(text(),'Organizations')]");
    public final By PD_projectTab=By.xpath("//span[contains(@id,'gs4trnavigationgroupfolderbutton')]//span[contains(text(),'Projects')]");
    public final By PD_project_chooseFilter_input=By.xpath("//input[contains(@name,'fieldLabelCombo')]");
    public final By PD_project_filter_arrow=By.xpath("//input[contains(@placeholder,'Choose filter')]/../../div[contains(@class,'form-arrow')]");
    public final By PD_project_filter_options(String option) {
    	return By.xpath("//ul[contains(@class,'list-plain')]//li[contains(@class,'boundlist-item')][contains(text(),'"+option+"')]");//(" //li[contains(@class,'boundlist')][contains(text(),'"+option+"')]')]");//Submission ID
    
    }
    public final By PD_project_filter_input=By.xpath("//input[contains(@name,'textFieldFilter')]");
    
    public final By PD_searchProject_firstRow(String projectName) {
    	return By.xpath("//table//td[contains(@role,'gridcell')]//div[(text()='"+projectName+"')]");//("//table//td[contains(@role,'gridcell')]//div[contains(text(),'"+submissionId+"')]");
    	
    }
    
    public final By PD_project_copyButton=By.xpath("//span[contains(@id,'pd_copy_project_action')]//span[contains(text(),'Copy Project')]");
    public final By PD_project_copyProject_input=By.xpath("//input[contains(@id,'pdAdminProjectCopyProjectName')]");
    public final By PD_project_copyProject_okButton=By.xpath("//span[contains(text(),'OK')]");
    public final By PD_project_copyProject_cancelButton=By.xpath("//span[contains(text(),'Cancel')]");
    public final By PD_project_refreshButton=By.xpath("//span[(text()='Refresh')]"); 
    
    public final By PD_project_enableDisable_button=By.xpath("//span[contains(@id,'pd_enable_project_action')]//span[contains(text(),'Enable/Disable Project')]");
    public final By PD_project_disablePopUp=By.xpath("//div[contains(text(),'Project disabled')]");
    public final By PD_project_disablePopUp_continueButton=By.xpath("//span[contains(text(),'Continue')]");
    
    
    
    
    //tpt authLogout page 
    public final By PD_tptAuthLogoutPage_yesButton=By.xpath("//form[contains(@action,'/Account/Logout')]//button[text()='Yes']");
    public final By PD_tptAuthLogoutPage_here=By.xpath("//a[contains(@title,'LogIn')][text()='here']");
    
    
  
    
}
