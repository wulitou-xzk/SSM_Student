<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>学生选课</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/revise.css" rel="stylesheet" />
</head>
<body>
	<c:if test="${not empty user }">
	<c:if test="${user.getType() eq -1 }">
		<span  id="notchoice">未到选课时间</span>
	</c:if>
	<c:if test="${user.getType() eq 0 }">
	<h2>学&nbsp;生&nbsp;选&nbsp;课&nbsp;界&nbsp;面</h2>
	<table>
	<tr>
		<th>课程名称</th>
		<th>授课教师</th>
		<th>已选人数</th>
		<th>上课人数</th>
		<th>上课地点</th>
		<th>上课节数</th>
		<th>授课周</th>
		<th>上课周</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${reviseTC }" var="course">
	<form action="${pageContext.request.contextPath }/student/electiveCourse.do" method="POST">
	<input type="hidden" name="cid" value="${course.getCourseId() }"/>
	<input type="hidden" name="tid" value="${course.getTeachId() }"/>
	<input type="hidden" name="name" value="${user.getUsername() }"/>
	<tr>
		<td><input type="text" name="cname" value="${course.getCname() }" size="15" disabled="disabled"/></td>
		<td><input type="text" name="tname" value="${course.getT_name() }" size="8" disabled="disabled"/></td>
		<td><input type="text" name="number" value="${course.getNumber() }" size="5" disabled="disabled"/></td>
		<td><input type="text" name="maxnumber" value="${course.getMaxnumber() }" size="5" disabled="disabled"/></td>
		<td><input type="text" name="place" value="${course.getPlace() }" size="10" disabled="disabled"/></td>
		<td><input type="text" name="time" value="${course.getTime() }" size="8" disabled="disabled"/></td>
		<td><input type="text" name="week" value="${course.getWeek() }" size="8" disabled="disabled"/></td>
		<td><input type="text" name="day" value="${course.getDay() }" size="5" disabled="disabled"/></td>
		<c:if test="${course.getNumber() lt course.getMaxnumber() }">
		<td><input type="submit" value="选择此课程"/></td>
		</c:if>
		<c:if test="${course.getNumber() ge course.getMaxnumber() }">
		<td><span>已满</span></td>
		</c:if>
	</tr>
	</form>
	</c:forEach>
	</table>
	
	<div class="pages">
		当前第【${rbegin }】页&nbsp;&nbsp;&nbsp;共【${rtotalPage }】页 
		<c:if test="${rbegin ne 1 }">
		 	<a href="${pageContext.request.contextPath }/course/reviceCourse.do?rbegin=1">首页</a> | 
		</c:if>
		<c:if test="${rbegin gt 1 }">
			 <a href="${pageContext.request.contextPath }/course/reviceCourse.do?rbegin=${rbegin-1}">&lt;&lt;上一页</a> | 
		</c:if>
		<c:if test="${rbegin lt rtotalPage }">
		 	 <a href="${pageContext.request.contextPath }/course/reviceCourse.do?rbegin=${rbegin+1}">下一页&gt;&gt;</a>  
		</c:if>
		<c:if test="${rbegin ne rtotalPage }">
			<a href="${pageContext.request.contextPath }/course/reviceCourse.do?rbegin=${rtotalPage}">尾页</a>
		</c:if>
		
		<form action="${pageContext.request.contextPath }/course/reviceCourse.do" method="POST">
			跳转到第<select id="rbegin" name="rbegin">
					<option id="0">--</option>
					<c:forEach var ="page" begin="${1 }" end="${rtotalPage }">
						<option id="${page }">${page }</option>
					</c:forEach>
			</select>页
			<input type="submit" value="跳转"/>
		</form>
		</c:if>
		</c:if>
	</div>
	<font color="red">${elective }</font>
	<c:if test="${not empty zeroRevise }">
		<font color="red">${zeroRevise }</font>
	</c:if>
	<a href="${pageContext.request.contextPath }/pages/jsp/student/student.jsp" id="back">返回</a>
	<c:if test="${empty user }">
		<span>请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a></span>
	</c:if>
</body>
</html>