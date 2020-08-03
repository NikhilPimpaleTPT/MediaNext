package modules;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.utility.General;
import conifg.CommonElements;
import locators.CommonLocartors;
import locators.LoginLocators;
import locators.Pm_User_Submission_Locators;

public class PM_user extends General{
	
	private static PM_user pm_objects;

	 /**
	  * Method used to self-instantiate the class. Will make sure one object, and
	  * one object only is created in memory for this class. The purpose is to be
	  * able to call this object dynamically from any JAVA class that includes
	  * this as an import.
	  * 
	  * @return Returns the object instantiated from the class.
	  */
	 public static synchronized PM_user action() {
	  try {
	   if (pm_objects.equals(null)) {
		   pm_objects = new PM_user();
	   }
	  } catch (Exception NOSYSTEM) {
		  pm_objects = new PM_user();
	  }
	  return pm_objects;
	 }
	
	
	 //##########################################################################################################################
	/**
	 * @updated : Mandar
	 * 
	 * @param ReadingSpeed
	 * @param MaxCharperLimit
	 * @param Minduration
	 * @param MaxDuration
	 * @throws Exception
	 */
	    public void CreateSubmisson_Step1(String ReadingSpeed,String MaxCharperLine,String MindurationVSreadingSpeed,String MaxdurationVSreadingSpeed) throws Exception{
		 System.out.println("INSIDE method CreateSubmisson_Step1()\n");
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
			Thread.sleep(1000);
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
			Thread.sleep(1000);
			ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input);
			Thread.sleep(1000);
			type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input,ReadingSpeed);
			Thread.sleep(1000);
			
			ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
			Thread.sleep(1000);
			type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,MaxCharperLine);
			Thread.sleep(1000);
			
			ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
			Thread.sleep(1000);
			type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,MindurationVSreadingSpeed);
			Thread.sleep(1000);
			
			ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
			Thread.sleep(1000);
			type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,MaxdurationVSreadingSpeed);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
			Thread.sleep(1000);
			
			//TODO BELOW NEXT BUTTON IS CLICKED AS PD INTERGRATION IS NOT IMPLEMENTED IN SUBTITLER, SO JUST CLICK NEXT TO GO TO NEXT STEP
			//TODO THIS WILL BE REMOVED WHEN WE IPLEMENT SUBTITLER-PD INTEGRATION CODE
			//TODO COMMENTED BELOW CODE AS PER 1.12.0 RC1 
			//TODO UNCOMMENTED BELOW LINE FROM SUB_1.13.0 RC1
			Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
			Thread.sleep(4000);
			 System.out.println("EOM CreateSubmisson_Step1()\n");

		}
	    
	    
	    public void CreateSubmisson_Step1_SnapToShotChanges(String ReadingSpeed,String MaxCharperLine,String MindurationVSreadingSpeed,String MaxdurationVSreadingSpeed,boolean snapToShotChange) throws Exception{
			 System.out.println("INSIDE method CreateSubmisson_Step1_SnapToShotChanges()\n");
			    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
				Thread.sleep(1000);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
				Thread.sleep(1000);
				General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input);
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input,ReadingSpeed);
				Thread.sleep(1000);
				
				General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,MaxCharperLine);
				Thread.sleep(1000);
				
				General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,MindurationVSreadingSpeed);
				Thread.sleep(1000);
				
				General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,MaxdurationVSreadingSpeed);
				Thread.sleep(1000);
				
				if(snapToShotChange) {
				if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShotChanges_checked))
				{
				System.out.println("Snap To Shot Changes is checked.");
				}else {
				System.out.println("Snap To Shot Changes is not checked , Checked it");
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShotChanges_unchecked);
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SnapToShotChanges_unchecked);
				Thread.sleep(1000);
				}
				}
				
				Thread.sleep(1000);
				Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
				Thread.sleep(1000);
				Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
				Thread.sleep(4000);
				 System.out.println("EOM CreateSubmisson_Step1_SnapToShotChanges()\n");
				
				

			}
	  //##########################################################################################################################  
	    
	    public void CreateSubmisson_Step1_PD_Config(String ReadingSpeed,String MaxCharperLine,String MindurationVSreadingSpeed,String MaxdurationVSreadingSpeed) throws Exception{
			 System.out.println("INSIDE method CreateSubmisson_Step1()\n");
				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
				Thread.sleep(1000);
				Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
				Thread.sleep(1000);
				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
				Thread.sleep(1000);
				ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input);
				Thread.sleep(1000);
				type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input,ReadingSpeed);
				Thread.sleep(1000);
				
				ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
				Thread.sleep(1000);
				type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,MaxCharperLine);
				Thread.sleep(1000);
				
				ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
				Thread.sleep(1000);
				type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,MindurationVSreadingSpeed);
				Thread.sleep(1000);
				
				ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
				Thread.sleep(1000);
				type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,MaxdurationVSreadingSpeed);
				Thread.sleep(1000);
				Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
				Thread.sleep(1000);
				System.out.println("EOM CreateSubmisson_Step1()\n");

			}
	    
	    
	    
	    
	    public void CreateSubmisson_Step2_PD_Config(String instance,String department,String workflow, String client ) throws Exception{
			System.out.println("INSIDE method CreateSubmisson_Step2_PD_Config()\n");

			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instance);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instance);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instanceDropDownOptions(instance));
			Thread.sleep(5000);
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_department);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_department);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_departmentDropDownOptions(department));
			Thread.sleep(1000);
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_workflow);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_workflow);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_workflowDropDownOptions(workflow));
			Thread.sleep(2000);
			/*Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instance,Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_instanceDropDownOptions(instance));
		    Thread.sleep(5000);
			
		    waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_department);
			Thread.sleep(1000);
			Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_department,Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_departmentDropDownOptions(department));
		    Thread.sleep(2000);
		    
		    waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_workflow);
			Thread.sleep(1000);
			Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_workflow,Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_workflowDropDownOptions(workflow));
		    Thread.sleep(2000);*/
		    
		    type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_client, client);
	    	Thread.sleep(4000);
	    	mouseOver(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
        	Thread.sleep(1000);
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_pdmt_nextButton);
	    	Thread.sleep(3000);
			 
			System.out.println("EOM CreateSubmisson_Step2_PD_Config()\n");
	    }
	    
	  //##########################################################################################################################
	    //TODO START WITH THIS METHOD
	    public void CreateSubmisson_Step2_OrganizationAndWorkflow(String OrganizationName,String WorkflowName,String transStep,String TransGroupName,String reviewStep,String ReviewGroupName,Boolean Review) throws Exception {
	    	System.out.println("INSIDE method CreateSubmisson_Step2_OrganizationAndWorkflow()\n"); 
	    	Thread.sleep(defaultWaitPeriod*2);
	    	Thread.sleep(2000);
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    	Thread.sleep(2000);
	    	//Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    	//Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));

//	    	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	    	Dropdownwithoutselect_with_javaScript(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	    	
	    	Thread.sleep(defaultWaitPeriod*10);
	    	Thread.sleep(2000);
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown);
	    	Thread.sleep(2000);
	    	Thread.sleep(2000);
            Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown,Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown(WorkflowName));
	    	Thread.sleep(2000);
	    	
	    	//TODO NOT REQUIREED
//	    	Dropdownwithoutselect_with_javaScript(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown,Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown(WorkflowName));
//	    	Thread.sleep(2000);
	    	
	    	//TODO NOT REQUIRED CODE
//	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_Trans_input);
//	    	Thread.sleep(1000);
//	    	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_Trans_input, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_trans(TransGroupName));
//	    	Thread.sleep(2000);
	    	
	    	
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep));
	    	Thread.sleep(2000);
	    	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_trans(TransGroupName));
	    	Thread.sleep(2000);
	    	
	    	if(Review){
	    		//todo NOT REQUIRED
//	    		waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_Review_input);
//	    		Thread.sleep(1000);
//		    	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_Review_input, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_review(ReviewGroupName));
//		    	Thread.sleep(1000);
	    		waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep));
	    		Thread.sleep(2000);
		    	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_review(ReviewGroupName));
		    	Thread.sleep(2000);
	    	}
	    	
	    	Thread.sleep(2000);
