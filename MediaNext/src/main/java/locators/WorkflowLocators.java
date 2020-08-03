package locators;

import org.openqa.selenium.By;

public class WorkflowLocators {
	public static WorkflowLocators workflow_object;
	
	 public static synchronized WorkflowLocators Locator() {
		  try {
		   if (workflow_object.equals(null)) {
			   workflow_object = new WorkflowLocators();
		   }
		  } catch (Exception NOSYSTEM) {
			  workflow_object = new WorkflowLocators();
		  }
		  return workflow_object;
		 }
	public final By AddWorkflow_button=By.xpath("//span[text()='NEW WORKFLOW']");
	public final By WorkflowName_input=By.xpath("//mat-form-field[not(contains(@class,'add-step-comp'))]//input[@name='name']");//("//input[@placeholder='Workflow name']");
	public final By Workflow_AddOrganization_input=By.xpath("//input[@placeholder='Clients']");
	public final By selectOrganziation_dropdown=By.xpath("//span[contains(@class,'transformPlaceholder')]/following-sibling::span[contains(@class,'select-arrow')]");
	public final By SelectSearchOrganization_dropdown(String OrganizationName){
		return By.xpath("//span[contains(text(),'"+OrganizationName+"')]");
	}
	
	public final By Workflow_StepName_input(int index) {
		return By.xpath("//section//div["+index+"][contains(@fxflexalign,'space-around center')]//input[@name='name']");//("//section//input[@name='name'][contains(@formcontrolname,'name')]");//("//input[@name='name'][contains(@placeholder,'Step')]");
	}
	
	public final By SelectStep_Dropdwon(int index) {
		return By.xpath("//section//div["+index+"][contains(@fxflexalign,'space-around center')]//mat-select[@aria-label='Select a type']");//("//mat-select[@aria-label='Select a type']");
	}
	public final By SelectStepFrom_dropdown(String Stepname){
		return By.xpath("//mat-option//span[contains(text(),'"+Stepname+"')]");
	}
	
	public final By AddStepWorkflow_icon=By.xpath("//button[contains(@class,'mat-stroked-button')]//span[text()='ADD STEP']");//("//button[contains(@class,'accent')]//mat-icon[text()='add']");
	public final By AddWorkflow_Save_button=By.xpath("//span[contains(text(),'SAVE')]");//By.xpath("//button[@type='button']//span[contains(text(),'Save')]");
	public final By WorkflowCreated_message(String WorkflowName){
		return By.xpath("//div[contains(text(),'Workflow "+WorkflowName+" created.')]");
	}
	public final By searchEditIcon_button=By.xpath("//mat-icon[text()='mode_edit']");
	
	public final By SelectWorkflow_Checkbox(String Workflow_Name){
		return By.xpath("//mat-cell[text()='"+Workflow_Name+"']/preceding-sibling::mat-cell//mat-checkbox[contains(@id,'checkbox')]");
	}
	
//Search Workflow	
	public final By SearchWorkflow_input=By.xpath("//input[@placeholder='Filter by name']");
	public final By Search_Workflow_Result_verify(String WorkflowName){
		return By.xpath("//mat-cell[text()='"+WorkflowName+"']");
	}
	
// For Workflow Name Limitation 
	
	public final By EditWokflowName_ErrorMessege=By.xpath("//div[contains(@class,'mat-form-field-wrapper')]//mat-error[contains(text(),'2 to 128 characters')]");
	
	
	//Delete Workflow	
		public final By DeleteWorkflow_icon=By.xpath("//mat-icon[text()='delete']");
		public final By DeleteWorkflow_Alert_button=By.xpath("//span[text()='DELETE']");
		public final By DeleteWorkflow_message=By.xpath("//div[text()='Workflow delete']");	
		
	//WorkflowNameAscending/Descending
//		public static By AscendingWorkflow_Name=By.xpath("//mat-table//div[//button[contains(text(),'Name')]]/following-sibling::span[contains(text(),'ascending')]");
//		public static By DescendingWorkflow_Name=By.xpath("//mat-table//div[//button[contains(text(),'Name')]]/following-sibling::span[contains(text(),'descending')]");
		public final By AscendingWorkflow_Name=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'ascending')]]");
		public final By DescendingWorkflow_Name=By.xpath("//mat-table//div[button[contains(text(),'Name')]]//div[../following-sibling::span[contains(text(),'descending')]]");
		public final By Workflow_Name=By.xpath("//mat-table//div//button[contains(text(),'Name')]");


		 
		
	
}
