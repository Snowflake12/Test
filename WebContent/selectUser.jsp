<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/deleteUser.js" ></script>
<title>查找用户</title>
<style type="text/css">
	.page{
		text-align:center;
		margin-top: 50px;
	}
	.page a,.page span{
		text-decoration:none;
		border:1px solid #f9d52b;
		padding:5px 7px;
		color:#767675;
		cursor:pointer;
	}
	.page a:hover,.page span:hover{
		color:red;
	}
</style>
</head>
<body>
	<form action="UserManageServlet" method="post">
		<div>
			姓名：<input type="text" name="username" >
			年龄：<input type ="number" name="age" >
			每页显示
			<select name="pageSize">
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="15">15</option>
			</select>
			条
			<button type="submit" >查询</button>
		</div>
		<div id="data" >
		<table>
			<tr>
				<th>ID</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>电话号码</th>
				<th>邮箱</th>
				<th>编辑</th>
			</tr>
			<!--  
			<tr>
				<th>张三</th>
				<th>23</th>
				<th>123134</th>
				<th>123@qq.com</th>
			</tr>
			<tr>
				<th>李四</th>
				<th>24</th>
				<th>123114214</th>
				<th>123@qq.com</th>
			</tr>
			<tr>
				<th>王五</th>
				<th>25</th>
				<th>1231244</th>
				<th>123@qq.com</th>
			</tr>
			-->
			<!--
				forEach:遍历循环
				items属性：进行循环的数据
				var属性：当前遍历到的元素 
			 -->
			<c:forEach items="${ resultList}" var="user">
				<tr>
					<td>
						<input type="checkbox"  value="${user.id }" name="chk">
					</td>
					<td>${user.username}</td>
					<td>${user.age}</td>
					<td>${user.phonenumber}</td>
					<td>${user.email}</td>
					<td><a href="EditUserServlet?userId=${user.id}&action=edit" >编辑</a></td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<div>
		
			<c:forEach begin="1" end="${pagecount }" var="p" >
				<c:if test="${p==pageNum}">
					${p}
				</c:if>
				<c:if test="${p!=pageNum}">
					<a href="UserManageServlet?pageNum=${p}" >${p}</a>
				</c:if>
				&nbsp;&nbsp;
			</c:forEach>
			<button type="button" onclick="deleteUser()" >删除</button>
			<button type="button" onclick="exportUser()" >导出Excel</button>
		</div>
	</form>
</body>
</html>