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

public class SurveyDoRequestFilter implements Filter {

	private FilterConfig fc;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.fc=filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		String name = httpReq.getRemoteUser();
		if (name != null) {fc.getServletContext().log("User " + name + " do Filter in SurveyDoRequestFilter");}
		HttpSession session=httpReq.getSession();
		String voteProduct=(String) session.getAttribute("voteProduct");
		if (voteProduct!=null){
			httpReq.setAttribute("info", "You have voted " + voteProduct);
			httpReq.setAttribute("voted", new Boolean(true));}
		else{httpReq.setAttribute("voted", new Boolean(false));}
		String testinva=(String) session.getAttribute("testinva");
    	String Agent = httpReq.getHeader("User-Agent");
    	if (testinva==null){testinva="testinva----"+Agent;session.setAttribute("testinva", testinva);}
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {

	}

}
