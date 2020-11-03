package com.phucle.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phucle.blog.model.Category;
import com.phucle.blog.service.CategoryService;

/**
 * Servlet implementation class AdminCategory
 */
@WebServlet("/admin-category")
public class AdminCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryService catService = new CategoryService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();

		String username = (String) session.getAttribute("username");
		if (username == null || username.isEmpty()) {
			response.sendRedirect("authentication");
		}
		
		String action = request.getParameter("action");
		if (action == null)
			action = "";
		switch (action) {
		case "add":
			this.showAddView(request, response);
			break;
		case "edit":
			this.showEditView(request, response);
			break;
		case "delete":
			this.doDeleteUser(request, response);
			break;
		default:
			this.showListView(request, response);
			break;

		}
	}

	private void showAddView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category cate = new Category();
		request.setAttribute("cate", cate);

		RequestDispatcher rd = request.getRequestDispatcher("admin-category-add.jsp");
		rd.forward(request, response);

	}

	private void showEditView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Category cate = this.catService.findById(id);
		if (cate!=null) {
			request.setAttribute("cate", cate);
			RequestDispatcher rd = request.getRequestDispatcher("admin-category-edit.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect("404");
		}
		
	}

	private void doDeleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		this.catService.delete(id);
		
		request.setAttribute("message","Xoa thanh cong");
		this.showListView(request, response);
		
		
	}

	private void showListView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> listCategory = this.catService.findAll();

		request.setAttribute("list", listCategory);

		RequestDispatcher rd = request.getRequestDispatcher("admin-category.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();

		String username = (String) session.getAttribute("username");
		if (username == null || username.isEmpty()) {
			response.sendRedirect("authentication");
		}
		
		String action = request.getParameter("action");
		if (action == null)
			action = "";
		switch (action) {
		case "add":
			this.doAdd(request, response);
			break;
		case "edit":
			this.doEdit(request, response);
			break;
		case "delete":
			break;
		default:
			// this.showListView(request, response);
			break;

		}
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		Category cate = new Category(0,name,description);
		this.catService.save(cate);
		
		//
		request.setAttribute("message","Thêm  moi thanh cong");
		this.showAddView(request, response);
		//this.showListView(request, response);
	}

	private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int id = Integer.parseInt(request.getParameter("id"));
		Category cate = new Category(id,name,description);
		
		this.catService.update(cate);
		
		//
		request.setAttribute("message","Cap nhat thanh cong");
		this.showEditView(request, response);
	}
}
