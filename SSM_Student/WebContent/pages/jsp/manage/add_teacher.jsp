<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>添加教师</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/total.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/static/css/add_StuTea.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/add_active.js"></script>
</head>
<body>
	<c:if test="${not empty user }">
		<h1>添加教师</h1>
		<div class="manage">
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manager.jsp">管理员首页</a> | 
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manage_teachers.jsp">返回</a><br>
		</div>
		<form action="${pageContext.request.contextPath }/teachermanage/addOrupdateTeacher.do" method="POST">
			<input type="hidden" name="type" value="add"/>
			<table id="box" class="left">
				<tr>
					<td>姓名：</td>
					<td>
						<input type="text" name="tname" value="${tname }" placeholder="请输入教师姓名"/>
						<font color="red" size="1">${tnameError }</font>
					</td>
				</tr>
				<tr>
					<td>性别：</td>
					<td>
						<select id="tgender" name="tgender">
							<option id="1" value="男">男</option>
							<option id="2" value="女">女</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>工号：</td>
					<td>
						<input type="text" name="tnumber" value="${tnumber }" placeholder="请输入教师工号"/>
						<font color="red" size="1">${tnumberError }</font>
					</td>
				</tr>
				<tr>
					<td>联系方式：</td>
					<td>
						<input type="text" name="tphone" value="${tphone }" placeholder="请输入教师联系方式"/>
						<font color="red" size="1">${tphoneError }</font>
					</td>
				</tr>
					<td>学院：</td>
					<td>
						<select id="college" name="college">
							<option id="0">--选择学院--</option>
							<c:forEach items="${colleges }" var ="college">
									<option>${college.getCollege() }</option>
								</c:forEach>
						</select>
						<font color="red" size="1">${collegeError }</font>
					</td>
				</tr>
			</table>
			<input class="inp" type="submit" value="添加"/>
		</form>
	</c:if>
	<c:if test="${empty user }">
		<span>
			请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a>
		</span>
	</c:if>
</body>
</html>