//	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Next_Button);
//	    	Thread.sleep(1000);
//	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Next_Button);
//	    	Thread.sleep(2000);
	    	
			Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
			Thread.sleep(1000);
	    	
    	    System.out.println("EOM CreateSubmisson_Step2_OrganizationAndWorkflow()\n");
	    }
	    
	    
	    //#######################################################################################################################
	    
	    /**
	     * @updated : Pranali
	     * 
	      * @param OrganizationName
	     * @param WorkflowName
	     * @param transcGroupName
	     * @param TransGroupName
	     * @param ReviewGroupName
	     * @param Review
	     * @throws Exception
	     *
	     */	 
	    
	    public void CreateSubmisson_Step2_OrganizationAndWorkflow_ThreeStepWorkflow(String OrganizationName,String WorkflowName,String transcStep,String transcGroupName,String transStep,String TransGroupName,Boolean Trans,String reviewStep,String ReviewGroupName,Boolean Review) throws Exception {
	    	System.out.println("INSIDE method CreateSubmisson_Step2_OrganizationAndWorkflow()\n"); 
	    	
	    	Thread.sleep(2000);
	    
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    
//	    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	        General.action().Dropdownwithoutselect_with_javaScript(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown);
	    	Thread.sleep(2000);
	    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown,Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown(WorkflowName));
	    	Thread.sleep(2000);

	    	
	    	
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transcStep));
	    	Thread.sleep(2000);
	    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transcStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_trans(transcGroupName));
	    	Thread.sleep(2000);
	    	
	    	if(Trans){

	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep));
	    		Thread.sleep(1000);
	    		General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_review(TransGroupName));
		    	Thread.sleep(1000);
	    	}
	    	
	    	if(Review){

	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep));
	    		Thread.sleep(1000);
	    		General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_transc(ReviewGroupName));
		    	Thread.sleep(1000);
	    	}
	    	Thread.sleep(2000);

	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
			Thread.sleep(2000);
	    	
		    System.out.println("EOM CreateSubmisson_Step2_OrganizationAndWorkflow()\n");
	    }
	    
	    
	    
	    //#######################################################################################################################
	    
	    /**
	     * @updated : Pranali
	     * 
	      * @param OrganizationName
	     * @param WorkflowName
	     * @param transcGroupName
	     * @param TransGroupName
	     * @param ReviewGroupName
	     * @param Review
	     * @throws Exception
	     *
	     */	 
	    
	    public void CreateSubmisson_Step2_OrganizationAndWorkflow_MultiReview_StepWorkflow(String OrganizationName,String WorkflowName,String transStep,String TransGroupName,Boolean Trans,String reviewStep,String ReviewGroupName,Boolean Review ,String reviewStep1,String ReviewGroupName1,Boolean Review1,String reviewStep2,String ReviewGroupName2,Boolean Review2,String reviewStep3,String ReviewGroupName3,Boolean Review3) throws Exception {

	    	System.out.println("INSIDE method CreateSubmisson_Step2_OrganizationAndWorkflow()\n"); 
	    	
	    	Thread.sleep(2000);
	    
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    
//	    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	        General.action().Dropdownwithoutselect_with_javaScript(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	    	Thread.sleep(2000);
	    	General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown);
	    	Thread.sleep(2000);
	    	General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown,Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown(WorkflowName));
	    	Thread.sleep(2000);
	    	
	    	if(Trans){

	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep));
	    		Thread.sleep(1000);
	    		General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(transStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_review(TransGroupName));
		    	Thread.sleep(1000);
	    	}
	    	
	    	if(Review){

	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep));
	    		Thread.sleep(1000);
	    		General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_transc(ReviewGroupName));
		    	Thread.sleep(1000);
	    	}
	    	
	    	
	    	
	    	if(Review1){

	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep1));
	    		Thread.sleep(1000);
	    		General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep1), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_transc(ReviewGroupName1));
		    	Thread.sleep(1000);
	    	}
	    	
	    	if(Review2){

	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep2));
	    		Thread.sleep(1000);
	    		General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep2), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_transc(ReviewGroupName2));
		    	Thread.sleep(1000);
	    	}
	    	
	    	if(Review3){

	    		General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep3));
	    		Thread.sleep(1000);
	    		General.action().Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_select(reviewStep3), Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_transc(ReviewGroupName3));
		    	Thread.sleep(1000);
	    	}
	    	
	    	
	    	Thread.sleep(2000);

	    	
	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
			Thread.sleep(2000);
	    	
		    System.out.println("EOM CreateSubmisson_Step2_OrganizationAndWorkflow()\n");
	    }
	    
	    
	    
	    
	    
	    
	    
	    /**
	     * @updated : Pranali
	     * 
	     * @param FolderName
	     * @throws Exception
	     *
	     */
	    public void CreateSubmisson_Step3_videoFile(String FilePath , boolean uploadFromComputer ,String assetID) throws Exception{
	    	System.out.println("INSIDE method CreateSubmisson_Step2_videoFile()\n");
	    	Thread.sleep(3000);
	    	
	    	
	    	
	    	if(uploadFromComputer) {
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectFromComputer_RadioButton);
    		Thread.sleep(3000);
    		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectFromComputer_RadioButton);
			Thread.sleep(2000);
//	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button);
//	    	Thread.sleep(1000);
	    	String path = null;
	    	File afile=new File(FilePath);
			File[] listOfFiles={afile};
	    	if (afile.isDirectory())    
	    	    listOfFiles = afile.listFiles();
	    	   
	    	   Thread.sleep(defaultWaitPeriod*2);
	    	      
	    	   //Process array
	    	    for (int i = 0; i < listOfFiles.length; i++)
	    	    { 
	    	     if(listOfFiles[i].isDirectory()) 
	    	    	 continue;
	    	    
	    	      path = listOfFiles[i].getAbsolutePath();
	    	     
	    	    }
	    	 	Thread.sleep(1000);

