<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javabean.*" %>
<%@page import="dao.NewsDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Page p = new Page(1,3);
		int newsCount = NewsDao.getNewsCount();
		if(newsCount != 0)
		{
			p.setTotal(newsCount);
		}
		if(request.getParameter("start") != null)
		{
			int start = Integer.parseInt(request.getParameter("start"));
			if(start==0)
				start=1;
			p.setStart(start);
		}
		ArrayList<News> newsList = NewsDao.getAllNews(p);
		if(newsList != null)
		{
			request.setAttribute("page", p);
			request.setAttribute("newsList", newsList);
		}
	%>
<jsp:include page="head.jsp"></jsp:include>
	<center>
		<table width="900" height="60" cellpadding="5px" cellspacing="5px" border="1">
		<tr>
			<th>类型</th><th>标题</th><th>作者</th><th>内容</th><th>新闻时间</th>
		</tr>
		<c:forEach items="${requestScope.newsList}" var="news">
		  <c:if test="${news.newsEnable=='use'}" >
			<tr>
				<td><span><c:out value="${news.newsType} "></c:out></span></td>
				<td><span><c:out value="${news.caption} "></c:out></span></td>
				<td><span><c:out value="${news.author} "></c:out></span></td>
				<td><span><a href="<%=request.getContextPath()%>/servlet/NewsServlet?type=showDetailContent&newsId=${news.newsId}" target="blank">详细内容</a></span></td>
				<td><span><c:out value="${news.newsTime} "></c:out></span></td>	
			</tr>
		  </c:if>	
		</c:forEach>
		<tr>
				<td>当前页数：${requestScope.page.getPageNow()}</td>
				<td colspan="4" align="center">
					总页数：${requestScope.page.getTotalPage()} 
					<a href="<%=request.getContextPath()%>/servlet/NewsServlet?type=showNews&start=1">首页</a>
					<a href="<%=request.getContextPath() %>/servlet/NewsServlet?type=showNews&start=${requestScope.page.getPrePage()} ">上一页</a>
					<a href="<%=request.getContextPath() %>/servlet/NewsServlet?type=showNews&start=${requestScope.page.getNextPage()} ">下一页</a>
					<a href="<%=request.getContextPath() %>/servlet/NewsServlet?type=showNews&start=${requestScope.page.getLast()}">末页</a>
				</td>
			</tr>
		</table>
	</center>
<jsp:include page="tail.jsp"></jsp:include>
</body>
</html>