package subtitler.project_manager.Project_Director;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.PD_Pm_User_Submission_Locators;
import locators.Pm_User_Submission_Locators;
import modules.PD_PM_user;
import modules.PM_user;
import modules.Verify;

/**
 * 
 * @author pvaidya
 *Summary:Text in the toolbar reflect the user navigation.
 *Preconditions :Some submissions should be created in MediaNext and Project director tab.
 *
 */

public class Sub_1923681 { 
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String SubmissionName_MediaNext = "SUB_1923681_MediaNext"+CommonElements.BROWSER+"A2";
	String SubmissionName_PD = "SUB_1923681_PD"+CommonElements.BROWSER+"A2";
    String OrganizationName = "TransPerfect";
    String WorkflowName = "One_Step_Workflow";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String pd_tab_barColor;
	
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_1923681");
		dataSet.put("TL_test_case_description", "Toolbar should reflect users navigation");
		dataSet.put("TL_internal_testCase_ID", "1923681");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			 // login using PM Crdentials 
		    General.action().login(CommonElements.action().PD_PM_username,CommonElements.action().PD_PM_password);
		    Thread.sleep(5000);
		    
		    //Create Submission In Frome Media.Next Tab
			PM_user.action().Navigate(menu_Submission);
			PM_user.action().CreateSubmisson_Step1("17", "35", "80", "100");
			PM_user.action().CreateSubmisson_Step2_OrganizationAndWorkflow("Subtitle_Common_orgs", WorkflowName, "trans",TranslatorGroupName, "review", ReviewGroupName, false);
			PM_user.action().CreateSubmisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PM_user.action().CreateSubmisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			PM_user.action().CreateSubmisson_Step4_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName_MediaNext,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			Thread.sleep(2000);
			
			//Verify created submission in pending status
			PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName_MediaNext);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_MediaNext), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				
			}
			
			
			 //Create Submission In Frome PD Tab
			 //Create Submission In Frome PD Tab
		    PD_PM_user.action().Create_PD_Submisson_Step1_ProjectDirector(CommonElements.action().INSTANCE, CommonElements.action().DEPARTMENT, CommonElements.action().WORKFLOW, CommonElements.action().CLIENT,SubmissionName_PD);
			PD_PM_user.action().Create_PD_Submisson_Step2_MetaData(CommonElements.action().DATE_OFFSET,SubmissionName_PD,OrganizationName,CommonElements.action().COMMON_SOURCE_LANGUAGE,CommonElements.action().COMMON_TARGET_LANGUAGE);
			PD_PM_user.action().Create_PD_Submisson_Step4_MediSettings("17", "35", "80", "100");
			PD_PM_user.action().Create_PD_Submisson_Step3_videoFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_VIDEO_FOLDER,false,CommonElements.action().FILE_COMMON_VIDEO_ASSET_ID);
			PD_PM_user.action().Create_PD_Submisson_Step3_VideoSRTFile(CommonElements.action().FILE_ABSOLUTE_PATH+ CommonElements.action().FILE_COMMON_FOLDER+ CommonElements.action().FILE_COMMON_SRT_FOLDER);
			//Verify created submission in pending status
			PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName_PD);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_PD), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				
			}
			
			Thread.sleep(2000);
			PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_MEDIANext_Tab);
			Thread.sleep(2000);
			PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName_MediaNext);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_MediaNext), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				
			}
		
			System.out.println("Progress Bar Color:"+General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_User_MEDIANext_Tab_barColor).getCssValue("background-color"));
			String mediaNext_tab_barColor =General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_User_MEDIANext_Tab_barColor).getCssValue("background-color");		
			
			assertion = General.action().getFieldColor(mediaNext_tab_barColor,CommonElements.action().MediaNext_TAB_BARCOLOR);		
			if(assertion==false){
		    report("f","Failed at the checking Media.Next Tab Color");			
			}
			
		    
			Thread.sleep(2000);
			PM_user.action().Click(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab);
			Thread.sleep(2000);
			PD_PM_user.action().SearchSubmisson_andCheck(SubmissionName_PD);
			
			assertion = Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_SearchResult(SubmissionName_PD), 5);
			if (assertion == false) {
				report("f","Assertion failed while verifying SubmissionName  after Search");
				
			}
		    //Verify Blue bar above the submission list should show user navigation.
			System.out.println("Progress Bar Color:"+General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab_barColor).getCssValue("background-color"));
			pd_tab_barColor =General.driver.findElement(PD_Pm_User_Submission_Locators.Locator().Pm_User_ProjectDirector_Tab_barColor).getCssValue("background-color");		
			
			assertion = General.action().getFieldColor(pd_tab_barColor,CommonElements.action().PD_TAB_BARCOLOR);		
			if(assertion==false){
		    report("f","Failed at the checking PD Tab Color");			
			}
		    

		} catch (Exception e) {
	         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
         }
}
	
	
	public void assertion() throws Exception {
		
		//Verify that blue bar above the submission list is showing user navigation.
		assertion = General.action().getFieldColor(pd_tab_barColor,CommonElements.action().PD_TAB_BARCOLOR);		
		if(assertion==false){
	    report("f","Failed at the checking PD Tab Color");			
		}else {
		report("p", "All assertions passed");
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

