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
	NewsDetails newsDetails = (NewsDetails) request
			.getAttribute("newsDetails");
%>
<title><%=newsDetails.getNewsTitle()%></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/newsDetails.css" rel="stylesheet" type="text/css" />

</head>

<body class="newsDetails-body">
	<jsp:include page="front-top.jsp"></jsp:include>
	<div class="newsDetails-body-middle">
		<div class="newsDetails-body-middle-newsDetails">
			<div class="newsDetails-body-middle-newsDetails-top">
				当前位置：<a href="">主页</a>&gt;<a
					href="category?action=look&id=<%=newsDetails.getNewsCategoryId()%>&name=<%=newsDetails.getNewsCategoryName()%>"><%=newsDetails.getNewsCategoryName()%></a>&gt;<%=newsDetails.getNewsTitle()%>
			</div>
			<div class="newsDetails-body-middle-newsDetails-content">
				<h1><%=newsDetails.getNewsTitle()%></h1>
				<p>
					发表时间：<%=newsDetails.getNewsPublishTime()%>&nbsp; 作者：<a
						href="<%=newsDetails.getNewsAuthorSite()%>" target="_blank"><%=newsDetails.getNewsAuthor()%></a>&nbsp;
					新闻类别：<a
						href="category?action=look&id=<%=newsDetails.getNewsCategoryId()%>"><%=newsDetails.getNewsCategoryName()%></a>&nbsp;
					阅读次数：<%=newsDetails.getNewsHitCount()%>&nbsp; 评论条数：<%=newsDetails.getNewsCommentCount()%>
				</p>
				<div>
					<%=newsDetails.getNewsContent()%>
				</div>
			</div>
		</div>

		<div class="newsDetails-body-middle-comment">
			<br> <h2>评论</h2>
			<form action="news" method="post">
				<input type="hidden" value="<%=newsDetails.getNewsId()%>"
					name="newsId"> <input type="hidden" value="comment"
					name="action">
				<textarea rows="4" cols="82" style="resize:none;"
					name="commentContent"></textarea>
				<br> <br> <input type="submit"  style="width: 100px;height: 30px;"  value="发表评论"><br> <br> 
			</form>
		</div>
		<div class="newsDetails-body-middle-comments">
			<h2>评论列表</h2>
			<%
				CommentLists[] commentLists = (CommentLists[]) request
						.getAttribute("commentLists");
				for (int i = 0; i < commentLists.length; i++) {
			%>
			<div class="newsDetails-body-middle-comments-list">
				<div class="newsDetails-body-middle-comments-list-title">
					<div style="width: 500px;float: left;padding-left: 20px;" ><%=commentLists[i].getCommentAddress()%></div>
					<div style="width: 140px;float: left;" ><%=commentLists[i].getCommentPublishTime()%></div>
				</div>
				<div class="newsDetails-body-middle-comments-list-content"><%=commentLists[i].getCommentContent()%>
				</div>
			</div>
			<%
				}
			%>
		</div>
	</div>
	<div class="newsDetails-body-right">
		<div class="newsDetails-body-right-title">热门新闻</div>
		<div class="newsDetails-body-right-list">
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
