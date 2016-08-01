<%@page import="com.shopping.entity.UserSearchChoices"%>
<%@page import="com.shopping.entity.Page"%>
<%@page import="com.shopping.entity.User"%>
<%@page import="com.shopping.daoimpl.UserDaoImpl"%>
<%@page import="com.shopping.dao.UserDao"%>
<%@page import="com.shopping.entity.Category"%>
<%@page import="com.shopping.daoimpl.CategoryDaoImpl"%>
<%@page import="com.shopping.dao.CategoryDao"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
/*这里与前面的相同，也是如果session里面存在订单的分页和搜索对象就取出，不存在就重新赋值再绑定到session里面去*/
Page p = new Page();
UserSearchChoices choices = new UserSearchChoices();
UserDao ud = new UserDaoImpl();
List<User> users = new ArrayList<User>();
if(request.getSession().getAttribute("search_user_choices") != null) {
	choices = (UserSearchChoices)request.getSession().getAttribute("search_user_choices");
} else {
	request.getSession().setAttribute("search_user_choices", choices);
}
if(request.getSession().getAttribute("page_users") == null) {
	p.setTotalRows(ud.countAll(choices));
	p.setPageSize(2);
	p.setCurrentPage(1);
	p.setTotalPage(p.getTotalPage());
	users = ud.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
	p.setUsers(users);
	
	request.getSession().setAttribute("page_users", p); 
} 
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>后台管理-会员管理页</title>
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
    					<h2 class="miri_h2">平台会员信息</h2>
    					<div class="space10px"></div>
    					<div class="miri_form">
    						<div>
    						<!-- 下面是搜索的表单模块，如果有搜索对象就取出展示出来，没有就显示默认值 -->
    							<form action="/shopping/admin/adminSearchUsers.do" method="post">
    								<span>账号：</span><input type="text" name="user_name" class="user_name" placeholder="ex:xxx@sina.com" style="width: 150px;" value="${sessionScope.search_user_choices.user_name}"/>
    								&nbsp;&nbsp;&nbsp;
    								<span>联系电话：</span><input type="text" name="user_telnum" class="user_telnum" placeholder="ex:123" style="width: 100px;" value="${sessionScope.search_user_choices.user_telnum}"/>
    								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    								<span>邮箱：</span><input type="text" name="user_email" class="user_email" placeholder="ex:xxx@sina.com" style="width: 100px;" value="${sessionScope.search_user_choices.user_email}"/>
    								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    								<span>等级：</span><input type="text" name="user_level" class="user_level" placeholder="ex:123" style="width: 100px;" value="${sessionScope.search_user_choices.user_level}"/>
    								<br>
    								<span>消费金额：</span><input type="text" name="user_ttpayment_below" class="user_ttpayment_below" placeholder="" style="width: 50px;" value="${sessionScope.search_user_choices.user_ttpayment_below}"/>
    								<span>-</span>
    								<input type="text" name="user_ttpayment_above" class="user_ttpayment_above" placeholder="" style="width: 50px;" value="${sessionScope.search_user_choices.user_ttpayment_above}"/>
    								<input type="submit" value="搜索" style="width: 60px;"/>
    								<!-- 这里同样的加了一个清空的按钮，实际上就是发送一个后台请求然后把session里面的搜索类重新初始化再绑定到session里面，同时回到第一页 -->
    								<input type="reset" value="清空" style="width: 60px;" onclick="window.location.href='/shopping/admin/resetUserSearchChoices.do'"/>
    							</form>
    						</div>
    						<br>
    						<table border="0" cellspacing="0" cellpadding="0" width="100%">
    							<thead>
	    							<tr>
	    								<th>No.</th>
	    								<th>图片</th>
	    								<th>账号</th>
	    								<th>联系电话</th>
	    								<th>邮箱</th>
	    								<th>等级</th>
	    								<th>消费总金额</th>
	    								<th>注册时间</th>
	    								<th>最近登录时间</th>
	    							</tr>
    							</thead>
    							<tbody>
    							<!-- 下面就是循环显示用户信息 -->
    								<c:forEach items="${sessionScope.page_users.users}" var="user" varStatus="status">
	    								<tr>
		    								<td>${status.index+1}</td>
		    								<td>
		    									<img src="${user.user_img}"/>
		    								</td>
		    								<td>
		    									${user.user_name}
		    								</td>
		    								<td>
		    									${user.user_telnum}
		    								</td>
		    								<td>
		    									${user.user_email}
		    								</td>
		    								<td>VIP${user.user_level}</td>
		    								<td><fmt:formatNumber type="number" value="${user.user_ttpayment}" maxFractionDigits="2" minFractionDigits="2"/></td>
		    								<td>${user.user_regtime}</td>
		    								<td>${user.user_lastlogintime}</td>
		    							</tr>
		    						</c:forEach>
    							</tbody>
    						</table>
    						<div class="space20px"></div>
    					</div>
    					
    					
						<div class="space20px"></div>
						<!-- 下面是分页的模块 -->
						<div class="mir_page">
							<span>当前第&nbsp;<b>${sessionScope.page_users.currentPage}</b>&nbsp;页，共计&nbsp;<b>${page_users.totalPage}</b>&nbsp;页</span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span>
								每页显示&nbsp;
								<select name="page_size" class="page_size">
									<%-- <option value="default">${page_keys.pageSize}</option> --%>
									<c:choose>
									<c:when test="${sessionScope.page_users.pageSize == 2}">
										<option value='2' selected="selected">2</option>
										<option value='3'>3</option>
										<option value='5'>5</option>
										<option value='10'>10</option>
										<option value='15'>15</option>
									</c:when>
									<c:when test="${sessionScope.page_users.pageSize == 3}">
										<option value='2'>2</option>
										<option value='3' selected="selected">3</option>
										<option value='5'>5</option>
										<option value='10'>10</option>
										<option value='15'>15</option>
									</c:when>
									<c:when test="${sessionScope.page_users.pageSize == 5}">
										<option value='2'>2</option>
										<option value='3'>3</option>
										<option value='5' selected="selected">5</option>
										<option value='10'>10</option>
										<option value='15'>15</option>
									</c:when>
									<c:when test="${sessionScope.page_users.pageSize == 10}">
										<option value='2'>2</option>
										<option value='3'>3</option>
										<option value='5'>5</option>
										<option value='10' selected="selected">10</option>
										<option value='15'>15</option>
									</c:when>
									<c:when test="${sessionScope.page_users.pageSize == 15}">
										<option value='2'>2</option>
										<option value='3'>3</option>
										<option value='5'>5</option>
										<option value='10'>10</option>
										<option value='15' selected="selected">15</option>
									</c:when>
									<c:otherwise>
										<option value='2'>2</option>
										<option value='3'>3</option>
										<option value='5'>5</option>
										<option value='10'>10</option>
										<option value='15'>15</option>
									</c:otherwise>
									</c:choose>
									
									
									
								</select>
								&nbsp;条
							</span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="mirp_btn" href="#">首　页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="mirp_btn" href="#">上一页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="mirp_btn" href="#">下一页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="mirp_btn" href="#">末　页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span>
								前往第&nbsp;
								<select name="toPage" class="toPage">
									<c:forEach begin="1" end="${page_users.totalPage}" var="i"> 
										<c:choose>
										<c:when test="${sessionScope.page_users.currentPage == i}">
											<option value="${i}" selected="selected">${i}</option>
										</c:when>
										<c:otherwise>
											<option value="${i}">${i}</option>
										</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
								&nbsp;页
							</span>
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
	    	
	    	/*下面三个方法都是用ajax提交到后台然后更改分页对象
	  	  更改完了要刷新页面，不然分页状态没法显示出来
	  	  中间稍微对当前页做了一个判断，如果是点上一页会给一个提示，
	  	  如果是最后一页， 点下一页会给一个提示*/
	    	$(".page_size").change(function(){
	    		var page_size_val = $(".page_size").val();
	    		$.ajax({
	    			type:"post",
	    			url:"/shopping/admin/adminUserUpdatePages.do",
	    			data:{"pageSize_str":page_size_val,"from":"page_size"},
	    			success:function(){
	    				 location.reload();
	    			}
	    		});
	    	});
	    	
	    	$(".mirp_btn").click(function(){
	    		var current_page_val = $(this).text();
	    		var total_pages_now_val = ${sessionScope.page_users.totalPage};
	    		var current_page_now_val = ${sessionScope.page_users.currentPage};
	    		if(current_page_val == "首　页") {
	    			current_page_val = 1;
	    		} else if(current_page_val == "上一页") {
	    			current_page_val = current_page_now_val - 1;
	    		} else if(current_page_val == "下一页") {
	    			current_page_val = current_page_now_val + 1;
	    		} else if(current_page_val == "末　页") {
	    			current_page_val = total_pages_now_val;
	    		} 
	    		if(current_page_val < 1) {
	    			layer.msg("现在已经是首页，没有上一页了~~");
	    		} else if(current_page_val > total_pages_now_val) {
	    			layer.msg("现在已经是末页，没有下一页了~~");
	    		} else {
		    		$.ajax({
		    			type:"post",
		    			url:"/shopping/admin/adminUserUpdatePages.do",
		    			data:{"currentPage_str":current_page_val,"from":"current_page"},
		    			success:function(msg){
		    				 location.reload();
		    			}
		    		});
	    		}
	    	});
	    	
	    	$(".toPage").change(function(){
	    		var current_page_val = $(".toPage").val();
	    		$.ajax({
	    			type:"post",
	    			url:"/shopping/admin/adminUserUpdatePages.do",
	    			data:{"currentPage_str":current_page_val,"from":"current_page"},
	    			success:function(){
	    				 location.reload();
	    			}
	    		});
	    	});
		});
	</script>   
 	</body>
</html>