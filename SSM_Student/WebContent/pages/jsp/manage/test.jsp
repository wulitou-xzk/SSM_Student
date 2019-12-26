<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="jquery/jquery-1.7.2.js"></script>
<!-- <script type="text/javascript">
      $(function(){
  	   $.ajax({
       url:'studentmanage/testcollege.do',
       data:{},
       type:'get',
       success:function(data){
           $(data).each(function(index){
               $("#college").append(
                  '<option value="'+data[index].id+'" id="'+index+'">'+data[index].college+'</option');
           		});
       		}
  		});
  
	$("#college").bind("change",function(){
    	var id=$(this).val();
    	//每点一次就清空一次
    	$("#major").html('<option value="0">----请选择省份----</option>');
    		 $.ajax({
             url:'studentmanage/testmajor.do',
             data:{"coId":id},
             type:'post',
             success:function(data){
                  $(data).each(function(index){
                     $("#major").append(
                        '<option value="'+data[index].id+'">'+data[index].major+'</option>'
                     		);
                 		 });
            		 }
            	});
			});
       });
</script> -->
<title>添加学生（联动测试）</title>
</head>
<body>
	
	<a href="${pageContext.request.contextPath }/pages/jsp/manage/manager.jsp">管理员首页</a> | 
	<a href="${pageContext.request.contextPath }/pages/jsp/manage/manage_stu.jsp">上一页</a><br>
	<form action="${pageContext.request.contextPath }/studentmanage/add_updateStudent.do" method="POST">
	<input type="hidden" name="type" value="0"/>
	<table>
		<tr>
		<td>所属学院：</td>
		<td>
			<select id="college" name="college">
				<option>
					<c:if test="${empty ccc }">--学院--</c:if>
					<c:if test="${not empty ccc }">${ccc }</c:if>
				</option>
				<c:forEach items="${colleges }" var ="college">
					<option value="${pageContext.request.contextPath }/studentmanage/findMajorByCollege.do?colId=${college.getId() }">${college.getCollege() }</option>
				</c:forEach>
			</select>
			${collError }
		</td>
	</tr>
	<tr>
		<td>所学专业：</td>
		<td>
			<select id="major" name="major">
			<option>
				<c:if test="${empty mmm }">--专业--</c:if>
				<c:if test="${not empty mmm }">${mmm }</c:if>
			</option>
				<c:forEach items="${majors }" var ="major">
					<option id="${major.getId() }">${major.getMajor() }</option>
				</c:forEach>
			</select>
			${majorError }
		</td>
	</tr>
	<tr>
		<td>学生姓名：</td>
		<td><input type="text" name="sname" value="${student.getSname() }" placeholder="请输入学生姓名"/>${nameError }</td>
	</tr>
	<tr>
		<td>学生学号：</td>
		<td><input type="text" name="number" value="${student.getNumber() }" placeholder="请输入学生学号"/>${numError}</td>
	</tr>
	<tr>
		<td>学生性别：</td>
		<td>
			<select id="gender" name="gender">
				<option id="0">--性别--</option>
				<option>男</option>
				<option>女</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>出生年月：</td>
		<td><input type="text" name="birth" value="${birth }" placeholder="请输入学生出生日期"/>${birthError}</td>
	</tr>
	<tr>
		<td>就读年级：</td>
		<td><input type="text" name="grader" value="${student.getGrader() }" placeholder="请输入学生年级"/>${graderError }</td>
	</tr>
	</table>
		<input type="submit" value="添加"/>
	</form>
</body>
</html>