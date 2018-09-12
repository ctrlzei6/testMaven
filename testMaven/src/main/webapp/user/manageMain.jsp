<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!doctype html>
<html>
 <head>
 	<link href="/jspNews/css/1.css" rel="stylesheet" type="text/css">
 	<meta charset="utf-8"> 
 	<script type="text/javascript"> 
 		function getOnePage(type){
    	  	var url1;
    	  	var page=document.getElementById("page");
    	  	var pageSize=document.getElementById("pageSize");
    	  	var totalPageCount=document.getElementById("totalPageCount");
			
    	  	pageValue=parseInt(page.value);
    	  	if(type=="pre"){
    	  		pageValue-=1;
    	  		page.value=pageValue.toString();
    	  	}else if(type=="next"){
    	  		pageValue+=1;
    	  		page.value=pageValue.toString();
    	  	}
    	  	//提交
    	  	document.getElementById('myform').submit();
      	}
	</script>  
  </head>
  <body>
  	 <jsp:include page="../head.jsp"></jsp:include>
  	  <div class="newsShowByType_frame center"  id="frameDiv">
  	  	<div class="newsShowByType_left" id="leftDiv">
  	  		<ul style="list-style-type: none;">
		    	<li><a href="/jspNews/servlet/UserServlet?type=showPrivate" target='frame'>显示个人信息</a></li>
  				
  				<li><a href="/jspNews/user/changePassword.jsp" target='frame'>修改密码</a></li>
  				<c:if test="${sessionScope.user.userType=='manager'}" >
  					<br>
	 		    	<li><a href="/jspNews/servlet/UserServlet?type=showUsers" target='frame'>浏览用户</a></li>
	  				<li><a href="/jspNews/servlet/UserServlet?type=checkUsers" target='frame'>审查信息</a></li>
	  				<br>
	  				<li><a href="/jspNews/servlet/NewsServlet?type=checkNews" target='frame'>管理新闻</a></li>
	 			</c:if>	
  				<c:if test="${sessionScope.user.userType=='newsAuthor'}" >
  					<br>
	 		    	<li><a href="/jspNews/news/addNews.jsp" target='_blank'>添加新闻</a></li>
 					<li><a href="/jspNews/servlet/NewsServlet?type=checkNews" target='frame'>管理自己的新闻</a></li>
	 			</c:if>		 			
  	  		</ul>
		</div>	
		<div class="manageMain_right">
			<iframe name="frame" id="frame" scrolling="no" seamless frameborder="0" width="100%" height="100%">
			</iframe>
		</div>
		<div class="clear"></div>
  	  </div>
  	  <jsp:include page="../tail.jsp"></jsp:include>	
  </body>
  
  <script type="text/javascript">
  	//让父div与子div高度一致（这里因为子div是绝对定位，脱离了文档流，导致父div高度为0了，所以要手动设定父div高度。
  	//否则下面父div后面的兄弟div就会往上移，因为父div的高度为0
  	var father = document.getElementById('frameDiv');
    var son = document.getElementById('leftDiv');
    frameDiv.style.height=leftDiv.offsetHeight+'px';
  </script>
  
</html>
