<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>后台管理-主页</title>
        <link rel="stylesheet" type="text/css" href="css/admin_common.css"/>
        <link rel="stylesheet" type="text/css" href="css/global.css"/>
		<link rel="SHORTCUT ICON" href="/shopping/chenchen.ico">
    </head>
    <body>
    	<%@ include file="common_header.jsp" %> 
    	
    	
    	<div class="main">
    		<div class="main_inner">
    			<div class="mi_left">
    				<%@ include file="common_left.jsp" %> 
    			</div>
    			<div class="mi_right">
					<div class="mir_inner">
						
						<!--
						logo 图片管理
						版权信息 管理
						其他
						-->
						<h2 class="miri_h2">全局参数设置</h2>
						<div class="space30px"></div>
						<div class="miri_form">
							<form action="/shopping/admin/adminUpdateLogoAndCopyright.do" method="post" enctype="multipart/form-data">
								<table border="0" cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<th>全局LOGO图片</th>
										<td>
											<input type="file" name="global_logo" class="global_logo" />
										</td>
									</tr>
									<tr>
										<th>版权信息</th>
										<td>
											<input type="text" name="global_copy" class="global_copy" />
										</td>
									</tr>
									<tr>
										<th>&nbsp;</th>
										<td>
											<input type="submit" value="确认提交" />
											<input type="reset" value="清空" />
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
    				
    			</div>
    			<div class="clear"></div>
    		</div>
    	</div>
    	
    	<%@ include file="common_footer.jsp" %> 
<script src="js/jQuery.1.8.3.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/shopping/layer/layer.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function(){
	/*后台request.setAttribute()设置错误信息，前台用EL表达式拿到，再用layer.confirm()提示用户
	为了防止重复提示，用户点完确认以后再重定向到本页面 */
	var wrong_msg = "${updateLogoAndCopyWrongMsg}";
	//alert(wrong_msg);
	if(wrong_msg != "") {
		layer.confirm(wrong_msg, {
			 btn: ["确认"] 
			,btn1: function(index, layero){
			    window.location.href = "/shopping/admin/global.jsp";
			 }
		});
	}
	
	$.ajax({
		type:"post",
		url:"/shopping/user/getLogoAndCopyright.do",
		success:function(data){
			LogoAndCopyright_val = eval("("+data+")");
			$(".hil_logo").attr("style","background: url(/img/"+LogoAndCopyright_val.logo_img+");");
			$(".footer_inner p").text(LogoAndCopyright_val.copyright_detail);
		}
	});
	
	/* $(".administrator_name").focus(function(){
		$(".wrong_msg").text("");
	}).blur(function(){
		if($(this).val() == "") {
			layer.msg("用户名不能为空！");
			$(".wrong_msg").text("用户名不能为空！");
		}
	}); */
});
</script>    	
 	</body>
</html>