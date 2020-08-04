package subtitler.Reviewer;

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
import locators.ReviewerLocators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Reviewer;
import modules.Translator;
import modules.Verify;

/**                                                                  
 *                                                                   
 * @author pvaidya                                                   
 *Summary: Display subtitle from target if modified target is not present.
 *
 */ 

public class Sub_1595263 {
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName = "Submission_1595263"+CommonElements.BROWSER+"M1";
	 String OrganizationName = "Subtitle_Common_orgs";
	    String WorkflowName = "Two_Step_Workflow";
		String fileDirName = "common";
		String menu_Submission = "Submissions";
		String transcGroupName = "Common_group";
		String TranslatorGroupName = "Common_group";
		String ReviewGroupName = "Common_group";
		String targetlanguage = "German (Germany)";
		String menu_AllJobs = "Jobs";
		String menu_to_claim = "To Claim";
		String menu_ongoing = "Ongoing";
		String path;
		Boolean assertion = true;
		
		
		@BeforeMethod
		public void setup() throws Exception {
			General.action().startSystem("SUB_1595263");
			dataSet.put("TL_test_case_description", "SUB-1595263:Display subtitle from target if modified target is not present.");
			dataSet.put("TL_internal_testCase_ID", "1595263");
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
					// TODO NEW IMPL OF SUBMISSION CREATION USING 2K SRT
					PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
					PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", true);
					PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_LARGE_VIDEO,false,CommonElements.action().FILE_COMMON_LARGE_VIDEO_ASSET_ID);
					PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_2K_SRT_FOLDER);
					PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
					Thread.sleep(130000);

					//SEARCH SUBMISSION AND CHECK
					PM_user.action().SearchSubmisson_andCheck(SubmissionName);
					Thread.sleep(2000);
					assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
					if (assertion == false) {
						report("f","Assertion failed while verifying SubmissionName  after Search");
					}
					General.action().logoutMethod();
					
					
					// tran login
					General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
					Thread.sleep(1000);
					Translator.action().Navigate(menu_to_claim);
					Thread.sleep(1000);
					Translator.action().trans_ToClaim(SubmissionName);
					Thread.sleep(1000);
					Translator.action().Navigate(menu_ongoing);
					Thread.sleep(1000);
					Thread.sleep(1000);
					Translator.action().translate_onGoing_submission(SubmissionName, targetlanguage,true, false);
					Thread.sleep(1000);
					
					
					Thread.sleep(2000);
				    General.action().logoutMethod();
			           
			        // Review LogIn 
					General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
					Thread.sleep(1000);
					Reviewer.action().Navigate(menu_to_claim);
					Thread.sleep(1000);
					Reviewer.action().review_ToClaim(SubmissionName);
					Thread.sleep(1000);
					Reviewer.action().Navigate(menu_ongoing);
					Thread.sleep(1000);
					reviewer_onGoing_submission(SubmissionName, targetlanguage);
					Thread.sleep(2000);
					
					
			} catch (Exception e) {
		         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
	            }
	  }
		
		
		public void reviewer_onGoing_submission(String SubmissionName, String target)throws Exception {

			System.out.println("INSIDE reviewer_onGoing_submission  method()");

			General.action().waitforelemenetpresent(ReviewerLocators.Locator().review_search_input);
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().review_search_input);
			Thread.sleep(1000);
			General.action().ClearInputvalues(ReviewerLocators.Locator().review_search_input);
			Thread.sleep(1000);
			General.action().type(ReviewerLocators.Locator().review_search_input,SubmissionName);
			Thread.sleep(2000);

			if (Verify.action().VerifyElementPresent(ReviewerLocators.Locator().check_review_Target_Lang(SubmissionName, target))) {
				System.out.println("INSIDE IF--------");
				General.action().Click(ReviewerLocators.Locator().reviewer_selectSubmission_checkbox(SubmissionName,target));
			}
			Thread.sleep(1000);
			General.action().Click(ReviewerLocators.Locator().reviewer_Submission_Edit_icon);
			Thread.sleep(10000);
			
			PM_user.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
			Thread.sleep(2000);
			PM_user.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
			Thread.sleep(2000);
			General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1),"review modify 1");
			Thread.sleep(2000);
			PM_user.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
			Thread.sleep(2000);
			PM_user.action().ClearInputvalues(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
			Thread.sleep(2000);
			General.action().type(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3),"review modify 3");
			Thread.sleep(2000);
			
			PM_user.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3));
			Thread.sleep(2000);
			
			General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
			Thread.sleep(1000);
			General.action().ClearInputvalues(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput);
			Thread.sleep(1000);
			General.action().type(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput,"Comment for 1st Modified Target segment");
			Thread.sleep(5000);
			General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CommentButton);
			Thread.sleep(2000);
			
			PM_user.action().Click(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1));
			Thread.sleep(2000);
			
			General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab);
			Thread.sleep(1000);
			General.action().ClearInputvalues(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput);
			Thread.sleep(1000);
			General.action().type(ReviewerLocators.Locator().review_segmentHistoryTab_commentInput,"Comment for 1st Modified Target segment");
			Thread.sleep(5000);
			General.action().Click(ReviewerLocators.Locator().review_segmentHistoryTab_commentDialogBox_CommentButton);
			Thread.sleep(2000);
			
			assertion = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(3)).getText().contains("review modify 3");
			if (assertion == false) {
				report("f","Assertion failed while verifying modified target area ");
			}
			
			
			
			
		}
		
		
		public void assertion() throws Exception {
			//Note Last asertion is blocks because need to verify the text on video
			//Verify below steps :
			//1.Run the video and validate that subtitles are correctly displayed for both Target and Modified Target texts.
			assertion = General.driver.findElement(ReviewerLocators.Locator().reviewer_ModifiedTargetSegement_textarea(1)).getText().contains("review modify 1");
			if (assertion == false) {
				report("f","Assertion failed while verifying modified target area ");
			}else {
					report("b", "Last assertion is block");
				}
		}
					
					
		@AfterMethod
		public void tearDown() throws Exception {
				General.action().stopsystem();
		}

		public void report(String result, String notes) throws Exception
		{
		TestRailClient.testRailReportByID_production(dataSet.get("TL_internal_testCase_ID"),Subtitler_TestRail_Common_Properties.idTestPlan,Subtitler_TestRail_Common_Properties.idBuild,result,Subtitler_TestRail_Common_Properties.assignedTo,notes);
		if(result == "f")
		assertTrue(false);

		}


		}

