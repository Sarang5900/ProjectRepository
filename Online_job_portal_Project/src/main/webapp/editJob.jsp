<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<%@ page import="com.dao.JobDAO"%>
<%@ page import="com.DB.DBConnect"%>
<%@ page import="com.entity.Jobs"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit job</title>

<%@include file="all_component/all_css.jsp"%>

</head>
<body style="background-color: #f0f1f2;">

	<c:if test="${userobj.role ne 'admin'}">
		<c:redirect url ="login.jsp"></c:redirect>
	</c:if>

	<%@include file="all_component/navbar.jsp"%>
	
	<div class="container p-2">
		<div class="col-md-10 offset-md-1">
			<div class="card">
				<div class="card-body">
					<div class="text-center text-success">
						<i class="fa-solid fa-user-pen fa-3x"></i>
						
						<%
						int id = Integer.parseInt(request.getParameter("id"));
						
						JobDAO dao = new JobDAO(DBConnect.getConnection());
						Jobs job = dao.getJobById(id);
						%>
						
						<h5>Edit Job</h5>
					</div>

					<form action="update" method="post">
					
					<input type = "hidden" value="<%=job.getId()%>" name="id">
					
						<div class="form-group">
							<label>Enter Title</label> <input type="text" name="title"
								required class="form-control" value="<%=job.getTitle()%>">
						</div>
						<div class="form-row">
							<div class="form-group col-md-4">
								<label>Location</label> 
								<select name="location"
									class="custom-select " id="inlineFormCustomSelectPref">
									<option value="<%=job.getLocation() %>"><%=job.getLocation() %></option>
									<option value="Mumabi">Mumbai</option>
									<option value="Odisha">Odisha</option>
									<option value="Jharkhand">Jharkhand</option>
									<option value="Gujurat">Gujurat</option>
									<option value="Bhubaneswar">Bhubaneswar</option>
									<option value="Delhi">Delhi</option>
									<option value="Banglore">Banglore</option>
									<option value="Chennai">Chennai</option>
									<option value="Hydrabad">Hydrabad</option>
									<option value="Pune">Pune</option>
								</select>
							</div>
							<div class="form-group col-md-4">
								<label>Category</label> 
								<select class="custom-select "
									id="inlineFormCustomSelectPref" name="category">
									<option value="<%=job.getCategory() %> "><%=job.getCategory() %></option>
									<option value="IT">IT</option>
									<option value="Devloper">Developer</option>
									<option value="Tester">Tester</option>
									<option value="Banking">Banking</option>
									<option value="Engineer">Engineer</option>
									<option value="Teacher">Teacher</option>
								</select>
							</div>
							<div class="form-group col-md-4">
								<label>Status</label> 
								<select class="form-control" name="status">
									<option class="Active" value="<%=job.getStatus() %>"><%=job.getStatus() %></option>
									<option class="Active" value="Active">Active</option>
									<option class="Inactive" value="Inactive">Inactive</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label>Enter Description</label> 
							<textarea required rows="6" cols="" name="desc" class="form-control"><%=job.getDescription() %></textarea>
						</div>
						<button class="btn btn-success">Save Changes</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>