package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;
/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: This case verifies if user can search video file by asset id while creating submission.
 */                                                                  
                                                                     
public class Sub_1266215 {
	

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	
	String SubmissionName = "SUB_1266215"+CommonElements.BROWSER+"M1";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String assetID="46555";

	@BeforeMethod
	public void setup() throws Exception {General.action().startSystem("SUB_1266215");
		dataSet.put("TL_test_case_description", "SUB-1266215:Create Submission - Search Catalog by Asset ID");
		dataSet.put("TL_internal_testCase_ID", "1266215");
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
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
			
			//SELECT SEARCH IN CATALOG OPTION AND ENTER ASSET ID
			CreateSubmisson_Step3_SearchInCatalog();
			Thread.sleep(3000);
			
			//VERIFY FILE IS PRESENT FOR RESPECTIVE ASSET ID
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_fileFirstRow(assetID), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying materials by asset id");
				
			}
			
		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}
	
	
	 public void CreateSubmisson_Step3_SearchInCatalog() throws Exception{
		 
		    System.out.println("INSIDE METHOD CreateSubmisson_Step3_SearchInCatalog()");
		 
		    Thread.sleep(3000);
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
			General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID, assetID);
		
			Thread.sleep(2000);
		    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_SearchBtn);
			Thread.sleep(5000);
				
	 }
	
			
			public void assertion() throws Exception {
				//VERIFY FILE IS PRESENT FOR RESPECTIVE ASSET ID
				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_fileFirstRow(assetID), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying materials by asset id");
					
				} else {
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