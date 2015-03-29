package survey.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import survey.model.SurveyFormModel;

public class SurveyDoRequestFilter implements Filter {

	private FilterConfig fc;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.fc=filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		SurveyFormModel model=new SurveyFormModel((HttpServletRequest) request);
		String name = model.getRemoteUser();
		if (name != null) {fc.getServletContext().log("User " + name + " do Filter in SurveyDoRequestFilter");}
		HttpSession session=model.getSession();
		String voteProduct=(String) session.getAttribute("voteProduct");
		if (voteProduct!=null){
			model.setInfo("You have voted " + voteProduct);
			model.setVoted(new Boolean(true));
		}
		else{model.setVoted(new Boolean(false));}
		String testinva=(String) session.getAttribute("testinva");
    	String Agent = model.getHeader("User-Agent");
    	if (testinva==null){testinva="testinva----"+Agent;session.setAttribute("testinva", testinva);}
		chain.doFilter(model, response);

	}

	@Override
	public void destroy() {

	}

}
