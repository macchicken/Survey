package survey;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import survey.listener.BeerSessionCounter;
import survey.model.SurveyResult;
/**
 * Servlet implementation class SurveyForm
 * This servlet displays a survey for user's next purchase
 */
 
@WebServlet(value="/surveyForm", initParams = {
		@WebInitParam(name="products", value="iphone6s, Samsung Galaxy 7, Nokia Lumia 532 ")
})
public class SurveyForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SurveyForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Prepare some initial data
     */
    public void init(){
    	String products = getServletConfig().getInitParameter("products");
    	String[] productList = products.split(",");
    	SurveyResult sr = new SurveyResult(productList.length);
    	getServletContext().setAttribute("productList", productList);
    	getServletContext().setAttribute("surveyResult", sr);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/survey.jsp");
		request.setAttribute("activeSessions", BeerSessionCounter.getActiveSessions());
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
