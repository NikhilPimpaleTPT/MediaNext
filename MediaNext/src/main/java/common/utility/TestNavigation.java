package common.utility;

import locators.CommonLocartors;


public class TestNavigation extends General{
	
	
	public void Navigate(String menu) throws Exception {
		
		if(VerifyElementPresent(CommonLocartors.Locator().CollapseMenuSideBar)){
			Click(CommonLocartors.Locator().ExpandMenuSideBar);
		}
		
		if(menu!=null){
			waitforelemenetpresent(CommonLocartors.Locator().SelectMenu(menu));
			Click(CommonLocartors.Locator().SelectMenu(menu));
			
			System.out.println("navigate to ------------------------------------------------------"+menu);
		}
		
		
	}
}