//	    	     System.out.println("FolderName is------>"+FolderName);
//	    	 	Thread.sleep(1000);
//	    	    waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button);
//	    		Thread.sleep(1000);
//		    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button);
//		    	Thread.sleep(1000);
//		    	/*JavascriptExecutor jse = (JavascriptExecutor)driver;
//		    	Document doc = (Document) Jsoup.connect(driver.getCurrentUrl());
//
//		    			doc.getElementsByClass("td-file-input-hidden");
//		    	jse.executeScript("document.document.getElementsByClassName('td-file-input-hidden')[0].setAttribute('class', 'td-file-input');");
//		    	Thread.sleep(defaultWaitPeriod*3);*/
	    	 	

		    
//		    	Thread.sleep(2000);
//		    	System.out.println("Video FIle---------"+System.getProperty("user.dir")+"\\data\\Autoit\\"+FolderName+"\\Video\\FileUpload_Video.exe");
//		    	Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\data\\Autoit\\"+FolderName+"\\Video\\FileUpload_Video.exe");
		    	//Runtime.getRuntime().exec("C:\\Ameya\\Subtitle\\Autoit\\Video\\FileUpload.exe");
		    	
		    	
		    	//TODO NEW IMPL
		    	System.out.println("path---->"+path);
		    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_videoUpload_input);
		    	Thread.sleep(1000);
		    	type(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_videoUpload_input,path);
		    	Thread.sleep(1000);
		    	
	    	}else {
	    		
	    		 System.out.println("INSIDE METHOD CreateSubmisson_Step3_SearchInCatalog()");
	    		 
//	 		    Thread.sleep(3000);
//	 		    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_RadioButton);
//	 	    	Thread.sleep(3000);
//	 	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_RadioButton);
	 			Thread.sleep(2000);
	 			Thread.sleep(3000);
				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID);
			    Thread.sleep(3000);
			    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID);
				Thread.sleep(2000);
				Thread.sleep(1000);
				General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID, assetID);
				Thread.sleep(2000);
			    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_SearchBtn);
				Thread.sleep(15000);
				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_fileFirstRow(assetID));
				Thread.sleep(2000);
				
				 System.out.println("EOD CreateSubmisson_Step3_SearchInCatalog()");
	    		
	    	}
		    	
		    //TODO
		    System.out.println("Ho gya bhai");
		    	//driver.switchTo().activeElement().sendKeys(path);
		    	//General.driver.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button).sendKeys(path);
		    	//type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_VideoFile_Button, path);    
		    	
		    System.out.println("EOM CreateSubmisson_Step2_videoFile()\n");
	    } 
	    
	  //##########################################################################################################################
	    /**
	     * @updated : Mandar
	     * 
	     * @param FolderName
	     * @throws Exception
	     *
	     */	    
	    public void CreateSubmisson_Step3_VideoSRTFile(String FilePath) throws Exception {
	    	System.out.println("INSIDE method CreateSubmisson_Step3_VideoSRTFile()\n"); 
//	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceFile_Button);
//	    	File afile=new File(FilePath);
//			File[] listOfFiles={afile};
//	    	if (afile.isDirectory())    
//	    	    listOfFiles = afile.listFiles();
//	    	   
//	    	   Thread.sleep(defaultWaitPeriod*2);
//	    	      
//	    	   //Process array
//	    	    for (int i = 0; i < listOfFiles.length; i++)
//	    	    { 
//	    	     if (listOfFiles[i].isDirectory()) continue;
//	    	    
//	    	     String path = listOfFiles[i].getAbsolutePath();
//	    	   
//	    	    System.out.println("Path is------>"+path);
//	    	    waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceFile_Button);
//	    		Thread.sleep(1000);
//		    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceFile_Button);
////		    	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceFile_Button, path);    
//	    	    
//		    	
//		    	Thread.sleep(2000);
//		    	System.out.println("Source Path---------"+System.getProperty("user.dir")+"\\data\\Autoit\\"+FolderName+"\\Source\\FileUpload_Source.exe");
//		    	Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\data\\Autoit\\"+FolderName+"\\Source\\FileUpload_Source.exe");
//		    	//Runtime.getRuntime().exec("C:\\Ameya\\Subtitle\\Autoit\\Source\\FileUpload_Source.exe");
//	    	    
//	    	    Thread.sleep(defaultWaitPeriod*10);	    
//		
//	    	    Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Step1Next_Button);
//	    		Thread.sleep(1000);
	    	
	    	//TODO NEW IMPLEMENTAION AS PER 1.6.0RC1
	    	
	    	String path = null;
	    	File afile=new File(FilePath);
			File[] listOfFiles={afile};
	    	if (afile.isDirectory())    
	    	    listOfFiles = afile.listFiles();
	    	   
	    	   Thread.sleep(defaultWaitPeriod*2);
	    	      
	    	   //Process array
	    	    for (int i = 0; i < listOfFiles.length; i++)
	    	    { 
	    	     if(listOfFiles[i].isDirectory()) 
	    	    	 continue;
	    	    
	    	      path = listOfFiles[i].getAbsolutePath();
	    	     
	    	    }
	    	 	Thread.sleep(1000);
	    	 	
		    	System.out.println("path---->"+path);
		    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input);
		    	Thread.sleep(1000);
		    	type(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input,path);
		    	Thread.sleep(1000);

		    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
				Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
				Thread.sleep(1000);
				
	    	    System.out.println("EOM CreateSubmisson_Step3_VideoSRTFile()\n");

	    }
	    
	    
	    //#######################################################################################################################

	  //##########################################################################################################################
	    
	    public void CreateSubmisson_Step3_source_OSTFile(String sourceFilePath,String ostFilePath) throws Exception {
	    	System.out.println("INSIDE method CreateSubmisson_Step3_OSTFile()\n"); 
	    	Thread.sleep(3000);
	    	    	
	    	String path = null;
	    	File afile=new File(sourceFilePath);
			File[] listOfFiles={afile};
	    	if (afile.isDirectory())    
	    	    listOfFiles = afile.listFiles();
	    	   
	    	   Thread.sleep(defaultWaitPeriod*2);
	    	      
	    	   //Process array
	    	    for (int i = 0; i < listOfFiles.length; i++)
	    	    { 
	    	     if(listOfFiles[i].isDirectory()) 
	    	    	 continue;
	    	    
	    	      path = listOfFiles[i].getAbsolutePath();
	    	     
	    	    }
	    	 	Thread.sleep(1000);
	    	 	
		    	System.out.println("path---->"+path);
		    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input);
		    	Thread.sleep(1000);
		    	type(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input,path);
		    	Thread.sleep(1000);

	    	
	    	String path1 = null;
	    	File afile1=new File(ostFilePath);
			File[] listOfFiles1={afile1};
	    	if (afile1.isDirectory())    
	    		listOfFiles1 = afile1.listFiles();
	    	   
	    	   Thread.sleep(defaultWaitPeriod*2);
	    	      
	    	   //Process array
	    	    for (int i = 0; i < listOfFiles1.length; i++)
	    	    { 
	    	     if(listOfFiles1[i].isDirectory()) 
	    	    	 continue;
	    	    
	    	     path1 = listOfFiles1[i].getAbsolutePath();
	    	     
	    	    }
	    	 	Thread.sleep(1000);
	    	 	
		    	System.out.println("path1---->"+path1);
		    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_ostUpload_input);
		    	Thread.sleep(1000);
		    	type(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_ostUpload_input,path1);
		    	Thread.sleep(1000);

		        waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
				Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
				Thread.sleep(1000);
				
	    	    System.out.println("EOM CreateSubmisson_Step3_OSTFile()\n");

	    }
	    
	    //#####################################################################################################################
	    /**
	     * @updated : Mandar
	     * 
	     * @param OrganizationName
	     * @param WorkflowName
	     * @param TransGroupName
	     * @param ReviewGroupName
	     * @param Review
	     * @throws Exception
	     */
	    public void CreateSubmisson_Step4_OrganizationAndWorkflow(String OrganizationName,String WorkflowName,String TransGroupName,String ReviewGroupName,Boolean Review) throws Exception {
	    	System.out.println("INSIDE method CreateSubmisson_Step4_OrganizationAndWorkflow()\n"); 
	    	Thread.sleep(defaultWaitPeriod*2);
	    	Thread.sleep(1000);
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    	//Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown);
	    	//Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	    	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganization_dropdown, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectOrganizationFrom_dropdown(OrganizationName));
	    	Thread.sleep(defaultWaitPeriod*10);
	    	Thread.sleep(2000);
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown);
	    	Thread.sleep(1000);
	    	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown,Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectWorkflow_dropdown(WorkflowName));
	    	Thread.sleep(2000);
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_Trans_input);
	    	Thread.sleep(1000);
	    	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_Trans_input, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_trans(TransGroupName));
	    	Thread.sleep(2000);
	    	if(Review){
	    		waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_Review_input);
	    		Thread.sleep(1000);
		    	Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroup_Review_input, Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_AddGroupValue_Dropdown_for_review(ReviewGroupName));
		    	Thread.sleep(1000);
	    	}
	    	
	    	Thread.sleep(2000);
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Next_Button);
	    	Thread.sleep(1000);
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Next_Button);
	    	Thread.sleep(2000);
    	    System.out.println("EOM CreateSubmisson_Step4_OrganizationAndWorkflow()\n");
	    }
	    
	    //##########################################################################################################################
	    /**
	     * @updated : Mandar
	     * 
	     * @param DueDate
	     * @param SubmissionName
	     * @param SourceLanguage
	     * @param TargetLanguage
	     * @throws Exception
	     */
	    public void CreateSubmisson_Step4_MetaData(String DueDate,String SubmissionName,String SourceLanguage,String TargetLanguage) throws Exception {
	    	System.out.println("INSIDE method CreateSubmisson_Step4_MetaData()\n"); 
	    	//TODO NOT REQUIRED
//	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
//	    	Thread.sleep(1000);
//	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
//	    	Thread.sleep(1000);
	    	
	    	System.out.println("DATE IMPLEMENTATION");
	    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
			String newdate = GetDataPlus(Integer.valueOf(DueDate));
			System.out.println(newdate);
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);
	    	ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(2000);		
	    	
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_arrow);
	    	Thread.sleep(1000);		
	    	
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_year(newdate.substring(9, 13)));
	    	Thread.sleep(1000);		
	    	
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_month(newdate.substring(0, 3).toUpperCase()));
	    	Thread.sleep(1000);		
	    	if(newdate.substring(5, 6).contentEquals("0")){
	    		System.out.println("IF--->"+newdate.substring(6, 7));
	    		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(6, 7)));
	    		
	    		Thread.sleep(1000);		
	    	}else{
	    		System.out.println("ELSE--->"+newdate.substring(5, 7));
	    		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(5, 7)));
	    		Thread.sleep(1000);		
	    	}

	    	System.out.println("DATE IMPLEMENTATION END--------------");

	    	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input, SubmissionName);
	    	Thread.sleep(3000);
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
	    	Thread.sleep(2000);
	    	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, SourceLanguage);
	    	Thread.sleep(3000);
//	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
//	    	Thread.sleep(1000);
	    	System.out.println("CLICKED");
//	    	((JavascriptExecutor) General.driver).executeScript(
//	                "arguments[0].scrollIntoView();", General.findElement(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage).toString()));
	    	
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage));
	    	Thread.sleep(3000);
	    	if(TargetLanguage!=""){
	    		System.out.println("TARGET LANGUAGE NOT NULL");
		    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input);
		    	Thread.sleep(1000);
		    	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_TargetLanguage_input, TargetLanguage);
		    	Thread.sleep(1000);
		    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(TargetLanguage));
		    	Thread.sleep(4000);
	    	}
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
	    	Thread.sleep(1000);
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
	    	Thread.sleep(20000);
	    	//TODO NOT REQUIRED
