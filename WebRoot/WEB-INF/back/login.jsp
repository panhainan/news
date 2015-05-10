<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>吊炸天新闻网后台管理系统登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="./js/login.js"></script>
<script type="text/javascript" src="./js/jquery-1.11.0.js"></script>
</head>

<body>
	<div class="background">
				<center>
				<h1>吊炸天新闻网后台管理系统登录</h1>
				<form action="back/manage?action=login" method="post">
					用户名：<input name="adminName" type="text" /><br /><br /> 密&nbsp;&nbsp;码：<input
						name="adminPass" type="password" /><br /><br /><input type="submit"
						value="确认登陆" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="重 置" />
				</form></center>
	</div>
</body>
</html>
