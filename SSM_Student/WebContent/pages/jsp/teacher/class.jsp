<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>班级查看</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/add.css" />
</head>
<body>
	<c:if test="${not empty user }">
		<h3>班&nbsp;级&nbsp;查&nbsp;看&nbsp;界&nbsp;面</h3>
		<c:if test="${not empty zeroStudent }">
			<font color="red">${zeroStudent }</font>
		</c:if>
		<fieldset>
			<legend>添加学生</legend>
			<form action="${pageContext.request.contextPath }/teacher/addStudent.do" method="POST">
			   <input type="hidden" name="cid" value="${cid }"/>
				学生姓名:
		        ${revised }
				<input type="text" name="sname" value="${sname }" placeholder="请输入学生姓名"/>
				<font color="red">${snameError }</font>
				<br />
				学生学号:
			    <input type="text" name="number" value="${number }" placeholder="请输入学生学号"/>
			    <font color="red">${numError }</font>
			    <br />
				<input type="submit" value="添加" id="addBtn"/>
				<font color="red">${fullError }</font>
			</form>
		</fieldset>
		<hr>
		<c:if test="${not empty students }">
			<table>
				<tr>
					<th>学生姓名</th>
					<th>学号</th>
					<th>学院</th>
					<th>专业</th>
					<th>性别</th>
					<th>年级</th>
					<th>成绩</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${students }" var="student">
				<form action="${pageContext.request.contextPath }/teacher/delOrupdate.do" method="POST">
					<input type="hidden" name="cid" value="${cid }"/>
					<input type="hidden" name="sid" value="${student.getId() }"/>
					<tr>
						<td><input disabled="disabled" value="${student.getSname() }" size="5"/></td>
						<td><input disabled="disabled" value="${student.getNumber() }" size="12"/></td>
						<td><input disabled="disabled" value="${student.getCollege() }"/></td>
						<td><input disabled="disabled" value="${student.getMajor() }"/></td>
						<td><input disabled="disabled" value="${student.getGender()=='F' ? '女' : '男' }" size="5"/></td>
						<td><input disabled="disabled" value="${student.getGrader() }" size="5"/></td>
						<td><input type="text" name="score" value="${student.getScore() }" size="5"/></td>
						<td><input type="submit" value="修改"/>
						<input type="button" value="删除" onclick="location='delOrupdate.do?cid=${cid}&sid=${student.getId() }'"/>
						</td>
					</tr>
				</form>
				</c:forEach>
			</table>
			
			<div id="page">
				当前第【${fbegin }】页  共 【${ftotalPage }】页 
				<c:if test="${fbegin ne 1 }">
				 	<a href="${pageContext.request.contextPath }/teacher/findStudents.do?cbegin=1">首页</a> | 
				</c:if>
				<c:if test="${fbegin gt 1 }">
					 <a href="${pageContext.request.contextPath }/teacher/findStudents.do?fbegin=${fbegin-1}">&lt;&lt;上一页</a> | 
				</c:if>
				<c:if test="${fbegin lt ftotalPage }">
				 	 <a href="${pageContext.request.contextPath }/teacher/findStudents.do?fbegin=${fbegin+1}">下一页&gt;&gt;</a>  
				</c:if>
				<c:if test="${fbegin ne ftotalPage }">
					<a href="${pageContext.request.contextPath }/teacher/findStudents.do?fbegin=${ftotalPage}">尾页</a> 
				</c:if>
				
				<form action="${pageContext.request.contextPath }/teacher/findStudents.do" method="POST">
					<input type="hidden" name="cid" value="${cid }"/>
					跳转到第<select id="fbegin" name="fbegin">
							<option id="0">--</option>
							<c:forEach var ="page" begin="${1 }" end="${ftotalPage }">
								<option id="${page }">${page }</option>
							</c:forEach>
					</select>页
					<input type="submit" value="跳转"/>
				</form>
			</div>
		</c:if>
	</c:if>
	<a href="${pageContext.request.contextPath }/pages/jsp/teacher/teach_course.jsp" id="back">上一页</a><br>
	<c:if test="${empty user }">
		<span>请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a></span>
	</c:if>
</body>
</html>