package subtitler.Linguist.Open_Job;
import static org.testng.AssertJUnit.assertTrue;


/**
 * 
 * @author pvaidya
 * This Test Case Is Restrict subtitles to maximum 2 lines
 */
import java.util.LinkedHashMap;
import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.PM_user;
import modules.Translator;
import modules.Verify;

public class Sub_979380 {
	
	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();

	
	String SubmissionName = "SUB_979380"+CommonElements.BROWSER+"R6";
    String OrganizationName = "Subtitle_Common_orgs";
    String WorkflowName = "Two_Step_Workflow";
	String fileDirName = "common";
	String menu_Submission = "Submissions";
	String TranslatorGroupName = "Common_group";
	String ReviewGroupName = "Common_group";
	String targetlanguage_1 = "German (Germany)";
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	String filePath = System.getProperty("user.dir") + "\\data\\Submission\\";
	String ThreeLineText[]= {"Transperfect","Solutions","pune"};
	 String TextAfterTypedThreeLineText;
	
	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("Sub_979380");
		dataSet.put("TL_test_case_description", "Sub_979380 :Restrict subtitles to maximum 2 lines");
		dataSet.put("TL_internal_testCase_ID", "979380");
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
				General.action().logoutMethod();
						
				// trans login
				General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
				Thread.sleep(1000);
					
				EditTransAndReviewMethod();
				
				assertion = TextAfterTypedThreeLineText.contains("Solutions\npune");
				System.out.println("Solutions\npune");
				System.out.println("Assertion Is======>"+assertion);
				if (assertion == true) {
					report("f","Three Line Text Is Possible To Enter");
				}else {
					System.out.println("Three Line Text Is Not Possible To Enter");
				}
				
				CompleteTaskForTransAndReviewAnd();
				
				Thread.sleep(2000);
				General.action().logoutMethod();
				
				//Review Login
				General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
				Thread.sleep(1000);
				
				EditTransAndReviewMethod();
						
						
		        } catch (Exception e) {
			         report("f","Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		            }
	      }
	
	
     public void EditTransAndReviewMethod() throws Exception {
		
		
    	 Translator.action().Navigate(menu_to_claim);
 		 Thread.sleep(1000);
 		 Translator.action().trans_ToClaim(SubmissionName);
 		 Thread.sleep(2000);
         Translator.action().Navigate(menu_ongoing);
         Thread.sleep(2000);
         Translator.action().SearchSubmisson_andCheck(SubmissionName);
 		
 		General.action().waitforelemenetpresent(CommonLocartors.Locator().Submission_Edit_icon);
 		Thread.sleep(2000);
 		General.action().Click(CommonLocartors.Locator().Submission_Edit_icon);
 		Thread.sleep(10000);
 		
 		General.action().waitforelemenetpresent(CommonLocartors.Locator().EditSegment_textarea(1));
        Thread.sleep(1000);
 	    General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
 	    Thread.sleep(3000);
 	    General.action().ClearInputvalues(CommonLocartors.Locator().EditSegment_textarea(1));
 	    Thread.sleep(1000);
 	    
 	    
 	   WebElement tabContent = General.driver.findElement(CommonLocartors.Locator().EditSegment_textarea(1));
 	   General.action().type(CommonLocartors.Locator().EditSegment_textarea(1),ThreeLineText[0]);
 	   Thread.sleep(2000);
 	   General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
	   Thread.sleep(3000); 
	 	   
	   Actions act= new Actions(General.driver);
	   act.moveToElement(tabContent).click().sendKeys(Keys.ENTER).build().perform();
	   General.action().type(CommonLocartors.Locator().EditSegment_textarea(1),ThreeLineText[1]);
	   Thread.sleep(2000);
	   General.action().Click(CommonLocartors.Locator().EditSegment_textarea(1));
	   Thread.sleep(3000);

	   Actions act1= new Actions(General.driver);
	   act1.moveToElement(tabContent).click().sendKeys(Keys.ENTER).build().perform();
	   General.action().type(CommonLocartors.Locator().EditSegment_textarea(1),ThreeLineText[2]);
		

 	    
 	    TextAfterTypedThreeLineText = General.driver.findElement(CommonLocartors.Locator().EditSegment_textarea(1)).getText();
		System.out.println("Text After Typing Three Line Text On 1st Segment Is=======>" +TextAfterTypedThreeLineText);
		Thread.sleep(1000);
		
		}
     
     
     public void CompleteTaskForTransAndReviewAnd() throws InterruptedException {
			
			System.out.println("INSIDE method CComplete Task For Trans , Review And Transcription()\n"); 
			

		    PM_user.action().waitforelemenetpresent(CommonLocartors.Locator().SubmissionEdit_complete_task);
	        Thread.sleep(1000);
	        PM_user.action().Click(CommonLocartors.Locator().SubmissionEdit_complete_task);
		    Thread.sleep(1000);

	        PM_user.action().waitforelemenetpresent(CommonLocartors.Locator().SubmissionEdit_complete_Task_Alert_button);
	        Thread.sleep(1000);
	        PM_user.action().Click(CommonLocartors.Locator().SubmissionEdit_complete_Task_Alert_button);
	        Thread.sleep(6000);
	        
	        System.out.println("EOM Complete Task For Trans , Review And Transcription()\n");
			
			
		}


public void assertion() throws Exception {
	assertion = TextAfterTypedThreeLineText.contains("Solutions\npune");
	System.out.println("Solutions\npune");
	System.out.println("Assertion Is======>"+assertion);
	if (assertion == true) {
		report("f","Three Line Text Is Possible To Enter");
	}else {
		report("p", "Three Line Text Is Not Possible To Enter"+" All Assertion passed.");
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
