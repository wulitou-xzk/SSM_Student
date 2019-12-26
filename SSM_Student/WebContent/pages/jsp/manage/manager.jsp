<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>管理员</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/manage.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/jquery-1.11.1.js"></script>
</head>
<body>
	<header>
		<span>管&nbsp;理&nbsp;员&nbsp;界&nbsp;面</span>
		<div id="name">
			管理员：
			<div class="manageName">${user.getUsername() }</div>
		</div>
		<div class="time">
			<p>北京时间：</p>
			<div class="timeBox"></div>
		</div>
	</header>
	<c:if test="${not empty user }">
		<div class="mian">
			<div class="left">
				<div class="title">管理员权限</div>
				<div class="chose one">
					<a href="${pageContext.request.contextPath }/teachermanage/findAllTeacher.do">管理教师信息</a>
				</div>
				<div class="chose two">
					<a href="${pageContext.request.contextPath }/studentmanage/findAll.do">管理学生信息</a>
				</div>
				<div class="chose three">
					<a href="${pageContext.request.contextPath }/course/findAllCourses.do">管理课程信息</a>
				</div>
				<div class="chose four">
					<a href="${pageContext.request.contextPath }/pages/jsp/changePwd.jsp">修改密码</a>
				</div>
				<div class="chose five">
					<a href="${pageContext.request.contextPath }/user/logout.do">退出</a><br>
				</div>
			</div>
			<div class="right">
				<h3>系统功能介绍</h3>
				<span>
					1.教师管理功能：添加新增教师的基本信息、删除已辞职 / 辞退的教师信息、根据工号查找教师；
					     查看教师授课课程、为教师添加授课课程、取消教师授课课程 、更新教师授课课程.
					<br />
					2.学生管理功能：添加新入学的学生的信息、删除已毕业的学生信息、更新学生的信息、根据学生学号查询学生信息；
					  查看、修改、删除学生选择的课程.
					<br />
					3.课程管理功能：添加新增课程的信息、删除被取消的课程、更新课程信息.
				</span>
			</div>
		</div>
	</c:if>
	<c:if test="${empty user }">
		<span>请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a></span>
	</c:if>
	<div class="footer"></div>
	<div id='divname'></div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/time.js"></script>
</body>
</html>