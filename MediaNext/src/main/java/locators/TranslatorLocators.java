package locators;

import modules.Verify;

import org.openqa.selenium.By;

public class TranslatorLocators {
	private static TranslatorLocators trans_objects;
	
	 public static synchronized TranslatorLocators Locator() {
		  try {
		   if (trans_objects.equals(null)) {
			   trans_objects = new TranslatorLocators();
		   }
		  } catch (Exception NOSYSTEM) {
			  trans_objects = new TranslatorLocators();
		  }
		  return trans_objects;
		 }
	 
	
	//Search Submission in Translator user
	 public final By translator_search_input=By.xpath("//input[@placeholder='Filter by name']");
	 
	 
		public final By translatorSearchResult(String SubmissionName){
			return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]");//("//md-cell[contains(text(),'"+SubmissionName+"')]");
		}
		

		//Search
			public final By Trans_user_SearchResult(String SubmissionName){
				return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]");
			}
		
//Assign Submission	
		public final By translator_clamSubmission_icon=By.xpath("//mat-icon[contains(text(),'assignment_returned')]");//("//mat-icon[@mattooltip='Claim task']");//("//md-icon[@mdtooltip='Claim task']");
		
		
		public final By translator_selectSubmission_checkbox(String SubmissionName){
			return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../preceding-sibling::mat-cell//div[contains(@class,'checkbox-inner-container')]");
		}
		
		public final By translator_claimAlert_confirm_button=By.xpath("//app-dialog//button//span[contains(text(),'CLAIM')]");//By.xpath("//app-dialog//button[@md-dialog-close='confirm']");
		
		
	//In-Progress
		public final By translator_OpenSubmission_Text(String SubmissionName){
			//return By.xpath("//md-cell[contains(text(),'"+SubmissionName+"')]/..//md-cell[contains(@class,'column-select')]//md-checkbox[contains(@class,'unchecked')]");
			return By.xpath("//span[contains(text(),'"+SubmissionName+"')]");
		}
		public final By translator_selectSubmissionInprogress_checkbox(String SubmissionName){
			return By.xpath("//md-cell[contains(text(),'"+SubmissionName+"')]/preceding-sibling::md-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]//input");
			//return By.xpath("//md-cell[contains(text(),'"+SubmissionName+"')]/..//md-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
		}
		//public final By Translator_Submission_Edit_icon=By.xpath("//md-icon[@mdtooltip='Edit']");
		
		public final By translator_SourceSegement(int index, String sourceSegment){
			return By.xpath("//mat-list-item["+index+"]//app-text-segment//div[contains(text(),'"+sourceSegment+"')]");
		}
		
		public final By translator_TargetSegement(int index){
			return By.xpath("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::div[3]");
		}
		public final By translator_TargetSegement_textarea(int index){
			return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]");//("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::textarea[contains(@id,'textarea')]");
		}
		public final By translator_TargetSegement_textarea_x(int index){
			return By.xpath("//mat-list-item[(@id='"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]");//("//mat-list-item[contains(@id,'"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]//i");//("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::textarea[contains(@id,'textarea')]");
		}
		public final By translator_duration(int index) {
			return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//div[8][contains(@class,'font-smal')]");
		}
	    public final By translator_TargetSegement_textarea_line(int index){
			return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//div[contains(@class,'ng-star-inserted')]//span[contains(text(),'L')]");
		}
	    
	    
	    public final By translator_GO_to_Subtitle = By.xpath("//h1[contains(text(),'Go to Subtitle')]");
		public final By translator_GO_to_Subtitle_input = By.xpath("//input[contains(@type,'number')]");
		public final By translator_GO_to_Subtitle_GO_Button = By.xpath("//span[contains(text(),' GO')]");
		//mat-list-item[contains(@id,'1')]//div[contains(@class,'ng-star-inserted')]//span[contains(text(),'L')]
		
		public final By translator_TargetSegement_textarea_demo(int index,  String tagname){
			return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]//"+tagname+" ");//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::textarea[contains(@id,'textarea')]");
		}
		 
		/*public final By PM_TargetSegement_textarea(int index){
			return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]");//("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::textarea[contains(@id,'textarea')]");
		}*/
		
		public final By translator_Sourcesegement_textarea(int index){
			return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//app-text-segment//div[not(contains(@contenteditable,'true'))]");//("//mat-list-item[contains(@id,'"+index+"')]//div[contains(@aria-readonly,'true')]");//div[contains(@aria-readonly,'true')]//By.xpath("//mat-list-item[contains(@id,'"+index+"')]//div[contains(@class,'source-read-only')]");//("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::textarea[contains(@id,'textarea')]");
		}
		
		public final By translator_deleteSegmentMessage=By.xpath("//app-delete-segment-confirmation//p[contains(text(),'Are you sure you want to delete this subtitle?')]");
		public final By translator_deleteSegment_cancel=By.xpath("//mat-dialog-actions//span[contains(text(),'CANCEL')]");
		public final By translator_deleteSegment_delete=By.xpath("//mat-dialog-actions//span[contains(text(),'DELETE')]");
		
		public final By traslator_videoScreen_playButton=By.xpath("//div[contains(@id,'video-controls')]//mat-icon[1][contains(text(),'play_arrow')]");
		public final By traslator_videoScreen_pauseButton=By.xpath("//div[contains(@id,'video-controls')]//mat-icon[contains(text(),'pause')]");
		
		
		
		public final By translator_Video_pause = By.xpath("//div[contains(@class,'play-pause-box')]//mat-icon[contains(text(),'pause')]");
		public final By translator_Video_play= By.xpath("//div[contains(@class,'play-pause-box')]//mat-icon[contains(text(),'play_arrow')]");
		
		public final By demo = By.xpath("//shared-text-editor//div[contains(@class,'mousetrap')]");
		
		
		public final By Trans_ongoing_TimeIn(int index) {
			return By.xpath("//mat-list-item["+index+"]//div[7]//span[1]");
		}
		
		
		public final By Trans_ongoing_TimeOut(int index) {
			return By.xpath("//mat-list-item["+index+"]//div[7]//span[2]");
		}
		public final By Trans_ongoing_ReadingSpeed(int index) {
			return By.xpath("//mat-list-item["+index+"]//shared-reading-speed//span[contains(@class,'reading-speed')]");
		}
		
		public final By translator_Listofallsegments_index=By.xpath("//mat-list//td-virtual-scroll-container/..//div//mat-list-item");//By.xpath("//md-list[@role='list']//md-list-item");
		//public final By translator_CopySourceTarget_Button=By.xpath("//button[contains(@mattooltip,'Copy source to target')]");
		public final By translator_cancel_current_edit =By.xpath("//button[contains(@mattooltip,'Cancel current edit (Esc)')]");// By.xpath("//button[contains(@mattooltip,'Esc')]");
		//public final By translator_Complete_Button=By.xpath("//md-icon[text()='done']");
		//public final By translator_CompleteDailoge_Button=By.xpath("//md-dialog-container//md-dialog-actions//button//span[text()='COMPLETE']");
				//md-dialog-actions//button//span[text()='COMPLETE']/following-sibling::div[contains(@class,'ripple')]");
		
		
		
			
			//select header checkbox in trans toClaim tab
			public final By trans_toClaim_select_header_checkbox=By.xpath("//mat-header-cell//mat-checkbox[contains(@id,'checkbox')]");
			
			public final By trans_toClaim_checkbox_checked=By.xpath("//mat-header-cell//mat-checkbox[contains(@class,'checkbox-checked')]");
			
			//Claim  Submission	in Translator user
			
			public final By translator_selectSubmission_checkbox_1(String SubmissionName){
				return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../preceding-sibling::mat-cell//mat-checkbox[contains(@id,'checkbox')]");
			}
			
			// For Tranlation( Go to Subtitle ID after Alt + g) 
			public final By Trans_GoToSubtitleID_Menu = By.xpath("//label//span[contains(text(),'Go to subtitle ID')]");
			public final By Trans_Transc_GoToSubtitleID_Menu = By.xpath("//label[contains(text(),'Subtitle ID')]");	
				public final By translator_selectSubmission_checkbox(String SubmissionName,String target){
					//return By.xpath("//mat-cell[contains(text(),'"+SubmissionName+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
					return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
					//  return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'mat-checkbox-background')]//div[contains(@class,'checkbox-mixedmark')]");
				}
				public final By translator_claimAlert_claim_button=By.xpath("//span[contains(text(),'CLAIM')]");
				
				
				// FOR OPEN TASK MASSAGE AFTER CLAIM
				
				public final By translator_claimAlert_claim_button_OpenTaskMassegeButton=By.xpath("//div[contains(@class,'mat-simple-snackbar-action')]//button//span[contains(text(),'OPEN TASK')]");
				
				//Ongoing Submission in Translator User
			    public final By translator_toClaim_ListofallIds=By.xpath("//mat-list-item[contains(@class,'separator ng-star-inserted')]");//By.xpath("//mat-list-item[contains(@class,'separator mat-list-item ng-star-inserted')]");//NOTE:- work on that locator.
				public final By Translator_Submission_Edit_icon=By.xpath("//mat-icon[contains(text(),'mode_edit')]");//("//mat-icon[@mattooltip='Edit']");
				public final By Translator_completeTask_message(String message) {
					return By.xpath("//translation-complete-job//p[contains(text(),'"+message+"')]");
				}
				public final By Translator_completedTask_completeTaskPopUp=By.xpath("//simple-snack-bar//span[contains(text(),'Task now marked as completed.')]");
				public final By translator_Complete_Button=By.xpath("//mat-toolbar//button//span[contains(text(),'COMPLETE TASK')]");//By.xpath("//mat-icon[contains(text(),'check')]");
				public final By translator_CompleteDailoge_Button=By.xpath("//mat-dialog-actions//button//span[contains(text(),'COMPLETE')]");// //mat-dialog-actions//button//span[contains(text(),'COMPLETE TASK')]//By.xpath("//mat-dialog-container//mat-dialog-actions//button//span[text()='COMPLETE TASK']");
				public final By translator_CompleteDailoge_completeButton=By.xpath("//mat-dialog-actions//button//span[contains(text(),'COMPLETE')]");
				public final By translator_CompleteDailoge_cancelButton=By.xpath("//mat-dialog-actions//button//span[contains(text(),'CANCEL')]");
				//TODO below mention locator has been changed from back to menu in Sub_1.10.0
				public final By translator_BackToSubmissionList_Button=By.xpath("//mat-toolbar[1]//div//button[1]//span[1]//mat-icon[contains(@role,'img')]");//By.xpath("//mat-toolbar//button//mat-icon[contains(text(),'arrow_back')]");
				public final By translator_BackToSubmissionList_Menubutton_forCompleted=By.xpath("//mat-toolbar[1]//button[1]//span[1]//mat-icon[contains(@role,'img')]");
				
				public final By translator_CopySource_toTarget_Button_tooltip_message=By.xpath("//button[contains(@ng-reflect-message,'Copy Source to Target (Alt + S')]");//By.xpath("//button[contains(@mattooltip,'Copy source to target')]");
				public final By translator_CopySource_toTarget_Button=By.xpath("//mat-icon[contains(text(),'content_copy')]");
				
				
				//Select status of Submission in submissions tab
				public final By check_submission_status(String SubmissionName, String status){
					return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+status+"')]");

					}
				
				//Select Target_Lang of Submission in trans_Ongoing tab
				public final By check_trans_Target_Lang(String SubmissionName, String target){
					return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]");//("//mat-cell[contains(text(),'"+SubmissionName+"')]/following-sibling::mat-cell[contains(text(),'"+target+"')]");

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
				
				 //click on help link in pm login
				 public final By trans_HelpLink =  By.xpath("//span[contains(text(),'Help')]");//By.xpath("//h4[contains(text(),'Help')]");
				 
				 //help link new window title 
				 public final By trans_help_window_title= By.xpath("//div[contains(@class,'top_navbar')]//h1[contains(text(),'Play Online Help')]");//("//span[contains(text(),'Subtitler Online Help')]");
				 
				 //trans complete submission target max char
				// public final By trans_ongoing_sub_target_maxChar = By.xpath("//span[contains(text(),'L1: 50')]");
				 
				 
				 public final By trans_ongoing_sub_target_Chars_Length(int Chars_Length){
				return By.xpath("//span[contains(text(),'"+Chars_Length+"')]");
				 
		}	
				 public final By trans_ongoing_sub_target_exceed_Chars_Length(int exceed_Chars_Length){
						return By.xpath("//span[contains(text(),'"+exceed_Chars_Length+"')]");
		
}
				 public final By Linguist_login= By.xpath("//mat-toolbar[contains(text(),'Subtitler_Translator Subtitler_Translator')]");
				 
				 //Select status of Submission in PM_Submission tab
					public final By check_trans_submission_status(String SubmissionName, String status){
						return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+status+"')]");
}

					public final By Trans_selectSubmission_checkbox(String SubmissionName,String target){
						//return By.xpath("//mat-cell[contains(text(),'"+SubmissionName+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
						//return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'mat-checkbox-background')]");
						return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]/..//mat-cell[contains(@class,'column-select')]//div[contains(@class,'checkbox')]");
					}
					public final By Trans_Ongoing_edit_button = By.xpath("//mat-icon[contains(text(),'mode_edit')]");
					public final By Trans_cancel_current_edit_tooltip_message = By.xpath("//button[contains(@mattooltip,'Esc')]");//By.xpath("//button[contains(@mattooltip,'Cancel current edit')]");
					public final By Trans_cancel_current_edit = By.xpath("//mat-icon[contains(text(),'undo')]");
					
					//Locator for Slow Play Keyboard Shortcut 
					public final By Translator_Ongoing_Edit_KeyboardShortCut_CancelBtn=By.xpath("//hotkeys-cheatsheet//div//div//div[contains(@class,'close')]");//By.xpath("//hotkeys-cheatsheet//div//div//div[contains(text(),'�')]");
					
					
					
					public final By Trans_submission_complete_task=By.xpath("//mat-icon[contains(text(),'check')]");
					public final By Trans_Complete_Task_Alert_button_trans=By.xpath("//button//span[contains(text(),'COMPLETE TASK')]");
					public final By Trans_Cancel_Alert_button=By.xpath("//button//span[contains(text(),'CANCEL')]");
					
					//select header checkbox in trans toClaim tab
					public final By Trans_toClaim_select_header_checkbox=By.xpath("//mat-header-cell//mat-checkbox[contains(@id,'checkbox')]");
					public final By Trans_AllJobs_select_header_checkbox=By.xpath("//mat-header-cell//mat-checkbox[contains(@id,'checkbox')]");
					public final By Trans_toClaim_checkbox_checked=By.xpath("//mat-header-cell//mat-checkbox[contains(@class,'checkbox-checked')]");
					public final By Trans_AllJobs_checkbox_checked=By.xpath("//mat-header-cell//mat-checkbox[contains(@class,'checkbox-checked')]");
					//Assign Submission	
					public final By Trans_clamSubmission_icon=By.xpath("//button[contains(@mattooltip,'Claim task')]");//("//mat-icon[@mattooltip='Claim task']");//("//md-icon[@mdtooltip='Claim task']");
					
					public final By Trans_claimAlert_claim_button=By.xpath("//span[contains(text(),'CLAIM')]");
					public final By Trans_claimAlert_cancel_button=By.xpath("//span[contains(text(),'CANCEL')]");
					

					public final By Trans_InvalidImportFile_Error_message() throws Exception{
						System.out.println("The file has not the same count of subtitles than the source. Upload cancelled.");
						if(Verify.action().verifyElementPresent(By.xpath("//div[contains(text(),'Error importing targets. The number of captions does not match.')]"),5))
							return By.xpath("//div[contains(text(),'Error importing targets. The number of captions does not match.')]");
						else{
							return By.xpath("//div[contains(text(),'The file has not the same count of subtitles than the source. Upload cancelled.')][contains(@class,'hidden')]");
						}
					}
					
					public final By Trans_ToClaim_message() throws Exception{
						System.out.println("The task has been assigned to you.");
						if(Verify.action().verifyElementPresent(By.xpath("//div[contains(text(),'The task has been assigned to you.')]"),5))
							return By.xpath("//div[contains(text(),'The task has been assigned to you.')]");
						else{
							return By.xpath("//div[contains(text(),'The task has been assigned to you.')][contains(@class,'hidden')]");
						}
					}
					
					
				public final By Pm_user_SearchSubmission_Trans_WordCountSource(String CountHeaderText) {
					
					return By.xpath("//mat-header-cell//div//span[contains(text(),'"+CountHeaderText+"')]");
				}
					
				public final By Trans_ongoing_multiOption_icon=By.xpath("//mat-icon[contains(text(),'more_vert')]");
				public final By Trans_Ongoing_Import_file=By.xpath("//span[contains(text(),'Import File')]");
				public final By Trans_ongoing_multiOption_ExportWord=By.xpath("//span[contains(text(),'Export to Word')]");
				public final By Trans_Ongoing_Importfile_ByCaptionNumber=By.xpath("//div[contains(@class,'transformMenu')]//button[contains(text(),'By caption numbers')]");
				public final By Trans_Ongoing_Importfile_ByTimecode=By.xpath("//div[contains(@class,'transformMenu')]//button[contains(text(),'By timecode')]");
				
			
				
				public final By Trans_Ongoing_Import_file_input=By.xpath("//button//span[contains(text(),'Import')]/following-sibling::input");
				public final By Trans_Ongoing_Import_file_captionNumber_input=By.xpath("//div[contains(@class,'transformMenu')]//button[contains(text(),'By caption numbers')]/following-sibling::input[1]");
				public final By Trans_ongoing_find_and_replace = By.xpath("//span[contains(text(),'Find & Replace')]");
				
				public final By Trans_ongoing_overwrite_current_translation = By.xpath("//span[contains(text(),'OVERWRITE')]");
				
				//Select Target_Lang of Submission in Trans_Ongoing tab
				public final By check_Trans_Target_Lang(String SubmissionName, String target){
					return By.xpath("//mat-cell//span[contains(text(),'"+SubmissionName+"')]/../following-sibling::mat-cell[contains(text(),'"+target+"')]");
				}
				
				public final By trans_TargetSegement_textarea(int index){
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]");//("//md-list-item["+index+"]//span[//b[text()='1']]/following-sibling::textarea[contains(@id,'textarea')]");
					
				}
				
				public final By trans_TargetSegement_textarea_textformat(String format,String targetText){
					return By.xpath("//shared-text-editor/div//"+format+"[contains(text(),'"+targetText+"')]");
					
				}
				
				public final By trans_formattingIcons(String Text){
					return By.xpath("//div[contains(@id,'describedby-message')][contains(text(),'"+Text+"')]");//("//button[@ng-reflect-message='"+Text+"']");
					
				}
						
				public final By Trans_GoToSubtitle_transc = By.xpath("//input[contains(@name,'id')]");//("//input[contains(@ng-reflect-name,'id')]");
				public final By Trans_GoToSubtitle = By.xpath("//input[contains(@placeholder,'Go to subtitle ID')]");//By.xpath("//div[contains(@class,'input-infix')]//label[contains(text(),'Subtitle ID')]/following-sibling::input");
				public final By Trans_GoToSubtitle_Go_button = By.xpath("//button//span[contains(text(),'GO')]");//By.xpath("//button//b[contains(text(),'GO')]");
				public final By Trans_GoToSubtitle_Close_button = By.xpath("//button//span[contains(text(),'CANCEL')]");//By.xpath("//button//b[contains(text(),'CLOSE')]");
				public final By Trans_GoToSubtitle_transc_textarea=By.xpath("//shared-text-editor//div[contains(@class,'mousetrap')]");
				public final By Trans_GoToSubtitle_message=By.xpath("//mat-hint[contains(@class,'mat-hint')]");
				public final By SubtitleRowId(int id){
					return By.xpath("//mat-list-item[contains(@id,'"+id+"')][contains(@class,'current-record')]");//By.xpath("//mat-list-item[contains(@id,'"+id+"')][contains(@class,'playing-segment')]")
				}		
				public final By Trans_GoToSubtitle_transc(int index){
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//div[contains(@class,'editor-read-only')]");//By.xpath("//mat-list-item[contains(@id,'"+index+"')]//div[contains(@class,'source-read-only')]");
				}	
				
				
				
				public final By Trans_ongoing_keyboard_shortcuts = By.xpath("//span[contains(text(),'Keyboard Shortcuts')]");
				public final By table_keyboard_shortcuts = By.xpath("//div[contains(@class,'cfp-hotkeys-container fade in')]//table//tbody");
				public final By table_keyboard_shortcuts_row = By.xpath("//div[contains(@class,'cfp-hotkeys-container fade in')]//table//tbody//tr");
				public final By table_keyboard_shortcuts_column= By.xpath("//div[contains(@class,'cfp-hotkeys-container fade in')]//table//tbody//tr//td");
				public final By table_keyboard_shortcuts_cancel = By.xpath("//div[contains(@class,'cfp-hotkeys-close')]");
				public final By trans_ongoing_press_keyboard_shortcut_keys = By.xpath("//mat-chip[contains(text(),'In_Progress')]");
			    public final By Trans_toClaim_ListofallIds=By.xpath("//mat-list-item[contains(@class,'separator mat-list-item ng-star-inserted')]");
			    public final By Trans_Find_and_Replace = By.xpath("//input[contains(@placeholder,'Find')]");
			    public final By Trans_Find_and_Replace_Close = By.xpath("//span[contains(text(),'CANCEL')]");//By.xpath("//span[contains(text(),'CLOSE')]");
			    public final By Trans_NumberOfOccurences = By.xpath("//h5[contains(text(),'occurrences found.')]");	
			    public final By Trans_Find_and_replace_findInputBox = By.xpath("//input[contains(@placeholder,'Find')]");
				public final By Trans_Find_and_replace_replaceButton = By.xpath("//button[3]//span[contains(text(),'REPLACE')]");
				public final By Trans_Find_and_replace_findButton = By.xpath("//span[contains(text(),'FIND')]");
				public final By Trans_Find_and_replace_cancelButton = By.xpath("//span[contains(text(),'CANCEL')]");
				public final By Trans_Find_and_replace_replaceBy = By.xpath("//input[contains(@placeholder,'Replace by')]");
				
				
			    public final By AscendingSubmission_Name=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
				public final By DescendingSubmission_Name=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'descending')]]");
				public final By Submission_Name=By.xpath("//mat-table//div//button[contains(text(),'Name')]");
				
				public final By keyborad_shortcut_save_and_go_to_next = By.xpath("//table//tr[10]");
				public final By time_format = By.xpath("//mat-list-item[contains(@id,'1')]//div[contains(@fxlayout,'column')]//span");
				public final By Text_Formatting_Bold = By.xpath("//mat-icon[contains(text(),'format_bold')]");
				public final By Text_Formatting_Italic = By.xpath("//mat-icon[contains(text(),'format_italic')]");
				public final By Text_Formatting_Underline = By.xpath("//mat-icon[contains(text(),'format_underlined')]");
				public final By trans_ongoing_delete_icon = By.xpath("//mat-icon[contains(text(),'delete')]");
				public final By trans_ongoing_editTime_icon = By.xpath("//mat-icon[contains(text(),'access_time')]");
				public final By trans_ongoing_editTime_tooltip=By.xpath("//button[contains(@mattooltip,'Edit time-in / time-out')]");
				public final By trans_ongoing_delete_icon_mattooltip = By.xpath("//button[contains(@ng-reflect-message,'Clear the text (Alt + X)')]");
				public final By trans_ongoing_onScreenText_icon=By.xpath("//button[contains(@mattooltip,'Mark/unmark this segment as On Screen Text')]//mat-icon[text()='tv']");
				
				public final By Trans_file_download =By.xpath("//mat-icon[contains(text(),'file_download')]");	
				public final By Trans_FileDownload_SRT= By.xpath("//button[text()='SRT']");
				public final By Trans_FileDownload_TTML= By.xpath("//button[text()='TTML1']");
				public final By Trans_FileDownload_EBU= By.xpath("//button[text()='EBU-TT-D']");
				public final By Trans_ongoing_qualitycheck_button=By.xpath("//div[contains(@class,'tab-label-content')][contains(text(),'Quality Check')]");
				public final By Trans_ongoing_qualitycheck_Run_button = By.xpath("//span[contains(text(),'Run')]");
				public final By Trans_ongoing_tabes_leftArrow=By.xpath("//div[contains(@class,'pagination-before')]//div[contains(@class,'mat-tab-header-pagination-chevron')]");
				public final By Trans_ongoing_tabes_rightArrow=By.xpath("//div[contains(@class,'pagination-after')]//div[contains(@class,'mat-tab-header-pagination-chevron')]");
				public final By Trans_ongoing_taskInfo_button=By.xpath("//div[contains(@class,'tab-label-content')][contains(text(),'Task info')]");
				public final By Trans_ongoing_comment_button=By.xpath("//div[contains(@class,'tab-label-content')][contains(text(),'Comments')]");
				public final By Trans_ongoing_qualitycheck_table=By.xpath("//mat-nav-list//cdk-virtual-scroll-viewport[contains(@class,'cdk-virtual-scroll-viewport')]//mat-list-item");
				public final By qualitycheck_norule_violation_mesaage= By.xpath("//span[contains(text(),'Character Limitation')]");//By.xpath("//p[contains(text(),'There is no rule violation')]");
				public final By qualitycheck_number_of_issues= By.xpath("//mat-chip-list//mat-chip[contains(text(),'issues')]");
				public final By qualitycheck_rule_violation_mesaages= By.xpath("//mat-nav-list[contains(@class,'mat-nav-list')]//mat-list-item/div/span[not(@class='col-spacing')]");//("//mat-list-item[contains(@class,'qc-list-item')]");
				public final By qualitycheck_rule_violation_message_for_bold(int index) {
					return By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'"+index+"')]/..//span[contains(text(),'There are inconsistent tags')]");
				}
				
				public final By qualitycheck_rule_notEnoughFrames(int segment) {
					return By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'"+segment+"')]//following-sibling::span[contains(text(),'There are not enough frames between this segment and the next one.')]");
				}
				
				
				public final By qualitycheck_message_before_run= By.xpath("//p[contains(text(),'Quality Check has not been run yet')]");
				public final By qualitycheck_rule_violation_message_for_Min_RS= By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'Reading speed is too low')]");
				public final By qualitycheck_rule_violation_message_for_Max_RS= By.xpath("//mat-list-item[contains(@class,'qc-list-item')]//span[contains(text(),'Reading speed is too high')]");
				public final By Trans_ongoing_taskInfo=By.xpath("//mat-tab-group//div[contains(@class,'mat-tab-label-content')][contains(text(),'Task info')]"); 
				public final By taskInfo_maxLinePerSub=By.xpath("//mat-list-item//h4[contains(text(),'Max lines per sub')]");
				public final By taskInfo_charPerLine=By.xpath("//mat-list-item//h4[contains(text(),'Max chars per line')]");
				public final By taskInfo_minCountOfFrames=By.xpath("//shared-job-info//mat-list-item[7]//h4");
				public final By taskInfo_minDurationOfSub=By.xpath("//mat-list-item//h4[contains(text(),'Min duration of a sub')]");
				public final By taskInfo_maxDurationOfSub=By.xpath("//mat-list-item//h4[contains(text(),'Max duration of a sub')]");
				public final By taskInfo_minCountOfFramesBetweenSubs=By.xpath("//mat-list-item//h4[contains(text(),'Min count of frames between subs')]");
				public final By qualitycheck_rule_violation_mesaages_for_segment(int index){
					return By.xpath("//mat-nav-list[contains(@class,'mat-nav-list')]//span[contains(text(),'"+index+"')]");
				}	
				
				public final By Trans_qualityChecks_rules(String index, String rules) {
					return By.xpath("//mat-nav-list//mat-list-item//span[contains(text(),'"+index+"')]//following-sibling::span[contains(text(),'"+rules+"')]");
				}
				
				public final By Trans_segments_currentSegment(int index) {
					return By.xpath("//mat-list-item["+index+"][contains(@class,'inserted current-record')]");
				}
				
				public final By Trans_segments_characterList(int row,String character) {
					return By.xpath("//mat-list-item["+row+"]//div[contains(@class,'small character')][contains(text(),'"+character+"')]");
				}
				
				
				public final By Trans_readingSpeed_value(int index){
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//span[contains(@class,'reading-speed')]");
				}
				
				public final By target_AN_tag_upward(int index){
					return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//mat-icon[contains(text(),'arrow_upward')]");
				}
				
				public final By target_AN_tag_vertical_align_top= By.xpath("//mat-icon[contains(text(),'vertical_align_top')]");
				public final By target_AN_tag_vertical_align_bottom= By.xpath("//mat-icon[contains(text(),'vertical_align_bottom')]");
				
				public final By menu_button= By.xpath("//button[1]//mat-icon[1]");
				public final By GlobalLink_Subtitler_Logo = By.xpath("//mat-toolbar//div[@class='logo']");
				
				public final By translator_submissionName_header(String  submissionName){
					return By.xpath("//span[contains(text(),'"+submissionName+"')]");
				}
				
				public final By translator_segmentHistoryTab=By.xpath("//div[contains(@class,'top-action-buttons')]//mat-icon[contains(text(),'comment')]");
				public final By translator_segmentHistoryTab_commentInput=By.xpath("//mat-form-field//textarea[contains(@formcontrolname,'comment')]");//("//mat-form-field//textarea[contains(@ng-reflect-name,'comment')]");
				public final By translator_segmentHistoryTab_commentDialogBox_CommentButton=By.xpath("//mat-dialog-actions//SPAN[contains(text(),'COMMENT')]");
				public final By translator_segmentHistoryTab_commentDialogBox_CancelButton=By.xpath("//mat-dialog-actions//SPAN[contains(text(),'CANCEL')]");
				public final By translator_segmentHistory_User=By.xpath("//mat-list-item//div[contains(text(),'USER')]");
				public final By translator_segmentHistory_Step=By.xpath("//mat-list-item//div[contains(text(),'STEP')]");
				public final By translator_segmentHistory_translation=By.xpath("//mat-list-item//div[contains(text(),'TRANSLATION')]");
				
				public final By translator_segmentHistory_firstRow(String history) {
					return By.xpath("//mat-dialog-content//mat-list[2]//div[contains(@fxlayout,'row wrap')]//div[contains(text(),'"+history+"')]");
				}
				
	
				public final By translator_jobHeaderOptions(int index) {
					return By.xpath("//mat-tab-header//div[contains(@class,'mat-tab-labels')]/div["+index+"]");
				}
				public final By translator_commentTab_longText=By.xpath("//app-comments-list//div[contains(@class,'mat-list-item-content')]");
				public final By translator_commentTab=By.xpath("//mat-tab-header//div[contains(@class,'mat-tab-labels')]//div[contains(text(),'Comment')]");
				public final By translator_commentTab_comments(int id,String comment) {
					return By.xpath("//mat-nav-list[contains(@class,'comment')]/mat-list-item//span[contains(text(),'"+id+"')]//following-sibling::span[contains(text(),'"+comment+"')]");
				}
				
				public final By translator_currentSegment(int id) {
					return By.xpath("//mat-list-item[contains(@id,'"+id+"')][contains(@class,'current-record')]");
				}
				
				public final By translator_commentIds(int id,String comment) {
					return By.xpath("//mat-list-item//span[(text()="+id+")]//following-sibling::span[contains(text(),'"+comment+"')]");
				}
				
				public final By  translator_commentTab_SegmentIds(int id) {
					return By.xpath("//mat-list-item["+id+"]//span[(text()="+id+")]");
				}
				
				public final By  translator_commentTab_SegmentIds_String(int id) {
					return By.xpath("//mat-list-item//span[(text()="+id+")]/..//span[contains(text(),'Empty Subtitle')]");
				}
				public final By translator_SegmentArea_segmentId(int Id) {
					return By.xpath("//mat-list-item[(@id='"+Id+"')]");
				}
				
				
				public final By Trans_readingSpeed(int index) {
					return By.xpath("//cdk-virtual-scroll-viewport//mat-list-item["+index+"]//shared-reading-speed//span");
				}
				
				public final By Trans_duration(int index) {
					return By.xpath("//cdk-virtual-scroll-viewport//mat-list-item["+index+"]//div[8][contains(@class,'text-warn')]");
				}
				public final By Trans_segmentLength_colomn(int index)
				{ return By.xpath("//mat-list-item["+index+"]//shared-line-counter//span[contains(@class,'counter')]");
				}
				
				public final By Trans_segmentLength_L_multiPleTLines(int index) {
					return By.xpath("//mat-list-item[1]//shared-line-counter/div/div[contains(@class,'ng-star-inserted')]["+index+"]");
					
				}
				
				public final By Trans_segmentLength_L(int index) {
					return By.xpath("//mat-list-item["+index+"]//shared-line-counter/div/div[contains(@class,'ng-star-inserted')]");
					
				}
				
				
				public final By trans_segmentHistoryTab_comment_history=By.xpath("//mat-list/mat-list-item//div[contains(text(),'trans')]//following-sibling::div[contains(@class,'comments')]//div[contains(@class,'text-wrap-lines')]");
                
				
				public final By trans_ongoing_waterMark=By.xpath("//app-translation-video//span[contains(text(),'subtitlertrans@gmail.com')]");
				
				 public final By trans_ongoing_video_seconds=By.xpath("//app-time-box//div[contains(@class,'time-code')]//span[5]");
		         public final By trans_ongoing_video_frames=By.xpath("//app-time-box//div[contains(@class,'time-code')]//span[7]");
				
		         public final By trans_ongoing_indexCaption(String indexCaption) {
						return By.xpath("//button[contains(@class,'mat-button')]//span[contains(text(),'"+indexCaption+"')]");
					}
		         
		         public final By PM_Transcription_VideoBar_playButton=By.xpath("//div[contains(@id,'video-controls')]//mat-icon[contains(text(),'play_arrow')]");
				 public final By PM_Transcription_VideoBar_pauseButton=By.xpath("//div[contains(@id,'video-controls')]//mat-icon[contains(text(),'pause')]");
		         
		         //Alt + c (Calibration)
		         public final By trans_ongoing_calibration_hour=By.xpath("//mat-form-field//input[contains(@name,'hours')]");
		         public final By trans_ongoing_calibration_calibrateButton=By.xpath("//app-calibration-dialog//button//span[contains(text(),'CALIBRATE')]");
		         public final By trans_ongoing_calibration_cancelButton=By.xpath("//app-calibration-dialog//button//span[contains(text(),'CANCEL')]");
		         public final By trans_ongoing_videoTime_hour=By.xpath("//app-time-box//span[1]");



}




