package locators;

import org.openqa.selenium.By;

public class ReviewerLocators {
	
	private static ReviewerLocators review_objects;
	
	 public static synchronized ReviewerLocators Locator() {
		  try {
		   if (review_objects.equals(null)) {
			   review_objects = new ReviewerLocators();
		   }
		  } catch (Exception NOSYSTEM) {
			  review_objects = new ReviewerLocators();
		  }
		  return review_objects;
		 }

	 
	 //ADD LOCATORS
	 
	//Search Submission in Review user
		 public final By review_search_input=By.xpath("//input[@placeholder='Filter by name']");	 
		 
		 public final By reviewerSearchResult(String SubmissionName){
				return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]");//("//md-cell[contains(text(),'"+SubmissionName+"')]");
			}
	
	 
		public final By colunm_Name(String colunmName){
			return By.xpath("//mat-table//div//button[contains(text(),'"+colunmName+"')]");
		}
		
		public final By Ascending_sort(String colunmName){
			return By.xpath("//mat-table//div[button[contains(text(),'"+colunmName+"')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
             }
		//public final By Descending_sort=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'descending')]]");
		public final By descending_sort(String colunmName){
			return By.xpath("//mat-table//div[button[contains(text(),'"+colunmName+"')]]//div[../following-sibling::span[contains(text(),'descending')]]");
		}
		
		//Select Submission checkbox
				public final By SelectSubmission_Checkbox(String Submission_Name){
					return By.xpath("//mat-cell//span[contains(text(),'"+Submission_Name+"')]/../preceding-sibling::mat-cell//mat-checkbox[contains(@id,'checkbox')]");
				     }
				
				//select header checkbox in review  toClaim tab
				public final By review_toClaim_select_header_checkbox=By.xpath("//mat-header-cell//mat-checkbox[contains(@id,'checkbox')]");
				
				public final By review_toClaim_checkbox_checked=By.xpath("//mat-header-cell//mat-checkbox[contains(@class,'checkbox-checked')]");
				
				//Assign Submission	
				public final By reviewer_clamSubmission_icon=By.xpath("//mat-icon[contains(text(),'assignment_returned')]");
				
				public final By reviewer_claimAlert_claim_button=By.xpath("//span[contains(text(),'CLAIM')]");
				
				//Select Target_Lang of Submission in review_Ongoing tab
				public final By check_review_Target_Lang(String SubmissionName, String target){
					return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]");

					}
				
				public final By reviewer_selectSubmission_checkbox(String SubmissionName,String target){
					//return By.xpath("//mat-cell[contains(text(),'"+SubmissionName+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
					//return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");//("//mat-cell[contains(text(),'"+SubmissionName+"')]/following-sibling::mat-cell[contains(text(),'"+target+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
				    //return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'mat-checkbox-background')]");
				    return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
				}
				
				
				//Ongoing Submission in Reviewer User
			    public final By reviewer_toClaim_ListofallIds=By.xpath("//mat-list-item[contains(@class,'separator ng-star-inserted')]");//By.xpath("//mat-list-item[contains(@class,'separator mat-list-item ng-star-inserted')]");//NOTE:- work on that locator.
				public final By reviewer_Submission_Edit_icon=By.xpath("//mat-icon[contains(text(),'mode_edit')]");//("//mat-icon[@mattooltip='Edit']");
				public final By reviewer_Complete_Button=By.xpath("//span[contains(text(),' COMPLETE TASK ')]");
				public final By reviewer_CompleteDailoge_Button=By.xpath("//mat-dialog-container//mat-dialog-actions//button//span[text()='COMPLETE TASK']");
				public final By reviewer_CompleteDailoge_cancelButton=By.xpath("//mat-dialog-actions//button//span[contains(text(),'CANCEL')]");
				public final By reviewer_completeTask_message(String message) {
					return By.xpath("//mat-dialog-content//p[contains(text(),'"+message+"')]");
				}
				public final By reviewer_completedTask_completeTaskPopUp=By.xpath("//simple-snack-bar//span[contains(text(),'Task now marked as completed.')]");
				
				public final By reviewer_BackToSubmissionList_Button=By.xpath("//review-toolbar//mat-toolbar//button//mat-icon[contains(text(),'menu')]");

				
				public final By reviewer_CopySource_toTarget_Button=By.xpath("//mat-icon[contains(text(),'content_copy')]");//By.xpath("//button[contains(@mattooltip,'Copy target to modified target')]");
				public final By reviewer_CopySource_toTarget_Button_tooltip_message=By.xpath("//div[contains(@id,'message-container')]//div[contains(text(),'Copy Target to Modified Target')]");//By.xpath("//button[contains(@ng-reflect-message,'Ctrl + S')]");//By.xpath("//button[contains(@mattooltip,'Copy target to modified target')]");
				
				/*public final By reviewer_ModifiedTargetSegement(int index){
					return By.xpath("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::div[3]");
				}*/
				public final By reviewer_ModifiedTargetSegement_textarea(int index){
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]");//("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::textarea[contains(@id,'textarea')]");
				}
				
				
				public final By  reviewer_segments_characterList(int row,String character) {
					return By.xpath("//mat-list-item["+row+"]//div[contains(@class,'small character')][contains(text(),'"+character+"')]");
				}
				
				public final By reviewer_ModifiedTargetSegement_textarea_x(int index){
					return By.xpath("//mat-list-item[(@id='"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]");//("//mat-list-item[contains(@id,'"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]");//("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::textarea[contains(@id,'textarea')]");
				}
				public final By multiReviewer_ModifiedTargetSegement_textarea(int index){
					return By.xpath("//mat-list-item[(@id='"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]");//("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::textarea[contains(@id,'textarea')]");
				}
				public final By review_ongoing_delete_icon_button=By.xpath("//review-table//mat-icon[contains(text(),'delete')]");
				public final By review_ongoing_delete_icon_mattooltip = By.xpath("//div[contains(@id,'message-container')]//div[contains(text(),'Clear the text (Alt + X)')]");//("//button[contains(@ng-reflect-message,'Clear the text (Alt + X)')]");
				
				public final By reviewer_ModifiedTargetSegement_textarea_line(int index){
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//div[contains(@class,'ng-star-inserted')]//span[contains(text(),'L')]");
				}
				
				public final By reviewer_TargetSegement_textarea_textformat(String format,String modifiedtargetText){
					return By.xpath("//shared-text-editor/div//"+format+"[contains(text(),'"+modifiedtargetText+"')]");
					
				}
				
				public final By reviewe_source_readyOnly(int index) {
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//app-text-segment[contains(@id,'source')]//div[contains(@class,'container')]/div[(@class='backdrop')]");//("//mat-list-item[contains(@id,'"+index+"')]//app-text-segment//div[contains(@aria-readonly,'true')]");
				}
				
				public final By reviewe_target_readyOnly(int index) {
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//div[contains(@class,'editor-read-only')]");
				}
				
				public final By reviewer_ModifiedTargetSegement_textarea_RS=By.xpath("//div[contains(@class,'rs-above')]//span[contains(@class,'reading-spee')]");	
				
				public final By reviewer_TargetSegement_textarea(int index){
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//div[contains(@class,'editor-read-only')][2]");//("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::textarea[contains(@id,'textarea')]");
				}
				
				public final By review_ongoing_multiOption_icon= By.xpath("//mat-icon[contains(text(),'more_vert')]");
				public final By review_file_download =By.xpath("//mat-icon[contains(text(),'file_download')]");	
				public final By review_FileDownload_SRT= By.xpath("//button[contains(text(),'SRT')][1]");
				public final By review_ongoing_multiOption_ExportCurrentarget=By.xpath("//span[contains(text(),'Export current target')]");
				public final By review_TargetSegement_FileDownLoad_format(String FileFormatText){
					return By.xpath("//button[contains(text(),'"+FileFormatText+"')][1]");
				}
				public final By review_ongoing_multiOption_compareVersions=By.xpath("//span[contains(text(),'Compare versions')]");
				public final By review_ongoing_multiOption_ExportWord=By.xpath("//span[contains(text(),'Export to Word')]");
				public final By review_ongoing_multiOption_compareVersions_timeIn(int index) {
					return By.xpath("//mat-dialog-container//mat-list-item["+index+"]//div[contains(@class,'item-content')]//div[3]");//("//mat-dialog-container//mat-list-item["+index+"]//div[contains(@class,'data-list-header')]");
				}
				public final By review_ongoing_multiOption_compareVersions_deletedSegment(String segment) {
					return By.xpath("//div[contains(@class,'segment-deleted')][contains(text(),'"+segment+"')]");//("//mat-cell[contains(@class,'segment-deleted')][contains(text(),'"+segment+"')]");
				}
				
				public final By review_ongoing_multiOption_compareVersions_download=By.xpath("//span[contains(text(),'DOWNLOAD')]");
				public final By review_ongoing_multiOption_compareVersions_cancel=By.xpath("//span[contains(text(),'CANCEL')]");
				
				public final By review_ongoing_deleteSegmentMessage=By.xpath("//app-delete-segment-confirmation//p[contains(text(),'Are you sure you want to delete this subtitle?')]");
				public final By review_ongoing_deleteSegment_cancel=By.xpath("//mat-dialog-actions//span[contains(text(),'CANCEL')]/..");
				public final By review_ongoing_deleteSegment_delete=By.xpath("//mat-dialog-actions//span[contains(text(),'DELETE')]/..");
				
				public final By review_ongoing_TimeIn(int index) {
					return By.xpath("//mat-list-item["+index+"]//div[9]//span[1]");
				}
				
				public final By review_ongoing_TimeOut(int index) {
					return By.xpath("//mat-list-item["+index+"]//div[9]//span[2]");
				}
				
				public final By review_ongoing_ReadingSpeed(int index) {
					return By.xpath("//mat-list-item["+index+"]//shared-reading-speed//span[contains(@class,'reading-speed')]");
				}
				
				public final By review_segmentLength_L(int index) {
					return By.xpath("//mat-list-item["+index+"]//shared-line-counter/div/div[contains(@class,'ng-star-inserted')]//span");
					
				}
				public final By review_duration(int index) {
					return By.xpath("//cdk-virtual-scroll-viewport//mat-list-item["+index+"]//div[10][contains(@class,'font-small')]");
				}
					
				//public final String REVIEWER_DOWNLOADFILE_PATH= "This PC:\\Downloads";
				public final By reviewer_cancel_current_edit_tooltip_message =By.xpath("//div[contains(@id,'message-container')]//div[contains(text(),'Cancel current edit (Esc)')]");//By.xpath("//button[contains(@mattooltip,'Cancel current edit')]");//By.xpath("//button[contains(@mattooltip,'Esc')]");// By.xpath("//button[contains(@mattooltip,'Cancel current edit')]");
			    public final By reviewer_cancel_current_edit= By.xpath("//mat-icon[contains(text(),'undo')]");
				public final By review_modifiedTarget_Line = By.xpath("//span[contains(text(),'L')]");
				public final By reviewer_delete_icon_tooltip_message = By.xpath("//div[contains(@id,'message-container')]//div[contains(text(),'Clear the text (Alt + X)')]");// By.xpath("//button[contains(@ng-reflect-message,'Clear the text");//By.xpath("//button[contains(@ng-reflect-message,'Clear the text (Alt + X)')]");//By.xpath("//button[contains(@mattooltip,'Clear the text')]");
			    public final By reviewer_delete_icon = By.xpath("//mat-icon[contains(text(),'delete')]");
			    
			    //quality check locators
			    
			    public final By review_ongoing_tabes_leftArrow=By.xpath("//div[contains(@class,'pagination-before')]//div[contains(@class,'mat-tab-header-pagination-chevron')]");
				public final By review_ongoing_tabes_rightArrow=By.xpath("//div[contains(@class,'pagination-after')]//div[contains(@class,'mat-tab-header-pagination-chevron')]");
			    public final By review_ongoing_qualitycheck_button=By.xpath("//div[contains(@class,'tab-label-content')][contains(text(),'Quality Check')]");
				public final By review_ongoing_taskInfo_button=By.xpath("//div[contains(@class,'tab-label-content')][contains(text(),'Task info')]");
				public final By review_ongoing_comment_button=By.xpath("//div[contains(@class,'tab-label-content')][contains(text(),'Comments')]");
			    public final By review_ongoing_qualitycheck_Run_button = By.xpath("//span[contains(text(),'Run')]");
			    public final By review_ongoing_qualitycheck_table=By.xpath("//mat-nav-list//cdk-virtual-scroll-viewport[contains(@class,'cdk-virtual-scroll-viewport')]//mat-list-item");
				public final By qualitycheck_norule_violation_mesaage= By.xpath("//p[contains(text(),'There is no rule violation')]");
				public final By qualitycheck_number_of_issues= By.xpath("//mat-chip-list//mat-chip[contains(text(),'issues')]");
				public final By qualitycheck_rule_violation_mesaages= By.xpath("//mat-nav-list//div//mat-list-item//span/following-sibling::span");//("//mat-list-item[contains(@class,'qc-list-item')]");
				public final By qualitycheck_rule_violation_message_for_bold= By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'Bold tags count not consistent from Source to Target')]");
				public final By qualitycheck_message_before_run= By.xpath("//p[contains(text(),'Quality Check has not been run yet')]");
				public final By qualitycheck_rule_violation_message_for_Min_RS= By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'Reading speed is too low')]");
				public final By qualitycheck_rule_violation_message_for_Max_RS= By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'Reading speed is too high')]");
				public final By taskInfo_minCountOfFrames=By.xpath("//shared-job-info//mat-list-item[7]//h4");
				public final By taskInfo_minDurationOfSub=By.xpath("//mat-list-item//h4[contains(text(),'Min duration of a sub')]");
				public final By taskInfo_maxDurationOfSub=By.xpath("//mat-list-item//h4[contains(text(),'Max duration of a sub')]");
				public final By taskInfo_minCountOfFramesBetweenSubs=By.xpath("//mat-list-item//h4[contains(text(),'Min count of frames between subs')]");
				public final By taskInfo_maxLinePerSub=By.xpath("//mat-list-item//h4[contains(text(),'Max lines per sub')]");
				public final By taskInfo_charPerLine=By.xpath("//mat-list-item//h4[contains(text(),'Max chars per line')]");
				public final By qualitycheck_rule_violation_mesaages_for_segment(int index){
					return By.xpath("//mat-nav-list[contains(@class,'mat-nav-list')]//span[contains(text(),'"+index+"')]");
				}	
				
				public final By review_qualityChecks_rules(String index, String rules) {
					return By.xpath("//mat-nav-list//mat-list-item//span[contains(text(),'"+index+"')]//following-sibling::span[contains(text(),'"+rules+"')]");
				}
				public final  By review_segmentLength_L_multipleLines_L1=By.xpath("//mat-list-item[1]//div[contains(@class,'mat-list-item')]//div[8]//span[contains(text(),'L1')]");
				public final By review_segmentLength_L_multipleLines(int index) {
					return By.xpath("//mat-list-item[1]//shared-line-counter/div/div[contains(@class,'ng-star-inserted')]["+index+"]");//("//mat-list-item[1]//shared-line-counter[contains(@ng-reflect-content,'Transcription')]/div/div[contains(@class,'ng-star-inserted')]["+index+"]");
					
				}
				
				public final By review_readingSpeed_value(int index){
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//span[contains(@class,'reading-speed')]");
				}
				
				public final By modifiedtarget_AN_tag_upward(int index){
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//mat-icon[contains(text(),'arrow_upward')]");
				}
				
				public final By modifiedtarget_AN_tag_vertical_align_top= By.xpath("//mat-icon[contains(text(),'vertical_align_top')]");
				public final By modifiedtarget_AN_tag_vertical_align_bottom= By.xpath("//mat-icon[contains(text(),'vertical_align_bottom')]");
				
				public final By qualityCheck_EmptySubtitle_message = By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'1')]/following-sibling::span");
				public final By qualityCheck_EmptyMT_message = By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'2')]/following-sibling::span[contains(text(),'Empty Subtitle')]");//("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'2')]/following-sibling::span");
				public final By qualityCheck_TCopytosource_message = By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'3')]/following-sibling::span");
				public final By qualityCheck_MTCopytosource_message = By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'4')]/following-sibling::span");
				public final By qualityCheck_charLimitation_message = By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'5')]/following-sibling::span");
				public final By qualityCheck_MultiLine_message = By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'6')]/following-sibling::span");
				public final By qualityCheck_wrongreadingSpeed_message = By.xpath("//mat-nav-list//div//mat-list-item//span/following-sibling::span[contains(text(),'Reading speed is too high')]");
			
				//go to subtitle locators
				public final By review_GoToSubtitle = By.xpath("//input[contains(@placeholder,'Go to subtitle ID')]");//By.xpath("//div[contains(@class,'input-infix')]//label[contains(text(),'Subtitle ID')]/following-sibling::input");
				public final By review_GoToSubtitle_Go_button = By.xpath("//button//span[contains(text(),'GO')]");//By.xpath("//button//b[contains(text(),'GO')]");
				public final By review_GoToSubtitle_Close_button = By.xpath("//button//span[contains(text(),'CANCEL')]");//By.xpath("//button//b[contains(text(),'CLOSE')]");
				public final By review_GoToSubtitle_transc_textarea=By.xpath("//shared-text-editor//div[contains(@class,'mousetrap')]");
				public final By review_GoToSubtitle_message=By.xpath("//mat-hint[contains(@class,'mat-hint')]");
				
				public final By SubtitleRowId(int id){
					return By.xpath("//mat-list-item[contains(@id,'"+id+"')][contains(@class,'current-record')]"); //By.xpath("//mat-list-item[contains(@id,'"+id+"')][contains(@class,'playing-segment')]");
				}		
				

				//Find and replace
				 public final By review_Find_and_Replace = By.xpath("//input[contains(@placeholder,'Find')]");
				 public final By review_Find_and_Replace_Close = By.xpath("//span[contains(text(),'CANCEL')]");//By.xpath("//span[contains(text(),'CLOSE')]");
				 public final By review_NumberOfOccurences = By.xpath("//h5[contains(text(),'occurrences found.')]");	
				 public final By review_Find_and_replace_findInputBox = By.xpath("//input[contains(@placeholder,'Find')]");
				 public final By review_Find_and_replace_replaceButton = By.xpath("//button[3]//span[contains(text(),'REPLACE')]");
				 public final By review_Find_and_replace_findButton = By.xpath("//span[contains(text(),'FIND')]");
				 public final By review_Find_and_replace_cancelButton = By.xpath("//span[contains(text(),'CANCEL')]");
				 public final By review_Find_and_replace_replaceBy = By.xpath("//input[contains(@placeholder,'Replace by')]");
				// For Tranlation( Go to Subtitle ID after Alt + g) 
				public final By Review_GoToSubtitleID_Menu = By.xpath("//label//span[contains(text(),'Go to subtitle ID')]");
				
				// text formatting locators
				public final By keyborad_shortcut_save_and_go_to_next = By.xpath("//table//tr[10]");
				public final By time_format = By.xpath("//mat-list-item[contains(@id,'1')]//div[contains(@fxlayout,'column')]//span");
				public final By Text_Formatting_Bold = By.xpath("//mat-icon[contains(text(),'format_bold')]");
				public final By Text_Formatting_Italic = By.xpath("//mat-icon[contains(text(),'format_italic')]");
				public final By Text_Formatting_Underline = By.xpath("//mat-icon[contains(text(),'format_underlined')]");
				
				public final By review_ongoing_keyboard_shortcuts = By.xpath("//span[contains(text(),'Keyboard Shortcuts')]");
				public final By table_keyboard_shortcuts = By.xpath("//div[contains(@class,'cfp-hotkeys-container fade in')]//table//tbody");
				public final By table_keyboard_shortcuts_row = By.xpath("//div[contains(@class,'cfp-hotkeys-container fade in')]//table//tbody//tr");
				public final By table_keyboard_shortcuts_column= By.xpath("//div[contains(@class,'cfp-hotkeys-container fade in')]//table//tbody//tr//td");
				public final By table_keyboard_shortcuts_cancel = By.xpath("//div[contains(@class,'cfp-hotkeys-close')]");
				
				public final By menu_button= By.xpath("//button[1]//mat-icon[1]");
				public final By GlobalLink_Subtitler_Logo = By.xpath("//mat-toolbar//div[@class='logo']");
				
				public final By review_BackToSubmissionList_Menubutton_forCompleted=By.xpath("//mat-toolbar[1]//button[1]//span[1]//mat-icon[contains(@role,'img')]");
				public final By review_BackToSubmissionList_Button=By.xpath("//mat-toolbar[1]//div//button[1]//span[1]//mat-icon[contains(@role,'img')]");


				public final By review_segmentHistoryTab=By.xpath("//div[contains(@class,'top-action-buttons')]//mat-icon[contains(text(),'comment')]");
				public final By review_segmentHistoryTab_commentInput=By.xpath("//mat-form-field//textarea[contains(@formcontrolname,'comment')]");
				public final By review_segmentHistoryTab_commentDialogBox_CommentButton=By.xpath("//mat-dialog-actions//SPAN[contains(text(),'COMMENT')]");
				public final By review_segmentHistoryTab_commentDialogBox_CancelButton=By.xpath("//mat-dialog-actions//SPAN[contains(text(),'CANCEL')]");
				public final By review_segmentHistoryTab_comment_history=By.xpath("//mat-list/mat-list-item//div[contains(text(),'trans')]//following-sibling::div[contains(@class,'comments')]//div[contains(@class,'text-wrap-lines')]");
				public final By review_segmentHistory_User=By.xpath("//mat-list-item//div[contains(text(),'USER')]");
				public final By review_segmentHistory_Step=By.xpath("//mat-list-item//div[contains(text(),'STEP')]");
				public final By review_segmentHistory_translation=By.xpath("//mat-list-item//div[contains(text(),'TRANSLATION')]");
				public final By review_segmentHistory_historyRows(int row ,String history) {
					return By.xpath("//mat-dialog-content//mat-list[2]//mat-list-item["+row+"]//div[contains(@fxlayout,'row wrap')]//div[contains(text(),'"+history+"')]");
				}
				
				
				public final By review_segment_editIcon(int index) {
					return By.xpath("//cdk-virtual-scroll-viewport//mat-list-item["+index+"]//mat-icon[contains(text(),'edit')]");
				}
				
				public final By review_segmentHistory_commentHistory(int row,String comment) {
					return By.xpath("//mat-dialog-content//mat-list[2]//mat-list-item["+row+"]//div[contains(text(),'"+comment+"')]");
				}
				
				public final By review_currentVersion_history(int row, String header) {
					return By.xpath("//mat-dialog-content//mat-list[1][contains(@class,'header')]//div["+row+"]//p[contains(text(),'"+header+"')]");//("//mat-dialog-content//mat-header-row[1]//mat-header-cell["+row+"]//p[contains(text(),'"+header+"')]");
				}
				
				public final By review_currentVersion_dialogBox_cancelButton= By.xpath("//mat-dialog-actions//span[contains(text(),'CANCEL')]");
				public final By review_currentVersion_dialogBox_downloadButton= By.xpath("//mat-dialog-actions//span[contains(text(),'DOWNLOAD')]");
				
				public final By review_currentSegment(int id) {
					return By.xpath("//mat-list-item[contains(@id,'"+id+"')][contains(@class,'current-record')]");
				}
				
				public final By review_commentIds(int id,String comment) {
					return By.xpath("//mat-list-item//span[(text()="+id+")]//following-sibling::span[contains(text(),'"+comment+"')]");
				}
				
				
				public final By   review_commentTab_SegmentIds(int id) {
					return By.xpath("//mat-list-item["+id+"]//span[(text()="+id+")]");
				}
				
				public final By  review_commentTab_SegmentIds_String(int id) {
					return By.xpath("//mat-list-item//span[(text()="+id+")]/..//span[contains(text(),'Empty Subtitle')]");
				}
				
				
				public final By review_SegmentArea_segmentId(int Id) {
					return By.xpath("//mat-list-item[(@id='"+Id+"')]");
				}
			
				
				public final By review_ongoing_waterMark=By.xpath("//app-review-video//span[contains(text(),'subtitlerreviewer@gmail.com')]");

				 public final By review_ongoing_video_seconds=By.xpath("//app-time-box//div[contains(@class,'time-code')]//span[5]");
		         public final By review_ongoing_video_frames=By.xpath("//app-time-box//div[contains(@class,'time-code')]//span[7]");
		         
		         
		         public final By review_ongoing_indexCaption(String indexCaption) {
						return By.xpath("//button[contains(@class,'mat-button')]//span[contains(text(),'"+indexCaption+"')]");
					}
		         
		         
		        public final By review_Video_pause = By.xpath("//div[contains(@class,'play-pause-box')]//mat-icon[contains(text(),'pause')]");
		 		public final By review_Video_play_arrow = By.xpath("//div[contains(@class,'play-pause-box')]//mat-icon[contains(text(),'play_arrow')]");
		 		
		 		
		 		
		 		
		 		
		 		
		 		public final By review_completedTask_completeTaskPopUp=By.xpath("//simple-snack-bar//span[contains(text(),'Task now marked as completed.')]");
				public final By review_Complete_Button=By.xpath("//mat-toolbar//button//span[contains(text(),'COMPLETE TASK')]");//By.xpath("//mat-icon[contains(text(),'check')]");
				public final By review_CompleteDailoge_Button=By.xpath("//mat-dialog-actions//button//span[contains(text(),'COMPLETE')]");// //mat-dialog-actions//button//span[contains(text(),'COMPLETE TASK')]//By.xpath("//mat-dialog-container//mat-dialog-actions//button//span[text()='COMPLETE TASK']");
				public final By review_CompleteDailoge_completeButton=By.xpath("//mat-dialog-actions//button//span[contains(text(),'COMPLETE')]");
				public final By review_CompleteDailoge_cancelButton=By.xpath("//mat-dialog-actions//button//span[contains(text(),'CANCEL')]");
}