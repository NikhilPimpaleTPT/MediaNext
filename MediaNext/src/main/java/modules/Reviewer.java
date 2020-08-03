package modules;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import locators.CommonLocartors;
import locators.ReviewerLocators;

public class Reviewer extends General {

	
	private static Reviewer review_objects;

	 /**
	  * Method used to self-instantiate the class. Will make sure one object, and
	  * one object only is created in memory for this class. The purpose is to be
	  * able to call this object dynamically from any JAVA class that includes
	  * this as an import.
	  * 
	  * @return Returns the object instantiated from the class.
	  */
	 public static synchronized Reviewer action() {
	  try {
	   if (review_objects.equals(null)) {
		   review_objects = new Reviewer();
	   }
	  } catch (Exception NOSYSTEM) {
		  review_objects = new Reviewer();
	  }
	  return review_objects;
	 }
	 
	 //ADD GENERIC METHODS
//############################################################################################################################
		/**
		 * This method is used to search submission
		 * 
		 * @param SubmissionName
		 * @throws Exception
		 */
		public void SearchSubmisson(String SubmissionName) throws Exception {
			
			System.out.println("INSIDE METHOD SearchSubmisson");
			waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
			Thread.sleep(1000);
			ClearInputvalues(ReviewerLocators.Locator().review_search_input);
			Thread.sleep(1000);
			type(ReviewerLocators.Locator().review_search_input, SubmissionName);
			Thread.sleep(1000);
//			type(ReviewerLocators.Locator().review_search_input, Keys.chord(Keys.ENTER));
//			Thread.sleep(1000);
	    	//TODO NOT REQUIRED AS PER JAVA 8
	    	type_withKEYS(ReviewerLocators.Locator().review_search_input, Keys.ENTER,false);
	    	Thread.sleep(1000);	
			waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
			Thread.sleep(1000);
			System.out.println("EOM SearchSubmisson()");
		}
	 
 //###########################################################################################################################
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
//	    	type(ReviewerLocators.Locator().review_search_input, Keys.chord(Keys.ENTER));
//	    	Thread.sleep(1000);	
	    	//TODO NOT REQUIRED AS PER JAVA 8
	    	//TODO NOT REQUIRED AS PER NEW IMPROVEMENT FOR FILTER SUBMISSION (1.22.0 RC1)
//	    	type_withKEYS(ReviewerLocators.Locator().review_search_input, Keys.ENTER,false);
//	    	Thread.sleep(1000);	
//	    	waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
//	    	Thread.sleep(1000);	
			General.action().waitforelemenetpresent(ReviewerLocators.Locator().SelectSubmission_Checkbox(SubmissionName));
			Thread.sleep(1000);			
			General.action().Click(ReviewerLocators.Locator().SelectSubmission_Checkbox(SubmissionName));
			Thread.sleep(2000);
	    	
 	    System.out.println("EOM SearchSubmisson()\n");

	    }
 //############################################################################################################################
	    
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
//#################################################################################################################
	    public void sort(String menu, String columnName,boolean sort) throws Exception{
	    	
	    	System.out.println("INSIDE METHOD sort");
		 	
			Reviewer.action().Navigate(menu);
			Thread.sleep(4000);
			General.action().Click(ReviewerLocators.Locator().colunm_Name(columnName));
					
			//THIS FOR ASCENDING
			if(sort){
				if(Verify.action().verifyElementPresent(ReviewerLocators.Locator().descending_sort(columnName), 5 )){
					General.action().Click(ReviewerLocators.Locator().colunm_Name(columnName));
				}
			}
			else{
				if(Verify.action().verifyElementPresent(ReviewerLocators.Locator().Ascending_sort(columnName), 5 )){
					General.action().Click(ReviewerLocators.Locator().colunm_Name(columnName));
				}
			}
			Thread.sleep(3000);		
			
			System.out.println("EOM sort()");
			}
