<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>课程及成绩</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/static/css/total.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/add_active.js"></script>
</head>
<body>
	<c:if test="${not empty user }">
		<h1>课程及成绩</h1>
		<div class="manage">
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manager.jsp">管理员首页</a> | 
			<c:if test="${type eq 1 }">
			 	<a href="${pageContext.request.contextPath }/pages/jsp/manage/manage_stu.jsp">返回</a><br>
			</c:if>
			<c:if test="${type eq 0 }">
				<a href="${pageContext.request.contextPath }/studentmanage/findStudent.do?number=${number}">返回</a><br>
			</c:if>
		</div>
		<c:if test="${not empty nullRevise }">
			<font color="red">${nullRevise }</font>
		</c:if>
		<c:if test="${empty nullRevise }">
		<font color="blue">${sname }</font>同学所学的课程：<br>
		<!-- 课程管理操作 -->
		<table>
			<tr class="title">
				<td>课程名称</td>
				<td>开课学院</td>
				<td>授课教师</td>
				<td>成绩评定</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${sc }" var="sc">
			<form action="${pageContext.request.contextPath }/studentmanage/add_updateSC.do" method="POST">
				<input type="hidden" name="sId" value="${stuId }"/>
				<input type="hidden" name="id" value="${sc.getId()}"/>
				<input type="hidden" name="msg" value="all"/>
				<tr>
					<td><input type="text" name="cname" value="${sc.getCname() }" size="8"/></td>
					<td><input type="text" name="college" disabled="disabled" value="${sc.getCollege() }" size="15"/></td>
					<td><input type="text" name="tname" disabled="disabled" value="${sc.getT_name() }" size="8"/></td>
					<td><input type="text" name="score" value="${sc.getScore() }" size="5"/></td>
					<td colspan="3"><input type="button" value="删除" onclick="location='delSC.do?sid=${sc.getSid() }&cname=${sc.getCname() }'"/>|
					<input type="submit" value="修改"/></td>
				</tr>
			</form>
			</c:forEach>
		</table>
		</c:if>
	</c:if>
	<c:if test="${empty user }">
		<span>
			请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a>
		</span>
	</c:if>
</body>
</html>