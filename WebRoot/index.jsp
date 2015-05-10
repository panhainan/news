<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="吊炸天新闻网">
<meta http-equiv="cache-control" content="吊炸天新闻网">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="吊炸天新闻网">
<meta http-equiv="description" content="吊炸天新闻网">
</head>

<%
response.sendRedirect("index");
%>

</html>