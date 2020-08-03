/*
 * 
 */
package modules;

import java.io.File;

import org.gs4tr.qa.framework.TestSystem;
/*import org.gs4tr.qa.framework.TestSystem;
import org.gs4tr.qa.locators.PD4_main_client_dashboard_Locators;
import org.gs4tr.qa.locators.PD4_vendor_dashboard_Locators;
import org.gs4tr.qa.util.FileSaveThread;
import org.gs4tr.qa.util.FileUtil;
import org.gs4tr.qa.util.FileZipUnzipThread;
import org.gs4tr.qa.util.GetCenterScreen;
import org.gs4tr.qa.util.RobotExt;*/
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.utility.General;

/**
 * Designed to house basic actions in Project Director 4 that would mainly be
 * considered general actions in a web environment.
 * 
 * 
 * @author aalmanza
 */
public class Verify extends General{

	/** The general_actions. */
	private static Verify general_actions;

	/**
	 * Method used to self-instantiate the class. Will make sure one object, and
	 * one object only is created in memory for this class. The purpose is to be
	 * able to call this object dynamically from any JAVA class that includes
	 * this as an import.
	 * 
	 * @return Returns the object instantiated from the class.
	 */
	public static synchronized Verify action() {
		try {
			if (general_actions.equals(null)) {
				general_actions = new Verify();
			}
		} catch (Exception NOSYSTEM) {
			general_actions = new Verify();
		}
		return general_actions;
	}

	//###########################################################################################################

	/**
	 * Verifies an element is present in the current page loaded in the browser used Web driver Wait
	 * 
	 * 	 * @param tagName
	 *            name of element to verify if present.
	 * 
	 * @throws Exception
	 *             used by Thread.sleep, which requires an exception handler.
	 */

