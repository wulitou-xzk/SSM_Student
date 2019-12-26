<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程管理</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/total.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/static/css/manage_course.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/change_page.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/add_active.js"></script>
</head>
<body>
	<c:if test="${not empty user }">
		<div class="manage">
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manager.jsp">管理员首页</a>
		</div>
		<table>
			<tr class="title">
				<td>课程名称</td>
				<td>开课学院</td>
				<td>操作</td>
			</tr>
			
			<form action="${pageContext.request.contextPath }/course/addOrupCourse.do" method="POST">
			<tr>
				<td><input type="text" name="cname"/></td>
				<td>
					<select id="college" name="college">
						<option id="0">--</option>
						<c:forEach items="${colleges }" var ="college">
							<option id="${college.getId() }">${college.getCollege() }</option>
						</c:forEach>
					</select>
				</td>
				<td colspan="4">
					<input type="submit" value="添加"/>
					<font color="red">${nullError }</font>
				</td>
			</tr>
			</form>
		</table>
		<hr>
		<table>
			<tr class="totswitch">
				<td colspan="4">
				<c:if test="${empty clock }">
					<input type="button" value="点击一键关闭全部"  onclick="location='onOrOffAll.do?clock=0'"/>
				</c:if>
				<c:if test="${not empty clock }">
					<input type="button" value="点击一键开启全部"  onclick="location='onOrOffAll.do?clock=1'"/>
				</c:if>
				</td>
			</tr>
			<c:forEach items="${courses }" var="course">
			<form action="${pageContext.request.contextPath }/course/addOrupCourse.do" method="POST">
			<tr class="totswitch"><input type="hidden" name="cid" value="${course.getId() }"/>
				<td><input type="text" name="cname" value="${course.getCname() }"/></td>
				<td><input type="text" name="college" value="${course.getCollege() }"/></td>
				<c:if test="${course.getClock() eq 0 }">
					<td colspan="7"><input type="button" value="已关闭，点击开启"  onclick="location='onOrOffOne.do?cid=${course.getId() }&clock=1'"/></td>
				</c:if>
				<c:if test="${course.getClock() eq 1 }">
					<td colspan="7"><input type="button" value="已开启，点击关闭"  onclick="location='onOrOffOne.do?cid=${course.getId() }&clock=0'"/></td>
				</c:if>
				<td><input type="submit" value="修改"/></td>
				<td><input type="button" value="删除"  onclick="location='delCourse.do?cid=${course.getId() }'"/></td>
			</tr>
			</form>
			</c:forEach>
		</table>
		
		当前第【${cbegin }】页  共 【${ctotalPage }】页 
		<c:if test="${cbegin ne 1 }">
		 	<a href="${pageContext.request.contextPath }/course/findAllCourses.do?cbegin=1">首页</a> | 
		</c:if>
		<c:if test="${cbegin gt 1 }">
			 <a href="${pageContext.request.contextPath }/course/findAllCourses.do?cbegin=${cbegin-1}">&lt;&lt;上一页</a> | 
		</c:if>
		<c:if test="${cbegin lt ctotalPage }">
		 	 <a href="${pageContext.request.contextPath }/course/findAllCourses.do?cbegin=${cbegin+1}">下一页&gt;&gt;</a>  
		</c:if>
		<c:if test="${cbegin ne ctotalPage }">
			<a href="${pageContext.request.contextPath }/course/findAllCourses.do?cbegin=${ctotalPage}">尾页</a> | 
		</c:if>
		
		<form action="${pageContext.request.contextPath }/course/findAllCourses.do" method="POST">
			跳转到第<select id="cbegin" name="cbegin">
					<option id="0">--</option>
					<c:forEach var ="page" begin="${1 }" end="${ctotalPage }">
						<option id="${page }">${page }</option>
					</c:forEach>
			</select>页
			<input type="submit" value="跳转"/>
		</form>
	</c:if>
	<c:if test="${empty user }">
		<span>
			请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a>
		</span>
	</c:if>
</body>
</html>