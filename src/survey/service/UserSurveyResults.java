package survey.service;

public interface UserSurveyResults {

	public void addSurveyResult(String userName,String result);
	
	public String getSurveyResult(String userName);
}
