package survey.service;

import java.util.HashMap;

public class InMemoryUserSurveyResults implements UserSurveyResults {

	private static HashMap<String,String> results;
	
	private static class UserSurveyResultsHolder{
		private static final InMemoryUserSurveyResults instance=new InMemoryUserSurveyResults();
	}

	private InMemoryUserSurveyResults(){
		results=new HashMap<String,String>();
	}

	public static InMemoryUserSurveyResults getInstance(){
		return UserSurveyResultsHolder.instance;
	}

	@Override
	public void addSurveyResult(String userName, String result) {
		if (results != null) {
			synchronized (results) {
					results.put(userName, result);
			}
		}
	}

	@Override
	public String getSurveyResult(String userName) {
		return results.get(userName);
	}

}