	public void isPresent(String tagName) throws Exception{
	int time=30;
		try{
			WebDriverWait wait = new WebDriverWait(driver,time);
			if(tagName.indexOf("/")!=0)
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(tagName)));
			}
			else
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(tagName)));
			}
		}
		catch(Exception e)
		{
			System.err.print(e);
			throw new Exception(e);
		}
			
		
	}
	//###########################################################################################################
	/** Not Required
	 * 
	 * Verifies an element is present in the current page loaded in the browser.
	 * Used to confirm the presence of an element without breaking the test and
	 * returning a boolean.
	 * 
	 * @param tagName
	 *            name of element to verify if present.
	 * 
	 * @param second
	 *            value in seconds to wait for an element.
	 * 
	 * @return boolean condition of element's existence.
	 * 
	 * @throws Exception
	 *             used by Thread.sleep, which requires an exception handler.
	 */
	public boolean ifIsPresent(String tagName, int second) throws Exception {
		boolean result=false;
		try{
			WebDriverWait wait = new WebDriverWait(driver,second);
			if(tagName.indexOf("/")!=0)
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(tagName)));
			}
			else
			{
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(tagName)));
			}
			result=true;
		}
		catch(Exception e)
		{
			System.err.print(e);
			//throw new Exception(e);
		}
		return result;
		
	}
	//###########################################################################################################
	/**
	 * Verifies an element with specified text is present in the current page
	 * loaded in the browser. Usually used to check the new value after refresh using Webdriver wait
	 * 
	 * @param tagName
	 *            name of element to verify if present.
	 * @param expectedText
	 *            text value expected for an element.
	 * @param second
	 *            the second
	 * @return boolean condition of element's existence.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public Boolean verifyElementTextPresent(String tagName,String expectedText, int second) throws Exception{
		boolean result=false;
		try{
			WebDriverWait wait = new WebDriverWait(driver,second);
			if(tagName.indexOf("/")!=0)
			{
				wait.until(ExpectedConditions.textToBePresentInElement(By.name(tagName), expectedText));
			}
			else
			{
				wait.until(ExpectedConditions.textToBePresentInElement(By.xpath(tagName), expectedText));
			}
			result=true;
			
		}
		catch(Exception e)
		{
			System.out.println("Could not found text: "+expectedText+" with locator: "+tagName);
			//throw new Exception(e);
		}
		return result;
	}
	
	
	//###########################################################################################################
	/**
	 * Go to specified folder, filter out submission and verify that submission is not present
	 * 
	 * @param submissionName 
	 *           name of the submission to be processed
	 * 
	 * @param folderName 
	 *           name of the folder to process the submission in<br>
	 * (Hold,Active)
	 * 
	 * @param  second 
	 *           Time in seconds to wait the specified status (ignored if status is blank)
	 * 
	 * @return boolean condition of submission's existence
	 * 
	 * @throws InterruptedException 
	 * 
	 * @throws Exception used by Thread.sleep, which requires an exception handler.
	 */
	/*	public boolean verifySubmissionNotPresent(String submissionName, String folderName,Integer second) throws Exception {
			System.out.println("In verifySubmissionNotPresent() Method \n");
			
			if(folderName.equalsIgnoreCase("On Hold"))
			{
			Verify.action().verifyElementPresent(PD4_main_client_dashboard_Locators.exts().workflows_onHoldSubmissionsButton,10);
			TestSystem.SystemEngine().findElement(PD4_main_client_dashboard_Locators.exts().workflows_onHoldSubmissionsButton).click();
			
			}
			else if(folderName.equalsIgnoreCase("Active"))
			{
			Verify.action().verifyElementPresent(PD4_main_client_dashboard_Locators.exts().workflows_activeSubmissionsButton,10);
			TestSystem.SystemEngine().findElement(PD4_main_client_dashboard_Locators.exts().workflows_activeSubmissionsButton).click();
			
			}
			else if(folderName.equalsIgnoreCase("Completed"))
			{
			Verify.action().verifyElementPresent(PD4_main_client_dashboard_Locators.exts().workflows_completedSubmissionsButton,10);
			TestSystem.SystemEngine().findElement(PD4_main_client_dashboard_Locators.exts().workflows_completedSubmissionsButton).click();
		
			}
			
			vendor.action().filterByFirstFilterType(submissionName);
			
			if(Verify.action().verifyElementNotPresent(PD4_main_client_dashboard_Locators.exts().firstDataRowName,10)==true)
			
			return true;
		
			else
				
			return false;
			
		}*/
	
	/*	*//**
		 * 
		 * Changed acc.. since this shld not fail since we do not want the text to be present
		 * Verifies an element with specified text is not present in the current page
		 * loaded in the browser. 
		 * 
		 * @param tagName
		 *            name of element to verify if present.
		 * @param expectedText
		 *            text value expected for an element.
		 * @param second
		 *            the second
		 * @return boolean condition of element's existence.
		 * @throws InterruptedException
		 *             the interrupted exception
		 *//*
		public Boolean verifyElementTextNotPresent(String tagName,
				String expectedText, int second) throws InterruptedException {
			boolean result=true;
			try
			{
				WebDriverWait wait = new WebDriverWait(driver,second);
				if(tagName.indexOf("/")!=0)
				{
					System.out.println("//");
					wait.until(ExpectedConditions.textToBePresentInElement(By.name(tagName), expectedText));
				}
				else
				{
					wait.until(ExpectedConditions.textToBePresentInElement(By.xpath(tagName), expectedText));
				}
				result=false;
			}
			catch(Exception e)
			{
				System.err.print(e);
				
				//throw new Exception(e);
			}
			
			return result;
		}

		*//**There is no way we can check a text is present on a page. we can check text of element.
		 * 
		 * Verifies text is present in the current page loaded in the browser. Used
		 * to confirm the presence of an element without breaking the test and
		 * returning a boolean.
		 * 
		 * @param text
		 *            text of element to verify if present.
		 * 
		 * @param second
		 *            value in seconds to wait for an element.
		 * 
		 * @return boolean condition of element's existence.
		 * 
		 * @throws Exception
		 *             used by Thread.sleep, which requires an exception handler.
		 */
		public boolean verifyTextPresent(String text, int second) throws Exception {
			  for (int _second = 0;; _second++) {
			   if (_second >= second)
			   {    
			    return false;
			   }
			   try {
//			    if (TestSystem.SystemEngine().selenium1.getBodyText().contains(text))
//			     return true;
				   WebElement body = driver.findElement(By.tagName("body"));
//				    String bodyText = body.getText();
				    if (body.getText().contains(text)) {
				    	System.out.println("DOES HTML BODY CONTAINS TEXT------>"+body.getText().contains(text));
				     return true;
				    }
			   } catch (Exception e) {
			   }
			   Thread.sleep(1000);
			  }
			 }

		/**There is no way we can check a text is present on a page. we can check text of element.
		 * 
		 * Verifies text is not present in the current page loaded in the browser.
		 * Used to confirm the absence of an element without breaking the test and
		 * returning a boolean.
		 * 
		 * @param text
		 *            text of element to verify if present.
		 * 
		 * @param second
		 *            value in seconds to wait for an element.
		 * 
		 * @return boolean condition of element's existence.
		 * 
		 * @throws Exception
		 *             used by Thread.sleep, which requires an exception handler.
		 *//*
		
		
		public boolean verifyTextNotPresent(String text, int second)
				   throws Exception {
				  for (int _second = 0;; _second++) {
				   if (_second >= second)
				   {
					   return false;
				   }
				   try {
				    if (!TestSystem.SystemEngine().selenium1.getBodyText().contains(text))
				     return true;
				   } catch (Exception e) {
				   }
				   Thread.sleep(1000);
				  }
				 }*/
	//###########################################################################################################
		/** Swapnil change this Is present here shld return a boolean try using web driver wait
		 * Verifies an element is present in the current page loaded in the browser.
		 * Used to confirm the presence of an element without breaking the test and
		 * returning a boolean.
		 * 
		 * @param tagName
		 *            name of element to verify if present.
		 * 
		 * @param second
		 *            value in seconds to wait for an element.
		 * 
		 * @return boolean condition of element's existence.
		 * 
		 * @throws Exception
		 *             used by Thread.sleep, which requires an exception handler.
		 */
		
		
		public boolean verifyElementPresent(By by, int second)
				throws Exception {
			boolean result = false;
			try{
				WebDriverWait wait = new WebDriverWait(driver,second);
					wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				result=true;
			}
			catch(Exception e)
			{
				System.err.print(e);
				//throw new Exception(e);
			}
			return result;
		}
		//###########################################################################################################
		/** try using web driver wait
		 * Verifies an element is not present in the current page loaded in the
		 * browser. Used to confirm the presence of an element without breaking the
		 * test and returning a boolean.
		 * 
		 * @param tagName
		 *            name of element to verify if present.
		 * 
		 * @param second
		 *            value in seconds to wait for an element.
		 * 
		 * @return boolean condition of element's existence.
		 * 
		 * @throws Exception
		 *             used by Thread.sleep, which requires an exception handler.
		 */
		public boolean verifyElementNotPresent(By by, int second)
				throws Exception {
			boolean result = true;
			try
			{
				WebDriverWait wait = new WebDriverWait(driver,second);
					wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				result=false;
			}
			catch(Exception e)
			{
				System.err.print(e);
				
				//throw new Exception(e);
			}
			return result;
		}
		//###########################################################################################################
		/** Shirley:::
		 * Verifies an element value is present in the current page loaded in the
		 * browser. Used to confirm the presence of an element without breaking the
		 * test and returning a boolean.
		 * 
		 * @param tagName
		 *            name of element to verify if present.
		 * 
		 * @param value
		 *            expected value of element to verify if present.
		 * 
		 * @param second
		 *            value in seconds to wait for an element.
		 * 
		 * @return boolean condition of element's existence.
		 * 
		 * @throws Exception
		 *             used by Thread.sleep, which requires an exception handler.
		 */
		public boolean verifyElementValuePresent(By tagName, String value,
				int second) throws Exception {
			for (int _second = 0;; _second++) {
				if (_second >= second)
				{
					System.err.print("Verification failed. Expected value: "+value+" not found.");				
					return false;
				}
				try {

					if (value.contentEquals(driver.findElement(tagName).getText())){
						return true;
				} 
				}
					catch (Exception e) {
				}
				
				Thread.sleep(1000);
			}
			}
		//###########################################################################################################
		/**
		 * @author Mandar
		 * 
		 * Verifies an text of Input element value is present in the current page loaded in the
		 * browser. Used to confirm the presence of an element without breaking the
		 * test and returning a boolean.
		 * 
		 * @param tagName
		 * @param value
		 * @param second
		 * @return
		 * @throws Exception
		 */
		//TODO - This method can be used only for INPUT element
		public boolean verifyInputElementTextPresent(By tagName, String value,
				int second) throws Exception {
			for (int _second = 0;; _second++) {
				if (_second >= second)
				{
					System.err.print("Verification failed. Expected value: "+value+" not found.");				
					return false;
				}
				try {
					System.out.println("Value to be verified :"+value);
					if (value.contentEquals(driver.findElement(tagName).getAttribute("value"))){
						System.out.println("Value "+value+" present is input element is same.");
						return true;
					}
				} catch (Exception e) {
					System.out.println("Could not found text: "+value+" with locator: "+tagName);

				}
				Thread.sleep(1000);
			}
		}
		
		
		//###########################################################################################################
		/**
		 * @author Mandar
		 * 
		 * Verifies an text of Input element value is NOT present in the current page loaded in the
		 * browser. Used to confirm the presence of an element without breaking the
		 * test and returning a boolean.
		 * 
		 * @param tagName
		 * @param value
		 * @param second
		 * @return
		 * @throws Exception
		 */
		//TODO - This method can be used only for INPUT element
		public boolean verifyInputElementTextNotPresent(String tagName, String value,
				int second) throws Exception {
			for (int _second = 0;; _second++) {
				if (_second >= second)
				{
					System.err.print("Verification failed. Expected value: "+value+" not found.");				
					return false;
				}
				try {
					System.out.println("Value to be verified :"+value);
					if (!value.contentEquals(driver.findElement(By.xpath(tagName)).getAttribute("value"))){
						System.out.println("Value "+value+" in input element is not present.");
						return true;
					}
				} catch (Exception e) {
					System.out.println("Could not found text: "+value+" with locator: "+tagName);

				}
				Thread.sleep(1000);
			}
			
		}
		
		//###########################################################################################################

		/** Verifies an element is visible in the current page loaded in the browser.
		 * Used to confirm the presence of an element without breaking the test and
		 * returning a boolean.
		 * 
		 * @param tagName
		 *            name of element to verify if Visible.
		 * 
		 * @param second
		 *            value in seconds to wait for an element.
		 * 
		 * @return boolean condition of element's existence.
		 * 
		 * @throws Exception
		 *             used by Thread.sleep, which requires an exception handler.
		 */
		public boolean verifyElementIsVisible(String tagName, int second)
				throws Exception {
			boolean result = false;
			try{
				WebDriverWait wait = new WebDriverWait(driver,second);
				if(tagName.indexOf("/")!=0)
				{
					System.out.println("//");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(tagName)));
				}
				else
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tagName)));
				}
				result=true;
				
				
			}
			catch(Exception e)
			{
				System.err.print(e);
				//throw new Exception(e);
			}
			return result;
		}
		//###########################################################################################################
		/** Verifies an element is not visible in the current page loaded in the
		 * browser. Used to confirm the absence of an element without breaking the
		 * test and returning a boolean.
		 * 
		 * @param tagName
		 *            name of element to verify if present.
		 * 
		 * @param second
		 *            value in seconds to wait for an element.
		 * 
		 * @return boolean condition of element's existence.
		 * 
		 * @throws Exception
		 *             used by Thread.sleep, which requires an exception handler.
		 */
		public boolean verifyElementIsNotVisible(String tagName, int second)
				throws Exception {
			boolean result=true;
			try
			{
				WebDriverWait wait = new WebDriverWait(driver,second);
				if(tagName.indexOf("/")!=0)
				{
					System.out.println("//");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(tagName)));
				}
				else
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tagName)));
				}
				result=false;
			}
			catch(Exception e)
			{
				System.err.print(e);
				
				//throw new Exception(e);
			}
			
			return result;
			
		}
		//###########################################################################################################
		/**
		 * Verifies an element is checked in the current page loaded in the browser.
		 * Used to confirm the presence of an checked element without breaking the
		 * test and returning a boolean.
		 * 
		 * @param tagName
		 *            name of element to verify if Visible.
		 * 
		 * @param second
		 *            value in seconds to wait for an element.
		 * 
		 * @return boolean condition of element's existence.
		 * 
		 * @throws Exception
		 *             used by Thread.sleep, which requires an exception handler.
		 */
		public boolean verifyElementIschecked(String tagName, int second)
				throws Exception {
			boolean result = false;
			try{
				WebDriverWait wait = new WebDriverWait(driver,second);
				if(tagName.indexOf("/")!=0)
				{
					System.out.println("//");
					wait.until(ExpectedConditions.elementToBeSelected(By.name(tagName)));
				}
				else
				{
					wait.until(ExpectedConditions.elementToBeSelected(By.xpath(tagName)));
				}
				result=true;
				
				
			}
			catch(Exception e)
			{
				System.err.print(e);
				//throw new Exception(e);
			}
			return result;
			
		}
		//###########################################################################################################
		/** Swapnil
		 * Verifies an element is not checked in the current page loaded in the
		 * browser. Used to confirm an element is not checked without breaking the
		 * test and returning a boolean.
		 * 
		 * @param tagName
		 *            name of element to verify if Visible.
		 * 
		 * @param second
		 *            value in seconds to wait for an element.
		 * 
		 * @return boolean condition of element's existence.
		 * 
		 * @throws Exception
		 *             used by Thread.sleep, which requires an exception handler.
		 */
		public boolean verifyElementIsNotchecked(String tagName, int second)
				throws Exception {
			boolean result = true;
			try
			{
				WebDriverWait wait = new WebDriverWait(driver,second);
				if(tagName.indexOf("/")!=0)
				{
					System.out.println("//");
					wait.until(ExpectedConditions.elementToBeSelected(By.name(tagName)));
				}
				else
				{
					wait.until(ExpectedConditions.elementToBeSelected(By.xpath(tagName)));
				}
				result=false;
				
			}
			catch(Exception e)
			{
				System.err.print(e);
				
				//throw new Exception(e);
			}
			
			return result;
			
		}

		//###########################################################################################################

	/**
	 * Verifies file is available by the path specified. Used to confirm the
	 * presence of a file without breaking the test and returning a boolean.
	 * 
	 * @param filePath
	 *            text of filePath to verify if present.
	 * 
	 * @param second
	 *            value in seconds to wait for a result.
	 * 
	 * @return boolean condition of element's existence.
	 * 
	 * @throws Exception
	 *             used by Thread.sleep, which requires an exception handler.
	 */
	public boolean verifyFilePresent(String filePath, int second)
			throws Exception {
		File f = new File(filePath);
		for (int _second = 0;; _second++) {
			if (_second >= second)
				return false;
			try {
				if (f.exists())
					return true;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}

	/**
	 * Save file.
	 * 
	 * @param fileName
	 *            the file name
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	/*public void saveFile(String fileName) throws InterruptedException {
		new FileSaveThread(fileName).start(); // launch input thread.
		Thread.sleep(3000);
		// this method will held current thread while FileChooser gives the file
		// name
	}*/
	//###########################################################################################################
	
/**
 * Whenever there is error occurs in script this method collects the stack strace i.e error message, and writes to testlink
 * @param e
 * @return error
 */
	
	public String getErrorBuffer(Throwable e) {

		StackTraceElement[] errors = e.getStackTrace();
		StringBuffer errorBuffer = new StringBuffer();
		for (int i = 0; i < errors.length; i++) {
			errorBuffer.append(errors[i]);
			errorBuffer.append("\n");
		}
		System.err
				.println("\nExecution level error encountered.\n\nError Log:\n\n"
						+ errorBuffer);
		return errorBuffer.toString();
	}
	//###########################################################################################################
	/**
     * Verifies that directory exists and is not empty.
     * 
     * Used to confirm the presence of a file without breaking the test and returning a boolean.
     * 
     * @param filePath text of filePath to verify if present.
     * 
     * @param second value in seconds to wait for a result.
     * 
     * @return boolean condition of element's existence.
     * 
     * @throws Exception used by Thread.sleep, which requires an exception handler.
     */
    public boolean verifyDirectoryIsNotEmpty(String filePath, int second)throws Exception{
    	File f = new File(filePath);
    	
    	for (int _second = 0;; _second++) {
			if (_second >= second) 
				return false;
			try {
				if (f.isDirectory())
					{
					File [] filelist = f.listFiles();
					if (filelist.length>0)
					return true;
					}
				} catch (Exception e) {} 
				Thread.sleep(1000);
			}
    	}
    
  //###########################################################################################################
    public boolean WaitUntilElementPresent(String tagName, int second)
			throws Exception {
		boolean result = false;
		try{
			
			if(Verify.action().verifyElementIsVisible(tagName, second)){
				WebDriverWait wait = new WebDriverWait(driver,second);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(tagName)));
			}
			result=true;
		}
		catch(Exception e)
		{
			System.err.print(e);
			//throw new Exception(e);
		}
		return result;
	}
     
}
