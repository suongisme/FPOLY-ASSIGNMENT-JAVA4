package poly.pt15307.sof3011.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import poly.pt15307.sof3011.model.User;
import poly.pt15307.sof3011.model.Video;
import poly.pt15307.sof3011.service.ReactService;

@WebServlet("/react")
public class ReactController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	ReactService reactService;
	
    public ReactController() {
        super();
        reactService = new ReactService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hello world");
		response.getWriter().print("hello world");
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		Video video = (Video) session.getAttribute("video");
		
		boolean isLike = "like".equals(request.getParameter("state"));
		
		if (reactService.delete(user, video, isLike)) {
			user.removeVideoInSession(video);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		Video video = (Video) session.getAttribute("video");
		
		boolean isLike = "like".equals(request.getParameter("state"));
		
		if (reactService.update(user, video, isLike)) {
			if (isLike == false)
				user.removeVideoInSession(video);
			if (isLike == true)
				user.addVideoToSession(video);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		Video video = (Video) session.getAttribute("video");
		boolean isLike = "like".equals(request.getParameter("state"));
		if (reactService.insert(user, video, isLike)) {
			user.addVideoToSession(video);
		}
	}

}
