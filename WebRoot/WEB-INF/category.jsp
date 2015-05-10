<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.phn.dto.*"%>
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
<%
	Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");
	String newsCategoryName = (String)map.get("newsCategoryName");
	int newsCategoryId = (Integer)map.get("newsCategoryId");
%>
<title><%=newsCategoryName%></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/category.css" rel="stylesheet" type="text/css" />
</head>

<body class="category-body">
	<jsp:include page="front-top.jsp"></jsp:include>
	<div class="category-body-middle">
		<div class="category-body-middle-top">
			当前位置：<a href="">主页</a>&gt;<%=newsCategoryName%>
		</div>
		<div class="category-body-middle-content">
			<ul>
				<%
					NewsList[] listNewsList  = (NewsList[])map.get("listNewsList");
									Page pageBean = (Page)map.get("pageBean");
									Object cate = map.get("newsCategoryId");
									int categoryId;
									if(null==cate){
										categoryId = 0;
									}else{
										categoryId = (Integer)cate;
									}
									for (int i = 0; i < listNewsList.length; i++) {
				%>
				<a href="news?action=look&id=<%=listNewsList[i].getNewsId()%>"><li>[<%=listNewsList[i].getNewsPublishTime()%>]<%=listNewsList[i].getNewsTitle()%></li>
				</a>
				<%
					}
				%>
			</ul>

		</div>
		<div class="category-body-middle-bottom">
			<table style="font-size: 14px;text-align: center;padding-left: 100px;">
				<tr>
					<td width="100px">现在是第<%=pageBean.getCurrentPage()%>页</td>
					<td width="80px">共<%=pageBean.getTotalPages()%>页</td>
					<td width="80px">共<%=pageBean.getAllRecords()%>条</td>
					<td  width="60px">
						<%
							if(!pageBean.isFirstPage()){
						%> <a
						href="category?action=look&page=1&id=<%=newsCategoryId%>&name=<%=newsCategoryName%>">首页</a>
						<%
							}
						%>
					</td>
					<td  width="80px">
						<%
							if(pageBean.isHasPreviousPage()){
						%> <a
						href="category?action=look&page=<%=pageBean.getCurrentPage()-1%>&id=<%=newsCategoryId%>&name=<%=newsCategoryName%>">上一页</a>
						<%
							}
						%>
					</td>
					<td  width="80px">
						<%
							if(pageBean.isHasNextPage()){
						%> <a
						href="category?action=look&page=<%=pageBean.getCurrentPage()+1%>&id=<%=newsCategoryId%>&name=<%=newsCategoryName%>">下一页</a>
						<%
							}
						%>
					</td>
					<td  width="60px">
						<%
							if(!pageBean.isFinalPage()){
						%> <a
						href="category?action=look&page=<%=pageBean.getTotalPages()%>&id=<%=newsCategoryId%>&name=<%=newsCategoryName%>">尾页</a>
						<%
							}
						%>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="category-body-right">
		<div class="category-body-right-title">热门新闻</div>
		<div class="category-body-right-list">
			<%
				NewsList[] newsList = (NewsList[]) request.getAttribute("newsList");
			%>
			<ul>
				<%
					for (int i = 0; i < newsList.length; i++) {
				%>
				<li><a href="news?action=look&id=<%=newsList[i].getNewsId()%>"><span
						style="font-size: 12px;"><%=newsList[i].getNewsPublishTime()%></span><%=newsList[i].getNewsTitle()%></a>
				</li>
				<%
					}
				%>
			</ul>
		</div>
	</div>
	<jsp:include page="front-bottom.jsp"></jsp:include>
</body>
</html>
