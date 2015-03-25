package survey;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import survey.listener.BeerSessionCounter;
import survey.model.SurveyResult;

/**
 * Servlet implementation class Survey
 * This class handles survey submitted by the user and displays results in another JSP
 */

@WebServlet("/survey")
public class Survey extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Survey() {
        super();
        // TODO Auto-generated constructor stub
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
    	SurveyResult surveyResult=(SurveyResult) getServletContext().getAttribute("surveyResult");
    	String[] productList=(String[]) getServletContext().getAttribute("productList");
    	HttpSession session=request.getSession();
    	if (surveyResult==null){surveyResult=new SurveyResult(productList.length);}
    	String[][] preferences=(String[][]) session.getAttribute("preferences");
    	String voteProduct=(String) session.getAttribute("voteProduct");
    	String testinva=(String) session.getAttribute("testinva");
    	String Agent = request.getHeader("User-Agent");
    	if (testinva==null){testinva="testinva----"+Agent;session.setAttribute("testinva", testinva);}
    	if (preferences==null){preferences=initPreferences(productList,surveyResult);}
    	String gender=request.getParameter("gender");
    	String vote=request.getParameter("vote");
    	if (voteProduct!=null){request.setAttribute("info", "You have voted " + voteProduct);}
    	else{
    	if (vote!=null&&!"".equals(vote.trim())) {
				surveyResult.addPref(Integer.parseInt(gender),Integer.parseInt(vote));
				int i=Integer.parseInt(vote);
				preferences[Integer.parseInt(gender)][i]=productList[i].trim()+ ": "+
						String.valueOf(surveyResult.getPref(Integer.parseInt(gender), i));
				request.setAttribute("info", "Thank you for participating in the Mobile Purchasing Survey!");
				session.setAttribute("voteProduct", productList[Integer.parseInt(vote)]);
		}else{
			request.setAttribute("info", "Plz choose a prodcut for voting" );
		}}
    	request.setAttribute("preferences", preferences);
    	session.setAttribute("preferences", preferences);
    	request.setAttribute("activeSessions", BeerSessionCounter.getActiveSessions());
		// let a jsp page display the result
    	RequestDispatcher view = request.getRequestDispatcher("/surveyResult.jsp");
		view.forward(request,response);
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
