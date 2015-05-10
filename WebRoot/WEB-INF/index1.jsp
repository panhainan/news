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

<title>吊炸天新闻网</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/pictrueRotation.css" rel="stylesheet" type="text/css" />
<SCRIPT src="js/js.js" type=text/javascript></SCRIPT>
</head>

<body class="index-body">
	<jsp:include page="front-top.jsp"></jsp:include>
	<div class="index-body-middle">
		<div class="news-list">
			<div class="news-nocategory">
				<div class="news-picture">
					<%
						List<PicNews> listPicNews  = (List<PicNews>) request.getAttribute("listPicNews");
												if(listPicNews!=null){
					%>
					<div id="ifocus">
						<div id="ifocus_btn">
							<ul>
								<li class="current"><img
									src="<%=listPicNews.get(0).getNewsPictureSite()%>" alt="" />
								</li>
								<%
									for (int i = 1; i < listPicNews.size(); i++) {
								%>
								<li class="normal"><img
									src="<%=listPicNews.get(i).getNewsPictureSite()%>" alt="" />
								</li>
								<%
									}
								%>
							</ul>
						</div>
						<div id="ifocus_pic">
							<div id="ifocus_piclist" style="left:0; top:0;">
								<ul>
									<%
										for (int i = 0; i < listPicNews.size(); i++) {
									%>
									<li><a
										href="news?action=look&id=<%=listPicNews.get(i).getNewsId()%>"><img
											src="<%=listPicNews.get(i).getNewsPictureSite()%>" alt="" />
									</a>
									</li>
									<%
										}
									%>
								</ul>
							</div>
							<div id="ifocus_opdiv"></div>
							<div id="ifocus_tx">
								<ul>
									<li class="current"><%=listPicNews.get(0).getNewsTitle()%></li>
									<%
										for (int i = 1; i < listPicNews.size(); i++) {
									%>
									<li class="normal"><%=listPicNews.get(i).getNewsTitle()%></li>
									<%
										}
									%>
								</ul>
							</div>
						</div>

					</div>
					<%
						}
					%>

				</div>
				<div class="news-topline">
					<div class="news-topline-title">今日头条</div>
					<div>
						<%
							TopLineNews topLineNews = (TopLineNews) request.getAttribute("topLineNews");
							if(null!=topLineNews){
						%>
						<a href="news?action=look&id=<%=topLineNews.getNewsId()%>"><font
							color="red" style="font-weight: bold;font-size: 16px;"><%=topLineNews.getNewsTitle()%></font>
						</a> <br>
						<p style="text-indent:2em"><%=topLineNews.getNewsSumary()%></p>
						<p style="text-align: right;font-size: 14px;">
							<a href="news?action=look&id=<%=topLineNews.getNewsId()%>">&lt;查看更多&gt;</a>
						</p>
						<%} %>
					</div>
				</div>
				<div class="news-hot">
					<div class="news-hot-title">热点新闻</div>
					<%
						List<NewsList> listHotNews = (List<NewsList>) request.getAttribute("listHotNews");
						if(listHotNews!=null){
					%>
					<ul>
						<%
							for (int i = 0; i < listHotNews.size(); i++) {
						%>
						<li><a
							href="news?action=look&id=<%=listHotNews.get(i).getNewsId()%>"><span
								style="font-size: 12px;"><%=listHotNews.get(i).getNewsPublishTime()%></span><%=listHotNews.get(i).getNewsTitle()%></a>
						</li>
						<%
							}
						%>
					</ul>
					<%} %>
				</div>
			</div>
			<div class="news-category">
				<%
					NewsLists[] listNewsLists = (NewsLists[]) request.getAttribute("listNewsLists");
													for (int i = 0; i < listNewsLists.length; i++) {
				%>
				<div class="news-category-list">
					<div class="category-title">
						<div style="width: 80%;float: left;"><%=listNewsLists[i].getNewsCategoryName()%></div>
						<div style="width: 20%;float: left;">
							<a
								href="category?action=look&id=<%=listNewsLists[i].getNewsCategoryId()%>&name=<%=listNewsLists[i].getNewsCategoryName()%>">更多&gt;&gt;</a>
						</div>
					</div>
					<div class="category-news-list">
						<ul>
							<%
								NewsList[] listCategoryNews =listNewsLists[i].getListCategoryNews();
																												for (int j = 0; j < listCategoryNews.length;j++){
							%>
							<li><a
								href="news?action=look&id=<%=listCategoryNews[j]
							.getNewsId()%>"><%=listCategoryNews[j]
							.getNewsPublishTime()%><%=listCategoryNews[j]
							.getNewsTitle()%></a></li>
							<%
								}
							%>
						</ul>
					</div>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
	<jsp:include page="front-bottom.jsp"></jsp:include>
</body>
</html>
