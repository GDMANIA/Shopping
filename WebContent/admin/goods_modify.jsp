<%@page import="com.shopping.entity.GoodsInfo"%>
<%@page import="com.shopping.daoimpl.GoodsInfoDaoImpl"%>
<%@page import="com.shopping.dao.GoodsInfoDao"%>
<%@page import="com.shopping.daoimpl.CategoryDaoImpl"%>
<%@page import="com.shopping.daoimpl.KeywordDaoImpl"%>
<%@page import="com.shopping.dao.CategoryDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shopping.entity.Category"%>
<%@page import="com.shopping.entity.Keyword"%>
<%@page import="com.shopping.dao.KeywordDao"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
/*这里拿到地址栏传过来的goods_id，然后取出相应的商品信息，展示在各个表单文本框中*/
String goods_id = request.getParameter("goods_id");
GoodsInfoDao gid = new GoodsInfoDaoImpl();
GoodsInfo gi = new GoodsInfo();
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
gi = gid.loadRegardlessOfOnSite(goods_id);
keys = kd.loadByCategory(gi.getGoods_cat());
request.setAttribute("goodsinfo", gi);
request.setAttribute("categories", cats);
request.setAttribute("keywords", keys);
request.setAttribute("all_keywords", all_keys);
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>后台管理-修改商品信息页</title>
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
    					<h2 class="miri_h2">商品管理--修改商品信息</h2>
    					<div class="space20px"></div>
    					<div class="miri_form">
							<form action="/shopping/admin/adminUpdateGoodsDetail.do" method="post" enctype="multipart/form-data">
								<input type="hidden" value="${goodsinfo.goods_id}" name="goods_id">
								<table border="0" cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<th>商品所属分类</th>
										<td>请选择一级分类：&nbsp;
											<select name="goods_categoryfirst" class="goods_categoryfirst">
												<c:forEach items="${requestScope.categories}" var="category" varStatus="stat">
													<c:choose>
													<c:when test="${category.cat_id == goodsinfo.goods_cat}">
														<option value="${category.cat_id}" selected="selected">${category.cat_name}</option>
													</c:when>
													<c:otherwise>
														<option value="${category.cat_id}">${category.cat_name}</option>
													</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											请选择二级分类：&nbsp;
											<select name="goods_categorysecond" class="goods_categorysecond">
												<c:forEach items="${requestScope.keywords}" var="keyword" varStatus="stat">
													<c:choose>
													<c:when test="${keyword.key_id == goodsinfo.goods_key}">
														<option value="${keyword.key_id}" selected="selected">${keyword.key_name}</option>
													</c:when>
													<c:otherwise>
														<option value="${keyword.key_id}">${keyword.key_name}</option>
													</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<th>标题</th>
										<td>
											<input type="text" name="goods_title" class="goods_title" placeholder="请输入标题信息..." style="width: 500px;" value="${goodsinfo.goods_name}"/>
										</td>
									</tr>
									<tr>
										<th>描述</th>
										<td>
											<textarea name="goods_desc" rows="4" cols="81" placeholder="请输入描述内容..." style="resize: none;" >${goodsinfo.goods_info}</textarea>
										</td>
									</tr>
									<tr>
										<th>图片</th>
										<td>
											<table border="0" cellspacing="0" cellpadding="0">
												<tr>
													<th>商品图</th>
													<th>商品图</th>
													<th>商品图</th>
													<th>商品图</th>
													<th>商品图</th>
												</tr>
												<tr>
													<td>
														<div id="goods_div_img01">
															<img src="/goodsimg/${goodsinfo.goods_img.split(',')[0]}" style="width: 100px;height: 100px;" id="goods_img_img01"/>
														</div>
													</td>
													<td>
														<div id="goods_div_img02">
															<img src="/goodsimg/${goodsinfo.goods_img.split(',')[1]}" style="width: 100px;height: 100px;;" id="goods_img_img02"/>
														</div>
													</td>
													<td>
														<div id="goods_div_img03">
															<img src="/goodsimg/${goodsinfo.goods_img.split(',')[2]}" style="width: 100px;height: 100px;" id="goods_img_img03"/>
														</div>
													</td>
													<td>
														<div id="goods_div_img04">
															<img src="/goodsimg/${goodsinfo.goods_img.split(',')[3]}" style="width: 100px;height: 100px;" id="goods_img_img04"/>
														</div>
													</td>
													<td>
														<div id="goods_div_img05">
															<img src="/goodsimg/${goodsinfo.goods_img.split(',')[4]}" style="width: 100px;height: 100px;" id="goods_img_img05"/>
														</div>
													</td>
													
													
												</tr>
												<tr>
													<td>
														<div id="">
															<input type="file" name="goods_img01" class="goods_img01" style="width: 120px;" 
																onchange="PreviewImage(this,'goods_img_img01','goods_div_img01');"/>
														</div>
													</td>
													<td>
														<div id="">
															<input type="file" name="goods_img01" class="goods_img02" style="width: 120px;" 
																onchange="PreviewImage(this,'goods_img_img02','goods_div_img02');"/>
														</div>
													</td>
													<td>
														<div id="">
															<input type="file" name="goods_img01"  class="goods_img03" style="width: 120px;" 
																onchange="PreviewImage(this,'goods_img_img03','goods_div_img03');"/>
														</div>
													</td>
													<td>
														<div id="">
															<input type="file" name="goods_img01" class="goods_img04" style="width: 120px;" 
																onchange="PreviewImage(this,'goods_img_img04','goods_div_img04');"/>
														</div>
													</td>
													<td>
														<div id="">
															<input type="file" name="goods_img01" class="goods_img05" style="width: 120px;" 
																onchange="PreviewImage(this,'goods_img_img05','goods_div_img05');"/>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<th>价格</th>
										<td>
											市场价：&nbsp;&nbsp;<input type="text" name="goods_mprice" class="goods_oriprice" placeholder="例如：18.80" style="width: 160px;" value="${goodsinfo.goods_oriprice}"/>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											优惠价：&nbsp;&nbsp;<input type="text" name="goods_rprice" class="goods_curprice" placeholder="例如：16.80" style="width: 160px;" value="${goodsinfo.goods_curprice}"/>
										</td>
									</tr>
									<tr>
										<th>库存</th>
										<td>
											<input type="text" name="goods_stock" class="goods_ttnum" placeholder="例如：888" style="width: 220px;" value="${goodsinfo.goods_ttnum}"/>
										</td>
									</tr>
									<tr>
										<th>&nbsp;</th>
										<td>
											<input type="submit" value="确认提交修改" />
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
    	<script src="js/PreviewImage.js" type="text/javascript" charset="utf-8"></script>
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
	    	
	    	/*下面是拿到二级分类的下拉选择框内容，只要一级分类选择变了，那么动态发一个ajax到后台取出对应的二级分类*/
	    	$(".goods_categoryfirst").change(function(){
	    		var cat_id_val = $(".goods_categoryfirst").val();
				var _html = "<option value='default'>--请选择--</option>";
				$.ajax({
					type:"post",
					url:"/shopping/admin/adminGetGoodsKeyByCat.do",
					data:{"cat_id":cat_id_val},
					success:function(data){
						keys = eval("("+data+")");
						for (var i = 0; i < keys.length; i++) {
							_html += "<option value='"+keys[i].key_id+"'>"+keys[i].key_name+"</option>";
						}
						$(".goods_categorysecond").html(_html);
					}
				});
	    	});
	    	
	    	/*下面是jquery控制前台数据的，每一项都必须要填，最后三个应该是数字，如果都符合条件，才能提交*/
	    	$("form").submit(function(){
	    		var cat_id_val = $(".goods_categoryfirst").val();
	    		var key_id_val = $(".goods_categorysecond").val();
	    		var goods_name_val = $(".goods_title").val();
	    		var goods_info_val = $("textarea").val();
	    		var goods_img01_val = $(".goods_img01").val();
	    		var goods_img02_val = $(".goods_img02").val();
	    		var goods_img03_val = $(".goods_img03").val();
	    		var goods_img04_val = $(".goods_img04").val();
	    		var goods_img05_val = $(".goods_img05").val();
	    		var goods_oriprice_val = $(".goods_oriprice").val();
	    		var goods_curprice_val = $(".goods_curprice").val();
	    		var goods_ttnum_val = $(".goods_ttnum").val();
	    		var integer_test = new RegExp("^[0-9]*[0-9][0-9]*$");
	    		var float_test = new RegExp("^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$");
	    		if(key_id_val == "default") {
	    			layer.msg("二级分类不能为空！");
	    			return false;
	    		}
	    		if(goods_name_val == "") {
	    			layer.msg("商品名不能为空！");
	    			return false;
	    		}
	    		if(goods_info_val == "") {
	    			layer.msg("商品简介不能为空！");
	    			return false;
	    		}
	    		if(goods_oriprice_val == "") {
	    			layer.msg("商品市场价不能为空！");
	    			return false;
	    		}
	    		if(goods_curprice_val == "") {
	    			layer.msg("商品优惠价不能为空！");
	    			return false;
	    		}
	    		if(goods_ttnum_val == "") {
	    			layer.msg("商品库存不能为空！");
	    			return false;
	    		}
	    		if(!float_test.test(goods_oriprice_val)) {
	    			layer.msg("商品市场价应该为数字，且为正数！");
	    			return false;
	    		}
	    		if(!float_test.test(goods_curprice_val)) {
	    			layer.msg("商品优惠价应该为数字，且为正数！");
	    			return false;
	    		}
	    		if(!integer_test.test(goods_ttnum_val)) {
	    			layer.msg("商品库存应该为数字，且为非负整数！");
	    			return false;
	    		}
	    	});
		});
		</script> 
 	</body>
</html>