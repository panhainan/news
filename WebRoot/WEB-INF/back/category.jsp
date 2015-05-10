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

<title>新闻类别管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=basePath%>back/css/back.css" />
<script type="text/javascript">
function confirmDelete(){
	if(confirm("确定进行删除操作，此操作同时会删除所有该新闻类别下的新闻，且操作不可逆！请谨慎操作！")==true){
		return true;
	}else{
		return false;
	}
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
			<h1>新闻类别维护</h1>
			新闻类别列表 <br>
			<br>
			<table style="border: 1px silver solid;" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td width="60px">编号</td>
					<td width="600px">名称</td>
					<td>优先级</td>
					<td>操作</td>
				</tr>
				<%
					CategoryList[] listCategoryList = (CategoryList[]) request
							.getAttribute("listCategoryList");
					for (int i = 0; i < listCategoryList.length; i++) {
				%>
				<tr>
					<td><%=i + 1%></td>
					<td style="text-align: left;"><%=listCategoryList[i].getCategoryName()%></td>
					<td><%=listCategoryList[i].getCategoryPriority()%></td>
					<td><a
						href="back/manage/category?action=goupdate&id=<%=listCategoryList[i].getCategoryId()%>">更新</a>&nbsp;&nbsp;<a
						href="back/manage/category?action=delete&id=<%=listCategoryList[i].getCategoryId()%>"
						onclick="return confirmDelete();">删除</a></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>
</body>
</html>
