<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>管理授课</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/total.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/add_active.js"></script>
</head>
<body>
	<h1>管理授课</h1>
	<c:if test="${not empty user }">
		<div class="manage">
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manager.jsp">管理员首页</a> | 
			<c:if test="${type eq '1' }">
				<a href="${pageContext.request.contextPath }/pages/jsp/manage/manage_teachers.jsp">上一页</a><br>
			</c:if>
			<c:if test="${type eq '0' }">
				<a href="${pageContext.request.contextPath }/teachermanage/findTeacher.do?tnumber=${tc[0].getT_number() }">上一页</a><br>
			</c:if>
		</div>
		<c:if test="${empty tc }">
			${tname } 老师暂未授课<br>
			<input type="button" value="添加授课" onclick="location='${pageContext.request.contextPath }/course/findByCollege.do?tid=${param.tid }&tname=${param.tname }'">
		</c:if>
		<c:if test="${not empty tc }">
			<font color="blue">${tc[0].getT_name() }</font> 老师的授课课程如下：<br><br>
			<table>
				<tr class="title">
					<td>课程名称</td>
					<td>上课人数</td>
					<td>最大容纳</td>
					<td>上课地点</td>
					<td>上课时间</td>
					<td>上课节数</td>
					<td>上课周数</td>
					<td>操作</td>
				</tr>
				
				<c:forEach items="${tc }" var="tc">
				<form action="${pageContext.request.contextPath }/teachermanage/addOrupdateTC.do" method="POST">
				<input type="hidden" name="tid" value="${tc.getTeachId() }"/>
				<input type="hidden" name="tname" value="${tc.getT_name() }"/>
				<input type="hidden" name="id" value="${tc.getId() }"/>
				<tr>
					<td><input type="text" name="cname" value="${tc.getCname() }" size="10"/></td>
					<td><input type="text" name="number" value="${tc.getNumber() }" size="5"/></td>
					<td><input type="text" name="maxnumber" value="${tc.getMaxnumber() }" size="5"/></td>
					<td><input type="text" name="place" value="${tc.getPlace() }" size="5"/></td>
					<td><input type="text" name="day" value="${tc.getDay() }" size="5"/></td>
					<td>
						<input type="text" name="on" value="${tc.getOn() }" size="1"/> - 
						<input type="text" name="off" value="${tc.getOff() }" size="1"/>节
					</td>
					<td>
						<input type="text" name="begin" value="${tc.getBegin() }" size="1"/> - 
						<input type="text" name="end" value="${tc.getEnd() }" size="1"/> 周
					</td>
					<td>
						<input type="button" value="取消授课" onclick="location='${pageContext.request.contextPath }/teachermanage/delTC.do?tname=${tc.getT_name() }&tid=${tc.getTeachId() }&id=${tc.getId()}'">
						<input type="button" value="添加授课" onclick="location='${pageContext.request.contextPath }/course/findByCollege.do?college=${tc.getCollege() }&tid=${param.tid }&tname=${param.tname }&type=${type }'">
						<input type="submit" value="修改"/>
					</td>
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