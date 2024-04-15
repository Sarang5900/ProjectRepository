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


@WebServlet("/update")
public class UpdateJobServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String title = req.getParameter("title");
			String desc = req.getParameter("desc");
			String category = req.getParameter("category");
			String status = req.getParameter("status");
			String location = req.getParameter("location");
			
			Jobs job = new Jobs();
			
			job.setId(id);
			job.setTitle(title);
			job.setDescription(desc);
			job.setCategory(category);
			job.setStatus(status);
			job.setLocation(location);
			
			HttpSession session = req.getSession();
			
			JobDAO jDao = new JobDAO(DBConnect.getConnection());
			boolean f = jDao.updateJob(job);
			
			if(f) {
				session.setAttribute("succMsg", "Job Updated Successfully!!");
				resp.sendRedirect("viewJobs.jsp");
			}else {
				session.setAttribute("succMsg", "Something went wrong!!");
				resp.sendRedirect("viewJobs.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
