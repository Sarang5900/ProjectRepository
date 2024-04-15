package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Jobs;

public class JobDAO {
	private Connection conn;
	
	public JobDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean addJobs(Jobs job) {
		boolean status = false;
		
		try {
			String query = "insert into jobs(title, description, category, status, location) values(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, job.getTitle());
			ps.setString(2, job.getDescription());
			ps.setString(3, job.getCategory());
			ps.setString(4, job.getStatus());
			ps.setString(5, job.getLocation());
			
			int affectedRows  = ps.executeUpdate();
			
			if(affectedRows == 1) {
				status = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public List<Jobs> getAllJobs(){
		
		List<Jobs> list = new ArrayList<Jobs>();
		
		Jobs job = null;
		
		try {
			
			String query = "select * from jobs order by id desc";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				job = new Jobs();
				job.setId(rs.getInt(1));
				job.setTitle(rs.getString(2));
				job.setDescription(rs.getString(3));
				job.setCategory(rs.getString(4));
				job.setStatus(rs.getString(5));
				job.setLocation(rs.getString(6));
				job.setJobPostDate(rs.getTimestamp(7)+"");
				
				list.add(job);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public List<Jobs> getJobsForUSer(){
		
		List<Jobs> list = new ArrayList<Jobs>();
		
		Jobs job = null;
		
		try {
			
			String query = "select * from jobs where status=? order by id desc";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "Active");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				job = new Jobs();
				job.setId(rs.getInt(1));
				job.setTitle(rs.getString(2));
				job.setDescription(rs.getString(3));
				job.setCategory(rs.getString(4));
				job.setStatus(rs.getString(5));
				job.setLocation(rs.getString(6));
				job.setJobPostDate(rs.getTimestamp(7)+"");
				
				list.add(job);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Jobs getJobById(int id){
				
		Jobs job = null;
		
		try {
			
			String query = "select * from jobs where id = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				job = new Jobs();
				job.setId(rs.getInt(1));
				job.setTitle(rs.getString(2));
				job.setDescription(rs.getString(3));
				job.setCategory(rs.getString(4));
				job.setStatus(rs.getString(5));
				job.setLocation(rs.getString(6));
				job.setJobPostDate(rs.getTimestamp(7)+"");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return job;
	}
	
	public boolean updateJob(Jobs job) {
		boolean f = false;
		
		try {
			String query = "update Jobs set title=?, description=?, category=?, status=?, location=? where id =?";
			
			PreparedStatement ps = conn.prepareStatement(query);
					
			ps.setString(1, job.getTitle());
			ps.setString(2, job.getDescription());
			ps.setString(3, job.getCategory());
			ps.setString(4, job.getStatus());
			ps.setString(5, job.getLocation());
			ps.setInt(6, job.getId());
			
			int affectedRows  = ps.executeUpdate();
			
			if(affectedRows == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public boolean deleteJobById(int id) {
		boolean f = false;
		
		try {
			String query ="delete from Jobs where id = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if(i ==1 ) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public List<Jobs> getJobsByLocationOrCategory(String category, String location){
		
		List<Jobs> list = new ArrayList<Jobs>();
		
		Jobs job = null;
		
		try {
			
			String query = "select * from jobs where category=? or location=? order by id desc";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, category);
			ps.setString(2, location);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				job = new Jobs();
				job.setId(rs.getInt(1));
				job.setTitle(rs.getString(2));
				job.setDescription(rs.getString(3));
				job.setCategory(rs.getString(4));
				job.setStatus(rs.getString(5));
				job.setLocation(rs.getString(6));
				job.setJobPostDate(rs.getString(7));
				list.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Jobs> getJobsByLocationAndCategory(String category, String location){
		
		List<Jobs> list = new ArrayList<Jobs>();
		
		Jobs job = null;
		
		try {
			
			String query = "select * from jobs where category=? and location=? order by id desc";
			
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, category);
			ps.setString(2, location);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				job = new Jobs();
				job.setId(rs.getInt(1));
				job.setTitle(rs.getString(2));
				job.setDescription(rs.getString(3));
				job.setCategory(rs.getString(4));
				job.setStatus(rs.getString(5));
				job.setLocation(rs.getString(6));
				job.setJobPostDate(rs.getString(7));
				list.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
