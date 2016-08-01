<%@page import="com.shopping.entity.GoodsSearchChoices"%>
<%@page import="com.shopping.entity.AdminLoginRecord"%>
<%@page import="com.shopping.daoimpl.AdminLoginRecordDaoImpl"%>
<%@page import="com.shopping.daoimpl.UserOrderDaoImpl"%>
<%@page import="com.shopping.daoimpl.GoodsInfoDaoImpl"%>
<%@page import="com.shopping.daoimpl.UserDaoImpl"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" import="java.util.*,com.shopping.dao.*" %>
<%
//String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";
/*
这里的一段代码是为了拿到main.jsp页面需要显示的所有信息，各项数据，以及管理员登录记录等，
拿到以后存入request里面再在页面中用EL表达式取出
*/



String currentTime = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
int user_ttnum = 0;
int donedeal_user_num = 0;
double total_user_payment = 0;
double average_user_payment = 0;
int order_ttnum = 0;
int order_paid_ttnum = 0;
int order_success_ttnum = 0;
double order_success_scale = 0;
int goodsinfo_ttnum = 0;
int goodsinfo_onsite_ttnum = 0;
int goodsinfo_tobeonsite_ttnum = 0;
double goodsinfo_onsite_scale = 0;

UserDao ud = new UserDaoImpl();
GoodsInfoDao gid = new GoodsInfoDaoImpl();
UserOrderDao uod = new UserOrderDaoImpl();
user_ttnum = ud.countAll();
donedeal_user_num = ud.countAllDeal();
total_user_payment = ud.countAllPayment();
average_user_payment = total_user_payment / donedeal_user_num;
order_ttnum = uod.countAll();
order_paid_ttnum = uod.countPaid();
order_success_ttnum = uod.countSuccess();
order_success_scale = order_success_ttnum / order_ttnum * 100;
GoodsSearchChoices choices = new GoodsSearchChoices();

goodsinfo_ttnum = gid.countAll(choices);
goodsinfo_onsite_ttnum = gid.getTotalRows(choices);
goodsinfo_tobeonsite_ttnum = goodsinfo_ttnum - goodsinfo_onsite_ttnum;
goodsinfo_onsite_scale = goodsinfo_onsite_ttnum / goodsinfo_ttnum * 100;

AdminLoginRecordDao alrd = new AdminLoginRecordDaoImpl();
List<AdminLoginRecord> alrs = new ArrayList<AdminLoginRecord>();
List<AdminLoginRecord> alrs_site = new ArrayList<AdminLoginRecord>();
alrs = alrd.selectLatestFive();
if(alrs.size() != 0) {
	for(int i = alrs.size()-1; i >= 0; i--) {
		alrs_site.add(alrs.get(i));
	}
}

request.setAttribute("currentTime", currentTime);
request.setAttribute("user_ttnum", user_ttnum);
request.setAttribute("donedeal_user_num", donedeal_user_num);
request.setAttribute("total_user_payment", String.format("%.2f", total_user_payment));
request.setAttribute("average_user_payment", String.format("%.2f", average_user_payment));
request.setAttribute("order_ttnum", order_ttnum);
request.setAttribute("order_paid_ttnum", order_paid_ttnum);
request.setAttribute("order_success_ttnum", order_success_ttnum);
request.setAttribute("order_success_scale", String.format("%.2f", order_success_scale));
request.setAttribute("goodsinfo_ttnum", goodsinfo_ttnum);
request.setAttribute("goodsinfo_onsite_ttnum", goodsinfo_onsite_ttnum);
request.setAttribute("goodsinfo_tobeonsite_ttnum", goodsinfo_tobeonsite_ttnum);
request.setAttribute("goodsinfo_onsite_scale", String.format("%.2f", goodsinfo_onsite_scale));
request.setAttribute("admin_login_records", alrs_site);

