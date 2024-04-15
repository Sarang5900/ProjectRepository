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
<title>Admin: View Jobs</title>

<%@include file="all_component/all_css.jsp"%>

</head>
<body style="background-color: #f0f1f2;">

	<c:if test="${userobj.role ne 'admin'}">
		<c:redirect url ="login.jsp"></c:redirect>
	</c:if>

	<%@include file="all_component/navbar.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h5 class="text-center text-primary">All Jobs</h5>
				
				<c:if test="${not empty succMsg }">
					<div class="alert alert-success" role="alert"> ${ succMsg}</div>
					<c:remove var="succMsg" />
				</c:if>
				
				<% 
				JobDAO dao = new JobDAO(DBConnect.getConnection());
				
				List<Jobs> list = dao.getAllJobs();
				for(Jobs job : list)
				{%>
				
				<div class="card mt-2">
					<div class="card-body">
						<div class="text-center text-primary">
							<i class="far fa-clipboard fa-2x"></i>
						</div>
						
						<h6><%=job.getTitle() %></h6>
						<p><%=job.getDescription() %></p>
						<br>
						<div class="form-row">
							<div class="form-group col-md-3"> <input type="text"
								class="form-control form-control-sm" value="Location:<%=job.getLocation() %>" readonly>
							</div>

							<div class="form-group col-md-3">
								<input type="text" class="form-control form-control-sm"
									value="Category:<%=job.getCategory() %>" readonly>
							</div>

							<div class="form-group col-md-3">
								<input type="text" class="form-control form-control-sm"
									value="Status:<%=job.getStatus() %>" readonly>
							</div>
					    </div>
						<h6>Publish Date: <%=job.getJobPostDate() %></h6>
						<div class=" text-center ">
							<a href="editJob.jsp?id=<%=job.getId() %>" class="btn btn-sm bg-success text-white">Edit</a> 
							
							<a href="delete?id=<%=job.getId() %>" class="btn btn-sm bg-danger text-white">Delete</a>
						</div>
				  </div>
			   </div>
			   
				<%}
				
				%>
		    </div>
	     </div>
     </div>
</body>
</html>