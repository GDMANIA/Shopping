<%@page import="com.shopping.entity.Category"%>
<%@page import="com.shopping.daoimpl.CategoryDaoImpl"%>
<%@page import="com.shopping.dao.CategoryDao"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
//这里从数据库中取出所有的一级分类，以便于下面遍历
CategoryDao cd = new CategoryDaoImpl();
List<Category> cats = new ArrayList<Category>();
cats = cd.selectAll();
request.setAttribute("categories", cats);
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>后台管理-商品一级分类管理</title>
        <link rel="stylesheet" type="text/css" href="css/admin_common.css"/>
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
    					<h2 class="miri_h2">商品一级分类管理<small>(最多添加10个商品的一级分类)</small></h2>
    					<div class="miri_form">
    						<form class="" action="/shopping/admin/adminAddCategory.do" method="post">
    							<table border="0" cellspacing="0" cellpadding="0" width="100%">
    							<!-- 这里用JSTL先判断一下数据库里面的一级分类个数，如果已经大于等于10，则不显示添加分类的按钮 -->
    								<c:if test="${categories.size() < 10}">
	    								<tr>
	    									<th align="right">新增加商品一级分类&nbsp;&nbsp;</th>
	    									<td width="210">
	    										<input type="text" name="categrayfirst" class="categrayfirst" placeholder="请输入一级分类名称..." />
	    									</td>
	    									<td>
	    										<input type="submit" value="确认添加"/>
	    									</td>
	    								</tr>
    								</c:if>
    							</table>
    						</form>
    						<table border="0" cellspacing="0" cellpadding="0" width="100%">
    							<tr><th>No.</th><th>ID</th><th>一级分类名称</th><th>Admin</th><th>AddTime</th><th>操作</th></tr>
    							<!-- jstl+el表达式循环拿出所有的一级分类信息 -->
    							<c:forEach items="${requestScope.categories}" var="category" varStatus="status">
									<tr>
									<td>${status.index+1}</td><td>${category.cat_id}</td><td>${category.cat_name}</td><td>${category.administrator_name}</td><td>${category.cat_createtime}</td>
									<td><a href="JavaScript:void(0);">修改</a></td>
									<td><a href="JavaScript:void(0);">删除</a></td>
									</tr>
									
								</c:forEach>
    							
    						</table>
    					</div>
    				</div>
    			</div>
    			<div class="clear"></div>
    		</div>
    	</div>
    	
    	
    	
    	
    	
    	<%@ include file="common_footer.jsp" %> 
    <script src="/shopping/js/jQuery.1.8.3.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/shopping/layer/layer.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript"> 
	$(document).ready(function(){
    	$.ajax({
			type:"post",
			url:"/shopping/user/getLogoAndCopyright.do",
			success:function(data){
				LogoAndCopyright_val = eval("("+data+")");
				$(".hil_logo").attr("style","background: url(/img/"+LogoAndCopyright_val.logo_img+");");
				$(".footer_inner p").text(LogoAndCopyright_val.copyright_detail);
			}
		});
    	
    	/*后台request.setAttribute()设置错误信息，前台用EL表达式拿到，再用layer.confirm()提示用户
    	为了防止重复提示，用户点完确认以后再重定向到本页面 */
    	var wrong_msg = "${catNameWrongMsg}";
    	//alert(wrong_msg);
    	if(wrong_msg != "") {
    		layer.confirm(wrong_msg, {
				 btn: ["确认"] 
				,btn1: function(index, layero){
				    window.location.href = "/shopping/admin/categoryfirst.jsp";
				 }
			});
    	}
	});
    </script>
 	</body>
</html>