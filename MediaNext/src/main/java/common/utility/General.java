package common.utility;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVReader;

import locators.CommonLocartors;
import locators.LoginLocators;
import modules.Verify;

public class General {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static WebElement element;
	public static Properties properties;
	public static int defaultWaitPeriod;
	public static long pollingInterval;
	public String Adminusername;
	public String Adminpassword;
	public static String DownloadedFile;
	
	public static String BROWSER;
	
	private static General genral_objects;
	
	//

	 /**
	  * Method used to self-instantiate the class. Will make sure one object, and
	  * one object only is created in memory for this class. The purpose is to be
	  * able to call this object dynamically from any JAVA class that includes
	  * this as an import.
	  * 
	  * @return Returns the object instantiated from the class.
	  */
	 public static synchronized General action() {
	  try {
	   if (genral_objects.equals(null)) {
		   genral_objects = new General();
	   }
	  } catch (Exception NOSYSTEM) {
		  genral_objects = new General();
	  }
	  return genral_objects;
	 }
	//###########################################################################################################
		/**
		 * @description - read Configuration details from property file
		 * @description - This method implements to launch browser and open the expected
		 *                site url.
		 * @param- BrowserURL=site Url , BrowserName=(firefox,IE,Chrome etc).
		 */
	
		@SuppressWarnings("deprecation")
		public void startSystem(String FileName) throws Exception {
			
			System.out.println("INSIDE METHOD startSystem");

			
			properties = new Properties();
			FileInputStream fip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/conifg/config.properties");
			properties.load(fip);
			defaultWaitPeriod=Integer.parseInt(properties.getProperty("defaultWaitPeriod"));
			pollingInterval=Long.parseLong(properties.getProperty("pollingInterval")); 
			System.out.println("browser : "+properties.getProperty("BrowserName"));
			System.out.println("Site : "+properties.getProperty("BrowserURL"));
			
			Adminusername=properties.getProperty("adminusername");
			Adminpassword=properties.getProperty("adminpassword");
			System.out.println("adminusername : "+properties.getProperty("adminusername"));
			System.out.println("adminpassword : "+properties.getProperty("adminpassword"));
			
			
			String BrowserURL = properties.getProperty("BrowserURL");
			String BrowserName = properties.getProperty("BrowserName");
			String DriverPath = properties.getProperty("DriverPath");

			
			
			if (BrowserName.equalsIgnoreCase("Firefox")) {

				//TODO OPTION 1
//		    	System.setProperty("webdriver.gecko.driver","geckodriver.exe");
//				FirefoxOptions options = new FirefoxOptions();
////				options.setCapability("marionette", true);
////				options.setCapability("firefox_binary",new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe").getAbsolutePath());		
//				driver = new FirefoxDriver(options);
				
				
				//TODO OPTION 2
				System.out.println("WE ARE IN WebDriverListener_Subtitler YEEEEAAAHHHHH !!!!!!!!");
				System.setProperty("webdriver.gecko.driver","geckodriver.exe");
				WebDriverListener_Subtitler eventListener1=new WebDriverListener_Subtitler(FileName);
				FirefoxOptions fOptions = new FirefoxOptions();
				
				//TODO CAN BE USED IF REQUIRED
//			    FirefoxProfile profile = new FirefoxProfile();
//		        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/srttext/csv,application/csv,application/zip");
//		        profile.setPreference("browser.helperApps.neverAsk.openFile", "text/srt,text/csv,application/csv,application/zip");
//              fOptions.setProfile(profile);
				
			
				
//				fOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "text/srt,text/csv,application/csv,multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed");
				fOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "text/srt,text/csv,application/csv,application/zip,text/zip");
				fOptions.addPreference("browser.helperApps.neverAsk.openFile", "text/srt,text/csv,application/csv,application/zip,text/zip");
				
				fOptions.addPreference("browser.download.folderList", 1);
				fOptions.addPreference("browser.helperApps.alwaysAsk.force", true);
				fOptions.addPreference("browser.download.manager.showWhenStarting", false);
				fOptions.addPreference("browser.safebrowsing.downloads.remote.block_potentially_unwanted", false);
				fOptions.addPreference("browser.safebrowsing.downloads.remote.block_uncommon", false);
				fOptions.addPreference("browser.safebrowsing.downloads.remote.block_dangerous", false);



				 WebDriver base1 = new FirefoxDriver(fOptions);
				 driver=new EventFiringWebDriver(base1).register(eventListener1);
				 BROWSER=properties.getProperty("BrowserName");
				 System.out.println("BROWSER---->"+BROWSER);

			} else if (BrowserName.equalsIgnoreCase("Chrome")) {
				System.out.println(DriverPath);
				
//				System.setProperty("webdriver.chrome.driver", DriverPath);
////				driver = new ChromeDriver();						
//				 WebDriver base = new ChromeDriver();
//				 WebDriverEventListener eventListener=new WebDriverListener(FileName);
//				 driver=new EventFiringWebDriver(base).register(eventListener);
				 
				//TODO OPTION 1
				System.out.println("WE ARE IN WebDriverListener_Subtitler WWWOOOOOOOOOOOOOOWWWWWWWW !!!!!!!!");
				 System.setProperty("webdriver.chrome.driver", "chromedriver.exe");		
				 WebDriverListener_Subtitler eventListener1=new WebDriverListener_Subtitler(FileName);
				 ChromeOptions cOptions = new ChromeOptions();
				 
				 //From CHROME 76 THIS COMMAND DOESN:T WORK SO COMMENTED AND USER ALTERNATIVE
		//		 cOptions.addArguments("disable-infobars");
				 
				 cOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				 cOptions.setExperimentalOption("useAutomationExtension", false);
				 
				 
				 WebDriver base1 = new ChromeDriver(cOptions);
				 driver=new EventFiringWebDriver(base1).register(eventListener1);
				 

				//TODO OPTION 2
//			    LoggingPreferences logPrefs = new LoggingPreferences();
//			    logPrefs.enable(LogType.BROWSER, Level.ALL);
//			        
//			    DesiredCapabilities caps = DesiredCapabilities.chrome();
//			    caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//				 
//				 ChromeOptions cOptions = new ChromeOptions();
//				 cOptions.addArguments("disable-infobars");
//				 cOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//				 
//				 driver = new ChromeDriver(cOptions);
				 

			} else if (BrowserName.equals("Internet Explorer")) {
				driver = new InternetExplorerDriver();
				System.setProperty("webdriver.ie.driver", DriverPath);
			}
			else if (BrowserName.equals("Edge")) {		
				System.out.println("Inside edge browser");
				
				ChromeOptions chromeOptions = new ChromeOptions();
				System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
				MutableCapabilities edgeOptions = new EdgeOptions().merge(chromeOptions);
			    driver = new EdgeDriver(edgeOptions);
			    System.out.println("EOD edge browser");
				 
				
			}
			
