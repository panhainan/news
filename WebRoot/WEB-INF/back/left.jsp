<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>
<link rel="SHORTCUT ICON" href="./image/logo.jpg">
<style type="text/css">
.title {
	height: 40px;
	line-height: 40px;
	font-size: 18px;
	font-weight: bold;
	float: left;
	width:100%;
	border-bottom: 1px solid silver;
}

.links {
	margin-top: 1px;
	border-bottom: 1px solid silver;
	width: 100%;
	float: left;
	font-size: 16px;
	font-family: "微软雅黑";
	text-align: center;
}

.links a {
	color: black;
	height: 30px;
	line-height: 30px;
}


</style>
</head>
<link rel="stylesheet" type="text/css" href="./css/manager_left.css">
<div class="title">新闻管理</div>
<div class="links"><a href="back/manage/news?action=goaddnews">新闻添加</a></div>
<div class="links">
	<a href="back/manage/news?action=listnews&page=1" >新闻维护</a>
</div>
<div class="title">新闻类别管理</div>
<div class="links">
	<a href="back/manage/category?action=goadd" >新闻类别添加</a>
</div>
<div class="links">
	<a href="back/manage/category?action=list" >新闻类别维护</a>
</div>
<div class="title">新闻评论管理</div>
<div class="links">
	<a href="back/manage/comment?action=list" >新闻评论维护</a>
</div>
<div class="title">其他管理</div>
<div class="links">
	<a href="./index.jsp">返 回 首 页</a>
</div>
