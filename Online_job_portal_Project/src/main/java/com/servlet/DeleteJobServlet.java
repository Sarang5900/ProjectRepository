package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.JobDAO;

@WebServlet("/delete")
public class DeleteJobServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			
			JobDAO dao = new JobDAO(DBConnect.getConnection());
			boolean f = dao.deleteJobById(id);
			
			HttpSession session = req.getSession();
			if(f){
				session.setAttribute("succMsg", "Job Deleted Successfully!!");
				resp.sendRedirect("viewJobs.jsp");
			} else {
				session.setAttribute("succMsg", "Something Went Wrong!!");
				resp.sendRedirect("viewJobs.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	

}