//	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
//	    	Thread.sleep(1000);
	    	//waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	//Thread.sleep(1000);
    	    System.out.println("EOM CreateSubmisson_Step4_MetaData()\n");
	    }
	    
	   /**
	    * @author Mandar
	    * This method is used to get date with specifed Offset
	    */
		public static String GetDataPlus(Integer Days) throws Exception {
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, Days);
			DateFormat dateFormat = new SimpleDateFormat(". dd, yyyy HH:mm", Locale.getDefault());
			DateFormat dateFormat2 = new SimpleDateFormat("MMMM");
			String monthParsed = dateFormat2.format(cal.getTime());
			return (monthParsed.substring(0, 3) + dateFormat.format(cal.getTime()));
		}
		
	//##########################################################################################################################
	    
	    /**
	     * @updated : Mandar
	     * 
	     * @param SubmissionName
	     */
	    public void SearchSubmisson(String SubmissionName) throws Exception {
	    	System.out.println("INSIDE method SearchSubmisson()\n"); 
	    	
	    	if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().filters_clearFilterButton_enabled, 5)) {
	    		Click(Pm_User_Submission_Locators.Locator().filters_clearFilterButton);
	    		Thread.sleep(2000);	
	    	}

	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
	    	
	    	ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
	    	type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	    	Thread.sleep(4000);	

            //type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
	    	//TODO NOT REQUIRED AS PER JAVA 8
	    	//type_withKEYS(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.ENTER,false);
	    	Thread.sleep(1000);	
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
    	    System.out.println("EOM SearchSubmisson()\n");

	    }
	    
	    //#######################################################################################################################
	    /**
	     * This method is used to search and check submission
	     */
	    public void SearchSubmisson_andCheck(String SubmissionName) throws Exception {
	    	System.out.println("INSIDE method SearchSubmisson()\n"); 
	    	
	    	
	    	if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().filters_clearFilterButton_enabled, 5)) {
	    		Click(Pm_User_Submission_Locators.Locator().filters_clearFilterButton);
	    		Thread.sleep(4000);	
	    	}

	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
	    	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
	    	type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
	    	Thread.sleep(1000);	
//	    	type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.chord(Keys.ENTER));
//	    	Thread.sleep(1000);
	    	//TODO NOT REQUIRED AS PER JAVA 8
	    	//TODO FOR GL PLAY 1.22.0 WITH KEYS IS NOT WORKING
//	    	type_withKEYS(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, Keys.ENTER,false);
	    	Thread.sleep(1000);	
	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
	    	Thread.sleep(1000);	
			General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
			Thread.sleep(1000);			
			General.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
			Thread.sleep(2000);
	    	
    	    System.out.println("EOM SearchSubmisson()\n");

	    }
	    
	    //###########################################################################################################################
	    /**
	     * This method is used for new filter panel
	     */
	    	public void applyFilters(boolean clearFilters,boolean filterOrganization, String organizationName, boolean filterStatus, String status, boolean filterLanguage,String language) throws Exception {
		    	System.out.println("INSIDE method applyFilters()\n"); 
	
		    	if(clearFilters) {
					General.action().Click(Pm_User_Submission_Locators.Locator().filters_clearFilters);
					Thread.sleep(2000);
		    	}
		    	
		    	if(filterOrganization && organizationName!="") {
					General.action().Click(Pm_User_Submission_Locators.Locator().filters_organization_dropDown);
					Thread.sleep(2000);
					General.action().Click(Pm_User_Submission_Locators.Locator().filters_selectOption(organizationName));
					Thread.sleep(2000);
					General.action().Click(Pm_User_Submission_Locators.Locator().toolbarpanel_submission_label);
					Thread.sleep(2000);
		    	}
		    	
		    	if(filterStatus && status!="") {
					General.action().Click(Pm_User_Submission_Locators.Locator().filters_status_dropDown);
					Thread.sleep(2000);
					General.action().Click(Pm_User_Submission_Locators.Locator().filters_selectOption(status));
					Thread.sleep(2000);
					General.action().Click(Pm_User_Submission_Locators.Locator().toolbarpanel_submission_label);
					Thread.sleep(2000);
		    	}
		    	
		    	if(filterLanguage && language!="") {
					General.action().Click(Pm_User_Submission_Locators.Locator().filters_language_dropDown);
					Thread.sleep(2000);
					General.action().Click(Pm_User_Submission_Locators.Locator().filters_selectOption(language));
					Thread.sleep(2000);
					General.action().Click(Pm_User_Submission_Locators.Locator().toolbarpanel_submission_label);
					Thread.sleep(2000);
		    	}
		    	
	    	    System.out.println("EOM applyFilters()\n");

	    	}
	    
	    
	    //########################################################################################################################
	    /**
	     * This methos is used to complete trans submission
	     * @author psukhwani
	     * @param SubmissionName
	     * @param status
	     * @throws Exception
	     */
		public void Pm_Complete_trans_Submission(String SubmissionName, String status) throws Exception {
			
			 System.out.println("INSIDE Pm_Complete_trans_Submission  method()");
			 
				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
				Thread.sleep(2000);
				Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
				Thread.sleep(2000);
				ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
				Thread.sleep(2000);
				type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
				Thread.sleep(2000);
				
				if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, status)))
					{
					System.out.println("INSIDE IF--------");
					Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
					}
				  Thread.sleep(6000);
				  Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
				  Thread.sleep(6000);
				  General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
				  Thread.sleep(4000);
				
			
			      List <WebElement> listofIds1= driver.findElements(Pm_User_Submission_Locators.Locator().Pm_Submission_ListofallIds);
					Thread.sleep(2000);
				   System.out.println("No of IDS--------"+listofIds1.size());
		
				    for(int i=1;i<=listofIds1.size();i++){
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i));
					Thread.sleep(1000);
					Click(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i));
					Thread.sleep(1000);
					ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i));
					Thread.sleep(1000);
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Submission_TargetSegement_textarea(i));
					Thread.sleep(1000);
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_CopySource_toTarget_Button);
					Thread.sleep(1000);
					Click(Pm_User_Submission_Locators.Locator().Pm_CopySource_toTarget_Button);
					Thread.sleep(1000);
					
				    }
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
					Thread.sleep(1000);
					Click(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
					Thread.sleep(1000);
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_trans);
					Thread.sleep(1000);
					Click(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_trans);
					Thread.sleep(3000);
					if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
						General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
						Thread.sleep(1000);
						General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
				    	}
					
					 System.out.println("EOM Pm_Complete_trans_Submission  method()");

		}
//##################################################################################################################################
		/**
		 * This methos is used to complete review submission
		 * @author psukhwani
		 * @param SubmissionName
		 * @param status
		 * @throws Exception
		 */
		
		public void Pm_Complete_review_Submission(String SubmissionName, String step) throws Exception {
			
			 System.out.println("INSIDE Pm_Complete_review_Submission  method()");
			 
				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
				Thread.sleep(1000);
				Click(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
				Thread.sleep(1000);
				ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
				Thread.sleep(1000);
				type(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input, SubmissionName);
				Thread.sleep(1000);
				
				if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_status(SubmissionName, step)))
				{
				System.out.println("INSIDE IF--------");
				PM_user.action().Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
				}
				  Thread.sleep(6000);
				  Click(Pm_User_Submission_Locators.Locator().Pm_submission_open_button);
				  Thread.sleep(6000);
			      Click(Pm_User_Submission_Locators.Locator().Pm_submission_review_header);
				  Thread.sleep(6000);
				  General.action().Click(Pm_User_Submission_Locators.Locator().Pm_inside_submission_open_button);
				  Thread.sleep(6000);
				
			
			      List <WebElement> listofIds1= driver.findElements(Pm_User_Submission_Locators.Locator().Pm_Submission_ListofallIds);
					Thread.sleep(2000);
				   System.out.println("No of IDS--------"+listofIds1.size());
		
				    for(int i=1;i<=listofIds1.size();i++){
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
					Thread.sleep(1000);
					Click(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
					Thread.sleep(1000);
					ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
					Thread.sleep(1000);
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
					Thread.sleep(1000);
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_CopySource_toModifiedTarget_Button);
					Thread.sleep(1000);
					Click(Pm_User_Submission_Locators.Locator().Pm_CopySource_toModifiedTarget_Button);
					Thread.sleep(1000);
					
				    }
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
					Thread.sleep(1000);
					Click(Pm_User_Submission_Locators.Locator().Pm_submission_complete_task);
					Thread.sleep(1000);
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_review);
					Thread.sleep(1000);
					Click(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_review);
					Thread.sleep(3000);
					if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
						General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
						Thread.sleep(1000);
						General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
				    	}
					 System.out.println("EOM Pm_Complete_review_Submission  method()");

		}
	//##############################################################################################    
		
		public void Navigate(String menu) throws Exception {
			
			System.out.println("INSIDE METHOD Navigate");
			
			if(VerifyElementPresent(CommonLocartors.Locator().CollapseMenuSideBar)){
				Click(CommonLocartors.Locator().ExpandMenuSideBar);
			}
			
			if(menu!=null){
				waitforelemenetpresent(CommonLocartors.Locator().SelectMenu(menu));
				Click(CommonLocartors.Locator().SelectMenu(menu));
				/*System.out.println("//app-"+menu.toLowerCase()+"");
				if(VerifyElementPresent(by))
				waitforelemenetpresent(By.xpath("//app-"+menu.toLowerCase()+""));*/
				Thread.sleep(defaultWaitPeriod*8);
				System.out.println("navigate to ------------------------------------------------------"+menu);
			}
			
			System.out.println("EOM Navigate()");
		}	
