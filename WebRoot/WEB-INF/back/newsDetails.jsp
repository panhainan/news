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
<link rel="stylesheet" href="<%=basePath%>back/css/back.css" />
</head>

<body class="newsDetails-body">
	<div class="body">
		<div class="top">
			<jsp:include page="./top.jsp"></jsp:include>
		</div>
		<div class="left">
			<jsp:include page="./left.jsp"></jsp:include>
		</div>
		<div class="right">
			<div class="newsDetails-body-middle">
				<div class="newsDetails-body-middle-content">
					<h1><%=newsDetails.getNewsTitle()%></h1>
					<p>
						发表时间：<%=newsDetails.getNewsPublishTime()%>&nbsp; 作者：<a
							href="<%=newsDetails.getNewsAuthorSite()%>" target="_blank"><%=newsDetails.getNewsAuthor()%></a>&nbsp;
						阅读次数：<%=newsDetails.getNewsHitCount()%>&nbsp; 评论条数：<%=newsDetails.getNewsCommentCount()%>
					</p>
					<div>
						<%=newsDetails.getNewsContent()%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
