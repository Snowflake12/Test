<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/register.css">
<script type="text/javascript" src="js/register.js" ></script>
<title>注册页面</title>
</head>
<body>

	<form action="Register" method="post" enctype="multipart/form-data" >
		<div>
			<p>用户名：</p>
			<input type="text" name="username" id="username" onblur="validate()">
			<span id="res" style="color:red"></span>
		</div>
		
		<div>
			<p>密码：</p>
			<input type="password" name="password">
		</div>
		
		<div>
			<p>年龄：</p>
			<input type="number" name="age">
		</div>
		
		<div>
			<p>电话号码：</p>
			<input type="text" name="phonenumber">
		</div>
		
		<div>
			<p>邮箱：</p>
			<input type="email" name="email">
		</div>
		
		<div>
			<p>头像：</p>
			<input type="file" name="photo">
		</div>
		
		<div>
			<button type="submit">注册</button>
		</div>
	</form>
</body>
</html>