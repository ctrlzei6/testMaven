<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javabean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>分类显示新闻选择页面</title>
</head>
<body>
	<center>
		<h2>请选择分类：</h2>
		<hr>
		<form action="<%=request.getContextPath()%>/servlet/NewsServlet?type=sortNewsByType" method="post">
			<select name="newsType">
				<option value="体育">体育</option>
				<option value="国际">国际</option>
				<option value="社会">社会</option>
				<option value="汽车">汽车</option>
				<option value="科学">科学</option>
			</select>
			<input type="submit" value="选择">
		</form>
	</center>
</body>
</html>