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

public class VideoAuth implements Filter {

    public VideoAuth() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		
		String idVideo = req.getParameter("v");
		
		if (idVideo == null) {
			chain.doFilter(request, response);
			return;
		}
		
		if (user.getVideosMap().containsKey(Integer.parseInt(idVideo))) {
			chain.doFilter(request, response);
			return;
		}
		
		resp.sendRedirect(req.getContextPath()+"/video/list");
		
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