			driver.manage().window().maximize();
			driver.get(BrowserURL);
			Thread.sleep(8000);
			System.out.println("\n Executing "+ FileName +"...\n");
			
			System.out.println("EOM startSystem()");

		}
		//###########################################################################################################
		public String getText(By by){
			
			System.out.println("INSIDE METHOD getText");
			return driver.findElement(by).getText();
			
		
			
		}
		//###########################################################################################################	
		public boolean VerifyElementPresent(By by){
			System.out.println("INSIDE METHOD VerifyElementPresent");
				try {
					 return driver.findElement(by).isDisplayed();
		 		} catch (Exception e) {
					return false;
				}
			
			}
		
		//###################################################################################################################### 
		 public void Click(By by) {
				System.out.println("INSIDE METHOD Click");
		        
		 driver.findElement(by).click();
	      //  NgWebDriver.this.findElement(by).click();
	     //   findElement(by).click();
			System.out.println("EOM Click()");
	       
	    }
		 
		 
		 public void ClickX(By by) throws InterruptedException {
				System.out.println("INSIDE METHOD Click");
		        
				Actions actions = new Actions(driver);
		 		   actions.click(driver.findElement(by));
		 			Thread.sleep(2000);
					actions.perform();
//					driver.findElement(by).click();	
					
			System.out.println("EOM Click()");
	       
	    }

		//###########################################################################################################
		    public void type(By by) {
				System.out.println("INSIDE METHOD type");
		       // findElement(by).sendKeys(text);
			    driver.findElement(by);
		    	System.out.println("EOM type()");
		    }
		//########################################################################################################### 
	    public void type(By by, String text) {
			System.out.println("INSIDE METHOD type");
	       // findElement(by).sendKeys(text);
		    driver.findElement(by).sendKeys(text);
	    	System.out.println("EOM type()");
	    }
	  //###########################################################################################################
	    /**
	     * This method is used to type with KEYS class only
	     * 
	     * @author mghole
	     * @param by
	     * @param text
	     */
	    public void type_withKEYS(By by, Keys key, boolean convertToString) {
			System.out.println("INSIDE METHOD type_withKEYS");
	       // findElement(by).sendKeys(text);
			if(key.toString().contains(Keys.NULL)) {
				System.out.println("IN IF********"+key+" ******** "+Keys.NULL);
			}else {
				System.out.println("IN ELSE********"+key+" ******** "+Keys.NULL);

			}

			if(convertToString) {
				System.out.println("##########IF");
		    	driver.findElement(by).sendKeys(key.toString());
			}else {
				System.out.println("##########ELSE");
		    	driver.findElement(by).sendKeys(key);
			}
	    	System.out.println("EOM type_withKEYS()");
	    }
	  //###########################################################################################################
	    public void ClearInputvalues(By by) {
			System.out.println("INSIDE METHOD ClearInputvalues");
		       // findElement(by).sendKeys(text);
		    	driver.findElement(by).clear();
		    	System.out.println("EOM ClearInputvalues()");
		    }
	  //###########################################################################################################    
	    public void BrowseFile(By by,String Path) {
			System.out.println("INSIDE METHOD BrowseFile");
	    	//   findElement(by).sendKeys(Path);
	    	 driver.findElement(by).sendKeys(Path);
	    		System.out.println("EOM BrowseFile()");
		}
	  //###########################################################################################################
	    public void checkUncheck(By by, boolean check) {
			System.out.println("INSIDE METHOD checkUncheck");
	    	boolean select =  driver.findElement(by).isSelected();
			if (check != select && !select == check)
				 driver.findElement(by).click();
	    	
	    	/*if (Verify.action().verifyElementPresent(by, 5)){
				 driver.findElement(by).click();
	    	}*/
			System.out.println("EOM checkUncheck()");
		}
	  //###########################################################################################################   
		public void select(By by,String value){
			System.out.println("INSIDE METHOD select");
			//element=driver.findElement(by);
			System.out.println(driver.findElement(by));
			Select select=new Select(driver.findElement(by));
			select.selectByVisibleText(value);	
			
			System.out.println("EOM select()");
		}
		//###########################################################################################################	
		public void Dropdownwithoutselect(By bydropdwon,By byvalue) throws InterruptedException{
			System.out.println("INSIDE METHOD Dropdownwithoutselect");
			driver.findElement(bydropdwon).click();
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript(
				    "arguments[0].scrollIntoView();",driver.findElement(byvalue));
			waitforelemenetpresent(byvalue);
			Thread.sleep(2000);
			Actions actions = new Actions(driver);
 		   actions.moveToElement(driver.findElement(byvalue));
 			Thread.sleep(2000);
			actions.perform();
			driver.findElement(byvalue).click();	
			System.out.println("EOM Dropdownwithoutselect()");
		}
		//#########################################################################################################
		public void Dropdownwithoutselect_with_javaScript(By bydropdwon,By byvalue) throws InterruptedException{
			System.out.println("INSIDE METHOD Dropdownwithoutselect");
			driver.findElement(bydropdwon).click();
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript(
				    "arguments[0].scrollIntoView();",driver.findElement(byvalue));
			waitforelemenetpresent(byvalue);
			Thread.sleep(2000);
			Actions actions = new Actions(driver);
 		   actions.moveToElement(driver.findElement(byvalue));
 			Thread.sleep(2000);
			actions.perform();
			driver.findElement(byvalue).click();	
			System.out.println("EOM Dropdownwithoutselect()");
		}
		//###########################################################################################################		
		public static void waitTillElementNotVisible(By by) {
			System.out.println("INSIDE METHOD waitTillElementNotVisible");
			try {
				wait = new WebDriverWait(driver, defaultWaitPeriod * 2);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
				Thread.sleep(defaultWaitPeriod);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("EOM waitTillElementNotVisible()");
		}
		//###########################################################################################################	
		public void doubleClick(By by) throws Exception{
			System.out.println("INSIDE METHOD doubleClick");
			  Actions act=new Actions(driver);
			  act.doubleClick(driver.findElement(by));
			  act.build().perform();
			  Thread.sleep(1000);
				System.out.println("EOM doubleClick()");
			  
			 }
		//###########################################################################################################
	    public void waitforelemenetpresent(final By by) {
			
	    	System.out.println("INSIDE METHOD waitforelementpresent");
	    	wait = new WebDriverWait(driver, defaultWaitPeriod);
			//wait.pollingEvery(pollingInterval,TimeUnit.MILLISECONDS);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			System.out.println("EOM  waitforelementpresent");
	    }
	  //###########################################################################################################
	  //  @AfterMethodSuite(alwaysRun = true)
		public void stopsystem() {
			System.out.println("INSIDE METHOD stopsystem");

			driver.quit();
			System.out.println("EOM stopsystem()");
		}
		
		//############################################################################################################
		/**
		 * @author Mandar
		 * @param username
		 * @param password
		 * @throws Exception
		 */
	    public void login(String username,String password) throws Exception{
		 System.out.println("INSIDE General login method ");
			waitforelemenetpresent(LoginLocators.Locator().Email_input);
			ClearInputvalues(LoginLocators.Locator().Email_input);
			Thread.sleep(1000);
			type(LoginLocators.Locator().Email_input, username);
			Thread.sleep(1000);
			type(LoginLocators.Locator().Email_input,Keys.chord(Keys.TAB));			
			if(Verify.action().verifyElementPresent(LoginLocators.Locator().continue_button, 5)){
				System.out.println("IN IF****************");
				Click(LoginLocators.Locator().continue_button);
				Thread.sleep(1000);
			}
			waitforelemenetpresent(LoginLocators.Locator().password_input);
			ClearInputvalues(LoginLocators.Locator().password_input);
			Thread.sleep(1000);
			type(LoginLocators.Locator().password_input, password);
			Thread.sleep(2000);
			Click(LoginLocators.Locator().continue_button);
			Thread.sleep(8000);
//			Click(LoginLocators.Locator().signin_button);
//			Thread.sleep(5000);
			System.out.println("EOM General login ()");
		}
	    
	    //##################################################################################################################
	    
	    public void logoutMethod() throws InterruptedException{
	    	
	    	System.out.println("INSIDE METHOD LogoutMethod");
		 	
			if(VerifyElementPresent(CommonLocartors.Locator().CollapseMenuSideBar)){
				Click(CommonLocartors.Locator().ExpandMenuSideBar);
			}
			
			waitforelemenetpresent(LoginLocators.Locator().Logout_button);
			Click(LoginLocators.Locator().Logout_button);
			Thread.sleep(defaultWaitPeriod*40);
			
			waitforelemenetpresent(LoginLocators.Locator().SignOut_link);
			Click(LoginLocators.Locator().SignOut_link);
			Thread.sleep(defaultWaitPeriod*40);
			
			System.out.println("EOM LogoutMethod()");
			}
	    
	    //####################################################################################################################
	    
		public static WebElement findElement(String locator)
		{
			return driver.findElement(By.xpath(locator));
		}
		
		//######################################################################################################################
		

		
		public void performAction(By locator, String text)
		{
			WebElement tabContent = General.driver.findElement(locator);
			Actions act= new Actions(General.driver);
			act.moveToElement(tabContent).click().sendKeys(text).build().perform();		}
		
		/**
		 * This method is used to get file name without Extension
		 * 
		 * @author Mandar
		 * @param path
		 * @return
		 * @throws Exception
		 */
		public String getZipFilewithoutExtension(String path) throws Exception{
			 // Directory path here
			  System.out.println("\nIn getZipFilewithoutExtension()\n");
			  String files ;
			  String fileNameWithOutExt = null;
			  File folder = new File(path);
			  File[] listOfFiles = folder.listFiles(); 

			  for (int i = 0; i < listOfFiles.length; i++) {
			   if (listOfFiles[i].isFile()) {
			   files = listOfFiles[i].getName();
			       if (files.endsWith(".rar") || files.endsWith(".zip")) {
			           fileNameWithOutExt = FilenameUtils.removeExtension(files);
			          System.out.println("File Name without extension :"+fileNameWithOutExt);
			       }
			     }
			  }
			  
			  System.out.println("\nEOM - getZipFilewithoutExtension().\n");
	         return fileNameWithOutExt;
		}
		
		
		
		public String printFileList(String filePath,String folderName){
			 String fileName;
			 String finalFilePath = null;
	        FileInputStream fis = null;
	        ZipInputStream zipIs = null;
	        ZipEntry zEntry = null;
	        try {
	            fis = new FileInputStream(filePath);
	            zipIs = new ZipInputStream(new BufferedInputStream(fis));
	            while((zEntry = zipIs.getNextEntry()) != null){
	            	fileName=zEntry.getName();
	                System.out.println("File name--->"+fileName);
	                finalFilePath=filePath+"\\"+fileName;
	                System.out.println("finalFilePath--->"+finalFilePath);
	            }
	            zipIs.close();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			return finalFilePath;
	    }
		
		
		public void extractFolder(String zipFile,String destPath) throws Exception 
		{
		    //System.out.println(zipFile);
		    int BUFFER = 2048;
		    String newPath="";
		    File file = new File(zipFile);

		    ZipFile zip = new ZipFile(file);
		    System.out.println("zipFile-->"+zipFile);
		    System.out.println("zipFile.lastIndexOf(\"/\")-->"+zipFile.lastIndexOf("/"));
		    System.out.println("zipFile.length()-->"+zipFile.length());

//		     newPath = zipFile.substring(zipFile.lastIndexOf("/"), zipFile.length() - 4);
		    newPath = General.action().getZipFilewithoutExtension(System.getProperty("user.home")+ "\\Downloads\\");
            System.out.println("newPath--->"+newPath);
		    
		   // System.out.println(zipFile.length());
		    File destfile=new File(destPath);
		    //System.out.println(newPath);
		    destPath=destPath+"/"+newPath;

		    new File(destPath).mkdir();
		    //destfile
		    Enumeration zipFileEntries = zip.entries();

		    // Process each entry
		    while (zipFileEntries.hasMoreElements())
		    {
		        // grab a zip file entry
		        ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
		        String currentEntry = entry.getName();
		        File destFile = new File(destPath, currentEntry);
		        //destFile = new File(newPath, destFile.getName());
		        File destinationParent = destFile.getParentFile();

		        // create the parent directory structure if needed
		        destinationParent.mkdirs();

		        if (!entry.isDirectory())
		        {
		            BufferedInputStream is = new BufferedInputStream(zip
		            .getInputStream(entry));
		            int currentByte;
		            // establish buffer for writing file
		            byte data[] = new byte[BUFFER];

		            // write the current file to disk
		            System.out.println("destFile--->"+destFile);
		            FileOutputStream fos = new FileOutputStream(destFile);
		            BufferedOutputStream dest = new BufferedOutputStream(fos,
		            BUFFER);

		            // read and write until last byte is encountered
		            while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
		                dest.write(data, 0, currentByte);
		            }
		            
		            dest.flush();
		            dest.close();
		            is.close();
		        }

		        if (currentEntry.endsWith(".zip"))
		        {
		             //found a zip file, try to open
		            //extractFolder();
		        }
		    }
		    System.out.println("Unzip Complete");
		}
//############################################################################################################################
		
		/**
		 * @author psukhwani
		 * this method is use to handle open and save button in firefox while downloading any file in FF
		 * @throws Exception 
		 * 
		 * 
		 */
		
		  public void downloadFileFirefox() throws Exception {
			  
			  System.out.println("******INSIDE METHOD downloadFileFirefox()************");
			  
			    Robot robot = new Robot();  
	            robot.keyPress(KeyEvent.VK_DOWN);  // press arrow down key of keyboard to navigate and select Save radio button	
	            Thread.sleep(4000);  	
	            robot.keyPress(KeyEvent.VK_TAB);	
	            Thread.sleep(4000);	
	            robot.keyPress(KeyEvent.VK_TAB);	
	            Thread.sleep(4000);	
	            robot.keyPress(KeyEvent.VK_ENTER);	
			    Thread.sleep(4000);
			    
			    System.out.println("******END OF METHOD ************");
		  }
		  
		  
		  public static List<String> readCSVFileDataLineByLine(String filePath ,String SubmissionName) 
		  { 
		  	List<String> List = new ArrayList<String>();
		    
		      try { 
		    
		          // Create an object of filereader 
		          // class with CSV file as a parameter.	    	
		          FileReader filereader = new FileReader(filePath);
		          
		          
		          // create csvReader object passing 
		          // file reader as a parameter 
		          CSVReader csvReader = new CSVReader(filereader); 
		          String[] nextRecord; 
		    
		          // we are going to read data line by line 
		          while ((nextRecord = csvReader.readNext()) != null) { 
		              for (String cell : nextRecord) { 
		              	//TODO if appointment id is found then the store data in array list
		                  if(cell.contains(SubmissionName)) {	                	
		                  	  for (String cell2 : nextRecord) {	                		
		                  		  List.add(cell2);	  
		                  		  
		                  	  }
		                  }
		                  
		              } 
		             
		          }    
		        
		      } 
		      catch (Exception e) { 
		          e.printStackTrace(); 
		      }
		      return List;
		  }
		  
		  

			/**
			 * @author pvaidya
			 * this method is use to mouse over on element
			 * @throws Exception 
			 * 
			 * 
			 */
		  public void mouseOver(By locator) throws Exception{
				Thread.sleep(500);
				Actions act=new Actions(General.driver);
				act.moveToElement(General.driver.findElement(locator));
				act.build().perform();
				Thread.sleep(1000);
				
			}
		  /**
			 * @author pvaidya
			 * this method is use to export csv file for different browsers
			 * @throws Exception 
			 * 
			 * 
			 */
		  
		  public static void FileDownloadMethodForDifferentBrowser() throws Exception {
				
				System.out.println("******INSIDE METHOD FileDownloadMethodForDifferentBrowser()************");
						Thread.sleep(2000);
					 if(General.properties.getProperty("BrowserName").contains("Firefox")) {
					    	System.out.println("------THIS IS FIREFOX-----");
					    	Thread.sleep(3000);
					    	downloadFileForFirefox();
							Thread.sleep(3000);
							
				    	}
				    	else {
				    		
				    		System.out.println("------THIS IS CHROME-----");
			    		Thread.sleep(5000);
				    	}
					 
					 
					  System.out.println("******END OF METHOD FileDownloadMethodForDifferentBrowser()************");
					}
			
			
			 
			 public static void downloadFileForFirefox() throws Exception {
				  
				  System.out.println("******INSIDE METHOD downloadFileForFirefox()************");
				  
				   Robot robot = new Robot();  
		           // press arrow down key of keyboard to navigate and select Save radio button	
		           Thread.sleep(4000);
		           robot.keyPress(KeyEvent.VK_DOWN);	
				   Thread.sleep(2000);
				   robot.keyRelease(KeyEvent.VK_DOWN);
				   Thread.sleep(2000);
				   robot.keyPress(KeyEvent.VK_ENTER);	
				   Thread.sleep(2000);
				   robot.keyRelease(KeyEvent.VK_ENTER);
				   Thread.sleep(2000);
				    
				    
				    System.out.println("******END OF METHOD downloadFileForFirefox()************");
			 }
			 
			 
			 public void scrollDown(int no) throws Exception{
					Actions act=new Actions(General.driver);
					for(int i=1;i<=no;i++)
					{
						act.sendKeys(Keys.PAGE_DOWN);
						act.build().perform();
						Thread.sleep(6000);
						System.out.println("Scroll time"+i);
					}	
				}
			 
			 public void scrollDown_end(int no) throws Exception{
					Actions act=new Actions(General.driver);
					for(int i=1;i<=no;i++)
					{
						act.sendKeys(Keys.END);
						act.build().perform();
						Thread.sleep(500);
					}	
				}
		  
			 public void scrollup(int no) throws Exception{
					Actions act=new Actions(General.driver);
					for(int i=1;i<=no;i++)
					{
						act.sendKeys(Keys.PAGE_UP);
						act.build().perform();
						Thread.sleep(500);
					}	
				}
			 
			 
			 public void Trans_editText_alt_p_m(int event1,int event2) throws Exception {
				  
				  System.out.println("******Trans_editText_alt_p()************");
				  
				Robot robot = new Robot();
				robot.keyPress(event1);
		        Thread.sleep(2000);
		        robot.keyPress(event2);
		        Thread.sleep(2000);
				robot.keyRelease(event2);
		        Thread.sleep(2000);
		        robot.keyRelease(event1);
		        Thread.sleep(2000);
				    
				    System.out.println("******Trans_editText_alt_p()************");
			  }
			 
			 
			 
			 public void switchToPopupWindow(int windowIndex) throws Exception{
					System.out.println("\nIn vendor.java -  switchToPopupWindow()\n");
					int noOfWindows=General.driver.getWindowHandles().toArray().length;
					System.out.println("No of Windows - "+noOfWindows);
					for(int i=0;i<noOfWindows;i++)
					{
						System.out.println("Printing window handles - "+General.driver.getWindowHandles().toArray()[i]);
					}
					
					General.driver.switchTo().window(General.driver.getWindowHandles().toArray()[windowIndex].toString());
					Thread.sleep(2000);
					  System.out.println("\nEOM - switchToPopupWindow().\n");
				}
			 
			 
			 /**
			    * @author pvaidya
			    * This method is used to enter key events
			    * 
			    * @throws Exception
			    */
				  public void Enter_keyEnvents(int event1,int event2) throws Exception {
					  
					  System.out.println("******INSIDE METHOD transcription_enterText_Video()************");
					  
					  Robot robot = new Robot();
					  robot.keyPress(event1);
			          Thread.sleep(2000);
			          robot.keyPress(event2);
			          Thread.sleep(2000);
					  robot.keyRelease(event2);
			          Thread.sleep(2000);
			          robot.keyRelease(event1);
			          Thread.sleep(2000);
					    
					    System.out.println("******END OF METHOD transcription_enterText_Video()************");
				  }
				  
			 
			 
			 
			 
			 public void Enter_keyEnvents(int event1) throws Exception {
				  
				  System.out.println("******INSIDE METHOD transcription_enterText_Video()************");
				  
				  Robot robot = new Robot();
				  robot.keyPress(event1);
			    Thread.sleep(2000);
			    robot.keyRelease(event1);
			    Thread.sleep(2000);
				    
				    System.out.println("******END OF METHOD transcription_enterText_Video()************");
			}
			 
			 
			 
			 public Boolean getFieldColor(String locator,String expectedHexValue) throws Exception {
					System.out.println("In Method- getFieldColor()" );
					System.out.println(locator);
				    String rgb [] = locator.replaceAll("(4px solid)|(rgba)|(rgb)|(\\()|(\\s)|(\\))","").split(",");
				    System.out.println(rgb[0]);
				    System.out.println(rgb[1]);
				    System.out.println(rgb[2]);
				    String originalHexValue = String.format("#%s%s%s", toBrowserHexValue(Integer.parseInt(rgb[0])), toBrowserHexValue(Integer.parseInt(rgb[1])), toBrowserHexValue(Integer.parseInt(rgb[2])));
				    System.out.println("The background border css value is "+ originalHexValue);
					if (expectedHexValue.equalsIgnoreCase(originalHexValue))
					{
						System.out.println("Expected element background color "+expectedHexValue+" matches Orignal background Hex value "+originalHexValue);			
						return true;
					}
					else
					{
						System.out.println("Expected element background color "+expectedHexValue+" does not match Orignal background Hex value "+originalHexValue);			
					}
					return false;

					}

				private static String toBrowserHexValue(int number)
				{
				    StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
				    while (builder.length() < 2) {
				        builder.append("0");
				    }
				    return builder.toString().toUpperCase();
				}
				
				
				 
				/**
			      * This Method is used to Get Downloaded File Name From Local Drive.
				  * @author pvaidya
				  */
								 
				    public static String GetFileName(String path) throws Exception {
									  
						    System.out.println("******INSIDE METHOD GetFileName() ************");
									  
							File f1 = new File(path);
							String filenames1[]=f1.list();
							for(String filename1:filenames1) {
								System.out.println("Downloaded File is======>"+filename1);
								DownloadedFile=filename1;
								}
							System.out.println("****** EOM GetFileName() ************");
							return DownloadedFile;
										
					}

				    
				    
				    
		
}
