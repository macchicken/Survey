package survey.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SurveyFormModel extends HttpServletRequestWrapper {

	private String gender;
	private String vote;
	private String info;
	private Boolean voted;
	private String[][] preferences;


	public SurveyFormModel(HttpServletRequest request) {
		super(request);
		gender=(String) request.getParameter("gender");
		vote=(String) request.getParameter("vote");
	}

	public String getGender() {
		return gender;
	}

	public String getVote() {
		return vote;
	}
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
		this.setAttribute("info", info);
	}
	
	public Boolean isVoted() {
		return voted;
	}
	
	public void setVoted(Boolean voted) {
		this.voted = voted;
		this.setAttribute("voted", voted);
	}

	public void setPreferences(String[][] preferences) {
		this.preferences = preferences;
		this.setAttribute("preferences", preferences);
	}

	public String[][] getPreferences() {
		return preferences;
	}

	
	
}
