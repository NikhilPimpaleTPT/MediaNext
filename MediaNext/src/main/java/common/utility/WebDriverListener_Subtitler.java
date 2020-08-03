package common.utility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.gs4tr.qa.framework.HtmlFormatter;
import org.gs4tr.qa.framework.TestSystem;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.unitedinternet.portal.selenium.utils.logging.LoggingUtils;
/**
 * 
 * @author Mandar
 * This is WebDriverEventListener for Subtitler for Chrome and Firefox browser
 *
 */
public class WebDriverListener_Subtitler implements WebDriverEventListener , TakesScreenshot{
	
	
	FileHandler htmlfile;
	Logger log1;	
	private final String RESULTS_BASE_PATH = "logs" + File.separator
	+ "loggingResults";
	private String resultsPath = new File(RESULTS_BASE_PATH).getAbsolutePath()+ File.separator;
	
	
	public WebDriverListener_Subtitler (String testName) throws Exception {
		super();		
		System.out.println("Logs results--->"+resultsPath+testName+"_"+General.BROWSER+"_"+LoggingUtils.timeStampForFileName()+".html");
		htmlfile=new FileHandler(resultsPath+testName+"_"+General.BROWSER+"_"+LoggingUtils.timeStampForFileName()+".html");		
		log1=Logger.getLogger(testName+".java");
	}
	
	public void initiate()
	{
	//	FileHandler htmlfile = new FileHandler("LogApp.htm");
		HtmlFormatter htmlformatter = new HtmlFormatter();
		htmlfile.setFormatter(htmlformatter);
		log1.addHandler(htmlfile);
		//handler.setFormatter(new SimpleFormatter());
		//log1.addHandler(handler);
	}

	@Override
	public void afterAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub
		log1.info("afterAlertAccept || "+arg0.toString()+  new Date().getTime());		
	}

	@Override
	public void afterAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub
		log1.info("afterAlertDismiss || "+arg0.toString()+"|| "+  new Date().getTime());		
	}
	
	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub
		log1.info("beforeType || "+arg0.getAttribute("value")+"|"+  new Date().getTime());		
	}

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub
		log1.info("afterType || "+arg0.getAttribute("value")+"|"+  new Date().getTime());
		log1.info("Type ||"+arg0.getAttribute("value")+"|"+  new Date().getTime());
	}
	
	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		log1.info("beforeClickOn || "+new Date().getTime());		
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		log1.info("afterClickOn || "+new Date().getTime());	
		log1.info("Click ||"+new Date().getTime());		
		
	}
	
	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub
		log1.info("beforeFindBy || "+arg0.toString()+"|"+new Date().getTime());
		
	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		// TODO Auto-generated method stub
		log1.info("afterFindBy || "+arg0.toString()+"|"+ new Date().getTime());
		
	}
	
	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		log1.info("beforeNavigateBack || "+arg0.getCurrentUrl()+" "+new Date().getTime());		
	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		log1.info("beforeNavigateForward || "+arg0.toString()+"|"+  new Date().getTime());
		
	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		log1.info("beforeNavigateRefresh || "+arg0.toString()+"|"+  new Date().getTime());
		
	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		log1.info("Start || "+new Date().getTime());
		
	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		log1.info("afterNavigateBack || "+arg0.toString()+"|"+  new Date().getTime());
	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		log1.info("afterNavigateForward || "+arg0.toString()+"|"+  new Date().getTime());
	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		log1.info("afterNavigateRefresh || "+arg0.toString()+"|"+  new Date().getTime());
	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		log1.info("Open URL || "+arg0+"||"+ new Date().getTime());
		
	}

	
	@Override
	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		log1.info("beforeSwitchToWindow || "+arg0.toString()+"|"+  new Date().getTime());
	}

	@Override
	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		log1.info("afterSwitchToWindow || "+arg0.toString()+"|"+  new Date().getTime());
	}

	@Override
	public void beforeAlertAccept(WebDriver arg0) {
		// TODO Auto-generated method stub
		log1.info("beforeAlertAccept || "+arg0.toString()+"|"+  new Date().getTime());
	}

	@Override
	public void beforeAlertDismiss(WebDriver arg0) {
		// TODO Auto-generated method stub
		log1.info("beforeAlertDismiss || "+arg0.toString()+"|"+  new Date().getTime());
	}


	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		if (arg0.startsWith("type"))
		{
			if(arg0.contains("Init"))
			{
				initiate();				
				String message = arg0.substring(arg0.indexOf("\"")+1,arg0.lastIndexOf("\""))+"|";				
				log1.info(message);
			}else
			if(arg0.contains("Finish"))
			{							
					String message = arg0.substring(arg0.indexOf("\"")+1,arg0.lastIndexOf("\""))+"|";				
					log1.info(message);
			}else
			if(arg0.contains("Result"))
			{							
					String message = arg0.substring(arg0.indexOf("\"")+1,arg0.lastIndexOf("\""))+"|";
					message=message.replace("|p|", "|Passed|");
					message=message.replace("|f|", "|Failed|");
					log1.info(message);
			}else
			log1.info("Log|"+arg0.substring(arg0.indexOf("\"")+1,arg0.lastIndexOf("\""))+"|");
		}
		
	}
	
	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onException(Throwable arg0, WebDriver driver) {
		String path="screenshots\\automaticErrorScreenshot_"+LoggingUtils.timeStampForFileName()+".png";
		log1.severe(arg0.getMessage()+"|"+path+"|");
		File screenShotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShotFile, new File(resultsPath+path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void beforeGetText(WebElement element, WebDriver driver) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void afterGetText(WebElement element, WebDriver driver, String text) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void beforeGetText(WebElement arg0, WebDriver arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
//		// TODO Auto-generated method stub
//		
//	}

}
