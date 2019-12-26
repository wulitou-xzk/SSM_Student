<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>教师信息</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/total.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/change_page.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/pages/static/css/search_tr1.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pages/static/js/add_active.js"></script>
</head>
<body>
	<c:if test="${not empty user }">
		<h1>教师信息</h1>
		<div class="manage">
			<a href="${pageContext.request.contextPath }/pages/jsp/manage/manager.jsp">管理员首页</a><br><br><hr>
		</div>
		<table>
			<tr class="none">
				<form action="${pageContext.request.contextPath }/teachermanage/findTeacher.do" method="POST">
					<td colspan="3" class="search"><input type="text" name="tnumber" placeholder="输入教师工号"/>
					<font color="red" size="1">${tnumberError }</font>
					<input type="submit" value="查找 "/> |</td>
				</form>
				<td><input type="button" value="点击添加教师" onclick="location='${pageContext.request.contextPath }/pages/jsp/manage/add_teacher.jsp'"/></td>
			</tr>
			<select id="college" name="college" onchange="window.location=this.value">
				<option id="0">--选择学院--</option>
				<c:forEach items="${colleges }" var ="college">
					<option value="${pageContext.request.contextPath }/teachermanage/findAllTeacher.do?colId=${college.getId() }">${college.getCollege() }</option>
				</c:forEach>
			</select>
		<hr>
		<c:if test="${not empty teachers }">
			<tr class="title">
				<td>姓名</td>
				<td>工号</td>
				<td>性别</td>
				<td>联系方式</td>
				<td>所在学院</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${teachers }" var="teacher">
				<form action="${pageContext.request.contextPath }/teachermanage/addOrupdateTeacher.do" method="POST">
				<input type="hidden" name="tid" value="${teacher.getId() }"/>
				<input type="hidden" name="type" value="all"/>
				<tr>
					<td><input type="text" name="tname" value="${teacher.getT_name() }"/></td>
					<td><input type="text" name="tnumber" value="${teacher.getT_number() }" size="5"/></td>
					<td><input type="text" name="tgender" value="${teacher.getT_gender()=='F' ? '女' : '男' }" size="5"/></td>
					<td><input type="text" name="tphone" value="${teacher.getT_phone() }" size="8"/></td>
					<td><input type="text" name="college" value="${teacher.getCollege() }" size="15"/></td>
		 			<td>
			 			<input type="button" value="删除" onclick="location='${pageContext.request.contextPath }/teachermanage/delTeacher.do?tid=${teacher.getId()}'"> |
			 			<input type="button" value="查看教师课程" onclick="location='${pageContext.request.contextPath }/teachermanage/findTC.do?tid=${teacher.getId()}&tname=${teacher.getT_name() }&type=1'"> | 
						<input type="submit" value="修改"/>
					</td>
				</tr>
				</form>
			</c:forEach>
		</table>
		
		<div class="bot-paper">
			<span>当前第【${tbegin }】页  共 【${ttotalPage }】页 </span>
			<c:if test="${tbegin ne 1 }">
			 	<a href="${pageContext.request.contextPath }/teachermanage/findAllTeacher.do?colId=${colId }&tbegin=1">首页</a> | 
			</c:if>
			<c:if test="${tbegin gt 1 }">
				 <a href="${pageContext.request.contextPath }/teachermanage/findAllTeacher.do?colId=${colId }&tbegin=${tbegin-1}">&lt;&lt;上一页</a> | 
			</c:if>
			<c:if test="${tbegin lt ttotalPage }">
			 	 <a href="${pageContext.request.contextPath }/teachermanage/findAllTeacher.do?colId=${colId }&tbegin=${tbegin+1}">下一页&gt;&gt;</a>  
			</c:if>
			<c:if test="${tbegin ne ttotalPage }">
				<a href="${pageContext.request.contextPath }/teachermanage/findAllTeacher.do?colId=${colId }&tbegin=${ttotalPage}">尾页</a> 
			</c:if>
			
			<form action="${pageContext.request.contextPath }/teachermanage/findAllTeacher.do?colId=${colId }" method="POST">
				跳转到第<select id="tbegin" name="tbegin">
						<option id="0">--</option>
						<c:forEach var ="page" begin="${1 }" end="${ttotalPage }">
							<option id="${page }">${page }</option>
						</c:forEach>
				</select>页
				<input type="submit" value="跳转"/>
			</form>
		</div>
		</c:if>
	</c:if>
	<c:if test="${empty user }">
		<span>请先<a href="${pageContext.request.contextPath }/pages/jsp/login.jsp">登录</a></span>
	</c:if>
</body>
</html>