<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
//String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";
%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
    	<%-- <base href="<%=basePath%>"> --%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>管理员登录界面</title>
    	<link rel="stylesheet" type="text/css" href="css/admin_login.css"/>
    	<link rel="SHORTCUT ICON" href="/shopping/chenchen.ico">
</head>
<body>
<div class="top_div"></div>
<div style="width: 400px;height: 200px;margin: auto auto;background: #ffffff;text-align: center;margin-top: -100px;border: 1px solid #e7e7e7">
    <div style="width: 165px;height: 96px;position: absolute">
        <div class="tou"></div>
        <div id="left_hand" class="initial_left_hand"></div>
        <div id="right_hand" class="initial_right_hand"></div>
    </div>
	
	<form action="/shopping/admin/adminLoginCheck.do" method="post">
    <p style="padding: 30px 0px 10px 0px;position: relative;">
        <span class="u_logo"></span>
        <input class="ipt administrator_name" type="text" name="administrator_name" value="${admin_name}" placeholder="请输入用户名或邮箱">
		
    </p>
    <p style="position: relative;">
        <span class="p_logo"></span>
        <input id="password" class="ipt administrator_password" name="administrator_password" type="password"  placeholder="请输入密码">
    </p>
    <div style="height: 50px;line-height: 50px;margin-top: 30px;border-top: 1px solid #e7e7e7;">
        <p style="margin: 0px 35px 20px 45px;">
           <!--<span style="float: left"><a href="#" style="color:#ccc;">忘记密码?</a></span>-->
           <span style="float: right">
               <!--<a href="#" style="color:#ccc;margin-right:10px;">注册</a>-->
               <input type="submit" value="Login" style="background: #008ead;padding: 7px 10px;border-radius: 4px;border: 1px solid #1a7598;color: #FFF;font-weight: bold;"></input>
<!--                <a href="JavaScript:void(0);" style="background: #008ead;padding: 7px 10px;border-radius: 4px;border: 1px solid #1a7598;color: #FFF;font-weight: bold;">Login</a> -->
           </span>
        </p>
    </div>
	</form>
</div>

<div style="position: fixed;bottom: 0px;text-align: center;width: 100%;">
    Copyright ©2015 <a style="margin-left: 10px;color: #000000;text-decoration: underline" href="http://www.sucaihuo.com">http://www.sucaihuo.com</a>
</div>


<script src="js/jQuery.1.8.3.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/admin_login.js" type="text/javascript" charset="utf-8"></script>
<script src="/shopping/layer/layer.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function(){
	/*后台request.setAttribute()设置错误信息，前台用EL表达式拿到，再用layer.msg()提示用户*/
	var wrong_msg = "${adminLoginWrongMsg}";
	//alert(wrong_msg);
	if(wrong_msg != "") {
		layer.msg(wrong_msg);
	}

	/*前台判断用户名不能为空*/
	$(".administrator_name").focus(function(){
		$(".wrong_msg").text("");
	}).blur(function(){
		if($(this).val() == "") {
			layer.msg("用户名不能为空！");
			$(".wrong_msg").text("用户名不能为空！");
		}
	});
});
</script>
</body>
</html>