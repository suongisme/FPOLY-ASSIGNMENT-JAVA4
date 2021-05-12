package poly.pt15307.sof3011.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.pt15307.sof3011.model.User;

public class Active implements Filter {

    public Active() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if (user.isActive()) {
			chain.doFilter(request, response);
			return;
		}
		
		session.removeAttribute("user");
		req.setAttribute("error", "Your account was disable. Please contact the administrators to solve it!");
		resp.sendError(404);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