//##########################################################################################################
		
		public void CreateSubmisson_Step5_MetaData_MultiLanguages(String DueDate,String SubmissionName,String SourceLanguage,String [] Targetlanguage) throws Exception {
			
			System.out.println("INSIDE METHOD CreateSubmisson_Step5_MetaData_MultiLanguages()\n");
			
			System.out.println("DATE IMPLEMENTATION");
	    	System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
			String newdate = GetDataPlus(Integer.valueOf(DueDate));

	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);
	    	ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(1000);
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
	    	Thread.sleep(2000);		
	    	
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_arrow);
	    	Thread.sleep(1000);		
	    	
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_year(newdate.substring(9, 13)));
	    	Thread.sleep(1000);		
	    	
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_month(newdate.substring(0, 3).toUpperCase()));
	    	Thread.sleep(1000);		
	    	if(newdate.substring(5, 6).contentEquals("0")){
	    		System.out.println("IF--->"+newdate.substring(6, 7));
	    		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(6, 7)));
	    		
	    		Thread.sleep(1000);		
	    	}else{
	    		System.out.println("ELSE--->"+newdate.substring(5, 7));
	    		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(5, 7)));
	    		Thread.sleep(1000);		
	    	}

	    	System.out.println("DATE IMPLEMENTATION END--------------");

	    	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input, SubmissionName);
	    	Thread.sleep(3000);
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input);
	    	Thread.sleep(2000);
	    	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguage_input, SourceLanguage);
	    	Thread.sleep(3000);
	    	Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SourceLanguageFrom_dropdown(SourceLanguage));
	    	Thread.sleep(1000);
			
			System.out.println("Start for loop");

			for(String language : Targetlanguage) {	
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_TargetLanguage_input);
			 Thread.sleep(defaultWaitPeriod*2);
			   Thread.sleep(2000);
			Click(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_TargetLanguage_input);
			 Thread.sleep(defaultWaitPeriod*2);
			   Thread.sleep(2000);
			   type(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_TargetLanguage_input, language);
				Thread.sleep(2000);
			   
//			((JavascriptExecutor)driver).executeScript(
//				    "arguments[0].scrollIntoView();",driver.findElement(Admin_User_Submission_Locators.Locator().admin_user_CreateSubmission_TargetLanguageFrom_dropdown(language)));
//			 Thread.sleep(defaultWaitPeriod*2);
			   Thread.sleep(2000);
			Click(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_SourceLanguageFrom_dropdown(language));
			 Thread.sleep(defaultWaitPeriod*2);
			   Thread.sleep(2000);
			}
			
			System.out.println("End for loop)");

			Thread.sleep(2000);
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_Create_Button);
			Click(Pm_User_Submission_Locators.Locator().PM_user_CreateSubmission_Create_Button);
			Thread.sleep(25000);
			//waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_User_New_Submission_Button);
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
			   System.out.println("EOM CreateSubmisson_Step5_MetaData_MultiLanguages()\n");
		}
		
//########################################################################################################   
		
		public void PM_ToClaim(String SubmissionName) throws Exception {
			
			 System.out.println("INSIDE PM_ToClaim  method()");
			 
				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
				Thread.sleep(1000);
				Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
				Thread.sleep(1000);
				ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
				Thread.sleep(1000);
				type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input, SubmissionName);
				Thread.sleep(1000);
				
				   if(Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_toClaim_checkbox_checked,5))
				   {	
					System.out.println("INSIDE IF--------");
					Thread.sleep(1000);
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_toClaim_select_header_checkbox);
					Thread.sleep(1000);
					Click(Pm_User_Submission_Locators.Locator().PM_toClaim_select_header_checkbox);
					Thread.sleep(1000);
					System.out.println("CLICK DONE------");
				   }
				Click(Pm_User_Submission_Locators.Locator().PM_clamSubmission_icon);
				Thread.sleep(3000);
				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_claimAlert_claim_button);
				Thread.sleep(1000);
				Click(Pm_User_Submission_Locators.Locator().PM_claimAlert_claim_button);
				Thread.sleep(2000);
				Thread.sleep(defaultWaitPeriod*5);
				
				System.out.println("EOM PM_ToClaim()");

		}
		
		
//########################################################################################################		
		
		public void PM_onGoing_submission(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
			
			 System.out.println("INSIDE PM_Ongoing  method()");
			 
				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
				Thread.sleep(1000);
				Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
				Thread.sleep(1000);
				ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
				Thread.sleep(1000);
				type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input, SubmissionName);
				Thread.sleep(2000);
				
				if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName, target)))
				{
					System.out.println("INSIDE IF--------");
					Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
				}
					  Thread.sleep(2000);
				      Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
				
					  Thread.sleep(2000);
				      List <WebElement> listofIds1= driver.findElements(Pm_User_Submission_Locators.Locator().PM_toClaim_ListofallIds);
					  Thread.sleep(1000);
					  System.out.println("No of IDS--------"+listofIds1.size());
		
				    for(int i=1;i<=listofIds1.size();i++){
						waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(i));
						Thread.sleep(1000);
						Click(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(i));
						Thread.sleep(1000);
						ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(i));
						Thread.sleep(1000);
						waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_TargetSegement_textarea(i));
						Thread.sleep(1000);
						waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_CopySource_toTarget_Button);
						Thread.sleep(1000);
						Click(Pm_User_Submission_Locators.Locator().Pm_CopySource_toTarget_Button);
						Thread.sleep(1000);
					
				    }
				    
				    if(complete){
						waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
						Thread.sleep(1000);
						Click(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
						Thread.sleep(1000);
						waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
						Thread.sleep(1000);
						Click(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
						Thread.sleep(3000);
						if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
							General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
							Thread.sleep(1000);
							General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
					    	}
					}
				    
				    if(back){
				    	System.out.println("IN BACK-----");
				    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
							General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
							Thread.sleep(1000);
							General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
					    	}
				  }
				    
					 System.out.println("EOM PM_Ongoing  method()");
		}
		
		public void PM_onGoing_submission_transc(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
			
			 System.out.println("INSIDE PM_Ongoing  method()");
			 
				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
				Thread.sleep(1000);
				Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
				Thread.sleep(1000);
				ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
				Thread.sleep(1000);
				type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input, SubmissionName);
				Thread.sleep(2000);
				
				if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName, target)))
				{
					System.out.println("INSIDE IF--------");
					Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
				}
					  Thread.sleep(2000);
				      Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
				
					  Thread.sleep(2000);
				      List <WebElement> listofIds1= driver.findElements(Pm_User_Submission_Locators.Locator().PM_toClaim_ListofallIds);
					  Thread.sleep(1000);
					  System.out.println("No of IDS--------"+listofIds1.size());
				    
				    if(complete){
						waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
						Thread.sleep(1000);
						Click(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
						Thread.sleep(1000);
						waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
						Thread.sleep(1000);
						Click(Pm_User_Submission_Locators.Locator().PM_CompleteDailoge_Button);
						Thread.sleep(3000);
						if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
							General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
							Thread.sleep(1000);
							General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
					    	}
					}
				    
				    if(back){
				    	System.out.println("IN BACK-----");
				    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
							General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
							Thread.sleep(1000);
							General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
					    	}
				  }
				    
					 System.out.println("EOM PM_Ongoing  method()");
		}
