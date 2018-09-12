<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javabean.*"%>
<%@ page import="dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新闻的详细内容</title>
</head>
<body>
	<center>
		<h2>
			<c:out value="${requestScope.news.caption} "></c:out>
		</h2>
		<p>
			<c:out value="${requestScope.news.author} "></c:out>
		</p>
		<hr>
		${requestScope.news.content}

		<!-- 商品循环开始 -->
		<%
			ArrayList<Comment> commentList = CommentDao.getAllComments();
			request.setAttribute("commentList", commentList);
		%>
		<table width="750" height="60" cellpadding="5px" cellspacing="5px"
			border="1">
			<tr>
				<td>用户ID</td>
				<td>回复内容</td>
				<td>日期</td>
			</tr>
			<c:forEach items="${requestScope.commentList}" var="comment">
				<c:if test="${comment.newsId==requestScope.news.newsId}">
					<%
						//User u = UserDao.getUserById(comment.userId);
					%>
					<tr>
						<td><span><c:out value="${comment.userId} "></c:out></span></td>
						<td><span>${comment.content}</span></td>
						<td><span><c:out value="${comment.commentTime} "></c:out></span></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</center>
	<jsp:include page="../comment/addComment.jsp"></jsp:include>
</body>
</html>