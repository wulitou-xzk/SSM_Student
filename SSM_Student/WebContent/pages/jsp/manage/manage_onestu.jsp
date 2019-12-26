<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>单个学生信息管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/static/css/total.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/static/css/option.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/add_active.js"></script>
</head>
<body>
	<c:if test="${not empty user }">
		<h1>单个学生信息管理</h1>
		<div class="manage">
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manager.jsp">管理员首页</a> | 
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manage_stu.jsp">返回</a><br>
		</div>
		<form action="${pageContext.request.contextPath }/studentmanage/add_updateStudent.do" method="POST">
			<input type="hidden" name="type" value="1"/>
			<input type="hidden" name="number" value="${student.getNumber() }"/>
			<table>
				<tr class="title">
					<td>姓名</td>
					<td>学号</td>
					<td>性别</td>
					<td>出生年月</td>
					<td>年级</td>
					<td>所在学院</td>
					<td>所学专业</td>
					<td>操作</td>
				</tr>
				<tr class="option">
					<td><input type="text" name="sname" value="${student.getSname() }" size="5"/></td>
					<td><input type="text" name="number" disabled="disabled" value="${student.getNumber() }" size="10"/></td>
					<td><input type="text" name="gender" value="${student.getGender() }" size="5"/></td>
					<td><input type="text" name="birth" value="<fmt:formatDate value="${student.getBirth() }" pattern="yyyy-MM-dd"/>" size="8"/></td>
					<td><input type="text" name="grader" value="${student.getGrader() }" size="5"/></td>
					<td><input type="text" name="college" value="${student.getCollege() }" size="15"/></td>
					<td><input type="text" name="major" value="${student.getMajor() }" size="20"/>${scmError }</td>
					<td><input type="button" value="查看课程" onclick="location='findSC.do?number=${student.getNumber() }&sid=${student.getId() }&sname=${student.getSname()}&type=0'">
					<input type="button" value="删除" onclick="location='delStudent.do?number=${student.getNumber() }&id=${student.getId()}'">
					<input type="submit" value="修改"/></td>
				</tr>
			</table>
		</form>
	</c:if>
	<c:if test="${empty user }">
		<span>
			请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a>
		</span>
	</c:if>
</body>
</html>