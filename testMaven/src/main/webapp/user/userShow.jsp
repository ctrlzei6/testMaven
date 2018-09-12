<%@ page language="java" import="java.util.ArrayList" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javabean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>userShow.jsp</title>
</head>
<body>
	
	<center>
		 <table width="500" height="60" cellpadding="2px" cellspacing="2px" border="1">
      <tr>
        <td>用户头像</td><td>用户名</td><td>性别</td><td>爱好</td><td>注册日期</td><td>是否可用</td>
      </tr>
			<c:forEach items="${requestScope.userList}" var="user">
				<tr>
					<td><span><image src="${user.userImage}" width="70px" /></span></td>
					<td><span><c:out value="${user.username} "></c:out></span></td>
					<td><span><c:out value="${user.userGender} "></c:out></span></td>
					<td><span><c:out value="${user.userHobby} "></c:out></span></td>
					<td><span><c:out value="${user.registerDate} "></c:out></span></td>
					<td><span><c:out value="${user.userEnable} "></c:out></span></td>
				</tr>
			</c:forEach>
	
          <tr>
          <td>当前页数：${requestScope.page.getPageNow()}</td>
          	<td colspan="5" align="center">
          		总页数：${requestScope.page.getTotalPage()}
	          	<a href="<%=request.getContextPath() %>/servlet/UserServlet?type=showUsers&start=1">首页</a>  
	          	<a href="<%=request.getContextPath() %>/servlet/UserServlet?type=showUsers&start=${requestScope.page.getPrePage()} ">上一页</a>  
	          	<a href="<%=request.getContextPath() %>/servlet/UserServlet?type=showUsers&start=${requestScope.page.getNextPage()} ">下一页</a>  
	          	<a href="<%=request.getContextPath() %>/servlet/UserServlet?type=showUsers&start=${requestScope.page.getLast()}">末页</a>
          	</td>
          </tr>
          
    </table>
	</center>
	
</body>
</html>