package survey.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import survey.model.SurveyFormModel;
import survey.model.SurveyResult;
import survey.service.InMemoryUserSurveyResults;
import survey.service.UserSurveyResults;

/**
 * Servlet implementation class Survey
 * This class handles survey submitted by the user and displays results in another JSP
 */

@WebServlet("/survey.do")
public class Survey extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserSurveyResults userSurveyResults;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Survey() {
        super();
    }

    @Override
	public void init() throws ServletException {
    	userSurveyResults=InMemoryUserSurveyResults.getInstance();
	}

	private String[][] initPreferences(String[] productList,SurveyResult surveyResult){
    	String[][] preferences=new String[2][productList.length];
    	for (int i = 0; i < preferences.length; i++) {
    		for (int j=0;j<preferences[i].length;j++){
    			preferences[i][j] = productList[j].trim()+ ": "+ String.valueOf(surveyResult.getPref(i, j));
    		}
    	}
    	return preferences;
    }

    public void processResult(HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException{
    	// add your code here to get the survey data 
    	// and to store it in the surveyResult object
    	SurveyFormModel model=(SurveyFormModel) request;
    	SurveyResult surveyResult=(SurveyResult) getServletContext().getAttribute("surveyResult");
    	String[] productList=(String[]) getServletContext().getAttribute("productList");
    	HttpSession session=model.getSession();
    	if (surveyResult==null){surveyResult=new SurveyResult(productList.length);}
    	String[][] preferences=(String[][]) session.getAttribute("preferences");
    	if (preferences==null){preferences=initPreferences(productList,surveyResult);}
    	String gender=model.getGender();String vote=model.getVote();
    	if (!model.isVoted()){
    	if (vote!=null&&!"".equals(vote.trim())) {
	    		int i=Integer.parseInt(vote);
				surveyResult.addPref(Integer.parseInt(gender),i);
				preferences[Integer.parseInt(gender)][i]=productList[i].trim()+ ": "+
						String.valueOf(surveyResult.getPref(Integer.parseInt(gender), i));
				model.setInfo("Thank you for participating in the Mobile Purchasing Survey!");
				session.setAttribute("voteProduct", productList[i]);
				userSurveyResults.addSurveyResult(model.getRemoteUser(), productList[i]);
		}else{
			model.setInfo("Plz choose a prodcut for voting");
		}}
    	model.setPreferences(preferences);
    	session.setAttribute("preferences", preferences);
		// let a jsp page display the result
    	RequestDispatcher view = model.getRequestDispatcher("/surveyResult.jsp");
		view.forward(model,response);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processResult(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processResult(request,response);
	}

}
