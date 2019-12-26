<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>添加授课</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/total.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/add_active.js"></script>
</head>
<body>
	<c:if test="${not empty user }">
		<h1>添加授课</h1>
		<div class="manage">
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manager.jsp">管理员首页</a> | 
			<a href="${pageContext.request.contextPath }/teachermanage/findTC.do?tid=${tid }&tname=${tname }&type=${type}">返回</a><br>
		</div>
		<c:if test="${not empty nullCourse }">
			<font color="red">${nullCourse }</font>
		</c:if>
		<c:if test="${empty nullCourse }">
			<table>
				<tr class="title">
					<td>课程名称</td>
					<td>最大容纳</td>
					<td>上课地点</td>
					<td>上课时间</td>
					<td>上课节数</td>
					<td>上课周数</td>
					<td>操作</td>
				</tr>
				
				<form action="${pageContext.request.contextPath }/teachermanage/addOrupdateTC.do" method="POST">
					<input type="hidden" name="tid" value="${param.tid }"/>
					<input type="hidden" name="tname" value="${param.tname }"/>
					<tr>
						<td>
							<select id="cname" name="cname">
								<option id="0">--选择课程--</option>
								<c:forEach items="${courses }" var="course">
									<option id="${course.getId() }">${course.getCname() }</option>
								</c:forEach>
							</select>
						</td>
						<td><input type="text" name="maxnumber" size="5"/></td>
						<td><input type="text" name="place" size="5"/></td>
						<td>
							<select id="day" name="day">
								<option>--确认上课时间--</option>
								<option>星期一</option>
								<option>星期二</option>
								<option>星期三</option>
								<option>星期四</option>
								<option>星期五</option>
								<option>星期六</option>
								<option>星期七</option>
							</select>
						</td>
						<td>
							<select id="on" name="on">
								<option>--</option>
								<c:forEach var ="on" begin="${1 }" end="${8 }">
									<option>${on}</option>
								</c:forEach>
							</select>	- 
							<select id="off" name="off">
								<option>--</option>
								<c:forEach var ="off" begin="${2 }" end="${9 }">
									<option>${off}</option>
								</c:forEach>
							</select>节
						</td>
						<td>
							<select id="begin" name="begin">
								<option>--</option>
								<c:forEach var ="begin" begin="${1 }" end="${15 }">
									<option>${begin}</option>
								</c:forEach>
							</select> - 
							<select id="end" name="end">
								<option>--</option>
								<c:forEach var ="end" begin="${2 }" end="${16 }">
									<option>${end}</option>
								</c:forEach>
							</select> 周
						</td>
						<td>
							<input type="submit" value="添加"/>
						</td>
					</tr>
				</form>
			</table>
		</c:if>
	</c:if>
	<c:if test="${empty user }">
		<span>请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a></span>
	</c:if>
</body>
</html>