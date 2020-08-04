package subtitler.project_manager.Open_Jobs;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import modules.PM_user;
import modules.Verify;

public class Sub_306371 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	String SubmissionName = "SUB_306371"+CommonElements.BROWSER+"P2";
	String SubmissionName_transc = "SUB_306371_transc"+CommonElements.BROWSER+"P2";
	String OrganizationName = "Subtitle_Common_orgs";
	String WorkflowName = "Two_Step_Workflow";
	String WorkflowName_tranc = "transc_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
    String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String ReviewGroupName = "Common_group";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_306371");
		dataSet.put("TL_test_case_description","Sub_306371: Go To Subtitle dialog");
		dataSet.put("TL_internal_testCase_ID", "306371");
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
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName, "trans",TranslatorGroupName, "review", "", true);
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
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			PM_GoToSubtitle(SubmissionName, targetlanguage_1, 2, "2");
			Thread.sleep(2000);

			// sub for transcription
			PM_user.action().Navigate(menu_Submission);
			// TODO NEW IMPL OF SUBMISSION CREATION
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow(OrganizationName, WorkflowName_tranc, "trans",TranslatorGroupName, "", "", false);
			System.out.println("filePath------>" + filePath);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName_transc,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			PM_user.action().SearchSubmisson(SubmissionName_transc);
			Thread.sleep(2000);

			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_transc), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
			}
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_to_claim);
			Thread.sleep(2000);
			PM_user.action().PM_ToClaim(SubmissionName_transc);
			Thread.sleep(2000);
			PM_user.action().Navigate(menu_ongoing);
			Thread.sleep(2000);
			PM_GoToSubtitle_transc(SubmissionName_transc, targetlanguage_1, 2,"2");
			Thread.sleep(2000);

		} catch (Exception e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}

	}

	public void PM_GoToSubtitle(String SubmissionName, String target,int subtitleNum, String subtitleNumber) throws Exception {

		System.out.println("INSIDE PM_GoToSubtitle  method()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		Thread.sleep(1000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName,target));
		}
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
        Thread.sleep(5000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1),Keys.chord(Keys.ALT, "g"));//PM_GoToSubtitleID_Menu
		Thread.sleep(3000);
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Trans_GoToSubtitleID_Menu, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Go To Subtittle ID Menu");
		}
		
        General.action().type(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle,subtitleNumber);
		Thread.sleep(2000);
        General.action().Click(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Go_button);
		Thread.sleep(2000);
		
        assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().SubtitleRowId(subtitleNum), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying subtitle text area is active  after Search");
		}

		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1),Keys.chord(Keys.ALT, "g"));
		Thread.sleep(1000);
        General.action().type(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle, "abc");
		Thread.sleep(2000);

		boolean PM_GoToSubtitle_Go_Button_alphabets = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Go_button).isDisplayed();
        System.out.println("PM_GoToSubtitle_Go_Button is disabled for alphabets values:- "+ PM_GoToSubtitle_Go_Button_alphabets);
		Thread.sleep(2000);

		assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Go_button).isDisplayed();
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_GoToSubtitle_Go_Button_alphabets values  after Search");
		}

		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1),Keys.chord(Keys.ALT, "g"));
		Thread.sleep(1000);

		General.action().type(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle, ".0");
		Thread.sleep(2000);

		boolean PM_GoToSubtitle_Go_Button_decimal = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Go_button).isDisplayed();

		System.out.println("PM_GoToSubtitle_Go_Button is disabled for decimal values:- "+ PM_GoToSubtitle_Go_Button_decimal);
		Thread.sleep(2000);

		assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Go_button).isDisplayed();
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_GoToSubtitle_Go_Button_decimal values  after Search");
		}
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Close_button);
		Thread.sleep(2000);

	    General.action().type(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(1),Keys.chord(Keys.ALT, "g"));
		Thread.sleep(1000);

		General.action().type(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle, "7");
		Thread.sleep(2000);

		boolean PM_GoToSubtitle_Go_Button_outOfrange_value = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Go_button).isDisplayed();

		System.out.println("PM_GoToSubtitle_Go_Button is disabled for Out of range values:- "+ PM_GoToSubtitle_Go_Button_outOfrange_value);
		Thread.sleep(2000);

		String PM_GoToSubtitle_Go_Button_outOfrange_error_message = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_message).getText();
		System.out.println("ERROR MESSAGE*********"+ PM_GoToSubtitle_Go_Button_outOfrange_error_message);

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_message,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_GoToSubtitle_Go_Button_outOfrange_error_message  after Search");
		}
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Close_button);

		Thread.sleep(3000);
		/*General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
		*/
		if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
	    	}

		System.out.println("EOM PM_GoToSubtitle  method()");
	}

	public void PM_GoToSubtitle_transc(String SubmissionName, String target,int subtitleNum, String subtitleNumber) throws Exception {

		System.out.println("INSIDE PM_GoToSubtitle_transc  method()");

		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		Thread.sleep(1000);
		General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
		Thread.sleep(1000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input,SubmissionName);
		Thread.sleep(2000);

		if (Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName, target))) {
			System.out.println("INSIDE IF--------");
			General.action().Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName,target));
		}
		Thread.sleep(1000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);

		Thread.sleep(5000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_transc(2));

		Thread.sleep(3000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_transc_textarea,Keys.chord(Keys.ALT, "g"));
		Thread.sleep(1000);
		
		
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_Transc_GoToSubtitleID_Menu, 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying Go To Subtittle ID Menu");
		}

		General.action().type(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_transc,subtitleNumber);
		Thread.sleep(2000);

		General.action().Click(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Go_button);
		Thread.sleep(3000);

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().SubtitleRowId(subtitleNum), 5);
		if (assertion == false) {
			report("f","Assertion failed while verifying subtitle text area is active  after Search");
		}

		Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_transc(2));

		Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_transc_textarea,Keys.chord(Keys.ALT, "g"));
		Thread.sleep(1000);

		General.action().type(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_transc, "abc");
		Thread.sleep(2000);

		boolean PM_GoToSubtitle_Go_Button_alphabets = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Go_button).isDisplayed();
		System.out.println("PM_GoToSubtitle_Go_Button is Disabled for alphabets values:- "+ PM_GoToSubtitle_Go_Button_alphabets);
		Thread.sleep(2000);

		assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Go_button).isDisplayed();
		if (assertion == false) {
			report("f","Assertion failed while verifying subtitle text area is active  after Search");
		}
		Thread.sleep(2000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Close_button);
	    Thread.sleep(3000);
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_transc(2));
        Thread.sleep(2000);
		General.action().type(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_transc_textarea,Keys.chord(Keys.ALT, "g"));
		Thread.sleep(1000);
        General.action().type(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_transc, ".0");
		Thread.sleep(2000);

		boolean PM_GoToSubtitle_Go_Button_decimal = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Go_button).isDisplayed();
		System.out.println("PM_GoToSubtitle_Go_Button is Disabled for decimal values:- "+ PM_GoToSubtitle_Go_Button_decimal);
		Thread.sleep(2000);

		assertion = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Go_button).isDisplayed();
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_GoToSubtitle_Go_Button for decimal values  after Search");
		}
		General.action().Click(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_Close_button);
		Thread.sleep(2000);
		
		General.action().type(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_transc_textarea,Keys.chord(Keys.ALT, "g"));
		Thread.sleep(1000);

		General.action().type(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_transc, "7");
		Thread.sleep(2000);

		String PM_GoToSubtitle_Go_Button_outOfrange_error_message = General.driver.findElement(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_message).getText();
		System.out.println("ERROR MESSAGE*********"+ PM_GoToSubtitle_Go_Button_outOfrange_error_message);

		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_message,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_GoToSubtitle_Go_Button_outOfrange_error_message  after Search");
		}

		System.out.println("EOM PM_GoToSubtitle_transc  method()");
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().PM_GoToSubtitle_message,5);
		if (assertion == false) {
			report("f","Assertion failed while verifying PM_GoToSubtitle_Go_Button_outOfrange_error_message  after Search");
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
