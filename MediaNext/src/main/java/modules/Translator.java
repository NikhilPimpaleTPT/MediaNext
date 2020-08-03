package modules;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.utility.General;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;

public class Translator extends General{
	
	private static Translator trans_objects;

	 /**
	  * Method used to self-instantiate the class. Will make sure one object, and
	  * one object only is created in memory for this class. The purpose is to be
	  * able to call this object dynamically from any JAVA class that includes
	  * this as an import.
	  * 
	  * @return Returns the object instantiated from the class.
	  */
	 public static synchronized Translator action() {
	  try {
	   if (trans_objects.equals(null)) {
		   trans_objects = new Translator();
	   }
	  } catch (Exception NOSYSTEM) {
		  trans_objects = new Translator();
	  }
	  return trans_objects;
	 }
	 
	 
	 //##########################################################################################################################
	    public void Trans_SearchSubmisson(String SubmissionName)throws Exception {
			 
	    	waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
	    	type(TranslatorLocators.Locator().translator_search_input, SubmissionName);
	    	type(TranslatorLocators.Locator().translator_search_input, Keys.chord(Keys.ENTER));
	    	waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
	    }
	    
	  //##########################################################################################################################
	    public void Trans_AssigneSubmisson(String SubmissionName) throws Exception {
			 
	    	waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
	    	Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName));
	    	Thread.sleep(1000);
	    	waitforelemenetpresent(TranslatorLocators.Locator().translator_clamSubmission_icon);
	    	Click(TranslatorLocators.Locator().translator_clamSubmission_icon);
	    	Thread.sleep(1000);
	    	waitforelemenetpresent(TranslatorLocators.Locator().translator_claimAlert_confirm_button);
	    	Click(TranslatorLocators.Locator().translator_claimAlert_confirm_button);
	    	Thread.sleep(3000);
	    	Thread.sleep(defaultWaitPeriod*5);
	    }
	    
	    
	  //##########################################################################################################################
	    public void Trans_OpenSubmissionStep1(String SubmissionName) throws Exception {
			 
	    	waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
	    	waitforelemenetpresent(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName));
	    	Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName));
	    	Thread.sleep(1000);
	    	waitforelemenetpresent(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
	    	Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
	    	Thread.sleep(1000);
	    	waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
	    	/*waitforelemenetpresent(TranslatorLocators.Locator().translator_claimAlert_confirm_button);
	    	Click(TranslatorLocators.Locator().translator_claimAlert_confirm_button);*/
	    	Thread.sleep(defaultWaitPeriod*5);
	    }
	    
	  //##########################################################################################################################
	    public void Trans_Step2(Boolean Complete,Boolean UploadTranslationFile,boolean back) throws Exception {
			 
		    if(UploadTranslationFile){
		    		waitforelemenetpresent(TranslatorLocators.Locator().translator_Listofallsegments_index);
		    		List <WebElement> listofsegements=driver.findElements(TranslatorLocators.Locator().translator_Listofallsegments_index);
		    		System.out.println("No of segements--------"+listofsegements.size());
		    		
		    		for(int i=1;i<=listofsegements.size();i++){
		    			waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement(i));
		    			Click(TranslatorLocators.Locator().translator_TargetSegement(i));
		    			Thread.sleep(1000);
		    			waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
		    			waitforelemenetpresent(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
		    			Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
		    			Thread.sleep(1000);
		    			Thread.sleep(defaultWaitPeriod*2);
		    		}
	    		
		    	}
	    
			    if(Complete){
			    	waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
			    	Click(TranslatorLocators.Locator().translator_Complete_Button);
			    	Thread.sleep(defaultWaitPeriod);
			    	waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
			    	Actions actions = new Actions(driver);
					actions.moveToElement(driver.findElement(TranslatorLocators.Locator().translator_CompleteDailoge_Button));
					actions.perform();
					actions.sendKeys(Keys.ENTER);
					driver.findElement(TranslatorLocators.Locator().translator_CompleteDailoge_Button).click();
 	
			    }
			    
			    
			    if(back){
			    	waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
			    	Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
			    }
			    
	    }
	    
	    
