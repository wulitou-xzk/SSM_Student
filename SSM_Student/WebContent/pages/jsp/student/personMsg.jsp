<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>个人信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/revise.css" />
</head>
<body>
	<c:if test="${not empty user }">
	<h2>个&nbsp;人&nbsp;信&nbsp;息&nbsp;界&nbsp;面</h2>
		<table>
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>学号</td>
				<th>出生日期</th>
				<th>所属学院</th>
				<th>所学专业</th>
				<th>年级</th>
			</tr>
			<tr>
				<td>${person.getSname() }</td>
				<td>${person.getGender() }</td>
				<td>${person.getNumber() }</td>
				<td><fmt:formatDate value="${person.getBirth() }" pattern="yyyy-MM-dd"/></td>
				<td>${person.getCollege() }</td>
				<td>${person.getMajor() }</td>
				<td>${person.getGrader() }</td>
			</tr>
		</table>
		<a href="${pageContext.request.contextPath }/pages/jsp/student/student.jsp" id="fanhui">返回</a><br>
	</c:if>
	<c:if test="${empty user }">
		<span>请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a></span>
	</c:if>
</body>
</html>