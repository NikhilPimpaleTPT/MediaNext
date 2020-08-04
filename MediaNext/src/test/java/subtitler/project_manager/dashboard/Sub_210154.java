package subtitler.project_manager.dashboard;

import static org.testng.AssertJUnit.assertTrue;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.gs4tr.qa.testrail.framework.TestRailClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.utility.General;
import common.utility.Subtitler_TestRail_Common_Properties;
import conifg.CommonElements;
import locators.LoginLocators;
import locators.Pm_User_Submission_Locators;
import locators.TranslatorLocators;
import modules.Verify;

public class Sub_210154 {

	Boolean assertion = true;
	LinkedHashMap<String, String> dataSet = new LinkedHashMap<String, String>();
    String Pm_child_url;
	String Pm_expectedUrl = "https://onlinehelp.translations.com/Subtitler/PM/Subtitler_Help.htm#t=Welcome.htm ";
	String trans_child_url;
	String trans_expectedUrl = "https://onlinehelp.translations.com/Subtitler/Linguist/Subtitler_Help.htm#t=Welcome.htm";

	@BeforeMethod
	public void setup() throws Exception {
		General.action().startSystem("SUB_210154");
		dataSet.put("TL_test_case_description", "SUB_210154:Help link");
		dataSet.put("TL_internal_testCase_ID", "210154");
	}

	@Test
	public void execute() throws Exception {
		testCase(dataSet);
		assertion();
	}

	public void testCase(LinkedHashMap<String, String> dataSet)
			throws Exception {
		try {
			// PM user login
			General.action().login(CommonElements.action().PM_username,CommonElements.action().password);
			Thread.sleep(2000);
		   // this will find parent window
			String Pm_parent_window = General.driver.getWindowHandle();
			System.out.println("parent_window id:-" + Pm_parent_window);
			Thread.sleep(2000);
			// this will click on HelpLink
			General.action().waitforelemenetpresent(LoginLocators.Locator().Logout_button);
			Thread.sleep(2000);
			General.action().Click(LoginLocators.Locator().Logout_button);
			Thread.sleep(2000);
			
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_HelpLink);
			Thread.sleep(2000);
			General.action().Click(Pm_User_Submission_Locators.Locator().Pm_HelpLink);
			Thread.sleep(2000);
			// this will find all window id's and iterate through each id
			Set<String> Pm_allwindows = General.driver.getWindowHandles();
			System.out.println("Pm_allwindows id:-" + Pm_allwindows);
			Iterator<String> Pm_Iterate_window = Pm_allwindows.iterator();
            System.out.println("PM ALL WINDOW SIZE---->" + Pm_allwindows.size());

			while (Pm_Iterate_window.hasNext()) {
				String Pm_child_window = Pm_Iterate_window.next();
				System.out.println("Pm_child_window:-" + Pm_child_window);
				System.out.println("ITERATOR---->"+ Pm_Iterate_window.toString());
				if (!Pm_Iterate_window.equals(Pm_child_window)) {
					System.out.println("INSIDE IF**********");
					General.driver.switchTo().window(Pm_child_window);
					System.out.println(General.driver.switchTo().window(Pm_child_window).getTitle());
					Pm_child_url = General.driver.switchTo().window(Pm_child_window).getCurrentUrl();
					System.out.println("Pm_child_url---->" + Pm_child_url);
					System.out.println("END IF**********");
				}
			}
			System.out.println("Pm_expectedUrl:-" + Pm_expectedUrl);
			Thread.sleep(1000);
			System.out.println("Pm_child_url---->" + Pm_child_url);

			if (Pm_child_url.trim().equalsIgnoreCase(Pm_expectedUrl.trim())) {
				System.out.println("true");
			} else {
				System.out.println("false");
			}
			General.driver.switchTo().window(Pm_parent_window);
			Thread.sleep(3000);
			General.action().logoutMethod();

			// Linguist/trans user login
			
			General.action().login(CommonElements.action().Translator_username, CommonElements.action().password);
			Thread.sleep(1000);
			// this will find parent window
			String trans_parent_window = General.driver.getWindowHandle();
			System.out.println("trans_parent_window id:-" + trans_parent_window);
			Thread.sleep(2000);
			// this will click on HelpLink
			General.action().waitforelemenetpresent(LoginLocators.Locator().Logout_button);
			Thread.sleep(2000);
			General.action().Click(LoginLocators.Locator().Logout_button);
			Thread.sleep(2000);
			
			General.action().waitforelemenetpresent(TranslatorLocators.Locator().trans_HelpLink);
			Thread.sleep(2000);
			General.action().Click(TranslatorLocators.Locator().trans_HelpLink);
			Thread.sleep(2000);
			// this will find all window id's and iterate through each id
			Set<String> trans_allwindows = General.driver.getWindowHandles();
			System.out.println("trans_allwindows id:-" + trans_allwindows);
			Iterator<String> trans_Iterate_window = trans_allwindows.iterator();
            System.out.println(" TRANS ALL WINDOW SIZE---->"+ trans_allwindows.size());

			while (trans_Iterate_window.hasNext()) {
				String trans_child_window = trans_Iterate_window.next();
				System.out.println("trans_child_window:-" + trans_child_window);
				System.out.println("ITERATOR---->"+ trans_Iterate_window.toString());
				if (!trans_Iterate_window.equals(trans_child_window)) {
					System.out.println("INSIDE IF**********");
					General.driver.switchTo().window(trans_child_window);
					System.out.println(General.driver.switchTo().window(trans_child_window).getTitle());
					trans_child_url = General.driver.switchTo().window(trans_child_window).getCurrentUrl();
					System.out.println("trans_child_url---->" + trans_child_url);
					System.out.println("END IF**********");
				}
			}
			System.out.println("trans_expectedUrl:-" + trans_expectedUrl);
			Thread.sleep(1000);
			System.out.println("trans_child_url---->" + trans_child_url);

			if (trans_child_url.trim().equalsIgnoreCase(trans_expectedUrl.trim())) {
				System.out.println("true");
			} else {
				System.out.println("false");
			}

		} catch (Throwable e) {
			report("f","Execution level error was encountered.\n\nError log:\n\n"	+ Verify.action().getErrorBuffer(e));
		}
	}

	public void assertion() throws Exception {
		assertion = Verify.action().verifyElementPresent(TranslatorLocators.Locator().trans_help_window_title, 5);
		if (assertion == false)
			report("f", "Assertion failed while verifying Help Link");
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