//##########################################################################################################################
		
		public void PM_onGoing_submission_review(String SubmissionName, String target,boolean complete,boolean back) throws Exception {
			
			 System.out.println("INSIDE PM_Ongoing  method()");
			 
				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
				Thread.sleep(1000);
				Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
				Thread.sleep(1000);
				ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
				Thread.sleep(1000);
				type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input, SubmissionName);
				Thread.sleep(2000);
				
				if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_PM_Target_Lang(SubmissionName, target)))
				{
					System.out.println("INSIDE IF--------");
					Click(Pm_User_Submission_Locators.Locator().PM_selectSubmission_checkbox(SubmissionName, target));
				}
					  Thread.sleep(1000);
				      Click(Pm_User_Submission_Locators.Locator().PM_Submission_Edit_icon);
				
					  Thread.sleep(2000);
				      List <WebElement> listofIds1= driver.findElements(Pm_User_Submission_Locators.Locator().PM_toClaim_ListofallIds);
					  Thread.sleep(1000);
					  System.out.println("No of IDS--------"+listofIds1.size());
		
				    for(int i=1;i<=listofIds1.size();i++){
						waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
						Thread.sleep(1000);
						Click(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
						Thread.sleep(1000);
						ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
						Thread.sleep(1000);
						waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Submission_Modified_TargetSegement_textarea(i));
						Thread.sleep(1000);
						waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_CopySource_toModifiedTarget_Button);
						Thread.sleep(1000);
						Click(Pm_User_Submission_Locators.Locator().Pm_CopySource_toModifiedTarget_Button);
						Thread.sleep(1000);
					
				    }
				    
				    if(complete){
						waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
						Thread.sleep(1000);
						Click(Pm_User_Submission_Locators.Locator().PM_Complete_Button);
						Thread.sleep(1000);
						waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_review);
						Thread.sleep(1000);
						Click(Pm_User_Submission_Locators.Locator().Pm_Complete_Task_Alert_button_review);
						Thread.sleep(3000);
						if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
							General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
							Thread.sleep(1000);
							General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Menubutton_forCompleted);
					    	}
					}
				    
				    if(back){
				    	
				    	System.out.println("IN BACK-----");
				    	if(Verify.action().verifyElementNotPresent(CommonLocartors.Locator().GlobalLink_logo, 5)){
							General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
							Thread.sleep(1000);
							General.action().Click(Pm_User_Submission_Locators.Locator().PM_BackToSubmissionList_Button);
					    	}
				    }
				    
					 System.out.println("EOM PM_Ongoing  method()");
		}
		
//###########################################################################################################################		
		
		public void PM_Submission_Reassignment(String SubmissionName,boolean user, String UserName, boolean group,String GroupName) throws Exception {
			
			 System.out.println("INSIDE PM_Submission_Reassignment  method()");
			     
			    PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			    Thread.sleep(4000);
				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment);
				Thread.sleep(4000);
				Click(Pm_User_Submission_Locators.Locator().PM_AllJobs_Reassignment);
				Thread.sleep(4000);
				
			    if(user){
			    	System.out.println("INSIDE IF FOR USERS-----");
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Users_radio_button);
					Thread.sleep(1000);
					Click(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Users_radio_button);
					Thread.sleep(1000);
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Users);
					Thread.sleep(1000);
	                //Dropdownwithoutselect(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Users, Pm_User_Submission_Locators.Locator().PM_Reassign_Job_from_Users_dropdown(UserName));
					Dropdownwithoutselect_with_javaScript(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Users, Pm_User_Submission_Locators.Locator().PM_Reassign_Job_from_Users_dropdown(UserName));
					Thread.sleep(2000);
			    	Thread.sleep(2000);
			    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
					Thread.sleep(1000);
					Click(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
					Thread.sleep(2000);
					System.out.println("OUTSIDE IF FOR USERS-----");
				}
			    
			    if(group){
			    	System.out.println("INSIDE IF FOR GROUPS-----");
			    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Groups_radio_button);
					Thread.sleep(1000);
					Click(Pm_User_Submission_Locators.Locator().PM_Reassign_job_Groups_radio_button);
					Thread.sleep(1000);
					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Groups);
					Thread.sleep(1000);
					Dropdownwithoutselect_with_javaScript(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_dropdown_Groups, Pm_User_Submission_Locators.Locator().PM_Reassign_Job_from_Groups_dropdown(GroupName));
			    	Thread.sleep(defaultWaitPeriod*10);
			    	Thread.sleep(2000);
			    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
					Thread.sleep(1000);
					Click(Pm_User_Submission_Locators.Locator().PM_Reassign_Job_REASSIGN_Button);
					Thread.sleep(2000);
			      System.out.println("OUTSIDE IF FOR GROUPS-");
			    
				 System.out.println("EOM PM_Submission_Reassignment  method()");
	}
			    Thread.sleep(1000);
				Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
				Thread.sleep(1000);
			 	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_Search_Submission_input);
		    	Thread.sleep(1000);	
			    
		}		
				

//##########################################################################################################################################

		public void deleteSubmisson(String SubmissionName) throws Exception {
			
			System.out.println("INSIDE METHOD deleteSubmisson");
	        PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(1000);
			/*Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
			Thread.sleep(2000);*/
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().DeleteSubmission_Icon);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().DeleteSubmission_Icon);
			Thread.sleep(1000);
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().DeleteSubmission_Alert_delete_button);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().DeleteSubmission_Alert_delete_button);
			Thread.sleep(2000);
			
			if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().DeleteSubmission_Second_Alert_button)){
				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().DeleteSubmission_Second_Alert_button);
				Thread.sleep(1000);
				Click(Pm_User_Submission_Locators.Locator().DeleteSubmission_Second_Alert_button);
				Thread.sleep(2000);
			}
			
			
