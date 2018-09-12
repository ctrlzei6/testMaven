<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>register.jsp</title>
<script type="text/javascript">
	function valUsername(){
		var pattern = new RegExp("^[a-z]([a-z0-9])*[-_]?([a-z0-9]+)$","i");//创建模式对象
		var str1=document.getElementById("username").value;//获取文本框的内容
		
		if(document.getElementById("username").value==null || document.getElementById("username").value==""){
			document.getElementById("usernameSpan").innerHTML="*不能为空";
			return false;
		}else if(str1.length>=8 && pattern.test(str1)){//pattern.test() 模式如果匹配，会返回true，不匹配返回false
			document.getElementById("usernameSpan").innerHTML="ok";
			return true;
		}else{
			document.getElementById("usernameSpan").innerHTML="*用户名至少需要8个字符，以字母开头，以字母或数字结尾，可以有-和_";
			return false;
		}
	}
	
	function valPassword(){
		var str = document.getElementById("password").value;
		var pattern=/^(\w){6,20}$/;
		
		if(document.getElementById("password").value==null || document.getElementById("password").value==""){
			document.getElementById("passwordSpan").innerHTML="*不能为空";
			return false;
		}else if(str.match(pattern)==null){
			document.getElementById("passwordSpan").innerHTML="*密码只能输入6-12个字母、数字、下划线";
			return false;
		}else{
			document.getElementById("passwordSpan").innerHTML="ok";
			return true;
		}
	}
	
	function passwordSame(){
		var str = document.getElementById("password").value;
		if(document.getElementById("passwordConfirm").value==null || document.getElementById("passwordConfirm").value==""){
			document.getElementById("passwordConfirmSpan").innerHTML="*不能为空";
			return false;
		}else if(document.getElementById("password").value==document.getElementById("passwordConfirm").value){			
			document.getElementById("passwordConfirmSpan").innerHTML="ok";
			return true ;
		}else{
			document.getElementById("passwordConfirmSpan").innerHTML="*两次密码不一样";
			return false;
		}
				
	}
		
	function submit1(){
		result1=valUsername();
		result1=valPassword() && result1;
		result1=passwordSame() && result1;
		if( result1)
			return true;//提交
		else 
			return false;//阻止提交
	}

</script>


</head>
<body>
	<h1>用户注册</h1>
	<hr>
	<br/>
	<center>
	<!--加了enctype="multipart/form-data"后，servlet无法通过request.getParameter()来获得客户端传来的参数 -->
		<form action="<%=request.getContextPath() %>/servlet/UserServlet?type=register" method="post" onsubmit="return submit1()"  enctype="multipart/form-data" >
			<table>
				<tr>
					<td>用户名类型：</td>
					<td>
						<input type="radio" name="userType" id="userType" value="manager">管理员
						<input type="radio" name="userType" id="userType" value="newsAuthor">新闻发布员
						<input type="radio" name="userType" id="userType" value="user">普通用户
					</td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username" id="username" onBlur="valUsername()"><span id="usernameSpan"></span></td>
				</tr>
				<tr>
					<td>密 码：</td>
					<td><input type="password" name="password" id="password" onBlur="valPassword()"><span id="passwordSpan"></span></td>
				</tr>
				<tr>
					<td>再次输入密码：</td>
					<td><input type="password" name="passwordConfirm" id="passwordConfirm" onBlur="passwordSame()"><span id="passwordConfirmSpan"></span></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td>
						<label><input type="radio" name="userGender" checked="checked" value="man">男</label>
						<label><input type="radio" name="userGender" value="woman">女</label>
					</td>
				</tr>
				<tr>
					<td>兴趣爱好：</td>
					<td>
						<label><input type="checkbox" name="userHobby" value="篮球">篮球</label>
						<label><input type="checkbox" name="userHobby" value="足球">足球</label>
						<label><input type="checkbox" name="userHobby" value="电影">电影</label>
						<label><input type="checkbox" name="userHobby" value="游泳">游泳</label>
						<label><input type="checkbox" name="userHobby" value="上网">上网</label>
						<label><input type="checkbox" name="userHobby" value="听音乐">听音乐</label>
					</td>
				</tr>
				<tr>
					<td>上传用户头像：</td>
					<td>
						<input type="file"  name="userImage">
					</td>
				</tr>
				
				<tr><td colspan="2"><input type="submit" value="注册"></td></tr>
			</table>
		</form>
	</center>
</body>
</html>