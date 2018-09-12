<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html>
<html>
  <head>
	<link href="/jspNews/css/1.css" rel="stylesheet" type="text/css">
	<meta charset="utf-8">
  </head>
  <body>
    <div class="center" style="width:800px;margin-top:30px;">
	  	<form action="/jspNews/servlet/CommentServlet?type=addComment" method="post">
	  		<div class="center" style="width:500px">
				<textarea name="content" cols="72" rows="8" id="textarea" required></textarea>
			</div>
			<p>
			<div class="center" style="width:50px;height:80px;">
				<br>
				<input type="submit" value="提 交 评 论 ">
			</div>		
			<input type="hidden" name="newsId" id="newsId" value="${param.newsId}">
		</form> 
	 </div>   	
  </body>
</html>
