<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
 <head>
 	<meta charset="utf-8"></head>
  <body>
    
	<table width="500" border="2px" align="center">
		<tbody>
			<tr><td colspan="2"  align="center">个人信息：</td></tr>
			
			<tr><td  align="right" width="150">用户类型：</td>
				<td>${ sessionScope.user.userType}</td>
			</tr>			
			<tr><td  align="right" >用户名：</td>
				<td>${ sessionScope.user.username}</td>
			</tr>			
			<tr><td align="right">头像：</td>	
				<td><img src="${ sessionScope.user.userImage}" height="200" /></td></tr>
			<tr><td align="right">注册日期：</td>
				<td><c:out value="${ sessionScope.user.registerDate}"></c:out></td>
			</tr> 
			
			<tr><td align="right">性别：</td>	
				<td>${ sessionScope.user.userGender}</td></tr>
			<tr><td align="right">爱好：</td>
				<td>${ sessionScope.user.userHobby}</td>
			</tr> 
								
		</tbody>
	</table> 				   
  </body>
</html>
