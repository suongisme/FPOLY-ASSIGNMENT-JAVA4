package poly.pt15307.sof3011.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import poly.pt15307.sof3011.constant.VideoConstant;
import poly.pt15307.sof3011.model.React;
import poly.pt15307.sof3011.model.User;
import poly.pt15307.sof3011.model.Video;
import poly.pt15307.sof3011.service.VideoService;

@WebServlet("/video/*")
@MultipartConfig(
		fileSizeThreshold = 1024*1024, // 01MB
		maxFileSize = 1024*1024*35,    // 35MB
		maxRequestSize = 1024*1024*35) // 35MB
public class VideoController extends HttpServlet{

	private static final long serialVersionUID = 2001L;

	private VideoService videoService;
	
	public VideoController() {
		videoService = new VideoService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String pathInfo = req.getPathInfo();
		
		if (pathInfo == null) {
			homePage(req, resp);
			return;
		}
		
		switch (pathInfo) {
			case "/watch":
				this.watchVideoPage(req, resp);
				break;
			case "/search":
				this.resultSearchPage(req, resp);
				break;
			case "/add":
				this.showVideoForm(req, resp);
				break;
			case "/edit":
				this.showVideoForm(req, resp);
				break;
			case "/delete":
				this.delete(req, resp);
				break;
			case "/":
				homePage(req, resp);
				break;
			default:
				resp.sendError(404);
				break;
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pathInfo = req.getPathInfo();
		
		if (pathInfo == null) {
			return;
		}
		
		switch (pathInfo) {
		case "/add":
			this.add(req, resp);
			break;
		case "/edit":
			this.edit(req, resp);
			break;
		default:
			break;
		}
	}
	
	private void homePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("cssPage", "home.css");
		req.setAttribute("videos", videoService.getAll());
		req.setAttribute("page", "page/home.jsp");
		req.setAttribute("title", "Youtube");
		req.getRequestDispatcher("/view/ui/layout.jsp").forward(req, resp);
	}
	
	public void resultSearchPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String search = req.getParameter("s");
		req.setAttribute("cssPage", "ResultSearch.css");
		req.setAttribute("page", "page/ResultSearch.jsp");
		req.setAttribute("title", search + " - youtube");
		req.setAttribute("videos", videoService.findVideo(search.trim()));
		req.getRequestDispatcher("/view/ui/layout.jsp").forward(req, resp);
	}
	
	private void watchVideoPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Video video = videoService.getByIdAndView(Integer.parseInt(req.getParameter("v")));
			
			req.getSession().setAttribute("video", video);
			req.setAttribute("numberLike", video.getNumber(VideoConstant.LIKE));
			
			req.setAttribute("numberDislike", video.getNumber(VideoConstant.DISLIKE));
			req.setAttribute("video", video);
			
			req.setAttribute("videos", video.getAuthor().getAllVideoExcep(video));
			
			req.getRequestDispatcher("/view/ui/video.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(404);
		}
	}
	
	private void showVideoForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		String pathInfo = req.getPathInfo();
		
		switch (pathInfo) {
			case "/add":
				req.setAttribute("action", req.getContextPath()+"/video/add");
				req.setAttribute("video", new Video());
				break;
			case "/edit":
				req.setAttribute("action", req.getContextPath()+"/video/edit");
				req.setAttribute("video", user.getVideosMap().get(Integer.parseInt(req.getParameter("v"))));
				break;
			default:
				break;
		}
		
		req.setAttribute("jsPage", "videoform.js");
		req.setAttribute("cssPage", "videoform.css");
		req.setAttribute("page", "page/videoform.jsp");
		req.setAttribute("title", "Video-Youtube");
		req.getRequestDispatcher("/view/ui/layout.jsp").forward(req, resp);
	}
	
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Video video = new Video();
		
		try {
			BeanUtils.populate(video, req.getParameterMap());
			video.setAuthor((User) session.getAttribute("user"));
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		Video vd = this.addInDB(video);
		handleImage("id-"+vd.getId(), req);
		this.addInSession(video, session);
		
		resp.sendRedirect(req.getContextPath()+"/user/myvideo");
		
	}
	
	private void addInSession(Video video,HttpSession session) {
		User user = (User) session.getAttribute("user");
		
		if (video.getReact() == null) {
			video.setReact(new ArrayList<React>());
		}
		
		user.getVideosMap().put(video.getId(), video);
	}
	
	private Video addInDB(Video video) {
		return videoService.insert(video);
		
	}
	
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Video video = new Video();
		
		try {
			BeanUtils.populate(video, req.getParameterMap());
			video.setThumbnailUrl(handleImage("id-"+video.getId(),req));
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		if (this.editInDB(video))
			this.editInSession(video, req.getSession());
		resp.sendRedirect(req.getContextPath()+"/user/myvideo");
	}
	
	private boolean editInDB(Video video) {
		return videoService.update(video);
	}
	
	private void editInSession(Video video, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Video vd = user.getVideosMap().get(video.getId());
		
		video.setReact(vd.getReact());
		
		user.getVideosMap().put(video.getId(), video);
	}
	
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idVideo = req.getParameter("v");
		HttpSession session = req.getSession();
		
		int id = Integer.parseInt(idVideo);
		
		if (this.deleteInDB(id))
			this.deleteInSession(id, session);
		
		resp.sendRedirect(req.getContextPath()+"/user/myvideo");
		
	}
	
	private boolean deleteInDB(int id) {
		return videoService.delete(id);
	}
	
	private void deleteInSession(int id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		user.getVideosMap().remove(id);
	}
	
	
	private String handleImage(String fileName, HttpServletRequest req) throws IOException, ServletException {
		
		String prefix = req.getServletContext()
							.getInitParameter("path-image-video-prefix");
		
		String suffix = req.getServletContext()
							.getInitParameter("path-suffix");

		StringBuffer path = new StringBuffer(prefix)
								.append(fileName)
								.append(suffix);
		
		
		Part part = req.getPart("photo");
		
		InputStream input = part.getInputStream();
		
		String realPath = req.getServletContext().getRealPath("");
		
		String relativePath = path.toString();
		String absolutePath =  path.insert(0, realPath).toString();
		
		OutputStream output = new FileOutputStream(new File(absolutePath));
		
		try (input; output) {
			output.write(input.readAllBytes());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return relativePath;
	}
}
