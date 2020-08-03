package locators;

import org.openqa.selenium.By;

public class CommonLocartors {
	public static CommonLocartors common_object;
	
	
	 public static synchronized CommonLocartors Locator() {
		  try {
		   if (common_object.equals(null)) {
			   common_object = new CommonLocartors();
		   }
		  } catch (Exception NOSYSTEM) {
			  common_object = new CommonLocartors();
		  }
		  return common_object;
		 }
	
	public final By CollapseMenuSideBar=By.xpath("//md-sidenav[contains(@class,'closed')]");
	public final By ExpandMenuSideBar=By.xpath("//md-sidenav[contains(@class,'opened')]");
	
	
	public final By userNameLogedIn(String userName){
		return By.xpath("//span[text()='"+userName+"']");
	}
	
	public final By SelectMenu(String menuname){
		return By.xpath("//h4[text()='"+menuname+"']/../..//mat-icon");
	}
	
	public final By selectMenu_settings_header=By.xpath("//mat-card-header//mat-card-title[contains(text(),'Project Director')]");
	public final By setting_newInstanceButton=By.xpath("//button//span[contains(text(),'NEW INSTANCE')]");
	public final By setting_newInstance_name=By.xpath("//input[contains(@placeholder,'Name')]");
	public final By setting_newInstance_url=By.xpath("//input[contains(@placeholder,'Url')]");
	public final By setting_newInstance_add=By.xpath("//button//span[contains(text(),'ADD')]");
	public final By setting_newInstance_cancel=By.xpath("//button//span[contains(text(),'CANCEL')]");
	
	public final By setting_newInstance_addedInstance(String name,String url) {
		return By.xpath("//tr//td[contains(@class,'mat-cell')][contains(text(),'"+name+"')]/..//td[contains(text(),'"+url+"')]");
	}
	
	public final By setting_newInstance_addedInstance_delete(String name,String url) {
		return By.xpath("//tr//td[contains(@class,'mat-cell')][contains(text(),'"+name+"')]/..//td[contains(text(),'"+url+"')]/..//mat-icon[text()='delete']");
	}
	
	public final By setting_newInstance_addedInstance_deleteButton=By.xpath("//mat-dialog-actions//button//span[text()='DELETE']");
	
   public final By ongoing_button= By.xpath("//div//h4[contains(text(),'Ongoing')]");
   public final By submissionList_firstRow= By.xpath("//mat-sidenav-content//mat-table//mat-row[1]");
   	
	public final By GlobalLink_logo= By.xpath("//mat-toolbar//div[contains(@class,'logo')]");
	public final By GlobalLink_logo_hidden= By.xpath("//mat-sidenav[contains(@style,'visibility: hidden;')]//mat-toolbar//div[contains(@class,'logo')]");

	
	
	public final By Update_button=By.xpath("//span[contains(text(),'UPDATE')]");//("//button[@type='submit']//span[contains(text(),'Save')]");
	
	
	//== Locators For Vendor(Common For Translator , Transcription, Review)==
	
	public final By Submission_Edit_icon=By.xpath("//button[contains(@mattooltip,'Edit')]");
	
	//Locator to perform Alt Operations for trans and Review
	
	public final By EditSubmission_VideoUpperBorder = By.xpath("//video[contains(@crossorigin,'anonymous')]");
	
	//For Edit Segment Trans and Review
	
	public final By EditSegment_textarea(int index){
			return By.xpath("//mat-list-item[contains(@id,'"+index+"')]//shared-text-editor//div[contains(@class,'mousetrap')]");
		}
	
	//For Complete Task
	
	public final By SubmissionEdit_complete_task=By.xpath("//mat-toolbar//button//span[contains(text(),'COMPLETE TASK')]");
	public final By SubmissionEdit_complete_Task_Alert_button=By.xpath("//mat-dialog-actions//span[contains(text(),'COMPLETE')]");
	
	
	public final By EditSubmission_KeyboardShortcut_ForSpeed(String Speed) {
		return By.xpath("//shared-video//span[contains(text(),'"+Speed+"')]");//By.xpath("//shared-video//div[contains(text(),'"+Speed+"')]");//shared-video//span[contains(text(),'1 X')]
	}
	
	
	public final By Ongoing_edit_shortKey_ForSlowPlay= By.xpath("//tr//td[contains(text(),'Slow play / Super slow play')]");
	
	
	// Common Locator for on Screan Tool Tip  "Mark/unmark this segment as On Screen Text" 
	
	public final By SubmissionEdit_OnScreenToolTip	=  By.xpath("//button[contains(@mattooltip,'Mark/unmark this segment as On')]");
	
	public final By PM_Transcription_Video_Styling_onScreenButton=By.xpath("//button//mat-icon[contains(text(),'tv')]");
	
	public final By Ongoing_actionButtons_Tooltip(String tooltip) {
		return By.xpath("//button[contains(@ng-reflect-message,'"+tooltip+"')]");
	}
	
	
	
