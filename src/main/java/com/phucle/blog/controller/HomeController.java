package com.phucle.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phucle.blog.model.About;
import com.phucle.blog.model.Blog;
import com.phucle.blog.model.Category;
import com.phucle.blog.service.AboutService;
import com.phucle.blog.service.BlogService;
import com.phucle.blog.service.CategoryService;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BlogService blogService = new BlogService();
	private CategoryService catService = new CategoryService();
     
	private AboutService aboutService = new AboutService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//
		//DUA DU LIEU LIST CATEGORY RA UI
		List<Category> listCategory = this.catService.findAll();
		request.setAttribute("listCate", listCategory);
		//DUA DU LIEU BLOG RA UI
		List<Blog> list3Blogs =this.blogService.findTop3();
		List<Blog> list12Blogs =this.blogService.findTop12();
		List<Blog> list5Popular =this.blogService.findTop5Popular();
		
		request.setAttribute("list3Blogs", list3Blogs);
		request.setAttribute("list12Blogs", list12Blogs);
		request.setAttribute("list5Popular", list5Popular);
		
		About about = this.aboutService.getAbout();
		request.setAttribute("about", about);
		
		RequestDispatcher rd = request.getRequestDispatcher("ui-home.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
