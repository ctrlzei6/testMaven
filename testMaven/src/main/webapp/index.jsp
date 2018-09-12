<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index.jsp</title>
</head>
<body>

<center>
	<br><br><br>
	<!-- 
	<h1><a href="<%=request.getContextPath()%>/servlet/NewsServlet?type=showNews">显示新闻</a></h1>
	 -->
	 <jsp:include page="showNews.jsp"></jsp:include>
	<br><br><br>
</center>
</body>
</html>