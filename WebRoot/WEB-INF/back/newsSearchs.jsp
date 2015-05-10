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

<title>查找结果</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=basePath%>back/css/back.css" />
<script type="text/javascript">
	function confirmDelete(){
		if(confirm("确定进行删除操作，此操作同时会删除所有该新闻的评论，且操作不可逆！请谨慎操作！")==true){
			return true;
		}else{
			return false;
		}
	}
	function setCategoryName(){
		var obj = document.getElementById("newsCategoryId"); //定位id
		var index = obj.selectedIndex; // 选中索引
		var text = obj.options[index].text; // 选中文本
		document.getElementById("newsCategoryName").value=text;
		return true;
	}
	function check(){
		if(setCategoryName()==true){
			return true;
		}
		return false;
	}
</script>
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
			<%
				Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");
			String newsTitle= (String)map.get("newsTitle");
			String newsCategoryName= (String)map.get("newsCategoryName");
			%>
			<h1>筛选结果</h1>
			筛选类别：<%=newsCategoryName%><br>
			<br> 筛选标题：<%=newsTitle%>
			<br>
			<br>
			<form action="back/manage/news" method="post"
				onsubmit="return check();">
				<input type="hidden" value="search" name="action"> 类别： <select
					name="newsCategoryId" id="newsCategoryId"
					style="min-height:24px;min-width: 100px;line-height: 24px;font-size: 16px;">
					<option
						style="min-height:24px;min-width: 100px;line-height: 24px;font-size: 16px;"
						value="0">-请选择-</option>
					<%
						CategoryList[] listCategoryList = (CategoryList[]) request
								.getAttribute("listCategoryList");
						for (int i = 0; i < listCategoryList.length; i++) {
					%>
					<option
						style="min-height:24px;min-width: 100px;line-height: 24px;font-size: 16px;"
						value="<%=listCategoryList[i].getCategoryId()%>"><%=listCategoryList[i].getCategoryName()%></option>
					<%
						}
					%>
				</select> <input type="hidden" id="newsCategoryName" name="newsCategoryName">
				<input type="hidden" value="1" name="page"> 标题：<input
					style="min-height:24px;width: 300px;line-height: 24px;font-size: 16px;"
					type="text" name="newsTitle"> <input type="submit"
					value="筛选">
			</form>
			<div style="min-height: 250px">
			<table>
				<tr>
					<td width="60px">编号</td>
					<td width="600px">标题</td>
					<td>发布时间</td>
					<td>操作</td>
				</tr>
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
				<tr>
					<td><%=(pageBean.getCurrentPage()-1)*pageBean.getPageSize()+i+1%></td>
					<td style="text-align: left;"><%=listNewsList[i].getNewsTitle()%></td>
					<td><%=listNewsList[i].getNewsPublishTime()%></td>
					<td><a
						href="back/manage/news?action=goupdate&id=<%=listNewsList[i].getNewsId()%>">更新</a>&nbsp;&nbsp;<a
						href="back/manage/news?action=delete&id=<%=listNewsList[i].getNewsId()%>"
						onclick="return confirmDelete();">删除</a></td>
				</tr>
				<%
					}
				%>
			</table>
			</div>
			<br>
			<table>
				<tr>
					<td>现在是第<%=pageBean.getCurrentPage()%>页</td>
					<td>共<%=pageBean.getTotalPages()%>页</td>
					<td>共<%=pageBean.getAllRecords()%>条</td>
					<td>
						<%
							if(!pageBean.isFirstPage()){
						%> <a
						href="back/manage/news?action=search&page=1&newsCategoryId=<%=categoryId%>&newsCategoryName=<%=newsCategoryName%>&newsTitle=<%=newsTitle%>">首页</a>
						<%
							}
						%>
					</td>
					<td>
						<%
							if(pageBean.isHasPreviousPage()){
						%> <a
						href="back/manage/news?action=search&page=<%=pageBean.getCurrentPage()-1%>&newsCategoryId=<%=categoryId%>&newsCategoryName=<%=newsCategoryName%>&newsTitle=<%=newsTitle%>">上一页</a>
						<%
							}
						%>
					</td>
					<td>
						<%
							if(pageBean.isHasNextPage()){
						%> <a
						href="back/manage/news?action=search&page=<%=pageBean.getCurrentPage()+1%>&newsCategoryId=<%=categoryId%>&newsCategoryName=<%=newsCategoryName%>&newsTitle=<%=newsTitle%>">下一页</a>
						<%
							}
						%>
					</td>
					<td>
						<%
							if(!pageBean.isFinalPage()){
						%> <a
						href="back/manage/news?action=search&page=<%=pageBean.getTotalPages()%>&newsCategoryId=<%=categoryId%>&newsCategoryName=<%=newsCategoryName%>&newsTitle=<%=newsTitle%>">尾页</a>
						<%
							}
						%>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>