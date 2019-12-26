<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>学生界面</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/student.css" />
</head>
<body>
	<c:if test="${not empty user }">
	<header>
			<h3>学&nbsp;生&nbsp;选&nbsp;课&nbsp;系&nbsp;统</h3>
			<div id="name">
				<span>学号：</span>
				<div id="text">${user.getUsername() }</div>
			</div>
			<div id="back">
				<a href="${pageContext.request.contextPath }/user/logout.do">退出</a><br>
			</div>
		</header>
		<div id="body">
			<div class="left">
				<div class="title">学生选项</div>
				<div class="chose one">
					<a href="${pageContext.request.contextPath }/student/person.do">查看个人信息</a><br>
				</div>
				<div class="chose two">
					<a href="${pageContext.request.contextPath }/student/findRevised.do">已修课程</a><br>
				</div>
				<div class="chose three">
					<a href="${pageContext.request.contextPath }/course/reviceCourse.do" class="one">选择课程</a><br>
				</div>
				<div class="chose four">
					<a href="${pageContext.request.contextPath }/pages/jsp/changePwd.jsp">修改密码</a><br>
				</div>
			</div>
            <div class="right">
            	<div class="include">
            		<img src="${pageContext.request.contextPath }/pages/static/img/1.jpg" height="330px" width="850px" />
            	</div>
            </div>
		</div>
	</c:if>
	<c:if test="${empty user }">
		<span>请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a></span>
	</c:if>
</body>
</html>