package com.phucle.blog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.phucle.blog.service.*;
import com.phucle.blog.model.*;
/**
 * Servlet implementation class AuthenticationController
 */
@WebServlet("/authentication")
public class AuthenticationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService userService = new UserService(); 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthenticationController() {
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
		String action = request.getParameter("action");
		if (action == null)
			action = "login";
		switch (action) {
			case "login":
				this.showLogin(request, response);
				break;
			case "logout":
				this.showLogout(request, response);
				break;

		}

	}

	private void showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
	
	private void showLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//XOA HET CAC SESSION DE TRA VE LOGIN
		session.invalidate();
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String username= request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = userService.findByUsernamePassword(username, password);
		//Dang nhap thanh cong
		if (user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());
			session.setAttribute("user", user);
			response.sendRedirect("dashboard");
		}else {
			//Dang nhap khong thanh cong
			request.setAttribute("message", "Username, password could wrong, pls try again!");
			this.showLogin(request, response);	
		}
		
		
		//
	}

}
