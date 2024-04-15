package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			User user = new User();
			HttpSession session = req.getSession();
			
			if("admin@gmail.com".equals(email) && "Sarang@5900".equals(password)) {
				session.setAttribute("userobj", user);
				user.setRole("admin");
				resp.sendRedirect("admin.jsp");
			} else {

				UserDAO dao = new UserDAO(DBConnect.getConnection());
				
				User u = dao.loginUser(email, password);
				
				if(u != null) {
					session.setAttribute("userobj", u);
					resp.sendRedirect("home.jsp");
				} else {
					session.setAttribute("succMsg", "Invalid Credentials. please try again!!");
					resp.sendRedirect("login.jsp");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
