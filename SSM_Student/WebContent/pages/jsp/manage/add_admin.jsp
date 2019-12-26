<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>添加管理员</title>
</head>
<body>
	<c:if test="${not empty user }">
	<form action="${pageContext.request.contextPath }/user/addOrupUser.do" method="POST">
		<table>
			<tr>
				<td>用户名：</td>
				<td>
					<input type="text" name="username" value="${username }" placeholder="请输入登录名"/>
					<font color="red">${loginError }</font>
				</td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" placeholder="请输入登录密码 "/></td><br>
			</tr>
		</table>
		<input type="submit" value="添加"/>
	</form>
	</c:if>
	<c:if test="${empty user }">
		<span>
			请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a>
		</span>
	</c:if>
</body>
</html>