package subtitler.project_manager.submission;
import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.LoginLocators;
import locators.Pm_User_Submission_Locators;
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Verify;
import modules.admin;
/**
 * 
 * @author pvaidya
 * This Test Case Is To Create New Submission - Search in catalog
 */
public class Sub_1011358 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_1011358"+CommonElements.BROWSER+"T4";
	String targetlanguage_1 = "German (Germany)";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Three_Step_Transc_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String transcGroupName = "Common_group";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String SearchText="*testing*";
	
	String[] SearchIncatalog_Table_Header= {"Owner","Title","Serial title","Season #","Season title","Episode #"};
	
	int ItemPerPage1= 10;
	int ItemPerPage2= 20;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub__1011358");
		dataSet.put("TL_test_case_description", "New Submission - Search in catalog .");
		dataSet.put("TL_internal_testCase_ID", "1011358");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			// PM login
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow_ThreeStepWorkflow(OrganizationName, WorkflowName,"transcription",transcGroupName,"Trans",TranslatorGroupName,true, "Review", ReviewGroupName, true);
			CreateSubmisson_Step3_SearchInCatalog();
			admin.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			admin.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET, SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			admin.action().SearchSubmisson(SubmissionName);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			
			//To Verify Submission Can Open In Transcription Phase When Submission created Using video form catalogue
			
			MethodeToEditSubmission();
			Thread.sleep(6000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
    	    Thread.sleep(6000);
    	    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
    	    Thread.sleep(4000);
    		
    	     assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transcription_textarea(1), 5);
 			 if (assertion == false) {
 				report("f","Assertion failed while verifying Submission Created By Selecting Video From Search In Catalog Can Be Edited(Verifying Text Area)");
 			 }
 			 
 			CompleteTaskForTransAndReviewAndTranscription();
 			
 			Thread.sleep(3000);
			General.action().logoutMethod();
    		
 	        //Translator login	
    		General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
    		
    		//To Verify Submission Can Open In Translation Phase When Submission created Using video form catalogue
    		
    		MethodeToEditSubmission();
    		
    		General.action().waitforelemenetpresent(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
    	    Thread.sleep(2000);
    	    General.action().Click(TranslatorLocators.Locator().Translator_Submission_Edit_icon);
    	    Thread.sleep(4000);
    		
    	     assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().translator_TargetSegement_textarea(1), 5);
 			 if (assertion == false) {
 				report("f","Assertion failed while verifying Submission Created By Selecting Video From Search In Catalog Can Be Edited(Verifying Text Area)");
 			 }
			 
 			 CompleteTaskForTransAndReviewAndTranscription();
 			
 			Thread.sleep(3000);
			General.action().logoutMethod();
			// Review LogIn 
		    General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
		   
		  //To Verify Submission Can Open In Review Phase When Submission created Using video form catalogue
		    
		    MethodeToEditSubmission();
	 		   
		    General.action().waitforelemenetpresent(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
   	        Thread.sleep(2000);
   	        General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
   	        Thread.sleep(4000);
			
				}
			catch (Exception e) {
				   report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
			       }

			   }
	
	
	 public void MethodeToEditSubmission() throws Exception {
		Thread.sleep(2000);
		PM_user.action().Navigate(menu_to_claim);
        Thread.sleep(2000);
        PM_user.action().PM_ToClaim(SubmissionName);
        Thread.sleep(3000);
        PM_user.action().Navigate(menu_ongoing);
        Thread.sleep(3000);
		 
		PM_user.action().SearchSubmisson_andCheck(SubmissionName);
		Thread.sleep(2000);
	}
		
	 public void CreateSubmisson_Step3_SearchInCatalog() throws Exception{
			 
		    System.out.println("INSIDE METHOD CreateSubmisson_Step3_SearchInCatalog()");
		 
//		    Thread.sleep(3000);
//		    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_RadioButton);
//	    	Thread.sleep(3000);
//	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_RadioButton);
			Thread.sleep(2000);
			
			Thread.sleep(3000);
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_Owner);
		    Thread.sleep(3000);
		    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_Owner);
			Thread.sleep(2000);
			 
		    Thread.sleep(1000);
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_Owner, SearchText);
		
			Thread.sleep(2000);
		    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_SearchBtn);
			Thread.sleep(15000);
			 
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_HeaderOptions(SearchIncatalog_Table_Header[0]), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying Owner Header After Search");
					
				}
				
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_HeaderOptions(SearchIncatalog_Table_Header[1]), 5);
				if (assertion == false) {
						report("f","Assertion failed while verifying Title Header After Search");
						
				}
				 
					
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_HeaderOptions(SearchIncatalog_Table_Header[2]), 5);
				if (assertion == false) {
							report("f","Assertion failed while verifying SerialTitle Header After Search");
							
				}
					 
						
			 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_HeaderOptions(SearchIncatalog_Table_Header[3]), 5);
				if (assertion == false) {
								report("f","Assertion failed while verifying Season Header After Search");
								
				}
						 
							
			 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_HeaderOptions(SearchIncatalog_Table_Header[4]), 5);
				if (assertion == false) {
									report("f","Assertion failed while verifying Season Title Header After Search");
									
				}
							 
								
			 assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_HeaderOptions(SearchIncatalog_Table_Header[5]), 5);
				if (assertion == false) {
										report("f","Assertion failed while verifying Episode Header After Search");
										
				}
				
			    
			  //Sort by Owner in Descending order
			  SortOwner(true);
			  Thread.sleep(3000);
			    
			    
			  //Sort by Owner in Ascending order
		      SortOwner(false);
			  Thread.sleep(3000);
			    
			  
			
			  
			 //To Select Item Per Page
			 
			 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SearchFromCatalog_ItemPerPage);
			 Thread.sleep(3000);
			 General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SearchFromCatalog_ItemPerPage);
			 Thread.sleep(2000);
				
				 
			 General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SearchFromCatalog_ItemPerPage_Value(ItemPerPage1));
			 Thread.sleep(3000);
			 General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SearchFromCatalog_ItemPerPage_Value(ItemPerPage1));
			 Thread.sleep(2000);
			    
			 List <WebElement> allRows= General.driver.findElements(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AllRow);
			 Thread.sleep(1000);
			 System.out.println("All Rows--------"+allRows.size());
			
				
				
			for(int i=1;i<=allRows.size();i++) {
					
			System.out.println("OWNER NAME--->"+allRows.get(i).getText());
			Thread.sleep(3000);															
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_searchCatalog_videoFile(i));
		    Thread.sleep(3000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_searchCatalog_videoFile(i));
			Thread.sleep(2000);
					
					
					
			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Searchincatalog_NoVideoAttachedMassage, 5)) {
			//IF TRUE
			System.out.println("THERE IS NO VIDEO FILE SO MOVING ON TO NEXT FILE");
			}else {
			//IF FALSE
			System.out.println("GREAT!!! IT HAS VIDEO FILE ATTACHED");
			System.out.println("Selected Video From Catalogue is =====>"+allRows.get(i).getText());
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_searchCatalog_videoFile(i));
			Thread.sleep(3000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_searchCatalog_videoFile(i));
			Thread.sleep(3000);
			System.out.println("Video Is Selected to create Submission");
			break;
					}
				    
					
		    if(i==allRows.size()) {
		    System.out.println("WE ARE ON LAST LINE OR FILE");
		
		    // Navigate Page When Size Of Page Equal To All Rows
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SearchFromCatalog_ItemPerPage);
			Thread.sleep(3000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SearchFromCatalog_ItemPerPage);
			Thread.sleep(2000);
							
							 
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SearchFromCatalog_ItemPerPage_Value(ItemPerPage2));
			Thread.sleep(3000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SearchFromCatalog_ItemPerPage_Value(ItemPerPage2));
			Thread.sleep(2000);
				    }
				} 
				
              
			 System.out.println(" EOM METHOD CreateSubmisson_Step3_SearchInCatalog()");
				
	 }
	 
	 
	 public void CompleteTaskForTransAndReviewAndTranscription() throws InterruptedException {
			
			System.out.println("INSIDE method CompleteTaskForTransAndReviewAndTranscription()\n"); 
			

		    PM_user.action().waitforelemenetpresent(CommonLocartors.Locator().SubmissionEdit_complete_task);
	        Thread.sleep(1000);
	        PM_user.action().Click(CommonLocartors.Locator().SubmissionEdit_complete_task);
		    Thread.sleep(1000);

	        PM_user.action().waitforelemenetpresent(CommonLocartors.Locator().SubmissionEdit_complete_Task_Alert_button);
	        Thread.sleep(1000);
	        PM_user.action().Click(CommonLocartors.Locator().SubmissionEdit_complete_Task_Alert_button);
	        Thread.sleep(6000);
	        
	        System.out.println("EOM CompleteTaskForTransAndReviewAndTranscription()\n");
			
			
		}
		 
		   public void SortOwner(boolean sort) throws Exception{
           	
           	System.out.println("INSIDE METHOD SortOwner");
           	
           	//To  get focus on column header
           	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_Header_Owener);
        
           	
           	if(sort){
           		
           		//This is for DSCE
       			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_OwnerAsc, 5)){
       				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_Header_Owener);
       			}
           		
                }
           	else{
           		//This is for ASC
           		if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_OwnerDsc, 5)){
       				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_Header_Owener);
       			}
           		
           		
           		
           		
           		
       		}
       		Thread.sleep(3000);	
       		
       		System.out.println("EOM SortOwner");
       		}
           	
	

		public void assertion() throws Exception {
			 assertion = Verify.action().verifyElementPresent(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1), 5);
			 if (assertion == false) {
				report("f","Assertion failed while verifying Submission Created By Selecting Video From Search In Catalog Can Be Edited(Verifying Text Area)");
			 }
			 else {
				report("p", "All Assertion passed.");
			}
		}

		@AfterMethod
		public void tearDown() throws Exception {
			General.action().stopsystem();
		}

		public void report(String result, String notes) throws Exception {
			TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild, result,Subtitler_TestRail_Common_Properties.assignedTo, notes);
			if (result == "f")
				assertTrue(false);
		}
		}

		
