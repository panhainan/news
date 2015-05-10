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

<title>编辑新闻</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="<%=basePath%>/umeditor/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="<%=basePath%>/umeditor/jquery-1.10.2.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>/umeditor/umeditor.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/umeditor/lang/zh-cn/zh-cn.js"></script>
<link rel="stylesheet" href="<%=basePath%>back/css/back.css" />
<style type="text/css">
#pic-site-input {
	display: none;
}
</style>
<script type="text/javascript">
	function showPicSiteInput() {
		if (document.getElementById("newsIsPicture").checked == true) {
			document.getElementById("pic-site-input").style.display = "block";
			document.getElementById("newsIsPicture").value = "true";
		} else {
			document.getElementById("pic-site-input").style.display = "none";
			document.getElementById("newsIsPicture").value = "false";
		}
		return true;
	}
	function setTopInput() {
		if (document.getElementById("newsIsTopLine").checked == true) {
			document.getElementById("newsIsTopLine").value = "true";
		} else {
			document.getElementById("newsIsTopLine").value = "false";
		}
		return true;
	}
	function setHotInput(){
		if (document.getElementById("newsIsHot").checked == true) {
			document.getElementById("newsIsHot").value = "true";
		} else {
			document.getElementById("newsIsHot").value = "false";
		}
		return true;
	}
	function checkInput(){
		$("#newsContent").val(editor.getContent());
		if(showPicSiteInput()==true && setTopInput()==true && setHotInput()==true ){
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
				CategoryList[] listCategoryList = (CategoryList[]) request
						.getAttribute("listCategoryList");
				NewsDetails newsDetails = (NewsDetails) request
						.getAttribute("newsDetails");
			%>
			<h1>编辑新闻</h1>
			<form action="<%=basePath%>back/manage/news?action=update"
				method="post" onsubmit="return checkInput();"
				enctype="multipart/form-data">
				<input name="newsId" type="hidden"
					value="<%=newsDetails.getNewsId()%>">
				标&nbsp;&nbsp;&nbsp;&nbsp;题：<input
					value="<%=newsDetails.getNewsTitle()%>"
					style="	min-height: 30px;min-width: 400px;" type="text"
					name="newsTitle"> <br> <br>
				作&nbsp;&nbsp;&nbsp;&nbsp;者：<input type="text"
					value="<%=newsDetails.getNewsAuthor()%>"
					style="	min-height: 30px;min-width: 400px;" type="text"
					name="newsAuthor"><br> <br> 作者网址：<input
					type="text" value="<%=newsDetails.getNewsAuthorSite()%>"
					style="	min-height: 30px;min-width: 400px;" type="text"
					name="newsAuthorSite" value="http://panhainan.com"><br>
				<br> 新闻类别：<select
					style="min-height:30px;min-width: 100px;line-height: 30px;font-size: 16px;"
					name="newsCategoryId" id="newsCategoryId">
					<%
						for (int i = 0; i < listCategoryList.length; i++) {
					%>
					<option
						style="min-height:30px;min-width: 100px;line-height: 30px;font-size: 16px;"
						value="<%=listCategoryList[i].getCategoryId()%>"><%=listCategoryList[i].getCategoryName()%></option>
					<%
						}
					%>
				</select> <br> <br> 新闻属性： <input type="checkbox"
					onclick="showPicSiteInput()" value="false" id="newsIsPicture"
					name="newsIsPicture"> <label for="newsIsPicture">图片新闻</label>
				<input type="checkbox" onclick="setTopInput()" value="false"
					id="newsIsTopLine" name="newsIsTopLine"> <label
					for="newsIsTopLine">今日头条</label> <input type="checkbox"
					value="false" onclick="setHotInput()" id="newsIsHot"
					name="newsIsHot"> <label for="newsIsHot">热点新闻</label><br>
				<br>
				<div id="pic-site-input">
					图片地址：<input type="file" id="picFile" name="picFile" />
				</div>
				<br> 新闻内容：<br>
				<br>
				<textarea style="display: none" id="newsContent" name="newsContent"></textarea>
				<!-- 加载编辑器的容器 -->
				<script id="container" name="content" type="text/plain"><%=newsDetails.getNewsContent()%></script>
				<!-- 实例化编辑器 -->
				<script type="text/javascript">
    	    var editor = UM.getEditor('container');
    	</script>
				<br>
				<br> <input type="submit" style="height: 30px;font-size: 16px;"
					value="确认保存">
			</form>
		</div>
	</div>
</body>
</html>
