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
import com.entity.Jobs;


@WebServlet("/addJob")
public class addPostServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String title = req.getParameter("title");
			String desc = req.getParameter("desc");
			String category = req.getParameter("category");
			String status = req.getParameter("status");
			String location = req.getParameter("location");
			
			Jobs job = new Jobs();
			
			job.setTitle(title);
			job.setDescription(desc);
			job.setCategory(category);
			job.setStatus(status);
			job.setLocation(location);
			
			HttpSession session = req.getSession();
			
			JobDAO jDao = new JobDAO(DBConnect.getConnection());
			boolean f = jDao.addJobs(job);
			
			if(f) {
				session.setAttribute("succMsg", "Job Posted Successfully!!");
				resp.sendRedirect("add_jobs.jsp");
			}else {
				session.setAttribute("succMsg", "Something went wrong!!");
				resp.sendRedirect("add_jobs.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