//##########################################################################################################################
	    /**
	     * @author psukhwani
	     * This method is used to complete ONGOING Submission
	     * @param SubmissionName
	     * @param target
	     * @throws Exception
	     */
		public void translate_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
			
			 System.out.println("INSIDE trans_Ongoing  method()");
			 
				waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
				Thread.sleep(1000);
				Click(TranslatorLocators.Locator().translator_search_input);
				Thread.sleep(1000);
				ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
				Thread.sleep(1000);
				type(TranslatorLocators.Locator().translator_search_input, SubmissionName);
				Thread.sleep(2000);
				
				if(Verify.action().VerifyElementPresent(TranslatorLocators.Locator().check_trans_Target_Lang(SubmissionName, target)))
				{
					System.out.println("INSIDE IF--------");
					Click(TranslatorLocators.Locator().translator_selectSubmission_checkbox(SubmissionName, target));
				}
					  Thread.sleep(1000);
				      Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
				
					  Thread.sleep(5000);
				      List <WebElement> listofIds1= driver.findElements(TranslatorLocators.Locator().translator_toClaim_ListofallIds);
					  Thread.sleep(1000);
					  System.out.println("No of IDS--------"+listofIds1.size());
					  Thread.sleep(3000);
					  
				    for(int i=1;i<=listofIds1.size();i++){
				    	  Thread.sleep(2000);
						waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
						Thread.sleep(1000);
						Click(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
						Thread.sleep(1000);
						ClearInputvalues(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
						Thread.sleep(1000);
						waitforelemenetpresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(i));
						Thread.sleep(1000);
						waitforelemenetpresent(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
						Thread.sleep(1000);
						Click(TranslatorLocators.Locator().translator_CopySource_toTarget_Button);
						Thread.sleep(1000);
					
				    }
				    
				    if(complete){
						waitforelemenetpresent(TranslatorLocators.Locator().translator_Complete_Button);
						Thread.sleep(1000);
						Click(TranslatorLocators.Locator().translator_Complete_Button);
						Thread.sleep(1000);
						waitforelemenetpresent(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
						Thread.sleep(1000);
						Click(TranslatorLocators.Locator().translator_CompleteDailoge_Button);
						Thread.sleep(3000);
						if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
							General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
							Thread.sleep(1000);
							General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Menubutton_forCompleted);
					    	}
					}
					
				    
				    if(back){
				    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
							General.action().waitforelemenetpresent(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
							Thread.sleep(1000);
							General.action().Click(TranslatorLocators.Locator().translator_BackToSubmissionList_Button);
					    	}
				    }
				    
					 System.out.println("EOM trans_Ongoing  method()");
		}
		
//###################################################################################################################################################
		
		/**
		 * This method is used to Navigate to Folders
		 * 
		 * @param menu
		 * @throws Exception
		 */
		public void Navigate(String menu) throws Exception {
			
			System.out.println("INSIDE METHOD Navigate");
			
			if(VerifyElementPresent(CommonLocartors.Locator().CollapseMenuSideBar)){
				Click(CommonLocartors.Locator().ExpandMenuSideBar);
			}
			
			if(menu!=null){
				waitforelemenetpresent(CommonLocartors.Locator().SelectMenu(menu));
				Click(CommonLocartors.Locator().SelectMenu(menu));
				/*System.out.println("//app-"+menu.toLowerCase()+"");
				if(VerifyElementPresent(by))
				waitforelemenetpresent(By.xpath("//app-"+menu.toLowerCase()+""));*/
				Thread.sleep(defaultWaitPeriod*8);
				System.out.println("navigate to ------------------------------------------------------"+menu);
			}
			
			System.out.println("EOM Navigate()");
		}	

//#############################################################################################################################################
		/**
		 * @author psukhwani
		 * This method is used to claim submissions
		 * 
		 * @param SubmissionName
		 * @throws Exception
		 */
		public void trans_ToClaim(String SubmissionName) throws Exception {
			
			 System.out.println("INSIDE trans_ToClaim  method()");
			 
				waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
				Thread.sleep(1000);
				Click(TranslatorLocators.Locator().translator_search_input);
				Thread.sleep(1000);
				ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
				Thread.sleep(1000);
				type(TranslatorLocators.Locator().translator_search_input, SubmissionName);
				Thread.sleep(1000);
				
				   if(Verify.action().verifyElementNotPresent(TranslatorLocators.Locator().trans_toClaim_checkbox_checked,5))
				   {	
					System.out.println("INSIDE IF--------");
					Thread.sleep(1000);
					waitforelemenetpresent(TranslatorLocators.Locator().trans_toClaim_select_header_checkbox);
					Thread.sleep(1000);
					Click(TranslatorLocators.Locator().trans_toClaim_select_header_checkbox);
					Thread.sleep(1000);
					System.out.println("CLICK DONE------");
				   }
				Click(TranslatorLocators.Locator().translator_clamSubmission_icon);
				Thread.sleep(3000);
				waitforelemenetpresent(TranslatorLocators.Locator().translator_claimAlert_claim_button);
				Thread.sleep(1000);
				Click(TranslatorLocators.Locator().translator_claimAlert_claim_button);
				Thread.sleep(2000);
				Thread.sleep(defaultWaitPeriod*5);
				
				System.out.println("EOM trans_ToClaim()");

		}
		
