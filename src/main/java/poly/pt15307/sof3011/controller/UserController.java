package poly.pt15307.sof3011.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import poly.pt15307.sof3011.model.User;
import poly.pt15307.sof3011.model.Video;
import poly.pt15307.sof3011.service.UserService;

@WebServlet("/user/*")
@MultipartConfig(
		fileSizeThreshold = 1024*1024, // 01MB
		maxFileSize = 1024*1024*35,    // 35MB
		maxRequestSize = 1024*1024*35) // 35MB
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService;

	public UserController() {
		userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();

		if (pathInfo == null) {
			req.getRequestDispatcher("/view/ui/LoginForm.jsp").forward(req, resp);
			return;
		}
		
		switch (pathInfo) {
		case "/register":
			req.getRequestDispatcher("/view/ui/RegisterForm.jsp").forward(req, resp);
			break;
		case "/profile":
			req.setAttribute("jsPage", "profile.js");
			req.setAttribute("cssPage", "profile.css");
			req.setAttribute("page", "page/profile.jsp");
			req.setAttribute("title", "PROFILE");
			req.getRequestDispatcher("/view/ui/layout.jsp").forward(req, resp);
			break;
		case "/logout":
			this.logout(req, resp);
			break;
		case "/loved":
			User user = (User) req.getSession().getAttribute("user");
			List<Video> lovedVideos = user.lovedVideos();
			
			req.setAttribute("videos", lovedVideos);
			req.setAttribute("cssPage", "loved.css");
			req.setAttribute("page", "page/loved.jsp");
			req.setAttribute("title", "Youtube");
			req.getRequestDispatcher("/view/ui/layout.jsp").forward(req, resp);
			break;
		case "/myvideo":
			req.setAttribute("cssPage", "listvideo.css");
			req.setAttribute("page", "page/listvideo.jsp");
			req.setAttribute("title", "Youtube");
			req.setAttribute("jsPage", "listvideo.js");
			req.getRequestDispatcher("/view/ui/layout.jsp").forward(req, resp);
			break;
		case "/login":
			req.getRequestDispatcher("/view/ui/LoginForm.jsp").forward(req, resp);
			break;
		default:
			resp.sendError(404);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pathInfo = req.getPathInfo();
		switch (pathInfo) {
		case "/login":
			this.login(req, resp);
			break;
		case "/register":
			this.register(req, resp);
			break;
		case "/profile":
			this.edit(req, resp);
			break;
		default:
			break;
		}
	}
	
	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		
		String email = req.getParameter("email").trim();
		String password = req.getParameter("password").trim();
		User user;
		if ((user = userService.login(email, password)) != null) {
			session.setAttribute("user", user);
			resp.sendRedirect(req.getContextPath()+"/user/myvideo");
			return;
		}
		
		req.setAttribute("status", "Your email or password is wrong");
		req.getRequestDispatcher("/view/ui/LoginForm.jsp").forward(req, resp);
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath()+"/video");
	}
	
	private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		User user = new User();
		try {
			BeanUtils.populate(user, req.getParameterMap());
			user.setAvatar(handleImage(req, user.getEmail()));
			user.setActive(true);
			userService.insert(user);
			resp.sendRedirect(req.getContextPath()+"/user/login");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("status", e.getMessage());
			req.getRequestDispatcher("/view/ui/RegisterForm.jsp").forward(req, resp);
		}
	}
	
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		User user = new User();
		
		try {
			BeanUtils.populate(user, req.getParameterMap());
			user.setAvatar(handleImage(req, user.getEmail()));
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		if (userService.update(user)) {
			if (!user.isActive()) {
				req.getSession().invalidate();
			} else {
				updateUserSession(user, req);
			}
			resp.sendRedirect(req.getContextPath()+"/video");
		}
	}
	
	public void updateUserSession(User user,HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		User userSession = (User) session.getAttribute("user");
		
		user.setVideosMap(userSession.getVideosMap());
		user.setReacts(userSession.getReacts());
		
		session.setAttribute("user", user);
	}
	
	private String handleImage(HttpServletRequest request, String email) throws IOException, ServletException {
		String prefix = request.getServletContext()
								.getInitParameter("path-image-user-prefix");
		
		String suffix = request.getServletContext()
								.getInitParameter("path-suffix");
		
		StringBuilder path = new StringBuilder()
								 .append(prefix)
								 .append(email)
								 .append(suffix);
		
		Part part = request.getPart("photo");
		
		InputStream input = part.getInputStream();
		
		String realPath = request.getServletContext().getRealPath("");
		
		String relativePath = path.toString();
		String absolutePath = path.insert(0, realPath).toString();
		
		OutputStream output = new FileOutputStream(absolutePath);
		
		try (input; output) {
			output.write(input.readAllBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return relativePath;
	}

}
