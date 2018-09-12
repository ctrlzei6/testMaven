<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>head.jsp</title>
<link href="/NewsManagerSystemDemo/css/1.css" rel="stylesheet"
	type="text/css">

</head>
<body>
	<form id="searchForm" method="post"
		action="/jspNews/servlet/NewsServlet?type=searchNews">
		<div class="div-out">
			<div class="logleft">
				<img src="/jspNews/images/log.png">
			</div>
			<div class="logMiddle">
				<div class="logMiddleInner">
					<input type="text" id="search" name="search"> 
					<input type="button" onclick="document.getElementById('searchForm').submit();" value="搜索">
				</div>
			</div>
			<div class="logRight">
				<div class="logRightInner">
					<c:if test="${!(empty sessionScope.user) }">
						<a href="/jspNews/user/manageMain.jsp">管理</a>&nbsp;
					</c:if>

					<a href="/jspNews/index.jsp">首页</a>&nbsp;
					<c:choose>
						<c:when test="${empty sessionScope.user}">
							<a href="/jspNews/user/login.jsp">登录</a>
							&nbsp;<a href="/jspNews/user/register.jsp">注册</a>
						</c:when>
						<c:otherwise>
					    	&nbsp;<img src="${sessionScope.user.userImage}" width="50px" />&nbsp;
					    	<a
								href="/jspNews/servlet/UserServlet?type=logOut">注销</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</form>
</body>
</html>