//#################################################################################################################################################
		
		/**
		 * This method is used to search submission
		 * 
		 * @param SubmissionName
		 * @throws Exception
		 */
		public void SearchSubmisson(String SubmissionName) throws Exception {
			
			System.out.println("INSIDE METHOD SearchSubmisson");
			waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
			Thread.sleep(1000);
			ClearInputvalues(TranslatorLocators.Locator().translator_search_input);
			Thread.sleep(1000);
			type(TranslatorLocators.Locator().translator_search_input, SubmissionName);
			Thread.sleep(1000);
//			type(TranslatorLocators.Locator().translator_search_input, Keys.chord(Keys.ENTER));
//			Thread.sleep(1000);
	    	//TODO NOT REQUIRED AS PER JAVA 8
			
			//TODO NOT REQUIRED AS PER NEW IMPROVEMENT FOR FILTER SUBMISSION (1.22.0 RC1)
//	    	type_withKEYS(TranslatorLocators.Locator().translator_search_input, Keys.ENTER,false);
//	    	Thread.sleep(1000);	
//			waitforelemenetpresent(TranslatorLocators.Locator().translator_search_input);
//			Thread.sleep(1000);
			System.out.println("EOM SearchSubmisson()");
		}
//#############################################################################################################################################
		
		  /**
	     * This method is used to search and check submission
	     */
	    public void SearchSubmisson_andCheck(String SubmissionName) throws Exception {
	    	System.out.println("INSIDE method SearchSubmisson()\n"); 

	    	waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
	    	Thread.sleep(1000);	
	    	General.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
	    	Thread.sleep(1000);	
	    	type(ReviewerLocators.Locator().review_search_input, SubmissionName);
	    	Thread.sleep(1000);	
//	    	type(ReviewerLocators.Locator().review_search_input, Keys.ENTER.toString());
//	    	Thread.sleep(1000);	
	    	//TODO NOT REQUIRED AS PER JAVA 8
	    	type_withKEYS(TranslatorLocators.Locator().translator_search_input, Keys.ENTER,false);
	    	Thread.sleep(1000);	
	    	waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
	    	Thread.sleep(1000);	
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().SelectSubmission_Checkbox(SubmissionName));
			Thread.sleep(1000);			
			General.action().Click(ReviewerLocators.Locator().SelectSubmission_Checkbox(SubmissionName));
			Thread.sleep(2000);
	    	
 	    System.out.println("EOM SearchSubmisson()\n");

	    }
		
//####################################################################################################################################
	    public void sort(String menu, String columnName,boolean sort) throws Exception{
	    	
	    	System.out.println("INSIDE METHOD sort");
		 	
			admin.action().Navigate(menu);
			Thread.sleep(4000);
			General.action().Click(TranslatorLocators.Locator().colunm_Name(columnName));
					
			//THIS FOR ASCENDING
			if(sort){
				if(Verify.action().verifyElementPresent(TranslatorLocators.Locator().descending_sort(columnName), 5 )){
					General.action().Click(TranslatorLocators.Locator().colunm_Name(columnName));
				}
			}
			else{
				if(Verify.action().verifyElementPresent(TranslatorLocators.Locator().Ascending_sort(columnName), 5 )){
					General.action().Click(TranslatorLocators.Locator().colunm_Name(columnName));
				}
			}
			Thread.sleep(3000);		
			
			System.out.println("EOM sort()");
			}

//############################################################################################################################################
	    public void sortSubmission(String submissionName, boolean sort) throws Exception{
        	
        	System.out.println("INSIDE METHOD sortSubmission");
        	
        	
                    SearchSubmisson(submissionName);
        	Thread.sleep(4000);
        	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
        	General.action().Click(Pm_User_Submission_Locators.Locator().Submission_Name);
        	
        	//THIS FOR ASCENDING
        	
        	if(sort){
    			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().DescendingSubmission_Name, 5)){
    				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_Name);
    			}
    		}
        	else{
    			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().AscendingSubmission_Name, 5)){
    				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_Name);
    			}
    		}
    		Thread.sleep(3000);	
    		
    		System.out.println("EOM sortSubmission()");
    		}
        	
            
	    
//###########################################################################################################################################
	    
}