//			waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input);
			Thread.sleep(1000);
			System.out.println("EOM deleteSubmisson()");
		}
		//##############################################################################################

		public void cancelDeleteSubmisson(String SubmissionName) throws Exception {
			
			System.out.println("INSIDE METHOD cancelSubmisson");

			admin.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(1000);
//			Click(Admin_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
//			Thread.sleep(2000);
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().DeleteSubmission_Icon);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().DeleteSubmission_Icon);
			Thread.sleep(1000);
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().DeleteSubmission_Alert_Cancel_button);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().DeleteSubmission_Alert_Cancel_button);
			Thread.sleep(2000);
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
			Thread.sleep(2000);
			//waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input);
			Thread.sleep(1000);
			System.out.println("EOM cancelSubmisson()");
		}
		//###################################################################################################
		
		
            public void deleteSubmisson_InProgress(String SubmissionName) throws Exception {
			
			System.out.println("INSIDE METHOD deleteSubmisson_InProgress");
	        PM_user.action().SearchSubmisson_andCheck(SubmissionName);
			Thread.sleep(1000);
	/*		Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
			Thread.sleep(2000);*/
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().DeleteSubmission_Icon);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().DeleteSubmission_Icon);
			Thread.sleep(1000);
			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().DeleteSubmission_Alert_delete_button);
			Thread.sleep(1000);
			Click(Pm_User_Submission_Locators.Locator().DeleteSubmission_Alert_delete_button);
			Thread.sleep(2000);
			Click(Pm_User_Submission_Locators.Locator().DeleteJobs_Second_confirmation_Alert_button);
			Thread.sleep(2000);
			
         if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().DeleteJobs_final_message));
				{
				System.out.println("INSIDE IF--------");
				Click(Pm_User_Submission_Locators.Locator().deleteJobs_OK_button);
				}
				
				Thread.sleep(2000);
				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
				Thread.sleep(1000);
				Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
				Thread.sleep(2000);
				
			 System.out.println("EOM deleteSubmisson_InProgress()");
		}
		
	//#############################################################################################################
            public void cancelDeleteSubmisson_second_confirmation(String SubmissionName) throws Exception {
    			
    			System.out.println("INSIDE METHOD cancelSubmisson");

    			admin.action().SearchSubmisson_andCheck(SubmissionName);
    			Thread.sleep(1000);
//    			Click(Admin_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
//    			Thread.sleep(2000);
    			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().DeleteSubmission_Icon);
    			Thread.sleep(1000);
    			Click(Pm_User_Submission_Locators.Locator().DeleteSubmission_Icon);
    			Thread.sleep(1000);
    			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().DeleteSubmission_Alert_delete_button);
    			Thread.sleep(1000);
    			Click(Pm_User_Submission_Locators.Locator().DeleteSubmission_Alert_delete_button);
    			Thread.sleep(2000);
    			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().DeleteSubmission_Alert_Cancel_button);
    			Thread.sleep(1000);
    			Click(Pm_User_Submission_Locators.Locator().DeleteSubmission_Alert_Cancel_button);
    			Thread.sleep(2000);
    			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
    			Thread.sleep(1000);
    			Click(Pm_User_Submission_Locators.Locator().SelectSubmission_Checkbox(SubmissionName));
    			Thread.sleep(2000);
    			//waitforelemenetpresent(Admin_User_Submission_Locators.Locator().admin_user_Search_Submission_input);
    			Thread.sleep(1000);
    			System.out.println("EOM cancelSubmisson()");
    		}
		
 //#####################################################################################################################################         
            public void clone_submission(String videofilePath, String srtFilePath, String cloned_submissionName, String DueDate,boolean uploadFromComputer,String assetID) throws Exception{
            	
            	 System.out.println("INSIDE method clone_submission()\n");
        			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().CloneSubmission_Icon);
        			Thread.sleep(2000);
        			Click(Pm_User_Submission_Locators.Locator().CloneSubmission_Icon);
        			Thread.sleep(2000);
        			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
        			Thread.sleep(2000);
        			
        		    System.out.println("Clone Submission's First Step NEXT button");
        		    waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CloneSubmission_Next_Button);
           			Click(Pm_User_Submission_Locators.Locator().Pm_user_CloneSubmission_Next_Button);
           			Thread.sleep(4000);
           			System.out.println("Clone Submission's Second Step NEXT button");
           		    waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CloneSubmission_Next_Button);
           			Click(Pm_User_Submission_Locators.Locator().Pm_user_CloneSubmission_Next_Button);
           			Thread.sleep(1000);
           			System.out.println("Clone Submission's Third Step NEXT button");
           		    waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CloneSubmission_Next_Button);
           			Click(Pm_User_Submission_Locators.Locator().Pm_user_CloneSubmission_Next_Button);
           			Thread.sleep(1000);
           			
           			if(uploadFromComputer) {
           			
        	    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectFromComputer_RadioButton);
            		Thread.sleep(3000);
            		Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_SelectFromComputer_RadioButton);
        			Thread.sleep(2000);
        			
           			System.out.println("Clone Submissions Third Step");
           			Thread.sleep(3000);
           			String path = null;
           	    	File afile=new File(videofilePath);
           			File[] listOfFiles={afile};
           	    	if (afile.isDirectory())    
           	    	    listOfFiles = afile.listFiles();
           	    	   
           	    	   Thread.sleep(defaultWaitPeriod*2);
           	    	      
           	    	   //Process array
           	    	    for (int i = 0; i < listOfFiles.length; i++)
           	    	    { 
           	    	     if(listOfFiles[i].isDirectory()) 
           	    	    	 continue;
           	    	    
           	    	      path = listOfFiles[i].getAbsolutePath();
           	    	     
           	    	    }
           	    	 	Thread.sleep(2000);
           	    	//TODO NEW IMPL
           		    	System.out.println("path---->"+path);
           		    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_videoUpload_input);
           		    	Thread.sleep(1000);
           		    	type(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_videoUpload_input,path);
           		    	Thread.sleep(1000);
           		    	
           		    	//TODO

           		    	System.out.println("Ho gya bhai");
           		    	
           			}else {
           				
           			 System.out.println("INSIDE METHOD CreateSubmisson_Step3_SearchInCatalog()");
    	    		 
//     	 		    Thread.sleep(3000);
//     	 		    General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_RadioButton);
//     	 	    	Thread.sleep(3000);
//     	 	    	General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_RadioButton);
     	 			Thread.sleep(2000);
     	 			Thread.sleep(3000);
     				General.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID);
     			    Thread.sleep(3000);
     			    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID);
     				Thread.sleep(2000);
     				Thread.sleep(1000);
     				General.action().type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_AssetID, assetID);
     				Thread.sleep(2000);
     			    General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_SearchBtn);
     				Thread.sleep(15000);
     				General.action().Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_searchInMediaLibrary_fileFirstRow(assetID));
     				Thread.sleep(2000);
     				
     				 System.out.println("EOD CreateSubmisson_Step3_SearchInCatalog()");
           				
           			}
            
           		   //TODO NEW IMPLEMENTAION AS PER 1.6.0RC1
               	    	
               	    	String path1 = null;
               	    	File afile1=new File(srtFilePath);
               			File[] listOfFiles1={afile1};
               	    	if (afile1.isDirectory())    
               	    	    listOfFiles1 = afile1.listFiles();
               	    	   
               	    	   Thread.sleep(defaultWaitPeriod*2);
               	    	      
               	    	   //Process array
               	    	    for (int i = 0; i < listOfFiles1.length; i++)
               	    	    { 
               	    	     if(listOfFiles1[i].isDirectory()) 
               	    	    	 continue;
               	    	    
               	    	  path1 = listOfFiles1[i].getAbsolutePath();
               	    	     
               	    	    }
               	    	 	Thread.sleep(2000);
               	    	 	
               		    	System.out.println("path1---->"+path1);
               		    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input);
               		    	Thread.sleep(2000);
               		    	type(Pm_User_Submission_Locators.Locator().Pm_user_createSubmission_sourceUpload_input,path1);
               		    	Thread.sleep(2000);
               		    	System.out.println("Ho gya bhai");
               		    	waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CloneSubmission_Next_Button);
               				Click(Pm_User_Submission_Locators.Locator().Pm_user_CloneSubmission_Next_Button);
               				Thread.sleep(4000);
               				
               				System.out.println("Clone Submission fourth Step");
               				
               			 System.out.println("DATE IMPLEMENTATION");
               			System.out.println("Integer.valueOf(DueDate)--->"+Integer.valueOf(DueDate));
               			String newdate = GetDataPlus(Integer.valueOf(DueDate));

               			waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
               			Thread.sleep(1000);
               			ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
               			Thread.sleep(1000);
               			Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
               			Thread.sleep(2000);		
               			
               			Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_arrow);
               			Thread.sleep(1000);		
               			
               			Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_year(newdate.substring(9, 13)));
               			Thread.sleep(1000);		
               			
               			Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_month(newdate.substring(0, 3).toUpperCase()));
               			Thread.sleep(1000);		
               			if(newdate.substring(5, 6).contentEquals("0")){
               				System.out.println("IF--->"+newdate.substring(6, 7));
               				Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(6, 7)));
               				
               				Thread.sleep(1000);		
               			}else{
               				System.out.println("ELSE--->"+newdate.substring(5, 7));
               				Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_select_date(newdate.substring(5, 7)));
               				Thread.sleep(1000);		
               			}

               			System.out.println("DATE IMPLEMENTATION END--------------");
               				/*waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
               		    	Thread.sleep(1000);
               		    	ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input);
               		    	Thread.sleep(1000);
               		    	type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_DueDate_input, DueDate);
               		    	Thread.sleep(2000);*/
               				Thread.sleep(2000);
               				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input);
               				Thread.sleep(2000);
               		        type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Name_input, cloned_submissionName);
                   	    	Thread.sleep(2000);
                   	 	    waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
               	    	    Thread.sleep(2000);
               	    	    Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
               	    	   Thread.sleep(25000);
           			
             System.out.println("EOM clone_submission()\n");

       		}
 //##########################################################################################################################
            
            public void PM_UnClaim_Job(String SubmissionName ,String step) throws Exception {
    			
   			 System.out.println("INSIDE PM_UnClaim_Job  method()");
   			 
   				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
   				Thread.sleep(1000);
   				Click(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
   				Thread.sleep(1000);
   				ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
   				Thread.sleep(1000);
   				type(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input, SubmissionName);
   				Thread.sleep(1000);
   				
   				/*   if(Verify.action().verifyElementNotPresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_checkbox_checked,5))
   				   {	
   					System.out.println("INSIDE IF--------");
   					Thread.sleep(1000);
   					waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_AllJobs_select_header_checkbox);
   					Thread.sleep(1000);
   					Click(Pm_User_Submission_Locators.Locator().PM_AllJobs_select_header_checkbox);
   					Thread.sleep(1000);
   					System.out.println("CLICK DONE------");
   				   }*/
   				
   				if(Verify.action().VerifyElementPresent(Pm_User_Submission_Locators.Locator().check_Pm_submission_step(SubmissionName, step)))
				{
				System.out.println("INSIDE IF--------");
				Thread.sleep(1000);
				General.action().Click(Pm_User_Submission_Locators.Locator().PM_ToClaim_Step_checkbox(SubmissionName, step));
				Thread.sleep(1000);
				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ToClaim_Step_checkbox(SubmissionName, step));
				System.out.println("CLICK DONE------");
				}
   				Thread.sleep(3000);
   				
   				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_UnclaimJob_button);
   				Thread.sleep(3000);
   				Click(Pm_User_Submission_Locators.Locator().PM_UnclaimJob_button);
   				Thread.sleep(3000);
   				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_ToClaim_Step_checkbox(SubmissionName, step));
   				Thread.sleep(1000);
   				General.action().Click(Pm_User_Submission_Locators.Locator().PM_ToClaim_Step_checkbox(SubmissionName, step));
   				Thread.sleep(1000);
   				waitforelemenetpresent(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
   				Thread.sleep(1000);
   				ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
   				Thread.sleep(1000);
   				
   				
   			System.out.println("EOM PM_UnClaim_Job()");

   		}
            
 //##########################################################################################################################
            
            
            public void sortSubmission(String submissionName, boolean sort) throws Exception{
            	
            	System.out.println("INSIDE METHOD sortSubmission");
            	
            	
                        SearchSubmisson(submissionName);
            	Thread.sleep(4000);
            	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
            	General.action().Click(Pm_User_Submission_Locators.Locator().Submission_Name);
            	
            	//THIS FOR ASCENDING
            	
            	if(sort){
        			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().DescendingSubmission_Name, 5)){
        				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_Name);
        			}
        		}
            	else{
        			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().AscendingSubmission_Name, 5)){
        				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_Name);
        			}
        		}
        		Thread.sleep(3000);	
        		
        		System.out.println("EOM sortSubmission()");
        		}
            	
            
            
            
 //######################################################################################################################
            
            public void sortSubmission_dueDate(String submissionName, boolean sort) throws Exception{
            	
            	System.out.println("INSIDE METHOD sortSubmission_dueDate");
            	
            	
                SearchSubmisson(submissionName);
            	Thread.sleep(4000);
            	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
            	General.action().Click(Pm_User_Submission_Locators.Locator().Submission_DueDate);
            	
            	//THIS FOR ASCENDING
            	
            	if(sort){
            		//This is for ASC
        			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().DescendingSubmission_status, 5)){
        				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_DueDate);
        			}
        		}
            	else{
            		//This is for DESC
            		if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().AscendingSubmission_status, 5)){
        				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_DueDate);
        			}
        		}
        		Thread.sleep(3000);	
        		
        		System.out.println("EOM sortSubmission_dueDate()");
        		}
            	

