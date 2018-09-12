<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javabean.*" %>
<%@page import="dao.NewsDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>搜索新闻结果页面</title>
</head>
<body>
	<jsp:include page="../head.jsp"></jsp:include>
	
	<center>
		<table width="900" height="60" cellpadding="5px" cellspacing="5px" border="1">
		<tr>
			<th colspan="5">查找结果</th>
		</tr>
		<tr>
			<th>类型</th><th>标题</th><th>作者</th><th>内容</th><th>新闻时间</th>
		</tr>
		<c:forEach items="${requestScope.captionList}" var="news">
		
			<tr>
				<td><span><c:out value="${news.newsType} "></c:out></span></td>
				<td><span><c:out value="${news.caption} "></c:out></span></td>
				<td><span><c:out value="${news.author} "></c:out></span></td>
				<td><span><a href="<%=request.getContextPath()%>/servlet/NewsServlet?type=showDetailContent&newsId=${news.newsId}">详细内容</a></span></td>
				<td><span><c:out value="${news.newsTime} "></c:out></span></td>	
			</tr>	
		</c:forEach>
		</table>
	<center>
	<jsp:include page="../tail.jsp"></jsp:include>

</body>
</html>