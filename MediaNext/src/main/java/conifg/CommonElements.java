package conifg;

public class CommonElements {
	private static CommonElements commonelements_objects;
	 public static synchronized CommonElements action() {
		  try {
		   if (commonelements_objects.equals(null)) {
			   commonelements_objects = new CommonElements();
		   }
		  } catch (Exception NOSYSTEM) {
			  commonelements_objects = new CommonElements();
		  }
		  return commonelements_objects;
		 }
	
	// EACH TEST CASE SUBMISSION NAME ANS SUFFIX 
    //public static final String BROWSER="_FIREFOX_"; 
    public static final String BROWSER="_CHROME_";
	//public static final String BROWSER="_EGCH_";
	public static final String SUFFIX="Mh";

	 
	public  final  String  PM_Role= "Role_PM";
	public  final  String  Translator_Role= "Role_Translator";
	public  final  String  Reviwer_Role= "Role_Review";
	public  final  String  password= "Password1!";
	
	public  final  String  PM_Name= "Subtitler_PM";
	//public  final static String  PM_username= "praveen.quagnito@gmail.com";
//	public  final static String  PM_username= "subtitlerpm@gmail.com";
	
	public  final  String  PM_username_second_manual= "mayagurnale06@gmail.com";
	public  final  String  PM_username_second_manual_password="Common@1";
	public  final  String  PM_username="prashant.dongare80@gmail.com";
	public  final String PM_username_second_org="pmanager@server.com";
	public  final String PM_username_second_group="linguist@server.com";
	
	//PD Users
	
	public final String PD_PM_username="glplay_tdc14_pm@fakemail.com";
	public final String PD_PM_password="Password2$";
	
	public final String PD_VENDOR1_username="glplay_tdc14_vendor_1@fakemail.com";
	public final String PD_VENDOR1_password="Password2!";
	public final String PD_VENDOR2_username="glplay_tdc14_vendor_2@fakemail.com";
	public final String PD_VENDOR2_password="Password2!";
	
	
	//All Users firstNames
	public  final String  admin_username_firstName="admin";
	public  final  String  PM_username_firstName="Subtitler_PM";
	public  final  String  Translator_username_firstName="Subtitler_Translator_1";
	public  final  String  PM_username_second_manual_firstName="Maya";
	public  final String  Reviwer_username_firstName= "Subtitler_Reviewer_1";
	//USER AS PER NAME
	public  final  String  Translator_Name= "Subtitler_Translator";
	
//	public  final static String  Translator_username= "translatortmgr@gmail.com";
	
	public  final  String  Translator_username= "subtitlertrans@gmail.com";
	public final String Translator_username_multilang = "subtitlertransmultilang@gmail.com";
	
	public final String MENU_SUBMISSION = "Submissions";
	public final String MENU_TOCLAIM = "To Claim";
	public final String MENU_ONGOING = "Ongoing";
	public final String MENU_COMPLETED = "Completed";
	public final String MENU_ALLJOBS = "All Jobs";
	
	
	
