<%@ page language="java" import="java.util.ArrayList" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javabean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>userShow.jsp</title>
<script type="text/javascript">
function checkANews(newsId){
	var id=document.getElementById("newsId");
  	id.value=newsId;
	//提交
	document.getElementById('myform').submit();	  		  	
 }
</script>
</head>
<body>
	<center>
	<form action="<%=request.getContextPath() %>/servlet/NewsServlet?type=manageNews" id="myform" method="post">
	<table width="500" height="60" cellpadding="2px" cellspacing="2px" border="1">
      <tr>
        <td>标题</td><td>作者</td><td>内容</td><td>新闻日期</td><td>发表日期</td><td>是否可用</td><td>审查</td>
      </tr>
			<c:forEach items="${requestScope.newsList}" var="news">
				<tr>
					<td><span><c:out value="${news.caption} "></c:out></span></td>
					<td><span><c:out value="${news.author} "></c:out></span></td>
					<td><span><a href="<%=request.getContextPath()%>/servlet/NewsServlet?type=showDetailContent&newsId=${news.newsId}" target="blank">详细内容</a></span></td>
					<td><span><c:out value="${news.newsTime} "></c:out></span></td>
					<td><span><c:out value="${news.publishTime} "></c:out></span></td>
					<td><span><c:out value="${news.newsEnable} "></c:out></span></td>
					<td>
					    <c:if test="${news.newsEnable == 'use'}">
					        <a href="javascript:void(0);" onclick="checkANews('${news.newsId}');">停用</a> 
					    </c:if>
					    <c:if test="${news.newsEnable == 'stop'}">
					        <a href="javascript:void(0);" onclick="checkANews('${news.newsId}');">启用</a>
					    </c:if>
		   		 </td>  
				</tr>
				
			</c:forEach>
	
          <tr>
          	<td colspan="7" align="center">
          		总页数：${requestScope.page.getTotalPage()}
	          	<a href="<%=request.getContextPath() %>/servlet/NewsServlet?type=checkNews&start=1">首页</a>  
	          	<a href="<%=request.getContextPath() %>/servlet/NewsServlet?type=checkNews&start=${requestScope.page.getPrePage()} ">上一页</a>  
	          	<a href="<%=request.getContextPath() %>/servlet/NewsServlet?type=checkNews&start=${requestScope.page.getNextPage()} ">下一页</a>  
	          	<a href="<%=request.getContextPath() %>/servlet/NewsServlet?type=checkNews&start=${requestScope.page.getLast()}">末页</a>
          	</td>
          </tr>  
    </table>
    	<input type="hidden" name="newsId" id="newsId" value="">
    </form>
	</center>
	
</body>
</html>