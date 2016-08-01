<%@page import="com.shopping.entity.OrderSearchChoices"%>
<%@page import="com.shopping.entity.UserOrderGoodsInfo"%>
<%@page import="com.shopping.entity.GoodsInfo"%>
<%@page import="com.shopping.daoimpl.GoodsInfoDaoImpl"%>
<%@page import="com.shopping.dao.GoodsInfoDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.shopping.daoimpl.UserOrderDaoImpl"%>
<%@page import="com.shopping.dao.UserOrderDao"%>
<%@page import="com.shopping.entity.UserOrder"%>
<%@page import="com.shopping.entity.Page"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
/*这里与前面的相同，也是如果session里面存在订单的分页和搜索对象就取出，不存在就重新赋值再绑定到session里面去*/
Page p = new Page();
UserOrderDao uod = new UserOrderDaoImpl();
UserOrder uo = new UserOrder();
List<UserOrder> uos = new ArrayList<UserOrder>();
GoodsInfoDao gid = new GoodsInfoDaoImpl();
GoodsInfo gi = new GoodsInfo();
List<UserOrderGoodsInfo> uogis = new ArrayList<UserOrderGoodsInfo>();
UserOrderGoodsInfo uogi = new UserOrderGoodsInfo();
OrderSearchChoices choices = new OrderSearchChoices();
String[] goods_id_nums;
String goods_id = "";
int goods_num = 0;
if(request.getSession().getAttribute("search_order_choices") != null) {
	choices = (OrderSearchChoices)request.getSession().getAttribute("search_order_choices");

}

if(request.getSession().getAttribute("page_orders") == null) {
	p.setTotalRows(uod.countAll(choices));
	p.setPageSize(2);
	p.setCurrentPage(1);
	p.setTotalPage(p.getTotalPage());
	uos = uod.loadAll(p.getOffsetRows(), p.getPageSize(),choices);
	for(int i = 0; i < uos.size(); i++) {
		uo = new UserOrder();
		uogis = new ArrayList<UserOrderGoodsInfo>();
		uo = uos.get(i);
		goods_id_nums = uo.getGoods_id_num().split(",");
		for(int j = 0; j < goods_id_nums.length; j++) {
			goods_id = goods_id_nums[j].split(":")[0];
			goods_num = Integer.parseInt(goods_id_nums[j].split(":")[1]);
			gi = new GoodsInfo();
			gi = gid.loadRegardlessOfDeleted(goods_id);
			uogi = new UserOrderGoodsInfo();
			uogi.setGoods_id(goods_id);
			uogi.setGoods_name(gi.getGoods_name());
			uogi.setGoods_num(goods_num);
			uogi.setGoods_price(gi.getGoods_curprice());
			uogi.setGoods_img(gi.getGoods_img().split(",")[0]);
			uogis.add(uogi);
		}
		uo.setUogis(uogis);
		uos.set(i, uo);
	}
	
	p.setUos(uos);
	
	request.getSession().setAttribute("page_orders", p);
}

