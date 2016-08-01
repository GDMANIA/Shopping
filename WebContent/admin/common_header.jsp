<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
/*这段代码是判断管理员有没有登录，先找cookies值，再找cookies下面对应的admin的值，
如果都为空那么意味着没登录，直接转发到管理员登录界面，并给一个错误提示*/
Cookie[] cookies = request.getCookies();
boolean flag_login = false;
if(cookies != null) {
	for(int i = 0; i < cookies.length; i++) {
		if("admin".equals(cookies[i].getName())) {
			flag_login = true;
			if(cookies[i].getValue() == null || cookies[i].getValue() == "" || "null".equals(cookies[i].getValue())) {
				flag_login = false;
			}
		} 
	}
}
if(!flag_login) {
	String adminLoginWrongMsg = "您尚未登录！";
	request.setAttribute("adminLoginWrongMsg", adminLoginWrongMsg);
	request.getRequestDispatcher("/admin/admin_login.jsp").forward(request, response);
}

/*下面这段代码是为了清空一下四个页面的分页和搜索信息的，
如果当前页面的Url不是下面四个页面对应的url，那么就把分页信息和搜索信息清空，
实际效果就是链接到其他页面再点回来的时候，分页和搜索信息不能继续存在在页面上，应该还原*/
String page_url = request.getRequestURI().split("/")[3];
if(!"goods.jsp".equals(page_url)) {
	request.getSession().removeAttribute("page_goodsinfos");
	request.getSession().removeAttribute("search_goodsinfo_choices");
}
if(!"categorysecond.jsp".equals(page_url)) {
	request.getSession().removeAttribute("page_keys");
}
if(!"user.jsp".equals(page_url)) {
	request.getSession().removeAttribute("page_users");
	request.getSession().removeAttribute("search_user_choices");
}
if(!"order.jsp".equals(page_url)) {
	request.getSession().removeAttribute("page_orders");
	request.getSession().removeAttribute("search_order_choices");
}
%>

<div class="header">
	<div class="header_inner">
		<div class="hi_left">
			<a href="main.jsp" class="hil_logo">LOGO</a>
		</div>
		<div class="hi_center">
			<h1>电子商务后台管理系统</h1>
		</div>
		<div class="hi_right">
		<!-- 下面用EL表达式拿出cookie中存的管理员名，并给一个退出登录的链接 -->
			<span>当前用户：<b>${cookie.admin.value}</b></span>　
			<a href="/shopping/admin/adminLogOut.do">退出登录</a>
		</div>
		<div class="clear"></div>
	</div>
</div>