	public final By ongoing_multiOption_icon=By.xpath("//mat-icon[contains(text(),'more_vert')]");
	public final By ongoing_find_and_replace = By.xpath("//span[contains(text(),'Find & Replace')]");
	public final By ongoing_keyboard_shortcuts = By.xpath("//span[contains(text(),'Keyboard Shortcuts')]");
	
	
	public final By Ongoing_FindandReplace_Close_button =By.xpath("//span[contains(text(),'CANCEL')]");
	
	
	
	//Locators for  time-in / time-out of a segment After ALT+t
	
	public final By ongoing_EditTimeInOut_ToolTip = By.xpath("//button[contains(@mattooltip,'Edit time-in / time-out')]");//("//button[contains(@ng-reflect-message,'Edit time-in / time-out')]");
	
	public final By ongoing_TimeInOutSegmentDialogBox_CancelBtn = By.xpath("//mat-dialog-actions[contains(@class,'mat-dialog-actions')]//button//span[contains(text(),'CANCEL')]");
	
	public final By ongoing_TimeInOutSegmentDialogBox_UpdateBtn = By.xpath("//mat-dialog-actions[contains(@class,'mat-dialog-actions')]//button//span[contains(text(),'UPDATE')]");
	
	public final By ongoing_TimeInOutSegmentDialogBox_TimeInSegmentDate(String TimeSlot ) {
		return By.xpath("//input[contains(@name,'"+TimeSlot+"')]");
	}
	public final By ongoing_TimeInOutSegmentDialogBox_TimeOutSegmentDate(String TimeSlot ) {
		return By.xpath("//input[contains(@name,'"+TimeSlot+"')]");
		
		
	}
	
	public final By ongoing_EditTimeInOut_DefaultValue(int IndexNo) {
		return By.xpath("//div[contains(@id,'data-grid')]//mat-list-item[1]//div[contains(@style,'flex-direction: column')]//span["+IndexNo+"]");
	}
	
	public final By ongoing_EditTimeInOut_DefaultValueText(String DefaultValueText) {
		return By.xpath("//div[contains(@id,'data-grid')]//mat-list-item[1]//div[contains(@style,'flex-direction: column')]//span[contains(text(),'"+DefaultValueText+"')]");
	}
	
	public final By Trans_ongoing_TimeIn(int index) {
		return By.xpath("//mat-list-item["+index+"]//div[7]//span[1]");
	}
	
	public final By Trans_ongoing_TimeOut(int index) {
		return By.xpath("//mat-list-item["+index+"]//div[7]//span[2]");
	}
	
	public final By review_ongoing_TimeIn(int index) {
		return By.xpath("//mat-list-item["+index+"]//div[9]//span[1]");
	}
	
	public final By review_ongoing_TimeOut(int index) {
		return By.xpath("//mat-list-item["+index+"]//div[9]//span[2]");
	}
	
	
	public final By ongoing_TimeInOutSegmentDialogBox_OverlapErrorMsg = By.xpath("//mat-error//p[contains(text(),'Notice that there will be overlap with other segments.')]");
	
	public final By ongoing_TimeInOutSegmentDialogBox_TimeOut_smallerThan_timeIn = By.xpath("//mat-error//p[contains(text(),'Time-out can not be smaller than time-in.')]");

	public final By ongoing_TimeInOutSegmentDialogBoxFrameMsg=By.xpath("//mat-error//p[contains(text(),'Notice that there will not be enough frame between subtitles')]");

  // To Verify Bold Text
	
	public final By Translation_Submission_Edit_Glossaryterms_Icon= By.xpath("//mat-list-item//div//h4[contains(text(),'Glossary')]");
	
	public final By Translation_Submission_Edit_TextArea_RemovedBoldText(int index) {
		return By.xpath("//mat-list-item[contains(@id,"+index+")]//shared-text-editor//div//b[contains(text(),'Glossary')]");
	}
	
	public final By allJobs_columns(String columnName) {
		return By.xpath("//mat-table//mat-header-cell//button[contains(text(),'"+columnName+"')]");
	}
	
	public final By allJobs_columns_segment=By.xpath("//mat-table//mat-header-cell//button//span[contains(text(),'Segments')]");
	
	//Export CSV
	
	public final By submission_exportCSV =By.xpath("//shared-export-csv//mat-icon[contains(text(),'content_paste')]");
	
	
	public final By ongoing_submission_segmentGrid_Scroll=By.xpath("//cdk-virtual-scroll-viewport[contains(@class,'virtual-scroll')]//div[contains(@class,'virtual-scroll-content')]");
	public final By ongoing_submission_sourceSegments(String id,String sourceSegment) {
		return  By.xpath("//mat-list-item//b[(text()='"+id+"')]//ancestor::mat-list-item//app-text-segment//div[contains(@class,'backdrop')]//div[contains(text(),'"+sourceSegment+"')]");
	}
	
	
	
	public final By Text_Formatting_Bold = By.xpath("//mat-icon[contains(text(),'format_bold')]");
	public final By Text_Formatting_Italic = By.xpath("//mat-icon[contains(text(),'format_italic')]");
	public final By Text_Formatting_Underline = By.xpath("//mat-icon[contains(text(),'format_underlined')]");
}