%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>后台管理-订单查询页</title>
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
    					<h2 class="miri_h2">订单一览</h2>
						<div class="space10px"></div>
    					<div class="miri_form">
    						<div>
    						<!-- 下面是搜索的表单模块，如果有搜索对象就取出展示出来，没有就显示默认值 -->
    							<form action="/shopping/admin/adminSearchOrders.do" method="post">
    								<span>账号：</span><input type="text" name="user_name" class="user_name" placeholder="ex:123" style="width: 100px;" value="${sessionScope.search_order_choices.user_name}"/>
    								&nbsp;&nbsp;
    								<span>订单流转状态：</span><select name="order_state" class="order_state">
									<c:choose>
									<c:when test="${sessionScope.search_order_choices.order_state == ''}">
										<option value="请选择" selected="selected">--请选择--</option>
										<option value="待支付">待支付</option>
										<option value="待发货">待发货</option>
										<option value="已发货">已发货</option>
										<option value="待评价">待评价</option>
										<option value="已评价">已评价</option>
										<option value="无效">无效</option>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:when>
									<c:when test="${sessionScope.search_order_choices.order_state == '待支付'}">
										<option value="请选择">--请选择--</option>
										<option value="待支付"  selected="selected">待支付</option>
										<option value="待发货">待发货</option>
										<option value="已发货">已发货</option>
										<option value="待评价">待评价</option>
										<option value="已评价">已评价</option>
										<option value="无效">无效</option>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:when>
									<c:when test="${sessionScope.search_order_choices.order_state == '待发货'}">
										<option value="请选择">--请选择--</option>
										<option value="待支付">待支付</option>
										<option value="待发货" selected="selected">待发货</option>
										<option value="已发货">已发货</option>
										<option value="待评价">待评价</option>
										<option value="已评价">已评价</option>
										<option value="无效">无效</option>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:when>
									<c:when test="${sessionScope.search_order_choices.order_state == '已发货'}">
										<option value="请选择">--请选择--</option>
										<option value="待支付">待支付</option>
										<option value="待发货">待发货</option>
										<option value="已发货"   selected="selected">已发货</option>
										<option value="待评价">待评价</option>
										<option value="已评价">已评价</option>
										<option value="无效">无效</option>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:when>
									<c:when test="${sessionScope.search_order_choices.order_state == '待评价'}">
										<option value="请选择">--请选择--</option>
										<option value="待支付">待支付</option>
										<option value="待发货">待发货</option>
										<option value="已发货">已发货</option>
										<option value="待评价"   selected="selected">待评价</option>
										<option value="已评价">已评价</option>
										<option value="无效">无效</option>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:when>
									<c:when test="${sessionScope.search_order_choices.order_state == '已评价'}">
										<option value="请选择">--请选择--</option>
										<option value="待支付">待支付</option>
										<option value="待发货">待发货</option>
										<option value="已发货">已发货</option>
										<option value="待评价">待评价</option>
										<option value="已评价"  selected="selected">已评价</option>
										<option value="无效">无效</option>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:when>
									<c:when test="${sessionScope.search_order_choices.order_state == '无效'}">
										<option value="请选择">--请选择--</option>
										<option value="待支付">待支付</option>
										<option value="待发货">待发货</option>
										<option value="已发货">已发货</option>
										<option value="待评价">待评价</option>
										<option value="已评价">已评价</option>
										<option value="无效" selected="selected">无效</option>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:when>
									<c:otherwise>
									<option value="请选择" selected="selected">--请选择--</option>
										<option value="待支付">待支付</option>
										<option value="待发货">待发货</option>
										<option value="已发货">已发货</option>
										<option value="待评价">待评价</option>
										<option value="已评价">已评价</option>
										<option value="无效">无效</option>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:otherwise>
									</c:choose>
									</select>
    								<span>订单删除状态：</span><select name="order_isdeleted" class="order_isdeleted">
									<c:choose>
									<c:when test="${sessionScope.search_order_choices.order_isdeleted == ''}">
										<option value="请选择" selected="selected">--请选择--</option><option value="未删除">未删除</option><option value="已删除">已删除</option><option value="不再显示">不再显示</option>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:when>
									<c:when test="${sessionScope.search_order_choices.order_isdeleted == '未删除'}">
										<option value="请选择">--请选择--</option><option value="未删除" selected="selected">未删除</option><option value="已删除">已删除</option><option value="不再显示">不再显示</option>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:when>
									<c:when test="${sessionScope.search_order_choices.order_isdeleted == '已删除'}">
										<option value="请选择">--请选择--</option><option value="未删除">未删除</option><option value="已删除" selected="selected">已删除</option><option value="不再显示">不再显示</option>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:when>
									<c:when test="${sessionScope.search_order_choices.order_isdeleted == '不再显示'}">
										<option value="请选择">--请选择--</option><option value="未删除">未删除</option><option value="已删除">已删除</option><option value="不再显示" selected="selected">不再显示</option>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:when>
									<c:otherwise>
									<option value="请选择" selected="selected">--请选择--</option><option value="未删除">未删除</option><option value="已删除">已删除</option><option value="不再显示">不再显示</option>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:otherwise>
									</c:choose>
									</select>
    								<span>金额区间：</span><input type="text" name="order_ttprice_below" class="order_ttprice_below" placeholder="" style="width: 50px;" value="${sessionScope.search_order_choices.order_ttprice_below}"/>
    								<span>-</span>
    								<input type="text" name="order_ttprice_above" class="order_ttprice_above" placeholder="" style="width: 50px;" value="${sessionScope.search_order_choices.order_ttprice_above}"/>
    								<input type="submit" value="查询" style="width: 60px;"/>
    								<!-- 这里同样的加了一个清空的按钮，实际上就是发送一个后台请求然后把session里面的搜索类重新初始化再绑定到session里面，同时回到第一页 -->
    								<input type="reset" value="清空" style="width: 60px;" onclick="window.location.href='/shopping/admin/resetOrderSearchChoice.do'"/>
    							</form>
    						</div>