%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>后台管理-主页</title>
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
<style type="text/css">
.mirit_info{
	width:250px;float: left;margin-left: 50px;background: #F2F2F2;
/*		-moz-border-radius: 8px;
	-webkit-border-radius: 8px;
	border-radius: 8px;
	-moz-box-shadow: 0px 0px 6px gray;
	-webkit-box-shadow: 0px 0px 6px gray;
	box-shadow: 0px 0px 6px gray;*/
}
.mirit_info h3{height: 40px;line-height: 40px;background: #D3D3D3;text-align: center;}
.mirit_info span{display: inline-block;margin:10px 10px;}
.mirit_info span b{font-size: 18px;color: green;}
.mirit_info .miriti_bigfont{font-size: 30px;color: #FF0000;}

.miric_block{width: 470px;min-height: 250px;max-height: 250px;float: left;margin:15px 10px;
/*	-moz-box-shadow: 0px 0px 6px gray;
	-webkit-box-shadow: 0px 0px 6px gray;
	box-shadow: 0px 0px 6px gray;*/
}
.miric_block h2{height: 40px;line-height: 40px;background: #F2F2F2;text-indent: 1em;}
</style>
    			<div class="mi_right">
    				<div class="mir_inner">
    					<h2 class="miri_h2">全站信息一览</h2>
    					<div class="miri_total" style="margin: 15px 10px;">
    						<div class="mirit_info">
    							<h3>用户统计</h3>
    							<span>用户数：<b>${user_ttnum}</b></span><br />
    							<span>成交用户数：<b>${donedeal_user_num}</b></span><br />
    							<span>消费总额：<b>${total_user_payment}</b></span><br />
    							<span>平均消费：<b class="miriti_bigfont">${average_user_payment}</b></span>
    						</div>
    						<div class="mirit_info">
    							<h3>订单统计</h3>
    							<span>订单总数：<b>${order_ttnum}</b></span><br />
    							<span>已支付订单：<b>${order_paid_ttnum}</b></span><br />
    							<span>交易成功：<b>${order_success_ttnum}</b></span><br />
    							<span>交易成功比例：<b class="miriti_bigfont">${order_success_scale}%</b></span>
    						</div>
    						<div class="mirit_info">
    							<h3>商品统计</h3>
    							<span>商品总数：<b>${goodsinfo_ttnum}</b></span><br />
    							<span>已上架：<b>${goodsinfo_onsite_ttnum}</b></span><br />
    							<span>待上架：<b>${goodsinfo_tobeonsite_ttnum}</b></span><br />
    							<span>上架比例：<b class="miriti_bigfont">${goodsinfo_onsite_scale}%</b></span>
    						</div>
    						<div class="clear"></div>
    					</div>
    					<div class="miri_content">
    						<div class="miric_block">
    							<h2>服务器统计信息</h2>
    							<div class="space10px"></div>
    							<div class="miricb_content">
    								<table border="0" cellspacing="0" cellpadding="0" width="100%">
    									<tr><td>获取HTTP头文件中Host的值： </td><td><% out.println(request.getHeader("Host"));%> </td></tr>
    									<tr><td>获取HTTP头文件中accept-encoding的值： </td><td><% out.println(request.getHeader("accept-encoding"));%> </td></tr>
    									<tr><td>获取客户的IP地址： </td><td><% out.println(request.getRemoteAddr()); %> </td></tr>
    									<tr><td>获取客户机的名称： </td><td><% out.println(request.getRemoteHost());%> </td></tr>
    									<tr><td>获取服务器的名称： </td><td><% out.println(request.getServerName());%> </td></tr>
    									<tr><td>获取服务器的端口号： </td><td><% out.println(request.getServerPort());%> </td></tr>
    								</table>
    							</div>
    						</div>
    						<div class="miric_block">
    							<h2>管理登录记录</h2>
    							<div class="space10px"></div>
    							<div class="miricb_content">
    								<table border="0" cellspacing="0" cellpadding="0" width="100%">
    									<tr><th>No.</th><th>admin</th><th>time</th></tr>
    									<%-- <c:if test="${requestScope.admin_login_records.size() == 0} ">
											<span>暂时没有管理员登陆记录</span>
										</c:if> --%>
										<!-- 为什么这里if不起作用？ -->
										<%-- <c:if test="${admin_login_records.size() != 0} "> --%>
	    									<c:forEach items="${requestScope.admin_login_records}" var="admin_login_record" varStatus="status">
												<tr><td>${status.index+1}</td><td>${admin_login_record.administrator_name}</td><td>${admin_login_record.login_time}</td></tr>
											</c:forEach>
										<%-- </c:if> --%>
    								</table>
    							</div>
    						</div>
    						<div class="clear"></div>
    					</div>
    				</div>
    			</div>
    			<div class="clear"></div>
    		</div>
    	</div>
    	
    	<%@ include file="common_footer.jsp" %> 
		<script src="/shopping/js/jQuery.1.8.3.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">   	
		$(document).ready(function(){
			/*下面的ajax是为了从数据库拿到当前网站的Logo图片和版权信息*/
	    	$.ajax({
				type:"post",
				url:"/shopping/user/getLogoAndCopyright.do",
				success:function(data){
					LogoAndCopyright_val = eval("("+data+")");
					$(".hil_logo").attr("style","background: url(/img/"+LogoAndCopyright_val.logo_img+");");
					$(".footer_inner p").text(LogoAndCopyright_val.copyright_detail);
				}
			});
		});
    	</script>
 	</body>
</html>
