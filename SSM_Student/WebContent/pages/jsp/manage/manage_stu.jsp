<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>学生信息管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/static/css/total.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/static/css/search_tr1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/static/css/option.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pages/static/css/change_page.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/add_active.js"></script>
</head>
<body>
	<c:if test="${not empty user }">
		<h1>学生信息管理</h1>
		<div class="manage">
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manager.jsp">管理员首页</a>
		</div>
		<table>
			<tr class="none">
				<form action="${pageContext.request.contextPath }/studentmanage/findStudent.do" method="POST">
					<td colspan="7">
						<input type="text" name="number" size="8" placeholder="输入学生学号"/>
						<c:if test="${not empty numberError }">
							<font color="red">${numberError }</font>
						</c:if>
						<input type="submit" value="查找"/> |
					</td>
				</form>
				<td colspan="4">
					<input type="button" value="点击添加学生" onclick="location='${pageContext.request.contextPath }/pages/jsp/manage/add_student.jsp'"/>
				</td>
			</tr>
		<hr>
		<select id="college" name="colId" onchange="window.location=this.value">
			<option id="0">--选择学院--</option>
			<c:forEach items="${colleges }" var ="college">
				<option value="${pageContext.request.contextPath }/studentmanage/findAll.do?colId=${college.getId() }">${college.getCollege() }</option>
			</c:forEach>
		</select>
		<hr>
		<c:if test="${not empty students }">
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
			<c:forEach items="${students }" var="student">
				<form action="${pageContext.request.contextPath }/studentmanage/add_updateStudent.do" method="POST">
				<input type="hidden" name="type" value="1"/>
				<input type="hidden" name="number" value="${student.getNumber() }"/>
				<tr class="option">
					<td><input type="text" name="sname" value="${student.getSname() }"/></td>
					<td><input type="text" name="number" value="${student.getNumber() }" size="8" disabled="disabled"/></td>
					<td><input type="text" name="gender" value="${student.getGender()=='F' ? '女' : '男' }" size="5"/></td>
					<td><input type="text" name="birth" value="<fmt:formatDate value="${student.getBirth() }" pattern="yyyy-MM-dd"/>" size="8"/></td>
					<td><input type="text" name="grader" value="${student.getGrader() }" size="5"/></td>
					<td><input type="text" name="college" value="${student.getCollege() }"  size="15"/></td>
					<td><input type="text" name="major" value="${student.getMajor() }" size="14"/></td>
					<%-- <td><a href="delStudent.do?number=${student.getNumber() }&id=${student.getId()}">删除</a></td> --%>
					<td><input type="button" value="删除" onclick="location='${pageContext.request.contextPath }/studentmanage/delStudent.do?number=${student.getNumber() }&id=${student.getId()}'">|
					<input type="button" value="查看课程" onclick="location='${pageContext.request.contextPath }/studentmanage/findSC.do?sid=${student.getId() }&sname=${student.getSname()}&type=1'">|
					<input type="submit" value="修改"/></td>
				</tr>
				</form>
			</c:forEach>
		</table>
			
			<div class="bot-paper">
				<span>当前 第【${begin }】页  共 【${totalPage }】页 </span>
				<c:if test="${begin ne 1 }">
				 	<a href="${pageContext.request.contextPath }/studentmanage/findAll.do?colId=${colId }&begin=1">首页</a> | 
				</c:if>
				<c:if test="${begin gt 1 }">
					 <a href="${pageContext.request.contextPath }/studentmanage/findAll.do?colId=${colId }&begin=${begin-1}">&lt;&lt;上一页</a> | 
				</c:if>
				<c:if test="${begin lt totalPage }">
				 	 <a href="${pageContext.request.contextPath }/studentmanage/findAll.do?colId=${colId }&begin=${begin+1}">下一页&gt;&gt;</a>  
				</c:if>
				<c:if test="${begin ne totalPage }">
					<a href="${pageContext.request.contextPath }/studentmanage/findAll.do?colId=${colId }&begin=${totalPage}">尾页</a>
				</c:if>
				
				<form class="skip" action="${pageContext.request.contextPath }/studentmanage/findAll.docolId=${colId }&" method="POST">
					跳转到第<select id="begin" name="begin">
							<option id="0">--</option>
							<c:forEach var ="page" begin="${1 }" end="${totalPage }">
								<option id="${page }">${page }</option>
							</c:forEach>
					</select>页
					<input type="submit" value="跳转"/>
				</form>
			</div>
		</c:if>
	</c:if>
	<c:if test="${empty user }">
		<span>
			请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a>
		</span>
	</c:if>
</body>
</html>