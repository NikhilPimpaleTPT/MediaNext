package initial_config;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import common.utility.General;
import conifg.CommonElements;
import modules.admin;

public class Subtitler_initial_config {
	
	 @BeforeMethod	
		public void setup() throws Exception{
		General.action().startSystem(this.getClass().getSimpleName());
			}
		
		
		@Test
		public void execute() throws Exception {
			testCases();
			
			
		}
		
		
	public void testCases() throws Exception{
		
		String Roles_permission_Manage_PM[] = {"Manage Users","Manage Groups","Manage Clients","Manage Submissions"}; 
		String Roles_permission_Edit_Translation[] = {"Edit Subtitles","View History Of Segments"};		
		//String Organization_CommonOrg_User[]={CommonElements.action().PM_username,CommonElements.action().PM_username_second_manual,CommonElements.action().Translator_username,CommonElements.action().Translator_username_multilang,CommonElements.action().Reviwer_username_multilang,CommonElements.action().Reviwer_username,CommonElements.action().admin_username};
		String Organization_CommonOrg_User[]={"Subtitler_PM","Maya","Subtitler_Translator_1","Subtitler_Translator_MultiLang","Subtitler_Reviewer_MultiLang","Subtitler_Reviewer_1","admin","Subtitler_Reviewer_QMReview","Subtitler_Reviewer_Correction","Subtitler_Reviewer_finalReview","glplay_tdc14_pm"};

		String WorkflowName[] = {"Two_Step_Workflow","One_Step_Workflow","transc_Step_Workflow"}; 
		
		admin.action().login(General.action().Adminusername,General.action().Adminpassword);
		admin.action().Navigate("Roles");
		
	//Create a PM Role	
		admin.action().CreateRole_Step1("Role_PM", "To Create PM Role");
		admin.action().Create_AND_EditRole_Permission_manage(Roles_permission_Manage_PM,true);
		admin.action().Create_And_SaveRole_CreateStep();
		
		
	//Create Translator Roles
		admin.action().CreateRole_Step1("Role_Translator", "To Create Translator Role");
		admin.action().Create_AND_EditRole_Permission_manage(Roles_permission_Edit_Translation,true);
		admin.action().Create_And_SaveRole_CreateStep();
		
	//Create Reviewer Role	
		admin.action().CreateRole_Step1("Role_Review", "To Create Review Role");
		admin.action().Create_AND_EditRole_Permission_manage(Roles_permission_Edit_Translation,true);
		admin.action().Create_And_SaveRole_CreateStep();
		
	//Navigate To Users	
		Thread.sleep(2000);
		admin.action().Navigate("Users");	
		
	//Create a Admin User
//		admin.action().CreateUser_Step1("Employee","admin1", "Admin User", "dummytest@mailinator.com");
//		admin.action().Create_And_EditUser_SelectRole("admin");
//		admin.action().CreateUser_SelectLanguage("en-US", "de-DE");
//		admin.action().Create_And_SaveUser();
//		
		
//		//Create a PM User 
		//prashant.dongare80@gmail.com
				admin.action().CreateUser_Step1("Vendor","Subtitler_PM", "Subtitler_PM", CommonElements.action().PM_username);
				admin.action().Create_And_EditUser_SelectRole("Role_PM");
				admin.action().CreateUser_SelectLanguage("", "");
				admin.action().Create_And_SaveUser();
				
				//Second PM User required for AUTOMATION AND MANUAL
				//praveen.quagnito@gmail.com
				admin.action().CreateUser_Step1("Vendor","Maya", "Gurnale", CommonElements.action().PM_username_second_manual);
				admin.action().Create_And_EditUser_SelectRole("PM");
				admin.action().CreateUser_SelectLanguage("", "");
				admin.action().Create_And_SaveUser();

				//Second Translator User required for MANUAL
				admin.action().CreateUser_Step1("Vendor","Subtitler_Translator_2","User", "translatortmgr@gmail.com");
				admin.action().Create_And_EditUser_SelectRole("Linguist");
				admin.action().CreateUser_SelectLanguage("", "");
				admin.action().Create_And_SaveUser();
				
				//Second Reviewer User required for MANUAL
				admin.action().CreateUser_Step1("Vendor","Subtitler_Reviewer_2","User", "quagnitosolutions@gmail.com");
				admin.action().Create_And_EditUser_SelectRole("Linguist");
				admin.action().CreateUser_SelectLanguage("", "");
				admin.action().Create_And_SaveUser();
				
				
//		//Create a Translator User 
				admin.action().CreateUser_Step1("Vendor","Subtitler_Translator_1","Subtitler_Translator", CommonElements.action().Translator_username);
				admin.action().Create_And_EditUser_SelectRole("Role_Translator");
				admin.action().CreateUser_SelectLanguage("en-US", "de-DE");
				admin.action().Create_And_SaveUser();		
		
//		//Create a Reviewer User
				admin.action().CreateUser_Step1("Vendor","Subtitler_Reviewer_1","Subtitler_Reviewer", CommonElements.action().Reviwer_username);
				admin.action().Create_And_EditUser_SelectRole("Role_Review");
				admin.action().CreateUser_SelectLanguage("en-US", "de-DE");
				admin.action().Create_And_SaveUser();	
		

//		//Create a Translator User for MultiLang
				admin.action().CreateUser_Step1("Vendor","Subtitler_Translator_MultiLang","Subtitler_Translator_MultiLang", "subtitlertransmultilang@gmail.com");
				admin.action().Create_And_EditUser_SelectRole("Role_Translator");
				admin.action().CreateUser_SelectLanguage("", "");
				admin.action().Create_And_SaveUser();		
		
//		//Create a Reviewer User for MultiLang
				admin.action().CreateUser_Step1("Vendor","Subtitler_Reviewer_MultiLang","Subtitler_Reviewer_MultiLang", "subtitlerreviewmultilang@gmail.com");
				admin.action().Create_And_EditUser_SelectRole("Role_Review");
				admin.action().CreateUser_SelectLanguage("", "");
				admin.action().Create_And_SaveUser();	
				
				
				//Create a  multi Reviewer User 
				admin.action().CreateUser_Step1("Vendor","Subtitler_Reviewer_QMReview","Subtitler_Reviewer_QMReview", CommonElements.action().Reviwer_username_QMReview);
				admin.action().Create_And_EditUser_SelectRole("Role_Review");
				admin.action().CreateUser_SelectLanguage("en-US", "de-DE");
				admin.action().Create_And_SaveUser();
				
				admin.action().CreateUser_Step1("Vendor","Subtitler_Reviewer_Correction","Subtitler_Reviewer_Correction", CommonElements.action().Reviwer_username_correction);
				admin.action().Create_And_EditUser_SelectRole("Role_Review");
				admin.action().CreateUser_SelectLanguage("en-US", "de-DE");
				admin.action().Create_And_SaveUser();
				
				admin.action().CreateUser_Step1("Vendor","Subtitler_Reviewer_finalReview","Subtitler_Reviewer_finalReview", CommonElements.action().Reviwer_username_finalReview);
				admin.action().Create_And_EditUser_SelectRole("Role_Review");
				admin.action().CreateUser_SelectLanguage("en-US", "de-DE");
				admin.action().Create_And_SaveUser();
		
				
			//Navigate To Organization	
				admin.action().Navigate("Clients");
				Thread.sleep(1000);	
		//Create a Organization
				admin.action().CreateOrganization_Step1("Subtitle_Common_orgs", "Common Organization");
				admin.action().CreateOrganization_AddParentOrg("TransPerfect");
				//TODO below mention single line code is not required.
				//admin.action().CreateOrganization_AddWorkflow(WorkflowName);
				admin.action().CreateOrganization_AddUser(Organization_CommonOrg_User);
				admin.action().CreateOrganization_last();
	
		//Create multiple Organization	
		Thread.sleep(5000);
				admin.action().CreateOrganization_Step1("Subtitle_Common_orgs_multi", "Common multi Organization");
				admin.action().CreateOrganization_AddParentOrg("Subtitle_Common_orgs");
				//admin.action().CreateOrganization_AddWorkflow(WorkflowName);
				admin.action().CreateOrganization_AddUser(Organization_CommonOrg_User);
				admin.action().CreateOrganization_last();
				
				//Navigate To Workflows	
				admin.action().Navigate("Workflows");		

		//Create a Two Step Workflow
				admin.action().CreateWorkflow_Step1("Two_Step_Workflow");
				admin.action().CreateWorkflow_AddOrganization("Subtitle_Common_orgs");
				admin.action().CreateWorkflow_AddOrganization("Subtitle_Common_orgs_multi");
				admin.action().CreateWorkflow_AddSteps(1,"trans", "Translation");
				admin.action().CreateWorkflow_AddSteps_multi(2,"review", "Review");
				admin.action().CreateWorkflow();
				
		//Create a One Step Workflow
				admin.action().CreateWorkflow_Step1("One_Step_Workflow");
				admin.action().CreateWorkflow_AddOrganization("Subtitle_Common_orgs");
				admin.action().CreateWorkflow_AddOrganization("Subtitle_Common_orgs_multi");
				admin.action().CreateWorkflow_AddSteps(1,"trans", "Translation");
				admin.action().CreateWorkflow();	
				
		//Create a Transcription Step Workflow
				admin.action().CreateWorkflow_Step1("transc_Step_Workflow");
				admin.action().CreateWorkflow_AddOrganization("Subtitle_Common_orgs");
				admin.action().CreateWorkflow_AddOrganization("Subtitle_Common_orgs_multi");
				admin.action().CreateWorkflow_AddSteps(1,"transc", "Transcription");
				admin.action().CreateWorkflow();	
				
				//TODO This is invalied Workflow Not Required
	   //Create a Three Step Workflow
//				admin.action().CreateWorkflow_Step1("Three_Step_Workflow");
//				admin.action().CreateWorkflow_AddOrganization("Subtitle_Common_orgs");
//				admin.action().CreateWorkflow_AddOrganization("Subtitle_Common_orgs_multi");
//				admin.action().CreateWorkflow_AddSteps("trans", "Translation");
//				admin.action().CreateWorkflow_AddSteps("review", "Review");
//				admin.action().CreateWorkflow_AddSteps("transc", "Transcription");
//				admin.action().CreateWorkflow();
				
				
				 //Create a Three_Step_Transc_Workflow
				admin.action().CreateWorkflow_Step1("Three_Step_Transc_Workflow");
				admin.action().CreateWorkflow_AddOrganization("Subtitle_Common_orgs");
				admin.action().CreateWorkflow_AddOrganization("Subtitle_Common_orgs_multi");
				admin.action().CreateWorkflow_AddSteps(1,"transcription", "Transcription");
				admin.action().CreateWorkflow_AddSteps_multi(2,"Trans", "Translation");
				admin.action().CreateWorkflow_AddSteps_multi(3,"Review", "Review");
				admin.action().CreateWorkflow();
					
				//Create Trans + MultiReview Workflow
				admin.action().CreateWorkflow_Step1("MultiReview_Step_Workflow");
				admin.action().CreateWorkflow_AddOrganization("Subtitle_Common_orgs");
				admin.action().CreateWorkflow_AddOrganization("Subtitle_Common_orgs_multi");
				admin.action().CreateWorkflow_AddSteps(1,"trans", "Translation");
				admin.action().CreateWorkflow_AddSteps_multi(2,"review", "Review");
				admin.action().CreateWorkflow_AddSteps_multi(3,"review1", "Review");
				admin.action().CreateWorkflow_AddSteps_multi(4,"review2", "Review");
				admin.action().CreateWorkflow_AddSteps_multi(5,"review3", "Review");
				admin.action().CreateWorkflow();	
				

		//Navigate To Groups	
				admin.action().Navigate("Groups");
			
		//Create a Group		
				admin.action().CreateGroup_Step1("Common_group", "Common group for Subtitler Submission");
				admin.action().CreateGroup_AddOrg("Subtitle_Common_orgs");
				admin.action().CreateGroup_AddUser(Organization_CommonOrg_User);
				admin.action().CreateGroup_last();
				
				
	 //Create Multiple Group
				for(int i=1; i<=2;i++){
				admin.action().CreateGroup_Step1("Common_group_multi" +i, "Common group multi for Subtitler Submission");
				admin.action().CreateGroup_AddOrg("Subtitle_Common_orgs_multi");
				admin.action().CreateGroup_AddUser(Organization_CommonOrg_User);
				admin.action().CreateGroup_last();
				}
				
				//Create Group for multi review
				admin.action().CreateGroup_Step1("Common_group_review1", "Group for Multi review1");
				admin.action().CreateGroup_AddOrg("Subtitle_Common_orgs");
				admin.action().CreateGroup_AddUser(Organization_CommonOrg_User);
				admin.action().CreateGroup_last();
				
				
				admin.action().CreateGroup_Step1("Common_group_review2", "Group for Multi review2");
				admin.action().CreateGroup_AddOrg("Subtitle_Common_orgs");
				admin.action().CreateGroup_AddUser(Organization_CommonOrg_User);
				admin.action().CreateGroup_last();
				
				admin.action().CreateGroup_Step1("Common_group_review3", "Group for Multi review3");
				admin.action().CreateGroup_AddOrg("Subtitle_Common_orgs");
				admin.action().CreateGroup_AddUser(Organization_CommonOrg_User);
				admin.action().CreateGroup_last();
				
				
			     
				General.action().logoutMethod();
				
				System.out.println("----------SUBTITLER INITIAL CONFIG COMPLETE-----------");
				
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		General.action().stopsystem();
	}
}
