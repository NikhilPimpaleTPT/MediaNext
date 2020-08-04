package subtitler.project_manager.submission;

import static org.testng.AssertJUnit.assertTrue;

import java.util.LinkedHashMap;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.Color;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.CommonLocartors;
import modules.Verify;
import modules.admin;
/**
 * 
 * @author pvaidya
 *
 */
public class Sub_776262 {

	
Boolean assertion = true;
	
	
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
	String hexColorCode1 ="#000000";
	String hexColorCode2 ="#303030";
	String bgColor1;
	String bgColor2;
	String hexColor1;
	String hexColor2;
	String menu_to_claim = "To Claim";
	String menu_ongoing = "Ongoing";
	
@BeforeMethod	
	public void setup() throws Exception{
	General.action().startSystem("SUB_776262 ");
	dataSet.put("TL_test_case_description","SUB-776262 :The purpose of this ticket is to apply a dark theme to ALL screens / dialogs of Subtitler.");
	dataSet.put("TL_internal_testCase_ID", "776262");
		}
	
	
	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}
	
	
	public void testCase(LinkedHashMap<String, String> dataSet) throws Exception {
		try{
			
			//=======Admin=======
			
			admin.action().login(CommonElements.action().admin_username,CommonElements.action().admin_passowrd);
			Thread.sleep(5000);
			backgroundVerification();
			Thread.sleep(3000);
			General.action().logoutMethod();
			
			//=======PM=======
			
			admin.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			Thread.sleep(5000);
			backgroundVerification();
			Thread.sleep(3000);
			General.action().logoutMethod();
			
			//=======Translator=======
			
		    General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
	        Thread.sleep(5000);
			backgroundVerification();
			Thread.sleep(3000);
			General.action().logoutMethod();
			 
			//=======Reviewer=======	
			 
			 General.action().login(CommonElements.action().Reviwer_username, CommonElements.action().password);
			  Thread.sleep(5000);

				 General.action().Click(CommonLocartors.Locator().ongoing_button);		
				 Thread.sleep(3000);
				 bgColor1 = General.driver.findElement(CommonLocartors.Locator().ongoing_button).getCssValue("background-color");
				 General.action().Click(CommonLocartors.Locator().submissionList_firstRow);
				 Thread.sleep(3000);
				 bgColor2 = General.driver.findElement(CommonLocartors.Locator().submissionList_firstRow).getCssValue("background-color");
				 
		    	 System.out.println("bgColor1======"+bgColor1);
				 System.out.println("bgColor2======"+bgColor2);
				 
			     hexColor1=Color.fromString(bgColor1).asHex();
				 System.out.println("Hex Color1====="+hexColor1);
					
				 hexColor2=Color.fromString(bgColor2).asHex();
				 System.out.println("Hex Color2====="+hexColor2);
			
				assertion = hexColor1.equalsIgnoreCase(hexColorCode1);
				if (assertion == false) {
					report("f","Assertion failed while verifying Background color of On-going button");
				}
				
				assertion = hexColor2.equalsIgnoreCase(hexColorCode2);
				 if (assertion == false) {
					report("f","Assertion failed while verifying Background color of Submission first row button");
				}

	    }catch(Throwable e){
				   report("f", "Execution level error was encountered.\n\nError log:\n\n"+ Verify.action().getErrorBuffer(e));
		}
		
		
		
		
	}
	
     public void backgroundVerification() throws Exception {
    	 System.out.println("ENTER method backgroundVerification()");
//			 General.action().Click(CommonLocartors.Locator().ongoing_button);		
//			 Thread.sleep(3000);
			 General.action().waitforelemenetpresent(CommonLocartors.Locator().SelectMenu(menu_to_claim));
			 General.action().Click(CommonLocartors.Locator().SelectMenu(menu_to_claim));
			 Thread.sleep(1000);
//			 bgColor1 = General.driver.findElement(CommonLocartors.Locator().ongoing_button).getCssValue("background-color");
			 bgColor1 = General.driver.findElement(CommonLocartors.Locator().SelectMenu(menu_to_claim)).getCssValue("background-color");
			 Thread.sleep(4000);
			 General.action().Click(CommonLocartors.Locator().submissionList_firstRow);
			 Thread.sleep(3000);
			 bgColor2 = General.driver.findElement(CommonLocartors.Locator().submissionList_firstRow).getCssValue("background-color");
			 
	    	 System.out.println("bgColor1======"+bgColor1);
			 System.out.println("bgColor2======"+bgColor2);
			 
		     hexColor1=Color.fromString(bgColor1).asHex();
			 System.out.println("Hex Color1====="+hexColor1);
				
			 hexColor2=Color.fromString(bgColor2).asHex();
			 System.out.println("Hex Color2====="+hexColor2);
		
			assertion = hexColor1.equalsIgnoreCase(hexColorCode1);
			if (assertion == false) {
				report("f","Assertion failed while verifying Background color of On-going button");
			}
			
	    	 System.out.println("EOM backgroundVerification()");
     }

     
	public void assertion() throws Exception {
 
		assertion = hexColor2.equalsIgnoreCase(hexColorCode2);
		 if (assertion == false) {
			report("f","Assertion failed while verifyingBackground color of Submission first row button");
		 } else{
			report("p", "All Assertion passed.");
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




	
	
	
	

