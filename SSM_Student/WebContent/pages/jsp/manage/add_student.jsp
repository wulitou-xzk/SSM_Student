<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>添加学生</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/static/css/total.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/static/css/add_StuTea.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/static/css/add_student.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/add_active.js"></script>
</head>
<body>
	<c:if test="${not empty user }">
		<h1>添加学生</h1>
		<div class="manage">
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manager.jsp">管理员首页</a> | 
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manage_stu.jsp">返回</a><br>
		</div>
		<form action="${pageContext.request.contextPath }/studentmanage/add_updateStudent.do" method="POST">
			<input type="hidden" name="type" value="0"/>
			<table class="left">
				<tr>
					<td>所属学院：</td>
					<td>
						<select id="college" name="college" onchange="window.location=this.value">
							<option>
								<c:if test="${empty ccc }">--学院--</c:if>
								<c:if test="${not empty ccc }">${ccc }</c:if>
							</option>
							<c:forEach items="${colleges }" var ="college">
								<option value="${pageContext.request.contextPath }/studentmanage/findMajorByCollege.do?colId=${college.getId() }">${college.getCollege() }</option>
							</c:forEach>
						</select>
						<font color="red">${collError }</font>
					</td>
				</tr>
				<tr>
					<td>所学专业：</td>
					<td>
						<select id="major" name="major">
						<option>
							<c:if test="${empty mmm }">--专业--</c:if>
							<c:if test="${not empty mmm }">${mmm }</c:if>
						</option>
							<c:forEach items="${majors }" var ="major">
								<option id="${major.getId() }">${major.getMajor() }</option>
							</c:forEach>
						</select>
						<font color="red">${majorError }</font>
					</td>
				</tr>
				<tr>
					<td>学生姓名：</td>
					<td>
						<input type="text" name="sname" value="${student.getSname() }" placeholder="请输入学生姓名"/>
						<font color="red">${nameError }</font>
					</td>
				</tr>
				<tr>
					<td>学生学号：</td>
					<td>
						<input type="text" name="number" value="${student.getNumber() }" placeholder="请输入学生学号"/>
						<font color="red">${numError}</font>
					</td>
				</tr>
				<tr>
					<td>学生性别：</td>
					<td>
						<select id="gender" name="gender">
							<option id="1" value="男">男</option>
							<option id="2" value="女">女</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>出生年月：</td>
					<td>
						<input type="text" name="birth" value="${birth }" placeholder="请输入学生出生日期"/>
						<font color="red">${birthError}</font>
					</td>
				</tr>
				<tr>
					<td>就读年级：</td>
					<td>
						<input type="text" name="grader" value="${student.getGrader() }" placeholder="请输入学生年级"/>
						<font color="red">${graderError }</font>
					</td>
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