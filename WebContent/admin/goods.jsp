<%@page import="java.util.regex.Pattern"%>
<%@page import="com.shopping.entity.GoodsSearchChoices"%>
<%@page import="com.shopping.entity.Keyword"%>
<%@page import="com.shopping.dao.KeywordDao"%>
<%@page import="com.shopping.daoimpl.KeywordDaoImpl"%>
<%@page import="com.shopping.daoimpl.CategoryDaoImpl"%>
<%@page import="com.shopping.dao.CategoryDao"%>
<%@page import="com.shopping.entity.Category"%>
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
/* 这个页面全是用jsp表达式直接写的，没有用JSTL和EL表达式，因为数据库里面商品的分类字段存的是ID，而页面上要显示的是NAME，用EL表达式不方便，所以干脆直接用JSP*/
Page p = new Page();
GoodsInfoDao gid = new GoodsInfoDaoImpl();
GoodsInfo gi = new GoodsInfo();
List<GoodsInfo> gis = new ArrayList<GoodsInfo>();
GoodsSearchChoices choices = new GoodsSearchChoices();
CategoryDao cd = new CategoryDaoImpl();
KeywordDao kd = new KeywordDaoImpl();
Category cat = null;
Keyword key = new Keyword();
List<Category> cats = cd.selectAll();
List<Keyword> keys = null;
List<Keyword> all_keys = kd.loadAll();
for (int i = 0; i < cats.size(); i++) {
	keys = new ArrayList<Keyword>();
	cat = new Category();
	cat = cats.get(i);
	keys = kd.loadByCategory(cat.getCat_id());
	for (int j = 0; j < keys.size(); j++) {
		key = new Keyword();
		key = keys.get(j);
		key.setCat_name(cat.getCat_name());
		keys.set(j, key);
	}
	cat.setKeys(keys);
	cats.set(i, cat);
}
/*这里先判断一下搜索商品信息的类在session里面存在与否，如果不存在就初始化一下，
同时这里需要判断一下搜索类里面存的主分类和二级分类是数字还id是中文名，如果是数字
需要转化为中文名，因为前台输出的要求是中文名*/
if(request.getSession().getAttribute("search_goodsinfo_choices") != null) {
	choices = (GoodsSearchChoices)request.getSession().getAttribute("search_goodsinfo_choices");
	String goods_cat = choices.getGoods_cat();
	String goods_key = choices.getGoods_key();
	String test_num = "(^[0-9]*$)";
	if(goods_cat != null && goods_cat != "" && !goods_cat.isEmpty()) {
		if(Pattern.matches(test_num, goods_cat)) {
			for (int i = 0; i < cats.size(); i++) {
				if(Integer.parseInt(goods_cat) == cats.get(i).getCat_id()) {
					goods_cat = cats.get(i).getCat_name();
					choices.setGoods_cat(goods_cat);
					break;
				}
			}
		}
	}
	if(goods_key !=null && goods_key != "" && !goods_key.isEmpty()) {
		if(Pattern.matches(test_num, goods_key)) {
			for (int i = 0; i < all_keys.size(); i++) {
				if(Integer.parseInt(goods_key) == all_keys.get(i).getKey_id()) {
					goods_key = all_keys.get(i).getKey_name();
					choices.setGoods_key(goods_key);
					break;
				}
			}
		}
	} 
	
	request.getSession().setAttribute("search_goodsinfo_choices", choices);
}

/*这里先判断session里面是否存在商品信息的分页对象，如果不存在就初始化一个对象再绑定到session里面*/
if(request.getSession().getAttribute("page_goodsinfos") == null) {
	p.setTotalRows(gid.countAll(choices));
	p.setPageSize(10);
	p.setCurrentPage(1);
	p.setTotalPage(p.getTotalPage());
	gis = gid.loadAll(p.getOffsetRows(), p.getPageSize(),choices);
	p.setGis(gis);
	
	request.getSession().setAttribute("page_goodsinfos", p);
}

int cat_id = 0;
int key_id = 0;
String cat_name = "";
String key_name = "";

