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

<title>新闻评论管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="back/css/back.css">
<script type="text/javascript">
function confirmDelete(){
	if(confirm("确定进行删除操作，操作不可逆！请谨慎操作！")==true){
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
			<h1>新闻评论维护</h1>
			新闻评论列表 <br>
			<br>
			<div style="min-height: 350px">
				<table style="border: 1px silver solid;" cellpadding="1px"
					cellspacing="1px">
					<tr>
						<td width="40px">编号</td>
						<td width="100px">用户IP</td>
						<td>用户地址</td>
						<td>内容</td>
						<td>时间</td>
						<td>操作</td>
					</tr>
					<%
						Map<String, Object> map = (Map<String, Object>)request.getAttribute("map");
						CommentLists[] listCommentList = (CommentLists[]) map.get("listCommentList");
						Page pageBean =(Page)map.get("pageBean");
						for (int i = 0; i < listCommentList.length; i++) {
					%>
					<tr>
						<td><%=i+1%></td>
						<td style="text-align: left;"><%=listCommentList[i].getCommentIP()%></td>
						<td><%=listCommentList[i].getCommentAddress()%></td>
						<td><%=listCommentList[i].getCommentContent()%></td>
						<td><%=listCommentList[i].getCommentPublishTime()%></td>
						<td><a
							href="back/manage/comment?action=delete&id=<%=listCommentList[i].getCommentId()%>&newsId=<%=listCommentList[i].getCommentNewsId()%>"
							onclick="return confirmDelete();">删除</a></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
			<table>
				<tr>
					<td>现在是第<%=pageBean.getCurrentPage()%>页</td>
					<td>共<%=pageBean.getTotalPages()%>页</td>
					<td>共<%=pageBean.getAllRecords()%>条</td>
					<td>
						<%
							if(!pageBean.isFirstPage()){
						%> <a href="back/manage/comment?action=list&page=1">首页</a> <%
 	}
 %>
					</td>
					<td>
						<%
							if(pageBean.isHasPreviousPage()){
						%> <a
						href="back/manage/comment?action=list&page=<%=pageBean.getCurrentPage()-1%>">上一页</a>
						<%
							}
						%>
					</td>
					<td>
						<%
							if(pageBean.isHasNextPage()){
						%> <a
						href="back/manage/comment?action=list&page=<%=pageBean.getCurrentPage()+1%>">下一页</a>
						<%
							}
						%>
					</td>
					<td>
						<%
							if(!pageBean.isFinalPage()){
						%> <a
						href="back/manage/comment?action=list&page=<%=pageBean.getTotalPages()%>">尾页</a>
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
