<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>单个教师信息</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/total.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/add_active.js"></script>
</head>
<body>
	<c:if test="${not empty user }">
		<h1>单个教师信息</h1>
		<div class="manage">
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manager.jsp">管理员首页</a> | 
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manage_teachers.jsp">返回</a><br>
		</div>
		<table>
			<tr class="title">
				<td>姓名</td>
				<td>工号</td>
				<td>性别</td>
				<td>联系方式</td>
				<td>所在学院</td>
				<td>操作</td>
			</tr>
			<form action="${pageContext.request.contextPath }/teachermanage/addOrupdateTeacher.do" method="POST">
				<input type="hidden" name="tid" value="${teacher.getId() }"/>
				<input type="hidden" name="type" value="change"/>
				<tr>
					<td><input type="text" name="tname" value="${teacher.getT_name() }"/></td>
					<td><input type="text" name="tnumber" value="${teacher.getT_number() }" size="5"/></td>
					<td><input type="text" name="tgender" value="${teacher.getT_gender() }" size="5"/></td>
					<td><input type="text" name="tphone" value="${teacher.getT_phone() }" size="8"/></td>
					<td><input type="text" name="college" value="${teacher.getCollege() }" size="15"/></td>
		 			<td>
			 			<input type="button" value="删除" onclick="location='${pageContext.request.contextPath }/teachermanage/delTeacher.do?tid=${teacher.getId()}'"> |
			 			<input type="button" value="查看教师课程" onclick="location='${pageContext.request.contextPath }/teachermanage/findTC.do?tid=${teacher.getId()}&tname=${teacher.getT_name() }&type=0'"> | 
						<input type="submit" value="修改"/>
					</td>
				</tr>
			</form>
		</table>
	</c:if>
	<c:if test="${empty user }">
		<span>
			请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a>
		</span>
	</c:if>
</body>
</html>