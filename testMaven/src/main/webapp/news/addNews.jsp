<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!doctype html>
<html>
  <head>
  	<link href="/jspNews/css/1.css" rel="stylesheet" type="text/css">
  	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/plugin/utf8-jsp/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/plugin/utf8-jsp/ueditor.all.min.js"> </script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/plugin/utf8-jsp/lang/zh-cn/zh-cn.js"></script>
	  	
  </head>
  
  <body>
 <div class="div-out"> 
 <form action="/jspNews/servlet/NewsServlet?type=addNews" name="form1" method="post" onsubmit="return submit1()" enctype="multipart/form-data" >
	<table width="600" align="center" border="1">
		<tbody>
			<tr><td>标题：</td><td>
					<input type="text" size="100" name="caption"></td>
			</tr>
			<tr><td>类型：</td>
				<td>
					<select name="newsType">
						<option value="体育">体育</option>
						<option value="国际">国际</option>
						<option value="社会">社会</option>
						<option value="汽车">汽车</option>
						<option value="科学">科学</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>作者：</td>
				<td><input type="text" name="author"></td>
			</tr>
			<tr>
				<td>日期：</td>
				<td><input type="datetime-local" name="newsTime"></td>
			</tr>
			<tr>
				<td colspan="2">    
					<div>
    					<script id="content" type="text/plain" style="width:800px;height:500px;"></script>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="添加新闻"></td>
			</tr>
		</tbody>
	</table>
</form>
</div>
</body>
</html>
	<script type="text/javascript">
	    //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    var ue = UE.getEditor('content');
	</script>