%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>后台管理-商品管理</title>
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
    					<h2 class="miri_h2">商品管理<small>(每页显示${sessionScope.page_goodsinfos.pageSize}条商品数据)</small></h2>
    					<div class="space10px"></div>
						<div>
						<!-- 如果已经进行了搜索，那么此页面的表单上应该显示出搜索所使用的条件，那么就在这里用el表达式取出session里面的搜索类的值 -->
							<form action="/shopping/admin/adminSearchGoodsInfo.do" method="post">
								<input type="button" name="button" onclick="window.location.href='goods_add.jsp'"  style="display: inline-block;width:80px;float: left;margin-right: 50px;" value="+新增商品" />
								
								<span>标题：</span><input type="text" name="goods_name" class="goods_name" placeholder="ex:时尚" style="width: 100px;" value="${sessionScope.search_goodsinfo_choices.goods_name}"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>简介：</span><input type="text" name="goods_info" class="goods_info" placeholder="ex:时尚" style="width: 100px;" value="${sessionScope.search_goodsinfo_choices.goods_info}"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>主分类：</span><input type="text" name="goods_cat" class="goods_cat" placeholder="ex:女装" style="width: 100px;" value="${sessionScope.search_goodsinfo_choices.goods_cat}"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>次分类：</span><input type="text" name="goods_key" class="goods_key" placeholder="ex:时尚" style="width: 100px;" value="${sessionScope.search_goodsinfo_choices.goods_key}"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br>
								<span>价格：</span><input type="text" name="goods_price_below" class="goods_price_below" placeholder="" style="width: 50px;" value="${sessionScope.search_goodsinfo_choices.goods_price_below}"/>
								<span>-</span>
								<input type="text" name="goods_price_above" class="goods_price_above" placeholder="" style="width: 50px;" value="${sessionScope.search_goodsinfo_choices.goods_price_above}"/>
								<span>库存：</span><input type="text" name="goods_ttnum_below" class="goods_ttnum_below" placeholder="" style="width: 50px;" value="${sessionScope.search_goodsinfo_choices.goods_ttnum_below}"/>
								<span>-</span>
								<input type="text" name="goods_ttnum_above" class="goods_ttnum_above" placeholder="" style="width: 50px;" value="${sessionScope.search_goodsinfo_choices.goods_ttnum_above}"/>
								<span>售出数量：</span><input type="text" name="goods_soldnum_below" class="goods_soldnum_below" placeholder="" style="width: 50px;" value="${sessionScope.search_goodsinfo_choices.goods_soldnum_below}"/>
								<span>-</span>
								<input type="text" name="goods_soldnum_above" class="goods_soldnum_above" placeholder="" style="width: 50px;" value="${sessionScope.search_goodsinfo_choices.goods_soldnum_above}"/>
								<span>评价数量：</span><input type="text" name="goods_commentnum_below" class="goods_commentnum_below" placeholder="" style="width: 50px;" value="${sessionScope.search_goodsinfo_choices.goods_commentnum_below}"/>
								<span>-</span>
								<input type="text" name="goods_commentnum_above" class="goods_commentnum_above" placeholder="" style="width: 50px;" value="${sessionScope.search_goodsinfo_choices.goods_commentnum_above}"/>
								<br><br>
								<span>上架状态：</span><select name="goods_isonsite" class="goods_isonsite">
								<c:choose>
								<c:when test="${sessionScope.search_goodsinfo_choices.goods_isonsite == ''}">
									<option value="请选择" selected="selected">--请选择--</option><option value="已上架">已上架</option><option value="未上架">未上架</option></select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</c:when>
								<c:when test="${sessionScope.search_goodsinfo_choices.goods_isonsite == '1'}">
									<option value="请选择">--请选择--</option><option value="已上架" selected="selected">已上架</option><option value="未上架">未上架</option></select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</c:when>
								<c:when test="${sessionScope.search_goodsinfo_choices.goods_isonsite == '0'}">
									<option value="请选择">--请选择--</option><option value="已上架">已上架</option><option value="未上架" selected="selected">未上架</option></select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</c:when>
								<c:otherwise>
								<option value="请选择" selected="selected">--请选择--</option><option value="已上架">已上架</option><option value="未上架">未上架</option></select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</c:otherwise>
								</c:choose>
								<input type="submit" value="搜索" style="width: 60px;"/>
								<!-- 这里加了一个清空的按钮，实际上就是发送一个后台请求然后把session里面的搜索类重新初始化再绑定到session里面，同时回到第一页 -->
								<input type="reset" value="清空" style="width: 60px;" onclick="window.location.href='/shopping/admin/adminResetGoodsSearchChoice.do'"/>
							</form>
						</div>
						<div class="space10px"></div>
    					<div class="miri_form">
    						<table border="0" cellspacing="0" cellpadding="0" width="1200">
    							<thead>
	    							<tr>
	    								<th>No.</th>
	    								<th>操作</th>
	    								<th>主分类</th>
	    								<th>次分类</th>
	    								<th>图片</th>
	    								<th>标题</th>
	    								<th>描述</th>
	    								<th>原价</th>
	    								<th>现价</th>
	    								<th>库存</th>
	    								<th>已售件数</th>
	    								<th>评价次数</th>
	    								<th>点击次数</th>
	    								<th>创建时间</th>
	    							</tr>
    							</thead>
    							<tbody>
    								<%
    									//下面是遍历循环page对象里面存的商品信息的List并在页面中展示的代码
    									Page page_val = new Page();
    									page_val = (Page)request.getSession().getAttribute("page_goodsinfos");
    									gis = new ArrayList<GoodsInfo>();
    									gis = page_val.getGis();
    									String[] goods_imgs = new String[5];
    									//由于数据库里面图片只存了一个字段，所以这里要重新用一个数组取出
    									for(int i = 0; i < gis.size(); i++) {
    										goods_imgs = new String[5];
    										gi = new GoodsInfo();
    										gi = gis.get(i);
    								%>
	    								<tr class="goods_id_<%=gi.getGoods_id() %>">
		    								<td><%=i+1 %></td>
		    								<td>
		    								<!-- 下面要先对商品状态进行一个判断，从而决定最后能对该商品进行什么操作 -->
		    									<a href="goods_modify.jsp?goods_id=<%=gi.getGoods_id() %>" class="mirif_operate">修改</a>
		    									<a href="JavaScript:void(0);" class="mirif_operate">删除</a>
		    									<%
		    										if(gi.getGoods_isonsite() == 0) {
		    									%>
													<a href="JavaScript:void(0);" class="mirif_operate">上架</a>
												<%
		    										} else {
												%>
													<a href="JavaScript:void(0);" class="mirif_operate">下架</a>
												<%
		    										}
												%>
		    									
		    								</td>
		    								<%
		    									cat_id = 0;
		    									key_id = 0;
		    									cat_name = "";
		    									key_name = "";
		    									cat_id = gi.getGoods_cat();
		    									key_id = gi.getGoods_key();
		    									goods_imgs = gi.getGoods_img().split(",");
		    									outer:for(int j = 0; j < cats.size(); j++) {
													if(cats.get(j).getCat_id() == cat_id) {
														cat_name = cats.get(j).getCat_name();
														inner:for(int g = 0; g < cats.get(j).getKeys().size(); g++) {
															if(cats.get(j).getKeys().get(g).getKey_id() == key_id) {
																key_name = cats.get(j).getKeys().get(g).getKey_name();
																break inner;
															}
														}
														break outer;
													}
		    									}
		    								%>
		    								<td><%=cat_name %></td>
		    								<td><%=key_name %></td>
		    								<td>
		    									<img src="/goodsimg/<%=goods_imgs[0]%>"/>
		    									<img src="/goodsimg/<%=goods_imgs[1]%>"/>
		    									<img src="/goodsimg/<%=goods_imgs[2]%>"/>
		    									<img src="/goodsimg/<%=goods_imgs[3]%>"/>
		    									<img src="/goodsimg/<%=goods_imgs[4]%>"/>
		    								</td>
		    								<td>
		    									<p class="mirif_title" title="<%=gi.getGoods_name() %>">
		    										<%=gi.getGoods_name() %>
		    									</p>
		    								</td>
		    								<td>
		    									<p class="mirif_desc" title="<%=gi.getGoods_info() %>">
		    										<%=gi.getGoods_info() %>
		    									</p>
		    								</td>
		    								<td><%=String.format("%.2f", gi.getGoods_oriprice()) %></td>
		    								<td><%=String.format("%.2f", gi.getGoods_curprice()) %></td>
		    								<td><%=gi.getGoods_ttnum() %></td>
		    								<td><%=gi.getGoods_soldnum() %></td>
		    								<td><%=gi.getGoods_commentnum() %></td>
		    								<td><%=gi.getGoods_clicknum() %></td>
		    								<td><%=gi.getGoods_createtime() %></td>
		    								
		    							</tr>
		    						<%
    									}
		    						%>
    							</tbody>
    						</table>
    						<div class="space20px"></div>
    					</div>
							<div class="space20px"></div>
							<!-- 下面是分页的模块 -->
    						<div class="mir_page">
							<span>当前第&nbsp;<b>${sessionScope.page_goodsinfos.currentPage}</b>&nbsp;页，共计&nbsp;<b>${sessionScope.page_goodsinfos.totalPage}</b>&nbsp;页</span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span>
								每页显示&nbsp;
								<select name="page_size" class="page_size">
									<%-- <option value="default">${page_keys.pageSize}</option> --%>
									<c:choose>
									<c:when test="${sessionScope.page_goodsinfos.pageSize == 10}">
										<option value='10' selected="selected">10</option>
										<option value='15'>15</option>
										<option value='20'>20</option>
										<option value='30'>30</option>
										<option value='50'>50</option>
									</c:when>
									<c:when test="${sessionScope.page_goodsinfos.pageSize == 15}">
										<option value='10'>10</option>
										<option value='15' selected="selected">15</option>
										<option value='20'>20</option>
										<option value='30'>30</option>
										<option value='50'>50</option>
									</c:when>
									<c:when test="${sessionScope.page_goodsinfos.pageSize == 20}">
										<option value='10'>10</option>
										<option value='15'>15</option>
										<option value='20' selected="selected">20</option>
										<option value='30'>30</option>
										<option value='50'>50</option>
									</c:when>
									<c:when test="${sessionScope.page_goodsinfos.pageSize == 30}">
										<option value='10'>10</option>
										<option value='15'>15</option>
										<option value='20'>20</option>
										<option value='30' selected="selected">30</option>
										<option value='50'>50</option>
									</c:when>
									<c:when test="${sessionScope.page_goodsinfos.pageSize == 50}">
										<option value='10'>10</option>
										<option value='15'>15</option>
										<option value='20'>20</option>
										<option value='30'>30</option>
										<option value='50' selected="selected">50</option>
									</c:when>
									<c:otherwise>
										<option value='10'>10</option>
										<option value='15'>15</option>
										<option value='20'>20</option>
										<option value='30'>30</option>
										<option value='50'>50</option>
									</c:otherwise>
									</c:choose>
									
									
									
								</select>
								&nbsp;条
							</span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="mirp_btn" href="JavaScript:void(0);">首　页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="mirp_btn" href="JavaScript:void(0);">上一页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="mirp_btn" href="JavaScript:void(0);">下一页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="mirp_btn" href="JavaScript:void(0);">末　页</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span>
								前往第&nbsp;
								<select name="toPage" class="toPage">
									<c:forEach begin="1" end="${sessionScope.page_goodsinfos.totalPage}" var="i"> 
										<c:choose>
										<c:when test="${sessionScope.page_goodsinfos.currentPage == i}">
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
			/*后台request.setAttribute()设置错误信息，前台用EL表达式拿到，再用layer.confirm()提示用户
	    	为了防止重复提示，用户点完确认以后再重定向到本页面，这里会拿到两个页面传过来的错误或正确信息，一个是修改商品的页面，一个是添加商品的页面 */
			var wrong_msg = "${updateGoodsDetailWrongMsg}";
			//alert(wrong_msg);
			if(wrong_msg != "") {
				layer.confirm(wrong_msg, {
					 btn: ["确认"] 
					,btn1: function(index, layero){
					    window.location.href = "/shopping/admin/goods.jsp";
					 }
				});
			}
			
			wrong_msg = "${addGoodsDetailWrongMsg}";
			//alert(wrong_msg);
			if(wrong_msg != "") {
				layer.confirm(wrong_msg, {
					 btn: ["确认"] 
					,btn1: function(index, layero){
					    window.location.href = "/shopping/admin/goods.jsp";
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
	    	
	    	/*下面三个方法都是用ajax提交到后台然后更改分页对象
	    	  更改完了要刷新页面，不然分页状态没法显示出来
	    	  中间稍微对当前页做了一个判断，如果是点上一页会给一个提示，
	    	  如果是最后一页， 点下一页会给一个提示*/
	    	$(".page_size").change(function(){
	    		var page_size_val = $(".page_size").val();
	    		$.ajax({
	    			type:"post",
	    			url:"/shopping/admin/adminGoodsInfoUpdatePages.do",
	    			data:{"pageSize_str":page_size_val,"from":"page_size"},
	    			success:function(){
	    				 location.reload();
	    			}
	    		});
	    	});
	    	
	    	$(".mirp_btn").click(function(){
	    		var current_page_val = $(this).text();
	    		var total_pages_now_val = ${sessionScope.page_goodsinfos.totalPage};
	    		var current_page_now_val = ${sessionScope.page_goodsinfos.currentPage};
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
		    			url:"/shopping/admin/adminGoodsInfoUpdatePages.do",
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
	    			url:"/shopping/admin/adminGoodsInfoUpdatePages.do",
	    			data:{"currentPage_str":current_page_val,"from":"current_page"},
	    			success:function(){
	    				 location.reload();
	    			}
	    		});
	    	});
	    	
	    	//下面是更改商品状态的ajax，主要就是上架下架以及删除（这里做了假删除），如果后台处理没问题，那么前台重新刷新页面
	    	$(".mirif_operate").live("click",function(){
	    		var goods_id_val = $(this).parent().parent().attr("class");
	    		var operation_val = $(this).text();
	    		$.ajax({
		    		type:"post",
		    		url:"/shopping/admin/adminUpdateGoodsState.do",
		    		data:{"goods_id":goods_id_val,"operation":operation_val},
		    		success:function(msg){
						if(msg == "success") {
							location.reload();
						} 
		    		}
		    	}); 
				
	    	});
		    	
		});
    	</script>    	
 	</body>
</html>