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
 * Servlet implementation class CategoryController
 */
@WebServlet("/category")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {
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
		Category category = categoryService.findById(id);		
		
		List<Category> listCat = categoryService.findAll();
		
		BlogService blogService = new BlogService();
		List<Blog> listBlogByCatId = blogService.findByCate(id);
		List<Blog> listTop5Popular = blogService.findTop5Popular();
		
		AboutService aboutService = new AboutService();
		About about = aboutService.getAbout();
		
		request.setAttribute("cat", category);
		request.setAttribute("about", about);
		request.setAttribute("listCat", listCat);
		request.setAttribute("listTop5Popular", listTop5Popular);
		request.setAttribute("listBlog", listBlogByCatId);
		RequestDispatcher rd=request.getRequestDispatcher("ui-category2.jsp");
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
