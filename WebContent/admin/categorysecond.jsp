<%@page import="com.shopping.entity.Page"%>
<%@page import="com.shopping.entity.Keyword"%>
<%@page import="com.shopping.daoimpl.KeywordDaoImpl"%>
<%@page import="com.shopping.dao.KeywordDao"%>
<%@page import="com.shopping.entity.Category"%>
<%@page import="com.shopping.daoimpl.CategoryDaoImpl"%>
<%@page import="com.shopping.dao.CategoryDao"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
CategoryDao cd = new CategoryDaoImpl();
List<Category> cats = new ArrayList<Category>();
Category cat = new Category();
cats = cd.selectAll();

List<Keyword> keys = new ArrayList<Keyword>();
List<Keyword> keys_onsite = new ArrayList<Keyword>();
KeywordDao kd = new KeywordDaoImpl();
for(int i = 0; i < cats.size(); i++) {
	cat = new Category();
	cat = cats.get(i);
	keys = new ArrayList<Keyword>();
	keys = kd.loadByCategory(cat.getCat_id());
	cat.setKeys(keys);
	cats.set(i, cat);
}
request.setAttribute("categories", cats);

/*这里先判断session里面是否存在二级分类的分页对象，如果不存在就初始化一个对象再绑定到session里面*/
if(request.getSession().getAttribute("page_keys") == null) {
	Page p = new Page();
	p.setTotalRows(kd.getTotalRows());
	p.setPageSize(2);
	p.setCurrentPage(1);
	p.setTotalPage(p.getTotalPage());
	keys = new ArrayList<Keyword>();
	keys = kd.loadAll(p.getOffsetRows(), p.getPageSize());
	Keyword kw = new Keyword();
	for(int i = 0; i < keys.size(); i++) {
		kw = new Keyword();
		kw = keys.get(i);
		kw.setCat_name(cd.load(kw.getCat_id()).getCat_name());
		keys.set(i, kw);
	}
	
	p.setKeys(keys);
	
	request.getSession().setAttribute("page_keys", p);
}
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>后台管理-商品二级分类管理</title>
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
    					<h2 class="miri_h2">商品二级分类管理<small>(每一个一级分类下面最多添加10个二级分类)</small></h2>
    					<div class="miri_form">
    						<form class="" action="/shopping/admin/adminAddKeyword.do" method="post">
    							<table border="0" cellspacing="0" cellpadding="0" width="100%">
    								<tr>
    									<th>新增加商品二级分类&nbsp;&nbsp;</th>
    									<td width="350">
    										<select name="categrayfirst">
    										
    										<!-- 这里后面加一个判断，如果没有一级分类就让先添加一级分类，后面的servlet也要加一个判断 -->
    										
    											<option value="default">--请选择--</option>
    												<c:forEach items="${requestScope.categories}" var="category" varStatus="status">
    													<option value="${category.cat_id}">${category.cat_name}</option>
    												</c:forEach>
    										</select>
    										<input type="text" name="categraysecond" class="categraysecond" placeholder="请输入二级分类名称..." />
    									</td>
    									<td>
    										<input type="submit" value="确认添加"/>
    									</td>
    								</tr>
    							</table>
    						</form>
    						<!-- 下面循环拿到所有二级分类的值并展示出来 -->
    						<table border="0" cellspacing="0" cellpadding="0" width="100%">
    							<tr><th>No.</th><th>ID</th><th>二级分类名称</th><th>所属一级分类名称</th><th>Admin</th><th>AddTime</th><th>操作</th></tr>
    								<c:forEach items="${sessionScope.page_keys.keys}" var="keyword" varStatus="status">
										<tr>
											<td>${status.index+1}</td><td>${keyword.key_id}</td><td>${keyword.key_name}</td>
											<td>${keyword.cat_name}</td>
											<td>${keyword.administrator_name}</td><td>${keyword.key_createtime}</td>
											<td><a href="JavaScript:void(0);">修改</a></td>
											<td><a href="JavaScript:void(0);">删除</a></td>
										</tr>
									</c:forEach>
    						</table>
    					</div>
    					<div class="space20px"></div>
    					<!-- 下面是分页的模块 -->
						<div class="mir_page">
							<span>当前第&nbsp;<b>${sessionScope.page_keys.currentPage}</b>&nbsp;页，共计&nbsp;<b>${page_keys.totalPage}</b>&nbsp;页</span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span>
								每页显示&nbsp;
								<select name="page_size" class="page_size">
									<%-- <option value="default">${page_keys.pageSize}</option> --%>
									<c:choose>
									<c:when test="${sessionScope.page_keys.pageSize == 2}">
										<option value='2' selected="selected">2</option>
										<option value='3'>3</option>
										<option value='5'>5</option>
										<option value='10'>10</option>
										<option value='15'>15</option>
									</c:when>
									<c:when test="${sessionScope.page_keys.pageSize == 3}">
										<option value='2'>2</option>
										<option value='3' selected="selected">3</option>
										<option value='5'>5</option>
										<option value='10'>10</option>
										<option value='15'>15</option>
									</c:when>
									<c:when test="${sessionScope.page_keys.pageSize == 5}">
										<option value='2'>2</option>
										<option value='3'>3</option>
										<option value='5' selected="selected">5</option>
										<option value='10'>10</option>
										<option value='15'>15</option>
									</c:when>
									<c:when test="${sessionScope.page_keys.pageSize == 10}">
										<option value='2'>2</option>
										<option value='3'>3</option>
										<option value='5'>5</option>
										<option value='10' selected="selected">10</option>
										<option value='15'>15</option>
									</c:when>
									<c:when test="${sessionScope.page_keys.pageSize == 15}">
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
									<c:forEach begin="1" end="${page_keys.totalPage}" var="i"> 
										<c:choose>
										<c:when test="${sessionScope.page_keys.currentPage == i}">
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
    	
    	/*后台request.setAttribute()设置错误信息，前台用EL表达式拿到，再用layer.confirm()提示用户
    	为了防止重复提示，用户点完确认以后再重定向到本页面 */
    	var wrong_msg = "${keyNameWrongMsg}";
    	//alert(wrong_msg);
    	if(wrong_msg != "") {
    		layer.confirm(wrong_msg, {
				 btn: ["确认"] 
				,btn1: function(index, layero){
				    window.location.href = "/shopping/admin/categorysecond.jsp";
				 }
			});
    	}
    	
    	/*下面三个方法都是用ajax提交到后台然后更改分页对象
  	  更改完了要刷新页面，不然分页状态没法显示出来
  	  中间稍微对当前页做了一个判断，如果是点上一页会给一个提示，
  	  如果是最后一页， 点下一页会给一个提示*/
    	$(".page_size").change(function(){
    		var page_size_val = $(".page_size").val();
    		$.ajax({
    			type:"post",
    			url:"/shopping/admin/adminKeywordsUpdatePages.do",
    			data:{"pageSize_str":page_size_val,"from":"page_size"},
    			success:function(){
    				 location.reload();
    			}
    		});
    	});
    	
    	$(".mirp_btn").click(function(){
    		var current_page_val = $(this).text();
    		var total_pages_now_val = ${sessionScope.page_keys.totalPage};
    		var current_page_now_val = ${sessionScope.page_keys.currentPage};
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
	    			url:"/shopping/admin/adminKeywordsUpdatePages.do",
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
    			url:"/shopping/admin/adminKeywordsUpdatePages.do",
    			data:{"currentPage_str":current_page_val,"from":"current_page"},
    			success:function(){
    				 location.reload();
    			}
    		});
    	});
    	
    	//这里后面试着加一个change事件，如果选择了某个一级分类，看看能不能直接拿到这个分类下面的关键词的数量，如果等于10，给一个提示不能再添加了
    	/* 
    	$(".page_size").change(function(){
    		page_size_val = $(this).children(":selected").val();
    		$.ajax({
    			type:"post",
    			url:"/shopping/admin/getKeywordPage.do",
    			data:{"pageSize":page_size_val,"from":"change_size"},
    			success:function(data){
    				page_keys = eval("("+data+")");
    				var _html = "tr><th>No.</th><th>ID</th><th>二级分类名称</th><th>所属一级分类名称</th><th>Admin</th><th>AddTime</th><th>操作</th></tr>";
    				for (var i = 0; i < page_keys.keys.length; i++) {
						_html += "<tr>"
							+ "<td>"+i+1+"</td><td>"+page_keys.keys[i].key_id+"</td><td>"+page_keys.keys[i].key_name+"</td>"
							+ "<td>"+page_keys.keys[i].cat_name+"</td>"
							+ "<td>"+page_keys.keys[i].administrator_name+"</td><td>"+page_keys.keys[i].key_createtime+"</td>"
							+ "<td><a href='JavaScript:void(0);'>修改</a></td>"
							+ "<td><a href='JavaScript:void(0);'>删除</a></td>"
						    + "</tr>";
					}
    				$(".miri_form table").html(_html);
    				_html = "<span>当前第&nbsp;<b>"+page_keys.currentPage+"</b>&nbsp;页，共计&nbsp;<b>"+page_keys.totalPage+"</b>&nbsp;页</span>"
						  + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					      + "<span>"
					      + "每页显示&nbsp;"
						  + "<select name='page_size' class='page_size'>";
					switch (page_keys.pageSize) {
					case 2:
						_html +=  "<option value='2' selected='selected'>2</option>"
						  		+ "<option value='3'>3</option>"
							    + "<option value='5'>5</option>"
						 	    + "<option value='10'>10</option>"
						        + "<option value='15'>15</option>";
						break;
					case 3:
						_html +=  "<option value='2'>2</option>"
						  		+ "<option value='3' selected='selected'>3</option>"
							    + "<option value='5'>5</option>"
						 	    + "<option value='10'>10</option>"
						        + "<option value='15'>15</option>";
						break;
					case 5:
						_html +=  "<option value='2'>2</option>"
						  		+ "<option value='3'>3</option>"
							    + "<option value='5' selected='selected'>5</option>"
						 	    + "<option value='10'>10</option>"
						        + "<option value='15'>15</option>";
						break;
					case 10:
						_html +=  "<option value='2'>2</option>"
						  		+ "<option value='3'>3</option>"
							    + "<option value='5'>5</option>"
						 	    + "<option value='10' selected='selected'>10</option>"
						        + "<option value='15'>15</option>";
						break;
					case 15:
						_html +=  "<option value='2'>2</option>"
						  		+ "<option value='3'>3</option>"
							    + "<option value='5'>5</option>"
						 	    + "<option value='10'>10</option>"
						        + "<option value='15' selected='selected'>15</option>";
						break;
					default:
						_html +=  "<option value='2'>2</option>"
				  				+ "<option value='3'>3</option>"
					  		  	+ "<option value='5'>5</option>"
				 	  		  	+ "<option value='10'>10</option>"
				      		 	+ "<option value='15'>15</option>";
						break;
					}
					_html += "</select>"
						  + "&nbsp;条"
						  + "</span>"
						  + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						  + "<a class='mirp_btn' href='JavaScript:void(0);'>首　页</a>"
						  + "&nbsp;&nbsp;&nbsp;&nbsp;"
						  + "<a class='mirp_btn' href='JavaScript:void(0);'>上一页</a>"
						  + "&nbsp;&nbsp;&nbsp;&nbsp;"
						  + "<a class='mirp_btn' href='JavaScript:void(0);'>下一页</a>"
						  + "&nbsp;&nbsp;&nbsp;&nbsp;"
						  + "<a class='mirp_btn' href='JavaScript:void(0);'>末　页</a>"
						  + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						  + "<span>"
						  + "前往第&nbsp;"
						  + "<select name='toPage' class='toPage'>";
					for (var j = 0; j < page_keys.totalPage; j++) {
						_html += "<option value="+j+1+">"+j+1+"</option>";
					}
					_html += "</select>"
						  + "&nbsp;页"
						  + "</span>";
					$(".mir_page").html(_html);
    			}
    		});
    	});
    	 */
	});
    </script>	
 	</body>
</html>