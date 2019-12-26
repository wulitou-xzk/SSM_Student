<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>我的课程</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/revise.css" rel="stylesheet" />
</head>
<body>
	<c:if test="${not empty user }">
	<c:if test="${not empty elective }">
		<font color="red">${elective }</font>
	</c:if>
	<c:if test="${empty revisedCourse }">
		<font color="red">${zeroCourse }</font>
	</c:if>
	<c:if test="${not empty revisedCourse }">
	<h2>已&nbsp;修&nbsp;课&nbsp;程&nbsp;界&nbsp;面</h2>
		<table>
			<tr>
				<th>课程名称</th>
				<th>授课教师</th>
				<th>开课学院</th>
				<th>成绩评定</th>
				<th></th>
			</tr>
		<c:forEach items="${revisedCourse }" var="sc">
		<form action="${pageContext.request.contextPath }/student/removeRevisedCourse.do" method="POST">
			<input type="hidden" name="cid" value="${sc.getCid() }"/>
			<input type="hidden" name="tid" value="${sc.getTid() }"/>
			<input type="hidden" name="sid" value="${sid }"/>
			<tr>
				<td><input type="text" name="cname" value="${sc.getCname() }" size="15" disabled="disabled"/></td>
				<td><input type="text" name="tname" value="${sc.getT_name() }" size="8" disabled="disabled"/></td>
				<td><input type="text" name="tname" value="${sc.getCollege() }" size="15" disabled="disabled"/></td>
				<c:if test="${sc.getScore() gt 0 }">
					<td><input type="text" name="score" value="${sc.getScore() }" size="8" disabled="disabled"/></td>
					<td><span>&nbsp;已测试</span></td>
				</c:if>
				<c:if test="${sc.getScore() eq 0 }">
					<td><span>未进行测试</span></td>
					<td>
						<c:if test="${cancel > 0}"><span>&nbsp;---</span></c:if>
						<c:if test="${cancel <= 0}">&nbsp;<input type="submit" value="取消"/></c:if>
					</td>
				</c:if>
			</tr>
			</form>
		</c:forEach>
		</table>
		<a href="${pageContext.request.contextPath }/pages/jsp/student/student.jsp" id="back">返回</a><br>
	</c:if>
	<font color="red">${elective }</font>
	</c:if>
	<c:if test="${empty user }">
		<span>请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a></span>
	</c:if>
</body>
</html>