	public final String FILE_ABSOLUTE_PATH=System.getProperty("user.dir")+"\\data\\Submission\\";
	public final String FILE_COMMON_FOLDER="common\\";
	public final String FILE_COMMON_VIDEO_FOLDER="mp4";
	public final String FILE_COMMON_VIDEO_ASSET_ID="68723";
	public final String FILE_COMMON_SRT_FOLDER="Srt";
	public final String FILE_COMMON_SRT_BOLD_FOLDER="srt_bold";
	public final String FILE_COMMON_WRONG_VIDEO_FOLDER="InvalidVideo";
	public final String FILE_COMMON_WRONG_VIDEO_ASSET_ID="68724";
	public final String FILE_COMMON_SRT_CAPITAL_FOLDER="srt_capital";
	public final String FILE_COMMON_OST_FOLDER="OST";
	public final String FILE_COMMON_VIDEO_FOLDER_SECOND="mp4_second";
	public final String FILE_COMMON_VIDEO_SECOND_ASSET_ID="68721";
	public final String FILE_COMMON_SRT_FOLDER_SECOND="srt_second";
	public final String FILE_COMMON_TTML_FOLDER="ttml";
	public final String FILE_COMMON_SUB_TTML_FOLDER="Sub_ttml";
	public final String FILE_COMMON_TTML_WITH_BR_TAG_FOLDER="ttml";
	public final String FILE_COMMON_APOSTROPHE_FOLDER="Apostrophes Srt";
	public final String FILE_COMMON_ACCENTED_FOLDER="Accented Srt";
	public final String FILE_COMMON_ACCENTED_SECOND_FOLDER="Accented Second Srt";
	public final String FILE_COMMON_AN_TAG_SRT_FOLDER="AN_tagSrt";
	public final String FILE_COMMON_CHINESE_SRT_FOLDER="Chinese_Srt";
	public final String FILE_COMMON_IMPORT_SRT_FOLDER="ImportSRT";
	public final String FILE_COMMON_IMPORT_WRONGSRT_FOLDER="WrongSrt";
	public final String FILE_COMMON_TOPCENTER_SRT_FOLDER="TopCenter";
	public final String FILE_COMMON_2K_SRT_FOLDER="2k_SRT";
	public final String FILE_COMMON_EMPTY_SEGMENTS_FOLDER="emptySegments";
	public final String FILE_COMMON_BOLDANDITALIC_FOLDER="Bold_Italic";
	public final String FILE_COMMON_ARABIC_FOLDER="Arabic";
	public final String FILE_COMMON_DOT_SRT_FOLDER="Dot file";
	public final String FILE_COMMON_SPLIT_AND_MERGED="Split and merged";
	public final String FILE_COMMON_1343933_Source="TC_1343933_source";
	public final String FILE_COMMON_1343933_Import="TC_1343933_import";
	public final String FILE_COMMON_TTML_BOM_FOLDER="ttmlBom";
	public final String FILE_COMMON_LARGE_VIDEO="LargeVideoFile";
	public final String FILE_COMMON_LARGE_VIDEO_ASSET_ID="68722";
	public final String FILE_COMMON_1480484_SRT="TC_1480484";
	public final String DATE ="6/20/2018";
	public final String FILE_COMMON_XML_FOLDER="xmlFile";
	public final String FILE_COMMON_DROPFRAME_VIDEO_ASSET_ID="46554";
	public final String FILE_COMMON_SRT_FOLDER_AMPERSAND="ampersand character";

	public static final String UTF_8 = "UTF-8";
	

//	public final String COMMON_SOURCE_LANGUAGE="en-US - English (United States)";
	public final String COMMON_SOURCE_LANGUAGE="en-US";
//	public final String COMMON_SOURCE_LANGUAGE="English (United States)";

//	public final String COMMON_TARGET_LANGUAGE="de-DE - German (Germany)";
	public final String COMMON_TARGET_LANGUAGE="de-DE";
//	public final String COMMON_TARGET_LANGUAGE="German (Germany)";
	

	
	public  final String  Reviwer_Name= "Subtitler_Reviewer";
	//public  final static String  Reviwer_username= "quagnitosolutions@gmail.com";
	public  final String  Reviwer_username= "subtitlerreviewer@gmail.com";
    public final String Reviwer_username_multilang="subtitlerreviewmultilang@gmail.com";
	
	public  final String  admin_username= "admin@server.com";
	public  final String  admin_passowrd= "4d6pBQCM";
	
	
	public  final String  Reviwer_username_QMReview= "Glplayqmreview@gmail.com";
	public  final String  Reviwer_username_correction= "Glplaycorrectionreview@gmail.com";
	public  final String  Reviwer_username_finalReview= "Glplayfinalreview@gmail.com";
	
	public final String DATE_OFFSET ="10";
	public final Integer DATE_OFFSET_INTEGER =5;
	
	
	
	//PD DATA  pd_instance, pd_department, pd_workflow, pd_client,
	public final String PD_INSTANCE_URL="https://qa-tdc13.translations.com/PD/login";
	public final String INSTANCE ="qa-tdc13"; 
	public final String DEPARTMENT="GLPlay_Test_Project";
	public final String WORKFLOW="GLPlay_MT-Trans-Rev";
	public final String CLIENT="Subtitler_PAClient_4";
	public final String REVENUE="100";
	public final String RATE_TRANS="60";
	public final String RATE_REVIEW="30";
	
	public final String MediaNext_TAB_BARCOLOR="#3d87bb";
	public final String PD_TAB_BARCOLOR="#3d87bb";
	

}
