<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/login.css" />
</head>
<body>
	<div class="title">欢&nbsp;迎&nbsp;登&nbsp;录&nbsp;选&nbsp;课&nbsp;系&nbsp;统</div>
		<div class="mian">
			<form action="${pageContext.request.contextPath }/user/login.do" method="POST">
				<label for="name">用户名：</label>
				<input type="text" id="name" name="username" value="${username }" placeholder="请输入您的姓名"/>
				<br />
				<label for="myPassWord">密码：</label>
				<input type="password" id="myPassWord" name="password" placeholder="请输入登录密码 "/>
				<font color="red">${loginError }</font>
				<br />
				<input type="submit" value="登录" id="sub"/>
			</form>
		</div>
</body>
</html>