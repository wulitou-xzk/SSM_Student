<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/changePwd.css" />
</head>
<body>
	<c:if test="${not empty user }">
	<div class="title">修&nbsp;改&nbsp;密&nbsp;码&nbsp;界&nbsp;面</div>
		<div class="mian">
			<form action="${pageContext.request.contextPath }/user/changePWD.do" method="POST">
				<label for="name">用户名：</label>
				<input type="text" name="username" value="${user.getUsername() }" placeholder="请输入登录名"/><br>
				<font color="red">${usernameError }</font>
				<br />
				<label for="myPassWord">旧密码：</label>
				<input type="password" id="myPassWord" name="oldPwd" placeholder="请输入登录密码 "/><br>
				<font color="red">${pwdError1 }</font>
				<br />
				<label>新密码：</label>
				<input type="password" name="newPwd1" placeholder="请输入新密码 "/><br>
				<font color="red">${pwdError2 }</font>
				<br />
				<label>确认新密码：</label>
				<input type="password" name="newPwd2" placeholder="请确认新密码 " id="newPwd2"/>
				<input type="submit" value="修改" id="sub"/>
			</form>
		</div>
	</c:if>
	<c:if test="${empty user }">
		<span>请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a></span>
	</c:if>
</body>
</html>