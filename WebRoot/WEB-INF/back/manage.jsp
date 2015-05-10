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

<title>吊炸天新闻网管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=basePath%>back/css/back.css" />
</head>
<body>
	<div class="body">
		<div class="top">
			<jsp:include page="./top.jsp"></jsp:include>
		</div>
		<div class="left">
			<jsp:include page="./left.jsp"></jsp:include>
		</div>
		<div class="right">
			<iframe name="mainFrame" frameborder="0" width="100%" height="100%"
				id="mainFrame" title="mainFrame">
				<h1>欢迎来到吊炸天新闻网后台管理系统</h1>
			</iframe>
		</div>
	</div>

</body>
</html>
