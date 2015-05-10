<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.phn.dto.*"%>
<link href="css/front-top.css" rel="stylesheet" type="text/css" />
<div class="body-top">
	<div class="logo"></div>
	<div class="nav">
		<ul>
			<li><a href="">首页</a>
			</li>
			<%
				CategoryList[] listCategoryList = (CategoryList[])request.getAttribute("listCategoryList");
				for(int i =0;i<listCategoryList.length;i++){
			%>
			<li><a href="category?action=look&id=<%=listCategoryList[i].getCategoryId() %>&name=<%=listCategoryList[i].getCategoryName() %>"><%=listCategoryList[i].getCategoryName() %></a>
			</li>
			<%
				}
			%>
		</ul>
	</div>
</div>