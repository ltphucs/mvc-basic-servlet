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
 * Servlet implementation class BlogSingleController
 */
@WebServlet("/blog-single")
public class BlogSingleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogSingleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		CategoryService categoryService = new CategoryService();
		List<Category> listCat = categoryService.findAll();
		
		BlogService blogService = new BlogService();
		Blog blog = blogService.findById(id);
		//Tang view
		blogService.incViews(id);
		//
		List<Blog> listTop5Popular = blogService.findTop5Popular();
		List<Blog> listTop3Related = blogService.findTop3Related(blog.getIdCategory());
		
		AboutService aboutService = new AboutService();
		About about = aboutService.getAbout();
		
		request.setAttribute("blog", blog);
		request.setAttribute("about", about);
		request.setAttribute("listCat", listCat);
		request.setAttribute("listTop5Popular", listTop5Popular);
		request.setAttribute("listTop3Related", listTop3Related);
		RequestDispatcher rd=request.getRequestDispatcher("ui-details.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
