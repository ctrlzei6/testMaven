<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>login.jsp</title>
</head>
<body>

	<h1>用户登录</h1>
	<hr>
	<br />
	<center>
		<form action="<%=request.getContextPath()%>/servlet/UserServlet?type=login" method="post">
			<table>
			<!-- 
				<tr>
					<td>用户名类型：</td>
					<td>
						<label><input type="radio" name="userType" value="manager">管理员</label>
						<label><input type="radio" name="userType" value="newsAuthor">新闻发布员</label>
						<label><input type="radio" name="userType" value="user">普通用户</label>
					</td>
				</tr>
			 -->
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>密 码：</td>
					<td><input type="password" name="password"></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="登录"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>