<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户信息</title>
</head>
<body>
	<form action="EditUserServlet" method="post">
		<input type="hidden" name="id" value="${editUser.id}">
		<input type="hidden" name="action" value="update">
		<div>
			<p>用户名：</p>
			<input type="text" name="username" value="${editUser.username }">
		</div>
		<div>
			<p>年龄：</p>
			<input type="number" name="age" value="${editUser.age }">
		</div>
		<div>
			<p>电话号码：</p>
			<input type="text" name="phonenumber" value="${editUser.phonenumber }">
		</div>
		<div>
			<p>邮箱：</p>
			<input type="email" name="email" value="${editUser.email }">
		</div>
		<div>
			<br>
			<button type="submit" >修改</button>
		</div>
	</form>
</body>
</html>