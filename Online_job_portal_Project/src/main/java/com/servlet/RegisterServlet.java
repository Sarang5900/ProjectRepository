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

@WebServlet("/add_user")
public class RegisterServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String name = req.getParameter("name");
			String qualification = req.getParameter("qua");
			String email = req.getParameter("email");
			String password = req.getParameter("ps");
			
			UserDAO dao = new UserDAO(DBConnect.getConnection());
			
			User u = new User(name, email, password, qualification, "user");
			boolean f = dao.addUser(u);
			
			HttpSession session = req.getSession();
			
			if(f) {
				session.setAttribute("succMsg", "Registration Successful!!");
				resp.sendRedirect("signUp.jsp");
			} else {
				session.setAttribute("succMsg", "Something went wrong!!");
				resp.sendRedirect("signUp.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
