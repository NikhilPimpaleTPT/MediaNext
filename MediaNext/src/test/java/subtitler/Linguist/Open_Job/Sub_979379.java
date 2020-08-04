package subtitler.Linguist.Open_Job;
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
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;
/**
 * 
 * @author pvaidya
 * This Test Case Is Remove segment count column from To Claim, Ongoing, Completed folder
 */
public class Sub_979379 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String SubmissionName = "SUB_979379"+CommonElements.BROWSER+"M3";
    String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "One_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String menu_completed = "Completed";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String Wordcountsource_Text="Word count (source)";
	String Segmentcount_Text="segment count";
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_979379");
		dataSet.put("TL_test_case_description", "Sub_979379 :Remove segment count column from To Claim, Ongoing, Completed folder");
		dataSet.put("TL_internal_testCase_ID", "979379");
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
				PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", false);
				System.out.println("filePath------>" + filePath);
				PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
				PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
				PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
				Thread.sleep(2000);
				PM_user.action().SearchSubmisson(SubmissionName);
				Thread.sleep(2000);

				assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName), 5);
				if (assertion == false) {
					report("f","Assertion failed while verifying SubmissionName  after Search");
				}
				Thread.sleep(2000);
				General.action().logoutMethod();
						
				// trans login
				General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
				Thread.sleep(1000);
					
				EditTransAndReviewMethod();
						
						
		        } catch (Exception e) {
			         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		            }
	      }
	
	
     public void EditTransAndReviewMethod() throws Exception {
		
		
		Translator.action().Navigate(menu_to_claim);
		Thread.sleep(1000);
		
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Pm_user_SearchSubmission_Trans_WordCountSource(Wordcountsource_Text), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Word count source Header after Search");
		}
		
		assertion = Verify.action().verifyElementNotPresent(TranslatorLocators.Locator().Pm_user_SearchSubmission_Trans_WordCountSource(Segmentcount_Text), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Segment count Header Present after Search");
		}
		
		Thread.sleep(2000);
        Translator.action().Navigate(menu_ongoing);
        Thread.sleep(2000);
        
        assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Pm_user_SearchSubmission_Trans_WordCountSource(Wordcountsource_Text), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Word count source Header after Search");
		}
		
		assertion = Verify.action().verifyElementNotPresent(TranslatorLocators.Locator().Pm_user_SearchSubmission_Trans_WordCountSource(Segmentcount_Text), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Segment count Header Present after Search");
		}
		
		Thread.sleep(2000);
        Translator.action().Navigate(menu_completed);
        Thread.sleep(2000);
		 
        assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().Pm_user_SearchSubmission_Trans_WordCountSource(Wordcountsource_Text), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Word count source Header after Search");
		}
		
		
	}


public void assertion() throws Exception {
	assertion = Verify.action().verifyElementNotPresent(TranslatorLocators.Locator().Pm_user_SearchSubmission_Trans_WordCountSource(Segmentcount_Text), 5);
	if (assertion == false) {
		report("f","Assertion failed while verifying Segment count Header Present after Search");
	}else {
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