<style type="text/css">
table.miri_table tbody tr td{text-align: center;padding-top: 3px;}
table.miri_table tbody tr td a.show_order_detail{display: inline-block;width: 22px;height: 22px;background: url(img/show_order_detail.png) no-repeat center center;text-indent: -99999px;}
table.miri_table tbody tr td a.show_order_detail:hover{background-image: url(img/show_order_detail_hover.png);}
.order_detail table{border: 1px solid #D3D3D3;}




	.ucro_title{border-bottom: 1px solid #D3D3D3;height: 26px;line-height: 26px;background: #F2F2F2;text-align: left;}
	.ucro_title *{font-size: 11px;}
	.ucro_title span b{color:gray;}
	
	table.ucrol_li{background:#f8f8f8;}
	.ucro_content{padding-top: 10px;padding-bottom: 10px;}
	.ucro_content a.ucroc_img{display: inline-block;padding: 3px 3px;border: 1px solid #D3D3D3;float: left;margin-left: 10px;margin-right: 10px;}
	.ucro_content a.ucroc_img img{display: inline-block;float: left;width: 50px;height: 50px;}
	.ucro_content p.ucroc_title{display: inline-block;width: 340px;margin-top: 10px;float: left;font-size: 11px;text-align: left;}
	.ucro_content span.ucroc_count{display: inline-block;width: 85px;float: left;margin-top: 10px;text-align: center;margin-left: 2px;width: 50px;color: gray;}
		.ucroc_block{float: left;margin: 5px 10px;}



</style>
    						<table class="miri_table" border="0" cellspacing="0" cellpadding="0" width="100%">
    							<thead>
	    							<tr>
	    								<th>No.</th>
	    								<th>用户账号</th>
	    								<th>订单金额</th>
	    								<th>收货地址</th>
	    								<th>订单流转状态</th>
	    								<th>订单删除状态：</th>
	    								<th>创建时间</th>
	    								<th>查看详情</th>
	    								<th>操作</th>
	    							</tr>
    							</thead>
    							<tbody>
    							<!-- 下面就是循环显示订单信息 -->
    								<c:forEach items="${sessionScope.page_orders.uos}" var="userorder" varStatus="status">
		    							<tr class="${userorder.userorder_id}">
		    								<td>${status.index+1}</td>
		    								<td>${userorder.user_name}</td>
		    								<td><fmt:formatNumber type="number" value="${userorder.order_ttprice}" maxFractionDigits="2" minFractionDigits="2"/></td>
		    								<td>${userorder.order_address}</td>
		    								<c:choose>
											<c:when test="${userorder.order_state == 0}">
												<td>待支付</td>
											</c:when>
											<c:when test="${userorder.order_state == 1}">
												<td>待发货</td>
											</c:when>
											<c:when test="${userorder.order_state == 2}">
												<td>待签收</td>
											</c:when>
											<c:when test="${userorder.order_state == 3}">
												<td>待评价</td>
											</c:when>
											<c:when test="${userorder.order_state == 4}">
												<td>已评价</td>
											</c:when>
											<c:when test="${userorder.order_state == 5}">
												<td>无效</td>
											</c:when>
											<c:otherwise>
												<td>无状态</td>
											</c:otherwise>
											</c:choose>
		    								<c:choose>
											<c:when test="${userorder.order_isdeleted == 0}">
												<td>未删除</td>
											</c:when>
											<c:when test="${userorder.order_isdeleted == 1}">
												<td>已删除</td>
											</c:when>
											<c:when test="${userorder.order_isdeleted == 2}">
												<td>不再显示</td>
											</c:when>
											<c:otherwise>
												<td>无状态</td>
											</c:otherwise>
											</c:choose>
											<td>${userorder.order_createtime}</td>
		    								<td>
		    									<a href="JavaScript:void(0);" class="show_order_detail">show_order_detail</a>
		    								</td>
		    								<td>
		    									<c:choose>
												<c:when test="${userorder.order_state == 0}">
													<a href="JavaScript:void(0);" class="mirif_operate">设为无效</a>
												</c:when>
												<c:when test="${userorder.order_state == 1}">
													<a href="JavaScript:void(0);" class="mirif_operate">立即发货</a>
												</c:when>
												<c:when test="${userorder.order_state == 5}">
													<a href="JavaScript:void(0);" class="mirif_operate">删除</a>
												</c:when>
												<c:otherwise>
													<span>无操作</span>
												</c:otherwise>
												</c:choose>
		    								</td>
		    							</tr>
		    							<!--下面是动态显示order详情数据-->
		    							<tr>
		    								<td colspan="9">
		    									<div class="order_detail" style="display: none;">
		    										
													<table class="ucrol_li" border="0" cellspacing="0" cellpadding="0" width="100%">
														<tr>
															<td colspan="4">
																<div class="ucro_title">
																	&nbsp;
																	<span>${userorder.order_createtime}</span>
																	&nbsp;&nbsp;&nbsp;
																	<span><b>订单号：</b>${userorder.userorder_id}</span>
																</div>
															</td>
														</tr>
														<tr class="ucro_content">
															<td>
																<c:forEach items="${userorder.uogis}" var="goodsinfo" varStatus="i">
																	<div class="ucroc_block">
																		<a href="/shopping/user/page_goods/detail.html?goods_id=${goodsinfo.goods_id}" class="ucroc_img">
																			<img src="/shopping/goodsImg/${goodsinfo.goods_img}"/>
																		</a>
																		<p class="ucroc_title">
																			${goodsinfo.goods_name}
																		</p>
																		<span class="ucroc_count">x${goodsinfo.goods_num}</span><br />
																		<%-- <span class="">成交价：<b>${goodsinfo.goods_price}</b>元</span> --%>
																	</div>
																</c:forEach>
															</td>
														</tr>
													</table>
												</div>
		    								</td>
		    							</tr>
	    							</c:forEach>
	    							
    							</tbody>
    						</table>
    						<div class="space20px"></div>
    					</div>

    					<div class="space20px"></div>
    					<!-- 下面是分页的模块 -->
						<div class="mir_page">
							<span>当前第&nbsp;<b>${sessionScope.page_orders.currentPage}</b>&nbsp;页，共计&nbsp;<b>${page_orders.totalPage}</b>&nbsp;页</span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span>
								每页显示&nbsp;
								<select name="page_size" class="page_size">
									<%-- <option value="default">${page_keys.pageSize}</option> --%>
									<c:choose>
									<c:when test="${sessionScope.page_orders.pageSize == 2}">
										<option value='2' selected="selected">2</option>
										<option value='3'>3</option>
										<option value='5'>5</option>
										<option value='10'>10</option>
										<option value='15'>15</option>
									</c:when>
									<c:when test="${sessionScope.page_orders.pageSize == 3}">
										<option value='2'>2</option>
										<option value='3' selected="selected">3</option>
										<option value='5'>5</option>
										<option value='10'>10</option>
										<option value='15'>15</option>
									</c:when>
									<c:when test="${sessionScope.page_orders.pageSize == 5}">
										<option value='2'>2</option>
										<option value='3'>3</option>
										<option value='5' selected="selected">5</option>
										<option value='10'>10</option>
										<option value='15'>15</option>
									</c:when>
									<c:when test="${sessionScope.page_orders.pageSize == 10}">
										<option value='2'>2</option>
										<option value='3'>3</option>
										<option value='5'>5</option>
										<option value='10' selected="selected">10</option>
										<option value='15'>15</option>
									</c:when>
									<c:when test="${sessionScope.page_orders.pageSize == 15}">
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
									<c:forEach begin="1" end="${page_orders.totalPage}" var="i"> 
										<c:choose>
										<c:when test="${sessionScope.page_orders.currentPage == i}">
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
    					

<script src="js/jQuery.1.8.3.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/shopping/layer/layer.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(function(){
	$(".show_order_detail").live("click",function(){
		var $this = $(this) ;
		$this.parent("td").parent("tr").next("tr").children("td").children(".order_detail").toggle();
	});
	
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
			url:"/shopping/admin/adminOrderUpdatePages.do",
			data:{"pageSize_str":page_size_val,"from":"page_size"},
			success:function(){
				 location.reload();
			}
		});
	});
	
	$(".mirp_btn").click(function(){
		var current_page_val = $(this).text();
		var total_pages_now_val = ${sessionScope.page_orders.totalPage};
		var current_page_now_val = ${sessionScope.page_orders.currentPage};
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
    			url:"/shopping/admin/adminOrderUpdatePages.do",
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
			url:"/shopping/admin/adminOrderUpdatePages.do",
			data:{"currentPage_str":current_page_val,"from":"current_page"},
			success:function(){
				 location.reload();
			}
		});
	});
	
	//下面是更改订单状态的ajax，主要就是发货，设置无效以及删除（这里是真删除）
	//如果后台处理没问题，那么前台重新刷新页面
	$(".mirif_operate").live("click",function(){
		var order_id_val = $(this).parent().parent().attr("class");
		var operation_val = $(this).text();
		//alert(order_id_val);
		$.ajax({
    		type:"post",
    		url:"/shopping/admin/adminUpdateOrderState.do",
    		data:{"userorder_id":order_id_val,"operation":operation_val},
    		success:function(msg){
    			layer.confirm(msg, {
					 btn: ["确认"] 
					,btn1: function(index, layero){
					    window.location.href = "/shopping/admin/order.jsp";
					 }
				});
    		}
    	}); 
		
	});
});
</script>    	
 	</body>
</html>