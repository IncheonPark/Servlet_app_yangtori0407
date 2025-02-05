<%@page import="com.yang.app.departments.DepartmentDTO"%>
<%@page import="com.yang.app.departments.DepartmentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	DepartmentDTO departmentDTO = new DepartmentDTO();
	String department_id = request.getParameter("department_id");
	departmentDTO.setDepartment_id(Long.parseLong(department_id));
	DepartmentDAO departmentDAO = new DepartmentDAO();
	departmentDTO = departmentDAO.getDetail(departmentDTO);
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Department Detail Page</h1>
	<%if(departmentDTO != null) { %>
		<h3><%= departmentDTO.getDepartment_id() %></h3>
		<h3><%= departmentDTO.getDepartment_name() %></h3>
		<h3><%= departmentDTO.getManager_id() %></h3>
	<% } else { %>
		<h3>없는 부서입니다.</h3>
	<% } %>
</body>
</html>