<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>查看授课课程</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/revise.css" />
</head>
<body>
	<c:if test="${not empty user }">
		<h2>查&nbsp;看&nbsp;授&nbsp;课&nbsp;课&nbsp;程</h2>
		<table>
			<tr>
				<td>课程名称</td>
				<td>班级人数</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${tccourses }" var="course">
				<tr>
					<td><input type="text" name="cname" value="${course.getCname() }" disabled="disabled"/></td>
					<td><input type="text" name="number" value="${course.getNumber() }" disabled="disabled"/></td>
					<td><input type="button" value="查看班级" onclick="location='findStudents.do?cid=${course.getCourseId()}'"/></td>
				</tr>
			</c:forEach>
		</table>
		<a href="${pageContext.request.contextPath }/pages/jsp/teacher/teacher.jsp" id="back">返回</a><br>
	</c:if>
	<c:if test="${empty user }">
		<span>请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a></span>
	</c:if>
</body>
</html>