//####################################################################################################################################
            
   public void sortSubmission_creationDate(String submissionName, boolean sort) throws Exception{
            	
            	System.out.println("INSIDE METHOD sortSubmission_creationDate()");
            	
            	
                        SearchSubmisson(submissionName);
            	Thread.sleep(4000);
            	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
            	General.action().Click(Pm_User_Submission_Locators.Locator().Submission_creationDate);
            	
            	//THIS FOR ASCENDING
            	
            	if(sort){
        			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().DescendingSubmission_status, 5)){
        				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_creationDate);
        			}
        		}
            	else{
        			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().AscendingSubmission_status, 5)){
        				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_creationDate);
        			}
        		}
        		Thread.sleep(3000);	
        		
        		System.out.println("EOM sortSubmission_creationDate()");
        		}
            	  
//#########################################################################################################################################
   
   public void sortSubmission_Status(String submissionName, boolean sort) throws Exception{
   	
   	System.out.println("INSIDE METHOD sortSubmission_Status()");
   	
   	
               SearchSubmisson(submissionName);
   	Thread.sleep(4000);
   	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
   	General.action().Click(Pm_User_Submission_Locators.Locator().Submission_status);
   	
   	//THIS FOR ASCENDING
   	
   	if(sort){
			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().DescendingSubmission_status, 5)){
				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_status);
			}
		}
   	else{
			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().AscendingSubmission_status, 5)){
				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_status);
			}
		}
		Thread.sleep(3000);	
		
		System.out.println("EOM sortSubmission_Status()");
		}
 //###########################################################################################################################################
   
   public void sortSubmission_Assignee(String submissionName, boolean sort) throws Exception{
   	
   	System.out.println("INSIDE METHOD sortSubmission_Assignee");
   	
   	
               SearchSubmisson(submissionName);
   	Thread.sleep(4000);
   	General.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().PM_user_Search_Submission_input);
   	General.action().Click(Pm_User_Submission_Locators.Locator().Submission_Assignee);
   	
   	//THIS FOR ASCENDING
   	
   	if(sort){
			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().DescendingSubmission_Assignee, 5)){
				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_Assignee);
			}
		}
   	else{
			if(Verify.action().verifyElementPresent(Pm_User_Submission_Locators.Locator().AscendingSubmission_Assignee, 5)){
				General.action().Click(Pm_User_Submission_Locators.Locator().Submission_Assignee);
			}
		}
		Thread.sleep(3000);	
		
		System.out.println("EOM sortSubmission_Assignee()");
		}
 //##############################################################################################################################################
   
   
   /**
    * @author pvaidya
    * This method is used to enter text in Video
    * 
    * @throws Exception
    */
	  public void transcription_keyEnvents(int event1,int event2) throws Exception {
		  
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
	  
public void transcription_keyEnvents(int event1,int event2,int event3) throws Exception {
		  
		  System.out.println("******INSIDE METHOD transcription_enterText_Video()************");
		  Robot robot = new Robot();
		  robot.keyPress(event1);
          Thread.sleep(2000);
          robot.keyPress(event2);
          Thread.sleep(2000);
          robot.keyPress(event3);
          Thread.sleep(2000);
		  robot.keyRelease(event3);
          Thread.sleep(2000);
          robot.keyRelease(event2);
          Thread.sleep(2000);
          robot.keyRelease(event1);
          Thread.sleep(2000);
		    System.out.println("******END OF METHOD transcription_enterText_Video()************");
	  }
	  

public void transcription_keyEnvents(int event1) throws Exception {
	  
	  System.out.println("******INSIDE METHOD transcription_enterText_Video()************");
	  
	  Robot robot = new Robot();
	  robot.keyPress(event1);
    Thread.sleep(2000);
    robot.keyRelease(event1);
    Thread.sleep(2000);
	    
	    System.out.println("******END OF METHOD transcription_enterText_Video()************");
}

	  
//##########################################################################################################################################	  
 

public void CreateSubmisson_Step1_forCustomizedLine(String ReadingSpeed,String MaxCharperLine,String MindurationVSreadingSpeed,String MaxdurationVSreadingSpeed,String linesPerSegments) throws Exception{
	 System.out.println("INSIDE method CreateSubmisson_Step1_forCustomizedLine()\n");
	 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
	 Thread.sleep(1000);
	 PM_user.action().Click(Pm_User_Submission_Locators.Locator().Pm_User_New_Submission_Button);
	 Thread.sleep(1000);
	 PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Create_Button);
	 Thread.sleep(1000);
	 
    
     PM_user.action().waitforelemenetpresent(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
	 Thread.sleep(1000);
	 PM_user.action().Click(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
	 Thread.sleep(1000);
	 
	 PM_user.action().ClearInputvalues(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input);
	 Thread.sleep(1000);
	 PM_user.action().type(Pm_User_Submission_Locators.Locator().pm_user_CreateSubmission_MaxLinesPerSub_input,linesPerSegments);
	 Thread.sleep(1000);
    
	 ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input);
	 Thread.sleep(1000);
	 type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_Readingspeed_input,ReadingSpeed);
	 Thread.sleep(1000);
		
	 ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input);
	 Thread.sleep(1000);
	 type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxCharPerLine_input,MaxCharperLine);
	 Thread.sleep(1000);
		
	 ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input);
	 Thread.sleep(1000);
	 type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MinDuration_input,MindurationVSreadingSpeed);
	 Thread.sleep(1000);
		
	 ClearInputvalues(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input);
	 Thread.sleep(1000);
	 type(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_MaxDuration_input,MaxdurationVSreadingSpeed);
	 Thread.sleep(1000);
	 Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
	 Thread.sleep(1000);
	 Click(Pm_User_Submission_Locators.Locator().Pm_user_CreateSubmission_step1_Next_Button);
	 Thread.sleep(4000);
	 System.out.println("EOM CreateSubmisson_Step1_forCustomizedLine()\n");

	}
}
