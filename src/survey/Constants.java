package survey;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class Constants {

	public static final ArrayList<String> roles=new ArrayList<String>();
	static{
		roles.add("SurveyAdmin");
		roles.add("SurveyMember");
		roles.add("SurveyGuest");
	}
	
	public static String displayUserRoleInfo(HttpServletRequest request){
		String displayRoleInfo="";
		for (String role:Constants.roles){
			if (request.isUserInRole(role)){displayRoleInfo=displayRoleInfo+role+" ";}
		}
		if (!displayRoleInfo.equals("")){displayRoleInfo="user "+request.getRemoteUser()+" has "+displayRoleInfo.trim();}
		return displayRoleInfo;
	}
}
