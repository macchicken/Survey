package survey.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import survey.common.Constants;
import survey.listener.BeerSessionCounter;

public class BeerRequestFilter implements Filter {

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
		if (name != null) {fc.getServletContext().log("User " + name + " do Filter in BeerRequestFilter");}
		request.setAttribute("activeSessions", BeerSessionCounter.getActiveSessions());
		request.setAttribute("displayRoleInfo", Constants.displayUserRoleInfo(httpReq));
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
