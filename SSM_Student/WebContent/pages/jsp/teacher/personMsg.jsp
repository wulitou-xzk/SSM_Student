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
	<h2>老&nbsp;师&nbsp;个&nbsp;人&nbsp;信&nbsp;息</h2>
	<table>
		<tr>
			<th>姓名</td>
			<th>性别</th>
			<th>工号</th>
			<th>联系方式</td>
			<th>所属学院</td>
		</tr>
		<tr>
			<td>${person.getT_name() }</td>
			<td>${person.getT_gender() }</td>
			<td>${person.getT_number() }</td>
			<td>${person.getT_phone() }</td>
			<td>${person.getCollege() }</td>
		</tr>
	</table>
	<a href="${pageContext.request.contextPath }/pages/jsp/teacher/teacher.jsp" id="fanhui">返回</a><br>
	</c:if>
	<c:if test="${empty user }">
		<span>请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a></span>
	</c:if>
</body>
</html>