//###########################################################################################################
	    
	    public void review_ToClaim(String SubmissionName) throws Exception {
			
			 System.out.println("INSIDE review_ToClaim  method()");
			 
				waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
				Thread.sleep(1000);
				Click(ReviewerLocators.Locator().review_search_input);
				Thread.sleep(1000);
				ClearInputvalues(ReviewerLocators.Locator().review_search_input);
				Thread.sleep(1000);
				type(ReviewerLocators.Locator().review_search_input, SubmissionName);
				Thread.sleep(1000);
				
				   if(Verify.action().verifyElementNotPresent(ReviewerLocators.Locator().review_toClaim_checkbox_checked,5))
				   {	
					System.out.println("INSIDE IF--------");
					Thread.sleep(1000);
					waitforelemenetpresent(ReviewerLocators.Locator().review_toClaim_select_header_checkbox);
					Thread.sleep(1000);
					Click(ReviewerLocators.Locator().review_toClaim_select_header_checkbox);
					Thread.sleep(1000);
					System.out.println("CLICK DONE------");
				   }
				Click(ReviewerLocators.Locator().reviewer_clamSubmission_icon);
				Thread.sleep(3000);
				waitforelemenetpresent(ReviewerLocators.Locator().reviewer_claimAlert_claim_button);
				Thread.sleep(1000);
				Click(ReviewerLocators.Locator().reviewer_claimAlert_claim_button);
				Thread.sleep(2000);
				Thread.sleep(defaultWaitPeriod*5);
				
				System.out.println("EOM review_ToClaim()");

		}
		
//###########################################################################################################
	    
	    
	    
	    public void Reviewer_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
			
			 System.out.println("INSIDE trans_Ongoing  method()");
			 
			    General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
				Thread.sleep(1000);
				General.action().Click(ReviewerLocators.Locator().review_search_input);
				Thread.sleep(1000);
				General.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
				Thread.sleep(1000);
				General.action().type(ReviewerLocators.Locator().review_search_input, SubmissionName);
				Thread.sleep(2000);
				
				if(Verify.action().VerifyElementPresent(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target)))
				{
					System.out.println("INSIDE IF--------");
					General.action().Click(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target));
				}
					  Thread.sleep(1000);
					  General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
				
					  Thread.sleep(5000);
				      List <WebElement> listofIds1= General.action().driver.findElements(ReviewerLocators.Locator().reviewer_toClaim_ListofallIds);
					  Thread.sleep(1000);
					  System.out.println("No of IDS--------"+listofIds1.size());
					  Thread.sleep(3000);
					  
				    for(int i=1;i<=listofIds1.size();i++){
				    	Thread.sleep(2000);
				    	General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
						Thread.sleep(1000);
						General.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
						Thread.sleep(1000);
						General.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
						Thread.sleep(1000);
						General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(i));
						Thread.sleep(1000);
						General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
						Thread.sleep(1000);
						General.action().Click(ReviewerLocators.Locator().reviewer_CopySource_toTarget_Button);
						Thread.sleep(1000);
					
				    }
				    
				    if(complete){
				    	General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_Complete_Button);
						Thread.sleep(1000);
						General.action().Click(ReviewerLocators.Locator().reviewer_Complete_Button);
						Thread.sleep(1000);
						General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_CompleteDailoge_Button);
						Thread.sleep(1000);
						General.action().Click(ReviewerLocators.Locator().reviewer_CompleteDailoge_Button);
						Thread.sleep(3000);
						if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
						General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_BackToSubmissionList_Menubutton_forCompleted);
						Thread.sleep(1000);
						General.action().Click(ReviewerLocators.Locator().review_BackToSubmissionList_Menubutton_forCompleted);
					    }
					}
					
				    
				    if(back){
				    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
							General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_BackToSubmissionList_Button);
							Thread.sleep(1000);
							General.action().Click(ReviewerLocators.Locator().review_BackToSubmissionList_Button);
					    	}
				    }
				    
					 System.out.println("EOM trans_Ongoing  method()");
		}
}
